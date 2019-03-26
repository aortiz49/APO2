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

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;
import uniandes.cupi2.cupiTrains.world.*;

/**
 * Class used to verify the correct implementation of Train.
 */
@SuppressWarnings("deprecation")
public class TrainTest {

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Class on which the tests will be realized.
     */
    private Train train;

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Scenario 1: Creates a new train without wagons.
     */
    @Before
    public void setupScenario1() {
        train = new Train(35);

        // Train stops for the route.
        Date arrivalTime = new Date();
        arrivalTime.setHours(0);
        arrivalTime.setMinutes(0);
        train.addTrainStop(new TrainStop("City" + 0, arrivalTime));
        arrivalTime = new Date();
        arrivalTime.setHours(1);
        arrivalTime.setMinutes(1);
        train.addTrainStop(new TrainStop("City" + 1, arrivalTime));
        arrivalTime = new Date();
        arrivalTime.setHours(2);
        arrivalTime.setMinutes(2);
        train.addTrainStop(new TrainStop("City" + 2, arrivalTime));
        arrivalTime = new Date();
        arrivalTime.setHours(3);
        arrivalTime.setMinutes(3);
        train.addTrainStop(new TrainStop("City" + 3, arrivalTime));
        arrivalTime = new Date();
        arrivalTime.setHours(4);
        arrivalTime.setMinutes(4);
        train.addTrainStop(new TrainStop("City" + 4, arrivalTime));
    }

    /**
     * Scenario 2: Creates a new train with wagons.
     */
    public void setupScenario2() {
        setupScenario1();
        try {
            // Wagon in first class.
            train.addWagon(3, 5, Wagon.CLASSES[0], 300);
            // Wagon in second class.
            train.addWagon(5, 30, Wagon.CLASSES[1], 100);
            // Wagon in business class.
            train.addWagon(7, 20, Wagon.CLASSES[2], 150);
            // Wagon in VIP class.
            train.addWagon(9, 10, Wagon.CLASSES[3], 200);
        } catch (ElementExistsException e) {
            fail("Should not generate an error.");
        }
    }

    /**
     * Scenario 3: Creates a new train with one wagon.
     */
    public void setupScenario3() {
        setupScenario1();
        try {
            // Wagon in first class.
            train.addWagon(3, 1, Wagon.CLASSES[0], 300);
        } catch (ElementExistsException e) {
            fail("Should not generate an error.");
        }
    }

    /**
     * Test 1: Verifies the constructor method of the Train class. <br>
     * <b> Methods to test: </b> <br>
     * Train <br>
     * getId <br>
     * getTrainStops <br>
     * getFirstWagon <br>
     * <b> Test cases: </b> <br>
     * 1. Creates a train correctly and returns the appropriate values. <br>
     */
    @Test
    public void testTrainRoute() {
        assertEquals("Id wasn't initialized correctly.", 35, train.getId());
        assertEquals("The number of train stops wasn't initialized correctly.", 5,
                     train.getTrainStops().size());
        assertNull("The train shouldn't have wagons.", train.getFirstWagon());

    }

    /**
     * Test 2: Verifies that a train stop is added to the train correctly. <br>
     * <b> Methods to test: </b> <br>
     * Train <br>
     * addTrainStop <br>
     * getId <br>
     * getTrainStops <br>
     * getNextTrain <br>
     * getFirstWagon <br>
     * getAmountCollected <br>
     * <b> Test cases: </b> <br>
     * 1. Creates a train correctly and adds the train stops described in scenario 1. It should
     * not have wagons. <br>
     */
    @Test
    public void testAddTrainStop() {
        try {
            assertEquals("Id wasn't initialized correctly.", 35, train.getId());

            ArrayList trainStops = train.getTrainStops();
            assertEquals("The number of train stops wasn't initialized correctly.", 5,
                         trainStops.size());

            assertEquals("Train stop wasn't initialized correctly.", "City0",
                         ((TrainStop) trainStops.get(0)).getName());
            assertEquals("Train stop wasn't initialized correctly.", "City1",
                         ((TrainStop) trainStops.get(1)).getName());
            assertEquals("Train stop wasn't initialized correctly.", "City2",
                         ((TrainStop) trainStops.get(2)).getName());
            assertEquals("Train stop wasn't initialized correctly.", "City3",
                         ((TrainStop) trainStops.get(3)).getName());
            assertEquals("Train stop wasn't initialized correctly.", "City4",
                         ((TrainStop) trainStops.get(4)).getName());

            assertEquals("Arrival time wasn't initialized correctly.", "00:00",
                         ((TrainStop) trainStops.get(0)).getSchedule());
            assertEquals("Arrival time wasn't initialized correctly.", "01:01",
                         ((TrainStop) trainStops.get(1)).getSchedule());
            assertEquals("Arrival time wasn't initialized correctly.", "02:02",
                         ((TrainStop) trainStops.get(2)).getSchedule());
            assertEquals("Arrival time wasn't initialized correctly.", "03:03",
                         ((TrainStop) trainStops.get(3)).getSchedule());
            assertEquals("Arrival time wasn't initialized correctly.", "04:04",
                         ((TrainStop) trainStops.get(4)).getSchedule());

            assertNull("Should not have a next train.", train.getNextTrain());
            assertNull("Should not have a previous train.", train.getNextTrain());
            assertNull("Should not have a first wagon.", train.getFirstWagon());

            assertEquals("Money collected wasn't initialized correctly.", 0.0,
                         train.getAmountCollected(), 0.0);
        } catch (Exception e) {
            fail("Should not generate an error.");
        }
    }

