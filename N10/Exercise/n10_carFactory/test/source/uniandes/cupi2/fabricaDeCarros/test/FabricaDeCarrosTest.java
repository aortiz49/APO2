/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: carFactory
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.carFactory.test;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import uniandes.cupi2.carFactory.world.*;
import uniandes.cupi2.carFactory.world.DelanteraCompacto;
import uniandes.cupi2.carFactory.world.CarFactory;
import uniandes.cupi2.carFactory.world.Rayo;
import uniandes.cupi2.carFactory.world.RinesDeLujo;
import uniandes.cupi2.carFactory.world.TraseraCompacto;

/**
 * Clase usada para verificar que los m�todos de la clase CarFactory est�n correctamente implementados.
 */
public class FabricaDeCarrosTest
{

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * La carFactory donde se har�n las pruebas.
     */
    private CarFactory fabricaDeCarros;

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * <b>Escenario 1 :</b>Crea una nueva f�brica de carros a partir de un archivo.
     */
    @Before
    public void setupEscenario1( )
    {

        fabricaDeCarros = new CarFactory( );

        try
        {
            fabricaDeCarros.openFile( "./test/data/test.dat" );

        }
        catch( Exception e )
        {
            fail( "No se pudo cargar el archivo: " + e.getMessage( ) + "." );
        }

    }

    /**
     * <b>Escenario 2 :</b>Crea una nueva f�brica de carros vac�a.
     */
    public void setupEscenario2( )
    {
        fabricaDeCarros = new CarFactory( );

    }

    /**
     * Prueba 1: Prueba el m�todo constructor de la f�brica. <br>
     * <b>Methods a probar:</b> <br>
     * CarFactory<br>
     * getParts<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye la f�brica y se verifica que cada uno de sus atributos se hayan inicializado correctamente.<br>
     */
    @Test
    public void testFabricaDeCarros( )
    {
        setupEscenario2( );
        assertNull( "Deber�a inicializar el nombre del archivo con null.", fabricaDeCarros.getFilePath( ) );
        Collection listaPartes = fabricaDeCarros.getParts( );
        assertNotNull( "No inicializ� la lista de parts.", listaPartes );
        assertTrue( "Deber�a inicializar una lista vacia.", listaPartes.isEmpty( ) );
    }

    /**
     * Prueba 2: Prueba el m�todo createPart de los chasis delanteros. <br>
     * <b>Methods a probar:</b> <br>
     * createPart<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se le pide a la f�brica que cree tres parts, se verifica que no sean nulas y que sus atributos est�n correctamente inicializados.<br>
     */
    @Test
    public void testCrearParte1( )
    {
        setupEscenario2( );
        int x = 255;
        int y = 300;
        Color colorCarro = Color.green;
        IPart p = fabricaDeCarros.createPart(TruckHood.TYPE, x, y, colorCarro );
        assertNotNull( "No se cre� el chasis, el objeto no deber�a ser nulo.", p );
        assertEquals("Error al inicializar tipo.", TruckHood.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorCarro, p.getColor( ) );

        x = 100;
        y = 400;
        colorCarro = Color.yellow;
        p = fabricaDeCarros.createPart( DelanteraCompacto.TYPE, x, y, colorCarro );
        assertNotNull( "No se cre� el chasis, el objeto no deber�a ser nulo.", p );
        assertEquals( "Error al inicializar tipo.", DelanteraCompacto.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorCarro, p.getColor( ) );

        x = 100;
        y = 400;
        colorCarro = Color.orange;
        p = fabricaDeCarros.createPart(SedanHood.TYPE, x, y, colorCarro );
        assertNotNull( "No se cre� el chasis, el objeto no deber�a ser nulo.", p );
        assertEquals("Error al inicializar tipo.", SedanHood.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorCarro, p.getColor( ) );

    }

    /**
     * Prueba 3: Prueba el m�todo createPart de los chasis traseros. <br>
     * <b>Methods a probar:</b> <br>
     * createPart<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se le pide a la f�brica que cree tres parts, se verifica que no sean nulas y que sus atributos est�n correctamente inicializados.<br>
     */
    @Test
    public void testCrearParte2( )
    {
        setupEscenario2( );
        int x = 0;
        int y = 40;
        Color colorCarro = Color.blue;
        IPart p = fabricaDeCarros.createPart(TruckTrunk.TYPE, x, y, colorCarro );
        assertNotNull( "No se cre� el chasis, el objeto no deber�a ser nulo.", p );
        assertEquals("Error al inicializar tipo.", TruckTrunk.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorCarro, p.getColor( ) );

        x = 20;
        y = 100;
        colorCarro = Color.cyan;
        p = fabricaDeCarros.createPart( TraseraCompacto.TYPE, x, y, colorCarro );
        assertNotNull( "No se cre� el chasis, el objeto no deber�a ser nulo.", p );
        assertEquals( "Error al inicializar tipo.", TraseraCompacto.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorCarro, p.getColor( ) );

        x = 300;
        y = 30;
        colorCarro = Color.magenta;
        p = fabricaDeCarros.createPart(SedanTrunk.TYPE, x, y, colorCarro );
        assertNotNull( "No se cre� el chasis, el objeto no deber�a ser nulo.", p );
        assertEquals("Error al inicializar tipo.", SedanTrunk.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorCarro, p.getColor( ) );
    }

