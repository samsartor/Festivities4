package net.doctorocclusion.festivities4;

import java.util.List;

import net.doctorocclusion.festivities4.entity.lights.EntityBlockLights;
import net.doctorocclusion.festivities4.item.FestiveItems;
import net.doctorocclusion.festivities4.network.MessageLightsColor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FestivitiesEventHandler
{
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if (event.action == Action.LEFT_CLICK_BLOCK)
		{
			ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
			if (stack != null && stack.getItem() == FestiveItems.blockLights)
			{
				@SuppressWarnings("unchecked")
				List<Entity> ents = event.world.getEntitiesWithinAABB(EntityBlockLights.class, new AxisAlignedBB(event.pos, event.pos.add(1, 1, 1)));
				if (!ents.isEmpty())
				{
					EntityBlockLights ent = (EntityBlockLights) ents.get(0);
					ent.onBroken(event.entityPlayer);
					ent.setDead();
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onMouseEvent(MouseEvent event)
	{
		if (event.button < 0)
		{
			EntityPlayer entityPlayer = Minecraft.getMinecraft().thePlayer;
			if (entityPlayer != null && entityPlayer.isSneaking())
			{
				ItemStack stack = entityPlayer.getCurrentEquippedItem();
				if (stack != null && stack.getItem() == FestiveItems.blockLights)
				{
					if (event.dwheel != 0)
					{
						Festivities.network.sendToServer(new MessageLightsColor(entityPlayer.inventory.currentItem, event.dwheel > 0));
					}
					event.setCanceled(true);
				}
			}
		}
	}
}
