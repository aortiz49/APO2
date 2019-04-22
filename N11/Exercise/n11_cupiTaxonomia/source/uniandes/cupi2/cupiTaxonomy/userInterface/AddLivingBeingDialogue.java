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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiTaxonomy.world.Taxon;

/**
 * Dialogue to add a new living being.
 */
@SuppressWarnings({"rawtypes", "serial", "unchecked"})
public class AddLivingBeingDialogue extends JDialog implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant that represents the add command.
     */
    private final static String ADD = "Add";

    /**
     * Constant that represents the search command.
     */
    private final static String SEARCH = "Search";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal window of the application.
     */
    private TaxonomicTreeInterface principal;

    // -----------------------------------------------------------------
    // User interface attributes
    // -----------------------------------------------------------------

    /**
     * ComboBox with the species in the taxonomic tree.
     */
    private JComboBox comboSpecies;

    /**
     * Text field with the common name of the living being.
     */
    private JTextField txtCommonName;

    /**
     * Text field with the scientific nme of the living being.
     */
    private JTextField txtScientificName;

    /**
     * Text area with the characteristics of the living being.
     */
    private JTextArea areaCharacteristics;

    /**
     * Scroll panel containing the characteristics of the living being.
     */
    private JScrollPane scrollCharacteristics;

    /**
     * Text field with the image of the living thing.
     */
    private JTextField txtImage;

    /**
     * Add button.
     */
    private JButton btnAdd;

    /**
     * Image search button.
     */
    private JButton btnSearch;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructs a dialog to add a new living being.
     * <b> post: </b> A dialog is created with all its graphical elements.
     *
     * @param pPrincipal Principal window of the application.
     *                   pPrincipal != null.
     */
    public AddLivingBeingDialogue(TaxonomicTreeInterface pPrincipal) {
        principal = pPrincipal;

        setLayout(new BorderLayout());
        setSize(320, 250);
        setModal(true);
        setLocationRelativeTo(principal);
        setTitle("Add living being");
        setResizable(true);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        panel1.add(new JLabel(" Species: "), gbc);

        gbc.gridy = 1;
        panel1.add(new JLabel(" Common name: "), gbc);

        gbc.gridy = 2;
        panel1.add(new JLabel(" Scientific name: "), gbc);

        gbc.gridy = 3;
        areaCharacteristics = new JTextArea();
        areaCharacteristics.setLineWrap(true);

        gbc.gridwidth = 3;
        scrollCharacteristics = new JScrollPane(areaCharacteristics);
        scrollCharacteristics.setSize(new Dimension(0, 80));
        scrollCharacteristics.setPreferredSize(new Dimension(0, 80));
        scrollCharacteristics.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCharacteristics.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollCharacteristics.setBorder(new TitledBorder(" Characteristics: "));
        panel1.add(scrollCharacteristics, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel1.add(new JLabel(" Image: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        comboSpecies = new JComboBox(principal.getTaxaLevel(Taxon.SPECIES).toArray());
        panel1.add(comboSpecies, gbc);

        gbc.gridy = 1;
        txtCommonName = new JTextField();
        panel1.add(txtCommonName, gbc);

        gbc.gridy = 2;
        txtScientificName = new JTextField();
        panel1.add(txtScientificName, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0.9;
        txtImage = new JTextField();
        txtImage.setEditable(false);
        panel1.add(txtImage, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 0.1;
        btnSearch = new JButton(SEARCH);
        btnSearch.setActionCommand(SEARCH);
        btnSearch.addActionListener(this);
        panel1.add(btnSearch, gbc);

        add(panel1, BorderLayout.NORTH);

        btnAdd = new JButton(ADD);
        btnAdd.setActionCommand(ADD);
        btnAdd.addActionListener(this);

        add(btnAdd, BorderLayout.SOUTH);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Button events manager.
     *
     * @param pEvent Action generated by the event.
     *               pEvent != null.
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();
        if (command.equals(ADD)) {
            String species = (String) comboSpecies.getSelectedItem();
            String name = txtCommonName.getText();
            String sciName = txtScientificName.getText();
            String characteristics = areaCharacteristics.getText();
            String image = txtImage.getText();

            if (species != null && !species.isEmpty() && name != null && !name.isEmpty()
                    && sciName != null && !sciName.isEmpty() && characteristics != null
                    && !characteristics.isEmpty() && image != null && !image.isEmpty()) {
                principal.addLivingBeing(species, name, sciName, characteristics, image);
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(this,
                                              "Please enter all of the information for the "
                                                      + "living being.", "Add living being",
                                              JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (command.equals(SEARCH)) {
            JFileChooser fileChooser = new JFileChooser("./data/images");
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                txtImage.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }
}