package com.google.kobatokokoa0429.Etc;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Explosion {

	public void explosion(Entity shootingEntity, World worldObj, int xTile, int yTile, int zTile, int i, float f) {

	}

	public void bakuretu(Entity shootingEntity, World worldObj, int xT, int yT, int zT, int r, float power) {

		Random rnd = new Random();
		int rndint = rnd.nextInt(r / 2);
		r /= 2;
		r += rndint;

		for (int x = 0; x <= r; x++) {
			for (int y = 0; y * 2 <= r; y++) {
				for (int z = 0; z <= r; z++) {
					double dis = (x * x) + ((y * y) * 4) + (z * z);
					if (dis < r * r) {

						double block_damage = Math.abs(1 - dis / (r * r)); // 距離の二乗を
																			// 0
																			// -
																			// 1で表したもの
						double b = Math.sqrt(block_damage);
						b = Math.abs(b - 0.5);
						b *= b;
						block_damage += (2 * b);

						if (worldObj.getBlock((xT + x), (yT + y), (zT + z))
								.getExplosionResistance(shootingEntity) < block_damage * power) {

							Dropitem drop = new Dropitem((xT + x), (yT + y), (zT + z), worldObj);

							worldObj.setBlock((xT + x), (yT + y), (zT + z), Blocks.air);

							drop.dropitem();

						}
						if (worldObj.getBlock((xT + x), (yT + y), (zT - z))
								.getExplosionResistance(shootingEntity) < block_damage * power) {

							Dropitem drop = new Dropitem((xT + x), (yT + y), (zT - z), worldObj);
							worldObj.setBlock((xT + x), (yT + y), (zT - z), Blocks.air);
							drop.dropitem();

						}
						if (worldObj.getBlock((xT + x), (yT - y), (zT + z))
								.getExplosionResistance(shootingEntity) < block_damage * power) {

							Dropitem drop = new Dropitem((xT + x), (yT - y), (zT + z), worldObj);
							worldObj.setBlock((xT + x), (yT - y), (zT + z), Blocks.air);
							drop.dropitem();

						}
						if (worldObj.getBlock((xT + x), (yT - y), (zT - z))
								.getExplosionResistance(shootingEntity) < block_damage * power) {

							Dropitem drop = new Dropitem((xT + x), (yT - y), (zT - z), worldObj);
							worldObj.setBlock((xT + x), (yT - y), (zT - z), Blocks.air);
							drop.dropitem();

						}
						if (worldObj.getBlock((xT - x), (yT + y), (zT + z))
								.getExplosionResistance(shootingEntity) < block_damage * power) {

							Dropitem drop = new Dropitem((xT - x), (yT + y), (zT + z), worldObj);
							worldObj.setBlock((xT - x), (yT + y), (zT + z), Blocks.air);
							drop.dropitem();

						}
						if (worldObj.getBlock((xT - x), (yT + y), (zT - z))
								.getExplosionResistance(shootingEntity) < block_damage * power) {

							Dropitem drop = new Dropitem((xT - x), (yT + y), (zT - z), worldObj);
							worldObj.setBlock((xT - x), (yT + y), (zT - z), Blocks.air);
							drop.dropitem();

						}
						if (worldObj.getBlock((xT - x), (yT - y), (zT + z))
								.getExplosionResistance(shootingEntity) < block_damage * power) {

							Dropitem drop = new Dropitem((xT - x), (yT - y), (zT + z), worldObj);
							worldObj.setBlock((xT - x), (yT - y), (zT + z), Blocks.air);
							drop.dropitem();

						}
						if (worldObj.getBlock((xT - x), (yT - y), (zT - z))
								.getExplosionResistance(shootingEntity) < block_damage * power) {

							Dropitem drop = new Dropitem((xT - x), (yT - y), (zT - z), worldObj);
							worldObj.setBlock((xT - x), (yT - y), (zT - z), Blocks.air);
							drop.dropitem();
						}

					}
				}
			}
		}

	}

}
