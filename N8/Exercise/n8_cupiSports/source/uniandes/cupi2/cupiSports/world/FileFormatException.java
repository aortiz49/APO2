
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

/**
 * This exception is thrown when there is a problem processing the file with the information to
 * update the athletes. <br>
 * The message associated with the exception describes the problem that occurred.
 */
public class FileFormatException extends Exception {

    public FileFormatException(String pCause) {
        super(pCause);
    }
}
