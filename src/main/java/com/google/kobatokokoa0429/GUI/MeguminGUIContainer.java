package com.google.kobatokokoa0429.GUI;

import com.google.kobatokokoa0429.GUI.MeguminContainer;
import com.google.kobatokokoa0429.MeguminMOD;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class MeguminGUIContainer  extends GuiContainer{

    /**変更・ドメイン部分を変数に変更*/
    private static final ResourceLocation TEXTURE = new ResourceLocation(MeguminMOD.Domein, "textures/gui/gui_test.jpg");
    public MeguminGUIContainer(int x, int y, int z) {
        super(new MeguminContainer(x, y, z));
    }

    /*GUIの文字等の描画処理*/
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseZ) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseZ);
    }

    /*GUIの背景の描画処理*/
    /**削除・デバックコード*/
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseZ) {
    	//背景描画
        this.mc.renderEngine.bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);

    }

    /*GUIが開いている時にゲームの処理を止めるかどうか。*/
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}