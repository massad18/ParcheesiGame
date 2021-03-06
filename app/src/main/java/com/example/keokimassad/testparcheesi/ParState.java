package com.example.keokimassad.testparcheesi;

import java.util.Hashtable;

import game.Game;
import game.infoMsg.GameState;

/**
 * Created by KeokiMassad on 11/15/16.
 */

public class ParState extends GameState {

    private static final long serialVersionUID = 2387235129834730232L;

    //Defined integer constants for different substages of game
    protected static int Roll = 0;
    protected static int Begin_Move = 1;
    protected static int Mid_Move = 2;
    protected static int Game_Over = 3;

    private int currentSubstage; //integer to hold current substage of the game
    private int [] dieVals = new int[2]; //array to hold current dice values
    private int numOfDoubles; //holds number of doubles current player has had in the turn
    private int playerTurn; //holds who's turn it currently is

    // initialize the radio button that is checked
    int radioButtonChecked = -1;
    // initialize the die value button selected
    int dieValueSelected = -1;
    // initialize the die that was selected
    int dieSelected = -1;

    // initialize the pawnActionMade
    boolean pawnActionMade = false;
    // initialize the useDieActionMade
    boolean useDieActionMade = false;
    // initialize the checkLegalMoveActionMade
    boolean checkLegalMoveActionMade = true;

    // initialize the boolean to check if the last throw was a double
    boolean doublesThrown = false;

    // initialize the legal move array
    Hashtable<String, Integer> legalMoves0 = new Hashtable<>();
    Hashtable<String, Integer> legalMoves1 = new Hashtable<>();
    Hashtable<String, Integer> legalMoves2 = new Hashtable<>();
    Hashtable<String, Integer> legalMoves3 = new Hashtable<>();

    //4 location arrays to hold locations of each players' pawns
    private int[] player0LocationsX = new int[4];
    private int[] player1LocationsX = new int[4];
    private int[] player2LocationsX = new int[4];
    private int[] player3LocationsX = new int[4];

    private int[] player0LocationsY = new int[4];
    private int[] player1LocationsY = new int[4];
    private int[] player2LocationsY = new int[4];
    private int[] player3LocationsY = new int[4];

    // up to down coordinates for the edges of the board pieces
    private int[] hori = {0, 81, 150, 217, 284, 358, 424, 491, 558, 626, 694, 763, 833, 898, 967, 1036, 1102, 1165, 1235, 1300, 1366};
    // left to right coordinates for the edges of the board pieces
    private int[] vert = {0, 82, 144, 205, 271, 337, 398, 464, 519, 578, 637, 696, 761, 820, 877, 939, 1003, 1066, 1133, 1191, 1261};

    private Rect[] board = new Rect[100];

    private int selectedLocation;

    private PawnLocation pawnLocation = new PawnLocation();

    //Constructor
    public ParState() {
        super();
        initBoardPieces();
        for (int i = 0; i < 4 /*number of players*/; i++) {
            initPawnLocation(i);
        }
    }

