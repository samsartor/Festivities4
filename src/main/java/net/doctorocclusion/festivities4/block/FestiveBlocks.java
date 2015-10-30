package net.doctorocclusion.festivities4.block;

import net.doctorocclusion.festivities4.FestiveTabs;
import net.doctorocclusion.festivities4.item.ItemFestiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FestiveBlocks
{
	public static BlockCandyLog candyLog = new BlockCandyLog();
	public static Block candyPlanks = new Block(Material.wood);
	public static Block snowman = new BlockSnowman();
	public static Block magicSack = new BlockMagicBag();
	
	public static void registerBlocks()
	{
		candyLog.setUnlocalizedName("candyLog");
		GameRegistry.registerBlock(candyLog, ItemFestiveBlock.class, "candy_log");
		candyLog.setCreativeTab(FestiveTabs.block);
		
		candyPlanks.setUnlocalizedName("candyPlanks");
		GameRegistry.registerBlock(candyPlanks, ItemFestiveBlock.class, "candy_planks");
		candyPlanks.setCreativeTab(FestiveTabs.block);
		
		snowman.setUnlocalizedName("snowman");
		GameRegistry.registerBlock(snowman, null, "snowman");
		
		magicSack.setUnlocalizedName("magicSack");
		GameRegistry.registerBlock(magicSack, ItemFestiveBlock.class, "magic_sack");
		magicSack.setCreativeTab(FestiveTabs.block);
	}
}
