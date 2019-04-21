/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiTaxonomia
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiTaxonomia.world;

import java.io.Serializable;

/**
 * Clase que representa un ser vivo del �rbol taxon�mico.
 */
public class SerVivo implements Serializable {
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Constante de serializaci�n de la clase.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Nombre com�n del ser vivo.
     */
    private String nombreComun;

    /**
     * Nombre cient�fico del ser vivo.
     */
    private String nombreCientifico;

    /**
     * Caracter�sticas del ser vivo.
     */
    private String caracteristicas;

    /**
     * Ruta con la imagen del ser vivo.
     */
    private String rutaImagen;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Construye un nuevo ser vivo con la informaci�n dada por par�metro. <br>
     * <b> post: </b> El ser vivo se inicializ� con los valores de nombre com�n, nombre
     * cient�fico, caracter�sticas e imagen dados por par�metro.
     * @param pNombreComun Nombre com�n del ser vivo. pNombreComun != null y pNombreComun != "".
     * @param pNombreCientifico Nombre cient�fico del ser vivo. pNombreCientifico != null y
     *                          pNombreCientifico != "".
     * @param pCaracteristicas Caracter�sticas del ser vivo. pCaracteristicas != null y
     *                         pCaracteristicas != "".
     * @param pImagen Ruta con la imagen del ser vivo. pImagen != null y pImagen != "".
     */
    public SerVivo(String pNombreComun, String pNombreCientifico, String pCaracteristicas,
                   String pImagen) {
        nombreComun = pNombreComun;
        nombreCientifico = pNombreCientifico;
        caracteristicas = pCaracteristicas;
        rutaImagen = pImagen;
    }

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Retorna las caracter�sticas del ser vivo.
     * @return Caracter�sticas del ser vivo.
     */
    public String darCaracteristicas() {
        return caracteristicas;
    }

    /**
     * Retorna el nombre com�n del ser vivo.
     * @return Nombre com�n del ser vivo.
     */
    public String darNombreComun() {
        return nombreComun;
    }

    /**
     * Retorna el nombre cient�fico del ser vivo.
     * @return Nombre cient�fico del ser vivo.
     */
    public String darNombreCientifico() {
        return nombreCientifico;
    }


    /**
     * Retorna la ruta de la imagen del ser vivo.
     * @return Ruta de la imagen del ser vivo.
     */
    public String darRutaImagen() {
        return rutaImagen;
    }

    /**
     * Retorna una cadena con el nombre cient�fico y el nombre com�n del ser vivo.
     * @return La representaci�n del ser vivo en String: <nombreCientifico> - <nombreComun>.
     */
    public String toString() {
        return nombreCientifico + " - " + nombreComun;
    }
}