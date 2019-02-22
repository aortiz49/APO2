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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import uniandes.cupi2.cupiSports.world.ElementExistsException;
import uniandes.cupi2.cupiSports.world.FileFormatException;
import uniandes.cupi2.cupiSports.world.PersistenceException;
import uniandes.cupi2.cupiSports.world.*;

import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Clase usada para verificar que los m�todos de la clase CupiSports est�n correctamente
 * implementados.
 */
public class CupiSportsTest {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Ruta para cargar el file de prueba.
     */
    private final static String RUTA_PRUEBA = "./test/data/cupiSports.data";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private CupiSports cupiSports;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo sistema CupiSports con 2 sports y 2 athletes. <br>
     * deporte1: F�tbol, FCF, 2500, futbol.jpg. <br>
     * athlete1: James, 23, Madrid, 39, jamesRodriguez.jpg.<br>
     * deporte2: Tenis, FCT, 110, tenis.jpg. <br>
     * athlete2: Alejandro, 31, Cali, 11, alejandroFalla.jpg.
     */
    @Before
    public void setupScenario1() {
        try {
            cupiSports = new CupiSports(RUTA_PRUEBA);
        } catch (PersistenceException e) {
            fail("Hay errores al cargar el file de CupiSports.");
        }
    }

    /**
     * Construye una nuevo sistema CupiSports vac�o.
     */
    private void setupScenario2() {
        try {
            cupiSports = new CupiSports("");
        } catch (PersistenceException e) {
            fail("Hay errores al cargar el file de CupiSports.");
        }
    }

    /**
     * Test 1: Verifica el m�todo constructor.<br>
     * <b> Methods to test: </b> <br>
     * constructor getSports <br>
     * <b> Objective: </b> Probar inicializaci�n correcta del objeto CupiSports<br>
     * <b> Expected results: </b> <br>
     * 1. Inicializaci�n correcta de CupiSports<br>
     */
    @Test
    public void testConstructorCupiSports() {

        ArrayList<Sport> listaSports = cupiSports.getSports();
        assertNotNull("No deber�a ser nula la lista.", listaSports);
        assertEquals("El tama�o de la lista no corresponde.", 2, listaSports.size());

        // El primer deporte
        Sport primero = listaSports.get(0);
        assertEquals("El primer deporte no corresponde.", "F�tbol", primero.getName());
        ArrayList<Athlete> athletesPrimero = primero.getOutstandingAthletes();
        Athlete primerAthlete = athletesPrimero.get(0);
        assertEquals("El primer athlete no corresponde.", "James", primerAthlete.getName());

        // El segundo deporte
        Sport segundo = listaSports.get(1);
        assertEquals("El segundo deporte no corresponde.", "Tenis", segundo.getName());
        ArrayList<Athlete> athletesSegundo = segundo.getOutstandingAthletes();
        Athlete segundoAthlete = athletesSegundo.get(0);
        assertEquals("El segundo athlete no corresponde.", "Alejandro", segundoAthlete.getName());

    }

    /**
     * Test 2: Verifica el m�todo existsSport.<br>
     * <b> Methods to test: </b> <br>
     * existsSport <br>
     * <b> Objective: </b> Probar funcionamiento correcto existsSport<br>
     * <b> Expected results: </b> <br>
     * 1. Existe el deporte.<br>
     * 2. No exists el deporte.<br>
     */
    @Test
    public void testExisteSport() {

        // 1
        assertTrue("Deber�a existir el deporte.", cupiSports.existsSport("F�tbol"));

        // 2
        assertFalse("No deber�a existir el deporte.", cupiSports.existsSport("Basket"));
    }

    /**
     * Test 3: Verifica el m�todo addSport.<br>
     * <b> Methods to test: </b> <br>
     * addSport<br>
     * existsSport<br>
     * getSports<br>
     * <b> Objective: </b> Probar funcionamiento correcto addSport<br>
     * <b> Expected results: </b> <br>
     * 1. Agrega un deporte nuevo.<br>
     */
    @Test
    public void testAddSportOK() {
        //TODO Parte 4 punto E: Implemente el m�todo seg�n la documentaci�n.
    }

