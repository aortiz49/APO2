/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n7_Hurricane
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiSports.world;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * This exception is thrown when trying to add a repeated element to the sports system. <br>
 * The message associated with the exception should indicate the player or the sport that
 * caused the error.
 */
public class ElementExistsException extends Exception {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Used to represent a repeated element of type sport.
     */
    public static final String REPEATED_SPORT = "sport";

    /**
     * Used to represent a repeated element of type athlete.
     */
    public static final String REPEATED_ATHLETE = "athlete";

    /**
     * Name of the file that registers the program errors.
     */
    private static final String LOG_FILE = "./data/error.log";

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructs the exception indicating the player or sport that caused the error.
     *
     * @param pType    Type of the repeated element.
     * @param elemName Name of the repeated element.
     */
    public ElementExistsException(String pType, String elemName) {
        super(elemName);
        registrarError(pType, elemName);
    }

    /**
     * Registra en el archivo de log del programa toda la informaci�n referente a una excepci�n,
     * ocurrida durante el proceso de persistencia
     *
     * @param pType    Type of the repeated element.
     * @param elemName Name of the repeated element.
     */
    public void registrarError(String pType, String elemName) {
        try {
            FileWriter out = new FileWriter(LOG_FILE, true);
            PrintWriter log = new PrintWriter(out);

            Date currentDate= new Date();
            log.println("Fecha:            " + currentDate.toString());
            log.println("---------------------------------------");
            log.println("Discotienda.java :" + new Date().toString());
            log.println("---------------------------------------");
            log.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