    // creates a template ParState that will be sent to the other players within the game
    public ParState(ParState p) {
        Roll = 0;
        Begin_Move = 1;
        Mid_Move = 2;
        Game_Over = 3;
        radioButtonChecked = p.getRadioButtonChecked();
        dieValueSelected = p.getDieValueSelected();
        dieSelected = p.getDieSelected();
        currentSubstage = p.getCurrentSubstage();
        pawnActionMade = p.getPawnActionMade();
        useDieActionMade = p.getUseDieActionMade();
        checkLegalMoveActionMade = p.getCheckLegalMoveActionMade();
        for (int i = 0; i < 2; i++) {
            dieVals[i] = p.getDiceVals(i);
        }
        numOfDoubles = p.getNumOfDoubles();
        doublesThrown = p.getDoublesThrown();
        playerTurn = p.getPlayerTurn();
        for (int i = 0; i < 4; i++) {
            player0LocationsX[i] = p.getPawnLocationsXForPlayer(0, i);
        }
        for (int i = 0; i < 4; i++) {
            player1LocationsX[i] = p.getPawnLocationsXForPlayer(1, i);
        }
        for (int i = 0; i < 4; i++) {
            player2LocationsX[i] = p.getPawnLocationsXForPlayer(2, i);
        }
        for (int i = 0; i < 4; i++) {
            player3LocationsX[i] = p.getPawnLocationsXForPlayer(3, i);
        }
        for (int i = 0; i < 4; i++) {
            player0LocationsY[i] = p.getPawnLocationsYForPlayer(0, i);
        }
        for (int i = 0; i < 4; i++) {
            player1LocationsY[i] = p.getPawnLocationsYForPlayer(1, i);
        }
        for (int i = 0; i < 4; i++) {
            player2LocationsY[i] = p.getPawnLocationsYForPlayer(2, i);
        }
        for (int i = 0; i < 4; i++) {
            player3LocationsY[i] = p.getPawnLocationsYForPlayer(3, i);
        }
        board = p.getBoard();

        legalMoves0 = p.legalMoves0;
        legalMoves1 = p.legalMoves1;
        legalMoves2 = p.legalMoves2;
        legalMoves3 = p.legalMoves3;
    }

