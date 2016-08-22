package com.andrewRnagel.animalShelter;
import java.io.IOException;

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
        //passing dataRepo as argument clones ArrayList from disk to local program
        AnimalsService animalsService = new AnimalsService(dataRepo);

        //console-based menu system driven via while loop
        while(true) {
            int action = menu.promptForMainMenuSelection(animalsService);

            if(action == MenuService.LIST_ANIMALS) {
                menu.listAnimals(animalsService);
            } else if(action == MenuService.CREATE_ANIMAL) {
                menu.createNewAnimal(animalsService);
                //sync repo with local program ArrayList
                dataRepo.saveAllAnimals(animalsService.listAnimals());
            } else if(action == MenuService.VIEW_ANIMAL_DETAILS) {
                menu.viewAnimalDetails(animalsService);
            } else if(action == MenuService.EDIT_ANIMAL_DETAILS) {
                menu.editAnimal(animalsService);
                //sync repo with local program ArrayList
                dataRepo.saveAllAnimals(animalsService.listAnimals());
            } else if(action == MenuService.DELETE_ANIMAL) {
                menu.deleteAnimal(animalsService);
                //sync repo with local program ArrayList
                dataRepo.saveAllAnimals(animalsService.listAnimals());
            } else if(action == MenuService.QUIT) {
                menu.quitProgram();
            } else {
                //unreachable block
            }
        }
    }
}