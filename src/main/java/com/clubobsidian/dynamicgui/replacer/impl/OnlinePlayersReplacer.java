package com.clubobsidian.dynamicgui.replacer.impl;

import com.clubobsidian.dynamicgui.DynamicGui;
import com.clubobsidian.dynamicgui.entity.PlayerWrapper;

public class OnlinePlayersReplacer extends Replacer {

	public OnlinePlayersReplacer(String toReplace) 
	{
		super(toReplace);
	}

	@Override
	public String replacement(String text, PlayerWrapper<?> player)
	{
		return String.valueOf(DynamicGui.get().getServer().getGlobalPlayerCount());
	}
}