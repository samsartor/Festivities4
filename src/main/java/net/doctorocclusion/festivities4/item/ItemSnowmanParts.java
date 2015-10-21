package net.doctorocclusion.festivities4.item;

import net.doctorocclusion.festivities4.block.BlockSnowman;
import net.doctorocclusion.festivities4.block.FestiveBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSnowmanParts extends ItemFestive
{
	public ItemSnowmanParts()
	{
		this.maxStackSize = 1;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		BlockPos bottom = pos;
		BlockPos top = bottom.up();
		if (world.getBlockState(bottom).getBlock() != Blocks.snow)
		{
			return false;
		}
		if (world.getBlockState(top).getBlock() != Blocks.snow)
		{
			top = bottom;
			bottom = top.down();
			if (world.getBlockState(bottom).getBlock() != Blocks.snow)
			{
				return false;
			}
		}
		if (!player.capabilities.allowEdit)
		{
			return false;
		}
		EnumFacing facing = player.getHorizontalFacing().getOpposite();
		world.setBlockState(top, FestiveBlocks.snowman.getDefaultState().withProperty(BlockSnowman.HALF, BlockSnowman.EnumBlockHalf.UPPER).withProperty(BlockSnowman.FACING, facing), 2);
		world.setBlockState(bottom, FestiveBlocks.snowman.getDefaultState().withProperty(BlockSnowman.HALF, BlockSnowman.EnumBlockHalf.LOWER).withProperty(BlockSnowman.FACING, facing), 2);
		world.notifyNeighborsOfStateChange(top, FestiveBlocks.snowman);
		world.notifyNeighborsOfStateChange(bottom, FestiveBlocks.snowman);
		stack.stackSize--;
		return true;
	}
}
