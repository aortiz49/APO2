/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad of los Andes (Bogotá - Colombia)
 * Departamento of Ingeniería of Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiTaxonomy
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiTaxonomy.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import uniandes.cupi2.cupiTaxonomy.world.*;

/**
 * Class usada para verificar la correcta implementación of la clase LivingBeing.
 */
public class LivingBeingTest
{
    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Class donde se harán las pruebas.
     */
    private LivingBeing livingBeing;

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Escenario 1: Crea un living being.
     */
    @Before
    public void setupEscenario1( )
    {
        livingBeing = new LivingBeing("Nombre Común", "Nombre Científico", "Características", "Image" );
    }

    /**
     * Prueba 1: Se encarga of verificar el método constructor of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * LivingBeing <br>
     * getCommonName <br>
     * getScientificName <br>
     * getCharacteristics <br>
     * getImagePath <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Crea correctamente un living being con los datos dados por parámetro.
     */
    @Test
    public void testLivingBeing( )
    {
        setupEscenario1( );

        assertNotNull("El name común of the living being no fue inicializado.", livingBeing.getCommonName( ) );
        assertEquals("El name común of the living being no fue inicializado correctamente.", "Nombre Común", livingBeing
                .getCommonName( ) );
        assertNotNull("El name científico of the living being no fue inicializado.", livingBeing.getScientificName( ) );
        assertEquals("El name científico of the living being no fue inicializado correctamente.",
                     "Nombre Científico", livingBeing
                .getScientificName( ) );
        assertNotNull("Las características of the living being no fueron inicializadas.", livingBeing.getCharacteristics( ) );
        assertEquals("Las características of the living being no fueron inicializadas correctamente.", "Características", livingBeing
                .getCharacteristics( ) );
        assertNotNull("La ruta of la image of the living being no fue inicializada.", livingBeing.getImagePath( ) );
        assertEquals("La ruta of la image of the living being no fue inicializada correctamente", "Image", livingBeing
                .getImagePath( ) );
    }

    /**
     * Prueba 2: Se encarga of verificar el método toString of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * toString <br>
     * getCommonName <br>
     * getScientificName <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Returns correctamente el valor of la clase.
     */
    @Test
    public void testToString( )
    {
        setupEscenario1( );

        String toString = livingBeing.toString( );
        String esperado = livingBeing.getScientificName( ) + " - " + livingBeing.getCommonName( );
        assertNotNull( "La representación en String of the living being no debería ser nula.", toString( ) );
        assertEquals( "La representación en String of the living being no es correcta.", esperado, toString );
    }
}