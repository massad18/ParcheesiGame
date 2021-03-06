package com.example.keokimassad.testparcheesi;

import java.io.Serializable;

/**
 * Created by KeokiMassad on 11/18/16.
 */

public class Rect implements Serializable{

    private static final long serialVersionUID = 3443192416886596539L;

    private float top;
    private float bottom;
    private float right;
    private float left;
    private float midH;
    private float midV;
    private float mid;
    //rect, safeZ, trapD, trapU, homebase, neutral (square under the safeZone)
    protected String type;
    // section 1 = [65,68] & [1,4] & [22,29] (blue and red on bottom of screen)
    // seciton 2 = [48,55] & [5,12] (tilted 90˚ CCW)
    // section 3 = [31,38] & [56,63] (upside down 180˚),
    // section 4 = [14,21] & [39,46] (tilted 90˚ CW)
    private int section;

    // normal rectangle board pieces
    // going from top side, clockwise to left side
    // x1 < x2 & y1 < y2
    public Rect (float x1, float x2, float y1, float y2, String s, int i) {
        section = i;
        switch (section) {
            case 1:
                top = y1;
                right = x2;
                bottom = y2;
                left = x1;
                break;
            case 2:
                top = x2;
                right = y2;
                bottom = x1;
                left = y1;
                break;
            case 3:
                top = y2;
                right = x1;
                bottom = y1;
                left = x2;
                break;
            case 4:
                top = x1;
                right = y1;
                bottom = x2;
                left = y2;
                break;
        }

        if (s.equals("rect") || s.equals("safeZ") || s.equals("neutral")) {type = s;}
    }

    // normal trapezoid board pieces where the slope is either going down or up
    // on the right side of the board piece
    // going from top side, clockwise to left side
    // x1 < x2 & y1 < y2
    public Rect (float x1, float x2, float y1, float y2, float middle, String s, int i) {
        section = i;
        switch (section) {
            case 1:
                top = y1;
                right = x2;
                bottom = y2;
                left = x1;
                mid = middle;
                break;
            case 2:
                top = x2;
                right = y2;
                bottom = x1;
                left = y1;
                mid = middle;
                break;
            case 3:
                top = y2;
                right = x1;
                bottom = y1;
                left = x2;
                mid = middle;
                break;
            case 4:
                top = x1;
                right = y1;
                bottom = x2;
                left = y2;
                mid = middle;
                break;
        }
        if (s.equals("trapD") || s.equals("trapU")) {type = s;}
    }

    // homebase squares (L shaped)
    // going from top side, clockwise to left side
    // x1 < x2 < x3 & y1 < y2 < y3
    public Rect (float x1, float mX, float x2, float y1, float mY, float y2, int i) {
        section = i;
        switch (section) {
            case 1:
                top = y1;
                right = x2;
                bottom = y2;
                left = x1;
                midH = mX;
                midV = mY;
                break;
            case 2:
                top = x2;
                right = y2;
                bottom = x1;
                left = y1;
                midH = mY;
                midV = mX;
                break;
            case 3:
                top = y2;
                right = x1;
                bottom = y1;
                left = x2;
                midH = mX;
                midV = mY;
                break;
            case 4:
                top = x1;
                right = y1;
                bottom = x2;
                left = y2;
                midH = mY;
                midV = mX;
                break;
        }
        type = "homebase";
    }

