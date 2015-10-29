package net.doctorocclusion.festivities4.client.renderer.entity;

import org.lwjgl.opengl.GL11;

import net.doctorocclusion.festivities4.entity.lights.EntityBlockLights;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBlockLights extends Render
{
	public static final ResourceLocation lights = new ResourceLocation("festivities4:textures/entity/lights_plain.png");
	
	public static float[][][] twinkle = new float[8][3][2];
	
	public RenderBlockLights(RenderManager renderManager)
	{
		super(renderManager);
	}
	
	public void doRender(EntityBlockLights entity, double x, double y, double z, float rotate, float partialTicks)
	{
		this.bindEntityTexture(entity);
		GlStateManager.pushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GlStateManager.translate(x, y, z);
		GlStateManager.disableRescaleNormal();
		this.bindEntityTexture(entity);
		
		GlStateManager.scale(1, 1, 1);
		int color = entity.color;
		GlStateManager.color(((color >> 16) & 0xFF) / 255.0F, ((color >> 8) & 0xFF) / 255.0F, ((color >> 0) & 0xFF) / 255.0F, 1.0F);
		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		if (entity.twinkle)
		{
			int tex = entity.randTex;
			for (int i = 0; i < 3; i++)
			{
				this.setLightmap(twinkle[entity.randTime][i][1] * partialTicks + twinkle[entity.randTime][i][0] * (1 - partialTicks));
				this.renderGeo(-0.5, -0.5, -0.5, 0.5, 0.5, 0.5, tex++ / 16.0F, tex / 16.0F, 0, 1);
				tex %= 16;
			}
		}
		else
		{
			this.setLightmap(1.0f);
			this.renderGeo(-0.5, -0.5, -0.5, 0.5, 0.5, 0.5, entity.randTex / 16.0F, (entity.randTex + 1) / 16.0F, 0, 1);
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, rotate, partialTicks);
	}
	
	public void renderGeo(double x0, double y0, double z0, double x1, double y1, double z1, double u0, double u1, double v0, double v1)
	{
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		
		// worldrenderer.setBrightness(1);
		// worldrenderer.putNormal(0, 1, 0);
		
		worldrenderer.addVertexWithUV(x0, y0, z0, u0, v0);
		worldrenderer.addVertexWithUV(x0, y1, z0, u0, v1);
		worldrenderer.addVertexWithUV(x1, y1, z1, u1, v1);
		worldrenderer.addVertexWithUV(x1, y0, z1, u1, v0);
		
		worldrenderer.addVertexWithUV(x0, y0, z1, u0, v0);
		worldrenderer.addVertexWithUV(x0, y1, z1, u0, v1);
		worldrenderer.addVertexWithUV(x1, y1, z0, u1, v1);
		worldrenderer.addVertexWithUV(x1, y0, z0, u1, v0);
		
		worldrenderer.addVertexWithUV(x1, y0, z1, u1, v0);
		worldrenderer.addVertexWithUV(x1, y1, z1, u1, v1);
		worldrenderer.addVertexWithUV(x0, y1, z0, u0, v1);
		worldrenderer.addVertexWithUV(x0, y0, z0, u0, v0);
		
		worldrenderer.addVertexWithUV(x1, y0, z0, u1, v0);
		worldrenderer.addVertexWithUV(x1, y1, z0, u1, v1);
		worldrenderer.addVertexWithUV(x0, y1, z1, u0, v1);
		worldrenderer.addVertexWithUV(x0, y0, z1, u0, v0);
		
		tessellator.draw();
	}
	
	public void setLightmap(float light)
	{
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240 * light, 240 * light);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
	{
		this.doRender((EntityBlockLights) entity, x, y, z, p_76986_8_, partialTicks);
		super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
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
