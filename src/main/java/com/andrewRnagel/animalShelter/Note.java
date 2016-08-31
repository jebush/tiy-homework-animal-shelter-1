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
    private int noteID;
    //the text of the note, which cannot be null
    private String noteContent;
    //date and time note was created, which is automatically populated
    private LocalDateTime noteCreationDateTime;

    //constructors
    //default constructor
    public Note() {}

    //constructor for note made in program (date set automatically)
    public Note(int noteID, String noteContent) {
        this.noteID = noteID;
        this.noteContent = noteContent;
        this.noteCreationDateTime = LocalDateTime.now();
    }

    //constructor for note loaded from note table (date read from table)
    public Note(int noteID, String noteContent, String date) {
        this.noteID = noteID;
        this.noteContent = noteContent;
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        this.noteCreationDateTime = LocalDateTime.parse(date, formatter);
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
        LocalDate date = this.noteCreationDateTime.toLocalDate();
        return formatter.format(date);
    }

    protected String getNoteCreationTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        LocalTime time = this.noteCreationDateTime.toLocalTime();
        return formatter.format(time);
    }

    //setters
    protected void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}