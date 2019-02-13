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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

import uniandes.cupi2.cupihurricanes.world.Hurricane;
import uniandes.cupi2.cupihurricanes.world.HurricaneSystem;

/**
 * Panel where the list of hurricanes is shown as well as the buttons to interact with the list.
 */
public class HurricaneListPanel extends JPanel implements ListSelectionListener, ActionListener {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant to register a hurricane.
     */
    private static final String REGISTER_HURRICANE = "REGISTER_HURRICANE";

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
     * List to be shown.
     */
    private JList hurricaneList;

    /**
     * Component to contain the list.
     */
    private JScrollPane scroll;

    /**
     * Button to registrar a hurricane.
     */
    private JButton buttonRegisterHurricane;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs the panel and initializes its components.
     *
     * @param pPrincipal Principal window of the application. pPrincipal != null.
     */
    public HurricaneListPanel(HurricaneInterface pPrincipal) {
        principal = pPrincipal;

        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(200, 300));


        hurricaneList = new JList();
        hurricaneList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hurricaneList.addListSelectionListener(this);

        scroll = new JScrollPane(hurricaneList);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(
                new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new LineBorder(Color.BLACK, 1)));
        add(scroll,BorderLayout.CENTER);

        buttonRegisterHurricane = new JButton("Register hurricane");
        buttonRegisterHurricane.addActionListener(this);
        buttonRegisterHurricane.setActionCommand(REGISTER_HURRICANE);
        add(buttonRegisterHurricane, BorderLayout.SOUTH);

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the hurricane list being shown.
     *
     * @param pList List containing the hurricanes that must be shown.
     */
    public void refreshList(ArrayList pList) {

        hurricaneList.setListData(pList.toArray());
        hurricaneList.setSelectedIndex(0);

    }

    /**
     * Selects an element of the list.
     *
     * @param pSelected Position of the elemen to be selected.
     */
    public void select(int pSelected) {
        hurricaneList.setSelectedIndex(pSelected);
        hurricaneList.ensureIndexIsVisible(pSelected);
    }

    /**
     * Updates the information of the panel with the information from the hurricane
     * according to the hurricane slected on the list.
     *
     * @param pEvent Event of a change in a selected item on the list.
     */
    public void valueChanged(ListSelectionEvent pEvent) {

        if(hurricaneList.getSelectedValue()!=null){
            Hurricane hurricane = (Hurricane)hurricaneList.getSelectedValue();
            principal.updateHurricaneInformation(hurricane);
        }
    }

    /**
     * Method is called when the user click a button. M
     *
     * @param pEvent Event on mouse click.
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();

        if (REGISTER_HURRICANE.equals(command)) {

            principal.showRegisterHurricaneDialog();
        }

    }
}