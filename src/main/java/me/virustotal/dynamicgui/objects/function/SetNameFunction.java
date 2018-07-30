package me.virustotal.dynamicgui.objects.function;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.virustotal.dynamicgui.DynamicGUI;
import me.virustotal.dynamicgui.gui.Slot;
import me.virustotal.dynamicgui.nbt.NBTItem;
import me.virustotal.dynamicgui.objects.Function;

public class SetNameFunction extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5599516930903780834L;

	public SetNameFunction(String name) 
	{
		super(name);
	}

	public boolean function(Player player)
	{
		Slot slot = this.getOwner();
		if(slot != null)
		{
			if(player.getOpenInventory() != null)
			{
				InventoryView inv = player.getOpenInventory();
				if(inv != null)
				{
					for(int i = 0; i < inv.countSlots(); i++)
					{
						ItemStack item = inv.getItem(i);
						if(item != null && item.getType() != Material.AIR)
						{
							try
							{
								NBTItem nbtItem = new NBTItem(item);
								if(nbtItem.hasKey(DynamicGUI.TAG))
								{
									UUID uuid = UUID.fromString(nbtItem.getString(DynamicGUI.TAG));

									if(slot.getUUID().equals(uuid))
									{
										ItemMeta meta = item.getItemMeta();
										meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getData()));
										item.setItemMeta(meta);
										inv.setItem(i, item);
										break;
									}
								}
							}
							catch(SecurityException | IllegalArgumentException ex)
							{
								ex.printStackTrace();
							}
						}
					}
				}
			}
		}
		return true;
	}	
}