/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiTaxonomia
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.cupiTaxonomia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import uniandes.cupi2.cupiTaxonomia.world.*;

/**
 * Clase usada para verificar la correcta implementaci�n de la clase SerVivo.
 */
public class SerVivoTest
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private SerVivo serVivo;

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Escenario 1: Crea un ser vivo.
     */
    @Before
    public void setupEscenario1( )
    {
        serVivo = new SerVivo("Nombre Com�n", "Nombre Cient�fico", "Caracter�sticas", "Imagen" );
    }

    /**
     * Prueba 1: Se encarga de verificar el m�todo constructor de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * SerVivo <br>
     * darNombreComun <br>
     * darNombreCientifico <br>
     * darCaracteristicas <br>
     * darRutaImagen <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Crea correctamente un ser vivo con los datos dados por par�metro.
     */
    @Test
    public void testSerVivo( )
    {
        setupEscenario1( );

        assertNotNull( "El nombre com�n del ser vivo no fue inicializado.", serVivo.darNombreComun( ) );
        assertEquals( "El nombre com�n del ser vivo no fue inicializado correctamente.", "Nombre Com�n", serVivo.darNombreComun( ) );
        assertNotNull( "El nombre cient�fico del ser vivo no fue inicializado.", serVivo.darNombreCientifico( ) );
        assertEquals( "El nombre cient�fico del ser vivo no fue inicializado correctamente.", "Nombre Cient�fico", serVivo.darNombreCientifico( ) );
        assertNotNull( "Las caracter�sticas del ser vivo no fueron inicializadas.", serVivo.darCaracteristicas( ) );
        assertEquals( "Las caracter�sticas del ser vivo no fueron inicializadas correctamente.", "Caracter�sticas", serVivo.darCaracteristicas( ) );
        assertNotNull( "La ruta de la imagen del ser vivo no fue inicializada.", serVivo.darRutaImagen( ) );
        assertEquals( "La ruta de la imagen del ser vivo no fue inicializada correctamente", "Imagen", serVivo.darRutaImagen( ) );
    }

    /**
     * Prueba 2: Se encarga de verificar el m�todo toString de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * toString <br>
     * darNombreComun <br>
     * darNombreCientifico <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Retorna correctamente el valor de la clase.
     */
    @Test
    public void testToString( )
    {
        setupEscenario1( );

        String toString = serVivo.toString( );
        String esperado = serVivo.darNombreCientifico( ) + " - " + serVivo.darNombreComun( );
        assertNotNull( "La representaci�n en String del ser vivo no deber�a ser nula.", toString( ) );
        assertEquals( "La representaci�n en String del ser vivo no es correcta.", esperado, toString );
    }
}