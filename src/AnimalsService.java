import java.util.ArrayList;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:33 PM EST.
 */

//private properties ONLY
public class AnimalsService {
    //internal structure to hold list of animals
    private ArrayList<Animal> animalList = new ArrayList<Animal>();

    //return private list of animals
    protected ArrayList<Animal> listAnimals() {
        return animalList;
    }

    //return animal from specified index
    protected Animal getAnimal(int index) {
        return animalList.get(index);
    }

    //add animal to the list of animals
    protected void addAnimal(Animal animal) {
        animalList.add(animal);
    }
}