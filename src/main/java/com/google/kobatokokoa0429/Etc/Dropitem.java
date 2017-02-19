package com.google.kobatokokoa0429.Etc;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Dropitem {

	int px;
	int py;
	int pz;
	int qx;
	int qy;
	int qz;
	World world;
	Block block;

	Random rnd = new Random();

	public Dropitem(int x, int y, int z, World worldObj,int ax ,int ay , int az) {
		// TODO 自動生成されたコンストラクター・スタブ
		px = x;
		py = y;
		pz = z;

		qx = ax;
		qy = ay;
		qz = az;

		world = worldObj;

		block = worldObj.getBlock(x, y, z);
	}

	public void dropitem() {
		// TODO 自動生成されたメソッド・スタブ

		if (block != Blocks.air && rnd.nextInt(40) < 1) {

			EntityItem item = new EntityItem(world, (double) qx, (double) qy, (double) qz,
					new ItemStack(block.getItemDropped(0, rnd, 1), 1));
			world.spawnEntityInWorld(item);

		}
	}

}