    public boolean contains (float x, float y) {
        if (type.equals("rect")) {
            switch (section) {
                case 1:
                    if (y >= top & y < bottom & x >= left & x <= right) {
                        return true;
                    }
                    break;
                case 2:
                    if (y >= left & y <= right & x > bottom & x <= top) {
                        return true;
                    }
                    break;
                case 3:
                    if (y > bottom & y <= top & x >= right & x <= left) {
                        return true;
                    }
                    break;
                case 4:
                    if (y >= right & y <= left & x >= top & x < bottom) {
                        return true;
                    }
                    break;
            }
        }
        else if (type.equals("neutral")) {
            switch (section) {
                case 1:
                    if (y >= top & y <= bottom & x > left & x < right) {
                        return true;
                    }
                    break;
                case 2:
                    if (y < right & y > left & x <= top & x >= bottom) {
                        return true;
                    }
                    break;
                case 3:
                    if (y >= bottom & y <= top & x > right & x < left) {
                        return true;
                    }
                    break;
                case 4:
                    if (y < left & y > right & x <= bottom & x >= top) {
                        return true;
                    }
                    break;
            }
        }
        else if (type.equals("trapD")) {
            switch (section) {
                case 1:
                    if (y >= top & y < bottom & x >= left & x <= mid) {
                        return true;
                    }
                    else if (x >= mid & y >= ((top-bottom)/(mid-right))*(x-mid)+top & y < bottom) {
                        return true;
                    }
                    break;
                case 2:
                    if (y >= left & y <= mid & x > bottom & x <= top) {
                        return true;
                    }
                    else if (y >= mid & y <= ((mid-right)/(top-bottom))*(x-top)+mid & x > bottom) {
                        return true;
                    }
                    break;
                case 3:
                    if (y > bottom & y <= top & x >= mid & x <= left) {
                        return true;
                    }
                    else if (x <= mid & y <= ((top-bottom)/(mid-right))*(x-mid)+top & y > bottom) {
                        return true;
                    }
                    break;
                case 4:
                    if (y >= mid & y <= left & x >= top & x < bottom) {
                        return true;
                    }
                    else if (y <= mid & y >= ((mid-right)/(top-bottom))*(x-top)+mid & x < bottom) {
                        return true;
                    }
                    break;
            }
        }
        else if (type.equals("trapU")) {
            switch (section) {
                case 1:
                    if (y >= top & y < bottom & x >= mid & x <= right) {
                        return true;
                    } else if (x <= mid & y >= ((top - bottom) / (mid - left)) * (x - mid) + top & y < bottom) {
                        return true;
                    }
                    break;
                case 2:
                    if (y >= mid & y <= right & x > bottom & x <= top) {
                        return true;
                    }
                    else if (y <= mid & y >= ((mid-left)/(top-bottom))*(x-top)+mid & x > bottom) {
                        return true;
                    }
                    break;
                case 3:
                    if (y > bottom & y <= top & x >= right & x <= mid) {
                        return true;
                    }
                    else if (x >= mid & y <= ((top - bottom) / (mid - left)) * (x - mid) + top & y > bottom) {
                        return true;
                    }
                    break;
                case 4:
                    if (y >= right & y <= mid & x >= top & x < bottom) {
                        return true;
                    }
                    else if (y >= mid & y <= ((mid-left)/(top-bottom))*(x-top)+mid & x < bottom) {
                        return true;
                    }
                    break;
            }
        }
        else if (type.equals("safeZ")) {
            switch (section) {
                case 1:
                    if (y >= top & y < bottom & x > left & x < right) {
                        return true;
                    }
                    break;
                case 2:
                    if (y > left & y < right & x <= top & x > bottom) {
                        return true;
                    }
                    break;
                case 3:
                    if (y > bottom & y <= top & x > right & x < left) {
                        return true;
                    }
                    break;
                case 4:
                    if (y > right & y < left & x < bottom & x >= top) {
                        return true;
                    }
                    break;
            }
        }
        else if (type.equals("homebase")) {
            switch (section) {
                case 1:
                    // little square on the top
                    if (y >= top & y <= midV & x > left & x < midH) {
                        return true;
                    }
                    // large rectangle on the bottom
                    else if (y >= midV & y < bottom & x >= left & x <= right) {
                        return true;
                    }
                    break;
                case 2:
                    if (y > left & y < midH & x >= midV & x <= top) {
                        return true;
                    }
                    else if (y >= left & y <= right & x > bottom & x <= midV) {
                        return true;
                    }
                    break;
                case 3:
                    if (y >= midV & y <= top & x > midH & x < left) {
                        return true;
                    }
                    else if (y > bottom & y <= midV & x >= right & x < left) {
                        return true;
                    }
                    break;
                case 4:
                    if (y > midH & y < left & x >= top & x <= midV) {
                        return true;
                    }
                    else if (y >= right & y <= left & x > midV & x <= bottom) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

}
