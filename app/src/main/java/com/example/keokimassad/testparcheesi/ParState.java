package com.example.keokimassad.testparcheesi;

import java.util.Hashtable;

import game.infoMsg.GameState;

/**
 * Created by KeokiMassad on 11/15/16.
 */

public class ParState extends GameState {

    //Defined integer constants for different substages of game
    private static final int Roll = 0;
    private static final int Begin_Move = 1;
    private static final int Mid_Move = 2;
    private static final int Game_Over = 3;

    private int currentSubstage; //integer to hold current substage of the game
    private int dieVals[]; //array to hold current dice values
    private int numOfDoubles; //holds number of doubles current player has had in the turn
    private int playerTurn; //holds who's turn it currently is

    //4 location arrays to hold locations of each players' pawns
    private int[] player1Locations = new int[4];
    private int[] player2Locations = new int[4];
    private int[] player3Locations = new int[4];
    private int[] player4Locations = new int[4];

    // up to down
    private int[] hori = {0, 81, 150, 217, 284, 358, 424, 491, 558, 626, 694, 763, 833, 898, 967, 1036, 1102, 1165, 1235, 1300, 1366};
    // left to right
    private int[] vert = {0, 82, 144, 205, 271, 337, 398, 464, 519, 578, 637, 696, 761, 820, 877, 939, 1003, 1066, 1133, 1191, 1261};

    private String[] colors = {"red", "blue", "yellow", "green"};

    private Rect[] board = new Rect[100];
    private Hashtable<Integer, Rect> boardPieces = new Hashtable<>();
    private Hashtable<String, Rect> homebasePieces = new Hashtable<>();
    private Hashtable<Integer, Rect> safezonePieces = new Hashtable<>();

    private int selectedLocation;
    private String selectedLocationColor;
    private String selectedLocationType;

    public ParState() {super();}

    protected void initBoardPieces() {
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

    protected String containsInRect (float x, float y) {
        for (int i = 0; i < board.length; i++) {
            Rect rect = board[i];
            String type = rect.type;
            if (rect.contains(x,y)) {
                return type + " " + i;
            }
        }

        return "Error " + -1;
    }

    //Getter to return the number of doubles the current player has had in the turn
    public int getNumOfDoubles() { return numOfDoubles; }

    //Getter to return who's turn it currently is
    public int getPlayerTurn() { return playerTurn; }

    //setter to set whose move it is
    public void setPlayerTurn(int player)
    {
        playerTurn = player;
    }

    //Getter to return integer array of both dice values
    public int[] getDiceVals()
    {
        return dieVals;
    }

    //Getter to return integer value of first die value
    public int getDice1Val() { return dieVals[0]; }

    //Getter to return integer value of second die value
    public int getDice2Val() { return dieVals[1]; }

    //Setter to set values of both dice
    public void setDieVals(int player, int dieVal1, int dieVal2) {
        dieVals[0] = dieVal1;
        dieVals[1] = dieVal2;
    }

    //Getter to return current substage
    public int getCurrentSubstage() { return currentSubstage; }

    public int getSelectedLocation () {
        return selectedLocation;
    }

    public String getSelectedLocationType () {
        return selectedLocationType;
    }

    public String getSelectedLocationColor () {
        return selectedLocationColor;
    }

    public int[] getPawnLocationsForPlayer(int playerIdx)
    {
        switch (playerIdx)
        {
            case 1:
                return player1Locations;
            case 2:
                return player2Locations;
            case 3:
                return player3Locations;
            case 4:
                return player4Locations;
            default:
                System.out.println("Error: The player index passed in is not a valid player ID");
                return player1Locations;
        }
    }

    public void setPawnLocationsForPlayer(int playerIdx, int pawnIndex, int location)
    {
        switch (playerIdx)
        {
            case 1:
                player1Locations[pawnIndex] = location;
                break;
            case 2:
                player2Locations[pawnIndex] = location;
                break;
            case 3:
                player3Locations[pawnIndex] = location;
                break;
            case 4:
                player4Locations[pawnIndex] = location;
                break;
        }
    }

    public void setHomeBaseLocation(int playerIdx, String color, String type) {
        //ToDo: can player's change
    }
}
