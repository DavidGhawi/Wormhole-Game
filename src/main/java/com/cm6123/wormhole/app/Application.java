package com.cm6123.wormhole.app;

import com.cm6123.wormhole.board.BoardCreation;

import com.cm6123.wormhole.player.PlayersHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    /**
     * Create a logger for the class.
     */
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    protected Application() {
    }

    /**
     * main entry point.  If args provided, result is displayed and program exists. Otherwise, user is prompted for
     * input.
     * @param args command line args.
     */
    public static void main(final String[] args) {


        logger.info("Application Started with args:{}", String.join(",", args));

        boolean playAgain = true;
        while (playAgain) {
            System.out.println("Hello World.  Welcome to Wormhole.");

            PlayerInputChecks aPlayerInputChecks = new PlayerInputChecks();
            PlayerInputChecks playerInput = (PlayerInputChecks) aPlayerInputChecks;

            int width = playerInput.widthInput();
            BoardCreation b = new BoardCreation(width);
            System.out.println("The size of your board is " + b.getSize() + " There are wormhole entrances at " + b.getPositiveWormholes()
                    + " (positive) and at" + b.getNegativeWormholes() + " (negative) and wormhole exits at " + b.getExits());

            int numberOfPlayers = playerInput.numberOfPlayers();

            PlayersHandler playerHandler = new PlayersHandler(b);
            String playerName = playerInput.nameOfPlayers(playerHandler);
            playerHandler.printPlayerPosition();
            while (!playerHandler.isWinner()) {
                playerHandler.fullTurn();
            }
            playAgain = playerInput.playAgain();
        }

        logger.info("Application closing");
    }
}
