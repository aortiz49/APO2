/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: EmpresaTest.java,v 1.10 2006/10/30 15:50:45 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005
 *
 * Proyecto Cupi2
 * Ejercicio: n11_organigrama
 * Autor: Mario Sánchez - 21-nov-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.organigrama.test;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.organigrama.mundo.Cargo;
import uniandes.cupi2.organigrama.mundo.Empleado;
import uniandes.cupi2.organigrama.mundo.Empresa;
import uniandes.cupi2.organigrama.mundo.OrganigramaException;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Empresa estén correctamente implementados
 */
public class EmpresaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Empresa empresa;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una empresa vacía
     */
    private void setupEscenario0( )
    {
        try
        {
            empresa = new Empresa( "./test/data/empresa0.dat" );
        }
        catch( OrganigramaException e )
        {
            fail( "Esta excepción no debería arrojarse" );
        }
    }

    /**
     * Construye una empresa y lo inicializa con algunos cargos
     */
    private void setupEscenario1( )
    {
        try
        {
            empresa = new Empresa( "./test/data/empresa1.dat" );
            empresa.crearCargo( "Gerente General", 50000, "" );
            empresa.crearCargo( "G. Comercial", 20000, "Gerente General" );
            empresa.crearCargo( "Coordinador Ventas", 50000, "G. Comercial" );
            empresa.crearCargo( "Vendedor 0", 100000, "Coordinador Ventas" );
            empresa.crearCargo( "Vendedor 1", 250000, "Coordinador Ventas" );
            empresa.crearCargo( "Vendedor 2", 1000000, "Coordinador Ventas" );
            empresa.crearCargo( "Vendedor 3", 2000000, "Coordinador Ventas" );
            empresa.crearCargo( "Vendedor 4", 500000, "Coordinador Ventas" );
            empresa.crearCargo( "G. de Tecnología", 250000, "Gerente General" );
            empresa.crearCargo( "Arquitecto 1", 250000, "G. de Tecnología" );
            empresa.crearCargo( "Arquitecto 2", 350000, "G. de Tecnología" );
            empresa.crearCargo( "G. Financiero", 500000, "Gerente General" );
            empresa.crearCargo( "Contador1", 600000, "G. Financiero" );
            empresa.crearCargo( "Contador2", 750000, "G. Financiero" );
            empresa.crearCargo( "Contador3", 650000, "G. Financiero" );
        }
        catch( OrganigramaException e )
        {
            fail( "Esta excepción no debería arrojarse" );
        }
    }

    /**
     * Construye una empresa y lo inicializa con algunos cargos y empleados
     */
    private void setupEscenario2( )
    {
        try
        {
            File archi = new File( "./test/data/empresa2.dat" );
            if( archi.exists( ) )
            {
                archi.delete( );
            }
            empresa = new Empresa( "./test/data/empresa2.dat" );
            empresa.crearCargo( "Gerente General", 50000, "" );
            empresa.crearCargo( "G. Comercial", 20000, "Gerente General" );
            empresa.crearCargo( "Coordinador Ventas", 50000, "G. Comercial" );
            empresa.crearCargo( "Vendedor 0", 100000, "Coordinador Ventas" );
            empresa.crearCargo( "Vendedor 1", 250000, "Coordinador Ventas" );
            empresa.crearCargo( "Vendedor 2", 1000000, "Coordinador Ventas" );
            empresa.crearCargo( "Vendedor 3", 2000000, "Coordinador Ventas" );
            empresa.crearCargo( "Vendedor 4", 500000, "Coordinador Ventas" );
            empresa.crearCargo( "G. de Tecnología", 250000, "Gerente General" );
            empresa.crearCargo( "Arquitecto 1", 250000, "G. de Tecnología" );
            empresa.crearCargo( "Arquitecto 2", 350000, "G. de Tecnología" );
            empresa.crearCargo( "G. Financiero", 500000, "Gerente General" );
            empresa.crearCargo( "Contador1", 600000, "G. Financiero" );
            empresa.crearCargo( "Contador2", 750000, "G. Financiero" );
            empresa.crearCargo( "Contador3", 650000, "G. Financiero" );
            empresa.crearCargo( "Contador4", 650000, "G. Financiero" );
            empresa.crearCargo( "Contador5", 650000, "G. Financiero" );
            empresa.crearCargo( "Contador6", 650000, "G. Financiero" );
            empresa.crearCargo( "Contador8", 650000, "G. Financiero" );

            empresa.contratarPersona( "555", "Diana Ortiz", new Date( System.currentTimeMillis( ) / 2 ), "Contador1" );
            empresa.contratarPersona( "556", "Carlos Mendez", new Date( ), "Contador3" );
            empresa.contratarPersona( "557", "Daniel Castro", new Date( ), "Gerente General" );
            empresa.contratarPersona( "558", "Francisco Guiterrez", new Date( ), "Coordinador Ventas" );
            empresa.contratarPersona( "559", "Pedro Pérez", new Date( ), "Vendedor 0" );
            empresa.contratarPersona( "560", "Francisco Miguel", new Date( ), "Arquitecto 2" );
        }
        catch( OrganigramaException e )
        {
            fail( "Esta excepción no debería arrojarse" );
        }
    }

    /**
     * Prueba el método crearCargo. <br>
     * <b> Métodos a probar: </b> <br>
     * crearCargo, buscarCargo. <br>
     * <b> Objetivo: </b> Probar que el método crearCargo() se capaz de crear correctamente un cargo en la empresa. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un cargo cuyo nombre no corresponda a otro cargo existente, al buscarlo éste debe ser encontrado.
     */
    public void testCrearCargoOK( )
    {
        setupEscenario1( );

        try
        {
            empresa.crearCargo( "Vendedor puerta a puerta", 500000, "Gerente General" );
            empresa.crearCargo( "Vendedor puerta a puerta2", 700000, "Vendedor 4" );
            empresa.crearCargo( "Vendedor puerta a puerta3", 700000, "Contador2" );
        }
        catch( OrganigramaException e )
        {
            fail( "No se debió arrojar excepción" );
        }

        Cargo c = empresa.buscarCargo( "Vendedor puerta a puerta" );
        Cargo c2 = empresa.buscarCargo( "Vendedor puerta a puerta2" );
        Cargo c3 = empresa.buscarCargo( "Vendedor puerta a puerta3" );
        assertNotNull( "No se encontró el cargo recién creado", c );
        assertNotNull( "No se encontró el cargo recién creado", c2 );
        assertNotNull( "No se encontró el cargo recién creado", c3 );
    }

    /**
     * Prueba el método crearCargo. <br>
     * <b> Métodos a probar: </b> <br>
     * crearCargo, buscarCargo. <br>
     * <b> Objetivo: </b> Probar que el método crearCargo() arroje excepción cuando se intente crear un cargo con un <br>
     * nombre que corresponda a otro cargo o que el cargo del que depende no exista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un cargo cuyo nombre corresponda a otro cargo existente, se debe arrojar excepción. <br>
     * 2. Al crear un cargo cuyo cargo del que depende no exista, se debe arrojar excepción
     */
    public void testCrearCargoError( )
    {
        setupEscenario1( );

        try
        {
            // Cargo existente
            empresa.crearCargo( "Gerente General", 500000, "Vendedor 4" );
            fail( "Se debió arrojar excepción" );
        }
        catch( OrganigramaException e )
        {
            try
            {
                // Cargo existente
                empresa.crearCargo( "Vendedor 4", 800000, "gerente general" );
                fail( "Se debió arrojar excepción" );
            }
            catch( OrganigramaException e1 )
            {
                try
                {
                    // Cargo del que se depende no existe
                    empresa.crearCargo( "Vendedor7", 900000, "gente general" );
                    fail( "Se debió arrojar excepción" );
                }
                catch( OrganigramaException e2 )
                {
                    Cargo c = empresa.buscarCargo( "Vendedor7" );
                    assertNull( "No se debió encontrar el cargo que se trató de crear", c );
                }

            }
        }
    }

    /**
     * Verifica el método eliminarCargo. <br>
     * <b> Métodos a probar: </b> <br>
     * eliminarCargo, buscarCargo. <br>
     * <b> Objetivo: </b> Probar que el método eliminarCargo() sea capaz de eliminar un cargo existente <br>
     * en la empresa. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar un cargo existente, vacante y que no tenga cargos dependientes, éste no debe ser encontrado al buscarlo. <br>
     */
    public void testEliminarCargoOK( )
    {
        setupEscenario1( );

        // Cargo existente
        try
        {
            empresa.eliminarCargo( "Contador3" );
            empresa.eliminarCargo( "contador2" );
            empresa.eliminarCargo( "contador1" );

        }
        catch( OrganigramaException e )
        {
            e.printStackTrace( );
            fail( "No se debió arrojar excepción" );

        }
        Cargo c = empresa.buscarCargo( "contador1" );
        assertNull( "El cargo no debió encontrarse", c );

        c = empresa.buscarCargo( "contador3" );
        assertNull( "El cargo no debió encontrarse", c );

        c = empresa.buscarCargo( "contador2" );
        assertNull( "El cargo no debió encontrarse", c );

        assertEquals( "El número de empleados que se eliminó no es el correcto", 12, empresa.darListaCargos( ).size( ) );

    }

    /**
     * Verifica el método eliminarCargo. <br>
     * <b> Métodos a probar: </b> <br>
     * eliminarCargo, buscarCargo. <br>
     * <b> Objetivo: </b> Probar que el método eliminarCargo() arroje excepción al tratar de eliminar un cargo <br>
     * inexistente, un cargo no vacante que sea hoja o un cargo vacante que no sea hoja. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de eliminar un cargo inexistente, se debe arrojar excepción. <br>
     * 2. Al tratar de eliminar un cargo no vacante (sea o no hoja), se debe arrojar excepción. <br>
     * 3. Al tratar de eliminar un cargo vacante que no sea hoja, se debe arrojar excepción.
     */
    public void testEliminarCargoError( )
    {
        setupEscenario2( );

        try
        {
            // Cargo inexistente
            empresa.eliminarCargo( "gerente gener" );
            fail( "Se debió arrojar excepción" );
        }
        catch( OrganigramaException e )
        {
            assertEquals( "El número de empleados no debió cambiar", 19, empresa.darListaCargos( ).size( ) );

            try
            {
                // Cargo existente pero no vacante (y que es hoja)
                empresa.eliminarCargo( "contador3" );
                fail( "Se debió arrojar excepción" );
            }
            catch( OrganigramaException e1 )
            {
                assertEquals( "El número de empleados no debió cambiar", 19, empresa.darListaCargos( ).size( ) );
                try
                {
                    // Cargo existente pero no vacante (y que no es hoja)
                    empresa.eliminarCargo( "gerente general" );
                    fail( "Se debió arrojar excepción" );
                }
                catch( OrganigramaException e2 )
                {
                    assertEquals( "El número de empleados no debió cambiar", 19, empresa.darListaCargos( ).size( ) );
                    try
                    {
                        // Cargo vacante (y que no es hoja)
                        empresa.eliminarCargo( "vendedor 0" );
                        fail( "Se debió arrojar excepción" );
                    }
                    catch( OrganigramaException e3 )
                    {
                        assertEquals( "El número de empleados no debió cambiar", 19, empresa.darListaCargos( ).size( ) );
                    }

                }
            }

        }
    }

    /**
     * Prueba el método crearCargo. <br>
     * <b> Métodos a probar: </b> <br>
     * contratarPersona, buscarEmpleado. <br>
     * <b> Objetivo: </b> Probar que el método contratarPersona() se capaz de crear correctamente <br>
     * una persona en el organigrama de la empresa. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un empleado cuyo cargo exista, al buscarlo éste debe ser encontrado.
     */
    public void testContratarPersonaOK( )
    {
        setupEscenario1( );

        try
        {
            empresa.crearCargo( "Vendedor puerta a puerta", 0, "contador1" );
            empresa.contratarPersona( "555", "Carlos Ochoa", new Date( ), "Vendedor Puerta a Puerta" );
            empresa.contratarPersona( "557", "Marcela Mantilla", new Date( ), "Gerente General" );
            empresa.contratarPersona( "558", "Paola Duque", new Date( ), "contador1" );
        }
        catch( OrganigramaException e )
        {
            fail( "No se debió arrojar excepción" );
        }

        Empleado e = empresa.buscarEmpleado( "555" );
        Empleado e2 = empresa.buscarEmpleado( "557" );
        Empleado e3 = empresa.buscarEmpleado( "558" );
        assertNotNull( "No se encontró el empleado recién creado", e );
        assertNotNull( "No se encontró el empleado recién creado", e2 );
        assertNotNull( "No se encontró el empleado recién creado", e3 );
    }

    /**
     * Prueba el método crearCargo. <br>
     * <b> Métodos a probar: </b> <br>
     * contratarPersona, buscarEmpleado. <br>
     * <b> Objetivo: </b> Probar que el método contratarPersona() arroje excepción cuando se trate de crear una persona con un código existente o con un cargo inválido. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de crear un empleado cuyo cargo no exista, se debe arrojar excepción. <br>
     * 2. Al tratar de crear un empleado cuyo código ya este asiganado a otro empleado, se debe arrojar excepción. <br>
     */
    public void testContratarPersonaError( )
    {
        setupEscenario2( );

        try
        {
            // Código repetido
            empresa.contratarPersona( "555", "Carlos Ochoa", new Date( ), "Contador1" );
            fail( "Se debió arrojar excepción" );
        }
        catch( OrganigramaException e )
        {
            try
            {
                // Cargo inexistente
                empresa.contratarPersona( "600", "Juan Pérez", new Date( ), "Contador1000" );
                fail( "Se debió arrojar excepción" );
            }
            catch( OrganigramaException e1 )
            {
                Empleado em = empresa.buscarEmpleado( "600" );
                assertNull( "No se debió encontrar el empleado", em );
                try
                {
                    // Cargo inexistente y código repetido
                    empresa.contratarPersona( "557", "Maritza Bueno", new Date( ), "G.General" );
                    fail( "Se debió arrojar excepción" );
                }
                catch( OrganigramaException e2 )
                {
                }
            }

        }

    }

    /**
     * Verifica el método buscarEmpleado. <br>
     * <b> Métodos a probar: </b> <br>
     * buscarEmpleado. <br>
     * <b> Objetivo: </b> Probar que el método buscarEmpleado() sea capaz de encontrar un empleado <br>
     * existente en la empresa <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un empleado existente en la empresa, éste debe ser encontrado. <br>
     * 2. Al buscar un empleado que no pertenezca a la empresa, éste no debe ser encontrado. <br>
     */
    public void testBuscarEmpleado( )
    {
        setupEscenario2( );

        // Empleado existente
        Empleado e = empresa.buscarEmpleado( "558" );
        assertNotNull( "El empleado debió encontrarse", e );

        // Empleado existente
        e = empresa.buscarEmpleado( "560" );
        assertNotNull( "El empleado debió encontrarse", e );

        // Empleado inexistente
        e = empresa.buscarEmpleado( "1" );
        assertNull( "El empleado no debió encontrarse", e );
    }

    /**
     * Verifica el método buscarCargoEmpleado. <br>
     * <b> Métodos a probar: </b> <br>
     * buscarCargoEmpleado. <br>
     * <b> Objetivo: </b> Probar que el método buscarCargoEmpleado() sea capaz de encontrar el cargo asociado <br>
     * con un empleado existente en la empresa <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el cargo de un empleado existente en la empresa, éste debe ser encontrado. <br>
     * 2. Al buscar el cargo un empleado que no pertenezca a la empresa, éste no debe ser encontrado. <br>
     */
    public void testBuscarCargoEmpleado( )
    {
        setupEscenario2( );

        // Empleado existente
        Cargo c = empresa.buscarCargoEmpleado( "558" );
        assertNotNull( "El cargo debió encontrarse", c );

        // Empleado existente
        c = empresa.buscarCargoEmpleado( "555" );
        assertNotNull( "El cargo debió encontrarse", c );

        // Empleado inexistente
        c = empresa.buscarCargoEmpleado( "1000" );
        assertNull( "El cargo no debió encontrarse", c );
    }

    /**
     * Verifica el método contarCargos. <br>
     * <b> Métodos a probar: </b> <br>
     * contarCargos. <br>
     * <b> Objetivo: </b> Probar que el método contarCargos() retorne correctamente el número <br>
     * de cargos de la empresa <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el la cantidad de cargos es X. Al pedir el número de cargos se debe retornar X.
     */
    public void testContarCargos( )
    {
        setupEscenario0( );

        assertEquals( "El número de cargos es incorrecto", 0, empresa.contarCargos( ) );

        setupEscenario1( );

        assertEquals( "El número de cargos es incorrecto", 15, empresa.contarCargos( ) );

        setupEscenario2( );

        assertEquals( "El número de cargos es incorrecto", 19, empresa.contarCargos( ) );
    }

    /**
     * Verifica el método darListaCargos. <br>
     * <b> Métodos a probar: </b> <br>
     * darListaCargos. <br>
     * <b> Objetivo: </b> Probar que el método darListaCargos() retorne correctamente la lista de cargos <br>
     * de la empresa <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el la cantidad de cargos es X. Al pedir el la lista de cargos su tamaño debe ser X. <br>
     */
    public void testDarListaCargos( )
    {
        setupEscenario1( );

        Collection lista = empresa.darListaCargos( );
        assertEquals( "El tamaño de la lista es incorrecto", 15, lista.size( ) );

        setupEscenario2( );

        lista = empresa.darListaCargos( );
        assertEquals( "El tamaño de la lista es incorrecto", 19, lista.size( ) );
    }

    /**
     * Verifica el método darListaCargosDisponibles. <br>
     * <b> Métodos a probar: </b> <br>
     * darListaCargosDisponibles. <br>
     * <b> Objetivo: </b> Probar que el método darListaCargosDisponibles() retorne correctamente la lista de cargos <br>
     * de la empresa que están vacantes <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el la cantidad de cargos vacantes es X. Al pedir la lista de cargos vacantes su tamaño debe ser X. <br>
     */
    public void testDarListaCargosDisponibles( )
    {
        setupEscenario0( );

        Collection lista = empresa.darListaCargosDisponibles( );
        assertEquals( "El tamaño de la lista es incorrecto", 0, lista.size( ) );

        setupEscenario1( );

        lista = empresa.darListaCargosDisponibles( );
        assertEquals( "El tamaño de la lista es incorrecto", 15, lista.size( ) );

        setupEscenario2( );

        lista = empresa.darListaCargosDisponibles( );
        assertEquals( "El tamaño de la lista es incorrecto", 13, lista.size( ) );
    }

    /**
     * Verifica el método guardar. <br>
     * <b> Métodos a probar: </b> <br>
     * guardar, Empresa. <br>
     * <b> Objetivo: </b> Probar que el método guardar() salve correctamente el organigrama <br>
     * de la empresa <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al guardar el estado de la empresa, al recuperarlo éste debe ser el mismo.
     */
    public void testGuardar( )
    {
        setupEscenario2( );

        try
        {
            empresa.guardar( );

            empresa = new Empresa( "./test/data/empresa2.dat" );

            Collection lista = empresa.darListaCargos( );
            assertEquals( "El tamaño de la lista es incorrecto", 19, lista.size( ) );

            for( int cont = 555; cont <= 560; cont++ )
            {
                Empleado em = empresa.buscarEmpleado( Integer.toString( cont ) );

                assertNotNull( "El empleado debió encontrarse", em );
            }

        }
        catch( OrganigramaException e )
        {
            fail( "No se debió arrojar excepción" );
        }

    }
}