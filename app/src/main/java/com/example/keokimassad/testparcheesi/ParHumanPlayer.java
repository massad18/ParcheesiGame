package com.example.keokimassad.testparcheesi;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;

import game.GameHumanPlayer;
import game.GameMainActivity;
import game.infoMsg.GameInfo;
import static com.example.keokimassad.testparcheesi.R.id.surfaceView;

public class ParHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener, RadioGroup.OnCheckedChangeListener{

    //Instance Variables
    private GameMainActivity myActivity; //the android activity that we are running
    ParLocalGame parLocalGame = new ParLocalGame();
    private int playerIdx;
    ParState parState = new ParState();
    private ParState state;
    
    private ImageButton[] diceButtons = new ImageButton[2]; //array to hold dice button variables
    private Button[] useDieButtons = new Button[3]; //array to hold buttons to select specific dice values to use
    private Button makeMoveButton;
    private RadioGroup selectPawnButtons;

    private TextView textView;
    private TextView textView1;
    // this is the view on which you will listen for touch events

    private ParSurfaceView parSurfaceView;
    private PawnLocation pawnLocation = new PawnLocation();

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
     * 		the top object in the GUI's view hierarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    @Override
    public void receiveInfo(GameInfo info)
    {
        if(info instanceof ParState)
        {
            state = (ParState)info;

            switch(state.getDice1Val())
            {
                case 1:
                    diceButtons[0].setBackgroundResource(R.drawable.die1);
                    break;
                case 2:
                    diceButtons[0].setBackgroundResource(R.drawable.die2);
                    break;
                case 3:
                    diceButtons[0].setBackgroundResource(R.drawable.die3);
                    break;
                case 4:
                    diceButtons[0].setBackgroundResource(R.drawable.die4);
                    break;
                case 5:
                    diceButtons[0].setBackgroundResource(R.drawable.die5);
                    break;
                case 6:
                    diceButtons[0].setBackgroundResource(R.drawable.die6);
                    break;
            }
            switch(state.getDice2Val())
            {
                case 1:
                    diceButtons[1].setBackgroundResource(R.drawable.die1);
                    break;
                case 2:
                    diceButtons[1].setBackgroundResource(R.drawable.die2);
                    break;
                case 3:
                    diceButtons[1].setBackgroundResource(R.drawable.die3);
                    break;
                case 4:
                    diceButtons[1].setBackgroundResource(R.drawable.die4);
                    break;
                case 5:
                    diceButtons[1].setBackgroundResource(R.drawable.die5);
                    break;
                case 6:
                    diceButtons[1].setBackgroundResource(R.drawable.die6);
                    break;
            }
        }

        if (parSurfaceView == null) return;

        /* if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if the move was out of turn or otherwise illegal, flash the screen
            surfaceView.flash(Color.RED, 50);
        } */
       /* else */ if (!(info instanceof ParState))
            // if we do not have a TTTState, ignore
            return;
        else {
            parSurfaceView.setState((ParState)info);
            parSurfaceView.invalidate();
            Log.i("human player", "receiving");
        }
    }

    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        //Decides how objects of same type interact with each other
        //Uses an enum, SameObjectCollisionType to tell what action to take
        switch(checkedId) {
            //Will bounce off each other
            case R.id.radioPawn1:
                break;
            //Will merge into one larger object
            case R.id.radioPawn2:
                break;
            //Will destroy slower of two objects
            case R.id.radioPawn3:
                break;
            //Will destroy smaller of two objects
            case R.id.radioPawn4:
                break;
        }
    }

    public void onClick (View button) {
        //Peforms actions when dice pressed
        //If the first die is pressed
        if(button == diceButtons[0])
        {
            //ToDo:Not sure if actions called correctly (may want to check that)
            //ToDo:Move the setDieVals to makeMove in ParLocalGame
            switch (state.getCurrentSubstage())
            {
                //Player has yet to roll the dice
                case 0:
                    //int die1 = (int)(Math.random()*6) + 1;
                    //int die2 = (int)(Math.random()*6) + 1;
                    //parState.setDieVals(die1,die2);
                    //ParRollAction is called to roll the dice
                    ParRollAction actRoll = new ParRollAction(this);
                    game.sendAction(actRoll);
                    break;
                //Player has rolled and has to move pawns
                case 1:
                case 2:
                    //ParMoveAction is called to move a pawn
                    ParMoveAction actMove = new ParMoveAction(this);
                    game.sendAction(actMove);
                    //diceButtons[0].setEnabled(false); //sets die to no longer be pressed
                    break;
            }
        }
        //If the second die is pressed
        else if(button == diceButtons[1])
        {
            switch (state.getCurrentSubstage())
            {
                //Player has yet to roll the dice
                case 0:
                    //int die1 = (int)(Math.random()*6) + 1;
                    //int die2 = (int)(Math.random()*6) + 1;
                    //parState.setDieVals(die1,die2);
                    //ParRollAction is called to roll the dice
                    ParRollAction actRoll = new ParRollAction(this);
                    game.sendAction(actRoll);
                    break;
                //Player has rolled and has to move pawns
                case 1:
                case 2:
                    //ParMoveAction is called to move a pawn
                    ParMoveAction actMove = new ParMoveAction(this);
                    game.sendAction(actMove);
                    //diceButtons[1].setEnabled(false); //sets die to no longer be pressed
                    break;
            }
        }
        else if(button == useDieButtons[0])
        {
            System.out.println("0 pressed");
        }
        else if(button == useDieButtons[1])
        {
            System.out.println("1 pressed");
        }
        else if(button == useDieButtons[2])
        {
            System.out.println("2 pressed");
        }
        else if(button == makeMoveButton)
        {
            System.out.println("3 pressed");
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
        this.useDieButtons[0] = (Button) activity.findViewById(R.id.useDie1);
        this.useDieButtons[1] = (Button) activity.findViewById(R.id.useDie2);
        this.useDieButtons[2] = (Button) activity.findViewById(R.id.useBothDice);
        textView = (TextView) activity.findViewById(R.id.textView);
        textView1 = (TextView) activity.findViewById(R.id.textView1);
        // this is the view on which you will listen for touch events
        parSurfaceView = (ParSurfaceView) activity.findViewById(R.id.surfaceView);

        //Listeners for dice  buttons
        diceButtons[0].setOnClickListener(this);
        diceButtons[1].setOnClickListener(this);
        useDieButtons[0].setOnClickListener(this);
        useDieButtons[1].setOnClickListener(this);
        useDieButtons[2].setOnClickListener(this);

        selectPawnButtons = (RadioGroup) activity.findViewById(R.id.selectPawn);
        selectPawnButtons.setOnCheckedChangeListener(this);

        parSurfaceView.setOnTouchListener(this);

        parState.initBoardPieces();
        for (int i = 0; i < 4 /*number of players*/; i++) {
            pawnLocation.initPawnLocation(i);
        }

        //Initializes the current substage to Roll (0)
        parState.setCurrentSubstage(0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        textView.setText("Touch coordinates : " +
                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
        textView1.setText(parState.containsInRect(event.getX(), event.getY()));
        return true;
    }
}