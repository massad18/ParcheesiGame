package com.example.keokimassad.testparcheesi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by AvayaBhattarai on 11/23/16.
 */

public class ParSurfaceView extends SurfaceView {


    /*
     * Instance variables
	 */
    //Holds the location of the players pawn in question
    private static int playerXCor = 0;
    private static int playerYCor = 0;

    // the game's state
    protected ParState parState;

    public ParSurfaceView(Context context) {
        super(context);
        init();
    }// ctor

    /**
     * An alternate constructor for use when a subclass is directly specified
     * in the layout.
     *
     * @param context - a reference to the activity this animation is run under
     * @param attrs   - set of attributes passed from system
     */
    public ParSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }// ctor

    /**
     * Helper-method for the constructors
     */
    private void init() {
        setBackgroundColor(backgroundColor());
    }// init


    public void setState(ParState state) {
        this.parState = state;
    }


    /**
     */
    public int backgroundColor() {
        return Color.TRANSPARENT;
    }

    /**
     * callback method, called whenever it's time to redraw
     * frame
     *
     * @param g the canvas to draw on
     */
    public void onDraw(Canvas g) {

        boolean selected;

        // update the variables that relate
        // to the dimensions of the animation surface

        // display the Parcheesi gameboard image
        setBackgroundResource(R.drawable.parcheesiboard);

        // if we don't have any state, there's nothing more to draw, so return
        if (parState == null) {
            return;
        }

        //loops through each player and each pawn and draws each one by calling the Pawn class
        for (int i = 0; i < 4 /* number of players */; i++) {
            for (int j = 0; j < 4; j++) {
                playerXCor = parState.getPawnLocationsXForPlayer(i, j); //gets x coordinate of the current pawn and player in question
                playerYCor = parState.getPawnLocationsYForPlayer(i, j); //gets y coordinate of the current pawn and player in question
                Pawn currLoc = new Pawn(playerXCor, playerYCor); //Creates a pawn object
                // change the background color of the selected pawn for the player whos turn it is
                if (i == parState.getPlayerTurn() && j == parState.getRadioButtonChecked()) {
                    selected = true;
                }
                else {
                    selected = false;
                }
                currLoc.drawOn(playerXCor, playerYCor, g, i, selected); //draws each pawn according to player, pawn number, and if its selected
            }
        }
    }




}