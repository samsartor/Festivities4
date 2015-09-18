package net.doctorocclusion.festivities4.block;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CandyLog extends BlockLog
{
	public CandyLog()
	{
		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}
	
	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		BlockLog.EnumAxis axis = BlockLog.EnumAxis.Y;
		
		switch (meta & 3)
		{
			case 0:
				axis = BlockLog.EnumAxis.Y;
				break;
			case 1:
				axis = BlockLog.EnumAxis.X;
				break;
			case 2:
				axis = BlockLog.EnumAxis.Z;
				break;
			case 3:
				axis = BlockLog.EnumAxis.NONE;
				break;
		}
		
		return this.getDefaultState().withProperty(LOG_AXIS, axis);
	}
	
	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state)
	{
		BlockLog.EnumAxis axis = (EnumAxis) state.getValue(LOG_AXIS);
		
		switch (axis)
		{
			case Y:
				return 0;
			case X:
				return 1;
			case Z:
				return 2;
			case NONE:
				return 4;
			default:
				return 0;
		}
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { LOG_AXIS });
	}
	
	@Override
	protected ItemStack createStackedBlock(IBlockState state)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, 0);
	}
	
	/**
	 * Get the damage value that this Block should drop
	 */
	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}
}
