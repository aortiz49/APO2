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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel containing the image banner.
 */
public class ImagePanel extends JPanel {

    // -----------------------------------------------------------------
    // Attributes of the interface
    // -----------------------------------------------------------------

    /**
     * Label with the banner image.
     */
    private JLabel lblImage;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     */
    public ImagePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 165));

        ImageIcon icon = new ImageIcon("./data/images/Banner.jpg");
        lblImage = new JLabel("");
        lblImage.setIcon(icon);
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        lblImage.setVerticalAlignment(JLabel.CENTER);
        lblImage.setIcon(icon);

        add(lblImage, BorderLayout.CENTER);
    }
}
