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
        AnimalRepository dataRepo = new AnimalRepository("Animals.txt");
        AnimalsService animalsService = new AnimalsService(dataRepo);
        MenuService menu = new MenuService();

        //console-based menu system driven via while loop
        while(true) {
            int action = menu.promptForMainMenuSelection(animalsService.listAnimals().size());

            if(action == MenuService.LIST_ANIMALS) {
                menu.listAnimals(animalsService.listAnimals());
            } else if(action == MenuService.CREATE_ANIMAL) {
                Animal animal = menu.createNewAnimal();
                animalsService.addAnimal(animal);
            } else if(action == MenuService.VIEW_ANIMAL_DETAILS) {
                int animalIndex = menu.viewAnimalDetails(animalsService.listAnimals());
                if(animalIndex >= 0) {
                    menu.printAnimal(animalsService.getAnimal(animalIndex));
                }
            } else if(action == MenuService.EDIT_ANIMAL_DETAILS) {
                String[] results = menu.editAnimal(animalsService.listAnimals());
                if (results != null && !results[0].equals(-1)) {
                    int animalIndex = Integer.parseInt(results[0]);
                    Animal animal = new Animal(results[1], results[2], results[3], results[4]);
                    animalsService.updateAnimal(animalIndex, animal);
                    menu.printAnimal(animalsService.getAnimal(animalIndex));
                }
            } else if(action == MenuService.DELETE_ANIMAL) {
                int deleteMe = menu.deleteAnimal(animalsService.listAnimals());
                if(deleteMe >= 0) {
                    animalsService.removeAnimal(deleteMe);
                }
            } else if(action == MenuService.QUIT) {
                menu.quitProgram();
            }
        }
    }
}