package com.andrewRnagel.animalShelter;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Andrew Nagel on 8/23/16 at 11:51 AM EST.
 */

public class AnimalsServiceTest {

    //constructor tests
    @Test
    /**
     * Given: Animal Repository
     * When: AnimalsService is instantiated
     * Then: Animal Data is mirrored in AnimalsService
     */
    public void whenAnimalsServiceInstantiatedWithRepositoryDataMirroredCorrectly() throws IOException {
        //Arrange
        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
        //Act
        AnimalsService animalsService = new AnimalsService(dataRepo);
        //Assert
        assertThat(animalsService.getAnimal(0), equalTo(dataRepo.getAnimal(0)));
    }

    //method tests
    @Test
    /**
     * Given: Animal Repository with animals
     * When: Listing all animals
     * Then: Animal Data is correct compared to Animal Repository Inventory
     */
    public void whenAnimalListCalledAllDataCorrectlyDisplayed() throws IOException {
        //Arrange
        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
        AnimalsService animalsService = new AnimalsService(dataRepo);
        //Act
        ArrayList<Animal> animals = animalsService.listAnimals();
        //Assert
        assertThat(animals, equalTo(dataRepo.listAnimals()));
    }

    @Test
    /**
     * Given: Animal Repository with animals
     * When: Adding an animal
     * Then: Animal Data is correctly updated, as compared to Animal Repository Inventory
     */
    public void whenAnimalAddedDataCorrectlyUpdated() throws IOException {
        //Arrange
        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
        AnimalsService animalsService = new AnimalsService(dataRepo);
        Animal vole = new Animal("Vicky", "Vole", "Furry", "Grey");
        //Act
        animalsService.addAnimal(vole);
        //Assert
        assertThat(animalsService.getAnimal(1), equalTo(dataRepo.getAnimal(1)));
        assertThat(vole.getName(), equalTo("Vicky"));
        assertThat(vole.getSpecies(), equalTo("Vole"));
        assertThat(vole.getBreed(), equalTo("Furry"));
        assertThat(vole.getDescription(), equalTo("Grey"));

    }

    @Test
    /**
     * Given: Animal Repository with animals
     * When: Retrieving an animal
     * Then: Animal Data is correctly retrieved, as compared to Animal Repository Inventory
     */
    public void whenAnimalDataCorrectlyRetrieved() throws IOException {
        //Arrange
        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
        AnimalsService animalsService = new AnimalsService(dataRepo);
        //Act
        Animal animal = animalsService.getAnimal(0);
        //Assert
        assertThat(animal, equalTo(dataRepo.getAnimal(0)));
        assertThat(animal.getName(), equalTo("Fred"));
        assertThat(animal.getSpecies(), equalTo("Frog"));
        assertThat(animal.getBreed(), equalTo("Poison Dart"));
        assertThat(animal.getDescription(), equalTo("Green"));
    }

    @Test
    /**
     * Given: Animal Repository with animals
     * When: Removing an animal
     * Then: Animal Data is correctly removed
     */
    public void whenAnimalDataCorrectlyRemoved() throws IOException {
        //Arrange
        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
        AnimalsService animalsService = new AnimalsService(dataRepo);
        //Act
        animalsService.removeAnimal(0);
        //Assert
        assertThat(animalsService.listAnimals().isEmpty(), equalTo(true));
    }
}
