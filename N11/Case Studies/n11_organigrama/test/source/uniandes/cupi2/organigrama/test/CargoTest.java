/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CargoTest.java,v 1.7 2006/10/30 15:50:45 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_organigrama
 * Autor: Jorge Villalobos - 20-oct-2006
 * Autor: Mario S�nchez - 21-nov-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.organigrama.test;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.organigrama.mundo.Cargo;
import uniandes.cupi2.organigrama.mundo.Empleado;
import uniandes.cupi2.organigrama.mundo.OrganigramaException;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Cargo est�n correctamente implementados
 */
public class CargoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Cargo cargo;

    /**
     * Crear un cargo sin empleado asociado
     * 
     */
    private void setupEscenario1( )
    {
        cargo = new Cargo( "cargo1", 1000, null );
    }

    /**
     * Crear una jerarqu�a de cargos de tres niveles sin empleados asociados
     * 
     */
    private void setupEscenario2( )
    {
        cargo = new Cargo( "cargo1", 1000, null );
        int numCargos = 20;
        int numCargosJerarquia = 1;

        for( int cont = 0; cont < numCargos; cont++ )
        {
            numCargosJerarquia++;
            cargo.agregarCargo( "cargo" + numCargosJerarquia, 400000 );
            assertEquals( "El n�mero de cargos no es correcto", cont + 2, cargo.darPeso( ) );

        }

        ArrayList subalternos = cargo.darSubAlternos( );

        for( int cont = 0; cont < subalternos.size( ); cont++ )
        {
            Cargo c = ( Cargo )subalternos.get( cont );

            for( int cont2 = 0; cont2 < numCargos; cont2++ )
            {
                numCargosJerarquia++;
                c.agregarCargo( "cargo" + numCargosJerarquia, 400000 );
                assertEquals( "El n�mero de cargos no es correcto", cont2 + 2, c.darPeso( ) );
            }
        }

        for( int cont = 0; cont < subalternos.size( ); cont++ )
        {
            Cargo c = ( Cargo )subalternos.get( cont );

            for( int cont2 = 0; cont2 < numCargos; cont2++ )
            {
                numCargosJerarquia++;
                c.agregarCargo( "cargo" + numCargosJerarquia, 400000 );
                assertEquals( "El n�mero de cargos no es correcto", numCargos + cont2 + 2, c.darPeso( ) );
            }
        }
    }

    /**
     * Crear una jerarqu�a de cargos de tres niveles con empleados asociados
     * 
     */
    private void setupEscenario3( )
    {
        cargo = new Cargo( "cargo1", 1000, null );
        int numCargos = 20;
        int numCargosJerarquia = 1;

        try
        {
            for( int cont = 0; cont < numCargos; cont++ )
            {
                numCargosJerarquia++;
                cargo.agregarCargo( "cargo" + numCargosJerarquia, 400000 );
            }

            ArrayList subalternos = cargo.darSubAlternos( );

            for( int cont = 0; cont < subalternos.size( ); cont++ )
            {
                Cargo c = ( Cargo )subalternos.get( cont );

                for( int cont2 = 0; cont2 < numCargos; cont2++ )
                {
                    numCargosJerarquia++;
                    c.agregarCargo( "cargo" + numCargosJerarquia, 400000 );
                }
            }

            for( int cont = 0; cont < subalternos.size( ); cont++ )
            {
                Cargo c = ( Cargo )subalternos.get( cont );

                for( int cont2 = 0; cont2 < numCargos; cont2++ )
                {
                    numCargosJerarquia++;
                    c.agregarCargo( "cargo" + numCargosJerarquia, 400000 );
                }
            }

            for( int cont = 0; cont < numCargosJerarquia; cont++ )
            {
                Cargo c = cargo.buscarCargo( "cargo" + ( cont + 1 ) );
                c.contratar( "empleado" + cont, "nombre", new Date( ) );
            }
        }
        catch( OrganigramaException e )
        {
            fail( "No se deber�a arrojarse la excepci�n" );
        }

    }

    /**
     * Verifica que los cargos se adicionen correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarCargo, darSubAlternos, darPeso, darAltura. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarCargo() se capaz de agregar correctamente un cargo en la jerarqu�a. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al adicionar cargos, el peso y altura del �rbol deben aumentar.
     */
    public void testAgregarCargo( )
    {
        setupEscenario1( );

        int numCargos = 20;
        int numCargosJerarquia = 1;

        // Adici�n de cargos en la ra�z
        for( int cont = 0; cont < numCargos; cont++ )
        {
            numCargosJerarquia++;
            cargo.agregarCargo( "cargo" + numCargosJerarquia, 400000 );
            assertEquals( "El n�mero de cargos no es correcto", cont + 2, cargo.darPeso( ) );

        }

        assertEquals( "El n�mero de cargos no es correcto", 2, cargo.darAltura( ) );

        ArrayList subalternos = cargo.darSubAlternos( );

        assertEquals( "El n�mero de subalternos no es correcto", numCargos, subalternos.size( ) );

        // Adici�n de cargos en las hojas
        for( int cont = 0; cont < subalternos.size( ); cont++ )
        {
            Cargo c = ( Cargo )subalternos.get( cont );

            for( int cont2 = 0; cont2 < numCargos; cont2++ )
            {
                numCargosJerarquia++;
                c.agregarCargo( "cargo" + numCargosJerarquia, 400000 );
                assertEquals( "El n�mero de cargos no es correcto", cont2 + 2, c.darPeso( ) );
            }
        }

        assertEquals( "El n�mero de cargos no es correcto", 3, cargo.darAltura( ) );
        assertEquals( "El n�mero de cargos no es correcto", numCargosJerarquia, cargo.darPeso( ) );

        // Adici�n de cargos en nodos intermedios
        for( int cont = 0; cont < subalternos.size( ); cont++ )
        {
            Cargo c = ( Cargo )subalternos.get( cont );

            for( int cont2 = 0; cont2 < numCargos; cont2++ )
            {
                numCargosJerarquia++;
                c.agregarCargo( "cargo" + numCargosJerarquia, 400000 );
                assertEquals( "El n�mero de cargos no es correcto", numCargos + cont2 + 2, c.darPeso( ) );
            }
        }

        assertEquals( "El n�mero de cargos no es correcto", 3, cargo.darAltura( ) );
        assertEquals( "El n�mero de cargos no es correcto", numCargosJerarquia, cargo.darPeso( ) );

    }

    /**
     * Verifica el m�todo que se encarga de buscar cargos. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarCargo. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarCargo() sea capaz de encontrar los cargos existentes. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un cargo previamente agregado, �ste debe ser encontrado. <br>
     * 2. Al buscar un cargo que no exista se debe retornar null.
     */
    public void testBuscarCargo( )
    {
        setupEscenario2( );

        // Busca cargos que existen
        for( int cont = 0; cont < 821; cont++ )
        {
            Cargo c = cargo.buscarCargo( "cargo" + ( cont + 1 ) );
            assertNotNull( "El cargo debi� encontrarse", c );
        }

        // Busca cargos que no existen
        for( int cont = 0; cont < 821; cont++ )
        {
            Cargo c = cargo.buscarCargo( "cargo" + ( cont * -1 ) );
            assertNull( "El cargo no debi� encontrarse", c );
        }

    }

    /**
     * Verifica que la contrataci�n de empleados se efect�e de forma correcta. <br>
     * <b> M�todos a probar: </b> <br>
     * contratar, buscarCargo, buscarEmpleado. <br>
     * <b> Objetivo: </b> Probar que el m�todo contratar() se capaz de asociar correctamente un empleado con un cargo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al contratar un empleado, �ste debe quedar asociado con el cargo en el que fue contratado.
     */
    public void testContratarOK( )
    {
        setupEscenario2( );

        // Le asigna a cada cargo un empleado
        for( int cont = 0; cont < 821; cont++ )
        {
            Cargo c = cargo.buscarCargo( "cargo" + ( cont + 1 ) );
            try
            {
                c.contratar( "empleado" + cont, "nombre", new Date( ) );
            }
            catch( OrganigramaException e )
            {

                fail( "No se debi� arrojar excepci�n" );
            }
        }

        // Verifica que los empleados hayan sido agregados correctamente
        for( int cont = 0; cont < 821; cont++ )
        {
            Empleado em = cargo.buscarEmpleado( "empleado" + cont );

            assertNotNull( "El empleado debi� ser encontrado", em );

            Cargo c = cargo.buscarCargo( "cargo" + ( cont + 1 ) );

            em = c.darEmpleado( );
            assertEquals( "El empleado no se asigno de forma correcta", "empleado" + cont, em.darCodigo( ) );

        }
    }

    /**
     * Verifica que la contrataci�n de empleados se efect�e de forma correcta. <br>
     * <b> M�todos a probar: </b> <br>
     * contratar. <br>
     * <b> Objetivo: </b> Probar que el m�todo contratar() arroje excepci�n si el cargo en el que se <br>
     * va a contratar el empleado ya se encuentra ocupado. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de contratar un empleado en un cargo ocupado se debe arrojar excepci�n.
     */
    public void testContratarError( )
    {
        setupEscenario3( );

        // Le asigna un empleado a un cargo ya ocupado
        try
        {
            cargo.contratar( "empleado1000", "nombre", new Date( ) );
            fail( "Se debi� arrojar excepci�n" );
        }
        catch( OrganigramaException e )
        {
        }
    }

    /**
     * Verifica el m�todo que se encarga de buscar el cargo de un empleado. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarCargoEmpleado. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarCargoEmpleado() se capaz de encontrar los cargos de los empleados <br>
     * de la empresa. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el cargo de un empleado existente, �ste debe ser encontrado. <br>
     * 2. Al buscar el cargo de un empleado inexistente, se debe retornar null.
     */
    public void testBuscarCargoEmpleado( )
    {
        setupEscenario3( );
        // Verifica que los cargos de los empleados existentes se encuentren
        for( int cont = 0; cont < 821; cont++ )
        {
            Cargo c = cargo.buscarCargoEmpleado( "empleado" + cont );
            assertNotNull( "El cargo debi� encontrarse", c );
        }

        // Verifica que no se encuentren cargos de empleados inexistentes
        for( int cont = 0; cont < 821; cont++ )
        {
            Cargo c = cargo.buscarCargoEmpleado( "empleado" + ( cont + 1 * 1000 ) );
            assertNull( "El cargo no debi� encontrarse", c );
        }
    }

    /**
     * Verifica el m�todo que se encarga de buscar un empleado. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarEmpleado. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarEmpleado() se capaz de encontrar los empleados <br>
     * de la empresa. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un empleado existente, �ste debe ser encontrado. <br>
     * 2. Al buscar un empleado inexistente, se debe retornar null.
     */
    public void testBuscarEmpleado( )
    {
        setupEscenario3( );
        // Verifica que los empleados existentes se encuentren
        for( int cont = 0; cont < 821; cont++ )
        {
            Empleado em = cargo.buscarEmpleado( "empleado" + cont );
            assertNotNull( "El empleado debi� encontrarse", em );
        }

        // Verifica que los empleados inexistentes no se encuentren
        for( int cont = 0; cont < 821; cont++ )
        {
            Empleado em = cargo.buscarEmpleado( "empleado" + ( cont + 1 * 1000 ) );
            assertNull( "El empleado no debi� encontrarse", em );
        }
    }

    /**
     * Verifica el m�todo que se encarga de buscar el cargo jefe de un cargo. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarJefe. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarJefe() se capaz de encontrar los cargos jefes <br>
     * de los cargos de la empresa <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el cargo jefe de un cargo existente, �ste debe ser encontrado. <br>
     * 2. Al buscar el cargo jefe de un cargo inexistente, se debe retornar null. <br>
     */
    public void testBuscarJefe( )
    {
        setupEscenario3( );
        // Verifica que los cargos jefes de los cargos existentes se encuentren
        for( int cont = 0; cont < 821; cont++ )
        {
            Cargo c = cargo.buscarJefe( "cargo" + ( cont + 1 ) );
            if( cont != 0 )
                assertNotNull( "El cargo jefe debi� encontrarse", c );
        }

        // Verifica que los cargos jefes de cargos inexistentes no se encuentren
        for( int cont = 0; cont < 821; cont++ )
        {
            Cargo c = cargo.buscarJefe( "cargo" + ( cont + 1 * 1000 ) );
            assertNull( "El cargo jefe no debi� encontrarse", c );
        }
    }

    /**
     * Verifica el m�todo que cuenta las hojas del �rbol. <br>
     * <b> M�todos a probar: </b> <br>
     * contarHojas. <br>
     * <b> Objetivo: </b> Probar que el m�todo contarHojas() retorne correctamente <br>
     * el n�mero de hojas del �rbol <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el n�mero de hojas es X. Al pedir el n�mero de hojas se debe obtener X.
     */
    public void testContarHojas( )
    {
        setupEscenario1( );
        assertEquals( "El n�mero de hojas no es el correcto", 1, cargo.contarHojas( ) );

        setupEscenario2( );
        assertEquals( "El n�mero de hojas no es el correcto", 800, cargo.contarHojas( ) );

        setupEscenario3( );
        assertEquals( "El n�mero de hojas no es el correcto", 800, cargo.contarHojas( ) );
    }

    /**
     * Verifica el m�todo que cuenta la altura del �rbol. <br>
     * <b> M�todos a probar: </b> <br>
     * darAltura. <br>
     * <b> Objetivo: </b> Probar que el m�todo darAltura() retorne correctamente <br>
     * la altura del �rbol <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que la altura es X. Al pedir la altura se debe obtener X.
     */
    public void testDarAltura( )
    {
        setupEscenario1( );
        assertEquals( "La altura no es correcta", 1, cargo.darAltura( ) );

        setupEscenario2( );
        assertEquals( "La altura no es correcta", 3, cargo.darAltura( ) );

        setupEscenario2( );
        assertEquals( "La altura no es correcta", 3, cargo.darAltura( ) );
    }

    /**
     * Verifica el m�todo que retorna la lista de cargos. <br>
     * <b> M�todos a probar: </b> <br>
     * darListaCargos. <br>
     * <b> Objetivo: </b> Probar que el m�todo darListaCargos() retorne correctamente <br>
     * los cargos del �rbol <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el n�mero de cargos es X. Al pedir la lista de cargos su tama�o debe ser X.
     */
    public void testDarListaCargos( )
    {
        setupEscenario1( );
        ArrayList lista = new ArrayList( );
        cargo.darListaCargos( lista );
        assertEquals( "El n�mero de cargos no es correcto", 1, lista.size( ) );

        setupEscenario2( );
        lista = new ArrayList( );
        cargo.darListaCargos( lista );
        assertEquals( "El n�mero de cargos no es correcto", 821, lista.size( ) );

        setupEscenario3( );
        lista = new ArrayList( );
        cargo.darListaCargos( lista );
        assertEquals( "El n�mero de cargos no es correcto", 821, lista.size( ) );

    }

    /**
     * Verifica el m�todo que retorna la lista de cargos disponibles. <br>
     * <b> M�todos a probar: </b> <br>
     * darListaCargosDisponibles. <br>
     * <b> Objetivo: </b> Probar que el m�todo darListaCargosDisponibles() retorne correctamente <br>
     * los cargos del �rbol que se encuentran disponibles <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el n�mero de cargos disponibles es X. Al pedir la lista de cargos disponibles su tama�o debe ser X.
     */
    public void testDarListaCargosDisponibles( )
    {
        setupEscenario1( );
        ArrayList lista = new ArrayList( );
        cargo.darListaCargosDisponibles( lista );
        assertEquals( "El n�mero de cargos no es correcto", 1, lista.size( ) );

        setupEscenario2( );
        lista = new ArrayList( );
        cargo.darListaCargosDisponibles( lista );
        assertEquals( "El n�mero de cargos no es correcto", 821, lista.size( ) );

        setupEscenario3( );
        lista = new ArrayList( );
        cargo.darListaCargosDisponibles( lista );
        assertEquals( "El n�mero de cargos no es correcto", 0, lista.size( ) );

    }

    /**
     * Verifica el m�todo que retorna el peso del �rbol. <br>
     * <b> M�todos a probar: </b> <br>
     * darPeso. <br>
     * <b> Objetivo: </b> Probar que el m�todo darPeso() retorne correctamente <br>
     * el peso del �rbol <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el peso del �rbol es X. Al pedir peso se debe obtener X.
     */
    public void testDarPeso( )
    {
        setupEscenario1( );
        assertEquals( "El n�mero de cargos no es el correcto", 1, cargo.darPeso( ) );

        setupEscenario2( );
        assertEquals( "El n�mero de cargos no es el correcto", 821, cargo.darPeso( ) );

        setupEscenario3( );
        assertEquals( "El n�mero de cargos no es el correcto", 821, cargo.darPeso( ) );
    }

    /**
     * Verifica el m�todo que elimina los cargos. <br>
     * <b> M�todos a probar: </b> <br>
     * eliminarCargo. <br>
     * <b> Objetivo: </b> Probar que el m�todo eliminarCargo() sea capaz de eliminar <br>
     * un cargo que sea hoja y este vacante <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar un cargo hoja y que este vacante, al buscarlo no debe ser encontrado.
     */
    public void eliminarCargoOK( )
    {
        setupEscenario3( );

        try
        {
            for( int cont = 0; cont < 800; cont++ )
            {
                cargo.eliminarCargo( "cargo" + ( cont + 21 ) );
            }

            assertEquals( "El peso del �rbol no es correcto", 21, cargo.darPeso( ) );
            for( int cont = 0; cont < 800; cont++ )
            {
                Cargo c = cargo.buscarCargo( "cargo" + ( cont + 21 ) );
                assertNull( "El cargo no deber�a existir", c );
            }
        }
        catch( OrganigramaException e )
        {
            fail( "No se debi� arrojar excepci�n" );
        }
    }

    /**
     * Verifica el m�todo que elimina los cargos. <br>
     * <b> M�todos a probar: </b> <br>
     * eliminarCargo. <br>
     * <b> Objetivo: </b> Probar que el m�todo eliminarCargo() arroje excepci�n <br>
     * al tratar eliminar un cargo no vacante o que no sea hoja <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de eliminar un cargo que no sea hoja, se debe arrojar excepci�n. <br>
     * 2. Al tratar de eliminar un cargo que sea hoja pero que no �ste vacante, se debe arrojar excepci�n. <br>
     */
    public void eliminarCargoError( )
    {
        setupEscenario2( );
        try
        {
            // El cargo est� vacante pero no es una hoja
            cargo.eliminarCargo( "cargo15" );
            fail( "Se debi� arrojar excepci�n" );

        }
        catch( OrganigramaException e )
        {
            assertEquals( "El n�mero de cargos no debi� cambiar", 821, cargo.darPeso( ) );
            setupEscenario3( );
            // El cargo no est� vacante pero es una hoja
            try
            {
                cargo.eliminarCargo( "cargo800" );
                fail( "Se debi� arrojar excepci�n" );
            }
            catch( OrganigramaException e1 )
            {
                assertEquals( "El n�mero de cargos no debi� cambiar", 821, cargo.darPeso( ) );
            }

        }
    }
}