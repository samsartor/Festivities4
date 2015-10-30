package net.doctorocclusion.festivities4.network;

import io.netty.buffer.ByteBuf;
import net.doctorocclusion.festivities4.entity.lights.EnumBulbColor;
import net.doctorocclusion.festivities4.item.ItemBlockLights;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageLightsColor implements IMessage
{
	public int slot;
	public boolean up;
	
	public MessageLightsColor()
	{
	}
	
	public MessageLightsColor(int slot, boolean up)
	{
		this();
		this.slot = slot;
		this.up = up;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.slot = buf.readInt();
		this.up = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.slot);
		buf.writeBoolean(this.up);
	}
	
	public static class Handler implements IMessageHandler<MessageLightsColor, IMessage>
	{
		@Override
		public IMessage onMessage(final MessageLightsColor message, final MessageContext ctx)
		{
			final EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			IThreadListener mainThread = (WorldServer) player.worldObj;
			mainThread.addScheduledTask(new Runnable()
			{
				@Override
				public void run()
				{
					ItemStack stack = player.inventory.getStackInSlot(message.slot);
					int colorind = ItemBlockLights.getColor(stack).ordinal();
					int colornum = EnumBulbColor.values().length;
					if (message.up)
					{
						colorind++;
					}
					else
					{
						colorind += colornum - 1;
					}
					colorind %= colornum;
					ItemBlockLights.setColor(stack, EnumBulbColor.values()[colorind]);
				}
			});
			return null;
		}
	}
}
