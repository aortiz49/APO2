/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Ovalo.java,v 1.9 2007/04/13 04:29:55 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_paint
 * Autor: Mario Sánchez - 27/09/2005 
 * Autor: Jorge Villalobos - 26/09/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.paint.mundo;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;

/**
 * Esta clase representa un óvalo que hace parte de una composición. <br>
 * <b>inv:</b> <br>
 * ovalo!=null
 */
public class Ovalo extends FiguraRellena
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa el tipo de la figura
     */
    public final static String TIPO = "OVALO";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Representación gráfica del óvalo
     */
    private Ellipse2D ovalo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un óvalo
     * @param x1f La coordenada x del primer punto - x1f>=0
     * @param y1f La coordenada y del primer punto - y1f>=0
     * @param x2f La coordenada x del segundo punto - x2f>=0
     * @param y2f La coordenada y del segundo punto - y2f>=0
     * @param colorLineaF El color de la línea de la figura - colorLineaF != null
     * @param colorFondoF El color del fondo de la figura - colorFondoF != null
     * @param tipoLineaF El tipo de línea usado para pintar la figura - tipoLineaF != null
     */
    public Ovalo( int x1f, int y1f, int x2f, int y2f, Color colorLineaF, Color colorFondoF, BasicStroke tipoLineaF )
    {
        super( Math.min( x1f, x2f ), Math.min( y1f, y2f ), Math.max( x1f, x2f ), Math.max( y1f, y2f ), colorLineaF, colorFondoF, tipoLineaF );
        int ancho = Math.abs( x1 - x2 );
        int alto = Math.abs( y1 - y2 );
        ovalo = new Ellipse2D.Double( x1, y1, ancho, alto );
    }

    /**
     * Construye un óvalo a partir de los datos contenidos en un archivo
     * @param br Es el flujo que sirve para leer el archivo
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato del archivo no es el esperado
     */
    public Ovalo( BufferedReader br ) throws IOException, FormatoInvalidoException
    {
        super( br );
        int ancho = Math.abs( x1 - x2 );
        int alto = Math.abs( y1 - y2 );
        ovalo = new Ellipse2D.Double( x1, y1, ancho, alto );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la cadena de caracteres que va a identificar este tipo de figuras en la persistencia
     * @return Retorna el tipo de la figura
     */
    public String darTipoFigura( )
    {
        return TIPO;
    }

    /**
     * Pinta la figura, incluyendo su texto.
     * @param g Es la superficie donde se pinta el óvalo
     * @param seleccionada Indica si la figura que se va a dibujar es la seleccionada
     */
    public void dibujar( Graphics2D g, boolean seleccionada )
    {
        // Pintar la línea
        dibujarOvalo( g );
        // Pintar el texto
        dibujarTexto( g );
        // Dibujar la marca de seleccionada
        if( seleccionada )
        {
            BasicStroke continuo = new BasicStroke( 5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{ 10f, 0f }, 0 );
            g.setStroke( continuo );
            g.setColor( Color.GREEN );
            g.drawOval( x1 - 3, y1 - 3, 7, 7 );
            g.drawOval( x1 - 3, y2 - 3, 7, 7 );
            g.drawOval( x2 - 3, y2 - 3, 7, 7 );
            g.drawOval( x2 - 3, y1 - 3, 7, 7 );
        }
    }

    /**
     * Pinta el óvalo
     * @param g Es la superficie donde se pinta el óvalo
     */
    private void dibujarOvalo( Graphics2D g )
    {
        g.setPaint( colorFondo );
        g.setStroke( tipoLinea );
        g.fill( ovalo );
        g.setPaint( colorLinea );
        g.draw( ovalo );
    }

    /**
     * Sirve para saber si un punto está dentro de una figura o no.
     * @param x Es la coordenada x del punto
     * @param y Es la coordenada y del punto
     * @return Retorna true si el punto está dentro de la figura. Retorna false en caso contrario.
     */
    public boolean estaDentro( int x, int y )
    {
        return ovalo.contains( x, y );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv:</b> <br>
     * ovalo!=null
     */
    protected void verificarInvariante( )
    {
        super.verificarInvariante( );

        assert ovalo != null : "La representación gráfica del óvalo no debería ser null";
    }
}