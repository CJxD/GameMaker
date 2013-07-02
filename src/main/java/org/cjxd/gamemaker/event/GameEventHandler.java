package org.cjxd.gamemaker.event;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public interface GameEventHandler {
    /**
     * Called when a player joins the game
     */
    public void onPlayerJoin(PlayerJoinEvent e);
    
    /**
     * Called when a player quits the game
     */
    public void onPlayerLeave(PlayerLeaveEvent e);
    
    /**
     * Called when a player dies and respawns
     */
    public void onPlayerDeath(PlayerDeathEvent e);
    
    /**
     * Called when a player kills another players
     */
    public void onPlayerKill(PlayerKillEvent e);
    
    /**
     * Called when a player kills an NPC
     */
    public void onPlayerNPCKill(PlayerNPCKillEvent e);
    
    /**
     * Called when a player kills a mob
     */
    public void onPlayerMobKill(PlayerMobKillEvent e);
    
    /**
     * Called when a player enters a defined region
     */
    public void onPlayerEnter(PlayerEnterEvent e);
    
    /**
     * Called when a player exits a defined region
     */
    public void onPlayerExit(PlayerExitEvent e);
    
    /**
     * Called when a player sends a chat message
     */
    public void onPlayerChat(AsyncPlayerChatEvent e);
    
    /**
     * Called before a game starts
     */
    public void onGameStart(GameStartEvent e);
    
    /**
     * Called after a game ends
     */
    public void onGameEnd(GameEndEvent e);
}
