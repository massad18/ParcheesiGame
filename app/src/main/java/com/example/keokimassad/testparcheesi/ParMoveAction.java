package com.example.keokimassad.testparcheesi;

import game.GamePlayer;
import game.actionMsg.GameAction;

/**
 * Created by KeokiMassad on 11/20/16.
 */

public class ParMoveAction extends GameAction {

    private int rectangle;

    public ParMoveAction(GamePlayer player, int rectNum)
    {
        super(player);
        this.rectangle = rectNum;
    }

    public int getRectangle()
    {
        return rectangle;
    }
}
