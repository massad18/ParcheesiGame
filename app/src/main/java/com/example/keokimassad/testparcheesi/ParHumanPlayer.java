package com.example.keokimassad.testparcheesi;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

import java.util.Hashtable;

import game.GameHumanPlayer;
import game.GameMainActivity;
import game.infoMsg.GameInfo;

public class ParHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    //Instance Variables
    private GameMainActivity myActivity; //the android activity that we are running
    ParLocalGame pigLocalGame = new ParLocalGame();
    private int[] location = new int[4];
    private int playerIdx;

    private ImageButton[] diceButtons = null;

    /**
     * constructor does nothing extra
     */
    public ParHumanPlayer(String name) {
        super(name);
        //initLocations();
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
            //Changes the image of die 1 based on the current die value
            switch(((ParState)info).getDice1Val())
            {
                case 1:
                    diceButtons[0].setImageResource(R.mipmap.die_val_1);
                    break;
                case 2:
                    diceButtons[0].setImageResource(R.mipmap.die_val_2);
                    break;
                case 3:
                    diceButtons[0].setImageResource(R.mipmap.die_val_3);
                    break;
                case 4:
                    diceButtons[0].setImageResource(R.mipmap.die_val_4);
                    break;
                case 5:
                    diceButtons[0].setImageResource(R.mipmap.die_val_5);
                    break;
                case 6:
                    diceButtons[0].setImageResource(R.mipmap.die_val_6);
                    break;
            }
            //Changes the image of die 2 based on the current die value
            switch(((ParState)info).getDice2Val())
            {
                case 1:
                    diceButtons[1].setImageResource(R.mipmap.die_val_1);
                    break;
                case 2:
                    diceButtons[1].setImageResource(R.mipmap.die_val_2);
                    break;
                case 3:
                    diceButtons[1].setImageResource(R.mipmap.die_val_3);
                    break;
                case 4:
                    diceButtons[1].setImageResource(R.mipmap.die_val_4);
                    break;
                case 5:
                    diceButtons[1].setImageResource(R.mipmap.die_val_5);
                    break;
                case 6:
                    diceButtons[1].setImageResource(R.mipmap.die_val_6);
                    break;
            }
        }
        else
        {
            flash(Color.BLACK, 5);
        }
    }

    public void onClick (View button) {
        //Peforms actions when dice pressed
        if(button == diceButtons[0])
        {

        }
        else if(button == diceButtons[1])
        {

        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        //sets the activity
        myActivity = activity;

        //Loads the layout for the main screen of the game
        activity.setContentView(R.layout.activity_main);

        //assigns variables to widgets on screen
        this.diceButtons[0] = (ImageButton) activity.findViewById(R.id.die1);
        this.diceButtons[1] = (ImageButton) activity.findViewById(R.id.die2);

        //Listeners for buttons
        diceButtons[0].setOnClickListener(this);
        diceButtons[1].setOnClickListener(this);
    }

    private void initLocations(int locationIdx) {

    }

    private void setLocations(int locationIdx) {

    }

    public int getLocationPlayer(int locationIdx) {
        return -1;
    }
}
