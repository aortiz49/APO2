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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import uniandes.cupi2.cupiSports.world.Sport;
import uniandes.cupi2.cupiSports.mundo.ElementExistsException;
import uniandes.cupi2.cupiSports.mundo.FormatoArchivoException;
import uniandes.cupi2.cupiSports.world.CupiSports;
import uniandes.cupi2.cupiSports.world.Athlete;
import uniandes.cupi2.cupiSports.mundo.PersistenciaException;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Clase usada para verificar que los m�todos de la clase CupiSports est�n correctamente implementados.
 */
public class CupiSportsTest{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Ruta para cargar el archivo de prueba.
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
     * Construye un nuevo sistema CupiSports con 2 deportes y 2 deportistas. <br>
     * deporte1: F�tbol, FCF, 2500, futbol.jpg. <br>
     * deportista1: James, 23, Madrid, 39, jamesRodriguez.jpg.<br>
     * deporte2: Tenis, FCT, 110, tenis.jpg. <br>
     * deportista2: Alejandro, 31, Cali, 11, alejandroFalla.jpg.
     */
    @Before
    public void setupEscenario1( )
    {
        try
        {
            cupiSports = new CupiSports( RUTA_PRUEBA );
        }
        catch( PersistenciaException e )
        {
            fail( "Hay errores al cargar el archivo de CupiSports." );
        }
    }

    /**
     * Construye una nuevo sistema CupiSports vac�o.
     */
    private void setupEscenario2( )
    {
        try
        {
            cupiSports = new CupiSports( "" );
        }
        catch( PersistenciaException e )
        {
            fail( "Hay errores al cargar el archivo de CupiSports." );
        }
    }

    /**
     * Prueba 1: Verifica el m�todo constructor.<br>
     * <b> Methods a probar: </b> <br>
     * constructor getSports <br>
     * <b> Objetivo: </b> Probar inicializaci�n correcta del objeto CupiSports<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Inicializaci�n correcta de CupiSports<br>
     */
    @Test
    public void testConstructorCupiSports( )
    {
        
        ArrayList<Sport> listaSports = cupiSports.getSports( );
        assertNotNull( "No deber�a ser nula la lista.", listaSports );
        assertEquals( "El tama�o de la lista no corresponde.", 2, listaSports.size( ) );

        // El primer deporte
        Sport primero = ( Sport )listaSports.get( 0 );
        assertEquals( "El primer deporte no corresponde.", "F�tbol", primero.getName( ) );
        ArrayList<Athlete> deportistasPrimero = primero.getOutstandingAthletes( );
        Athlete primerAthlete = ( Athlete )deportistasPrimero.get( 0 );
        assertEquals( "El primer deportista no corresponde.", "James", primerAthlete.getName( ) );

        // El segundo deporte
        Sport segundo = ( Sport )listaSports.get( 1 );
        assertEquals( "El segundo deporte no corresponde.", "Tenis", segundo.getName( ) );
        ArrayList<Athlete> deportistasSegundo = segundo.getOutstandingAthletes( );
        Athlete segundoAthlete = ( Athlete )deportistasSegundo.get( 0 );
        assertEquals( "El segundo deportista no corresponde.", "Alejandro", segundoAthlete.getName( ) );

    }

    /**
     * Prueba 2: Verifica el m�todo existsSport.<br>
     * <b> Methods a probar: </b> <br>
     * existsSport <br>
     * <b> Objetivo: </b> Probar funcionamiento correcto existsSport<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Existe el deporte.<br>
     * 2. No exists el deporte.<br>
     */
    @Test
    public void testExisteSport( )
    {
        
        // 1
        assertTrue( "Deber�a existir el deporte.", cupiSports.existsSport( "F�tbol" ) );

        // 2
        assertFalse( "No deber�a existir el deporte.", cupiSports.existsSport( "Basket" ) );
    }

    /**
     * Prueba 3: Verifica el m�todo agregarSport.<br>
     * <b> Methods a probar: </b> <br>
     * agregarSport<br>
     * existsSport<br>
     * getSports<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto agregarSport<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Agrega un deporte nuevo.<br>
     */
    @Test
    public void testAgregarSportOK( )
    {
    	//TODO Parte 4 punto E: Implemente el m�todo seg�n la documentaci�n.  
    }

