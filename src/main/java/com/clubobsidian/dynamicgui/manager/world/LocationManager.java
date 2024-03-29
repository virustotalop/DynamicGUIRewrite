package com.clubobsidian.dynamicgui.manager.world;

import com.clubobsidian.dynamicgui.DynamicGui;
import com.clubobsidian.dynamicgui.manager.world.bukkit.BukkitLocationManager;
import com.clubobsidian.dynamicgui.manager.world.sponge.SpongeLocationManager;
import com.clubobsidian.dynamicgui.server.ServerType;
import com.clubobsidian.dynamicgui.world.LocationWrapper;

public abstract class LocationManager {

	private static LocationManager instance;
	
	public static LocationManager get()
	{
		if(instance == null)
		{
			if(ServerType.SPIGOT == DynamicGui.get().getServer().getType())
			{
				instance = new BukkitLocationManager();
			}
			else if(ServerType.SPONGE == DynamicGui.get().getServer().getType())
			{
				instance = new SpongeLocationManager();
			}
		}
		return instance;
	}
	
	public Object toLocation(String location)
	{
		if(location == null || location.length() == 0)
			return null;
		if(!location.contains(","))
			return null;
		String[] split = location.split(",");
		if(split.length != 4)
			return null;
		
		try
		{
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			int z = Integer.parseInt(split[2]);
			String world = split[3];
			return this.toLocation(world, x, y, z);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public abstract Object toLocation(String world, int x, int y, int z);
	
	public abstract LocationWrapper<?> toLocationWrapper(Object location);
	
	public LocationWrapper<?> toLocationWrapper(String location)
	{
		Object obj = this.toLocation(location);
		return this.toLocationWrapper(obj);
	}
	
	public LocationWrapper<?> toLocationWrapper(String world, int x, int y, int z)
	{
		Object location = this.toLocation(world, x, y, z);
		return this.toLocationWrapper(location);
	}
}