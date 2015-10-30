package net.doctorocclusion.festivities4.item;

import java.util.List;

import net.doctorocclusion.festivities4.FestiveTabs;
import net.doctorocclusion.festivities4.client.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFestive extends Item
{
	public ItemFestive()
	{
		this.setCreativeTab(FestiveTabs.misc);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		if (this.addFestiveTips())
		{
			ClientProxy.addItemTip(stack.getUnlocalizedName(), tooltip, advanced);
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	public boolean addFestiveTips()
	{
		return true;
	}
}
