package com.google.kobatokokoa0429.Block;

import java.util.Random;

import com.google.kobatokokoa0429.MeguminMOD;
import com.google.kobatokokoa0429.TileEntity.TueTileEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWand extends BlockContainer {

	public BlockWand() {
		/**
		 * 変更・クリエイティブタブの設定をここに移動。 変更・setBlockTextureNameのドメイン部分をModIDに変更。
		 */
		super(Material.grass);
		setCreativeTab(MeguminMOD.tabMeguminMod);
		setBlockName("blockTue");
		setHardness(0F);/* 硬さ */
		setResistance(100.0F);/* 爆破耐性 */
		setBlockBounds(0.0F, 0, 0.0F, 1.0F, 3.0F, 1.0F);
		setLightLevel(15.0F);
		setBlockTextureName(MeguminMOD.MOD_ID + ":tue3");
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public Item getItemDropped(int par1, Random random, int par3) {
		return MeguminMOD.itemWand;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return false;
	}

	/** 削除・デバックコード */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		player.openGui(MeguminMOD.INSTANCE, MeguminMOD.GUI_ID, world, x, y, z);
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TueTileEntity();
	}
}
