package com.andrewRnagel.animalShelter;
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.System.exit;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:34 PM EST.
 */

public class MenuService {
    //object properties
    //static final assignments for main.menuDriver() method
    static final int LIST_ANIMALS = 1, CREATE_ANIMAL = 2, VIEW_ANIMAL_DETAILS = 3,
            EDIT_ANIMAL_DETAILS = 4, DELETE_ANIMAL = 5, QUIT = 6;
    //instantiate scanner to read console input
    private Scanner scanner = new Scanner(System.in);

    //methods (protected)
    //menu prompt
    protected int promptForMainMenuSelection(int dataSize) {
        //interface with user
        System.out.printf("\n***** Main Menu *****\n" +
                "<1> List Animals\n" +
                "<2> Create new Animal\n" +
                "<3> View Animal details\n" +
                "<4> Edit Animal details\n" +
                "<5> Delete Animal\n" +
                "<6> Quit\n");
        return waitForInt(dataSize, "Please choose an option:", true);
    }

    //submenu prompts
    //list subroutine (format: ### name species)
    protected void listAnimals(ArrayList<Animal> animals) {
        //local properties
        int numberAnimals = animals.size();

        //interface with user
        System.out.printf("\n*** List Animals ***\n");
        for (int i = 0; i < numberAnimals; i++) {
            System.out.printf("%-3s %-16s %-16s \n", (i + 1) + ")", animals.get(i).getName(), animals.get(i).getSpecies());
        }
        System.out.printf("[%d] total animal(s) are on record.\n", numberAnimals);
    }

    //create entry subroutine
    protected Animal createNewAnimal() {
        //local properties
        String tempName, tempSpecies, tempBreedOpt, tempDescription;

        //interface with user
        System.out.printf("\n*** Create new Animal ***\n");
        System.out.printf("Please answer the following:\n");
        tempName = requiredInput("Animal Name: ");
        tempSpecies = requiredInput("Species: " );
        tempBreedOpt = optionalInput("Breed (opt.): " );
        tempDescription = requiredInput("Description: ");

        //create object based on data entered from breed entry and add to arrayList
        Animal newEntry = new Animal(tempName, tempSpecies, tempBreedOpt, tempDescription);
        return newEntry;
    }

    //view entry subroutine
    protected int viewAnimalDetails(ArrayList<Animal> animals) {
        //interface with user
        System.out.printf("\n*** View Animal details ***\n");
        int result = waitForInt(animals.size(), "Please enter the ID# of the animal to view: ", false);
        return result - 1;
    }

    //edit entry subroutine
    protected String[] editAnimal(ArrayList<Animal> animals) {
        //interface with user
        System.out.printf("\n*** Edit Animal details ***\n");
        int result = waitForInt(animals.size(), "Please enter the ID# of the animal to edit: ", false);

        //process input (if animal arrayList has at least 1 entry)
        if (result != -1) {
            System.out.printf("Please enter changes below. Press <Enter> to retain current value.");
            int animalNum = result - 1;

            //cycle through four parameters, overwrite data with entry other than ""
            String tempName = optionalInputRetainer(String.format("\nName [%s]: ",
                    animals.get(animalNum).getName()), animals.get(animalNum).getName());
            String tempSpecies = optionalInputRetainer(String.format("Species [%s]: ",
                    animals.get(animalNum).getSpecies()), animals.get(animalNum).getSpecies());
            String tempBreedOpt = optionalInputRetainer(String.format("Breed [%s]: ",
                    animals.get(animalNum).getBreed()), animals.get(animalNum).getBreed());
            String tempDescription = optionalInputRetainer(String.format("Description [%s]: ",
                    animals.get(animalNum).getDescription()), animals.get(animalNum).getDescription());

            //return results to main to complete action
            String[] results = new String[]{Integer.toString(animalNum), tempName, tempSpecies, tempBreedOpt, tempDescription};
            System.out.printf("\nEdit operation successful!\nUpdated record to:\n");
            return results;
        }
        return null;
    }

    //delete entry subroutine
    protected int deleteAnimal(ArrayList<Animal> animals) {
        //interface with user
        System.out.printf("\n*** Delete Animal ***\n");
        int result = waitForInt(animals.size(), "Please enter the ID# of the animal to delete: ", false);
        int animalNum = result - 1;

        //process input (if animal arrayList has at least 1 entry)
        if (result != -1) {
            System.out.print(animals.get(animalNum) + "\n");
            System.out.printf("\nAre you sure you want to delete this animal?\n");
            System.out.printf("Type \"yes\" to confirm, \"no\" to select a different animal.\n" +
                    "Any other data entry will return to the main menu.\n");
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                //confirm delete request
                case "yes":
                case "y":
                    System.out.printf("Deletion operation successful!\n");
                    return animalNum;
                //abort request and return to delete entry subroutine
                case "no":
                case "n":
                    System.out.printf("Deletion operation has been cancelled.\n");
                    deleteAnimal(animals);
                    break;
                //didn't input 'yes' or 'no'
                default:
                    System.out.printf("\"%s\" is not a valid entry! Returning to main menu.\n", input);
            }
        }
        return -1;
    }

    //quit subroutine
    protected void quitProgram() {
        //interface with user
        System.out.printf("\n*** Quit ***\n");
        System.out.printf("Are you sure you want to quit?\n");
        System.out.printf("Type \"yes\" to confirm, \"no\" to cancel.\n");
        String input = scanner.nextLine().toLowerCase();

        //process input
        switch (input) {
            //confirm exit request
            case "yes":
            case "y":
                scanner.close();

                System.out.printf("Goodbye!\n");
                exit(0);
                break;
            //abort exit request, return to menu prompt
            case "no":
            case "n":
                break;
            //invalid input, rerun the quit subroutine
            default:
                System.out.printf("Please try again, \"%s\" is not a valid entry!\n", input);
                quitProgram();
        }
    }

    //print subroutine
    protected void printAnimal(Animal animal) {
        System.out.println(animal);
    }

    //supporting private functions
    //user input integer validator, for menu and/or submenu user selections
    private int waitForInt(int dataSize, String message, boolean mainMenu) {
        //verify animal records exist first
        if ((dataSize == 0) && (!mainMenu)) {
            System.out.printf("[0] total animal(s) are on record.\n");
            return -1;
        }

        //interface with user
        System.out.println(message);
        String input = scanner.nextLine();

        //process input
        int value;
        try {
            value = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.printf("Please try again, \"%s\" is not a valid number!\n", input);
            value = waitForInt(dataSize, message, mainMenu);
        }
        if ((value < 1) ||
                ((value > 6) && (mainMenu)) ||
                ((value > dataSize) && (!mainMenu))) {
            System.out.printf("Please try again. \"%s\" is not a valid number!\n", input);
            value = waitForInt(dataSize, message, mainMenu);
        }
        return value;
    }

    //required input: repeated loop on empty
    private String requiredInput(String prompt) {
        System.out.printf("%s", prompt);
        String input = scanner.nextLine();
        while ((input.isEmpty()) || (input.trim().isEmpty())) {
            System.out.printf("%s is required. Please try again.\n" +
                    "%s", prompt, prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    //optional input: single pass accepts empty
    private String optionalInput(String prompt) {
        System.out.printf(prompt);
        return scanner.nextLine();
    }

    //optional input: single pass accept empty, retains previous value
    private String optionalInputRetainer(String prompt, String returnValue) {
        System.out.printf(prompt);
        String input = scanner.nextLine();
        if (input.trim().equals("")) {
            return returnValue;
        } else {
            return input.trim();
        }
    }
}