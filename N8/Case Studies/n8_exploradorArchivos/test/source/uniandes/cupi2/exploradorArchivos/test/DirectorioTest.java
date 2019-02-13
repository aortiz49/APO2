/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_exploradorArchivos
 * Autor: Pablo Barvo - Jul 17, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.exploradorArchivos.test;

import java.io.File;

import junit.framework.TestCase;
import uniandes.cupi2.exploradorArchivos.mundo.Directorio;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Directorio estén correctamente implementados
 */
public class DirectorioTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Directorio directorio;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye Directorio en la ruta por defecto
     */
    private void setupEscenario1( )
    {
        directorio = new Directorio( );
    }

    /**
     * Construye Directorio en la ruta de pruebas
     */
    private void setupEscenario2( )
    {
        directorio = new Directorio( new File( "test" + File.separator + "data" ).getAbsolutePath( ) );
    }

    /**
     * Verifica que el constructor del directorio sin parámetros recupere la información del sistema de archivos de forma correcta. <br>
     * <b> Métodos a probar: </b> Directorio (constructor), darRuta, darNombre, esRaiz.<br>
     * <b> Objetivo: </b> Probar que el constructor sin parámetros sea capaz de recuperar la información del sistema de archivos asociada con el directorio. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear directorio con el constructor sin parámetros, éste debe quedar situado en la raíz del sistema de archivos. <br>
     */
    public void testConstruirDirectorio1( )
    {
        setupEscenario1( );
        assertEquals( "La ruta es inválida", "C:" + File.separator, directorio.darRuta( ) );
        assertEquals( "La ruta es inválida", "", directorio.darNombre( ) );
        assertTrue( "Debe ser raíz", directorio.esRaiz( ) );
    }

    /**
     * Verifica que el constructor del directorio con parámetros recupere la información del sistema de archivos de forma correcta. <br>
     * <b> Métodos a probar: </b> Directorio (constructor), darRuta, darNombre, esRaiz.<br>
     * <b> Objetivo: </b> Probar que el constructor con parámetros sea capaz de recuperar la información del sistema de archivos asociada con el directorio. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear directorio con el constructor con parámetros . <br>
     */
    public void testConstruirDirectorio2( )
    {
        setupEscenario2( );
        assertEquals( "La ruta es inválida", new File( "test" + File.separator + "data" ).getAbsolutePath( ), directorio.darRuta( ) );
        assertEquals( "La ruta es inválida", "data", directorio.darNombre( ) );
        assertFalse( "No debe ser raíz", directorio.esRaiz( ) );
    }

    /**
     * Verifica que el método para subir un nivel en la jerarquía funcione correctamente. <br>
     * <b> Métodos a probar: </b> subirNivel.<br>
     * <b> Objetivo: </b> Probar que el método subirNivel() sea capaz de subir un nivel en la jerarquía de archivos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al subir un nivel la nueva ruta del directorio debe quedar en la ruta del directorio padre.
     */
    public void testSubirDirectorio( )
    {
        setupEscenario2( );
        directorio.subirNivel( );
        assertEquals( "La ruta es inválida", new File( "test" ).getAbsolutePath( ), directorio.darRuta( ) );
    }

}
