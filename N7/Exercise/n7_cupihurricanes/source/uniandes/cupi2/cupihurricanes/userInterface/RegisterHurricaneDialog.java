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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Dialogue for registering a hurricane.
 */
public class RegisterHurricaneDialog extends JDialog implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant with the predetermined image path.
     */
    private static final String PATH_NO_IMAGE = "./data/images/noImage.png";

    /**
     * Constant with the action button to register a hurricane.
     */
    private static final String REGISTER = "RegisterHurricane";

    /**
     * Constant with the action button to find an image.
     */
    private static final String SEARCH = "FindImage";

    /**
     * Constant with the action button to close the dialog.
     */
    private static final String CLOSE = "CLOSE";

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
     * Text field for the hurricane image.
     */
    private JTextField txtImage;

    /**
     * Text field for the hurricane name.
     */
    private JTextField txtName;

    /**
     * Text field for the hurricane category.
     */
    private JTextField txtCategory;

    /**
     * Text field for the hurricane velocity.
     */
    private JTextField txtVelocity;

    /**
     * Text field for the hurricane damages.
     */
    private JTextField txtCosts;

    /**
     * Label for the hurricane image.
     */
    private JLabel lblImage;

    /**
     * Label for the hurricane name.
     */
    private JLabel lblName;

    /**
     * Label for the hurricane category.
     */
    private JLabel lblCategory;

    /**
     * Label for the hurricane velocity.
     */
    private JLabel lblCosts;

    /**
     * Field for the hurricane damages.
     */
    private JLabel lblVelocity;

    /**
     * Button to register a hurricane.
     */
    private JButton buttonRegister;

    /**
     * Button to select the hurricane image.
     */
    private JButton buttonExamine;

    /**
     * Button to close the dialog.
     */
    private JButton buttonClose;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs a dialog and initializes its components.
     *
     * @param pPrincipal Principal window of the application.
     */
    public RegisterHurricaneDialog(HurricaneInterface pPrincipal) {
        principal = pPrincipal;

        setLayout(new GridBagLayout());
        setSize(new Dimension(600, 170));
        setTitle("Registrar hurricane");

        JPanel dataPanel = new JPanel(new GridBagLayout());

        // Name
        lblName = new JLabel("Name: ");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        dataPanel.add(lblName, gbc);
        txtName = new JTextField("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;

        dataPanel.add(txtName, gbc);

        // Category
        lblCategory = new JLabel("Category: ");
        lblCategory.setBorder(new EmptyBorder(0, 5, 0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.EAST;

        dataPanel.add(lblCategory, gbc);
        txtCategory = new JTextField("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        dataPanel.add(txtCategory, gbc);

        // Velocity
        lblVelocity = new JLabel("Velocity: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        dataPanel.add(lblVelocity, gbc);
        txtVelocity = new JTextField("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        dataPanel.add(txtVelocity, gbc);

        // Costo en damages
        lblCosts = new JLabel("Cost in damages: ");
        lblCosts.setBorder(new EmptyBorder(0, 5, 0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        dataPanel.add(lblCosts, gbc);
        txtCosts = new JTextField("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.ipadx = 160;
        dataPanel.add(txtCosts, gbc);

        // Image
        lblImage = new JLabel("Image: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        dataPanel.add(lblImage, gbc);
        txtImage = new JTextField("");
        buttonExamine = new JButton("Examine");
        buttonExamine.setActionCommand(SEARCH);
        buttonExamine.addActionListener(this);

        // Add button
        JPanel panelBoton = new JPanel();
        buttonRegister = new JButton("Registrar hurricane");
        buttonRegister.setActionCommand(REGISTER);
        buttonRegister.addActionListener(this);
        panelBoton.add(buttonRegister);

        buttonClose = new JButton("Close");
        buttonClose.setActionCommand(CLOSE);
        buttonClose.addActionListener(this);
        panelBoton.add(buttonClose);

        JPanel panelImage = new JPanel(new GridLayout());

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        panelImage.add(txtImage);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        panelImage.add(buttonExamine);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        dataPanel.add(panelImage, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(dataPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(panelBoton, gbc);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Method that executes when you clock on a button.
     *
     * @param pEvent Event when clciking on a button.
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();

        if (REGISTER.equals(command)) {
            try {
                String name = txtName.getText();
                int category = Integer.parseInt(txtCategory.getText());
                String image = txtImage.getText();
                double damages = Double.parseDouble(txtCosts.getText());
                int velocity = Integer.parseInt(txtVelocity.getText());

                File file = new File(image);
                if (!file.exists()) {
                    image = PATH_NO_IMAGE;
                    JOptionPane.showMessageDialog(null,
                                                  "Image path invalid. Will use a default image.",
                                                  "Add Hurricane",
                                                  JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    try {
                        BufferedImage bImage = ImageIO.read(file);
                        if (bImage == null) {
                            image = PATH_NO_IMAGE;
                            JOptionPane.showMessageDialog(null,
                                                          "Image path invalid. Will use a default image.",
                                                          "Add Hurricane",
                                                          JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException e) {
                        image = PATH_NO_IMAGE;
                        JOptionPane.showMessageDialog(null,
                                                      "Image path invalid. Will use a default image.",
                                                      "Add Hurricane",
                                                      JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                if (name == null || name.equals("")) {
                    JOptionPane.showMessageDialog(this, "You must enter the name of the hurricane.",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (category < 1 || category > 5) {
                    JOptionPane.showMessageDialog(this,
                                                  "The hurricane category is incorrect.",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (damages < 0) {
                    JOptionPane.showMessageDialog(this,
                                                  "The estimated damages are incorrect.",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (velocity < 0) {
                    JOptionPane.showMessageDialog(this,
                                                  "The velocity is incorrect.",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (image == null || image.equals("")) {
                    JOptionPane.showMessageDialog(this, "Enter the hurricane image.",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    principal.registerHurricane(name, category, velocity, damages, image);

                    txtName.setText("");
                    txtCategory.setText("");
                    txtImage.setText("");
                    txtCosts.setText("");
                    txtVelocity.setText("");
                    this.dispose();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                                              "The category must be an integer. Damage and "
                                                      + "velocity must be real numbers.",
                                              "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (SEARCH.equals(command)) {
            JFileChooser fc = new JFileChooser("./data");
            fc.setDialogTitle("Find hurricane image.");
            fc.setMultiSelectionEnabled(false);

            int resultado = fc.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String image = fc.getSelectedFile().getAbsolutePath();
                txtImage.setText(image);
            }
        }
        else if (CLOSE.equals(command)) {
            this.dispose();
        }

    }

}
