package powercrystals.minefactoryreloaded.net;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommonProxy implements IMFRProxy
{
	@Override
	public void load()
	{
		// NOOP
	}

	@Override
	public void movePlayerToCoordinates(EntityPlayer e, double x, double y, double z)
	{
		if (e instanceof EntityPlayerMP)
		{
			((EntityPlayerMP)e).playerNetServerHandler.setPlayerLocation(x, y, z, e.cameraYaw, e.cameraPitch);
		}
	}
}
