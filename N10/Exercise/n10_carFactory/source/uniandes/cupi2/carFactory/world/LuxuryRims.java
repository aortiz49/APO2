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

import java.awt.*;
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

    public void pintarRines(Graphics2D pG) {

        double yConst = 0.829;
        double xConst = 0.559;


        int diameter1 = 3 * DIAMETER / 5;
        int radius = DIAMETER / 2;
        int diameter2 = 4;
        int delta0 = DIAMETER / 5;
        int delta2 = radius - diameter2 / 2;
        int d1 = (int) (diameter1 * yConst / 2);
        int d2 = (int) (diameter1 * xConst / 2);
        int stroke = DIAMETER / 15;


        // Points
        int x1 = x + radius - d2; // Represents the x value in QII and QIII
        int y1 = y + radius - d1; // Represents the y value in QI and QII
        int x2 = x + radius + d2; // Represents the x value in QI and QIV
        int y2 = y + radius + d1; // Represents the y value in QIII and QIV
        int horizY = y + radius;
        int leftX = x + delta0;
        int rightX = x + delta0 + diameter1;

        // Set stroke conditions
        pG.setColor(new Color(220, 181, 22));
        BasicStroke wheelStroke = new BasicStroke(stroke);
        pG.setStroke(wheelStroke);

        // Paint inner yellow border
        pG.drawOval(x + delta0, y + delta0, diameter1, diameter1);

        // Paint the rim bars
        pG.setStroke(wheelStroke);
        pG.drawLine(x1, y1, x2, y2);
        pG.drawLine(x1, y2, x2, y1);
        pG.drawLine(leftX, horizY, rightX, horizY);


        // Paint inner black circle
        pG.setColor(Color.BLACK);
        pG.drawOval(x + delta2, y + delta2, diameter2, diameter2);
    }

}

