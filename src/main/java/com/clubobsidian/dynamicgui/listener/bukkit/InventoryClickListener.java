package com.clubobsidian.dynamicgui.listener.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.clubobsidian.dynamicgui.DynamicGUI;
import com.clubobsidian.dynamicgui.entity.player.PlayerWrapper;
import com.clubobsidian.dynamicgui.entity.player.bukkit.BukkitPlayerWrapper;
import com.clubobsidian.dynamicgui.event.inventory.Click;
import com.clubobsidian.dynamicgui.inventory.InventoryWrapper;
import com.clubobsidian.dynamicgui.inventory.bukkit.BukkitInventoryWrapper;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if(e.getInventory() == null)
			return;
		
		int slot = e.getSlot();
		if(e.getSlot() < 0 || e.getSlot() > e.getInventory().getSize())
			return;
		
		if(e.getWhoClicked() instanceof Player)
		{
			if(e.getClick() == ClickType.LEFT || e.getClick() == ClickType.RIGHT || e.getClick() == ClickType.MIDDLE)
			{
				Player player = (Player) e.getWhoClicked();
				InventoryWrapper<?> inventoryWrapper = new BukkitInventoryWrapper<Inventory>(e.getInventory());
				PlayerWrapper<?> playerWrapper = new BukkitPlayerWrapper<Player>(player);
				Click clickType = Click.valueOf(e.getClick().toString());
				com.clubobsidian.dynamicgui.event.inventory.InventoryClickEvent clickEvent = new com.clubobsidian.dynamicgui.event.inventory.InventoryClickEvent(playerWrapper, inventoryWrapper, slot, clickType);
				DynamicGUI.get().getEventManager().callEvent(clickEvent);
				if(clickEvent.isCancelled())
				{
					e.setCancelled(true);
				}
			}
		}
	}	
}