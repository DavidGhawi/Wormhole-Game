package com.cm6123.wormhole;

import com.cm6123.wormhole.board.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void shouldCreateBoardOf5X5 () {
        BoardCreation b = new BoardCreation(5);
        assertEquals(25, b.getSize());
    }

    @Test
    public void shouldAddPositiveEntrance () {
        BoardCreation b = new BoardCreation(5);
        b.createPositiveWormhole(5, 6);
        assertTrue(b.getPositiveWormholes().contains(5));
    }

    @Test
    public void shouldAddPositiveExit () {
        BoardCreation b = new BoardCreation(5);
        b.createPositiveWormhole(5, 6);
        assertTrue(b.getExits().contains(6));
    }

    @Test
    public void shouldAddNegativeEntrance () {
        BoardCreation b = new BoardCreation(5);
        b.createNegativeWormhole(6,5);
        assertTrue(b.getNegativeWormholes().contains(6));
    }

    @Test
    public void shouldAddNegativeExit () {
        BoardCreation b = new BoardCreation(5);
        b.createNegativeWormhole(6, 5);
        assertTrue(b.getExits().contains(5));
    }

    @Test
    public void shouldCreatePositiveWormhole (){
        BoardCreation b = new BoardCreation(5);
        b.createPositive();
        assertTrue(b.getPositiveWormholes().get(0)< b.getExits().get(0));
    }

    @Test
    public void shouldCreateNegativeWormhole (){
        BoardCreation b = new BoardCreation(5);
        b.createNegative();
        assertTrue(b.getNegativeWormholes().get(0)> b.getExits().get(0));
    }

    @Test
    public void shouldCreate5Wormholes (){
        BoardCreation b = new BoardCreation(5);
        b.setNumberWormholes();
        b.createWormholes();
        assertEquals(5, b.getExits().size());
    }

    @Test
    public void shouldHaveDistinctValues(){
        BoardCreation b = new BoardCreation(5);
        b.createPositiveWormhole(5,6);
        assertTrue(b.checkWormholes(5));
    }
}