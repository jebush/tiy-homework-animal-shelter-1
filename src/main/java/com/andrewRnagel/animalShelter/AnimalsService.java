package com.andrewRnagel.animalShelter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:33 PM EST.
 */

public class AnimalsService {
    //object properties
    //private repositories for Animal and Note DAOs
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
    protected ArrayList<Animal> listAnimalsAll() throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            ResultSet results = this.animalRepository.listAnimalsAll();
            animals = populateList(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    //return list holding stored animal objects by name (sans notes)
    protected ArrayList<Animal> listAnimalsByName(String name) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            ResultSet results = this.animalRepository.listAnimalsByName(name);
            animals = populateList(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    //return list holding stored animal objects by type (sans notes)
    protected ArrayList<Animal> listAnimalsByType(String type) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            ResultSet results = this.animalRepository.listAnimalsByType(type);
            animals = populateList(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    //add animal to end of list after ensuring typeID is present
    protected void addAnimal(Animal animal) throws SQLException {
        if(!animal.getType().isEmpty() && animal.getAnimalTypeID() == -1) {
            animal.setAnimalTypeID(this.animalRepository.getTypeID(animal.getType()));
        }
        this.animalRepository.addAnimal(animal);
    }

    //return animal from specified index
    protected Animal getAnimal(int index) {
        Animal animal = new Animal();
        try {
            ResultSet results = this.animalRepository.getAnimal(index);
            while (results.next()) {
                animal.setAnimalID(results.getInt("animalid"));
                animal.setName(results.getString("name"));
                animal.setSpecies(results.getString("species"));
                animal.setBreed(results.getString("breed"));
                animal.setDescription("desc");
                animal.setAnimalTypeID(results.getInt("type"));
                animal.setType(this.animalRepository.getType(results.getInt("type")));
                animal.setAnimalNotes(getAnimalNotesList(index));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }

    //remove animal from specified index
    protected void removeAnimal(int index) throws SQLException {
        this.animalRepository.removeAnimal(index);
    }

    //update animal from specified index
    protected void updateAnimal(int index, Animal animal) throws SQLException {
        this.animalRepository.updateAnimal(index, animal);
    }

    //add note to note table associated with animal
    protected void addNote(Animal animal, Note note) throws SQLException {
        this.noteRepository.addAnimalNote(animal, note);
    }

    //return animal notes in an arrayList to be attached to animal object
    protected ArrayList<Note> getAnimalNotesList(int index) throws SQLException {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            ResultSet results = this.noteRepository.listAllNotesByAnimal(index);
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

    //remove note from note table associate with animal
    protected void removeNote(int noteid) throws SQLException {
        this.noteRepository.removeNote(noteid);
    }

    //remove ALL notes from note table associated with an animal (preparation to delete animal)
    protected void removeAllNotesByAnimal(Animal animal) throws SQLException {
        try {
            ResultSet results = this.noteRepository.listAllNotesByAnimal(animal);
            while(results.next()) {
                this.noteRepository.removeNote(animal, results.getInt("noteid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get all types from type lookup table
    protected ArrayList<String> getTypesALL() throws SQLException {
        ArrayList<String> types = new ArrayList<>();
        try {
            ResultSet results = this.animalRepository.getTypes();
            while (results.next()) {
                types.add(results.getString("typeName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    //get typeID from lookup table
    protected int getTypeID(String string) throws SQLException {
        return animalRepository.getTypeID(string);
    }

    //add type to type lookup table
    protected void addType(String type) throws SQLException {
        this.animalRepository.addType(type);
    }

    //supporting private functions
    //cycle through a resultSet and return an ArrayList of Animals (sans notes)
    private ArrayList<Animal> populateList(ResultSet results) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        while (results.next()) {
            Animal animal = new Animal(
                    results.getInt("animalid"),
                    results.getString("name"),
                    results.getString("species"),
                    results.getString("breed"),
                    results.getString("description"),
                    results.getInt("type"),
                    this.animalRepository.getType(results.getInt("type"))
            );
            animals.add(animal);
        }
        return animals;
    }
}