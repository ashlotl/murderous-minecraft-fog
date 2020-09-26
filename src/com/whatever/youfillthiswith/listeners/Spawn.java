package com.whatever.youfillthiswith.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Spawn implements Listener{
	private static Spawn instance;
	public static Spawn instance() {
		if (instance==null) {
			instance = new Spawn();
		}
		return instance;
	}
	
	@EventHandler
	public void onLlamaSpitSpawn(EntitySpawnEvent e) {//Boy am I getting carried away
		if (e.getEntityType()==EntityType.LLAMA_SPIT) {
			e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 20, false);
			for (Player p : e.getEntity().getWorld().getPlayers()) {
				p.sendTitle("Llama Lord Is Angry", "Fear him", 1, 20, 1);
			}
		}
	}
	
	@EventHandler
	public void onAreaEffectSpawn(EntitySpawnEvent e) {//removes those pesky infinite regeneration creeper clouds
		if (e.getEntity() instanceof AreaEffectCloud) {
			AreaEffectCloud a = (AreaEffectCloud)e.getEntity();
			for (PotionEffect p : a.getCustomEffects()) {
				if (p.getDuration()>1000) {
					a.removeCustomEffect(p.getType());
					a.addCustomEffect(new PotionEffect(p.getType(),1,1),false);
				}
			}
		}
	}
	
	@EventHandler
	public void onMonsterSpawn(EntitySpawnEvent e) {
		if (e.getEntity() instanceof Monster) {
			Monster m = (Monster) e.getEntity();
			m.setSilent(true);
			m.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,10000,5));
			m.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,10000,2));
			m.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,10000,2));
		}
		if (e.getEntityType()==EntityType.ZOMBIE||e.getEntityType()==EntityType.SKELETON) {
			OfflinePlayer[] list = Bukkit.getOfflinePlayers();
			if (list.length>0) {
				OfflinePlayer mimic = list[(int)(Math.random()*list.length)];
				Monster m = (Monster) e.getEntity();
				m.setCustomName(mimic.getName());
				Inventory search = mimic.getPlayer().getInventory();
				
				//find best helmet
				
				findAndReplicateItem(m, search, Material.LEATHER_HELMET, mimic, false, true);//we'll omit chainmail as an easter egg, should be interesting
				findAndReplicateItem(m, search, Material.IRON_HELMET, mimic, false, true);
				findAndReplicateItem(m, search, Material.DIAMOND_HELMET, mimic, false, true);
				findAndReplicateItem(m, search, Material.NETHERITE_HELMET, mimic, false, true);
				
				
				//find best chestplate
				
				findAndReplicateItem(m, search, Material.LEATHER_CHESTPLATE, mimic, false, true);//we'll omit chainmail as an easter egg, should be interesting
				findAndReplicateItem(m, search, Material.IRON_CHESTPLATE, mimic, false, true);
				findAndReplicateItem(m, search, Material.DIAMOND_CHESTPLATE, mimic, false, true);
				findAndReplicateItem(m, search, Material.NETHERITE_CHESTPLATE, mimic, false, true);
				
				
				//find best boots
				
				findAndReplicateItem(m, search, Material.LEATHER_BOOTS, mimic, false, true);//we'll omit chainmail as an easter egg, should be interesting
				findAndReplicateItem(m, search, Material.IRON_BOOTS, mimic, false, true);
				findAndReplicateItem(m, search, Material.DIAMOND_BOOTS, mimic, false, true);
				findAndReplicateItem(m, search, Material.NETHERITE_BOOTS, mimic, false, true);
			}
		}
		if (e.getEntityType()==EntityType.ZOMBIE) {
			OfflinePlayer[] list = Bukkit.getOfflinePlayers();
			if (list.length>0) {
				OfflinePlayer mimic = list[(int)(Math.random()*list.length)];
				Zombie m = (Zombie) e.getEntity();
				m.setCustomName(mimic.getName());
				Inventory search = mimic.getPlayer().getInventory();
				
				
				//find best sword (better swords will overwrite)
				
				findAndReplicateItem(m, search, Material.WOODEN_SWORD, mimic, false, false);
				findAndReplicateItem(m, search, Material.STONE_SWORD, mimic, false, false);
				findAndReplicateItem(m, search, Material.IRON_SWORD, mimic, false, false);
				findAndReplicateItem(m, search, Material.DIAMOND_SWORD, mimic, false, false);
				findAndReplicateItem(m, search, Material.NETHERITE_SWORD, mimic, false, false);
				
			}
		} else if (e.getEntityType()==EntityType.CREEPER) {
			//unused atm
		}
	}
	
	private void findAndReplicateItem(Monster m, Inventory search, Material itemType, OfflinePlayer mimic, boolean offHand, boolean wear) {
		String[] fakeSynonyms = {"Fake", "Lookalike", "Imposter", "Definitely", "Seemingly", "Twin", "Murdurous Edition", "Reincarnated"};//TODO CONFIG
		
		String fs1 = fakeSynonyms[(int)(fakeSynonyms.length*Math.random())];
		String fs2 = fakeSynonyms[(int)(fakeSynonyms.length*Math.random())];
		if (search.contains(itemType)) {
			ItemStack ws = new ItemStack(itemType);
			ItemMeta mt = ws.getItemMeta();
			ItemMeta cp = search.getItem(search.first(itemType)).getItemMeta();
			String itemName=cp.getDisplayName();
			if (itemName.trim().equals("")) {
				itemName=itemType.name();
			}
			mt.setDisplayName(fs1+ " "+mimic.getName()+"'s "+fs2+" "+itemName);
			ws.setItemMeta(mt);
			if (wear) {
				if (itemType==Material.LEATHER_HELMET||itemType==Material.IRON_HELMET||itemType==Material.DIAMOND_HELMET||itemType==Material.NETHERITE_HELMET) {
					m.getEquipment().setHelmet(ws);
					m.getEquipment().setHelmetDropChance(0.0f);
				}
				if (itemType==Material.LEATHER_CHESTPLATE||itemType==Material.IRON_CHESTPLATE||itemType==Material.DIAMOND_CHESTPLATE||itemType==Material.NETHERITE_CHESTPLATE) {
					m.getEquipment().setChestplate(ws);
					m.getEquipment().setChestplateDropChance(0.0f);
				}
				if (itemType==Material.LEATHER_LEGGINGS||itemType==Material.IRON_LEGGINGS||itemType==Material.DIAMOND_LEGGINGS||itemType==Material.NETHERITE_LEGGINGS) {
					m.getEquipment().setLeggings(ws);
					m.getEquipment().setLeggingsDropChance(0.0f);
				}
				if (itemType==Material.LEATHER_BOOTS||itemType==Material.IRON_BOOTS||itemType==Material.DIAMOND_BOOTS||itemType==Material.NETHERITE_BOOTS) {
					m.getEquipment().setBoots(ws);
					m.getEquipment().setBootsDropChance(0.0f);
				}
			} else if (offHand) {
				m.getEquipment().setItemInOffHand(ws);
				m.getEquipment().setItemInOffHandDropChance(1.0f);
			} else {
				m.getEquipment().setItemInMainHand(ws);
				m.getEquipment().setItemInMainHandDropChance(1.0f);
			}
			
		}
	}
}
