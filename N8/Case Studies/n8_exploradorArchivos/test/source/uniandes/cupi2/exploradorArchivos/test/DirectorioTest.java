/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Esta es la clase usada para verificar que los m�todos de la clase Directorio est�n correctamente implementados
 */
public class DirectorioTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Directorio directorio;

    // -----------------------------------------------------------------
    // M�todos
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
     * Verifica que el constructor del directorio sin par�metros recupere la informaci�n del sistema de archivos de forma correcta. <br>
     * <b> M�todos a probar: </b> Directorio (constructor), darRuta, darNombre, esRaiz.<br>
     * <b> Objetivo: </b> Probar que el constructor sin par�metros sea capaz de recuperar la informaci�n del sistema de archivos asociada con el directorio. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear directorio con el constructor sin par�metros, �ste debe quedar situado en la ra�z del sistema de archivos. <br>
     */
    public void testConstruirDirectorio1( )
    {
        setupEscenario1( );
        assertEquals( "La ruta es inv�lida", "C:" + File.separator, directorio.darRuta( ) );
        assertEquals( "La ruta es inv�lida", "", directorio.darNombre( ) );
        assertTrue( "Debe ser ra�z", directorio.esRaiz( ) );
    }

    /**
     * Verifica que el constructor del directorio con par�metros recupere la informaci�n del sistema de archivos de forma correcta. <br>
     * <b> M�todos a probar: </b> Directorio (constructor), darRuta, darNombre, esRaiz.<br>
     * <b> Objetivo: </b> Probar que el constructor con par�metros sea capaz de recuperar la informaci�n del sistema de archivos asociada con el directorio. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear directorio con el constructor con par�metros . <br>
     */
    public void testConstruirDirectorio2( )
    {
        setupEscenario2( );
        assertEquals( "La ruta es inv�lida", new File( "test" + File.separator + "data" ).getAbsolutePath( ), directorio.darRuta( ) );
        assertEquals( "La ruta es inv�lida", "data", directorio.darNombre( ) );
        assertFalse( "No debe ser ra�z", directorio.esRaiz( ) );
    }

    /**
     * Verifica que el m�todo para subir un nivel en la jerarqu�a funcione correctamente. <br>
     * <b> M�todos a probar: </b> subirNivel.<br>
     * <b> Objetivo: </b> Probar que el m�todo subirNivel() sea capaz de subir un nivel en la jerarqu�a de archivos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al subir un nivel la nueva ruta del directorio debe quedar en la ruta del directorio padre.
     */
    public void testSubirDirectorio( )
    {
        setupEscenario2( );
        directorio.subirNivel( );
        assertEquals( "La ruta es inv�lida", new File( "test" ).getAbsolutePath( ), directorio.darRuta( ) );
    }

}
