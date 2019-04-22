/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n11_taxonomicTree
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package uniandes.cupi2.cupiTaxonomy.userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Di�logo parto be added un newItem tax�n.
 */
@SuppressWarnings({"rawtypes", "serial", "unchecked"})
public class AddTaxonDialogue extends JDialog implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant that represents el command parto be added.
     */
    private final static String ADD = "Add";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal window of the application.
     */
    private TaxonomicTreeInterface principal;

    /**
     * Level of the tax�n to be added.
     */
    private int levelTaxon;

    // -----------------------------------------------------------------
    // User interface attributes
    // -----------------------------------------------------------------

    /**
     * ComboBox que guarda los posibles taxa padres.
     */
    private JComboBox comboFathers;

    /**
     * Text field with the name of the tax�n.
     */
    private JTextField txtNombre;

    /**
     * Button Add.
     */
    private JButton btnAdd;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructs un di�logo parto be added un tax�n.
     * <b> post: </b> Se crea el di�logo con todos sus elementos gr�ficos.
     * @param pPrincipal Principal window of the application. pPrincipal != null.
     * @param pLevel Level of the tax�n to be added. pLevel != null && pLevel != "".
     */
    public AddTaxonDialogue(TaxonomicTreeInterface pPrincipal, String pLevel) {
        principal = pPrincipal;

        String[] info = pLevel.split(". ");
        levelTaxon = Integer.parseInt(info[0]);

        setLayout(new BorderLayout());
        setSize(250, 120);
        setModal(true);
        setLocationRelativeTo(principal);
        setTitle("Add " + info[1]);
        setResizable(false);

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(0, 50));
        panel1.setLayout(new GridLayout(2, 2));

        ArrayList listFathers = principal.getTaxaLevel(levelTaxon - 1);

        panel1.add(new JLabel(" Father: "));
        comboFathers = new JComboBox(listFathers.toArray());
        panel1.add(comboFathers);

        panel1.add(new JLabel(" Nombre: "));
        txtNombre = new JTextField();
        panel1.add(txtNombre);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        add(panel1, BorderLayout.CENTER);

        btnAdd = new JButton(ADD);
        btnAdd.setActionCommand(ADD);
        btnAdd.addActionListener(this);

        add(btnAdd, BorderLayout.SOUTH);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Button events manager.
     * @param pEvent Action generated by the event. pEvent != null.
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();
        if (command.equals(ADD)) {
            String padre = (String) comboFathers.getSelectedItem();
            String name = txtNombre.getText();

            if (padre != null && !padre.isEmpty() && name != null && !name.isEmpty()) {
                principal.addTaxon(padre, levelTaxon, name);
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(this,
                                              "Por favor ingrese la informaci�n completa of the "
                                                      + "tax�n.",
                                              "Add tax�n", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}