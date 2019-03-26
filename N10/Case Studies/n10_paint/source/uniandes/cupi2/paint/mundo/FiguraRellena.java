/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: FiguraRellena.java,v 1.4 2006/10/05 22:03:38 jvillalo2 Exp $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_paint
 * Autor: Jorge Villalobos - 26/09/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.paint.mundo;

import java.awt.*;
import java.io.*;

/**
 * Esta es una clase abstracta que representa una Figura rellena (que puede ser coloreada por dentro) <b>inv:</b> <br>
 * colorFondo!=null
 */
public abstract class FiguraRellena extends Figura
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El color del fondo de la figura
     */
    protected Color colorFondo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la figura rellena
     * @param x1f La coordenada x del primer punto - x1f>=0
     * @param y1f La coordenada y del primer punto - y1f>=0
     * @param x2f La coordenada x del segundo punto - x2f>=0
     * @param y2f La coordenada y del segundo punto - y2f>=0
     * @param colorLineaF El color de la línea de la figura - colorLineaF != null
     * @param colorFondoF El color del fondo de la figura - colorFondoF != null
     * @param tipoLineaF El tipo de línea usado para pintar la figura - tipoLineaF != null
     */
    public FiguraRellena( int x1f, int y1f, int x2f, int y2f, Color colorLineaF, Color colorFondoF, BasicStroke tipoLineaF )
    {
        super( x1f, y1f, x2f, y2f, colorLineaF, tipoLineaF );
        colorFondo = colorFondoF;
    }

    /**
     * Construye una figura rellena a partir de los datos contenidos en un archivo
     * @param br Es el flujo que sirve para leer el archivo
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato del archivo no es el esperado
     */
    public FiguraRellena( BufferedReader br ) throws IOException, FormatoInvalidoException
    {
        super( br );
        String lineaFondo = br.readLine( );

        String[] strValoresFondo = lineaFondo.split( ";" );
        if( strValoresFondo.length != 3 )
            throw new FormatoInvalidoException( lineaFondo );

        try
        {
            int r2 = Integer.parseInt( strValoresFondo[ 0 ] );
            int g2 = Integer.parseInt( strValoresFondo[ 1 ] );
            int b2 = Integer.parseInt( strValoresFondo[ 2 ] );
            colorFondo = new Color( r2, g2, b2 );
        }
        catch( NumberFormatException nfe )
        {
            throw new FormatoInvalidoException( lineaFondo );
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Guarda la figura rellena en un archivo que recibe como parámetro
     * @param out Es el flujo donde se va a guardar la figura rellena
     */
    public void guardar( PrintWriter out )
    {
        super.guardar( out );
        out.println( colorFondo.getRed( ) + ";" + colorFondo.getGreen( ) + ";" + colorFondo.getBlue( ) );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv:</b> <br>
     * colorFondo!=null
     */
    protected void verificarInvariante( )
    {
        super.verificarInvariante( );

        assert colorFondo != null : "La figura rellena debería tener definido el color de fondo";
    }
}
