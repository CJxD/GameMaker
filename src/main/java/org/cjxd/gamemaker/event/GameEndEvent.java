package org.cjxd.gamemaker.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.cjxd.gamemaker.entity.Game;

public class GameEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Game game;
    
    public GameEndEvent(Game game) {
        super();
        this.setGame(game);
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
}
