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
package uniandes.cupi2.hurricanes.test;

import uniandes.cupi2.cupihurricanes.world.Hurricane;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class used to verify the methods used in the Hurricane class.
 */
public class HurricaneTest {
    // --------------------------------------------------------
    // Attributes
    // --------------------------------------------------------

    /**
     * Hurricane used for the test cases.
     */
    private Hurricane hurricane1;

    /**
     * Hurricane used for the test cases.
     */
    private Hurricane hurricane2;

    /**
     * Hurricane used for the test cases.
     */
    private Hurricane hurricane3;

    /**
     * Hurricane used for the test cases.
     */
    private Hurricane hurricane4;

    // --------------------------------------------------------
    // Methods
    // --------------------------------------------------------

    /**
     * Scenario 1: Constructs hurricane1 y hurricane2.
     */
    @Before
    public void setupScenario1() {
        hurricane1 = new Hurricane("Abel", 1, 100, 1.1, "image1");
        hurricane2 = new Hurricane("Zena", 4, 100, 2.3, "image2");
        hurricane3 = new Hurricane("Charlie", 1, 300, 1.1, "image3");
        hurricane4 = new Hurricane("Brian", 3, 200, 3.5, "image4");
    }

    /**
     * Test 1: Verifies the constructor method. <br>
     * <b> Methods to test: </b> <br>
     * Hurricane (constructor) <br>
     * getName <br>
     * getCategory <br>
     * getVelocity <br>
     * getEstimatedDamageCost <br>
     * getImage<br>
     * <b> Objective: </b> Test that the constructor creates a new hurricane properly. <br>
     * <b> Expected results: </b> <br>
     * 1. Upon creating a hurrciane, the attributes of the object must have the correct value.
     */
    @Test
    public void testHurricane() {

        assertEquals("The hurricane's name is incorrect.", "Abel", hurricane1.getName());
        assertEquals("The hurricane's category is incorrect.", 1, hurricane1.getCategory());
        assertEquals("The hurricane's velocity is incorrect.", 100, hurricane1.getVelocity());
        assertEquals("The estimated cost of damage is incorrect.", 1.1,
                     hurricane1.getEstimatedDamageCost(), 0.1);
        assertEquals("The image of the hurricane is incorrect.", "image1", hurricane1.getImage());

        assertEquals("The hurricane's name is incorrect.", "Brian", hurricane4.getName());
        assertEquals("The hurricane's category is incorrect", 3, hurricane4.getCategory());
        assertEquals("The hurricane's velocity is incorrect.", 200, hurricane4.getVelocity());
        assertEquals("The estimated cost of damage is incorrect.", 3.5,
                     hurricane4.getEstimatedDamageCost(), 0.1);
        assertEquals("The image of the hurricane is incorrect.", "image4", hurricane4.getImage());

    }


    /**
     * Test 2: Verifies the method compareByName(). <br>
     * <b> Methods to test: </b> <br>
     * compareByName. <br>
     * <b> Objective: </b> Tests that the method compareByName() performs the comparison of the
     * two hurricanes correctly. <br>
     * <b> Expected results: </b> <br>
     * 1. When comparing a hurricane who's name is lower than the other, the result must be -1.<br>
     * 2. When comparing a hurricane who's name is equal to the other, the result must be 0. <br>
     * 3. When comparing a hurricane who's name is greater than the other, the result must be 1.
     */
    @Test
    public void testCompareByName() {
        Hurricane hurricane5 = new Hurricane("Zena", 1, 3, 5.2, "image5");
        assertEquals("Hurricane 1 should be less than hurricane 2 lexicographically.", -1,
                     hurricane1.compareByName(hurricane2));
        assertEquals("Hurricane 2 should be equal to hurricane 5 lexicographically.", 0,
                     hurricane2.compareByName(hurricane5));
        assertEquals("Hurricane 2 should be greater than hurricane 4 lexicographically.", 1,
                     hurricane2.compareByName(hurricane4));
    }

    /**
     * Test 3: Verifies the method compareByDamage(). <br>
     * <b> Methods to test: </b> <br>
     * compareByDamage. <br>
     * <b> Objective: </b> Tests that the method compareByDamage() performs the comparison of the
     * two hurricanes correctly. <br>
     * <b> Expected results: </b> <br>
     * 1. When comparing a hurricane who's estimated damage cost is lower than the other, the
     * result must be -1. <br>
     * 2. When comparing a hurricane who's estimated damage cost is equal to the other, the
     * result must be 0. <br>
     * 3.When comparing a hurricane who's estimated damage cost is greater than the other, the
     * result must be 1.
     */
    @Test
    public void testCompareByDamage() {
        assertEquals("Hurricane 1's damage should be less than hurricane 2's damage.", -1,
                     hurricane1.compareByDamage(hurricane2));
        assertEquals("Hurricane 1 should be equal to hurricane 3's damage.", 0,
                     hurricane1.compareByDamage(hurricane3));
        assertEquals("Hurricane 4's damage should be greater than hurricane 1's damage.", 1,
                     hurricane4.compareByDamage(hurricane1));

    }

    /**
     * Test 4: Verifies the method compareByVelocity().  <br>
     * <b> Methods to test: </b> <br>
     * compareByVelocity. <br>
     * <b> Objective: </b> Tests that the method compareByDamage() performs the comparison of the
     * two hurricanes correctly. <br>
     * <b> Expected results: </b> <br>
     * 1. When comparing a hurricane who's velocity is lower than the other, the result must be -1.
     * <br>
     * 2. When comparing a hurricane who's velocity is equal to the other, the result must be -1.
     * <br>
     * 3. When comparing a hurricane who's velocity is greater than the other, the result must be 1.
     */
    @Test
    public void testCompareByVelocity() {
        assertEquals("Hurricane 1's velocity should be less than hurricane 3's velocity.", -1,
                     hurricane1.compareByVelocity(hurricane3));
        assertEquals("Hurricane 1's velocity should be equal to hurricane 3's velocity.", 0,
                     hurricane1.compareByVelocity(hurricane2));
        assertEquals("Hurricane 3's damage should be greater than hurricane 4's damage.", 1,
                     hurricane3.compareByVelocity(hurricane4));

    }

    /**
     * Test 5: Verifies the method toString(). <br>
     * <b> Methods to test: </b> <br>
     * toString <br>
     * <b> Objective: </b> Tests that the method toString() works properly. </b> <br>
     * 1. The method toString returns the correct string of characters.
     */
    @Test
    public void testToString() {
        assertEquals("The string of characters is not correct.", "Abel",
                     hurricane1.toString());
        assertEquals("The string of characters is not correct.", "Zena",
                     hurricane2.toString());
        assertEquals("The string of characters is not correct.", "Charlie",
                     hurricane3.toString());
        assertEquals("The string of characters is not correct.", "Brian",
                     hurricane4.toString());
    }
}
