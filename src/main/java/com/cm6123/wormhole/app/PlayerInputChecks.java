package com.cm6123.wormhole.app;

import com.cm6123.wormhole.player.Player;
import com.cm6123.wormhole.player.PlayersHandler;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerInputChecks extends Application{

    /**
     * Scanner for user input.
     */
    private Scanner userInput = new Scanner(System.in);
    /**
     * Number of players.
     */
    private int numberOfPlayers = 0;
    /**
     * Player name.
     */
    private String playerName = "";

    /**
     * @return Returns the width of the board according to player input.
     * Validation checks for width input.
     */
    public int widthInput () {
        int width = 0;
        do {
            System.out.println("Enter the width of your board:");
            try {
                width = userInput.nextInt();
            } catch (Exception e) {
                userInput.reset();
                userInput.nextLine();
                continue;
            }
            if (width < 5 || width > 10) {
                System.out.println("Width of the board can only be between 5 and 10");
            }
        } while (width < 5 || width > 10);

        return width;
    }

    /**
     * @return Returns the number of player according to player input.
     * Validation checks for number of players.
     */
    public int numberOfPlayers () {
        do {
            System.out.println("Enter the number of players (2-6):");
            try {
                numberOfPlayers = userInput.nextInt();
            } catch (Exception e) {
                userInput.reset();
                userInput.nextLine();
                continue;
            }
            if (numberOfPlayers < 2 || numberOfPlayers > 6) {
                System.out.println("Number of players must be between 2 and 6");
            }
        } while (numberOfPlayers < 2 || numberOfPlayers > 6);

        return numberOfPlayers;
    }


    /**
     * @param ph PlayersHandler class.
     * @return Returns the name of the player.
     * Checks if player name is alphabetical.
     */
    public String nameOfPlayers (final PlayersHandler ph) {
        String regex = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);

        for (int i=0; i<numberOfPlayers; i++) {
            System.out.println("Enter player's name:");
            playerName = userInput.next();
            Matcher matcher = pattern.matcher(playerName);
            while (!matcher.matches()) {
                System.out.println("(Player names must be alphabetical) Enter a valid player name:");
                playerName = userInput.next();
                matcher = pattern.matcher(playerName);
                if (matcher.matches()) {
                    break;
                }
            }
            System.out.println("Do you want to roll the dice (Any character) or should I do it for you (Y)");
            String isAutomatic = userInput.next().toUpperCase();
            Boolean automaticToBoolean;
            if (isAutomatic.equals("Y")) {
                automaticToBoolean = true;
            } else {
                automaticToBoolean = false;
            }
            Player p = new Player(playerName, automaticToBoolean);
            ph.addPlayerName(p);
        }
        return playerName;
    }

    /**
     * @return Returns if the player wants to play again.
     * Checks for user input to see if user wants to play again.
     */
    public boolean playAgain () {
        System.out.println("If you would like to play again enter 'Y', otherwise enter any other character.");
        if (userInput.hasNextLine()){
            userInput.nextLine();
        }
        String playAgain = userInput.nextLine().toUpperCase();
        return (playAgain.equals("Y"));
    }
}
