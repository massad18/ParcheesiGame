package com.example.keokimassad.testparcheesi;

/**
 * Created by KeokiMassad on 11/15/16.
 */

public class ParPlayer {
    private int loc0;
    private int loc1;
    private int loc2;
    private int loc3;

    void parPlayer() {
        for (int i = 0; i < 4; i++) {
            initLocation(i);
        }
    }

    public void initLocation(int number) {
        switch (number) {
            case 0: loc0 = 0;
                break;
            case 1: loc1 = 1;
                break;
            case 2: loc2 = 2;
                break;
            case 3: loc3 = 3;
                break;
        }
    }

    public void setLocation(int number, int loc) {
        switch (number) {
            case 0: loc0 = loc;
                break;
            case 1: loc1 = loc;
                break;
            case 2: loc2 = loc;
                break;
            case 3: loc3 = loc;
                break;
        }
    }

    public int getLocation(int number) {
        switch (number) {
            case 0: return loc0;
            case 1: return loc1;
            case 2: return loc2;
            case 3: return loc3;
        }
        return -1;
    }
}
