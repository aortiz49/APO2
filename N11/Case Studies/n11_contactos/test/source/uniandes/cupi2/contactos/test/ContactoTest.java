/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ContactoTest.java,v 1.5 2006/10/22 16:29:38 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005
 *
 * Proyecto Cupi2
 * Ejercicio: n11_contactos
 * Autor: Katalina Marcos - 27/10/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.contactos.test;

import java.util.*;

import junit.framework.*;
import uniandes.cupi2.contactos.mundo.*;

/**
 * Clase de prueba de la clase Contacto
 */
public class ContactoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Contacto 1
     */
    private Contacto contacto1;
    /**
     * Nombre del contacto 1
     */
    private String nombre1;
    /**
     * Teléfono del contacto 1
     */
    private String telefono1;
    /**
     * Dirección del contacto 1
     */
    private String direccion1;
    /**
     * Email del contacto 1
     */
    private String email1;

    // -----------------------------------------------------------------
    // Mètodos
    // -----------------------------------------------------------------

    /**
     * Crea un árbol de contactos con la información básica de contacto
     */
    private void setupEscenario1( )
    {
        nombre1 = "Marta";
        direccion1 = "dirección";
        telefono1 = "1234567";
        email1 = "marta@email.com";
        contacto1 = new Contacto( nombre1, telefono1, direccion1, email1 );
    }

    /**
     * Crea un escenario donde al contacto inicial le inserta un contacto menor
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );

        String nombre = "Alberto";
        String direccion = "dirección";
        String telefono = "1234567";
        String email = "alberto@email.com";
        Contacto contacto2 = new Contacto( nombre, telefono, direccion, email );

        try
        {
            contacto1.insertar( contacto2 );
        }
        catch( ContactoRepetidoException e )
        {
            fail( "El contacto debió agregarse" );
        }
    }

    /**
     * Crea un escenario donde al contacto inicial le inserta un contacto menor, teniendo el árbol ya dos nodos
     */
    private void setupEscenario3( )
    {
        setupEscenario2( );

        String nombre = "Paco";
        String direccion = "dirección";
        String telefono = "1234567";
        String email = "paco@email.com";
        Contacto contacto3 = new Contacto( nombre, telefono, direccion, email );

        try
        {
            contacto1.insertar( contacto3 );
        }
        catch( ContactoRepetidoException e )
        {
            fail( "El contacto debió agregarse" );
        }
    }

    /**
     * Crea un escenario con un árbol de contactos de 5 nodos teniendo el árbol ya tres nodos
     */
    private void setupEscenario4( )
    {
        setupEscenario3( );

        String nombre = "Luis";
        String direccion = "dirección";
        String telefono = "1234567";
        String email = "luis@email.com";
        Contacto contacto = new Contacto( nombre, telefono, direccion, email );
        try
        {
            contacto1.insertar( contacto );
        }
        catch( ContactoRepetidoException e )
        {
            fail( "El contacto debió agregarse" );
        }

        nombre = "Xiomara";
        direccion = "dirección";
        telefono = "1234567";
        email = "xiomara@email.com";
        contacto = new Contacto( nombre, telefono, direccion, email );
        try
        {
            contacto1.insertar( contacto );
        }
        catch( ContactoRepetidoException e )
        {
            fail( "El contacto debió agregarse" );
        }
    }

    /**
     * Prueba la creación inicial del árbol. <br>
     * <b> Métodos a probar: </b> <br>
     * Contacto, darAltura, darPeso, darPesoIterativo. <br>
     * <b> Objetivo: </b> Probar que el método Contacto() se capaz de crear correctamente el contacto. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un contacto y pedir su peso y altura éstos deben ser igual a 1.
     */
    public void testCrearArbolContactos( )
    {
        setupEscenario1( );

        // La información debe estar consistente.
        assertEquals( nombre1, contacto1.darNombre( ) );
        assertEquals( telefono1, contacto1.darTelefono( ) );
        assertEquals( direccion1, contacto1.darDireccion( ) );
        assertEquals( email1, contacto1.darEmail( ) );

        // La profundidad del árbol es 1
        assertEquals( 1, contacto1.darAltura( ) );

        // El peso del árbol es 1
        assertEquals( 1, contacto1.darPeso( ) );
        assertEquals( 1, contacto1.darPesoIterativo( ) );
    }

    /**
     * Prueba la inserción al lado izquierdo del árbol. <br>
     * <b> Métodos a probar: </b> <br>
     * Contacto, darAltura, darPeso, darPesoIterativo, insertar. <br>
     * <b> Objetivo: </b> Probar que el método insertar() inserte correctamente un contacto en el árbol de contactos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al insertar un contacto en un árbol compuesto sólo de un contacto se debe aumentar el peso y altura del árbol.
     */
    public void testInsertarIzquierda( )
    {
        setupEscenario2( );

        // El árbol tiene profundidad 2
        assertEquals( 2, contacto1.darAltura( ) );

        // El peso del árbol es 2
        assertEquals( 2, contacto1.darPeso( ) );
        assertEquals( 2, contacto1.darPesoIterativo( ) );
    }

    /**
     * Prueba la inserción al lado derecho del árbol en un árbol con 2 nodos. <br>
     * <b> Métodos a probar: </b> <br>
     * Contacto, darAltura, darPeso, darPesoIterativo, insertar. <br>
     * <b> Objetivo: </b> Probar que el método insertar() inserte correctamente un contacto en el árbol de contactos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al insertar un contacto en un árbol compuesto por la raíz y el hijo izquierdo, se debe aumentar el peso y altura del árbol.
     */
    public void testInsertarDerecha( )
    {
        setupEscenario3( );

        // El árbol tiene profundidad 2
        assertEquals( 2, contacto1.darAltura( ) );

        // El peso del árbol es 3
        assertEquals( 3, contacto1.darPeso( ) );
        assertEquals( 3, contacto1.darPesoIterativo( ) );
    }

    /**
     * Prueba la inserción de 5 nodos al árbol. <br>
     * <b> Métodos a probar: </b> <br>
     * Contacto, darAltura, darPeso, darPesoIterativo, insertar. <br>
     * <b> Objetivo: </b> Probar que el método insertar() inserte correctamente un contacto en el árbol de contactos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al insertar dos contacto en un árbol compuesto por la raíz y sus dos hijos, se debe aumentar el peso y altura del árbol.
     */
    public void testInsertar5Nodos( )
    {
        setupEscenario4( );

        // El árbol tiene profundidad 3
        assertEquals( 3, contacto1.darAltura( ) );

        // El peso del árbol es 5
        assertEquals( 5, contacto1.darPeso( ) );
        assertEquals( 5, contacto1.darPesoIterativo( ) );
    }

    /**
     * Prueba que al insertar un contacto repetido se genere el error. <br>
     * <b> Métodos a probar: </b> <br>
     * insertar. <br>
     * <b> Objetivo: </b> Probar que el método insertar() arroje excepción cuando se trate de adicionar un contacto repetido. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al insertar un contacto previamente existente en el árbol se debe arrojar excepción.
     */
    public void testInsertarRepetido( )
    {
        setupEscenario4( );
        String direccion = "dirección";
        String telefono = "1234567";
        String email = "nombre@email.com";
        Contacto contactoR = new Contacto( nombre1, telefono, direccion, email );
        try
        {
            contacto1.insertar( contactoR );
            fail( "El contacto estaba repetido y no podía ser insertado" );
        }
        catch( ContactoRepetidoException e )
        {
            assertTrue( "El contacto estaba repetido y debía generarse la excepción", true );
        }
    }

    /**
     * Prueba el método de búsqueda de contactos de forma iterativa. <br>
     * <b> Métodos a probar: </b> <br>
     * buscarIterativo. <br>
     * <b> Objetivo: </b> Probar que el método buscarIterativo() busque correctamente los contactos dados. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un contacto que no existe se debe retornar null. <br>
     * 2. Al buscar un contacto que existe éste debe ser retornado.
     */
    public void testBuscarIterativo( )
    {
        setupEscenario4( );

        Contacto c;

        // Busca un nombre que no existe
        c = contacto1.buscarIterativo( "Petronila" );
        assertNull( c );

        // Busca uno que si existe
        c = contacto1.buscarIterativo( "Marta" );
        assertNotNull( c );
        assertEquals( "Marta", c.darNombre( ) );

        // Busca a la raíz del árbol
        c = contacto1.buscarIterativo( nombre1 );
        assertNotNull( c );
        assertEquals( nombre1, c.darNombre( ) );

        // Busca al mayor
        c = contacto1.buscarIterativo( "Xiomara" );
        assertNotNull( c );
        assertEquals( "Xiomara", c.darNombre( ) );

        // Busca el menor
        c = contacto1.buscarIterativo( "Alberto" );
        assertNotNull( c );
        assertEquals( "Alberto", c.darNombre( ) );
    }

    /**
     * Prueba el método de búsqueda de contactos. <br>
     * <b> Métodos a probar: </b> <br>
     * buscar. <br>
     * <b> Objetivo: </b> probar que el método buscar() busque correctamente los contactos dados. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un contacto que no existe se debe retornar null. <br>
     * 2. Al buscar un contacto que existe éste debe ser retornado.
     */
    public void testBuscar( )
    {
        setupEscenario4( );

        Contacto c;

        // Busca un nombre que no existe
        c = contacto1.buscar( "Petronila" );
        assertNull( c );

        // Busca uno que si existe
        c = contacto1.buscar( "Marta" );
        assertNotNull( c );
        assertEquals( "Marta", c.darNombre( ) );

        // Busca a la raíz del árbol
        c = contacto1.buscar( nombre1 );
        assertNotNull( c );
        assertEquals( nombre1, c.darNombre( ) );

        // Busca al mayor
        c = contacto1.buscar( "Xiomara" );
        assertNotNull( c );
        assertEquals( "Xiomara", c.darNombre( ) );

        // Busca el menor
        c = contacto1.buscar( "Alberto" );
        assertNotNull( c );
        assertEquals( "Alberto", c.darNombre( ) );
    }

    /**
     * Prueba los algoritmos que retornan el mayor y el menor del árbol <b> Métodos a probar: </b> <br>
     * darMenor, darMayor. <br>
     * <b> Objetivo: </b> Probar que los métodos darMayor() y darMenor() retornen correctamente los contactos con los nombres que sean mayor y menor (lexicograficamente)
     * respectivamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el elemento menor del árbol se debe retornar el nombre menor lexicograficamente. <br>
     * 2. Al pedir el elemento mayor del árbol se debe retornar el nombre mayor lexicograficamente.
     */
    public void testMayorYMenor( )
    {
        setupEscenario4( );

        // Busca el primero
        Contacto c = contacto1.darMenor( );
        assertEquals( "Alberto", c.darNombre( ) );

        // Busca el último
        c = contacto1.darMayor( );
        assertEquals( "Xiomara", c.darNombre( ) );

    }

    /**
     * Prueba la eliminación de un nodo que es hoja del árbol. <br>
     * <b> Métodos a probar: </b> <br>
     * eliminar. <br>
     * <b> Objetivo: </b> Probar que el método eliminar() elimine correctamente un elemento del árbol. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar un elemento del árbol, el peso del árbol debe disminuir en 1. <br>
     * 2. Al eliminar un elemento del árbol al buscarlo se debe obtener null.
     */
    public void testEliminarHoja( )
    {
        setupEscenario4( );

        // Elimina al último
        Contacto c = contacto1.darMayor( );
        contacto1 = contacto1.eliminar( c.darNombre( ) );
        // El árbol tiene profundidad 3
        assertEquals( 3, contacto1.darAltura( ) );
        // El peso del árbol es 4
        assertEquals( 4, contacto1.darPeso( ) );
        assertEquals( 4, contacto1.darPesoIterativo( ) );
        // Ya no se encuentra el contacto
        assertNull( contacto1.buscar( c.darNombre( ) ) );

    }

    /**
     * Prueba borrando nodos del medio. <br>
     * <b> Métodos a probar: </b> <br>
     * eliminar. <br>
     * <b> Objetivo: </b> Probar que el método eliminar() elimine correctamente un elemento del árbol. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar un elemento del árbol, el peso del árbol debe disminuir en 1. <br>
     * 2. Al eliminar un elemento del árbol al buscarlo se debe obtener null.
     */
    public void testEliminarMedios( )
    {
        setupEscenario4( );
        contacto1 = contacto1.eliminar( "Paco" );
        // El árbol tiene profundidad 3
        assertEquals( 3, contacto1.darAltura( ) );
        // El peso del árbol es 4
        assertEquals( 4, contacto1.darPeso( ) );
        assertEquals( 4, contacto1.darPesoIterativo( ) );
        // Ya no se encuentra el contacto
        assertNull( contacto1.buscar( "Paco" ) );

        contacto1 = contacto1.eliminar( "Alberto" );
        // El árbol tiene profundidad 2
        assertEquals( 2, contacto1.darAltura( ) );
        // El peso del árbol es 3
        assertEquals( 3, contacto1.darPeso( ) );
        // Ya no se encuentra el contacto
        assertNull( contacto1.buscar( "Alberto" ) );

    }

    /**
     * Borra la raíz de todo el árbol. <br>
     * <b> Métodos a probar: </b> <br>
     * eliminar. <br>
     * <b> Objetivo: </b> Probar que el método eliminar() elimine correctamente un elemento del árbol. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar un elemento del árbol, el peso del árbol debe disminuir en 1. <br>
     * 2. Al eliminar un elemento del árbol al buscarlo se debe obtener null.
     */
    public void testBorrarRaiz( )
    {
        setupEscenario4( );
        contacto1 = contacto1.eliminar( "Marta" );
        // El árbol tiene profundidad 3
        assertEquals( 3, contacto1.darAltura( ) );
        // El peso del árbol es 4
        assertEquals( 4, contacto1.darPeso( ) );
        assertEquals( 4, contacto1.darPesoIterativo( ) );
        // Ya no se encuentra el contacto
        assertNull( contacto1.buscar( "Marta" ) );
    }

    /**
     * Prueba el recorrido inorden del árbol de contactos. <br>
     * <b> Métodos a probar: </b> <br>
     * inorden. <br>
     * <b> Objetivo: </b> Probar que el método inorden() retorne correctamente el recorrido del árbol en inorden. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el recorrido en inorden del árbol, los elementos deben ser retornados en orden ascendente.
     */
    public void testInorden( )
    {
        setupEscenario4( );
        Collection lista = new ArrayList( );
        contacto1.inorden( lista );
        assertEquals( "[Alberto, Luis, Marta, Paco, Xiomara]", lista.toString( ) );
    }

    /**
     * Prueba que las hojas del árbol se cuenten correctamente. <br>
     * <b> Métodos a probar: </b> <br>
     * contarHojas. <br>
     * <b> Objetivo: </b> Probar que el método contarHojas() realice el conteo correcto del número de hojas del árbol. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el número de hojas de un árbol es X. Al pedir el número de hojas se debe obtener X.
     */
    public void testContarHojas( )
    {
        setupEscenario1( );
        int numHojas = contacto1.contarHojas( );
        assertEquals( "El número de hojas no es el esperado", 1, numHojas );

        setupEscenario2( );
        numHojas = contacto1.contarHojas( );
        assertEquals( "El número de hojas no es el esperado", 1, numHojas );

        setupEscenario3( );
        numHojas = contacto1.contarHojas( );
        assertEquals( "El número de hojas no es el esperado", 2, numHojas );

        setupEscenario4( );
        numHojas = contacto1.contarHojas( );
        assertEquals( "El número de hojas no es el esperado", 2, numHojas );

    }

    /**
     * Prueba el método para contar el número de ocurrencias de un elemento dentro del árbol <b> Métodos a probar: </b> <br>
     * contarOcurrencias. <br>
     * <b> Objetivo: </b> Probar que el método contarOcurrencias() cuente correctamente las apariciones de un elemento en el árbol. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el número de ocurrencias de un elemento que existe en el árbol, se debe obtener 1. <br>
     * 2. Al pedir el número de ocurrencias de un elemento que no existe en el árbol, se debe obtener 0.
     * 
     */
    public void testContarOcurrencias( )
    {
        setupEscenario4( );
        int ocurrencias = contacto1.contarOcurrencias( "Xiomara" );
        assertEquals( "El número de ocurrencias del contacto no es el esperado", 1, ocurrencias );

        ocurrencias = contacto1.contarOcurrencias( "Paco" );
        assertEquals( "El número de ocurrencias del contacto no es el esperado", 1, ocurrencias );

        ocurrencias = contacto1.contarOcurrencias( "Marta" );
        assertEquals( "El número de ocurrencias del contacto no es el esperado", 1, ocurrencias );

        ocurrencias = contacto1.contarOcurrencias( "Alberto" );
        assertEquals( "El número de ocurrencias del contacto no es el esperado", 1, ocurrencias );

        ocurrencias = contacto1.contarOcurrencias( "Luis" );
        assertEquals( "El número de ocurrencias del contacto no es el esperado", 1, ocurrencias );

        ocurrencias = contacto1.contarOcurrencias( "José Luis" );
        assertEquals( "El número de ocurrencias del contacto no es el esperado", 0, ocurrencias );
    }

}