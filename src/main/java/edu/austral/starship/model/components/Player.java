package edu.austral.starship.model.components;

import java.util.List;

public class Player {
    private String id;
    private List<Key> keys;
    private int lives;
    private String name;
    private int score;
    private Spaceship spaceship;
    private float inmunityFrames;

    public Player(List<Key> keys, String name, Spaceship spaceship) {
        this.keys = keys;
        this.name = name;
        this.spaceship = spaceship;
        this.lives = 3;
        this.score = 0;
        this.inmunityFrames = 0;
    }

    public void checkKey(int keyCode){
        for (Key key: keys) {
            if (key.getKeyCode() == keyCode){
                key.execute(spaceship);
                break;
            }
        }
    }

    public String getId() {
        return id;
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

    public void addScore(int score) {
        this.score += score;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public float getInmunityFrames() {
        return inmunityFrames;
    }

    public void setInmunityFrames(float inmunityFrames) {
        if (this.inmunityFrames > 0)
            this.inmunityFrames = inmunityFrames;
        else
            this.inmunityFrames = 0;
    }

    private boolean isInmune(){
        return inmunityFrames > 0;
    }

    public void checkCollision(Spaceship spaceship) {
        if (!isInmune()) {
            this.lives--;
            setInmunityFrames(50);
        }
    }

    public boolean ownShip(Spaceship spaceship){
        return this.spaceship.equals(spaceship);
    }
}
