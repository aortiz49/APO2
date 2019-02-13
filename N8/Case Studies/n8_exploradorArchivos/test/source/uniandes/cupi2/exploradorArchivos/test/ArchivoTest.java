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
import java.io.IOException;

import uniandes.cupi2.exploradorArchivos.mundo.Archivo;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Archivo est�n correctamente implementados
 */
public class ArchivoTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Archivo archivo;

    // -----------------------------------------------------------------
    // M�todos
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
     * Verifica que los m�todos que dan la informaci�n de un archivo lo hagan de forma correcta. <br>
     * <b> M�todos a probar: </b> darNombre, darTamanio, darTamanioString, darRuta, esTexto.<br>
     * <b> Objetivo: </b> Probar que los m�todos darNombre(), darTamanio(), darTamanioString(), darRuta() y esTexto() retornen la informaci�n correspondiente al archivo real.
     * <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la informaci�n de un archivo (tama�o, tama�o en String, nombre, ruta y si es texto) �sta debe corresponder a la informaci�n real.
     */
    public void testInformacion( )
    {
        setupEscenario1( );

        // Prueba la informaci�n del archivo
        File ruta = new File( "test" + File.separator + "data" + File.separator + "texto1.txt" );
        String path = ruta.getAbsolutePath( );
        assertEquals( "El nombre es inv�lido", "texto1.txt", archivo.darNombre( ) );
        assertEquals( "El tama�o es inv�lido", 31, archivo.darTamanio( ) );
        assertEquals( "El tama�o en String es inv�lido", "31 Bytes", archivo.darTamanioString( ) );
        assertEquals( "La ruta no es inv�lida", path, archivo.darRuta( ) );
        assertTrue( "Deber�a ser un archivo de texto", archivo.esTexto( ) );
    }

    /**
     * Verifica que el m�todo para escribir a un archivo funcione correctamente. <br>
     * <b> M�todos a probar: </b> escribirArchivo.<br>
     * <b> Objetivo: </b> Probar que el m�todo escribirArchivo() sea capaz de escribir el contenido dado por el usuario en un archivo real. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el tama�o de un archivo en el que se acabo de escribir su tama�o debe ser igual al tama�o del String que se le escribi�.
     */
    public void testEscribirArchivo( )
    {

        // Crea un archivo temporal vac�o
        setupEscenario2( );
        assertEquals( "El tama�o es inv�lido", 0, archivo.darTamanio( ) );
        assertEquals( "El tama�o en String es inv�lido", "0 Bytes", archivo.darTamanioString( ) );
        //
        // Escribe el archivo
        try
        {
            archivo.escribirArchivo( "Hola Mundo!" );
            assertEquals( "El tama�o es inv�lido", 13, archivo.darTamanio( ) );
            assertEquals( "El tama�o en String es inv�lido", "13 Bytes", archivo.darTamanioString( ) );
        }
        catch( IOException e )
        {
            fail( "Debe poder escribir el archivo" );
        }
    }

}
