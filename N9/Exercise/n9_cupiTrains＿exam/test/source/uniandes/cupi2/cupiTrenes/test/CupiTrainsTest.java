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


import org.junit.Before;
import org.junit.Test;
import uniandes.cupi2.cupiTrains.world.*;

import static org.junit.Assert.*;

/**
 * Clase usada para verify la correcta implementaci�n de CupiTrains.
 */
public class CupiTrainsTest {
    // -------------------------------------------------------------
    // Constante
    // -------------------------------------------------------------

    /**
     * Prefijo de la ruta donde est�n los archivos de prueba.
     */
    public static final String RUTA_ARCHIVO = "test/data/test";

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private CupiTrains cupiTrenes;

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Scenario 1: Crea un nuevo cupiTrains vac�o.
     */
    @Before
    public void setupScenario1() {
        try {
            cupiTrenes = new CupiTrains(RUTA_ARCHIVO + "0.data");
        } catch (Exception e) {
            fail("No deber�a generar excepci�n.");
        }
    }

    /**
     * Scenario 2: Crea un nuevo cupiTrains con 2 trenes sin vagones.
     */
    public void setupScenario2() {
        try {
            cupiTrenes = new CupiTrains(RUTA_ARCHIVO + "0.data");

            String[] trainStops1 = {"Lyon", "Geneve", "Zurich", "Konstanz", "Stuttgart"};
            int horas1[] = {3, 9, 12, 13, 18};
            int minutos1[] = {30, 35, 50, 50, 10};
            Date[] arrivalTimes1 = new Date[horas1.length];
            for (int i = 0; i < arrivalTimes1.length; i++) {
                arrivalTimes1[i] = new Date(0, 0, 0, horas1[i], minutos1[i]);
            }
            cupiTrenes.addNewTrain(35, trainStops1, arrivalTimes1);

            String[] trainStops2 = {"Barcelona", "Madrid"};
            int horas2[] = {6, 12};
            int minutos2[] = {0, 35};
            Date[] arrivalTimes2 = new Date[horas2.length];
            for (int i = 0; i < arrivalTimes2.length; i++) {
                arrivalTimes2[i] = new Date(0, 0, 0, horas2[i], minutos2[i]);
            }
            cupiTrenes.addNewTrain(50, trainStops2, arrivalTimes2);
        } catch (Exception e) {
            fail("No deber�a generar excepci�n.");
        }
    }

    /**
     * Scenario 3: Crea un nuevo cupiTrains con trenes que tienen vagones.
     */
    public void setupScenario3() {
        try {
            setupScenario2();

            // Vag�n en segunda wagonClass.
            cupiTrenes.addWagon(35, 4, 5, Wagon.CLASSES[1], 50.0);

            // Vag�n en first wagonClass.
            cupiTrenes.addWagon(35, 5, 40, Wagon.CLASSES[0], 100.0);

        } catch (Exception e) {
            fail("No deber�a generar excepci�n.");
        }
    }

    /**
     * Test 1: Se encarga de verify el m�todo constructor de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * CupiTrains <br>
     * darTotralRecaudo <br>
     * getTotalAvailableChairs <br>
     * getFirstTrain <br>
     * <b> Casos de Test: </b> <br>
     * 1. Crea un nuevo cupiTrains con trenes sin vagones.
     */
    @Test
    public void testCupiTrains1() {
        setupScenario2();

        assertEquals("No se inicializ� la wagonClass correctamente.", 0.0,
                     cupiTrenes.getTotalMoneyCollected(), 0.0);
        assertEquals("No se inicializ� la wagonClass correctamente.", 0,
                     cupiTrenes.getTotalAvailableChairs());

        Train rutaTren = cupiTrenes.getFirstTrain();
        assertNotNull("No se inicializaron los trenes correctamente.", rutaTren);
        assertEquals("No se inicializaron los trenes correctamente.", 35, rutaTren.getId());
        assertEquals("No se inicializaron los trenes correctamente.", 5,
                     rutaTren.getTrainStops().size());

        rutaTren = rutaTren.getNextTrain();
        assertEquals("No se inicializaron los trenes correctamente.", 50, rutaTren.getId());
        assertEquals("No se inicializaron los trenes correctamente.", 2,
                     rutaTren.getTrainStops().size());

    }

