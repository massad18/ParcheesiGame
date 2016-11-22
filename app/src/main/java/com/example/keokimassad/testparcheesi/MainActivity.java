package com.example.keokimassad.testparcheesi;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

import game.GameMainActivity;
import game.GamePlayer;
import game.LocalGame;
import game.config.GameConfig;
import game.config.GamePlayerType;


public class MainActivity extends GameMainActivity{

    private RelativeLayout rl_Main;
    private ParState parState;
    public static final int PORT_NUMBER = 5213;



    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Red Start - index 0
        playerTypes.add(new GamePlayerType("Local Human Player (Red)") {
            public GamePlayer createPlayer(String name) {
                return new ParHumanPlayer(name);
            }
        });

        // Blue Start - index 1
        playerTypes.add(new GamePlayerType("Local Human Player (Blue)") {
            public GamePlayer createPlayer(String name) {
                return new ParHumanPlayer(name);
            }
        });

        // Yellow Start - index 2
        playerTypes.add(new GamePlayerType("Local Human Player (Yellow)") {
            public GamePlayer createPlayer(String name) {
                return new ParHumanPlayer(name);
            }
        });

        // Green Start - index 3
        playerTypes.add(new GamePlayerType("Local Human Player (Green)") {
            public GamePlayer createPlayer(String name) {
                return new ParHumanPlayer(name);
            }
        });

        // dumb computer player - index 4
        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
            public GamePlayer createPlayer(String name) {
                return new ParComputerPlayerE(name);
            }
        });

        // Create a game configuration class for Tic-tac-toe
        GameConfig defaultConfig = new GameConfig(playerTypes, 2,4, "Tic-Tac-Toe", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0); //Red
        defaultConfig.addPlayer("Computer", 4); //Easy Computer

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Remote Player", "", 1); //Blue

        //done!
        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame()
    {
        return new ParLocalGame();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        parState = new ParState();
        parState.initBoardPieces();

        final TextView textView = (TextView)findViewById(R.id.textView);
        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        // this is the view on which you will listen for touch events
        final View touchView = findViewById(R.id.imageView);

        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                textView.setText("Touch coordinates : " +
                        String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                textView1.setText(parState.containsInRect(event.getX(), event.getY()));
                return true;
            }
        });
    }
}
