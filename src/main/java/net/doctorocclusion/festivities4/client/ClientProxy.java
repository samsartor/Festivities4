package net.doctorocclusion.festivities4.client;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.doctorocclusion.festivities4.CommonProxy;
import net.doctorocclusion.festivities4.Festivities;
import net.doctorocclusion.festivities4.block.FestiveBlocks;
import net.doctorocclusion.festivities4.item.FestiveItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		this.setItemModel(FestiveBlocks.candyLog, 0, Festivities.ID + ":candy_log");
		this.setItemModel(FestiveBlocks.candyPlanks, 0, Festivities.ID + ":candy_planks");
		
		this.setItemModel(FestiveItems.candyCane, 0, Festivities.ID + ":candy_cane");
		this.setItemModel(FestiveItems.peppermintStick, 0, Festivities.ID + ":peppermint_stick");
	}
	
	public void setItemModel(Item item, int meta, ModelResourceLocation loc)
	{
		// ModelLoader.setCustomModelResourceLocation(item, meta, loc);
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		renderItem.getItemModelMesher().register(item, meta, loc);
	}
	
	public void setItemModel(Item item, int meta, String loc)
	{
		this.setItemModel(item, meta, new ModelResourceLocation(loc, "inventory"));
	}
	
	public void setItemModel(Block block, int meta, ModelResourceLocation loc)
	{
		this.setItemModel(Item.getItemFromBlock(block), meta, loc);
	}
	
	public void setItemModel(Block block, int meta, String loc)
	{
		this.setItemModel(Item.getItemFromBlock(block), meta, loc);
	}
	
	public static void addItemTip(ItemStack stack, List<String> tip, boolean advanced)
	{
		String name = stack.getUnlocalizedName();
		boolean shift = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
		boolean hasshift = StatCollector.canTranslate(name + ".shifttip");
		if ((advanced || !shift || !hasshift) && StatCollector.canTranslate(name + ".tip"))
		{
			tip.addAll(Arrays.asList(StatCollector.translateToLocal(name + ".tip").split("\\|")));
		}
		if ((advanced || shift) && hasshift)
		{
			tip.addAll(Arrays.asList(StatCollector.translateToLocal(name + ".shifttip").split("\\|")));
		}
	}
}