    /**
     * Test 4: Verifica el m�todo addSport.<br>
     * <b> Methods to test: </b> <br>
     * addSport<br>
     * getSports<br>
     * <b> Objective: </b> Probar funcionamiento correcto addSport al lanzar excepci�n<br>
     * <b> Expected results: </b> <br>
     * 1. No agrega, dado que ya exist�a un deporte con el name dado.<br>
     */
    @Test
    public void testAddSportError() {
        //TODO Parte 4 punto F: Implemente el m�todo seg�n la documentaci�n.
    }

    /**
     * Test 5: Verifica el m�todo deleteSport.<br>
     * <b> Methods to test: </b> <br>
     * deleteSport<br>
     * existsSport<br>
     * <b> Objective: </b> Probar funcionamiento correcto deleteSport<br>
     * <b> Expected results: </b> <br>
     * 1. No exists el deporte.<br>
     */
    @Test
    public void testEliminarSport() {

        cupiSports.deleteSport("F�tbol");
        assertEquals("El tama�o de la lista de sports deber�a ser 1.", 1,
                     cupiSports.getSports().size());
        assertFalse("No deber�a existir el deporte.", cupiSports.existsSport("F�tbol"));
    }

    /**
     * Test 6: Verifica el m�todo addAthlete.<br>
     * <b> Methods to test: </b> <br>
     * addAthlete<br>
     * getSports<br>
     * <b> Objective: </b> Probar funcionamiento correcto addAthlete<br>
     * <b> Expected results: </b> <br>
     * 1. Agrega el athlete.<br>
     */
    @Test
    public void testAddAthleteOK() {
        // 1
        try {
            cupiSports
                    .addOutstandingAthlete("F�tbol", "Falcao", 29, "Manchester", 35, "falcao.jpg");
            Sport primero = (Sport) cupiSports.getSports().get(0);
            assertEquals("El tama�o de los athletes no coincide.", 2,
                         primero.getOutstandingAthletes().size());
            Athlete nuevo = primero.getOutstandingAthletes().get(1);
            assertEquals("El name of the athlete nuevo no coincide.", "Falcao", nuevo.getName());
        } catch (ElementExistsException e) {
            fail("No deber�a lanzar una excepci�n al add el athlete.");
        }

        // 2
        try {
            cupiSports
                    .addOutstandingAthlete("F�tbol", "Falcao", 29, "Manchester", 35, "falcao.jpg");
            fail("Deber�a lanzar una excepci�n al add el athlete.");
        } catch (ElementExistsException e) {
            Sport primero = (Sport) cupiSports.getSports().get(0);
            assertEquals("El tama�o de los athletes no coincide.", 2,
                         primero.getOutstandingAthletes().size());
        }
    }

    /**
     * Test 7: Verifica el m�todo addAthlete.<br>
     * <b> Methods to test: </b> <br>
     * addAthlete<br>
     * getSports<br>
     * <b> Objective: </b> Probar funcionamiento correcto addAthlete<br>
     * <b> Expected results: </b> <br>
     * 1. No agrega el athlete porque lanza excepci�n.<br>
     */
    @Test
    public void testAddAthleteError() {

        // 2
        try {
            cupiSports.addOutstandingAthlete("F�tbol", "James", 23, "Madrid", 39, "james.jpg");
            fail("Deber�a lanzar una excepci�n al add el athlete.");
        } catch (ElementExistsException e) {
            Sport primero = (Sport) cupiSports.getSports().get(0);
            assertEquals("El tama�o de los athletes no coincide.", 1,
                         primero.getOutstandingAthletes().size());
        }
    }

    /**
     * Test 8: Verifica el m�todo deleteAthlete.<br>
     * <b> Methods to test: </b> <br>
     * deleteAthlete<br>
     * getSports<br>
     * <b> Objective: </b> Probar funcionamiento correcto deleteAthlete<br>
     * <b> Expected results: </b> <br>
     * 1. No exists el athlete.<br>
     */
    @Test
    public void testEliminarAthlete() {

        cupiSports.eliminateOutstandingAthlete("F�tbol", "Falcao");
        Sport primero = (Sport) cupiSports.getSports().get(0);
        assertEquals("El tama�o de los athletes no coincide.", 1,
                     primero.getOutstandingAthletes().size());

    }

