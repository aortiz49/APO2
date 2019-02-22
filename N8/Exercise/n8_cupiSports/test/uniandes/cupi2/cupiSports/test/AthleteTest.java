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

package uniandes.cupi2.cupiSports.test;


import org.junit.Before;
import org.junit.Test;
import uniandes.cupi2.cupiSports.world.Athlete;


import static org.junit.Assert.assertEquals;


/**
 * Class used to verifie that the methods in the Athlete class are correctly implemented.
 */
public class AthleteTest {
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Class in which the tests will be performed.
     */
    private Athlete athlete;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Constructs a new athlete.
     */
    @Before
    public void setupScenario1() {

        athlete = new Athlete("James", 23, "Madrid", 39, "image");

    }

    /**
     * Test 1: Verifies the constructor method.<br>
     * <b> Methods to test: </b> <br>
     * constructor <br>
     * getName() <br>
     * getPlaceOfResidency() <br>
     * getAmountOfTrophies() <br>
     * getImagePath() <br>
     * getAge() <br>
     * <b> Objective: </b> Test the correct initialization of an athlete. <br>
     * <b> Expected results: </b> <br>
     * 1. Athlete correctly initialized.<br>
     */
    @Test
    public void testConstructor() {
        assertEquals("Athlete's name is incorrect.", "James", athlete.getName());
        assertEquals("Athlete's age is incorrect.", 23, athlete.getAge());
        assertEquals("Athlete's place of residence is incorrect.", "Madrid",
                     athlete.getPlaceOfResidency());
        assertEquals("Athlete's image path is incorrect", "image", athlete.getImagePath());
        assertEquals("Athlete's trophy count is incorrect.", 39, athlete.getAmountOfTrophies());
    }

    /**
     * Test 2: Verifies the modifyAge() method.<br>
     * <b> Methods to test: </b> <br>
     * modifyAge() <br>
     * getAge() <b><br>
     * Objective: </b> Test the implementation of modifyAge()<br>
     * <b> Expected results: </b> <br>
     * 1. The age fue cambiada correctamente.<br>
     */
    @Test

    public void testModifyAge() {
        athlete.modifyAge(30);
        assertEquals("La age of the athlete es incorrecta.", 30, athlete.getAge());
    }

    /**
     * Test 3: Verifica el m�todo modifyPlaceOfResidency.<br>
     * <b> Methods to test: </b> <br>
     * modifyPlaceOfResidency<br>
     * getPlaceOfResidency<br>
     * <b> Objective: </b> Probar funcionamiento correcta del m�todo modifyPlaceOfResidency<br>
     * <b> Expected results: </b> <br>
     * 1. El lugar de residencia fue cambiado correctamente.<br>
     */
    @Test
    public void testModifyPlaceOfResidency() {
        athlete.modifyPlaceOfResidency("Bogot�");
        assertEquals("El lugar de residencia of the athlete es incorrecto.", "Bogot�",
                     athlete.getPlaceOfResidency());

    }

    /**
     * Test 4: Verifica el m�todo modifyAmountOfTrophies.<br>
     * <b> Methods to test: </b> <br>
     * modifyAmountOfTrophies<br>
     * getAmountOfTrophies <br>
     * <b> Objective: </b> Probar funcionamiento correcta del m�todo modifyAmountOfTrophies<br>
     * <b> Expected results: </b> <br>
     * 1. La cantidad de trofeos fue cambiada correctamente.<br>
     */
    @Test
    public void testModifyAmountOfTrophies() {
        athlete.modifyAmountOfTrophies(10);
        assertEquals("La cantidad detrofeos of the athlete es incorrecta.", 10,
                     athlete.getAmountOfTrophies());

    }

    /**
     * Test 5: Verifica el m�todo modifyImagePath.<br>
     * <b> Methods to test: </b> <br>
     * modifyImagePath<br>
     * getImagePath<br>
     * <b> Objective: </b> Probar funcionamiento correcta del m�todo modifyImagePath<br>
     * <b> Expected results: </b> <br>
     * 1. La ruta de la imagen fue cambiada correctamente.<br>
     */
    @Test
    public void testModifyImagePath() {
        athlete.modifyImagePath("imagen2");
        assertEquals("La ruta de la imagen of the athlete es incorrecta.", "imagen2",
                     athlete.getImagePath());

    }

}