package com.andrewRnagel.animalshelter.servlet;

import com.andrewRnagel.animalshelter.factory.ServiceFactory;
import com.andrewRnagel.animalshelter.service.AnimalsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class AbstractServlet extends HttpServlet{

    AnimalsService animalsService;

    @Override
    public void init() throws ServletException {
        try {
            // get our widget service
            this.animalsService = ServiceFactory.getAnimalsService();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new ServletException("Something went wrong initializing servlet" + this.getClass().getCanonicalName() , e);
        }
    }

    Integer getParameterAsInt(HttpServletRequest req, String name) {

        String param = req.getParameter(name);
        // make "" be null
        param = param == null || param.equals("") ? null : param;

        Integer paramAsInteger = null;
        if (param != null) {
            paramAsInteger = Integer.parseInt(param);
        }

        return paramAsInteger;
    }


    String getParameterAsString(HttpServletRequest req, String name) {
        String param = req.getParameter(name);

        return param == null || param.equals("") ? null : param;
    }
}