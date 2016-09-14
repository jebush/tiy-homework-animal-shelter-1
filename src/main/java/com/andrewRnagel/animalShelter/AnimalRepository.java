package com.andrewRnagel.animalShelter;
import java.sql.*;

/**
 * Created by Andrew Nagel on 8/30/16 at 4:04 PM EST.
 */

public class AnimalRepository {
    //object properties
    private Connection conn;

    //constructors
    //constructor for database specificity
    public AnimalRepository(String jdbcUrl) throws SQLException {
        this.conn = DriverManager.getConnection(jdbcUrl);
    }

    //methods
    //return ResultSet of animals in animal table (ALL)
    protected ResultSet listAllAnimals() throws SQLException {
        PreparedStatement stmt = this.conn.prepareStatement("SELECT animal.animalID, animal.name, type.typename, animal.breed, animal.description, type.typeid FROM animal JOIN type ON animal.type = type.typeid;");
        return stmt.executeQuery();
        //Statement stmt = this.conn.createStatement();
        //return stmt.executeQuery("SELECT * FROM animal");
    }

    //return ResultSet of animals in animal table (by type, String)
    protected ResultSet listAllAnimalsByType(String type) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT animal.animalID, animal.name, type.typename, animal.breed, animal.description FROM animal JOIN type ON animal.type = type.typeid WHERE typename = ?;");
        stmt.setString(1, type);
        return stmt.executeQuery();
    }

    //return ResultSet of animals in animal table (by name, Substring)
    protected ResultSet listAllAnimalsByName(String name) throws SQLException {
        //Parameter/Sanitized SQL query
        //perform lookup on animal table for animals containing provided substring
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM animal WHERE upper(name) LIKE ?");
        name = "%" + name.toUpperCase() + "%";
        stmt.setString(1, name);
        return stmt.executeQuery();
    }

    //return ResultSet of animals in animal table (by name, Substring)
    protected ResultSet listAllAnimalsByID(int animalID) throws SQLException {
        //Parameter/Sanitized SQL query
        //perform lookup on animal table for animals containing provided substring
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM animal WHERE animalID = ?");
        stmt.setInt(1, animalID);
        return stmt.executeQuery();
    }

    //add animal to animal table
    protected void addAnimal(Animal animal) throws SQLException{
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO animal (name, breed, description, type) VALUES (?, ?, ?, ?)");
        stmt.setString(1, animal.getName());
        stmt.setString(2, animal.getBreed());
        stmt.setString(3, animal.getDescription());
        stmt.setInt(4, animal.getAnimalTypeID());
        stmt.executeUpdate();
    }

    //remove animal from specified index in animal table
    protected void removeAnimal(int index) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM animal WHERE animalID = ?");
        stmt.setInt(1, index);
        stmt.executeUpdate();
    }

    //get animal from specified index in animal table
    protected ResultSet getAnimal(int index) throws SQLException{
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM animal WHERE animalID = ?");
        stmt.setInt(1, index);
        return stmt.executeQuery();
    }

    //update animal at specified index in animal table
    protected void updateAnimal(Animal animal) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("UPDATE animal SET name = ?, breed = ?, description = ?, type = ? WHERE animalID = ?");
        stmt.setString(1, animal.getName());
        stmt.setString(2, animal.getBreed());
        stmt.setString(3, animal.getDescription());
        stmt.setInt(4, animal.getAnimalTypeID());
        stmt.setInt(5, animal.getAnimalID());
        stmt.executeUpdate();
    }
}