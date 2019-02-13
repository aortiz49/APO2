/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDirectorios.java,v 1.5 2006/08/06 01:05:55 da-romer Exp $
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
 * Panel con los subdirectorios actuales
 */
public class PanelDirectorios extends JPanel implements ListSelectionListener
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Interfaz principal de la aplicación
     */
    private InterfazExploradorArchivos principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Lista de directorios
     */
    private JList listaDirectorios;

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
    public PanelDirectorios( InterfazExploradorArchivos pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Sub-Directorios" ) );
        setLayout( new BorderLayout( ) );

        // Agrega los elementos
        listaDirectorios = new JList( );
        listaDirectorios.addListSelectionListener( this );
        scroll = new JScrollPane( listaDirectorios );
        scroll.setPreferredSize( new Dimension( 250, 180 ) );
        add( scroll, BorderLayout.CENTER );
        setPreferredSize( new Dimension( 300, 230 ) );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la información de los directorios
     * @param directorios son los directorios nuevos
     */
    public void refrescar( Directorio[] directorios )
    {
        listaDirectorios.setListData( directorios );
    }

    // -----------------------------------------------------------------
    // Eventos
    // -----------------------------------------------------------------

    /**
     * Acción ejecutada cuando la lista cambia de valor
     * @param e es el evento de cambio
     */
    public void valueChanged( ListSelectionEvent e )
    {
        // Selección de un elemento de la lista
        int indice = listaDirectorios.getSelectedIndex( );
        if( indice != -1 )
        {
            principal.navegar( indice );
        }
    }
}
