package powercrystals.minefactoryreloaded.farmables;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import powercrystals.minefactoryreloaded.MineFactoryReloadedCore;
import powercrystals.minefactoryreloaded.animals.TileEntityRancher;
import powercrystals.minefactoryreloaded.api.IFactoryRanchable;

public class RanchableCow implements IFactoryRanchable
{
	@Override
	public Class<?> getRanchableEntity()
	{
		return EntityCow.class;
	}

	@Override
	public List<ItemStack> ranch(World world, EntityLiving entity, TileEntityRancher rancher)
	{
		List<ItemStack> drops = new LinkedList<ItemStack>();
		int bucketIndex = rancher.findFirstStack(Item.bucketEmpty.shiftedIndex, 0);
		if(bucketIndex >= 0)
		{
			drops.add(new ItemStack(Item.bucketMilk));
			rancher.decrStackSize(bucketIndex, 1);
		}
		else
		{
			drops.add(new ItemStack(MineFactoryReloadedCore.milkItem));
		}
		
		return drops;
	}
}