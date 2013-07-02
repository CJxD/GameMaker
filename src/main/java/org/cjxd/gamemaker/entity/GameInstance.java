package org.cjxd.gamemaker.entity;

import java.util.ArrayList;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.cjxd.gamemaker.event.GameEventHandler;
import org.cjxd.gamemaker.event.PlayerEnterEvent;
import org.cjxd.gamemaker.event.PlayerExitEvent;
import org.cjxd.gamemaker.event.PlayerJoinEvent;
import org.cjxd.gamemaker.event.PlayerKillEvent;
import org.cjxd.gamemaker.event.PlayerLeaveEvent;
import org.cjxd.gamemaker.event.PlayerMobKillEvent;
import org.cjxd.gamemaker.event.PlayerNPCKillEvent;
import org.cjxd.gamemaker.mock.VerboseEventHandler;


public class GameInstance implements Listener {
    
    private final Game game;
    private final Arena arena;
    
    private final ArrayList<Player> players;
    
    private GameEventHandler handler = new VerboseEventHandler();
    
    public GameInstance(Game game, Arena arena) {
        this.game = game;
        this.arena = arena;
        
        players = new ArrayList<>();
    }
    
    /**
     * Join a game
     * @param p The player joining
     */
    public void join(Player p) {
        players.add(p);
        handler.onPlayerJoin(new PlayerJoinEvent(p));
    }
    
    /**
     * Leave a game
     * @param p The player leaving
     */
    public void leave(Player p) {
        players.remove(p);
        handler.onPlayerLeave(new PlayerLeaveEvent(p));
    }
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (players.contains(e.getEntity())) {
            handler.onPlayerDeath(e);
        }
    }
    
    @EventHandler
    public void onKill(EntityDeathEvent e) {
        LivingEntity victim = e.getEntity();
        Player killer = victim.getKiller();
        if (players.contains(killer)) {
            switch(e.getEntityType()) {
                case PLAYER:
                    // TODO Make stronger
                    handler.onPlayerKill(new PlayerKillEvent(e));
                    break;
                case VILLAGER:
                    handler.onPlayerNPCKill(new PlayerNPCKillEvent(e));
                    break;
                case CREEPER:
                    handler.onPlayerMobKill(new PlayerMobKillEvent(e));
                    break;
                default:
                    break;
            }
        }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        for (Region r : arena.regions()) {
            if (r.contains(e.getTo()) && !r.contains(e.getFrom())) {
                PlayerEnterEvent ev = new PlayerEnterEvent(e.getPlayer(), r);
                handler.onPlayerEnter(ev);
            } else if (r.contains(e.getFrom()) && !r.contains(e.getTo())) {
                PlayerExitEvent ev = new PlayerExitEvent(e.getPlayer(), r);
                handler.onPlayerExit(ev);
            }
        }
    }
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (players.contains(e.getPlayer())) {
            handler.onPlayerChat(e);
        } else {
            if (e.getMessage().equals("join")) {
                this.players.add(e.getPlayer());
                System.out.println("Player added");
            }
        }
    }

    public Game getGame() {
        return game;
    }
    
    public Arena getArena() {
        return arena;
    }
}
