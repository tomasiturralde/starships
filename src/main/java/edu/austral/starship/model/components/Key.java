package edu.austral.starship.model.components;


import edu.austral.starship.model.components.commands.Command;

public class Key {
    private int keyCode;
    private Command command;

    public Key(int keyCode, Command command) {
        this.keyCode = keyCode;
        this.command = command;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void execute(Spaceship spaceship){
        command.execute(spaceship);
    }
}
