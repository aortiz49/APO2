/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupiTrenes
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package source.uniandes.cupi2.cupiTrenes.test;

import java.util.Date;

import uniandes.cupi2.cupiTrains.world.TrainStop;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.*;

/**
 * Clase usada para verify la correcta implementaci�n de TrainStop.
 */
public class TrainStopTest {
    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private TrainStop parada;

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Scenario 1: Crea una nueva parada.
     */
    @Before
    public void setupScenario1() {
        Date arrivalTime = new Date();
        arrivalTime.setHours(2);
        arrivalTime.setMinutes(0);
        parada = new TrainStop("Munich", arrivalTime);
    }

    /**
     * Test 1: Se encarga de verify el m�todo constructor de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * TrainStop <br>
     * getName <br>
     * getArrivalTime <br>
     * <b> Test cases: </b> <br>
     * 1. Construye correctamente la parada del escenario 1, cada uno de los valores corresponde
     * al esperado.
     */
    @Test
    public void testTrainStop() {
        assertEquals("No inicializ� el name de la parada correctamente.", "Munich",
                     parada.getName());
        assertEquals("No inicializ� el arrivalTime de la parada correctamente.", "02:00",
                     parada.getSchedule());
    }

}
