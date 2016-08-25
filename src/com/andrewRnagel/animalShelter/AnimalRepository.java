package com.andrewRnagel.animalShelter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Andrew Nagel on 8/19/16 at 12:11 PM EST.
 * Purpose: Handle data persistence ONLY.
 */

public class AnimalRepository {
    //object properties
    //local private copy of animal objects
    private ArrayList<Animal> animalList = new ArrayList<>();

    //file instantiation
    private String animalDataDir, animalDataFilePath;
    private File dataDir, animalDataFile;

    //constructors
    //constructor with data file specificity
    public AnimalRepository(String fileName) throws IOException{
        animalDataDir = "Data";
        dataDir = new File(animalDataDir);
        animalDataFilePath = animalDataDir + "/" + fileName;
        animalDataFile = new File(animalDataFilePath);
        makeFile();
        loadAllAnimals();
    }

    //methods cloned from AnimalsService (protected)
    //return local arrayList holding stored animals
    protected ArrayList<Animal> listAnimals() {
        return animalList;
    }

    //add animal to end of local arrayList
    protected void addAnimal(Animal animal) {
        animalList.add(animal);
        try {
            saveAllAnimals();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //return animal from specified index in local arrayList
    protected Animal getAnimal(int index) {
        return animalList.get(index);
    }

    //remove animal from specified index in local arrayList
    protected void removeAnimal(int index) {
        animalList.remove(index);
        try {
            saveAllAnimals();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //update animal at specified index in local arrayList
    protected void updateAnimal(int index, Animal animal) {
        animalList.get(index).setName(animal.getName());
        animalList.get(index).setSpecies(animal.getSpecies());
        animalList.get(index).setBreed(animal.getBreed());
        animalList.get(index).setDescription(animal.getDescription());
        try {
            saveAllAnimals();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //supporting private functions
    //Create data directory and data file on disk
    //called by: AnimalRepository constructor
    private void makeFile() throws IOException {
        //verify and create directory
        if(!dataDir.exists()) {
            new File(animalDataDir).mkdir();
        }
        //verify and create data file
        if(!animalDataFile.exists())
        {
            animalDataFile.createNewFile();
        }
    }

    //Load all animals from disk to List on this class
    //Called by: AnimalRepository constructor
    protected ArrayList<Animal> loadAllAnimals() throws IOException {
        //read all lines from file as a string, parse, and push to ArrayList in AnimalService
        String animalData = new String(Files.readAllBytes(Paths.get(animalDataFilePath)));

        //verify file info is not empty nor has mismatch on delimiters
        int delimiterCount = StringUtils.countMatches(animalData, "|");
        if (!(animalData.isEmpty()) && ((delimiterCount % 3) != 0)) {
            System.out.printf(">>>Error opening ./%s. Operation aborted.<<<", animalDataFilePath);
            return animalList;
        }

        //initial integrity checks passed, deserialize Animals parsing by newline character
        List<String> lines = Files.readAllLines(Paths.get(animalDataFilePath));
        for(String line : lines) {
            animalList.add(Animal.deserialize(line));
        }
        return animalList;
    }

    //Save all animals to disk from List on this class
    //Called by: addAnimal, removeAnimal, updateAnimal
    protected void saveAllAnimals() throws IOException {
        //data collector for file writing
        String animalData = "";

        //go through each animal, turn serializable, and write to file
        for(Animal animal : animalList) {
            animalData = animalData + animal.serialize();
        }
        Files.write(Paths.get(animalDataFilePath), animalData.getBytes());
    }
}