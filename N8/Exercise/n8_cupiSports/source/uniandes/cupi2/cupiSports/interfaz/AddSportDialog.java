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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiSports.world.ElementExistsException;

/**
 * Dialog to add a sport.
 */
public class AddSportDialog extends JDialog implements ActionListener {
    // -----------------------------------------------
    // Constants
    // -----------------------------------------------

    /**
     * Constant to add a sport.
     */
    private final static String ADD = "Add a sport";

    /**
     * Constant to browse files.
     */
    private final static String BROWSE = "Browse";

    // -----------------------------------------------
    // Attributes
    // -----------------------------------------------

    /**
     * Label for the sport name.
     */
    private JLabel lblName;

    /**
     * Label for regulatory entity.
     */
    private JLabel lblRegulatoryEntity;

    /**
     * Label for the registered athletes.
     */
    private JLabel lblRegisteredAthletes;

    /**
     * Label for the sport image.
     */
    private JLabel lblImage;

    /**
     * Text field for sport name.
     */
    private JTextField txtName;

    /**
     * Text field for regulatory entity.
     */
    private JTextField txtRegulatoryEntity;

    /**
     * Text field for registered athletes.
     */
    private JTextField txtRegisteredAthletes;

    /**
     * Text field for sport image.
     */
    private JTextField txtImage;

    /**
     * Panel containing the sport information.
     */
    private JPanel panelInfo;

    /**
     * Button to add the sport.
     */
    private JButton btnAdd;

    /**
     * Button to enter the sport image path.
     */
    private JButton btnBrowse;

    /**
     * Principal window of the application.
     */
    private CupiSportsInterface principal;

    // -----------------------------------------------
    // Methods
    // -----------------------------------------------

    /**
     * Creates the dialog to add a new sport to the system.
     *
     * @param pPrincipal Principal window of the application. pPrincipal != null.
     */
    public AddSportDialog(CupiSportsInterface pPrincipal) {
        super(pPrincipal, true);

        principal = pPrincipal;
        setTitle("Add a sport");
        setLayout(new java.awt.BorderLayout());

        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(500, 180));

        panelInfo.setBorder(new TitledBorder("Information"));
        GridLayout layout = new GridLayout(4, 2, -175, 30);
        layout.setVgap(10);
        panelInfo.setLayout(layout);

        lblName = new JLabel("Name: ");
        panelInfo.add(lblName);
        txtName = new JTextField();
        panelInfo.add(txtName);

        lblRegulatoryEntity = new JLabel("Regulatory entity: ");
        panelInfo.add(lblRegulatoryEntity);
        txtRegulatoryEntity = new JTextField();
        panelInfo.add(txtRegulatoryEntity);

        lblRegisteredAthletes = new JLabel("Registered athletes: ");
        panelInfo.add(lblRegisteredAthletes);
        txtRegisteredAthletes = new JTextField();
        panelInfo.add(txtRegisteredAthletes);

        lblImage = new JLabel("Image: ");
        panelInfo.add(lblImage);

        BorderLayout border = new BorderLayout();
        border.setHgap(5);
        border.setVgap(5);

        JPanel panelImagePath = new JPanel(border);
        txtImage = new JTextField();
        panelImagePath.add(txtImage, BorderLayout.CENTER);

        btnBrowse = new JButton(BROWSE);
        btnBrowse.setActionCommand(BROWSE);
        btnBrowse.addActionListener(this);
        panelImagePath.add(btnBrowse, BorderLayout.EAST);

        panelInfo.add(panelImagePath);

        add(panelInfo, BorderLayout.NORTH);

        btnAdd = new JButton(ADD);
        btnAdd.setActionCommand(ADD);
        btnAdd.addActionListener(this);
        add(btnAdd, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    /**
     * Method that collects the actions of the objects it is listening to.
     *
     * @param e Event realized.
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(ADD)) {
            String name = txtName.getText();
            String regulatoryEntity = txtRegulatoryEntity.getText();
            String image = txtImage.getText();
            String registeredAthletesStr = txtRegisteredAthletes.getText();

            if (name.equals("") || regulatoryEntity.equals("") || registeredAthletesStr.equals("")
                    || image.equals("")) {
                JOptionPane.showMessageDialog(this, "Incomplete data fields!", "Add a sport",
                                              JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int registeredAthletes = Integer.parseInt(registeredAthletesStr);
                if (registeredAthletes <= 0) {
                    JOptionPane.showMessageDialog(this, "Registered athletes must be a "
                            + "positive whole number! ", "Add a sport", JOptionPane.ERROR_MESSAGE);
                }

                else {
                    // If the number of registered athletes is correct, add the sport.
                    principal.addSport(name, regulatoryEntity, registeredAthletes, image);
                    dispose();
                }
            } catch (ElementExistsException e1) {
                JOptionPane.showMessageDialog(this,
                                              "A sport already exists with the name " + name + "!",
                                              "Add a sport", JOptionPane.ERROR_MESSAGE);

            } catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(this, "Registered athletes must be a number! ",
                                              "Add a sport", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getActionCommand().equals(BROWSE)) {
            JFileChooser fc = new JFileChooser("./data/images");
            fc.setDialogTitle("Apply image...");
            fc.setMultiSelectionEnabled(false);

            int result = fc.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String image = fc.getSelectedFile().getName();
                try {
                    Image variableImage = (ImageIO.read(new File("./data/images/" + image)));

                    if (variableImage != null) {
                        txtImage.setText("./data/images/" + image);
                    }
                    else {
                        JOptionPane
                                .showMessageDialog(this, "The selected file is not a valid image!",
                                                   "Add a sport", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Error when reading the image.", "Add a "
                                                          + "sport",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
}
