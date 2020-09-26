package com.whatever.youfillthiswith.crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.whatever.youfillthiswith.Fog2;

public class CouragePotion {
	public static void registerCouragePotionRecipes(String name) {
		ItemStack item = new ItemStack(Material.MILK_BUCKET);
    	ItemMeta meta=item.getItemMeta();
    	ArrayList<String> lore = new ArrayList<String>();
    	lore.add("Removes the blindness from your soul");
    	lore.add("No, it's not booze... Just beer.");
    	lore.add("May cause mild dysentery, American diabetes, and death.");
    	meta.setLore(lore);
    	meta.setDisplayName(name);
    	item.setItemMeta(meta);
    	meta.setUnbreakable(true);
    	NamespacedKey key = new NamespacedKey(Fog2.instance, "couragepotion");
    	ShapedRecipe bucket = new ShapedRecipe(key, item);
    	bucket.shape("BCB"," A ");
    	bucket.setIngredient('A', Material.EMERALD_BLOCK);
    	bucket.setIngredient('B', Material.IRON_BLOCK);
    	bucket.setIngredient('C', Material.MILK_BUCKET);
    	Bukkit.getServer().addRecipe(bucket);
	}
}
