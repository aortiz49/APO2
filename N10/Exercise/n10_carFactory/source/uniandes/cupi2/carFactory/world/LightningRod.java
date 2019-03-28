
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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;


import java.awt.*;
import java.io.BufferedReader;


/**
 * Clase que representa la pintura de una calavera.
 */
public class LightningRod extends Stencil {


    
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
    public final static int ANCHO = 100;

    /**
     * Constante que representa el tipo LightningRodl.
     */
    public final static String TYPE = "LightningRod";

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
    public LightningRod(int pX, int pY, Color pColorStencil) {
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
    public LightningRod(BufferedReader pBr) throws Exception {
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

        int dx = ANCHO/21;
        int dy = ALTO/13;

        int x2[] = {x,x+dx,x+6*dx,x+7*dx,x+11*dx,x+12*dx,x+21*dx,x+11*dx,x+10*dx,x+6*dx,x+5*dx};
        int y2[] = {y+6*dy,y,y+4*dy,y+2*dy,y+6*dy,y+4*dy,y+13*dy,y+8*dy,y+10*dy,y+7*dy,y+9*dy};
        pG.setColor(color);
        pG.fillPolygon (x2, y2, 11);

        pG.setStroke(new BasicStroke(1));
        pG.setColor(Color.black);
        pG.setStroke(new BasicStroke(1));
        pG.drawPolygon(x2,y2,11);




    }
}
