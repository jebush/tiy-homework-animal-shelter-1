package com.andrewRnagel.animalShelter;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Andrew Nagel on 8/23/16 at 11:51 AM EST.
 */

public class AnimalTest {

    //constructor tests
    @Test
    /**
     * Given: Animal object
     * When: Constructed with Breed info
     * Then: Appropriate object is created
     */
    public void whenConstructedWithBreedThenInstantiatedCorrectly() {
        //Arrange
        //Act
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Assert
        assertThat(animal.getName(), equalTo("Fred"));
        assertThat(animal.getSpecies(), equalTo("Frog"));
        assertThat(animal.getBreed(), equalTo("Poison Dart"));
        assertThat(animal.getDescription(), equalTo("Green"));
    }

    @Test
    /**
     * Given: Animal object
     * When: Constructed without Breed info
     * Then: Appropriate object is created
     */
    public void whenConstructedWithoutBreedThenInstantiatedCorrectly() {
        //Arrange
        //Act
        Animal animal = new Animal("Fred", "Frog", "", "Green");
        //Assert
        assertThat(animal.getName(), equalTo("Fred"));
        assertThat(animal.getSpecies(), equalTo("Frog"));
        assertThat(animal.getBreed(), equalTo(""));
        assertThat(animal.getDescription(), equalTo("Green"));
    }

    //getter tests
    @Test
    /**
     * Given: Animal object
     * When: Retrieving Name
     * Then: Object property correctly returned
     */
    public void whenAnimalNameGetObjectPropertyCorrectlyReturned() {
        //Arrange
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Act
        String name = animal.getName();
        //Assert
        assertThat(name, equalTo("Fred"));
    }

    @Test
    /**
     * Given: Animal object
     * When: Retrieving Species
     * Then: Object property correctly returned
     */
    public void whenAnimalSpeciesGetObjectPropertyCorrectlyReturned() {
        //Arrange
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Act
        String species = animal.getSpecies();
        //Assert
        assertThat(species, equalTo("Frog"));
    }

    @Test
    /**
     * Given: Animal object
     * When: Retrieving Breed
     * Then: Object property correctly returned
     */
    public void whenAnimalBreedGetObjectPropertyCorrectlyReturned() {
        //Arrange
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Act
        String breed = animal.getBreed();
        //Assert
        assertThat(breed, equalTo("Poison Dart"));
    }

    @Test
    /**
     * Given: Animal object
     * When: Retrieving Description
     * Then: Object property correctly returned
     */
    public void whenAnimalDescriptionGetObjectPropertyCorrectlyReturned() {
        //Arrange
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Act
        String desc = animal.getDescription();
        //Assert
        assertThat(desc, equalTo("Green"));
    }

    //setter tests
    @Test
    /**
     * Given: Animal object
     * When: Setting Name
     * Then: Object property correctly changed
     */
    public void whenAnimalNameSetObjectPropertyCorrectlyChanged() {
        //Arrange
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Act
        animal.setName("Bob");
        //Assert
        assertThat(animal.getName(), equalTo("Bob"));
    }

    @Test
    /**
     * Given: Animal object
     * When: Setting Species
     * Then: Object property correctly changed
     */
    public void whenAnimalNameSetSpeciesPropertyCorrectlyChanged() {
        //Arrange
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Act
        animal.setSpecies("Bobcat");
        //Assert
        assertThat(animal.getSpecies(), equalTo("Bobcat"));
    }

    @Test
    /**
     * Given: Animal object
     * When: Setting Breed
     * Then: Object property correctly changed
     */
    public void whenAnimalBreedSetObjectPropertyCorrectlyChanged() {
        //Arrange
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Act
        animal.setBreed("Wild");
        //Assert
        assertThat(animal.getBreed(), equalTo("Wild"));
    }

    @Test
    /**
     * Given: Animal object
     * When: Setting Description
     * Then: Object property correctly changed
     */
    public void whenAnimalDescriptionSetSpeciesPropertyCorrectlyChanged() {
        //Arrange
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Act
        animal.setDescription("High-jumper");
        //Assert
        assertThat(animal.getDescription(), equalTo("High-jumper"));
    }

    //method tests
    @Test
    /**
     * Given: Animal object
     * When: Converting to String
     * Then: Appropriate String is created
     */
    public void whenAnimalObjectConvertedToStringCorrectlyReturned() {
        //Arrange
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Act
        String animalString = animal.toString();
        //Assert
        assertThat(animalString, allOf(containsString("Name:"), containsString("Species:"),
                containsString("Breed:"), containsString("Description:")));
        assertThat(animalString, allOf(containsString("Fred"), containsString("Frog"),
                containsString("Poison Dart"), containsString("Green")));
    }

    @Test
    /**
     * Given: Animal object
     * When: Converting to Serialized String
     * Then: Appropriate String is created with pipe(|) delimiters and newline character
     */
    public void whenAnimalObjectSerialized() {
        //Arrange
        Animal animal = new Animal("Fred", "Frog", "Poison Dart", "Green");
        //Act
        String animalSerialized = animal.serialize();
        //Assert
        assertThat(animalSerialized, is("Fred|Frog|Poison Dart|Green\n"));
    }

    @Test
    /**
     * Given: Serialized String representation of Animal object
     * When: Converting to Animal Object
     * Then: Appropriate parse via pipe(|) delimiters to instantiate Animal object
     */
    public void whenStringRepresentationDeserializedInstantiatesCorrectAnimalObject() {
        //Arrange
        String animalSerialized = "Fred|Frog|Poison Dart|Green";
        //Act
        Animal animal = Animal.deserialize(animalSerialized);
        //Assert
        assertThat(animal.getName(), equalTo("Fred"));
        assertThat(animal.getSpecies(), equalTo("Frog"));
        assertThat(animal.getBreed(), equalTo("Poison Dart"));
        assertThat(animal.getDescription(), equalTo("Green"));
    }
}
