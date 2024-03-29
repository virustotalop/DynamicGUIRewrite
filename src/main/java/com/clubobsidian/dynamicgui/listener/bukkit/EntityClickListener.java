package com.clubobsidian.dynamicgui.listener.bukkit;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.clubobsidian.dynamicgui.DynamicGui;
import com.clubobsidian.dynamicgui.entity.EntityWrapper;
import com.clubobsidian.dynamicgui.entity.PlayerWrapper;
import com.clubobsidian.dynamicgui.entity.bukkit.BukkitEntityWrapper;
import com.clubobsidian.dynamicgui.entity.bukkit.BukkitPlayerWrapper;

public class EntityClickListener implements Listener {

	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent e)
	{
		if(e.getRightClicked() != null)
		{
			PlayerWrapper<Player> playerWrapper = new BukkitPlayerWrapper<Player>(e.getPlayer());
			EntityWrapper<Entity> entityWrapper = new BukkitEntityWrapper<Entity>(e.getRightClicked());
			
			DynamicGui.get().getEventManager().callEvent(new com.clubobsidian.dynamicgui.event.inventory.PlayerInteractEntityEvent(playerWrapper, entityWrapper));
		}
	}
}