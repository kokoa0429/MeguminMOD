package com.google.kobatokokoa0429.Block;

import com.google.kobatokokoa0429.MeguminMOD;
import com.google.kobatokokoa0429.Energy.EnergyManager;
import com.google.kobatokokoa0429.TileEntity.energytile;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockKobato extends BlockContainer {

	public BlockKobato() {
		super(Material.rock);
		this.setCreativeTab(MeguminMOD.tabMeguminMOD);
		this.setBlockTextureName(MeguminMOD.MOD_ID+":blockkobato");
		this.setTickRandomly(true);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		//ブロックの設置時に生成される

		System.out.println("こばとかわいい");

		return new energytile();
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);

		EnergyManager enm = new EnergyManager(par1World, par2, par3, par4);

		enm.setCanhaveenergy(true);
		enm.setGenerate(true);
		enm.setGenerateenergy(100);

	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {

		System.out.println("小鳩と結婚したい");

		EnergyManager enm = new EnergyManager(par1World, par2, par3, par4);

		if (!par1World.isRemote) {

			par5EntityPlayer.addChatMessage(new ChatComponentText(Integer.toString(enm.getEnergy())));

		}
		return true;
	}

}
