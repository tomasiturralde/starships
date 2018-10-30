package edu.austral.starship.model.components;

import java.util.List;

public class Player {
    List<Key> keys;
    int lives;
    String name;
    int score;
    Spaceship spaceship;



    class Key {
        char key;
        String function;
    }
}
