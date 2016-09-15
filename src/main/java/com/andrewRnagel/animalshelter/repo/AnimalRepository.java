package com.andrewRnagel.animalshelter.repo;
import com.andrewRnagel.animalshelter.entity.Animal;

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
    public ResultSet listAllAnimals() throws SQLException {
        PreparedStatement stmt = this.conn.prepareStatement("SELECT animal.animalID, animal.name, type.typename, animal.breed, animal.description, type.typeid FROM animal JOIN type ON animal.type = type.typeid;");
        return stmt.executeQuery();
        //Statement stmt = this.conn.createStatement();
        //return stmt.executeQuery("SELECT * FROM animal");
    }

    //return ResultSet of animals in animal table by(name, typeID, animalID)
    public ResultSet listAllAnimals(String name, Integer typeID, Integer ID) throws SQLException {
        String sql = "SELECT a.*, t.typename FROM animal as a LEFT JOIN type as t ON a.type = t.typeID WHERE 1 = 1 ";

        if(name != null){
            sql += " AND upper(a.name) LIKE upper(?) ";
        }
        if(typeID != null){
            sql += " AND t.typeID = ? ";
        }
        if(ID != null){
            sql += " AND a.animalID = ? ";
        }

        PreparedStatement statement = conn.prepareStatement(sql);
        int indexOfQuestionMark = 0;

        if(name != null){
            statement.setString(++indexOfQuestionMark, "%" + name + "%");
        }
        if(typeID != null){
            statement.setInt(++indexOfQuestionMark, typeID);
        }
        if(ID != null){
            statement.setInt(++indexOfQuestionMark, ID);
        }
        return statement.executeQuery();
    }

    //return ResultSet of animals in animal table (by type, String)
    public ResultSet listAllAnimalsByType(String type) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT animal.animalID, animal.name, type.typename, animal.breed, animal.description FROM animal JOIN type ON animal.type = type.typeid WHERE typename = ?;");
        stmt.setString(1, type);
        return stmt.executeQuery();
    }

    //return ResultSet of animals in animal table (by name, Substring)
    public ResultSet listAllAnimalsByName(String name) throws SQLException {
        //Parameter/Sanitized SQL query
        //perform lookup on animal table for animals containing provided substring
        PreparedStatement stmt = this.conn.prepareStatement("SELECT animal.animalID, animal.name, type.typename, animal.breed, animal.description FROM animal JOIN type ON animal.type = type.typeid WHERE upper(name) LIKE ?;");
        name = "%" + name.toUpperCase() + "%";
        stmt.setString(1, name);
        return stmt.executeQuery();
    }

    //return ResultSet of animals in animal table (by name, Substring)
    public ResultSet listAllAnimalsByID(int animalID) throws SQLException {
        //Parameter/Sanitized SQL query
        //perform lookup on animal table for animals containing provided substring
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM animal WHERE animalID = ?");
        stmt.setInt(1, animalID);
        return stmt.executeQuery();
    }

    //add animal to animal table
    public void addAnimal(Animal animal) throws SQLException{
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO animal (name, breed, description, type) VALUES (?, ?, ?, ?)");
        stmt.setString(1, animal.getName());
        stmt.setString(2, animal.getBreed());
        stmt.setString(3, animal.getDescription());
        stmt.setInt(4, animal.getAnimalTypeID());
        stmt.executeUpdate();
    }

    //remove animal from specified index in animal table
    public void removeAnimal(int index) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM animal WHERE animalID = ?");
        stmt.setInt(1, index);
        stmt.executeUpdate();
    }

    //get animal from specified index in animal table
    public ResultSet getAnimal(int index) throws SQLException{
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM animal WHERE animalID = ?");
        stmt.setInt(1, index);
        return stmt.executeQuery();
    }

    //update animal at specified index in animal table
    public void updateAnimal(Animal animal) throws SQLException {
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