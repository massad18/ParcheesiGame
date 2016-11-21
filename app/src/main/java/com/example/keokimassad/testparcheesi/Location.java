package com.example.keokimassad.testparcheesi;

/*
 * This class is used to store the location of a given pawn.
 */
public class Location {
    /*
     * Variable to store the current location of a given pawn (uses enum LocationType).
     * A pawn can be at home, on the game board, on the home stretch, or at the goal
     */
    public LocationType locationType;
    public int locationIndex; //stores current index of pawn
}
