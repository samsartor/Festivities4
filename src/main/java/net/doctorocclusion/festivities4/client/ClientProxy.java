package net.doctorocclusion.festivities4.client;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.doctorocclusion.festivities4.CommonProxy;
import net.doctorocclusion.festivities4.Festivities;
import net.doctorocclusion.festivities4.block.FestiveBlocks;
import net.doctorocclusion.festivities4.client.renderer.entity.RenderBlockLights;
import net.doctorocclusion.festivities4.entity.lights.EntityBlockLights;
import net.doctorocclusion.festivities4.item.FestiveItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();
		
		this.setItemModel(FestiveBlocks.candyLog, 0, Festivities.ID + ":candy_log");
		this.setItemModel(FestiveBlocks.candyPlanks, 0, Festivities.ID + ":candy_planks");
		this.setItemModel(FestiveBlocks.magicSack, 0, Festivities.ID + ":magic_sack");
		
		this.setItemModel(FestiveItems.candyCane, 0, Festivities.ID + ":candy_cane");
		this.setItemModel(FestiveItems.peppermintStick, 0, Festivities.ID + ":peppermint_stick");
		this.setItemModel(FestiveItems.snowmanParts, 0, Festivities.ID + ":snowman_parts");
		
		this.setItemModel(FestiveItems.blockLights, new ItemMeshDefinition()
		{
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				if ((stack.getMetadata() & 0x100) > 0)
				{
					return new ModelResourceLocation(Festivities.ID + ":block_lights_sparkle", "inventory");
				}
				else
				{
					return new ModelResourceLocation(Festivities.ID + ":block_lights_plain", "inventory");
				}
			}
		});
		ModelBakery.addVariantName(FestiveItems.blockLights, Festivities.ID + ":block_lights_plain", Festivities.ID + ":block_lights_sparkle");
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBlockLights.class, new RenderBlockLights(rm));
	}
	
	public void setItemModel(Item item, int meta, ModelResourceLocation loc)
	{
		// ModelLoader.setCustomModelResourceLocation(item, meta, loc);
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		renderItem.getItemModelMesher().register(item, meta, loc);
	}
	
	public void setItemModel(Item item, ItemMeshDefinition def)
	{
		// ModelLoader.setCustomModelResourceLocation(item, meta, loc);
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		renderItem.getItemModelMesher().register(item, def);
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
		if (StatCollector.canTranslate(name + ".subname"))
		{
			tip.addAll(Arrays.asList(StatCollector.translateToLocal(name + ".subname").split("\\|")));
		}
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
