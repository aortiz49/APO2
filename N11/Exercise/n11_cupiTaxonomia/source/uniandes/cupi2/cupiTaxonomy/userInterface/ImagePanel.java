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

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Class that places the banner image.
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
    /**
     * Default constructor method. <br>
     * <b> post: </b> Places the banner image.
     */
    public ImagePanel() {
        JLabel image = new JLabel();
        ImageIcon icono = new ImageIcon("data/images/banner.png");
        // La agrega a la etiqueta
        image = new JLabel("");
        image.setIcon(icono);
        add(image);
        setBorder(new LineBorder(Color.BLACK));
    }
}