    /**
     * Prueba 4: Prueba el m�todo createPart para los diferentes est�nciles. <br>
     * <b>Methods a probar:</b> <br>
     * createPart<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se le pide a la f�brica que cree tres parts de los diferentes est�nciles, se verifica que no sean nulas y que sus atributos est�n correctamente inicializados.<br>
     */
    @Test
    public void testCrearParte3( )
    {
        setupEscenario2( );
        int x = 500;
        int y = 125;
        Color colorCarro = Color.red;
        IPart p = fabricaDeCarros.createPart(Tesselation.TYPE, x, y, colorCarro );
        assertNotNull( "No se cre� el est�ncil, el objeto no deber�a ser nulo.", p );
        assertEquals("Error al inicializar tipo.", Tesselation.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorCarro, p.getColor( ) );

        x = 500;
        y = 125;
        colorCarro = Color.red;
        p = fabricaDeCarros.createPart(Skull.TYPE, x, y, colorCarro );
        assertNotNull( "No se cre� el est�ncil, el objeto no deber�a ser nulo.", p );
        assertEquals("Error al inicializar tipo.", Skull.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorCarro, p.getColor( ) );

        x = 10;
        y = 0;
        colorCarro = Color.pink;
        p = fabricaDeCarros.createPart( Rayo.TYPE, x, y, colorCarro );
        assertNotNull( "No se cre� el est�ncil, el objeto no deber�a ser nulo.", p );
        assertEquals( "Error al inicializar tipo.", Rayo.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorCarro, p.getColor( ) );
    }

    /**
     * Prueba 5: Prueba el m�todo createPart para los diferentes llantas. <br>
     * <b>Methods a probar:</b> <br>
     * createPart<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se le pide a la f�brica que cree tres parts de las diferentes llantas, se verifica que no sean nulas y que sus atributos est�n correctamente inicializados.<br>
     */
    @Test
    public void testCrearParte4( )
    {
        setupEscenario2( );
        int x = 700;
        int y = 300;
        Color colorParte = Color.orange;
        IPart p = fabricaDeCarros.createPart(MediumRims.TYPE, x, y, colorParte );
        assertNotNull( "No se cre� el llanta, el objeto no deber�a ser nulo.", p );
        assertEquals("Error al inicializar tipo.", MediumRims.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorParte, p.getColor( ) );

        x = 700;
        y = 300;
        colorParte = Color.white;
        p = fabricaDeCarros.createPart( RinesDeLujo.TYPE, x, y, colorParte );
        assertNotNull( "No se cre� el llanta, el objeto no deber�a ser nulo.", p );
        assertEquals( "Error al inicializar tipo.", RinesDeLujo.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x.", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y.", y, p.getY( ) );
        assertEquals( "Error al inicializar color.", colorParte, p.getColor( ) );

        x = 452;
        y = 321;
        colorParte = Color.gray;
        p = fabricaDeCarros.createPart(EconomyRims.TYPE, x, y, colorParte );
        assertNotNull( "No se cre� el llanta, el objeto no deber�a ser nulo.", p );
        assertEquals("Error al inicializar tipo", EconomyRims.TYPE, p.getType( ) );
        assertEquals( "Error al inicializar coordenada x", x, p.getX( ) );
        assertEquals( "Error al inicializar coordenada y", y, p.getY( ) );
        assertEquals( "Error al inicializar color", colorParte, p.getColor( ) );
    }

    /**
     * Prueba 6: Prueba el m�todo addPart para los diferentes chasis delanteros. <br>
     * <b>Methods a probar:</b> <br>
     * createPart<br>
     * addPart<br>
     * addPart<br>
     * <b> Caso de prueba 1 : <b><br>
     * Para cada chasis delantero, se crea una parte y se agrega. Despu�s se busca y se verifica que exista en la posici�n donde deber�a estar.<br>
     * Adem�s se verifica que la cantidad de parts sea la adecuada.<br>
     */
    @Test
    public void testAgregarParte1( )
    {
        setupEscenario2( );

        Part p1 = fabricaDeCarros.createPart(DelanteraCompacto.TYPE, 10, 10, Color.RED );
        fabricaDeCarros.addPart( p1 );
        DelanteraCompacto pa = ( DelanteraCompacto )fabricaDeCarros.findPart( 10, 10 );
        assertNotNull( "No se agreg� correctamente el chasis, la f�brica no encontr� el objeto en la posici�n dada.", pa );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 1 );

