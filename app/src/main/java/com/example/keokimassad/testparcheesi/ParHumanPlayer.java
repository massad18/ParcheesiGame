package com.example.keokimassad.testparcheesi;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import game.GameHumanPlayer;
import game.GameMainActivity;
import game.LocalGame;
import game.infoMsg.GameInfo;


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
    private Button endTurnButton;
    private RadioGroup selectPawnButtons;

    private TextView textView;
    private TextView textView1;
    // this is the view on which you will listen for touch events

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

        switch(checkedId) {

            case R.id.radioPawn1:
                ParSelectAction parSelectAction0 = new ParSelectAction(this, 0);
                game.sendAction(parSelectAction0);
                break;

            case R.id.radioPawn2:
                ParSelectAction parSelectAction1 = new ParSelectAction(this, 1);
                game.sendAction(parSelectAction1);
                break;

            case R.id.radioPawn3:
                ParSelectAction parSelectAction2 = new ParSelectAction(this, 2);
                game.sendAction(parSelectAction2);
                break;

            case R.id.radioPawn4:
                ParSelectAction parSelectAction3 = new ParSelectAction(this, 3);
                game.sendAction(parSelectAction3);
                break;
        }
    }

    public void onClick (View button) {
        //Performs actions when dice pressed
        //If the first die is pressed
        if(button == diceButtons[0])
        {
            switch (state.getCurrentSubstage())
            {
                //Player needs to role the dice
                case 0:
                    //ParRollAction is called to roll the dice
                    ParRollAction actRoll = new ParRollAction(this);
                    game.sendAction(actRoll);
                    break;
                //Player has rolled and has to move pawns
                case 1:
                case 2:
                    //ParMoveAction is called to move a pawn
                    //ParMoveAction actMove = new ParMoveAction(this); //ToDo: REDO THIS STUFF, AVAYA STILL MESSING AROUND
                    //game.sendAction(actMove); //ToDo: Look above
                    break;
            }
        }
        //If the second die is pressed
        else if(button == diceButtons[1])
        {
            switch (state.getCurrentSubstage())
            {
                //Player needs to role the dice
                case 0:
                    //ParRollAction is called to roll the dice
                    ParRollAction actRoll = new ParRollAction(this);
                    game.sendAction(actRoll);
                    break;
                //Player has rolled and has to move pawns
                case 1:
                case 2:
                    //ParMoveAction is called to move a pawn
                    //ParMoveAction actMove = new ParMoveAction(this); //ToDo: REDO THIS STUFF, AVAYA STILL MESSING AROUND
                    //game.sendAction(actMove); //ToDo: Look above
                    break;
            }
        }
        else if(button == useDieButtons[0])
        {
            System.out.println("0 pressed");
            // calls ParUseDieAction and sets the current die value selected to die 1
            ParUseDieAction parUseDieAction = new ParUseDieAction(this, parState.getDice1Val());
            game.sendAction(parUseDieAction);
        }
        else if(button == useDieButtons[1])
        {
            System.out.println("1 pressed");
            // calls ParUseDieAction and sets the current die value selected to die 2
            ParUseDieAction parUseDieAction = new ParUseDieAction(this, parState.getDice2Val());
            game.sendAction(parUseDieAction);
        }
        else if(button == useDieButtons[2])
        {
            System.out.println("2 pressed");
            // calls ParUseDieAction and sets the current die value selected to die 1 & 2
            ParUseDieAction parUseDieAction = new ParUseDieAction(this, parState.getDice1Val() + parState.getDice2Val());
            game.sendAction(parUseDieAction);
        }
        else if(button == endTurnButton)
        {

        }
//        if((parState.getCurrentSubstage() == parState.Begin_Move) || (parState.getCurrentSubstage() == parState.Mid_Move))
//        {
            else if(button == makeMoveButton)
            {
                ParMoveAction parMoveAction = new ParMoveAction(this);
                game.sendAction(parMoveAction);
            }
//        }
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
        this.makeMoveButton = (Button) activity.findViewById(R.id.makeMoveButton);
        this.endTurnButton = (Button) activity.findViewById(R.id.endTurnButton);
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
        selectPawnButtons.clearCheck();

        makeMoveButton.setOnClickListener(this);

        endTurnButton.setOnClickListener(this);

        parSurfaceView.setOnTouchListener(this);

        //Initializes the current substage to Roll (0)
        parState.setCurrentSubstage(parState.Roll);
    }


    // displays the rectangle in which the human is touching on the board (surface view)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        textView.setText("Touch coordinates : " +
                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
        textView1.setText(parState.containsInRect(event.getX(), event.getY()));


        int x = (int) event.getX();
        int y = (int) event.getY();
        int rectNum = parState.getRect(x, y);
        if( rectNum >= 0)
        {
            ParMoveAction action  = new ParMoveAction(this);
            //surfaceView.invalidate();
        }




        return true;
    }




}