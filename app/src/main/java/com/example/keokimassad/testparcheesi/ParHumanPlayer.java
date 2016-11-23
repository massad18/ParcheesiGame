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
    
    private ImageButton[] diceButtons = new ImageButton[2]; //array to hold dice button variables
    private Button[] useDieButtons = new Button[3]; //array to hold buttons to select specific dice values to use
    private Button makeMoveButton;
    private RadioGroup selectPawnButtons;

    private TextView textView;
    private TextView textView1;
    // this is the view on which you will listen for touch events
    private View touchView;

    private ParSurfaceView parSurfaceView;

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
    public void receiveInfo(GameInfo info)
    {
        switch(parState.getDice1Val())
        {
            case 1:
                //diceButtons[0].setImageResource(R.mipmap.die_val_1);
                diceButtons[0].setImageResource(R.drawable.die1);
                break;
            case 2:
                diceButtons[0].setImageResource(R.drawable.die2);
                break;
            case 3:
                diceButtons[0].setImageResource(R.drawable.die3);
                break;
            case 4:
                diceButtons[0].setImageResource(R.drawable.die4);
                break;
            case 5:
                diceButtons[0].setImageResource(R.drawable.die5);
                break;
            case 6:
                diceButtons[0].setImageResource(R.drawable.die6);
                break;
        }
        switch(parState.getDice2Val())
        {
            case 1:
                //diceButtons[0].setImageResource(R.mipmap.die_val_1);
                diceButtons[1].setImageResource(R.drawable.die1);
                break;
            case 2:
                diceButtons[1].setImageResource(R.drawable.die2);
                break;
            case 3:
                diceButtons[1].setImageResource(R.drawable.die3);
                break;
            case 4:
                diceButtons[1].setImageResource(R.drawable.die4);
                break;
            case 5:
                diceButtons[1].setImageResource(R.drawable.die5);
                break;
            case 6:
                diceButtons[1].setImageResource(R.drawable.die6);
                break;
        }
        //Checks to make sure the game info being sent in is an instance of the state class
        /*if(info instanceof ParState)
        {
            //Changes the image of die 1 based on the current die 1 value
            switch(((ParState)info).getDice1Val())
            {
                case 1:
                    //diceButtons[0].setImageResource(R.mipmap.die_val_1);
                    diceButtons[0].setImageResource(R.drawable.die1);
                    break;
                case 2:
                    diceButtons[0].setImageResource(R.drawable.die2);
                    break;
                case 3:
                    diceButtons[0].setImageResource(R.drawable.die3);
                    break;
                case 4:
                    diceButtons[0].setImageResource(R.drawable.die4);
                    break;
                case 5:
                    diceButtons[0].setImageResource(R.drawable.die5);
                    break;
                case 6:
                    diceButtons[0].setImageResource(R.drawable.die6);
                    break;
            }
            //Changes the image of die 2 based on the current die 2 value
            switch(((ParState)info).getDice2Val())
            {
                case 1:
                    diceButtons[1].setImageResource(R.drawable.die1);
                    break;
                case 2:
                    diceButtons[1].setImageResource(R.drawable.die2);
                    break;
                case 3:
                    diceButtons[1].setImageResource(R.drawable.die3);
                    break;
                case 4:
                    diceButtons[1].setImageResource(R.drawable.die4);
                    break;
                case 5:
                    diceButtons[1].setImageResource(R.drawable.die5);
                    break;
                case 6:
                    diceButtons[1].setImageResource(R.drawable.die6);
                    break;
            }
        }
        else
        {
            //Screen flashes black in info isn't an instance of ParState (which shouldn't occur unless error)
            flash(Color.BLACK, 5);
        }*/

//        if (surfaceView == null) return;
//
//        /* if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
//            // if the move was out of turn or otherwise illegal, flash the screen
//            surfaceView.flash(Color.RED, 50);
//        } */
//       /* else */ if (!(info instanceof ParState))
//            // if we do not have a TTTState, ignore
//            return;
//        else {
//            surfaceView.setState((ParState)info);
//            surfaceView.invalidate();
//            Log.i("human player", "receiving");
//        }
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
            switch (parState.getCurrentSubstage())
            {
                //Player has yet to roll the dice
                case 0:
                    int die1 = (int)(Math.random()*6) + 1;
                    int die2 = (int)(Math.random()*6) + 1;
                    parState.setDieVals(die1,die2);
                    //ParRollAction is called to roll the dice
                    ParRollAction actRoll = new ParRollAction(this);
                    game.sendAction(actRoll);
                    break;
                //Player has rolled and has to move pawns
                case 1:
                case 2:
                    die1 = (int)(Math.random()*6) + 1;
                    die2 = (int)(Math.random()*6) + 1;
                    parState.setDieVals(die1,die2);
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
            switch (parState.getCurrentSubstage())
            {
                //Player has yet to roll the dice
                case 0:
                    System.out.println("Entering case 0");
                    int die1 = (int)(Math.random()*6) + 1;
                    int die2 = (int)(Math.random()*6) + 1;
                    parState.setDieVals(die1,die2);
                    //ParRollAction is called to roll the dice
                    ParRollAction actRoll = new ParRollAction(this);
                    game.sendAction(actRoll);
                    break;
                //Player has rolled and has to move pawns
                case 1:
                case 2:
                    die1 = (int)(Math.random()*6) + 1;
                    die2 = (int)(Math.random()*6) + 1;
                    parState.setDieVals(die1,die2);
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
        touchView = activity.findViewById(R.id.imageView);

        //Listeners for dice  buttons
        diceButtons[0].setOnClickListener(this);
        diceButtons[1].setOnClickListener(this);
        useDieButtons[0].setOnClickListener(this);
        useDieButtons[1].setOnClickListener(this);
        useDieButtons[2].setOnClickListener(this);

        selectPawnButtons = (RadioGroup) activity.findViewById(R.id.selectPawn);
        selectPawnButtons.setOnCheckedChangeListener(this);

        touchView.setOnTouchListener(this);

        parState.initBoardPieces();

        //ToDo:Not sure if temp
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