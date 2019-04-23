/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n11_taxonomicTree
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiTaxonomy.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import uniandes.cupi2.cupiTaxonomy.world.*;

/**
 * Class used to verify the implementation of the living being class.
 */
public class LivingBeingTest {
    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Class on which the tests will be performed.
     */
    private LivingBeing livingBeing;

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Scenario 1: Crea un living being.
     */
    @Before
    public void setupScenario1() {
        livingBeing = new LivingBeing("Name Común", "Name Científico", "Características", "Image");
    }

    /**
     * Prueba 1: Se encarga of verificar el método constructor of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * LivingBeing <br>
     * getCommonName <br>
     * getScientificName <br>
     * getCharacteristics <br>
     * getImagePath <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Crea correctamente un living being con los datos dados por parámetro.
     */
    @Test
    public void testLivingBeing() {
        setupScenario1();

        assertNotNull("El name común of the living being no fue inicializado.",
                      livingBeing.getCommonName());
        assertEquals("El name común of the living being no fue inicializado correctamente.",
                     "Name Común", livingBeing.getCommonName());
        assertNotNull("El name científico of the living being no fue inicializado.",
                      livingBeing.getScientificName());
        assertEquals("El name científico of the living being no fue inicializado correctamente.",
                     "Name Científico", livingBeing.getScientificName());
        assertNotNull("Las características of the living being no fueron inicializadas.",
                      livingBeing.getCharacteristics());
        assertEquals(
                "Las características of the living being no fueron inicializadas correctamente.",
                "Características", livingBeing.getCharacteristics());
        assertNotNull("La ruta of la image of the living being no fue inicializada.",
                      livingBeing.getImagePath());
        assertEquals("La ruta of la image of the living being no fue inicializada correctamente",
                     "Image", livingBeing.getImagePath());
    }

    /**
     * Prueba 2: Se encarga of verificar el método toString of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * toString <br>
     * getCommonName <br>
     * getScientificName <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Returns correctamente el valor of la clase.
     */
    @Test
    public void testToString() {
        setupScenario1();

        String toString = livingBeing.toString();
        String esperado = livingBeing.getScientificName() + " - " + livingBeing.getCommonName();
        assertNotNull("La representación en String of the living being no debería ser nula.",
                      toString());
        assertEquals("La representación en String of the living being no es correcta.", esperado,
                     toString);
    }
}