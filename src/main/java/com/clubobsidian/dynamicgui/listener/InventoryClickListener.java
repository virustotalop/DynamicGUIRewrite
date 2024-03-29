package com.clubobsidian.dynamicgui.listener;

import java.util.List;

import com.clubobsidian.dynamicgui.DynamicGui;
import com.clubobsidian.dynamicgui.entity.PlayerWrapper;
import com.clubobsidian.dynamicgui.event.inventory.InventoryClickEvent;
import com.clubobsidian.dynamicgui.function.Function;
import com.clubobsidian.dynamicgui.gui.Gui;
import com.clubobsidian.dynamicgui.gui.Slot;
import com.clubobsidian.dynamicgui.inventory.ItemStackWrapper;
import com.clubobsidian.dynamicgui.manager.dynamicgui.GuiManager;
import com.clubobsidian.dynamicgui.util.FunctionUtil;

import com.clubobsidian.trident.EventHandler;
import com.clubobsidian.trident.Listener;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void invClick(final InventoryClickEvent e)
	{
		if(e.getPlayerWrapper().getOpenInventoryWrapper() == null)
		{
			return;
		}
		
		if(!GuiManager.get().hasGuiCurrently(e.getPlayerWrapper()))
		{
			return;
		}
		
		e.setCanceled(true);
		
		ItemStackWrapper<?> item = e.getInventoryWrapper().getItem(e.getSlot());
		if(item.getItemStack() == null)
		{
			return;
		}

		if(e.getClick() == null) //For other types of clicks besides left, right, middle
			return;

		PlayerWrapper<?> player = e.getPlayerWrapper();
		Gui gui = GuiManager.get().getCurrentGui(player);
		Slot slot = null;
		for(Slot s : gui.getSlots())
		{
			if(e.getSlot() == s.getIndex())
			{
				slot = s;
				break;
			}
		}

		if(slot == null)
			return;

		List<Function> functions = slot.getFunctions();
		List<Function> leftClickFunctions = slot.getLeftClickFunctions();
		List<Function> rightClickFunctions = slot.getRightClickFunctions();
		List<Function> middleClickFunctions = slot.getMiddleClickFunctions();

		if(functions == null && leftClickFunctions == null && rightClickFunctions == null && middleClickFunctions != null)
			return;

		Boolean close = null;
		if(slot.getClose() != null)
			close = slot.getClose();
		else if(gui.getClose() != null)
			close = gui.getClose();
		else
			close = true;

		if(close)
			player.closeInventory();

		FunctionUtil.tryFunctions(slot, e.getClick(), player);

	}
}