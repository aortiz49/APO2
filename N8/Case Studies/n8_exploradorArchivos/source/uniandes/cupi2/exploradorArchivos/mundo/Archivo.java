/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Archivo.java,v 1.12 2007/04/13 03:55:41 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_exploradorArchivos
 * Autor: Pablo Barvo - 4/07/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.exploradorArchivos.mundo;

import java.io.*;
import java.util.*;

/**
 * Representa un archivo en el disco duro. <br>
 * inv: archivo != null <br>
 * archivo.getAbsolutePath( ).startsWith( Directorio.RAIZ ) <br>
 */
public class Archivo {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Archivo actual
     */
    private File archivo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del archivo
     *
     * @param pRuta Ruta del archivo - pRuta != null
     */
    public Archivo(String pRuta) {
        // Crea el archivo
        archivo = new File(pRuta);
        verificarInvariante();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve el tama�o del archivo
     *
     * @return Tama�o del archivo
     */
    public long darTamanio() {
        return archivo.length();
    }

    /**
     * Indica si es un archivo de texto (el sufijo del nombre es .txt)
     *
     * @return True si es un archivo de texto o false en caso contrario
     */
    public boolean esTexto() {
        String nombre = archivo.getName();
        return nombre.toUpperCase().endsWith(".TXT");
    }

    /**
     * Devuelve el tama�o del archivo formateado como debe ser (bytes, KB, MB, GB o TB)
     *
     * @return Cadena de caracteres con el tama�o del archivo
     */
    public String darTamanioString() {
        long tamanio = darTamanio();
        double size = 0;
        int numVeces = 0;
        while (tamanio > 1000 && numVeces < 4) {
            tamanio = tamanio / 1000;
            size = (int)((tamanio*100))/100.0;
            numVeces++;
        }
        return Double.toString(size) + tipoDatos(numVeces);
    }

    /**
     * Devuelve la ruta completa del archivo
     *
     * @return Ruta completa del archivo
     */
    public String darRuta() {
        return archivo.getAbsolutePath();
    }

    /**
     * Nombre del archivo
     *
     * @return Nombre del archivo
     */
    public String darNombre() {
        return archivo.getName();
    }

    /**
     * Devuelve la fecha de la �ltima modificaci�n del archivo
     *
     * @return Fecha de la �ltima modificaci�n del archivo
     */
    public Date darFechaUltimaModificacion() {
        return new Date(archivo.lastModified());
    }

    /**
     * Escribe en el archivo el contenido especificado
     *
     * @param contenido es el contenido a escribir
     * @throws IOException error al escribir el archivo
     */
    public void escribirArchivo(String contenido) throws IOException {
        // Utiliza un PrintWriter para escribir el contenido
        PrintWriter escritor = new PrintWriter(archivo);
        escritor.println(contenido);
        escritor.close();
    }

    /**
     * Verifica si alguna de las palabras que contiene el archivo comienza con el prefijo
     * especificado
     *
     * @param prefijo es el prefijo para la verificaci�n
     * @return True si contiene el prefijo, False si no
     * @throws IOException error de lectura del archivo
     */
    public boolean contienePrefijo(String prefijo) throws IOException {
        // Comienza suponiendo que no se encuentra
        boolean contiene = false;

        // Abre el archivo utilizando un FileReader
        FileReader reader = new FileReader(archivo);
        // Utiliza un BufferedReader para leer por l�neas
        BufferedReader lector = new BufferedReader(reader);

        // Lee l�nea por l�nea del archivo
        String linea = lector.readLine();
        while (linea != null && !contiene) {
            contiene = lineaContiene(linea, prefijo);
            linea = lector.readLine();
        }

        // Cierra los lectores y devuelve el resultado
        lector.close();
        reader.close();
        return contiene;
    }

    /**
     * Verifica si la l�nea especificada contiene el prefijo que llega como par�metro
     *
     * @param linea   es la l�nea a verificar
     * @param prefijo es el prefijo a buscar
     * @return True si lo contiene, false en caso contrario
     */
    private boolean lineaContiene(String linea, String prefijo) {
        // Suprime de la l�nea todos los caracteres de puntuaci�n, remplaz�ndolos por un car�cter
        // blanco
        linea = limpiarLinea(linea);

        // Parte la l�nea en palabras
        String[] palabras = linea.split(" ");

        // Busca palabra por palabra
        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].toLowerCase().startsWith(prefijo.toLowerCase()))
                return true;
        }
        return false;
    }

    /**
     * Suprime de la l�nea todos los caracteres de puntuaci�n, remplaz�ndolos por un car�cter blanco
     *
     * @param linea es la l�nea de entrada
     * @return L�nea procesada
     */
    private String limpiarLinea(String linea) {
        // Elimina los signos de puntuaci�n y los tabs
        linea = linea.replace('\t', ' ');
        linea = linea.replace('/', ' ');
        linea = linea.replace('.', ' ');
        linea = linea.replace(',', ' ');
        linea = linea.replace(':', ' ');
        linea = linea.replace(';', ' ');
        linea = linea.replace('!', ' ');
        linea = linea.replace('\"', ' ');
        linea = linea.replace('*', ' ');
        linea = linea.replace('(', ' ');
        linea = linea.replace(')', ' ');
        linea = linea.replace('[', ' ');
        linea = linea.replace(']', ' ');
        linea = linea.replace('{', ' ');
        linea = linea.replace('}', ' ');
        linea = linea.replace('\'', ' ');
        linea = linea.replace('\\', ' ');

        // Devuelve la l�nea sin espacios iniciales/finales
        return linea.trim();
    }

    /**
     * Representaci�n del archivo en string
     *
     * @return Representaci�n del archivo
     */
    public String toString() {
        return darNombre() + " - " + darTamanioString();
    }

    /**
     * Devuelve el tipo de datos seg�n el tama�o
     *
     * @param numVeces es el n�mero de veces dividido
     * @return Tipo de datos
     */
    private String tipoDatos(int numVeces) {
        switch (numVeces) {
            case 0:
                return " Bytes";
            case 1:
                return " KB";
            case 2:
                return " MB";
            case 3:
                return " GB";
            case 4:
                return " TB";
            default:
                return " Bytes";
        }
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica que el invariante de la clase se cumpla. Si algo falla, lanza un AssertError. <br>
     * <b>inv: </b> <br>
     * archivo != null <br>
     * archivo.getAbsolutePath( ).startsWith( Directorio.RAIZ )
     */
    private void verificarInvariante() {
        assert archivo != null : "Archivo nulo";
        assert archivo.getAbsolutePath().startsWith(Directorio.RAIZ) :
                "Archivo no pertenece al disco C:";
    }
}
