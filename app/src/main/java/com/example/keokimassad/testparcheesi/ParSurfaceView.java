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

    // some constants, which are percentages with respect to the minimum
    // of the height and the width. All drawing will be done in the "middle
    // square".
    //
    // The divisions both horizontally and vertically within the
    // playing square are:
    // - first square starts at 5% and goes to 33%
    // - second square starts at 36% and goes to 64%
    // - third square starts at 67& and goes to 95%
    // There is therefore a 5% border around the edges; each square
    // is 28% high/wide, and the lines between squares are 3%
    private final static float BORDER_PERCENT = 5; // size of the border
    private final static float SQUARE_SIZE_PERCENT = 28; // size of each of our 9 squares
    private final static float LINE_WIDTH_PERCENT = 3; // width of a tic-tac-toe line
    private final static float SQUARE_DELTA_PERCENT = SQUARE_SIZE_PERCENT
            + LINE_WIDTH_PERCENT; // distance from left (or top) edge of square to the next one
    private static int playerXCor = 0;
    private static int playerYCor = 0;

    /*
     * Instance variables
	 */

    // the game's state
    protected ParState parState = new ParState();


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
     * @return the color to paint the tic-tac-toe lines, and the X's and O's
     */
    public int foregroundColor() {
        return Color.YELLOW;
    }

    /**
     * @return the color to paint the tic-tac-toe lines, and the X's and O's
     */
    public int backgroundColor() {
        return Color.BLUE;
    }

    /**
     * callback method, called whenever it's time to redraw
     * frame
     *
     * @param g the canvas to draw on
     */
    public void onDraw(Canvas g) {

        // update the variables that relate
        // to the dimensions of the animation surface

        // paint the TTT-board's horizontal and vertical lines
        setBackgroundResource(R.drawable.parcheesiboard);

        // if we don't have any state, there's nothing more to draw, so return
        if (parState == null) {
            return;
        }

        // for each square that has an X or O, draw it on the appropriate
        // place on the canvas
        for (int i = 0; i < 4 /* number of players */; i++) {
            for (int j = 0; j < 4; j++) {
                playerXCor = parState.getPawnLocationsXForPlayer(i, j);
                playerYCor = parState.getPawnLocationsYForPlayer(i, j);
                Pawn currLoc = new Pawn(playerXCor, playerYCor);
                currLoc.drawOn(playerXCor, playerYCor, g, i);
            }
        }
    }




}