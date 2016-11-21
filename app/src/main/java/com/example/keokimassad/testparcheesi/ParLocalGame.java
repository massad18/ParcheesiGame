package com.example.keokimassad.testparcheesi;

import game.GamePlayer;
import game.LocalGame;
import game.actionMsg.GameAction;

/**
 * Created by KeokiMassad on 11/20/16.
 */

public class ParLocalGame extends LocalGame {

    ParState parState;
    GameAction myAction;

    public ParLocalGame () {
        parState = new ParState();
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if (parState.getPlayerTurn() == playerIdx) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {

        int locPlayer1Init;
        int locPlayer2Init;
        ParPlayer currentPlayer;
        ParPlayer targetPlayer;
        myAction = action;

        for (int m = 0; m < players.length; m ++) {
            if (canMove(m)) {
                // need an array of the players
                currentPlayer = players[m];
                if (myAction instanceof ParMoveAction) {
                    // need a method to get the selected pawn piece to move
                    int pawn = ...something.getSelectedPiece();
                    // need a method within the par human player to get the location of the selected pawn piece
                    locPlayer1Init = currentPlayer.getLocationPlayer(pawn);

                    if (currentPlayer.)

                    for (int k = 0; k < m; k++) {
                        targetPlayer = players[k];

                        outerloop:
                        for (int i = 0; i < 4; i++) {
                            locPlayer2Init = targetPlayer.getLocationPlayer(i);

                            // determine if there is a block ahead
                            for (int j = 0; j < i; j++) {
                                if (locPlayer2Init == targetPlayer.getLocation(j)) {
                                    // is the block between the initial position and the final position??
                                    if (locPlayer1Init < locPlayer2Init && locPlayer2Init < locPlayer1Final || locPlayer1Final == locPlayer2Init) {
                                        break outerloop;
                                    }
                                }
                            }
                            for (int j = i + 1; j < 4; j++) {
                                if (locPlayer2Init == targetPlayer.getLocation(j)) {
                                    // is the block between the initial position and the final position??
                                    if (locPlayer1Init < locPlayer2Init && locPlayer2Init < locPlayer1Final || locPlayer1Final == locPlayer2Init) {
                                        break outerloop;
                                    }
                                }
                            }
                            // is the final location of the piece on a single opposing piece??
                            if (locPlayer1Final == locPlayer2Init) {
                                player1.setLocation(numPlayer1, locPlayer1Final);
                                player2.initLocation(i);
                                return true;
                            }
                        }
                    }
                }
                if (myAction instanceof ParRollAction) {

                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

    }


    // possible usages for methods above
//    protected void initLocations(ParPlayer player) {
//        player.initLocation(0);
//        player.initLocation(1);
//        player.initLocation(2);
//        player.initLocation(3);
//    }

//    protected int getPlayerIdx (ParPlayer player) {
//        for (int i = 0; i < 4; i++) {
//            if (players[i] == player) {
//                return i;
//            }
//        }
//        return -1;
//    }

//    protected boolean gameOver(ParPlayer player) {
//        if (player.getLocation(0) == 50 & player.getLocation(1) == 50 & player.getLocation(2) == 50 & player.getLocation(3) == 50) {
//            return true;
//        }
//        return false;
//    }

//    protected boolean canMove (ParPlayer player) {
//        int idx = getPlayerIdx(player);
//        if (idx == count) {
//            return true;
//        }
//        return false;
//    }

//    protected boolean makeMove (ParPlayer player1, int numPlayer1, int locPlayer1Final, ParPlayer player2) {
//        int locPlayer1Init = player1.getLocation(numPlayer1);
//        int locPlayer2Init;
//
//        outerloop:
//        for (int i = 0; i < 4; i++) {
//            locPlayer2Init = player2.getLocation(i);
//
//            // determine if there is a block ahead
//            for (int j = 0; j < i; j++) {
//                if (locPlayer2Init == player2. getLocation(j)) {
//                    // is the block between the initial position and the final position??
//                    if (locPlayer1Init < locPlayer2Init && locPlayer2Init < locPlayer1Final || locPlayer1Final == locPlayer2Init) {
//                        break outerloop;
//                    }
//                }
//            }
//            for (int j = i+1; j < 4; j++) {
//                if (locPlayer2Init == player2. getLocation(j)) {
//                    // is the block between the initial position and the final position??
//                    if (locPlayer1Init < locPlayer2Init && locPlayer2Init < locPlayer1Final || locPlayer1Final == locPlayer2Init) {
//                        break outerloop;
//                    }
//                }
//            }
//            // is the final location of the piece on a single opposing piece??
//            if (locPlayer1Final == locPlayer2Init) {
//                player1.setLocation(numPlayer1, locPlayer1Final);
//                player2.initLocation(i);
//                return true;
//            }
//        }
//        return false;
//    }

}
