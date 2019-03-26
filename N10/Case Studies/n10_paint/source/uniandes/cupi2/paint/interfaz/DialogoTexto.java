/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoTexto.java,v 1.5 2006/10/02 17:14:35 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_paint
 * Autor: Mario Sánchez - 27/09/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.paint.interfaz;

import java.awt.*;
import java.util.*;

import javax.swing.*;

/**
 * Es el diálogo para modificar el texto y la fuente de una figura
 */
public class DialogoTexto extends JDialog {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazPaint principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * El panel donde se modifica el texto y la fuente usada en una figura
     */
    private PanelModificarTexto panelModificar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo para modificar el texto y la fuente de una figura
     * @param ip Es una referencia a la clase principal de la interfaz
     * @param texto Es el texto que se escribirá sobre la figura
     * @param fuente Es la fuente que se usará para el texto de la figura
     */
    public DialogoTexto(InterfazPaint ip, String texto, Font fuente) {
        super(ip, "Texto", true);

        principal = ip;

        // Averiguar cuales son las fuentes disponibles en el sistema
        String[] nombresFuentes =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        ArrayList listaFuentes = new ArrayList();
        for (int i = 0; i < nombresFuentes.length; i++) {
            listaFuentes.add(nombresFuentes[i]);
        }

        // Construir el panel y mostrar el diálogo
        panelModificar = new PanelModificarTexto(this, listaFuentes);
        getContentPane().add(panelModificar);
        panelModificar.cambiarTexto(texto, fuente);

        setSize(350, 180);
        centrarVentana();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el texto mostrado
     * @param texto Nuevo texto
     * @param fuente Nueva fuente
     */
    public void cambiarTexto(String texto, Font fuente) {
        principal.cambiarTexto(texto, fuente);
        setVisible(false);
        dispose();
    }

    /**
     * Centra la ventana en la pantalla
     */
    private void centrarVentana() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) (pantalla.getWidth() / 2 - getWidth() / 2),
                    (int) (pantalla.getHeight() / 2 - getHeight() / 2));
    }

}