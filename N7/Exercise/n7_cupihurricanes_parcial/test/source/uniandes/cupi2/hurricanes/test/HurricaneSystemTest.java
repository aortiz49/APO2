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

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import uniandes.cupi2.cupihurricanes.world.Hurricane;
import uniandes.cupi2.cupihurricanes.world.HurricaneSystem;

/**
 * Class used to verify the methods used in the HurricaneSystem class.
 */
public class HurricaneSystemTest {
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Hurricane system test.
     */
    private HurricaneSystem hurricaneSystem;


    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Scenario 1: Constructs a new system of hurricanes from a text file.  <b>
     * The hurricanes are created from real data.
     */
    @Before
    public void setupScenario1() {
        hurricaneSystem = new HurricaneSystem();
        loadHurricanes(
                "/Users/renegade/Uniandes/Sem 2/APO2/N7/Exercise/n7_cupihurricanes/data/hurricanes"
                        + ".txt");
    }

    /**
     * Scenario 2: Constructs a new empty hurricane system.
     */
    private void setupScenario2() {
        hurricaneSystem = new HurricaneSystem();
    }

    /**
     * Test 1: Verifies the method registerHurricane. <br>
     * <b> Methods to test: </b> <br>
     * registerHurricane() <br>
     * findHurricane() <br>
     * getHurricanes() <br>
     * <b> Objective: </b> Tests that the method is able to register a hurricane properly. <br>
     * <b> Expected results: </b> <br>
     * 1. When searching (by name) for a hurricane previously added, it should return an index
     * different from -1 (it should be found) and the hurricane data at that position should
     * correspond to the hurricane with the correspinding name. <br>
     */
    @Test
    public void testRegisterHurricane1() {
        // Configures the data for the test
        setupScenario2();

        String name;
        int category;
        int velocity;
        double damageCost;
        String image;
        boolean added;

        // Adds a hurricane and then verifies that it was added properly.
        for (int i = 1; i <= 10; i++) {
            name = "name" + i;
            category = 1 + i % 5;
            velocity = i;
            damageCost = i;
            image = "image" + i;

            added = hurricaneSystem.registerHurricane(name, category, velocity, damageCost, image);
            int pos = hurricaneSystem.findHurricane(name);
            Hurricane hurricane = (Hurricane) hurricaneSystem.getHurricanes().get(pos);

            assertTrue("The hurricane wasn't added properly.", added);
            assertEquals("The hurricane wasn't added properly.", i - 1, pos);
            assertEquals("The hurricane wasn't added with the correct name.", name,
                         hurricane.getName());
            assertEquals("The hurricane wasn't added with the correct category.", category,
                         hurricane.getCategory());
            assertEquals("The hurricane wasn't added with the correct velocity.", velocity,
                         hurricane.getVelocity());
            assertEquals("The hurricane wasn't added with the correct cost.", damageCost,
                         hurricane.getEstimatedDamageCost(), 0.1);
            assertEquals("The hurricane wasn't added with the correct image.", image,
                         hurricane.getImage());
        }
    }

    /**
     * Test 2: Verifies the method registerHurricane(), adding a hurricane with a repeated name.
     * <br>
     * <b> Methods to test: </b> <br>
     * registerHurricane() <br>
     * findHurricane() <br>
     * getHurricanes() <br>
     * <b> Objective: </b> Tests that the method registerHurricane() doesn't added a hurricane
     * to the system when a hurricane with that name is already registered. <br>
     * <b> Expected results: </b> <br>
     * 1. When registering a hurricane with an existing name, it shouldn't add it to the list of
     * hurricanes.
     */
    @Test
    public void testRegisterHurricane2() {
        int size = hurricaneSystem.getHurricanes().size();

        boolean added = hurricaneSystem.registerHurricane("Bertha", 5, 97, 2, "image_bertha.png");
        assertFalse("The hurricane shouldn't have been added.", added);
        assertEquals("The size of the list shouldn't have been incremented.", size,
                     hurricaneSystem.getHurricanes().size());

        added = hurricaneSystem.registerHurricane("Fay", 3, 100, 1, "image_fay.png");
        assertFalse("The hurricane shouldn't have been added.", added);
        assertEquals("The size of the list shouldn't have been incremented.", size,
                     hurricaneSystem.getHurricanes().size());

        added = hurricaneSystem.registerHurricane("Arthur", 1, 450, 20, "image_arthur.png");
        assertFalse("The hurricane shouldn't have been added.", added);
        assertEquals("The size of the list shouldn't have been incremented.", size,
                     hurricaneSystem.getHurricanes().size());
    }

