package net.doctorocclusion.festivities4.block;

import net.doctorocclusion.festivities4.item.ItemFestiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FestiveBlocks
{
	public static BlockCandyLog candyLog = new BlockCandyLog();
	public static Block candyPlanks = new Block(Material.wood);
	public static Block snowman = new BlockSnowman();
	
	public static void registerBlocks()
	{
		candyLog.setUnlocalizedName("candyLog");
		GameRegistry.registerBlock(candyLog, ItemFestiveBlock.class, "candy_log");
		
		candyPlanks.setUnlocalizedName("candyPlanks");
		GameRegistry.registerBlock(candyPlanks, ItemFestiveBlock.class, "candy_planks");
		
		snowman.setUnlocalizedName("snowman");
		GameRegistry.registerBlock(snowman, null, "snowman");
	}
}
