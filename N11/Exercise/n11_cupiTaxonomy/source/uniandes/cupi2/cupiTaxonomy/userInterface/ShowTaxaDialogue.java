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
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Dialog to see the list of taxa in the taxonomic tree.
 */
@SuppressWarnings({"rawtypes", "serial", "unchecked"})
public class ShowTaxaDialogue extends JDialog {
    // -----------------------------------------------------------------
    // User interface attributes
    // -----------------------------------------------------------------

    /**
     * List with the taxa.
     */
    private JList taxonList;

    /**
     * Scroll panel containing the taxon list.
     */
    private JScrollPane scroll;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructs a dialogue that shows the list of taxa.
     * <b> post: </b> The dialogue was created with all its graphical elements.
     *
     * @param pTaxa List of taxa to be shown.
     *              pTaxa != null
     */
    public ShowTaxaDialogue(ArrayList pTaxa) {
        setLayout(new BorderLayout());
        setSize(300, 280);
        setModal(true);
        setTitle("Show taxa");
        setResizable(false);

        taxonList = new JList(pTaxa.toArray());
        scroll = new JScrollPane(taxonList);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(
                new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new LineBorder(Color.BLACK, 1)));
        add(scroll, BorderLayout.CENTER);
    }
}