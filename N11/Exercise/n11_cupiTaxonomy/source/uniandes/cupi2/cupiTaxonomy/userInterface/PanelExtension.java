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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Panel to manage the extensions to the project.
 */
@SuppressWarnings({"serial"})
public class PanelExtension extends JPanel implements ActionListener {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant that represents the command to add a taxon.
     */
    private static final String ADD_TAXON = "Add taxon";

    /**
     * Constant that represents the command to add a new living being.
     */
    private final static String ADD_SER = "Add living being";

    /**
     * Constant that represents the command for option 1.
     */
    private static final String OPTION_1 = "OPTION_1";

    /**
     * Constant that represents the command for option 2.
     */
    private static final String OPTION_2 = "OPTION_2";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal window of the application.
     */
    private TaxonomicTreeInterface principal;

    // -----------------------------------------------------------------
    // Attributes of userInterface
    // -----------------------------------------------------------------

    /**
     * Button to add a taxon.
     */
    private JButton btnAddTaxon;

    /**
     * Button to add a living being.
     */
    private JButton btnAddLivingBeing;

    /**
     * Text field with the number of taxa.
     */
    private JTextField txtTaxa;

    /**
     * Text field with the number of living things.
     */
    private JTextField txtLivingBeings;

    /**
     * Button for Option 1.
     */
    private JButton btnOption1;

    /**
     * Button for Option 2.
     */
    private JButton btnOption2;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor of the panel of extension.
     * <b> post: </b> The panel was created with all its graphical components.
     *
     * @param pPrincipal Principal window of the application. pPrincipal != null.
     */
    public PanelExtension(TaxonomicTreeInterface pPrincipal) {
        principal = pPrincipal;

        setBorder(new TitledBorder("Options"));
        setLayout(new GridLayout(2, 4));

        JLabel lbl1 = new JLabel("Number of taxa: ");
        lbl1.setHorizontalAlignment(JLabel.CENTER);
        add(lbl1);

        txtTaxa = new JTextField("0");
        txtTaxa.setEditable(false);
        txtTaxa.setHorizontalAlignment(JTextField.CENTER);
        add(txtTaxa);

        JLabel lbl2 = new JLabel("Number of living beings: ");
        lbl2.setHorizontalAlignment(JLabel.CENTER);
        add(lbl2);

        txtLivingBeings = new JTextField("0");
        txtLivingBeings.setEditable(false);
        txtLivingBeings.setHorizontalAlignment(JTextField.CENTER);
        add(txtLivingBeings);

        // Button to add taxon
        btnAddTaxon = new JButton(ADD_TAXON);
        btnAddTaxon.setActionCommand(ADD_TAXON);
        btnAddTaxon.addActionListener(this);
        add(btnAddTaxon);

        // Button to add living being
        btnAddLivingBeing = new JButton(ADD_SER);
        btnAddLivingBeing.setActionCommand(ADD_SER);
        btnAddLivingBeing.addActionListener(this);
        add(btnAddLivingBeing);

        // Button for option 1
        btnOption1 = new JButton("Option 1");
        btnOption1.setActionCommand(OPTION_1);
        btnOption1.addActionListener(this);
        add(btnOption1);

        // Button for option 2
        btnOption2 = new JButton("Option 2");
        btnOption2.setActionCommand(OPTION_2);
        btnOption2.addActionListener(this);
        add(btnOption2);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the text fields with information from the taxonomic tree.
     *
     * @param pNumTaxa  Number of taxa in the tree.
     *                  pNumTaxa >= 0.
     * @param pNumSeres Number of living beings in the tree.
     *                  pNumSeres >= 0.
     */
    public void updateTree(int pNumTaxa, int pNumSeres) {
        txtTaxa.setText("" + pNumTaxa);
        txtLivingBeings.setText("" + pNumSeres);
    }

    /**
     * Button events manager.
     *
     * @param pEvent Action generated by the event. pEvent != null.
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();
        if (OPTION_1.equals(command)) {
            principal.reqFuncOption1();
        }
        else if (OPTION_2.equals(command)) {
            principal.reqFuncOption2();
        }
        else if (command.equals(ADD_TAXON)) {
            String[] levels =
                    new String[]{"1. Domain", "2. Kingdom", "3. Phylum", "4. Class", "5. Order",
                            "6. Family", "7. Genus", "8. Species"};
            String level = (String) JOptionPane
                    .showInputDialog(principal, "Level of the taxon:", "Add taxon",
                                     JOptionPane.INFORMATION_MESSAGE, null, levels, "Domain");

            if (level != null) {
                AddTaxonDialogue dialogue = new AddTaxonDialogue(principal, level);
                dialogue.setVisible(true);
            }
        }
        else if (command.equals(ADD_SER)) {
            AddLivingBeingDialogue dialogue = new AddLivingBeingDialogue(principal);
            dialogue.setVisible(true);
        }
    }

}
