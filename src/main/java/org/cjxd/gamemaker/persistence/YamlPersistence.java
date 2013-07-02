package org.cjxd.gamemaker.persistence;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.cjxd.gamemaker.entity.Arena;
import org.cjxd.gamemaker.entity.Game;
import org.cjxd.gamemaker.entity.Region;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import org.zone.commandit.util.Message;

import com.google.common.io.Files;
import com.google.common.io.LineProcessor;


public class YamlPersistence implements PersistenceHandler {

    private Yaml yaml;
    private String base;
    
    public YamlPersistence(File dataFolder) {
        this.base = dataFolder.toString();
        
        Representer representer = new Representer();
        representer.addClassTag(Game.class, new Tag("!game"));
        representer.addClassTag(Arena.class, new Tag("!arena"));
        representer.addClassTag(Region.class, new Tag("!region"));
        
        yaml = new Yaml(representer, new DumperOptions());
    }

    /**
     * Write file to disk
     * 
     * @throws IOException
     */
    private void writeFile(String name, String data) {
        // Make sure parent directory and file are ready
        File file = new File(base + name);
        try {
            Files.createParentDirs(file);
            Files.write(data, file, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Message.severe("file_write_error", file.getName());
        }
    }
    
    /**
     * Read file from disk
     * 
     * @throws IOException
     */
    private String readFile(String name) {
        // Make sure parent directory and file are ready
        File file = new File(base + name);
        try {
            return Files.readLines(file, StandardCharsets.UTF_8, new LineProcessor<String>() {
                private StringBuilder sb = new StringBuilder();
                
                @Override
                public String getResult() {
                    return sb.toString();
                }

                @Override
                public boolean processLine(String arg0) throws IOException {
                    sb.append(arg0);
                    return true;
                }
            });
        } catch (IOException ex) {
            Message.severe("file_read_error", name);
            return null;
        }
    }

    @Override
    public void saveGames(List<Game> games) {
        String data;
        for (Game game : games) {
            data = yaml.dump(game);
            writeFile("games/" + game.getName(), data);
        }
    }

    @Override
    public List<Game> loadGames() {
        ArrayList<Game> games = new ArrayList<>();
        // Get all YAML files in the data folder
        File[] yamlFiles = new File(base + "games/").listFiles(new FileFilter() {
            @Override
            public boolean accept(File arg0) {
                String name = arg0.getName().toLowerCase();
                return FilenameUtils.getExtension(name).equals("yml");
            }
        });
        
        if (yamlFiles != null) {
            String data;
            for (File f : yamlFiles) {
                data = readFile(f.getName());
                try {
                    if (data != null) games.add((Game) yaml.load(data));
                } catch (ClassCastException ex) {
                    Message.warning("load_error", f.getName());
                }
            }
            return games;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void saveArenas(List<Arena> arenas) {
        String data;
        for (Arena arena : arenas) {
            data = yaml.dump(arenas);
            writeFile("games/" + arena.getName(), data);
        }
    }

    @Override
    public List<Arena> loadArenas() {
        ArrayList<Arena> arenas = new ArrayList<>();
        // Get all YAML files in the data folder
        File[] yamlFiles = new File(base + "arenas/").listFiles(new FileFilter() {
            @Override
            public boolean accept(File arg0) {
                String name = arg0.getName().toLowerCase();
                return FilenameUtils.getExtension(name).equals("yml");
            }
        });
        
        if (yamlFiles != null) {
            String data;
            for (File f : yamlFiles) {
                data = readFile(f.getName());
                try {
                    if (data != null) arenas.add((Arena) yaml.load(data));
                } catch (ClassCastException ex) {
                    Message.warning("load_error", f.getName());
                }
            }
            return arenas;
        } else {
            return new ArrayList<>();
        }
    }
    
}
