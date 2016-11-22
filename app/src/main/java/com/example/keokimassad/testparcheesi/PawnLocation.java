package com.example.keokimassad.testparcheesi;

/**
 * Created by KeokiMassad on 11/21/16.
 */

public class PawnLocation {

    private int xPos;
    private int yPos;

    // the initial positions are #100-115
    // the homebase coordinates are #116 - 131
    private int[] pawnLocationx = {833, 833, 833, 798, 866, 933, 1002, 1070, 1134, 1200, 1268, 1333,
            1333, 1333, 1268, 1200, 1134, 1070, 1002, 933, 866, 798, 833, 833, 833, 833, 833, 833,
            833, 694, 558, 558, 558, 558, 558, 558, 558, 592, 525, 456, 391, 321, 251, 184, 116, 41,
            41, 41, 116, 184, 251, 321, 391, 456, 525, 592, 558, 558, 558, 558, 558, 558,  558, 694,
            833, 833, 833, 833, 694, 694, 694, 694, 694, 694, 694, 0, 116, 184, 251, 321, 391,
            456, 0, 694, 694, 694, 694, 694, 694, 694, 0, 933, 1002, 1070, 1134, 1200, 1268, 0, 967,
            967, 967, 967, 81, 150, 217, 284, 424, 424, 424, 424, 1102, 1165, 1235, 1300,
            660, 660, 694, 729, 660, 626, 592, 592, 729, 729, 694, 660, 729, 763, 798, 798};
    private int[] getPawnLocationBlockade1x = {};
    private int[] pawnLocationBlockade2x = {};

    // the initial positions are #100-115
    // the homebase coordinates are #116 - 131
    private int[] pawnLocationy = {971, 908, 791, 729, 761, 761, 761, 761, 761, 761, 761, 637, 519,
            519, 519, 519, 519, 519, 519, 549, 492, 431, 368, 304, 238, 175, 113, 41, 41, 41, 113,
            175, 238, 304, 368, 431, 492, 549, 519, 519, 519, 519, 519, 519, 519, 637, 761, 761,
            761, 761, 761, 761, 761, 729, 791, 908, 971, 1035, 1100, 1162, 1226, 1226, 1226, 1162,
            1100, 1035, 1162, 1100, 1035, 971, 908, 791, 0, 637, 637, 637, 637, 637, 637, 637, 0,
            113, 175, 238, 304, 368, 431, 492, 0, 637, 637, 637, 637, 637, 637, 637, 0, 1191, 1133, 1066,
            1003, 877, 877, 877, 877, 82, 144, 205, 271, 398, 398, 398, 398, 667, 696, 729, 729,
            608, 608, 637, 667, 608, 578, 549, 549, 667, 667, 637, 608};
    private int[] getPawnLocationBlockade1y = {};
    private int[] pawnLocationBlockade2y = {};

