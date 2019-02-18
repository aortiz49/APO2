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

import uniandes.cupi2.cupiSports.world.Athlete;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Clase usada para verificar que los m�todos de la clase Athlete est�n correctamente implementados.
 */
public class AthleteTest
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private Athlete deportista;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo deportista.s
     */
    @Before
    public void setupEscenario1( )
    {

        deportista = new Athlete( "James", 23, "Madrid", 39, "imagen" );

    }

    /**
     * Prueba 1: Verifica el m�todo constructor.<br>
     * <b> Methods a probar: </b> <br>
     * constructor<br>
     * getName<br>
     * getPlaceOfResidency<br>
     * getAmountOfTrophies<br>
     * getImagePath<br>
     * getAge<br>
     * <b> Objetivo: </b> Probar inicializaci�n correcta del objeto Athlete<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Inicializaci�n correcta de Athlete<br>
     */
    @Test
    public void testConstructor( )
    {
        assertEquals( "El name of the athlete es incorrecto.", "James", deportista.getName( ) );
        assertEquals( "La age of the athlete es incorrecta.", 23, deportista.getAge( ) );
        assertEquals( "El lugar de residencia of the athlete es incorrecto.", "Madrid", deportista.getPlaceOfResidency( ) );
        assertEquals( "La ruta a la imagen of the athlete es incorrecta.", "imagen", deportista.getImagePath( ) );
        assertEquals( "La cantidad de trofeos of the athlete es incorrecta.", 39, deportista.getAmountOfTrophies( ) );
    }

    /**
     * Prueba 2: Verifica el m�todo modifyAge.<br>
     * <b> Methods a probar: </b> <br>
     * modifyAge<br>
     * getAge <b><br>
     * Objetivo: </b> Probar funcionamiento correcta del m�todo modifyAge<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La age fue cambiada correctamente.<br>
     */
    @Test
    public void testCambiarAge( )
    {
        deportista.modifyAge( 30 );
        assertEquals( "La age of the athlete es incorrecta.", 30, deportista.getAge( ) );

    }

    /**
     * Prueba 3: Verifica el m�todo modifyPlaceOfResidency.<br>
     * <b> Methods a probar: </b> <br>
     * modifyPlaceOfResidency<br>
     * getPlaceOfResidency<br>
     * <b> Objetivo: </b> Probar funcionamiento correcta del m�todo modifyPlaceOfResidency<br>
     * <b> Resultados esperados: </b> <br>
     * 1. El lugar de residencia fue cambiado correctamente.<br>
     */
    @Test
    public void testCambiarPlaceOfResidency( )
    {
        deportista.modifyPlaceOfResidency( "Bogot�" );
        assertEquals( "El lugar de residencia of the athlete es incorrecto.", "Bogot�", deportista.getPlaceOfResidency( ) );

    }

    /**
     * Prueba 4: Verifica el m�todo modifyAmountOfTrophies.<br>
     * <b> Methods a probar: </b> <br>
     * modifyAmountOfTrophies<br>
     * getAmountOfTrophies <br>
     * <b> Objetivo: </b> Probar funcionamiento correcta del m�todo modifyAmountOfTrophies<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La cantidad de trofeos fue cambiada correctamente.<br>
     */
    @Test
    public void testCambiarAmountOfTrophies( )
    {
        deportista.modifyAmountOfTrophies( 10 );
        assertEquals( "La cantidad detrofeos of the athlete es incorrecta.", 10, deportista.getAmountOfTrophies( ) );

    }

    /**
     * Prueba 5: Verifica el m�todo modifyImagePath.<br>
     * <b> Methods a probar: </b> <br>
     * modifyImagePath<br>
     * getImagePath<br>
     * <b> Objetivo: </b> Probar funcionamiento correcta del m�todo modifyImagePath<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La ruta de la imagen fue cambiada correctamente.<br>
     */
    @Test
    public void testCambiarImagePath( )
    {
        deportista.modifyImagePath( "imagen2" );
        assertEquals( "La ruta de la imagen of the athlete es incorrecta.", "imagen2", deportista.getImagePath( ) );

    }

}