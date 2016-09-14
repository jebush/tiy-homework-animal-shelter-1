package com.andrewRnagel.animalshelter.repo;
import com.andrewRnagel.animalshelter.entity.Type;

import java.sql.*;

/**
 * Created by Andrew Nagel on 9/12/16 at 4:12 PM EST.
 */

public class TypeRepository {
    //object properties
    private Connection conn;

    //constructors
    //constructor for database specificity
    public TypeRepository(String jdbcUrl) throws SQLException {
        this.conn = DriverManager.getConnection(jdbcUrl);
    }

    //methods
    //return ResultSet of types in type table (ALL, Alphabetical organized)
    public ResultSet getAllTypes() throws SQLException {
        Statement stmt = this.conn.createStatement();
        return stmt.executeQuery("SELECT typeName FROM type ORDER BY typename ASC");
    }

    //add animal type to type table (does not test for uniqueness)
    public void addType(String type) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO type (typeName) VALUES (?)");
        stmt.setString(1, type);
        stmt.executeUpdate();
    }

    //add type to type table (does not test for uniqueness)
    public void addType(Type type) throws SQLException{
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO type (typename) VALUES (?)");
        stmt.setString(1, type.getType());
        stmt.executeUpdate();
    }

    //remove an animal type from the type table
    public void removeType(int typeID) throws SQLException {
        //Parameter/Sanitized SQL query
        //Confirm and delete specific note
        PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM type WHERE typeID = ?");
        stmt.setInt(1, typeID);
        stmt.executeUpdate();
    }

    //get note from note tableID
    public ResultSet getAnimalType(int typeID) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM type WHERE typeID = ?");
        stmt.setInt(1, typeID);
        return stmt.executeQuery();
    }

    //update animal type at specified index in type table
    public void updateType(int index, String type) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("UPDATE type SET typeName = ? WHERE typeID = ?");
        stmt.setString(1, type);
        stmt.setInt(2, index);
        stmt.executeUpdate();
    }

    //get animal type from specified index in type table (index from type table --> String type name)
    public String getTypeNameByID(int index) throws SQLException{
        String returnString = "";
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT typename FROM type WHERE typeID = ?");
        stmt.setInt(1, index);
        ResultSet strResult = stmt.executeQuery();
        if(strResult.next()) {
            returnString = strResult.getString(1);
        }
        return returnString;
    }

    //get animal type from specified index in type table (String type in table --> int typeID from type table)
    public int getTypeIDByName(String type) throws SQLException{
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
}