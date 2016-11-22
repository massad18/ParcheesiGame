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
        parState = new ParState(); //initializes an instance of parState
    }

    public GamePlayer[] getPlayerArray()
    {
        return players;
    }

    @Override
    //Method that returns true or false based on if it's the player's turn
    protected boolean canMove(int playerIdx) {
        if (parState.getPlayerTurn() == playerIdx) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String checkIfGameOver() {
        //Checks every player for winning condition
        for(int playerIdx = 0; playerIdx < players.length; playerIdx++)
        {
            //holds number of pawns at center for given player
            int totalPawnsAtGoalForPlayer = 0;
            //integer array to hold locations of pawns for given player
            int pawnLocations[] = parState.getPawnLocationsForPlayer(playerIdx);

            //Checks every pawn that player has
            for(int pawnIdx = 0; pawnIdx < 4; pawnIdx++)
            {
                //Checks if given pawn has reached the end goal
                //ToDo: Change value to reflect true indexes for winning (91 temp value)
                if(pawnLocations[0] == 91)
                {
                    totalPawnsAtGoalForPlayer++;
                }
            }
            //Checks if player has 4 pawns at goal
            if(totalPawnsAtGoalForPlayer == 4)
            {
                //If so, the player has won the game
                return "Player " + playerIdx + " has won!";
            }
        }
        return null; //returns null if no player has 4 pawns at goal
    }

    @Override
    protected boolean makeMove(GameAction action) {
        /*Instance Variables*/
        //integer arrays to hold each player's current pawn locations
        int[] player1LocationsX = parState.getPawnLocationsXForPlayer(0);
        int[] player2LocationsX = parState.getPawnLocationsXForPlayer(1);
        int[] player3LocationsX = parState.getPawnLocationsXForPlayer(2);
        int[] player4LocationsX = parState.getPawnLocationsXForPlayer(3);

        int[] player1LocationsY = parState.getPawnLocationsYForPlayer(0);
        int[] player2LocationsY = parState.getPawnLocationsYForPlayer(1);
        int[] player3LocationsY = parState.getPawnLocationsYForPlayer(2);
        int[] player4LocationsY = parState.getPawnLocationsYForPlayer(3);

        int locPlayer1Init;
        int locPlayer2Init;
        ParPlayer currentPlayer;
        ParPlayer targetPlayer;
        myAction = action;

        //Loops through each player in the game
        for (int m = 0; m < players.length; m ++) {
            //Checks if it's the player's turn
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
