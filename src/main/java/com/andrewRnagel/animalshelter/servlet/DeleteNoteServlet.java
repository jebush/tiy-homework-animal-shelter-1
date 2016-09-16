package com.andrewRnagel.animalshelter.servlet;

import com.andrewRnagel.animalshelter.entity.Animal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/16/16 at 4:35 PM EST.
 */
@WebServlet("/DeleteNote")
public class DeleteNoteServlet extends AbstractServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get animal requested
        try {
            //get animalID
            Integer animalID = getParameterAsInt(req, "animalID");

            //get noteID
            Integer noteID = getParameterAsInt(req, "noteID");

            //delete note
            animalsService.removeNote(noteID);

            resp.sendRedirect("/AnimalNote?animalID=" + animalID);
        } catch (Exception e){
            throw new ServletException("Something went wrong! I would panic, and then grab a fire extinguisher.", e);
        }
    }
}
