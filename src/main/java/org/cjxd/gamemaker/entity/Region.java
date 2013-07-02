package org.cjxd.gamemaker.entity;

import org.bukkit.Location;
import org.bukkit.World;


public class Region {
    private Location start, end;
    
    /**
     * Create a new region from two points in a world
     */
    public Region(World world, double x1, double y1, double z1,
                                double x2, double y2, double z2) {
        this(new Location(world, x1, y1, z1), new Location(world, x2, y2, z2));
    }
    
    /**
     * Create a new region from two locations
     */
    public Region(Location start, Location end) {
        if (!start.getWorld().equals(end.getWorld())) {
            throw new IllegalArgumentException("Start and end points are not in the same world!");
        }
        this.start = start;
        this.end = end;
    }
    
    /**
     * @return True if location is within region bounds
     */
    public boolean contains(double x, double y, double z) {
        boolean contains = true;

        // The cases where the point can't possibly be in the region
        if (start.getX() < x && end.getX() < x) contains = false;
        if (start.getX() > x && end.getX() > x) contains = false;
        if (start.getY() < y && end.getY() < y) contains = false;
        if (start.getY() > y && end.getY() > y) contains = false;
        if (start.getZ() < z && end.getZ() < z) contains = false;
        if (start.getZ() > z && end.getZ() > z) contains = false;
        
        return contains;
    }
    
    /**
     * @param l Location to be checked
     * @return True if location is within region bounds
     */
    public boolean contains(Location l) {
        if (l.getWorld().equals(start.getWorld())) {
            return contains(l.getX(), l.getY(), l.getZ());
        } else {
            return false;
        }
    }
}
