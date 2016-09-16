package com.andrewRnagel.animalshelter.servlet;

import com.andrewRnagel.animalshelter.entity.Animal;
import com.andrewRnagel.animalshelter.entity.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/14/16 at 3:24 PM EST.
 */

@WebServlet("")
public class ListAnimalsServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get ArrayList of Types, Animals, and Notes
        try {
            String name = getParameterAsString(req, "name");
            req.setAttribute("name", name);

            Integer typeID = getParameterAsInt(req, "type");
            req.setAttribute("type", typeID);

            Integer animalID = getParameterAsInt(req, "animalID");
            req.setAttribute("animalID", animalID);

            ArrayList<Animal> animalsArray = animalsService.listAllAnimals(name, typeID, animalID);
            req.setAttribute("animalsList", animalsArray);

            ArrayList<Type> typesArray = animalsService.getAllTypesAsTypes();
            req.setAttribute("typesList", typesArray);

        } catch (SQLException e){
            throw new ServletException("Something went wrong! I would panic, and then grab a fire extinguisher.", e);
        }

        // forward the request to fortune.jsp
        req.getRequestDispatcher("/WEB-INF/ListAnimals.jsp").forward(req, resp);
    }
}