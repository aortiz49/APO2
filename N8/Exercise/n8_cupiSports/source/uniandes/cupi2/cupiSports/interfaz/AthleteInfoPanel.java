/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiSports
 * Autor: Equipo Cupi2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiSports.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiSports.world.Athlete;

/**
 * Panel para para visualizar la informaci�n de un athlete.
 */
public class AthleteInfoPanel extends JPanel {
    // -----------------------------------------------------------------
    // Attributes de interfaz
    // -----------------------------------------------------------------

    /**
     * Label con el name of the athlete.
     */
    private JLabel lblNameAthlete;

    /**
     * Campo de texto para el name of the athlete.
     */
    private JTextField txtNameAthlete;

    /**
     * Label con la age of the athlete.
     */
    private JLabel lblAge;

    /**
     * Campo de texto con la age of the athlete.
     */
    private JTextField txtAge;

    /**
     * Label con el lugar de residencia of the athlete.
     */
    private JLabel lblPlaceOfResidency;

    /**
     * Campo de texto para el lugar de residencia of the athlete.
     */
    private JTextField txtPlaceOfResidency;

    /**
     * Label Para la cantidad de trofeos ganados.
     */
    private JLabel lblTrophiesWon;

    /**
     * Campo de texto con los trofeos ganados.
     */
    private JTextField txtTrophiesWon;

    /**
     * Panel con la imagen of the athlete.
     */
    private JPanel panelImage;

    /**
     * Label con la imagen of the athlete.
     */
    private JLabel lblImage;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     */
    public AthleteInfoPanel() {
        setLayout(new java.awt.BorderLayout());
        setBorder(new TitledBorder("Athlete"));

        setPreferredSize(new Dimension(350, 150));
        // Panel Image
        panelImage = new JPanel();
        panelImage.setPreferredSize(new Dimension(110, 195));
        panelImage.setLayout(new BorderLayout());
        add(panelImage, java.awt.BorderLayout.NORTH);

        lblImage = new JLabel();
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        lblImage.setVerticalAlignment(JLabel.CENTER);
        panelImage.add(lblImage, BorderLayout.CENTER);

        JPanel granPanel = new JPanel();
        granPanel.setPreferredSize(new Dimension(400, 195));
        granPanel.setLayout(new BorderLayout());
        add(granPanel, java.awt.BorderLayout.CENTER);
        JPanel panelInformacion = new JPanel();
        GridLayout layout = new GridLayout(4, 2);
        layout.setVgap(8);
        layout.setHgap(8);
        panelInformacion.setLayout(layout);

        lblNameAthlete = new JLabel("Name:");
        panelInformacion.add(lblNameAthlete);

        txtNameAthlete = new JTextField();
        txtNameAthlete.setEditable(false);
        panelInformacion.add(txtNameAthlete);

        lblAge = new JLabel("Age:");
        panelInformacion.add(lblAge);

        txtAge = new JTextField();
        txtAge.setEditable(false);
        panelInformacion.add(txtAge);

        lblPlaceOfResidency = new JLabel("Lugar de residencia:");
        panelInformacion.add(lblPlaceOfResidency);

        txtPlaceOfResidency = new JTextField();
        txtPlaceOfResidency.setEditable(false);
        panelInformacion.add(txtPlaceOfResidency);

        lblTrophiesWon = new JLabel("Trophies ganados:");
        panelInformacion.add(lblTrophiesWon);

        txtTrophiesWon = new JTextField();
        txtTrophiesWon.setEditable(false);
        panelInformacion.add(txtTrophiesWon);

        granPanel.add(panelInformacion, BorderLayout.NORTH);

    }

    /**
     * Actualiza la informaci�n presentada por el panel.
     * @param pAthlete Athlete del cual se va a obtener la informaci�n. pAthlete != null.
     */
    public void updateInfo(Athlete pAthlete) {
        if (pAthlete != null) {
            lblImage.setIcon(new ImageIcon(pAthlete.getImagePath()));
            txtNameAthlete.setText(pAthlete.getName());
            txtAge.setText(String.valueOf(pAthlete.getAge()));
            txtTrophiesWon.setText(String.valueOf(pAthlete.getAmountOfTrophies()));
            txtPlaceOfResidency.setText(pAthlete.getPlaceOfResidency());

        }
        else {
            lblImage.setIcon(new ImageIcon(""));
            txtNameAthlete.setText("");
            txtAge.setText("");
            txtTrophiesWon.setText("");
            txtPlaceOfResidency.setText("");
        }

    }

}
