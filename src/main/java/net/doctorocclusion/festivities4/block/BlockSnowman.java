package net.doctorocclusion.festivities4.block;

import java.util.List;
import java.util.Random;

import net.doctorocclusion.festivities4.item.FestiveItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSnowman extends Block
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum HALF = PropertyEnum.create("half", EnumBlockHalf.class);
	
	public BlockSnowman()
	{
		super(Material.craftedSnow);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, EnumBlockHalf.LOWER));
		this.setHardness(0.0F);
		this.setStepSound(soundTypeSnow);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean isFullCube()
	{
		return false;
	}
	
	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!this.canBlockStay(worldIn, pos, state))
		{
			boolean flag = state.getValue(HALF) == EnumBlockHalf.UPPER;
			BlockPos blockpos1 = flag ? pos : pos.up();
			BlockPos blockpos2 = flag ? pos.down() : pos;
			Object object = flag ? this : worldIn.getBlockState(blockpos1).getBlock();
			Object object1 = flag ? worldIn.getBlockState(blockpos2).getBlock() : this;
			
			if (object == this)
			{
				worldIn.destroyBlock(blockpos1, true);
			}
			
			if (object1 == this)
			{
				worldIn.destroyBlock(blockpos2, true);
			}
		}
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
	{
		if (state.getValue(HALF) == EnumBlockHalf.UPPER)
		{
			return worldIn.getBlockState(pos.down()).getBlock() == this;
		}
		else
		{
			return worldIn.getBlockState(pos.up()).getBlock() == this;
		}
	}
	
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
		this.checkAndDropBlock(worldIn, pos, state);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		this.checkAndDropBlock(worldIn, pos, state);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Blocks.snow);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (state.getValue(HALF) == EnumBlockHalf.UPPER)
		{
			if (worldIn.getBlockState(pos.down()).getBlock() == this)
			{
				worldIn.destroyBlock(pos, !player.capabilities.isCreativeMode);
			}
		}
		else if (worldIn.getBlockState(pos.up()).getBlock() == this)
		{
			worldIn.destroyBlock(pos, !player.capabilities.isCreativeMode);
		}
		
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(HALF, (meta & 4) > 0 ? EnumBlockHalf.UPPER : EnumBlockHalf.LOWER).withProperty(FACING, EnumFacing.getHorizontal(meta & 3).rotateYCCW());
	}
	
	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return (state.getValue(HALF) == EnumBlockHalf.UPPER ? 4 : 0) | ((EnumFacing) state.getValue(FACING)).rotateY().getHorizontalIndex();
	}
	
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { HALF, FACING });
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		
		if (state.getValue(HALF) == EnumBlockHalf.UPPER)
		{
			ret.add(new ItemStack(FestiveItems.snowmanParts));
		}
		
		return ret;
	}
	
	public static enum EnumBlockHalf implements IStringSerializable
	{
		UPPER, LOWER;
		
		@Override
		public String toString()
		{
			return this.getName();
		}
		
		@Override
		public String getName()
		{
			return this == UPPER ? "upper" : "lower";
		}
	}
}
