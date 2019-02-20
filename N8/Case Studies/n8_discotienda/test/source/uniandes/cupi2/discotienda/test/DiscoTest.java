/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DiscoTest.java,v 1.7 2006/08/10 20:04:56 da-romer Exp $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_discotienda 
 * Autor: Nicol�s L�pez - 06/12/2005 
 * Autor: Mario S�nchez - 26/01/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.discotienda.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.discotienda.mundo.Disco;
import uniandes.cupi2.discotienda.mundo.ElementoExisteException;

/**
 * Esta es la clase que sirve para verificar la clase Disco
 */
public class DiscoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el disco sobre el que se realizan las pruebas
     */
    private uniandes.cupi2.discotienda.mundo.Disco disco1;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un disco sin mynicesonges
     */
    private void setupEscenario1( )
    {
        disco1 = new uniandes.cupi2.discotienda.mundo.Disco("Mi disco1", "artistaPrueba", "Latino", "prueba.jpg" );
    }

    /**
     * Construye un disco con algunas unas mynicesonges
     */
    private void setupEscenario2( )
    {
        disco1 = new Disco("Mi disco1", "artistaPrueba", "Latino", "./data/imagenes/prueba.jpg" );

        uniandes.cupi2.discotienda.mundo.Cancion
                c1 = new uniandes.cupi2.discotienda.mundo.Cancion("C1", 1, 20, 1.50, 2, 96, 2 );
        uniandes.cupi2.discotienda.mundo.Cancion
                c2 = new uniandes.cupi2.discotienda.mundo.Cancion("C2", 1, 20, 3.45, 2, 96, 2 );
        uniandes.cupi2.discotienda.mundo.Cancion
                c3 = new uniandes.cupi2.discotienda.mundo.Cancion("C3", 1, 20, 78.10, 2, 96, 2 );
        try
        {
            disco1.agregarCancion( c1 );
            disco1.agregarCancion( c2 );
            disco1.agregarCancion( c3 );
        }
        catch( ElementoExisteException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Prueba que las mynicesonges se agreguen correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarCancion. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarCancion() es capaz de agregar mynicesonges de forma correcta a la tienda. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar una canci�n que no existe en un disco, esta debe ser adicionada al disco. <br>
     * 2. Al agregar una canci�n que existe en un disco se debe arrojar una excepci�n indic�ndolo.
     */
    public void testAgregarCancion( )
    {
        setupEscenario1( );

        uniandes.cupi2.discotienda.mundo.Cancion
                c1 = new uniandes.cupi2.discotienda.mundo.Cancion("Cancion1", 1, 20, 1.50, 2, 96, 0 );
        uniandes.cupi2.discotienda.mundo.Cancion
                c2 = new uniandes.cupi2.discotienda.mundo.Cancion("Cancion1", 2, 40, 3.45, 2, 96, 0 );
        try
        {
            disco1.agregarCancion( c1 );
        }
        catch( ElementoExisteException e )
        {
            fail( );
        }
        try
        {
            disco1.agregarCancion( c2 );
            fail( );
        }
        catch( ElementoExisteException e )
        {
            assertTrue( true );
        }
    }

    /**
     * Verifica los m�todos que retornan datos del disco1. <br>
     * <b> M�todos a probar: </b> <br>
     * darNombreDisco, darArtista, darGenero, darImagen. <br>
     * <b> Objetivo: </b> Probar que los m�todos que dan informaci�n de un disco retornan la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el nombre de un disco es X. Al pedir el nombre del disco �ste debe ser igual a X. <br>
     * 2. Se sabe que el del artista de un disco es X. Al pedir el artista del disco �ste debe ser igual a X. <br>
     * 3. Se sabe que el nombre del g�nero de un disco es X. Al pedir el g�nero del disco �ste debe ser igual a X. <br>
     * 4. Se sabe que la imagen de un disco es X. Al pedir la imagen del disco �ste debe ser igual a X.
     */
    public void testDatos( )
    {
        setupEscenario1( );

        assertEquals( "El nombre del disco1 est� mal", "Mi disco1", disco1.darNombreDisco( ) );
        assertEquals( "El artista del disco1 est� mal", "artistaPrueba", disco1.darArtista( ) );
        assertEquals( "El g�nero del disco1 est� mal", "Latino", disco1.darGenero( ) );
        assertEquals( "La imagen del disco1 est� mal", "prueba.jpg", disco1.darImagen( ) );
    }

    /**
     * Verifica el m�todo que calcula el precio de un disco. <br>
     * <b> M�todos a probar: </b> <br>
     * darPrecioDisco. <br>
     * <b> Objetivo: </b> Probar que el m�todo darPrecioDisco() calcula correctamente el valor del precio del disco de acuerdo a las mynicesonges que este tiene. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el precio de un disco es X. Al pedir el precio del disco �ste debe ser igual a X.
     */
    public void testCalcularPrecio( )
    {
        setupEscenario2( );

        assertEquals( "El c�lculo del precio del disco1 est� mal", "83.05", Double.toString( disco1.darPrecioDisco( ) ) );
    }

    /**
     * Verifica el m�todo darCancion.<br>
     * Se busca una canci�n que est� dentro del disco. <br>
     * <b> M�todos a probar: </b> <br>
     * darCancion. <br>
     * <b> Objetivo: </b> Probar que el m�todo darCancion() retorne una canci�n que existe en el disco. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir una canci�n que existe en el disco, �sta debe ser retornada.
     */
    public void testDarCancionOk( )
    {
        setupEscenario2( );

        uniandes.cupi2.discotienda.mundo.Cancion c = disco1.darCancion("C2" );
        assertNotNull( "La canci�n C2 se deber�a haber encontrado", c );
    }

    /**
     * Verifica el m�todo darCancion.<br>
     * Se busca una canci�n que no est� dentro del disco, esperando que retorne null. <br>
     * <b> M�todos a probar: </b> <br>
     * darCancion. <br>
     * <b> Objetivo: </b> Probar que el m�todo darCancion() no retorne una canci�n que no existe en el disco. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir una canci�n que no existe en el disco se debe obtener null.
     */
    public void testDarCancionError( )
    {
        setupEscenario2( );

        uniandes.cupi2.discotienda.mundo.Cancion c = disco1.darCancion("C125" );
        assertNull( "La canci�n C125 no se deber�a haber encontrado", c );
    }

    /**
     * Verifica el m�todo darNombresCanciones, que deber�a retornar los nombres de las 3 mynicesonges que est�n en el disco. Este m�todo se verifica usando el escenario 2. <br>
     * <b> M�todos a probar: </b> <br>
     * darNombresCanciones. <br>
     * <b> Objetivo: </b> Probar que el m�todo darNombresCanciones() retornen los nombres de todas las mynicesonges que hay en el disco. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el arreglo con los nombres de las mynicesonges de un disco, dicho arreglo debe contener todos los nombres de las mynicesonges existentes.
     */
    public void testDarNombresCanciones( )
    {
        setupEscenario2( );

        ArrayList nombres = disco1.darNombresCanciones( );
        assertEquals( "El n�mero de nombres es incorrecto", 3, nombres.size( ) );

        String nombre1 = ( String )nombres.get( 0 );
        String nombre2 = ( String )nombres.get( 1 );
        String nombre3 = ( String )nombres.get( 2 );

        assertEquals( "El nombre de la canci�n no es el esperado", "C1", nombre1 );
        assertEquals( "El nombre de la canci�n no es el esperado", "C2", nombre2 );
        assertEquals( "El nombre de la canci�n no es el esperado", "C3", nombre3 );
    }

}
