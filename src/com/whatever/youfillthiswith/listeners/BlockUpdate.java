package com.whatever.youfillthiswith.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.whatever.youfillthiswith.ConfigData;
import com.whatever.youfillthiswith.Fog2;
import com.whatever.youfillthiswith.helpertypes.SLocation;

public class BlockUpdate implements Listener {
	
	public static BlockUpdate instance;
	
	public static BlockUpdate instance() {
		if (instance==null) {
			instance = new BlockUpdate();
		}
		return instance;
	}
	
	@EventHandler
	public static void onAnyPlace(BlockPlaceEvent e) {
		if (e.getBlock().getType()!=Material.YELLOW_BED&&e.getBlock().getType()!=Material.TNT&&e.getBlock().getType()!=Material.FIRE) {
			Location l = e.getBlock().getLocation();
			for (SLocation bed : Fog2.pd.bedList) {
				if (l.getWorld().getName().equals(bed.world)&&Math.sqrt(Math.pow(bed.x-l.getBlockX(),2)+Math.pow(bed.z-l.getBlockZ(),2))<ConfigData.BED_RADIUS) {
					return;
				}
			}
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public static void onAnyBreak(BlockBreakEvent e) {
		if (e.getBlock().getType()!=Material.YELLOW_BED&&e.getBlock().getType()!=Material.TNT&&e.getBlock().getType()!=Material.TALL_GRASS&&e.getBlock().getType()!=Material.GRASS&&e.getBlock().getType()!=Material.DIAMOND_ORE) {//TODO compare against config file
			Location l = e.getBlock().getLocation();
			for (SLocation bed : Fog2.pd.bedList) {
				if (l.getWorld().getName().equals(bed.world)&&Math.sqrt(Math.pow(bed.x-l.getBlockX(),2)+Math.pow(bed.z-l.getBlockZ(),2))<ConfigData.BED_RADIUS) {
					return;
				}
			}
			e.setCancelled(true);
		}
	}

	
	@EventHandler
	public void onBedPlace(BlockPlaceEvent e) {
		if (e.getBlock().getBlockData().getMaterial()==Material.YELLOW_BED) {
			Location l = e.getBlock().getLocation();
			Fog2.pd.bedList.add(new SLocation(e.getBlock().getLocation()));
			
		}
	}
	
	@EventHandler
	public void onBedBreak(BlockBreakEvent e) {
		if (e.getBlock().getBlockData().getMaterial()==Material.YELLOW_BED) {
			e.setCancelled(true);//IT IS INDESTRUCTIBLE
		}
	}
	private boolean playerDroppedIt = false;
	@EventHandler
	public void onBedDropItem(ItemSpawnEvent e) {//technically this should have it's own class, but meh, I need to fix drops for this block so I'll put it here
		if (playerDroppedIt) {
			playerDroppedIt = false;
			return;
		}
		if (e.getEntity().getItemStack().getType()==Material.YELLOW_BED) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onPlayerDropBedItem(PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().getType()==Material.YELLOW_BED) {
			playerDroppedIt = true;
		}
	}
	@EventHandler
	public void onDropperDropBedItem(BlockDispenseEvent e) {
		if (e.getItem()!=null&&e.getItem().getType()==Material.YELLOW_BED) {
			playerDroppedIt = true;
		}
	}
	
	
	private boolean tryBedPlace(Block b, BlockPistonExtendEvent e, Location offset) {
		Location tlu = b.getLocation().add(offset);
		Material testu = b.getWorld().getBlockAt(tlu).getType();
		Location tlu2u = getFirstAdjacentBlock(b.getLocation(),Material.YELLOW_BED);
		BlockData otherBlockInfo = b.getWorld().getBlockAt(tlu2u).getBlockData();
		if (tlu2u!=null) {
			Location tlu2=tlu2u.add(offset);
			Material testu2 = b.getWorld().getBlockAt(tlu2).getType();
			if ((testu==Material.AIR||testu==Material.WATER||testu==Material.LAVA) && (testu2==Material.AIR||testu2==Material.WATER||testu2==Material.LAVA)) {
				b.getWorld().getBlockAt(tlu).setBlockData(b.getBlockData());
				b.getWorld().getBlockAt(tlu2).setBlockData(otherBlockInfo);
				Fog2.pd.bedListRemoveMatch(new SLocation(b.getLocation()));
				Fog2.pd.bedListRemoveMatch(new SLocation(tlu2));
				Fog2.pd.bedList.add(new SLocation(tlu));
			} else {
				e.setCancelled(true);//IT IS INDESTRUCTIBLE
				return true;
			}
		} else {//wtf
			e.setCancelled(true);//IT IS INDESTRUCTIBLE
			return true;
		}
		return false;
	}
	
	@EventHandler
	public void onBedGetPushed(BlockPistonExtendEvent e) {
		for (Block b : e.getBlocks()) {
			if (b.getType()==Material.YELLOW_BED) {
				
				if (e.getDirection()==BlockFace.UP&&tryBedPlace(b,e,new Location(b.getWorld(),0,1,0))) {
					return;
				}
				
				if (e.getDirection()==BlockFace.NORTH&&tryBedPlace(b,e,new Location(b.getWorld(),0,0,-1))) {
					return;
				}
				
				if (e.getDirection()==BlockFace.EAST&&tryBedPlace(b,e,new Location(b.getWorld(),1,0,0))) {
					return;
				}
				
				if (e.getDirection()==BlockFace.SOUTH&&tryBedPlace(b,e,new Location(b.getWorld(),0,0,1))) {
					return;
				}
				
				if (e.getDirection()==BlockFace.WEST&&tryBedPlace(b,e,new Location(b.getWorld(),-1,0,0))) {
					return;
				}
				
				if (e.getDirection()==BlockFace.DOWN&&tryBedPlace(b,e,new Location(b.getWorld(),0,-1,0))) {
					return;
				}
			}
		}
	}

	private Location getFirstAdjacentBlock(Location location, Material type) {
		for (int x = location.getBlockX()-1;x<location.getBlockX()+2;x++) {
			for (int y = location.getBlockY()-1;y<location.getBlockY()+2;y++) {
				for (int z = location.getBlockZ()-1;z<location.getBlockZ()+2;z++) {
					if (x==location.getBlockX()&&y==location.getBlockY()&&z==location.getBlockZ()) continue;
					Location l = new Location(location.getWorld(), x,y,z);
					if (location.getWorld().getBlockAt(l).getType()==type) {
						return l;
					}
				}
			}
		}
		return null;
	}
}
