package com.andrewRnagel.animalShelter;
import java.util.ArrayList;

import static java.lang.String.format;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:30 PM EST.
 */

public class Animal {
    //object properties
    //core attributes of any com.andrewRnagel.Animal object
    private String name, species, breed, description, type;
    //the id is the primary key value assigned by the main animal table, and id for animalTypeID from types lookup table
    private int animalID = -1, animalTypeID = -1;
    //the ArrayList that holds notes for this animal
    private ArrayList<Note> animalNotes = new ArrayList<>();

    //constructors
    //default constructor
    protected Animal() {}

    //legacy constructor with breed(opt)
    protected Animal(String name, String species, String breed, String desc) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = desc;
    }

    //constructor with breed(opt), including animal type; missing animalID and typeID
    //used when creating a new Animal from within AnimalShelter program
    protected Animal(String name, String species, String breed, String desc, String type) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = desc;
        this.type = type;
    }

    //constructor with breed(opt), including animal index
    //used when reading an existing animal from the animal table
    protected Animal(int animalid, String name, String species, String breed, String desc, int typeID, String type) {
        this.animalID = animalid;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = desc;
        this.animalTypeID = typeID;
        this.type = type;
    }

    //methods
    //animal toString (formatted)
    public String toString() {
        String animalNotes = "";
        String animalStats = String.format("%-12s %-32s\n%-12s %-32s\n%-12s %-32s\n%-12s %-32s\n%-12s %-64s\n%-12s ",
                "Name:", this.name,
                "Type:", this.type,
                "Species:", this.species,
                "Breed (opt):", this.breed,
                "Description:", this.description,
                "Notes:");
        if(this.getAnimalNotes().isEmpty()) {
            animalNotes = String.format("No animal notes found for this animal.\n");
        } else {
            animalNotes = "\n";
            //iterate through and generate string
            for(Note note : getAnimalNotes()) {
                String currentNote = String.format("%-12s %s: %-64s\n", "", note.getNoteCreationDate().toString(), note.getNoteContent());
                animalNotes = animalNotes + currentNote;
            }
        }
        return animalStats+animalNotes;
    }

    //getters
    protected String getName() {
        return this.name;
    }

    protected String getSpecies() {
        return this.species;
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

    protected void setSpecies(String species) {
        this.species = species;
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
        return format("%s|%s|%s|%s\n", name, species, breed, description);
    }

    protected static Animal deserialize(String data){
        String[] parsedData = data.split("\\|");
        return new Animal(parsedData[0], parsedData[1], parsedData[2], parsedData[3]);
    }
}