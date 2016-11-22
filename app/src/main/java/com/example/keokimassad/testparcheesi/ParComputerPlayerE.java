package com.example.keokimassad.testparcheesi;

import game.GameComputerPlayer;
import game.infoMsg.GameInfo;

/**
 * Created by KeokiMassad on 11/20/16.
 */

public class ParComputerPlayerE extends GameComputerPlayer {

    ParLocalGame parLocalGame = new ParLocalGame();
    int player1 = playerTurn;
    private int[] location = new int[4];

    public ParComputerPlayerE (String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(parLocalGame.canMove(player1))
        {
            if(info instanceof ParGameState)
            {
                if //(one or two of pieces are at home)
                {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //have one dice be three and one dice be 2.
                            //bring one piece out.
                        }
                    }, 2000);

                }
                else
                {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            //roll two 1's and advance each piece one spot
                        }
                    }, 2000);

                }
            }
        }

    }
    else
    {
        return;
    }
}
