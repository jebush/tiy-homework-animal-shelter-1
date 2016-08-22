package com.andrewRnagel.animalShelter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:33 PM EST.
 */

public class AnimalsService {
    //object properties
    //must be private per assignment constraints
    private ArrayList<Animal> animalList = new ArrayList<>();

    //constructors
    //default constructor
    public AnimalsService() {}

    //constructor with Data Repository
    public AnimalsService(AnimalRepository animalRepository) throws IOException {
        syncAllAnimals(animalRepository);
    }

    //methods
    //return local arrayList holding stored animals
    protected ArrayList<Animal> listAnimals() {
        return animalList;
    }

    //return animal from specified index in local arrayList
    protected Animal getAnimal(int index) {
        return animalList.get(index);
    }

    //add animal to end of local arrayList
    protected void addAnimal(Animal animal) {
        animalList.add(animal);
    }

    //remove animal from specified index in local arrayList
    protected void removeAnimal(int index) {
        animalList.remove(index);
    }

    //supporting methods
    //sync animal list with Animal Repo post-initialization
    protected void syncAllAnimals(AnimalRepository animalRepository) throws IOException{
        this.animalList = animalRepository.loadAllAnimals();
    }
}