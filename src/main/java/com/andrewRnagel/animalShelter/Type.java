package com.andrewRnagel.animalShelter;

/**
 * Created by Andrew Nagel on 9/12/16 at 3:38 PM EST.
 */

public class Type {
    //object properties
    private String type;
    private int typeID = -1;

    //constructors
    //default constructor
    public Type() {}

    //getters
    public int getTypeID() {
        return this.typeID;
    }

    public String getType() {
        return this.type;
    }

    //setters
    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public void setType(String type) {
        this.type = type;
    }
}