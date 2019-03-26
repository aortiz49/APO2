/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: TipoLineaRenderer.java,v 1.4 2006/10/02 17:14:35 da-romer Exp $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_paint
 * Autor: Mario Sánchez - 27/09/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.paint.interfaz;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

/**
 * Esta clase se usa para determinar la forma en la que los tipos de línea se muestran en el combo box
 */
public class TipoLineaRenderer extends JPanel implements ListCellRenderer
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el tipo de línea que debe mostrarse
     */
    private BasicStroke tipoLinea;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el campo
     */
    public TipoLineaRenderer( )
    {
        setOpaque( true );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Determina la forma en la que debe verse la opción dependiendo del valor, la posición, si está seleccionado y si tiene el foco
     * @param list Es la lista en la que se va a mostrar la opción
     * @param value Es el valor que se debe mostrar
     * @param index Es la posición que ocupa el valor en la lista
     * @param isSelected Indica si el valor está seleccionado
     * @param cellHasFocus Indica si el valor tiene el foco actualmente
     * @return Retorna el componente configurado para mostrar el valor
     */
    public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus )
    {
        tipoLinea = ( BasicStroke )value;

        if( tipoLinea != null && getGraphics( ) != null )
        {
            setBackground( isSelected ? new Color( 220, 220, 220 ) : Color.white );

            Graphics2D g = ( Graphics2D )getGraphics( );
            g.setColor( Color.BLACK );
            g.setStroke( tipoLinea );
            Line2D.Double linea = new Line2D.Double( 5, 5, 15, 5 );
            g.draw( linea );
        }
        return this;
    }

    /**
     * Dibuja el campo
     * @param g Es la superficie sobre la que se pinta el campo
     */
    public void paint( Graphics g )
    {
        super.paint( g );

        Graphics2D g2 = ( Graphics2D )g;
        g2.setColor( Color.BLACK );
        g2.setStroke( tipoLinea );

        Line2D.Double linea = new Line2D.Double( 5, 10, 35, 10 );
        g2.draw( linea );

    }

}