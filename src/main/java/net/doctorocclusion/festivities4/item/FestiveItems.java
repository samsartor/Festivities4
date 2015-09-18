package net.doctorocclusion.festivities4.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FestiveItems
{
	public static Item candyCane = new ItemFestive();
	public static Item peppermintStick = new ItemFestive();
	
	public static void registerItems()
	{
		candyCane.setUnlocalizedName("candyCane");
		GameRegistry.registerItem(candyCane, "candy_cane");
		
		peppermintStick.setUnlocalizedName("peppermintStick");
		GameRegistry.registerItem(peppermintStick, "peppermint_stick");
	}
}
