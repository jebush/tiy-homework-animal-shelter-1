package com.andrewRnagel.animalShelter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Andrew Nagel on 8/19/16 at 12:11 PM EST.
 * Purpose: Handle data persistence ONLY.
 */

public class AnimalRepository {
    //object properties
    //local private copy of animals from AnimalService
    private ArrayList<Animal> animalList = new ArrayList<>();

    //file instantiation
    //File currentDirectory = new File(".");
    private String animalDataFilePath = "Data/Animals.txt";
    private File animalDataFile = new File(animalDataFilePath);

    //Create data directory and data file on disk
    protected void makeFile() throws IOException {
        //local properties
        String animalDataDir = "Data";
        File dataDir = new File(animalDataDir);
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

    //Load all animals from disk (to ArrayList on this class)
    protected ArrayList<Animal> loadAllAnimals() throws IOException {
        //read all lines from file as a string, parse, and push to ArrayList in AnimalService
        String animalData = new String(Files.readAllBytes(Paths.get(animalDataFilePath)));

        //verify file info is not empty nor has mismatch on delimiters
        int delimiterCount = StringUtils.countMatches(animalData, "|");
        if (animalData.isEmpty() || ((delimiterCount % 3) != 0)) {
            return animalList;
        }

        //initial integrity checks passed, deserialize Animals parsing by newline character
        String[] parsedAnimalData = animalData.split("\\n");
        for (int i = 0; i < parsedAnimalData.length; i++) {
            //add Animal to local
            animalList.add(i, Animal.deserialize(parsedAnimalData[i]));
        }
        //alternative implementation
        //List<String> lines = Files.readAllLines(Paths.get("Data/Animals.txt"));
        //for(string line : lines) {
        //  animalList.add(Animal.deserialize(line));
        //}
        return animalList;
    }

    //Save all animals to disk (from ArrayList on Class)
    protected void saveAllAnimals(ArrayList<Animal> animals) throws IOException {
        //copy working list from AnimalsService Class
        animalList = animals;
        //data collector for file writing
        String animalData = "";

        //go through each animal, turn serializable, and write to file
        for(Animal animal : animalList) {
            animalData = animalData + animal.serialize();
        }
        Files.write(Paths.get(animalDataFilePath), animalData.getBytes());
    }
}