/**
 * Created by Andrew Nagel on 8/15/16 at 12:30 PM EST.
 */

public class Animal {
    String name = "";
    String species = "";
    String breed = "";
    String description = "";

    public Animal() {}

    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
    }

    public Animal(String name, String species, String description) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
    }

    public Animal(String name, String species, String breed, String description) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getSpecies() {
        return this.species;
    }

    public String getBreed() {
        return this.breed;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return (this.name + ", " + this.breed);
    }
}