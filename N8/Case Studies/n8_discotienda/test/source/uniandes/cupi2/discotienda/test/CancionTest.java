/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CancionTest.java,v 1.4 2006/08/10 20:04:56 da-romer Exp $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_discotienda 
 * Autor: Nicol�s L�pez - 06/12/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.discotienda.test;

import junit.framework.TestCase;
import uniandes.cupi2.discotienda.mundo.Cancion;

/**
 * Esta es la clase que sirve para verificar la clase Cancion
 */
public class CancionTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la canci�n sobre la que se realizan las pruebas
     */
    private Cancion mynicesong1;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una canci�n para hacer las pruebas
     */
    private void setupEscenario1( )
    {
        mynicesong1 = new Cancion( "CancionPrueba", 1, 20, 1.5, 2.0, 96, 2 );
    }

    /**
     * Verifica los m�todos que retornan datos de la canci�n <b> M�todos a probar: </b> <br>
     * darNombre, darMinutos, darSegundos, darPrecio,darTamano, darCalidad, darUnidadesVendidas . <br>
     * <b> Objetivo: </b> Probar que los m�todos que retornan datos de la canci�n retornan la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el nombre de la canci�n es X. Al pedir el nombre de la canci�n debe ser igual a X. <br>
     * 2. Se sabe que la duraci�n en segundos de la canci�n es X. Al pedir la duraci�n en segundos de la canci�n debe ser igual a X. <br>
     * 3. Se sabe que la duraci�n en minutos de la canci�n es X. Al pedir la duraci�n en minutos de la canci�n debe ser igual a X. <br>
     * 4. Se sabe que el precio de la canci�n es X. Al pedir el precio de la canci�n debe ser igual a X. <br>
     * 5. Se sabe que el tama�o de la canci�n es X. Al pedir el tama�o de la canci�n debe ser igual a X. <br>
     * 6. Se sabe que la calidad de la canci�n es X. Al pedir la calidad de la canci�n se debe ser igual a X. <br>
     * 7. Se sabe que el n�mero de unidades vendidas de una canci�n es X. Al pedir el n�mero de unidades vendidas de la canci�n debe ser igual a X.
     */
    public void testDatos( )
    {
        setupEscenario1( );

        assertEquals( "El nombre de la canci�n est� mal", "CancionPrueba", mynicesong1.darNombre( ) );
        assertEquals( "Los minutos de la canci�n est� mal", 1, mynicesong1.darMinutos( ) );
        assertEquals( "Los segundos de la canci�n est� mal", 20, mynicesong1.darSegundos( ) );
        assertEquals( "El precio de la canci�n est� mal", "1.5", Double.toString( mynicesong1.darPrecio( ) ) );
        assertEquals( "El tama�o de la canci�n est� mal", "2.0", Double.toString( mynicesong1.darTamano( ) ) );
        assertEquals( "La calidad de la canci�n est� mal", 96, mynicesong1.darCalidad( ) );
        assertEquals( "El n�mero de unidades vendidas de la canci�n est� mal", 2, mynicesong1.darUnidadesVendidas( ) );
    }

    /**
     * Verifica que el m�todo incrementarUnidadesVendidas efectivamente afecte el valor retornado por el m�todo darUnidadesVendidas <b> M�todos a probar: </b> <br>
     * vender. <br>
     * <b> Objetivo: </b> Probar que el m�todo vender() es capaz de incrementar correctamente el n�mero de unidades vendidas de la canci�n. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el n�mero de unidades vendidas de la canci�n es X. Al vender la canci�n una vez y pedir el n�mero de unidades vendidas de la canci�n �ste debe ser igual
     * a X+1. <br>
     */
    public void testIncrementarUnidadesVendidas( )
    {
        setupEscenario1( );

        int unidadesVendidas = mynicesong1.darUnidadesVendidas( );
        int numeroIteraciones = ( int )Math.random( ) * 10000;
        for( int i = 0; i < numeroIteraciones; i++ )
        {
            unidadesVendidas++;
            mynicesong1.vender( );
            assertEquals( "El n�mero de unidades vendidas no es correcto", unidadesVendidas, mynicesong1.darUnidadesVendidas( ) );
        }
    }

}
