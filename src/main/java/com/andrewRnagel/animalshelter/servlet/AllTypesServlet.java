package com.andrewRnagel.animalshelter.servlet;

/**
 * Created by Andrew Nagel on 9/14/16 at 3:24 PM EST.
 */

import com.andrewRnagel.animalshelter.repo.AnimalRepository;
import com.andrewRnagel.animalshelter.repo.NoteRepository;
import com.andrewRnagel.animalshelter.repo.TypeRepository;
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
public class AllTypesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //declare data source
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String jdbcUrl = "jdbc:postgresql://localhost:5432/animalshelter";
        //instantiate dependent services from their classes
        AnimalRepository dataRepo = null;
        NoteRepository noteRepo = null;
        TypeRepository typeRepo = null;
        AnimalsService animalsService = null;
        try {
            dataRepo = new AnimalRepository(jdbcUrl);
            noteRepo = new NoteRepository(jdbcUrl);
            typeRepo = new TypeRepository(jdbcUrl);
            animalsService = new AnimalsService(dataRepo, noteRepo, typeRepo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // get ArrayList of Animals
        ArrayList<String> typesArray = null;
        try {
           typesArray = animalsService.getAllTypes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // add it to the request as an attribute
        req.setAttribute("typesList", typesArray);

        // forward the request to fortune.jsp
        req.getRequestDispatcher("/WEB-INF/ListAnimals.jsp").forward(req, resp);
    }
}