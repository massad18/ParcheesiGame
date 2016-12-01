package com.example.keokimassad.testparcheesi;

/**
 * Created by KeokiMassad on 11/21/16.
 */

public class PawnLocation {

    private int xPos;
    private int yPos;

    // the initial positions are #100-115
    // the homebase coordinates are #116 - 131
    protected int[] pawnLocationX = {/*0*/ 833, 833, 833, 798, 866, 933, 1002, 1069, 1134, 1200,
            /*10*/ 1268, 1333, 1333, 1333, 1268, 1200, 1134, 1069, 1002, 933, /*20*/ 866, 798, 833,
            833, 833, 833, 833, 833, 833, 694, /*30*/ 558, 558, 558, 558, 558, 558, 558, 592, 525,
            458, /*40*/ 391, 321, 251, 184, 116, 41, 41, 41, 116, 184, /*50*/ 251, 321, 391, 458,
            525, 592, 558, 558, 558, 558, /*60*/ 558, 558, 558, 694, 833, 833, 833, 833,
            /* RedSafeZone */ 694, 694, 694, 694, 694, 694, 694, /* RedHomebase OMIT */ 0,
            /* BlueSafeZone */ 116, 184, 251, 321, 391, 458, 525, /* BlueHomeBase OMIT */ 0,
            /* YellowSafeZone */ 694, 694, 694, 694, 694, 694, 694, /* YellowHomeBase OMIT */ 0,
            /* GreenSafeZone */ 1268, 1200, 1134, 1069, 1002, 933, 866, /* GreenHomeBase OMIT */ 0,
            /* RedStart */ 967, 967, 967, 967, /* BlueStart */ 284, 217, 150, 81,
            /* YellowStart */ 424, 424, 424, 424, /* GreenStart */ 1102, 1165, 1235, 1300,
            /* RedHomeBase */ 660, 660, 694, 729, /* BlueHomeBase */ 660, 626, 592, 592,
            /* YellowHomeBase */ 729, 729, 694, 660, /* GreenHomeBase */ 729, 763, 798, 798};
    private int[] pawnLocationBlockade1x = {/*0*/ 843, 843, 843, 843, 866, 933, 1002, 1069, 1134,
            1200, /*10*/ 1268, 1333, 1333, 1333, 1268, 1200, 1134, 1069, 1002, 933, /*20*/ 866, 843,
            843, 843, 843, 843, 843, 843, 843, 706, /*30*/ 570, 570, 570, 570, 570, 570, 570, 570,
            525, 458, /*40*/ 391, 321, 251, 184, 116, 41, 41, 41, 116, 184, /*50*/ 251, 321, 391,
            458, 525, 570, 570, 570, 570, 570, /*60*/ 570, 570, 570, 706, 843, 843, 843, 843,
            /*RedSafeZone*/ 706, 706, 706, 706, 706, 706, 706, /*RedHomeBase OMIT*/ 0,
            /*BlueSafeZone*/ 116, 184, 251, 321, 391, 458, 525, /*BlueHomeBase OMIT*/ 0,
            /*YellowSafeZone*/ 706, 706, 706, 706, 706, 706, 706, /*YellowHomeBase OMIT*/ 0,
            /*GreenSafeZone*/ 1268, 1200, 1134, 1069, 1002, 933, 866, /*GreenHomeBase OMIT*/ 0}; 
    private int[] pawnLocationBlockade2x = {/*0*/ 888, 888, 888, 888, 866, 933, 1002, 1069, 1134,
            1200, /*10*/ 1268, 1333, 1333, 1333, 1268, 1200, 1134, 1069, 1002, 933, /*20*/ 866, 888,
            888, 888, 888, 888, 888, 888, 888, 752, /*30*/ 615, 615, 615, 615, 615, 615, 615, 615,
            525, 458, /*40*/ 391, 321, 251, 184, 116, 41, 41, 41, 116, 184, /*50*/ 251, 321, 391,
            458, 525, 615, 615, 615, 615, 615, /*60*/ 615, 615, 615, 752, 888, 888, 888, 888,
            /*RedSafeZone*/ 752, 752, 752, 752, 752, 752, 752, /*RedHomeBase OMIT*/ 0,
            /*BlueSafeZone*/ 116, 184, 251, 321, 391, 458, 525, /*BlueHomeBase OMIT*/ 0,
            /*YellowSafeZone*/ 752, 752, 752, 752, 752, 752, 752, /*YellowHomeBase OMIT*/ 0,
            /*GreenSafeZone*/ 1268, 1200, 1134, 1069, 1002, 933, 866, /*GreenHomeBase OMIT*/ 0};


