package com.andrewRnagel.animalShelter;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.System.exit;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:34 PM EST.
 */

public class MenuService {
    //object properties
    //static final assignments for main.menuDriver() method
    static final int ADD_ANIMAL = 1, MANAGE_ANIMAL = 2, MANAGE_ANIMAL_TYPES = 3, QUIT = 4;
    //instantiate scanner to read console input
    private Scanner scanner = new Scanner(System.in);

    //methods (protected)
    //menu prompt
    protected int promptForMainMenuSelection() {
        //interface with user
        System.out.printf("\n***** Main Menu *****\n" +
                "<1> Add a new animal\n" +
                "<2> Manage an existing animal\n" +
                "<3> Manage animal types\n" +
                "<4> Quit\n");
        return waitForInt("Please choose an option:", 4);
    }

    //create entry subroutine
    protected Animal addNewAnimal(ArrayList<String> types) {
        //local properties
        String tempName, tempSpecies, tempBreedOpt, tempDescription, tempType;

        //interface with user
        System.out.printf("\n*** Add a new Animal ***\n");
        System.out.printf("Please answer the following:\n");
        tempName = requiredInput("Name");
        tempType = requiredInputType("Type (" + listToString(types) + "): ", types);
        tempSpecies = requiredInput("Species" );
        tempBreedOpt = optionalInput("Breed (opt.): " );
        tempDescription = requiredInput("Description");

        //create object based on data entered from breed entry and add to arrayList
        Animal newEntry = new Animal(tempName, tempSpecies, tempBreedOpt, tempDescription, tempType);
        System.out.printf("\nOperation successful! Animal %s added.\n", tempName);
        return newEntry;
    }

    //TODO
    protected void manageAnimal(AnimalsService animalService) throws SQLException {
        //search for animal by type, name, id, or all animals
        //interface with user
        System.out.printf("\n*** Manage an existing animal ***\n");
        int tempID = searchAnimals(animalService);
    }

    private int searchAnimals(AnimalsService animalService) throws SQLException {
        //local properties
        int result = -1;
        ArrayList<String> types = animalService.getTypesALL();
        String query;

        //interface with user
        System.out.printf("Search for animals:");
        System.out.printf("\n<1> Type\n" +
                "<2> Name\n" +
                "<3> ID Number\n" +
                "<4> All Animals\n" +
                "<5> Return to main menu\n");
        System.out.printf("How would you like to search for an animal:\n");
        int input = waitForInt("Please choose an option:", 5);

        //process input
        //use search methods to return animalID
        switch (input) {
            case 1:
                System.out.printf("Please input a type below:\n");
                query = requiredInputType("Type (" + listToString(types) + "): ", types);
                printAnimal(animalService.listAnimalsByType(query));
                System.out.printf("\nWhich animal do you want to manage?: ");
                //get valid int(check), then set result equal
                break;
            case 2:
                System.out.printf("Please input a name below:\n");
                query = requiredInput("Name");
                printAnimal(animalService.listAnimalsByName(query));
                System.out.printf("\nWhich animal do you want to manage?: ");
                //get valid int(check), then set result equal
                break;
            case 3:
                System.out.printf("Please input an ID below:\n");
                int tempID = waitForInt("Animal ID: ", animalService.listAnimalsAll().size());
                printAnimal(animalService.getAnimal(tempID));
                System.out.printf("\nWhich animal do you want to manage?: ");
                //get valid int(check), then set result equal
                break;
            case 4:
                System.out.printf("Listing all animals below:\n");
                printAnimal(animalService.listAnimalsAll());
                System.out.printf("\nWhich animal do you want to manage?: ");
                //get valid int(check), then set result equal
                break;
            case 5:
            default:
                break;
        }
        return result;
    }

