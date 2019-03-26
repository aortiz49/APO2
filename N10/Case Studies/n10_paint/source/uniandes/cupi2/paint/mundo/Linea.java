/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Linea.java,v 1.9 2007/04/13 04:29:55 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
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
 * Esta clase representa a una línea que hace parte de una composición. <br>
 * <b>inv:</b> <br>
 * linea!=null
 */
public class Linea extends Figura {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa el tipo de la figura
     */
    public final static String TIPO = "LINEA";

    /**
     * Indica la distancia mínima para que se considere que un clic fue realizado dentro de la línea
     */
    protected final double distanciaMinima = 5.0;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Representación gráfica de la línea
     */
    private Line2D linea;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una línea entre un par de puntos y con los parámetros indicados
     * @param x1f La coordenada x del primer punto - x1f>=0
     * @param y1f La coordenada y del primer punto - y1f>=0
     * @param x2f La coordenada x del segundo punto - x2f>=0
     * @param y2f La coordenada y del segundo punto - y2f>=0
     * @param colorLineaF El color de la línea de la figura - colorLineaF!=null
     * @param tipoLineaF El tipo de línea usado para pintar la figura - tipoLineaF!=null
     */
    public Linea(int x1f, int y1f, int x2f, int y2f, Color colorLineaF, BasicStroke tipoLineaF) {
        super(x1f, y1f, x2f, y2f, colorLineaF, tipoLineaF);
        linea = new Line2D.Double(x1, y1, x2, y2);
    }
    /**
     * Construye una línea a partir de los datos contenidos en un archivo
     * @param br Es el flujo que sirve para leer el archivo
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato del archivo no es
     * el esperado
     */
    public Linea(BufferedReader br) throws IOException, FormatoInvalidoException {
        super(br);
        linea = new Line2D.Double(x1, y1, x2, y2);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la cadena de caracteres que va a identificar este tipo de figuras en la persistencia
     * @return Retorna la cadena con el tipo de la figura
     */
    public String darTipoFigura() {
        return TIPO;
    }

    /**
     * Pinta la figura, incluyendo su texto.
     * @param g Es la superficie donde se pinta la figura
     * @param seleccionada Indica si la figura que se va a dibujar es la seleccionada
     */
    public void dibujar(Graphics2D g, boolean seleccionada) {
        // Dibujar la línea
        dibujarLinea(g);
        // Dibujar el texto
        dibujarTexto(g);
        // Dibujar la marca de seleccionada
        if (seleccionada) {
            BasicStroke continuo =
                    new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1,
                                    new float[]{10f, 0f}, 0);
            g.setStroke(continuo);
            g.setColor(Color.GREEN);
            g.drawOval(x1 - 3, y1 - 3, 7, 7);
            g.drawOval(x2 - 3, y2 - 3, 7, 7);
        }
    }

    /**
     * Dibuja la línea
     * @param g Es la superficie sobre la cual se debe pintar
     */
    private void dibujarLinea(Graphics2D g) {
        g.setColor(colorLinea);
        g.setStroke(tipoLinea);
        g.draw(linea);
    }

    /**
     * Sirve para saber si un punto está suficientemente cercano a la línea como para poder decir
     * que está en la línea.
     * @param x Es la coordenada x del punto
     * @param y Es la coordenada y del punto
     * @return Retorna true si el punto está suficientemente cercano a la línea. <br>
     *         Retorna false en caso contrario.
     */
    public boolean estaDentro(int x, int y) {
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);
        return minX <= x && x <= maxX && minY <= y && y <= maxY
                && linea.ptLineDist(x, y) < distanciaMinima;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv:</b> <br>
     * linea!=null
     */
    protected void verificarInvariante() {
        super.verificarInvariante();

        assert linea != null : "La representación gráfica de la línea no debería ser null";
    }
}