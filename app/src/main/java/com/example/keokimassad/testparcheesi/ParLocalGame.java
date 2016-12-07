package com.example.keokimassad.testparcheesi;

import android.util.Log;

import java.util.Collection;
import java.util.Collections;

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

    //Arrays to locations of each players' pawn using X and Y coordinates
    private int[] player0LocationsX = new int[4];
    private int[] player1LocationsX = new int[4];
    private int[] player2LocationsX = new int[4];
    private int[] player3LocationsX = new int[4];

    private int[] player0LocationsY = new int[4];
    private int[] player1LocationsY = new int[4];
    private int[] player2LocationsY = new int[4];
    private int[] player3LocationsY = new int[4];

    private int[] player0Rect = new int[4];
    private int[] player1Rect = new int[4];
    private int[] player2Rect = new int[4];
    private int[] player3Rect = new int[4];

    //Variables to hold new X and Y coordinates for where the pawn is going to move
    private int movingLocationX;
    private int movingLocationY;
    // the rectangle in which the pawn that is looking to move is currently in
    private int movingRectangle;

    private int[] checkingLocationX = new int[4];
    private int[] checkingLocaitonY = new int[4];

    //Constructor
    public ParLocalGame () {
        super();
        parState = new ParState(); //initializes an instance of parState
    }

    //Getter to return array of current player's in the game
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
    //Method to check if a player has met the winning conditions (returns string of player if yes and NULL if not)
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
                    //Sets the substate of the game to Game_Over (4)
                    parState.setCurrentSubstage(parState.Game_Over);
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
        //Checks to see if it's the player's turn
        if (this.players[parState.getPlayerTurn()] == action.getPlayer()) {
            // Roll Action (called when either die button is pressed and the player needs to role the dice)
            if (action instanceof ParRollAction) {
                if (parState.getCurrentSubstage() == parState.Roll) {
                    // ToDo: BUG!!! can roll multiple times when one double is rolled, but anything is rolled after (called when
                    int randomDieVal1;
                    int randomDieVal2;
                    //Creates two random integers between 1 and 6 for the dice values
                    // ToDo: CHANGE BACK TO RANDOM!!!
                    randomDieVal1 = (int) (Math.random() * 6) + 1;
                    randomDieVal2 = (int) (Math.random() * 6) + 1;

                    //assigns the die values to the random integers just generated
                    parState.setDieVals(randomDieVal1, randomDieVal2);

                    //Checks to see if the two random die values are equal. If they're equal a double has been rolled
                    if (randomDieVal1 == randomDieVal2) {
                        parState.setDoublesThrown(true);
                        //Adds one to the total amount of doubles a player has rolled in the current turn
                        parState.setNumOfDoubles(parState.getNumOfDoubles() + 1);

                        //Player has rolled doubles 3 times, furthest piece on board has to move back
                        if (parState.getNumOfDoubles() >= 3) {
                            int currentPlayer = parState.getPlayerTurn();
                            int maxPawnLoc = 0;
                            int maxPawnNum = 0;
                            for (int i = 0; i < 4; i++) //loops through each pawn
                            {
                                int curPawnX = parState.getPawnLocationsXForPlayer(currentPlayer, i);
                                int curPawnY = parState.getPawnLocationsXForPlayer(currentPlayer, i);

                                outerloop:
                                for (int k = 0; k < pawnLocation.pawnLocationX.length; k++) {
                                    for (int j = 0; j < pawnLocation.pawnLocationY.length; j++) {
                                        if (pawnLocation.pawnLocationX[k] == curPawnX && pawnLocation.pawnLocationY[j] == curPawnY && k == j) {
                                            movingRectangle = k;
                                            break outerloop;
                                        }
                                    }
                                }


                                // Look at the PawnLocation class and pawnLocationX and pawnLocationY... if the pawn is in the homebase, it is
                                // indexes #116 - 131
                                //
                                if (movingRectangle <= 99) //finds the furthest pawn
                                {
                                    if (movingRectangle > maxPawnLoc) {
                                        maxPawnLoc = movingRectangle;
                                        maxPawnNum = i;
                                    }
                                }
                            }
                            parState.resetPawnLocation(currentPlayer, maxPawnNum); //resets the location of the furthest pawn

                            //Player's turn is over since they rolled to many doubles
                            //Calls setPlayerTurn() to change player's turn and reset numOfDoubles and the substage
                            parState.setPlayerTurn();
                        }
                        // Player rolled doubles, but not 3 or more times
                        else {
                            //substage is changed to Begin_Move (1)
                            parState.setCurrentSubstage(parState.Begin_Move);
                        }
                    }
                    //Player hasn't rolled a double
                    else {
                        parState.setDoublesThrown(false);
                        //substage is changed to Begin_Move (1)
                        parState.setCurrentSubstage(parState.Begin_Move);
                    }

                    // check for the legal moves for the player who is moving
                    ParCheckLegalMoveAction parCheckLegalMoveAction = new ParCheckLegalMoveAction(action.getPlayer());

                    sendAction(parCheckLegalMoveAction);
                }
                return true;
            }
            // Move Action (called when confirm move is pressed)
            else if (action instanceof ParMoveAction) {
                // ToDo: implement checking if a pawn of a different player is in the possible legal moves, therefore the pawn will be "eaten" (i.e. sent back to their respective starting location through the parState.resetPawnLocation method)

                if (parState.getCurrentSubstage() == parState.Begin_Move || parState.getCurrentSubstage() == parState.Mid_Move) {
                    int radioButtonChecked = parState.radioButtonChecked;
                    int dieSelected = parState.dieSelected;

                    for (int i = 0; i < 4; i++) {
                        player0LocationsX[i] = parState.getPawnLocationsXForPlayer(0, i);
                        player1LocationsX[i] = parState.getPawnLocationsXForPlayer(1, i);
                        player2LocationsX[i] = parState.getPawnLocationsXForPlayer(2, i);
                        player3LocationsX[i] = parState.getPawnLocationsXForPlayer(3, i);
                        player0LocationsY[i] = parState.getPawnLocationsYForPlayer(0, i);
                        player1LocationsY[i] = parState.getPawnLocationsYForPlayer(1, i);
                        player2LocationsY[i] = parState.getPawnLocationsYForPlayer(2, i);
                        player3LocationsY[i] = parState.getPawnLocationsYForPlayer(3, i);
                    }
                    // get the rectangle of all of the pawns
                    // if the rectangle is -1... then it is in the starting locations
                    for (int i = 0; i < 4; i++) {
                        player0Rect[i] = parState.getRect(player0LocationsX[i], player0LocationsY[i]);
                        player1Rect[i] = parState.getRect(player1LocationsX[i], player1LocationsY[i]);
                        player2Rect[i] = parState.getRect(player2LocationsX[i], player2LocationsY[i]);
                        player3Rect[i] = parState.getRect(player3LocationsX[i], player3LocationsY[i]);
                    }

                    String dieValue = null;

                    switch (dieSelected) {
                        case 0:
                            dieValue = "dieValue1";
                            break;
                        case 1:
                            dieValue = "dieValue2";
                            break;
                        case 2:
                            dieValue = "dieValueTotal";
                            break;
                    }

                    // ToDo: check if there are no more legal moves, change player turn (check if the legalMoves Hashmap in the ParState is empty)
                    // there is a legal move that can and will be made
                    switch (parState.getRadioButtonChecked()) {
                        case 0:
                            if (parState.legalMoves0.isEmpty()) {
                                // flash the screen
                            } else {
                                // there is a legal move for the pawn 1
                                if (parState.containsLegalMoves(dieValue, radioButtonChecked)) {
                                    // make the move
                                    int locationX = pawnLocation.pawnLocationX[parState.getLegalMoves(dieValue, radioButtonChecked)];
                                    int locationY = pawnLocation.pawnLocationY[parState.getLegalMoves(dieValue, radioButtonChecked)];
                                    parState.setPawnLocationsForPlayer(parState.getPlayerTurn(), radioButtonChecked, locationX, locationY);
                                    // remove the legal move that was made along with any other legal moves that correspond to the die that was used
                                    // change the die values to disable the die/dice that were used
                                    if (dieSelected == 0) {
                                        parState.setDieVals(-1, parState.getDice2Val());
                                    } else if (dieSelected == 1) {
                                        parState.setDieVals(parState.getDice1Val(), -1);
                                    } else {
                                        parState.setDieVals(-1, -1);
                                    }
                                    // recalculate the values of the rectangles in which the pawns are in
                                    player0Rect[radioButtonChecked] = parState.getRect(locationX, locationY);
                                    // check to see if a pawn of the other player is being eaten
                                    outerloop:
                                    for (int i = 0; i < 4; i++) {
                                        if (player0Rect[radioButtonChecked] == player1Rect[i]) {
                                            parState.resetPawnLocation(1, i);
                                            break outerloop;
                                        } else if (player0Rect[radioButtonChecked] == player2Rect[i]) {
                                            parState.resetPawnLocation(2, i);
                                            break outerloop;
                                        } else if (player0Rect[radioButtonChecked] == player3Rect[i]) {
                                            parState.resetPawnLocation(3, i);
                                            break outerloop;
                                        }
                                    }
                                } else {
                                    // flash the screen
                                }
                            }
                            break;
                        case 1:
                            if (parState.legalMoves1.isEmpty()) {
                                // flash the screen
                            } else {
                                // there is a legal move for the pawn 2
                                if (parState.containsLegalMoves(dieValue, radioButtonChecked)) {
                                    // make the move
                                    int locationX = pawnLocation.pawnLocationX[parState.getLegalMoves(dieValue, radioButtonChecked)];
                                    int locationY = pawnLocation.pawnLocationY[parState.getLegalMoves(dieValue, radioButtonChecked)];
                                    parState.setPawnLocationsForPlayer(parState.getPlayerTurn(), radioButtonChecked, locationX, locationY);
                                    // remove the legal move that was made along with any other legal moves that correspond to the die that was used
                                    // change the die values to disable the die/dice that were used
                                    if (dieSelected == 0) {
                                        parState.setDieVals(-1, parState.getDice2Val());
                                    } else if (dieSelected == 1) {
                                        parState.setDieVals(parState.getDice1Val(), -1);
                                    } else {
                                        parState.setDieVals(-1, -1);
                                    }
                                    // recalculate the values of the rectangles in which the pawns are in
                                    player1Rect[radioButtonChecked] = parState.getRect(locationX, locationY);
                                    // check to see if a pawn of the other player is being eaten
                                    outerloop:
                                    for (int i = 0; i < 4; i++) {
                                        if (player1Rect[radioButtonChecked] == player0Rect[i]) {
                                            parState.resetPawnLocation(0, i);
                                            break outerloop;
                                        } else if (player1Rect[radioButtonChecked] == player2Rect[i]) {
                                            parState.resetPawnLocation(2, i);
                                            break outerloop;
                                        } else if (player1Rect[radioButtonChecked] == player3Rect[i]) {
                                            parState.resetPawnLocation(3, i);
                                            break outerloop;
                                        }
                                    }
                                } else {
                                    // flash the screen
                                }
                            }
                            break;

                        case 2:
                            if (parState.legalMoves2.isEmpty()) {
                                // flash the screen
                            } else {
                                // there is a legal move for the pawn 3
                                if (parState.containsLegalMoves(dieValue, radioButtonChecked)) {
                                    // make the move
                                    int locationX = pawnLocation.pawnLocationX[parState.getLegalMoves(dieValue, radioButtonChecked)];
                                    int locationY = pawnLocation.pawnLocationY[parState.getLegalMoves(dieValue, radioButtonChecked)];
                                    parState.setPawnLocationsForPlayer(parState.getPlayerTurn(), radioButtonChecked, locationX, locationY);
                                    // remove the legal move that was made along with any other legal moves that correspond to the die that was used
                                    // change the die values to disable the die/dice that were used
                                    if (dieSelected == 0) {
                                        parState.setDieVals(-1, parState.getDice2Val());
                                    } else if (dieSelected == 1) {
                                        parState.setDieVals(parState.getDice1Val(), -1);
                                    } else {
                                        parState.setDieVals(-1, -1);
                                    }
                                    // recalculate the values of the rectangles in which the pawns are in
                                    player2Rect[radioButtonChecked] = parState.getRect(locationX, locationY);
                                    // check to see if a pawn of the other player is being eaten
                                    outerloop:
                                    for (int i = 0; i < 4; i++) {
                                        if (player2Rect[radioButtonChecked] == player0Rect[i]) {
                                            parState.resetPawnLocation(0, i);
                                            break outerloop;
                                        } else if (player2Rect[radioButtonChecked] == player1Rect[i]) {
                                            parState.resetPawnLocation(1, i);
                                            break outerloop;
                                        } else if (player2Rect[radioButtonChecked] == player3Rect[i]) {
                                            parState.resetPawnLocation(3, i);
                                            break outerloop;
                                        }
                                    }
                                } else {
                                    // flash the screen
                                }
                            }
                            break;
                        case 3:
                            if (parState.legalMoves3.isEmpty()) {
                                // flash the screen
                            } else {
                                // there is a legal move for the pawn 4
                                if (parState.containsLegalMoves(dieValue, radioButtonChecked)) {
                                    // make the move
                                    int locationX = pawnLocation.pawnLocationX[parState.getLegalMoves(dieValue, radioButtonChecked)];
                                    int locationY = pawnLocation.pawnLocationY[parState.getLegalMoves(dieValue, radioButtonChecked)];
                                    parState.setPawnLocationsForPlayer(parState.getPlayerTurn(), radioButtonChecked, locationX, locationY);
                                    // remove the legal move that was made along with any other legal moves that correspond to the die that was used
                                    // change the die values to disable the die/dice that were used
                                    if (dieSelected == 0) {
                                        parState.setDieVals(-1, parState.getDice2Val());
                                    } else if (dieSelected == 1) {
                                        parState.setDieVals(parState.getDice1Val(), -1);
                                    } else {
                                        parState.setDieVals(-1, -1);
                                    }
                                    // recalculate the values of the rectangles in which the pawns are in
                                    player3Rect[radioButtonChecked] = parState.getRect(locationX, locationY);
                                    // check to see if a pawn of the other player is being eaten
                                    outerloop:
                                    for (int i = 0; i < 4; i++) {
                                        if (player3Rect[radioButtonChecked] == player0Rect[i]) {
                                            parState.resetPawnLocation(0, i);
                                            break outerloop;
                                        } else if (player3Rect[radioButtonChecked] == player1Rect[i]) {
                                            parState.resetPawnLocation(1, i);
                                            break outerloop;
                                        } else if (player3Rect[radioButtonChecked] == player2Rect[i]) {
                                            parState.resetPawnLocation(2, i);
                                            break outerloop;
                                        }
                                    }
                                } else {
                                    // flash the screen
                                }
                            }
                            break;
                    }
                }

                // check for the legal moves after the move has been made and the die/dice have been used
                ParCheckLegalMoveAction parCheckLegalMoveAction = new ParCheckLegalMoveAction(action.getPlayer());

                sendAction(parCheckLegalMoveAction);

                return true;
            }
            else if (action instanceof ParCheckLegalMoveAction) {
                // ToDo: implement highlighting legal moves given the numbers on the die

                for (int i = 0; i < 4; i++) {
                    // clears the legalMoves HashMap so that new entries can be entered
                    switch (i) {
                        case 0:
                            parState.legalMoves0.clear();
                            break;
                        case 1:
                            parState.legalMoves1.clear();
                            break;
                        case 2:
                            parState.legalMoves2.clear();
                            break;
                        case 3:
                            parState.legalMoves3.clear();
                            break;
                    }

                    // set the initial x and y locations of the pawn that is selected to move
                    movingLocationX = parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), i);
                    movingLocationY = parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), i);

                    int dieValue1 = parState.getDice1Val();
                    int dieValue2 = parState.getDice2Val();
                    int dieValueTotal = dieValue1 + dieValue2;


                    outerloop:
                    for (int k = 0; k < pawnLocation.pawnLocationX.length; k++) {
                        for (int j = 0; j < pawnLocation.pawnLocationY.length; j++) {
                            if (pawnLocation.pawnLocationX[k] == movingLocationX && pawnLocation.pawnLocationY[j] == movingLocationY && k == j) {
                                movingRectangle = k;
                                break outerloop;
                            }
                        }
                    }

                    // if either of the die values = 5 or the sum up to 5
                    // and the pawn is in the homebase

                    if (parState.getPlayerTurn() == 0) {
                        if (movingRectangle == 100 + i) {
                            boolean illegalMoveStart0 = false;
                            int finalMovingRectangleStart0 = 0;
                            if (dieValue1 == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart0 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart0 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart0 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart0 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart0 == false) {
                                    parState.setLegalMoves("dieValue1", i, 0);
                                }
                            }
                            if (dieValue2 == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart0 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart0 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart0 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart0 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart0 == false) {
                                    parState.setLegalMoves("dieValue2", i, 0);
                                }
                            }
                            if (dieValue1 != -1 && dieValue2 != -1 && dieValueTotal == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart0 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart0 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart0 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart0 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart0 == false) {
                                    parState.setLegalMoves("dieValueTotal", i, 0);
                                }
                            }
                        }
                    } else if (parState.getPlayerTurn() == 1) {
                        if (movingRectangle == 104 + i) {
                            boolean illegalMoveStart1 = false;
                            int finalMovingRectangleStart1 = 51;
                            if (dieValue1 == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart1 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart1 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart1 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart1 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart1 == false) {
                                    parState.setLegalMoves("dieValue1", i, 51);
                                }
                            }
                            if (dieValue2 == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart1 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart1 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart1 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart1 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart1 == false) {
                                    parState.setLegalMoves("dieValue2", i, 51);
                                }
                            }
                            if (dieValue1 != -1 && dieValue2 != -1 && dieValueTotal == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart1 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart1 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart1 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart1 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart1 == false) {
                                    parState.setLegalMoves("dieValueTotal", i, 51);
                                }
                            }
                        }
                    } else if (parState.getPlayerTurn() == 2) {
                        if (movingRectangle == 108 + i) {
                            boolean illegalMoveStart2 = false;
                            int finalMovingRectangleStart2 = 34;
                            if (dieValue1 == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart2 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart2 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart2 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart2 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart2 == false) {
                                    parState.setLegalMoves("dieValue1", i, 34);
                                }
                            }
                            if (dieValue2 == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart2 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart2 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart2 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart2 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart2 == false) {
                                    parState.setLegalMoves("dieValue2", i, 34);
                                }
                            }
                            if (dieValue1 != -1 && dieValue2 != -1 && dieValueTotal == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart2 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart2 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart2 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart2 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart2 == false) {
                                    parState.setLegalMoves("dieValueTotal", i, 34);
                                }
                            }
                        }
                    } else {
                        if (movingRectangle == 112 + i) {
                            boolean illegalMoveStart3 = false;
                            int finalMovingRectangleStart3 = 17;
                            if (dieValue1 == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart3 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart3 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart3 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart3 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart3 == false) {
                                    parState.setLegalMoves("dieValue1", i, 17);
                                }
                            }
                            if (dieValue2 == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart3 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart3 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart3 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart3 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart3 == false) {
                                    parState.setLegalMoves("dieValue2", i, 17);
                                }
                            }
                            if (dieValue1 != -1 && dieValue2 != -1 && dieValueTotal == 5) {
                                outerloop:
                                for (int j = 0; j < i; j++) {
                                    if (finalMovingRectangleStart3 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart3 = true;
                                        break outerloop;
                                    }
                                }
                                outerloop:
                                for (int j = i + 1; j < 4; j++) {
                                    if (finalMovingRectangleStart3 == parState.getRect(parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j),parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), j))) {
                                        illegalMoveStart3 = true;
                                        break outerloop;
                                    }
                                }
                                if (illegalMoveStart3 == false) {
                                    parState.setLegalMoves("dieValueTotal", i, 17);
                                }
                            }
                        }
                    }

                    // make a normal move if the piece is on the normal board pieces
                    // check for other pawns of the same player in the possible legal move locations, therefore cannot make the move (no blockades)

                    //
                    // checking legal moves while using die 1
                    //

                    boolean illegalMove1 = false;
                    int finalMovingRectangle1 = movingRectangle + dieValue1;

                    // check if the die value was already used
                    if (dieValue1 == -1) {
                        illegalMove1 = true;
                    } else {
                        // check if the player is trying to move into their safe zone or into the homebase
                        if (parState.getPlayerTurn() == 0) {
                            // moving into safe zone
                            //
                            // if (initial location is less than 64 and the final location is greater than
                            // or equaled to 64) the pawn will go into the safe zone.
                            // Final location will be 67 + [roll - (63-initial location)]
                            //          i.e. start at 60 and roll 4... 63-60 = 3... 4 - 3 = 1...
                            //          67 + 1 = 68 (starting location of the safe zone)

                            // moving into home base
                            //
                            // if (final location is greater than 75) move is invalid...

                            if (movingRectangle < 64 && finalMovingRectangle1 >= 64) {
                                finalMovingRectangle1 = 67 + (dieValue1 - (63 - movingRectangle));
                            }

                            if (finalMovingRectangle1 > 75) {
                                illegalMove1 = true;
                            }

                        } else if (parState.getPlayerTurn() == 1) {
                            // moving into safe zone
                            //
                            // if (initial location is less than 47 and the final location is greater than
                            // or equaled to 47) the pawn will go into the safe zone.
                            // Final location will be 75 + [roll - (46-initial location)]
                            //          i.e. start at 43 and roll 4... 46-43 = 3... 4 - 3 = 1...
                            //          75 + 1 = 76 (starting location of the safe zone)

                            // moving into home base
                            //
                            // if (final location is greater than 83) move is invalid...

                            if (movingRectangle < 47 && finalMovingRectangle1 >= 47) {
                                finalMovingRectangle1 = 75 + (dieValue1 - (46 - movingRectangle));
                            }

                            if (finalMovingRectangle1 > 83) {
                                illegalMove1 = true;
                            }
                        } else if (parState.getPlayerTurn() == 2) {
                            // moving into safe zone
                            //
                            // if (initial location is less than 30 and the final location is greater than
                            // or equaled to 30) the pawn will go into the safe zone.
                            // Final location will be 83 + [roll - (29-initial location)]
                            //          i.e. start at 26 and roll 4... 29-26 = 3... 4 - 3 = 1...
                            //          83 + 1 = 84 (starting location of the safe zone)

                            // moving into home base
                            //
                            // if (final location is greater than 91) move is invalid...

                            if (movingRectangle < 30 && finalMovingRectangle1 >= 30) {
                                finalMovingRectangle1 = 83 + (dieValue1 - (29 - movingRectangle));
                            }

                            if (finalMovingRectangle1 > 91) {
                                illegalMove1 = true;
                            }
                        }
                        // player 3
                        else {
                            // moving into safe zone
                            //
                            // if (initial location is less than 13 and the final location is greater than
                            // or equaled to 13) the pawn will go into the safe zone.
                            // Final location will be 91 + [roll - (12-initial location)]
                            //          i.e. start at 9 and roll 4... 12-9 = 3... 4 - 3 = 1...
                            //          91 + 1 = 92 (starting location of the safe zone)

                            // moving into home base
                            //
                            // if (final location is greater than 99) move is invalid...

                            if (movingRectangle < 13 && finalMovingRectangle1 >= 13) {
                                finalMovingRectangle1 = 91 + (dieValue1 - (12 - movingRectangle));
                            }

                            if (finalMovingRectangle1 > 99) {
                                illegalMove1 = true;
                            }
                        }

                        outerloop:
                        for (int j = 0; j < i; j++) {
                            if (pawnLocation.pawnLocationX[finalMovingRectangle1] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMove1 = true;
                                break outerloop;
                            }
                        }
                        outerloop:
                        for (int j = i + 1; j < 4; j++) {
                            if (pawnLocation.pawnLocationX[finalMovingRectangle1] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMove1 = true;
                                break outerloop;
                            }
                        }
                    }

                    // if there are no illegal moves... then set the legal move for that die value

                    if (illegalMove1 == false) {
                        parState.setLegalMoves("dieValue1", i, finalMovingRectangle1);
                    }

                    //
                    // checking legal moves while using die 2
                    //

                    boolean illegalMove2 = false;
                    int finalMovingRectangle2 = movingRectangle + dieValue2;

                    if (dieValue2 == -1) {
                        illegalMove2 = true;
                    } else {
                        // check if the player is trying to move into their safe zone or into the homebase
                        if (parState.getPlayerTurn() == 0) {

                            if (movingRectangle < 64 && finalMovingRectangle2 >= 64) {
                                finalMovingRectangle2 = 67 + (dieValue2 - (63 - movingRectangle));
                            }

                            if (finalMovingRectangle2 > 75) {
                                illegalMove2 = true;
                            }

                        } else if (parState.getPlayerTurn() == 1) {

                            if (movingRectangle < 47 && finalMovingRectangle2 >= 47) {
                                finalMovingRectangle2 = 75 + (dieValue2 - (46 - movingRectangle));
                            }

                            if (finalMovingRectangle2 > 83) {
                                illegalMove2 = true;
                            }
                        } else if (parState.getPlayerTurn() == 2) {

                            if (movingRectangle < 30 && finalMovingRectangle2 >= 30) {
                                finalMovingRectangle2 = 83 + (dieValue2 - (29 - movingRectangle));
                            }

                            if (finalMovingRectangle2 > 91) {
                                illegalMove2 = true;
                            }
                        }
                        // player 3
                        else {

                            if (movingRectangle < 13 && finalMovingRectangle2 >= 13) {
                                finalMovingRectangle2 = 91 + (dieValue2 - (12 - movingRectangle));
                            }

                            if (finalMovingRectangle2 > 99) {
                                illegalMove2 = true;
                            }
                        }

                        outerloop:
                        for (int j = 0; j < i; j++) {
                            if (pawnLocation.pawnLocationX[finalMovingRectangle2] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMove2 = true;
                                break outerloop;
                            }
                        }
                        outerloop:
                        for (int j = i + 1; j < 4; j++) {
                            if (pawnLocation.pawnLocationX[finalMovingRectangle2] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMove2 = true;
                                break outerloop;
                            }
                        }
                    }

                    // if there are no illegal moves... then set the legal move for that die value

                    if (illegalMove2 == false) {
                        parState.setLegalMoves("dieValue2", i, finalMovingRectangle2);
                    }

                    //
                    // checking legal moves while using die 1 and 2
                    //

                    boolean illegalMoveTotal = false;
                    int finalMovingRectangleTotal = movingRectangle + dieValueTotal;

                    if (dieValue1 == -1 || dieValue2 == -1) {
                        illegalMoveTotal = true;
                    } else {
                        // check if the player is trying to move into their safe zone or into the homebase
                        if (parState.getPlayerTurn() == 0) {

                            if (movingRectangle < 64 && finalMovingRectangleTotal >= 64) {
                                finalMovingRectangleTotal = 67 + (dieValueTotal - (63 - movingRectangle));
                            }

                            if (finalMovingRectangleTotal > 75) {
                                illegalMoveTotal = true;
                            }

                        } else if (parState.getPlayerTurn() == 1) {

                            if (movingRectangle < 47 && finalMovingRectangleTotal >= 47) {
                                finalMovingRectangleTotal = 75 + (dieValueTotal - (46 - movingRectangle));
                            }

                            if (finalMovingRectangleTotal > 83) {
                                illegalMoveTotal = true;
                            }
                        } else if (parState.getPlayerTurn() == 2) {

                            if (movingRectangle < 30 && finalMovingRectangleTotal >= 30) {
                                finalMovingRectangleTotal = 83 + (dieValueTotal - (29 - movingRectangle));
                            }

                            if (finalMovingRectangleTotal > 91) {
                                illegalMoveTotal = true;
                            }
                        }
                        // player 3
                        else {

                            if (movingRectangle < 13 && finalMovingRectangleTotal >= 13) {
                                finalMovingRectangleTotal = 91 + (dieValueTotal - (12 - movingRectangle));
                            }

                            if (finalMovingRectangleTotal > 99) {
                                illegalMoveTotal = true;
                            }
                        }

                        outerloop:
                        for (int j = 0; j < i; j++) {
                            if (pawnLocation.pawnLocationX[finalMovingRectangleTotal] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMoveTotal = true;
                                break outerloop;
                            }
                        }
                        outerloop:
                        for (int j = i + 1; j < 4; j++) {
                            if (pawnLocation.pawnLocationX[finalMovingRectangleTotal] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMoveTotal = true;
                                break outerloop;
                            }
                        }
                    }

                    // if there are no illegal moves... then set the legal move for that die value

                    if (illegalMoveTotal == false) {
                        parState.setLegalMoves("dieValueTotal", i, finalMovingRectangleTotal);
                    }
                }
                if (parState.legalMoves0.isEmpty() && parState.legalMoves1.isEmpty() && parState.legalMoves2.isEmpty() && parState.legalMoves3.isEmpty()) {
                    // there are no legal moves
                    // check to see if rolled doubles or not
                    // then determine if need to change player turn
                    if (parState.getDoublesThrown()) {
                        // check to see if you rolled doubles 3
                        if (parState.getNumOfDoubles() >= 3) {
                            parState.setPlayerTurn();
                        } else {
                            // you get to roll again after rolling doubles ONLY if you have
                            // used up all of the values on the two die
                            if (parState.getDice1Val() == -1 && parState.getDice2Val() == -1) {
                                parState.setCurrentSubstage(parState.Roll);
                            }
                            // if you did not use all of the values, then you lose your turn
                            else {
                                parState.setPlayerTurn();
                            }
                        }
                    }
                    // you did not JUST throw doubles
                    else {
                        parState.setPlayerTurn();
                    }
                }
                return true;
            }
            // Select Pawn Action (called when a radio button is changed)
            else if (action instanceof ParSelectAction) {
                // set the radio button variable in the parState class after changing the radio button
                parState.setRadioButtonChecked(((ParSelectAction) action).getPawnIdx());

                // ToDo: need to implement some way of changing the highlighted legal moves when changing the radio button selected

                return true;
            }
            // Select Which Die To Use Action (called when a choose die button is selected)
            else if (action instanceof ParUseDieAction) {
                parState.setDieValueSelected(((ParUseDieAction) action).getTotalDieValue());
                parState.setDieSelected(((ParUseDieAction) action).getDieIndex());

                // ToDo: need to implement some way of changing the highlighted legal moves when changing the die value selected

                return true;
            }
        }

        return false;
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        ParState temp = new ParState(parState);
        p.sendInfo(temp);
    }

    protected void setMovingLocationX(int x) {
        movingLocationX = x;
    }

    protected void setMovingLocationY(int y) {
        movingLocationY = y;
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
