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
        AnimalRepository dataRepo = new AnimalRepository("Animals.txt");
        AnimalsService animalsService = new AnimalsService(dataRepo);

        //data source declaration
        ArrayList<Animal> dataStore = dataRepo.listAnimals();

        //console-based menu system driven via while loop
        while(true) {
            int action = menu.promptForMainMenuSelection(dataStore.size());

            if(action == MenuService.LIST_ANIMALS) {
                menu.listAnimals(dataStore);
            } else if(action == MenuService.CREATE_ANIMAL) {
                Animal animal = (menu.createNewAnimal());
                dataRepo.addAnimal(animal);
            } else if(action == MenuService.VIEW_ANIMAL_DETAILS) {
                int viewMe = menu.viewAnimalDetails(dataStore);
                if (viewMe != 0) {
                    System.out.print(dataRepo.getAnimal(viewMe) + "\n");
                }
            } else if(action == MenuService.EDIT_ANIMAL_DETAILS) {
                //change made to AnimalsService ArrayList
                menu.editAnimal(dataStore);
                //synced ArrayList on AnimalRepository is saved to disk
                //TODO update animal (return index and animal object, then overwrite in repo)
                //dataRepo.updateAnimal(index, animal);
                dataRepo.saveAllAnimals();
            } else if(action == MenuService.DELETE_ANIMAL) {
                int deleteMe = menu.deleteAnimal(dataStore);
                if(deleteMe > 0) {
                    dataRepo.removeAnimal(deleteMe);
                }
            } else if(action == MenuService.QUIT) {
                menu.quitProgram();
            }
        }
    }
}