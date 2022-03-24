package com.cm6123.wormhole;

import com.cm6123.wormhole.board.BoardCreation;
import com.cm6123.wormhole.player.Player;
import com.cm6123.wormhole.player.PlayersHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void shouldCreateAPlayer() {
        Player p = new Player("Batman", false);
        assertEquals("Batman", p.getName());
    }

    @Test
    public void shouldMovePlayer() {
        Player p = new Player("Batman", false);
        p.playerMovement(5,4);
        assertEquals(10,p.getPosition());
    }

    @Test
    public void shouldAddAPlayer() {
        PlayersHandler ps = new PlayersHandler();
        Player Batman = new Player("Batman", false);
        ps.addPlayerName(Batman);
        assertTrue(ps.getPlayers().contains(Batman));
    }

    @Test
    public void shouldShowPlayerPositionAs1() {
        PlayersHandler ps = new PlayersHandler();
        Player Batman = new Player("Batman", false);
        ps.addPlayerName(Batman);
        assertEquals(1, Batman.getPosition());
    }

    @Test
    public void shouldRollDice() {
        PlayersHandler ps = new PlayersHandler();
        Player Batman = new Player("Batman", true);
        ps.addPlayerName(Batman);
        ps.rollDice(Batman);
        assertNotEquals(1, Batman.getPosition());
    }

    @Test
    public void shouldRollMultipleDice() {
        PlayersHandler ps = new PlayersHandler();
        Player Batman = new Player("Batman", true);
        ps.addPlayerName(Batman);
        for (int i=0; i<=10; i++) {
            int TempPosition = Batman.getPosition();
            ps.rollDice(Batman);
            assertNotEquals(TempPosition, Batman.getPosition());
        }
    }

    @Test
    public void shouldMovePlayerToPositiveExit() {
        PlayersHandler ps = new PlayersHandler();
        BoardCreation bc = new BoardCreation(5, false);
        Player Batman = new Player("Batman", true);
        ps.addPlayerName(Batman);
        bc.createPositiveWormhole(7, 15);
        Batman.setPosition(7);
        ps.checkIfPlayerIsOnWormhole(bc, Batman);
        assertEquals(15, Batman.getPosition());
    }

    @Test
    public void shouldMovePlayerToNegativeExit() {
        PlayersHandler ps = new PlayersHandler();
        BoardCreation bc = new BoardCreation(5, false);
        Player Batman = new Player("Batman", true);
        ps.addPlayerName(Batman);
        bc.createNegativeWormhole(15, 7);
        Batman.setPosition(15);
        ps.checkIfPlayerIsOnWormhole(bc, Batman);
        assertEquals(7, Batman.getPosition());
    }

    @Test
    public void shouldMovePlayerToTheClosestNegativeExit() {
        PlayersHandler ps = new PlayersHandler();
        BoardCreation bc = new BoardCreation(5, false);
        Player Batman = new Player("Batman", true);
        ps.addPlayerName(Batman);
        bc.createNegativeWormhole(15, 10);
        bc.createNegativeWormhole(16, 5);
        Batman.setPosition(15);
        ps.checkIfPlayerIsOnWormhole(bc, Batman);
        assertEquals(10, Batman.getPosition());
    }

    @Test
    public void shouldKeepPlayerInSamePositon() {
        PlayersHandler ps = new PlayersHandler();
        BoardCreation bc = new BoardCreation(5, false);
        Player Batman = new Player("Batman", true);
        ps.addPlayerName(Batman);
        bc.createNegativeWormhole(20, 7);
        bc.createPositiveWormhole(8, 15);
        Batman.setPosition(13);
        ps.checkIfPlayerIsOnWormhole(bc, Batman);
        assertEquals(13, Batman.getPosition());
    }

    @Test
    public void shouldOnlyGoUpTo25() {
        PlayersHandler ps = new PlayersHandler();
        BoardCreation bc = new BoardCreation(5, false);
        Player Batman = new Player("Batman", true);
        ps.addPlayerName(Batman);
        Batman.setPosition(50);
        ps.checkIfPlayerIsOnWormhole(bc, Batman);
        assertTrue(bc.getSize() == Batman.getPosition());
    }

    @Test
    public void shouldDeclareWinner () {
        PlayersHandler ps = new PlayersHandler();
        BoardCreation bc = new BoardCreation(5, false);
        Player Batman = new Player("Batman", true);
        ps.addPlayerName(Batman);
        Batman.setPosition(25);
        ps.declareWinner(bc, Batman);
        assertTrue(ps.isWinner());
    }

    @Test
    public void shouldRunFullGame () {
        BoardCreation bc = new BoardCreation(5, false);
        PlayersHandler ps = new PlayersHandler(bc);
        Player Batman = new Player("Batman", true);
        bc.createNegativeWormhole(20, 7);
        bc.createPositiveWormhole(8, 15);
        ps.addPlayerName(Batman);
        Batman.setPosition(1);
        ps.fullTurn();
        assertTrue(Batman.getPosition() != 1);
    }
}
