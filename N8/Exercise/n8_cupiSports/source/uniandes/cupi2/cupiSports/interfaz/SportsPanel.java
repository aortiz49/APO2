/*/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n8_Sports
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiSports.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiSports.world.Sport;

/**
 * Panel to manage the sports.
 */
public class SportsPanel extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant to define the action command of the sports.
     */
    private static final String COMBO_SPORTS = "Combo sports";
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal interface of the application
     */
    private CupiSportsInterface principal;

    // -----------------------------------------------------------------
    // Attributes of the interface
    // -----------------------------------------------------------------

    /**
     * Combo Box where the sports are located.
     */
    private JComboBox comboSports;


    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the panel where teh sports are located.
     * @param pPrincipal Principal interface of the application pPrincipal != null.
     */
    public SportsPanel(CupiSportsInterface pPrincipal) {
        principal = pPrincipal;
        setBorder(new TitledBorder("Sports"));
        BorderLayout layout = new BorderLayout();
        layout.setVgap(5);
        layout.setHgap(5);
        setLayout(layout);

        comboSports = new JComboBox();
        comboSports.setEditable(false);
        comboSports.addActionListener(this);
        comboSports.setActionCommand(COMBO_SPORTS);
        add(comboSports, BorderLayout.NORTH);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the combo box with the list of sports.
     * @param pSports List of sports in the combo box.
     *                pSports != null.
     */
    public void updateSports(ArrayList<Sport> pSports) {
        comboSports.removeAllItems();
        for (int i = 0; i < pSports.size(); i++) {
            comboSports.addItem(pSports.get(i));
        }
    }

    /**
     * Returns the selected sport.
     * @return Selected sport.
     */
    public Sport getSportSeleccionado() {
        return (Sport) comboSports.getSelectedItem();
    }

    /**
     * Selects the sport.
     * @param pIndex Index of sport to select.
     */
    public void selectSport(int pIndex) {
        comboSports.setSelectedIndex(pIndex);
    }

    /**
     * Method that takes care of the actions <br>
     * <b>post:</b> Changes the athlete information that is being shown according to the
     * selected athlete.
     * @param e Parameter that encapsulates the information.
     */
    public void actionPerformed(ActionEvent e) {


        String command = e.getActionCommand();

        if(COMBO_SPORTS.equals(command)) {
            Sport sportName = (Sport) comboSports.getSelectedItem();
           principal.updateInfoSport(sportName);

        }
    }

}
