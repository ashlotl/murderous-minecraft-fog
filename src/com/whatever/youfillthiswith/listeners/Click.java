package com.whatever.youfillthiswith.listeners;

import java.util.concurrent.Callable;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.whatever.youfillthiswith.Fog2;

public class Click implements Listener {
	public static Click instance;
	
	public static Click instance() {
		if (instance==null) {
			instance = new Click();
		}
		return instance;
	}
	
	
	@EventHandler
	public void onPlayerConsume(PlayerItemConsumeEvent e) {
		if (e.getItem().getType()==Material.MILK_BUCKET) {
			if (e.getItem().getItemMeta().hasLore()&&e.getItem().getItemMeta().getLore().contains("Removes the blindness from your soul")) {
				e.getPlayer().sendMessage("Aaaawww yeaaaah");
				Fog2.pd.milkList.put(e.getPlayer().getUniqueId(), true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerEat(PlayerInteractEvent e) {
		if(e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK&&(!e.getClickedBlock().getType().isInteractable()||e.getClickedBlock().getType()==Material.PISTON_HEAD)) {
    		if (e.getItem()!=null) {
    			//food
	    		if (e.getItem().getType()==Material.GOLDEN_APPLE||e.getItem().getType()==Material.ENCHANTED_GOLDEN_APPLE||e.getItem().getType()==Material.GOLDEN_CARROT) {
	    			double proposedHealth=e.getPlayer().getHealth()+15.5;
	    			if (e.getPlayer().getHealth()<20) {
	    				e.getPlayer().setHealth(proposedHealth > 20 ? 20 : proposedHealth);
	    				e.getItem().setAmount(e.getItem().getAmount()-1);
	    			}
	    		}
	    		if (e.getItem().getType()==Material.COOKED_BEEF||e.getItem().getType()==Material.COOKED_MUTTON||e.getItem().getType()==Material.COOKED_PORKCHOP||e.getItem().getType()==Material.COOKED_SALMON) {
	    			double proposedHealth=e.getPlayer().getHealth()+7.5;
	    			if (e.getPlayer().getHealth()<20) {
	    				e.getPlayer().setHealth(proposedHealth > 20 ? 20 : proposedHealth);
	    				e.getItem().setAmount(e.getItem().getAmount()-1);
	    			}
	    		}
	    		if (e.getItem().getType()==Material.BAKED_POTATO||e.getItem().getType()==Material.BEETROOT_SOUP||e.getItem().getType()==Material.BEETROOT||e.getItem().getType()==Material.BREAD||e.getItem().getType()==Material.CARROT||e.getItem().getType()==Material.COOKED_CHICKEN||e.getItem().getType()==Material.COOKED_COD||e.getItem().getType()==Material.COOKED_RABBIT||e.getItem().getType()==Material.MUSHROOM_STEW||e.getItem().getType()==Material.RABBIT_STEW) {
	    			double proposedHealth=e.getPlayer().getHealth()+5.0;
	    			if (e.getPlayer().getHealth()<20) {
	    				e.getPlayer().setHealth(proposedHealth > 20 ? 20 : proposedHealth);
	    				e.getItem().setAmount(e.getItem().getAmount()-1);
	    			}
	    		}
	    		if (e.getItem().getType()==Material.APPLE||e.getItem().getType()==Material.DRIED_KELP||e.getItem().getType()==Material.MELON||e.getItem().getType()==Material.POTATO||e.getItem().getType()==Material.PUMPKIN_PIE||e.getItem().getType()==Material.BEEF||e.getItem().getType()==Material.CHICKEN||e.getItem().getType()==Material.MUTTON||e.getItem().getType()==Material.PORKCHOP||e.getItem().getType()==Material.RABBIT||e.getItem().getType()==Material.SALMON) {
	    			double proposedHealth=e.getPlayer().getHealth()+4.0;
	    			if (e.getPlayer().getHealth()<20) {
	    				e.getPlayer().setHealth(proposedHealth > 20 ? 20 : proposedHealth);
	    				e.getItem().setAmount(e.getItem().getAmount()-1);
	    			}
	    		}
	    		
	    		
	    		
    		}
    	}
	}
}
