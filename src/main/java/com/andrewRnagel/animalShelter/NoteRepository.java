package com.andrewRnagel.animalShelter;
import java.sql.*;

/**
 * Created by Andrew Nagel on 8/30/16 at 2:45 PM EST.
 */

public class NoteRepository {
    //object properties
    private Connection conn;

    //constructors
    //constructor for database specificity
    public NoteRepository(String jdbcUrl) throws SQLException {
        this.conn = DriverManager.getConnection(jdbcUrl);
    }

    //methods
    //return ResultSet of notes for specific animal in note table
    protected ResultSet listAllNotes() throws SQLException {
        Statement stmt = this.conn.createStatement();
        return stmt.executeQuery("SELECT * FROM note");
    }

    //return ResultSet of notes for specific animal in note table by object
    protected ResultSet listAllNotesByAnimal(Animal animal) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM note WHERE animal = ?");
        stmt.setInt(1, animal.getAnimalID());
        return stmt.executeQuery();
    }

    //return ResultSet of notes for specific animal in note table by index
    protected ResultSet listAllNotesByAnimal(int index) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM note WHERE animal = ?");
        stmt.setInt(1, index);
        return stmt.executeQuery();
    }

    //add note to note table
    protected void addAnimalNote(Animal animal, Note note) throws SQLException{
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO note (animal, date, text) VALUES (?, ?, ?)");
        stmt.setString(1, Integer.toString(animal.getAnimalID()));
        stmt.setString(2, note.getNoteCreationDateSQL());
        stmt.setString(3, note.getNoteContent());
        stmt.executeUpdate();
    }

    //get note from note table
    protected ResultSet getAnimalNote(int noteid) throws SQLException {
        //Parameter/Sanitized SQL query
        PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM note WHERE noteid = ?");
        stmt.setString(1, Integer.toString(noteid));
        return stmt.executeQuery();
    }

    //remove an animal note from the note table
    protected void removeNote(int noteid) throws SQLException {
        //Parameter/Sanitized SQL query
        //Confirm and delete specific note
        PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM note WHERE noteid = ?");
        stmt.setInt(1, noteid);
        stmt.executeUpdate();
    }

    //remove an animal note from the note table
    protected void removeNote(Animal animal, int noteid) throws SQLException {
        //Parameter/Sanitized SQL query
        //Retrieve all note
        listAllNotesByAnimal(animal);
        //Confirm and delete specific note
        PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM note WHERE noteid = ?");
        stmt.setInt(1, noteid);
        stmt.executeUpdate();
    }
}