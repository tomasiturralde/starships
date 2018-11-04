package edu.austral.starship.model.components;

import java.util.List;

public class Player {
    private String id;
    private List<Key> keys;
    private int lives;
    private String name;
    private int score;
    private Spaceship spaceship;
    private float immunityFrames;
    private boolean needsAShip;

    public Player(List<Key> keys, String name, Spaceship spaceship) {
        this.keys = keys;
        this.name = name;
        this.spaceship = spaceship;
        this.lives = 3;
        this.score = 0;
        this.immunityFrames = 20000;
    }

    public void checkKey(int keyCode){
        if (lives > 0) {
            for (Key key : keys) {
                if (key.getKeyCode() == keyCode) {
                    key.execute(spaceship);
                    break;
                }
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
        needsAShip = false;
    }

    public float getImmunityFrames() {
        return immunityFrames;
    }

    public void setImmunityFrames(float immunityFrames) {
        this.immunityFrames = immunityFrames;
    }

    private boolean isImmune(){
        return immunityFrames > 0;
    }

    public boolean hasCollided(Spaceship spaceship) {
        if (!isImmune()) {
            this.lives--;
            setImmunityFrames(20000);
            needsAShip = true;
            return true;
        }
        return false;
    }

    public boolean ownShip(Spaceship spaceship){
        return this.spaceship.equals(spaceship);
    }

    public boolean needsAShip() {
        return needsAShip && lives > 0;
    }
}
