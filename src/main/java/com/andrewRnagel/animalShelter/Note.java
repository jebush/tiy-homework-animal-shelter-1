package com.andrewRnagel.animalShelter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Created by Andrew Nagel on 8/30/16 at 2:41 PM EST.
 */

public class Note {
    //object properties
    private int noteID = -1;
    private String noteContent;
    private LocalDate noteCreationDate;

    //constructors
    //default constructor
    public Note() {}

    //constructor for note (date set automatically to NOW)
    //used when creating a new Note from within AnimalShelter program
    public Note(String noteContent) {
        this.noteContent = noteContent;
        this.noteCreationDate = LocalDate.now();
    }

    //constructor for note (date read from table)
    //used when reading an existing Note from the note table
    public Note(int noteID, String noteContent, String date) {
        this.noteID = noteID;
        this.noteContent = noteContent;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.noteCreationDate = LocalDate.parse(date, formatter);
    }

    //methods
    //getters
    //SQL format yyyy-mm-dd
    protected LocalDate getNoteCreationDateAsLocalDate() {
        return this.noteCreationDate;
    }

    protected String getNoteCreationDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        return this.noteCreationDate.format(formatter);
    }

    protected String getNoteContent() {
        return this.noteContent;
    }

    protected int getNoteID() {
        return this.noteID;
    }

    //setters
    protected void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}