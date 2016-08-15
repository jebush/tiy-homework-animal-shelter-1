/**
 * Created by Andrew Nagel on 8/15/16 at 12:35 PM EST.
 */

public class Main {

    public static void main(String[] args) {
        //instantiation of menu service
        MenuService menu = new MenuService();

        //loop that drives program
        while(true) {
            int action = menu.promptForMainMenuSelection();
            if(action == MenuService.LIST_ANIMALS) {
                menu.listAnimals();
            } else if(action == MenuService.CREATE_ANIMAL) {
                menu.createNewAnimal();
            } else if(action == MenuService.VIEW_ANIMAL_DETAILS) {
                menu.viewAnimalDetails();
            } else if(action == MenuService.EDIT_ANIMAL_DETAILS) {
                menu.editAnimal();
            } else if(action == MenuService.DELETE_ANIMAL) {
                menu.deleteAnimal();
            } else if(action == MenuService.QUIT) {
                menu.quitProgram();
            } else {
                //unreachable block
            }
        }
    }
}