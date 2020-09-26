package com.whatever.youfillthiswith.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class Craft implements Listener {
public static Craft instance;
	
	public static Craft instance() {
		if (instance==null) {
			instance = new Craft();
		}
		return instance;
	}
	
	@EventHandler
    public void onCraftBed(PrepareItemCraftEvent e) {
    	if (e!=null) {
    		Recipe r = e.getRecipe();
    		if (r!=null) {
		    	Material itemType = r.getResult().getType();
		    	if (itemType.toString().contains("BED")&&!e.getInventory().contains(new ItemStack(Material.DIAMOND))) {
		    		e.getInventory().setResult(new ItemStack(Material.COARSE_DIRT));
		    	}
    		}
    	}
    }
}