    /**
     * Prueba 4: Verifica el m�todo agregarSport.<br>
     * <b> Methods a probar: </b> <br>
     * agregarSport<br>
     * getSports<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto agregarSport al lanzar excepci�n<br>
     * <b> Resultados esperados: </b> <br>
     * 1. No agrega, dado que ya exist�a un deporte con el name dado.<br>
     */
    @Test
    public void testAgregarSportError( )
    {
    	//TODO Parte 4 punto F: Implemente el m�todo seg�n la documentaci�n.  
    }

    /**
     * Prueba 5: Verifica el m�todo eliminarSport.<br>
     * <b> Methods a probar: </b> <br>
     * eliminarSport<br>
     * existsSport<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto eliminarSport<br>
     * <b> Resultados esperados: </b> <br>
     * 1. No exists el deporte.<br>
     */
    @Test
    public void testEliminarSport( )
    {
        
        cupiSports.eliminarSport( "F�tbol" );
        assertEquals( "El tama�o de la lista de deportes deber�a ser 1.", 1, cupiSports.getSports( ).size( ) );
        assertFalse( "No deber�a existir el deporte.", cupiSports.existsSport( "F�tbol" ) );
    }

    /**
     * Prueba 6: Verifica el m�todo agregarAthlete.<br>
     * <b> Methods a probar: </b> <br>
     * agregarAthlete<br>
     * getSports<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto agregarAthlete<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Agrega el deportista.<br>
     */
    @Test
    public void testAgregarAthleteOK( )
    {
        // 1
        try
        {
            cupiSports.addOutstandingAthlete( "F�tbol", "Falcao", 29, "Manchester", 35, "falcao.jpg" );
            Sport primero = ( Sport )cupiSports.getSports( ).get( 0 );
            assertEquals( "El tama�o de los deportistas no coincide.", 2, primero.getOutstandingAthletes( ).size( ) );
            Athlete nuevo = ( Athlete )primero.getOutstandingAthletes( ).get( 1 );
            assertEquals( "El name of the athlete nuevo no coincide.", "Falcao", nuevo.getName( ) );
        }
        catch( ElementExistsException e )
        {
            fail( "No deber�a lanzar una excepci�n al agregar el deportista." );
        }

        // 2
        try
        {
            cupiSports.addOutstandingAthlete( "F�tbol", "Falcao", 29, "Manchester", 35, "falcao.jpg" );
            fail( "Deber�a lanzar una excepci�n al agregar el deportista." );
        }
        catch( ElementExistsException e )
        {
            Sport primero = ( Sport )cupiSports.getSports( ).get( 0 );
            assertEquals( "El tama�o de los deportistas no coincide.", 2, primero.getOutstandingAthletes( ).size( ) );
        }
    }

    /**
     * Prueba 7: Verifica el m�todo agregarAthlete.<br>
     * <b> Methods a probar: </b> <br>
     * agregarAthlete<br>
     * getSports<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto agregarAthlete<br>
     * <b> Resultados esperados: </b> <br>
     * 1. No agrega el deportista porque lanza excepci�n.<br>
     */
    @Test
    public void testAgregarAthleteError( )
    {
        
        // 2
        try
        {
            cupiSports.addOutstandingAthlete( "F�tbol", "James", 23, "Madrid", 39, "james.jpg" );
            fail( "Deber�a lanzar una excepci�n al agregar el deportista." );
        }
        catch( ElementExistsException e )
        {
            Sport primero = ( Sport )cupiSports.getSports( ).get( 0 );
            assertEquals( "El tama�o de los deportistas no coincide.", 1, primero.getOutstandingAthletes( ).size( ) );
        }
    }

    /**
     * Prueba 8: Verifica el m�todo eliminarAthlete.<br>
     * <b> Methods a probar: </b> <br>
     * eliminarAthlete<br>
     * getSports<br>
     * <b> Objetivo: </b> Probar funcionamiento correcto eliminarAthlete<br>
     * <b> Resultados esperados: </b> <br>
     * 1. No exists el deportista.<br>
     */
    @Test
    public void testEliminarAthlete( )
    {
        
        cupiSports.eliminateOutstandingAthlete( "F�tbol", "Falcao" );
        Sport primero = ( Sport )cupiSports.getSports( ).get( 0 );
        assertEquals( "El tama�o de los deportistas no coincide.", 1, primero.getOutstandingAthletes( ).size( ) );

    }