    /**
     * Test 2: Se encarga de verify el m�todo constructor de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * CupiTrains <br>
     * getTotalMoneyCollected <br>
     * getTotalAvailableChairs <br>
     * getFirstTrain <br>
     * <b> Casos de Test: </b> <br>
     * 1. Crea un nuevo cupiTrains con viajes y con vagones.
     */
    @Test
    public void testCupiTrains2() {
        setupScenario3();
        assertEquals("No se inicializ� la wagonClass correctamente.", 0.0,
                     cupiTrenes.getTotalMoneyCollected(), 0.0);
        assertEquals("No se inicializ� la wagonClass correctamente.", 45,
                     cupiTrenes.getTotalAvailableChairs());

        Wagon wagon = cupiTrenes.getFirstTrain().getFirstWagon();
        assertEquals("No se inicializaron los vagones correctamente.", 4, wagon.getNumber());
        assertEquals("No se inicializaron los vagones correctamente.", 5,
                     wagon.getTotalAmountOfAvailableSeats());
        assertEquals("No se inicializaron los vagones correctamente.", 50.0, wagon.getPrice(), 0.0);

        wagon = wagon.getNextWagon();
        assertEquals("No se inicializaron los vagones correctamente.", 5, wagon.getNumber());
        assertEquals("No se inicializaron los vagones correctamente.", 40,
                     wagon.getTotalAmountOfAvailableSeats());
        assertEquals("No se inicializaron los vagones correctamente.", 100.0, wagon.getPrice(),
                     0.0);

    }

    /**
     * Test 3: Se encarga de verify el m�todo getTotalMoneyCollected de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * getTotalMoneyCollected <br>
     * sellTicket <br>
     * <b> Test cases: </b> <br>
     * 1. Calcula el total recaudado despu�s de vender varios tiquetes.
     */
    @Test
    public void testDarTotalRecaudo() {
        setupScenario3();
        assertEquals("No calcula el recaudo correctamente.", 0.0,
                     cupiTrenes.getTotalMoneyCollected(), 0.0);
        try {
            cupiTrenes.sellTicket(35, 4);
            assertEquals("No calcula correctamente el recaudo.", 50.0,
                         cupiTrenes.getTotalMoneyCollected(), 0.0);

            cupiTrenes.sellTicket(35, 4);
            cupiTrenes.sellTicket(35, 5);
            cupiTrenes.sellTicket(35, 4);
            assertEquals("No calcula correctamente el recaudo.", 250.0,
                         cupiTrenes.getTotalMoneyCollected(), 0.0);
        } catch (ElementDoesNotExistException e) {
            fail("No deber�a generar error.");
        }
    }

    /**
     * Test 4: Se encarga de verify el m�todo buscarTrenPorTrainStops de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * buscarTrenPorTrainStops <br>
     * <b> Test cases: </b> <br>
     * 1. No hay trenes en el sistema. <br>
     * 2. El tren no existe. <br>
     * 3. El tren existe.
     */
    @Test
    public void testBuscarTrenPorTrainStops() {
        // Caso de prueba 1.
        assertNull("No hay trenes en el sistema.",
                   cupiTrenes.findTrainByTrainStops("Lyon", "Zurich"));

        // Caso de prueba 2.
        setupScenario2();
        assertNull("No deber�a encontrar un tren.",
                   cupiTrenes.findTrainByTrainStops("Stuttgart", "Konstanz"));
        assertNull("No deber�a encontrar un tren.",
                   cupiTrenes.findTrainByTrainStops("Madrid", "Barcelona"));
        assertNull("No deber�a encontrar un tren.",
                   cupiTrenes.findTrainByTrainStops("Madrid", "Stuttgart"));
        assertNull("No deber�a encontrar un tren.",
                   cupiTrenes.findTrainByTrainStops("Stuttgart", "Madrid"));

        // Caso de prueba 3.
        assertEquals("No encontr� el tren deseado.", 35,
                     cupiTrenes.findTrainByTrainStops("Lyon", "Zurich").getId());
        assertEquals("No encontr� el tren deseado.", 35,
                     cupiTrenes.findTrainByTrainStops("Geneve", "Konstanz").getId());
        assertEquals("No encontr� el tren deseado.", 50,
                     cupiTrenes.findTrainByTrainStops("Barcelona", "Madrid").getId());
    }

