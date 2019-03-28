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
import java.io.BufferedReader;

/**
 * Clase que representa la parte delantera del chasis de una camioneta.
 */
public class TruckHood extends Body {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    /**
     * Constante que representa el ancho de la imagen.
     */
    public final static int ANCHO = 320;

    /**
     * Constante que representa la imagen de la delantera de una camioneta.
     */
    public final static String IMAGE = "TruckHood.gif";

    /**
     * Constante que representa el tipo CompactHood.
     */
    public final static String TYPE = "TruckHood";

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Crea una delantera de camioneta con los valores especificados.<br>
     * <b> post: </b> Se inicializ� el objeto con las coordenadas 'x' y 'y', y el color dados por
     * par�metro.<br>
     * Se inicializ� el tipo con la constante de la clase.
     * @param pX Coordinate x del chasis. pX >= 0.
     * @param pY Coordinate y del chasis. pY >= 0.
     * @param pCarColor Color del carro. pCarColor != null.
     */
    public TruckHood(int pX, int pY, Color pCarColor) {
        super(pX, pY, ANCHO, IMAGE, pCarColor);
        tipo = TYPE;
    }

    /**
     * Crea una delantera de camioneta a partir de los datos contenidos en el archivo.<br>
     * <b> post: </b> Se inicializ� el objeto con el lector de texto dado por par�metro.<br>
     * Se inicializ� el tipo con la constante de la clase.
     * @param pBr Stream que sirve para leer el archivo. pBr != null.
     * @throws Exception Si hay problemas leyendo el archivo.
     * @throws Exception Si el formato del archivo no es el esperado.
     */
    public TruckHood(BufferedReader pBr) throws Exception {
        super(pBr, ANCHO, IMAGE);
        tipo = TYPE;
    }

}
