package com.whatever.youfillthiswith.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.whatever.youfillthiswith.Util;

public class Death implements Listener {
public static Death instance;
	
	public static Death instance() {
		if (instance==null) {
			instance = new Death();
		}
		return instance;
	}
	
	//TODO clear exp
	@EventHandler
	public void onPlayerDeath(EntityDeathEvent e) {
		if (e.getEntityType()==EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			p.getInventory().remove(Material.YELLOW_BED);
			p.setTotalExperience(0);
//			p.giveExp(-Util.getTotalExperience(p));
		}
	}
}