    /**
     * Test 5: Se encarga de verify el m�todo findTrainById de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * findTrainById <br>
     * getTrainStops <br>
     * <b> Test cases: </b> <br>
     * 1. No hay trenes en el sistema. <br>
     * 2. No existe un tren con el id dado. <br>
     * 3. Se encuentra el tren deseado.
     */
    @Test
    public void testBuscarTrenPorId() {
        // Caso de prueba 1.
        assertNull("No deber�a encontrar un tren.", cupiTrenes.findTrainById(1));

        // Caso de prueba 2.
        setupScenario2();
        assertNull("No deber�a encontrar un tren.", cupiTrenes.findTrainById(40));
        assertNull("No deber�a encontrar un tren.", cupiTrenes.findTrainById(5));

        // Caso de prueba 3.
        assertNotNull("Deber�a encontrar un tren.", cupiTrenes.findTrainById(35));
        assertEquals("No encontr� el tren deseado.", 35, cupiTrenes.findTrainById(35).getId());
        assertNotNull("Deber�a encontrar un tren.", cupiTrenes.findTrainById(50));
        assertEquals("No encontr� el tren deseado.", 50, cupiTrenes.findTrainById(50).getId());
    }

    /**
     * Test 6: Se encarga de verify el m�todo addNewTrain de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * addNewTrain <br>
     * darPrimeraTren <br>
     * <b> Test cases: </b> <br>
     * 1. No hay ninguna ruta. <br>
     * 2. El tren se agrega antes de la ruta actual. <br>
     * 3. El tren se agrega en la mitad. <br>
     * 4. El tren se debe agregar al final.
     */
    @Test
    public void testAgregarTren1() {
        String[] trainStops = {"Roma", "Berlin", "Londres"};
        Date[] arrivalTimes1 = new Date[3];
        for (int i = 10; i < 13; i++) {
            Date arrivalTime = new Date();
            arrivalTime.setHours(i);
            arrivalTime.setMinutes(i);
            arrivalTimes1[i - 10] = arrivalTime;
        }
        Date[] arrivalTimes2 = new Date[3];
        for (int i = 1; i < 4; i++) {
            Date arrivalTime = new Date();
            arrivalTime.setHours(i);
            arrivalTime.setMinutes(i);
            arrivalTimes2[i - 1] = arrivalTime;
        }
        Date[] arrivalTimes3 = new Date[3];
        for (int i = 11; i < 14; i++) {
            Date arrivalTime = new Date();
            arrivalTime.setHours(i);
            arrivalTime.setMinutes(i);
            arrivalTimes3[i - 11] = arrivalTime;
        }
        Date[] arrivalTimes4 = new Date[3];
        for (int i = 9; i < 12; i++) {
            Date arrivalTime = new Date();
            arrivalTime.setHours(i);
            arrivalTime.setMinutes(i);
            arrivalTimes4[i - 9] = arrivalTime;
        }
        try {
            // Caso de prueba 1.
            cupiTrenes.addNewTrain(35, trainStops, arrivalTimes1);
            assertEquals("No agrega el tren correctamente.", 35,
                         cupiTrenes.getFirstTrain().getId());
            // Caso de prueba 2.
            cupiTrenes.addNewTrain(40, trainStops, arrivalTimes2);

            cupiTrenes.isEarlier(cupiTrenes.getFirstTrain().getDepartureTime(),
                                 cupiTrenes.getFirstTrain().getNextTrain().getDepartureTime());
            assertEquals("No agrega el tren correctamente.", 40,
                         cupiTrenes.getFirstTrain().getId());
            assertEquals("No agrega el tren correctamente.", 35,
                         cupiTrenes.getFirstTrain().getNextTrain().getId());

            // Caso de prueba 3.
            cupiTrenes.addNewTrain(15, trainStops, arrivalTimes4);
            assertEquals("No agrega el tren correctamente.", 40,
                         cupiTrenes.getFirstTrain().getId());
            assertEquals("No agrega el tren correctamente.", 15,
                         cupiTrenes.getFirstTrain().getNextTrain().getId());
            assertEquals("No agrega el tren correctamente.", 35,
                         cupiTrenes.getFirstTrain().getNextTrain().getNextTrain().getId());

            // Caso de prueba 4.
            cupiTrenes.addNewTrain(20, trainStops, arrivalTimes3);
            assertEquals("No agrega el tren correctamente.", 40,
                         cupiTrenes.getFirstTrain().getId());
            assertEquals("No agrega el tren correctamente.", 15,
                         cupiTrenes.getFirstTrain().getNextTrain().getId());
            assertEquals("No agrega el tren correctamente.", 35,
                         cupiTrenes.getFirstTrain().getNextTrain().getNextTrain().getId());
            assertEquals("No agrega el tren correctamente.", 20,
                         cupiTrenes.getFirstTrain().getNextTrain().getNextTrain().getNextTrain()
                                   .getId());
        } catch (ElementExistsException e) {
            fail("No deber�a generar excepci�n.");
        }
    }

