package com.andrewRnagel;
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
        //instantiate dependent service classes
        MenuService menu = new MenuService();
        AnimalRepository dataRepo = new AnimalRepository();
        AnimalsService animalsService = new AnimalsService();

        //make file to write data to, if not already present
        dataRepo.makeFile();

        //console-based menu system driven via while loop
        while(true) {
            int action = menu.promptForMainMenuSelection(animalsService);
            if(action == MenuService.LIST_ANIMALS) {
                menu.listAnimals(animalsService);
            } else if(action == MenuService.CREATE_ANIMAL) {
                menu.createNewAnimal(animalsService);
            } else if(action == MenuService.VIEW_ANIMAL_DETAILS) {
                menu.viewAnimalDetails(animalsService);
            } else if(action == MenuService.EDIT_ANIMAL_DETAILS) {
                menu.editAnimal(animalsService);
            } else if(action == MenuService.DELETE_ANIMAL) {
                menu.deleteAnimal(animalsService);
            } else if(action == MenuService.QUIT) {
                menu.quitProgram();
            } else {
                //unreachable block
            }
        }
    }
}