    /**
     * Prueba 9: Verifica el m�todo getAthleteMostTrophies.<br>
     * <b> Methods a probar: </b> <br>
     * getAthleteMostTrophies <br>
     * <b> Objetivo: </b> Probar funcionamiento correcto getAthleteMostTrophies<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Cuando hay deportistas retorna el deportista con mas trofeos.<br>
     * 2. Cuando no hay deportistas retorna null. <br>
     */
    @Test
    public void testDarAthleteMasTrophies( )
    {
        // 1
        
        assertNotNull( "No deber�a ser nulo.", cupiSports.getAthleteMostTrophies( ) );
        assertEquals( "El name of the athlete con m�s trofeos no corresponde.", "James", cupiSports.getAthleteMostTrophies( ).getName( ) );

        // 2
        setupEscenario2( );
        assertNull( "Deber�a ser nulo.", cupiSports.getAthleteMostTrophies( ) );
    }

    /**
     * Prueba 10: Verifica el m�todo getAthleteMostTrophies.<br>
     * <b> Methods a probar: </b> <br>
     * getAthleteMostTrophies <br>
     * <b> Objetivo: </b> Probar funcionamiento correcto getAthleteMostTrophies<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Cuando hay 2 deportistas que tienen igual n�mero de trofeos y son m�ximos, retorna cualquiera.<br>
     */
    @Test
    public void testDarAthleteMasTrophies2( )
    {
        // 1
        
        try
        {
            cupiSports.addOutstandingAthlete( "F�tbol", "Falcao", 39, "Manchester", 35, "falcao.jpg" );
        }
        catch( ElementExistsException e )
        {
            fail( "No deber�a generar excepci�n." );
        }

        assertNotNull( "No deber�a ser nulo.", cupiSports.getAthleteMostTrophies( ) );
        assertTrue( "El name of the athlete con m�s trofeos no corresponde.", cupiSports.getAthleteMostTrophies( ).getName( ).equals( "James" ) || cupiSports.getAthleteMostTrophies( ).getName( ).equals( "Falcao" ) );

    }

    /**
     * Prueba 11: Verifica el m�todo getTotalTrophies.<br>
     * <b> Methods a probar: </b> <br>
     * getTotalTrophies <br>
     * <b> Objetivo: </b> Probar funcionamiento correcto getTotalTrophies<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Cuando hay deportistas retorna la suma de todos los trofeos.<br>
     * 2. Cuando no hay deportistas retorna 0. <br>
     */
    @Test
    public void testDarTotalTrophies( )
    {
        // 1
        
        assertEquals( "El n�mero total de trofeos no es el esperado.", 50, cupiSports.getTotalTrophies( ) );

        // 2
        setupEscenario2( );
        assertEquals( "El n�mero total de trofeos no es el esperado.", 0, cupiSports.getTotalTrophies( ) );

    }

    /**
     * Prueba 12: Verifica el m�todo gua.get.<br>
     * <b> Methods a probar: </b> <br>
     * gua.get <br>
     * constructor <br>
     * <b> Objetivo: </b> Probar funcionamiento correcto gua.get<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al gua.get, carga correctamente.<br>
     */
    @Test
    public void testGua.get( )
    {
        
        try
        {
            cupiSports.gua.get( RUTA_PRUEBA );
            CupiSports temp = new CupiSports( RUTA_PRUEBA );
            assertEquals( "No se guard� correctamente CupiSports.", 2, temp.getSports( ).size( ) );

        }
        catch( PersistenciaException e )
        {
            fail( "No se deber�a generar una excepci�n" );
        }
    }

