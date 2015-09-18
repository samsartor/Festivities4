package net.doctorocclusion.festivities4.item;

import java.util.List;

import net.doctorocclusion.festivities4.client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemFestiveBlock extends ItemBlock
{
	public ItemFestiveBlock(Block block)
	{
		super(block);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		ClientProxy.addItemTip(stack, tooltip, advanced);
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}
