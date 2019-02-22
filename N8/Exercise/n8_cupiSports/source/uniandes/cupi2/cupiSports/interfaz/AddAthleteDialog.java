/*
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
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiSports.world.ElementExistsException;

/**
 * Dialog to add an athlete.
 */
public class AddAthleteDialog extends JDialog implements ActionListener {
    // -----------------------------------------------
    // Constants
    // -----------------------------------------------

    /**
     * Constant to add an athlete.
     */
    private final static String ADD = "Add athlete";

    /**
     * Constant to browse files.
     */
    private final static String BROWSE = "Browse";

    // -----------------------------------------------
    // Attributes
    // -----------------------------------------------

    /**
     * Label for the athlete's name.
     */
    private JLabel lblName;

    /**
     * Label for the athlete's age.
     */
    private JLabel lblAge;

    /**
     * Label for the athlete's place of residency.
     */
    private JLabel lblPlaceOfResidency;

    /**
     * Label for the number of trophies won by the athlete.
     */
    private JLabel lblTrophiesWon;

    /**
     * Label for the athlete's image.
     */
    private JLabel labelImage;

    /**
     * Text field for the athlete's name.
     */
    private JTextField txtName;

    /**
     * Text field for the athlete's age.
     */
    private JTextField txtAge;

    /**
     * Text field for the athlete's place of residency.
     */
    private JTextField txtPlaceOfResidency;

    /**
     * Text field for the number of trophies won by the athlete.
     */
    private JTextField txtTrophiesWon;

    /**
     * Text field for the athlete's image.
     */
    private JTextField txtImage;

    /**
     * Panel containing the athlete's information.
     */
    private JPanel panelInfo;

    /**
     * Button to add an athlete.
     */
    private JButton btnAdd;

    /**
     * Button to browse files.
     */
    private JButton btnBrowse;

    /**
     * Principal window of the application.
     */
    private CupiSportsInterface principal;

    // -----------------------------------------------
    // Methods
    // -----------------------------------------------

    /**
     * Creates the dialog to add an athlete.
     *
     * @param pPrincipal Principal window of the application. pPrincipal != null.
     */
    public AddAthleteDialog(CupiSportsInterface pPrincipal) {
        super(pPrincipal, true);

        principal = pPrincipal;
        setTitle("Add athlete");
        setLayout(new java.awt.BorderLayout());

        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(450, 200));

        panelInfo.setBorder(new TitledBorder("Information"));
        GridLayout layout = new GridLayout(5, 2, -175, 30);
        layout.setVgap(10);
        panelInfo.setLayout(layout);

        lblName = new JLabel("Name: ");
        panelInfo.add(lblName);
        txtName = new JTextField();
        panelInfo.add(txtName);

        lblAge = new JLabel("Age: ");
        panelInfo.add(lblAge);
        txtAge = new JTextField();
        panelInfo.add(txtAge);

        lblPlaceOfResidency = new JLabel("Place of residence: ");
        panelInfo.add(lblPlaceOfResidency);
        txtPlaceOfResidency = new JTextField();
        panelInfo.add(txtPlaceOfResidency);

        lblTrophiesWon = new JLabel("Trophies won: ");
        panelInfo.add(lblTrophiesWon);
        txtTrophiesWon = new JTextField();
        panelInfo.add(txtTrophiesWon);

        labelImage = new JLabel("Image: ");
        panelInfo.add(labelImage);

        BorderLayout border = new BorderLayout();
        border.setHgap(5);
        border.setVgap(5);

        JPanel panelImagePath = new JPanel(border);
        txtImage = new JTextField();
        panelImagePath.add(txtImage, BorderLayout.CENTER);

        btnBrowse = new JButton(BROWSE);
        btnBrowse.setActionCommand(BROWSE);
        btnBrowse.addActionListener(this);
        panelImagePath.add(btnBrowse, BorderLayout.EAST);

        panelInfo.add(panelImagePath);

        add(panelInfo, BorderLayout.NORTH);

        btnAdd = new JButton(ADD);
        btnAdd.setActionCommand(ADD);
        btnAdd.addActionListener(this);
        add(btnAdd, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Method that collects the actions of the objects it is listening to.
     *
     * @param e Event realized.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ADD)) {
            String name = txtName.getText();
            String ageStr = txtAge.getText();
            String image = txtImage.getText();
            String placeOfResidence = txtPlaceOfResidency.getText();
            String trophiesStr = txtTrophiesWon.getText();

            if (name.equals("") || ageStr.equals("") || placeOfResidence.equals("") || image
                    .equals("") || trophiesStr.equals("")) {
                JOptionPane.showMessageDialog(this, "Incomplete data fields!", "Add athlete",
                                              JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    int age = Integer.parseInt(ageStr);
                    int trofeos = Integer.parseInt(trophiesStr);
                    if (age <= 0 || trofeos < 0) {
                        JOptionPane.showMessageDialog(this, "Age and trophies won must be positive "
                                + "whole numbers!", "Add athlete", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        principal
                                .addOutstandingAthlete(name, age, placeOfResidence, trofeos, image);
                        dispose();
                    }

                } catch (NumberFormatException e2) {
                    JOptionPane.showMessageDialog(this, "Age and trophies won must be numbers.",
                                                  "Add athlete", JOptionPane.ERROR_MESSAGE);
                } catch (ElementExistsException e3) {
                    JOptionPane.showMessageDialog(this, "The athlete " + name + " already exists.",
                                                  "Add athlete", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        if (e.getActionCommand().equals(BROWSE)) {
            JFileChooser fc = new JFileChooser("./data/images");
            fc.setDialogTitle("Apply Image...");
            fc.setMultiSelectionEnabled(false);

            int resultado = fc.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String image = fc.getSelectedFile().getName();
                try {
                    Image variableImage = (ImageIO.read(new File("./data/images/" + image)));

                    if (variableImage != null) {
                        txtImage.setText("./data/images/" + image);
                    }
                    else {
                        JOptionPane
                                .showMessageDialog(this, "The selected file is not a valid image!",
                                                   "Add athlete", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException e1) {
                    JOptionPane
                            .showMessageDialog(this, "Error when reading the image.", "Add athlete",
                                               JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
