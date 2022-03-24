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
    public void shouldAddNegativeExit () {
        BoardCreation b = new BoardCreation(5);
        b.createNegativeWormhole(6, 5);
        assertTrue(b.getExits().contains(5));
    }

    @Test
    public void shouldCreate5Wormholes (){
        BoardCreation b = new BoardCreation(5);
        assertEquals(5, b.getPositiveWormholes().size() + b.getNegativeWormholes().size());
    }

    @Test
    public void shouldHaveDistinctValues(){
        BoardCreation b = new BoardCreation(5);
        b.createPositiveWormhole(5,6);
        assertTrue(b.checkWormholes(5));
    }

    @Test
    public void shouldNotGenerate1or25Negative () {
        for (int i = 0; i<=50; i++) {
            BoardCreation b = new BoardCreation(5);
            assertFalse(b.getNegativeWormholes().contains(1) || b.getNegativeWormholes().contains(25));
            assertFalse(b.getPositiveWormholes().contains(1) || b.getPositiveWormholes().contains(25));
            assertFalse(b.getExits().contains(1) || b.getExits().contains(25));
        }
    }
}
