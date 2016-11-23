package com.example.keokimassad.testparcheesi;

/**
 * Created by KeokiMassad on 11/21/16.
 */

public class PawnLocation {

    private int xPos;
    private int yPos;
    ParState parState = new ParState();

    // the initial positions are #100-115
    // the homebase coordinates are #116 - 131
    protected int[] pawnLocationX = {833, 833, 833, 798, 866, 933, 1002, 1070, 1134, 1200, 1268, 1333,
            1333, 1333, 1268, 1200, 1134, 1070, 1002, 933, 866, 798, 833, 833, 833, 833, 833, 833,
            833, 694, 558, 558, 558, 558, 558, 558, 558, 592, 525, 456, 391, 321, 251, 184, 116, 41,
            41, 41, 116, 184, 251, 321, 391, 456, 525, 592, 558, 558, 558, 558, 558, 558,  558, 694,
            833, 833, 833, 833, 694, 694, 694, 694, 694, 694, 694, 0, 116, 184, 251, 321, 391,
            456, 0, 694, 694, 694, 694, 694, 694, 694, 0, 933, 1002, 1070, 1134, 1200, 1268, 0, 967,
            967, 967, 967, 81, 150, 217, 284, 424, 424, 424, 424, 1102, 1165, 1235, 1300,
            660, 660, 694, 729, 660, 626, 592, 592, 729, 729, 694, 660, 729, 763, 798, 798};
    private int[] pawnLocationBlockade1x = {};
    private int[] pawnLocationBlockade2x = {};

    // the initial positions are #100-115
    // the homebase coordinates are #116 - 131
    protected int[] pawnLocationY = {971, 908, 791, 729, 761, 761, 761, 761, 761, 761, 761, 637, 519,
            519, 519, 519, 519, 519, 519, 549, 492, 431, 368, 304, 238, 175, 113, 41, 41, 41, 113,
            175, 238, 304, 368, 431, 492, 549, 519, 519, 519, 519, 519, 519, 519, 637, 761, 761,
            761, 761, 761, 761, 761, 729, 791, 908, 971, 1035, 1100, 1162, 1226, 1226, 1226, 1162,
            1100, 1035, 1162, 1100, 1035, 971, 908, 791, 0, 637, 637, 637, 637, 637, 637, 637, 0,
            113, 175, 238, 304, 368, 431, 492, 0, 637, 637, 637, 637, 637, 637, 637, 0, 1191, 1133, 1066,
            1003, 877, 877, 877, 877, 82, 144, 205, 271, 398, 398, 398, 398, 667, 696, 729, 729,
            608, 608, 637, 667, 608, 578, 549, 549, 667, 667, 637, 608};
    private int[] pawnLocationBlockade1y = {};
    private int[] pawnLocationBlockade2y = {};

    public PawnLocation() {}

    public void initPawnLocation(int playerIdx) {
        // initialize the locations of the pawns

        //ToDo Add in the locations of the given player and initialize their location to their given starting locations
        switch (playerIdx) {
            //red
            case 0:
                parState.setPawnLocationsForPlayer(playerIdx, 0, pawnLocationX[100], pawnLocationY[100]);
                parState.setPawnLocationsForPlayer(playerIdx, 1, pawnLocationX[101], pawnLocationY[101]);
                parState.setPawnLocationsForPlayer(playerIdx, 2, pawnLocationX[102], pawnLocationY[102]);
                parState.setPawnLocationsForPlayer(playerIdx, 3, pawnLocationX[103], pawnLocationY[103]);
                break;
            //blue
            case 1:
                parState.setPawnLocationsForPlayer(playerIdx, 0, pawnLocationX[104], pawnLocationY[104]);
                parState.setPawnLocationsForPlayer(playerIdx, 1, pawnLocationX[105], pawnLocationY[105]);
                parState.setPawnLocationsForPlayer(playerIdx, 2, pawnLocationX[106], pawnLocationY[106]);
                parState.setPawnLocationsForPlayer(playerIdx, 3, pawnLocationX[107], pawnLocationY[107]);
                break;
            //yellow
            case 2:
                parState.setPawnLocationsForPlayer(playerIdx, 0, pawnLocationX[108], pawnLocationY[108]);
                parState.setPawnLocationsForPlayer(playerIdx, 1, pawnLocationX[109], pawnLocationY[109]);
                parState.setPawnLocationsForPlayer(playerIdx, 2, pawnLocationX[110], pawnLocationY[110]);
                parState.setPawnLocationsForPlayer(playerIdx, 3, pawnLocationX[111], pawnLocationY[111]);
                break;
            //green
            case 3:
                parState.setPawnLocationsForPlayer(playerIdx, 0, pawnLocationX[112], pawnLocationY[112]);
                parState.setPawnLocationsForPlayer(playerIdx, 1, pawnLocationX[113], pawnLocationY[113]);
                parState.setPawnLocationsForPlayer(playerIdx, 2, pawnLocationX[114], pawnLocationY[114]);
                parState.setPawnLocationsForPlayer(playerIdx, 3, pawnLocationX[115], pawnLocationY[115]);
                break;
        }
    }

