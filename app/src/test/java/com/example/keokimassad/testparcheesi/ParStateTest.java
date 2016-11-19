package com.example.keokimassad.testparcheesi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by KeokiMassad on 11/16/16.
 */
public class ParStateTest {

    @Test
    public void ParState() throws Exception {
        ParState state = new ParState();
        int length = state.players.length;
        assertEquals(length, 4);
    }

    @Test
    public void initLocations() throws Exception {
        ParState state = new ParState();
        ParPlayer player1 = state.players[1];
        state.initLocations(player1);
        for (int i = 0; i < 4; i++) {
            int loc = player1.getLocation(i);
            assertEquals(loc, i);
        }
    }

    @Test
    public void getPlayerIdx() throws Exception {
        ParState state = new ParState();
        for (int i = 0; i < 4; i++) {
            int idx = state.getPlayerIdx(state.players[i]);
            assertEquals(idx, i);
        }
    }

    @Test
    public void gameOver() throws Exception {
        ParState state = new ParState();
        state.players[1].setLocation(0, 50);
        state.players[1].setLocation(1, 50);
        state.players[1].setLocation(2, 50);
        state.players[1].setLocation(3, 50);
        assertEquals(state.gameOver(state.players[1]), true);

        state.players[1].initLocation(1);
        assertEquals(state.gameOver(state.players[1]), false);
    }

    @Test
    public void canMove() throws Exception {
        ParState state = new ParState();
        assertEquals(state.canMove(state.players[1]), false);
        assertEquals(state.canMove(state.players[0]), true);
    }

    @Test
    public void makeMove() throws Exception {
        ParState state = new ParState();
        ParPlayer player0 = state.players[0];
        ParPlayer player1 = state.players[1];

        player1.setLocation(1, 20);
        player1.setLocation(2, 20);
        player0.setLocation(1, 18);

        //blockade by player1 onto player0
        assertEquals(state.makeMove(player0, 1, 24, player1), false);

        player1.setLocation(2, 24);
        // player0 can move
        assertEquals(state.makeMove(player0, 1, 24, player1), true);
        // player0 ate player1's piece at location 24
        assertEquals(player1.getLocation(2), 2);

    }

}