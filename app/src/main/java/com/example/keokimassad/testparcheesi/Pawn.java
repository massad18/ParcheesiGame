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
    public Paint paint;


    public Pawn(float xCor, float yCor, float size, int color) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.size = size;
        paint = new Paint();
        paint.setColor(color);
    }


    public void drawOn(float xCor, float yCor, Canvas canvas, int playerIndex)
    {
        if(playerIndex == 0)
        {
            paint.setColor(Color.BLACK);
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(Color.RED);
            canvas.drawCircle(xCor, yCor, size-2, paint);
        }
        else if(playerIndex == 1)
        {
            paint.setColor(Color.BLACK);
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(xCor, yCor, size-2, paint);
        }
        else if(playerIndex == 2)
        {
            paint.setColor(Color.BLACK);
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(xCor, yCor, size-2, paint);
        }
        else
        {
            paint.setColor(Color.BLACK);
            canvas.drawCircle(xCor, yCor, size, paint);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(xCor, yCor, size-2, paint);
        }

    }


}
