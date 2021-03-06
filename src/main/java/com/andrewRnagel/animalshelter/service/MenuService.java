package com.andrewRnagel.animalshelter.service;

import com.andrewRnagel.animalshelter.entity.Animal;
import com.andrewRnagel.animalshelter.entity.Note;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:34 PM EST.
 */

public class MenuService {
    //object properties
    public static final int ADD_ANIMAL = 1, MANAGE_ANIMAL = 2, MANAGE_ANIMAL_TYPES = 3, QUIT = 4;
    private Scanner scanner = new Scanner(System.in);
    private AnimalsService animalsService;

    //constructors
    //default constructor
    public MenuService(AnimalsService animalsService) {
        this.animalsService = animalsService;
    }

    //methods
    //main menu prompt
    public int promptForMainMenuSelection() {
        //interface with user
        System.out.printf("\n***** Main Menu *****\n" +
                "<1> Add a new animal\n" +
                "<2> Manage an existing animal\n" +
                "<3> Manage animal types\n" +
                "<4> Quit\n");
        return waitForInt("Please choose an option: ", 4);
    }

    //menu option 1: add new animal
    public Animal addNewAnimal(ArrayList<String> types) {
        //local properties
        String tempName, tempType, tempBreedOpt, tempDescription;

        //interface with user
        System.out.printf("\n*** Add a new Animal ***\n");
        System.out.printf("Please answer the following:\n");
        tempName = requiredInput("Name");
        tempType = requiredInputType("Type (" + listTypesAsString(types) + "): ", types);
        tempBreedOpt = optionalInput("Breed (opt.): ");
        tempDescription = requiredInput("Description");

        //return a new animal object based on data entered
        Animal newEntry = new Animal(tempName, tempType, tempBreedOpt, tempDescription);
        System.out.printf("\nOperation successful! Animal %s added.\n", tempName);
        return newEntry;
    }

    //menu option 2: manage existing animal
    public void manageAnimal() throws SQLException {
        //search for animal by type, name, animalID, or all animals
        //interface with user
        System.out.printf("\n*** Manage an existing animal ***\n");
        if (!(this.animalsService.getAllAnimals().size() == 0)) {
            int tempID = searchAnimals();
            if (tempID != -1) {
                printAnimal(this.animalsService.getAnimal(tempID));
                int choice = printManageAnimalMenu();
                switch (choice) {
                    //edit animal
                    case 1:
                        editAnimal(this.animalsService.getAnimal(tempID));
                        break;
                    //delete animal
                    case 2:
                        deleteAnimal(tempID);
                        break;
                    //add note
                    case 3:
                        addNote(tempID);
                        break;
                    //return to main menu
                    case 4:
                    default:
                        break;
                }
            }
        } else {
            System.out.printf("Invalid request. [0] total animals are on record.\n");
        }
    }

    //menu option 3: manage existing types
    public void manageTypes() throws SQLException {
        if (!(this.animalsService.getAllTypesAsStrings().size() == 0)) {
            //local properties
            String tempType;

            //interface with user
            System.out.printf("\n*** Manage Types ***\n" +
                    "<1> Add a new type\n" +
                    "<2> Edit existing type\n" +
                    "<3> Delete existing type\n" +
                    "<4> Return to main menu\n");
            int input = waitForInt("Please choose an option: ", 4);

            //process input
            switch (input) {
                //add new type
                case 1:
                    System.out.printf("Please input the type to add:\n");
                    tempType = requiredInput("Type");
                    tempType = normalizeString(tempType);
                    if (this.animalsService.getAllTypesAsStrings().contains(tempType)) {
                        System.out.printf("Operation failed! Type %s already exists!\n", tempType);
                    } else {
                        this.animalsService.addType(tempType);
                        System.out.printf("Operation successful! Type %s added.\n", tempType);
                    }
                    manageTypes();
                    break;
                //edit type
                case 2:
                    System.out.printf("\n*** Current Types ***\n");
                    printTypes(this.animalsService.getAllTypesAsStrings());
                    System.out.printf("\n*** Edit type ***\n");
                    System.out.printf("Please input the type to edit:\n");
                    tempType = normalizeString(requiredInputType("Type: ", this.animalsService.getAllTypesAsStrings()));
                    //prompt what to change, then update typename
                    System.out.printf("Please input new text for type '" + tempType + "':\n");
                    String editType = requiredInput("Type");
                    this.animalsService.updateType(this.animalsService.getTypeIDByName(tempType), editType);
                    System.out.printf("'" + tempType + "' has been changed to '" + editType + "'!\n");
                    manageTypes();
                    break;
                //delete existing type
                case 3:
                    System.out.printf("\n*** Current Types ***\n");
                    printTypes(this.animalsService.getAllTypesAsStrings());
                    System.out.printf("Please input the type to delete:\n");
                    tempType = normalizeString(requiredInput("Type"));
                    if (this.animalsService.listAllAnimalsWithType(tempType).isEmpty() && this.animalsService.getAllTypesAsStrings().contains(tempType)) {
                        this.animalsService.removeType(this.animalsService.getTypeIDByName(tempType));
                        System.out.printf("Operation successful! Type %s deleted!\n", tempType);
                    } else if(!this.animalsService.getAllTypesAsStrings().contains(tempType)) {
                        System.out.printf("Operation failed! Type %s does not exist!\n", tempType);
                    } else {
                        System.out.printf("Operation failed! Type %s currently in use!\n", tempType);
                    }
                    manageTypes();
                    break;
                //return to main menu
                case 4:
                default:
                    break;
            }
        } else {
            System.out.printf("Invalid request. [0] total types are on record.\n");
        }
    }

