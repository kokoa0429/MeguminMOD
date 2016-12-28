package com.google.kobatokokoa0429.GUI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class MeguminContainer extends Container{
    //座標でGUIを開くか判定するためのもの。
    int xCoord, yCoord, zCoord;
    public MeguminContainer(int x, int y, int z) {
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
    }
 
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        //もし、ブロックとの位置関係でGUI制御したいなら、こちらを使う
//        return player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64D;
        return true;
    }
}
