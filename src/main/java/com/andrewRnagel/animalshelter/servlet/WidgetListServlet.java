package com.andrewRnagel.animalshelter.servlet;

/**
 * Created by Andrew Nagel on 9/14/16 at 3:24 PM EST.
 */

import com.andrewRnagel.animalshelter.entity.Animal;
import com.andrewRnagel.animalshelter.entity.Type;
import com.andrewRnagel.animalshelter.entity.Note;
import com.andrewRnagel.animalshelter.repo.AnimalRepository;
import com.andrewRnagel.animalshelter.repo.TypeRepository;
import com.andrewRnagel.animalshelter.repo.NoteRepository;
import com.andrewRnagel.animalshelter.service.AnimalsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("")
public class WidgetListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //instantiate JDBC resource
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String jdbcUrl = "jdbc:postgresql://localhost:5432/animalshelter";
        //declare repositories
        AnimalRepository dataRepo = null;
        NoteRepository noteRepo = null;
        TypeRepository typeRepo = null;
        try {
            dataRepo = new AnimalRepository(jdbcUrl);
            noteRepo = new NoteRepository(jdbcUrl);
            typeRepo = new TypeRepository(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //instantiate service dependent on (3) repositories
        AnimalsService animalsService = new AnimalsService(dataRepo, noteRepo, typeRepo);

        // get ArrayList of Types, Animals, and Notes
        ArrayList<Animal> animalsArray;
        ArrayList<Type> typesArray;
        ArrayList<Note> notesArray;
        try {
            typesArray = animalsService.getAllTypesAsTypes();
            animalsArray = animalsService.getAllAnimals();
            for(Animal animal : animalsArray) {
                animal.setAnimalNotes(animalsService.getAllAnimalNotesWithID(animal.getAnimalID()));
            }
            //notesArray = animalsService.getAllAnimalNotes();
        } catch (SQLException e) {
            throw new ServletException("Something went wrong!", e);
        }

        // add it to the request as an attribute
        req.setAttribute("typesList", typesArray);
        req.setAttribute("animalsList", animalsArray);
        //req.setAttribute("notesArray", notesArray);

        // forward the request to fortune.jsp
        req.getRequestDispatcher("/WEB-INF/ListAnimals.jsp").forward(req, resp);
    }

//    public static int getNoteQuantity(int animalID) {
//        return animalsService.getAllAnimalNotesWithID(animalID);
//    }
}