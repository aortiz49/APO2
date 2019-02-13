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
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;

import uniandes.cupi2.cupihurricanes.world.Hurricane;

/**
 * Panel where hurricane data is shown.
 */
public class HurricaneDataPanel extends JPanel {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant indicating the image height of the hurricane.
     */
    private static final int HEIGHT = 200;

    /**
     * Constant indicating the image width of the hurricane.
     */
    private static final int WIDTH = 200;

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Label for image name.
     */
    private JLabel lblImage;

    /**
     * Label for the name of the hurricane.
     */
    private JLabel lblName;

    /**
     * Label for the category of the hurricane.
     */
    private JLabel lblCategory;

    /**
     * Label for the estimated damages cost of the hurricane.
     */
    private JLabel lblCosts;

    /**
     * Label for the velocity of the hurricane.
     */
    private JLabel lblVelocity;

    /**
     * Text field for the name of the hurricane.
     */
    private JTextField txtName;

    /**
     * Text field for the category of the hurricane.
     */
    private JTextField txtCategory;

    /**
     * Text field for the cost in damages of hurricane.
     */
    private JTextField txtCosts;

    /**
     * Text field para la velocity of the  hurricane.
     */
    private JTextField txtVelocity;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs the panel and initializes its components.
     */
    public HurricaneDataPanel() {
        setLayout(new GridBagLayout());
        lblName = new JLabel("Name: ");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblName, gbc);

        txtName = new JTextField();
        txtName.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(txtName, gbc);

        lblCategory = new JLabel("Category: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblCategory, gbc);

        txtCategory = new JTextField();
        txtCategory.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(txtCategory, gbc);

        lblVelocity = new JLabel("Velocity: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblVelocity, gbc);

        txtVelocity = new JTextField();
        txtVelocity.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(txtVelocity, gbc);

        lblCosts = new JLabel("Damage costs: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(lblCosts, gbc);

        txtCosts = new JTextField();
        txtCosts.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(txtCosts, gbc);

        lblImage = new JLabel();
        lblImage.setBorder(new LineBorder(Color.BLACK, 1));
        lblImage.setMinimumSize(new Dimension(230, 153));
        lblImage.setMaximumSize(new Dimension(230, 153));

        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(lblImage, gbc);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the hurricane data
     *
     * @param pHurricane Hurricane whose data is to be seen.
     *                   pHurricane!= null.
     */
    public void updateHurricaneInformation(Hurricane pHurricane) {
        try {
            String image = pHurricane.getImage();
            BufferedImage bImage = ImageIO.read(new File(image));

            Image laImage = null;
            laImage = bImage.getScaledInstance((int) (WIDTH * 0.85), (int) (HEIGHT * 0.85),
                                               Image.SCALE_AREA_AVERAGING);
            lblImage.setIcon(new ImageIcon(laImage));

            txtName.setText(pHurricane.getName());
            txtCategory.setText("" + pHurricane.getCategory());
            txtVelocity.setText("" + pHurricane.getVelocity() + "km/h");
            txtCosts.setText("" + pHurricane.getEstimatedDamageCost() + " MUS");

            validate();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                                          "Invalid image path. Operation canceled.", "Add "
                                                  + "hurricane",
                                          JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Cleans every field.
     */
    public void clearData() {
        lblImage.setIcon(null);
        txtName.setText("");
        txtCategory.setText("");
        txtVelocity.setText("");
        txtCosts.setText("");
    }
}