    /**
     * Test 3: Verifies the method findHurricane().  <br>
     * <b> Methods to test: </b> <br>
     * findHurricane() <br>
     * <b> Objective: </b> Tests that the method findHurricane() is capable of finding
     * hurricanes registered in the system. <br>
     * <b> Expected results: </b> <br>
     * 1. When searching for a previously added hurricane, it should obtain the index where it is
     * found in the list.  <br>
     * 2. When searching for a hurricane that doesn't exist, it should return an index of -1.
     */
    @Test
    public void testFindHurricane() {
        assertEquals("The hurricane should be in index 7.", 7,
                     hurricaneSystem.findHurricane("Iselle"));
        assertEquals("The hurricane should be in index  0.", 0,
                     hurricaneSystem.findHurricane("Arthur"));
        assertEquals("Hurricane shouldn't have been found.", -1,
                     hurricaneSystem.findHurricane("Antonio"));
    }

    /**
     * Test 4: Verifies the method findBinaryByName().  <br>
     * <b> Methods to test: </b> <br>
     * findBinaryByName() <br>
     * orderByName() <br>
     * <b> Objective: </b> Tests that the method findBinaryByName() is capable of finding
     * hurricanes registered in the system. <br>
     * <b> Expected results: </b> <br>
     * 1. When searching for a previously added hurricane, it should obtain the index where it is
     * found in the list.  <br>
     * 2. When searching for a hurricane that doesn't exist, it should return an index of -1.
     */
    @Test
    public void testFindByName() {
        hurricaneSystem.orderByName();
        assertEquals("The hurricane should be in index 6.", 6,
                     hurricaneSystem.findBinaryByName("Iselle"));
        assertEquals("The hurricane should be in index  0.", 0,
                     hurricaneSystem.findBinaryByName("Arthur"));
        assertEquals("Hurricane shouldn't have been found.", -1,
                     hurricaneSystem.findBinaryByName("Antonio"));
    }

    /**
     * Test 5: Verifies the method orderByName()<br>
     * Methods to test: </b> <br>
     * orderByName() <br>
     * <b> Objective: </b> Tests that the method orderByName() orders the system properly.
     * (Ascending order by name). <br>
     * <b> Expected results: </b> <br>
     * 1. When ordering the system by name, the hurricanes should be in alphabetical order. <br>
     */
    @Test
    public void testOrderByName() {

        hurricaneSystem.orderByName();
        ArrayList hurricanes = hurricaneSystem.getHurricanes();
        for (int i = 1; i < hurricanes.size(); i++) {
            Hurricane h0 = (Hurricane) hurricanes.get(i - 1);
            Hurricane h1 = (Hurricane) hurricanes.get(i);

            assertTrue("Wasn't ordered correctly by name",
                       h0.getName().compareTo(h1.getName()) <= 0);
        }
    }

    /**
     * Test 6: Verifies the method orderByVelocity(). <br>
     * Method to test:
     * </b> <br>
     * orderByVelocity() <br>
     * <b> Objective: </b> Tests that the method orderByVelocity() orders the system correctly
     * . (Descending order by velocity). <br>
     * <b> Expected results: </b> <br>
     * 1. When ordering the system by velocity, the hurricane with the lowest velocity should be
     * first and the one with the greatest velocity last.
     */
    @Test
    public void testOrderByVelocity() {
        hurricaneSystem.orderByVelocity();
        ArrayList hurricanes = hurricaneSystem.getHurricanes();
        for (int i = 1; i < hurricanes.size(); i++) {
            Hurricane h0 = (Hurricane) hurricanes.get(i - 1);
            Hurricane h1 = (Hurricane) hurricanes.get(i);

            assertTrue("Wasn't ordered correctly by velocity.",
                       h0.getVelocity() >= h1.getVelocity());
        }
    }

    /**
     * Test 7: Verifies the method orderByDamage(). <br>
     * Methods to test: </b> <br>
     * orderByDamage(). <br>
     * <b> Objective: </b> Tests that the method orderByDamage() orders the system properly.
     * (Ascending order by cost in damages). <br>
     * <b> Expected results: </b> <br>
     * 1. When ordering the system by cost, the hurricane with the lowest cost should be
     * first and the one with the greatest cost last.
     */
    @Test
    public void testOrderByDamage() {
        hurricaneSystem.orderByDamage();
        ArrayList hurricanes = hurricaneSystem.getHurricanes();
        for (int i = 1; i < hurricanes.size(); i++) {
            Hurricane h0 = (Hurricane) hurricanes.get(i - 1);
            Hurricane h1 = (Hurricane) hurricanes.get(i);

            assertTrue("Wasn't ordered correctly by damages",
                       h0.getEstimatedDamageCost() <= h1.getEstimatedDamageCost());
        }
    }

