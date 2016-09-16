package com.andrewRnagel.animalshelter.servlet;

import com.andrewRnagel.animalshelter.entity.Animal;
import com.andrewRnagel.animalshelter.entity.Note;
import com.andrewRnagel.animalshelter.entity.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/16/16 at 2:59 PM EST.
 */

@WebServlet("/AnimalNote")
public class AnimalNotesServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get animal requested
        try {
            //get animalID
            String animalID = getParameterAsString(req, "animalID");

            //make blank animal
            Animal animal = new Animal();

            //load the animal if one is selected
            if(animalID != null) {
                //get the animal
                animal = animalsService.getAnimal(Integer.parseInt(animalID));
            }

            //load animal into attribute
            req.setAttribute("animal", animal);
        } catch (Exception e){
            throw new ServletException("Something went wrong! I would panic, and then grab a fire extinguisher.", e);
        }

        // forward the request to EditAnimals.jsp
        req.getRequestDispatcher("/WEB-INF/AnimalNotes.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer animalID = getParameterAsInt(req, "animalID");
            req.setAttribute("animalID", animalID);

            String animalNoteText = getParameterAsString(req, "animalNoteText");
            req.setAttribute("animalNoteText", animalNoteText);

            //Add note
            if (!animalNoteText.isEmpty() && !(animalNoteText.trim().equals(""))) {
                Note note = new Note(animalID, animalNoteText);

                animalsService.addNote(animalID, note);
            }


            //redirect on completion
            resp.sendRedirect("/AnimalNote?animalID="+ animalID);
        } catch (SQLException e) {
            throw new ServletException("Something went wrong! I would panic, and then grab a fire extinguisher.", e);
        }
    }
}