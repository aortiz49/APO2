/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n8_Trains
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTrains.world;

import java.io.Serializable;

/**
 * Class that represents the wagon of a train. <br>
 * <b>inv:</b><br>
 * The amount of seats available is greater than or equal to zero. <br>
 * The class of the wagon belongs to one of the values in the class constants array. <br>
 * The price of the ticket is greater than or equal to zero. <br>
 * The number of the wagon is greater than zero. <br>
 */
public class Wagon implements Serializable {

    // -------------------------------------------------------------
    // Constants
    // -------------------------------------------------------------

    /**
     * Serialization constant of the Wagon class.
     */
    private static final long serialVersionUID = 9015954459189238070L;

    /**
     * Array of constants containing the possible classes for a wagon.
     */
    public final static String[] CLASSES = {"First class", "Second class", "Business", "VIP"};

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Number of the wagon.
     */
    private int number;

    /**
     * Total amount of seats on the wagon.
     */
    private int totalAmountOfSeats;

    /**
     * Amount of available seats on the wagon.
     */
    private int amountOfAvailableSeats;

    /**
     * Class of the wagon.
     */
    private String wagonClass;

    /**
     * Price of the ticket.
     */
    private double price;

    /**
     * Next wagon.
     */
    private Wagon nextWagon;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Wagon constructor. <br>
     * <b>post: </b> Constructs a new wagon with the information given by the parameter.
     *
     * @param pNumber        Number of the wagon. pNumber > 0.
     * @param pAmountOfSeats Amount of seats on the wagon.
     *                       pAmountOfAvailableSeats >= 0.
     * @param pClass         Class of the wagon. pClass belongs to CLASSES.
     * @param pPrice         Price of one ticket. pPrice >= 0.
     */
    public Wagon(int pNumber, int pAmountOfSeats, String pClass, double pPrice) {
        number = pNumber;
        totalAmountOfSeats = pAmountOfSeats;
        amountOfAvailableSeats = pAmountOfSeats;
        wagonClass = pClass;
        price = pPrice;
        nextWagon = null;
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Returns the number of the wagon.
     *
     * @return Number of the wagon.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the total amount of seats on the wagon.
     *
     * @return Total amount of seats.
     */
    public int getTotalAmountOfSeats() {
        return totalAmountOfSeats;
    }

    /**
     * Returns the amount of available seats in the wagon.
     *
     * @return Amount of available seats.
     */
    public int getTotalAmountOfAvailableSeats() {
        return amountOfAvailableSeats;
    }

    /**
     * Returns the class of the wagon.
     *
     * @return Wagon's class.
     */
    public String getWagonClass() {
        return wagonClass;
    }

    /**
     * Returns the price of the ticket.
     *
     * @return Price of the ticket.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the next wagon.
     *
     * @return Next wagon.
     */
    public Wagon getNextWagon() {
        return nextWagon;
    }

    /**
     * Changes the next wagon for the one given by the parameter. <br>
     * <b>post:</b> The next wagon was changed for the wagon given by the parameter. <br>
     *
     * @param pNextWagon Wagon to be assigned as the next wagon.
     *                   pNextWagon != null.
     */
    public void changeNextWagon(Wagon pNextWagon) {
        nextWagon = pNextWagon;
    }

    /**
     * Sells a ticket on the wagon if there are seats available. <br>
     * <b>post:</b> The amount of available seats on the wagon is decreased by 1. <br>
     *
     * @return True if he ticket was sold, false if otherwise.
     */
    public boolean sellTicket() {
        boolean sold = false;
        if (amountOfAvailableSeats > 0) {
            amountOfAvailableSeats--;
            sold = true;
        }

        return sold;
    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------

    /**
     * Method that verifies the invariants of the wagon. <br>
     * <b>inv:</b><br>
     * The amount of seats available is greater than or equal to zero. <br>
     * The class of the wagon belongs to one of the values in the class constants array. <br>
     * The price of the ticket is greater than or equal to zero. <br>
     * The number of the wagon is greater than zero. <br>
     */
    private void verifyInvariant() {
        assert amountOfAvailableSeats >= 0 : "The number of seats cannot be zero.";
        assert wagonClass != null && !wagonClass.equals("") && wagonClassBelongsToConstant() :
                "The wagon class of this wagon is invalid.";
        assert price >= 0 : "The price cannot be negative.";
        assert number > 0 : "The wagon number cannot be negative.";
    }

    /**
     * Returns true if the class of the wagon belongs to one of the constants in CLASSES.
     * False if otherwise.
     *
     * @return True if the wagon class belongs to one of the constants in CLASSES.
     *      * False if otherwise.
     */
    private boolean wagonClassBelongsToConstant() {
        boolean belongs = false;
        for (int i = 0; i < CLASSES.length && !belongs; i++) {
            if (CLASSES[i].equals(wagonClass)) {
                belongs = true;
            }
        }
        return belongs;
    }
}