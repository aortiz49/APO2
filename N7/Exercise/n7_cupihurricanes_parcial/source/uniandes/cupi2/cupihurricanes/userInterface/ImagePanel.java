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

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel containing the image.
 */
public class ImagePanel extends JPanel {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant for the image path .
     */
    public final static String PATH = "data/Cupihurricanes1.jpg";

    // -----------------------------------------------------------------
    // Attributes of the user interface
    // -----------------------------------------------------------------

    /**
     * Label with the image.
     */
    private JLabel lblImage;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor of the panel.
     */
    public ImagePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);


        ImageIcon icon = new ImageIcon(PATH);
        lblImage = new JLabel("");
        lblImage.setIcon(icon);
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        lblImage.setVerticalAlignment(JLabel.CENTER);
        lblImage.setIcon(new ImageIcon(PATH));

        add(lblImage, BorderLayout.CENTER);
    }

}
