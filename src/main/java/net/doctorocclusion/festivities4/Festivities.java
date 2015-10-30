package net.doctorocclusion.festivities4;

import net.doctorocclusion.festivities4.block.FestiveBlocks;
import net.doctorocclusion.festivities4.entity.FestiveEntities;
import net.doctorocclusion.festivities4.item.FestiveItems;
import net.doctorocclusion.festivities4.network.MessageLightsColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Festivities.ID, version = Festivities.VERSION)
public class Festivities
{
	public static final String ID = "festivities4";
	public static final String VERSION = "0.0";
	
	@Instance("Festivities")
	public static Festivities instance;
	
	public static SimpleNetworkWrapper network;
	
	@SidedProxy(modId = Festivities.ID, clientSide = "net.doctorocclusion.festivities4.client.ClientProxy", serverSide = "net.doctorocclusion.festivities4.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		instance = this;
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("MyChannel");
		network.registerMessage(MessageLightsColor.Handler.class, MessageLightsColor.class, 0, Side.SERVER);
		
		FestiveItems.registerItems();
		FestiveBlocks.registerBlocks();
		FestiveEntities.registerEntities();
		this.registerCrafting();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
		
		MinecraftForge.EVENT_BUS.register(new FestivitiesEventHandler());
		FMLCommonHandler.instance().bus().register(new FestivitiesFMLEventHandler());
	}
	
	private void registerCrafting()
	{
		GameRegistry.addShapedRecipe(new ItemStack(FestiveBlocks.candyLog, 1), "##", "##", '#', FestiveItems.candyCane);
		GameRegistry.addShapelessRecipe(new ItemStack(FestiveItems.candyCane, 4), FestiveBlocks.candyLog);
		
		GameRegistry.addShapelessRecipe(new ItemStack(FestiveItems.candyCane, 1), FestiveItems.peppermintStick);
		GameRegistry.addShapelessRecipe(new ItemStack(FestiveItems.peppermintStick, 1), FestiveItems.candyCane);
		
		GameRegistry.addShapedRecipe(new ItemStack(FestiveBlocks.candyPlanks, 1), "##", "##", '#', FestiveItems.peppermintStick);
		GameRegistry.addShapelessRecipe(new ItemStack(FestiveItems.peppermintStick, 4), FestiveBlocks.candyPlanks);
	}
}
