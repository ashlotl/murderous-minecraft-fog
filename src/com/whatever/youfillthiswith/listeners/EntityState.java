package com.whatever.youfillthiswith.listeners;

import java.util.concurrent.Callable;
import java.util.function.Function;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.whatever.youfillthiswith.Fog2;

public class EntityState implements Listener{
	private static EntityState instance;
	public static EntityState instance() {
		if (instance==null) {
			instance = new EntityState();
		}
		return instance;
	}
	
	@EventHandler
	public void onMonsterBurn(EntityDamageEvent e) {
		if (e.getCause()==DamageCause.FIRE||e.getCause()==DamageCause.FIRE_TICK) {
			if (e.getEntity() instanceof Monster) {
				Location l = e.getEntity().getLocation();
				e.getEntity().remove();
				Blaze spawned = l.getWorld().spawn(l, Blaze.class);
				spawned.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,10000,255));
				
			}
		}
	}
}
