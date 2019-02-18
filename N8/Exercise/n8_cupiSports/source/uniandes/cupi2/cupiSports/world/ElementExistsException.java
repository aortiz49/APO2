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

public class ElementExistsException extends Exception {

    /**
     * This exception is thrown when trying to add a repeated element to the sports system. <br>
     * The message associated with the exception should indicate the player or the sport that
     * caused the error.
     */

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructs the exception indicating the player or sport that caused the error.
     *
     * @param elemName Name of the repeated element;
     */
    public ElementExistsException(String elemName) {
        super(elemName);
    }
}

