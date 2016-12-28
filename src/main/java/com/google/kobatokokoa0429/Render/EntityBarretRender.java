package com.google.kobatokokoa0429.Render;


import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.kobatokokoa0429.Entity.EntityBarret;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class EntityBarretRender extends Render
{


	public void renderArrow(EntityBarret par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
	    //IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(MeguminMOD.MOD_ID,"obj/hikati.obj"));
	    //ResourceLocation texture = new ResourceLocation(MeguminMOD.MOD_ID,"textures/blocks/skin.jpg");
        //bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
        GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6);
        GL11.glRotatef(par1Entity.prevRotationYaw + (par1Entity.rotationYaw - par1Entity.prevRotationYaw) * par9, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(par1Entity.prevRotationPitch + (par1Entity.rotationPitch - par1Entity.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
       // model.Render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        //model.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

//    protected ResourceLocation getArrowTextures(EntityVillagerHead par1EntityArrow)
//    {
//        return getArrowTextures(texture);
//    }

//    protected ResourceLocation getEntityTexture(Entity par1Entity)
//    {
//        return this.getArrowTextures((EntityVillagerHead)par1Entity);
//    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderArrow((EntityBarret)par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity arg0) {
		return null;
	}
}