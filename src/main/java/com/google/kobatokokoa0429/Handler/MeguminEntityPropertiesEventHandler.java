package com.google.kobatokokoa0429.Handler;

import com.google.kobatokokoa0429.MeguminMOD;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class MeguminEntityPropertiesEventHandler {

	/** 削除・不要な関数。IExtendedPlayerProperties関係は今は必要ない。 */

	@SubscribeEvent
	/* ワールドに入った時に呼ばれるイベント。ここでIExtendedEntityPropertiesを読み込む処理を呼び出す */
	/**
	 * 削除・デバックコード 復帰・ログイン時のopenGUI 削除・不要なコメント部分
	 */
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (event.world.isRemote && event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			World world = player.getEntityWorld();
			int x = (int) player.posX;
			int y = (int) player.posY;
			int z = (int) player.posZ;
			player.openGui(MeguminMOD.INSTANCE, MeguminMOD.GUI_ID, world, x, y, z);
		}
	}
}
