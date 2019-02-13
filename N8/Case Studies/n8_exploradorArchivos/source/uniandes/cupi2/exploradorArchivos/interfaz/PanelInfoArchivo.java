/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelInfoArchivo.java,v 1.3 2006/08/06 00:52:54 da-romer Exp $
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
import javax.swing.border.*;

import uniandes.cupi2.exploradorArchivos.mundo.*;

/**
 * Panel con la informaci�n de un archivo
 */
public class PanelInfoArchivo extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Archivo en visualizaci�n
     */
    private Archivo archivo;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta del nombre
     */
    private JLabel etiquetaNombre;

    /**
     * Nombre del archivo
     */
    private JLabel nombre;

    /**
     * Etiqueta del tama�o
     */
    private JLabel etiquetaTamanio;

    /**
     * Tama�o del archivo
     */
    private JLabel tamanio;

    /**
     * Etiqueta de fecha �ltima modificaci�n
     */
    private JLabel etiquetaFecha;

    /**
     * Ultima Modificaci�n del archivo
     */
    private JLabel fecha;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param pArchivo es el archivo a visualizar
     */
    public PanelInfoArchivo( Archivo pArchivo )
    {
        archivo = pArchivo;
        setLayout( new GridLayout( 3, 2 ) );
        setBorder( new TitledBorder( "Informaci�n Archivo" ) );

        // Nombre del archivo
        etiquetaNombre = new JLabel( "Nombre:" );
        add( etiquetaNombre );
        nombre = new JLabel( archivo.darNombre( ) );
        add( nombre );

        // Tama�o
        etiquetaTamanio = new JLabel( "Tama�o:" );
        add( etiquetaTamanio );
        tamanio = new JLabel( archivo.darTamanioString( ) );
        add( tamanio );

        // Fecha Ultima Modificaci�n
        etiquetaFecha = new JLabel( "Ultima Modificaci�n:" );
        add( etiquetaFecha );
        fecha = new JLabel( archivo.darFechaUltimaModificacion( ).toString( ) );
        add( fecha );
    }
}
