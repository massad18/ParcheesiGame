package com.example.keokimassad.testparcheesi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.Serializable;

/**
 * Class Pawn
 *
 * A class to draw pawns when called. Depending on if it is called when a pawn is selected by the
 * human player GUI, will draw an outline
 *
 * Created by AvayaBhattarai on 11/20/16.
 * @author Avaya Bhattarai
 * @version November 2016
 *
 */

public class Pawn implements Serializable {

    private static final long serialVersionUID = 3443195316586596539L;

    //instance variables

    public float xCor; //x coordinate
    public float yCor; //y coordinate
    public float size = 10; //default size of the pawns
    public Paint paint = new Paint();
    public float selectedSize; //size when selected.

    //Constructor for the Pawn class
    public Pawn(float xCor, float yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
    }

    //Draw method to draw a pawn on the board, will draw both selected pawns and non selected pawns
    public void drawOn(float xCor, float yCor, Canvas canvas, int playerIndex, boolean selected)
    {
        if(playerIndex == 0) //Red player
        {
            if (selected) {
                paint.setColor(Color.LTGRAY); //outline of the pawn
                selectedSize = size-5; //inner color of pawn
            }
            else {
                paint.setColor(Color.BLACK);
                selectedSize = size-2;
            }
            canvas.drawCircle(xCor, yCor, size, paint); //draws outline
            paint.setColor(0xFF9F1515); //sets color to dark red
            canvas.drawCircle(xCor, yCor, selectedSize, paint); //draws inner color
        }
        else if(playerIndex == 1) //Blue player
        {
            if (selected) {
                paint.setColor(Color.LTGRAY);
            }
            else {
                paint.setColor(Color.BLACK);
            }
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(xCor, yCor, size-2, paint);
        }
        else if(playerIndex == 2) //Yellow Player
        {
            if (selected) {
                paint.setColor(Color.LTGRAY);
            }
            else {
                paint.setColor(Color.BLACK);
            }
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(0XFFe5d31d); //sets color to darker yellow
            canvas.drawCircle(xCor, yCor, size-2, paint);
        }
        else //Green Player
        {
            if (selected) {
                paint.setColor(Color.LTGRAY);
            }
            else {
                paint.setColor(Color.BLACK);
            }
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(xCor, yCor, size-2, paint);
        }

    }

}
