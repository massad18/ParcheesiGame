package com.example.keokimassad.testparcheesi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by AvayaBhattarai on 11/20/16.
 */

public class Pawn {

    public float xCor;
    public float yCor;
    public float size = 10;
    public Paint paint = new Paint();
    public boolean safe = false;


    public Pawn(float xCor, float yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
    }


    public void drawOn(float xCor, float yCor, Canvas canvas, int playerIndex, boolean selected)
    {
        if(playerIndex == 0)
        {
            if (selected) {
                paint.setColor(Color.MAGENTA);
            }
            else {
                paint.setColor(Color.BLACK);
            }
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(Color.RED);
            canvas.drawCircle(xCor, yCor, size-2, paint);
        }
        else if(playerIndex == 1)
        {
            if (selected) {
                paint.setColor(Color.MAGENTA);
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
                paint.setColor(Color.MAGENTA);
            }
            else {
                paint.setColor(Color.BLACK);
            }
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(xCor, yCor, size-2, paint);
        }
        else
        {
            if (selected) {
                paint.setColor(Color.MAGENTA);
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