        p1 = fabricaDeCarros.createPart(SedanHood.TYPE, 100, 100, Color.RED );
        fabricaDeCarros.addPart( p1 );
        SedanHood pg = (SedanHood)fabricaDeCarros.findPart(100, 100 );
        assertNotNull( "No se agreg� correctamente el chasis, la f�brica no encontr� el objeto en la posici�n dada.", pg );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 2 );

        p1 = fabricaDeCarros.createPart(TruckHood.TYPE, 50, 50, Color.RED );
        fabricaDeCarros.addPart( p1 );
        TruckHood ten = (TruckHood)fabricaDeCarros.findPart(50, 50 );
        assertNotNull( "No se agreg� correctamente el chasis, la f�brica no encontr� el objeto en la posici�n dada.", ten );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 3 );
    }

    /**
     * Prueba 7: Prueba el m�todo addPart para los diferentes chasis traseros. <br>
     * <b>Methods a probar:</b> <br>
     * createPart<br>
     * addPart<br>
     * addPart<br>
     * <b> Caso de prueba 1 : <b><br>
     * Para cada chasis trasero, se crea una parte y se agrega. Despu�s se busca y se verifica que exista en la posici�n donde deber�a estar.<br>
     * Adem�s se verifica que la cantidad de parts sea la adecuada.<br>
     */
    @Test
    public void testAgregaParte2( )
    {
        setupEscenario2( );

        Part p1 = fabricaDeCarros.createPart(TruckTrunk.TYPE, 10, 10, Color.RED );
        fabricaDeCarros.addPart( p1 );
        TruckTrunk cb = (TruckTrunk)fabricaDeCarros.findPart(10, 10 );
        assertNotNull( "No se agreg� correctamente el chasis, la f�brica no encontr� el objeto en la posici�n dada.", cb );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 1 );

        p1 = fabricaDeCarros.createPart( TraseraCompacto.TYPE, 100, 100, Color.RED );
        fabricaDeCarros.addPart( p1 );
        TraseraCompacto co = ( TraseraCompacto )fabricaDeCarros.findPart( 100, 100 );
        assertNotNull( "No se agreg� correctamente el chasis, la f�brica no encontr� el objeto en la posici�n dada.", co );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 2 );

        p1 = fabricaDeCarros.createPart(SedanTrunk.TYPE, 50, 50, Color.RED );
        fabricaDeCarros.addPart( p1 );
        SedanTrunk ct = (SedanTrunk)fabricaDeCarros.findPart(50, 50 );
        assertNotNull( "No se agreg� correctamente el chasis, la f�brica no encontr� el objeto en la posici�n dada.", ct );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 3 );
    }

    /**
     * Prueba 8: Prueba el m�todo addPart para los diferentes est�nciles. <br>
     * <b>Methods a probar:</b> <br>
     * createPart<br>
     * addPart<br>
     * addPart<br>
     * <b> Caso de prueba 1 : <b><br>
     * Para cada est�ncil, se crea una parte y se agrega. Despu�s se busca y se verifica que exista en la posici�n donde deber�a estar.<br>
     * Adem�s se verifica que la cantidad de parts sea la adecuada.<br>
     */
    @Test
    public void testAgregarParte3( )
    {
        setupEscenario2( );

        Part p1 = fabricaDeCarros.createPart(Tesselation.TYPE, 10, 10, Color.RED );
        fabricaDeCarros.addPart( p1 );
        Tesselation mo = (Tesselation)fabricaDeCarros.findPart(10, 10 );
        assertNotNull( "No se agreg� correctamente el est�ncil, la f�brica no encontr� el objeto en la posici�n dada.", mo );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 1 );

        p1 = fabricaDeCarros.createPart(Skull.TYPE, 100, 100, Color.RED );
        fabricaDeCarros.addPart( p1 );
        Skull bo = (Skull)fabricaDeCarros.findPart(100, 100 );
        assertNotNull( "No se agreg� correctamente el est�ncil, la f�brica no encontr� el objeto en la posici�n dada.", bo );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 2 );

        p1 = fabricaDeCarros.createPart( Rayo.TYPE, 50, 50, Color.RED );
        fabricaDeCarros.addPart( p1 );
        Rayo oa = ( Rayo )fabricaDeCarros.findPart( 50, 50 );
        assertNotNull( "No se agreg� correctamente el est�ncil, la f�brica no encontr� el objeto en la posici�n dada.", oa );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 3 );
    }

    /**
     * Prueba 9: Prueba el m�todo addPart para las diferentes llantas. <br>
     * <b>Methods a probar:</b> <br>
     * createPart<br>
     * addPart<br>
     * addPart<br>
     * <b> Caso de prueba 1 : <b><br>
     * Para cada llanta, se crea una parte y se agrega. Despu�s se busca y se verifica que exista en la posici�n donde deber�a estar.<br>
     * Adem�s se verifica que la cantidad de parts sea la adecuada.<br>
     */
    @Test
    public void testAgregarParte4( )
    {
        setupEscenario2( );

        Part p1 = fabricaDeCarros.createPart(MediumRims.TYPE, 10, 10, Color.RED );
        fabricaDeCarros.addPart( p1 );
        MediumRims da = (MediumRims)fabricaDeCarros.findPart(10, 10 );
        assertNotNull( "No se agreg� correctamente el llanta, la f�brica no encontr� el objeto en la posici�n dada.", da );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 1 );

        p1 = fabricaDeCarros.createPart( RinesDeLujo.TYPE, 100, 100, Color.RED );
        fabricaDeCarros.addPart( p1 );
        RinesDeLujo dc = ( RinesDeLujo )fabricaDeCarros.findPart( 100, 100 );
        assertNotNull( "No se agreg� correctamente el llanta, la f�brica no encontr� el objeto en la posici�n dada.", dc );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 2 );

        p1 = fabricaDeCarros.createPart(EconomyRims.TYPE, 50, 50, Color.RED );
        fabricaDeCarros.addPart( p1 );
        EconomyRims dr = (EconomyRims)fabricaDeCarros.findPart(50, 50 );
        assertNotNull( "No se agreg� correctamente el llanta, la f�brica no encontr� el objeto en la posici�n dada.", dr );
        assertEquals( "Error al agregar la parte, el n�mero de parts que deben haber en la f�brica es incorrecto.", fabricaDeCarros.getParts( ).size( ), 3 );
    }

    /**
     * Prueba 10: Prueba el m�todo restart. <br>
     * <b>Methods a probar:</b> <br>
     * restart<br>
     * createPart<br>
     * addPart<br>
     * getParts<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se reinicia una f�brica vac�a y se verifica que el numero de parts sea cero.<br>
     * <b> Caso de prueba 2 : <b><br>
     * Se agrega una parte a la f�brica y se verifica que el n�mero de parts sea cero.<br>
     */
    @Test
    public void testReiniciar( )
    {
        setupEscenario2( );
        // Reiniciar una f�brica vac�a.
        fabricaDeCarros.restart( );
        assertEquals( "No se reinici� la f�brica correctamente, la lista de parts deber�a estar vac�a.", 0, fabricaDeCarros.getParts( ).size( ) );

        // Reiniciar una f�brica con parts.
        Part p1 = fabricaDeCarros.createPart(SedanTrunk.TYPE, 0, 0, Color.green );
        fabricaDeCarros.addPart( p1 );

        Part p2 = fabricaDeCarros.createPart(Rayo.TYPE, 12, 12, Color.red );
        fabricaDeCarros.addPart( p2 );
        fabricaDeCarros.restart( );

        assertEquals( "No se reinici� la f�brica correctamente, la lista de parts deber�a estar vac�a.", 0, fabricaDeCarros.getParts( ).size( ) );

    }

    /**
     * Prueba 11: Prueba el m�todo openFile. <br>
     * <b>Methods a probar:</b> <br>
     * openFile<br>
     * <b> Caso de prueba 1 : <b><br>
     * En una f�brica vac�a, se carga un archivo. Seguidamente se verifica que cada objeto en la f�brica corresponda a los del archivo.<br>
     */
    @Test
    public void testAbrir1( )
    {
        setupEscenario2( );

        try
        {
            fabricaDeCarros.openFile( "./test/data/test.dat" );
            assertEquals( "Error al openFile el archivo, la cantidad de parts actualmente no corresponde con la cantidad esperada.", 4, fabricaDeCarros.getParts( ).size( ) );

            Iterator<IPart> it = fabricaDeCarros.getParts( ).iterator( );
            IPart p = it.next( );
            assertEquals("Error al openFile el archivo, el tipo de la primera parte no corresponde al esperado.", TruckTrunk.TYPE, p.getType( ) );
            assertEquals( "Error al openFile el archivo, la coordenada 'x' de la primera parte no corresponde al esperado.", 0, p.getX( ) );
            assertEquals( "Error al openFile el archivo, la coordenada 'y' de la primera parte no corresponde al esperado.", 290, p.getY( ) );
            assertEquals( "Error al openFile el archivo, el color de la primera parte no corresponde al esperado.", new Color( -3620889 ), p.getColor( ));

            p = it.next( );
            assertEquals("Error al openFile el archivo, el tipo de la segunda parte no corresponde al esperado.", Tesselation.TYPE, p.getType( ) );
            assertEquals( "Error al openFile el archivo, la coordenada 'x' de la segunda parte no corresponde al esperado.", 9, p.getX( ) );
            assertEquals( "Error al openFile el archivo, la coordenada 'y' de la segunda parte no corresponde al esperado.", 376, p.getY( ) );
            assertEquals( "Error al openFile el archivo, el color de la segunda parte no corresponde al esperado.", new Color( -3620889 ), p.getColor( ));

            p = it.next( );
            assertEquals("Error al openFile el archivo, el tipo de la tercera parte no corresponde al esperado.", MediumRims.TYPE, p.getType( ) );
            assertEquals( "Error al openFile el archivo, la coordenada 'x' de la tercera parte no corresponde al esperado.", 50, p.getX( ) );
            assertEquals( "Error al openFile el archivo, la coordenada 'y' de la tercera parte no corresponde al esperado.", 285, p.getY( ) );
            assertEquals( "Error al openFile el archivo, el color de la tercera parte no corresponde al esperado.", new Color( -3620889 ), p.getColor( ));

            p = it.next( );
            assertEquals("Error al openFile el archivo, el tipo de la cuarta parte no corresponde al esperado.", Skull.TYPE, p.getType( ) );
            assertEquals( "Error al openFile el archivo, la coordenada 'x' de la cuarta parte no corresponde al esperado.", 78, p.getX( ) );
            assertEquals( "Error al openFile el archivo, la coordenada 'y' de la cuarta parte no corresponde al esperado.", 396, p.getY( ) );
            assertEquals( "Error al openFile el archivo, el color de la cuarta parte no corresponde al esperado.", new Color( -3368449 ), p.getColor( ));

        }
        catch( Exception e1 )
        {
            fail( "No deber�a generarse el error: " + e1.getMessage( ) + ".");
        }

    }

    /**
     * Prueba 12: Prueba el m�todo openFile. <br>
     * <b>Methods a probar:</b> <br>
     * openFile<br>
     * <b> Caso de prueba 1 : <b><br>
     * En una f�brica vac�a, se intenta leer un archivo con problemas de formato en la l�nea correspondiente al n�mero de parts.<br>
     * <b> Caso de prueba 2 : <b><br>
     * En una f�brica vac�a, se intenta leer un archivo con problemas de formato en la l�nea correspondiente al tipo de las parts.<br>
     * <b> Caso de prueba 3 : <b><br>
     * En una f�brica vac�a, se intenta leer un archivo con problemas de formato en la l�nea correspondiente a la posici�n de las parts.<br>
     * <b> Caso de prueba 4 : <b><br>
     * En una f�brica vac�a, se intenta leer un archivo con problemas de formato en la l�nea correspondiente al color de las parts.<br>
     */
    @Test
    public void testAbrir2( )
    {
        setupEscenario2( );

        try
        {
            fabricaDeCarros.openFile( "./test/data/testMalFormato1.dat" );
            fail( "El archivo tiene formato incorrecto en el n�mero de parts, deber�a fallar." );
        }
        catch( Exception e1 )
        {
            // Debe generar error.;
        }

        try
        {
            fabricaDeCarros.openFile( "./test/data/testMalFormato2.dat" );
            fail( "El archivo tiene formato incorrecto en el tipo de parte, deber�a fallar." );
        }
        catch( Exception e1 )
        {
            // Debe generar error.;
        }

        try
        {
            fabricaDeCarros.openFile( "./test/data/testMalFormato3.dat" );
            fail( "El archivo tiene formato incorrecto en los puntos de las posiciones de las parts, deber�a fallar." );
        }
        catch( Exception e1 )
        {
            // Debe generar error.;
        }

        try
        {
            fabricaDeCarros.openFile( "./test/data/testMalFormato4.dat" );
            fail( "El archivo tiene formato incorrecto en el color de la parte, deber�a fallar." );
        }
        catch( Exception e1 )
        {
            // Debe generar error.;
        }

    }

    /**
     * Prueba 13: Prueba el m�todo save con una ruta de archivo como par�metro. <br>
     * <b>Methods a probar:</b> <br>
     * eliminatePart<br>
     * findPart<br>
     * openFile<br>
     * save<br>
     * <b> Caso de prueba 1 : <b><br>
     * De una f�brica, se elimina una parte, esta se guarda en un archivo. De este archivo se vuelve a cargar la f�brica y se verifica que la parte no exista<br>
     * <b> Caso de prueba 2 : <b><br>
     * De una f�brica, se elimina una parte, esta se guarda en un archivo. De este archivo se vuelve a cargar la f�brica y se verifica que las otras parts existan<br>
     * <b> Caso de prueba 3 : <b><br>
     * Se trata de save en un directorio inexistente para simular una excepci�n IO<br>
     */
    @Test
    public void testGuardar1( )
    {
    
        try
        {
            fabricaDeCarros.eliminatePart( 2, 295 );

            fabricaDeCarros.save( "./test/data/test3.dat" );
            fabricaDeCarros.openFile( "./test/data/test3.dat" );

            IPart p = fabricaDeCarros.findPart(1, 291 );

            assertNull( "Archivo guardado incorrectamente. La parte en la posici�n buscada no deber�a existir.", p );
            assertEquals( "Archivo guardado incorrectamente, la cantidad de parts actualmente no corersponde con la esperada.", 3, fabricaDeCarros.getParts( ).size( ) );

            p = fabricaDeCarros.findPart( 10, 380 );
            assertNotNull( "Archivo guardado incorrectamente. No deber�a existir ninguna parte en la posici�n dada.", p );
            
            
            try
            {
                fabricaDeCarros.save( "./test/data/carpetaFalsa/test3.dat" );
                fail("Debe�a presentar una excepci�n IO");
            }
            catch( IOException e )
            {
                //
            }

        }
        catch( Exception e1 )
        {
            fail( "No deber�a generarse el error: " + e1.getMessage( ) +".");
        }
    }

    /**
     * Prueba 14: Prueba el m�todo save. <br>
     * <b>Methods a probar:</b> <br>
     * save<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se toma una f�brica y se guarda con dos rutas en dos archivos diferentes. Al hacer esto el atributo de ruta archivo de la f�brica debi� actualizarse a la �ltima de las
     * rutas.<br>
     * Este archivo se elimina, y se utiliza el m�todo save que no recibe par�metro. Se verifica que el primer archivo y el �ltimo sean exactamente igual en contenido.
     */
    @Test
    public void testGuardar2( )
    {
        

        try
        {
            fabricaDeCarros.save( "./test/data/testGuardar3.dat" );
            fabricaDeCarros.save( "./test/data/test3.dat" );
            File f = new File( "./test/data/test3.dat" );
            f.delete( );
            fabricaDeCarros.save( );

            BufferedReader br1 = new BufferedReader( new FileReader( "./test/data/testGuardar3.dat" ) );
            BufferedReader br2 = new BufferedReader( new FileReader( "./test/data/test3.dat" ) );

            for( int i = 0; i < 13; i++ )
            {
                assertEquals( "Archivo guardado incorrectamente. El archivo guardado con el m�todo save() no es igual al original.", br1.readLine( ), br2.readLine( ) );
            }
            br1.close( );
            br2.close( );
        }
        catch( Exception e1 )
        {
            fail( "No deber�a generarse el error: " + e1.getMessage( )  + ".");
        }
    }

    /**
     * Prueba 15: Prueba el m�todo eliminatePart para los diferentes chasis delanteros. <br>
     * <b>Methods a probar:</b> <br>
     * eliminatePart<br>
     * createPart<br>
     * addPart<br>
     * findPart<br>
     * getParts<br>
     * <b> Caso de prueba 1 : <b><br>
     * En una f�brica vac�a se agrega uno de cada uno de los tipos de chasis delanteros,se verifica que la parte existe en la f�brica, luego se elimina y se verifica que la parte no exista.<br>
     * <b> Caso de prueba 2 : <b><br>
     * Se verifica que al eliminar elementos de la f�brica, el n�mero de parts disminuya en 1.<br>
     * <b> Caso de prueba 3 : <b><br>
     * Se verifica que al intentar elementos de la f�brica que no existen, el n�mero de parts se mantenga.<br>
     */
    @Test
    public void testEliminarParte1( )
    {
        setupEscenario2( );
        int numPartes = fabricaDeCarros.getParts( ).size( );
        numPartes++;
        IPart p = fabricaDeCarros.createPart(TruckHood.TYPE, 0, 30, null );
        fabricaDeCarros.addPart( p );
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar el chasis, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 0, 30 ) );
        fabricaDeCarros.eliminatePart( 0, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        TruckHood co = (TruckHood)fabricaDeCarros.findPart(0, 30 );
        assertNull( "No elimin� el chasis correctamente.", co );

        p = fabricaDeCarros.createPart( DelanteraCompacto.TYPE, 200, 30, null );
        numPartes++;
        fabricaDeCarros.addPart( p );
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar el chasis, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 200, 30 ) );
        fabricaDeCarros.eliminatePart( 200, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        DelanteraCompacto ct = ( DelanteraCompacto )fabricaDeCarros.findPart( 200, 30 );
        assertNull( "No elimin� el chasis correctamente.", ct );

        p = fabricaDeCarros.createPart(SedanHood.TYPE, 400, 30, null );
        numPartes++;
        fabricaDeCarros.addPart( p );
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar el chasis, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 400, 30 ) );
        fabricaDeCarros.eliminatePart( 400, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        SedanHood cc = (SedanHood)fabricaDeCarros.findPart(400, 30 );
        assertNull( "No elimin� el chasis correctamente.", cc );
        
        
        fabricaDeCarros.eliminatePart( 400, 30 );
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );

    }

    /**
     * Prueba 16: Prueba el m�todo eliminatePart para los diferentes chasis traseros. <br>
     * <b>Methods a probar:</b> <br>
     * eliminatePart<br>
     * createPart<br>
     * addPart<br>
     * findPart<br>
     * getParts<br>
     * <b> Caso de prueba 1 : <b><br>
     * En una f�brica vac�a se agrega uno de cada uno de los tipos de chasis traseros,se verifica que la parte existe en la f�brica, luego se elimina y se verifica que la parte no exista.<br>
     * <b> Caso de prueba 2 : <b><br>
     * Se verifica que al eliminar elementos de la f�brica, el n�mero de parts disminuya.<br>
     * <b> Caso de prueba 3 : <b><br>
     * Se verifica que al intentar elementos de la f�brica que no existen, el n�mero de parts se mantenga.<br>
     */
    @Test
    public void testEliminarParte2( )
    {
        setupEscenario2( );

        int numPartes = fabricaDeCarros.getParts( ).size( );
        
        IPart p = fabricaDeCarros.createPart(TraseraCompacto.TYPE, 0, 30, null );
        numPartes++;
        fabricaDeCarros.addPart( p );
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar el chasis, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 0, 30 ) );
        fabricaDeCarros.eliminatePart( 0, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        TraseraCompacto co = ( TraseraCompacto )fabricaDeCarros.findPart( 0, 30 );
        assertNull( "No elimin� el chasis correctamente.", co );

        p = fabricaDeCarros.createPart(SedanTrunk.TYPE, 200, 30, null );
        numPartes++;
        fabricaDeCarros.addPart( p );
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar el chasis, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 200, 30 ) );
        fabricaDeCarros.eliminatePart( 200, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        SedanTrunk ct = (SedanTrunk)fabricaDeCarros.findPart(200, 30 );
        assertNull( "No elimin� el chasis correctamente.", ct );

        p = fabricaDeCarros.createPart(TruckTrunk.TYPE, 400, 30, null );
        numPartes++;
        fabricaDeCarros.addPart( p );
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar el chasis, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 400, 30 ) );
        fabricaDeCarros.eliminatePart( 400, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        TruckTrunk cc = (TruckTrunk)fabricaDeCarros.findPart(400, 30 );
        assertNull( "No elimin� el chasis correctamente.", cc );
        assertEquals( "Error eliminando parte.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        
        fabricaDeCarros.eliminatePart( 400, 30 );
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
    }

    /**
     * Prueba 17: Prueba el m�todo eliminatePart para los diferentes est�nciles. <br>
     * <b>Methods a probar:</b> <br>
     * eliminatePart<br>
     * createPart<br>
     * addPart<br>
     * findPart<br>
     * getParts<br>
     * <b> Caso de prueba 1 : <b><br>
     * En una f�brica vac�a se agrega uno de cada uno de los tipos de est�nciles,se verifica que la parte existe en la f�brica, luego se elimina y se verifica que la parte no exista.<br>
     * <b> Caso de prueba 2 : <b><br>
     * Se verifica que al eliminar elementos de la f�brica, el n�mero de parts disminuya.<br>
     * <b> Caso de prueba 3 : <b><br>
     * Se verifica que al intentar elementos de la f�brica que no existen, el n�mero de parts se mantenga.<br>
     */
    @Test
    public void testEliminarParte3( )
    {
        setupEscenario2( );

        int numPartes = fabricaDeCarros.getParts( ).size( );
        
        IPart p = fabricaDeCarros.createPart(Tesselation.TYPE, 0, 30, null );
        fabricaDeCarros.addPart( p );
        numPartes++;
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar el est�ncil, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 0, 30 ) );
        fabricaDeCarros.eliminatePart( 0, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        Tesselation co = (Tesselation)fabricaDeCarros.findPart(0, 30 );
        assertNull( "No elimin� el est�ncil correctamente.", co );

        p = fabricaDeCarros.createPart(Skull.TYPE, 200, 30, null );
        fabricaDeCarros.addPart( p );
        numPartes++;
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar el est�ncil, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 200, 30 ) );
        fabricaDeCarros.eliminatePart( 200, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        Skull ct = (Skull)fabricaDeCarros.findPart(200, 30 );
        assertNull( "No elimin� el est�ncil correctamente.", ct );

        p = fabricaDeCarros.createPart( Rayo.TYPE, 400, 30, null );
        fabricaDeCarros.addPart( p );
        numPartes++;
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar el est�ncil, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 400, 30 ) );
        fabricaDeCarros.eliminatePart( 400, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        Rayo cc = ( Rayo )fabricaDeCarros.findPart( 400, 30 );
        assertNull( "No elimin� el est�ncil correctamente.", cc );

        fabricaDeCarros.eliminatePart( 400, 30 );
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
    }

    /**
     * Prueba 18: Prueba el m�todo eliminatePart para las diferentes llantas. <br>
     * <b>Methods a probar:</b> <br>
     * eliminatePart<br>
     * createPart<br>
     * addPart<br>
     * findPart<br>
     * getParts<br>
     * <b> Caso de prueba 1 : <b><br>
     * En una f�brica vac�a se agrega uno de cada uno de los tipos de llantas,se verifica que la parte existe en la f�brica, luego se elimina y se verifica que la parte no exista.<br>
     * <b> Caso de prueba 2 : <b><br>
     * Se verifica que al eliminar elementos de la f�brica, el n�mero de parts disminuya.<br>
     * <b> Caso de prueba 3 : <b><br>
     * Se verifica que al intentar elementos de la f�brica que no existen, el n�mero de parts se mantenga.<br>
     */
    @Test
    public void testEliminarParte4( )
    {
        setupEscenario2( );
        int numPartes = fabricaDeCarros.getParts( ).size( );
        
        IPart p = fabricaDeCarros.createPart(MediumRims.TYPE, 0, 30, null );
        fabricaDeCarros.addPart( p );
        numPartes++;
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar la llanta, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 0, 30 ) );
        fabricaDeCarros.eliminatePart( 0, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        MediumRims dr = (MediumRims)fabricaDeCarros.findPart(0, 30 );
        assertNull( "No elimin� la llanta correctamente.", dr );

        p = fabricaDeCarros.createPart( RinesDeLujo.TYPE, 200, 30, null );
        fabricaDeCarros.addPart( p );
        numPartes++;
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar la llanta, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 200, 30 ) );
        fabricaDeCarros.eliminatePart( 200, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        RinesDeLujo da = ( RinesDeLujo )fabricaDeCarros.findPart( 200, 30 );
        assertNull( "No elimin� la llanta correctamente.", da );

        p = fabricaDeCarros.createPart(EconomyRims.TYPE, 400, 30, null );
        fabricaDeCarros.addPart( p );
        numPartes++;
        assertEquals( "Error agregando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        assertNotNull( "Error al agregar la llanta, no se encontr� el objeto en la posici�n esperada.", fabricaDeCarros.findPart( 400, 30 ) );
        fabricaDeCarros.eliminatePart( 400, 30 );
        numPartes--;
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
        EconomyRims dc = (EconomyRims)fabricaDeCarros.findPart(400, 30 );
        assertNull( "No elimin� la llanta correctamente.", dc );
        assertEquals( "Error eliminando parte.", numPartes, fabricaDeCarros.getParts( ).size( ) );
      
        fabricaDeCarros.eliminatePart( 400, 30 );
        assertEquals( "Error eliminando parte, el n�mero de parts no corresponde.", numPartes, fabricaDeCarros.getParts( ).size( ) );
    }

    /**
     * Prueba 19: Prueba el m�todo changePartPosition. <br>
     * <b>Methods a probar:</b> <br>
     * changePartPosition<br>
     * findPart<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se cambia de posici�n una parte y se verifica que ya no exista en la posici�n original.<br>
     * <b> Caso de prueba 2 : <b><br>
     * Se cambia de posici�n una parte y se verifica que exista en la nueva posici�n.<br>
     */
    @Test
    public void testCambiarPosicionParte( )
    {
        
        try
        {

            fabricaDeCarros.changePartPosition( 0, 290, 300, 300 );
            IPart p = fabricaDeCarros.findPart(0, 290 );
            assertNull( "No cambi� la posici�n de la parte correctamente.", p );

            TruckTrunk cc = (TruckTrunk)fabricaDeCarros.findPart(300, 300 );
            assertNotNull( "No cambi� la posici�n de la parte correctamente.", cc );
        }
        catch( Exception e )
        {
            fail( "No deber�a fallar" );
        }
    }

    
    /**
     * Prueba 20: Prueba el m�todo findPart. <br>
     * <b>Methods a probar:</b> <br>
     * findPart<br>
     * <b> Caso de prueba 1 : <b><br>
     * En una f�brica con varias parts, se verifica que al buscar cada parte en sus respectivas posiciones, la f�brica devuelva elementos no nulos.<br>
     */
    @Test
    public void testBuscarParte1( )
    {
        
        try
        {
            Skull ca = (Skull)fabricaDeCarros.findPart(80, 400 );
            assertNotNull( "Deber�a encontrar la parte en la posici�n dada.", ca );

            ca = (Skull)fabricaDeCarros.findPart(85, 411 );
            assertNotNull( "Deber�a encontrar la parte en la posici�n dada.", ca );

            MediumRims da = (MediumRims)fabricaDeCarros.findPart(60, 285 );
            assertNotNull( "Deber�a encontrar la parte en la posici�n dada.", da );

            da = (MediumRims)fabricaDeCarros.findPart(65, 292 );
            assertNotNull( "Deber�a encontrar la parte en la posici�n dada.", da );

            TruckTrunk cc = (TruckTrunk)fabricaDeCarros.findPart(30, 290 );
            assertNotNull( "Deber�a encontrar la parte en la posici�n dada.", cc );

            cc = (TruckTrunk)fabricaDeCarros.findPart(35, 300 );
            assertNotNull( "Deber�a encontrar la parte en la posici�n dada.", cc );

        }
        catch( Exception e )
        {
            fail( "No deber�a fallar (" + e.getMessage( ) + ")" );
        }
    }

    
    /**
     * Prueba 21: Prueba el m�todo findPart. <br>
     * <b>Methods a probar:</b> <br>
     * findPart<br>
     * <b> Caso de prueba 1 : <b><br>
     * En una f�brica con varias parts, se verifica que al buscar parts donde no hay nada, la f�brica devuelva elementos nulos.<br>
     */
    @Test
    public void testBuscarParte2( )
    {
        
        try
        {
            Tesselation mo = (Tesselation)fabricaDeCarros.findPart(0, 100 );
            assertNull( "No deber�a encontrar la parte en la posici�n dada.", mo );

            MediumRims dr = (MediumRims)fabricaDeCarros.findPart(200, 555 );
            assertNull( "No deber�a encontrar la parte en la posici�n dada.", dr );

            SedanTrunk ct = (SedanTrunk)fabricaDeCarros.findPart(430, 2 );
            assertNull( "No deber�a encontrar la parte en la posici�n dada.", ct );
        }
        catch( Exception e )
        {
            fail( "No deber�a fallar (" + e.getMessage( ) + ")" );
        }
    }
}