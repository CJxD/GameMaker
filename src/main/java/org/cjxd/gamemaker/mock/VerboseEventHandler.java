package org.cjxd.gamemaker.mock;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.cjxd.gamemaker.event.GameEventHandler;
import org.cjxd.gamemaker.event.GameEndEvent;
import org.cjxd.gamemaker.event.GameStartEvent;
import org.cjxd.gamemaker.event.PlayerEnterEvent;
import org.cjxd.gamemaker.event.PlayerExitEvent;
import org.cjxd.gamemaker.event.PlayerJoinEvent;
import org.cjxd.gamemaker.event.PlayerKillEvent;
import org.cjxd.gamemaker.event.PlayerLeaveEvent;
import org.cjxd.gamemaker.event.PlayerMobKillEvent;
import org.cjxd.gamemaker.event.PlayerNPCKillEvent;


public class VerboseEventHandler implements GameEventHandler {

    @Override
    public void onPlayerJoin(PlayerJoinEvent e) {
        System.out.println(e.getPlayer().getName() + " joined.");
    }

    @Override
    public void onPlayerLeave(PlayerLeaveEvent e) {
        System.out.println(e.getPlayer().getName() + " left.");
    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent e) {
        System.out.println(e.getEntity().getName() + " died.");
    }

    @Override
    public void onPlayerKill(PlayerKillEvent e) {
        System.out.println(e.getVictim().getName() + " was killed by " + e.getKiller().getName() + ".");
    }

    @Override
    public void onPlayerNPCKill(PlayerNPCKillEvent e) {
        System.out.println("An NPC " + e.getVictim().getType().getName() + " was killed by " + e.getKiller().getName() + ".");
    }

    @Override
    public void onPlayerMobKill(PlayerMobKillEvent e) {
        System.out.println("A " + e.getVictim().getType().getName() + " was killed by " + e.getKiller().getName() + ".");
    }

    @Override
    public void onPlayerEnter(PlayerEnterEvent e) {
        System.out.println(e.getPlayer().getName() + " entered a region.");
    }

    @Override
    public void onPlayerExit(PlayerExitEvent e) {
        System.out.println(e.getPlayer().getName() + " left a region.");
    }

    @Override
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        System.out.println(e.getPlayer().getName() + " said: " + e.getMessage());
    }

    @Override
    public void onGameStart(GameStartEvent e) {
        System.out.println(e.getGame().getName() + " has started.");
    }

    @Override
    public void onGameEnd(GameEndEvent e) {
        System.out.println(e.getGame().getName() + " has ended.");
    }
    
}
