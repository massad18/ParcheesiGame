package com.example.keokimassad.testparcheesi;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Hashtable;

import game.GameHumanPlayer;
import game.GameMainActivity;
import game.infoMsg.GameInfo;

public class ParHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    //Instance Variables
    private GameMainActivity myActivity; //the android activity that we are running
    ParLocalGame parLocalGame = new ParLocalGame();
    private int playerIdx;
    
    private ImageButton[] diceButtons = null; //array to hold dice button variables

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
        //Checks to make sure the game info being sent in is an instance of the state class
        if(info instanceof ParState)
        {
            //Changes the image of die 1 based on the current die 1 value
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
            //Changes the image of die 2 based on the current die 2 value
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
            //Screen flashes black in info isn't an instance of ParState (which shouldn't occur unless error)
            flash(Color.BLACK, 5);
        }
    }

    public void onClick (View button) {
        //Peforms actions when dice pressed
        //If the first die is pressed
        if(button == diceButtons[0])
        {
            //ToDo:Not sure if actions called correctly (may want to check that)
            switch (((MainActivity)myActivity).parState.getCurrentSubstage())
            {
                //Player has yet to roll the dice
                case ParState.Roll:
                    //ParRollAction is called to roll the dice
                    ParRollAction actRoll = new ParRollAction(this);
                    game.sendAction(actRoll);
                    break;
                //Player has rolled and has to move pawns
                case ParState.Begin_Move:
                case ParState.Mid_Move:
                    //ParMoveAction is called to move a pawn
                    ParMoveAction actMove = new ParMoveAction(this);
                    game.sendAction(actMove);
                    diceButtons[0].setEnabled(false); //sets die to no longer be pressed
                    break;
            }
        }
        //If the second die is pressed
        else if(button == diceButtons[1])
        {
            switch (((MainActivity)myActivity).parState.getCurrentSubstage())
            {
                //Player has yet to roll the dice
                case ParState.Roll:
                    //ParRollAction is called to roll the dice
                    ParRollAction actRoll = new ParRollAction(this);
                    game.sendAction(actRoll);
                    break;
                //Player has rolled and has to move pawns
                case ParState.Begin_Move:
                case ParState.Mid_Move:
                    //ParMoveAction is called to move a pawn
                    ParMoveAction actMove = new ParMoveAction(this);
                    game.sendAction(actMove);
                    diceButtons[1].setEnabled(false); //sets die to no longer be pressed
                    break;
            }
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

        //Listeners for dice  buttons
        diceButtons[0].setOnClickListener(this);
        diceButtons[1].setOnClickListener(this);
    }

    //Initializes players' pawn locations
    private void initLocations() {
        //Goes through every player in the game
        for(int playerIdx = 0; playerIdx < parLocalGame.getPlayerArray().length; playerIdx++)
        {
            switch (playerIdx)
            {
                //ToDo: change last value in setPawnLocationsForPlayer calls to correct indexes
                //setPawnLocationsForPlayer(player,pawn,location)
                //If player 1
                case 0:
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(0,0,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(0,1,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(0,2,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(0,3,0);
                    break;
                //If player 2
                case 1:
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(1,0,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(1,1,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(1,2,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(1,3,0);
                    break;
                //If player 3
                case 2:
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(2,0,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(2,1,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(2,2,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(2,3,0);
                    break;
                //If player 4
                case 3:
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(3,0,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(3,1,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(3,2,0);
                    ((MainActivity)myActivity).parState.setPawnLocationsForPlayer(3,3,0);
                    break;
            }
        }
    }
}