    /**
     * Prueba 13: Verifica el m�todo actualizarInformacionAthletes.<br>
     * <b> Methods a probar: </b> <br>
     * actualizarInformacionAthletes <br>
     * getSports <br>
     * <b> Objetivo: </b> Probar funcionamiento correcto actualizarInformacionAthletes<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Cuando actualiza correctamente.<br>
     * 2. Cuando hay errores de formato. Cantidad de deportistas mal.<br>
     * 3. Cuando hay errores de formato. No hay datos de deportistas.<br>
     * 4. Cuando hay errores de formato. Falta un dato en el formato.<br>
     * 5. Cuando hay errores de formato. N�meros negativos.<br>
     */
    @Test
    public void testActualizarInformacionAthletes( )
    {
        // 1
        
        File pArchivo = new File( "./test/data/datosPruebaOK.txt" );
        try
        {
            cupiSports.actualizarInformacionAthletes( pArchivo );
            Sport primero = ( Sport )cupiSports.getSports( ).get( 0 );
            Sport segundo = ( Sport )cupiSports.getSports( ).get( 1 );
            Athlete deport1 = ( Athlete )primero.getOutstandingAthletes( ).get( 0 );
            Athlete deport2 = ( Athlete )segundo.getOutstandingAthletes( ).get( 0 );

            assertEquals( "El name del primer deportista no corresponde.", "James", deport1.getName( ) );
            assertEquals( "La age del primer deportista no corresponde.", 24, deport1.getAge( ) );
            assertEquals( "El lugar de residencia del primer deportista no corresponde.", "Madrid, Espa�a", deport1.getPlaceOfResidency( ) );
            assertEquals( "La cantidad de trofeos del primer deportista no corresponde.", 45, deport1.getAmountOfTrophies( ) );
            assertEquals( "La ruta de la imagen del primer deportista no corresponde.", "./data/imagenes/jamesRodriguez.jpg", deport1.getImagePath( ) );

            assertEquals( "El name del segundo deportista no corresponde.", "Alejandro", deport2.getName( ) );
            assertEquals( "La age del segundo deportista no corresponde.", 32, deport2.getAge( ) );
            assertEquals( "El lugar de residencia del segundo deportista no corresponde.", "Cali, Colombia", deport2.getPlaceOfResidency( ) );
            assertEquals( "La cantidad de trofeos del segundo deportista no corresponde.", 12, deport2.getAmountOfTrophies( ) );
            assertEquals( "La ruta de la imagen del segundo deportista no corresponde.", "./data/imagenes/alejandroFalla.jpg", deport2.getImagePath( ) );
        }
        catch( FileNotFoundException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }
        catch( IOException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }
        catch( FormatoArchivoException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }

        // 2
        
        File pArchivo2 = new File( "./test/data/datosPruebaError.txt" );
        try
        {
            cupiSports.actualizarInformacionAthletes( pArchivo2 );
            fail( "Deber�a lanzar excepci�n." );
        }
        catch( FileNotFoundException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }
        catch( IOException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }
        catch( FormatoArchivoException e )
        {
            // Deber�a estar aca.
        }

        // 3
        
        File pArchivo3 = new File( "./test/data/datosPruebaError2.txt" );
        try
        {
            cupiSports.actualizarInformacionAthletes( pArchivo3 );
            fail( "Deber�a lanzar excepci�n." );
        }
        catch( FileNotFoundException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }
        catch( IOException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }
        catch( FormatoArchivoException e )
        {
            // Deber�a estar aca.
        }

        // 4
        
        File pArchivo4 = new File( "./test/data/datosPruebaError3.txt" );
        try
        {
            cupiSports.actualizarInformacionAthletes( pArchivo4 );
            fail( "Deber�a lanzar excepci�n." );
        }
        catch( FileNotFoundException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }
        catch( IOException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }
        catch( FormatoArchivoException e )
        {
            // Deber�a estar aca.
        }

        // 5
        
        File pArchivo5 = new File( "./test/data/datosPruebaError4.txt" );
        try
        {
            cupiSports.actualizarInformacionAthletes( pArchivo5 );
            fail( "Deber�a lanzar excepci�n." );
        }
        catch( FileNotFoundException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }
        catch( IOException e )
        {
            fail( "No deber�a lanzar esta excepci�n." );
        }
        catch( FormatoArchivoException e )
        {
            // Deber�a estar aca.
        }
    }
}