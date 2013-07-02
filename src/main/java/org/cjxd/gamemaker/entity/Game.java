package org.cjxd.gamemaker.entity;

import java.util.ArrayList;


public class Game {
    
    private String name;
    
    private ArrayList<String> regionNames;
    
    public Game(String name) {
        this.name = name;
        this.regionNames = new ArrayList<>();
    }

    public GameInstance createInstance(Arena arena) {
        return new GameInstance(this, arena);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getRegions() {
        return regionNames;
    }
 
}
