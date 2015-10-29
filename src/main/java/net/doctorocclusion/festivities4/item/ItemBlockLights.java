package net.doctorocclusion.festivities4.item;

import java.util.List;

import net.doctorocclusion.festivities4.entity.lights.EntityBlockLights;
import net.doctorocclusion.festivities4.entity.lights.EnumBulbColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public static String getItemName(int meta)
	{
		String name = EnumBulbColor.values()[meta & 0xFF].name;
		name += ".";
		if ((meta & 0x100) > 0)
		{
			name += "sparkle";
		}
		else
		{
			name += "plain";
		}
		return name;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			int i = stack.getMetadata();
			EnumBulbColor color = EnumBulbColor.values()[i & 0xFF];
			EntityBlockLights ent = new EntityBlockLights(world, pos, color, (i & 0x100) > 0);
			world.spawnEntityInWorld(ent);
		}
		stack.stackSize--;
		return true;
	}
	
	/**
	 * Returns the unlocalized name of this item. This version accepts an
	 * ItemStack so different stacks can have different names based on their
	 * damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + getItemName(stack.getMetadata());
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
			return EnumBulbColor.values()[stack.getMetadata() & 0xFF].color;
		}
	}
}
