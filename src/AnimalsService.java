/**
 * Created by Andrew Nagel on 8/15/16 at 12:33 PM EST.
 */

import java.util.ArrayList;

public class AnimalsService {
    //object properties
    //must be private per assignment constraints
    private ArrayList<Animal> animalList = new ArrayList<>();

    //methods
    //return local arrayList holding stored animals
    protected ArrayList<Animal> listAnimals() {
        return animalList;
    }

    //return animal from specified index in local arrayList
    protected Animal getAnimal(int index) {
        return animalList.get(index);
    }

    //add animal to end of local arrayList
    protected void addAnimal(Animal animal) {
        animalList.add(animal);
    }

    //remove animal from specified index in local arrayList
    protected void removeAnimal(int index) {
        animalList.remove(index);
    }
}