    // the initial positions are #100-115
    // the homebase coordinates are #116 - 131
    protected int[] pawnLocationY = {/*0*/ 971, 908, 849, 791, 729, 761, 761, 761, 761, 761,
            /*10*/ 761, 761, 637, 519, 519, 519, 519, 519, 519, 519, /*20*/ 549, 492, 431, 368, 304,
            238, 175, 113, 41, 41, /*30*/ 41, 113, 175, 238, 304, 368, 431, 492, 549, 519,
            /*40*/ 519, 519, 519, 519, 519, 519, 637, 761, 761, 761, /*50*/ 761, 761, 761, 761, 729,
            791, 849, 908, 971, 1035, /*60*/ 1100, 1162, 1226, 1226, 1226, 1162, 1100, 1035,
            /* RedSafeZone */ 1162, 1100, 1035, 971, 908, 849, 791, /* RedHomeBase OMIT */ 0,
            /* BlueSafeZone */ 637, 637, 637, 637, 637, 637, 637, /* BlueHomeBase OMIT */ 0,
            /* YellowSafeZone */ 113, 175, 238, 304, 368, 431, 492, /* YellowHomeBase OMIT */ 0,
            /* GreenSafeZone */ 637, 637, 637, 637, 637, 637, 637, /* GreenHomeBase OMIT */ 0,
            /* RedStart */ 1003, 1066, 1133, 1191, /* BlueStart */ 877, 877, 877, 877,
            /* YellowStart */ 271, 205, 144, 82, /* GreenStart */ 398, 398, 398, 398,
            /* RedHomeBase */ 667, 696, 729, 729, /* BlueHomeBase */ 608, 608, 637, 667,
            /* YellowHomeBase */ 608, 578, 549, 549, /* GreenHomeBase */ 667, 667, 637, 608};
    private int[] pawnLocationBlockade1y = {/*0*/ 971, 908, 849, 791, 769, 769, 769, 769, 769, 769,
            /*10*/ 769, 769, 648, 531, 531, 531, 531, 531, 531, 531, /*20*/ 531, 492, 431, 368, 304,
            238, 175, 113, 41, 41, /*30*/ 41, 113, 175, 238, 304, 368, 431, 492, 531, 531,
            /*40*/ 531, 531, 531, 531, 531, 531, 648, 769, 769, 769, /*50*/ 769, 769, 769, 769, 769,
            791, 849, 908, 971, 1035, /*60*/ 1100, 1162, 1226, 1226, 1226, 1162, 1100, 1035,
            /* RedSafeZone */ 1162, 1100, 1035, 971, 908, 849, 791, /* RedHomeBase OMIT */ 0,
            /* BlueSafeZone */ 648, 648, 648, 648, 648, 648, 648, /* BlueHomeBase OMIT */ 0,
            /* YellowSafeZone */ 82, 144, 205, 271, 337, 398, 464, /* YellowHomeBase OMIT */ 0,
            /* GreenSafeZone */ 648, 648, 648, 648, 648, 648, 648, /* GreenHomeBase OMIT */ 0};
    private int[] pawnLocationBlockade2y = {/*10*/ 971, 908, 849, 791, 809, 809, 809, 809, 809, 809,
            /*10*/ 809, 809, 689, 569, 569, 569, 569, 569, 569, 569, /*20*/ 569, 492, 431, 368, 304,
            238, 175, 113, 41, 41, /*30*/ 41, 113, 175, 238, 304, 368, 431, 492, 569, 569,
            /*40*/ 569, 569, 569, 569, 569, 569, 689, 809, 809, 809, /*50*/ 809, 809, 809, 809, 809,
            791, 849, 908, 971, 1035, /*60*/ 1100, 1162, 1226, 1226, 1226, 1162, 1100, 1035,
            /* RedSafeZone */ 1162, 1100, 1035, 971, 908, 849, 791, /* RedHomeBase OMIT */ 0,
            /* BlueSafeZone */ 689, 689, 689, 689, 689, 689, 689, /* BlueHomeBase OMIT */ 0,
            /* YellowSafeZone */ 82, 144, 205, 271, 337, 398, 464, /* YellowHomeBase OMIT */ 0,
            /* GreenSafeZone */ 689, 689, 689, 689, 689, 689, 689, /* GreenHomeBase OMIT */ 0};

    public PawnLocation() {}

    public void setPawnLocation(int playerIdx, int pawnIdx, int locationIdx) {
        // uses the normal locations array list that centers the single pawn in
        // the given location

    }

    public void setPawnLocation (int playerIdx, int pawnIdx1, int pawnIdx2, int locationIdx) {
        // uses the locations array that can fit two pawns within one given location

    }
}
