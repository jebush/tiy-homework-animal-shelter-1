package com.andrewRnagel.animalshelter.entity;

import java.util.ArrayList;

import static java.lang.String.format;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:30 PM EST.
 */

public class Animal {
    //object properties
    private String name, breed = "", description;
    private int animalID = -1;
    private ArrayList<Note> animalNotes = new ArrayList<>();
    private Type animalType = new Type();
    private String picture = "/images/X.jpg";

    //constructors
    //default constructor
    public Animal() {}

    //constructor with breed(opt); missing animalID and typeID
    //used when creating a new Animal from within AnimalShelter program
    public Animal(String name, String type, String breed, String desc) {
        this.name = name;
        this.breed = breed;
        this.description = desc;
        this.animalType.setType(type);
    }

    //constructor with breed(opt), including animal index and typeID
    //used when reading an existing animal from the animal table
    public Animal(int animalID, String name, String type, String breed, String desc, int typeID) {
        this.animalID = animalID;
        this.name = name;
        this.breed = breed;
        this.description = desc;
        this.animalType.setType(type);
        this.animalType.setTypeID(typeID);
    }

    //constructor
    public Animal(int animalID, String name, String typename, String breed, String description) {
        this.animalID = animalID;
        this.name = name;
        this.breed = breed;
        this.description = description;
        this.animalType.setType(typename);
    }

    //constructor without breed (add new animal, web version)
    public Animal(Integer animalID, String name, String type, String description, int typeID) {
        this.animalID = animalID;
        this.name = name;
        this.description = description;
        this.animalType.setType(type);
        this.animalType.setTypeID(typeID);
    }

    //methods
    //animal toString (formatted)
    public String toString() {
        String animalNotes = "";
        String animalStats = format("%-12s %-32s\n%-12s %-32s\n%-12s %-32s\n%-12s %-64s\n",
                "Name:", this.name,
                "Type:", this.animalType.getType(),
                "Breed (opt):", this.breed,
                "Description:", this.description);
        if(this.getAnimalNotes().isEmpty()) {
            animalNotes = String.format("%-12s %-64s\n", "Notes:", "No animal notes found for this animal.");
        } else {
            //iterate through and generate string
            for(int i = 0; i < getAnimalNotes().size(); i++) {
                if(i == 0){
                    String noteOne = getAnimalNotes().get(0).getNoteCreationDateAsString().toString() + ": "
                            + getAnimalNotes().get(0).getNoteContent();
                    animalNotes = String.format("%-12s %-64s\n", "Notes:", noteOne);
                } else {
                    String noteNow = getAnimalNotes().get(i).getNoteCreationDateAsString().toString() + ": "
                            + getAnimalNotes().get(i).getNoteContent();
                    String currentNote = String.format("%-12s %-64s\n", "", noteNow);
                    animalNotes = animalNotes + currentNote;
                }
            }
        }
        return animalStats+animalNotes;
    }

    //getters
    public String getName() {
        return this.name;
    }

    public String getBreed() {
        return this.breed;
    }

    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.animalType.getType();
    }

    public int getAnimalID() {
        return this.animalID;
    }

    public int getAnimalTypeID() {
        return this.animalType.getTypeID();
    }

    public ArrayList<Note> getAnimalNotes() {
        return this.animalNotes;
    }

    public String getPicture() {
        return this.picture;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.animalType.setType(type);
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public void setAnimalTypeID(int animalTypeID) {
        this.animalType.setTypeID(animalTypeID);
    }

    public void setAnimalNotes(ArrayList<Note> animalNotes) {
        this.animalNotes = animalNotes;
    }

    public void setPicture(String URL) {
        this.picture = URL;
    }

    //legacy disk serialization operations
    public String serialize() {
        return format("%s|%s|%s|%s\n", name, animalType.getType(), breed, description);
    }

    public static Animal deserialize(String data){
        String[] parsedData = data.split("\\|");
        return new Animal(parsedData[0], parsedData[1], parsedData[2], parsedData[3]);
    }
}