    /**
     * Test 3: Verifies that the train's origin is correct. <br>
     * <b> Methods to test: </b> <br>
     * getOrigin <br>
     * <b> Test cases: </b> <br>
     * 1. Returns the origin city. <br>
     */
    @Test
    public void testGetOrigin() {
        assertEquals("Doesn't return the correct origin city.", "City0", train.getOrigin());
    }

    /**
     * Test 4: Verifies that the train's final destination is correct. <br>
     * <b> Methods to test: </b> <br>
     * getDestination <br>
     * <b> Test cases: </b> <br>
     * 1. Returns the city of the final destination.
     */
    @Test
    public void testGetDestination() {
        assertEquals("Doesn't return the correct destination city.", "City4",
                     train.getDestination());
    }

    /**
     * Test 5: Verifies the changing of trains. <br>
     * <b> Methods to test: </b> <br>
     * Train <br>
     * changeNextTrain <br>
     * getNextTrain <br>
     * getId <br>
     * <b> Test cases: </b> <br>
     * 1. Changes the next train. <br>
     * 2. Changes the next train when there already exists a next train.
     */
    @Test
    public void testChangeNextWagon() {
        try {
            // Test case 1.
            Train newTrain = new Train(5);
            train.changeNextTrain(newTrain);
            assertNotNull("There should be a next train.", train.getNextTrain());
            assertEquals("The next train isn't added correctly.", 5,
                         train.getNextTrain().getId());

            // Test case 2.
            Train newTrain2 = new Train(6);
            train.changeNextTrain(newTrain2);
            assertEquals("The next train isn't added correctly.", 6,
                         train.getNextTrain().getId());

        } catch (Exception e) {
            fail("Should not generate an error.");
        }
    }

    /**
     * Test 6: Se encarga de verify el m�todo cambiarAnterior de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * Train <br>
     * cambiarAnterior <br>
     * darAnterior <br>
     * getId <br>
     * <b> Test cases: </b> <br>
     * 1. Cambia el train anterior. <br>
     * 2. Cambia el train anterior cuando ya existe un anterior.
     */
    @Test
    public void testCambiarAnterior() {
        try {
            // Caso de prueba 1.
            Train newTrain = new Train(5);
            train.changePreviousTrain(newTrain);
            assertNotNull("Deberia tener un train anterior.", train.getPreviousTrain());
            assertEquals("No agrega el train anterior correctamente.", 5,
                         train.getPreviousTrain().getId());

            // Caso de prueba 2.
            Train newTrain2 = new Train(6);
            train.changePreviousTrain(newTrain2);
            assertEquals("No agrega el train anterior correctamente.", 6,
                         train.getPreviousTrain().getId());
        } catch (Exception e) {
            fail("No se deber�a generar ning�n error.");
        }
    }

    /**
     * Test 7: Se encarga de verify el m�todo addWagon de la wagonClass.<br>
     * <b> Methods to test: </b> <br>
     * addWagon <br>
     * getFirstWagon <br>
     * <b> Test cases: </b> <br>
     * 1. Agrega un wagon al train cuando este no tiene vagones. <br>
     * 2. Agrega un wagon al train cuando este ya tiene vagones.
     */
    @Test
    public void testAgregarWagon1() {
        try {
            // Caso de prueba 1.
            // Wagon in first wagonClass.
            train.addWagon(3, 5, Wagon.CLASSES[0], 300);
            assertNotNull("Deber�a tener un wagon.", train.getFirstWagon());
            assertEquals("No agreg� el wagon correctamente.", 3, train.getFirstWagon().getNumber());

            // Caso de prueba 2.
            // Wagon in segunda wagonClass.
            train.addWagon(5, 10, Wagon.CLASSES[1], 100);
            assertNotNull("Deber�a tener el wagon.", train.getFirstWagon().getNextWagon());
            assertEquals("No agreg� el wagon correctamente.", 5,
                         train.getFirstWagon().getNextWagon().getNumber());
        } catch (ElementExistsException e) {
            fail("No deber�a generar error.");
        }

    }

