package com.cm6123.wormhole.player;

import com.cm6123.wormhole.board.BoardCreation;
import com.cm6123.wormhole.dice.Dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class PlayersHandler {
    /**
     * List of players.
     */
    private List<Player> players = new ArrayList<>();

    /**
     * Number of Faces on the dice.
     */
    private static Integer diceNumberOfFaces = 6;

    /**
     * Creates a new Dice with 'x' amount of faces.
     */
    private Dice aDice = new Dice(diceNumberOfFaces);

    /**
     * Number from the first dice.
     */
    private int diceRoll1;

    /**
     * Number from the second dice.
     */
    private int diceRoll2;

    /**
     * Scanner for User Input.
     */
    private Scanner userSC = new Scanner(System.in);

    /**
     * Calling the BoardCreation class.
     */
    private BoardCreation board;

    /**
     * Boolean to see if the player has won.
     */
    private boolean isWinner;

    /**
     * Construct a list with the players.
     */
    public PlayersHandler(){
        this.players = players;
    }

    /**
     * @param aBoard Constructor for testing purposes
     */
    public PlayersHandler(final BoardCreation aBoard){
        this.players = players;
        this.board = aBoard;
        this.isWinner = false;
    }

    /**
     * @param aPlayer Adds the player to the 'players' list.
     */
    public void addPlayerName(final Player aPlayer){
        players.add(aPlayer);
    }

    /**
     * Prints player position.
     */
    public void printPlayerPosition (){
        for (Player p : players){
            System.out.println("Player " + p.getName() + " is in position " + p.getPosition());
        }
    }

    /**
     * Full turn of each player.
     */
    public void fullTurn () {
        for (Player p : players) {
            if (!this.isWinner) {
                System.out.println(p.getName() + "'s turn");
                rollDice(p);
                checkIfPlayerIsOnWormhole(this.board, p);
                declareWinner(this.board, p);
                printPlayerPosition();
                System.out.println("");
            }
        }
    }

    /**
     * @param aPlayer Actual Player.
     * Roll the dice for the player and asks for the dice input.
     */
    public void rollDice(final Player aPlayer){
            if (aPlayer.getDiceAutomatic()){
                diceRoll1 = aDice.roll();
                diceRoll2 = aDice.roll();
                aPlayer.playerMovement(diceRoll1, diceRoll2);
            } else {
                System.out.println(aPlayer.getName() + " Please roll dice 1 and enter the result: ");
                diceRoll1 = userSC.nextInt();
                System.out.println(aPlayer.getName() + " Please roll dice 2 and enter the result: ");
                diceRoll2 = userSC.nextInt();
                aPlayer.playerMovement(diceRoll1, diceRoll2);
        }
    }

    /**
     * @param b Actual board.
     * @param p Actual Player.
     * Checks if the player has landed on a positive wormhole and moves the player to the appropriate exit,
     * Checks if the player has landed on a negative wormhole and moves the player to the appropriate exit,
     * Otherwise keeps the player where they are.
     */
    public void checkIfPlayerIsOnWormhole (final BoardCreation b, final Player p) {
            int playerPosition = p.getPosition();
            if (b.getPositiveWormholes().contains(playerPosition)){
                for (int exitPosition : b.getExits()){
                    if (exitPosition > playerPosition){
                        p.setPosition(exitPosition);
                    }
                }
            } else if (b.getNegativeWormholes().contains(playerPosition)) {
                ArrayList<Integer> tempList = new ArrayList<Integer>(b.getExits());
                Collections.reverse(tempList);
                for (int exitPosition : tempList) {
                    if (exitPosition < playerPosition) {
                        p.setPosition(exitPosition);
                    }
                }
            } else if (playerPosition >= b.getSize()){
                p.setPosition(b.getSize());
            } else {
                p.setPosition(playerPosition);
            }
    }

    /**
     * @param b Actual board.
     * @param p Actual Player.
     * Declares the winner of the game.
     */
    public void declareWinner (final BoardCreation b, final Player p) {
        if (p.getPosition() >= b.getSize()) {
            System.out.println(p.getName() + " Congratulations you have won the game!!");
            this.isWinner = true;
        }
    }

    /**
     * @return Returns true or false depending wether the player is the winner or not.
     */
    public boolean isWinner() {
        return isWinner;
    }

    /**
     * @return Returns the list "Players".
     */
    public List<Player> getPlayers() {
        return players;
    }
}
