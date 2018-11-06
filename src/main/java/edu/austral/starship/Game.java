package edu.austral.starship;

import edu.austral.starship.model.components.Component;
import edu.austral.starship.model.components.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Component> components;
    private List<Player> players;
    private float timer;

    Game() {
        components = new ArrayList<>();
        players = new ArrayList<>();
        timer = 105f;
    }

    public List<Component> getComponents() {
        return components;
    }

    List<Player> getPlayers() {
        return players;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }
}
