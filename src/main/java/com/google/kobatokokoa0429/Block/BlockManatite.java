package com.google.kobatokokoa0429.Block;

import java.util.Random;

import com.google.kobatokokoa0429.MeguminMOD;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockManatite extends Block {

	public BlockManatite() {
		/**
		 * 変更・クリエイティブタブの設定をここに変更。 変更・setBlockTextureNameの引数のドメイン部分をModIDに
		 */
		super(Material.rock);
		this.setCreativeTab(MeguminMOD.tabMeguminMod);
		this.setHardness(3.0F);
		this.setBlockTextureName(MeguminMOD.MOD_ID + ":manataito");
		this.setBlockName("manataito");
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return MeguminMOD.itemManatite;
	}

}
