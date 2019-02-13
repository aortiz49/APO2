/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ExploradorTest.java,v 1.5 2006/08/06 01:06:21 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_exploradorArchivos
 * Autor: Pablo Barvo - 04-jul-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.exploradorArchivos.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.exploradorArchivos.mundo.Archivo;
import uniandes.cupi2.exploradorArchivos.mundo.Directorio;
import uniandes.cupi2.exploradorArchivos.mundo.Explorador;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Explorador est�n correctamente implementados
 */
public class ExploradorTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Explorador explorador;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un explorador que inicia en la ruta de pruebas (carpeta test/data)
     */
    private void setupEscenario1( )
    {
        File ruta = new File( "test" + File.separator + "data" );
        String path = ruta.getAbsolutePath( );
        if( !path.startsWith( Directorio.RAIZ ) )
        {
            fail( "Ruta inicial inv�lida" );
        }
        explorador = new Explorador( path );
    }

    /**
     * Construye un Explorador en la ra�z del sistema de archivos
     * 
     */
    private void setupEscenario2( )
    {
        explorador = new Explorador( );
    }

    /**
     * Verifica que la ruta inicial sea correcta. <br>
     * <b> M�todos a probar: </b> Explorador (constructor), darRutaActual.<br>
     * <b> Objetivo: </b> Probar que la ruta del explorador sea la ra�z del sistema de archivos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear el explorador con el constructor sin par�metros, este debe quedar situado en la ra�z del sistema de archivos.
     */
    public void testRutaInicial( )
    {
        setupEscenario2( );
        assertEquals( "La ruta es inv�lida", "C:" + File.separator, explorador.darRutaActual( ) );
    }

    /**
     * Verifica que el constructor del explorador recupere la informaci�n del sistema de archivos de forma correcta. <br>
     * <b> M�todos a probar: </b> Explorador (constructor), darSubdirectorios, darArchivos.<br>
     * <b> Objetivo: </b> Probar que el constructor de la clase Explorador es capaz de recuperar correctamente la informaci�n del sistema de archivos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear el explorador en un directorio determinado debe quedar situado en este. <br>
     * 2. El explorador s�lo debe contener los subdirectorios que pertenecen al directorio en el que se encuentra situado. <br>
     * 3. El explorador s�lo debe contener los archivos que pertenecen al directorio en el que se encuentra situado.
     */
    public void testConstruirEscenario( )
    {
        setupEscenario1( );

        // Verifica que la ruta del explorador sea correcta
        File ruta = new File( "test" + File.separator + "data" );
        String path = ruta.getAbsolutePath( );
        assertEquals( "La ruta es inv�lida", path, explorador.darRutaActual( ) );

        // Verifica los directorios
        Directorio[] directorios = explorador.darSubDirectorios( );

        // assertEquals( "Debe tener 2 directorios", 2, directorios.length );
        for( int i = 0; i < directorios.length; i++ )
        {
            if( !"sub1".equals( directorios[ i ].darNombre( ) ) && !"sub2".equals( directorios[ i ].darNombre( ) ) )
            {
                fail( "La lista de directorios es inv�lida" );
            }
        }

        // Verifica los archivos
        Archivo[] archivos = explorador.darArchivos( );

        assertEquals( "Debe tener 3 archivos", 3, archivos.length );
        for( int i = 0; i < archivos.length; i++ )
        {
            if( !"texto1.txt".equals( archivos[ i ].darNombre( ) ) && !"texto2.txt".equals( archivos[ i ].darNombre( ) ) && !"texto3.txt".equals( archivos[ i ].darNombre( ) ) )
            {
                fail( "La lista de archivos es inv�lida" );
            }
        }
    }

    /**
     * Verifica que los m�todos para subir y bajar por los subdirectorios funcionan de forma correcta. <br>
     * <b> M�todos a probar: </b> irDirectorio, subirDirectorio.<br>
     * <b> Objetivo: </b> Probar que los m�todos subirDirectorio() e irDirectorio() funcionan correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al ir a un subdirectorio del directorio actual, el explorador debe quedar en tal subdirectorio. <br>
     * 2. El explorador s�lo debe contener los subdirectorios del nuevo directorio. <br>
     * 3. El explorador s�lo debe contener los archivos que pertenecen al nuevo directorio. <br>
     * 4. Al subir en el sistema de archivos, el explorador debe quedar en el directorio padre del directorio actual.
     */
    public void testSubirBajar( )
    {
        setupEscenario1( );

        // BAJAR a sub1
        // Busca el directorio
        int num = darNumeroDirectorio( "sub1" );
        if( num < 0 )
        {
            fail( "El directorio 'sub1' debe existir" );
        }

        // Navega al directorio especifico
        explorador.irDirectorio( num );

        // Verifica la ruta
        File ruta = new File( "test" + File.separator + "data" + File.separator + "sub1" );
        String path = ruta.getAbsolutePath( );
        assertEquals( "La ruta es inv�lida", path, explorador.darRutaActual( ) );

        // Verifica los sub-directorios y los archivos
        assertEquals( "No debe tener sub-directorios", 0, explorador.darSubDirectorios( ).length );
        assertEquals( "Debe tener solo un archivo", 1, explorador.darArchivos( ).length );
        assertEquals( "El archivo debe ser 'archivo1.txt'", "archivo1.txt", explorador.darArchivos( )[ 0 ].darNombre( ) );

        // SUBIR a test/data
        explorador.subirDirectorio( );

        // Verifica la ruta
        ruta = new File( "test" + File.separator + "data" );
        path = ruta.getAbsolutePath( );

        assertEquals( "La ruta es inv�lida", path, explorador.darRutaActual( ) );

        // BAJAR a sub2
        // Busca el directorio
        num = darNumeroDirectorio( "sub2" );
        if( num < 0 )
        {
            fail( "El directorio 'sub2' debe existir" );
        }

        // Navega al directorio especifico
        explorador.irDirectorio( num );

        // Verifica la ruta
        ruta = new File( "test" + File.separator + "data" + File.separator + "sub2" );
        path = ruta.getAbsolutePath( );
        assertEquals( "La ruta es inv�lida", path, explorador.darRutaActual( ) );

        // Verifica los sub-directorios y los archivos
        assertEquals( "No debe tener sub-directorios", 0, explorador.darSubDirectorios( ).length );
        assertEquals( "Debe tener solo un archivo", 1, explorador.darArchivos( ).length );
        assertEquals( "El archivo debe ser 'archivo1.txt'", "archivo2.txt", explorador.darArchivos( )[ 0 ].darNombre( ) );

        // SUBIR a test/data
        explorador.subirDirectorio( );

        // Verifica la ruta
        ruta = new File( "test" + File.separator + "data" );
        path = ruta.getAbsolutePath( );
        assertEquals( "La ruta es inv�lida", path, explorador.darRutaActual( ) );
    }

    /**
     * Verifica que el m�todo para ir a un directorio funciona de forma correcta. <br>
     * <b> M�todos a probar: </b> irDirectorio.<br>
     * <b> Objetivo: </b> Probar que el m�todo irDirectorio() funciona correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al ir a un subdirectorio del directorio actual, el explorador debe quedar en tal subdirectorio. <br>
     * 2. El explorador s�lo debe contener los subdirectorios del nuevo directorio.
     */
    public void testIrDirectorio( )
    {

        setupEscenario2( );

        // Verifica que tiene por lo menos un directorio
        if( explorador.darSubDirectorios( ).length == 0 )
        {
            fail( "Debe tener por lo menos un directorio en C: para probar." );
        }

        // Busca el primer directorio
        Directorio directorio = explorador.darSubDirectorios( )[ 0 ];
        String nombre = directorio.darNombre( );

        // Navega al directorio especifico
        explorador.irDirectorio( 0 );

        // Verifica la ruta
        assertEquals( "La ruta es inv�lida", "C:" + File.separator + nombre, explorador.darRutaActual( ) );
    }

    /**
     * Verifica que el m�todo para subir un directorio en la jerarqu�a funcione de forma correcta. <br>
     * <b> M�todos a probar: </b> irDirectorio, subirDirectorio.<br>
     * <b> Objetivo: </b> Probar que el m�todo subirDirectorio() funciona correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al ir a un subdirectorio del directorio actual, el explorador debe quedar en tal subdirectorio. <br>
     * 2. Al subir en el sistema de archivos, el explorador debe quedar en el directorio padre del directorio actual.
     */
    public void testSubirDirectorio( )
    {
        setupEscenario2( );

        // Verifica que tiene por lo menos un directorio
        if( explorador.darSubDirectorios( ).length == 0 )
        {
            fail( "Debe tener por lo menos un directorio en C: para probar." );
        }

        // Busca el primer directorio
        Directorio directorio = explorador.darSubDirectorios( )[ 0 ];
        String nombre = directorio.darNombre( );

        // Navega al directorio especifico
        explorador.irDirectorio( 0 );

        // Verifica la ruta
        assertEquals( "La ruta es inv�lida", "C:" + File.separator + nombre, explorador.darRutaActual( ) );

        // Sube el directorio
        explorador.subirDirectorio( );
        assertEquals( "La ruta es inv�lida", "C:" + File.separator, explorador.darRutaActual( ) );

        // Sube nuevamente y debe quedar en la misma ruta
        explorador.subirDirectorio( );
        assertEquals( "La ruta es inv�lida", "C:" + File.separator, explorador.darRutaActual( ) );
    }

    /**
     * Verifica que el m�todo buscar por prefijo funcione de forma correcta. <br>
     * <b> M�todos a probar: </b> buscarPorPrefijo.<br>
     * <b> Objetivo: </b> Probar que el m�todo buscarPorPrefijo() funciona correctamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al dar un prefijo que se sabe que est� en N archivos se deben obtener la lista con los N archivos. <br>
     * 2. Al dar un prefijo que se sabe que no est� en ning�n archivo se debe obtener una lista de archivos vac�a.
     */
    public void testBuscarPrefijo( )
    {
        setupEscenario1( );

        // Busca por el prefijo "prue"
        ArrayList archivos = explorador.buscarPorPrefijo( "prue" );

        // Verifica los resultados
        assertEquals( "Debe tener 3 resultados", 3, archivos.size( ) );
        for( int i = 0; i < archivos.size( ); i++ )
        {
            Archivo archivo = ( Archivo )archivos.get( i );
            if( !"texto1.txt".equals( archivo.darNombre( ) ) && !"texto2.txt".equals( archivo.darNombre( ) ) && !"texto3.txt".equals( archivo.darNombre( ) ) )
            {
                fail( "El resultado de la b�squeda es inv�lido" );
            }
        }

        // Busca por el prefijo "nuevo"
        archivos = explorador.buscarPorPrefijo( "nuevo" );

        // Verifica los resultados
        assertEquals( "Debe tener un resultado", 1, archivos.size( ) );
        Archivo archivo = ( Archivo )archivos.get( 0 );
        if( !"texto2.txt".equals( archivo.darNombre( ) ) )
        {
            fail( "El resultado de la b�squeda es inv�lido" );
        }

        // Busca por el prefijo "chao"
        archivos = explorador.buscarPorPrefijo( "chao" );

        // Verifica los resultados
        assertEquals( "Debe tener un resultado", 1, archivos.size( ) );
        archivo = ( Archivo )archivos.get( 0 );
        if( !"texto3.txt".equals( archivo.darNombre( ) ) )
        {
            fail( "El resultado de la b�squeda es inv�lido" );
        }

        // Busca por el prefijo "nunca"
        archivos = explorador.buscarPorPrefijo( "nunca" );

        // Verifica los resultados
        assertEquals( "Debe tener un resultado", 0, archivos.size( ) );
    }

    /**
     * Verifica que el m�todo para crear archivos los genere correctamente. <br>
     * <b> M�todos a probar: </b> crearArchivo.<br>
     * <b> Objetivo: </b> Probar que el m�todo crearArchivo() sea capaz de crear un archivo en el directorio en el que se encuentra el explorador. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un archivo este debe encontrarse en el directorio actual del explorador.
     */
    public void testCrearArchivo( )
    {
        setupEscenario1( );

        // Crea el archivo
        try
        {
            explorador.crearArchivo( "temporal.txt" );
            File archivo = new File( explorador.darRutaActual( ), "temporal.txt" );
            if( !archivo.exists( ) )
            {
                fail( "El archivo debe existir" );
            }
            archivo.delete( );
        }
        catch( IOException e )
        {
            fail( "Debe poder crear el archivo" );
        }

    }

    // -----------------------------------------------------------------
    // M�todos Auxiliares
    // -----------------------------------------------------------------

    /**
     * Devuelve el n�mero del directorio especificado
     * @param nombre es el nombre del directorio
     * @return N�mero del directorio en el explorador
     */
    private int darNumeroDirectorio( String nombre )
    {
        for( int i = 0; i < explorador.darSubDirectorios( ).length; i++ )
        {
            Directorio dir = explorador.darSubDirectorios( )[ i ];
            if( nombre.equals( dir.darNombre( ) ) )
            {
                return i;
            }
        }
        fail( "El directorio " + nombre + " no existe!" );
        return -1;
    }
}