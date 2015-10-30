package net.doctorocclusion.festivities4.item;

import java.util.List;

import net.doctorocclusion.festivities4.FestiveTabs;
import net.doctorocclusion.festivities4.client.ClientProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemFestiveFood extends ItemFood
{
	public ItemFestiveFood(int amount, float saturation)
	{
		super(amount, saturation, false);
		this.setCreativeTab(FestiveTabs.food);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		ClientProxy.addItemTip(stack.getUnlocalizedName(), tooltip, advanced);
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}
