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
package uniandes.cupi2.carFactory.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Barra que contiene los men�s de la aplicaci�n.
 */
public class BarraMenu extends JMenuBar implements ActionListener {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constante que representa Guardar.
     */
    private static final String SAVE = "Save";

    /**
     * Constante que representa Guardar.
     */
    private static final String SAVE_AS = "Save As";

    /**
     * Constante que representa Abrir.
     */
    private static final String OPEN = "Open";

    /**
     * Constante que representa Abrir.
     */
    private static final String NEW = "New";

    /**
     * Constante que representa Abrir.
     */
    private static final String EXIT = "Exit";


    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazFabricaDeCarros principal;

    // -----------------------------------------------------------------
    // Attributes de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Men� Archivo.
     */
    private JMenu menuArchivo;

    /**
     * Opci�n Nuevo del men� Archivo.
     */
    private JMenuItem newItem;
    /**
     * Opci�n Abrir del men� Archivo.
     */
    private JMenuItem openItem;

    /**
     * Opci�n Guardar del men� Archivo.
     */
    private JMenuItem saveItem;

    /**
     * Opci�n Guardar Como del men� Archivo.
     */
    private JMenuItem saveAsItem;

    /**
     * Opci�n Salir del men� Archivo.
     */
    private JMenuItem exitItem;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Construye la barra de men�.<br>
     * <b> post: </b> Se inicializ� la barra de men� con la interfaz de fabrica de carros dada
     * por pat�metro.
     *
     * @param pInterfazPrincipal Es una referencia a la clase principal de la interfaz.
     *                           pInterfazPrincipal !=null.
     */
    public BarraMenu(InterfazFabricaDeCarros pInterfazPrincipal) {
        principal = pInterfazPrincipal;

        menuArchivo = new JMenu("File");
        menuArchivo.setMnemonic(KeyEvent.VK_A);
        add(menuArchivo);

        // Add "new" menu item.
        newItem = new JMenuItem("New");
        newItem.setActionCommand(NEW);
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newItem.setMnemonic(KeyEvent.VK_N);
        newItem.addActionListener(this);
        menuArchivo.add(newItem);

        // Add "open" menu item.
        openItem = new JMenuItem("Open");
        openItem.setActionCommand(OPEN);
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        openItem.setMnemonic(KeyEvent.VK_A);
        openItem.addActionListener(this);
        menuArchivo.add(openItem);

        // Add "save" menu item.
        saveItem = new JMenuItem("Save");
        saveItem.setActionCommand(OPEN);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.addActionListener(this);
        menuArchivo.add(saveItem);

        // Add "save as" menu item.
        saveAsItem = new JMenuItem("Save As");
        saveAsItem.setActionCommand(OPEN);
        saveAsItem.addActionListener(this);
        menuArchivo.add(saveAsItem);

        // Separate the "exit" item
        menuArchivo.addSeparator();

        // Add "exit" menu item.
        exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand(EXIT);
        exitItem.addActionListener(this);
        menuArchivo.add(exitItem);

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Ejecuta la acci�n que corresponde a la opci�n del men� que fue seleccionada.
     *
     * @param pEvento Es el evento de seleccionar una opci�n del men�. pEvento !=null.
     */
    public void actionPerformed(ActionEvent pEvento) {
        String command = pEvento.getActionCommand();

        if(NEW.equals(command)){
            principal.restart();
            principal.saveComo();
        }
        else if (OPEN.equals(command)) {
            principal.openFile();
        }
        //TODO Parte10 PuntoI. Agregue las modificaciones para hacer funcionar los items que cre�.
    }

}
