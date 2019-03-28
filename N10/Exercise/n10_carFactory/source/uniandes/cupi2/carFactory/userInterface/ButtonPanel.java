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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * This is the panel where the buttons that control the application is controlled from.
 */
public class ButtonPanel extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    /**
     * Constant that represents the background color.
     */
    private static final String BACKGROUND_COLOR = "Background";

    /**
     * Constant that represents the select button.
     */
    public static final String SELECT = "Select";

    /**
     * Constant that represents that nothing is selected.
     */
    public static final String NONE = "None";

    /**
     * Constant that represents the erase button.
     */
    public static final String ERASE = "Erase";

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
     * Array of buttons.
     */
    private JToggleButton[] buttons;

    /**
     * Group of buttons.
     */
    private ButtonGroup buttonGroup;

    /**
     * Button for choosing a car.
     */
    private JButton btnCarColor;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs the panel and initializes its components. <br>
     * <b> post: </b> The panel was initialized with the userInterface of the car factory given
     * by the parameter.
     *
     * @param pPrincipalInterface Reference to the principal class of the interface.
     *                            pPrincipalInterface !=null.
     */
    public ButtonPanel(CarFactoryInterface pPrincipalInterface) {
        String imagesDir =
                "." + File.separator + "data" + File.separator + "images" + File.separator;

        principal = pPrincipalInterface;

        setBorder(new TitledBorder(""));

        ArrayList types = new ArrayList();
        types.add(SELECT);
        types.add(ERASE);
        types.addAll(pPrincipalInterface.darOpcionesSeleccion());
        buttons = new JToggleButton[types.size()];
        buttonGroup = new ButtonGroup();
        String tipo;
        for (int i = 0; i < types.size(); i++) {
            tipo = (String) types.get(i);
            buttons[i] = new JToggleButton(new ImageIcon(imagesDir + tipo + "Button" + ".gif"));
            buttons[i].setPreferredSize(new Dimension(50, 50));
            buttons[i].setToolTipText(tipo);
            buttons[i].setActionCommand(tipo);
            if (tipo.equals(ERASE)) {
                buttons[i].addActionListener(this);
            }
            buttonGroup.add(buttons[i]);
        }

        btnCarColor = new JButton(" ");
        btnCarColor.setActionCommand(BACKGROUND_COLOR);
        btnCarColor.setBackground(new Color(200, 0, 0));
        btnCarColor.addActionListener(this);
        btnCarColor.setToolTipText("Background color");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER,
                                                        GridBagConstraints.CENTER,
                                                        new Insets(2, 2, 2, 2), 0, 0);

        int b = 0;
        int size = buttons.length;
        for (int i = 0; i < size + 1 / 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (b != size) {

                    gbc.gridx = j;
                    gbc.gridy = i;
                    add(buttons[b], gbc);
                    b++;
                }

            }
        }

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = buttons.length / 2 + 1;
        add(btnCarColor, gbc);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the selections option.
     *
     * @return Option that is selected in this panel.
     */
    public String getSelectedOption() {
        ArrayList opciones = principal.darOpcionesSeleccion();
        String selectedType = NONE;
        if (buttons[0].isSelected()) {
            selectedType = SELECT;
        }
        else if (buttons[1].isSelected()) {
            selectedType = ERASE;
        }
        else {
            boolean selected = false;
            for (int i = 2; i < buttons.length && !selected; i++) {
                selected = buttons[i].isSelected();
                if (selected) {
                    selectedType = (String) opciones.get(i - 2);
                }
            }
        }
        return selectedType;
    }

    /**
     * Returns the selected color for the background of the cars.
     *
     * @return Background color.
     */
    public Color getColorBackground() {
        return btnCarColor.getBackground();
    }

    /**
     * Executes the corresponding action to the pressed button.
     *
     * @param pEvent The even for clicking ona button.
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();

        if (ERASE.equals(command)) {
            principal.eliminar();
        }

        else if (BACKGROUND_COLOR.equals(command)) {
            Color colorFondo =
                    JColorChooser.showDialog(this, "Background color", btnCarColor.getBackground());
            btnCarColor.setBackground(colorFondo);
        }
    }

}
