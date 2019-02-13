/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelArchivos.java,v 1.6 2006/08/06 00:52:54 da-romer Exp $
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

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import uniandes.cupi2.exploradorArchivos.mundo.*;

/**
 * Panel con la información de los archivos
 */
public class PanelArchivos extends JPanel implements ListSelectionListener
{
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
     * Lista de archivos
     */
    private JList listaArchivos;

    /**
     * Scroll para la lista
     */
    private JScrollPane scroll;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pPrincipal es la ventana principal de la aplicación
     */
    public PanelArchivos( InterfazExploradorArchivos pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Archivos" ) );
        setLayout( new BorderLayout( ) );

        // Agrega los elementos
        listaArchivos = new JList( );
        listaArchivos.addListSelectionListener( this );
        scroll = new JScrollPane( listaArchivos );
        scroll.setPreferredSize( new Dimension( 250, 200 ) );
        add( scroll, BorderLayout.CENTER );
        setPreferredSize( new Dimension( 300, 250 ) );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la información de los archivos
     * @param archivos son los archivos nuevos
     */
    public void refrescar( Archivo[] archivos )
    {
        listaArchivos.setListData( archivos );
    }

    /**
     * Devuelve el archivo seleccionado. Devuelve null si ningún archivo esta seleccionado
     * @return Archivo seleccionado
     */
    public Archivo darArchivoSeleccionado( )
    {
        return ( Archivo )listaArchivos.getSelectedValue( );
    }

    // -----------------------------------------------------------------
    // Eventos
    // -----------------------------------------------------------------

    /**
     * Cambio en la selección de la lista
     * @param e es el evento de cambio
     */
    public void valueChanged( ListSelectionEvent e )
    {
        // Selección en la lista de archivos
        Archivo archivo = darArchivoSeleccionado( );
        // Muestra el diálogo del archivo
        principal.verInfoArchivo( archivo );
    }
}