    public PawnLocation(int playerIdx) {
        // initialize the locations of the pawns

        //ToDo Add in the locations of the given player and initialize their location to their given starting locations
        switch (playerIdx) {
            //red
            case 0:
                // a sample code that could be what it looks like
                players[playerIdx].location[0].setX(pawnLocationx[100]);
                players[playerIdx].location[0].setY(pawnLocationy[100]);
                players[playerIdx].location[1].setX(pawnLocationy[101]);
                players[playerIdx].location[1].setY(pawnLocationy[101]);
                players[playerIdx].location[2].setX(pawnLocationy[102]);
                players[playerIdx].location[2].setY(pawnLocationy[102]);
                players[playerIdx].location[3].setX(pawnLocationy[103]);
                players[playerIdx].location[3].setY(pawnLocationy[103]);
                break;
            //blue
            case 1:
                players[playerIdx].location[0].setX(pawnLocationx[104]);
                players[playerIdx].location[0].setY(pawnLocationy[104]);
                players[playerIdx].location[1].setX(pawnLocationy[101]);
                players[playerIdx].location[1].setY(pawnLocationy[105]);
                players[playerIdx].location[2].setX(pawnLocationy[106]);
                players[playerIdx].location[2].setY(pawnLocationy[106]);
                players[playerIdx].location[3].setX(pawnLocationy[107]);
                players[playerIdx].location[3].setY(pawnLocationy[107]);
                break;
            //yellow
            case 2:
                players[playerIdx].location[0].setX(pawnLocationx[108]);
                players[playerIdx].location[0].setY(pawnLocationy[108]);
                players[playerIdx].location[1].setX(pawnLocationy[109]);
                players[playerIdx].location[1].setY(pawnLocationy[109]);
                players[playerIdx].location[2].setX(pawnLocationy[110]);
                players[playerIdx].location[2].setY(pawnLocationy[110]);
                players[playerIdx].location[3].setX(pawnLocationy[111]);
                players[playerIdx].location[3].setY(pawnLocationy[111]);
                break;
            //green
            case 3:
                players[playerIdx].location[0].setX(pawnLocationx[112]);
                players[playerIdx].location[0].setY(pawnLocationy[112]);
                players[playerIdx].location[1].setX(pawnLocationy[113]);
                players[playerIdx].location[1].setY(pawnLocationy[113]);
                players[playerIdx].location[2].setX(pawnLocationy[114]);
                players[playerIdx].location[2].setY(pawnLocationy[114]);
                players[playerIdx].location[3].setX(pawnLocationy[115]);
                players[playerIdx].location[3].setY(pawnLocationy[115]);
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
                // a sample code that could be what it looks like
                switch (pawnIdx) {
                    case 0:
                        players[playerIdx].location[0].setX(pawnLocationx[100]);
                        players[playerIdx].location[0].setY(pawnLocationy[100]);
                        break;
                    case 1:
                        players[playerIdx].location[1].setX(pawnLocationy[101]);
                        players[playerIdx].location[1].setY(pawnLocationy[101]);
                        break;
                    case 2:
                        players[playerIdx].location[2].setX(pawnLocationy[102]);
                        players[playerIdx].location[2].setY(pawnLocationy[102]);
                        break;
                    case 3:
                        players[playerIdx].location[3].setX(pawnLocationy[103]);
                        players[playerIdx].location[3].setY(pawnLocationy[103]);
                        break;
                }
            //blue
            case 1:
                switch (pawnIdx) {
                    case 0:
                        players[playerIdx].location[0].setX(pawnLocationx[104]);
                        players[playerIdx].location[0].setY(pawnLocationy[104]);
                        break;
                    case 1:
                        players[playerIdx].location[1].setX(pawnLocationy[105]);
                        players[playerIdx].location[1].setY(pawnLocationy[105]);
                        break;
                    case 2:
                        players[playerIdx].location[2].setX(pawnLocationy[106]);
                        players[playerIdx].location[2].setY(pawnLocationy[106]);
                        break;
                    case 3:
                        players[playerIdx].location[3].setX(pawnLocationy[107]);
                        players[playerIdx].location[3].setY(pawnLocationy[107]);
                        break;
                }
            //yellow
            case 2:
                switch (pawnIdx) {
                    case 0:
                        players[playerIdx].location[0].setX(pawnLocationx[108]);
                        players[playerIdx].location[0].setY(pawnLocationy[108]);
                        break;
                    case 1:
                        players[playerIdx].location[1].setX(pawnLocationy[109]);
                        players[playerIdx].location[1].setY(pawnLocationy[109]);
                        break;
                    case 2:
                        players[playerIdx].location[2].setX(pawnLocationy[110]);
                        players[playerIdx].location[2].setY(pawnLocationy[110]);
                        break;
                    case 3:
                        players[playerIdx].location[3].setX(pawnLocationy[111]);
                        players[playerIdx].location[3].setY(pawnLocationy[111]);
                        break;
                }
            //green
            case 3:
                switch (pawnIdx) {
                    case 0:
                        players[playerIdx].location[0].setX(pawnLocationx[112]);
                        players[playerIdx].location[0].setY(pawnLocationy[112]);
                        break;
                    case 1:
                        players[playerIdx].location[1].setX(pawnLocationy[113]);
                        players[playerIdx].location[1].setY(pawnLocationy[113]);
                        break;
                    case 2:
                        players[playerIdx].location[2].setX(pawnLocationy[114]);
                        players[playerIdx].location[2].setY(pawnLocationy[114]);
                        break;
                    case 3:
                        players[playerIdx].location[3].setX(pawnLocationy[115]);
                        players[playerIdx].location[3].setY(pawnLocationy[115]);
                        break;
                }
        }
    }
}
