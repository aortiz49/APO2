/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n10_carFactory
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.carFactory.world;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;

/**
 * Clase que representa la llanta con rines econ�micos.
 */
public class LuxuryRims extends Wheel {
    /**
     * Constante que representa el tipo LuxuryRims.
     */
    public final static String TYPE = "LuxuryRims";

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Crea la llanta con los valores especificados.<br>
     * <b> post: </b> Se inicializ� el rin con las coordenadas 'x' y 'y', y el color dados por
     * par�metro.<br>
     * Se inicializ� el tipo con la constante de la clase.
     *
     * @param pX        Coordinate x de la figura. pX >= 0.
     * @param pY        Coordinate y de la figura. pY >= 0.
     * @param pCarColor Color del carro. pCarColor != null.
     */
    public LuxuryRims(int pX, int pY, Color pCarColor) {
        super(pX, pY, pCarColor);
        tipo = TYPE;
    }

    /**
     * Crea la llanta a partir de los datos contenidos en el archivo.<br>
     * <b> post: </b> Se inicializ� el rin con el editor de texto dado por par�metro.<br>
     * Se inicializ� el tipo con la constante de la clase.
     *
     * @param pBr Stream que sirve para leer el archivo. pBr!=null.
     * @throws Exception Si hay problemas leyendo el archivo.
     * @throws Exception Si el formato del archivo no es el esperado.
     */
    public LuxuryRims(BufferedReader pBr) throws Exception {
        super(pBr);
        tipo = TYPE;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Dibuja los rines de la llanta.<br>
     * <b> post: </b> El rin se dibuj�.
     *
     * @param pG Superficie donde se debe paint. pG!=null.
     */
    public void paintRines(Graphics2D pG) {
        pG.setColor(new Color(14, 14, 14));
        pG.fillOval(x + 3 * DIAMETER / 10, y + 3 * DIAMETER / 10, +4 * DIAMETER / 10,
                    +4 * DIAMETER / 10);

        double cos = 0.829;
        double sin = 0.559;
        int d1 = DIAMETER / 5;
        int d2 = d1 / 3;
        int radio = DIAMETER / 2;
        int radio2 = DIAMETER / 2 - d2 - d1;

        pG.setColor(Color.DARK_GRAY);

        BasicStroke stroke = new BasicStroke((float) (DIAMETER / 8.6));
        pG.setStroke(stroke);
        int a = (int) (radio2 * sin);
        int b = (int) (radio2 * cos);

        int puntox1 = x + radio;
        int puntox2 = x + radio + b;
        int puntox3 = x + radio - b;
        int puntoy1 = y + d1 + d2;
        int puntoy2 = y + radio - a;
        int puntoy3 = y + radio + a;
        int puntoy4 = y + ancho - d2 - d1;

        pG.drawLine(puntox1, puntoy1, puntox1, puntoy4);
        pG.drawLine(puntox2, puntoy2, puntox3, puntoy3);
        pG.drawLine(puntox3, puntoy2, puntox2, puntoy3);

        pG.setStroke(stroke);
        pG.setColor(Color.gray);
        pG.drawOval(x + radio - d1 / 5, y + radio - d1 / 5, d1 * 2 / 5, d1 * 2 / 5);

        stroke = new BasicStroke(DIAMETER / 15);
        pG.setStroke(stroke);
        pG.setColor(new Color(25, 25, 25));
        pG.drawOval(x + radio - d1 / 8, y + radio - d1 / 8, d1 / 4, d1 / 4);
        pG.setColor(null);
    }
}
