package com.example.keokimassad.testparcheesi;

import android.util.Log;

import java.util.Random;

import game.GameComputerPlayer;
import game.infoMsg.GameInfo;

/**
 * Created by AvayaBhattarai on 12/6/16.
 * Will find out which move will cause the pawn to move the furthest and wil move that pawn
 * Also will eat other player's pawns if possible
 */

public class ParComputerPlayerH extends GameComputerPlayer {

    ParLocalGame parLocalGame = new ParLocalGame();
    // May need to change to actual player numbers
    //int playerNumber = playerNum;
    int playerNumber;
    private int[] location = new int[4];
    ParRollAction rollAction;
    ParSelectAction selectAction;
    ParUseDieAction useDieAction;
    ParMoveAction moveAction;
    ParCheckLegalMoveAction checkLegalMoveAction;
    ParState parState;

    // the booleans tell the code if a smart move was found for that type of action
    boolean smartMoveStartingZone = false;
    boolean smartMoveSafeZone = false;
    boolean smartMoveEating = false;
    boolean smartMove = false;

    // the instance variables that hold the important information about the smart move,
    // if one was found
    int smartPawn = -1;
    String smartDieValue = null;
    int smartDieValueIndex = -1;
    int smartDieValueNumber = -1;

    // the instance variables that hold the important information about a normal move around the
    // board when no smart move is found
    int normPawn = -1;
    String normDieValue = null;
    int normDieValueIndex = -1;
    int normDieValueNumber = -1;
    boolean legalMove = false;
    boolean checkNormalMoves = false;

    //locations of pawns
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

