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
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.cupiSports.world.ElementExistsException;
import uniandes.cupi2.cupiSports.world.FileFormatException;
import uniandes.cupi2.cupiSports.world.PersistenceException;
import uniandes.cupi2.cupiSports.world.*;


/**
 * Principal window of the application.
 */
public class CupiSportsInterface extends JFrame {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * File where the information will be stored.
     */
    private final static String DATA = "./data/cupiSports.data";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal class in the world
     */
    private CupiSports cupiSports;

    // -----------------------------------------------------------------
    // Attributes of the interface
    // -----------------------------------------------------------------

    /**
     * Panel containing the extensions.
     */
    private PanelExtension panelExtension;

    /**
     * Panel containing the banner image.
     */
    private ImagePanel panelImage;

    /**
     * Panel containing the sports.
     */
    private SportsPanel panelSports;

    /**
     * Panel containing the options.
     */
    private OptionsPanel panelOptiones;

    /**
     * Panel containing the selected sport's information.
     */
    private SportInfoPanel panelInfoSport;

    /**
     * Panel containing the selected athlete's information.
     */
    private AthleteInfoPanel panelInfoAthlete;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor of the principal window of the application.
     */
    public CupiSportsInterface() {
        try {
            cupiSports = new CupiSports(DATA);

            getContentPane().setLayout(new BorderLayout());
            setSize(800, 700);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setTitle("CupiSports");

            // Creaci�n de los paneles aqu�
            JPanel panelNorte = new JPanel(new BorderLayout());
            panelImage = new ImagePanel();
            panelNorte.add(panelImage, BorderLayout.NORTH);
            add(panelNorte, BorderLayout.NORTH);

            JPanel panelCentral = new JPanel(new BorderLayout());
            panelSports = new SportsPanel(this);
            panelCentral.add(panelSports, BorderLayout.NORTH);
            panelInfoSport = new SportInfoPanel(this);
            panelCentral.add(panelInfoSport, BorderLayout.CENTER);
            panelInfoAthlete = new AthleteInfoPanel();
            panelCentral.add(panelInfoAthlete, BorderLayout.EAST);
            add(panelCentral, BorderLayout.CENTER);

            panelExtension = new PanelExtension(this);
            add(panelExtension, BorderLayout.SOUTH);

            panelOptiones = new OptionsPanel(this);
            add(panelOptiones, BorderLayout.WEST);

            setLocationRelativeTo(null);

            updateSportsList();
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "CupiSports",
                                          JOptionPane.ERROR_MESSAGE);
        }

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the panel with the available sports.
     */
    public void updateSportsList() {
        panelSports.updateSports(cupiSports.getSports());
    }

    /**
     * Updates the information seen for the sport.
     *
     * @param pSport Sport whose information is being displayed.
     *               pSport != null.
     */
    public void updateInfoSport(Sport pSport) {
        panelInfoSport.updateInfo(pSport);
    }

    /**
     * Updates the information seen for the athlete.
     *
     * @param pAthlete Athlete whose information is being displayed.
     *                 pAthlete != null.
     */
    public void updateInfoAthlete(Athlete pAthlete) {
        panelInfoAthlete.updateInfo(pAthlete);
    }

