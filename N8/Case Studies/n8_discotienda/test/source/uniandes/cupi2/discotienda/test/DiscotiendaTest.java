/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *$Id: DiscotiendaTest.java,v 1.11 2007/04/10 12:21:44 p-marque Exp $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_discotienda 
 * Autor: Nicol�s L�pez - 06/12/2005
 * Autor: Mario S�nchez - 26/01/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.discotienda.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.discotienda.mundo.Cancion;
import uniandes.cupi2.discotienda.mundo.ElementoExisteException;
import uniandes.cupi2.discotienda.mundo.Disco;
import uniandes.cupi2.discotienda.mundo.Discotienda;
import uniandes.cupi2.discotienda.mundo.ArchivoVentaException;

/**
 * Esta es la clase que sirve para verificar la clase Discotienda
 */
public class DiscotiendaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la discotienda sobre la que se realizan las pruebas
     */
    private Discotienda discotienda1;

    /**
     * Es la discotienda sobre la que se realizan las pruebas para saber si se salv� bien un archivo
     */
    private Discotienda discotienda2;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una discotienda vac�a
     */
    private void setupEscenario1( )
    {
        try
        {
            File archivo = new File( "./test/data/discotienda.dat" );
            if( archivo.exists( ) )
            {
                archivo.delete( );
            }

            discotienda1 = new Discotienda( "./test/data/discotienda.dat" );
        }
        catch( Exception e )
        {
            fail( "No deber�a haber problemas cargando el archivo:" + e.getMessage( ) );
        }
    }

    /**
     * Construye una discotienda con tres discos (disco1, disco2 y disco3), cada uno con 3 mynicesonges
     */
    private void setupEscenario2( )
    {
        try
        {
            File archivo = new File( "./test/data/discotienda.dat" );
            if( archivo.exists( ) )
            {
                archivo.delete( );
            }

            discotienda1 = new Discotienda( "./test/data/discotienda.dat" );
            discotienda1.agregarDisco( "disco1", "artista1", "genero1", "imagen1" );
            discotienda1.agregarCancionADisco( "disco1", "mynicesong1", 1, 1, 1, 1, 1 );
            discotienda1.agregarCancionADisco( "disco1", "mynicesong2", 2, 2, 2, 2, 2 );
            discotienda1.agregarCancionADisco( "disco1", "mynicesong3", 3, 3, 3, 3, 3 );

            discotienda1.agregarDisco( "disco2", "artista2", "genero2", "imagen2" );
            discotienda1.agregarCancionADisco( "disco2", "mynicesong1", 1, 1, 1, 1, 1 );
            discotienda1.agregarCancionADisco( "disco2", "mynicesong2", 2, 2, 2, 2, 2 );
            discotienda1.agregarCancionADisco( "disco2", "mynicesong3", 3, 3, 3, 3, 3 );

            discotienda1.agregarDisco( "disco3", "artista3", "genero3", "imagen3" );
            discotienda1.agregarCancionADisco( "disco3", "mynicesong1", 1, 1, 1, 1, 1 );
            discotienda1.agregarCancionADisco( "disco3", "mynicesong2", 2, 2, 2, 2, 2 );
            discotienda1.agregarCancionADisco( "disco3", "mynicesong3", 3, 3, 3, 3, 3 );

        }
        catch( Exception e )
        {
            fail( "No deber�a haber problemas cargando el archivo:" + e.getMessage( ) );
        }
    }

    /**
     * Verifica el m�todo de creaci�n de la clase Discotienda.<br>
     * Se espera que la discotienda est� vac�a. <br>
     * <b> M�todos a probar: </b> <br>
     * Discotienda (constructor), darDiscos. <br>
     * <b> Objetivo: </b> Probar que el m�todo Discotienda() sea capaz de crear una tienda vac�a (sin discos). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear una discotienda esta debe quedar sin discos. <br>
     */
    public void testDiscotienda( )
    {
        setupEscenario1( );

        ArrayList discos = discotienda1.darDiscos( );
        assertEquals( "El n�mero de discos es incorrecto", 0, discos.size( ) );
    }

    /**
     * Verifica el m�todo que retorna un disco de la discotienda.<br>
     * Se verifica lo que sucede cuando se busca un disco que existe en la discotienda. <br>
     * <b> M�todos a probar: </b> <br>
     * darDisco. <br>
     * <b> Objetivo: </b> Probar que el m�todo darDisco() sea capaz de encontrar un disco existente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un disco existente en la tienda, este debe ser retornado.
     */
    public void testDarDiscoOk( )
    {
        setupEscenario2( );

        Disco disco = discotienda1.darDisco( "disco1" );
        assertNotNull( "No se encontr� el disco", disco );
        assertEquals( "El nombre del disco retornado no es el esperado", "disco1", disco.darNombreDisco( ) );
    }

    /**
     * Verifica el m�todo que retorna un disco de la discotienda.<br>
     * Se verifica lo que sucede cuando se busca un disco que NO existe en la discotienda. <br>
     * <b> M�todos a probar: </b> <br>
     * darDisco. <br>
     * <b> Objetivo: </b> Probar que el m�todo darDisco() no encuentre disco que no existe en la tienda. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un disco que no existe en la tienda se debe obtener null.
     */
    public void testDarDiscoError( )
    {
        setupEscenario2( );

        Disco disco = discotienda1.darDisco( "disco4" );
        assertNull( "Se encontr� un disco que no deber�a existir en la discotienda", disco );
    }

    /**
     * Verifica el m�todo que agrega un disco a la discotienda para el caso en el que no hay error.<br>
     * En este caso los datos del disco que se agrega son correctos. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarDisco. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarDisco() sea capaz de agregar un disco en la tienda. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un disco cuyo nombre no pertenece a otro disco existente en la tienda, este debe ser adicionado. <br>
     * 2. Al buscar un disco previamente agregado este debe ser encontrado.
     */
    public void testAgregarDiscoOk( )
    {
        setupEscenario2( );

        try
        {
            // Se agrega el disco
            discotienda1.agregarDisco( "Mi disco de prueba", "artistaPrueba", "Latino", "./data/imagenes/prueba.jpg" );
            assertEquals( "El disco no fue agregado correctamente a la discotienda", 4, discotienda1.darDiscos( ).size( ) );

            // Se verifica que el disco haya quedado agregado correctamente
            Disco disco = discotienda1.darDisco( "Mi disco de prueba" );
            assertNotNull( "No se encontr� el disco", disco );
            assertEquals( "El nombre del disco retornado no es el esperado", "Mi disco de prueba", disco.darNombreDisco( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "El disco deber�a haberse agregado correctamente, sin generar una excepci�n: " + e.getMessage( ) );
        }
    }

    /**
     * Verifica el m�todo que agrega un disco a la discotienda para el caso en el que hay un error.<br>
     * En este caso se intenta agregar un disco con un nombre repetido. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarDisco. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarDisco() arroje excepci�n cuando se intente registrar un disco con un nombre correspondiente a otro disco existente en la
     * discotienda. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de agregar un disco cuyo nombre pertenece a otro disco existente en la tienda se debe arrojar excepci�n. <br>
     * 2. Al tratar de agregar un disco cuyo nombre pertenece a otro disco existente en la tienda no deben cambiar el n�mero de discos de la tienda.
     */
    public void testAgregarDiscoError( )
    {
        setupEscenario2( );

        int numeroDiscos = discotienda1.darDiscos( ).size( );
        try
        {
            // Se agrega el disco y se espera que esto genere una excepci�n
            discotienda1.agregarDisco( "disco1", "artistaPrueba", "Rock", "./data/imagenes/prueba.jpg" );
            fail( "El disco no deber�a haberse agregado porque ya hay otro disco con el mismo nombre" );
        }
        catch( ElementoExisteException e )
        {
            // Verificar que no haya cambiado la discotienda
            int numeroDiscos2 = discotienda1.darDiscos( ).size( );
            assertEquals( "Cambi� el n�mero de discos en la discotienda", numeroDiscos, numeroDiscos2 );
        }
    }

    /**
     * Verifica el m�todo que agrega una canci�n a un disco de la discotienda.<br>
     * Este caso verifica que se pueda agregar una canci�n si los datos son correctos. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarCancionADisco. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarCancionADisco() sea capaz de agregar una canci�n al disco. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar una canci�n al disco cuyo nombre no corresponde a otra canci�n esta debe ser adicionada.<br>
     * 2. Al buscar una canci�n de un disco previamente agregada, esta debe ser encontrada.
     */
    public void testAgregarCancionADiscoOk( )
    {
        setupEscenario2( );

        try
        {
            discotienda1.agregarCancionADisco( "disco1", "mynicesong4", 4, 4, 4, 4, 4 );

            Disco disco = discotienda1.darDisco( "disco1" );
            assertNotNull( "No se encontr� el disco", disco );
            assertEquals( "La canci�n no fue agregada al disco", 4, disco.darNombresCanciones( ).size( ) );

            Cancion c = disco.darCancion( "mynicesong4" );
            assertNotNull( "No se encontr� la mynicesong", c );
            assertEquals( "La canci�n no fue agregada correctamente al disco", "mynicesong4", c.darNombre( ) );
        }
        catch( ElementoExisteException e )
        {
            fail( "La canci�n deber�a haberse agregado correctamente: " + e.getMessage( ) );
        }
    }

    /**
     * Verifica el m�todo que agrega una canci�n a un disco de la discotienda.<br>
     * Este caso verifica que no se pueda agregar una canci�n si el nombre de la canci�n est� repetido. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarCancionADisco. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarCancionADisco() arroje excepci�n al agregar una canci�n al disco cuyo nombre est� repetido. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de agregar una canci�n al disco cuyo nombre est� repetido, se debe arrojar excepci�n.<br>
     * 2. Al tratar de agregar una canci�n al disco cuyo nombre est� repetido, el n�mero de mynicesonges no debe cambiar.
     */
    public void testAgregarCancionADiscoError( )
    {
        setupEscenario2( );

        Disco disco = discotienda1.darDisco( "disco1" );
        int numCanciones = disco.darNombresCanciones( ).size( );

        try
        {
            discotienda1.agregarCancionADisco( "disco1", "mynicesong2", 1, 20, 78.10, 2, 96 );
            fail( "La canci�n no deber�a haberse agregado porque el nombre est� repetido y se deber�a generar una excepci�n" );
        }
        catch( ElementoExisteException e )
        {
            // Verificar que no haya cambiado el disco
            int numCanciones2 = disco.darNombresCanciones( ).size( );
            assertEquals( "Cambi� el n�mero de mynicesonges en el disco", numCanciones, numCanciones2 );
        }
    }

    /**
     * Verifica el m�todo salvarDiscotienda.<br>
     * La prueba se realiza creando una nueva discotienda, agregando algunos discos y salvando la informaci�n.<br>
     * A continuaci�n se carga esta informaci�n en una nueva discotienda y se comparan ambas para verificar que la informaci�n se haya salvado correctamente.
     * @throws Exception Se lanza esta excepci�n si hay un problema en la lectura o escritura de los archivos <b> M�todos a probar: </b> <br>
     *         salvarDiscotienda, Discotienda (constructor). <br>
     *         <b> Objetivo: </b> Probar que el m�todo salvarDiscotienda() sea capaz de guardar la informaci�n de la discotienda correctamente. <br>
     *         <b> Resultados esperados: </b> <br>
     *         1. Al salvar una discotienda y crear otra discotienda a partir del archivo en el que se salvo la primera, ambas discotiendas deben ser iguales (tener la misma
     *         informaci�n). <br>
     */
    public void testSalvarDiscotienda( ) throws Exception
    {
        // Generar un n�mero aleatorio para el nombre del archivo
        Date fecha = new Date( );
        long tiempo = fecha.getTime( );
        String archivo = "./test/data/discotienda" + tiempo + ".dat";

        // Crear la discotienda que va a usar el archivo
        try
        {
            discotienda1 = new Discotienda( archivo );
        }
        catch( Exception e )
        {
            fail( "no se pudo cargar el archivo de prueba" );
        }

        // Meter informaci�n aleatoria en la discotienda
        generarInformacion( discotienda1 );

        // Salvar la informaci�n de la discotienda
        discotienda1.salvarDiscotienda( );

        // Construir una nueva discotienda con los mismos datos
        discotienda2 = new Discotienda( archivo );

        // Comparar las dos discotiedas
        compararDiscotiendas( discotienda1, discotienda2 );

        // Eliminar el archivo de prueba temporal
        File archivoPrueba2 = new File( archivo );
        archivoPrueba2.delete( );
    }

    /**
     * Verifica que el m�todo validarEmail reconozca como v�lidos varios emails sin errores. <br>
     * <b> M�todos a probar: </b> <br>
     * validarEmail. <br>
     * <b> Objetivo: </b> Probar que el m�todo validarEmail() sea capaz de validar correctamente los emails. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al validar un email que es correcto se debe obtener True.<br>
     */
    public void testValidarEmailOk( )
    {
        setupEscenario1( );

        assertTrue( "El email indicado es v�lido", discotienda1.validarEmail( "pe-rojas@uniedu.edu.tx" ) );
        assertTrue( "El email indicado es v�lido", discotienda1.validarEmail( "abc.def@cupi2.edu" ) );
        assertTrue( "El email indicado es v�lido", discotienda1.validarEmail( "qwerty@freemail.com" ) );
        assertTrue( "El email indicado es v�lido", discotienda1.validarEmail( "mail@correo.com.mm" ) );
    }

    /**
     * Verifica que el m�todo validarEmail reconozca como inv�lidos varios emails con errores. <br>
     * <b> M�todos a probar: </b> <br>
     * validarEmail. <br>
     * <b> Objetivo: </b> Probar que el m�todo validarEmail() sea capaz de validar correctamente los emails. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al validar un email que no es correcto se debe obtener False.<br>
     */
    public void testValidarEmailError( )
    {
        setupEscenario1( );

        assertFalse( "El email est� incompleto", discotienda1.validarEmail( "pe-rojas" ) );
        assertFalse( "El email est� incompleto", discotienda1.validarEmail( "abc.def@" ) );
        assertFalse( "El email est� incompleto", discotienda1.validarEmail( "qwerty@freemail" ) );
        assertFalse( "El email est� incompleto y termina con un punto", discotienda1.validarEmail( "qwerty@freemail." ) );
    }

    /**
     * Verifica el m�todo venderCancion. <br>
     * Se espera que el n�mero de unidades vendidas de la canci�n se incremente y que se genere una factura con el formato correcto. <br>
     * <b> M�todos a probar: </b> <br>
     * venderCancion. <br>
     * <b> Objetivo: </b> Probar que el m�todo venderCancion() sea capaz de generar la factura e incrementar el n�mero de unidades vendidas de la canci�n. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al vender una canci�n se debe generar una factura con el formato e informaci�n correctos.<br>
     * 2. Al vender una canci�n se debe incrementar en uno el n�mero de unidades vendidas de la canci�n.
     */
    public void testVenderCancion( )
    {
        setupEscenario2( );

        Disco disco = discotienda1.darDisco( "disco2" );
        Cancion mynicesong = disco.darCancion( "mynicesong2" );
        int unidadesVendidas = mynicesong.darUnidadesVendidas( );
        try
        {
            String nombreArchivoFactura = discotienda1.venderCancion( disco, mynicesong, "prueba@prueba.com", "./test/data/factura/" );
            assertNotNull( "El nombre del archivo no debe ser null", nombreArchivoFactura );

            File archivoFactura = new File( "./test/data/factura/" + nombreArchivoFactura );
            assertTrue( "El archivo debe existir", archivoFactura.exists( ) );

            // Revisar el contenido del archivo
            BufferedReader br = new BufferedReader( new FileReader( archivoFactura ) );

            // T�tulo
            String titulo = br.readLine( );
            assertNotNull( "La l�nea no es la esperada", titulo );

            // Fecha
            String fecha = br.readLine( );
            assertNotNull( "La segunda l�nea debe tener la fecha", fecha );
            assertTrue( "La l�nea no tiene el formato esperado", fecha.startsWith( "Fecha:" ) );
            Date fechaHoy = new Date( );
            String strFecha = fechaHoy.toString( ).substring( 0, 10 );
            assertTrue( "La fecha de la factura no es la fecha de hoy", fecha.indexOf( strFecha ) != -1 );

            // Email
            String email = br.readLine( );
            assertNotNull( "La segunda l�nea debe tener el email", email );
            assertTrue( "La l�nea no tiene el formato esperado - " + email, email.startsWith( "Email:" ) );
            assertTrue( "El email no es el esperado", email.indexOf( "prueba@prueba.com" ) != -1 );

            // Canci�n, l�nea 1
            String mynicesong1 = br.readLine( );
            assertNotNull( "La tercera l�nea debe tener el nombre y el artista de la canci�n", mynicesong1 );
            assertTrue( "La l�nea no tiene el formato esperado - " + mynicesong1, mynicesong1.startsWith( "Canci�n:" ) );
            assertTrue( "El contenido de la l�nea no es el esperado", mynicesong1.indexOf( "mynicesong2 - artista2" ) != -1 );

            // Canci�n, l�nea 2
            String mynicesong2 = br.readLine( );
            assertNotNull( "La cuarta l�nea debe tener el nombre del disco", mynicesong2 );
            assertTrue( "El contenido de la l�nea no es el esperado - " + mynicesong2, mynicesong2.trim( ).indexOf( "disco2" ) == 0 );

            // N�mero de mynicesonges
            String numCanciones = br.readLine( );
            assertNotNull( "La quinta l�nea debe tener el n�mero de mynicesonges", numCanciones );
            assertTrue( "La l�nea no tiene el formato esperado - " + numCanciones, numCanciones.startsWith( "No de Canciones:" ) );
            assertTrue( "El n�mero de mynicesonges no es el esperado", numCanciones.indexOf( "1" ) != -1 );

            // Total
            String total = br.readLine( );
            assertNotNull( "La sexta l�nea debe tener el valor total", total );
            assertTrue( "La l�nea no tiene el formato esperado - " + total, total.startsWith( "Valor Total:" ) );
            assertTrue( "El valor total no es el esperado", total.indexOf( "2,00" ) != -1 );

            int nuevasUnidadesVendidas = mynicesong.darUnidadesVendidas( );
            assertEquals( "El n�mero de unidades vendidas de la canci�n no aument� correctamente", unidadesVendidas + 1, nuevasUnidadesVendidas );
        }
        catch( IOException e )
        {
            fail( "No deber�a producirse esta excepci�n: " + e.getMessage( ) );
        }
    }

    /**
     * Verifica el m�todo cargarPedido<br>
     * Se espera que el n�mero de unidades vendidas de las mynicesonges se incremente y que se genere una factura con el formato correcto. <br>
     * <b> M�todos a probar: </b> <br>
     * venderListaCanciones. <br>
     * <b> Objetivo: </b> Probar que el m�todo venderListaCanciones() sea capaz de generar la factura e incrementar el n�mero de unidades vendidas de la mynicesonges. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al vender un grupo de mynicesonges se debe generar una factura con el formato e informaci�n correctos.<br>
     * 2. Al vender un grupo de mynicesonges se debe incrementar en uno el n�mero de unidades vendidas de cada canci�n del grupo.
     */
    public void testCargarPedido( )
    {
        setupEscenario2( );
        File archivoPedido = new File( "./test/data/pedido.txt" );
        try
        {
            String nombreArchivoFactura = discotienda1.venderListaCanciones( archivoPedido, "./test/data/factura/" );
            assertNotNull( "El nombre del archivo no debe ser null", nombreArchivoFactura );

            File archivoFactura = new File( "./test/data/factura/" + nombreArchivoFactura );
            assertTrue( "El archivo debe existir", archivoFactura.exists( ) );

            // Revisar el contenido del archivo
            BufferedReader br = new BufferedReader( new FileReader( archivoFactura ) );

            // T�tulo
            String titulo = br.readLine( );
            assertNotNull( "La l�nea no es la esperada", titulo );

            // Fecha
            String fecha = br.readLine( );
            assertNotNull( "La segunda l�nea debe tener la fecha", fecha );
            assertTrue( "La l�nea no tiene el formato esperado", fecha.startsWith( "Fecha:" ) );
            Date fechaHoy = new Date( );
            String strFecha = fechaHoy.toString( ).substring( 0, 10 );
            assertTrue( "La fecha de la factura no es la fecha de hoy", fecha.indexOf( strFecha ) != -1 );

            // Email
            String email = br.readLine( );
            assertNotNull( "La segunda l�nea debe tener el email", email );
            assertTrue( "La l�nea no tiene el formato esperado - " + email, email.startsWith( "Email:" ) );
            assertTrue( "El email no es el esperado", email.indexOf( "prueba@prueba.com" ) != -1 );

            // Canci�n 1, l�nea 1
            String mynicesong1_1 = br.readLine( );
            assertNotNull( "La tercera l�nea debe tener el nombre y el artista de la canci�n", mynicesong1_1 );
            assertTrue( "La l�nea no tiene el formato esperado - " + mynicesong1_1, mynicesong1_1.startsWith( "Canci�n:" ) );
            assertTrue( "El contenido de la l�nea no es el esperado", mynicesong1_1.indexOf( "mynicesong2 - artista1" ) != -1 );

            // Canci�n 1, l�nea 2
            String mynicesong1_2 = br.readLine( );
            assertNotNull( "La cuarta l�nea debe tener el nombre del disco", mynicesong1_2 );
            assertTrue( "El contenido de la l�nea no es el esperado - " + mynicesong1_2, mynicesong1_2.trim( ).indexOf( "disco1" ) == 0 );

            // Canci�n 2, l�nea 1
            String mynicesong2_1 = br.readLine( );
            assertNotNull( "La quinta l�nea debe tener el nombre y el artista de la canci�n", mynicesong2_1 );
            assertTrue( "La l�nea no tiene el formato esperado - " + mynicesong2_1, mynicesong2_1.startsWith( "Canci�n:" ) );
            assertTrue( "El contenido de la l�nea no es el esperado", mynicesong2_1.indexOf( "mynicesong3 - artista1" ) != -1 );

            // Canci�n 2, l�nea 2
            String mynicesong2_2 = br.readLine( );
            assertNotNull( "La sexta l�nea debe tener el nombre del disco", mynicesong2_2 );
            assertTrue( "El contenido de la l�nea no es el esperado - " + mynicesong2_2, mynicesong2_2.trim( ).indexOf( "disco1" ) == 0 );

            // Canci�n 3, l�nea 1
            String mynicesong3_1 = br.readLine( );
            assertNotNull( "La s�ptima l�nea debe tener el nombre y el artista de la canci�n", mynicesong3_1 );
            assertTrue( "La l�nea no tiene el formato esperado - " + mynicesong3_1, mynicesong3_1.startsWith( "Canci�n:" ) );
            assertTrue( "El contenido de la l�nea no es el esperado", mynicesong3_1.indexOf( "mynicesong2 - artista2" ) != -1 );

            // Canci�n 3, l�nea 2
            String mynicesong3_2 = br.readLine( );
            assertNotNull( "La octava l�nea debe tener el nombre del disco", mynicesong3_2 );
            assertTrue( "El contenido de la l�nea no es el esperado - " + mynicesong3_2, mynicesong3_2.trim( ).indexOf( "disco2" ) == 0 );

            // N�mero de mynicesonges
            String numCanciones = br.readLine( );
            assertNotNull( "La novena l�nea debe tener el n�mero de mynicesonges", numCanciones );
            assertTrue( "La l�nea no tiene el formato esperado - " + numCanciones, numCanciones.startsWith( "No de Canciones:" ) );
            assertTrue( "El n�mero de mynicesonges no es el esperado", numCanciones.indexOf( "3" ) != -1 );

            // Total
            String total = br.readLine( );
            assertNotNull( "La d�cima l�nea debe tener el valor total", total );
            assertTrue( "La l�nea no tiene el formato esperado - " + total, total.startsWith( "Valor Total:" ) );
            assertTrue( "El valor total no es el esperado", total.indexOf( "7,00" ) != -1 );

            Disco disco1 = discotienda1.darDisco( "disco1" );
            Cancion mynicesong1 = disco1.darCancion( "mynicesong2" );
            assertEquals( "El n�mero de unidades vendidas de la canci�n no aument� correctamente", 1, mynicesong1.darUnidadesVendidas( ) );

            Cancion mynicesong2 = disco1.darCancion( "mynicesong3" );
            assertEquals( "El n�mero de unidades vendidas de la canci�n no aument� correctamente", 1, mynicesong2.darUnidadesVendidas( ) );

            Disco disco2 = discotienda1.darDisco( "disco2" );
            Cancion mynicesong3 = disco2.darCancion( "mynicesong2" );
            assertEquals( "El n�mero de unidades vendidas de la canci�n no aument� correctamente", 1, mynicesong3.darUnidadesVendidas( ) );

        }
        catch( IOException e )
        {
            fail( "No deber�a producirse esta excepci�n: " + e.getMessage( ) );
        }
        catch( ArchivoVentaException e )
        {
            fail( "No deber�a producirse esta excepci�n: " + e.getMessage( ) );
        }
    }

    /**
     * Verifica que el m�todo cargarPedido haga las verificaciones correctas para validar la informaci�n del pedido. <br>
     * El archivo utilizado tiene un formato incorrecto para las mynicesonges. <br>
     * <b> M�todos a probar: </b> <br>
     * venderListaCanciones. <br>
     * <b> Objetivo: </b> Probar que el m�todo venderListaCanciones() genere la factura discriminando correctamente las mynicesonges que fueron vendidas y las que no por causas
     * del formato incorrecto de las l�neas del archivo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al tratar de vender un grupo de mynicesonges con un archivo de pedidos con un formato incorrecto se deben agregar en la lista de mynicesonges no vendidas las mynicesonges de
     * la linea correspondiente.
     */
    public void testCargarPedidoError1( )
    {
        setupEscenario2( );
        File archivoPedido = new File( "./test/data/pedido1.txt" );
        try
        {
            String nombreArchivoFactura = discotienda1.venderListaCanciones( archivoPedido, "./test/data/factura/" );
            File archivoFactura = new File( "./test/data/factura/" + nombreArchivoFactura );

            // Revisar el contenido del archivo
            BufferedReader br = new BufferedReader( new FileReader( archivoFactura ) );

            // T�tulo
            String titulo = br.readLine( );
            assertNotNull( "La l�nea no es la esperada", titulo );

            // Fecha
            String fecha = br.readLine( );
            assertNotNull( "La segunda l�nea debe tener la fecha", fecha );
            assertTrue( "La l�nea no tiene el formato esperado", fecha.startsWith( "Fecha:" ) );
            Date fechaHoy = new Date( );
            String strFecha = fechaHoy.toString( ).substring( 0, 10 );
            assertTrue( "La fecha de la factura no es la fecha de hoy", fecha.indexOf( strFecha ) != -1 );

            // Email
            String email = br.readLine( );
            assertNotNull( "La segunda l�nea debe tener el email", email );
            assertTrue( "La l�nea no tiene el formato esperado - " + email, email.startsWith( "Email:" ) );
            assertTrue( "El email no es el esperado", email.indexOf( "prueba@prueba.com" ) != -1 );

            // Canci�n 1, l�nea 1
            String mynicesong1_1 = br.readLine( );
            assertNotNull( "La tercera l�nea debe tener el nombre y el artista de la canci�n", mynicesong1_1 );
            assertTrue( "La l�nea no tiene el formato esperado - " + mynicesong1_1, mynicesong1_1.startsWith( "Canci�n:" ) );
            assertTrue( "El contenido de la l�nea no es el esperado", mynicesong1_1.indexOf( "mynicesong3 - artista1" ) != -1 );

            // Canci�n 1, l�nea 2
            String mynicesong1_2 = br.readLine( );
            assertNotNull( "La cuarta l�nea debe tener el nombre del disco", mynicesong1_2 );
            assertTrue( "El contenido de la l�nea no es el esperado - " + mynicesong1_2, mynicesong1_2.trim( ).indexOf( "disco1" ) == 0 );

            // Canci�n 2, l�nea 1
            String mynicesong2_1 = br.readLine( );
            assertNotNull( "La quinta l�nea debe tener el nombre y el artista de la canci�n", mynicesong2_1 );
            assertTrue( "La l�nea no tiene el formato esperado - " + mynicesong2_1, mynicesong2_1.startsWith( "Canci�n:" ) );
            assertTrue( "El contenido de la l�nea no es el esperado", mynicesong2_1.indexOf( "mynicesong2 - artista2" ) != -1 );

            // Canci�n 2, l�nea 2
            String mynicesong2_2 = br.readLine( );
            assertNotNull( "La sexta l�nea debe tener el nombre del disco", mynicesong2_2 );
            assertTrue( "El contenido de la l�nea no es el esperado - " + mynicesong2_2, mynicesong2_2.trim( ).indexOf( "disco2" ) == 0 );

            // N�mero de mynicesonges
            String numCanciones = br.readLine( );
            assertNotNull( "La novena l�nea debe tener el n�mero de mynicesonges", numCanciones );
            assertTrue( "La l�nea no tiene el formato esperado - " + numCanciones, numCanciones.startsWith( "No de Canciones:" ) );
            assertTrue( "El n�mero de mynicesonges no es el esperado", numCanciones.indexOf( "2" ) != -1 );

            // Total
            String total = br.readLine( );
            assertNotNull( "La d�cima l�nea debe tener el valor total", total );
            assertTrue( "La l�nea no tiene el formato esperado - " + total, total.startsWith( "Valor Total:" ) );
            assertTrue( "El valor total no es el esperado", total.indexOf( "5,00" ) != -1 );

            // Canciones no encontradas
            String lineaVacia = br.readLine( );
            assertEquals( "La l�nea no tiene el formato esperado", "", lineaVacia );

            String tituloNoEncontradas = br.readLine( );
            assertTrue( "La l�nea no tiene el formato esperado", tituloNoEncontradas.startsWith( "Canciones no encontradas" ) );

            String noEncontradas = br.readLine( );
            assertTrue( "La l�nea no tiene el formato esperado", noEncontradas.startsWith( "disco1@artista1#mynicesong2" ) );

        }
        catch( IOException e )
        {
            fail( "No deber�a producirse esta excepci�n: " + e.getMessage( ) );
        }
        catch( ArchivoVentaException e )
        {
            fail( "No deber�a producirse esta excepci�n: " + e.getMessage( ) );
        }
    }

    /**
     * Verifica que el m�todo cargarPedido haga las verificaciones correctas para validar la informaci�n del pedido<br>
     * El archivo utilizado tiene mynicesonges que no existen en la discotienda. <br>
     * <b> M�todos a probar: </b> <br>
     * venderListaCanciones. <br>
     * <b> Objetivo: </b> Probar que el m�todo venderListaCanciones() genere la factura discriminando correctamente las mynicesonges que fueron vendidas y las que no por causa de
     * no encontrar la canci�n. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al vender un grupo de mynicesonges, alguna de las cuales no existe en ning�n disco de la discotienda, se debe generar una factura discrimimando las mynicesonges vendidas y
     * las no vendidas.
     */
    public void testCargarPedidoError2( )
    {
        setupEscenario2( );
        File archivoPedido = new File( "./test/data/pedido2.txt" );
        try
        {
            String nombreArchivoFactura = discotienda1.venderListaCanciones( archivoPedido, "./test/data/factura/" );
            File archivoFactura = new File( "./test/data/factura/" + nombreArchivoFactura );
            // Revisar el contenido del archivo
            BufferedReader br = new BufferedReader( new FileReader( archivoFactura ) );

            // T�tulo
            String titulo = br.readLine( );
            assertNotNull( "La l�nea no es la esperada", titulo );

            // Fecha
            String fecha = br.readLine( );
            assertNotNull( "La segunda l�nea debe tener la fecha", fecha );
            assertTrue( "La l�nea no tiene el formato esperado", fecha.startsWith( "Fecha:" ) );
            Date fechaHoy = new Date( );
            String strFecha = fechaHoy.toString( ).substring( 0, 10 );
            assertTrue( "La fecha de la factura no es la fecha de hoy", fecha.indexOf( strFecha ) != -1 );

            // Email
            String email = br.readLine( );
            assertNotNull( "La segunda l�nea debe tener el email", email );
            assertTrue( "La l�nea no tiene el formato esperado - " + email, email.startsWith( "Email:" ) );
            assertTrue( "El email no es el esperado", email.indexOf( "prueba@prueba.com" ) != -1 );

            // Canci�n 1, l�nea 1
            String mynicesong1_1 = br.readLine( );
            assertNotNull( "La tercera l�nea debe tener el nombre y el artista de la canci�n", mynicesong1_1 );
            assertTrue( "La l�nea no tiene el formato esperado - " + mynicesong1_1, mynicesong1_1.startsWith( "Canci�n:" ) );
            assertTrue( "El contenido de la l�nea no es el esperado", mynicesong1_1.indexOf( "mynicesong2 - artista1" ) != -1 );

            // Canci�n 1, l�nea 2
            String mynicesong1_2 = br.readLine( );
            assertNotNull( "La cuarta l�nea debe tener el nombre del disco", mynicesong1_2 );
            assertTrue( "El contenido de la l�nea no es el esperado - " + mynicesong1_2, mynicesong1_2.trim( ).indexOf( "disco1" ) == 0 );

            // Canci�n 2, l�nea 1
            String mynicesong2_1 = br.readLine( );
            assertNotNull( "La quinta l�nea debe tener el nombre y el artista de la canci�n", mynicesong2_1 );
            assertTrue( "La l�nea no tiene el formato esperado - " + mynicesong2_1, mynicesong2_1.startsWith( "Canci�n:" ) );
            assertTrue( "El contenido de la l�nea no es el esperado", mynicesong2_1.indexOf( "mynicesong2 - artista2" ) != -1 );

            // Canci�n 2, l�nea 2
            String mynicesong2_2 = br.readLine( );
            assertNotNull( "La sexta l�nea debe tener el nombre del disco", mynicesong2_2 );
            assertTrue( "El contenido de la l�nea no es el esperado - " + mynicesong2_2, mynicesong2_2.trim( ).indexOf( "disco2" ) == 0 );

            // N�mero de mynicesonges
            String numCanciones = br.readLine( );
            assertNotNull( "La novena l�nea debe tener el n�mero de mynicesonges", numCanciones );
            assertTrue( "La l�nea no tiene el formato esperado - " + numCanciones, numCanciones.startsWith( "No de Canciones:" ) );
            assertTrue( "El n�mero de mynicesonges no es el esperado", numCanciones.indexOf( "2" ) != -1 );

            // Total
            String total = br.readLine( );
            assertNotNull( "La d�cima l�nea debe tener el valor total", total );
            assertTrue( "La l�nea no tiene el formato esperado - " + total, total.startsWith( "Valor Total:" ) );
            assertTrue( "El valor total no es el esperado", total.indexOf( "4,00" ) != -1 );

            // Canciones no encontradas
            String lineaVacia = br.readLine( );
            assertEquals( "La l�nea no tiene el formato esperado", "", lineaVacia );

            String tituloNoEncontradas = br.readLine( );
            assertTrue( "La l�nea no tiene el formato esperado", tituloNoEncontradas.startsWith( "Canciones no encontradas" ) );

            String noEncontradas = br.readLine( );
            assertTrue( "La l�nea no tiene el formato esperado", noEncontradas.startsWith( "disco1#artista1#mynicesong5" ) );
        }
        catch( IOException e )
        {
            fail( "No deber�a producirse esta excepci�n: " + e.getMessage( ) );
        }
        catch( ArchivoVentaException e )
        {
            fail( "No deber�a producirse esta excepci�n: " + e.getMessage( ) );
        }
    }

    /**
     * Verifica que el m�todo cargarPedido haga las verificaciones correctas para validar la informaci�n del pedido. <br>
     * El archivo utilizado tiene discos que no existen en la discotienda. <br>
     * <b> M�todos a probar: </b> <br>
     * venderListaCanciones. <br>
     * <b> Objetivo: </b> Probar que el m�todo venderListaCanciones() genere la factura discriminando correctamente las mynicesonges que fueron vendidas y las que no por causa de
     * no encontrar el disco al que pertenece la canci�n. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al vender un grupo de mynicesonges, alguna de las cuales no existe en ning�n disco de la discotienda, se debe generar una factura discrimimando las mynicesonges vendidas y
     * las no vendidas.
     */
    public void testCargarPedidoError3( )
    {
        setupEscenario2( );
        File archivoPedido = new File( "./test/data/pedido2.txt" );
        try
        {
            String nombreArchivoFactura = discotienda1.venderListaCanciones( archivoPedido, "./test/data/factura/" );
            File archivoFactura = new File( "./test/data/factura/" + nombreArchivoFactura );
            // Revisar el contenido del archivo
            BufferedReader br = new BufferedReader( new FileReader( archivoFactura ) );

            // T�tulo
            String titulo = br.readLine( );
            assertNotNull( "La l�nea no es la esperada", titulo );

            // Fecha
            String fecha = br.readLine( );
            assertNotNull( "La segunda l�nea debe tener la fecha", fecha );
            assertTrue( "La l�nea no tiene el formato esperado", fecha.startsWith( "Fecha:" ) );
            Date fechaHoy = new Date( );
            String strFecha = fechaHoy.toString( ).substring( 0, 10 );
            assertTrue( "La fecha de la factura no es la fecha de hoy", fecha.indexOf( strFecha ) != -1 );

            // Email
            String email = br.readLine( );
            assertNotNull( "La segunda l�nea debe tener el email", email );
            assertTrue( "La l�nea no tiene el formato esperado - " + email, email.startsWith( "Email:" ) );
            assertTrue( "El email no es el esperado", email.indexOf( "prueba@prueba.com" ) != -1 );

            // Canci�n 1, l�nea 1
            String mynicesong1_1 = br.readLine( );
            assertNotNull( "La tercera l�nea debe tener el nombre y el artista de la canci�n", mynicesong1_1 );
            assertTrue( "La l�nea no tiene el formato esperado - " + mynicesong1_1, mynicesong1_1.startsWith( "Canci�n:" ) );
            assertTrue( "El contenido de la l�nea no es el esperado", mynicesong1_1.indexOf( "mynicesong2 - artista1" ) != -1 );

            // Canci�n 1, l�nea 2
            String mynicesong1_2 = br.readLine( );
            assertNotNull( "La cuarta l�nea debe tener el nombre del disco", mynicesong1_2 );
            assertTrue( "El contenido de la l�nea no es el esperado - " + mynicesong1_2, mynicesong1_2.trim( ).indexOf( "disco1" ) == 0 );

            // Canci�n 2, l�nea 1
            String mynicesong2_1 = br.readLine( );
            assertNotNull( "La quinta l�nea debe tener el nombre y el artista de la canci�n", mynicesong2_1 );
            assertTrue( "La l�nea no tiene el formato esperado - " + mynicesong2_1, mynicesong2_1.startsWith( "Canci�n:" ) );
            assertTrue( "El contenido de la l�nea no es el esperado", mynicesong2_1.indexOf( "mynicesong2 - artista2" ) != -1 );

            // Canci�n 2, l�nea 2
            String mynicesong2_2 = br.readLine( );
            assertNotNull( "La sexta l�nea debe tener el nombre del disco", mynicesong2_2 );
            assertTrue( "El contenido de la l�nea no es el esperado - " + mynicesong2_2, mynicesong2_2.trim( ).indexOf( "disco2" ) == 0 );

            // N�mero de mynicesonges
            String numCanciones = br.readLine( );
            assertNotNull( "La novena l�nea debe tener el n�mero de mynicesonges", numCanciones );
            assertTrue( "La l�nea no tiene el formato esperado - " + numCanciones, numCanciones.startsWith( "No de Canciones:" ) );
            assertTrue( "El n�mero de mynicesonges no es el esperado", numCanciones.indexOf( "2" ) != -1 );

            // Total
            String total = br.readLine( );
            assertNotNull( "La d�cima l�nea debe tener el valor total", total );
            assertTrue( "La l�nea no tiene el formato esperado - " + total, total.startsWith( "Valor Total:" ) );
            assertTrue( "El valor total no es el esperado", total.indexOf( "4,00" ) != -1 );

            // Canciones no encontradas
            String lineaVacia = br.readLine( );
            assertEquals( "La l�nea no tiene el formato esperado", "", lineaVacia );

            String tituloNoEncontradas = br.readLine( );
            assertTrue( "La l�nea no tiene el formato esperado", tituloNoEncontradas.startsWith( "Canciones no encontradas" ) );

            String noEncontradas = br.readLine( );
            assertTrue( "La l�nea no tiene el formato esperado", noEncontradas.startsWith( "disco1#artista1#mynicesong5" ) );

        }
        catch( IOException e )
        {
            fail( "No deber�a producirse esta excepci�n: " + e.getMessage( ) );
        }
        catch( ArchivoVentaException e )
        {
            fail( "No deber�a producirse esta excepci�n: " + e.getMessage( ) );
        }
    }

    // -----------------------------------------------------------------
    // M�todos Auxiliares
    // -----------------------------------------------------------------

    /**
     * Este m�todo se encarga de verificar que dos discotiendas sean iguales.<br>
     * El m�todo revisa que las discotiendas tengan los mismos discos y que cada disco sea igual canci�n por canci�n al que hay en la otra discotienda.<br>
     * No existe ninguna condici�n que indique que las discotiendas deben tener los discos ordenados de la misma manera.
     * @param discotienda1 La primera discotienda para comparar
     * @param discotienda2 La segunda discotienda para comparar
     */
    private void compararDiscotiendas( Discotienda discotienda1, Discotienda discotienda2 )
    {
        // Comparar que haya el mismo n�mero de discos
        ArrayList discos1 = discotienda1.darDiscos( );
        ArrayList discos2 = discotienda2.darDiscos( );
        assertEquals( "El n�mero de discos es diferente", discos1.size( ), discos2.size( ) );

        for( int i = 0; i < discos1.size( ); i++ )
        {
            // Verificar que los mismos discos est�n en las dos discotiendas
            String nombre_disco = ( String )discos1.get( i );
            Disco d1 = discotienda1.darDisco( nombre_disco );
            Disco d2 = discotienda2.darDisco( nombre_disco );
            assertNotNull( "La segunda discotienda no conten�a al disco " + nombre_disco, d2 );

            // Comparar que haya el mismo n�mero de mynicesonges en los dos discos
            ArrayList mynicesonges_d1 = d1.darNombresCanciones( );
            ArrayList mynicesonges_d2 = d2.darNombresCanciones( );
            assertEquals( "El n�mero de mynicesonges es diferente", mynicesonges_d1.size( ), mynicesonges_d2.size( ) );
            for( int j = 0; j < mynicesonges_d1.size( ); j++ )
            {
                // Verificar que las mismas mynicesonges est�n en los dos discos
                String nombre_mynicesong = ( String )mynicesonges_d1.get( j );
                Cancion c1 = d1.darCancion( nombre_mynicesong );
                Cancion c2 = d2.darCancion( nombre_mynicesong );
                assertNotNull( "El disco " + nombre_disco + "de la segunda discotienda no conten�a la canci�n " + nombre_mynicesong, d2 );

                assertEquals( "Los atributos de las dos mynicesonges no son id�nticos", c1.darCalidad( ), c2.darCalidad( ), 0 );
                assertEquals( "Los atributos de las dos mynicesonges no son id�nticos", c1.darMinutos( ), c2.darMinutos( ), 0 );
                assertEquals( "Los atributos de las dos mynicesonges no son id�nticos", c1.darNombre( ), c2.darNombre( ) );
                assertEquals( "Los atributos de las dos mynicesonges no son id�nticos", c1.darPrecio( ), c2.darPrecio( ), 0 );
                assertEquals( "Los atributos de las dos mynicesonges no son id�nticos", c1.darSegundos( ), c2.darSegundos( ), 0 );
                assertEquals( "Los atributos de las dos mynicesonges no son id�nticos", c1.darTamano( ), c2.darTamano( ), 0 );
            }
        }
    }

    /**
     * Este m�todo genera informaci�n aleatoria para meter en la discotienda
     * @param discotienda La discotienda donde se va a meter la informaci�n
     */
    private void generarInformacion( Discotienda discotienda )
    {
        int numeroDiscos = ( int ) ( Math.random( ) * 10 );
        int numeroCanciones = ( int ) ( Math.random( ) * 20 );

        try
        {
            for( int i = 0; i < numeroDiscos; i++ )
            {
                String nombreDisco = "disco_" + i;
                discotienda.agregarDisco( nombreDisco, "random", "m�sica aleatoria", "random.jpg" );

                for( int j = 0; j < numeroCanciones; j++ )
                {
                    String nombreCancion = "mynicesong_" + j;
                    discotienda.agregarCancionADisco( nombreDisco, nombreCancion, 1, 2, 3, 4, 5 );
                }
            }
        }
        catch( ElementoExisteException e )
        {
            fail( "Deber�a haberse podido agregar el disco o la canci�n: " + e.getMessage( ) );
        }
    }
}