    /**
     * Test 9: Verifica el m�todo getAthleteMostTrophies.<br>
     * <b> Methods to test: </b> <br>
     * getAthleteMostTrophies <br>
     * <b> Objective: </b> Probar funcionamiento correcto getAthleteMostTrophies<br>
     * <b> Expected results: </b> <br>
     * 1. Cuando hay athletes retorna el athlete con mas trofeos.<br>
     * 2. Cuando no hay athletes retorna null. <br>
     */
    @Test
    public void testDarAthleteMasTrophies() {
        // 1

        assertNotNull("No deber�a ser nulo.", cupiSports.getAthleteMostTrophies());
        assertEquals("El name of the athlete con m�s trofeos no corresponde.", "James",
                     cupiSports.getAthleteMostTrophies().getName());

        // 2
        setupScenario2();
        assertNull("Deber�a ser nulo.", cupiSports.getAthleteMostTrophies());
    }

    /**
     * Test 10: Verifica el m�todo getAthleteMostTrophies.<br>
     * <b> Methods to test: </b> <br>
     * getAthleteMostTrophies <br>
     * <b> Objective: </b> Probar funcionamiento correcto getAthleteMostTrophies<br>
     * <b> Expected results: </b> <br>
     * 1. Cuando hay 2 athletes que tienen igual n�mero de trofeos y son m�ximos, retorna
     * cualquiera.<br>
     */
    @Test
    public void testDarAthleteMasTrophies2() {
        // 1

        try {
            cupiSports
                    .addOutstandingAthlete("F�tbol", "Falcao", 39, "Manchester", 35, "falcao.jpg");
        } catch (ElementExistsException e) {
            fail("No deber�a generar excepci�n.");
        }

        assertNotNull("No deber�a ser nulo.", cupiSports.getAthleteMostTrophies());
        assertTrue("El name of the athlete con m�s trofeos no corresponde.",
                   cupiSports.getAthleteMostTrophies().getName().equals("James") || cupiSports
                           .getAthleteMostTrophies().getName().equals("Falcao"));

    }

    /**
     * Test 11: Verifica el m�todo getTotalTrophies.<br>
     * <b> Methods to test: </b> <br>
     * getTotalTrophies <br>
     * <b> Objective: </b> Probar funcionamiento correcto getTotalTrophies<br>
     * <b> Expected results: </b> <br>
     * 1. Cuando hay athletes retorna la suma de todos los trofeos.<br>
     * 2. Cuando no hay athletes retorna 0. <br>
     */
    @Test
    public void testDarTotalTrophies() {
        // 1

        assertEquals("El n�mero total de trofeos no es el esperado.", 50,
                     cupiSports.getTotalTrophies());

        // 2
        setupScenario2();
        assertEquals("El n�mero total de trofeos no es el esperado.", 0,
                     cupiSports.getTotalTrophies());

    }

    /**
     * Test 12: Verifica el m�todo gua.get.<br>
     * <b> Methods to test: </b> <br>
     * gua.get <br>
     * constructor <br>
     * <b> Objective: </b> Probar funcionamiento correcto gua.get<br>
     * <b> Expected results: </b> <br>
     * 1. Al gua.get, carga correctamente.<br>
     */
    @Test
    public void testGuardar() {

        try {
            cupiSports.saveState(RUTA_PRUEBA);
            CupiSports temp = new CupiSports(RUTA_PRUEBA);
            assertEquals("No se guard� correctamente CupiSports.", 2, temp.getSports().size());

        } catch (PersistenceException e) {
            fail("No se deber�a generar una excepci�n");
        }
    }

