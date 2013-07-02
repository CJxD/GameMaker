package org.cjxd.gamemaker.entity;

import java.util.ArrayList;


public class Arena {

    private String name;
    private Game game;
    
    private ArrayList<Region> regions;
    
    public Arena(String name, Game game, Region gameArea) {
        this.name = name;
        this.game = game;
        
        regions = new ArrayList<>();
        regions.add(gameArea);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<Region> regions() {
        return regions;
    }

}
