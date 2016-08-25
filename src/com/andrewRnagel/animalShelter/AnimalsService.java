package com.andrewRnagel.animalShelter;
import java.util.ArrayList;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:33 PM EST.
 */

public class AnimalsService {
    //object properties
    //must be private per assignment constraints
    private AnimalRepository animalRepository;

    //constructors
    //constructor with specific data repository
    public AnimalsService(AnimalRepository animalRepository) {
        //copy by reference (all changes to Repo will be effective here!)
        this.animalRepository = animalRepository;
    }

    //methods
    //return list holding stored animal objects
    protected ArrayList<Animal> listAnimals() {
        return animalRepository.listAnimals();
    }

    //add animal to end of list
    protected void addAnimal(Animal animal) {
        animalRepository.addAnimal(animal);
    }

    //return animal from specified index
    protected Animal getAnimal(int index) {
        return animalRepository.getAnimal(index);
    }

    //remove animal from specified index
    protected void removeAnimal(int index) {
        animalRepository.removeAnimal(index);
    }

    //update animal from specified index
    protected void updateAnimal(int index, Animal animal) {
        animalRepository.updateAnimal(index, animal);
    }
}