    /**
     * Test 13: Verifica el m�todo updateAthletesInformation.<br>
     * <b> Methods to test: </b> <br>
     * updateAthletesInformation <br>
     * getSports <br>
     * <b> Objective: </b> Probar funcionamiento correcto updateAthletesInformation<br>
     * <b> Expected results: </b> <br>
     * 1. Cuando actualiza correctamente.<br>
     * 2. Cuando hay errores de formato. Cantidad de athletes mal.<br>
     * 3. Cuando hay errores de formato. No hay datos de athletes.<br>
     * 4. Cuando hay errores de formato. Falta un dato en el formato.<br>
     * 5. Cuando hay errores de formato. N�meros negativos.<br>
     */
    @Test
    public void testActualizarAthletesInformation() {
        // 1

        File pFile = new File("./test/data/datosTestOK.txt");
        try {
            cupiSports.updateAthletesInformation(pFile);
            Sport primero = (Sport) cupiSports.getSports().get(0);
            Sport segundo = (Sport) cupiSports.getSports().get(1);
            Athlete deport1 = primero.getOutstandingAthletes().get(0);
            Athlete deport2 = segundo.getOutstandingAthletes().get(0);

            assertEquals("El name del primer athlete no corresponde.", "James", deport1.getName());
            assertEquals("La age del primer athlete no corresponde.", 24, deport1.getAge());
            assertEquals("El lugar de residencia del primer athlete no corresponde.",
                         "Madrid, Espa�a", deport1.getPlaceOfResidency());
            assertEquals("La cantidad de trofeos del primer athlete no corresponde.", 45,
                         deport1.getAmountOfTrophies());
            assertEquals("La ruta de la imagen del primer athlete no corresponde.",
                         "./data/images/jamesRodriguez.jpg", deport1.getImagePath());

            assertEquals("El name del segundo athlete no corresponde.", "Alejandro",
                         deport2.getName());
            assertEquals("La age del segundo athlete no corresponde.", 32, deport2.getAge());
            assertEquals("El lugar de residencia del segundo athlete no corresponde.",
                         "Cali, Colombia", deport2.getPlaceOfResidency());
            assertEquals("La cantidad de trofeos del segundo athlete no corresponde.", 12,
                         deport2.getAmountOfTrophies());
            assertEquals("La ruta de la imagen del segundo athlete no corresponde.",
                         "./data/images/alejandroFalla.jpg", deport2.getImagePath());
        } catch (FileNotFoundException e) {
            fail("No deber�a lanzar esta excepci�n.");
        } catch (IOException e) {
            fail("No deber�a lanzar esta excepci�n.");
        } catch (FileFormatException e) {
            fail("No deber�a lanzar esta excepci�n.");
        }

        // 2

        File pFile2 = new File("./test/data/datosTestError.txt");
        try {
            cupiSports.updateAthletesInformation(pFile2);
            fail("Deber�a lanzar excepci�n.");
        } catch (FileNotFoundException e) {
            fail("No deber�a lanzar esta excepci�n.");
        } catch (IOException e) {
            fail("No deber�a lanzar esta excepci�n.");
        } catch (FileFormatException e) {
            // Deber�a estar aca.
        }

        // 3

        File pFile3 = new File("./test/data/datosTestError2.txt");
        try {
            cupiSports.updateAthletesInformation(pFile3);
            fail("Deber�a lanzar excepci�n.");
        } catch (FileNotFoundException e) {
            fail("No deber�a lanzar esta excepci�n.");
        } catch (IOException e) {
            fail("No deber�a lanzar esta excepci�n.");
        } catch (FileFormatException e) {
            // Deber�a estar aca.
        }

        // 4

        File pFile4 = new File("./test/data/datosTestError3.txt");
        try {
            cupiSports.updateAthletesInformation(pFile4);
            fail("Deber�a lanzar excepci�n.");
        } catch (FileNotFoundException e) {
            fail("No deber�a lanzar esta excepci�n.");
        } catch (IOException e) {
            fail("No deber�a lanzar esta excepci�n.");
        } catch (FileFormatException e) {
            // Deber�a estar aca.
        }

        // 5

        File pFile5 = new File("./test/data/datosTestError4.txt");
        try {
            cupiSports.updateAthletesInformation(pFile5);
            fail("Deber�a lanzar excepci�n.");
        } catch (FileNotFoundException e) {
            fail("No deber�a lanzar esta excepci�n.");
        } catch (IOException e) {
            fail("No deber�a lanzar esta excepci�n.");
        } catch (FileFormatException e) {
            // Deber�a estar aca.
        }
    }
}