package com.andrewRnagel.animalShelter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:33 PM EST.
 */

public class AnimalsService {
    //object properties
    private AnimalRepository animalRepository;
    private NoteRepository noteRepository;

    //constructors
    //constructor with specific data repository
    public AnimalsService(AnimalRepository animalRepository, NoteRepository noteRepository) {
        this.animalRepository = animalRepository;
        this.noteRepository = noteRepository;
    }

    //methods
    //return list holding ALL stored animal objects (sans notes)
    protected ArrayList<Animal> listAllAnimals() throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            ResultSet results = this.animalRepository.listAllAnimals();
            animals = populateAnimalResultsAsList(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    //return list holding stored animal objects by type (sans notes)
    protected ArrayList<Animal> listAllAnimalsWithType(String type) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            ResultSet results = this.animalRepository.listAllAnimalsByType(type);
            animals = populateAnimalResultsAsList(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    //return list holding stored animal objects by name (sans notes)
    protected ArrayList<Animal> listAllAnimalsWithName(String name) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            ResultSet results = this.animalRepository.listAllAnimalsByName(name);
            animals = populateAnimalResultsAsList(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    //return list holding stored animal objects by name (sans notes)
    protected ArrayList<Animal> listAllAnimalsWithID(int animalID) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            ResultSet results = this.animalRepository.listAllAnimalsByID(animalID);
            animals = populateAnimalResultsAsList(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    //add animal to animal table
    protected void addAnimal(Animal animal) throws SQLException {
        if(!animal.getType().isEmpty() && animal.getAnimalTypeID() == -1) {
            animal.setAnimalTypeID(this.animalRepository.getTypeIDByName(animal.getType()));
        }
        this.animalRepository.addAnimal(animal);
    }

    //remove animal from specified index in animal table
    protected void removeAnimal(int index) throws SQLException {
        if(!getAllAnimalNotesWithID(index).isEmpty()) {
            removeAllAnimalNotesWithID(getAnimal(index));
        }
        this.animalRepository.removeAnimal(index);
    }

    //return animal from specified index in animal table
    protected Animal getAnimal(int index) {
        Animal animal = new Animal();
        try {
            ResultSet results = this.animalRepository.getAnimal(index);
            while (results.next()) {
                animal.setAnimalID(results.getInt("animalid"));
                animal.setName(results.getString("name"));
                animal.setBreed(results.getString("breed"));
                animal.setDescription(results.getString("description"));
                animal.setAnimalTypeID(results.getInt("type"));
                animal.setType(this.animalRepository.getTypeNameByID(results.getInt("type")));
                animal.setAnimalNotes(getAllAnimalNotesWithID(index));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }

    //update animal from specified index with update animal object in animal table
    protected void updateAnimal(int index, Animal animal) throws SQLException {
        this.animalRepository.updateAnimal(index, animal);
    }

    //add note associated with animal to note table
    protected void addNote(Animal animal, Note note) throws SQLException {
        this.noteRepository.addAnimalNote(animal, note);
    }

    //remove note associated with animal from note table by noteID
    protected void removeNote(int noteID) throws SQLException {
        this.noteRepository.removeNote(noteID);
    }

    //return all animal notes in an arrayList given animalID
    protected ArrayList<Note> getAllAnimalNotesWithID(int animalID) throws SQLException {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            ResultSet results = this.noteRepository.listAllNotesByAnimal(animalID);
            while (results.next()) {
                Note note = new Note(
                        results.getInt("noteid"),
                        results.getString("text"),
                        results.getString("date")
                );
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    //remove ALL notes associated with an animal from note table
    protected void removeAllAnimalNotesWithID(Animal animal) throws SQLException {
        try {
            ResultSet results = this.noteRepository.listAllNotesByAnimal(animal);
            while(results.next()) {
                this.noteRepository.removeNote(animal, results.getInt("noteid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get all types from the type lookup table
    protected ArrayList<String> getAllTypes() throws SQLException {
        ArrayList<String> types = new ArrayList<>();
        try {
            ResultSet results = this.animalRepository.getAllTypes();
            while (results.next()) {
                types.add(results.getString("typeName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    //add a type to the type lookup table
    protected void addType(String type) throws SQLException {
        this.animalRepository.addType(type);
    }

    //get typeID from lookup table, given the name of the type
    protected int getTypeIDByName(String string) throws SQLException {
        return animalRepository.getTypeIDByName(string);
    }

    //supporting private functions
    //cycle through a given resultSet and return an ArrayList of Animals (sans notes)
    private ArrayList<Animal> populateAnimalResultsAsList(ResultSet results) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        while (results.next()) {
            Animal animal = new Animal(
                    results.getInt("animalid"),
                    results.getString("name"),
                    this.animalRepository.getTypeNameByID(results.getInt("type")),
                    results.getString("breed"),
                    results.getString("description"),
                    results.getInt("type")
            );
            animals.add(animal);
        }
        return animals;
    }
}