    /**
     * Displays the dialog to add a sport.
     */
    public void displayAddSportDialog() {
        AddSportDialog dialog = new AddSportDialog(this);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    /**
     * Adds a sport.
     *
     * @param pNameSport                  Name of the sport.
     *                                    pNameSport != null && pNameSport!= "".
     * @param pRegulatoryEntity           Regulatory entity of the sport.
     *                                    pRegulatoryEntity != null && pRegulatoryEntity != "".
     * @param pNumberOfRegisteredAthletes Number of registered athletes.
     *                                    pNumberOfRegisteredAthletes > 0.
     * @param pImagePath                  Image path of the sport.
     *                                    pImagePath != null && pImagePath != "".
     * @throws ElementExistsException Si ya exists a sport con el name dado.
     */
    public void addSport(String pNameSport, String pRegulatoryEntity,
                         int pNumberOfRegisteredAthletes, String pImagePath)
            throws ElementExistsException {
        cupiSports.addSport(pNameSport, pRegulatoryEntity, pNumberOfRegisteredAthletes, pImagePath);
        updateSportsList();
    }

    /**
     * Shows the dialog to add an outstanding athlete.
     */
    public void displayAddAthleteDialog() {
        if (panelSports.getSportSeleccionado() == null) {
            JOptionPane.showMessageDialog(this, "You haven't selected any sport.", "Add athlete",
                                          JOptionPane.ERROR_MESSAGE);
        }
        else {
            AddAthleteDialog dialog = new AddAthleteDialog(this);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        }
    }

    /**
     * Adds an outstanding athlete to a sport with the information given by the parameter. <br>
     * <b>post: </b> The outstanding athlete was added to the corresponding sport.
     *
     * @param pNameAthlete      Name of the outstanding athlete.
     *                          pNameAthlete !=null &&  pNameAthlete != "".
     * @param pAge              Age of the athlete. pAge > 0.
     * @param pPlaceOfResidency Athlete's place of residence.
     *                          pPlaceOfResidency != null && pPlaceOfResidency != "".
     * @param pAmountOfTrophies Amount of trophies won by the athlete.
     *                          pAmountOfTrophies >= 0.
     * @param pImagePathAthlete Image path of the athlete.
     *                          pImagePathAthlete != null && pImagePathAthlete != "".
     * @throws ElementExistsException Throws an exception if there already exists an athlete
     *                                in the sport with the given name.
     */
    public void addOutstandingAthlete(String pNameAthlete, int pAge, String pPlaceOfResidency,
                                      int pAmountOfTrophies, String pImagePathAthlete)
            throws ElementExistsException {
        String nameSport = panelSports.getSportSeleccionado().getName();
        cupiSports.addOutstandingAthlete(nameSport, pNameAthlete, pAge, pPlaceOfResidency,
                                         pAmountOfTrophies, pImagePathAthlete);
        updateInfoSport(panelSports.getSportSeleccionado());
    }

    /**
     * Eliminates the selected sport.
     */
    public void deleteSport() {
        if (panelSports.getSportSeleccionado() == null) {
            JOptionPane
                    .showMessageDialog(this, "You haven't selected any sport.", "Eliminate sport",
                                       JOptionPane.ERROR_MESSAGE);
        }
        else {
            int confirmation = JOptionPane.showConfirmDialog(this, "Confirm that you want to "
                                                                     + "eliminate " + panelSports.getSportSeleccionado().toString() + "?",
                                                             "Eliminate sport",
                                                             JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                cupiSports.deleteSport(panelSports.getSportSeleccionado().getName());
                updateSportsList();
            }
        }

    }

    /**
     * Eliminates the selected athlete.
     */
    public void deleteAthlete() {
        if (panelSports.getSportSeleccionado() == null) {
            JOptionPane
                    .showMessageDialog(this, "You haven't selected any sport.", "Eliminate athlete",
                                       JOptionPane.ERROR_MESSAGE);
        }
        else if (panelInfoSport.getAthleteSeleccionado() == null) {
            JOptionPane.showMessageDialog(this, "You haven't selected an athlete of the sport.",
                                          "Eliminate athlete", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int confirmation = JOptionPane.showConfirmDialog(this,
                                                             "Confirm that you want to delete "
                                                                     + "athlete " + panelInfoSport
                                                                     .getAthleteSeleccionado()
                                                                     .toString() + "?",
                                                             "Eliminate athlete",
                                                             JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                cupiSports.eliminateOutstandingAthlete(panelSports.getSportSeleccionado().getName(),
                                                       panelInfoSport.getAthleteSeleccionado()
                                                                     .getName());
                updateInfoSport(panelSports.getSportSeleccionado());
            }
        }

    }

    /**
     * Updates the information of the athletes from a text file.
     */
    public void updateAthletesInformation() {
        JFileChooser fc = new JFileChooser("./data/updatePlayerInformation");
        fc.setDialogTitle("Update athlete information");
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (file != null) {
                try {
                    cupiSports.updateAthletesInformation(file);
                    JOptionPane.showMessageDialog(this, "The athletes information was updated.",
                                                  "Update information",
                                                  JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this,
                                                  "There was a problem reading the file:\n" + e
                                                          .getMessage() + ".", "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                } catch (FileFormatException e) {
                    JOptionPane.showMessageDialog(this,
                                                  "There was a problem with the file type:\n" + e
                                                          .getMessage(), "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }

        }

    }

    /**
     * Writes the trophy report of outstanding players.
     *
     * @param pFilePath Path to the fle.
     *                  pFilePath != null && pFilePath != "".
     */
    public void generateTrophyReport(String pFilePath) {
        try {
            cupiSports.generateTrophyReport(pFilePath);
            JOptionPane.showMessageDialog(this, "The trophy report was generated successfully.",
                                          "Generate trophy report",
                                          JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Generate trophy report",
                                          JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * This method is in charge of saving the information right before exiting the application.
     * If there is an exception during the serialization process, the method will inform the user
     * of the error that occurred and ask if he/she wishes to exit the application without saving.
     */
    public void dispose() {
        try {
            cupiSports.saveState(DATA);
            super.dispose();
        } catch (Exception e) {
            setVisible(true);
            int response = JOptionPane.showConfirmDialog(this,
                                                         "There were problems saving the sports "
                                                                 + "system" + ":\n" + e.getMessage()
                                                                 + "\nDo youw ant to close "
                                                                 + "without saving??", "Error",
                                                         JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                super.dispose();
            }
        }
    }

    // -----------------------------------------------------------------
    // Extension points
    // -----------------------------------------------------------------

    /**
     * Method for extension  1.
     */
    public void reqFuncOption1() {
        String result = cupiSports.method1();
        JOptionPane.showMessageDialog(this, result, "Response", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method for extension  2.
     */
    public void reqFuncOption2() {
        String result = cupiSports.method2();
        JOptionPane.showMessageDialog(this, result, "Response", JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * This method executes the application, creating a new interface.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        CupiSportsInterface userInterface = new CupiSportsInterface();
        userInterface.setVisible(true);
    }

}