package powercrystals.minefactoryreloaded.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import powercrystals.minefactoryreloaded.processing.TileEntityAutoEnchanter;

public class ContainerAutoEnchanter extends ContainerFactoryInventory
{
	private TileEntityAutoEnchanter _enchanter;
	
	public ContainerAutoEnchanter(TileEntityAutoEnchanter enchanter, InventoryPlayer inv)
	{
		super(enchanter, inv);
		
		_enchanter = enchanter;
	}
	
	@Override
	protected void addSlots(InventoryPlayer inv)
	{
		addSlotToContainer(new Slot((TileEntityAutoEnchanter)_te, 0, 8, 24));
		addSlotToContainer(new SlotRemoveOnly((TileEntityAutoEnchanter)_te, 1, 8, 54));
		
		bindPlayerInventory(inv);
	}

	@Override
	public void updateCraftingResults()
	{
		super.updateCraftingResults();
		for(int i = 0; i < crafters.size(); i++)
		{
			((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 100, _enchanter.getTargetLevel());
		}
	}
	
	@Override
	public void updateProgressBar(int var, int value)
	{
		super.updateProgressBar(var, value);
		if(var == 100) _enchanter.setTargetLevel(value);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		if(slotObject != null && slotObject.getHasStack())
		{
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();
			
			if(slot < 2)
			{
				if(!mergeItemStack(stackInSlot, 2, inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if(!mergeItemStack(stackInSlot, 0, 1, false))
			{
				return null;
			}
			
			if(stackInSlot.stackSize == 0)
			{
				slotObject.putStack(null);
			}
			else
			{
				slotObject.onSlotChanged();
			}
			
			if(stackInSlot.stackSize == stack.stackSize)
			{
				return null;
			}
			
			slotObject.onPickupFromSlot(player, stackInSlot);
		}

		return stack;
	}
}
