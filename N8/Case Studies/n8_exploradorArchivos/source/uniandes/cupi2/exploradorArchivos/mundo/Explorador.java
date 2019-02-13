/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Explorador.java,v 1.9 2006/08/06 01:05:55 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_exploradorArchivos
 * Autor: Pablo Barvo - 04-jul-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.exploradorArchivos.mundo;

import java.io.*;
import java.util.*;

/**
 * Representa un explorador de archivos. <br>
 * <b>inv:</b> <br>
 * dirActual != null <br>
 * subdirectorios != null <br>
 * archivos != null <br>
 *
 */
public class Explorador {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Directorio actual del explorador de archivos
     */
    private Directorio dirActual;

    /**
     * Subdirectorios del directorio actual
     */
    private ArrayList<Directorio> subdirectorios;

    /**
     * Archivos en el directorio actual
     */
    private ArrayList<Archivo> archivos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del explorador
     */
    public Explorador() {
        // Define el directorio actual como la raíz del disco C
        dirActual = new Directorio();
        actualizarInformacion();
    }

    /**
     * Constructor del explorador en un directorio dado
     * @param nombreDirectorio es el nombre del directorio - nombreDirectorio != null,
     *                         nombreDirectorio comienza por "C:\"
     */
    public Explorador(String nombreDirectorio) {
        // Define el directorio actual en la ruta definida que llega como parámetro
        dirActual = new Directorio(nombreDirectorio);
        actualizarInformacion();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ruta del directorio actual
     * @return Ruta actual del explorador
     */
    public String darRutaActual() {
        return dirActual.darRuta();
    }

    /**
     * Sube el directorio actual del explorador al directorio padre.<br>
     * Si se encuentra en el directorio raíz, no hace nada
     *
     */
    public void subirDirectorio() {
        dirActual.subirNivel();
        // Actualiza la información de archivos y sub-directorios
        actualizarInformacion();
    }

    /**
     * El directorio especificado se convierte en el directorio actual
     * @param numDir es el número de directorio a navegar
     */
    public void irDirectorio(int numDir) {
        // Verifica que tenga acceso al directorio especificado
        dirActual = (Directorio) subdirectorios.get(numDir);
        // Actualiza la información de archivos y sub-directorios
        actualizarInformacion();
    }

    /**
     * Busca en la lista de archivos, el archivo con el nombre dado
     * @param nombre es el nombre del archivo - nombre!=null
     * @return El archivo con el nombre dado
     */
    public Archivo buscarArchivo(String nombre) {
        for (int i = 0; i < archivos.size(); i++) {
            Archivo arch = (Archivo) archivos.get(i);
            if (arch.darNombre().equals(nombre))
                return arch;
        }
        return null;
    }

    /**
     * Crea el archivo especificado en la ruta especificada
     * @param nombre es el nombre para el archivo
     * @return Archivo creado. Si el archivo ya existe, devuelve el existente
     * @throws IOException si no tiene acceso para crear el archivo
     */
    public Archivo crearArchivo(String nombre) throws IOException {
        Archivo arch = buscarArchivo(nombre);
        if (arch == null) {
            File file = new File(dirActual.darRuta() + File.separator + nombre);
            file.createNewFile();
            actualizarInformacion();
        }
        return buscarArchivo(nombre);
    }

    /**
     * Busca en los archivos de texto del directorio actual los archivos que contienen una
     * palabra que comienza con el prefijo especificado
     * @param prefijo es el prefijo para la búsqueda
     * @return Archivos que contienen el prefijo
     */
    public ArrayList buscarPorPrefijo(String prefijo) {
        ArrayList respuesta = new ArrayList();
        // Recorre todos los archivos del directorio actual
        for (int i = 0; i < archivos.size(); i++) {
            try {
                Archivo arch = (Archivo) archivos.get(i);
                if (arch.esTexto() && arch.contienePrefijo(prefijo))
                    respuesta.add(arch);
            } catch (IOException e) {
                // En caso de error de lectura no se hace nada, simplemente no se incluye
            }
        }
        // Devuelve los archivos encontrados
        return respuesta;
    }

    /**
     * Devuelve los archivos del directorio actual
     * @return Archivos del directorio actual
     */
    public Archivo[] darArchivos() {
        Archivo[] archs = new Archivo[archivos.size()];
        archivos.toArray(archs);
        return archs;
    }

    /**
     * Devuelve los sub-directorios del directorio actual
     * @return Sub-directorios del directorio actual
     */
    public Directorio[] darSubDirectorios() {
        Directorio[] subdirs = new Directorio[subdirectorios.size()];
        subdirectorios.toArray(subdirs);
        return subdirs;
    }

    /**
     * Actualiza la información de la ruta actual
     */
    private void actualizarInformacion() {
        subdirectorios = new ArrayList();
        archivos = new ArrayList();

        // Saca la lista de archivos de directorio
        File directorio = new File(dirActual.darRuta());
        File[] elementos = directorio.listFiles();
        if (elementos != null) {
            for (int i = 0; i < elementos.length; i++) {
                // Verifica si es directorio o si es archivo
                if (elementos[i].isDirectory()) {
                    subdirectorios.add(new Directorio(elementos[i].getAbsolutePath()));
                }
                else if (elementos[i].isFile()) {
                    archivos.add(new Archivo(elementos[i].getAbsolutePath()));
                }
            }
        }
    }


    public void reporteDirActual(String pNombre) {
        try {
            PrintWriter pw = new PrintWriter(new File(pNombre));
            pw.println("#Subdirectories:");

            for (Directorio d: subdirectorios) {
                String subDirName = d.darNombre();
                pw.println(subDirName);
            }

            pw.println("#Files:");
            for (Archivo a: archivos) {
                String fileName = a.darNombre();
                pw.println(fileName);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }






    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1() {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2() {
        return "Respuesta 2";
    }

    /**
     * Método para la extensión3
     * @param archivo Archivo para la operación
     * @return respuesta3
     */
    public String metodo3(Archivo archivo) {
        return "Respuesta 3 - " + archivo.darNombre();
    }

    /**
     * Método para la extensión4
     * @param archivo Archivo para la operación
     * @return respuesta4
     */
    public String metodo4(Archivo archivo) {
        return "Respuesta 4 - " + archivo.darNombre();
    }
}