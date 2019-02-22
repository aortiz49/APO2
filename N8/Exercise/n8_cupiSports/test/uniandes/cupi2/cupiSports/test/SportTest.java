/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiSports
 * Autor: Equipo Cupi2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiSports.test;


import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.Test;
import uniandes.cupi2.cupiSports.world.Athlete;
import uniandes.cupi2.cupiSports.world.ElementExistsException;
import uniandes.cupi2.cupiSports.world.Sport;


/**
 * Clase usada para verificar que los m�todos de la clase Sport est�n correctamente implementados.
 */
public class SportTest {
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private Sport sport;

    /**
     * Athlete del sport.
     */
    private Athlete athlete1;

    /**
     * Deprtista del sport.
     */
    private Athlete athlete2;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Sport.
     */
    @Before
    public void setupScenario1() {
        sport = new Sport("Futbol", "FCF", 2300, "imagen");
    }

    /**
     * Construye un nuevo sport, dos athletes y se agrega uno.
     */
    private void setupScenario2() {
        try {
            sport = new Sport("F�tbol", "FCF", 2300, "imagen");
            athlete1 = new Athlete("James", 23, "Madrid", 39, "imagen2");
            athlete2 = new Athlete("Falcao", 29, "Manchester", 35, "imagen3");
            sport.addOutstandingAthlete(athlete1);
        } catch (ElementExistsException e) {
            fail("No deber�a generar una excepci�n");
        }
    }

    /**
     * Construye un nuevo sport, dos athletes y se agregan ambos.
     */
    private void setupScenario3() {
        try {
            sport = new Sport("Futbol", "FCF", 2300, "imagen");
            athlete1 = new Athlete("James", 23, "Madrid", 39, "imagen2");
            athlete2 = new Athlete("Falcao", 29, "Manchester", 35, "imagen3");
            sport.addOutstandingAthlete(athlete1);
            sport.addOutstandingAthlete(athlete2);
        } catch (ElementExistsException e) {
            fail("No deber�a generar una excepci�n");
        }
    }

    /**
     * Test 1: Verifica el m�todo constructor.<br>
     * <b> Methods to test: </b> <br>
     * constructor getName<br>
     * getRegulatoryEntity<br>
     * getNumberOfRegisteredAthletes<br>
     * getImagePath<br>
     * getOutstandingAthletes <br>
     * <b> Objective: </b> Probar inicializaci�n correcta del objeto Sport<br>
     * <b> Expected results: </b> <br>
     * 1. Inicializaci�n correcta de Sport<br>
     */
    @Test
    public void testConstructor() {

        assertEquals("El name del sport es incorrecto.", "Futbol", sport.getName());
        assertEquals("El ente regulador del sport es incorrecto.", "FCF",
                     sport.getRegulatoryEntity());
        assertEquals("La fecha cantidad de athletes registrados del sport es incorrecta.", 2300,
                     sport.getNumberOfRegisteredAthletes());
        assertEquals("La ruta de imagen del sport es incorrecta.", "imagen", sport.getImagePath());
        assertNotNull("La lista de athletes es nula.", sport.getOutstandingAthletes());
        assertEquals("La lista de athletes no es vac�a.", 0, sport.getOutstandingAthletes().size());
    }

    /**
     * Test 2: Verifica el m�todo outstandingAthleteExists.<br>
     * <b> Methods to test: </b> <br>
     * outstandingAthleteExists. <br>
     * <b> Objective: </b> Probar funcionamiento correcto de outstandingAthleteExists<br>
     * <b> Expected results: </b> <br>
     * 1. Resulado obtenido correcto de outstandingAthleteExists<br>
     */
    @Test
    public void testExisteAthleteSobresaliente() {
        setupScenario2();
        assertTrue("El athlete deber�a existir.",
                   sport.outstandingAthleteExists(athlete1.getName()));
        assertFalse("El athlete no deber�a existir.",
                    sport.outstandingAthleteExists(athlete2.getName()));
    }

    /**
     * Test 3: Verifica el m�todo addOutstandingAthlete.<br>
     * <b> Methods to test: </b> <br>
     * addOutstandingAthlete. <br>
     * outstandingAthleteExists.<br>
     * getOutstandingAthletes.<br>
     * <b> Objective: </b> Probar funcionamiento correcto de addOutstandingAthlete<br>
     * <b> Expected results: </b> <br>
     * 1. Agrega correctamente un athlete.<br>
     */
    @Test
    public void testAddAthleteSobresalienteOK() {
        Athlete test = new Athlete("Lebron", 32, "Cleveland", 40, "./data/images/lebron.png");
        try {
            sport.addOutstandingAthlete(test);
        } catch (ElementExistsException e) {
            // e.printStackTrace();
        }
        assertTrue("Athlete should be added",sport.outstandingAthleteExists("Lebron"));
    }