    public void initBoardPieces() {
        // normal rectangular board pieces
        board[0] = (new Rect(hori[11], hori[13], vert[15], vert[16], "rect", 1));
        board[1] = (new Rect(hori[11], hori[13], vert[14], vert[15], "rect", 1));
        board[2] = (new Rect(hori[11], hori[13], vert[13], vert[14], "rect", 1));
//        ...
//        ...
        board[22] = (new Rect(hori[11], hori[13], vert[6], vert[7], "rect", 1));
        board[23] = (new Rect(hori[11], hori[13], vert[5], vert[6], "rect", 1));
        board[24] = (new Rect(hori[11], hori[13], vert[4], vert[5], "rect", 1));
        board[25] = (new Rect(hori[11], hori[13], vert[3], vert[4], "rect", 1));
        board[26] = (new Rect(hori[11], hori[13], vert[2], vert[3], "rect", 1));
        board[27] = (new Rect(hori[11], hori[13], vert[1], vert[2], "rect", 1));
        board[28] = (new Rect(hori[11], hori[13], vert[0], vert[1], "rect", 1));
//        ...
//        ...
        board[64] = (new Rect(hori[11], hori[13], vert[19], vert[20], "rect", 1));
        board[65] = (new Rect(hori[11], hori[13], vert[18], vert[19], "rect", 1));
        board[66] = (new Rect(hori[11], hori[13], vert[17], vert[18], "rect", 1));
        board[67] = (new Rect(hori[11], hori[13], vert[16], vert[17], "rect", 1));
//        ...
//        ...
        board[5] = (new Rect(hori[13], hori[14], vert[11], vert[13], "rect", 2));
        board[6] = (new Rect(hori[14], hori[15], vert[11], vert[13], "rect", 2));
        board[7] = (new Rect(hori[15], hori[16], vert[11], vert[13], "rect", 2));
        board[8] = (new Rect(hori[16], hori[17], vert[11], vert[13], "rect", 2));
        board[9] = (new Rect(hori[17], hori[18], vert[11], vert[13], "rect", 2));
        board[10] = (new Rect(hori[18], hori[19], vert[11], vert[13], "rect", 2));
        board[11] = (new Rect(hori[19], hori[20], vert[11], vert[13], "rect", 2));
//        ...
//        ...
        board[47] = (new Rect(hori[0], hori[1], vert[11], vert[13], "rect", 2));
        board[48] = (new Rect(hori[1], hori[2], vert[11], vert[13], "rect", 2));
        board[49] = (new Rect(hori[2], hori[3], vert[11], vert[13], "rect", 2));
        board[50] = (new Rect(hori[3], hori[4], vert[11], vert[13], "rect", 2));
        board[51] = (new Rect(hori[4], hori[5], vert[11], vert[13], "rect", 2));
        board[52] = (new Rect(hori[5], hori[6], vert[11], vert[13], "rect", 2));
        board[53] = (new Rect(hori[6], hori[7], vert[11], vert[13], "rect", 2));
//        ...
//        ...
        board[30] = (new Rect(hori[7], hori[9], vert[0], vert[1], "rect", 3));
        board[31] = (new Rect(hori[7], hori[9], vert[1], vert[2], "rect", 3));
        board[32] = (new Rect(hori[7], hori[9], vert[2], vert[3], "rect", 3));
        board[33] = (new Rect(hori[7], hori[9], vert[3], vert[4], "rect", 3));
        board[34] = (new Rect(hori[7], hori[9], vert[4], vert[5], "rect", 3));
        board[35] = (new Rect(hori[7], hori[9], vert[5], vert[6], "rect", 3));
        board[36] = (new Rect(hori[7], hori[9], vert[6], vert[7], "rect", 3));
//        ...
//        ...
        board[56] = (new Rect(hori[7], hori[9], vert[13], vert[14], "rect", 3));
        board[57] = (new Rect(hori[7], hori[9], vert[14], vert[15], "rect", 3));
        board[58] = (new Rect(hori[7], hori[9], vert[15], vert[16], "rect", 3));
        board[59] = (new Rect(hori[7], hori[9], vert[16], vert[17], "rect", 3));
        board[60] = (new Rect(hori[7], hori[9], vert[17], vert[18], "rect", 3));
        board[61] = (new Rect(hori[7], hori[9], vert[18], vert[19], "rect", 3));
        board[62] = (new Rect(hori[7], hori[9], vert[19], vert[20], "rect", 3));
//        ...
//        ...
        board[13] = (new Rect(hori[19], hori[20], vert[7], vert[9], "rect", 4));
        board[14] = (new Rect(hori[18], hori[19], vert[7], vert[9], "rect", 4));
        board[15] = (new Rect(hori[17], hori[18], vert[7], vert[9], "rect", 4));
        board[16] = (new Rect(hori[16], hori[17], vert[7], vert[9], "rect", 4));
        board[17] = (new Rect(hori[15], hori[16], vert[7], vert[9], "rect", 4));
        board[18] = (new Rect(hori[14], hori[15], vert[7], vert[9], "rect", 4));
        board[19] = (new Rect(hori[13], hori[14], vert[7], vert[9], "rect", 4));
//        ...
//        ...
        board[39] = (new Rect(hori[6], hori[7], vert[7], vert[9], "rect", 4));
        board[40] = (new Rect(hori[5], hori[6], vert[7], vert[9], "rect", 4));
        board[41] = (new Rect(hori[4], hori[5], vert[7], vert[9], "rect", 4));
        board[42] = (new Rect(hori[3], hori[4], vert[7], vert[9], "rect", 4));
        board[43] = (new Rect(hori[2], hori[3], vert[7], vert[9], "rect", 4));
        board[44] = (new Rect(hori[1], hori[2], vert[7], vert[9], "rect", 4));
        board[45] = (new Rect(hori[0], hori[1], vert[7], vert[9], "rect", 4));
//        ...
//        ...
        // trapezoidal board pieces
        board[3] = (new Rect(hori[11], hori[13], vert[12], vert[13], hori[12], "trapD", 1));
        board[54] = (new Rect(hori[7], hori[8], vert[11], vert[13], vert[12], "trapD", 2));
        board[37] = (new Rect(hori[7], hori[9], vert[7], vert[8], hori[8], "trapD", 3));
        board[20] = (new Rect(hori[12], hori[13], vert[7], vert[9], vert[8], "trapD", 4));
//        ...
//        ...
        board[55] = (new Rect(hori[7], hori[9], vert[12], vert[13], hori[8], "trapU", 1));
        board[38] = (new Rect(hori[7], hori[8], vert[7], vert[9], vert[8], "trapU", 2));
        board[21] = (new Rect(hori[11], hori[13], vert[7], vert[8], hori[12], "trapU", 3));
        board[4] = (new Rect(hori[12], hori[13], vert[11], vert[13], vert[12], "trapU", 4));
//        ...
//        ...
        // neutral zone pieces
        board[63] = (new Rect(hori[9], hori[11], vert[19], vert[20], "neutral", 1));
        board[46] = (new Rect(hori[0], hori[1], vert[9], vert[11], "neutral", 2));
        board[29] = (new Rect(hori[9], hori[11], vert[0], vert[1], "neutral", 3));
        board[12] = (new Rect(hori[19], hori[20], vert[9], vert[11], "neutral", 4));
//        ...
//        ...
        // homebase pieces
        board[75] = (new Rect(hori[9], hori[10], hori[11], vert[10], vert[11], vert[12], 1));
        board[83] = (new Rect(hori[8], hori[9], hori[10], vert[9], vert[10], vert[11], 2));
        board[91] = (new Rect(hori[9], hori[10], hori[11], vert[8], vert[9], vert[10], 3));
        board[99] = (new Rect(hori[10], hori[11], hori[12], vert[9], vert[10], vert[11], 4));
//        ...
//        ...

        // safezone pieces
        board[68] = (new Rect(hori[9], hori[11], vert[18], vert[19], "safeZ", 1));
        board[69] = (new Rect(hori[9], hori[11], vert[17], vert[18], "safeZ", 1));
        board[70] = (new Rect(hori[9], hori[11], vert[16], vert[17], "safeZ", 1));
        board[71] = (new Rect(hori[9], hori[11], vert[15], vert[16], "safeZ", 1));
        board[72] = (new Rect(hori[9], hori[11], vert[14], vert[15], "safeZ", 1));
        board[73] = (new Rect(hori[9], hori[11], vert[13], vert[14], "safeZ", 1));
        board[74] = (new Rect(hori[9], hori[11], vert[12], vert[13], "safeZ", 1));
//        ...
//        ...
        board[76] = (new Rect(hori[1], hori[2], vert[9], vert[11], "safeZ", 2));
        board[77] = (new Rect(hori[2], hori[3], vert[9], vert[11], "safeZ", 2));
        board[78] = (new Rect(hori[3], hori[4], vert[9], vert[11], "safeZ", 2));
        board[79] = (new Rect(hori[4], hori[5], vert[9], vert[11], "safeZ", 2));
        board[80] = (new Rect(hori[5], hori[6], vert[9], vert[11], "safeZ", 2));
        board[81] = (new Rect(hori[6], hori[7], vert[9], vert[11], "safeZ", 2));
        board[82] = (new Rect(hori[7], hori[8], vert[9], vert[11], "safeZ", 2));
//        ...
//        ...
        board[84] = (new Rect(hori[9], hori[11], vert[1], vert[2], "safeZ", 3));
        board[85] = (new Rect(hori[9], hori[11], vert[2], vert[3], "safeZ", 3));
        board[86] = (new Rect(hori[9], hori[11], vert[3], vert[4], "safeZ", 3));
        board[87] = (new Rect(hori[9], hori[11], vert[4], vert[5], "safeZ", 3));
        board[88] = (new Rect(hori[9], hori[11], vert[5], vert[6], "safeZ", 3));
        board[89] = (new Rect(hori[9], hori[11], vert[6], vert[7], "safeZ", 3));
        board[90] = (new Rect(hori[9], hori[11], vert[7], vert[8], "safeZ", 3));
//        ...
//        ...
        board[92] = (new Rect(hori[18], hori[19], vert[9], vert[11], "safeZ", 4));
        board[93] = (new Rect(hori[17], hori[18], vert[9], vert[11], "safeZ", 4));
        board[94] = (new Rect(hori[16], hori[17], vert[9], vert[11], "safeZ", 4));
        board[95] = (new Rect(hori[15], hori[16], vert[9], vert[11], "safeZ", 4));
        board[96] = (new Rect(hori[14], hori[15], vert[9], vert[11], "safeZ", 4));
        board[97] = (new Rect(hori[13], hori[14], vert[9], vert[11], "safeZ", 4));
        board[98] = (new Rect(hori[12], hori[13], vert[9], vert[11], "safeZ", 4));
        playerTurn = 0;
    }

