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
 * Clase que representa la parte trasera del chasis de un veh�culo sed�n.
 */
public class SedanTrunk extends Body {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    /**
     * Constante que representa el ancho de la imagen.
     */
    public final static int ANCHO = 302;

    /**
     * Constante que representa la parte trasera de un sed�n.
     */
    public final static String IMAGE = "SedanTrunk.gif";

    /**
     * Constante que representa el tipo Trasera Sedan.
     */
    public final static String TYPE = "SedanTrunk";

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Crea la parte trasera de un sed�n con los valores especificados. <br>
     * <b> post: </b> Se inicializ� el objeto con las coordenadas 'x' y 'y', y el color dados por
     * par�metro.<br>
     * Se inicializ� el tipo con la constante de la clase.
     * @param pX Coordinate x del chasis. pX >= 0.
     * @param pY Coordinate y del chasis. pY >= 0.
     * @param pCarColor Color del carro. pCarColor != null.
     */
    public SedanTrunk(int pX, int pY, Color pCarColor) {
        super(pX, pY, ANCHO, IMAGE, pCarColor);
        tipo = TYPE;

    }

    /**
     * Crea la parte trasera de un sed�n a partir de los datos contenidos en el archivo.<br>
     * <b> post: </b> Se inicializ� el objeto el lector dado por par�metro.<br>
     * Se inicializ� el tipo con la constante de la clase.
     * @param pBr Stream que sirve para leer el archivo. pBr != null.
     * @throws Exception Si hay problemas leyendo el archivo.
     * @throws Exception Si el formato del archivo no es el esperado.
     */
    public SedanTrunk(BufferedReader pBr) throws Exception {
        super(pBr, ANCHO, IMAGE);
        tipo = TYPE;
    }

}
