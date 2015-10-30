package net.doctorocclusion.festivities4;

import net.doctorocclusion.festivities4.block.FestiveBlocks;
import net.doctorocclusion.festivities4.entity.lights.EnumBulbColor;
import net.doctorocclusion.festivities4.item.FestiveItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class FestiveTabs
{
	public static CreativeTabs lights = new CreativeTabs("festiveLights")
	{
		@Override
		public Item getTabIconItem()
		{
			return FestiveItems.blockLights;
		}
		
		@Override
		public int getIconItemDamage()
		{
			return EnumBulbColor.DARK_RED.ordinal();
		}
	};
	
	public static CreativeTabs food = new CreativeTabs("festiveFoods")
	{
		@Override
		public Item getTabIconItem()
		{
			return FestiveItems.candyCane;
		}
	};
	
	public static CreativeTabs block = new CreativeTabs("festiveBlocks")
	{
		@Override
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(FestiveBlocks.candyLog);
		}
	};
	
	public static CreativeTabs misc = new CreativeTabs("festiveMisc")
	{
		@Override
		public Item getTabIconItem()
		{
			return FestiveItems.snowmanParts;
		}
	};
}
