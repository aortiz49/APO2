/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBusqueda.java,v 1.5 2006/08/06 01:05:55 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel para la b�squeda de archivos
 */
public class PanelBusqueda extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para buscar
     */
    private static final String BUSCAR = "BUSCAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazExploradorArchivos principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Lista de archivos
     */
    private JList listaArchivos;

    /**
     * Scroll para la lista
     */
    private JScrollPane scroll;

    /**
     * Texto de la b�squeda
     */
    private JTextField txtBusqueda;

    /**
     * Bot�n Buscar
     */
    private JButton botonBuscar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pPrincipal es la ventana principal de la aplicaci�n
     */
    public PanelBusqueda( InterfazExploradorArchivos pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "B�squeda" ) );
        setLayout( new BorderLayout( ) );

        // Agrega los elementos
        listaArchivos = new JList( );
        scroll = new JScrollPane( listaArchivos );
        scroll.setPreferredSize( new Dimension( 250, 250 ) );
        add( scroll, BorderLayout.CENTER );

        // Panel de la b�squeda
        JPanel panelBusqueda = new JPanel( );
        panelBusqueda.setBorder( new TitledBorder( "Palabra" ) );
        panelBusqueda.setLayout( new BorderLayout( ) );
        add( panelBusqueda, BorderLayout.SOUTH );

        botonBuscar = new JButton( "Buscar" );
        botonBuscar.setActionCommand( BUSCAR );
        botonBuscar.addActionListener( this );
        panelBusqueda.add( botonBuscar, BorderLayout.EAST );

        txtBusqueda = new JTextField( );
        txtBusqueda.setColumns( 25 );
        panelBusqueda.add( txtBusqueda, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza la informaci�n de los archivos
     * @param archivos son los archivos presentes en el directorio actual
     */
    public void refrescar( ArrayList archivos )
    {
        if( archivos.size( ) > 0 )
        {
            listaArchivos.setListData( archivos.toArray( ) );
        }
        else
        {
            // Si no devolvi� archivos, muestra un mensaje
            listaArchivos.setListData( new Object[]{ "0 archivos encontrados..." } );
        }
    }

    /**
     * Limpia el panel
     */
    public void limpiar( )
    {
        // Limpia la lista de resultados y el campo de texto
        listaArchivos.setListData( new Object[0] );
        txtBusqueda.setText( "" );
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
        // Click en el bot�n buscar
        String criterio = txtBusqueda.getText( );

        // Verifica que no tenga espacios
        if( criterio.indexOf( ' ' ) != -1 )
        {
            JOptionPane.showMessageDialog( this, "No debe incluir espacios", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            principal.buscar( criterio );
        }
    }
}
