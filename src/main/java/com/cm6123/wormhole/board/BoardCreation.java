package com.cm6123.wormhole.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardCreation {

    private int width;
    private int size;

    private List<Integer> board = new ArrayList<>();
    private List<Integer> exits = new ArrayList<>();
    private List<Integer> positiveWormholes = new ArrayList<>();
    private List<Integer> negativeWormholes = new ArrayList<>();

    private Random random = new Random();
    private Integer numberOfNegativeEntrances;
    private Integer numberOfPositiveEntrances;

    public BoardCreation(int w) {
        width = w;
        size = width*width;
    }

    public void createPositiveWormhole(int positiveWormhole, int exit) {
        positiveWormholes.add(positiveWormhole);
        exits.add(exit);
    }

    public void createNegativeWormhole(int negativeWormhole, int exit) {
        negativeWormholes.add(negativeWormhole);
        exits.add(exit);
    }

    public void setNumberWormholes (){
        numberOfNegativeEntrances = random.nextInt(width)+1;
        numberOfPositiveEntrances = width - numberOfNegativeEntrances;
    }

    public void createPositive (){
        int entrance = random.nextInt(size) +1;
        while (checkWormholes(entrance)){
            entrance = random.nextInt(size) +1;
        }
        int exit = random.nextInt(size) +1;
        while(entrance >= exit || checkWormholes(exit)){
            exit = random.nextInt(size) +1;
        }
        createPositiveWormhole(entrance, exit);
    }

    public void createNegative (){
        int entrance = random.nextInt(size) +1;
        while (checkWormholes(entrance)){
            entrance = random.nextInt(size) +1;
        }
        int exit = random.nextInt(size) +1;
        while(entrance <= exit || checkWormholes(exit)){
            exit = random.nextInt(size) +1;
        }
        createNegativeWormhole(entrance, exit);
    }

    public void createWormholes (){
        for (int i=0; i<numberOfPositiveEntrances; i++){
            createPositive();
        }
        for (int i=0; i<numberOfNegativeEntrances; i++){
            createNegative();
        }
    }

    public boolean checkWormholes (int value) {
        if(positiveWormholes.contains(value) || negativeWormholes.contains(value) || exits.contains(value)){
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public List<Integer> getPositiveWormholes() {
        return positiveWormholes;
    }

    public List<Integer> getNegativeWormholes() {
        return negativeWormholes;
    }

    public List<Integer> getExits() {
        return exits;
    }
}
