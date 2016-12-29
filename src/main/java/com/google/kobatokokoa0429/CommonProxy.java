package com.google.kobatokokoa0429;

import com.google.kobatokokoa0429.TileEntity.TueTileEntity;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy {

	public EntityPlayer getEntityPlayerInstance() {
		return null;
	}

	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	public void registerRenderers() {

	}

	public void registerTileEntity() {
		GameRegistry.registerTileEntity(TueTileEntity.class, "tue");
	}

}
