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

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;

import uniandes.cupi2.cupiTaxonomy.world.TaxonomicTree;
import uniandes.cupi2.cupiTaxonomy.world.Taxon;

/**
 * Principal window of the application.
 */
@SuppressWarnings({"rawtypes", "serial"})
public class TaxonomicTreeInterface extends JFrame {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant that represents the file path to the taxonomic tree information .
     */
    private static final String FILE_PATH = "./data/taxonomicTree.data";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal class of the world
     */
    private TaxonomicTree tree;

    // -----------------------------------------------------------------
    // User interface attributes
    // -----------------------------------------------------------------

    /**
     * Panel containing the banner image.
     */
    private ImagePanel panelImage;

    /**
     * Panel containing the extension options.
     */
    private PanelExtension panelExtension;

    /**
     * Panel with the list of taxa.
     */
    private TaxaPanel panelTaxa;

    /**
     * Panel with the list of living beings.
     */
    private LivingBeingsPanel livingBeingsPanel;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs the principal window. <br>
     * <b>post: </b> The window was created with all its components.
     */
    public TaxonomicTreeInterface() {
        // Creates the principal class
        try {
            tree = new TaxonomicTree(FILE_PATH);

            // Constructs the form
            setLayout(new BorderLayout());
            setSize(650, 530);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setTitle(" Taxonomic Tree ");
            setResizable(false);

            // Creates the panel
            panelImage = new ImagePanel();
            add(panelImage, BorderLayout.NORTH);

            JPanel aux = new JPanel();
            aux.setLayout(new BorderLayout());

            panelTaxa = new TaxaPanel(this);
            aux.add(panelTaxa, BorderLayout.WEST);

            livingBeingsPanel = new LivingBeingsPanel();
            aux.add(livingBeingsPanel, BorderLayout.CENTER);

            add(aux, BorderLayout.CENTER);

            panelExtension = new PanelExtension(this);
            add(panelExtension, BorderLayout.SOUTH);

            // Center the window
            setLocationRelativeTo(null);

            updateTaxa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Taxonomic Tree",
                                          JOptionPane.ERROR_MESSAGE);
            System.exit(ERROR);
        }
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the list of shown taxa
     */
    private void updateTaxa() {
        panelTaxa.refreshTree(tree.getTaxonRoot());
        panelExtension.updateTree(tree.getNumTaxa(), tree.getNumLivingBeings());
    }

    /**
     * Shows the list of taxa in pre-order.
     */
    public void showTaxaPreOrder() {
        ArrayList taxa = tree.getTaxaPreOrder();
        ShowTaxaDialogue dialogue = new ShowTaxaDialogue(taxa);
        dialogue.setLocationRelativeTo(this);
        dialogue.setVisible(true);
    }

    /**
     * Shows the list of taxa in post-order.
     */
    public void showTaxaPostOrder() {
        ArrayList taxa = tree.getTaxaPostOrder();
        ShowTaxaDialogue dialogue = new ShowTaxaDialogue(taxa);
        dialogue.setLocationRelativeTo(this);
        dialogue.setVisible(true);
    }

    /**
     * Updates the list of living beings with the living beings of the given taxon.
     *
     * @param pTaxon Taxon in the taxonomic tree. pTaxon != null
     */
    public void updateLivingBeings(Taxon pTaxon) {
        ArrayList seres = tree.getLivingBeings(pTaxon.getLevel(), pTaxon.getName());
        livingBeingsPanel.refresh(seres);
    }