    public String containsInRect (float x, float y) {
        for (int i = 0; i < board.length; i++) {
            Rect rect = board[i];
            String type = rect.type;
            if (rect.contains(x,y)) {
                return type + " " + i;
            }
        }
        return "Error " + -1;
    }

    //TEMPORARY METHOD FOR AVAYA

    public int getRect (float x, float y) {
        if ((x == pawnLocation.pawnLocationX[116] && y == pawnLocation.pawnLocationY[116]) || (x == pawnLocation.pawnLocationX[117] && y == pawnLocation.pawnLocationY[117]) || (x == pawnLocation.pawnLocationX[118] && y == pawnLocation.pawnLocationY[118]) || (x == pawnLocation.pawnLocationX[119] && y == pawnLocation.pawnLocationY[119])){
            return 75;
        }
        else if ((x == pawnLocation.pawnLocationX[120] && y == pawnLocation.pawnLocationY[120]) || (x == pawnLocation.pawnLocationX[121] && y == pawnLocation.pawnLocationY[121]) || (x == pawnLocation.pawnLocationX[122] && y == pawnLocation.pawnLocationY[122]) || (x == pawnLocation.pawnLocationX[123] && y == pawnLocation.pawnLocationY[123])) {
            return 83;
        }
        else if ((x == pawnLocation.pawnLocationX[124] && y == pawnLocation.pawnLocationY[124]) || (x == pawnLocation.pawnLocationX[125] && y == pawnLocation.pawnLocationY[125]) || (x == pawnLocation.pawnLocationX[126] && y == pawnLocation.pawnLocationY[126]) || (x == pawnLocation.pawnLocationX[127] && y == pawnLocation.pawnLocationY[127])) {
            return 91;
        }
        else if ((x == pawnLocation.pawnLocationX[128] && y == pawnLocation.pawnLocationY[128]) || (x == pawnLocation.pawnLocationX[129] && y == pawnLocation.pawnLocationY[129]) || (x == pawnLocation.pawnLocationX[130] && y == pawnLocation.pawnLocationY[130]) || (x == pawnLocation.pawnLocationX[131] && y == pawnLocation.pawnLocationY[131])) {
            return 99;
        }
        else {
            for (int i = 0; i < board.length; i++) {
                Rect rect = board[i];
                if (rect.contains(x, y)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public void setUseDieActionMade(boolean x) {useDieActionMade = x;}

    public boolean getUseDieActionMade() {return useDieActionMade;}

    public void setPawnActionMade(boolean x) {pawnActionMade = x;}

    public boolean getPawnActionMade() {return pawnActionMade;}

    public void setCheckLegalMoveActionMade(boolean x) {
        checkLegalMoveActionMade = x;
    }

    public boolean getCheckLegalMoveActionMade() {return checkLegalMoveActionMade;}

    //Getter to return the number of doubles the current player has had in the turn
    public int getNumOfDoubles() { return numOfDoubles; }

    //Setter to set the number of doubles the current player has had in a turn
    public void setNumOfDoubles(int amountOfDoubles) { numOfDoubles = amountOfDoubles; }

    public void setDoublesThrown(boolean x) {
        doublesThrown = x;
    }

    public boolean getDoublesThrown() {
        return doublesThrown;
    }

    //Getter to return who's turn it currently is
    public int getPlayerTurn() { return playerTurn; }

    //method to change the player's turn
    // called when the players turn is completed
    public void setPlayerTurn() {
        //Moves player turn up by one (back to zero if last player
        playerTurn++;
        if (playerTurn > 3) {
            playerTurn = 0;
        }
        //Resets the substage of the game to Roll (0)
        currentSubstage = Roll;
        //Resets the counter for number of doubles back to 0
        numOfDoubles = 0;
        //Resets teh radio button that's checked
        radioButtonChecked = -1;
        //Unselects the current die value selected
        dieValueSelected = -1;
        //Resets the legal moves
        legalMoves0.clear();
        legalMoves1.clear();
        legalMoves2.clear();
        legalMoves3.clear();
    }

    //Getter to return integer array of both dice values
    public int getDiceVals(int index)
    {
        return dieVals[index];
    }

    //Getter to return integer value of first die value
    public int getDice1Val() { return dieVals[0]; }

    //Getter to return integer value of second die value
    public int getDice2Val() { return dieVals[1]; }

    //Setter to set values of both dice
    public void setDieVals(int dieVal1, int dieVal2) {
        dieVals[0] = dieVal1;
        dieVals[1] = dieVal2;
    }

    //Setter to set which radio button is selected
    public void setRadioButtonChecked(int x) {
        radioButtonChecked = x;
    }

    //Getter to return which radio button is selectec
    public int getRadioButtonChecked() {
        return radioButtonChecked;
    }

    //Setter to select which die value is selected
    public void setDieValueSelected (int x) { dieValueSelected = x;}

    //Getter to return which die value is selected
    public int getDieValueSelected() {return dieValueSelected;}

    //Setter to select which die is selected
    public void setDieSelected (int x) { dieSelected = x;}

    //Getter to return which die is selected
    public int getDieSelected() {return dieSelected;}

    //Setter to set which moves are legal
    public void setLegalMoves(String die, int index, int rectNumber) {
        switch (index) {
            case 0:
                legalMoves0.put(die, rectNumber);
                break;
            case 1:
                legalMoves1.put(die, rectNumber);
                break;
            case 2:
                legalMoves2.put(die, rectNumber);
                break;
            case 3:
                legalMoves3.put(die, rectNumber);
                break;
        }
    }

    //Getter to return which moves are legal moves
    public int getLegalMoves(String die, int index) {
        switch (index) {
            case 0:
                if (legalMoves0.get(die) == null) {
                    return -1;
                }
                return legalMoves0.get(die);
            case 1:
                if (legalMoves1.get(die) == null) {
                    return -1;
                }
                return legalMoves1.get(die);
            case 2:
                if (legalMoves2.get(die) == null) {
                    return -1;
                }
                return legalMoves2.get(die);
            case 3:
                if (legalMoves3.get(die) == null) {
                    return -1;
                }
                return legalMoves3.get(die);
        }
        return -1;
    }

    //Method to remove which moves are considered legal
    public void removeLegalMoves(String die, int index) {

        switch (index) {
            case 0:
                legalMoves0.remove(die);
            break;
            case 1:
                legalMoves1.remove(die);
            break;
            case 2:
                legalMoves2.remove(die);
            break;
            case 3:
                legalMoves3.remove(die);
            break;
        }
    }

    //Method to see if there is a legal move for the die value
    public boolean containsLegalMoves(String die, int index) {
        switch (index) {
            case 0:
                return legalMoves0.containsKey(die);
            case 1:
                return legalMoves1.containsKey(die);
            case 2:
                return legalMoves2.containsKey(die);
            case 3:
                return legalMoves3.containsKey(die);
        }
        return false;
    }

    //Method that checks to see if there are no legal moves
    public boolean isEmptyLegalMoves(int index) {
        switch (index) {
            case 0:
                return legalMoves0.isEmpty();
            case 1:
                return legalMoves1.isEmpty();
            case 2:
                return legalMoves2.isEmpty();
            case 3:
                return legalMoves3.isEmpty();
        }
        return false;
    }

    //Getter to return current substage
    public int getCurrentSubstage() { return currentSubstage; }

    //Setter to reset the current substage
    public void setCurrentSubstage(int newSubstage)
    {
        currentSubstage = newSubstage;
    }

    //Getter to set the current location
    public int getSelectedLocation () {
        return selectedLocation;
    }

    public Rect[] getBoard() {
        return board;
    }

    //Returns x coordinates of pawn location
    public int getPawnLocationsXForPlayer(int playerIdx, int pawnIdx)
    {
        switch (playerIdx)
        {
            case 0:
                return player0LocationsX[pawnIdx];
            case 1:
                return player1LocationsX[pawnIdx];
            case 2:
                return player2LocationsX[pawnIdx];
            case 3:
                return player3LocationsX[pawnIdx];
            default:
                //throws an error if a valid pawn or player index isn't providedd
                throw new IllegalArgumentException("Error: The player index passed in is not a valid player ID");
        }
    }

    //Getter to obtain y coords for pawn
    public int getPawnLocationsYForPlayer(int playerIdx, int pawnIdx)
    {
        switch (playerIdx)
        {
            case 0:
                return player0LocationsY[pawnIdx];
            case 1:
                return player1LocationsY[pawnIdx];
            case 2:
                return player2LocationsY[pawnIdx];
            case 3:
                return player3LocationsY[pawnIdx];
            default:
                //throws an error if a valid pawn or player index isn't provided
                throw new IllegalArgumentException("Error: The player index passed in is not a valid player ID");
        }
    }

    //Setter to set pawn locations for player (both x and y coords)
    public void setPawnLocationsForPlayer(int playerIdx, int pawnIndex, int locationX, int locationY)
    {
        switch (playerIdx)
        {
            case 0:
                player0LocationsX[pawnIndex] = locationX;
                player0LocationsY[pawnIndex] = locationY;
                break;
            case 1:
                player1LocationsX[pawnIndex] = locationX;
                player1LocationsY[pawnIndex] = locationY;
                break;
            case 2:
                player2LocationsX[pawnIndex] = locationX;
                player2LocationsY[pawnIndex] = locationY;
                break;
            case 3:
                player3LocationsX[pawnIndex] = locationX;
                player3LocationsY[pawnIndex] = locationY;
                break;
        }
    }

    public void initPawnLocation(int playerIdx) {
        // initialize the locations of the pawns
        switch (playerIdx) {
            //red
            case 0:
                setPawnLocationsForPlayer(playerIdx, 0, pawnLocation.pawnLocationX[100], pawnLocation.pawnLocationY[100]);
                setPawnLocationsForPlayer(playerIdx, 1, pawnLocation.pawnLocationX[101], pawnLocation.pawnLocationY[101]);
                setPawnLocationsForPlayer(playerIdx, 2, pawnLocation.pawnLocationX[102], pawnLocation.pawnLocationY[102]);
                setPawnLocationsForPlayer(playerIdx, 3, pawnLocation.pawnLocationX[103], pawnLocation.pawnLocationY[103]);
                break;
            //blue
            case 1:
                setPawnLocationsForPlayer(playerIdx, 0, pawnLocation.pawnLocationX[104], pawnLocation.pawnLocationY[104]);
                setPawnLocationsForPlayer(playerIdx, 1, pawnLocation.pawnLocationX[105], pawnLocation.pawnLocationY[105]);
                setPawnLocationsForPlayer(playerIdx, 2, pawnLocation.pawnLocationX[106], pawnLocation.pawnLocationY[106]);
                setPawnLocationsForPlayer(playerIdx, 3, pawnLocation.pawnLocationX[107], pawnLocation.pawnLocationY[107]);
                break;
            //yellow
            case 2:
                setPawnLocationsForPlayer(playerIdx, 0, pawnLocation.pawnLocationX[108], pawnLocation.pawnLocationY[108]);
                setPawnLocationsForPlayer(playerIdx, 1, pawnLocation.pawnLocationX[109], pawnLocation.pawnLocationY[109]);
                setPawnLocationsForPlayer(playerIdx, 2, pawnLocation.pawnLocationX[110], pawnLocation.pawnLocationY[110]);
                setPawnLocationsForPlayer(playerIdx, 3, pawnLocation.pawnLocationX[111], pawnLocation.pawnLocationY[111]);
                break;
            //green
            case 3:
                setPawnLocationsForPlayer(playerIdx, 0, pawnLocation.pawnLocationX[112], pawnLocation.pawnLocationY[112]);
                setPawnLocationsForPlayer(playerIdx, 1, pawnLocation.pawnLocationX[113], pawnLocation.pawnLocationY[113]);
                setPawnLocationsForPlayer(playerIdx, 2, pawnLocation.pawnLocationX[114], pawnLocation.pawnLocationY[114]);
                setPawnLocationsForPlayer(playerIdx, 3, pawnLocation.pawnLocationX[115], pawnLocation.pawnLocationY[115]);
                break;
        }
    }

    //Method to reset pawn locations for players
    public void resetPawnLocation(int playerIdx, int pawnIdx) {
        switch (playerIdx) {
            //red
            case 0:
                switch (pawnIdx) {
                    case 0:
                        setPawnLocationsForPlayer(playerIdx, 0, pawnLocation.pawnLocationX[100], pawnLocation.pawnLocationY[100]);
                        break;
                    case 1:
                        setPawnLocationsForPlayer(playerIdx, 1, pawnLocation.pawnLocationX[101], pawnLocation.pawnLocationY[101]);
                        break;
                    case 2:
                        setPawnLocationsForPlayer(playerIdx, 2, pawnLocation.pawnLocationX[102], pawnLocation.pawnLocationY[102]);
                        break;
                    case 3:
                        setPawnLocationsForPlayer(playerIdx, 3, pawnLocation.pawnLocationX[103], pawnLocation.pawnLocationY[103]);
                        break;
                }
                break;
                //blue
            case 1:
                switch (pawnIdx) {
                    case 0:
                        setPawnLocationsForPlayer(playerIdx, 0, pawnLocation.pawnLocationX[104], pawnLocation.pawnLocationY[104]);
                        break;
                    case 1:
                        setPawnLocationsForPlayer(playerIdx, 1, pawnLocation.pawnLocationX[105], pawnLocation.pawnLocationY[105]);
                        break;
                    case 2:
                        setPawnLocationsForPlayer(playerIdx, 2, pawnLocation.pawnLocationX[106], pawnLocation.pawnLocationY[106]);
                        break;
                    case 3:
                        setPawnLocationsForPlayer(playerIdx, 3, pawnLocation.pawnLocationX[107], pawnLocation.pawnLocationY[107]);
                        break;
                }
                break;
                //yellow
            case 2:
                switch (pawnIdx) {
                    case 0:
                        setPawnLocationsForPlayer(playerIdx, 0, pawnLocation.pawnLocationX[108], pawnLocation.pawnLocationY[108]);
                        break;
                    case 1:
                        setPawnLocationsForPlayer(playerIdx, 1, pawnLocation.pawnLocationX[109], pawnLocation.pawnLocationY[109]);
                        break;
                    case 2:
                        setPawnLocationsForPlayer(playerIdx, 2, pawnLocation.pawnLocationX[110], pawnLocation.pawnLocationY[110]);
                        break;
                    case 3:
                        setPawnLocationsForPlayer(playerIdx, 3, pawnLocation.pawnLocationX[111], pawnLocation.pawnLocationY[111]);
                        break;
                }
                break;
                //green
            case 3:
                switch (pawnIdx) {
                    case 0:
                        setPawnLocationsForPlayer(playerIdx, 0, pawnLocation.pawnLocationX[112], pawnLocation.pawnLocationY[112]);
                        break;
                    case 1:
                        setPawnLocationsForPlayer(playerIdx, 1, pawnLocation.pawnLocationX[113], pawnLocation.pawnLocationY[113]);
                        break;
                    case 2:
                        setPawnLocationsForPlayer(playerIdx, 2, pawnLocation.pawnLocationX[114], pawnLocation.pawnLocationY[114]);
                        break;
                    case 3:
                        setPawnLocationsForPlayer(playerIdx, 3, pawnLocation.pawnLocationX[115], pawnLocation.pawnLocationY[115]);
                        break;
                }
                break;
        }
    }
}
