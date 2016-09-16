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
 * Created by Andrew Nagel and Jimmy Bush on 9/16/16 at 10:52 AM EST.
 */

@WebServlet("/EditAnimal")
public class EditAnimalsServlet extends AbstractServlet{
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

            //load all types for dropdown
            ArrayList<Type> typesArray = animalsService.getAllTypesAsTypes();
            req.setAttribute("typesList", typesArray);
        } catch (SQLException e){
            throw new ServletException("Something went wrong! I would panic, and then grab a fire extinguisher.", e);
        }

        // forward the request to EditAnimals.jsp
        req.getRequestDispatcher("/WEB-INF/EditAnimals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer animalID = getParameterAsInt(req, "animalID");
            req.setAttribute("animalID", animalID);

            String name = getParameterAsString(req, "name");
            req.setAttribute("name", name);

            Integer typeID = getParameterAsInt(req, "type");
            req.setAttribute("type", typeID);

            String breed = getParameterAsString(req, "breed");
            req.setAttribute("breed", breed);

            String description = getParameterAsString(req, "description");
            req.setAttribute("description", description);

            Animal animal;
            Type type = animalsService.getAnimalType(typeID);

            //edit or add
            if(animalID > 0 ) {
                //if animalID != 0, edit animal
                animal = animalsService.getAnimal(animalID);
                animal.setName(name);
                animal.setType(type.getType());
                if(breed != null || breed == "") {
                    animal.setBreed(breed.trim());
                }
                animal.setDescription(description);
                animal.setAnimalTypeID(type.getTypeID());
            } else {
                //else create new animal
                if(breed == null || breed == "") {
                    animal = new Animal(animalID, name, type.getType(), description, type.getTypeID());
                } else {
                    animal = new Animal(animalID, name, type.getType(), breed, description, type.getTypeID());
                }
            }

            //perform the work
            if(animal.getAnimalID() > 0) {
                animalsService.updateAnimal(animal);
            } else {
                animalsService.addAnimal(animal);
            }

            //redirect on completion
            resp.sendRedirect("/");
        } catch (SQLException e) {
            throw new ServletException("Something went wrong! I would panic, and then grab a fire extinguisher.", e);
        }
    }
}