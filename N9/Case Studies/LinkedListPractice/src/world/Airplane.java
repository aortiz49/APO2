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

import java.io.PrintWriter;
import java.util.ArrayList;

public class Airplane {


    // -------------------------------------------------------------
    // Constants
    // -------------------------------------------------------------

    /**
     * Serialization constant for the Airplane class.
     */
    private static final long serialVersionUID = -3643918633848066198L;

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * String that identifies the airplane.
     */
    private String id;

    /**
     * Next airplane.
     */
    Airplane nextAirplane;

    /**
     * Previous airplane.
     */
    Airplane previousAirplane;

    /**
     * Airplane's first seat.
     */
    Seat firstSeat;

    /**
     * Airplane's stops.
     */
    ArrayList<String> stops;

    /**
     * Amount of money collected from airplane ticket sales.
     */
    private double amountCollected;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Airplane constructor.<br>
     * <b>post:</b> A airplane was created with the id given by the parameter. <br>
     * The amount of money collected was initialized to 0. <br>
     * The list of airplaneStops was created was an empty vector. <br>
     * The first seat, the previous airplane, and the next airplane are null.
     *
     * @param pId Number that identifies the airplane.
     */
    public Airplane(String pId) {
        id = pId;
        amountCollected = 0;
        firstSeat = null;
        previousAirplane = null;
        nextAirplane = null;
        stops = new ArrayList<>();
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    public boolean existenVagonesMismaCantidadDisponibles() {

        return false;
    }


    public Seat reverseSeats() {
        return null;
    }

    /**
     * Returns the next airplane.
     *
     * @return The next airplane.
     */
    public Airplane getNextAirplane() {
        return nextAirplane;
    }

    /**
     * Returns the previous airplane.
     *
     * @return The previous airplane.
     */
    public Airplane getPreviousAirplane() {
        return previousAirplane;
    }

    /**
     * Returns the first seat on the airplane.
     *
     * @return First seat on the airplane.
     */
    public Seat getFirstSeat() {
        return firstSeat;
    }

    /**
     * Returns the airplane's id string.
     *
     * @return Airplane's id string.
     */
    public String getId() {
        return id;
    }


    /**
     * Returns the amount of money collected from airplane ticket sales. <br>
     *
     * @return Amount of money collected.
     */
    public double getAmountCollected() {
        return amountCollected;
    }


    /**
     * Returns the numbers on the seats.
     *
     * @return A list with the numbers on the seats.
     */
    public ArrayList<Integer> getSeatNumbers() {
        return null;
    }

    /**
     * Returns the amount of seats available on the airplane.
     *
     * @return Amount of seats available.
     */
    public int getTotalAmountOfAvailableSeats() {
        return 0;
    }

    /**
     * Finds the seat with the number given by the parameter. <br>
     *
     * @param pSeatNumber Number of the seat to be found. pSeatNumber > 0.
     * @return Found seat, null in case no seat exists with that number.
     */
    public Seat findSeatByNumber(int pSeatNumber) {
        return null;
    }


    /**
     * Changes the next airplane to the one given by the parameter. <br>
     * <b>post:</b> The next airplane was changed to the one given by the parameter. <br>
     *
     * @param pNewNextAirplane The next airplane.
     */
    public void changeNextAirplane(Airplane pNewNextAirplane) {
        nextAirplane = pNewNextAirplane;
        verifyInvariant();
    }

    /**
     * Changes the previous airplane to the one given by the parameter. <br>
     * <b>post:</b> The previous airplane was changed to the one given by the parameter. <br>
     *
     * @param pNewPreviousAirplane The previous airplane.
     */
    public void changePreviousAirplane(Airplane pNewPreviousAirplane) {
        previousAirplane = pNewPreviousAirplane;
        verifyInvariant();
    }

    /**
     * Adds a new seat to the airplane with the values given by the parameters. <br>
     * <b>post:</b> The seat is added to the end of the list. <br>
     *
     * @param pSeatNumber    Number of the seat to be added. pSeatNumber > 0.
     * @param pClass         Seat class. pClass belongs a CLASSES.
     */
    public void addSeat(int pSeatNumber, String pClass) {


    }

    /**
     * Eliminates the seat with the number given by the parameter. <br>
     * <b>post:</b> The seat was eliminated from the list. <br>
     *
     * @param pSeatNumber Number of the seat to eliminate. pSeatNumber > 0.
     */
    public void eliminateSeat(int pSeatNumber) {


    }

    public void addStop(String pCity) {
        stops.add(pCity);
    }

    /**
     * Sells a ticket from the seat number given by the parameter. <br>
     * <b>post:</b> The amount of money collected was incremented by the price of the ticket. <br>
     *
     * @param pSeatNumber Number of the seat for which the ticket will be sold.
     *                    pSeatNumber > 0.
     * @return True if the ticket was sold, false if otherwise.
     */
    public boolean sellTicket(int pSeatNumber) {
        return false;
    }


    /**
     * Prints the airplane's data to a file. <br>
     *
     * @param pWriter Object that writes the report to a file.
     *                pWriter != null.
     */
    public void printAirplaneDetails(PrintWriter pWriter) {

    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------

    /**
     * Verifies the invariants of the Airplane class.  <br>
     * <b>inv:</b><br>
     * No two seats have the same name. <br>
     * The list of stops is initialized.<br>
     */
    private void verifyInvariant() {
        assert !existsRepeatedSeat() : "There is more than one seat with the same number.";
    }

    /**
     * Verifies if there are two seats with the same number.
     *
     * @return True if there are two seats with the same number, false if otherwise.
     */
    private boolean existsRepeatedSeat() {
        return false;
    }


}