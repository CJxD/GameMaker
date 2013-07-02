package org.cjxd.gamemaker.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;


public class PlayerKillEvent extends EntityDeathEvent {

    private static final HandlerList handlers = new HandlerList();

    public PlayerKillEvent(EntityDeathEvent e) {
        super(e.getEntity(), e.getDrops(), e.getDroppedExp());
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    public Player getVictim() {
        LivingEntity victim = this.getEntity();
        if (victim instanceof Player) { 
            return (Player) victim;
        } else {
            throw new IllegalArgumentException("The victim of this player kill is not a player!");
        }
    }
    
    public Player getKiller() {
        return this.getEntity().getKiller();
    }
}
