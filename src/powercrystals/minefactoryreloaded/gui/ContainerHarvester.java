package powercrystals.minefactoryreloaded.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import powercrystals.minefactoryreloaded.plants.TileEntityHarvester;

public class ContainerHarvester extends ContainerFactoryPowered
{
	public ContainerHarvester(TileEntityHarvester te, InventoryPlayer inv)
	{
		super(te, inv);
	}

	@Override
	public void updateCraftingResults()
	{
		super.updateCraftingResults();
		
		for(int i = 0; i < crafters.size(); i++)
		{
			((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 100, getSetting("silkTouch"));
			((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 101, getSetting("harvestSmallMushrooms"));
			((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 102, getSetting("harvestJungleWood"));
		}
	}
	
	@Override
	public void updateProgressBar(int var, int value)
	{
		super.updateProgressBar(var, value);
		
		if(var == 100) setSetting("silkTouch", value);
		if(var == 101) setSetting("harvestSmallMushrooms", value);
		if(var == 102) setSetting("harvestJungleWood", value);
	}
	
	private int getSetting(String setting)
	{
		TileEntityHarvester h = (TileEntityHarvester)_te;
		if(h.getSettings().get(setting) == null)
		{
			return 0;
		}
		return h.getSettings().get(setting) ? 1 : 0;
	}
	
	private void setSetting(String setting, int value)
	{
		((TileEntityHarvester)_te).getSettings().put(setting, value == 0 ? false : true);
	}
}
