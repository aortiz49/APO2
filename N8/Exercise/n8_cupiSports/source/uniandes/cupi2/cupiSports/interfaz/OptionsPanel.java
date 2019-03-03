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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel in charge of displaying the options buttons.
 */
public class OptionsPanel extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant to add a sport.
     */
    public static final String NEW_SPORT = "Add sport";

    /**
     * Constant to delete a sport.
     */
    public static final String ELIMINATE_SPORT = "Eliminate sport";

    /**
     * Constant to add a athlete.
     */
    public static final String NEW_ATHLETE = "Add athlete";

    /**
     * Constant to eliminate athlete.
     */
    public static final String ELIMINATE_ATHLETE = "Eliminate athlete";

    /**
     * Constant to generate report.
     */
    public static final String GENERATE_REPORT = "Generate trophy report";

    /**
     * Constant to import data.
     */
    public static final String IMPORT_DATA = "Import data";

    /**
     * Constant to define image directory.
     */
    public static final String DIRECTORY = "./data/images/";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * Principal interface of the application
     */
    private CupiSportsInterface principal;

    // -----------------------------------------------------------------
    // Attributes of the interface
    // -----------------------------------------------------------------

    /**
     * Button to add a new sport.
     */
    private JButton btnNewSport;

    /**
     * Button to delete a sport.
     */
    private JButton btnEliminateSport;

    /**
     * Button to add a new athlete.
     */
    private JButton btnNewAthlete;

    /**
     * Button to delete a athlete.
     */
    private JButton btnEliminateAthlete;

    /**
     * Button to generate report.
     */
    private JButton btnGenerateReport;

    /**
     * Button to leer a file.
     */
    private JButton btnReadFile;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the panel.
     *
     * @param pPrincipal Principal interface of the application pPrincipal != null.
     */
    public OptionsPanel(CupiSportsInterface pPrincipal) {
        principal = pPrincipal;
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 4));
        setBorder(new TitledBorder("Options"));
        setPreferredSize(new Dimension(80, 600));

        btnNewSport = new JButton(new ImageIcon("./data/images/addSport.png"));
        add(btnNewSport);
        btnNewSport.addActionListener(this);
        btnNewSport.setActionCommand(NEW_SPORT);
        btnNewSport.setToolTipText(NEW_SPORT);
        btnNewSport.setPreferredSize(new Dimension(60, 60));

        btnEliminateSport = new JButton(new ImageIcon(DIRECTORY + "deleteSport.png"));
        add(btnEliminateSport);
        btnEliminateSport.addActionListener(this);
        btnEliminateSport.setActionCommand(ELIMINATE_SPORT);
        btnEliminateSport.setToolTipText(ELIMINATE_SPORT);
        btnEliminateSport.setPreferredSize(new Dimension(60, 60));

        btnNewAthlete = new JButton(new ImageIcon(DIRECTORY + "addAthlete.png"));
        add(btnNewAthlete);
        btnNewAthlete.addActionListener(this);
        btnNewAthlete.setActionCommand(NEW_ATHLETE);
        btnNewAthlete.setToolTipText(NEW_ATHLETE);
        btnNewAthlete.setPreferredSize(new Dimension(60, 60));

        btnEliminateAthlete = new JButton(new ImageIcon(DIRECTORY + "deleteAthlete.png"));
        add(btnEliminateAthlete);
        btnEliminateAthlete.addActionListener(this);
        btnEliminateAthlete.setActionCommand(ELIMINATE_ATHLETE);
        btnEliminateAthlete.setToolTipText(ELIMINATE_ATHLETE);
        btnEliminateAthlete.setPreferredSize(new Dimension(60, 60));

        btnGenerateReport = new JButton(new ImageIcon(DIRECTORY + "Reporte.png"));
        add(btnGenerateReport);
        btnGenerateReport.addActionListener(this);
        btnGenerateReport.setActionCommand(GENERATE_REPORT);
        btnGenerateReport.setToolTipText(GENERATE_REPORT);
        btnGenerateReport.setPreferredSize(new Dimension(60, 60));

        btnReadFile = new JButton(new ImageIcon(DIRECTORY + "loadData.png"));
        add(btnReadFile);
        btnReadFile.addActionListener(this);
        btnReadFile.setActionCommand(IMPORT_DATA);
        btnReadFile.setToolTipText(IMPORT_DATA);
        btnReadFile.setPreferredSize(new Dimension(60, 60));
    }
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * M�todo que se encarga de tomar el control cuando se hace click en alguno de los botones.
     *
     * @param event Evento asociado al click del bot�n.
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(NEW_SPORT)) {
            principal.displayAddSportDialog();
        }
        else if (event.getActionCommand().equals(ELIMINATE_SPORT)) {
            principal.deleteSport();
        }
        else if (event.getActionCommand().equals(NEW_ATHLETE)) {
            principal.displayAddAthleteDialog();
        }
        else if (event.getActionCommand().equals(ELIMINATE_ATHLETE)) {
            principal.deleteAthlete();
        }
        else if (event.getActionCommand().equals(GENERATE_REPORT)) {
            principal.generateTrophyReport("./data/TrophyReport.txt");
        }
        else if (event.getActionCommand().equals(IMPORT_DATA)) {
            principal.updateAthletesInformation();
        }
    }
}
