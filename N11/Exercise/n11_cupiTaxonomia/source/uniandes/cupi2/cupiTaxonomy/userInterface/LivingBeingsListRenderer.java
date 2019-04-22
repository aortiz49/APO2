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

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import uniandes.cupi2.cupiTaxonomy.world.LivingBeing;

/**
 * Class that renders the living beings in the list.
 */
@SuppressWarnings({"rawtypes", "serial"})
public class LivingBeingsListRenderer extends DefaultListCellRenderer {
    /**
     * Method that scales the images of the living beings.
     *
     * @param pList        The list that will be painted.
     *                     pList != null.
     * @param pObject      The value returned by pList.getModel().getElementAt(pIndex).
     *                     pObject != null.
     * @param pIndex       Index of the cell.
     *                     pIndex >= 0.
     * @param pSelected    Indicates if the specified cell is selected.
     *                     pSelected != null.
     * @param pCellInFocus Indicates if the specified cell is in focus.
     *                     pCellInFocus != null.
     * @return Label with the image of the living being that is rendered.
     */
    public Component getListCellRendererComponent(JList pList, Object pObject, int pIndex,
                                                  boolean pSelected, boolean pCellInFocus) {
        JLabel label = (JLabel) super
                .getListCellRendererComponent(pList, pObject, pIndex, pSelected, pCellInFocus);

        try {
            LivingBeing ser = (LivingBeing) pObject;
            BufferedImage img = ImageIO.read(new File(ser.getImagePath()));
            ImageIcon icono = new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            label.setIcon(icono);
        } catch (IOException e) {
            // Not possible to load the image
        }

        return label;
    }
}
