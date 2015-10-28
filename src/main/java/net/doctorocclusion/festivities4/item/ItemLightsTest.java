package net.doctorocclusion.festivities4.item;

import net.doctorocclusion.festivities4.entity.lights.EntityLightsInternal;
import net.doctorocclusion.festivities4.entity.lights.EnumBulbColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import scala.util.Random;

public class ItemLightsTest extends ItemFestive
{
	public ItemLightsTest()
	{
		this.maxStackSize = 1;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			Random r = new Random();
			EnumBulbColor color = EnumBulbColor.values()[r.nextInt(EnumBulbColor.values().length)];
			EntityLightsInternal ent = new EntityLightsInternal(world, pos, color, r.nextBoolean());
			world.spawnEntityInWorld(ent);
		}
		stack.stackSize--;
		return true;
	}
}
