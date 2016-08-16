/**
 * Created by Andrew Nagel on 8/15/16 at 12:34 PM EST.
 */

import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.System.exit;

public class MenuService {
    //declarations and instantiation of scanner and animal service
    static final int LIST_ANIMALS = 1;
    static final int CREATE_ANIMAL = 2;
    static final int VIEW_ANIMAL_DETAILS = 3;
    static final int EDIT_ANIMAL_DETAILS = 4;
    static final int DELETE_ANIMAL = 5;
    static final int QUIT = 6;
    private AnimalsService animalsService = new AnimalsService();
    private Scanner scanner = new Scanner(System.in);

    //menu prompt
    protected int promptForMainMenuSelection() {
        //interface with user
        System.out.printf("\n***** Main Menu *****\n" +
                "<1> List animals.\n" +
                "<2> Create a new animal.\n" +
                "<3> View an animal's details.\n" +
                "<4> Edit an animal's details.\n" +
                "<5> Delete an animal.\n" +
                "<6> Quit.");
        return waitForInt("\nPlease choose an option:");
    }

    //menu actions
    protected void listAnimals() {
        //declarations
        ArrayList<Animal> animals = animalsService.listAnimals();
        int numberAnimals = animals.size();

        //interface with user
        System.out.printf("\n*** List of Animals ***\n");
        for(int i = 0; i < numberAnimals; i++) {
            System.out.printf("%-3s %-16s %-16s \n", (i+1) + ")", animals.get(i).getName(), animals.get(i).getSpecies());
        }
            System.out.printf("[%d] total animal(s) are on record.\n", numberAnimals);
    }

    protected void createNewAnimal() {
        //declarations
        String tempName;
        String tempSpecies;
        String tempBreedOpt;
        String tempDescription;

        //interface with user
        System.out.printf("\n*** Create new Animal ***\n");
        System.out.printf("Please answer the following:\n");
        tempName = requiredInput("Animal Name");
        tempSpecies = requiredInput("Species");
        tempBreedOpt = optionalInput("Breed (opt.)");
        tempDescription = requiredInput("Description");

        //create Animal object based on data present from breed entry
        if(tempBreedOpt.equals("")) {
            Animal newEntry = new Animal(tempName, tempSpecies, tempDescription);
            animalsService.addAnimal(newEntry);
        } else {
            Animal newEntry = new Animal(tempName, tempSpecies, tempBreedOpt, tempDescription);
            animalsService.addAnimal(newEntry);
        }
    }

    protected void viewAnimalDetails() {
        //interface with user
        System.out.printf("\n*** View an Animal ***\n");
        int result = waitForIntModified("Please enter the ID# of the animal to view: ");
        if(result != 0) {
            System.out.print(animalsService.getAnimal(result-1) + "\n");
        }
    }

    protected void editAnimal() {
        //interface with user
        System.out.printf("\n*** Edit an Animal ***\n");
        int result = waitForIntModified("Please enter the ID# of the animal to edit: ");
        if(result !=0) {
            System.out.printf("Please enter changes below. Press <Enter> to retain current value.");
            System.out.printf("\nName [%s]: ", animalsService.getAnimal(result-1).getName());
            String input = scanner.nextLine();
            if(!input.equals("")) {
                animalsService.getAnimal(result-1).setName(input);
            }
            System.out.printf("Species [%s]: ", animalsService.getAnimal(result-1).getSpecies());
            input = scanner.nextLine();
            if(!input.equals("")) {
                animalsService.getAnimal(result-1).setSpecies(input);
            }
            System.out.printf("Breed [%s]: ", animalsService.getAnimal(result-1).getBreed());
            input = scanner.nextLine();
            if(!input.equals("")) {
                animalsService.getAnimal(result-1).setBreed(input);
            }
            System.out.printf("Description [%s]: ", animalsService.getAnimal(result-1).getDescription());
            input = scanner.nextLine();
            if(!input.equals("")) {
                animalsService.getAnimal(result-1).setDescription(input);
            }
            System.out.printf("\nEdit operation successful! Update record to:\n");
            System.out.print(animalsService.getAnimal(result - 1) + "\n");

        }
    }

    protected void deleteAnimal() {
        //interface with user
        System.out.printf("\n*** Delete an Animal ***\n");
        int result = waitForIntModified("Please enter the ID# of the animal to delete: ");
        if(result != 0) {
            System.out.print(animalsService.getAnimal(result - 1) + "\n");
            System.out.printf("\nAre you sure you want to delete this animal?\n");
            System.out.printf("Type \"yes\" to confirm, \"no\" to select a different animal.\n" +
                    "Any other data entry will return to the main menu.\n");
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "yes":
                case "y":
                    //confirm delete request
                    animalsService.removeAnimal(result - 1);
                    System.out.printf("Deletion operation successful!\n");
                    break;
                case "no":
                case "n":
                    //abort exit request and return to deletion prompt
                    System.out.printf("Deletion operation has been cancelled.\n");
                    deleteAnimal();
                    break;
                default:
                    //didn't input 'yes' or 'no'
                    System.out.printf("\"%s\" is not a valid entry! Returning to main menu.\n", input);
            }
        }
    }

    protected void quitProgram() {
        //interface with user
        System.out.printf("Are you sure you want to quit? All data will be lost!\n");
        System.out.printf("Type \"yes\" to confirm, \"no\" to cancel.\n");
        String input = scanner.nextLine().toLowerCase();
        switch(input) {
            case "yes":
            case "y":
                //confirm exit request
                System.out.printf("Goodbye!\n");
                exit(0);
                break;
            case "no":
            case "n":
                //abort exit request and return to menu prompt
                break;
            default:
                //didn't input 'yes' or 'no'
                System.out.printf("Please try again, \"%s\" is not a valid entry!\n", input);
                quitProgram();
        }
    }

    //supporting private functions
    private int waitForInt(String message) {
        System.out.println(message);
        String input = scanner.nextLine();
        int value;
        try {
            value = Integer.parseInt(input);
        } catch(Exception e) {
            System.out.printf("Please try again, \"%s\" is not a valid number!", input);
            value = waitForInt(message);
        }
        if((value < 1) || (value > 6)) {
            System.out.printf("Please try again. \"%s\" is not a valid number!\n", input);
        }
        return value;
    }

    private int waitForIntModified(String message) {
        if(animalsService.listAnimals().isEmpty()) {
            System.out.printf("[0] total animal(s) are on record.\n");
            return 0;
        }
        System.out.println(message);
        String input = scanner.nextLine();
        int value;
        try {
            value = Integer.parseInt(input);
        } catch(Exception e) {
            System.out.printf("Please try again, \"%s\" is not a valid number!\n", input);
            value = waitForIntModified(message);
        }
        if((value < 1) || (value > animalsService.listAnimals().size())) {
            System.out.printf("Please try again. \"%s\" is not a valid number!\n", input);
            value = waitForIntModified(message);
        }
        return value;
    }

    private String requiredInput(String prompt) {
        System.out.printf("%s: ", prompt);
        String input = scanner.nextLine();
        while(input.isEmpty()) {
            System.out.printf("%s is required. Please try again.\n" +
                    "%s: ", prompt, prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    private String optionalInput(String prompt) {
        System.out.printf("%s: ", prompt);
        return scanner.nextLine();
    }
}