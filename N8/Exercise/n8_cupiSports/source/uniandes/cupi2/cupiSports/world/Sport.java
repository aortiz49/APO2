/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiDeportes
 * Autor: Equipo Cupi2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiSports.world;

import java.util.ArrayList;

/**
 * Clase que representa un deporte.<br>
 * TODO Parte 1 punto A: Defina el invariante
 */
public class Deporte
{
    
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n.
     */
    private static final long serialVersionUID = -8659162802685356289L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del deporte.
     */
    private String nombre;

    /**
     * Ente regulador del deporte.
     */
    private String enteRegulador;

    /**
     * Cantidad de deportistas registrados en el deporte.
     */
    private int cantidadDeportistasRegistrados;

    /**
     * Ruta a la imagen del deporte.
     */
    private String rutaImagen;

    /**
     * Lista con los deportistas sobresalientes.
     */
    private ArrayList<Deportista> deportistasSobresalientes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un deporte con los valores dados por par�metro. <br>
     * <b>post: </b> Se inicializaron los atributos con los datos dados por par�metro. Se crea una lista de deportistas vac�a. <br>
     * @param pNombre Nombre del deporte. pNombre != null && pNombre != "".
     * @param pEnteRegulador Ente regulador del deporte. pEnteRegulador != null && pEnteRegulador != "".
     * @param pCantidadDeportistasRegistrados Cantidad de deportistas registrados en el deporte. pCantidadDeportistasRegistrados > 0.
     * @param pRutaImagen Ruta a la imagen del deporte. pRutaImagen != null && pRutaImagen != "".
     */
    public Deporte( String pNombre, String pEnteRegulador, int pCantidadDeportistasRegistrados, String pRutaImagen )
    {
        nombre = pNombre;
        enteRegulador = pEnteRegulador;
        cantidadDeportistasRegistrados = pCantidadDeportistasRegistrados;
        rutaImagen = pRutaImagen;
        deportistasSobresalientes = new ArrayList<Deportista>( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del deporte.
     * @return Nombre del deporte.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna el ente regulador del deporte.
     * @return Ente regulador del deporte.
     */
    public String darEnteRegulador( )
    {
        return enteRegulador;
    }

    /**
     * Retorna la cantidad de deportistas registrados.
     * @return Cantidad de deportistas registrados.
     */
    public int darCantidadDeportistasRegistrados( )
    {
        return cantidadDeportistasRegistrados;
    }

    /**
     * Retorna la ruta a la imagen del deporte.
     * @return Ruta a la imagen del deporte.
     */
    public String darRutaImagen( )
    {
        return rutaImagen;
    }

    /**
     * Retorna una lista con los deportistas sobresalientes.
     * @return Deportistas sobresalientes del deporte.
     */
    public ArrayList<Deportista> darDeportistasSobresalientes( )
    {
        return deportistasSobresalientes;
    }

    /**
     * Agrega un deportista sobresaliente.
     * @param pDeportista Deportista a agregar a la lista de deportistas sobresalientes. pDeportista != null.<br>
     *        <b>post: </b> Se agreg� el deportista sobresaliente a la lista.
     * @throws ElementoExisteException Lanza una excepci�n si ya existe un deportista sobresaliente con el nombre del que se quiere agregar.
     */
    public void agregarDeportistaSobresaliente( Deportista pDeportista ) throws ElementoExisteException
    {
    	//TODO Parte 4 punto A: Implemente el m�todo seg�n la documentaci�n.
    }

    /**
     * Elimina el deportista sobresaliente con el nombre dado. <br>
     * <b>post: </b> Se elimin� el deportista sobresaliente de la lista.
     * @param pNombreDeportista Nombre del deportista a eliminar. pNombreDeportista != null && pNombreDeportista != "".
     */
    public void eliminarDeportistaSobresaliente( String pNombreDeportista )
    {
        boolean termino = false;
        for( int i = 0; i < deportistasSobresalientes.size( ) && !termino; i++ )
        {
            Deportista deportistaActual = deportistasSobresalientes.get( i );
            if( deportistaActual.darNombre( ).equals( pNombreDeportista ) )
            {
                deportistasSobresalientes.remove( i );
                termino = true;
            }
        }

    }

    /**
     * Verifica si existe o no un deportista sobresaliente con el nombre dado.
     * @param pNombreDeportista Nombre del deportista. pNombreDeportista != null && pNombreDeportista != "".
     * @return True si existe un deportista sobresaliente con el nombre dado por par�metro. False de lo contrario.
     */
    public boolean existeDeportistaSobresaliente( String pNombreDeportista )
    {
        boolean existe = false;

        for( int i = 0; i < deportistasSobresalientes.size( ) && !existe; i++ )
        {
            Deportista deportistaActual = ( Deportista )deportistasSobresalientes.get( i );
            if( deportistaActual.darNombre( ).equals( pNombreDeportista ) )
            {
                existe = true;
            }
        }
        return existe;
    }

    /**
     * Retorna el deportista que tiene el mayor n�mero de trofeos ganados. Si hay dos o m�s deportistas con el mismo n�mero de trofeos y son m�ximos, retorna cualquiera.
     * @return El deportista con mayor n�mero de trofeos. Si no hay deportistas retorna null.
     */
    public Deportista darDeportistaMasTrofeos( )
    {
        Deportista masTrofeos = null;
        int cantidadMaxima = 0;
        for( int i = 0; i < deportistasSobresalientes.size( ); i++ )
        {
            Deportista deportistaActual = deportistasSobresalientes.get( i );
            if( deportistaActual.darCantidadTrofeos( ) > cantidadMaxima )
            {
                cantidadMaxima = deportistaActual.darCantidadTrofeos( );
                masTrofeos = deportistaActual;
            }

        }
        return masTrofeos;
    }

    /**
     * Retorna el total de trofeos de los deportistas sobresalientes.
     * @return total de trofeos.
     */
    public int darTotalTrofeos( )
    {
        int total = 0;

        for( int i = 0; i < deportistasSobresalientes.size( ); i++ )
        {
            Deportista deportistaActual = deportistasSobresalientes.get( i );
            total += deportistaActual.darCantidadTrofeos( );
        }

        return total;
    }

    /**
     * Retorna el nombre del deporte.
     * @return Nombre del deporte.
     */
    public String toString( )
    {
        return nombre;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    //TODO Parte 1 punto B: Documente e implemente el m�todo verificarInvariante().
    //De ser necesario implemente m�todos auxiliares.

}