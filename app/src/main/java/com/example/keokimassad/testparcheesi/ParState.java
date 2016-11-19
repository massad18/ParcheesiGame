package com.example.keokimassad.testparcheesi;

import java.util.Hashtable;

/**
 * Created by KeokiMassad on 11/15/16.
 */

public class ParState {

    ParPlayer player0 = new ParPlayer();
    ParPlayer player1 = new ParPlayer();
    ParPlayer player2 = new ParPlayer();
    ParPlayer player3 = new ParPlayer();
    protected ParPlayer[] players = {player0, player1, player2, player3};
    private int count;

    // up to down
    private int[] hori = {0, 81, 150, 217, 284, 358, 424, 491, 558, 626, 694, 763, 833, 898, 967, 1036, 1102, 1165, 1235, 1300, 1366};
    // left to right
    private int[] vert = {0, 82, 144, 205, 271, 337, 398, 464, 519, 578, 637, 696, 761, 820, 877, 939, 1003, 1066, 1133, 1191, 1261};

    private Hashtable<Integer, Rect> boardPieces = new Hashtable<>();
    private Hashtable<Integer, Rect> homebasePieces = new Hashtable<>();
    private Hashtable<Integer, Rect> safezonePieces = new Hashtable<>();

    void ParState() {
        count = 0;
    }

    protected void initBoardPieces() {
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
    }

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

    protected int containsInRect (float x, float y) {
        for (int i = 1; i < 69; i++) {
            Rect rect = boardPieces.get(i);
            if (rect != null) {
                if (rect.contains(x, y)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
