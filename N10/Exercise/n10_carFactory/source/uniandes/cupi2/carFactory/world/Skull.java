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
 * Clase que representa la pintura de una calavera.
 */
public class Skull extends Stencil {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constante que representa el alto de la imagen.
     */
    public final static int ALTO = 50;

    /**
     * Constante que representa el ancho de la imagen.
     */
    public final static int ANCHO = 25;

    /**
     * Constante que representa el tipo Skull.
     */
    public final static String TYPE = "Skull";

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Crea el est�ncil con los valores especificados.<br>
     * <b> post: </b> Se inicializ� la calavera con las coordenadas 'x' y 'y', y el color dados
     * por par�metro.<br>
     * Se inicializ� el tipo con la constante de la clase.
     *
     * @param pX            Coordinate x de la figura. pX >= 0.
     * @param pY            Coordinate y de la figura. pY >= 0.
     * @param pColorStencil Color del est�ncil, pColorStencil. != null.
     */
    public Skull(int pX, int pY, Color pColorStencil) {
        super(pX, pY, ALTO, ANCHO, pColorStencil);
        tipo = TYPE;
    }

    /**
     * Crea el est�ncil a partir de los datos contenidos en el archivo.<br>
     * <b> post: </b> Se inicializ� la calavera con el lector de texto dado por par�metro.<br>
     * Se inicializ� el tipo con la constante de la clase.
     *
     * @param pBr Stream que sirve para leer el archivo. pBr!=null.
     * @throws Exception Si hay problemas leyendo el archivo.
     * @throws Exception Si el formato del archivo no es el esperado.
     */
    public Skull(BufferedReader pBr) throws Exception {
        super(pBr, ALTO, ANCHO);
        tipo = TYPE;
    }
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Dibuja el est�ncil de calavera.<br>
     * <b>post: </b> Se dibuj� el est�ncil en el lienzo.
     *
     * @param pG La superficie donde se debe paint g!=null.
     */
    public void paint(Graphics2D pG) {
        pG.setStroke(new BasicStroke(1));

        // Pintar el cr�neo
        pG.setColor(color);
        pG.setStroke(new BasicStroke(1));

        pG.fillOval(x, y, ANCHO, ALTO / 2);

        pG.fillOval(x + ANCHO / 7, y + ALTO / 4, ANCHO * 5 / 7, ANCHO * 5 / 7);

        // Pintar los ojos
        pG.setColor(Color.black);
        pG.fillOval(x + ANCHO / 5, y + ALTO / 4, ANCHO / 5, ANCHO / 5);
        pG.fillOval(x + ANCHO * 3 / 5, y + ALTO / 4, ANCHO / 5, ANCHO / 5);

        // Pintar las fosas
        int tamFosas = ANCHO / 18;
        pG.fillOval(x + ANCHO / 2 - tamFosas * 3 / 2, y + ALTO * 3 / 8 - tamFosas, tamFosas,
                    tamFosas * 2);
        pG.fillOval(x + ANCHO / 2 + tamFosas / 2, y + ALTO * 3 / 8 - tamFosas, tamFosas,
                    tamFosas * 2);

        // Pintar los dientes
        int tamDiente = ANCHO / 12;
        pG.setColor(Color.white);
        for (int i = 1; i <= 5; i++) {
            pG.fillRect(x + ANCHO / 5 + tamDiente * i, y + ALTO / 2 - tamDiente / 2, tamDiente,
                        tamDiente);
            pG.fillRect(x + ANCHO / 5 + tamDiente * i, y + ALTO / 2 - tamDiente * 3 / 2, tamDiente,
                        tamDiente);

        }
        pG.setColor(Color.black);
        for (int i = 1; i <= 5; i++) {
            pG.drawRect(x + ANCHO / 5 + tamDiente * i, y + ALTO / 2 - tamDiente / 2, tamDiente,
                        tamDiente);
            pG.drawRect(x + ANCHO / 5 + tamDiente * i, y + ALTO / 2 - tamDiente * 3 / 2, tamDiente,
                        tamDiente);

        }

        // Pintar los huesos
        pG.setColor(color);
        int anchoHueso = ANCHO / 6;
        int[] pxHueso =
                {x - anchoHueso, x + ANCHO + anchoHueso, x + ANCHO + anchoHueso, x - anchoHueso};
        int py1 = y + ALTO * 5 / 9;
        int py2 = y + ALTO * 4 / 5;
        int[] pyHueso1 = {py1, py2, py2 + anchoHueso, py1 + anchoHueso};
        int[] pyHueso2 = {py2 + anchoHueso, py1 + anchoHueso, py1, py2};

        pG.fillPolygon(pxHueso, pyHueso1, 4);
        pG.fillPolygon(pxHueso, pyHueso2, 4);

        pG.fillOval(x - anchoHueso * 3 / 2, py1 - anchoHueso / 2, anchoHueso * 2, anchoHueso * 2);
        pG.fillOval(x - anchoHueso * 3 / 2, py2 - anchoHueso / 2, anchoHueso * 2, anchoHueso * 2);
        pG.fillOval(x + ANCHO - anchoHueso / 2, py1 - anchoHueso / 2, anchoHueso * 2,
                    anchoHueso * 2);
        pG.fillOval(x + ANCHO - anchoHueso / 2, py2 - anchoHueso / 2, anchoHueso * 2,
                    anchoHueso * 2);





    }

}
