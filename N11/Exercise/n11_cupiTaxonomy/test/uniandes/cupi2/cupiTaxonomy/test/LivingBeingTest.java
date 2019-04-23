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
        livingBeing = new LivingBeing("Name Com�n", "Name Cient�fico", "Caracter�sticas", "Image");
    }

    /**
     * Prueba 1: Se encarga of verificar el m�todo constructor of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * LivingBeing <br>
     * getCommonName <br>
     * getScientificName <br>
     * getCharacteristics <br>
     * getImagePath <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Crea correctamente un living being con los datos dados por par�metro.
     */
    @Test
    public void testLivingBeing() {
        setupScenario1();

        assertNotNull("El name com�n of the living being no fue inicializado.",
                      livingBeing.getCommonName());
        assertEquals("El name com�n of the living being no fue inicializado correctamente.",
                     "Name Com�n", livingBeing.getCommonName());
        assertNotNull("El name cient�fico of the living being no fue inicializado.",
                      livingBeing.getScientificName());
        assertEquals("El name cient�fico of the living being no fue inicializado correctamente.",
                     "Name Cient�fico", livingBeing.getScientificName());
        assertNotNull("Las caracter�sticas of the living being no fueron inicializadas.",
                      livingBeing.getCharacteristics());
        assertEquals(
                "Las caracter�sticas of the living being no fueron inicializadas correctamente.",
                "Caracter�sticas", livingBeing.getCharacteristics());
        assertNotNull("La ruta of la image of the living being no fue inicializada.",
                      livingBeing.getImagePath());
        assertEquals("La ruta of la image of the living being no fue inicializada correctamente",
                     "Image", livingBeing.getImagePath());
    }

    /**
     * Prueba 2: Se encarga of verificar el m�todo toString of la clase. <br>
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
        assertNotNull("La representaci�n en String of the living being no deber�a ser nula.",
                      toString());
        assertEquals("La representaci�n en String of the living being no es correcta.", esperado,
                     toString);
    }
}