package com.example.keokimassad.testparcheesi;

import game.GamePlayer;
import game.LocalGame;
import game.actionMsg.GameAction;

/**
 * Created by KeokiMassad on 11/20/16.
 * Thanks Keoki!
 */

public class ParLocalGame extends LocalGame {

    ParState parState;
    //GameAction myAction;

    PawnLocation pawnLocation = new PawnLocation();

    private int[] player0LocationsX = new int[4];
    private int[] player1LocationsX = new int[4];
    private int[] player2LocationsX = new int[4];
    private int[] player3LocationsX = new int[4];

    private int[] player0LocationsY = new int[4];
    private int[] player1LocationsY = new int[4];
    private int[] player2LocationsY = new int[4];
    private int[] player3LocationsY = new int[4];

    private int movingLocationX;
    private int movingLocationY;

    private int[] checkingLocationX = new int[4];
    private int[] checkingLocaitonY = new int[4];

    public ParLocalGame () {
        parState = new ParState(); //initializes an instance of parState
    }

    public GamePlayer[] getPlayerArray()
    {
        return players;
    }

    @Override
    //Method that returns true or false based on if it's the player's turn
    // Calls before makeMove method
    protected boolean canMove(int playerIdx) {
        if (parState.getPlayerTurn() == playerIdx) {
            return true;
        }
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        //Checks every player for winning condition
        for (int playerIdx = 0; playerIdx < players.length; playerIdx++) {
            //holds number of pawns at center for given player
            int totalPawnsAtGoalForPlayer = 0;
            //integer array to hold locations of pawns for given player
            int[] pawnXLocations = new int[4];
            for (int i = 0; i < 4; i++) {
                pawnXLocations[i] = parState.getPawnLocationsXForPlayer(playerIdx, i);
            }
            int[] pawnYLocations = new int[4];
            for (int i = 0; i < 4; i++) {
                pawnYLocations[i] = parState.getPawnLocationsYForPlayer(playerIdx, i);
            }

            //Checks every pawn that player has
            for (int pawnIdx = 0; pawnIdx < 4; pawnIdx++) {
                switch (playerIdx) {
                    // red player
                    case 0:
                        if (pawnXLocations[pawnIdx] == pawnLocation.pawnLocationX[116+pawnIdx] & pawnYLocations[pawnIdx] == pawnLocation.pawnLocationX[116+pawnIdx]) {
                            totalPawnsAtGoalForPlayer++;
                        }
                        break;
                    // blue player
                    case 1:
                        if (pawnXLocations[pawnIdx] == pawnLocation.pawnLocationX[120+pawnIdx] & pawnYLocations[pawnIdx] == pawnLocation.pawnLocationX[120+pawnIdx]) {
                            totalPawnsAtGoalForPlayer++;
                        }
                        break;
                    // yellow player
                    case 2:
                        if (pawnXLocations[pawnIdx] == pawnLocation.pawnLocationX[124+pawnIdx] & pawnYLocations[pawnIdx] == pawnLocation.pawnLocationX[124+pawnIdx]) {
                            totalPawnsAtGoalForPlayer++;
                        }
                        break;
                    // green player
                    case 3:
                        if (pawnXLocations[pawnIdx] == pawnLocation.pawnLocationX[128+pawnIdx] & pawnYLocations[pawnIdx] == pawnLocation.pawnLocationX[128+pawnIdx]) {
                            totalPawnsAtGoalForPlayer++;
                        }
                        break;
                }
                //Checks if player has 4 pawns at goal
                if (totalPawnsAtGoalForPlayer == 4) {
                    //If so, the player has won the game
                    return "Player " + playerIdx + " has won!";
                }
            }
            return null; //returns null if no player has 4 pawns at goal
        }
        return null;
    }

    // canMove method is called before the makeMove method
    @Override
    protected boolean makeMove(GameAction action) {
        /*Justin trying random stuff here */
        System.out.println("Entering makeMove");
        if (this.players[parState.getPlayerTurn()] == action.getPlayer()) {
            System.out.println("player turn is true");
            // Roll Action
            if (action instanceof ParRollAction) {
                System.out.println("roll Action called");
                int randomDieVal1 = (int) (Math.random() * 6) + 1;
                int randomDieVal2 = (int) (Math.random() * 6) + 1;
                parState.setDieVals(randomDieVal1, randomDieVal2);
                System.out.println("die1 Val: " + parState.getDice1Val());
                System.out.println("die2 Val: " + parState.getDice2Val());
                return true;
            }
            // Move Action
            else if (action instanceof ParMoveAction) {

                // set the initial x and y locations of the pawn that is selected to move
                movingLocationX = parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), parState.getRadioButtonChecked());
                movingLocationY = parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), parState.getRadioButtonChecked());
                return true;
            }
            // Select Pawn Action
            else if (action instanceof ParSelectAction) {
                parState.setRadioButtonChecked(((ParSelectAction) action).getPawnIdx());
                return true;
            }
        }
        //return false;


        /*Instance Variables*/
        //integer arrays to hold each player's current pawn locations (x and y coordinates)
        for (int i = 0; i < 4; i++) {
            player0LocationsX[i] = parState.getPawnLocationsXForPlayer(0,i);
            player1LocationsX[i] = parState.getPawnLocationsXForPlayer(1,i);
            player2LocationsX[i] = parState.getPawnLocationsXForPlayer(2,i);
            player3LocationsX[i] = parState.getPawnLocationsXForPlayer(3,i);
            player0LocationsY[i] = parState.getPawnLocationsYForPlayer(0,i);
            player1LocationsY[i] = parState.getPawnLocationsYForPlayer(1,i);
            player2LocationsY[i] = parState.getPawnLocationsYForPlayer(2,i);
            player3LocationsY[i] = parState.getPawnLocationsYForPlayer(3,i);
        }

        int locPlayer1InitX;
        int locPlayer1InitY;
        int locPlayer2InitX;
        int locPlayer2InitY;


        /*

        //Loops through each player in the game
        for (int m = 0; m < players.length; m++) {
            //Checks if it's the player's turn
            if (canMove(m)) {
                if (myAction instanceof ParMoveAction) {
                    // ToDo:need a method to get the selected pawn piece to move
                    int pawn = ...something.getSelectedPiece();
                    // ToDo:need a method within the par human player to get the location of the selected pawn piece
                    locPlayer1InitX = parState.getPawnLocationsXForPlayer(m)[pawn];
                    locPlayer1InitY = parState.getPawnLocationsYForPlayer(m)[pawn];

                        for (int k = 0; k < m; k++) {
                            // target players before m
                            outerloop:
                            for (int i = 0; i < 4; i++) {
                                // target pawns
                                locPlayer2InitX = parState.getPawnLocationsXForPlayer(k)[i];
                                locPlayer2InitY = parState.getPawnLocationsYForPlayer(k)[i];

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
                    for (int k = m+1; k < players.length; k++) {
                        // target players after m
                        for (int i = 0; i < 4; i++) {

                        }
                    }
                }
                if (myAction instanceof ParRollAction) {

                }
                return true;
            }
        }*/
        return false;
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        ParState temp = new ParState(parState);
        p.sendInfo(temp);
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
