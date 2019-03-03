/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n8_Bonusx
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package datosEstudiantes;

import java.util.ArrayList;

/**
 * This exception is thrown when the date format doesn't match the expected date format. It
 * will parse the different format and generate the report.  <br>
 * The message associated with the exception will be displayed on the console.
 */
public class DateFormatException extends Exception {
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Year in which student performed the action.
     */
    private String year;

    /**
     * Month in which the student accessed the file;
     */
    private String month;

    /**
     * Day in which the student accessed the file;
     */
    private String day;

    /**
     * Hour in which the student accessed the file;
     */
    private String hour;

    /**
     * Second in which the student accessed the file;
     */
    private String second;

    /**
     * Millisecond in which the student accessed the file;
     */
    private String milli;


    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     *
     * @param pEvents
     * @param pId
     * @param pActivity
     * @param pTime
     */
    public DateFormatException(ArrayList<Event> pEvents, String pId, String pActivity,
                               String pTime) {
        super("Date format (" + pTime + ") doesn't match the default pattern.");

    }
}
