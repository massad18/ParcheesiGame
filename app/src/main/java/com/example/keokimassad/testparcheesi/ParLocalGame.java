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

    //Arrays to locations of each players' pawn using X and Y coordinates
    private int[] player0LocationsX = new int[4];
    private int[] player1LocationsX = new int[4];
    private int[] player2LocationsX = new int[4];
    private int[] player3LocationsX = new int[4];

    private int[] player0LocationsY = new int[4];
    private int[] player1LocationsY = new int[4];
    private int[] player2LocationsY = new int[4];
    private int[] player3LocationsY = new int[4];

    //Variables to hold new X and Y coordinates for the pawn that is looking to move
    private int movingLocationX;
    private int movingLocationY;
    // the rectangle in which the pawn that is looking to move is currently in
    private int movingRectangle;

    private int[] checkingLocationX = new int[4];
    private int[] checkingLocaitonY = new int[4];

    //Constructor
    public ParLocalGame () {
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

                //Creates two random integers between 1 and 6 for the dice values
                int randomDieVal1 = (int) (Math.random() * 6) + 1;
                int randomDieVal2 = (int) (Math.random() * 6) + 1;
                //assigns the die values to the random integers just generated
                parState.setDieVals(randomDieVal1, randomDieVal2);

                //Checks to see if the two random die values are equal. If they're equal a double has been rolled
                if(randomDieVal1 == randomDieVal2)
                {
                    //Adds one to the total amount of doubles a player has rolled in the current turn
                    parState.setNumOfDoubles(parState.getNumOfDoubles() + 1);

                    //Player has rolled doubles more than 3 times, furthest piece on board has to move back
                    if(parState.getNumOfDoubles() > 3)
                    {
                        int currentPlayer = parState.getPlayerTurn();
                        int maxPawnLoc = 0;
                        int maxPawnNum = 0;
                        for(int i = 0; i<4; i++) //loops through each pawn
                        {
                            int curPawnX = parState.getPawnLocationsXForPlayer(currentPlayer, i);
                            int curPawnY = parState.getPawnLocationsXForPlayer(currentPlayer, i);
                            int nextPawnLoc = parState.getRect(curPawnX, curPawnY); //finds the rectangle the pawn is currently in
                            //ToDo: add an if statement to make sure the pawn isn't in the end zone
                            if(nextPawnLoc>maxPawnLoc) //finds the furthest pawn
                            {

                                maxPawnLoc = nextPawnLoc;
                                maxPawnNum = i;
                            }
                        }
                        parState.resetPawnLocation(currentPlayer,maxPawnNum); //resets the location of the furthest pawn

                        //Player's turn is over since they rolled to many doubles
                        //Calls setPlayerTurn() to change player's turn and reset numOfDoubles and the substage
                        parState.setPlayerTurn();
                    }
                }
                //Player hasn't rolled a double
                else
                {
                    //substage is changed to Begin_Move (1)
                    parState.setCurrentSubstage(parState.Begin_Move);
                }
                // ToDo: after all of the die have been used or there are no more legal moves, change player turn (guessing this will be implemented in the "instanceOf MoveAction" section)

                return true;
            }
            // ToDo: add a confirm move when the confirm move is pressed
            // Move Action (called when confirm move is pressed)
            else if (action instanceof ParMoveAction) {

                // ToDo: confirm the move upon pressing the confirm move action and the given values for parState.radioButtonChecked, parState.dieValueSelected, etc.

                // ToDo: need to disable the die value that was used after making the move so that players cannot use the same die more than once and the board does not use that die value as a legal move when highlighting the board


                // when a move is called, set the value of the die that was used to a value of -1


                return true;
            }
            else if (action instanceof ParCheckLegalMoveAction) {
                // ToDo: implement checking for legal moves and then highlighting them given the numbers on the die

                // set the initial x and y locations of the pawn that is selected to move
                movingLocationX = parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), parState.getRadioButtonChecked());
                movingLocationY = parState.getPawnLocationsYForPlayer(parState.getPlayerTurn(), parState.getRadioButtonChecked());

                int dieValue1 = parState.getDice1Val();
                int dieValue2 = parState.getDice2Val();
                int dieValueTotal = dieValue1 + dieValue2;


                outerloop:
                for (int i = 0 ; i < pawnLocation.pawnLocationX.length; i++) {
                    for (int j = 0; j < pawnLocation.pawnLocationY.length; j++) {
                        if (pawnLocation.pawnLocationX[i] == movingLocationX && pawnLocation.pawnLocationY[j] == movingLocationY && i == j) {
                            movingRectangle = i;
                            break outerloop;
                        }
                    }
                }

                // if either of the die values = 5 or the sum up to 5
                if (dieValue1 == 5) {
                    if (parState.getPlayerTurn() == 0) {
                        parState.setLegalMoves("dieValue1", 0);
                    }
                    else if (parState.getPlayerTurn() == 1) {
                        parState.setLegalMoves("dieValue1", 51);
                    }
                    else if (parState.getPlayerTurn() == 2) {
                        parState.setLegalMoves("dieValue1", 34);
                    }
                    else {
                        parState.setLegalMoves("dieValue1", 17);
                    }
                }
                else if (dieValue2 == 5) {
                    if (parState.getPlayerTurn() == 0) {
                        parState.setLegalMoves("dieValue2", 0);
                    }
                    else if (parState.getPlayerTurn() == 1) {
                        parState.setLegalMoves("dieValue2", 51);
                    }
                    else if (parState.getPlayerTurn() == 2) {
                        parState.setLegalMoves("dieValue2", 34);
                    }
                    else {
                        parState.setLegalMoves("dieValue2", 17);
                    }
                }
                else if (dieValueTotal == 5) {
                    if (parState.getPlayerTurn() == 0) {
                        parState.setLegalMoves("dieValueTotal", 0);
                    }
                    else if (parState.getPlayerTurn() == 1) {
                        parState.setLegalMoves("dieValueTotal", 51);
                    }
                    else if (parState.getPlayerTurn() == 2) {
                        parState.setLegalMoves("dieValueTotal", 34);
                    }
                    else {
                        parState.setLegalMoves("dieValueTotal", 17);
                    }
                }

                // ToDo: implement moving around the normal board pieces
                // make a normal move if the piece is on the normal board pieces
                // check for other pawns of the same player in the possible legal move locations, therefore cannot make the move (no blockades)

                // ToDo: implement checking if pawns of the same player are in the possible legal moves, therefore not a legal move
                for (int i = 0; i < parState.getRadioButtonChecked(); i++) {
                    boolean illegalMove1 = false;
                    // check if the die value was already used
                    if (dieValue1 == -1) {
                        illegalMove1 = true;
                    }
                    else {
                        outerloop:
                        for (int j = 0; j < parState.getRadioButtonChecked(); j++) {
                            if (pawnLocation.pawnLocationX[movingRectangle + dieValue1] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMove1 = true;
                                break outerloop;
                            }
                        }
                        outerloop:
                        for (int j = parState.getRadioButtonChecked() + 1; j < 4; j++) {
                            if (pawnLocation.pawnLocationX[movingRectangle + dieValue1] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMove1 = true;
                                break outerloop;
                            }
                        }
                    }
                    if (illegalMove1 == false) {
                        parState.setLegalMoves("dieValue1", movingRectangle+dieValue1);
                    }

                    boolean illegalMove2 = false;
                    if (dieValue2 == -1) {
                        illegalMove2 = true;
                    }
                    else {
                        outerloop:
                        for (int j = 0; j < parState.getRadioButtonChecked(); j++) {
                            if (pawnLocation.pawnLocationX[movingRectangle + dieValue2] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMove2 = true;
                                break outerloop;
                            }
                        }
                        outerloop:
                        for (int j = parState.getRadioButtonChecked() + 1; j < 4; j++) {
                            if (pawnLocation.pawnLocationX[movingRectangle + dieValue2] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMove2 = true;
                                break outerloop;
                            }
                        }
                    }
                    if (illegalMove2 == false) {
                        parState.setLegalMoves("dieValue2", movingRectangle+dieValue2);
                    }

                    boolean illegalMoveTotal = false;
                    if (dieValue1 == -1 || dieValue2 == -1) {
                        illegalMoveTotal = true;
                    }
                    else {
                        outerloop:
                        for (int j = 0; j < parState.getRadioButtonChecked(); j++) {
                            if (pawnLocation.pawnLocationX[movingRectangle + dieValueTotal] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMoveTotal = true;
                                break outerloop;
                            }
                        }
                        outerloop:
                        for (int j = parState.getRadioButtonChecked() + 1; j < 4; j++) {
                            if (pawnLocation.pawnLocationX[movingRectangle + dieValueTotal] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                                illegalMoveTotal = true;
                                break outerloop;
                            }
                        }
                    }
                    if (illegalMoveTotal == false) {
                        parState.setLegalMoves("dieValueTotal", movingRectangle+dieValueTotal);
                    }
                }
                for (int i = parState.getRadioButtonChecked()+1; i < 4; i++) {
                    boolean sameLocation1 = false;
                    outerloop:
                    for (int j = 0; j < parState.getRadioButtonChecked(); j ++) {
                        if (pawnLocation.pawnLocationX[movingRectangle+dieValue1] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                            sameLocation1 = true;
                            break outerloop;
                        }
                    }
                    outerloop:
                    for (int j = parState.getRadioButtonChecked()+1; j < 4; j++) {
                        if (pawnLocation.pawnLocationX[movingRectangle+dieValue1] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                            sameLocation1 = true;
                            break outerloop;
                        }
                    }
                    if (sameLocation1 == false) {
                        parState.setLegalMoves("dieValue1", movingRectangle+dieValue1);
                    }

                    boolean sameLocation2 = false;
                    outerloop:
                    for (int j = 0; j < parState.getRadioButtonChecked(); j ++) {
                        if (pawnLocation.pawnLocationX[movingRectangle+dieValue2] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                            sameLocation2 = true;
                            break outerloop;
                        }
                    }
                    outerloop:
                    for (int j = parState.getRadioButtonChecked()+1; j < 4; j++) {
                        if (pawnLocation.pawnLocationX[movingRectangle+dieValue2] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                            sameLocation2 = true;
                            break outerloop;
                        }
                    }
                    if (sameLocation2 == false) {
                        parState.setLegalMoves("dieValue2", movingRectangle+dieValue2);
                    }

                    boolean sameLocationTotal = false;
                    outerloop:
                    for (int j = 0; j < parState.getRadioButtonChecked(); j ++) {
                        if (pawnLocation.pawnLocationX[movingRectangle+dieValueTotal] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                            sameLocationTotal = true;
                            break outerloop;
                        }
                    }
                    outerloop:
                    for (int j = parState.getRadioButtonChecked()+1; j < 4; j++) {
                        if (pawnLocation.pawnLocationX[movingRectangle+dieValueTotal] == parState.getPawnLocationsXForPlayer(parState.getPlayerTurn(), j)) {
                            sameLocationTotal = true;
                            break outerloop;
                        }
                    }
                    if (sameLocationTotal == false) {
                        parState.setLegalMoves("dieValueTotal", movingRectangle+dieValueTotal);
                    }
                }

                // check if the player is trying to move into their safe zone or into the homebase
                // ToDo: implement moving into safe zone and homebase
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
                    // if (final location is EQUALED to 75) move is valid...
                }
                else if (parState.getPlayerTurn() == 1) {
                    // moving into safe zone
                    //
                    // if (initial location is less than 47 and the final location is greater than
                    // or equaled to 47) the pawn will go into the safe zone.
                    // Final location will be 75 + [roll - (46-initial location)]
                    //          i.e. start at 43 and roll 4... 46-43 = 3... 4 - 3 = 1...
                    //          75 + 1 = 76 (starting location of the safe zone)

                    // moving into home base
                    //
                    // if (final location is EQUALED to 83) move is valid...
                }
                else if (parState.getPlayerTurn() == 2) {
                    // moving into safe zone
                    //
                    // if (initial location is less than 30 and the final location is greater than
                    // or equaled to 30) the pawn will go into the safe zone.
                    // Final location will be 83 + [roll - (29-initial location)]
                    //          i.e. start at 26 and roll 4... 29-26 = 3... 4 - 3 = 1...
                    //          83 + 1 = 84 (starting location of the safe zone)

                    // moving into home base
                    //
                    // if (final location is EQUALED to 91) move is valid...
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
                    // if (final location is EQUALED to 99) move is valid...
                }
            }
            // Select Pawn Action (called when a radio button is changed)
            else if (action instanceof ParSelectAction) {
                // set the radio button variable in the parState class after changing the radio button
                parState.setRadioButtonChecked(((ParSelectAction) action).getPawnIdx());

                ParCheckLegalMoveAction parCheckLegalMoveAction = new ParCheckLegalMoveAction(action.getPlayer());

                sendAction(parCheckLegalMoveAction);

                // ToDo: need to implement some way of changing the highlighted legal moves when changing the radio button selected

                return true;
            }
            // Select Which Die To Use Action (called when a choose die button is selected)
            else if (action instanceof ParUseDieAction) {
                parState.setDieValueSelected(((ParUseDieAction) action).getTotalDieValue());

                // ToDo: need to implement some way of changing the highlighted legal moves when changing the die value selected

                ParCheckLegalMoveAction parCheckLegalMoveAction = new ParCheckLegalMoveAction(action.getPlayer());

                sendAction(parCheckLegalMoveAction);

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
