/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n9_Trains
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package source.uniandes.cupi2.cupiTrenes.test;


import org.junit.Before;
import org.junit.Test;
import uniandes.cupi2.cupiTrains.world.*;

import static org.junit.Assert.*;

/**
 * Class used to verify the correct implementation of Wagon.  u
 */
public class WagonTest {

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     */
    private Wagon wagon;

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Scenario 1: Creates a new wagon.
     */
    @Before
    public void setupScenario1() {
        // Wagon in first class.
        wagon = new Wagon(35, 5, Wagon.CLASSES[0], 100);

    }

    /**
     * Test 1: Verifies the constructor method of Wagon. <br>
     * <b> Methods to test: </b> <br>
     * Wagon <br>
     * getNumber <br>
     * getTotalAmountOfAvailableSeats <br>
     * getWagonClass <br>
     * getPrice <br>
     * getNextWagon <br>
     * <b> Test cases: </b> <br>
     * 1. Constructs a wagon from scenario 1 and a new wagon with each of its
     * expected values.
     */
    @Test
    public void testWagon() {
        assertEquals("Wagon number not initialized correctly.", 35, wagon.getNumber());
        assertEquals("Number of available seats not initialized correctly.", 5,
                     wagon.getTotalAmountOfAvailableSeats());
        assertEquals("Wagon class not initialized correctly.", Wagon.CLASSES[0],
                     wagon.getWagonClass());
        assertEquals("Wagon price not initialized correctly.", 100.0, wagon.getPrice(), 0.0);
        assertNull("There shouldn't exists a next wagon.", wagon.getNextWagon());

        // Wagon in VIP class.
        Wagon newWagonWagon = new Wagon(36, 2, Wagon.CLASSES[3], 200);
        assertEquals("Wagon number not initialized correctly.", 36, newWagonWagon.getNumber());
        assertEquals("Number of available seats not initialized correctly.", 2,
                     newWagonWagon.getTotalAmountOfAvailableSeats());
        assertEquals("Wagon class not initialized correctly.", Wagon.CLASSES[3],
                     newWagonWagon.getWagonClass());
        assertEquals("Wagon price not initialized correctly.", 200.0, newWagonWagon.getPrice(),
                     0.0);
        assertNull("There shouldn't exists a next wagon.", wagon.getNextWagon());
    }

    /**
     * Test 2: Verifies the method changeNextWagon. <br>
     * <b> Methods to test: </b> <br>
     * Wagon <br>
     * changeNextWagon <br>
     * getNextWagon <br>
     * getNumber <br>
     * <b> Test cases: </b> <br>
     * 1. Correctly changes the next wagon.
     */
    @Test
    public void testChangeNextWagon() {
        // New wagon in second class. 
        Wagon newWagon = new Wagon(4, 20, Wagon.CLASSES[1], 50);
        wagon.changeNextWagon(newWagon);
        assertNotNull("It should have a next wagon.", wagon.getNextWagon());
        assertEquals("Didn't properly associate the next wagon.", 4,
                     wagon.getNextWagon().getNumber());
    }

    /**
     * Test 3: Verifies the method sellTicket. <br>
     * <b> Methods to test: </b> <br>
     * sellTicket <br>
     * getTotalAmountOfAvailableSeats <br>
     * <b> Test cases: </b> <br>
     * 1. Correctly sells all available tickets for the wagon. <br>
     * 2. There are no avialable seats and tickets are not sold.
     */
    @Test
    public void testSellTicket() {
        // Test case 1
        assertTrue("Ticket should have been sold.", wagon.sellTicket());
        assertEquals(4, wagon.getTotalAmountOfAvailableSeats());
        assertTrue("Ticket should have been sold.", wagon.sellTicket());
        assertEquals(3, wagon.getTotalAmountOfAvailableSeats());
        assertTrue("Ticket should have been sold.", wagon.sellTicket());
        assertEquals(2, wagon.getTotalAmountOfAvailableSeats());
        assertTrue("Ticket should have been sold.", wagon.sellTicket());
        assertEquals(1, wagon.getTotalAmountOfAvailableSeats());
        assertTrue("Ticket should have been sold.", wagon.sellTicket());
        assertEquals(0, wagon.getTotalAmountOfAvailableSeats());

        // Test case 2
        assertFalse("Ticket shouldn't have been sold.", wagon.sellTicket());
        assertEquals("There should be 0 seats available.", 0,
                     wagon.getTotalAmountOfAvailableSeats());
    }
}