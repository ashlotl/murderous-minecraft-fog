package com.whatever.youfillthiswith.listeners;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.whatever.youfillthiswith.ConfigData;
import com.whatever.youfillthiswith.Fog2;
import com.whatever.youfillthiswith.helpertypes.SLocation;

public class Tick implements Listener {//This is considered a listener because there should be a ontick event, smh
	public static Tick instance;
	
	public static Tick instance() {
		if (instance==null) {
			instance = new Tick();
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Fog2.instance, instance.new Repeat(), 1l, 1l);
		}
		return instance;
	}
	class Repeat implements Runnable {
		public void run(){
			socialDistanceMonsters();
			executeTaskQueue();
			showBorder();
			giveFogWarnings();
		}
	}
	
	public CopyOnWriteArrayList<Callable> tasks = new CopyOnWriteArrayList<Callable>();
	
	private void executeTaskQueue() {
		for (Callable f: tasks) {
			try {
				f.call();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final int numBorderClouds = 50;
	private void showBorder() {
		for (SLocation bed:Fog2.pd.bedList) {
			World w = Bukkit.getWorld(bed.world);
			for (int step=0;step<numBorderClouds;step++) {
				double x = Math.cos((float)(step)/numBorderClouds*2*Math.PI)*ConfigData.BED_RADIUS;
				double z = Math.sin((float)(step)/numBorderClouds*2*Math.PI)*ConfigData.BED_RADIUS;
				w.spawnParticle(Particle.LANDING_OBSIDIAN_TEAR, new Location(w, x+bed.x, 128, z+bed.z), 200, 1.0, 64.0, 1.0, 1.0, null, true);
			}
		}
	}
	
	private boolean checkOutsideBorders(Entity ent) {
		for (SLocation bed:Fog2.pd.bedList) {
			if (Math.pow(ent.getLocation().getBlockX()-bed.x,2)+Math.pow(ent.getLocation().getBlockZ()-bed.z,2)<Math.pow(ConfigData.BED_RADIUS, 2)) {
				return false;
			}
		}
		return true;
	}
	
	private void fakeTimeAndWeatherOutside(Player p) {
		p.setPlayerWeather(WeatherType.DOWNFALL);
		p.setPlayerTime(14475, false);
	}
	
	private void fakeTimeAndWeatherInside(Player p) {
		p.setPlayerWeather(WeatherType.CLEAR);
		p.setPlayerTime(1000, false);
	}
	
	private void giveBlindness(Player p) {
		Boolean b = Fog2.pd.milkList.get(p.getUniqueId());
		if (b==null||b!=null&&!b) {
			PotionEffect pe = new PotionEffect(PotionEffectType.BLINDNESS, 100000, 2);
			p.addPotionEffect(pe);
		}
	}
	
	private void removeBlindness(Player p) {
		Fog2.pd.milkList.remove(p.getUniqueId());
		p.removePotionEffect(PotionEffectType.BLINDNESS);//Technically this removes blindness regardless of whether it was the fog that applied it, but it's a lot of overhead (in terms of not development but performance, because I'd like to keep the nested loops to a minimum) otherwise for something relatively unimportant
	}
	
	private void giveFogWarnings() {
		for (World world:Bukkit.getServer().getWorlds()) {
			for (Entity ent : world.getEntities()) {
				if (ent instanceof Player) {
					Player player = (Player) ent;
					boolean outside = checkOutsideBorders(ent);
					if (outside) {
						giveBlindness(player);
						fakeTimeAndWeatherOutside(player);
					} else {
						removeBlindness(player);
						fakeTimeAndWeatherInside(player);
					}
				}
			}
		}
	}
	
	private void socialDistanceMonsters() {
		for (World world:Bukkit.getServer().getWorlds()) {
			for (Entity ent : world.getEntities()) {
				if (ent instanceof Monster || ent instanceof ThrownPotion || ent instanceof Fireball) {
					for (SLocation bed : Fog2.pd.bedList) {
						Location lent = ent.getLocation();
						if (Math.sqrt(Math.pow(lent.getX()-bed.x,2)+Math.pow(lent.getZ()-bed.z,2))<ConfigData.BED_RADIUS+ConfigData.ENTITY_BUFFER_DIST) {
							if (ent instanceof Fireball) {
								ent.remove();
							}
							
							double x=(ent.getLocation().getX()-bed.x);
	            			double z=(ent.getLocation().getZ()-bed.z);
	            			double wrongDistance=Math.sqrt(x*x+z*z);
	            			double unitX=x/wrongDistance;
	            			double unitZ=z/wrongDistance;
	            			Location l = ent.getLocation();
	            			l.setX(bed.x+unitX*(ConfigData.BED_RADIUS+ConfigData.ENTITY_BUFFER_DIST));
	            			l.setZ(bed.z+unitZ*(ConfigData.BED_RADIUS+ConfigData.ENTITY_BUFFER_DIST));
	            			
	            			if (!l.getBlock().isPassable()) l.setY(l.getY()+1);
	            			
	            			ent.teleport(l);
						}
					}
				}
			}
		}
	}
	
}
