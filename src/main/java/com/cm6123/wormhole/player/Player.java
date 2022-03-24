package com.cm6123.wormhole.player;

public class Player {
    /**
     * Name of the player as a String.
     */
    private String name;
    /**
     * Position of the player as an int.
     */
    private int position;

    /**
     * True/False depending on wether the player wants to roll the dice or not.
     */
    private Boolean isDiceAutomatic;

    /**
     * @param aName Name of the player.
     * @param isADiceAutomatic Will the player roll the dice or will it be generated automatically.
     */
    public Player (final String aName, final Boolean isADiceAutomatic) {
        name = aName;
        position = 1;
        isDiceAutomatic = isADiceAutomatic;
    }
    /**
     * @param dice1 Value of the first die.
     * @param dice2 Value of the second die.
     */
    public void playerMovement(final int dice1, final int dice2) {
        position += dice1 + dice2;
    }

    /**
     * @return Returns the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the position of the player.
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param aPosition Returns the position of the player.
     */
    public void setPosition(final int aPosition) {
        position = aPosition;
    }

    /**
     * @return Returns 'true/false' according to the automation of the dice.
     */
    public Boolean getDiceAutomatic() {
        return isDiceAutomatic;
    }
}
