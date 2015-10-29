package net.doctorocclusion.festivities4.entity;

import net.doctorocclusion.festivities4.Festivities;
import net.doctorocclusion.festivities4.entity.lights.EntityBlockLights;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class FestiveEntities
{
	public static void registerEntities()
	{
		int id = 0;
		
		EntityRegistry.registerModEntity(EntityBlockLights.class, "FestBlockLights", id++, Festivities.instance, 32, 600, false);
	}
}
