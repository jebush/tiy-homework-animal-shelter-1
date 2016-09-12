package com.andrewRnagel.animalShelter;
import static java.lang.String.format;
import java.util.ArrayList;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:30 PM EST.
 */

public class Animal {
    //object properties
    private String name, type, breed, description;
    private int animalID = -1, animalTypeID = -1;
    private ArrayList<Note> animalNotes = new ArrayList<>();

    //constructors
    //default constructor
    protected Animal() {}

    //constructor with breed(opt); missing animalID and typeID
    //used when creating a new Animal from within AnimalShelter program
    protected Animal(String name, String type, String breed, String desc) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.description = desc;
    }

    //constructor with breed(opt), including animal index and typeID
    //used when reading an existing animal from the animal table
    protected Animal(int animalID, String name, String type, String breed, String desc, int typeID) {
        this.animalID = animalID;
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.description = desc;
        this.animalTypeID = typeID;
    }

    //methods
    //animal toString (formatted)
    public String toString() {
        String animalNotes = "";
        String animalStats = String.format("%-12s %-32s\n%-12s %-32s\n%-12s %-32s\n%-12s %-64s\n",
                "Name:", this.name,
                "Type:", this.type,
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
    protected String getName() {
        return this.name;
    }

    protected String getBreed() {
        return this.breed;
    }

    protected String getDescription() {
        return this.description;
    }

    protected String getType() {
        return this.type;
    }

    protected int getAnimalID() {
        return this.animalID;
    }

    protected int getAnimalTypeID() {
        return this.animalTypeID;
    }

    protected ArrayList<Note> getAnimalNotes() {
        return this.animalNotes;
    }

    //setters
    protected void setName(String name) {
        this.name = name;
    }

    protected void setBreed(String breed) {
        this.breed = breed;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    protected void setAnimalTypeID(int animalTypeID) {
        this.animalTypeID = animalTypeID;
    }

    protected void setAnimalNotes(ArrayList<Note> animalNotes) {
        this.animalNotes = animalNotes;
    }

    //legacy disk serialization operations
    protected String serialize() {
        return format("%s|%s|%s|%s\n", name, type, breed, description);
    }

    protected static Animal deserialize(String data){
        String[] parsedData = data.split("\\|");
        return new Animal(parsedData[0], parsedData[1], parsedData[2], parsedData[3]);
    }
}