package com.example.keokimassad.testparcheesi;

import game.GameMainActivity;
import game.GamePlayer;
import game.actionMsg.GameAction;

/**
 * Created by KeokiMassad on 12/3/16.
 */

public class ParUseDieAction extends GameAction {

    private GameMainActivity myActivity;
    private int totalDieValue;

    public ParUseDieAction (GamePlayer player, int dieValue)
    {
        super(player);
        this.totalDieValue = dieValue;
    }

    public int getTotalDieValue() {
        return totalDieValue;
    }
}