    /**
     * Test 4: Verifica el m�todo addOutstandingAthlete.<br>
     * <b> Methods to test: </b> <br>
     * addOutstandingAthlete.<br>
     * outstandingAthleteExists.<br>
     * getOutstandingAthletes.<br>
     * <b> Objective: </b> Probar funcionamiento correcto de addOutstandingAthlete<br>
     * <b> Expected results: </b> <br>
     * 2. No agrega un athlete porque lanza excepci�n.<br>
     */
    @Test
    public void testAddAthleteSobresalienteError() {
        boolean added = false;
        Athlete test = new Athlete("Michael", 32, "Cleveland", 40, "./data/images/lebron.png");
        try {
            sport.addOutstandingAthlete(test);
            added = true;
        } catch (ElementExistsException e) {
            // e.printStackTrace();
        }
        assertTrue("Athlete shouldn't be added",added);
    }


    /**
     * Test 5: Verifica el m�todo eliminateOutstandingAthlete.<br>
     * <b> Methods to test: </b> <br>
     * addOutstandingAthlete.<br>
     * outstandingAthleteExists.<br>
     * getOutstandingAthletes.<br>
     * <b> Objective: </b> Probar funcionamiento correcto de eliminateOutstandingAthlete<br>
     * <b> Expected results: </b> <br>
     * 1. Elimina correctamente un athlete.<br>
     */
    @Test
    public void testEliminarAthleteSobresaliente() {
        setupScenario3();
        sport.eliminateOutstandingAthlete("James");
        assertEquals("El tama�o de los athletes deber�a ser 1.", 1,
                     sport.getOutstandingAthletes().size());
        assertFalse("No deber�a existir el athlete eliminado.",
                    sport.outstandingAthleteExists("James"));
        sport.eliminateOutstandingAthlete("Falcao");
        assertEquals("El tama�o de los athletes deber�a ser 0.", 0,
                     sport.getOutstandingAthletes().size());
        assertFalse("No deber�a existir el athlete eliminado.",
                    sport.outstandingAthleteExists("Falcao"));
    }

    /**
     * Test 6: Verifica el m�todo getAthleteMostTrophies.<br>
     * <b> Methods to test: </b> <br>
     * getAthleteMostTrophies.<br>
     * <b> Objective: </b> Probar funcionamiento correcto de getAthleteMostTrophies<br>
     * <b> Expected results: </b> <br>
     * 1. Cuando no hay athletes retorna null.<br>
     * 2. Cuando hay un athlete. 3. Cuando hay m�s de un athlete.
     */
    @Test
    public void testDarAthleteMasTrophies() {
        // 1
        assertNull("Deber�a ser nulo.", sport.getAthleteMostTrophies());

        // 2
        setupScenario2();
        assertNotNull("No deber�a ser nulo.", sport.getAthleteMostTrophies());
        assertEquals("El athlete con m�s trofeos no corresponde.", "James",
                     sport.getAthleteMostTrophies().getName());

        // 3
        setupScenario2();
        assertNotNull("No deber�a ser nulo.", sport.getAthleteMostTrophies());
        assertEquals("El athlete con m�s trofeos no corresponde.", "James",
                     sport.getAthleteMostTrophies().getName());

    }

    /**
     * Test 7: Verifica el m�todo getTotalTrophies.<br>
     * <b> Methods to test: </b> <br>
     * getTotalTrophies.<br>
     * <b> Objective: </b> Probar funcionamiento correcto de getTotalTrophies<br>
     * <b> Expected results: </b> <br>
     * 1. Cuando no hay athletes retorna 0.<br>
     * 2. Cuando hay un athlete. 3. Cuando hay m�s de un athlete.
     */
    @Test
    public void testDarTotalTrophies() {
        // 1
        assertEquals("El n�mero total de trofeos no corresponde.", 0, sport.getTotalTrophies());

        // 2
        setupScenario2();
        assertEquals("El n�mero total de trofeos no corresponde.", 39, sport.getTotalTrophies());

        // 3
        setupScenario3();
        assertEquals("El n�mero total de trofeos no corresponde.", 74, sport.getTotalTrophies());

    }

}