    public void setPawnLocation(int playerIdx, int pawnIdx, boolean createBlockade) {
        // uses the locations array that can fit two pawns within one given location
        if (createBlockade) {

        }
        // uses the normal locations array list that centers the single pawn in
        // the given location
        else {

        }
    }

    public void resetPawnLocation(int playerIdx, int pawnIdx) {
        //ToDo Add in the locations of the given player and reset the pawn that was eaten
        switch (playerIdx) {
            //red
            case 0:
                switch (pawnIdx) {
                    case 0:
                        parState.setPawnLocationsForPlayer(playerIdx, 0, pawnLocationX[100], pawnLocationY[100]);
                        break;
                    case 1:
                        parState.setPawnLocationsForPlayer(playerIdx, 1, pawnLocationX[101], pawnLocationY[101]);
                        break;
                    case 2:
                        parState.setPawnLocationsForPlayer(playerIdx, 2, pawnLocationX[102], pawnLocationY[102]);
                        break;
                    case 3:
                        parState.setPawnLocationsForPlayer(playerIdx, 3, pawnLocationX[103], pawnLocationY[103]);
                        break;
                }
            //blue
            case 1:
                switch (pawnIdx) {
                    case 0:
                        parState.setPawnLocationsForPlayer(playerIdx, 0, pawnLocationX[104], pawnLocationY[104]);
                        break;
                    case 1:
                        parState.setPawnLocationsForPlayer(playerIdx, 1, pawnLocationX[105], pawnLocationY[105]);
                        break;
                    case 2:
                        parState.setPawnLocationsForPlayer(playerIdx, 2, pawnLocationX[106], pawnLocationY[106]);
                        break;
                    case 3:
                        parState.setPawnLocationsForPlayer(playerIdx, 3, pawnLocationX[107], pawnLocationY[107]);
                        break;
                }
            //yellow
            case 2:
                switch (pawnIdx) {
                    case 0:
                        parState.setPawnLocationsForPlayer(playerIdx, 0, pawnLocationX[108], pawnLocationY[108]);
                        break;
                    case 1:
                        parState.setPawnLocationsForPlayer(playerIdx, 1, pawnLocationX[109], pawnLocationY[109]);
                        break;
                    case 2:
                        parState.setPawnLocationsForPlayer(playerIdx, 2, pawnLocationX[110], pawnLocationY[110]);
                        break;
                    case 3:
                        parState.setPawnLocationsForPlayer(playerIdx, 3, pawnLocationX[111], pawnLocationY[111]);
                        break;
                }
            //green
            case 3:
                switch (pawnIdx) {
                    case 0:
                        parState.setPawnLocationsForPlayer(playerIdx, 0, pawnLocationX[112], pawnLocationY[112]);
                        break;
                    case 1:
                        parState.setPawnLocationsForPlayer(playerIdx, 1, pawnLocationX[113], pawnLocationY[113]);
                        break;
                    case 2:
                        parState.setPawnLocationsForPlayer(playerIdx, 2, pawnLocationX[114], pawnLocationY[114]);
                        break;
                    case 3:
                        parState.setPawnLocationsForPlayer(playerIdx, 3, pawnLocationX[115], pawnLocationY[115]);
                        break;
                }
        }
    }
}
