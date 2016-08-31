//package com.andrewRnagel.animalShelter;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.*;
//
///**
// * Created by Andrew Nagel on 8/23/16 at 11:51 AM EST.
// */
//
//public class AnimalsServiceTest {
//
//    //constructor tests
//    @Test
//    /**
//     * Given: Animal Repository
//     * When: AnimalsService is instantiated
//     * Then: Animal Data is mirrored in AnimalsService
//     */
//    public void whenAnimalsServiceInstantiatedWithRepositoryDataMirroredCorrectly() throws IOException {
//        //Arrange
//        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
//        Animal frog = new Animal("Fred", "Frog", "Poison Dart", "Green");
//        dataRepo.addAnimal(frog);
//        //Act
//        AnimalsService animalsService = new AnimalsService(dataRepo);
//        //Assert
//        assertThat(animalsService.getAnimal(0), equalTo(dataRepo.getAnimal(0)));
//        //post clean-up
//        animalsService.removeAnimal(0);
//    }
//
//    //method tests
//    @Test
//    /**
//     * Given: Animal Repository with animals
//     * When: Listing all animals
//     * Then: Animal Data is correct compared to Animal Repository Inventory
//     */
//    public void whenAnimalListCalledAllDataCorrectlyReturned() throws IOException {
//        //Arrange
//        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
//        Animal frog = new Animal("Fred", "Frog", "Poison Dart", "Green");
//        Animal bat = new Animal("Bob", "Bat", "Vampire", "Black");
//        AnimalsService animalsService = new AnimalsService(dataRepo);
//        animalsService.addAnimal(frog);
//        animalsService.addAnimal(bat);
//        //Act
//        ArrayList<Animal> animals = animalsService.listAnimals();
//        //Assert
//        assertThat(animals, equalTo(dataRepo.listAnimals()));
//        //post clean-up
//        animalsService.removeAnimal(1);
//        animalsService.removeAnimal(0);
//    }
//
//    @Test
//    /**
//     * Given: Animal Repository with animals
//     * When: Adding an animal
//     * Then: Animal Data is correctly updated, as compared to Animal Repository Inventory
//     */
//    public void whenAnimalAddedDataCorrectlyUpdated() throws IOException {
//        //Arrange
//        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
//        AnimalsService animalsService = new AnimalsService(dataRepo);
//        Animal vole = new Animal("Vicky", "Vole", "Furry", "Grey");
//        //Act
//        animalsService.addAnimal(vole);
//        //Assert
//        assertThat(animalsService.getAnimal(0), equalTo(dataRepo.getAnimal(0)));
//        assertThat(vole.getName(), equalTo("Vicky"));
//        assertThat(vole.getSpecies(), equalTo("Vole"));
//        assertThat(vole.getBreed(), equalTo("Furry"));
//        assertThat(vole.getDescription(), equalTo("Grey"));
//        //post clean-up
//        animalsService.removeAnimal(0);
//    }
//
//    @Test
//    /**
//     * Given: Animal Repository with animals
//     * When: Retrieving an animal
//     * Then: Animal Data is correctly retrieved, as compared to Animal Repository Inventory
//     */
//    public void whenAnimalDataRetrievedMatchesRepo() throws IOException {
//        //Arrange
//        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
//        AnimalsService animalsService = new AnimalsService(dataRepo);
//        Animal vole = new Animal("Vicky", "Vole", "Furry", "Grey");
//        animalsService.addAnimal(vole);
//        //Act
//        Animal animal = animalsService.getAnimal(0);
//        //Assert
//        assertThat(animal, equalTo(dataRepo.getAnimal(0)));
//        assertThat(animal.getName(), equalTo("Vicky"));
//        assertThat(animal.getSpecies(), equalTo("Vole"));
//        assertThat(animal.getBreed(), equalTo("Furry"));
//        assertThat(animal.getDescription(), equalTo("Grey"));
//        //post clean-up
//        animalsService.removeAnimal(0);
//    }
//
//    @Test
//    /**
//     * Given: Animal Repository with animals
//     * When: Removing an animal
//     * Then: Animal Data is correctly removed
//     */
//    public void whenAnimalDataCorrectlyRemoved() throws IOException {
//        //Arrange
//        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
//        AnimalsService animalsService = new AnimalsService(dataRepo);
//        Animal vole = new Animal("Vicky", "Vole", "Furry", "Grey");
//        animalsService.addAnimal(vole);
//        //Act
//        animalsService.removeAnimal(0);
//        //Assert
//        assertThat(animalsService.listAnimals().isEmpty(), equalTo(true));
//    }
//
//    @Test
//    /**
//     * Given: Animal Repository with animals
//     * When: Updating an animal
//     * Then: Animal Data is correctly updated
//     */
//    public void whenAnimalDataCorrectlyUpdated() throws IOException {
//        //Arrange
//        AnimalRepository dataRepo = new AnimalRepository("Test.txt");
//        AnimalsService animalsService = new AnimalsService(dataRepo);
//        Animal vole = new Animal("Vicky", "Vole", "Furry", "Grey");
//        animalsService.addAnimal(vole);
//        Animal revisedAnimal = new Animal("Fred", "Frog", "Poison Dart", "Green");
//        //Act
//        animalsService.updateAnimal(0, revisedAnimal);
//        //Assert
//        assertThat(animalsService.getAnimal(0), equalTo(dataRepo.getAnimal(0)));
//        assertThat(revisedAnimal.getName(), equalTo("Fred"));
//        assertThat(revisedAnimal.getSpecies(), equalTo("Frog"));
//        assertThat(revisedAnimal.getBreed(), equalTo("Poison Dart"));
//        assertThat(revisedAnimal.getDescription(), equalTo("Green"));
//        //post clean-up
//        animalsService.removeAnimal(0);
//
//    }
//
//}