    //TODO edit or delete type via menu
    protected void manageTypes(AnimalsService animalService) throws SQLException {
        //local properties
        String tempType;

        //interface with user
        System.out.printf("\n*** Manage types ***\n" +
                "<1> List existing types\n" +
                "<2> Add a new type\n" +
                "<3> Return to main menu\n");
        int input = waitForInt("Please choose an option:", 3);

        //process input
        switch (input) {
            //list existing types
            case 1:
                printTypes(animalService.getTypesALL());
                break;
            //add new type
            case 2:
                System.out.printf("Please input the type to add:\n");
                tempType = requiredInput("Type");
                tempType = normalizeString(tempType);
                if(animalService.getTypesALL().contains(tempType)) {
                    System.out.printf("\nOperation failed! Type %s already exists!\n", tempType);
                } else {
                    animalService.addType(tempType);
                    System.out.printf("\nOperation successful! Type %s added.\n", tempType);
                }
                break;
            case 3:
            default:
                break;
        }
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

    //supporting functions
    //print animal search results
    protected void printAnimal(ArrayList<Animal> animals) {
        String id = "ID", name  = "NAME", type = "TYPE";
        System.out.printf("\n*** List of animals ***\n" +
                "\n%-3s | %-16s | %-16s\n" +
                "++++++++++++++++++++++++++++++++++++++\n", id, name, type);
        for(Animal animal : animals) {
            id = Integer.toString(animal.getAnimalID());
            name = animal.getName();
            type = animal.getType();
            System.out.printf("%-3s | %-16s | %-16s\n", id, name, type);
        }
    }

    //print animal search results
    protected void printAnimal(Animal animal) {
        String id = "ID", name  = "NAME", type = "TYPE";
        System.out.printf("\n*** List of animals ***\n" +
                "\n%-3s | %-16s | %-16s\n" +
                "++++++++++++++++++++++++++++++++++++++\n", id, name, type);
            id = Integer.toString(animal.getAnimalID());
            name = animal.getName();
            type = animal.getType();
            System.out.printf("%-3s | %-16s | %-16s\n", id, name, type);
    }

    //print types subroutine
    protected void printTypes(ArrayList<String> types) {
        for(String string : types) {
            System.out.println(string);
        }
    }

    //user input integer validator, for menu and/or submenu user selections
    private int waitForInt(String message, int validOptions) {
        //interface with user
        System.out.println(message);
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

    //required input: repeated loop on empty
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

    //required input: repeated loop on empty
    private String requiredInputType(String prompt, ArrayList<String> types) {
        System.out.printf("%s", prompt);
        String input = scanner.nextLine();
        while ((input.isEmpty()) || (input.trim().isEmpty()) || (!types.contains(input))) {
            if(!types.contains(input)) {
                System.out.printf("Please choose from (%s): ", listToString(types));
            } else {
                System.out.printf("%s is required. Please try again.\n" +
                        "%s", prompt, prompt);
            }
            input = scanner.nextLine();
        }
        return input;
    }

    //optional input: single pass accepts empty
    private String optionalInput(String prompt) {
        System.out.printf(prompt);
        return scanner.nextLine();
    }

    //make type arrayList toString pretty
    private String listToString(ArrayList<String> list) {
        String result = "";
        switch (list.size()) {
            case 1:
                result = result + list.get(0);
                break;
            case 2:
                result = result + list.get(0) + " or " + list.get(1);
                break;
            default:
                for(int i = 0; i < list.size(); i++) {
                    if((i + 1) != list.size()) {
                        result = result + list.get(i) + ", ";
                    } else {
                        result = result +  "or " + list.get(i);
                    }
                }
                break;
        }
        return result;
    }

    //Standardized strings to first uppercase letter, then remaining characters are lower case
    private String normalizeString(String string) {
        char firstLetter = string.toUpperCase().charAt(0);
        String remaining = string.substring(1).toLowerCase();
        return firstLetter + remaining;
    }

//    //submenu prompts
//    //list subroutine (format: ### name species)
//    protected void listAnimals(ArrayList<Animal> animals) {
//        //local properties
//        int numberAnimals = animals.size();
//
//        //interface with user
//        System.out.printf("\n*** List Animals ***\n");
//        for (int i = 0; i < numberAnimals; i++) {
//            System.out.printf("%-3s %-16s %-16s \n", (i + 1) + ")", animals.get(i).getName(), animals.get(i).getSpecies());
//        }
//        System.out.printf("[%d] total animal(s) are on record.\n", numberAnimals);
//    }
//
//    //view entry subroutine
//    protected int viewAnimalDetails(ArrayList<Animal> animals) {
//        //interface with user
//        System.out.printf("\n*** View Animal details ***\n");
//        int result = waitForInt(animals.size(), "Please enter the ID# of the animal to view: ", false);
//        return result - 1;
//    }
//
//    //edit entry subroutine
//    protected String[] editAnimal(ArrayList<Animal> animals) {
//        //interface with user
//        System.out.printf("\n*** Edit Animal details ***\n");
//        int result = waitForInt(animals.size(), "Please enter the ID# of the animal to edit: ", false);
//
//        //process input (if animal arrayList has at least 1 entry)
//        if (result != -1) {
//            System.out.printf("Please enter changes below. Press <Enter> to retain current value.");
//            int animalNum = result - 1;
//
//            //cycle through four parameters, overwrite data with entry other than ""
//            String tempName = optionalInputRetainer(String.format("\nName [%s]: ",
//                    animals.get(animalNum).getName()), animals.get(animalNum).getName());
//            String tempSpecies = optionalInputRetainer(String.format("Species [%s]: ",
//                    animals.get(animalNum).getSpecies()), animals.get(animalNum).getSpecies());
//            String tempBreedOpt = optionalInputRetainer(String.format("Breed [%s]: ",
//                    animals.get(animalNum).getBreed()), animals.get(animalNum).getBreed());
//            String tempDescription = optionalInputRetainer(String.format("Description [%s]: ",
//                    animals.get(animalNum).getDescription()), animals.get(animalNum).getDescription());
//
//            //return results to main to complete action
//            String[] results = new String[]{Integer.toString(animalNum), tempName, tempSpecies, tempBreedOpt, tempDescription};
//            System.out.printf("\nEdit operation successful!\nUpdated record to:\n");
//            return results;
//        }
//        return null;
//    }
//
//    //delete entry subroutine
//    protected int deleteAnimal(ArrayList<Animal> animals) {
//        //interface with user
//        System.out.printf("\n*** Delete Animal ***\n");
//        int result = waitForInt(animals.size(), "Please enter the ID# of the animal to delete: ", false);
//        int animalNum = result - 1;
//
//        //process input (if animal arrayList has at least 1 entry)
//        if (result != -1) {
//            System.out.print(animals.get(animalNum) + "\n");
//            System.out.printf("\nAre you sure you want to delete this animal?\n");
//            System.out.printf("Type \"yes\" to confirm, \"no\" to select a different animal.\n" +
//                    "Any other data entry will return to the main menu.\n");
//            String input = scanner.nextLine().toLowerCase();
//            switch (input) {
//                //confirm delete request
//                case "yes":
//                case "y":
//                    System.out.printf("Deletion operation successful!\n");
//                    return animalNum;
//                //abort request and return to delete entry subroutine
//                case "no":
//                case "n":
//                    System.out.printf("Deletion operation has been cancelled.\n");
//                    deleteAnimal(animals);
//                    break;
//                //didn't input 'yes' or 'no'
//                default:
//                    System.out.printf("\"%s\" is not a valid entry! Returning to main menu.\n", input);
//            }
//        }
//        return -1;
//    }
//
//    //optional input: single pass accept empty, retains previous value
//    private String optionalInputRetainer(String prompt, String returnValue) {
//        System.out.printf(prompt);
//        String input = scanner.nextLine();
//        if (input.trim().equals("")) {
//            return returnValue;
//        } else {
//            return input.trim();
//        }
//    }
}