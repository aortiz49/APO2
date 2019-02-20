/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n8_Sports
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiSports.world;


/**
 * This exception is thrown when there is an error reading or writing a file with the
 * information of the state of the model of the world.  <br>
 * The message associated with the exception must describe the problem presented.
 */
public class PersistenceException extends Exception {
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructs the exception with the message that passes as a parameter and describes
     * the problem.
     *
     * @param cause The message describing the problem.
     */
    public PersistenceException(String cause) {
        super(cause);
    }
}
