package com.google.kobatokokoa0429.Render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.kobatokokoa0429.MeguminMOD;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class Renderitemtue implements IItemRenderer {

	/** ModIDの大文字化のため、ModIDの代わりに別の変数を用意。 */
	IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(MeguminMOD.Domein, "obj/tue22.obj"));
	ResourceLocation texture = new ResourceLocation(MeguminMOD.Domein, "textures/blocks/skin.png");
	ResourceLocation icon1 = new ResourceLocation(MeguminMOD.Domein, "textures/blocks/iconn.jpg");
	private static RenderItem renderItem = new RenderItem();

	@Override
	public boolean handleRenderType(ItemStack itemStack, ItemRenderType type) {
		// return true;//return itemStack.getItem() == Item.getItemFromBlock(
		// BlockRegistry.blockGerminator );
		switch (type) {
		case ENTITY:
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
			// case INVENTORY:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScalef(0.2F, 0.2F, 0.2F);
		GL11.glTranslatef(4f, -5.25f, 4f);
		// GL11.glRotatef(60, 0, 0, 1);
		model.renderAll();
		GL11.glPopMatrix();
	}
}