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
    //TODO (FUTURE): Use JOIN for Type Queries
    //return ResultSet of animals in animal table (ALL)
    protected ResultSet listAllAnimals() throws SQLException {
        Statement stmt = this.conn.createStatement();
        return stmt.executeQuery("SELECT * FROM animal");
        //return stmt.executeQuery("SELECT animal.animalid, animal.name, type.typename, animal.species, animal.breed, animal.description FROM animal JOIN type ON animal.type = type.typeid;");
    }

    //return ResultSet of animals in animal table (by type, String)
    protected ResultSet listAllAnimalsByType(String type) throws SQLException {
        //Parameter/Sanitized SQL query
        //perform lookup on type table for typeID
        int typeID = getTypeIDByName(type);
        //perform lookup on animal table for animals with typeID for provided type
        PreparedStatement stmt2 = this.conn.prepareStatement("SELECT * FROM animal WHERE type = ?");
        stmt2.setInt(1, typeID);
        return stmt2.executeQuery();
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
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM animal WHERE animalid = ?");
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
        PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM animal WHERE animalid = ?");
        stmt.setInt(1, index);
        stmt.executeUpdate();
    }

    //get animal from specified index in animal table
    protected ResultSet getAnimal(int index) throws SQLException{
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM animal WHERE animalid = ?");
        stmt.setInt(1, index);
        return stmt.executeQuery();
    }

    //update animal at specified index in animal table
    protected void updateAnimal(int index, Animal animal) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("UPDATE animal SET name = ?, breed = ?, description = ?, type = ? WHERE animalid = ?");
        stmt.setString(1, animal.getName());
        stmt.setString(2, animal.getBreed());
        stmt.setString(3, animal.getDescription());
        stmt.setInt(4, getTypeIDByName(animal.getType()));
        stmt.setInt(5, animal.getAnimalID());
        stmt.executeUpdate();
    }

    //return ResultSet of types in type table (ALL, Alphabetical organized)
    public ResultSet getAllTypes() throws SQLException {
        Statement stmt = this.conn.createStatement();
        return stmt.executeQuery("SELECT typeName FROM type ORDER BY typename ASC");
    }

    //add animal type to type table
    protected void addType(String type) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO type (typeName) VALUES (?)");
        stmt.setString(1, type);
        stmt.executeUpdate();
    }

    //remove animal type from specified index in type table
    protected void removeType(int index) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM type WHERE typeid = ?");
        stmt.setInt(1, index);
        stmt.executeUpdate();
    }

    //get animal type from specified index in type table (index from type table --> String type name)
    protected String getTypeNameByID(int index) throws SQLException{
        String returnString = "";
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT typename FROM type WHERE typeid = ?");
        stmt.setInt(1, index);
        ResultSet strResult = stmt.executeQuery();
        if(strResult.next()) {
            returnString = strResult.getString(1);
        }
        return returnString;
    }

    //get animal type from specified index in type table (String type in table --> int typeid from type table)
    protected int getTypeIDByName(String type) throws SQLException{
        int returnInt = -1;
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM type WHERE upper(typeName) = ?");
        type = type.toUpperCase();
        stmt.setString(1, type);
        ResultSet intResult = stmt.executeQuery();
        if(intResult.next()) {
            returnInt = ((Number)intResult.getObject(1)).intValue();
        }
        return returnInt;
    }

    //update animal type at specified index in type table
    protected void updateType(int index, String type) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("UPDATE type SET typeName = ? WHERE typeid = ?");
        stmt.setString(1, type);
        stmt.setInt(2, index);
        stmt.executeUpdate();
    }
}