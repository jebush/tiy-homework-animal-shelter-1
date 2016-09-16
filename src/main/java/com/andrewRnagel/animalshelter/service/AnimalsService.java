package com.andrewRnagel.animalshelter.service;
import com.andrewRnagel.animalshelter.entity.Animal;
import com.andrewRnagel.animalshelter.entity.Note;
import com.andrewRnagel.animalshelter.entity.Type;
import com.andrewRnagel.animalshelter.repo.AnimalRepository;
import com.andrewRnagel.animalshelter.repo.NoteRepository;
import com.andrewRnagel.animalshelter.repo.TypeRepository;

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
    private TypeRepository typeRepository;

    //constructors
    //constructor with specific data repository
    public AnimalsService(AnimalRepository animalRepository, NoteRepository noteRepository, TypeRepository typeRepository) {
        this.animalRepository = animalRepository;
        this.noteRepository = noteRepository;
        this.typeRepository = typeRepository;
    }

    //methods
    //return list holding ALL stored animal objects (sans notes)
    public ArrayList<Animal> getAllAnimals() throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            ResultSet results = this.animalRepository.listAllAnimals();
            animals = populateAnimalResultsAsList(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animals;
    }

    //Return list meeting search criteria
    public ArrayList<Animal> listAllAnimals(String name, Integer typeID, Integer animalID) throws SQLException {
        ResultSet resultSet = animalRepository.listAllAnimals(name, typeID, animalID);
        ArrayList<Animal> animals = new ArrayList<>();

        while (resultSet.next()){
            Animal animal = new Animal(
                    resultSet.getInt("animalID"),
                    resultSet.getString("name"),
                    resultSet.getString("typename"),
                    resultSet.getString("breed"),
                    resultSet.getString("description")
            );

            // get the notes
            ResultSet noteResults = noteRepository.listAllNotesByAnimal(animal.getAnimalID());

            while(noteResults.next()){
                Note note = new Note(
                        noteResults.getInt("noteID"),
                        noteResults.getString("text"),
                        noteResults.getString("date")
                );
                animal.getAnimalNotes().add(note);
            }
            //This sets picture according to type
            if(animal.getType().equals("Cat")) {
                animal.setPicture("/images/Cat.png");
            } else if(animal.getType().equals("Dog")) {
                animal.setPicture("/images/Dog.jpg");
            }
            animals.add(animal);
        }
        return animals;
    }

    //return list holding stored animal objects by type (sans notes)
    public ArrayList<Animal> listAllAnimalsWithType(String type) throws SQLException {
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
    public ArrayList<Animal> listAllAnimalsWithName(String name) throws SQLException {
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
    public ArrayList<Animal> listAllAnimalsWithID(int animalID) throws SQLException {
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
    public void addAnimal(Animal animal) throws SQLException {
        if(!animal.getType().isEmpty() && animal.getAnimalTypeID() == -1) {
            animal.setAnimalTypeID(this.typeRepository.getTypeIDByName(animal.getType()));
        }
        this.animalRepository.addAnimal(animal);
    }

    //remove animal from specified index in animal table
    public void removeAnimal(int index) throws SQLException {
        if(!getAllAnimalNotesWithID(index).isEmpty()) {
            removeAllAnimalNotesWithID(getAnimal(index));
        }
        this.animalRepository.removeAnimal(index);
    }

    //return animal from specified index in animal table
    public Animal getAnimal(int index) {
        Animal animal = new Animal();
        try {
            ResultSet results = this.animalRepository.getAnimal(index);
            while (results.next()) {
                animal.setAnimalID(results.getInt("animalID"));
                animal.setName(results.getString("name"));
                animal.setBreed(results.getString("breed"));
                animal.setDescription(results.getString("description"));
                animal.setAnimalTypeID(results.getInt("type"));
                animal.setType(this.typeRepository.getTypeNameByID(results.getInt("type")));
                animal.setAnimalNotes(getAllAnimalNotesWithID(index));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }

    //update animal from specified index with update animal object in animal table
    public void updateAnimal(Animal animal) throws SQLException {
        this.animalRepository.updateAnimal(animal);
    }

    //return list holding all note objects from note table
    public ArrayList<Note> getAllAnimalNotes() throws SQLException {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            ResultSet results = this.noteRepository.listAllNotes();
            while (results.next()) {
                Note note = new Note(
                        results.getInt("noteID"),
                        results.getString("text"),
                        results.getString("date"),
                        results.getInt("animal")
                );
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    //add note associated with animal to note table
    public void addNote(Animal animal, Note note) throws SQLException {
        this.noteRepository.addAnimalNote(animal, note);
    }

    //remove note associated with animal from note table by noteID
    public void removeNote(int noteID) throws SQLException {
        this.noteRepository.removeNote(noteID);
    }

    //return all animal notes in an arrayList given animalID
    public ArrayList<Note> getAllAnimalNotesWithID(int animalID) throws SQLException {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            ResultSet results = this.noteRepository.listAllNotesByAnimal(animalID);
            while (results.next()) {
                Note note = new Note(
                        results.getInt("noteID"),
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
    public void removeAllAnimalNotesWithID(Animal animal) throws SQLException {
        try {
            ResultSet results = this.noteRepository.listAllNotesByAnimal(animal);
            while(results.next()) {
                this.noteRepository.removeNote(animal, results.getInt("noteID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get all types from the type lookup table
    public ArrayList<String> getAllTypesAsStrings() throws SQLException {
        ArrayList<String> types = new ArrayList<>();
        try {
            ResultSet results = this.typeRepository.getAllTypes();
            while (results.next()) {
                types.add(results.getString("typeName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    //get all types from the type lookup table
    public ArrayList<Type> getAllTypesAsTypes() throws SQLException {
        ArrayList<Type> types = new ArrayList<>();
        try {
            ResultSet results = this.typeRepository.getAllTypes();
            while (results.next()) {
                Type tempType = new Type(results.getInt("typeID"), results.getString("typeName"));
                types.add(tempType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    //get typeID from lookup table, given the name of the type
    public int getTypeIDByName(String string) throws SQLException {
        return typeRepository.getTypeIDByName(string);
    }

    //add a type to the type lookup table
    public void addType(String type) throws SQLException {
        this.typeRepository.addType(type);
    }

    public void removeType(int typeID) throws SQLException {
        this.typeRepository.removeType(typeID);
    }

    //replace typename with new string value
    public void updateType(int index, String type) throws SQLException {
            this.typeRepository.updateType(index, type);
    }

    public Type getAnimalType(int typeID) throws SQLException {
        ResultSet result = this.typeRepository.getAnimalType(typeID);
        if(result.next()){
            return new Type(
                    result.getInt("typeID"),
                    result.getString("typename")
            );
        }
        return null;
    }

    //supporting private functions
    //cycle through a given resultSet and return an ArrayList of Animals (sans notes)
    private ArrayList<Animal> populateAnimalResultsAsList(ResultSet results) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        while (results.next()) {
            Animal animal = new Animal(
                    results.getInt("animalID"),
                    results.getString("name"),
                    results.getString("typename"),
                    //this.typeRepository.getTypeNameByID(results.getInt("type")),
                    results.getString("breed"),
                    results.getString("description"),
                    //results.getInt("type")
                    this.typeRepository.getTypeIDByName(results.getString("typename"))
            );
            animals.add(animal);
        }
        return animals;
    }
}