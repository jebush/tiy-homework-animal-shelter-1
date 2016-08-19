package com.andrewRnagel;

/**
 * Created by Andrew Nagel on 8/19/16 at 12:11 PM EST.
 * Purpose: Handle data persistence ONLY.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;

public class AnimalRepository {
    //object properties
    private ArrayList<Animal> animalList = new ArrayList<>();
    //file instantiation
    //File currentDirectory = new File(".");
    File dataDir = new File("Data");
    File animalData = new File("Data/Animals.txt");


    //Create data directory and data file on disk
    protected void makeFile() throws IOException {
        if(!dataDir.exists()) {
            new File("data").mkdir();
        }
        if(!animalData.exists())
        {
            animalData.createNewFile();
        }
    }

    //Load all animals from disk (to ArrayList on Class)
    protected ArrayList<Animal> loadAllAnimals() throws IOException {
        //read lines from file, parse, and push to ArrayList in AnimalService
        String animalData = new String(Files.readAllBytes(Paths.get("Data/Animals.txt")));

        //check to verify file is not empty or has mismatch on delimiters
        int delimiterCount = StringUtils.countMatches(animalData, "|");
        if (animalData.isEmpty() || ((delimiterCount % 3) != 0)) {
            return animalList;
        }

        //initial checks passed, deserialize Animals by newline character
        String[] parsedAnimalData = animalData.split("\\n");
        for (int i = 0; i < parsedAnimalData.length; i++) {
            animalList.add(i, Animal.deserialize(parsedAnimalData[i]));
        }
        return animalList;
    }

    //Save all animals to disk (from ArrayList on Class)
    protected void saveAllAnimals(ArrayList<Animal> animals) throws IOException {
        //copy working list from AnimalsService Class
        animalList = animals;
        //data collector for file writing
        String animalData = "";

        //go thru each animal, turn serializable, and write to file
        for(Animal animal : animalList) {
            animalData = animalData + animal.serialize();
        }
        Files.write(Paths.get("Data/Animals.txt"), animalData.getBytes());
    }

    //TODO
    //Save new animal to disk
    protected void addNewAnimal(Animal animal) {
    }

    //TODO
    //Overwrite existing animal on disk
    protected void deleteExistingAnimal(int index, Animal animal) {

    }

    //TODO
    //Overwrite existing animal on disk
    protected void updateExistingAnimal(int index, Animal animal) {

    }
}