package net.doctorocclusion.festivities4.entity.lights;

import io.netty.buffer.ByteBuf;
import net.doctorocclusion.festivities4.item.FestiveItems;
import net.doctorocclusion.festivities4.item.ItemBlockLights;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBlockLights extends Entity implements IEntityAdditionalSpawnData
{
	public BlockPos hangingPosition;
	public int randTex;
	public int randTime;
	public int color;
	public boolean twinkle;
	
	public EntityBlockLights(World world)
	{
		super(world);
		this.setSize(8 / 16.0F, 8 / 16.0F);
		this.noClip = true;
	}
	
	public EntityBlockLights(World world, BlockPos pos, int color, boolean twinkle)
	{
		this(world);
		this.hangingPosition = pos;
		this.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
		this.fixPosition();
		this.color = color;
		this.twinkle = twinkle;
	}
	
	public EntityBlockLights(World world, BlockPos pos, EnumBulbColor color, boolean twinkle)
	{
		this(world, pos, color.color, twinkle);
	}
	
	@Override
	protected void entityInit()
	{
		this.getDataWatcher().addObject(8, Integer.valueOf(0xFFFFFF));
		this.randTex = this.rand.nextInt(16);
		this.randTime = this.rand.nextInt(8);
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity entityIn)
	{
		return null;
	}
	
	private void fixPosition()
	{
		double x = this.hangingPosition.getX() + 0.5D;
		double y = this.hangingPosition.getY() + 0.5D;
		double z = this.hangingPosition.getZ() + 0.5D;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		float f = 4 / 16.0F;
		this.setEntityBoundingBox(new AxisAlignedBB(x - f, y - f, z - f, x + f, y + f, z + f));
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
	}
	
	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}
	
	public void onBroken(Entity entity)
	{
		if (!this.worldObj.isRemote)
		{
			boolean drop = true;
			boolean direct = false;
			int colorid = -1;
			if (entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) entity;
				if (player.capabilities.isCreativeMode)
				{
					drop = false;
				}
				else
				{
					ItemStack held = player.getCurrentEquippedItem();
					if (held.getItem() == FestiveItems.blockLights)
					{
						direct = true;
						if (ItemBlockLights.isSparkle(held) == this.twinkle)
						{
							colorid = ItemBlockLights.getColor(held).ordinal();
						}
					}
				}
			}
			if (colorid == -1)
			{
				EnumBulbColor[] colors = EnumBulbColor.values();
				for (int i = 0; i < colors.length; i++)
				{
					if (colors[i].color == this.color)
					{
						colorid = i;
						break;
					}
				}
				colorid = EnumBulbColor.WHITE.ordinal();
			}
			if (colorid != -1 && drop)
			{
				ItemStack stack = new ItemStack(FestiveItems.blockLights, 1, colorid);
				stack = ItemBlockLights.setSparkle(stack, this.twinkle);
				if (direct)
				{
					((EntityPlayer) entity).inventory.addItemStackToInventory(stack);
				}
				else
				{
					this.entityDropItem(stack, 0);
				}
			}
		}
	}
	
	/**
	 * Called when a player attacks an entity. If this returns true the attack
	 * will not happen.
	 */
	@Override
	public boolean hitByEntity(Entity entityIn)
	{
		return entityIn instanceof EntityPlayer ? this.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) entityIn), 0.0F) : false;
	}
	
	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else
		{
			if (!this.isDead && !this.worldObj.isRemote)
			{
				this.setDead();
				this.setBeenAttacked();
				this.onBroken(source.getEntity());
			}
			
			return true;
		}
	}
	
	/**
	 * Tries to moves the entity by the passed in displacement. Args: x, y, z
	 */
	@Override
	public void moveEntity(double x, double y, double z)
	{
		if (!this.worldObj.isRemote && !this.isDead && x * x + y * y + z * z > 0.0D)
		{
			this.setDead();
			this.onBroken((Entity) null);
		}
	}
	
	/**
	 * Adds to the current velocity of the entity. Args: x, y, z
	 */
	@Override
	public void addVelocity(double x, double y, double z)
	{
		if (!this.worldObj.isRemote && !this.isDead && x * x + y * y + z * z > 0.0D)
		{
			this.setDead();
			this.onBroken((Entity) null);
		}
	}
	
	/**
	 * Sets the x,y,z of the entity from the given parameters. Also seems to set
	 * up a bounding box.
	 */
	@Override
	public void setPosition(double x, double y, double z)
	{
		BlockPos blockpos = this.hangingPosition;
		this.hangingPosition = new BlockPos(x, y, z);
		
		if (!this.hangingPosition.equals(blockpos))
		{
			this.isAirBorne = true;
			this.fixPosition();
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_)
	{
		this.setPosition(p_180426_1_, p_180426_3_, p_180426_5_);
		this.setRotation(p_180426_7_, p_180426_8_);
	}
	
	@Override
	public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch)
	{
		BlockPos blockpos = new BlockPos(x - this.posX, y - this.posY, z - this.posZ);
		BlockPos blockpos1 = this.hangingPosition.add(blockpos);
		this.setPosition((double) blockpos1.getX(), (double) blockpos1.getY(), (double) blockpos1.getZ());
	}
	
	public BlockPos position()
	{
		return this.hangingPosition;
	}
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		tagCompound.setInteger("TileX", this.position().getX());
		tagCompound.setInteger("TileY", this.position().getY());
		tagCompound.setInteger("TileZ", this.position().getZ());
		tagCompound.setInteger("Color", this.color);
		tagCompound.setBoolean("Twinkle", this.twinkle);
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		this.hangingPosition = new BlockPos(tagCompound.getInteger("TileX"), tagCompound.getInteger("TileY"), tagCompound.getInteger("TileZ"));
		this.setPosition(this.hangingPosition.getX() + 0.5, this.hangingPosition.getY() + 0.5, this.hangingPosition.getZ() + 0.5);
		this.color = tagCompound.getInteger("Color");
		this.twinkle = tagCompound.getBoolean("Twinkle");
	}
	
	@Override
	public void writeSpawnData(ByteBuf buffer)
	{
		buffer.writeInt(this.color);
		buffer.writeBoolean(this.twinkle);
	}
	
	@Override
	public void readSpawnData(ByteBuf additionalData)
	{
		this.color = additionalData.readInt();
		this.twinkle = additionalData.readBoolean();
	}
}
