package edu.austral.starship.model.components;

public class PlayerObserver {
    private Player owner;

    public PlayerObserver(Player owner) {
        this.owner = owner;
    }

    public void updateScore(int score) {
        owner.addScore(score);
    }

    public Player getOwner() {
        return owner;
    }
}
