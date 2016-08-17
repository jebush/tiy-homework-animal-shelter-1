/**
 * Created by Andrew Nagel on 8/15/16 at 12:30 PM EST.
 */

public class Animal {
    //object properties
    //core attributes of any Animal object
    private String name, species, breed, description;

    //constructors
    //default constructor
    protected Animal() {}

    //constructor with breed(opt)
    protected Animal(String name, String species, String breed, String description) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
    }

    //methods
    //toString (formatted)
    public String toString() {
        return String.format("%-12s %-16s\n%-12s %-16s\n%-12s %-16s\n%-12s %-16s",
                "Name:", this.name,
                "Species:", this.species,
                "Breed:", this.breed,
                "Description:", this.description);
    }

    //getters
    protected String getName() {
        return this.name;
    }

    protected String getSpecies() {
        return this.species;
    }

    protected String getBreed() {
        return this.breed;
    }

    protected String getDescription() {
        return this.description;
    }

    //setters
    protected void setName(String name) {
        this.name = name;
    }

    protected void setSpecies(String species) {
        this.species = species;
    }

    protected void setBreed(String breed) {
        this.breed = breed;
    }

    protected void setDescription(String description) {
        this.description = description;
    }
}