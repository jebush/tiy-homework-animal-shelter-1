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
                System.out.println(animalsService.getAnimal(animalIndex));
            } else if(action == MenuService.EDIT_ANIMAL_DETAILS) {
                String[] results = menu.editAnimal(animalsService.listAnimals());
                //process input
                if (results != null) {
                    //local variables
                    int animalIndex = Integer.parseInt(results[0]);
                    String tempName = results[1];
                    String tempSpecies = results[2];
                    String tempBreed = results[3];
                    String tempDescription = results[4];
                    Animal tempAnimal = new Animal();
                    //configure temp animal correctly and replace
                    if (!tempName.equals("")) {
                        tempAnimal.setName(tempName);
                    } else {
                        tempAnimal.setName(animalsService.listAnimals().get(animalIndex).getName());
                    }
                    if (!tempSpecies.equals("")) {
                        tempAnimal.setSpecies(tempSpecies);
                    } else {
                        tempAnimal.setSpecies(animalsService.listAnimals().get(animalIndex).getSpecies());
                    }
                    if (!tempBreed.equals("")) {
                        tempAnimal.setBreed(tempBreed);
                    } else {
                        tempAnimal.setBreed(animalsService.listAnimals().get(animalIndex).getBreed());
                    }
                    if (!tempDescription.equals("")) {
                        tempAnimal.setDescription(tempDescription);
                    } else {
                        tempAnimal.setDescription(animalsService.listAnimals().get(animalIndex).getDescription());
                    }
                    animalsService.updateAnimal(animalIndex, tempAnimal);
                    //output
                    System.out.printf("\nEdit operation successful!\nUpdated record to:\n");
                    System.out.printf(animalsService.getAnimal(animalIndex) + "\n");
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