package com.andrewRnagel;

/**
 * Created by Andrew Nagel on 8/19/16 at 12:11 PM EST.
 * Purpose: Handle data persistence ONLY.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AnimalRepository {
    //file instantiation
    File currentDirectory = new File(".");
    File dataDir = new File("Data");
    File animalData = new File("Data/Animals.txt");

    //Create data directory and data file on disk
    public void makeFile() throws IOException {
        if(!dataDir.exists()) {
            new File("data").mkdir();
        }
        if(!animalData.exists())
        {
            animalData.createNewFile();
        }
    }

    //TODO
    //Load all animals from disk
    public ArrayList<Animal> loadAllAnimals() {
        //read lines from file, parse, and push to ArrayList in AnimalService
        return null;
    }

    //Save new animal to disk
    public void addNewAnimal(Animal animal) {

    }

    //Overwrite existing animal on disk
    public void deleteExistingAnimal(int index, Animal animal) {

    }

    //Overwrite existing animal on disk
    public void updateExistingAnimal(int index, Animal animal) {

    }
}