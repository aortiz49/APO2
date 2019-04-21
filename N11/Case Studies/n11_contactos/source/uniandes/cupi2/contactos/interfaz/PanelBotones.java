/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBotones.java,v 1.12 2006/10/23 15:09:19 da-romer Exp $ 
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
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Es el panel donde se muestran los botones para controlar la aplicación
 */
public class PanelBotones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String INSERTAR = "Insertar";

    private static final String ELIMINAR = "Eliminar";

    private static final String BUSCAR = "Buscar";

    private static final String MODO_INSERCION = "Modo Inserción";

    private static final String MODO_BUSQUEDA = "Modo Búsqueda";

    private static final String MOSTRAR_ESTADISTICAS = "Estadísticas";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazContactos ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el botón para agregar un contacto
     */
    private JButton botonInsertar;

    /**
     * Es el botón para eliminar un contacto
     */
    private JButton botonEliminar;

    /**
     * Es el botón para buscar un contacto
     */
    private JButton botonBuscar;

    /**
     * Es el botón para cambiar el directorio a modo inserción
     */
    private JButton botonModoInsercion;

    /**
     * Es el botón para cambiar el directorio a modo búsqueda
     */
    private JButton botonModoBuqueda;

    /**
     * Es el botón para mostrar el díálogo con las estadísticas y las extensiones
     */
    private JButton botonMostrarEstisticas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param ic Es una referencia a la clase principal de la interfaz
     */
    public PanelBotones( InterfazContactos ic )
    {
        ventanaPrincipal = ic;

        setLayout( new GridBagLayout( ) );

        JPanel panelModos = new JPanel( );
        panelModos.setLayout( new GridBagLayout( ) );
        panelModos.setBorder( new TitledBorder( "Modos" ) );

        botonModoBuqueda = new JButton( MODO_BUSQUEDA );
        botonModoBuqueda.setActionCommand( MODO_BUSQUEDA );
        botonModoBuqueda.addActionListener( this );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        panelModos.add( botonModoBuqueda, gbc );

        botonModoInsercion = new JButton( MODO_INSERCION );
        botonModoInsercion.setActionCommand( MODO_INSERCION );
        botonModoInsercion.addActionListener( this );
        botonModoInsercion.setEnabled( false );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panelModos.add( botonModoInsercion, gbc );

        JPanel panelOpciones = new JPanel( );
        panelOpciones.setLayout( new GridBagLayout( ) );
        panelOpciones.setBorder( new TitledBorder( "Opciones" ) );

        botonInsertar = new JButton( new ImageIcon( "data/agregar.gif" ) );
        botonInsertar.setActionCommand( INSERTAR );
        botonInsertar.addActionListener( this );
        botonInsertar.setMnemonic( KeyEvent.VK_I );
        botonInsertar.setToolTipText( INSERTAR );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        panelOpciones.add( botonInsertar, gbc );

        gbc.gridy = 1;
        botonEliminar = new JButton( new ImageIcon( "data/eliminar.gif" ) );
        botonEliminar.setActionCommand( ELIMINAR );
        botonEliminar.addActionListener( this );
        botonEliminar.setMnemonic( KeyEvent.VK_E );
        botonEliminar.setToolTipText( ELIMINAR );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        panelOpciones.add( botonEliminar, gbc );

        botonBuscar = new JButton( new ImageIcon( "data/buscar.gif" ) );
        botonBuscar.setActionCommand( BUSCAR );
        botonBuscar.addActionListener( this );
        botonBuscar.setMnemonic( KeyEvent.VK_B );
        botonBuscar.setToolTipText( BUSCAR );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        panelOpciones.add( botonBuscar, gbc );

        botonMostrarEstisticas = new JButton( new ImageIcon( "data/estadisticas.png" ) );
        botonMostrarEstisticas.setActionCommand( MOSTRAR_ESTADISTICAS );
        botonMostrarEstisticas.addActionListener( this );
        botonMostrarEstisticas.setMnemonic( KeyEvent.VK_S );
        botonMostrarEstisticas.setToolTipText( MOSTRAR_ESTADISTICAS );
        gbc = new GridBagConstraints( );
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        panelOpciones.add( botonMostrarEstisticas, gbc );

        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 0, 5 );
        add( panelModos, gbc );

        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add( panelOpciones, gbc );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este es el método que se llama cuando se hace click sobre alguno de los botones del panel
     * @param evento Es el evento de click sobre el botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( INSERTAR.equals( comando ) )
        {
            ventanaPrincipal.agregarContacto( );
        }
        else if( ELIMINAR.equals( comando ) )
        {
            ventanaPrincipal.eliminarContacto( );
        }
        else if( BUSCAR.equals( comando ) )
        {
            ventanaPrincipal.buscarContacto( );
        }
        else if( MOSTRAR_ESTADISTICAS.equals( comando ) )
        {
            ventanaPrincipal.mostrarDialogoEstadisticas( );
        }
        else if( MODO_BUSQUEDA.equals( comando ) )
        {
            ventanaPrincipal.modoBusqueda( );
            botonModoBuqueda.setEnabled( false );
            botonModoInsercion.setEnabled( true );
        }
        else if( MODO_INSERCION.equals( comando ) )
        {
            ventanaPrincipal.modoInsercion( );
            botonModoBuqueda.setEnabled( true );
            botonModoInsercion.setEnabled( false );
        }
    }

    /**
     * Pone disponibles los botones que se pueden usar en el modo de inserción
     * 
     */
    public void modoInsercion( )
    {
        botonInsertar.setEnabled( true );
        botonEliminar.setEnabled( false );
        botonBuscar.setEnabled( false );
        botonMostrarEstisticas.setEnabled( false );

    }

    /**
     * Pone disponibles los botones que se pueden usar en el modo de búsqueda
     * 
     */
    public void modoBusqueda( )
    {
        botonInsertar.setEnabled( false );
        botonEliminar.setEnabled( true );
        botonBuscar.setEnabled( true );
        botonMostrarEstisticas.setEnabled( true );
    }
}