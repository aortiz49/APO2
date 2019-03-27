/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: carFactory
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.carFactory.world;

import java.awt.*;
import java.io.BufferedReader;

/**
 * Clase que representa la llanta con rines de gama media.
 */
public class MediumRims extends Wheel {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    /**
     * Constante que representa el tipo MediumRims.
     */
    public final static String TYPE = "MediumRims";

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
    public MediumRims(int pX, int pY, Color pCarColor) {
        super(pX, pY, pCarColor);
        tipo = TYPE;
    }

    /**
     * Crea la llanta a partir de los datos contenidos en el archivo.<br>
     * <b> post: </b> Se inicializ� el rin con el lector de texto dado por par�metro. <br>
     * Se inicializ� el tipo con la constante de la clase.
     *
     * @param pBr Stream que sirve para leer el archivo. pBr!=null.
     * @throws Exception Si hay problemas leyendo el archivo.
     * @throws Exception Si el formato del archivo no es el esperado.
     */
    public MediumRims(BufferedReader pBr) throws Exception {
        super(pBr);
        tipo = TYPE;
    }


    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Dibuja los rines de la llanta.
     *
     * @param pG Superficie donde se debe paint pG!=null.
     */

        public void pintarRines(Graphics2D pG) {


            double cos = 0.829;
            double sin = 0.559;
            int radius = DIAMETER / 2;
            int diameter1 = 2 * DIAMETER / 5;
            int diameter2 = DIAMETER / 5;
            int diameter3 = DIAMETER / 10;
            int delta0 = DIAMETER / 5;
            int delta1 = radius - diameter1 / 2;
            int delta2 = radius - diameter2 / 2;
            int delta3 = radius - diameter3 / 2;
            int d2 = (int) (diameter1 * cos / 2);
            int d1 = (int) (diameter1 * sin / 2);

            // Points
            int x1 = x + radius - d2; // Represents the x value in QII and QIII
            int y1 = y + radius - d1; // Represents the y value in QI and QII
            int x2 = x + radius + d2; // Represents the x value in QI and QIV
            int y2 = y + radius + d1; // Represents the y value in QIII and QIV
            int vertX = x+ radius;
            int topY = y + radius - diameter1/2;
            int bottomY = y + radius+diameter1/2;

            // Paints circle with diameter1
            pG.setColor(new Color(14, 14, 14));
            pG.fillOval(x + delta1, y + delta1, diameter1, diameter1);


            pG.setColor(Color.DARK_GRAY);

            BasicStroke stroke = new BasicStroke(DIAMETER / 15);
            pG.setStroke(stroke);
            pG.drawLine(x1, y1, x2, y2);
            pG.drawLine(x1,y2,x2,y1);
            pG.drawLine(vertX, topY, vertX, bottomY);

            // Paint circle with diamater 2
            pG.setColor(Color.DARK_GRAY);
            pG.fillOval(x + delta2, y + delta2, diameter2, diameter2);


            // Draw center donut shape
            pG.setColor(Color.GRAY);
            BasicStroke stroke1 = new BasicStroke(DIAMETER / 50);
            pG.setStroke(stroke1);
            pG.drawOval(x + delta3, y + delta3, diameter3, diameter3);


        }

}
