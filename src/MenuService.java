/**
 * Created by Andrew Nagel on 8/15/16 at 12:34 PM EST.
 */

import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.System.exit;

public class MenuService {
    //declarations and scanner instantiation
    static final int LIST_ANIMALS = 1;
    static final int CREATE_ANIMAL = 2;
    static final int VIEW_ANIMAL_DETAILS = 3;
    static final int EDIT_ANIMAL_DETAILS = 4;
    static final int DELETE_ANIMAL = 5;
    static final int QUIT = 6;
    Scanner scanner = new Scanner(System.in);
    AnimalsService animalsService = new AnimalsService();

    //menu prompt
    public int promptForMainMenuSelection() {
        System.out.printf("\n***** Main Menu *****\n" +
                "<1> List animals.\n" +
                "<2> Create a new animal.\n" +
                "<3> View an animal's details.\n" +
                "<4> Edit an animal's details.\n" +
                "<5> Delete an animal.\n" +
                "<6> Quit.\n");
        return waitForInt("\nPlease choose an option:");
    }

    //menu wait
    private int waitForInt(String message) {
        System.out.println(message);
        String input = scanner.nextLine();
        int value;
        try {
            value = Integer.parseInt(input);
        } catch(Exception e){
            System.out.printf("Please try again, \"%s\" is not a valid number!", input);
            value = waitForInt(message);
        }
        if((value > 7) || (value < 1)) {
            System.out.printf("Please try again. \"%s\" is not a valid number!\n", input);
        }
        return value;
    }

    //menu actions
    //TODO - format output columns
    public void listAnimals() {
        ArrayList<Animal> animals = animalsService.listAnimals();
        int numberAnimals = animals.size();
        System.out.printf("*** List of Animals ***\n");
        for(int i = 0; i < numberAnimals; i++) {
            System.out.printf("%d) %s | %s \n", (i+1), animals.get(i).getName(), animals.get(i).getSpecies());
        }
    }

    //TODO
    public void createNewAnimal() {
        //declarations
        String tempName = "";
        String tempSpecies = "";
        String tempBreedOpt = "";
        String tempDescription = "";

        //interface with user
        System.out.printf("*** Create new Animal ***\n");
        System.out.printf("Please answer the following:\n");
        System.out.printf("Animal Name: ");
        String input = scanner.nextLine();
        while(input.isEmpty()) {
            System.out.printf("Name is required. Please try again.");
            input = scanner.nextLine();
        }
        tempName = input;
        System.out.printf("Species: ");
        input = scanner.nextLine();
        while(input.isEmpty()) {
            System.out.printf("Species is required. Please try again.");
            input = scanner.nextLine();
        }
        tempSpecies = input;

        //create Animal
        Animal newEntry = new Animal(tempName, tempSpecies);
        animalsService.addAnimal(newEntry);
    }

    //TODO
    public void viewAnimalDetails() {
    }

    //TODO
    public void editAnimal() {
    }

    //TODO
    public void deleteAnimal() {
    }

    public void quitProgram() {
        System.out.printf("Are you sure you want to quit? All data will be lost!\n");
        System.out.printf("Type \"yes\" to confirm, \"no\" to cancel.\n");
        String input = scanner.nextLine();
        if(input.equals("yes")) {
            System.out.printf("*** Goodbye! ***\n");
            exit(0);
        } else if(input.equals("no")) {
            //will exit out and return to menu prompt
        } else {
            System.out.printf("Please try again, \"%s\" is not a valid entry!\n", input);
            quitProgram();
        }
    }
}