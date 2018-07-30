package me.virustotal.dynamicgui.objects.function;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.virustotal.dynamicgui.objects.Function;

public class StatisticFunction extends Function {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8624786841614185001L;

	public StatisticFunction(String name) 
	{
		super(name);
	}
	
	@Override
	public boolean function(Player player)
	{
		String[] split = this.getData().split(",");

		if(split.length == 3)
		{
			Statistic stat = Statistic.valueOf(split[0]);
			String type = split[1];
			Integer num = Integer.parseInt(split[2]);

			if(stat == Statistic.MINE_BLOCK)
			{
				return player.getStatistic(stat, Material.valueOf(type)) >= num;
			}
			else if(stat == Statistic.KILL_ENTITY)
			{
				return player.getStatistic(stat, EntityType.valueOf(type)) >= num;
			}
		}
		else if(split.length == 2)
		{
			Statistic stat = Statistic.valueOf(split[0]);
			Integer num = Integer.parseInt(split[1]);
			return player.getStatistic(stat) >= num;
		}
		return false;
	}
}