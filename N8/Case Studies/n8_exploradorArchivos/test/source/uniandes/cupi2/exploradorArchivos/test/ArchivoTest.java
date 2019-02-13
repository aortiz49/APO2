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
import java.io.IOException;

import uniandes.cupi2.exploradorArchivos.mundo.Archivo;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Archivo estén correctamente implementados
 */
public class ArchivoTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Archivo archivo;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye el archivo de un archivo de texto
     */
    private void setupEscenario1( )
    {
        archivo = new Archivo( new File( "test" + File.separator + "data" + File.separator + "texto1.txt" ).getAbsolutePath( ) );
    }

    /**
     * Construye el archivo de un archivo nuevo temporal
     */
    private void setupEscenario2( )
    {
        // Crea un temporal para borrar al cerrar Java
        try
        {
            File file = File.createTempFile( "tmp", "tmp" );
            file.deleteOnExit( );
            archivo = new Archivo( file.getAbsolutePath( ) );
        }
        catch( IOException e )
        {
            fail( "Debe poder crear el archivo temporal" );
        }
    }

    /**
     * Verifica que los métodos que dan la información de un archivo lo hagan de forma correcta. <br>
     * <b> Métodos a probar: </b> darNombre, darTamanio, darTamanioString, darRuta, esTexto.<br>
     * <b> Objetivo: </b> Probar que los métodos darNombre(), darTamanio(), darTamanioString(), darRuta() y esTexto() retornen la información correspondiente al archivo real.
     * <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la información de un archivo (tamaño, tamaño en String, nombre, ruta y si es texto) ésta debe corresponder a la información real.
     */
    public void testInformacion( )
    {
        setupEscenario1( );

        // Prueba la información del archivo
        File ruta = new File( "test" + File.separator + "data" + File.separator + "texto1.txt" );
        String path = ruta.getAbsolutePath( );
        assertEquals( "El nombre es inválido", "texto1.txt", archivo.darNombre( ) );
        assertEquals( "El tamaño es inválido", 31, archivo.darTamanio( ) );
        assertEquals( "El tamaño en String es inválido", "31 Bytes", archivo.darTamanioString( ) );
        assertEquals( "La ruta no es inválida", path, archivo.darRuta( ) );
        assertTrue( "Debería ser un archivo de texto", archivo.esTexto( ) );
    }

    /**
     * Verifica que el método para escribir a un archivo funcione correctamente. <br>
     * <b> Métodos a probar: </b> escribirArchivo.<br>
     * <b> Objetivo: </b> Probar que el método escribirArchivo() sea capaz de escribir el contenido dado por el usuario en un archivo real. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el tamaño de un archivo en el que se acabo de escribir su tamaño debe ser igual al tamaño del String que se le escribió.
     */
    public void testEscribirArchivo( )
    {

        // Crea un archivo temporal vacío
        setupEscenario2( );
        assertEquals( "El tamaño es inválido", 0, archivo.darTamanio( ) );
        assertEquals( "El tamaño en String es inválido", "0 Bytes", archivo.darTamanioString( ) );
        //
        // Escribe el archivo
        try
        {
            archivo.escribirArchivo( "Hola Mundo!" );
            assertEquals( "El tamaño es inválido", 13, archivo.darTamanio( ) );
            assertEquals( "El tamaño en String es inválido", "13 Bytes", archivo.darTamanioString( ) );
        }
        catch( IOException e )
        {
            fail( "Debe poder escribir el archivo" );
        }
    }

}
