/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: Linked Lists practice
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package world;

import java.io.Serializable;


/**
 * Class that represents the the seat on an airplane. <br>
 * <b>inv:</b><br>
 * The amount of seats available is greater than or equal to zero. <br>
 * The class of the seat belongs to one of the values in the class constants array. <br>
 * The price of the ticket is greater than or equal to zero. <br>
 * The number of the seat is greater than zero. <br>
 */
public class Seat implements Serializable {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Serialization constant for the Seat class.
     */
    public static final long serialVersionUID = 371337967955973683L;

    /**
     * Array of constants containing the possible classes for a seat on the airplane.
     */
    public final static String[] CLASSES = {"First class", "Second class", "Business", "VIP"};

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Number of the seat.
     */
    private int number;

    /**
     * Name of the passenger in the seat.
     */
    private String passenger;

    /**
     * Class of the seat.
     */
    private String seatClass;


    /**
     * Next seat.
     */
    private Seat nextSeat;

    /**
     * Indicates if seat was sold or not.
     */
    private boolean sold;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Seat constructor. <br>
     * <b>post: </b> Constructs a new seat with the information given by the parameter.
     *
     * @param pNumber    Number of the seat. pNumber > 0.
     * @param pPassenger Amount of seats on the seat.
     *                   pPassenger >= 0.
     * @param pClass     Class of the seat. pClass belongs to CLASSES.
     * @param pPrice     Price of one ticket. pPrice >= 0.
     */
    public Seat(int pNumber, String pPassenger, String pClass, double pPrice) {
        number = pNumber;
        passenger = pPassenger;
        seatClass = pClass;
        nextSeat = null;
    }

    /**
     * Returns the number of the seat.
     *
     * @return Seat's number.
     */
    public int getNumber() {
        return number;
    }


    /**
     * Returns the passenger of the seat.
     *
     * @return Seat's passenger.
     */

    public String getPassenger() {
        return passenger;
    }


    /**
     * Returns the class of the seat.
     *
     * @return Seat's class.
     */
    public String getSeatClass() {
        return seatClass;
    }



    /**
     * Returns the next seat of the current seat.
     *
     * @return Seat's next seat.
     */
    public Seat getNextSeat() {
        return nextSeat;
    }


    /**
     * Returns the seat's purchase status.
     *
     * @return Seat's purchase status.
     */
    public boolean isSold() {
        return sold;
    }


    /**
     * Changes the next seat for the one given by the parameter. <br>
     * <b>post:</b> The next seat was changed for the seat given by the parameter. <br>
     *
     * @param pNextSeat Seat to be assigned as the next seat.
     *                  pNextSeat != null.
     */
    public void changeNextSeat(Seat pNextSeat) {
        nextSeat = pNextSeat;
    }

    /**
     * Sells a ticket. <br>
     * <b>post:</b> The ticket was sold. <br>
     *
     * @return True if he ticket was sold, false if otherwise.
     */
    public void sellTicket() {
        sold = true;
    }


}
