package com.andrewRnagel.animalShelter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Created by Andrew Nagel on 8/30/16 at 2:41 PM EST.
 */

public class Note {
    //object properties
    //the ID of the note in the database
    private int noteID = -1;
    //the text of the note, which cannot be null
    private String noteContent;
    //date and time note was created, which is automatically populated
    private LocalDate noteCreationDate;

    //constructors
    //default constructor
    public Note() {}

    //constructor for note (date set automatically to NOW)
    //used when creating a new Note from within AnimalShelter program
    //SQL format yyyy-mm-dd
    public Note(int noteID, String noteContent) {
        this.noteID = noteID;
        this.noteContent = noteContent;
        this.noteCreationDate = LocalDate.now();
    }

    //constructor for note (date read from table)
    //used when reading an existing Note from the note table
    //SQL format yyyy-mm-dd
    public Note(int noteID, String noteContent, String date) {
        this.noteID = noteID;
        this.noteContent = noteContent;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.noteCreationDate = LocalDate.parse(date, formatter);
    }

    //methods
    //getters
    protected String getNoteContent() {
        return this.noteContent;
    }

    protected int getNoteID() {
        return this.noteID;
    }

    protected String getNoteCreationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        return this.noteCreationDate.format(formatter);
    }

    protected String getNoteCreationDateSQL() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.noteCreationDate.format(formatter);
    }
    //setters
    protected void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}