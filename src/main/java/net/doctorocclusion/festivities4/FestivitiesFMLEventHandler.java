package net.doctorocclusion.festivities4;

import net.doctorocclusion.festivities4.client.renderer.entity.RenderBlockLights;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FestivitiesFMLEventHandler
{
	public int clientticks = 0;
	
	@SubscribeEvent
	public void onRenderTick(TickEvent.ClientTickEvent event)
	{
		if (event.phase == TickEvent.Phase.START)
		{
			double tau = 2 * Math.PI;
			double angle0 = ((this.clientticks % 30) / 30.0);
			double angle1 = ((this.clientticks % 30 + 1) / 30.0);
			
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					RenderBlockLights.twinkle[i][j][0] = (float) Math.sin((angle0 + (i / 8.0) + (j / 3.0)) * tau) * 0.5F + 0.5F;
					RenderBlockLights.twinkle[i][j][1] = (float) Math.sin((angle1 + (i / 8.0) + (j / 3.0)) * tau) * 0.5F + 0.5F;
				}
			}
			
			this.clientticks++;
		}
	}
}
