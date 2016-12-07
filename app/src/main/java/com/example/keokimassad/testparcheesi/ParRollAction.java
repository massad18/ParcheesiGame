package com.example.keokimassad.testparcheesi;

import game.GameMainActivity;
import game.GamePlayer;
import game.actionMsg.GameAction;

/**
 * Created by KeokiMassad on 11/20/16.
 */

public class ParRollAction extends GameAction {

    private static final long serialVersionUID = 984123753985612978L;

    private GameMainActivity myActivity;

    public ParRollAction (GamePlayer player)
    {
        super(player);
    }
}
