package net.doctorocclusion.festivities4.item;

import java.util.List;

import net.doctorocclusion.festivities4.FestiveTabs;
import net.doctorocclusion.festivities4.client.ClientProxy;
import net.doctorocclusion.festivities4.entity.lights.EntityBlockLights;
import net.doctorocclusion.festivities4.entity.lights.EnumBulbColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockLights extends ItemFestive
{
	public ItemBlockLights()
	{
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(FestiveTabs.lights);
		this.setMaxStackSize(64);
	}
	
	public static boolean isSparkle(ItemStack stack)
	{
		return isSparkle(stack.getMetadata());
	}
	
	public static ItemStack setSparkle(ItemStack stack, boolean sparkle)
	{
		int meta = stack.getMetadata();
		meta &= ~0x100;
		meta |= (sparkle ? 0x100 : 0x0);
		stack.setItemDamage(meta);
		return stack;
	}
	
	public static boolean isSparkle(int meta)
	{
		return (meta & 0x100) > 0;
	}
	
	public static EnumBulbColor getColor(ItemStack stack)
	{
		return getColor(stack.getMetadata());
	}
	
	public static ItemStack setColor(ItemStack stack, EnumBulbColor color)
	{
		int meta = stack.getMetadata();
		meta &= ~0xFF;
		meta |= color.ordinal();
		stack.setItemDamage(meta);
		return stack;
	}
	
	public static EnumBulbColor getColor(int meta)
	{
		return EnumBulbColor.values()[meta & 0xFF];
	}
	
	public static String getItemName(int meta, boolean colored)
	{
		String name = "";
		if (isSparkle(meta))
		{
			name += "sparkle";
		}
		else
		{
			name += "plain";
		}
		if (colored)
		{
			name += getColor(meta).name;
			name += ".";
		}
		return name;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			int i = stack.getMetadata();
			EntityBlockLights ent = new EntityBlockLights(world, pos, getColor(i), isSparkle(i));
			world.spawnEntityInWorld(ent);
		}
		stack.stackSize--;
		return true;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		String color = StatCollector.translateToLocal("color." + getColor(stack).name + ".name").trim();
		String name = StatCollector.translateToLocal(this.getUnlocalizedBaseName(stack) + ".name").trim();
		return String.format("%s (%s)", name, color);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		ClientProxy.addItemTip(this.getUnlocalizedBaseName(stack), tooltip, advanced);
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	@Override
	public boolean addFestiveTips()
	{
		return false;
	}
	
	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + getItemName(stack.getMetadata(), true);
	}
	
	public String getUnlocalizedBaseName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + getItemName(stack.getMetadata(), false);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
		for (int i = 0; i < EnumBulbColor.values().length; ++i)
		{
			subItems.add(new ItemStack(itemIn, 1, i));
			subItems.add(new ItemStack(itemIn, 1, i | 0x100));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass)
	{
		if (renderPass == 0)
		{
			return 0xFFFFFF;
		}
		else
		{
			return getColor(stack).color;
		}
	}
}
