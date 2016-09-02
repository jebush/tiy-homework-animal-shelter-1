package com.andrewRnagel.animalShelter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:35 PM EST.
 */

public class Main {
    //methods
    //main method
    public static void main(String[] args) throws IOException, SQLException {
        menuDriver();
    }

    //supporting functions
    //menu driver
    private static void menuDriver() throws IOException, SQLException {
        //instantiate dependent services via classes
        String jdbcUrl = "jdbc:postgresql://localhost/animalshelter";
        AnimalRepository dataRepo = new AnimalRepository(jdbcUrl);
        NoteRepository noteRepo = new NoteRepository(jdbcUrl);
        AnimalsService animalsService = new AnimalsService(dataRepo, noteRepo);
        MenuService menu = new MenuService();

        //console-based menu system driven via while loop
        while(true) {
            //display main menu to user, prompt for selection
            int action = menu.promptForMainMenuSelection();

            //call appropriate menu method based on user selection
            if(action == MenuService.ADD_ANIMAL) {
                Animal newAnimal = menu.addNewAnimal(animalsService.getTypesALL());
                animalsService.addAnimal(newAnimal);
            } else if(action == MenuService.MANAGE_ANIMAL) {
                menu.manageAnimal(animalsService);
            } else if(action == MenuService.MANAGE_ANIMAL_TYPES) {
                menu.manageTypes(animalsService);
            } else if(action == MenuService.QUIT) {
                menu.quitProgram();
            }
        }
    }
}