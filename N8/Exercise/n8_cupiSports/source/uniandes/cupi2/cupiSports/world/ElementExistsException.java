/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n8_Hurricane
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
    // Attributes
    // -----------------------------------------------------------------

    /**
     * This is the type of element which is repeated.
     */
    private String repeatedElementType;

    /**
     * This is the name of the element which is repeated.
     */
    private String nameOfRepeatedElement;


    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructs the exception indicating the player or sport that caused the error.
     *
     * @param pRepeatedElementType Type of the repeated element.
     * @param pNameOfRepeatedName  Name of the repeated element.
     */
    public ElementExistsException(String pRepeatedElementType, String pNameOfRepeatedName) {
        super(pNameOfRepeatedName);
        repeatedElementType = pRepeatedElementType;
        nameOfRepeatedElement = pNameOfRepeatedName;
        registrarError();
    }

    /**
     * Registers the errors related to persistence in a log file. <br>
     *
     **/
    public void registrarError() {
        try {
            PrintWriter log = new PrintWriter(new FileWriter(LOG_FILE, true));

            Date currentDate = new Date();
            log.println("-----------------------------------------------------------------");
            log.println(currentDate.toString() + ":" + repeatedElementType + ":"
                                + nameOfRepeatedElement);
            log.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

