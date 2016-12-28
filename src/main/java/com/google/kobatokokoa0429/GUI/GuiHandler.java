package com.google.kobatokokoa0429.GUI;

import com.google.kobatokokoa0429.MeguminMOD;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    /*サーバー側の処理*/
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == MeguminMOD.GUI_ID) {
            return new MeguminContainer(x, y, z);
        }
        return null;
    }
    /*クライアント側の処理*/
    /**削除・デバックコード*/
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == MeguminMOD.GUI_ID) {
            return new MeguminGUIContainer(x, y, z);
        }
        return null;
    }

}