    /**
     * Test 7: Se encarga de verify el m�todo addNewTrain de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * addNewTrain <br>
     * <b> Test cases: </b> <br>
     * 1. Se intenta agregar un tren ya existente.
     */
    @Test
    public void testAgregarTren2() {

        String[] trainStops = {"Roma", "Berlin", "Londres"};
        Date[] arrivalTimes1 = new Date[3];
        for (int i = 10; i < 13; i++) {
            Date arrivalTime = new Date();
            arrivalTime.setHours(i);
            arrivalTime.setMinutes(i);
            arrivalTimes1[i - 10] = arrivalTime;
        }

        Date[] arrivalTimes2 = new Date[3];
        for (int i = 1; i < 4; i++) {
            Date arrivalTime = new Date();
            arrivalTime.setHours(i);
            arrivalTime.setMinutes(i);
            arrivalTimes2[i - 1] = arrivalTime;
        }
        try {
            cupiTrenes.addNewTrain(35, trainStops, arrivalTimes1);
            cupiTrenes.addNewTrain(35, trainStops, arrivalTimes2);
            fail("Deber�a generar excepci�n. Ya existe un tren con ese id.");
        } catch (ElementExistsException e) {
            // Debe generar error.
        }
    }

    /**
     * Test 8: Se encarga de verify el m�todo eliminarTren de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * eliminarTren <br>
     * <b> Test cases: </b> <br>
     * 1. Elimina un tren no existente.
     */
    @Test
    public void testEliminarTren1() {
        setupScenario2();
        try {
            cupiTrenes.eliminateTrain(60);
            fail("Deber�a generar Excepci�n. No existe un tren con ese id.");
        } catch (ElementDoesNotExistException e) {
            // Debe generar error.
        }
    }

    /**
     * Test 9: Se encarga de verify el m�todo eliminarTren de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * addNewTrain <br>
     * eliminarTren <br>
     * darPrimeraTren <br>
     * <b> Test cases: </b> <br>
     * 1. Elimina el primer tren. <br>
     * 2. Elimina un tren intermedio. <br>
     * 3. Elimina el �ltimo tren.
     */
    @Test
    public void testEliminarTren2() {
        setupScenario2();
        try {
            String[] trainStops = {"Roma", "Berlin", "Londres"};
            Date[] arrivalTimes = new Date[3];
            for (int i = 10; i < 13; i++) {
                Date arrivalTime = new Date();
                arrivalTime.setHours(i);
                arrivalTime.setMinutes(i);
                arrivalTimes[i - 10] = arrivalTime;
            }
            cupiTrenes.addNewTrain(40, trainStops, arrivalTimes);

            Date[] arrivalTimes2 = new Date[3];
            for (int i = 9; i < 12; i++) {
                Date arrivalTime = new Date();
                arrivalTime.setHours(i);
                arrivalTime.setMinutes(i);
                arrivalTimes[i - 9] = arrivalTime;
            }
            cupiTrenes.addNewTrain(15, trainStops, arrivalTimes);

            // Caso de prueba 1.
            cupiTrenes.eliminateTrain(35);
            assertEquals("No elimin� el tren correctamente.", 50,
                         cupiTrenes.getFirstTrain().getId());
            assertNull("El tren no deber�a existir.", cupiTrenes.findTrainById(35));

            // Caso de prueba 2.
            cupiTrenes.eliminateTrain(15);
            assertEquals("No elimin� el tren correctamente.", 40,
                         cupiTrenes.getFirstTrain().getNextTrain().getId());
            assertNull("El tren no deber�a existir.", cupiTrenes.findTrainById(15));

            // Caso de prueba 3.
            cupiTrenes.eliminateTrain(40);
            assertNull("El tren no deber�a existir.", cupiTrenes.findTrainById(40));
            assertNull("No elimin� el tren correctamente.",
                       cupiTrenes.getFirstTrain().getNextTrain());
        } catch (Exception e) {
            e.printStackTrace();
            fail("No deber�a generar excepci�n.");
        }
    }

