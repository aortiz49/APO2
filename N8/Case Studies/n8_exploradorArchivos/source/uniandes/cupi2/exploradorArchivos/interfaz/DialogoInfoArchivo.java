/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoInfoArchivo.java,v 1.4 2006/08/06 01:05:55 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_exploradorArchivos
 * Autor: Pablo Barvo - 7/07/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.exploradorArchivos.interfaz;

import java.awt.*;

import javax.swing.*;

import uniandes.cupi2.exploradorArchivos.mundo.*;

/**
 * Dialogo para la visualizaci�n de la informaci�n de un archivo
 */
public class DialogoInfoArchivo extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la informaci�n
     */
    private PanelInfoArchivo panelInfoArchivo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del di�logo de visualizaci�n
     * @param principal es la ventana principal de la aplicaci�n
     * @param archivo es el archivo que se va a visualizar
     */
    public DialogoInfoArchivo( JFrame principal, Archivo archivo )
    {
        super( principal, true );
        setTitle( "Archivo " + archivo.darNombre( ) );
        setLayout( new BorderLayout( ) );

        // Crea el panel con la informaci�n
        panelInfoArchivo = new PanelInfoArchivo( archivo );
        add( panelInfoArchivo, BorderLayout.CENTER );

        // Pack de la interfaz y centra la caja de di�logo
        pack( );
        setLocationRelativeTo( principal );
    }
}
