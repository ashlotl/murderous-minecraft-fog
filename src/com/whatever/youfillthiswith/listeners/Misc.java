package com.whatever.youfillthiswith.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Misc implements Listener {
	private static Misc instance;
	public static Misc instance() {
		if (instance==null) {
			instance = new Misc();
		}
		return instance;
	}
	@EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
    	e.setFoodLevel(20);
    }
}