    //menu option 4: quit program
    public void quitProgram() {
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
            //invalid input, re-run quit prompt
            default:
                System.out.printf("Please try again, \"%s\" is not a valid entry!\n", input);
                quitProgram();
        }
    }

    //supporting functions
    //turn an arrayList of animal types into an english-style string: (x, y, or z), (x or y), (x).
    //Called by: addNewAnimal
    private String listTypesAsString(ArrayList<String> types) {
        String result = "";
        switch (types.size()) {
            case 1:
                result = result + types.get(0);
                break;
            case 2:
                result = result + types.get(0) + " or " + types.get(1);
                break;
            default:
                for (int i = 0; i < types.size(); i++) {
                    if ((i + 1) != types.size()) {
                        result = result + types.get(i) + ", ";
                    } else {
                        result = result + "or " + types.get(i);
                    }
                }
                break;
        }
        return result;
    }

    //return animalID for the animal the user wishes to manage
    //Called by: manageAnimal
    private int searchAnimals() throws SQLException {
        //local properties
        int result = -1;
        ArrayList<String> types = this.animalsService.getAllTypesAsStrings();
        ArrayList<Animal> results;
        String query;

        //interface with user
        System.out.printf("Search for animals:");
        System.out.printf("\n<1> By type\n" +
                "<2> By name\n" +
                "<3> By ID number\n" +
                "<4> From list of all animals\n" +
                "<5> Return to main menu\n");
        System.out.printf("How would you like to search for an animal?\n");
        int input = waitForInt("Please choose an option: ", 5);

        //process input
        //use search methods to return animalID
        switch (input) {
            //Type
            case 1:
                System.out.printf("Please input a type below:\n");
                query = requiredInputType("Type (" + listTypesAsString(types) + "): ", types);
                results = this.animalsService.listAllAnimalsWithType(query);
                result = printAnimalsReturnID(results);
                break;
            //Name
            case 2:
                System.out.printf("Please input a name below:\n");
                query = requiredInput("Name");
                results = this.animalsService.listAllAnimalsWithName(query);
                result = printAnimalsReturnID(results);
                break;
            //ID
            case 3:
                System.out.printf("Please input an ID below:\n");
                result = waitForInt("Animal ID: ", this.animalsService.getAllAnimals());
                break;
            //ALL
            case 4:
                results = this.animalsService.getAllAnimals();
                result = printAnimalsReturnID(results);
                break;
            //Exit to main menu
            case 5:
            default:
                break;
        }
        return result;
    }

    //Use arrayList of animal results to determine what animal, if any, the user wants to manage
    //Called by: manageAnimal/searchAnimals
    private int printAnimalsReturnID(ArrayList<Animal> animals) {
        //local properties
        int result = -1;

        //get valid int(check), then set result equal
        if (!animals.isEmpty()) {
            printAnimals(animals);
            System.out.println();
            result = waitForInt("Which animal do you want to manage?: ", animals);
        } else {
            System.out.printf("[0] animals are on record matching criterion.\n");
        }
        return result;
    }

    //print one or more animals from an ArrayList in the search results
    //Called by: manageAnimal/searchAnimals/printAnimalsReturnID
    private void printAnimals(ArrayList<Animal> animals) {
        String id = "ID", name = "NAME", type = "TYPE";
        System.out.printf("\n*** List of animals ***\n" +
                "%-3s | %-16s | %-16s\n" +
                "++++++++++++++++++++++++++++++++++++++", id, name, type);
        for (Animal animal : animals) {
            id = Integer.toString(animal.getAnimalID());
            name = animal.getName();
            type = animal.getType();
            System.out.printf("\n%-3s | %-16s | %-16s", id, name, type);
        }
    }

    //print animal details for single animal
    //Called by: manageAnimal
    private void printAnimal(Animal animal) {
        System.out.println("\n*** Animal Details *** ");
        System.out.println(animal);
    }

    //submenu for manage animal
    //Called by: manageAnimal
    private int printManageAnimalMenu() {
        //interface with user
        System.out.printf("*** Manage animal ***\n" +
                "<1> Edit this animal\n" +
                "<2> Delete this animal\n" +
                "<3> Add note for this animal\n" +
                "<4> Return to main menu\n");
        int input = waitForInt("Please choose an option: ", 4);
        return input;
    }

    //delete animal record
    //Called by: manageAnimal
    private void deleteAnimal(int animalID) throws SQLException {
        System.out.printf("\nAre you sure you want to delete this animal?\n");
        System.out.printf("Type \"yes\" to confirm, \"no\" to select a different animal.\n" +
                "Any other data entry will return to the main menu.\n");
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            //confirm delete request
            case "yes":
            case "y":
                this.animalsService.removeAnimal(animalID);
                System.out.printf("Deletion operation successful!\n");
                break;
            //abort request and return to delete entry subroutine
            case "no":
            case "n":
                System.out.printf("Deletion operation has been cancelled.\n");
                break;
            //didn't input 'yes' or 'no'
            default:
                System.out.printf("\"%s\" is not a valid entry! Returning to main menu.\n", input);
        }
    }

    //edit animal record
    //Called by: manageAnimal
    private void editAnimal(Animal animal) throws SQLException{
        //interface with user
        System.out.printf("\n*** Edit animal ****\n");
        System.out.printf("Please enter changes below. Press <Enter> to retain current value.\n");

        //cycle through five parameters, overwrite data with entry other than ""; type is forced data entry
        animal.setName(optionalInputRetainer(String.format("Name [%s]: ", animal.getName()), animal.getName()));
        animal.setType(requiredInputType(String.format("Type [%s]: ", animal.getType()), this.animalsService.getAllTypesAsStrings()));
        animal.setBreed(optionalInputRetainer(String.format("Breed [%s]: ", animal.getBreed()), animal.getBreed()));
        animal.setDescription(optionalInputRetainer(String.format("Description [%s]: ", animal.getDescription()), animal.getDescription()));
        this.animalsService.updateAnimal(animal);
        System.out.printf("\nEdit operation successful!\nUpdated record to:\n");
        System.out.println(animal.toString());
    }

    //add note record
    //Called by: manageAnimal
    private void addNote(int animalID) throws SQLException {
        //interface with user
        System.out.printf("\n*** Add note ****\n");
        System.out.printf("Please enter note text: ");
        String noteText = scanner.nextLine();
        Note thisNote = new Note(noteText);
        this.animalsService.addNote(this.animalsService.getAnimal(animalID), thisNote);
        System.out.printf("The note was successfully added!\n");
    }

    //print existing types from type table, one per line
    //Called by: manageTypes
    private void printTypes(ArrayList<String> types) {
        for (String string : types) {
            System.out.println(string);
        }
    }

    //standardize a string input by user to have the first letter be uppercase, then all remaining characters lower case
    //Called by: manageTypes
    private String normalizeString(String string) {
        char firstLetter = string.toUpperCase().charAt(0);
        String remaining = string.substring(1).toLowerCase();
        return firstLetter + remaining;
    }

    //input subroutines
    //user input integer validator, for menu and/or submenu user selections (select 1 to 'validOptions')
    private int waitForInt(String message, int validOptions) {
        //interface with user
        System.out.printf(message);
        String input = scanner.nextLine();

        //process input
        int value;
        try {
            value = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.printf("Please try again, \"%s\" is not a valid number!\n", input);
            value = waitForInt(message, validOptions);
        }
        if ((value < 1) || (value > validOptions)) {
            System.out.printf("Please try again. \"%s\" is not a valid number!\n", input);
            value = waitForInt(message, validOptions);
        }
        return value;
    }

    //user input integer validator, for selection of Animals from a subset (select valid int from existing animalIDs)
    private int waitForInt(String message, ArrayList<Animal> animals) {
        //local properties
        ArrayList<Integer> animalIDs = new ArrayList<>();

        //interface with user
        System.out.printf(message);
        String input = scanner.nextLine();

        //process input
        int value;
        for (Animal animal : animals) {
            animalIDs.add(animal.getAnimalID());
        }
        try {
            value = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.printf("Please try again, \"%s\" is not a valid number!\n", input);
            value = waitForInt(message, animals);
        }
        if ((value < 1) || (!animalIDs.contains(value))) {
            System.out.printf("Please try again. \"%s\" is not a valid number!\n", input);
            value = waitForInt(message, animals);
        }
        return value;
    }

    //required input: repeated loop, does not accept empty input
    private String requiredInput(String prompt) {
        System.out.printf("%s: ", prompt);
        String input = scanner.nextLine();
        while ((input.isEmpty()) || (input.trim().isEmpty())) {
            System.out.printf("%s is required. Please try again.\n" +
                    "%s: ", prompt, prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    //required input: repeated loop, does not accept empty input, specifically for Type prompt
    private String requiredInputType(String prompt, ArrayList<String> types) {
        System.out.printf("%s", prompt);
        String input = scanner.nextLine();
        while ((input.isEmpty()) || (input.trim().isEmpty()) || (!types.contains(input))) {
            if (!types.contains(input)) {
                System.out.printf("Please choose from (%s): ", listTypesAsString(types));
            } else {
                System.out.printf("%s is required. Please try again.\n" +
                        "%s", prompt, prompt);
            }
            input = scanner.nextLine();
        }
        return input;
    }

    //optional input: single pass, accepts empty input
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