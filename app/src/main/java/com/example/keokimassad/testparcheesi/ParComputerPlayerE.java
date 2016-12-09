package com.example.keokimassad.testparcheesi;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Random;

import game.GameComputerPlayer;
import game.LocalGame;
import game.infoMsg.GameInfo;
import game.infoMsg.NotYourTurnInfo;


/**
 * Created by KeokiMassad on 11/20/16.
 * Will create a computer player that will go through all legal moves
 * and will randomly pick one of them to execute
 */

public class ParComputerPlayerE extends GameComputerPlayer {

    // May need to change to actual player numbers
    int playerNumber;
    private int[] location = new int[4];
    ParRollAction rollAction;
    ParSelectAction selectAction;
    ParUseDieAction useDieAction;
    ParMoveAction moveAction;
    ParCheckLegalMoveAction checkLegalMoveAction;
    ParState parState;

    public ParComputerPlayerE(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        //Updating proper computer player's move
        //May need to change to actual player numbers
        for (int i = 0; i < allPlayerNames.length; i++) {
            if (allPlayerNames[i].equals(name)) {
                playerNumber = i;
            }
        }

        if (!(info instanceof ParState)) return;
        ParState parState = (ParState)info;
        //check if it's a computer player's turn
        if (parState.getPlayerTurn() == playerNumber) {

            if (parState.getCheckLegalMoveActionMade() == true) {
                // roll the die
                if (parState.getCurrentSubstage() == parState.Roll) {
                    rollAction = new ParRollAction(this);
                    sleep(500);
                    game.sendAction(rollAction);
                    sleep(2000);
                    return;
                    //find out if it's time to move a pawn
                } else if (parState.getCurrentSubstage() == parState.Begin_Move || parState.getCurrentSubstage() == parState.Mid_Move) {
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
                        Log.i("moving", parState.getPawnActionMade() + " " + parState.getUseDieActionMade());
                        moveAction = new ParMoveAction(this);
                        game.sendAction(moveAction);
                        return;
                    }
                }
                //skipping
            } else {
                checkLegalMoveAction = new ParCheckLegalMoveAction(this);
                game.sendAction(checkLegalMoveAction);
                return;
            }
        }

    }

}