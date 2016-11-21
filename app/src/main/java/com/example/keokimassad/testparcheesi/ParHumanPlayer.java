package com.example.keokimassad.testparcheesi;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

import game.GameHumanPlayer;
import game.GameMainActivity;
import game.infoMsg.GameInfo;

public class ParHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    ParLocalGame pigLocalGame = new ParLocalGame();
    private int[] location = new int[4];
    private ImageButton[] diceButtons = null;

    // the android activity that we are running
    GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public ParHumanPlayer(String name) {
        super(name);
        initLocations();
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
    public void receiveInfo(GameInfo info)
    {
        if(info instanceof ParState)
        {

        }
        else
        {
            flash(Color.BLACK, 5);
        }
    }

    public void onClick (View button) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }

    private void initLocations() {
        for (int i = 0; i < 4; i++) {
            location[i] = 0;
        }
    }
}
