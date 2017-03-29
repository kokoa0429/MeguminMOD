package com.google.kobatokokoa0429.Item;

import com.google.kobatokokoa0429.MeguminMOD;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemManatite extends Item {

	/**
	 * 変更・クリエイティブタブの設定をここに変更。 変更・setTextureNameの引数のドメイン部分をModIDに
	 */
	public ItemManatite() {
		setUnlocalizedName("itemManatite");
		setTextureName(MeguminMOD.MOD_ID + ":manataito_item");
		setCreativeTab(MeguminMOD.tabMeguminMOD);
		setMaxStackSize(16);


	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		// System.out.println("kobatoright");
		// player.openGui(MeguminMOD.INSTANCE, MeguminMOD.GUI_ID, world,
		// MathHelper.ceiling_double_int(player.posX),
		// MathHelper.ceiling_double_int(player.posY),
		// MathHelper.ceiling_double_int(player.posZ));
		// MeguminMOD.INSTANCE

		return itemStack;
	}
}
