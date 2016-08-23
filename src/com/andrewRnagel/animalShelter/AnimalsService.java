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
        //copy by reference (all changes to Repo will be effective here!)
        this.animalList = animalRepository.loadAllAnimals();
    }

    //methods
    //return local arrayList holding stored animals
    protected ArrayList<Animal> listAnimals() {
        return animalList;
    }

    //add animal to end of local arrayList
    protected void addAnimal(Animal animal) {
        animalList.add(animal);
    }

    //deprecated methods
    //return animal from specified index in local arrayList
    protected Animal getAnimal(int index) {
        return animalList.get(index);
    }

    //remove animal from specified index in local arrayList
    protected void removeAnimal(int index) {
        animalList.remove(index);
    }
}