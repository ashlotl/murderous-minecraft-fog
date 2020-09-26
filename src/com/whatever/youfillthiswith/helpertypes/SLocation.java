package com.whatever.youfillthiswith.helpertypes;

import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.World;

public class SLocation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2625730630500248180L;
	
	public String world;
	public int x;
	public int y;
	public int z;
	
	public SLocation(World _world,int _x, int _y, int _z) {
		world=_world.getName();
		x=_x;
		y=_y;
		z=_z;
	}
	public SLocation(Location l) {
		world=l.getWorld().getName();
		x=l.getBlockX();
		y=l.getBlockY();
		z=l.getBlockZ();
	}
}
