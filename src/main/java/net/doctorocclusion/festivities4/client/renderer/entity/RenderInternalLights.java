package net.doctorocclusion.festivities4.client.renderer.entity;

import org.lwjgl.opengl.GL11;

import net.doctorocclusion.festivities4.entity.lights.EntityLightsInternal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderInternalLights extends Render
{
	public static final ResourceLocation lights = new ResourceLocation("festivities4:textures/entity/lights_plain.png");
	
	protected RenderInternalLights(RenderManager renderManager)
	{
		super(renderManager);
	}
	
	public void doRender(EntityLightsInternal entity, double x, double y, double z, float rotate, float partialTicks)
	{
		GlStateManager.pushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		this.setLightmap(1.0f);
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(180.0F - rotate, 0.0F, 1.0F, 0.0F);
		GlStateManager.enableRescaleNormal();
		this.bindEntityTexture(entity);
		
		float scale = 0.0625F;
		GlStateManager.scale(scale, scale, scale);
		// do render
		GlStateManager.disableRescaleNormal();
		GL11.glEnable(GL11.GL_LIGHTING);
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, rotate, partialTicks);
	}
	
	public void setLightmap(float light)
	{
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240 * light, 240 * light);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
	{
		this.doRender((EntityLightsInternal) entity, x, y, z, p_76986_8_, partialTicks);
	}
	
	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return lights;
	}
}
