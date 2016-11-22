package com.example.keokimassad.testparcheesi;

import game.GameComputerPlayer;
import game.infoMsg.GameInfo;

/**
 * Created by KeokiMassad on 11/20/16.
 */

public class ParComputerPlayerE extends GameComputerPlayer {

    ParLocalGame parLocalGame = new ParLocalGame();
    private int[] location = new int[4];

    public ParComputerPlayerE (String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(parLocalGame)

    }
}
