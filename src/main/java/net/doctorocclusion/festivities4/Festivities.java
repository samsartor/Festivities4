package net.doctorocclusion.festivities4;

import net.doctorocclusion.festivities4.block.FestiveBlocks;
import net.doctorocclusion.festivities4.item.FestiveItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Festivities.ID, version = Festivities.VERSION)
public class Festivities
{
	public static final String ID = "festivities4";
	public static final String VERSION = "0.0";
	
	@Instance("Festivities")
	public static Festivities instance;
	
	@SidedProxy(modId = Festivities.ID, clientSide = "net.doctorocclusion.festivities4.client.ClientProxy", serverSide = "net.doctorocclusion.festivities4.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		instance = this;
		
		FestiveItems.registerItems();
		FestiveBlocks.registerBlocks();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
	}
}
