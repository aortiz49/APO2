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

package uniandes.cupi2.cupiDeportes.mundo;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * Clase que se encarga de administrar el sistema de CupiDeportes.<br>
 * <b>inv:</b><br>
 * deportes != null. <br>
 * No existen dos o m�s deportes con el mismo nombre.<br>
 */
public class CupiDeportes
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista con los deportes.
     */
    private ArrayList<Deporte> deportes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nuevo sistema de deportes. <br>
     * Si el archivo indicado no existe, se crea una lista de deportes vac�a.<br>
     * Si el archivo existe, se carga la informaci�n de los deportes y su deportistas.
     * @param pNombreArchivo Nombre del archivo que contiene los datos serializados. pNombreArchivo != null && pNombreArchivo != "".
     * @throws PersistenciaException Se lanza una excepci�n si hay alg�n error cargando los datos del archivo.
     */
    public CupiDeportes( String pNombreArchivo ) throws PersistenciaException
    {
    	// TODO Parte 3 punto C: Implemente el m�todo seg�n la documentaci�n.

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la lista con todos los deportes.
     * @return Lista con los deportes.
     */
    public ArrayList<Deporte> darDeportes( )
    {
        return deportes;
    }

    /**
     * Agrega un deporte con la informaci�n dada a la lista de deportes. <br>
     * <b>post: </b> Se agreg� el deporte a la lista.
     * @param pNombreDeporte Nombre del deporte. pNombreDeporte != null && pNombreDeporte != "".
     * @param pEnteRegulador Ente regulador del deporte. pEnteRegulador != null && pEnteRegulador != "".
     * @param pCantidadDeportistasRegistrados Cantidad de deportistas registrados. pCantidadDeportistasRegistrados > 0.
     * @param pRutaImagen Ruta a la imagen del deporte. pRutaImagen != null && pRutasImagen != "".
     * @throws ElementoExisteException Lanza una excepci�n si ya existe un deporte con el nombre dado.
     */
    public void agregarDeporte( String pNombreDeporte, String pEnteRegulador, int pCantidadDeportistasRegistrados, String pRutaImagen ) throws ElementoExisteException
    {
        if( existeDeporte( pNombreDeporte ) )
        {
            throw new ElementoExisteException( ElementoExisteException.DEPORTE_REPETIDO, pNombreDeporte );
        }
        else
        {
            Deporte deporteNuevo = new Deporte( pNombreDeporte, pEnteRegulador, pCantidadDeportistasRegistrados, pRutaImagen );
            deportes.add( deporteNuevo );
            verificarInvariante( );
        }
    }

    /**
     * Elimina el deporte con nombre dado de la lista de deportes. <b>post: </b> Se elimin� el deporte de la lista.
     * @param pNombreDeporte Nombre del deporte a eliminar. pNombreDeporte != null && pNombreDeporte != "".
     */
    public void eliminarDeporte( String pNombreDeporte )
    {
        boolean encontro = false;
        for( int i = 0; i < deportes.size( ) && !encontro; i++ )
        {
            Deporte deporteActual = deportes.get( i );
            if( deporteActual.darNombre( ).equals( pNombreDeporte ) )
            {
                deportes.remove( i );
                encontro = true;
            }
        }
        verificarInvariante( );
    }

    /**
     * Agrega un deportista sobresaliente a un deporte con la informaci�n dada por par�metro. <br>
     * <b>post: </b> Se agreg� el deportista sobresaliente al deporte correspondiente.
     * @param pNombreDeporte Nombre del deporte. pNombreDeporte != null && pNombreDeporte != "".
     * @param pNombreDeportista Nombre del deportista sobresaliente. pNombreDeportista != null && pNombreDeportista != "".
     * @param pEdad Edad del deportista. pEdad > 0.
     * @param pLugarResidencia Lugar de residencia del deportista. pLugarResidencia != null && pLugarResidencia != "".
     * @param pCantidadTrofeos Cantidad de trofeos ganados por el deportista. pCantidadTrofeos >= 0.
     * @param pRutaImagenDeportista Ruta a la imagen del deportista. pRutaImagenDeportista != null && pRutaImagenDeportista != "".
     * @throws ElementoExisteException Lanza una excepci�n si en el deporte ya existe un deportista con el nombre dado.
     */
    public void agregarDeportistaSobresaliente( String pNombreDeporte, String pNombreDeportista, int pEdad, String pLugarResidencia, int pCantidadTrofeos, String pRutaImagenDeportista ) throws ElementoExisteException
    {
        //TODO Parte 4 punto D: Implemente el m�todo seg�n la documentaci�n.   
    }
    /**
     * Elimina el deportista sobresaliente con el nombre especificado del deporte que tiene el nombre dado por par�metro. <br>
     * <b>post: </b> Se elimin� el deportista sobresaliente del deporte correspondiente.
     * @param pNombreDeporte Nombre del deporte. pNombreDeporte != null && pNombreDeporte != "".
     * @param pNombreDeportista Nombre del deportista a eliminar. pNombreDeportista != null && pNombreDeportista != "".
     */
    public void eliminarDeportistaSobresaliente( String pNombreDeporte, String pNombreDeportista )
    {
        boolean encontro = false;
        for( int i = 0; i < deportes.size( ) && !encontro; i++ )
        {
            Deporte deporteActual = deportes.get( i );
            if( deporteActual.darNombre( ).equals( pNombreDeporte ) )
            {
                deporteActual.eliminarDeportistaSobresaliente( pNombreDeportista );
                encontro = true;
            }
        }
        verificarInvariante( );
    }

    /**
     * Verifica si existe un deporte con el nombre dado.
     * @param pNombreDeporte Nombre del deporte. pNombreDeporte != null && pNombreDeporte != "".
     * @return True si existe el deporte, false en caso contrario.
     */
    public boolean existeDeporte( String pNombreDeporte )
    {
        boolean existe = false;

        for( int i = 0; i < deportes.size( ) && !existe; i++ )
        {
            Deporte deporteActual = deportes.get( i );
            if( deporteActual.darNombre( ).equals( pNombreDeporte ) )
            {
                existe = true;
            }
        }
        return existe;
    }

    /**
     * Retorna el deportista sobresaliente que tiene el mayor n�mero de trofeos. <br>
     * Si hay dos o m�s deportistas con el mismo n�mero de trofeos y ambos son m�ximos, retorna cualquiera.
     * @return Retorna el deportista que m�s tiene trofeos. Si no hay deportistas retorna null.
     */
    public Deportista darDeportistaMasTrofeos( )
    {
        Deportista masTrofeos = null;
        int cantidadMax = 0;
        if( deportes.size( ) != 0 )
        {
            for( int i = 0; i < deportes.size( ); i++ )
            {
                Deporte deporteActual = deportes.get( i );
                Deportista deportistaActualMas = deporteActual.darDeportistaMasTrofeos( );
                if( deportistaActualMas.darCantidadTrofeos( ) > cantidadMax )
                {
                    masTrofeos = deportistaActualMas;
                    cantidadMax = deportistaActualMas.darCantidadTrofeos( );
                }
            }
        }
        return masTrofeos;
    }

    /**
     * Retorna el total de trofeos.
     * @return Cantidad total de trofeos.
     */
    public int darTotalTrofeos( )
    {
        int total = 0;

        for( int i = 0; i < deportes.size( ); i++ )
        {
            Deporte deporteActual = deportes.get( i );
            total += deporteActual.darTotalTrofeos( );
        }

        return total;
    }

    /**
     * Serializa el ArrayList de deportes en el archivo cuya ruta entra como par�metro.
     * @param pRutaArchivo Ruta del archivo donde se va guardar. pRutaArchivo != null && pRutaArchivo != "".
     * @throws PersistenciaException Lanza una excepci�n si se presenta un problema al momento de guardar el archivo.
     */
    public void guardar( String pRutaArchivo ) throws PersistenciaException
    {
        // TODO Parte 3 punto A: Implemente el m�todo seg�n la documentaci�n.
    }

    /**
     * Actualiza la informaci�n de un deportista.
     * @param pLinea L�nea de texto con la informaci�n para actualizar un deportista. pLinea != null && pLinea != "".
     * @throws FormatoArchivoException Lanza excepci�n si la l�nea de texto no cumple con el formato definido para actualizar la informaci�n de un deportista.
     */
    private void actualizarDeportista( String pLinea ) throws FormatoArchivoException
    {
        // TODO Parte 5 punto B: Implemente el m�todo seg�n la documentaci�n.
    }

    
    /**
     * Actualiza la informaci�n de los deportistas a partir de un archivo de texto.
     * @param pArchivo Archivo del cual se cargar� la informaci�n. pArchivo != null.
     * @throws FormatoArchivoException Si el archivo no cumple con el formato definido para actualizar la informaci�n.
     * @throws IOException Si hay problemas de lectura del archivo para actualizar la informaci�n.
     */
    public void actualizarInformacionDeportistas( File pArchivo ) throws FormatoArchivoException, IOException
    {
        // TODO Parte 5 punto C: Implemente el m�todo seg�n la documentaci�n.
    }

    /**
     * Genera el informe de los trofeos de los deportistas.
     * @param pRutaArchivo Ruta donde se desea guardar el archivo con el reporte. pRutaArchivo != null && pRutaArchivo != "".
     * @throws IOException Si ocurre un error en la generaci�n del reporte.
     */
    public void generarReporteTrofeos( String pRutaArchivo ) throws IOException
    {
        // TODO Parte 5 punto A: Implemente el m�todo seg�n la documentaci�n.
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase: <br>
     * deportes != null <br>
     * No existen dos o m�s deportes con el mismo nombre.<br>
     */
    private void verificarInvariante( )
    {
        assert deportes != null : "La lista de deportes es nula.";
        assert buscarDeportesConElMismoNombre( ) == false : "Hay deportes con el mismo nombre.";
    }

    /**
     * Revisa si hay dos deportes con el mismo nombre.
     * @return Retorna true si hay dos deportes con el mismo nombre. Retorna false en caso contrario.
     */
    private boolean buscarDeportesConElMismoNombre( )
    {
        for( int i = 0; i < deportes.size( ); i++ )
        {
            Deporte deporte1 = deportes.get( i );

            for( int j = i + 1; j < deportes.size( ); j++ )
            {
                Deporte deporte2 = deportes.get( j );
                if( deporte1.equals( deporte2 ) )
                    return true;
            }
        }

        return false;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}