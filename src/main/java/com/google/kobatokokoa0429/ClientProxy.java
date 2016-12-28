package com.google.kobatokokoa0429;

import com.google.kobatokokoa0429.TileEntity.TueTileEntity;
import com.google.kobatokokoa0429.Entity.EntityBarret;
import com.google.kobatokokoa0429.Render.EntityBarretRender;
import com.google.kobatokokoa0429.Render.Renderitemtue;
import com.google.kobatokokoa0429.Render.Rendertue;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	//public static void bindTileEntitySpecialRenderer(Class <? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer specialRenderer){
	   //	ClientRegistry.bindTileEntitySpecialRenderer(te.class, new Rendertue());
	//}
	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	/*
	 * client側のTileEntity登録メソッド。
	 * ここでは使用していませんが、TileEntityに独自レンダーを持たせたい場合、
	 * ClientRegistry.registerTileEntity(TileEntityのクラス名.class, "TileEntityの名前", new TileEntityのレンダ―クラス());
	 * のように記述して、レンダークラスとTileEntityを登録します。
	 *
	 * レンダー関係のクラスはクライアント側にしか存在しないため、サーバ側で誤って登録しないよう、（このように）プロキシを通すなどの対策を必ず行います。
	 */
	@Override
	public void registerTileEntity() {
		ClientRegistry.registerTileEntity(TueTileEntity.class, "tue", new Rendertue());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MeguminMOD.blockTue), new Renderitemtue());
		MinecraftForgeClient.registerItemRenderer(MeguminMOD.itemWand, new Renderitemtue());
		RenderingRegistry.registerEntityRenderingHandler(EntityBarret.class, new EntityBarretRender());

	}
	
	@Override
	 
	public EntityPlayer getEntityPlayerInstance() {
	 
	return Minecraft.getMinecraft().thePlayer;
	 
	}
	//@Override
	//public void load() {
	//ClientRegistry.bindTileEntitySpecialRenderer(te.class, new Rendertue());
	//}
}