package me.virustotal.dynamicgui.listener;

import java.util.List;
import java.util.UUID;

import me.virustotal.dynamicgui.DynamicGUI;
import me.virustotal.dynamicgui.api.GuiApi;
import me.virustotal.dynamicgui.entity.player.PlayerWrapper;
import me.virustotal.dynamicgui.event.inventory.InventoryClickEvent;
import me.virustotal.dynamicgui.function.Function;
import me.virustotal.dynamicgui.gui.GUI;
import me.virustotal.dynamicgui.gui.Slot;
import me.virustotal.dynamicgui.inventory.ItemStackWrapper;
import me.virustotal.dynamicgui.util.FunctionUtil;

import com.clubobsidian.trident.EventHandler;
import com.clubobsidian.trident.Listener;

public class InventoryClickListener implements Listener {

	@EventHandler
	public void invClick(final InventoryClickEvent e)
	{
		if(e.getPlayerWrapper().getOpenInventoryWrapper() == null)
		{
			DynamicGUI.get().getLogger().info("Open inventory wrapper is null");
			return;
		}
		if(!GuiApi.hasGuiTitle(e.getPlayerWrapper().getOpenInventoryWrapper().getTitle()))
		{
			DynamicGUI.get().getLogger().info("Open gui does not have correct title, title is : " + e.getPlayerWrapper().getOpenInventoryWrapper().getTitle());
			return;
		}
		
		ItemStackWrapper<?> item = e.getInventoryWrapper().getItem(e.getSlot());
		if(item.getItemStack() == null)
		{
			DynamicGUI.get().getLogger().info("ItemStack is null");
			return;
		}

		e.setCancelled(true);
		
		if(e.getClick() == null) //For other types of clicks besides left, right, middle
			return;


		DynamicGUI.get().getLogger().info("Click is not null");
		PlayerWrapper<?> player = e.getPlayerWrapper();
		if(GuiApi.hasGUICurrently(player))
		{
			DynamicGUI.get().getLogger().info("Has gui currently");
			if(GuiApi.hasGuiTitle(e.getInventoryWrapper().getTitle()))
			{
				DynamicGUI.get().getLogger().info("GuiApi has title");
				GUI gui = GuiApi.getCurrentGUI(player);
				Slot slot = null;
				String tag = item.getTag();
				DynamicGUI.get().getLogger().info("Tag data for item at slot  " + e.getSlot() + " is " + tag);
				if(tag != null)
				{
					for(int j = 0; j < gui.getSlots().size(); j++)
					{
						if(gui.getSlots().get(j) != null)
						{
							try
							{
								UUID uuid = UUID.fromString(tag);
								if(gui.getSlots().get(j) != null)
								{
									if(gui.getSlots().get(j).getUUID() != null)
									{
										if(gui.getSlots().get(j).getUUID().equals(uuid))
										{
											slot = gui.getSlots().get(j);
											break;
										}
									}
								}

							}
							catch(SecurityException | IllegalArgumentException ex)
							{
								ex.printStackTrace();
							}
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
		}
	}
}