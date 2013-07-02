package org.cjxd.gamemaker.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;


public class PlayerMobKillEvent extends EntityDeathEvent {

    private static final HandlerList handlers = new HandlerList();

    public PlayerMobKillEvent(EntityDeathEvent e) {
        super(e.getEntity(), e.getDrops(), e.getDroppedExp());
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    public LivingEntity getVictim() {
        return this.getEntity();
    }
    
    public Player getKiller() {
        return this.getEntity().getKiller();
    }
}
