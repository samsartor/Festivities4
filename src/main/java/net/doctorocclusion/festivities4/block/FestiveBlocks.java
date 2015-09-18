package net.doctorocclusion.festivities4.block;

import net.doctorocclusion.festivities4.item.ItemFestiveBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FestiveBlocks
{
	public static CandyLog candyLog = new CandyLog();
	
	public static void registerBlocks()
	{
		candyLog.setUnlocalizedName("candyLog");
		GameRegistry.registerBlock(candyLog, ItemFestiveBlock.class, "candy_log");
	}
}