    /**
     * Test 8: Verifies the method findHurricaneHighestDamage(). <br>
     * <b> Methods to test: </b> <br>
     * findHurricaneHighestDamage() <br>
     * <b> Objective: </b> Tests that the method findHurricaneHighestDamage() finds the
     * correct hurricane(The one that has the highest cost in damages). <br>
     * <b> Expected results: </b> <br>
     * 1. When searching for the hurricane with the highest cost in damages, it must find the
     * index of the one with the highest cost in damages in the system. <br>
     * 2. When searching for the hurricane with the highest cost in damages in an empty or null
     * list, the index should be -1.
     */
    @Test
    public void testFindHurricaneHighestDamage() {

        assertEquals("This hurricane doesn't have the highest damage.", 5,
                     hurricaneSystem.findHurricaneHighestDamage());
    }

    /**
     * Test 9: Verifies the method findHurricaneHighestDamage(). <br>
     * <b> Methods to test: </b> <br>
     * findHurricaneLowestDamage() <br>
     * <b> Objective: </b> Tests that the method findHurricaneHighestDamage() finds the
     * correct hurricane(The one that has the lowest cost in damages). <br>
     * <b> Expected results: </b> <br>
     * 1. When searching for the hurricane with the lowest cost in damages, it must find the
     * index of the one with the lowest cost in damages in the system. <br>
     * 2. When searching for the hurricane with the lowest cost in damages in an empty or null
     * list, the index should be -1.
     */
    @Test
    public void testFindHurricaneLowestDamage() {

        assertEquals("This hurricane doesn't have the lowest damage.", 3,
                     hurricaneSystem.findHurricaneLowestDamage());
    }

    /**
     * Test 10: Verifies the method findHighestVelocity().  <br>
     * <b> Methods to test: </b> <br>
     * findHurricaneHighestVelocity(). <br>
     * <b> Objective: </b> Tests that the method ighestVelocity() returns the correct
     * hurricane (the one with the highest velocity). <br>
     * <b> Expected results: </b> <br>
     * 1. When searching for the hurricane with the highest velocity, it should return the index
     * of the hurricane with he highest velocity in the system.  <br>
     * 2. When searching for the hurricane with the highest velocity in an empty or null
     *      * list, the index should be -1.
     */
    @Test
    public void testFindHurricaneHighestVelocity() {

        assertEquals("This hurricane doesn't have the highest velocity.", 3,
                     hurricaneSystem.findHurricaneHighestVelocity());
    }

    // -----------------------------------------------------------------
    // Auxiliary Methods
    // -----------------------------------------------------------------

    /**
     * Loads the hurricanes from the system using a text file of properties.
     *
     * @param pFile Name of the file of properties containing the hurricane information.
     */
    private void loadHurricanes(String pFile) {
        try {
            FileInputStream fis = new FileInputStream(new File(pFile));
            Properties properties = new Properties();
            properties.load(fis);

            // Load the hurricanes
            String data;
            String name;
            int category;
            int velocity;
            double damageCost;
            String image;
            String aux;
            data = "total.hurricanes";
            aux = properties.getProperty(data);
            int amount = Integer.parseInt(aux);

            for (int cont = 1; cont <= amount; cont++) {
                // Loads a hurricane
                data = "hurricane" + cont + ".name";
                name = properties.getProperty(data);

                data = "hurricane" + cont + ".category";
                category = Integer.parseInt(properties.getProperty(data));

                data = "hurricane" + cont + ".velocity";
                velocity = Integer.parseInt(properties.getProperty(data));

                data = "hurricane" + cont + ".damageCost";
                damageCost = Double.parseDouble(properties.getProperty(data));

                data = "hurricane" + cont + ".image";
                image = properties.getProperty(data);

                hurricaneSystem.registerHurricane(name, category, velocity, damageCost, image);
                fis.close();
            }
        } catch (Exception e) {
            fail("Couldn't load hurricanes file:" + e.getMessage());
        }
    }

}