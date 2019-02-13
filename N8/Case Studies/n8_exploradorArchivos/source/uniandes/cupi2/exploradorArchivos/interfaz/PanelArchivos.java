/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelArchivos.java,v 1.6 2006/08/06 00:52:54 da-romer Exp $
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

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import uniandes.cupi2.exploradorArchivos.mundo.*;

/**
 * Panel con la informaci�n de los archivos
 */
public class PanelArchivos extends JPanel implements ListSelectionListener
{
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

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pPrincipal es la ventana principal de la aplicaci�n
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza la informaci�n de los archivos
     * @param archivos son los archivos nuevos
     */
    public void refrescar( Archivo[] archivos )
    {
        listaArchivos.setListData( archivos );
    }

    /**
     * Devuelve el archivo seleccionado. Devuelve null si ning�n archivo esta seleccionado
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
     * Cambio en la selecci�n de la lista
     * @param e es el evento de cambio
     */
    public void valueChanged( ListSelectionEvent e )
    {
        // Selecci�n en la lista de archivos
        Archivo archivo = darArchivoSeleccionado( );
        // Muestra el di�logo del archivo
        principal.verInfoArchivo( archivo );
    }
}
