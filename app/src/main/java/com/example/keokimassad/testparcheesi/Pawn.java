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
    public Color color;
    public Paint paint;


    public Pawn(float xCor, float yCor, float size, Color color) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.size = size;
        this.color = color;
        paint = new Paint();
        paint.setColor(color.RED);

    }


    public float getxCor() {
        return xCor;
    }

    public void setxCor(float xCor) {
        this.xCor = xCor;
    }

    public float getyCor() {
        return yCor;
    }

    public void setyCor(float yCor) {
        this.yCor = yCor;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void drawOn(Canvas canvas) {
        canvas.drawCircle(xCor, yCor, size, paint);
    }


}
