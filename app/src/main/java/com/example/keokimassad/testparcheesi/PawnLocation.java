package com.example.keokimassad.testparcheesi;

/**
 * Created by KeokiMassad on 11/21/16.
 */

public class PawnLocation {

    private int xPos;
    private int yPos;

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

    public void setPawnLocation(int playerIdx, int pawnIdx, int locationIdx) {
        // uses the normal locations array list that centers the single pawn in
        // the given location

    }

    public void setPawnLocation (int playerIdx, int pawnIdx1, int pawnIdx2, int locationIdx) {
        // uses the locations array that can fit two pawns within one given location

    }
}
