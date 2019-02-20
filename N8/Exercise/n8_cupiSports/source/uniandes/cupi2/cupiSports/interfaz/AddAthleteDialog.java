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
 * Dialogo para add un athlete.
 */
public class AddAthleteDialog extends JDialog implements ActionListener {
    // -----------------------------------------------
    // Constants
    // -----------------------------------------------

    /**
     * Constante add athlete.
     */
    private final static String AGREGAR = "Agregar athlete";

    /**
     * Constante examinar.
     */
    private final static String EXAMINAR = "Examinar";

    // -----------------------------------------------
    // Attributes
    // -----------------------------------------------

    /**
     * Etiqueta name.
     */
    private JLabel lblName;

    /**
     * Etiqueta age.
     */
    private JLabel lblAge;

    /**
     * Etiqueta lugar de residencia.
     */
    private JLabel lblPlaceOfResidency;

    /**
     * Etiqueta trofeos ganados.
     */
    private JLabel lblTrophiesGanados;

    /**
     * Etiqueta Imagen.
     */
    private JLabel labelImagen;

    /**
     * Campo de texto name.
     */
    private JTextField txtName;

    /**
     * Campo de texto age.
     */
    private JTextField txtAge;

    /**
     * Campo de texto lugar de residencia.
     */
    private JTextField txtPlaceOfResidency;

    /**
     * Campo de texto trofeos ganados.
     */
    private JTextField txtTrophiesGanados;

    /**
     * Texto con la ruta de la imagen.
     */
    private JTextField txtImagen;

    /**
     * Panel con la informaci�n of the athlete.
     */
    private JPanel panelInfo;

    /**
     * Bot�n para add athlete.
     */
    private JButton btnAgregar;

    /**
     * Bot�n para ingresar la ruta de la imagen.
     */
    private JButton btnExaminar;

    /**
     * Ventana principal de la aplicaci�n.
     */
    private CupiSportsInterface principal;

    // -----------------------------------------------
    // Methods
    // -----------------------------------------------

    /**
     * Crea el dialogo para add un athlete.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public AddAthleteDialog(CupiSportsInterface pPrincipal) {
        super(pPrincipal, true);

        principal = pPrincipal;
        setTitle("Agregar athlete");
        setLayout(new java.awt.BorderLayout());

        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(450, 200));

        panelInfo.setBorder(new TitledBorder("Informaci�n"));
        GridLayout layout = new GridLayout(5, 2, -175, 30);
        layout.setVgap(10);
        panelInfo.setLayout(layout);

        lblName = new JLabel("Name: ");
        panelInfo.add(lblName);
        txtName = new JTextField();
        panelInfo.add(txtName);

        lblAge = new JLabel("Age: ");
        panelInfo.add(lblAge);
        txtAge = new JTextField();
        panelInfo.add(txtAge);

        lblPlaceOfResidency = new JLabel("Lugar de residencia: ");
        panelInfo.add(lblPlaceOfResidency);
        txtPlaceOfResidency = new JTextField();
        panelInfo.add(txtPlaceOfResidency);

        lblTrophiesGanados = new JLabel("Trophies ganados: ");
        panelInfo.add(lblTrophiesGanados);
        txtTrophiesGanados = new JTextField();
        panelInfo.add(txtTrophiesGanados);

        labelImagen = new JLabel("Imagen: ");
        panelInfo.add(labelImagen);

        BorderLayout border = new BorderLayout();
        border.setHgap(5);
        border.setVgap(5);

        JPanel panelImagePath = new JPanel(border);
        txtImagen = new JTextField();
        panelImagePath.add(txtImagen, BorderLayout.CENTER);

        btnExaminar = new JButton(EXAMINAR);
        btnExaminar.setActionCommand(EXAMINAR);
        btnExaminar.addActionListener(this);
        panelImagePath.add(btnExaminar, BorderLayout.EAST);

        panelInfo.add(panelImagePath);

        add(panelInfo, BorderLayout.NORTH);

        btnAgregar = new JButton(AGREGAR);
        btnAgregar.setActionCommand(AGREGAR);
        btnAgregar.addActionListener(this);
        add(btnAgregar, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * M�todo que recoge las acciones sobre los objetos que est� escuchando.
     * @param e Evento que se realiz�.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(AGREGAR)) {
            String name = txtName.getText();
            String ageSt = txtAge.getText();
            String imagen = txtImagen.getText();
            String placeOfResidence = txtPlaceOfResidency.getText();
            String trofeosSt = txtTrophiesGanados.getText();

            if (name.equals("") || ageSt.equals("") || placeOfResidence.equals("") || imagen
                    .equals("") || trofeosSt.equals("")) {
                JOptionPane.showMessageDialog(this, "Datos incompletos", "Agregar athlete",
                                              JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    int age = Integer.parseInt(ageSt);
                    int trofeos = Integer.parseInt(trofeosSt);
                    if (age <= 0 || trofeos < 0) {
                        JOptionPane.showMessageDialog(this,
                                                      "Age y trofeos ganados deben ser n�meros "
                                                              + "positivos.",
                                                      "Agregar athlete", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        principal.addOutstandingAthlete(name, age, placeOfResidence, trofeos,
                                                        imagen);
                        dispose();
                    }

                } catch (NumberFormatException e2) {
                    JOptionPane.showMessageDialog(this, "Age y trofeos ganados deben ser n�meros.",
                                                  "Agregar athlete", JOptionPane.ERROR_MESSAGE);
                } catch (ElementExistsException e3) {
                    JOptionPane.showMessageDialog(this, "El athlete " + name + " ya exists.",
                                                  "Agregar athlete", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        if (e.getActionCommand().equals(EXAMINAR)) {
            JFileChooser fc = new JFileChooser("./data/images");
            fc.setDialogTitle("Buscar Imagen...");
            fc.setMultiSelectionEnabled(false);

            int resultado = fc.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String imagen = fc.getSelectedFile().getName();
                try {
                    Image variableImagen = (ImageIO.read(new File("./data/images/" + imagen)));

                    if (variableImagen != null) {
                        txtImagen.setText("./data/images/" + imagen);
                    }
                    else {
                        JOptionPane.showMessageDialog(this,
                                                      "El archivo seleccionado no es una imagen "
                                                              + "v�lida.",
                                                      "Agregar athlete", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException e1) {
                    JOptionPane
                            .showMessageDialog(this, "Error al leer la imagen.", "Agregar athlete",
                                               JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
