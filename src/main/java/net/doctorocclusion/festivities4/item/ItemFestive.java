package net.doctorocclusion.festivities4.item;

import java.util.List;

import net.doctorocclusion.festivities4.client.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFestive extends Item
{
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		ClientProxy.addItemTip(stack, tooltip, advanced);
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}
