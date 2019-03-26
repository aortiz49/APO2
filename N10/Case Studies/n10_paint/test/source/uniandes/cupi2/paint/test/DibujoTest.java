/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DibujoTest.java,v 1.2 2006/10/20 15:47:32 da-romer Exp $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_paint
 * Autor: Mario Sánchez - 3/10/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.paint.test;

import java.awt.*;

import junit.framework.*;
import uniandes.cupi2.paint.mundo.*;

/**
 * Clase de Prueba del editor
 */
public class DibujoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Dibujo de figuras para pruebas
     */
    private Dibujo dibujo;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un editor y carga un archivo
     */
    public void setupEscenario1( )
    {
        dibujo = new Dibujo( );
        try
        {
            dibujo.cargar( "./test/data/test.dat" );
        }
        catch( Exception e )
        {
            fail( "No se pudo cargar el archivo: " + e.getMessage( ) );
        }
    }

    /**
     * Verifica que una figura se agregue correctamente. <br>
     * <b> Métodos a probar: </b> <br>
     * cargar, agregarFigura, hacerClick. <br>
     * <b> Objetivo: </b> probar que el método agregarFigura() agrega correctamente una figura al editor. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar una figura y hacer click sobre esta, esta debe ser seleccionada.
     */
    public void testAgregarFigura( )
    {
        setupEscenario1( );

        Linea l = new Linea( 20, 300, 100, 300, Color.BLACK, new BasicStroke( 4 ) );
        dibujo.agregarFigura( l );

        dibujo.hacerClick( 25, 300 );
        IFigura f = dibujo.darSeleccionada( );
        assertNotNull( "No se agregó correctamente la figura", f );
    }

    /**
     * Verifica el método darNombreArchivo. <br>
     * <b> Métodos a probar: </b> <br>
     * darNombreArchivo, cargar. <br>
     * <b> Objetivo: </b> probar que el método darNombreArchivo() retorna correctamente el nombre del archivo utilizado para cargar la gráfica. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el nombre del archivo, este debe ser retornado correctamente.
     */
    public void testDarNombreArchivo( )
    {
        setupEscenario1( );

        assertTrue( "El nombre del archivo es incorrecto", dibujo.darNombreArchivo( ).endsWith( "test.dat" ) );
    }

    /**
     * Verifica la eliminación de las figuras. <br>
     * <b> Métodos a probar: </b> <br>
     * cargar, agregarFigura, hacerClick, eliminarFigura. <br>
     * <b> Objetivo: </b> probar que el método eliminarFigura() elimina correctamente una figura al editor. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar una figura previamente agregada esta debe desaparecer del editor. <br>
     */
    public void testEliminarFigura( )
    {
        setupEscenario1( );

        Linea l = new Linea( 20, 300, 100, 300, Color.BLACK, new BasicStroke( 4 ) );
        dibujo.agregarFigura( l );

        dibujo.hacerClick( 25, 300 );
        IFigura f = dibujo.darSeleccionada( );
        assertNotNull( "No se agregó correctamente la figura", f );

        dibujo.eliminarFigura( );

        dibujo.hacerClick( 25, 300 );
        IFigura f2 = dibujo.darSeleccionada( );

        assertFalse( "La figura no se eliminó correctamente", f == f2 );
    }

    /**
     * Verifica el método reiniciar <b> Métodos a probar: </b> <br>
     * cargar, agregarFigura, hacerClick, reiniciar. <br>
     * <b> Objetivo: </b> probar que el método reiniciar() elimine correctamente todas las figuras del editor. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al reiniciar el editor, este debe quedar sin figuras, incluyendo la figura que se encontraba seleccionada. <br>
     */
    public void testReiniciar( )
    {
        setupEscenario1( );

        Linea l = new Linea( 20, 300, 100, 300, Color.BLACK, new BasicStroke( 4 ) );
        dibujo.agregarFigura( l );

        dibujo.hacerClick( 25, 300 );
        IFigura f = dibujo.darSeleccionada( );
        assertNotNull( "No se agregó correctamente la figura", f );

        dibujo.reiniciar( );

        dibujo.hacerClick( 25, 300 );
        f = dibujo.darSeleccionada( );
        assertNull( "No se limpió correctamente el editor", f );
    }

    /**
     * Verifica el método seleccionar con diferentes tipos de figuras <b> Métodos a probar: </b> <br>
     * cargar, hacerClick. <br>
     * <b> Objetivo: </b> probar que el método hacerClick() selecciona correctamente una figura del editor. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al hacer click sobre una figura cuyas coordenadas se conocen previamente, ésta debe quedar seleccionada. <br>
     */
    public void testSeleccionar( )
    {
        setupEscenario1( );

        dibujo.hacerClick( 25, 25 );
        IFigura rect = dibujo.darSeleccionada( );
        assertNotNull( "No se agregó correctamente la figura", rect );
        assertTrue( "La figura no es del tipo correcto", rect instanceof Rectangulo );

        dibujo.hacerClick( 275, 90 );
        IFigura ovalo = dibujo.darSeleccionada( );
        assertNotNull( "No se agregó correctamente la figura", ovalo );
        assertTrue( "La figura no es del tipo correcto", ovalo instanceof Ovalo );

        dibujo.hacerClick( 25, 178 );
        IFigura linea = dibujo.darSeleccionada( );
        assertNotNull( "No se agregó correctamente la figura", linea );
        assertTrue( "La figura no es del tipo correcto", linea instanceof Linea );

    }
}
