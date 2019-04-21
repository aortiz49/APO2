/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Contacto.java,v 1.13 2007/04/13 04:51:14 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_contactos
 * Autor: Katalina Marcos - 27/10/2005
 * Autor: Jorge Villalobos - 11/10/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.contactos.mundo;

import java.util.*;

/**
 * Información de contacto de una persona<br>
 * <b>inv:</b> <br>
 * nombre != null.
 */
public class Contacto implements Comparable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Nombre del contacto
     */
    private String nombre;

    /**
     * Teléfono del contacto
     */
    private String telefono;

    /**
     * Dirección del contacto
     */
    private String direccion;

    /**
     * Correo electrónico del contacto
     */
    private String eMail;

    /**
     * Subárbol izquierdo de contactos
     */
    private Contacto izq;

    /**
     * Subárbol derecho de contactos
     */
    private Contacto der;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un contacto con toda la información básica
     * @param elNombre nombre de la persona a contactar - nombre != null
     * @param elTelefono teléfono de contacto
     * @param laDireccion dirección de contacto
     * @param elEmail correo electrónico de contacto
     */
    public Contacto( String elNombre, String elTelefono, String laDireccion, String elEmail )
    {
        nombre = elNombre;
        telefono = elTelefono;
        direccion = laDireccion;
        eMail = elEmail;
        izq = null;
        der = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Método interfaz Comparable
    // -----------------------------------------------------------------

    /**
     * Compara este contacto con otro
     * @param o es el otro contacto con el que se compara
     * @return -1 si este contacto es menor al otro, 0 si son iguales y 1 si este contacto es mayor al otro
     */
    public int compareTo( Object o )
    {
        Contacto otro = ( Contacto )o;
        return nombre.compareToIgnoreCase( otro.nombre );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del contacto
     * @return nombre del contacto
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna el teléfono del contacto
     * @return teléfono del contacto
     */
    public String darTelefono( )
    {
        return telefono;
    }

    /**
     * Retorna la dirección del contacto
     * @return dirección del contacto
     */
    public String darDireccion( )
    {
        return direccion;
    }

    /**
     * Retorna la dirección electrónica del contacto
     * @return dirección electrónica del contacto
     */
    public String darEmail( )
    {
        return eMail;
    }

    /**
     * Indica si este nodo es una hoja
     * @return true si este nodo es una hoja y false en caso contrario
     */
    public boolean esHoja( )
    {
        return izq == null && der == null;
    }

    /**
     * Retorna el contacto que alfabéticamente corresponde al menor contacto del árbol que parte de este nodo
     * @return contacto con menor nombre
     */
    public Contacto darMenor( )
    {
        return ( izq == null ) ? this : izq.darMenor( );
    }

    /**
     * Retorna el contacto que alfabéticamente corresponde al mayor contacto del árbol que parte de este nodo
     * @return contacto con mayor nombre
     */
    public Contacto darMayor( )
    {
        return ( der == null ) ? this : der.darMayor( );
    }

    /**
     * Retorna la altura del árbol de contactos que comienza en este nodo
     * @return altura del árbol que comienza en este nodo
     */
    public int darAltura( )
    {
        if( esHoja( ) )
            return 1;
        else
        {
            int a1 = ( izq == null ) ? 0 : izq.darAltura( );
            int a2 = ( der == null ) ? 0 : der.darAltura( );
            return 1 + Math.max( a1, a2 );
        }
    }

    /**
     * Retorna el número de contactos que hay en el árbol que comienza en este nodo utilizando un algoritmo iterativo
     * @return número de contactos en el árbol que comienza en este nodo
     */
    public int darPesoIterativo( )
    {
        int peso = 0;
        ArrayList pila = new ArrayList( );
        Contacto p = this;
        while( p != null )
        {
            peso++;
            if( p.izq != null )
            {
                // Guarda el subárbol derecho en la pila, para recuperarlo más tarde
                if( p.der != null )
                    pila.add( p.der );

                // Baja por la izquierda
                p = p.izq;
            }
            else if( p.der != null )
            {
                // Baja por la derecha, puesto que no hay subárbol izquierdo
                p = p.der;
            }
            else if( pila.size( ) != 0 )
            {
                // Es una hoja, luego debemos sacar de la pila el último subárbol allí almacenado
                p = ( Contacto )pila.get( 0 );
                pila.remove( 0 );
            }
            else
            {
                p = null;
            }
        }
        return peso;
    }

    /**
     * Retorna el número de contactos que hay en el árbol que comienza en este nodo
     * @return número de contactos en el árbol que comienza en este nodo
     */
    public int darPeso( )
    {
        int p1 = ( izq == null ) ? 0 : izq.darPeso( );
        int p2 = ( der == null ) ? 0 : der.darPeso( );
        return 1 + p1 + p2;
    }

    /**
     * Retorna el número de hojas que hay en el árbol que comienza en este nodo
     * @return número de hojas que hay en el árbol que comienza en este nodo
     */
    public int contarHojas( )
    {
        if( esHoja( ) )
            return 1;
        else
        {
            int h1 = ( izq == null ) ? 0 : izq.contarHojas( );
            int h2 = ( der == null ) ? 0 : der.contarHojas( );
            return h1 + h2;
        }
    }

    /**
     * Inserta un nuevo contacto al árbol que comienza en este nodo.
     * @param nuevo el el nuevo contacto que se va a insertar - nuevo != null
     * @throws ContactoRepetidoException se lanza esta excepción si el contacto que se quiere agregar ya está en el directorio
     */
    public void insertar( Contacto nuevo ) throws ContactoRepetidoException
    {
        if( compareTo( nuevo ) == 0 )
            throw new ContactoRepetidoException( nuevo.nombre );

        if( compareTo( nuevo ) > 0 )
        {
            // Debe agregar el nuevo contacto por el subárbol izquierdo
            if( izq == null )
                izq = nuevo;
            else
                izq.insertar( nuevo );
        }
        else
        {
            // Debe agregar el nuevo contacto por el subárbol derecho
            if( der == null )
                der = nuevo;
            else
                der.insertar( nuevo );
        }
    }

    /**
     * Implementación iterativa para localizar un contacto en el árbol que comienza en este nodo
     * @param unNombre nombre que se va a buscar - unNombre != null
     * @return contacto asociado al nombre. Si no lo encuentra retorna null;
     */
    public Contacto buscarIterativo( String unNombre )
    {
        Contacto p = this;
        while( p != null )
        {
            int comp = p.nombre.compareToIgnoreCase( unNombre );
            if( comp == 0 )
                return p;
            else if( comp > 0 )
                p = p.izq;
            else
                p = p.der;
        }
        return null;
    }

    /**
     * Implementación recursiva para localizar un contacto en el árbol que comienza en este nodo
     * @param unNombre nombre que se va a buscar - unNombre != null
     * @return contacto asociado al nombre. Si no lo encuentra retorna null;
     */
    public Contacto buscar( String unNombre )
    {
        if( nombre.compareToIgnoreCase( unNombre ) == 0 )
            return this;
        else if( nombre.compareToIgnoreCase( unNombre ) > 0 )
            return ( izq == null ) ? null : izq.buscar( unNombre );
        else
            return ( der == null ) ? null : der.buscar( unNombre );
    }

    /**
     * Elimina un contacto del árbol que comienza en este nodo.
     * @param unNombre nombre del contacto que se va a eliminar - hay un contacto en el árbol que se llama unNombre
     * @return el árbol de contactos después de eliminar el contacto indicado
     */
    public Contacto eliminar( String unNombre )
    {
        if( esHoja( ) )
            // Tiene que ser el elemento que estamos buscando
            return null;
        if( nombre.compareToIgnoreCase( unNombre ) == 0 )
        {
            if( izq == null )
                return der;
            if( der == null )
                return izq;
            // Localiza el menor contacto del subárbol derecho
            Contacto sucesor = der.darMenor( );
            // Elimina del subárbol derecho el elemento que acaba de localizar
            der = der.eliminar( sucesor.darNombre( ) );
            // Deja el elemento localizado en la raíz del árbol de respuesta
            sucesor.izq = izq;
            sucesor.der = der;
            return sucesor;
        }
        else if( nombre.compareToIgnoreCase( unNombre ) > 0 )
            izq = izq.eliminar( unNombre );
        else
            der = der.eliminar( unNombre );
        return this;
    }

    /**
     * Retorna una colección con los nombres de todos los contactos, ordenados alfabéticamente en orden ascendente
     * @param acumulado colección donde se van agregando los nombres de los contactos ordenadamente
     */
    public void inorden( Collection acumulado )
    {
        // Recorre en inorden el subárbol izquierdo
        if( izq != null )
            izq.inorden( acumulado );
        // Incluye en el recorrido el contacto de la raíz
        acumulado.add( nombre );
        // Recorre en inorden el subárbol derecho
        if( der != null )
            der.inorden( acumulado );
    }

    /**
     * Indica si el árbol que comienza en este nodo es ordenado
     * @return true si el árbol que comienza en este nodo es ordenado
     */
    public boolean esOrdenado( )
    {
        if( esHoja( ) )
            return true;

        else if( izq == null )
            return der.esOrdenado( ) && nombre.compareTo( der.darMenor( ).darNombre( ) ) < 0;

        else if( der == null )
            return izq.esOrdenado( ) && nombre.compareTo( izq.darMayor( ).darNombre( ) ) > 0;

        else
            return der.esOrdenado( ) && nombre.compareTo( der.darMenor( ).darNombre( ) ) < 0 && izq.esOrdenado( ) && nombre.compareTo( izq.darMayor( ).darNombre( ) ) > 0;
    }

    /**
     * Cuenta el número de veces que aparece un contacto con un nombre que se recibe como parámetro
     * @param nombreP nombre del contacto que se está buscando - nombreP != null
     * @return número de contactos con un nombre dado
     */
    public int contarOcurrencias( String nombreP )
    {
        int cuantos = 0;
        if( nombre.equals( nombreP ) )
            cuantos++;
        cuantos += ( izq == null ) ? 0 : izq.contarOcurrencias( nombreP );
        cuantos += ( der == null ) ? 0 : der.contarOcurrencias( nombreP );
        return cuantos;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv:</b> <br>
     * nombre != null.
     */
    public void verificarInvariante( )
    {
        assert nombre != null : "Nombre inválido";
    }
}