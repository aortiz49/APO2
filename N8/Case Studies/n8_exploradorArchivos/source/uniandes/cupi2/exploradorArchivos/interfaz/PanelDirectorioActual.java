/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDirectorioActual.java,v 1.4 2006/08/06 01:05:55 da-romer Exp $
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
 * Panel con la información del directorio actual
 */
public class PanelDirectorioActual extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para subir el directorio
     */
    private static final String SUBIR = "SUBIR";

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
     * Texto del directorio actual
     */
    private JTextField txtDirectorio;

    /**
     * Botón subir directorio
     */
    private JButton botonSubir;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pPrincipal es la ventana principal de la aplicación
     */
    public PanelDirectorioActual( InterfazExploradorArchivos pPrincipal )
    {
        principal = pPrincipal;
        setBorder( new TitledBorder( "Directorio Actual:" ) );
        setLayout( new BorderLayout( ) );

        // Agrega los elementos
        txtDirectorio = new JTextField( );
        txtDirectorio.setColumns( 60 );
        txtDirectorio.setEditable( false );
        add( txtDirectorio, BorderLayout.CENTER );

        // Agrega el botón
        botonSubir = new JButton( "Subir" );
        botonSubir.setActionCommand( SUBIR );
        botonSubir.addActionListener( this );
        add( botonSubir, BorderLayout.EAST );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la información del directorio actual
     * @param rutaActual es la ruta actual del explorador
     */
    public void refrescar( String rutaActual )
    {
        txtDirectorio.setText( rutaActual );
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

        if( SUBIR.equals( comando ) )
            // Click en el botón subir
            principal.subir( );
    }
}
