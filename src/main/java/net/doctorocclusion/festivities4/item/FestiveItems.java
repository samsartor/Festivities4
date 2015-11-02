package net.doctorocclusion.festivities4.item;

import net.doctorocclusion.festivities4.FestiveTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FestiveItems
{
	public static Item candyCane = new ItemFestiveFood(3, 0.3F);
	public static Item peppermintStick = new ItemFestiveFood(3, 0.3F);
	public static Item snowmanParts = new ItemSnowmanParts();
	public static Item blockLights = new ItemBlockLights();
	public static Item chocolateMilk = new ItemDrink();
	public static Item hotChocolate = new ItemDrink();
	public static Item warmBulbs = new ItemFestive();
	public static Item coolBulbs = new ItemFestive();
	
	public static void registerItems()
	{
		candyCane.setUnlocalizedName("candyCane");
		GameRegistry.registerItem(candyCane, "candy_cane");
		
		peppermintStick.setUnlocalizedName("peppermintStick");
		GameRegistry.registerItem(peppermintStick, "peppermint_stick");
		
		snowmanParts.setUnlocalizedName("snowmanParts");
		GameRegistry.registerItem(snowmanParts, "snowman_parts");
		
		blockLights.setUnlocalizedName("blockLights");
		GameRegistry.registerItem(blockLights, "block_lights");
		
		chocolateMilk.setUnlocalizedName("chocolateMilk");
		GameRegistry.registerItem(chocolateMilk, "chocolate_milk");
		
		hotChocolate.setUnlocalizedName("hotChocolate");
		GameRegistry.registerItem(hotChocolate, "hot_chocolate");
		
		warmBulbs.setUnlocalizedName("warmBulbs");
		warmBulbs.setCreativeTab(FestiveTabs.lights);
		GameRegistry.registerItem(warmBulbs, "warm_bulbs");
		
		coolBulbs.setUnlocalizedName("coolBulbs");
		coolBulbs.setCreativeTab(FestiveTabs.lights);
		GameRegistry.registerItem(coolBulbs, "cool_bulbs");
	}
}
