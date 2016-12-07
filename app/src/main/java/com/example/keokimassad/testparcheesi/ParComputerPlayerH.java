package com.example.keokimassad.testparcheesi;

import android.util.Log;

import java.util.Random;

import game.GameComputerPlayer;
import game.infoMsg.GameInfo;

/**
 * Created by AvayaBhattarai on 12/6/16.
 */

public class ParComputerPlayerH extends GameComputerPlayer {

    ParLocalGame parLocalGame = new ParLocalGame();
    // ToDo: change to actual player numbers when we figure this shit out
    //int playerNumber = playerNum;
    int playerNumber;
    private int[] location = new int[4];
    ParRollAction rollAction;
    ParSelectAction selectAction;
    ParUseDieAction useDieAction;
    ParMoveAction moveAction;
    ParState parState;


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
        // ToDo: same as above... change to actual player numbers when we figure this shit out
        for (int i = 0; i < allPlayerNames.length; i++) {
            if (allPlayerNames[i].equals(name)) {
                playerNumber = i;
            }
        }

        if (!(info instanceof ParState)) return;
        ParState parState = (ParState) info;

        if (parLocalGame.canMove(playerNumber)) {
            if (parState.getCurrentSubstage() == parState.Roll) {
                rollAction = new ParRollAction(this);
                sleep(1000);
                game.sendAction(rollAction);
            }
            if (parState.getCurrentSubstage() == parState.Begin_Move || parState.getCurrentSubstage() == parState.Mid_Move) {
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
                // get the rectangle of all of the pawns
                // if the rectangle is -1... then it is in the starting locations
                for (int j = 0; j < 4; j++) {
                    player0Rect[j] = parState.getRect(player0LocationsX[j], player0LocationsY[j]);
                    player1Rect[j] = parState.getRect(player1LocationsX[j], player1LocationsY[j]);
                    player2Rect[j] = parState.getRect(player2LocationsX[j], player2LocationsY[j]);
                    player3Rect[j] = parState.getRect(player3LocationsX[j], player3LocationsY[j]);
                }
                for (int a = 0; a < 4; a++) {

                    switch (playerNumber) {
                        case 0:
                            if ((parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("dieVal2", a)) && (parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("totalDieVal", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            } else if ((parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("totalDieVal", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice2Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            } else if ((parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal2", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val() + ((ParState) info).getDice2Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            }

                        case 1:
                            if ((parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("dieVal2", a)) && (parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("totalDieVal", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            } else if ((parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("totalDieVal", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice2Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            } else if ((parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal2", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val() + ((ParState) info).getDice2Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            }

                        case 2:
                            if ((parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("dieVal2", a)) && (parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("totalDieVal", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            } else if ((parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("totalDieVal", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice2Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            } else if ((parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal2", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val() + ((ParState) info).getDice2Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            }


                        case 3:
                            if ((parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("dieVal2", a)) && (parState.getLegalMoves("dieVal1", a) > parState.getLegalMoves("totalDieVal", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            } else if ((parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("dieVal2", a) > parState.getLegalMoves("totalDieVal", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice2Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            } else if ((parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal1", a)) && (parState.getLegalMoves("totalDieVal", a) > parState.getLegalMoves("dieVal2", a))) {
                                useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val() + ((ParState) info).getDice2Val(), 0);
                                game.sendAction(selectAction);
                                break;
                            }


                    }
                }
                moveAction = new ParMoveAction(this);
                sleep(300);
                game.sendAction(moveAction);
            }
        }
    }
}
