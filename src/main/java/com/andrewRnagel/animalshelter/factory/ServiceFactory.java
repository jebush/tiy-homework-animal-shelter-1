package com.andrewRnagel.animalshelter.factory;

import com.andrewRnagel.animalshelter.repo.AnimalRepository;
import com.andrewRnagel.animalshelter.repo.TypeRepository;
import com.andrewRnagel.animalshelter.repo.NoteRepository;
import com.andrewRnagel.animalshelter.service.AnimalsService;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by doug on 9/15/16.
 * Modified by Jimmy and Andrew on 9/15/16.
 */

public class ServiceFactory {
    // the JDBC url
    private static String jdbcUrl = "jdbc:postgresql://localhost/animalshelter";

    // the private widgetService
    private static AnimalsService animalsService;

    /**
     * This method returns a single animalsService that is shared among anything
     * that uses this method to load it.
     * @return AnimalsService
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static AnimalsService getAnimalsService() throws ClassNotFoundException, SQLException, IOException {
        // if the widget service hasn't been created yet, then we need to create it.
        if(ServiceFactory.animalsService == null){
            Class.forName("org.postgresql.Driver");
            AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
            TypeRepository typeRepository = new TypeRepository(jdbcUrl);
            NoteRepository noteRepository = new NoteRepository(jdbcUrl);
            ServiceFactory.animalsService = new AnimalsService(animalRepository, noteRepository, typeRepository);
        }
        return animalsService;
    }
}