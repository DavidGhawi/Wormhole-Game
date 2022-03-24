package com.cm6123.wormhole.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardCreation {
    /**
     * Width of the board.
     */
    private int width;
    /**
     * Size of the board.
     */
    private int size;
    /**
     * List for number of exits.
     */
    private List<Integer> exits = new ArrayList<>();
    /**
     * List for number of positiveWormholes.
     */
    private List<Integer> positiveWormholes = new ArrayList<>();
    /**
     * List for number of negativeWormholes.
     */
    private List<Integer> negativeWormholes = new ArrayList<>();
    /**
     * Instance of the class Random.
     */
    private Random random = new Random();
    /**
     * Number of Negative Wormholes as an Integer.
     */
    private Integer numberOfNegativeEntrances;
    /**
     * Number of Positive Wormholes as an Integer.
     */
    private Integer numberOfPositiveEntrances;

    /**
     * @param aWidth Width of the board
     */
    public BoardCreation(final int aWidth) {
        width = aWidth;
        size = width * width;
        setNumberWormholes();
        createWormholes();
    }

    /**
     * @param aWidth Width of the board.
     * @param createWormholes Create the wormholes.
     * Constructor for testing purposes.
     */
    public BoardCreation(final int aWidth, final boolean createWormholes) {
        width = aWidth;
        size = width * width;
        if (createWormholes){
            setNumberWormholes();
            createWormholes();
        }
    }

    /**
     * @param positiveWormhole Adds the positive Wormhole to the ArrayList "positiveWormholes".
     * @param exit Adds an exit to the ArrayList "exits".
     */
    public void createPositiveWormhole(final int positiveWormhole, final int exit) {
        positiveWormholes.add(positiveWormhole);
        exits.add(exit);
    }

    /**
     * @param negativeWormhole Adds the negative Wormhole to the ArrayList "negativeWormhole".
     * @param exit Adds an exit to the ArrayList "exits".
     */
    public void createNegativeWormhole(final int negativeWormhole, final int exit) {
        negativeWormholes.add(negativeWormhole);
        exits.add(exit);
    }

    /**
     * Setting the number of Wormholes according to the width of the board.
     */
    public void setNumberWormholes (){
        numberOfNegativeEntrances = random.nextInt(width -1)+1;
        numberOfPositiveEntrances = width - numberOfNegativeEntrances;
    }

    /**
     * Randomly generates a number for a positive Wormhole.
     */
    public void createPositive (){
        int entrance = random.nextInt(size -2) +2;
        while (checkWormholes(entrance)){
            entrance = random.nextInt(size -2) +2;
            break;
        }
        int exit = random.nextInt(size -2) +2;
        while(entrance >= exit || checkWormholes(exit)){
            exit = random.nextInt(size -2) +2;
            break;
        }
        createPositiveWormhole(entrance, exit);
    }

    /**
     * Randomly generates a number for a negative Wormhole.
     */
    public void createNegative (){
        int entrance = random.nextInt(size -2) +2;
        while (checkWormholes(entrance)){
            entrance = random.nextInt(size -2) +2;
            break;
        }
        int exit = random.nextInt(size -2) +2;
        while(entrance <= exit || checkWormholes(exit)){
            exit = random.nextInt(size -2) +2;
            break;
        }
        createNegativeWormhole(entrance, exit);
    }

    /**
     * For-loops that create the Wormholes according to the number of Positive/Negative Wormholes,
     * calls the methods that generate the number randomly "createPositive()" & "createNegative()".
     */
    public void createWormholes (){
        for (int i=0; i<numberOfPositiveEntrances; i++){
            createPositive();
        }
        for (int i=0; i<numberOfNegativeEntrances; i++){
            createNegative();
        }
    }

    /**
     * @param value Actual number generated on the board.
     * @return Returns 'true' if they are no duplicate numbers, and 'false' if there is.
     */
    public boolean checkWormholes (final int value) {
        if(positiveWormholes.contains(value) || negativeWormholes.contains(value) || exits.contains(value)){
            return true;
        }
        return false;
    }

    /**
     * @return Returns the size of the board i.e. width*width.
     */
    public int getSize() {
        return size;
    }

    /**
     * @return Returns the positiveWormholes on the board.
     */
    public List<Integer> getPositiveWormholes() {
        return positiveWormholes;
    }

    /**
     * @return Returns the negativeWormholes on the board.
     */
    public List<Integer> getNegativeWormholes() {
        return negativeWormholes;
    }

    /**
     * @return Returns the exits on the board.
     */
    public List<Integer> getExits() {
        return exits;
    }
}
