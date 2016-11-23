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



//CHECK OVERRIDE OF ONCLICK
public class MainActivity extends GameMainActivity{

    private RelativeLayout rl_Main;
    public ParState parState;
    public static final int PORT_NUMBER = 5213;




    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Index 0
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new ParHumanPlayer(name);
            }
        });

        // Index 1
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new ParHumanPlayer(name);
            }
        });

        // Index 2
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new ParHumanPlayer(name);
            }
        });

        // Index 3
        playerTypes.add(new GamePlayerType("Local Human Player") {
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
        GameConfig defaultConfig = new GameConfig(playerTypes, 2,4, "Parcheesi", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Computer", 4); //Easy Computer

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Remote Player", "", 1);
        //done!
        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame()
    {
        return new ParLocalGame();
    }



}
