package org.cjxd.gamemaker.persistence;

import java.util.List;

import org.cjxd.gamemaker.entity.Game;


public interface PersistenceHandler {
    /**
     * Save all games and related data to disk
     * @param games The list of active games
     */
    public void save(List<Game> games);
    
    /**
     * Loads all known games from disk
     * @return List of games and related data
     */
    public List<Game> load();
}
