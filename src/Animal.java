/**
 * Created by Andrew Nagel on 8/15/16 at 12:30 PM EST.
 */

public class Animal {
    private String name = "";
    private String species = "";
    private String breed = "";
    private String description = "";

    protected Animal(String name, String species, String description) {
        this.name = name;
        this.species = species;
        this.description = description;
    }

    protected Animal(String name, String species, String breed, String description) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
    }

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

    public String toString() {
        return String.format("%-12s %-16s\n%-12s %-16s\n%-12s %-16s\n%-12s %-16s",
                "Name:", this.name,
                "Species:", this.species,
                "Breed:", this.breed,
                "Description:", this.description);
    }
}