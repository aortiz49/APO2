/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazContactos.java,v 1.11 2006/10/22 02:21:44 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_contactos
 * Autor: Mario Sánchez - 28/10/2005
 * Autor: Daniel Romero - 19/10/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.contactos.interfaz;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import uniandes.cupi2.contactos.mundo.*;

/**
 * Esta es la clase principal de la Interfaz
 */
public class InterfazContactos extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia al directorio de contactos
     */
    private IDirectorio directorio;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se muestran los contactos
     */
    private PanelContactos panelContactos;

    /**
     * Es el panel donde se encuentran los botones para controlar la aplicación
     */
    private PanelBotones panelBotones;

    /**
     * Es el panel con la imagen decorativa
     */
    private PanelImagen panelImagen;

    /**
     * Es el panel con los datos del contacto
     */
    private PanelDatosContacto panelDatosContacto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz de la aplicación
     */
    public InterfazContactos( )
    {
        directorio = new Directorio( );

        setLayout( new GridBagLayout( ) );

        panelImagen = new PanelImagen( );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;

        add( panelImagen, gbc );

        panelContactos = new PanelContactos( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add( panelContactos, gbc );

        panelDatosContacto = new PanelDatosContacto( );
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add( panelDatosContacto, gbc );

        panelBotones = new PanelBotones( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        getContentPane( ).add( panelBotones, gbc );

        setSize( 544, 450 );
        setTitle( "Directorio de Contactos" );
        setResizable( false );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        centrar( );

        modoInsercion( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Centra la ventana en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    /**
     * Muestra el diálogo con las estadísticas del directorio
     */
    public void mostrarDialogoEstadisticas( )
    {
        DialogoEstadisticas de = new DialogoEstadisticas( this, directorio.darEstadisticas( ) );
        de.setVisible( true );
    }

    /**
     * Agrega un nuevo contacto
     * @param nombre Nombre del nuevo contacto
     * @param telefono Teléfono del nuevo contacto
     * @param direccion Dirección del nuevo contacto
     * @param email Email del nuevo contacto
     */
    public void agregarContacto( String nombre, String telefono, String direccion, String email )
    {
        try
        {
            directorio.agregarContacto( nombre, telefono, direccion, email );
            actualizarContactos( );
        }
        catch( ContactoRepetidoException e )
        {
            JOptionPane.showMessageDialog( this, "Ya hay un contacto con ese nombre", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Elimina el contacto seleccionado en la lista
     */
    public void eliminarContacto( )
    {
        String nombre = panelContactos.darContactoSeleccionado( );
        if( nombre != null )
        {
            directorio.eliminarContacto( nombre );
            panelDatosContacto.limpiar( );
            actualizarContactos( );

        }
    }

    /**
     * Busca un contacto dado su nombre y lo selecciona en la lista
     */
    public void buscarContacto( )
    {
        String nombreBuscado = JOptionPane.showInputDialog( this, "Nombre del Contacto buscado" );
        if( nombreBuscado != null )
        {
            Contacto c = directorio.buscarContacto( nombreBuscado );
            if( c != null )
            {
                panelContactos.seleccionarContacto( c );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "No se encontró un contacto con ese nombre", "Buscar", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

    /**
     * Muestra los datos del contacto seleccionado
     */
    public void verContacto( )
    {
        String nombre = panelContactos.darContactoSeleccionado( );

        if( nombre != null )
        {
            Contacto c = directorio.buscarContacto( nombre );
            if( c != null )
            {
                panelDatosContacto.mostrarInformacionContacto( c );
            }
        }
    }

    /**
     * Actualiza la lista de contactos mostrada
     */
    public void actualizarContactos( )
    {
        Collection contactos = directorio.darListaContactos( );
        if( contactos == null )
            contactos = new ArrayList( );
        panelContactos.cambiarListaContactos( contactos );
    }

    /**
     * Agrega un contacto con los datos que se encuentran en el panel de datos
     */
    public void agregarContacto( )
    {
        String nombre = panelDatosContacto.darNombre( );
        String telefono = panelDatosContacto.darTelefono( );
        String direccion = panelDatosContacto.darDireccion( );
        String email = panelDatosContacto.darEmail( );

        if( "".equals( nombre ) )
        {
            JOptionPane.showMessageDialog( this, "Debe indicar un nombre", "Directorio Contactos", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try
            {
                directorio.agregarContacto( nombre, telefono, direccion, email );
                actualizarContactos( );
                panelDatosContacto.limpiar( );
            }
            catch( ContactoRepetidoException e )
            {
                JOptionPane.showMessageDialog( this, "Ya existe un contacto con el nombre " + nombre, "Directorio Contactos", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Configura todos los paneles de acuerdo al modo de búsqueda
     * 
     */
    public void modoBusqueda( )
    {
        panelBotones.modoBusqueda( );
        panelDatosContacto.modoBusqueda( );
        panelContactos.modoBusqueda( );
    }

    /**
     * Configura todos los paneles de acuerdo al modo de inserción
     * 
     */
    public void modoInsercion( )
    {
        panelBotones.modoInsercion( );
        panelDatosContacto.modoInsercion( );
        panelContactos.modoInsercion( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @param valor El valor necesario para la extensión
     */
    public void reqFuncOpcion1( int valor )
    {
        String resultado = directorio.metodo1( valor );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     * @param valor El valor necesario para la extensión
     */
    public void reqFuncOpcion2( int valor )
    {
        String resultado = directorio.metodo2( valor );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 3
     * @param valor El valor necesario para la extensión
     */
    public void reqFuncOpcion3( String valor )
    {
        String resultado = directorio.metodo3( valor );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 4
     * @param valor El valor necesario para la extensión
     */
    public void reqFuncOpcion4( String valor )
    {
        String resultado = directorio.metodo4( valor );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 5
     * @param valor El valor necesario para la extensión
     */
    public void reqFuncOpcion5( String valor )
    {
        String resultado = directorio.metodo5( valor );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación
     * @param args Parámetros de ejecución - No se deben utilizar
     */
    public static void main( String[] args )
    {
        InterfazContactos ic = new InterfazContactos( );
        ic.setVisible( true );
    }
}