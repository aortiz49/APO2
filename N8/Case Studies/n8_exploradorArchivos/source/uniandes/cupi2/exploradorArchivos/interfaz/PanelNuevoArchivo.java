/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelNuevoArchivo.java,v 1.7 2006/08/13 23:59:12 jvillalo2 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_exploradorArchivos
 * Autor: Pablo Barvo - 5/07/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.exploradorArchivos.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel para la creación de nuevos archivos
 */
public class PanelNuevoArchivo extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para crear el archivo
     */
    private static final String CREAR = "CREAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazExploradorArchivos principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Texto para el archivo
     */
    private JTextArea txtTexto;

    /**
     * Scroll para el texto
     */
    private JScrollPane scroll;

    /**
     * Texto con el nombre del archivo
     */
    private JTextField txtNombre;

    /**
     * Botón para crear el archivo
     */
    private JButton botonCrear;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pPrincipal es la ventana principal de la aplicación
     */
    public PanelNuevoArchivo( InterfazExploradorArchivos pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Crear Archivo" ) );
        setLayout( new BorderLayout( ) );

        // Agrega los elementos
        txtTexto = new JTextArea( );
        txtTexto.setLineWrap( true );
        txtTexto.setWrapStyleWord( true );
        scroll = new JScrollPane( txtTexto );
        scroll.setPreferredSize( new Dimension( 220, 220 ) );
        add( scroll, BorderLayout.CENTER );

        // Panel de la búsqueda
        JPanel panelBusqueda = new JPanel( );
        panelBusqueda.setBorder( new TitledBorder( "Nombre para el Archivo" ) );
        panelBusqueda.setLayout( new BorderLayout( ) );
        add( panelBusqueda, BorderLayout.SOUTH );

        botonCrear = new JButton( "Crear" );
        botonCrear.setActionCommand( CREAR );
        botonCrear.addActionListener( this );
        panelBusqueda.add( botonCrear, BorderLayout.EAST );

        txtNombre = new JTextField( );
        txtNombre.setColumns( 14 );
        panelBusqueda.add( txtNombre, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Limpia los texto de nombre y contenido del archivo
     */
    public void limpiar( )
    {
        txtNombre.setText( "" );
        txtTexto.setText( "" );
    }

    // -----------------------------------------------------------------
    // Eventos
    // -----------------------------------------------------------------

    /**
     * Maneja los eventos de los botones
     * @param e es el evento de click
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( CREAR.equals( comando ) )
            // Click en el botón buscar
            principal.crearArchivo( txtNombre.getText( ), txtTexto.getText( ) );
    }
}
