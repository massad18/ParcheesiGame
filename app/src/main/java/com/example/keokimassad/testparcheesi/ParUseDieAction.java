package com.example.keokimassad.testparcheesi;

import game.GameMainActivity;
import game.GamePlayer;
import game.actionMsg.GameAction;

/**
 * Created by KeokiMassad on 12/3/16.
 */

public class ParUseDieAction extends GameAction {
    private static final long serialVersionUID = 3524324364298L;

    private GameMainActivity myActivity;
    private int totalDieValue;
    private int dieIndex;

    public ParUseDieAction (GamePlayer player, int dieValue, int dieIndex)
    {
        super(player);
        this.totalDieValue = dieValue;
        this.dieIndex = dieIndex;
    }

    public int getTotalDieValue() {
        return totalDieValue;
    }
    public int getDieIndex() {
        return dieIndex;
    }
}
