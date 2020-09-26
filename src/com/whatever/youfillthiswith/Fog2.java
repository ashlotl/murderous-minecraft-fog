package com.whatever.youfillthiswith;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class Fog2 extends JavaPlugin implements Listener {
	
	public static Fog2 instance;
	
	
	public static PluginData pd;
	
	@Override
	public void onEnable() {
		instance = this;
		
		com.whatever.youfillthiswith.crafting.Bed.registerBedRecipes("Holy Bed (probably shrek's, imo)");
		com.whatever.youfillthiswith.crafting.CouragePotion.registerCouragePotionRecipes("LSD Bucket");
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(com.whatever.youfillthiswith.listeners.BlockUpdate.instance(), this);
		pm.registerEvents(com.whatever.youfillthiswith.listeners.Craft.instance(), this);
		pm.registerEvents(com.whatever.youfillthiswith.listeners.Death.instance(), this);
		pm.registerEvents(com.whatever.youfillthiswith.listeners.EntityState.instance(), this);
		pm.registerEvents(com.whatever.youfillthiswith.listeners.Misc.instance(), this);
		pm.registerEvents(com.whatever.youfillthiswith.listeners.Spawn.instance(), this);
		pm.registerEvents(com.whatever.youfillthiswith.listeners.Tick.instance(), this);
		pm.registerEvents(com.whatever.youfillthiswith.listeners.Click.instance(), this);
		
		
		ConfigData.load();//does nothing rn
		pd = PluginData.load();
	}
	@Override
	public void onDisable() {
		ConfigData.save();//if relevant, until commands to mess with parameters are added this is irrelevant
		pd.save();
	}
}
