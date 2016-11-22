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
    public float size;
    public Paint paint;


    public Pawn(float xCor, float yCor, float size, int color) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.size = size;
        paint = new Paint();
        paint.setColor(color);
    }



}
