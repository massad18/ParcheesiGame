package com.example.keokimassad.testparcheesi;

import game.GameMainActivity;
import game.GamePlayer;
import game.actionMsg.GameAction;

/**
 * Created by KeokiMassad on 11/20/16.
 */

public class ParRollAction extends GameAction {

    private GameMainActivity myActivity;

    public ParRollAction (GamePlayer player)
    {
        super(player);
        int rndDieVal1 = (int)(Math.random() * 6) + 1;
        int rndDieVal2 = (int)(Math.random() * 6) + 1;
        //ToDo: set dice vals and see if Justin is doing this correctly or going crazy
        //((MainActivity)myActivity).parState.setDieVals(player,rndDieVal1,rndDieVal2);
    }
}