    /**
     * Test 8: Se encarga de verify el m�todo addWagon de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * addWagon <br>
     * <b> Test cases: </b> <br>
     * 1. Intenta agregar un wagon con un n�mero de identificaci�n ya existente.
     */
    @Test
    public void testAgregarWagon2() {
        try {
            // Wagon in first wagonClass.
            train.addWagon(3, 5, Wagon.CLASSES[0], 300);
            // Wagon in segunda wagonClass.
            train.addWagon(3, 7, Wagon.CLASSES[1], 200);

            fail("Deber�a generar error. Ya existe un wagon con ese n�mero.");
        } catch (ElementExistsException e) {
            // Debe pasar por aqu�.
        }
    }

    /**
     * Test 9: Se encarga de verify el m�todo eliminateWagon de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * eliminateWagon <br>
     * getFirstWagon <br>
     * <b> Test cases: </b> <br>
     * 1. Elimina el primer wagon. <br>
     * 2. Elimina un wagon que est� in el medio. <br>
     * 3. Elimina el �ltimo wagon.
     */
    @Test
    public void testEliminarWagon1() {
        setupScenario2();
        try {
            // Caso de prueba 1.
            train.eliminateWagon(3);
            assertNull("El wagon no deber�a existir.", train.findWagonByNumber(3));
            assertEquals("No elimin� el wagon correctamente.", 5,
                         train.getFirstWagon().getNumber());

            // Caso de prueba 2.
            train.eliminateWagon(7);
            assertNull("El wagon no deber�a existir.", train.findWagonByNumber(7));
            assertEquals("No elimin� el wagon correctamente.", 9,
                         train.getFirstWagon().getNextWagon().getNumber());

            // Caso de prueba 3.
            train.eliminateWagon(9);
            assertNull("El wagon no deber�a existir.", train.findWagonByNumber(9));
            assertNull("No elimin� el wagon correctamente.", train.getFirstWagon().getNextWagon());
        } catch (ElementDoesNotExistException e) {
            fail("No deber�a generar error.");
        }

    }

    /**
     * Test 10: Se encarga de verify el m�todo eliminateWagon de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * eliminateWagon <br>
     * <b> Casos de Test: <br>
     * 1. Se intenta eliminar un wagon no existente.
     */
    @Test
    public void testEliminarWagon2() {
        setupScenario2();
        try {
            train.eliminateWagon(10);
            fail("Deber�a generar error. No existe un wagon con ese n�mero.");
        } catch (ElementDoesNotExistException e) {
            // Debe pasar por aqu�.
        }
    }

    /**
     * Test 11: Se encarga de verify el m�todo getTotalAmountOfAvailableSeats de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * getTotalAmountOfAvailableSeats <br>
     * <b> Casos de Test: </b> <br>
     * 1. Returns la cantidad de sillas cuando no hay vagones. <br>
     * 2. Returns la cantidad de sillas cuando hay un wagon. <br>
     * 3. Returns la cantidad de sillas cuando hay m�s de un wagon. <br>
     */
    @Test
    public void testDarCantidadSillasDisponibles() {
        // Caso de prueba 1.
        assertEquals("No calcula correctamente la cantidad de sillas dispoibles.", 0,
                     train.getTotalAmountOfAvailableSeats());

        // Caso de prueba 2.
        setupScenario3();
        assertEquals("No calcula correctamente la cantidad de sillas disponibles.", 1,
                     train.getTotalAmountOfAvailableSeats());

        // Caso de prueba 3.
        setupScenario2();
        assertEquals("No calcula correctamente la cantidad de sillas disponibles.", 65,
                     train.getTotalAmountOfAvailableSeats());
    }

    /**
     * Test 12: Se encarga de verify el m�todo containsTrainStops de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * containsTrainStops <br>
     * <b> Casos de Test: </b> <br>
     * 1. La ciudad de origen y de destino no se encuentra in la lista de trainStops. <br>
     * 2. La ciudad de destino no se encuentra in la lista de trainStops. <br>
     * 3. La ciudad de origen no se encuentre in la lista de trainStops. <br>
     * 4. Las ciudades de origen y destino existen, pero no est�n in el orden correcto. <br>
     * 5. Las ciudades de origen y destino existen y est�n in el orden correcto.
     */
    @Test
    public void testSirveItinerario() {
        // Caso de prueba 1.
        assertFalse("El train no tiene las trainStops.",
                    train.containsTrainStops("City5", "City6"));

        // Caso de prueba 2.
        assertFalse("El train no tiene las trainStops.",
                    train.containsTrainStops("City0", "City5"));

        // Caso de prueba 3.
        assertFalse("El train no tiene las trainStops.",
                    train.containsTrainStops("City-1", "City2"));

        // Caso de prueba 4.
        assertFalse("El train no tiene las trainStops.",
                    train.containsTrainStops("City4", "City2"));
        assertFalse("El train no tiene las trainStops.",
                    train.containsTrainStops("City3", "City1"));

        // Caso de prueba 5.
        assertTrue("El train tiene las trainStops.", train.containsTrainStops("City0", "City1"));
        assertTrue("El train tiene las trainStops.", train.containsTrainStops("City1", "City3"));
    }

