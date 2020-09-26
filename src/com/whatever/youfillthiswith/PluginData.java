package com.whatever.youfillthiswith;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import com.whatever.youfillthiswith.helpertypes.SLocation;

public class PluginData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4828631541037430573L;
	public ArrayList<SLocation> bedList = null;//TODO sort for faster access (don't want to loop through 50 beds to find the one in question)
	public Hashtable<UUID, Boolean> milkList = null;
	
	public PluginData() {
		bedList = new ArrayList<SLocation>();
		milkList = new Hashtable<UUID, Boolean>();
	}
	
	public void fixNulls() {
		//TODO actually bother to look up java reflection so I don't have to do this for every field
		if (bedList == null) {
			System.out.println("Warning! Field bedList is null! You can ignore this warning if you have just done an update.");
			bedList = new ArrayList<SLocation>();
		}
		
		if (milkList == null) {
			System.out.println("Warning! Field milkList is null! You can ignore this warning if you have just done an update.");
			milkList = new Hashtable<UUID, Boolean>();
		}
	}
	
	public static PluginData load() {
		try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream("DarkFogData")));
            PluginData data = (PluginData) in.readObject();
            in.close();
            data.fixNulls();
            return data;
        } catch (IOException | ClassNotFoundException e) {
        	System.out.println("NO PLUGIN DATA FILE FOUND (or outdated, using wrong version?), resetting");
        	return new PluginData();
        }
	}
	
	public void save() {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream("DarkFogData")));
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public void bedListRemoveMatch(SLocation l) {
		for (int i=0;i<bedList.size();i++) {
			SLocation c = bedList.get(i);
			if (c.world.equals(l.world)&&c.x==l.x&&c.y==l.y&&c.z==l.z) {
				System.out.println("Once per piston push");
				bedList.remove(i);
			}
		}
	}
}
