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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiTaxonomy.world.LivingBeing;

/**
 * Dialog to see the information of a living being.
 */
@SuppressWarnings({"serial"})
public class ShowLivingBeingDialogue extends JDialog {
    // -----------------------------------------------------------------
    // User interface attributes
    // -----------------------------------------------------------------

    /**
     * Text field with the common name of the living being.
     */
    private JTextField txtCommonName;

    /**
     * Text field with the name scientific name of the living being.
     */
    private JTextField txtScientificName;

    /**
     * Text area with the characteristics of the living being.
     */
    private JTextArea areaCharacteristics;

    /**
     * Scroll pane with the characteristics of the living being.
     */
    private JScrollPane scrollCharacteristics;

    /**
     * Label with the image of the living being.
     */
    private JLabel labImage;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructs a dialogue that shows the information of the living being.
     * <b> post: </b> Dialogue was created with all its graphical components.
     *
     * @param pLivingBeing Living being who's information will be shown.
     *                     pLivingBeing != null.
     */
    public ShowLivingBeingDialogue(LivingBeing pLivingBeing) {
        setLayout(new GridBagLayout());
        setSize(500, 250);
        setModal(true);
        setTitle("Show living being");
        setResizable(true);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridy = 0;
        gbc.gridheight = 3;
        labImage = new JLabel();
        labImage.setHorizontalAlignment(JLabel.CENTER);
        add(labImage, gbc);

        try {
            BufferedImage img = ImageIO.read(new File(pLivingBeing.getImagePath()));
            ImageIcon icono = new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
            labImage.setIcon(icono);
        } catch (IOException e) {
            // Not possible to load the image
        }

        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        areaCharacteristics = new JTextArea(pLivingBeing.getCharacteristics());
        areaCharacteristics.setLineWrap(true);
        areaCharacteristics.setEditable(false);

        gbc.gridwidth = 2;
        scrollCharacteristics = new JScrollPane(areaCharacteristics);
        scrollCharacteristics.setSize(new Dimension(250, 100));
        scrollCharacteristics.setPreferredSize(new Dimension(250, 100));
        scrollCharacteristics.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCharacteristics.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollCharacteristics.setBorder(new TitledBorder(" Characteristics: "));
        add(scrollCharacteristics, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 0;
        add(new JLabel(" Common name: "), gbc);

        gbc.gridy = 1;
        add(new JLabel(" Scientific name: "), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        txtCommonName = new JTextField(pLivingBeing.getCommonName());
        txtCommonName.setEditable(false);
        add(txtCommonName, gbc);

        gbc.gridy = 1;
        txtScientificName = new JTextField(pLivingBeing.getScientificName());
        txtScientificName.setEditable(false);
        add(txtScientificName, gbc);
    }
}