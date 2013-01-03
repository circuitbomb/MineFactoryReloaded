package powercrystals.minefactoryreloaded.processing;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

import powercrystals.minefactoryreloaded.core.TileEntityFactory;
import powercrystals.minefactoryreloaded.core.UtilInventory;

public class TileEntityCollector extends TileEntityFactory
{
	@Override
	public boolean canRotate()
	{
		return false;
	}
	
	public void addToChests(EntityItem i)
	{
		ItemStack s = i.func_92014_d();
		Map<ForgeDirection, IInventory> chests = UtilInventory.findChests(worldObj, xCoord, yCoord, zCoord);
		for(Entry<ForgeDirection, IInventory> chest : chests.entrySet())
		{
			s.stackSize = UtilInventory.addToInventory(chest.getValue(), chest.getKey(), s);
			if(s.stackSize == 0)
			{
				i.setDead();
				return;
			}
		}
	}
}