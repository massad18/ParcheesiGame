package com.example.keokimassad.testparcheesi;

import game.GameHumanPlayer;
import game.GameMainActivity;
import game.GamePlayer;
import game.actionMsg.GameAction;

/**
 * Created by KeokiMassad on 12/2/16.
 */

public class ParSelectAction extends GameAction {
    private static final long serialVersionUID = 4312438733974L;

    private GameMainActivity myActivity;
    private int pawnIdx;

    public ParSelectAction (GamePlayer player, int pawnIdx)
    {
        super(player);
        this.pawnIdx = pawnIdx;
    }

    public int getPawnIdx() {
        return pawnIdx;
    }
}
