package com.clubobsidian.dynamicgui.function.impl;

import com.clubobsidian.dynamicgui.entity.PlayerWrapper;
import com.clubobsidian.dynamicgui.function.Function;

public class CheckLevelFunction extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4927665292013293816L;

	public CheckLevelFunction(String name) 
	{
		super(name);
	}

	@Override
	public boolean function(PlayerWrapper<?> playerWrapper)
	{
		int level = -1;
		try
		{
			level = Integer.parseInt(this.getData());
		}
		catch(NumberFormatException ex)
		{
			ex.printStackTrace();
			return false;
		}
		
		return playerWrapper.getLevel() >= level;
	}
}