    /**
     * Adds a new taxon to the taxonomic tree.
     *
     * @param pNameOfFather Name of the taxon's father to be added to the tree.
     *                      pNameOfFather != null
     *                      pNameOfFather != "".
     * @param pLevel        Level of taxon to be added. pLevel belongs to { Taxon.LUCA,
     *                      Taxon.DOMAIN, Taxon.KINGDOM, Taxon.PHYLUM, Taxon.CLASS, Taxon.ORDER,
     *                      Taxon.FAMILY, Taxon.GENUS, Taxon.SPECIES}.
     * @param pTaxonName    Name of the taxon to be added.
     *                      pTaxonName !=null
     *                      pTaxonName != "".
     */
    public void addTaxon(String pNameOfFather, int pLevel, String pTaxonName) {
        try {
            tree.addTaxon(pNameOfFather, pLevel, pTaxonName);
            updateTaxa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Add taxon",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Eliminates the taxon with the name and level given from the taxonomic tree.
     *
     * @param pLevel     Level of taxon to be added. pLevel belongs to { Taxon.LUCA,
     *                   Taxon.DOMAIN, Taxon.KINGDOM, Taxon.PHYLUM, Taxon.CLASS, Taxon.ORDER,
     *                   Taxon.FAMILY, Taxon.GENUS, Taxon.SPECIES}.
     * @param pTaxonName Name of the taxon to be added.
     *                   pTaxonName !=null
     *                   pTaxonName != "".
     */
    public void eliminateTaxon(int pLevel, String pTaxonName) {
        tree.eliminateTaxon(pLevel, pTaxonName);
        updateTaxa();
    }

    /**
     * Adds a new living being to the taxonomic tree. <br>
     * <b> pre: </b> El taxón al que se va a gregar el living being es of level Species.
     *
     * @param pTaxonName       Name of the taxon to be added.
     *                         pTaxonName != null
     *                         pTaxonName != "".
     * @param pCommonName      Common name of the living thing.
     *                         pCommonName != null
     *                         pCommonName != "".
     * @param pScientificName  Scientific name of the living being.
     *                         pScientificName != null
     *                         pScientificName != "".
     * @param pCharacteristics Characteristics of the living being.
     *                         pCharacteristics != null
     *                         pCharacteristics != "".
     * @param pImage           Image path of the living being.
     *                         pImage !=null
     *                         pImage != "".
     */
    public void addLivingBeing(String pTaxonName, String pCommonName, String pScientificName,
                               String pCharacteristics, String pImage) {
        try {
            tree.addLivingBeing(pTaxonName, pCommonName, pScientificName, pCharacteristics, pImage);
            updateTaxa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Add living being",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Returns the list of name of the taxa who are in the level given by the parameter.
     *
     * @param pLevel Level of taxon to be added. pLevel belongs to { Taxon.LUCA,
     *               Taxon.DOMAIN, Taxon.KINGDOM, Taxon.PHYLUM, Taxon.CLASS, Taxon.ORDER,
     *               Taxon.FAMILY, Taxon.GENUS, Taxon.SPECIES}.
     * @return Lists with the names of taxa in the level.
     */
    public ArrayList<String> getTaxaLevel(int pLevel) {
        return tree.getTaxaLevel(pLevel);
    }

    /**
     * Method called when the main window is closed. <br>
     * Before closing the application, the state of the program is saved. <br>
     * If there is an error, a message is shown informing the user.
     */
    public void dispose() {
        try {
            tree.save(FILE_PATH);
            super.dispose();
        } catch (Exception e) {
            setVisible(true);
            int response = JOptionPane.showConfirmDialog(this,
                                                         e.getMessage() + "\n ¿Are you sure you "
                                                                 + "want to close the program "
                                                                 + "without saving?",
                                                         "Taxonomic Tree",
                                                         JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                super.dispose();
            }
        }
    }

    // -----------------------------------------------------------------
    // Extension Points
    // -----------------------------------------------------------------

    /**
     * Method for extension 1.
     */
    public void reqFuncOption1() {
        String result = tree.method1();
        JOptionPane.showMessageDialog(this, result, "Response", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method for extension 2.
     */
    public void reqFuncOption2() {
        String result = tree.method2();
        JOptionPane.showMessageDialog(this, result, "Response", JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * This method executes the application, creating a new user interface.
     *
     * @param pArgs Arguments of the application.
     *              pArgs != null.
     */
    public static void main(String[] pArgs) {
        // Unifies the userInterface for Mac & Windows.
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        TaxonomicTreeInterface userInterface = new TaxonomicTreeInterface();
        userInterface.setVisible(true);
    }
}
