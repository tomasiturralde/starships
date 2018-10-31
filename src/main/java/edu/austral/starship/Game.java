package edu.austral.starship;

import edu.austral.starship.model.components.Component;
import edu.austral.starship.model.components.Player;

import java.util.List;

public class Game {
    private List<Component> components;
    private List<Player> players;
    private float timer;
    private Map map;

    public Game(List<Component> components, List<Player> players, float timer, Map map) {
        this.components = components;
        this.players = players;
        this.timer = timer;
        this.map = map;
    }

    public List<Component> getComponents() {
        return components;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public float getTimer() {
        return timer;
    }

    public Map getMap() {
        return map;
    }
}
