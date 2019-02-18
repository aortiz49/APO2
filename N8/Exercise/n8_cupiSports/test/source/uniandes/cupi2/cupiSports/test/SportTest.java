/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiSports
 * Autor: Equipo Cupi2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiSports.test;


import uniandes.cupi2.cupiSports.world.Sport;
import uniandes.cupi2.cupiSports.world.Athlete;
import uniandes.cupi2.cupiSports.mundo.ElementExistsException;


import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Clase usada para verificar que los m�todos de la clase Sport est�n correctamente implementados.
 */
public class SportTest
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private Sport deporte;

    /**
     * Athlete del deporte.
     */
    private Athlete deportista1;

    /**
     * Deprtista del deporte.
     */
    private Athlete deportista2;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Sport.
     */
    @Before
    public void setupEscenario1( )
    {
        deporte = new Sport( "F�tbol", "FCF", 2300, "imagen" );
    }

    /**
     * Construye un nuevo deporte, dos deportistas y se agrega uno.
     */
    private void setupEscenario2( )
    {
        try
        {
            deporte = new Sport( "F�tbol", "FCF", 2300, "imagen" );
            deportista1 = new Athlete( "James", 23, "Madrid", 39, "imagen2" );
            deportista2 = new Athlete( "Falcao", 29, "Manchester", 35, "imagen3" );
            deporte.addOutstandingAthlete( deportista1 );
        }
        catch( ElementExistsException e )
        {
            fail( "No deber�a generar una excepci�n" );
        }
    }

    /**
     * Construye un nuevo deporte, dos deportistas y se agregan ambos.
     */
    private void setupEscenario3( )
    {
        try
        {
            deporte = new Sport( "F�tbol", "FCF", 2300, "imagen" );
            deportista1 = new Athlete( "James", 23, "Madrid", 39, "imagen2" );
            deportista2 = new Athlete( "Falcao", 29, "Manchester", 35, "imagen3" );
            deporte.addOutstandingAthlete( deportista1 );
            deporte.addOutstandingAthlete( deportista2 );
        }
        catch( ElementExistsException e )
        {
            fail( "No deber�a generar una excepci�n" );
        }
    }

    /**
     * Prueba 1: Verifica el m�todo constructor.<br>
     * <b> Methods a probar: </b> <br>
     * constructor getName<br>
     * getRegulatoryEntity<br>
     * getNumberOfRegisteredAthletes<br>
     * getImagePath<br>
     * getOutstandingAthletes <br>
     * <b> Objetivo: </b> Probar inicializaci�n correcta del objeto Sport<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Inicializaci�n correcta de Sport<br>
     */
    @Test
    public void testConstructor( )
    {
        assertEquals( "El name del deporte es incorrecto.", "F�tbol", deporte.getName( ) );
        assertEquals( "El ente regulador del deporte es incorrecto.", "FCF", deporte.getRegulatoryEntity( ) );
        assertEquals( "La fecha cantidad de deportistas registrados del deporte es incorrecta.", 2300, deporte.getNumberOfRegisteredAthletes( ) );
        assertEquals( "La ruta de imagen del deporte es incorrecta.", "imagen", deporte.getImagePath( ) );
        assertNotNull( "La lista de deportistas es nula.", deporte.getOutstandingAthletes( ) );
        assertEquals( "La lista de deportistas no es vac�a.", 0, deporte.getOutstandingAthletes( ).size( ) );
    }

    /**
     * Prueba 2: Verifica el m�todo outstandingAthleteExists.<br>
     * <b> Methods a probar: </b> <br>
     * outstandingAthleteExists. <br>
     * <b> Objetivo: </b> Probar funcionamiento correcto de outstandingAthleteExists<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Resulado obtenido correcto de outstandingAthleteExists<br>
     */
    @Test
    public void testExisteAthleteSobresaliente( )
    {
        setupEscenario2( );
        assertTrue( "El deportista deber�a existir.", deporte.outstandingAthleteExists( deportista1.getName( ) ) );
        assertFalse( "El deportista no deber�a existir.", deporte.outstandingAthleteExists( deportista2.getName( ) ) );
    }

    /**
     * Prueba 3: Verifica el m�todo addOutstandingAthlete.<br>
     * <b> Methods a probar: </b> <br>
     * addOutstandingAthlete. <br>
     * outstandingAthleteExists.<br>
     * getOutstandingAthletes.<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto de addOutstandingAthlete<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Agrega correctamente un deportista.<br>
     */
    @Test
    public void testAgregarAthleteSobresalienteOK( )
    {
    	//TODO Parte 4 punto B: Implemente la prueba

    }

    /**
     * Prueba 4: Verifica el m�todo addOutstandingAthlete.<br>
     * <b> Methods a probar: </b> <br>
     * addOutstandingAthlete.<br>
     * outstandingAthleteExists.<br>
     * getOutstandingAthletes.<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto de addOutstandingAthlete<br>
     * <b> Resultados esperados: </b> <br>
     * 2. No agrega un deportista porque lanza excepci�n.<br>
     */
    @Test
    public void testAgregarAthleteSobresalienteError( )
    {
    	//TODO Parte 4 punto C: Implemente la prueba
    }

    /**
     * Prueba 5: Verifica el m�todo eliminateOutstandingAthlete.<br>
     * <b> Methods a probar: </b> <br>
     * addOutstandingAthlete.<br>
     * outstandingAthleteExists.<br>
     * getOutstandingAthletes.<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto de eliminateOutstandingAthlete<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Elimina correctamente un deportista.<br>
     */
    @Test
    public void testEliminarAthleteSobresaliente( )
    {
        setupEscenario3( );
        deporte.eliminateOutstandingAthlete( "James" );
        assertEquals( "El tama�o de los deportistas deber�a ser 1.", 1, deporte.getOutstandingAthletes( ).size( ) );
        assertFalse( "No deber�a existir el deportista eliminado.", deporte.outstandingAthleteExists( "James" ) );
        deporte.eliminateOutstandingAthlete( "Falcao" );
        assertEquals( "El tama�o de los deportistas deber�a ser 0.", 0, deporte.getOutstandingAthletes( ).size( ) );
        assertFalse( "No deber�a existir el deportista eliminado.", deporte.outstandingAthleteExists( "Falcao" ) );
    }

    /**
     * Prueba 6: Verifica el m�todo getAthleteMostTrophies.<br>
     * <b> Methods a probar: </b> <br>
     * getAthleteMostTrophies.<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto de getAthleteMostTrophies<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Cuando no hay deportistas retorna null.<br>
     * 2. Cuando hay un deportista. 3. Cuando hay m�s de un deportista.
     */
    @Test
    public void testDarAthleteMasTrophies( )
    {
        // 1
        assertNull( "Deber�a ser nulo.", deporte.getAthleteMostTrophies( ) );

        // 2
        setupEscenario2( );
        assertNotNull( "No deber�a ser nulo.", deporte.getAthleteMostTrophies( ) );
        assertEquals( "El deportista con m�s trofeos no corresponde.", "James", deporte.getAthleteMostTrophies( ).getName( ) );

        // 3
        setupEscenario2( );
        assertNotNull( "No deber�a ser nulo.", deporte.getAthleteMostTrophies( ) );
        assertEquals( "El deportista con m�s trofeos no corresponde.", "James", deporte.getAthleteMostTrophies( ).getName( ) );

    }

    /**
     * Prueba 7: Verifica el m�todo getTotalTrophies.<br>
     * <b> Methods a probar: </b> <br>
     * getTotalTrophies.<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto de getTotalTrophies<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Cuando no hay deportistas retorna 0.<br>
     * 2. Cuando hay un deportista. 3. Cuando hay m�s de un deportista.
     */
    @Test
    public void testDarTotalTrophies( )
    {
        // 1
        assertEquals( "El n�mero total de trofeos no corresponde.", 0, deporte.getTotalTrophies( ) );

        // 2
        setupEscenario2( );
        assertEquals( "El n�mero total de trofeos no corresponde.", 39, deporte.getTotalTrophies( ) );

        // 3
        setupEscenario3( );
        assertEquals( "El n�mero total de trofeos no corresponde.", 74, deporte.getTotalTrophies( ) );

    }

}