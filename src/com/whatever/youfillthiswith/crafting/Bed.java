package com.whatever.youfillthiswith.crafting;

import com.whatever.youfillthiswith.Fog2;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Bed {
	
	public static void registerBedRecipes(String name) {
		ItemStack item = new ItemStack(Material.YELLOW_BED);
    	ItemMeta meta=item.getItemMeta();
    	meta.setDisplayName(name);
    	item.setItemMeta(meta);
    	meta.setUnbreakable(true);
    	NamespacedKey key = new NamespacedKey(Fog2.instance, "bed");
    	ShapedRecipe bed = new ShapedRecipe(key, item);
    	bed.shape("AAA","BBB","CCC");
    	bed.setIngredient('A', Material.DIAMOND);
    	bed.setIngredient('B', Material.WHITE_WOOL);
    	bed.setIngredient('C', Material.IRON_BLOCK);
    	Bukkit.getServer().addRecipe(bed);
    	
    	ItemStack item2 = new ItemStack(Material.YELLOW_BED);
    	ItemMeta meta2=item2.getItemMeta();
    	meta2.setDisplayName(name);
    	item2.setItemMeta(meta2);
    	meta2.setUnbreakable(true);
    	NamespacedKey key2 = new NamespacedKey(Fog2.instance, "bed2");
    	ShapedRecipe bed2 = new ShapedRecipe(key2, item2);
    	bed2.shape("AAA","BBB","CCC");
    	bed2.setIngredient('A', Material.DIAMOND);
    	bed2.setIngredient('B', Material.BROWN_WOOL);
    	bed2.setIngredient('C', Material.IRON_BLOCK);
    	Bukkit.getServer().addRecipe(bed2);
    	
    	ItemStack item3 = new ItemStack(Material.YELLOW_BED);
    	ItemMeta meta3=item3.getItemMeta();
    	meta3.setDisplayName(name);
    	item3.setItemMeta(meta3);
    	meta3.setUnbreakable(true);
    	NamespacedKey key3 = new NamespacedKey(Fog2.instance, "bed3");
    	ShapedRecipe bed3 = new ShapedRecipe(key3, item3);
    	bed3.shape("AAA","BBB","CCC");
    	bed3.setIngredient('A', Material.DIAMOND);
    	bed3.setIngredient('B', Material.BLACK_WOOL);
    	bed3.setIngredient('C', Material.IRON_BLOCK);
    	Bukkit.getServer().addRecipe(bed3);
    	
    	ItemStack item4 = new ItemStack(Material.YELLOW_BED);
    	ItemMeta meta4=item4.getItemMeta();
    	meta4.setDisplayName(name);
    	item4.setItemMeta(meta4);
    	meta4.setUnbreakable(true);
    	NamespacedKey key4 = new NamespacedKey(Fog2.instance, "bed4");
    	ShapedRecipe bed4 = new ShapedRecipe(key4, item4);
    	bed4.shape("AAA","BBB","CCC");
    	bed4.setIngredient('A', Material.DIAMOND);
    	bed4.setIngredient('B', Material.GRAY_WOOL);
    	bed4.setIngredient('C', Material.IRON_BLOCK);
    	Bukkit.getServer().addRecipe(bed4);
    	
    	ItemStack item5 = new ItemStack(Material.YELLOW_BED);
    	ItemMeta meta5=item5.getItemMeta();
    	meta5.setDisplayName(name);
    	item5.setItemMeta(meta5);
    	meta5.setUnbreakable(true);
    	NamespacedKey key5 = new NamespacedKey(Fog2.instance, "bed5");
    	ShapedRecipe bed5 = new ShapedRecipe(key5, item5);
    	bed5.shape("AAA","BBB","CCC");
    	bed5.setIngredient('A', Material.DIAMOND);
    	bed5.setIngredient('B', Material.LIGHT_GRAY_WOOL);
    	bed5.setIngredient('C', Material.IRON_BLOCK);
    	Bukkit.getServer().addRecipe(bed5);
    	
    	ItemStack item6 = new ItemStack(Material.YELLOW_BED);
    	ItemMeta meta6=item6.getItemMeta();
    	meta6.setDisplayName(name);
    	item6.setItemMeta(meta6);
    	meta6.setUnbreakable(true);
    	NamespacedKey key6 = new NamespacedKey(Fog2.instance, "bed6");
    	ShapedRecipe bed6 = new ShapedRecipe(key6, item6);
    	bed6.shape("AAA","BBB","CCC");
    	bed6.setIngredient('A', Material.DIAMOND);
    	bed6.setIngredient('B', Material.PINK_WOOL);
    	bed6.setIngredient('C', Material.IRON_BLOCK);
    	Bukkit.getServer().addRecipe(bed6);
	}
}
