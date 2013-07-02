package org.cjxd.gamemaker;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.cjxd.gamemaker.entity.Arena;
import org.cjxd.gamemaker.entity.Game;
import org.cjxd.gamemaker.entity.GameInstance;
import org.cjxd.gamemaker.entity.Region;
import org.cjxd.gamemaker.persistence.PersistenceHandler;
import org.cjxd.gamemaker.persistence.YamlPersistence;
import org.zone.commandit.util.Message;

public class GameMaker extends JavaPlugin {
    private List<Game> activeGames;
    private List<Arena> activeArenas;
    private PersistenceHandler ph;
    
    @Override
    public void onDisable() {
        // TODO: Place any custom disable code here.
    }

    @Override
    public void onEnable() {
        Message.init(this);
        ph = new YamlPersistence(this.getDataFolder());
        loadGames();
        
        /*
         * Test
         */
        Game game = new Game("My Game");
        Region region = new Region(this.getServer().getWorlds().get(0), -50.0, 0.0, -50.0, 50.0, 255.0, 50.0);
        Arena arena = new Arena("Testing Arena", game, region);
        GameInstance g = createGame(game, arena);
        activeGames.add(game);
        activeArenas.add(arena);
    }
    
    /**
     * Load all stored games and arenas from disk
     */
    public void loadGames() {
        if (ph != null) {
            activeGames = ph.loadGames();
            activeArenas = ph.loadArenas();
        } else {
            Message.severe("load_fail");
        }
    }
    
    /**
     * Save all games and arenas to disk
     */
    public void saveGames() {
        if (ph != null) {
            ph.saveGames(activeGames);
            ph.saveArenas(activeArenas);
        } else {
            Message.severe("save_fail");
        }
    }
    
    /**
     * Start a game and register event listeners
     * @param g The game to start
     * @param a The arena to start the game in
     * @return The instance of the game
     */
    public GameInstance createGame(Game g, Arena a) {
        GameInstance instance = g.createInstance(a);
        this.getServer().getPluginManager().registerEvents(instance, this);
        return instance;
    }
}

