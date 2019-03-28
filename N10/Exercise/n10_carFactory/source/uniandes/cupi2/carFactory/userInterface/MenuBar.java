/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n10_carFactory
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.carFactory.userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Bar containing the menus of the application.
 */
public class MenuBar extends JMenuBar implements ActionListener {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant that represents the Save option.
     */
    private static final String SAVE = "Save";

    /**
     * Constant that represents the Save As option.
     */
    private static final String SAVE_AS = "Save As";

    /**
     * Constant that represents the Open option.
     */
    private static final String OPEN = "Open";

    /**
     * Constant that represents the New option.
     */
    private static final String NEW = "New";

    /**
     * Constant that represents the Exit option.
     */
    private static final String EXIT = "Exit";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal window of the application.
     */
    private CarFactoryInterface principal;

    // -----------------------------------------------------------------
    // Interface attributes
    // -----------------------------------------------------------------

    /**
     * File menu.
     */
    private JMenu fileMenu;

    /**
     * New option in the File menu.
     */
    private JMenuItem newItem;
    /**
     * Option option in the File menu.
     */
    private JMenuItem openItem;

    /**
     * Save option in the File menu.
     */
    private JMenuItem saveItem;

    /**
     * Save As option in the File menu.
     */
    private JMenuItem saveAsItem;

    /**
     * Exit option in the File menu.
     */
    private JMenuItem exitItem;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs the menu bar.<br>
     * <b> post: </b> The menu bar was initialized along with the car factory interface given by
     * the parameter.
     *
     * @param pPrincipalInterface A reference to the principal class of the user interface.
     *                            pPrincipalInterface !=null.
     */
    public MenuBar(CarFactoryInterface pPrincipalInterface) {
        principal = pPrincipalInterface;

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_A);
        add(fileMenu);

        // Add "new" menu item.
        newItem = new JMenuItem("New");
        newItem.setActionCommand(NEW);
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newItem.setMnemonic(KeyEvent.VK_N);
        newItem.addActionListener(this);
        fileMenu.add(newItem);

        // Add "open" menu item.
        openItem = new JMenuItem("Open");
        openItem.setActionCommand(OPEN);
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        openItem.setMnemonic(KeyEvent.VK_A);
        openItem.addActionListener(this);
        fileMenu.add(openItem);

        // Add "save" menu item.
        saveItem = new JMenuItem("Save");
        saveItem.setActionCommand(SAVE);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.addActionListener(this);
        fileMenu.add(saveItem);

        // Add "save as" menu item.
        saveAsItem = new JMenuItem("Save As");
        saveAsItem.setActionCommand(SAVE_AS);
        saveAsItem.addActionListener(this);
        fileMenu.add(saveAsItem);

        // Separate the "exit" item
        fileMenu.addSeparator();

        // Add "exit" menu item.
        exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand(EXIT);
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Executes the action that corresponds to the option selected in the menu.
     *
     * @param pEvent The event that is selected as a menu option.
     *               pEvent !=null.
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();

        if (NEW.equals(command)) {
            principal.restart();
        }
        else if (OPEN.equals(command)) {
            principal.openFile();
        }
        else if (SAVE.equals(command)) {
            principal.save();
        }
        else if (SAVE_AS.equals(command)) {
            principal.saveComo();
        }
        else
            principal.dispose();
    }

}
