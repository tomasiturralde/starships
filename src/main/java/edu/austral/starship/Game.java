package edu.austral.starship;

import edu.austral.starship.model.components.Component;
import edu.austral.starship.model.components.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Component> components;
    private List<Player> players;
    private float timer;

    public Game() {
        components = new ArrayList<>();
        players = new ArrayList<>();
        timer = 105f;
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

    public void addScore(int score, String playerId) {
        for (Player player : players){
            if (player.getId().equals(playerId))
                player.addScore(score);
        }
    }

    public void destroyComponent(Component component) {
        components.remove(component);
    }
}