    /**
     * Test 13: Se encarga de verify el m�todo sellTicket de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * sellTicket <br>
     * <b> Casos de Test: </b> <br>
     * 1. Intenta vender un tiquete de un wagon no existente.
     */
    @Test
    public void testSellTicket1() {
        setupScenario2();

        try {
            train.sellTicket(8);
            fail("Deber�a genera Excepci�n.");
        } catch (ElementDoesNotExistException e) {
            // Debe pasar por aqu�.
        }
    }

    /**
     * Test 14: Se encarga de verify el m�todo sellTicket de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * sellTicket <br>
     * getTotalAmountOfAvailableSeats <br>
     * getAmountCollected <br>
     * <b> Test cases: </b> <br>
     * 1. Vende tiquetes exitosamente. <br>
     * 2. Trata de vender un tiquete cuando no hay sillas disponibles.
     */
    @Test
    public void testSellTicket2() {
        setupScenario2();

        try {
            // Caso de Test 1.
            assertTrue("Deber�a vender la silla.", train.sellTicket(3));
            assertEquals("No cambi� correctamente la cantidad de sillas disponibles.", 64,
                         train.getTotalAmountOfAvailableSeats());
            assertEquals("No calcula la cantidad recaudada correctamente.", 300.0,
                         train.getAmountCollected(), 0.0);

            assertTrue("Deber�a vender la silla.", train.sellTicket(7));
            assertEquals("No cambi� correctamente la cantidad de sillas disponibles.", 63,
                         train.getTotalAmountOfAvailableSeats());
            assertEquals("No calcula la cantidad recaudada correctamente.", 450.0,
                         train.getAmountCollected(), 0.0);

            // Solo hay una silla disponible.
            setupScenario3();
            assertTrue("Deber�a vender la silla.", train.sellTicket(3));
            assertEquals("No cambi� correctamente la cantidad de sillas disponibles.", 0,
                         train.getTotalAmountOfAvailableSeats());
            assertEquals("No calcula la cantidad recaudada correctamente.", 300.0,
                         train.getAmountCollected(), 0.0);

            // Caso de Test 2.
            assertFalse("No deber�a vender la silla.", train.sellTicket(3));

        } catch (ElementDoesNotExistException e) {
            fail("No deber�a genera Excepci�n.");
        }
    }

    /**
     * Test 15: Se encarga de verify el m�todo findWagonByNumber de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * findWagonByNumber <br>
     * <b> Test cases: </b> <br>
     * 1. El wagon buscado es el primero. <br>
     * 2. El wagon buscado no es el primero. <br>
     * 3. El wagon buscado no existe.
     */
    @Test
    public void testBuscarWagon() {
        setupScenario2();

        // Caso de Test 3.
        Wagon wagon = train.findWagonByNumber(3);
        assertNotNull("No encontr� el wagon.", wagon);
        assertEquals("No encontr� el wagon esperado.", 3, wagon.getNumber());

        // Caso de Test 2.
        wagon = train.findWagonByNumber(5);
        assertNotNull("No encontr� el wagon.", wagon);
        assertEquals("No encontr� el wagon esperado.", 5, wagon.getNumber());

        // Caso de Test 3.
        wagon = train.findWagonByNumber(15);
        assertNull("No deber�a encontrar un wagon.", wagon);
    }

    /**
     * Test 16: Se encarga de verify el m�todo getDepartureTime de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * getDepartureTime <br>
     * <b> Test cases: </b> <br>
     * 1. Returns la hora de salida correctamente.
     */
    @Test
    public void testDarHorarioSalida() {
        assertEquals("No retorna la hora de salida correctamente.", "00:00",
                     train.getDepartureTime());
    }

    /**
     * Test 17: Se encarga de verify el m�todo getArrivalTime de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * getDepartureTime <br>
     * <b> Test cases: </b> <br>
     * 1. Returns la hora de llegada correctamente.
     */
    @Test
    public void testDarHorarioLlegada() {
        assertEquals("No retorna la hora de llegada correctamente.", "04:04",
                     train.getArrivalTime());
    }
}