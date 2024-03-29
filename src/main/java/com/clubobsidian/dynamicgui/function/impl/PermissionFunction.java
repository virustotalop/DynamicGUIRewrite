package com.clubobsidian.dynamicgui.function.impl;

import com.clubobsidian.dynamicgui.entity.PlayerWrapper;
import com.clubobsidian.dynamicgui.function.Function;

public class PermissionFunction extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6578996849784218130L;
	
	public PermissionFunction(String name) 
	{
		super(name);
	}
	
	@Override
	public boolean function(final PlayerWrapper<?> playerWrapper)
	{
		return playerWrapper.hasPermission(this.getData());
	}
}