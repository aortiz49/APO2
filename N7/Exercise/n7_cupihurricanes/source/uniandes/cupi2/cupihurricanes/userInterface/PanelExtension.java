/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n7_Hurricane
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupihurricanes.userInterface;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel containing extension points.
 */
public class PanelExtension extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant with action for button 1.
     */
    private final String OPTION_1 = "option 1";

    /**
     * Constant with action for button 2.
     */
    private final String OPTION_2 = "option 2";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal window of the application.
     */
    private HurricaneInterface principal;

    // -----------------------------------------------------------------
    // Attributes of the interface
    // -----------------------------------------------------------------

    /**
     * Button of the extension 1.
     */
    private JButton buttonOption1;

    /**
     * Button of the extension 2.
     */
    private JButton buttonOption2;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs the panel with a reference to the principal window of the application.
     * @param pPrincipal Principal window of the application.- pPrincipal!=null.
     */
    public PanelExtension(HurricaneInterface pPrincipal) {
        principal = pPrincipal;
        inicializar();
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Initializes the components of the  panel. <br>
     * <b>post: </b> Components were initialized and placed.
     */
    private void inicializar() {
        setBorder(new TitledBorder("Extension points"));

        setLayout(new FlowLayout());
        buttonOption1 = new JButton("Option 1");
        buttonOption1.setActionCommand(OPTION_1);
        buttonOption1.addActionListener(this);

        buttonOption2 = new JButton("Option 2");
        buttonOption2.setActionCommand(OPTION_2);
        buttonOption2.addActionListener(this);

        add(buttonOption1);
        add(buttonOption2);
    }

    /**
     * Method is executed on a button press.
     * @param pEvent Event on mouse click.
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();
        if (OPTION_1.equals(command)) {
            principal.reqFuncOption1();
        }
        else if (OPTION_2.equals(command)) {
            principal.reqFuncOption2();
        }
    }

}
