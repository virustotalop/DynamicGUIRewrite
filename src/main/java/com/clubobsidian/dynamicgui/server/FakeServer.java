package com.clubobsidian.dynamicgui.server;

import java.util.Collection;
import java.util.UUID;

import com.clubobsidian.dynamicgui.entity.PlayerWrapper;
import com.clubobsidian.dynamicgui.plugin.DynamicGuiPlugin;
import com.clubobsidian.dynamicgui.scheduler.Scheduler;

public abstract class FakeServer {

	private Scheduler scheduler;
	public FakeServer(Scheduler scheduler)
	{
		this.scheduler = scheduler;
	}
	
	public Scheduler getScheduler()
	{
		return this.scheduler;
	}
	
	public abstract void broadcastMessage(String message);
	public abstract void dispatchServerCommand(String command);
	public abstract PlayerWrapper<?> getPlayer(UUID uuid);
	public abstract PlayerWrapper<?> getPlayer(String name);
	public abstract Collection<PlayerWrapper<?>> getOnlinePlayers();
	public abstract int getGlobalPlayerCount();
	public abstract ServerType getType();
	public abstract void registerOutgoingPluginChannel(DynamicGuiPlugin plugin, String channel);
	public abstract void registerIncomingPluginChannel(DynamicGuiPlugin plugin, String channel);
	
}