    public ParComputerPlayerH(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        // May need to change to actual player numbers
        // updating which computer player's move it is
        for (int i = 0; i < allPlayerNames.length; i++) {
            if (allPlayerNames[i].equals(name)) {
                playerNumber = i;
            }
        }

        if (!(info instanceof ParState)) return;
        ParState parState = (ParState) info;

        if (parState.getPlayerTurn() == playerNumber) {
            //roll dice
            if (parState.getCheckLegalMoveActionMade() == true) {
                if (parState.getCurrentSubstage() == parState.Roll) {
                    rollAction = new ParRollAction(this);
                    sleep(500);
                    game.sendAction(rollAction);
                    sleep(2000);
                }
                //find out if a pawn needs to be moved
                else if (parState.getCurrentSubstage() == parState.Begin_Move || parState.getCurrentSubstage() == parState.Mid_Move) {
                    if (smartMove == false) {
                        //find each original pawn location
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
                        // get the original rectangle of all of the pawns
                        // if the rectangle is -1... then it is in the starting locations
                        for (int j = 0; j < 4; j++) {
                            player0Rect[j] = parState.getRect(player0LocationsX[j], player0LocationsY[j]);
                            player1Rect[j] = parState.getRect(player1LocationsX[j], player1LocationsY[j]);
                            player2Rect[j] = parState.getRect(player2LocationsX[j], player2LocationsY[j]);
                            player3Rect[j] = parState.getRect(player3LocationsX[j], player3LocationsY[j]);
                        }

                        // check for a smart move, iterated over all of the pawns for the player that can move
                        StartingZoneLoop:
                        for (int i = 0; i < 4; i++) {
                            // check if the player is able to take their pawn out of the starting zone
                            switch (parState.getPlayerTurn()) {
                                case 0:
                                    if (parState.getLegalMoves("dieValue1", i) == 0) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValue1";
                                        break StartingZoneLoop;
                                    } else if (parState.getLegalMoves("dieValue2", i) == 0) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValue2";
                                        break StartingZoneLoop;
                                    } else if (parState.getLegalMoves("dieValueTotal", i) == 0) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValueTotal";
                                        break StartingZoneLoop;
                                    }
                                    break;
                                case 1:
                                    if (parState.getLegalMoves("dieValue1", i) == 51) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValue1";
                                        break StartingZoneLoop;
                                    } else if (parState.getLegalMoves("dieValue2", i) == 51) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValue2";
                                        break StartingZoneLoop;
                                    } else if (parState.getLegalMoves("dieValueTotal", i) == 51) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValueTotal";
                                        break StartingZoneLoop;
                                    }
                                    break;
                                case 2:
                                    if (parState.getLegalMoves("dieValue1", i) == 34) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValue1";
                                        break StartingZoneLoop;
                                    } else if (parState.getLegalMoves("dieValue2", i) == 34) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValue2";
                                        break StartingZoneLoop;
                                    } else if (parState.getLegalMoves("dieValueTotal", i) == 34) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValueTotal";
                                        break StartingZoneLoop;
                                    }
                                    break;
                                case 3:
                                    if (parState.getLegalMoves("dieValue1", i) == 17) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValue1";
                                        break StartingZoneLoop;
                                    } else if (parState.getLegalMoves("dieValue2", i) == 17) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValue2";
                                        break StartingZoneLoop;
                                    } else if (parState.getLegalMoves("dieValueTotal", i) == 17) {
                                        smartMoveStartingZone = true;
                                        smartPawn = i;
                                        smartDieValue = "dieValueTotal";
                                        break StartingZoneLoop;
                                    }
                                    break;
                            }
                        }

                        if (smartMoveStartingZone == false) {
                            SafeZoneLoop:
                            for (int i = 0; i < 4; i++) {
                                // check if the player is able to move into their safe zone/home base
                                switch (parState.getPlayerTurn()) {
                                    case 0:
                                        if (parState.getLegalMoves("dieValue1", i) >= 68 && parState.getLegalMoves("dieValue1", i) <= 75) {
                                            if (smartMoveSafeZone == false) {
                                                // if there is no smartMoveSafeZone, then set it to be so
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValue1";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValue1", i)) {
                                                // if there is and the new value is further along in
                                                // the safe zone than the previously determined legal
                                                // move that moves into the safe zone, set the values
                                                // to the new, greater value
                                                smartPawn = i;
                                                smartDieValue = "dieValue1";
                                            }
                                        } else if (parState.getLegalMoves("dieValue2", i) >= 68 && parState.getLegalMoves("dieValue2", i) <= 75) {
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValue2";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValue2", i)) {
                                                smartPawn = i;
                                                smartDieValue = "dieValue2";
                                            }
                                        } else if (parState.getLegalMoves("dieValueTotal", i) >= 68 && parState.getLegalMoves("dieValueTotal", i) <= 75) {
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValueTotal";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValueTotal", i)) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValueTotal";
                                            }
                                        }
                                        break;
                                    case 1:
                                        if (parState.getLegalMoves("dieValue1", i) >= 76 && parState.getLegalMoves("dieValue1", i) <= 83) {
                                            // check if the new value is further along in the safezone than the previously determined legal move that moves into the safe zone
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValue1";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValue1", i)) {
                                                smartPawn = i;
                                                smartDieValue = "dieValue1";
                                            }
                                        } else if (parState.getLegalMoves("dieValue2", i) >= 76 && parState.getLegalMoves("dieValue2", i) <= 83) {
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValue2";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValue2", i)) {
                                                smartPawn = i;
                                                smartDieValue = "dieValue2";
                                            }
                                        } else if (parState.getLegalMoves("dieValueTotal", i) >= 76 && parState.getLegalMoves("dieValueTotal", i) <= 83) {
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValueTotal";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValueTotal", i)) {
                                                smartPawn = i;
                                                smartDieValue = "dieValueTotal";
                                            }
                                        }
                                        break;
                                    case 2:
                                        if (parState.getLegalMoves("dieValue1", i) >= 84 && parState.getLegalMoves("dieValue1", i) <= 91) {
                                            // check if the new value is further along in the safezone than the previously determined legal move that moves into the safe zone
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValue1";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValue1", i)) {
                                                smartPawn = i;
                                                smartDieValue = "dieValue1";
                                            }
                                        } else if (parState.getLegalMoves("dieValue2", i) >= 84 && parState.getLegalMoves("dieValue2", i) <= 91) {
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValue2";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValue2", i)) {
                                                smartPawn = i;
                                                smartDieValue = "dieValue2";
                                            }
                                        } else if (parState.getLegalMoves("dieValueTotal", i) >= 84 && parState.getLegalMoves("dieValueTotal", i) <= 91) {
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValueTotal";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValueTotal", i)) {
                                                smartPawn = i;
                                                smartDieValue = "dieValueTotal";
                                            }
                                        }
                                        break;
                                    case 3:
                                        if (parState.getLegalMoves("dieValue1", i) >= 92 && parState.getLegalMoves("dieValue1", i) <= 99) {
                                            // check if the new value is further along in the safezone than the previously determined legal move that moves into the safe zone
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValue1";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValue1", i)) {
                                                smartPawn = i;
                                                smartDieValue = "dieValue1";
                                            }
                                        } else if (parState.getLegalMoves("dieValue2", i) >= 92 && parState.getLegalMoves("dieValue2", i) <= 99) {
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValue2";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValue2", i)) {
                                                smartPawn = i;
                                                smartDieValue = "dieValue2";
                                            }
                                        } else if (parState.getLegalMoves("dieValueTotal", i) >= 92 && parState.getLegalMoves("dieValueTotal", i) <= 99) {
                                            if (smartMoveSafeZone == false) {
                                                smartMoveSafeZone = true;
                                                smartPawn = i;
                                                smartDieValue = "dieValueTotal";
                                            } else if (parState.getLegalMoves(smartDieValue, smartPawn) < parState.getLegalMoves("dieValueTotal", i)) {
                                                smartPawn = i;
                                                smartDieValue = "dieValueTotal";
                                            }
                                        }
                                        break;
                                }
                            }

                            if (smartMoveSafeZone == false) {
                                // iterate over all of the pawns for the player that can move
                                for (int i = 0; i < 4; i++) {
                                    //ToDo: check if you can eat another pawn...
                                    // check if the player who is moving has a legal move that allows
                                    // them to eat another players pawn
                                    switch (parState.getPlayerTurn()) {
                                        case 0:
                                            // iterate over the other players pawns
                                            for (int j = 0; j < 4; j++) {
                                                // check the legal moves against player 1
                                                if (parState.getLegalMoves("dieValue1", i) == player1Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 63 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player1Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 63 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player1Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 63 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }

                                                // check the legal moves against player 2
                                                else if (parState.getLegalMoves("dieValue1", i) == player2Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 63 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player2Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 63 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player2Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 63 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }

                                                // check the legal moves against player 3
                                                else if (parState.getLegalMoves("dieValue1", i) == player3Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 63 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player3Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 63 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player3Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 63 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }
                                            }
                                            break;
                                        case 1:
                                            // iterate over the other players pawns
                                            for (int j = 0; j < 4; j++) {
                                                // check the legal moves against player 0
                                                if (parState.getLegalMoves("dieValue1", i) == player0Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 46 && parState.getLegalMoves(smartDieValue, smartPawn) >= 51 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 46 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 51 && parState.getLegalMoves("dieValue1", i) <= 67 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player0Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 46 && parState.getLegalMoves(smartDieValue, smartPawn) >= 51 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 46 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 51 && parState.getLegalMoves("dieValue2", i) <= 67 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player0Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 46 && parState.getLegalMoves(smartDieValue, smartPawn) >= 51 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 46 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 51 && parState.getLegalMoves("dieValueTotal", i) <= 67 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }

                                                // check the legal moves against player 2
                                                else if (parState.getLegalMoves("dieValue1", i) == player2Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 46 && parState.getLegalMoves(smartDieValue, smartPawn) >= 51 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 46 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 51 && parState.getLegalMoves("dieValue1", i) <= 67 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player2Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 46 && parState.getLegalMoves(smartDieValue, smartPawn) >= 51 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 46 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 51 && parState.getLegalMoves("dieValue2", i) <= 67 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player2Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 46 && parState.getLegalMoves(smartDieValue, smartPawn) >= 51 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 46 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 51 && parState.getLegalMoves("dieValueTotal", i) <= 67 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }

                                                // check the legal moves against player 3
                                                else if (parState.getLegalMoves("dieValue1", i) == player3Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 46 && parState.getLegalMoves(smartDieValue, smartPawn) >= 51 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 46 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 51 && parState.getLegalMoves("dieValue1", i) <= 67 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player3Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 46 && parState.getLegalMoves(smartDieValue, smartPawn) >= 51 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 46 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 51 && parState.getLegalMoves("dieValue2", i) <= 67 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player3Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 46 && parState.getLegalMoves(smartDieValue, smartPawn) >= 51 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 46 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 51 && parState.getLegalMoves("dieValueTotal", i) <= 67 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }
                                            }
                                            break;
                                        case 2:
                                            // iterate over the other players pawns
                                            for (int j = 0; j < 4; j++) {
                                                // check the legal moves against player 0
                                                if (parState.getLegalMoves("dieValue1", i) == player0Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 29 && parState.getLegalMoves(smartDieValue, smartPawn) >= 34 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 29 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 34 && parState.getLegalMoves("dieValue1", i) <= 67 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player0Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 29 && parState.getLegalMoves(smartDieValue, smartPawn) >= 34 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 29 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 34 && parState.getLegalMoves("dieValue2", i) <= 67 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player0Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 29 && parState.getLegalMoves(smartDieValue, smartPawn) >= 34 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 29 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 34 && parState.getLegalMoves("dieValueTotal", i) <= 67 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }

                                                // check the legal moves against player 1
                                                else if (parState.getLegalMoves("dieValue1", i) == player1Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 29 && parState.getLegalMoves(smartDieValue, smartPawn) >= 34 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 29 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 34 && parState.getLegalMoves("dieValue1", i) <= 67 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player1Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 29 && parState.getLegalMoves(smartDieValue, smartPawn) >= 34 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 29 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 34 && parState.getLegalMoves("dieValue2", i) <= 67 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player1Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 29 && parState.getLegalMoves(smartDieValue, smartPawn) >= 34 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 29 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 34 && parState.getLegalMoves("dieValueTotal", i) <= 67 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }

                                                // check the legal moves against player 3
                                                else if (parState.getLegalMoves("dieValue1", i) == player3Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 29 && parState.getLegalMoves(smartDieValue, smartPawn) >= 34 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 29 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 34 && parState.getLegalMoves("dieValue1", i) <= 67 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player3Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 29 && parState.getLegalMoves(smartDieValue, smartPawn) >= 34 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 29 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 34 && parState.getLegalMoves("dieValue2", i) <= 67 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player3Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 29 && parState.getLegalMoves(smartDieValue, smartPawn) >= 34 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 29 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 34 && parState.getLegalMoves("dieValueTotal", i) <= 67 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }
                                            }
                                            break;
                                        case 3:
                                            // iterate over the other players pawns
                                            for (int j = 0; j < 4; j++) {
                                                // check the legal moves against player 0
                                                if (parState.getLegalMoves("dieValue1", i) == player0Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 12 && parState.getLegalMoves(smartDieValue, smartPawn) >= 17 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 12 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 17 && parState.getLegalMoves("dieValue1", i) <= 67 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player0Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 12 && parState.getLegalMoves(smartDieValue, smartPawn) >= 17 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 12 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 17 && parState.getLegalMoves("dieValue2", i) <= 67 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player0Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 12 && parState.getLegalMoves(smartDieValue, smartPawn) >= 17 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 12 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 17 && parState.getLegalMoves("dieValueTotal", i) <= 67 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }

                                                // check the legal moves against player 1
                                                else if (parState.getLegalMoves("dieValue1", i) == player1Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 12 && parState.getLegalMoves(smartDieValue, smartPawn) >= 17 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 12 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 17 && parState.getLegalMoves("dieValue1", i) <= 67 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player1Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 12 && parState.getLegalMoves(smartDieValue, smartPawn) >= 17 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 12 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 17 && parState.getLegalMoves("dieValue2", i) <= 67 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player1Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 12 && parState.getLegalMoves(smartDieValue, smartPawn) >= 17 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 12 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 17 && parState.getLegalMoves("dieValueTotal", i) <= 67 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }

                                                // check the legal moves against player 2
                                                else if (parState.getLegalMoves("dieValue1", i) == player2Rect[j] && parState.getLegalMoves("dieValue1", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 12 && parState.getLegalMoves(smartDieValue, smartPawn) >= 17 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 0 && parState.getLegalMoves("dieValue1", i) <= 12 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    } else if (parState.getLegalMoves("dieValue1", i) >= 17 && parState.getLegalMoves("dieValue1", i) <= 67 && parState.getLegalMoves("dieValue1", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue1";
                                                    }
                                                } else if (parState.getLegalMoves("dieValue2", i) == player2Rect[j] && parState.getLegalMoves("dieValue2", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 12 && parState.getLegalMoves(smartDieValue, smartPawn) >= 17 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 0 && parState.getLegalMoves("dieValue2", i) <= 12 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    } else if (parState.getLegalMoves("dieValue2", i) >= 17 && parState.getLegalMoves("dieValue2", i) <= 67 && parState.getLegalMoves("dieValue2", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValue2";
                                                    }
                                                } else if (parState.getLegalMoves("dieValueTotal", i) == player2Rect[j] && parState.getLegalMoves("dieValueTotal", i) != -1) {
                                                    if (smartMoveEating == false) {
                                                        smartMoveEating = true;
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 12 && parState.getLegalMoves(smartDieValue, smartPawn) >= 17 && parState.getLegalMoves(smartDieValue, smartPawn) <= 67) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 0 && parState.getLegalMoves("dieValueTotal", i) <= 12 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    } else if (parState.getLegalMoves("dieValueTotal", i) >= 17 && parState.getLegalMoves("dieValueTotal", i) <= 67 && parState.getLegalMoves("dieValueTotal", i) > parState.getLegalMoves(smartDieValue, smartPawn)) {
                                                        smartPawn = i;
                                                        smartDieValue = "dieValueTotal";
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }
                                // if there was a smartMove for eating
                                if (smartMoveEating == true) {
                                    smartMove = true;
                                }
                                // else, there was no smart move for eating, moving into the safe zone
                                // or homebase, AND moving out of the starting zone
                                else {
                                    smartMove = false;
                                }
                            }
                            // if there was a smartMove for going into the safe zone or home base
                            else {
                                smartMove = false;
                            }
                        }
                        // if there was a smartMove for moving out of the starting zone
                        else {
                            smartMove = true;
                        }
                    }

                    if (smartMove) {
                        if (parState.getPawnActionMade() == false) {
                            selectAction = new ParSelectAction(this, smartPawn);
                            game.sendAction(selectAction);
                        } else if (parState.getUseDieActionMade() == false) {
                            if (smartDieValue.equals("dieValue1")) {
                                smartDieValueIndex = 0;
                                smartDieValueNumber = parState.getDice1Val();
                            } else if (smartDieValue.equals("dieValue2")) {
                                smartDieValueIndex = 1;
                                smartDieValueNumber = parState.getDice2Val();
                            } else if (smartDieValue.equals("dieValueTotal")) {
                                smartDieValueIndex = 2;
                                smartDieValueNumber = parState.getDice1Val() + parState.getDice2Val();
                            }

                            useDieAction = new ParUseDieAction(this, smartDieValueNumber, smartDieValueIndex);
                            game.sendAction(useDieAction);
                        } else {
                            moveAction = new ParMoveAction(this);
                            sleep(300);
                            game.sendAction(moveAction);
                            smartMove = false;
                            smartMoveStartingZone = false;
                            smartMoveSafeZone = false;
                            smartMoveEating = false;
                            return;
                        }
                    } else {
                        // if there is no smart move, then make a random move from the possible
                        // legal moves available
                        if (parState.getPawnActionMade() == false) {
                            Log.i("selecting pawn", "" + parState.getPawnActionMade());
                            Random rand = new Random();
                            // choosing a random pawn
                            int randSelect = rand.nextInt(4);
                            //make action
                            selectAction = new ParSelectAction(this, randSelect);
                            //execute action
                            game.sendAction(selectAction);
                            return;
                        } else if (parState.getUseDieActionMade() == false) {
                            Log.i("selecting use die", "" + parState.getUseDieActionMade());
                            Random rand = new Random();
                            // choose a random die
                            int randDie = rand.nextInt(3);
                            switch (randDie) {
                                //using first dice
                                case 0:
                                    useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val(), randDie);
                                    game.sendAction(useDieAction);
                                    break;
                                //using second dice
                                case 1:
                                    useDieAction = new ParUseDieAction(this, ((ParState) info).getDice2Val(), randDie);
                                    game.sendAction(useDieAction);
                                    break;
                                //using total of die
                                case 2:
                                    useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val() + ((ParState) info).getDice2Val(), randDie);
                                    game.sendAction(useDieAction);
                                    break;
                            }
                            return;
                        } else {
                            moveAction = new ParMoveAction(this);
                            sleep(300);
                            game.sendAction(moveAction);
                            return;
                        }
                        // ToDo: if non of the above changes the "smartMove = true", then make a normal move on the board

//                    // ToDo: select the pawns with a more thoughtful method
//                    if (parState.getPawnActionMade() == false) {
//                        Log.i("selecting pawn", "" + parState.getPawnActionMade());
//                        Random rand = new Random();
//                        // choose a random pawn
//                        int randSelect = rand.nextInt(4);
//                        selectAction = new ParSelectAction(this, randSelect);
//                        game.sendAction(selectAction);
//                        return;
//                    }
//
//                    else if (parState.getUseDieActionMade() == false) {
//                        for (int a = 0; a < 4; a++) {
//
//
//
//                            //Will go through each computer player
//                            switch (playerNumber) {
//                                //first pawn
//                                case 0:
//                                    //is using first dice moving pawn further
//                                    if ((parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("dieVal2", a)) && (parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("totalDieVal", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                        //is using second dice moving pawn further
//                                    } else if ((parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("totalDieVal", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice2Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                        //is using total of die moving pawn further
//                                    } else if ((parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal2", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val() + ((ParState) info).getDice2Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                    }
//                                    //second pawn
//                                case 1:
//                                    //is using first dice moving pawn further
//                                    if ((parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("dieVal2", a)) && (parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("totalDieVal", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                        //is using second dice moving pawn further
//                                    } else if ((parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("totalDieVal", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice2Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                        //is using total of die moving pawn further
//                                    } else if ((parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal2", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val() + ((ParState) info).getDice2Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                    }
//                                //third pawn
//                                case 2:
//                                    //is using first dice moving pawn further
//                                    if ((parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("dieVal2", a)) && (parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("totalDieVal", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                        //is using second dice moving pawn further
//                                    } else if ((parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("totalDieVal", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice2Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                        //is using total of die moving pawn further
//                                    } else if ((parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal2", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val() + ((ParState) info).getDice2Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                    }
//
//                                //fourth pawn
//                                case 3:
//                                    //is using first dice moving pawn further
//                                    if ((parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("dieVal2", a)) && (parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("totalDieVal", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                        //is using second dice moving pawn further
//                                    } else if ((parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("totalDieVal", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice2Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                        //is using total of die moving pawn further
//                                    } else if ((parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal2", a))) {
//                                        useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val() + ((ParState) info).getDice2Val(), 0);
//                                        game.sendAction(selectAction);
//                                        return;
//                                    }
//
//
//                            }
//                        }
//                    }
//                    //skipping
//                    else {
//                        moveAction = new ParMoveAction(this);
//                        sleep(300);
//                        game.sendAction(moveAction);
//                        return;
//                    }
                    }
                }
            }else {
                checkLegalMoveAction = new ParCheckLegalMoveAction(this);
                game.sendAction(checkLegalMoveAction);
                return;
            }
        }
    }
}
