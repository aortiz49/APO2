/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazExploradorArchivos.java,v 1.10 2006/08/06 00:52:54 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_exploradorArchivos
 * Autor: Pablo Barvo - 04-jul-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.exploradorArchivos.interfaz;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import uniandes.cupi2.exploradorArchivos.mundo.*;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazExploradorArchivos extends JFrame {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Explorador explorador;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la imagen de t�tulo
     */
    private PanelImagen panelImagen;

    /**
     * Panel con los archivos
     */
    private PanelArchivos panelArchivos;

    /**
     * Panel con los sub-directorios
     */
    private PanelDirectorios panelDirectorios;

    /**
     * Panel con el directorio actual
     */
    private PanelDirectorioActual panelDirectorioActual;

    /**
     * Panel para la b�squeda de documentos
     */
    private PanelBusqueda panelBusqueda;

    /**
     * Panel para la creaci�n de archivos
     */
    private PanelNuevoArchivo panelNuevoArchivo;

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la interfaz
     */
    public InterfazExploradorArchivos() {
        // Crea la clase principal
        explorador = new Explorador();

        // Construye la forma
        setLayout(new BorderLayout());
        setSize(800, 650);
        setTitle("miniExplorer - Explorador de Archivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel Imagen
        panelImagen = new PanelImagen();
        add(panelImagen, BorderLayout.NORTH);

        // Panel Izquierdo
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BorderLayout());
        add(panelIzquierdo, BorderLayout.WEST);
        panelDirectorios = new PanelDirectorios(this);
        panelIzquierdo.add(panelDirectorios, BorderLayout.SOUTH);
        panelArchivos = new PanelArchivos(this);
        panelIzquierdo.add(panelArchivos, BorderLayout.CENTER);

        // Panel Central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);
        panelDirectorioActual = new PanelDirectorioActual(this);
        panelCentral.add(panelDirectorioActual, BorderLayout.NORTH);
        panelBusqueda = new PanelBusqueda(this);
        panelCentral.add(panelBusqueda, BorderLayout.CENTER);
        panelNuevoArchivo = new PanelNuevoArchivo(this);
        panelCentral.add(panelNuevoArchivo, BorderLayout.EAST);

        // Panel Extensiones
        panelExtension = new PanelExtension(this);
        add(panelExtension, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setResizable(false);

        // Actualiza la informaci�n
        refrescar();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Navega a un subdirectorio especificado por su �ndice
     * @param numDir es el �ndice del subdirectorio destino
     */
    public void navegar(int numDir) {
        explorador.irDirectorio(numDir);
        // Actualiza la informaci�n
        refrescar();
    }

    /**
     * Sube un nivel el directorio actual
     */
    public void subir() {
        explorador.subirDirectorio();
        // Actualiza la informaci�n
        refrescar();
    }

    /**
     * Realiza una b�squeda de archivos por el criterio especificado
     * @param prefijo es el prefijo para la b�squeda
     */
    public void buscar(String prefijo) {
        ArrayList resultado = explorador.buscarPorPrefijo(prefijo);
        panelBusqueda.refrescar(resultado);
    }

    /**
     * Crea el archivo especificado con el texto especificado
     * @param nombre es el nombre del archivo
     * @param texto es el texto para el archivo
     */
    public void crearArchivo(String nombre, String texto) {
        try {
            Archivo archivo = explorador.crearArchivo(nombre);
            archivo.escribirArchivo(texto);
            panelNuevoArchivo.limpiar();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Actualiza la informaci�n
        panelArchivos.refrescar(explorador.darArchivos());
    }

    /**
     * Muestra la informaci�n del archivo especificado
     * @param archivo es el archivo a visualizar
     */
    public void verInfoArchivo(Archivo archivo) {
        //
        // Actualiza la informaci�n en el panel de botones
        panelExtension.actualizar(archivo);
        //
        // Muestra el dialogo si el archivo no es null
        if (archivo != null) {
            DialogoInfoArchivo dialogo = new DialogoInfoArchivo(this, archivo);
            dialogo.setVisible(true);
        }

    }

    /**
     * Actualiza la informaci�n en la interfaz
     */
    public void refrescar() {
        panelDirectorioActual.refrescar(explorador.darRutaActual());
        panelDirectorios.refrescar(explorador.darSubDirectorios());
        panelArchivos.refrescar(explorador.darArchivos());
        panelBusqueda.limpiar();
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1() {
        String resultado = explorador.metodo1();
        JOptionPane
                .showMessageDialog(this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2() {
        String resultado = explorador.metodo2();
        JOptionPane
                .showMessageDialog(this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * M�todo para la extensi�n 3
     */
    public void reqFuncOpcion3() {
        Archivo archivo = panelArchivos.darArchivoSeleccionado();
        if (archivo == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un archivo!", "Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
        else {
            String resultado = explorador.metodo3(archivo);
            JOptionPane.showMessageDialog(this, resultado, "Respuesta",
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * M�todo para la extensi�n 4
     */
    public void reqFuncOpcion4() {
        explorador.reporteDirActual("/Users/renegade/test1.txt");
        //  JOptionPane.showMessageDialog( this, resultado, "Respuesta",
        //                JOptionPane.INFORMATION_MESSAGE );

    }

    // -----------------------------------------------------------------
    // Programa principal
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando la ventana de la interfaz
     * @param args son los argumentos de la aplicaci�n. No se requiere ninguno.
     */
    public static void main(String[] args) {
        InterfazExploradorArchivos interfaz = new InterfazExploradorArchivos();
        interfaz.setVisible(true);
    }

}