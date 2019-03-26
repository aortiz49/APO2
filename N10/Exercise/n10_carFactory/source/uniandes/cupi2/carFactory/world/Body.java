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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Clase abstracta que representa una parte del chasis.
 */
public abstract class Body extends Part {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constante que representa el alto del chasis.
     */
    public final static int ALTO = 200;

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Figura que representa el chasis.
     */
    private BufferedImage figuraChasis;

    // -------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------

    /**
     * Crea un chasis con los valores especificados.<br>
     * <b> post: </b> Se inicializ� el chasis con las coordenadas 'x' y 'y', la imagen y el color
     * dados por par�metro.
     *
     * @param pX        Coordinate x del chasis. pX >= 0.
     * @param pY        Coordinate y del chasis. pY >= 0.
     * @param pAncho    Ancho de la imagen. pAncho > 0.
     * @param pCarColor Color del carro. pCarColor != null.
     * @param pImagen   Nombre de la Imagen que representa el chasis incluyendo la extensi�n (p.e.
     *                  delanteraCompacto.png). pImagen!= null && !pImagen.equals("").
     */
    public Body(int pX, int pY, int pAncho, String pImagen, Color pCarColor) {
        super(pX, pY, ALTO, pAncho, pCarColor);
        cargarImagen(pImagen);

    }

    /**
     * Crea un chasis a partir de los datos contenidos en el archivo.<br>
     * <b> post: </b> Se inicializ� el chasis con el lector de texto, el ancho y la imagen dada
     * por par�metro.
     *
     * @param pBr     Stream que sirve para leer el archivo. pBr != null.
     * @param pAncho  Ancho de la imagen. pAncho > 0.
     * @param pImagen Nombre del archivo con la figura. pImagen != null y !pImagen.equals("").
     * @throws Exception Si hay problemas leyendo el archivo.
     * @throws Exception Si el formato del archivo no es el esperado.
     */
    public Body(BufferedReader pBr, int pAncho, String pImagen) throws Exception {
        super(pBr, ALTO, pAncho);
        cargarImagen(pImagen);
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Carga la imagen con el nombre especificado.<br>
     * <b> post: </b> La imagen fue cargada en la figura del chasis.
     *
     * @param pImagen Nombre de la imagen a ser cargada. pImagen !=null && !pImagen.equals("").
     */
    public void cargarImagen(String pImagen) {
        try {
            figuraChasis = ImageIO.read(
                    new File("data" + File.separator + "images" + File.separator + pImagen));
        } catch (IOException e) {
            figuraChasis = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        }
    }

    /**
     * Dibuja el chasis .<br>
     * <b> post: </b> El chasis fue pintado.
     *
     * @param pG Superficie donde se debe paint. pG!= null.
     */
    @Override
    public void paint(Graphics2D pG) {
        pG.drawImage(figuraChasis, x, y, ancho, alto, color, null);

    }
}
