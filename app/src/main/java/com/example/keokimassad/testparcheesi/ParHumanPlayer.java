package com.example.keokimassad.testparcheesi;

import android.view.View;

import game.GameHumanPlayer;
import game.GameMainActivity;
import game.infoMsg.GameInfo;

/**
 * Created by KeokiMassad on 11/20/16.
 */

public class ParHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    ParLocalGame pigLocalGame = new ParLocalGame();
    private int[] location = new int[4];

    // the android activity that we are running
    GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public ParHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    public void onClick (View button) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }

    private void initiLocations() {
        for (int i = 0; i < 4; i++) {
            location[i] = 0;
        }
    }
}
