package net.doctorocclusion.festivities4.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FestiveItems
{
	public static Item candyCane = new ItemFestiveFood(3, 0.3F);
	public static Item peppermintStick = new ItemFestiveFood(3, 0.3F);
	public static Item snowmanParts = new ItemSnowmanParts();
	
	public static void registerItems()
	{
		candyCane.setUnlocalizedName("candyCane");
		GameRegistry.registerItem(candyCane, "candy_cane");
		
		peppermintStick.setUnlocalizedName("peppermintStick");
		GameRegistry.registerItem(peppermintStick, "peppermint_stick");
		
		snowmanParts.setUnlocalizedName("snowmanParts");
		GameRegistry.registerItem(snowmanParts, "snowman_parts");
	}
}
