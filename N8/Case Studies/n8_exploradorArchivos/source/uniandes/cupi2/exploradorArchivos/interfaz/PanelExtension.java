/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.4 2006/08/06 00:52:54 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_exploradorArchivos
 * Autor: Pablo Barvo - 04-jul-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.exploradorArchivos.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import uniandes.cupi2.exploradorArchivos.mundo.Archivo;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Opci�n 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opci�n 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando Opci�n 3
     */
    private static final String OPCION_3 = "OPCION_3";

    /**
     * Comando Opci�n 4
     */
    private static final String OPCION_4 = "OPCION_4";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazExploradorArchivos principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n Opci�n 1
     */
    private JButton btnOpcion1;

    /**
     * Bot�n Opci�n 2
     */
    private JButton btnOpcion2;

    /**
     * Bot�n Opci�n 3
     */
    private JButton btnOpcion3;

    /**
     * Bot�n Opci�n 4
     */
    private JButton btnOpcion4;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     *
     * @param ventana es la ventana principal
     */
    public PanelExtension(InterfazExploradorArchivos ventana) {
        principal = ventana;

        setBorder(new TitledBorder("Opciones"));
        setLayout(new GridLayout(1, 4));

        // Bot�n opci�n 1
        btnOpcion1 = new JButton("Opci�n 1");
        btnOpcion1.setActionCommand(OPCION_1);
        btnOpcion1.addActionListener(this);
        add(btnOpcion1);

        // Bot�n opci�n 2
        btnOpcion2 = new JButton("Opci�n 2");
        btnOpcion2.setActionCommand(OPCION_2);
        btnOpcion2.addActionListener(this);
        add(btnOpcion2);

        // Bot�n opci�n 3
        btnOpcion3 = new JButton("Opci�n 3 - N/A");
        btnOpcion3.setActionCommand(OPCION_3);
        btnOpcion3.addActionListener(this);
        add(btnOpcion3);

        // Bot�n opci�n 4
        btnOpcion4 = new JButton("Opci�n 4 - N/A");
        btnOpcion4.setActionCommand(OPCION_4);
        btnOpcion4.addActionListener(this);
        add(btnOpcion4);
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza la informaci�n de los botones
     *
     * @param archivoSeleccionado es el archivo seleccionado
     */
    public void actualizar(Archivo archivoSeleccionado) {
        if (archivoSeleccionado == null) {
            btnOpcion3.setText("Opci�n 3 - N/A");
            btnOpcion4.setText("Opci�n 4 - N/A");
        }
        else {
            btnOpcion3.setText("Opci�n 3 - " + archivoSeleccionado.darNombre());
            btnOpcion4.setText("Opci�n 4 - " + archivoSeleccionado.darNombre());
        }
    }

    /**
     * Manejo de los eventos de los botones
     *
     * @param e es la acci�n que gener� el evento.
     */
    public void actionPerformed(ActionEvent e) {
        if (OPCION_1.equals(e.getActionCommand())) {
            principal.reqFuncOpcion1();
        }
        else if (OPCION_2.equals(e.getActionCommand())) {
            principal.reqFuncOpcion2();
        }
        else if (OPCION_3.equals(e.getActionCommand())) {
            principal.reqFuncOpcion3();
        }
        else if (OPCION_4.equals(e.getActionCommand())) {
            principal.reqFuncOpcion4();
        }
    }
}

