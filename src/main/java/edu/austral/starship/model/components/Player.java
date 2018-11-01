package edu.austral.starship.model.components;

import edu.austral.starship.model.Key;

import java.util.List;

public class Player {
    private List<Key> keys;
    private int lives;
    private String name;
    private int score;
    private Spaceship spaceship;

    public Player(List<Key> keys, String name, Spaceship spaceship) {
        this.keys = keys;
        this.name = name;
        this.spaceship = spaceship;
        this.lives = 3;
        this.score = 0;
    }

    public void checkKey(int keyCode){
        for (Key key: keys) {
            if (key.getKeyCode() == keyCode){
                key.execute(spaceship);
                break;
            }
        }
    }

    public List<Key> getKeys() {
        return keys;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }
}
