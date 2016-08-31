package com.andrewRnagel.animalShelter;
import java.util.ArrayList;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:30 PM EST.
 */

public class Animal {
    //object properties
    //core attributes of any com.andrewRnagel.Animal object
    private String name, species, breed, description, type;
    //the id is the primary key value assigned by the database, and id for animalType from types lookup table
    private int animalID = -1;
    private int animalType = -1;
    //the ArrayList that holds notes for this animal
    private ArrayList<Note> animalNotes = new ArrayList<>();

    //constructors
    //default constructor
    protected Animal() {}

    //constructor with breed(opt)
    protected Animal(String name, String species, String breed, String desc) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = desc;
    }

    //constructor with breed(opt), including type
    protected Animal(String name, String species, String breed, String desc, String type) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = desc;
        this.type = type;
    }

    //constructor with breed(opt), including animal index from animal table and type index post lookup
    protected Animal(int animalid, String name, String species, String breed, String desc, int typeID, String type) {
        this.animalID = animalid;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = desc;
        this.animalType = typeID;
        this.type = type;
    }

    //methods
    //toString (formatted)
    public String toString() {
        return String.format("%-12s %-16s\n%-12s %-16s\n%-12s %-16s\n%-12s %-16s",
                "Name:", this.name,
                "Species:", this.species,
                "Breed:", this.breed,
                "Description:", this.description);
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

    protected int getAnimalType() {
        return this.animalType;
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

    protected void setAnimalType(int animalType) {
        this.animalType = animalType;
    }

    protected void setAnimalNotes(ArrayList<Note> animalNotes) {
        this.animalNotes = animalNotes;
    }

    //disk operations (deprecated)
    protected String serialize() {
        return String.format("%s|%s|%s|%s\n", name, species, breed, description);
    }

    protected static Animal deserialize(String data){
        String[] parsedData = data.split("\\|");
        return new Animal(parsedData[0], parsedData[1], parsedData[2], parsedData[3]);
    }
}