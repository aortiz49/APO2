/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Directorio.java,v 1.7 2007/04/13 03:55:41 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_exploradorArchivos
 * Autor: Pablo Barvo - 4/07/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.exploradorArchivos.mundo;

import java.io.*;

/**
 * Representa un directorio en el disco <br>
 * <b>inv:</b><br>
 * ruta != null <br>
 * la ruta comienza por la RAIZ
 */
public class Directorio {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Raíz de los archivos
     */
    public static final String RAIZ = "/Users/renegade" + File.separator;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ruta completa del directorio
     */
    private String ruta;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de un directorio en la raíz del disco duro
     */
    public Directorio() {
        ruta = RAIZ;
        verificarInvariante();
    }

    /**
     * Constructor del directorio dada la ruta del mismo
     * @param pRuta es la ruta del directorio
     */
    public Directorio(String pRuta) {
        ruta = pRuta;
        verificarInvariante();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ruta completa del directorio
     * @return Ruta completa del directorio
     */
    public String darRuta() {
        return ruta;
    }

    /**
     * Devuelve el nombre del directorio
     * @return Nombre del directorio sin la ruta del padre
     */
    public String darNombre() {
        if (esRaiz())
            return "";
        else {
            // Calcula la última posición en la que aparece el separador de directorios
            int posicion = ruta.lastIndexOf(File.separator);
            // Devuelve solo el nombre del directorio
            return ruta.substring(posicion + 1);
        }
    }

    /**
     * Sube un nivel en la jerarquía de directorio, si no es la raíz
     */
    public void subirNivel() {
        if (!esRaiz()) {
            int posicion = ruta.lastIndexOf(File.separator);
            ruta = ruta.substring(0, posicion);
            if (ruta.indexOf(File.separator) == -1) {
                ruta += File.separator;
            }
            verificarInvariante();
        }
    }

    /**
     * Indica si el directorio corresponde a la raíz del sistema de archivos
     * @return True si el directorio es la raíz del sistema de archivos o false en caso contrario
     */
    public boolean esRaiz() {
        return ruta.equals(RAIZ);
    }

    /**
     * Devuelve el nombre del directorio
     * @return Nombre del directorio
     */
    public String toString() {
        return darNombre();
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica que el invariante de la clase se cumpla. Si algo falla, lanza un AssertError. <br>
     * <b>inv: </b> <br>
     * ruta != null <br>
     * la ruta comienza por la RAIZ
     */
    private void verificarInvariante() {
        assert ruta != null : "Ruta nula";
        assert ruta.startsWith(RAIZ) : "Ruta inválida ";
    }
}
