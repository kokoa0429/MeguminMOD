package com.google.kobatokokoa0429.Handler;

import java.util.Random;

import com.google.kobatokokoa0429.MeguminMOD;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class ManataitoOreGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		if (world.provider instanceof WorldProviderSurface) {
			generateOre(world, random, chunkX << 4, chunkZ << 4);
		}
	}

	private void generateOre(World world, Random random, int x, int z) {
		for (int i = 0; i < 3; i++) {
			int genX = x + random.nextInt(1);
			int genY = 6 + random.nextInt(2);
			int genZ = z + random.nextInt(1);
			new WorldGenMinable(MeguminMOD.blockMatatite, 0, 4, Blocks.stone).generate(world, random, genX, genY, genZ);
		}
	}

}