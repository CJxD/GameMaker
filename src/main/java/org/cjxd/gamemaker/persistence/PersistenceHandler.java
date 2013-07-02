package org.cjxd.gamemaker.persistence;

import java.util.List;

import org.cjxd.gamemaker.entity.Arena;
import org.cjxd.gamemaker.entity.Game;


public interface PersistenceHandler {
    /**
     * Save all games and related data to disk
     * @param games The list of active games
     */
    public void saveGames(List<Game> games);
    
    /**
     * Loads all known games from disk
     * @return List of games and related data
     */
    public List<Game> loadGames();
    
    /**
     * Save all arenas and related data to disk
     * @param games The list of active arenas
     */
    public void saveArenas(List<Arena> arenas);
    
    /**
     * Loads all known arenas from disk
     * @return List of arenas and related data
     */
    public List<Arena> loadArenas();
}
