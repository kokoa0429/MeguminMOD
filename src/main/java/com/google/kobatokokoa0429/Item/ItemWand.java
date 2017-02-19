package com.google.kobatokokoa0429.Item;

import com.google.kobatokokoa0429.MeguminMOD;
import com.google.kobatokokoa0429.Entity.EntityBarret;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemWand extends ItemSword {

	private float field_150934_a;

	/**
	 * 変更・クリエイティブタブの設定をここに変更。 変更・setTextureNameの引数のドメイン部分をModIDに
	 */

	public ItemWand(Item.ToolMaterial par2) {
		super(par2);
		setUnlocalizedName("itemWand");
		setTextureName(MeguminMOD.MOD_ID + ":iconn");
		setCreativeTab(MeguminMOD.tabMeguminMod);
		setMaxStackSize(1);
	}

	/** コメント部分はとりあえず放置しておく */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer entityplayer, World world, int x, int y, int z, int par7,
			float par8, float par9, float par10) {

		if (par7 != 1) {
			return false;
		} else {
			if (entityplayer.isSneaking()) {
				if (entityplayer.canPlayerEdit(x, y + 1, z, par7, stack)) {
					world.setBlock(x, y + 1, z, MeguminMOD.blockTue);
					--stack.stackSize;
					return true;
				}
			} else {
				// world.createExplosion(entityplayer, x, y, z, 20F, true);
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityPlayer) {
		entityPlayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));

		this.setFull3D();
		// System.out.println("1");
		if (!world.isRemote) {
			// world.spawnEntityInWorld(new Entitybakuretu(world,
			// entityPlayer));
			//GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);

		}
		return itemstack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {

		//System.out.println("1");
		// par4は右クリックの押下時間。
		int j = this.getMaxItemUseDuration(ItemStack) - par4;
		//System.out.println(this.getMaxItemUseDuration(ItemStack));

		// 右クリック押下時間をもとに計算。20で割り（単位を秒に変換）、なにやら二次関数的な計算式に入れている。
		// ここではバニラ弓のまま使っているが、独自の計算式でも良いと思います。
		float f = (float) j / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		// タメ時間が一定以下の場合、何も起こさず処理から抜ける。
		if ((double) f < 1D) {
			return;
		}

		// fの上限値。
		if (f > 1.0F) {
			f = 1.0F;
		}

		EntityBarret bullet = new EntityBarret(par2World, par3EntityPlayer, f * 2.5F, 1.0F, 0.0F, 0.0F, 0.0F);

		par2World.playSoundAtEntity(par3EntityPlayer, "mob.villager.idle", 1.0F,
				1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

		if (!par2World.isRemote) {
			par2World.spawnEntityInWorld(bullet);

		}

	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}
}