package com.andrewRnagel.animalshelter.servlet;

/**
 * Created by Andrew Nagel on 9/14/16 at 3:24 PM EST.
 */

import com.andrewRnagel.animalshelter.entity.Animal;
import com.andrewRnagel.animalshelter.entity.Type;
import com.andrewRnagel.animalshelter.entity.Note;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("")
public class WidgetListServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get ArrayList of Types, Animals, and Notes
        ArrayList<Animal> animalsArray;
        ArrayList<Type> typesArray;
        try {
            typesArray = animalsService.getAllTypesAsTypes();
            animalsArray = animalsService.getAllAnimals();
            for(Animal animal : animalsArray) {
                animal.setAnimalNotes(animalsService.getAllAnimalNotesWithID(animal.getAnimalID()));
            }
        } catch (SQLException e) {
            throw new ServletException("Something went wrong!", e);
        }

        // add it to the request as an attribute
        req.setAttribute("typesList", typesArray);
        req.setAttribute("animalsList", animalsArray);

        // forward the request to fortune.jsp
        req.getRequestDispatcher("/WEB-INF/ListAnimals.jsp").forward(req, resp);
    }
}