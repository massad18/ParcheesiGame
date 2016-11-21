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


    // up to down
    private int[] hori = {0, 81, 150, 217, 284, 358, 424, 491, 558, 626, 694, 763, 833, 898, 967, 1036, 1102, 1165, 1235, 1300, 1366};
    // left to right
    private int[] vert = {0, 82, 144, 205, 271, 337, 398, 464, 519, 578, 637, 696, 761, 820, 877, 939, 1003, 1066, 1133, 1191, 1261};

    private String[] colors = {"red", "blue", "yellow", "green"};

    private Hashtable<Integer, Rect> boardPieces = new Hashtable<>();
    private Hashtable<String, Rect> homebasePieces = new Hashtable<>();
    private Hashtable<Integer, Rect> safezonePieces = new Hashtable<>();

    private int selectedLocation;
    private String selectedLocationColor;
    private String selectedLocationType;

    public ParState() {super();}

    protected void initBoardPieces() {
        // normal rectangular board pieces
        boardPieces.put(1, new Rect(hori[11], hori[13], vert[15], vert[16], "rect", 1));
        boardPieces.put(2, new Rect(hori[11], hori[13], vert[14], vert[15], "rect", 1));
        boardPieces.put(3, new Rect(hori[11], hori[13], vert[13], vert[14], "rect", 1));
//        ...
//        ...
        boardPieces.put(23, new Rect(hori[11], hori[13], vert[6], vert[7], "rect", 1));
        boardPieces.put(24, new Rect(hori[11], hori[13], vert[5], vert[6], "rect", 1));
        boardPieces.put(25, new Rect(hori[11], hori[13], vert[4], vert[5], "rect", 1));
        boardPieces.put(26, new Rect(hori[11], hori[13], vert[3], vert[4], "rect", 1));
        boardPieces.put(27, new Rect(hori[11], hori[13], vert[2], vert[3], "rect", 1));
        boardPieces.put(28, new Rect(hori[11], hori[13], vert[1], vert[2], "rect", 1));
        boardPieces.put(29, new Rect(hori[11], hori[13], vert[0], vert[1], "rect", 1));
//        ...
//        ...
        boardPieces.put(65, new Rect(hori[11], hori[13], vert[19], vert[20], "rect", 1));
        boardPieces.put(66, new Rect(hori[11], hori[13], vert[18], vert[19], "rect", 1));
        boardPieces.put(67, new Rect(hori[11], hori[13], vert[17], vert[18], "rect", 1));
        boardPieces.put(68, new Rect(hori[11], hori[13], vert[16], vert[17], "rect", 1));
//        ...
//        ...
        boardPieces.put(6, new Rect(hori[13], hori[14], vert[11], vert[13], "rect", 2));
        boardPieces.put(7, new Rect(hori[14], hori[15], vert[11], vert[13], "rect", 2));
        boardPieces.put(8, new Rect(hori[15], hori[16], vert[11], vert[13], "rect", 2));
        boardPieces.put(9, new Rect(hori[16], hori[17], vert[11], vert[13], "rect", 2));
        boardPieces.put(10, new Rect(hori[17], hori[18], vert[11], vert[13], "rect", 2));
        boardPieces.put(11, new Rect(hori[18], hori[19], vert[11], vert[13], "rect", 2));
        boardPieces.put(12, new Rect(hori[19], hori[20], vert[11], vert[13], "rect", 2));
//        ...
//        ...
        boardPieces.put(48, new Rect(hori[0], hori[1], vert[11], vert[13], "rect", 2));
        boardPieces.put(49, new Rect(hori[1], hori[2], vert[11], vert[13], "rect", 2));
        boardPieces.put(50, new Rect(hori[2], hori[3], vert[11], vert[13], "rect", 2));
        boardPieces.put(51, new Rect(hori[3], hori[4], vert[11], vert[13], "rect", 2));
        boardPieces.put(52, new Rect(hori[4], hori[5], vert[11], vert[13], "rect", 2));
        boardPieces.put(53, new Rect(hori[5], hori[6], vert[11], vert[13], "rect", 2));
        boardPieces.put(54, new Rect(hori[6], hori[7], vert[11], vert[13], "rect", 2));
//        ...
//        ...
        boardPieces.put(31, new Rect(hori[7], hori[9], vert[0], vert[1], "rect", 3));
        boardPieces.put(32, new Rect(hori[7], hori[9], vert[1], vert[2], "rect", 3));
        boardPieces.put(33, new Rect(hori[7], hori[9], vert[2], vert[3], "rect", 3));
        boardPieces.put(34, new Rect(hori[7], hori[9], vert[3], vert[4], "rect", 3));
        boardPieces.put(35, new Rect(hori[7], hori[9], vert[4], vert[5], "rect", 3));
        boardPieces.put(36, new Rect(hori[7], hori[9], vert[5], vert[6], "rect", 3));
        boardPieces.put(37, new Rect(hori[7], hori[9], vert[6], vert[7], "rect", 3));
//        ...
//        ...
        boardPieces.put(57, new Rect(hori[7], hori[9], vert[13], vert[14], "rect", 3));
        boardPieces.put(58, new Rect(hori[7], hori[9], vert[14], vert[15], "rect", 3));
        boardPieces.put(59, new Rect(hori[7], hori[9], vert[15], vert[16], "rect", 3));
        boardPieces.put(60, new Rect(hori[7], hori[9], vert[16], vert[17], "rect", 3));
        boardPieces.put(61, new Rect(hori[7], hori[9], vert[17], vert[18], "rect", 3));
        boardPieces.put(62, new Rect(hori[7], hori[9], vert[18], vert[19], "rect", 3));
        boardPieces.put(63, new Rect(hori[7], hori[9], vert[19], vert[20], "rect", 3));
//        ...
//        ...
        boardPieces.put(14, new Rect(hori[19], hori[20], vert[7], vert[9], "rect", 4));
        boardPieces.put(15, new Rect(hori[18], hori[19], vert[7], vert[9], "rect", 4));
        boardPieces.put(16, new Rect(hori[17], hori[18], vert[7], vert[9], "rect", 4));
        boardPieces.put(17, new Rect(hori[16], hori[17], vert[7], vert[9], "rect", 4));
        boardPieces.put(18, new Rect(hori[15], hori[16], vert[7], vert[9], "rect", 4));
        boardPieces.put(19, new Rect(hori[14], hori[15], vert[7], vert[9], "rect", 4));
        boardPieces.put(20, new Rect(hori[13], hori[14], vert[7], vert[9], "rect", 4));
//        ...
//        ...
        boardPieces.put(40, new Rect(hori[6], hori[7], vert[7], vert[9], "rect", 4));
        boardPieces.put(41, new Rect(hori[5], hori[6], vert[7], vert[9], "rect", 4));
        boardPieces.put(42, new Rect(hori[4], hori[5], vert[7], vert[9], "rect", 4));
        boardPieces.put(43, new Rect(hori[3], hori[4], vert[7], vert[9], "rect", 4));
        boardPieces.put(44, new Rect(hori[2], hori[3], vert[7], vert[9], "rect", 4));
        boardPieces.put(45, new Rect(hori[1], hori[2], vert[7], vert[9], "rect", 4));
        boardPieces.put(46, new Rect(hori[0], hori[1], vert[7], vert[9], "rect", 4));
//        ...
//        ...
        // trapezoidal board pieces
        boardPieces.put(4, new Rect(hori[11], hori[13], vert[12], vert[13], hori[12], "trapD", 1));
        boardPieces.put(55, new Rect(hori[7], hori[8], vert[11], vert[13], vert[12], "trapD", 2));
        boardPieces.put(38, new Rect(hori[7], hori[9], vert[7], vert[8], hori[8], "trapD", 3));
        boardPieces.put(21, new Rect(hori[12], hori[13], vert[7], vert[9], vert[8], "trapD", 4));
//        ...
//        ...
        boardPieces.put(56, new Rect(hori[7], hori[9], vert[12], vert[13], hori[8], "trapU", 1));
        boardPieces.put(39, new Rect(hori[7], hori[8], vert[7], vert[9], vert[8], "trapU", 2));
        boardPieces.put(22, new Rect(hori[11], hori[13], vert[7], vert[8], hori[12], "trapU", 3));
        boardPieces.put(5, new Rect(hori[12], hori[13], vert[11], vert[13], vert[12], "trapU", 4));
//        ...
//        ...
        // neutral zone pieces
        boardPieces.put(64, new Rect(hori[9], hori[11], vert[19], vert[20], "neutral", 1));
        boardPieces.put(47, new Rect(hori[0], hori[1], vert[9], vert[11], "neutral", 2));
        boardPieces.put(30, new Rect(hori[9], hori[11], vert[0], vert[1], "neutral", 3));
        boardPieces.put(13, new Rect(hori[19], hori[20], vert[9], vert[11], "neutral", 4));
//        ...
//        ...
        // homebase pieces
        homebasePieces.put("red", new Rect(hori[9], hori[10], hori[11], vert[10], vert[11], vert[12], 1));
        homebasePieces.put("blue", new Rect(hori[8], hori[9], hori[10], vert[9], vert[10], vert[11], 2));
        homebasePieces.put("yellow", new Rect(hori[9], hori[10], hori[11], vert[8], vert[9], vert[10], 3));
        homebasePieces.put("green", new Rect(hori[10], hori[11], hori[12], vert[9], vert[10], vert[11], 4));
//        ...
//        ...

        // safezone pieces
        safezonePieces.put(1, new Rect(hori[9], hori[11], vert[18], vert[19], "safeZ", 1));
        safezonePieces.put(2, new Rect(hori[9], hori[11], vert[17], vert[18], "safeZ", 1));
        safezonePieces.put(3, new Rect(hori[9], hori[11], vert[16], vert[17], "safeZ", 1));
        safezonePieces.put(4, new Rect(hori[9], hori[11], vert[15], vert[16], "safeZ", 1));
        safezonePieces.put(5, new Rect(hori[9], hori[11], vert[14], vert[15], "safeZ", 1));
        safezonePieces.put(6, new Rect(hori[9], hori[11], vert[13], vert[14], "safeZ", 1));
        safezonePieces.put(7, new Rect(hori[9], hori[11], vert[12], vert[13], "safeZ", 1));
//        ...
//        ...
        safezonePieces.put(8, new Rect(hori[1], hori[2], vert[9], vert[11], "safeZ", 2));
        safezonePieces.put(9, new Rect(hori[2], hori[3], vert[9], vert[11], "safeZ", 2));
        safezonePieces.put(10, new Rect(hori[3], hori[4], vert[9], vert[11], "safeZ", 2));
        safezonePieces.put(11, new Rect(hori[4], hori[5], vert[9], vert[11], "safeZ", 2));
        safezonePieces.put(12, new Rect(hori[5], hori[6], vert[9], vert[11], "safeZ", 2));
        safezonePieces.put(13, new Rect(hori[6], hori[7], vert[9], vert[11], "safeZ", 2));
        safezonePieces.put(14, new Rect(hori[7], hori[8], vert[9], vert[11], "safeZ", 2));
//        ...
//        ...
        safezonePieces.put(15, new Rect(hori[9], hori[11], vert[1], vert[2], "safeZ", 3));
        safezonePieces.put(16, new Rect(hori[9], hori[11], vert[2], vert[3], "safeZ", 3));
        safezonePieces.put(17, new Rect(hori[9], hori[11], vert[3], vert[4], "safeZ", 3));
        safezonePieces.put(18, new Rect(hori[9], hori[11], vert[4], vert[5], "safeZ", 3));
        safezonePieces.put(19, new Rect(hori[9], hori[11], vert[5], vert[6], "safeZ", 3));
        safezonePieces.put(20, new Rect(hori[9], hori[11], vert[6], vert[7], "safeZ", 3));
        safezonePieces.put(21, new Rect(hori[9], hori[11], vert[7], vert[8], "safeZ", 3));
//        ...
//        ...
        safezonePieces.put(22, new Rect(hori[18], hori[19], vert[9], vert[11], "safeZ", 4));
        safezonePieces.put(23, new Rect(hori[17], hori[18], vert[9], vert[11], "safeZ", 4));
        safezonePieces.put(24, new Rect(hori[16], hori[17], vert[9], vert[11], "safeZ", 4));
        safezonePieces.put(25, new Rect(hori[15], hori[16], vert[9], vert[11], "safeZ", 4));
        safezonePieces.put(26, new Rect(hori[14], hori[15], vert[9], vert[11], "safeZ", 4));
        safezonePieces.put(27, new Rect(hori[13], hori[14], vert[9], vert[11], "safeZ", 4));
        safezonePieces.put(28, new Rect(hori[12], hori[13], vert[9], vert[11], "safeZ", 4));
    }

    protected String containsInRect (float x, float y) {
        for (int i = 1; i < 69; i++) {
            Rect rect = boardPieces.get(i);
            if (rect != null) {
                if (rect.contains(x, y)) {
                    selectedLocation = i;
                    selectedLocationType = "Normal";
                    return selectedLocationType + " " + selectedLocation;
                }
            }
        }

        for (int j = 0; j < 4; j++) {
            Rect rect = homebasePieces.get(colors[j]);
            if (rect != null) {
                if (rect.contains(x,y)) {
                    selectedLocationColor = colors[j];
                    selectedLocationType = "HomeBase";
                    return selectedLocationType + " " + selectedLocationColor;
                }
            }
        }

        for (int k = 1; k < 29; k++) {
            Rect rect = safezonePieces.get(k);
            if (rect != null) {
                if (rect.contains(x,y)) {
                    selectedLocation = k;
                    selectedLocationType = "SafeZone";
                    return selectedLocationType + " " + selectedLocation;
                }
            }
        }

        return "Error " + -1;
    }

    //Getter to return the number of doubles the current player has had in the turn
    public int getNumOfDoubles() { return numOfDoubles; }

    //Getter to return who's turn it currently is
    public int getPlayerTurn() { return playerTurn; }

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

    //Getter to return current substage of turn
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

    public int getLocation(int playerIdx, int index) {
        return -1;
    }

    public void setLocation(int playerIdx, int index, String type) {

    }

    public void setHomeBaseLocation(int playerIdx, String color, String type) {

    }
}