    /**
     * Test 10: Se encarga de verify el m�todo eliminateWagon de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * eliminateWagon <br>
     * <b> Caso de prueba: </b> <br>
     * 1. Se intenta eliminar un wagon de un tren que no existe. <br>
     * 2. Se intenta eliminar un wagon no existente de una ruta existente. <br>
     */
    @Test
    public void testEliminarWagon1() {
        setupScenario3();
        try {
            // Caso de prueba 1.
            cupiTrenes.eliminateWagon(60, 4);
            fail("Deber�a generar Excepci�n. No existe un tren con ese n�mero.");

        } catch (ElementDoesNotExistException e) {
            // Debe generar error.
        }
        try {
            // Caso de prueba 2.
            cupiTrenes.eliminateWagon(35, 3);
            fail("Deber�a generar Excepci�n. No existe un wagon con ese n�mero.");
        } catch (ElementDoesNotExistException e) {
            // Debe generar error.
        }

    }

    /**
     * Test 11. Se encarga de verify el m�todo eliminateWagon de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * eliminateWagon <br>
     * getFirstTrain <br>
     * <b> Test cases: </b> <br>
     * 1. Se elimina el wagon correctamente.
     */
    @Test
    public void testEliminarWagon2() {
        setupScenario3();
        try {
            cupiTrenes.eliminateWagon(35, 4);
            assertEquals("No elimina el wagon correctamente.", 5,
                         cupiTrenes.getFirstTrain().getFirstWagon().getNumber());
        } catch (ElementDoesNotExistException e) {
            fail("No deber�a generar error.");
        }
    }

    /**
     * Test 12: Se encarga de verify el m�todo addWagon de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * addWagon <br>
     * findTrainById <br>
     * <b> Test cases: </b> <br>
     * 1. El tren no existe. <br>
     * 2. El tren ya existe, pero ya tiene un wagon con el n�mero dado.
     */
    @Test
    public void testAgregarWagon1() {
        setupScenario2();
        try {
            // Caso de prueba 1.
            cupiTrenes.addWagon(46, 8, 10, Wagon.CLASSES[0], 40.0);
            fail("Deber�a generar excepci�n. El id del tren no existe.");
        } catch (ElementExistsException e) {
            // Deber�a generar Excepci�n.
        } catch (ElementDoesNotExistException e) {
            // Deber�a generar Excepci�n.
        }

        try {
            // Caso de prueba 2.
            cupiTrenes.addWagon(35, 2, 20, Wagon.CLASSES[1], 50.0);
            cupiTrenes.addWagon(35, 2, 20, Wagon.CLASSES[1], 35.0);
            fail("Deber�a generar excepci�n. Ya existe un wagon con ese n�mero");
        } catch (ElementExistsException e) {
            // Deber�a generar Excepci�n.
        } catch (ElementDoesNotExistException e) {
            // Deber�a generar Excepci�n.
        }
    }

    /**
     * Test 13: Se encarga de verify el m�todo addWagon de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * addWagon <br>
     * findTrainById <br>
     * <b> Test cases: </b> <br>
     * 1. Se agrega el wagon exitosamente.
     */
    @Test
    public void testAgregarWagon2() {
        setupScenario2();
        try {
            // Vag�n en first wagonClass.
            cupiTrenes.addWagon(50, 4, 50, Wagon.CLASSES[0], 50.0);
            cupiTrenes.addWagon(50, 6, 10, Wagon.CLASSES[1], 30.0);

            Wagon wagon = cupiTrenes.findTrainById(50).getFirstWagon();
            assertEquals("No agreg� el wagon correctamente.", 4, wagon.getNumber());
            assertEquals("No agreg� el wagon correctamente.", 50.0, wagon.getPrice(), 0.0);
            assertEquals("No agreg� el wagon correctamente.", 50,
                         wagon.getTotalAmountOfAvailableSeats());
            assertEquals("No agreg� el wagon correctamente.", Wagon.CLASSES[0],
                         wagon.getWagonClass());

            wagon = wagon.getNextWagon();
            assertEquals("No agreg� el wagon correctamente.", 6, wagon.getNumber());

            wagon = wagon.getNextWagon();
            assertNull("El siguiente deber�a ser nulo.", wagon);
        } catch (ElementExistsException e) {
            fail("No deber�a generar error.");
        } catch (ElementDoesNotExistException e) {
            fail("No deber�a generar error.");
        }

    }

