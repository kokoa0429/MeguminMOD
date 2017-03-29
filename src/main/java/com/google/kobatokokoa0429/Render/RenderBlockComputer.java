package com.google.kobatokokoa0429.Render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.kobatokokoa0429.MeguminMOD;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderBlockComputer extends TileEntitySpecialRenderer {
	/** ModIDの大文字化のため、ModIDの代わりに別の変数を用意。 */
	IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(MeguminMOD.Domein, "obj/Laptop_High-Polay_HP_BI_2_obj.obj"));
	//ResourceLocation texture = new ResourceLocation(MeguminMOD.Domein, "textures/blocks/Bake_Col_3.jpg");
	//ResourceLocation texture2 = new ResourceLocation(MeguminMOD.Domein, "textures/blocks/Desktop.png");

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_) {
		//bindTexture(texture);
		//bindTexture(texture2);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 0.5F);
		GL11.glScalef(0.09375F, 0.09375F, 0.09375F);
		model.renderAll();
		GL11.glPopMatrix();
	}
}
