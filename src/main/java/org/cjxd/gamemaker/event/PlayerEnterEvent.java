package org.cjxd.gamemaker.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.cjxd.gamemaker.entity.Region;


public class PlayerEnterEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    
    private Region region;
    
    public PlayerEnterEvent(Player who, Region region) {
        super(who);
        
        this.region = region;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    public Region getRegion() {
        return region;
    }
}
