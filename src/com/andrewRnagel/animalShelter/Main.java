package com.andrewRnagel.animalShelter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:35 PM EST.
 */

public class Main {
    //methods
    //main method
    public static void main(String[] args) throws IOException {
        //call menuDriver method
        menuDriver();
    }

    //menu driving method
    private static void menuDriver() throws IOException {
        //instantiate dependent services via classes
        MenuService menu = new MenuService();
        //passing string will specify file name in Data directory
        AnimalRepository dataRepo = new AnimalRepository("Animals.txt");
        //passing dataRepo as argument points to ArrayList from disk by address for local program
        AnimalsService animalsService = new AnimalsService(dataRepo);

        //data source declaration
        ArrayList<Animal> dataStore = animalsService.listAnimals();

        //console-based menu system driven via while loop
        while(true) {
            int action = menu.promptForMainMenuSelection(dataStore.size());

            if(action == MenuService.LIST_ANIMALS) {
                menu.listAnimals(dataStore);
            } else if(action == MenuService.CREATE_ANIMAL) {
                dataStore.add(menu.createNewAnimal());
                //sync repo with local ArrayList
                dataRepo.saveAllAnimals();
            } else if(action == MenuService.VIEW_ANIMAL_DETAILS) {
                menu.viewAnimalDetails(dataStore);
            } else if(action == MenuService.EDIT_ANIMAL_DETAILS) {
                menu.editAnimal(dataStore);
                //sync repo with local ArrayList
                dataRepo.saveAllAnimals();
            } else if(action == MenuService.DELETE_ANIMAL) {
                menu.deleteAnimal(dataStore);
                //sync repo with local ArrayList
                dataRepo.saveAllAnimals();
            } else if(action == MenuService.QUIT) {
                menu.quitProgram();
            } else {
                //unreachable block
            }
        }
    }
}