    /**
     * Test 14: Se encarga de verify el m�todo sellTicket de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * sellTicket <br>
     * getTotalMoneyCollected <br>
     * getTotalAvailableChairs <br>
     * <b> Test cases: </b> <br>
     * 1. Los tiquetes se venden exitosamente. <br>
     * 2. Se venden tiquetes cuando ya no hay sillas disponibles.
     */
    @Test
    public void testSellTicket1() {
        setupScenario3();
        try {
            // Caso de prueba 1.
            assertTrue("Deber�a vender el tiquete.", cupiTrenes.sellTicket(35, 4));
            assertEquals("No vende el tiquete correctamente.", 50.0,
                         cupiTrenes.getTotalMoneyCollected(), 0.01);
            assertEquals("No vende el tiquete correctamente.", 44,
                         cupiTrenes.getTotalAvailableChairs());

            assertTrue("Deber�a vender el tiquete.", cupiTrenes.sellTicket(35, 5));
            assertEquals("No vende el tiquete correctamente.", 150.0,
                         cupiTrenes.getTotalMoneyCollected(), 0.01);
            assertEquals("No vende el tiquete correctamente.", 43,
                         cupiTrenes.getTotalAvailableChairs());
            assertTrue("Deber�a vender el tiquete.", cupiTrenes.sellTicket(35, 4));
            assertTrue("Deber�a vender el tiquete.", cupiTrenes.sellTicket(35, 4));
            assertTrue("Deber�a vender el tiquete.", cupiTrenes.sellTicket(35, 4));

            // Caso de prueba 2.
            assertTrue("Deber�a vender el tiquete.", cupiTrenes.sellTicket(35, 4));
            assertFalse("No deber�a vender el tiquete. No hay sillas disponibles.",
                        cupiTrenes.sellTicket(35, 4));
        } catch (ElementDoesNotExistException e) {
            fail("No deber�a generar error.");
        }
    }

    /**
     * Test 15: Se encarga de verify el m�todo sellTicket de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * sellTicket <br>
     * <b> Casos de Test: </b> <br>
     * 1. Intenta vender tiquetes de un wagon no existente. <br>
     * 2. Intenta vender tiquetes de un tren no existente.
     */
    @Test
    public void testSellTicket2() {
        setupScenario3();
        try {
            // Caso de Test 1.
            assertTrue(cupiTrenes.sellTicket(35, 8));
            fail("Deber�a generar error. No existe el wagon.");
        } catch (ElementDoesNotExistException e) {
            // Deber�a generar error.
        }
        try {
            // Caso de Test 2.
            assertTrue(cupiTrenes.sellTicket(27, 4));
            fail("Deber�a generar error. No existe la ruta.");
        } catch (ElementDoesNotExistException e) {
            // Deber�a generar error.
        }
    }

    /**
     * Test 16: Se encarga de verify el m�todo saveState de la wagonClass. <br>
     * <b> Methods to test: </b> <br>
     * saveState <br>
     * agregarRuta <br>
     * addWagon <br>
     * cambiarRutaArchivo <br>
     * CupiTrains <br>
     * getFirstTrain <br>
     * <b> Casos de Test: </b> <br>
     * 1. Se guarda el estado de CupiTrains.
     */
    @Test
    public void testGuardar() {
        String[] trainStops = {"Roma", "Berlin", "Londres"};
        Date[] arrivalTimes = new Date[3];
        for (int i = 0; i < 3; i++) {
            Date arrivalTime = new Date();
            arrivalTime.setHours(i);
            arrivalTime.setMinutes(i * 5);
            arrivalTimes[i] = arrivalTime;
        }
        try {
            String nuevaRuta = RUTA_ARCHIVO + "3.data";
            cupiTrenes.addNewTrain(20, trainStops, arrivalTimes);
            cupiTrenes.addWagon(20, 3, 50, Wagon.CLASSES[0], 100);

            cupiTrenes.saveState(nuevaRuta);

            cupiTrenes = new CupiTrains(nuevaRuta);
            assertEquals("No guard� correctamente los trenes.", 20,
                         cupiTrenes.getFirstTrain().getId());
            assertEquals("No guard� correctamente los vagones.", 3,
                         cupiTrenes.getFirstTrain().getFirstWagon().getNumber());
        } catch (ElementExistsException e) {
            fail("No deber�a generar Excepci�n.");
        } catch (ElementDoesNotExistException e) {
            fail("No deber�a generar Excepci�n.");
        } catch (PersistenceException e) {
            fail("No deber�a generar Excepci�n.");
        }

    }
}