package com.example.keokimassad.testparcheesi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.Serializable;

/**
 * Created by AvayaBhattarai on 11/20/16.
 */

public class Pawn implements Serializable {

    private static final long serialVersionUID = 3443195316586596539L;

    public float xCor;
    public float yCor;
    public float size = 10;
    public Paint paint = new Paint();
    public boolean safe = false;
    public float selectedSize;


    public Pawn(float xCor, float yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
    }


    public void drawOn(float xCor, float yCor, Canvas canvas, int playerIndex, boolean selected)
    {
        if(playerIndex == 0)
        {
            if (selected) {
                paint.setColor(Color.LTGRAY);
                selectedSize = size-5;
            }
            else {
                paint.setColor(Color.BLACK);
                selectedSize = size-2;
            }
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(0xFF9F1515);
            canvas.drawCircle(xCor, yCor, selectedSize, paint);
        }
        else if(playerIndex == 1)
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
        else if(playerIndex == 2)
        {
            if (selected) {
                paint.setColor(Color.LTGRAY);
            }
            else {
                paint.setColor(Color.BLACK);
            }
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(0XFFe5d31d);
            canvas.drawCircle(xCor, yCor, size-2, paint);
        }
        else
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

    public boolean isSafe()
    {
        return safe;
    }

    public void setSafe(boolean safetyChange)
    {
        safe = safetyChange;
    }

}
