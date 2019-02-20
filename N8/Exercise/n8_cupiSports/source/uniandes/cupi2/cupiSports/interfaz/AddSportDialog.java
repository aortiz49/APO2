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
 * Dialogo para agregar un deporte.
 */
public class AddSportDialog extends JDialog implements ActionListener {
    // -----------------------------------------------
    // Constants
    // -----------------------------------------------

    /**
     * Constante agregar deporte.
     */
    private final static String AGREGAR = "Agregar deporte";

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
     * Etiqueta ente regulaor.
     */
    private JLabel lblRegulatoryEntity;

    /**
     * Etiqueta athletes registrados.
     */
    private JLabel lblAthletesRegistrados;

    /**
     * Etiqueta Imagen.
     */
    private JLabel lblImagen;

    /**
     * Campo de texto Name.
     */
    private JTextField txtName;

    /**
     * Campo de texto ente regulador.
     */
    private JTextField txtRegulatoryEntity;

    /**
     * Campo de texto athletes registrados.
     */
    private JTextField txtAthletesRegistrados;

    /**
     * Texto con la ruta de la imagen.
     */
    private JTextField txtImagen;

    /**
     * Panel con la informaci�n del deporte.
     */
    private JPanel panelInfo;

    /**
     * Bot�n para agregar el deporte.
     */
    private JButton btnAgregar;

    /**
     * Bot�n para ingresar la ruta de la imagen.
     */
    private JButton btnExaminar;

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCupiSports principal;

    // -----------------------------------------------
    // Methods
    // -----------------------------------------------

    /**
     * Crea el dialogo para ingresar un deporte.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public AddSportDialog(InterfazCupiSports pPrincipal) {
        super(pPrincipal, true);

        principal = pPrincipal;
        setTitle("Agregar deporte");
        setLayout(new java.awt.BorderLayout());

        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(500, 180));

        panelInfo.setBorder(new TitledBorder("Informaci�n"));
        GridLayout layout = new GridLayout(4, 2, -175, 30);
        layout.setVgap(10);
        panelInfo.setLayout(layout);

        lblName = new JLabel("Name: ");
        panelInfo.add(lblName);
        txtName = new JTextField();
        panelInfo.add(txtName);

        lblRegulatoryEntity = new JLabel("Ente regulador: ");
        panelInfo.add(lblRegulatoryEntity);
        txtRegulatoryEntity = new JTextField();
        panelInfo.add(txtRegulatoryEntity);

        lblAthletesRegistrados = new JLabel("Athletes registrados: ");
        panelInfo.add(lblAthletesRegistrados);
        txtAthletesRegistrados = new JTextField();
        panelInfo.add(txtAthletesRegistrados);

        lblImagen = new JLabel("Imagen: ");
        panelInfo.add(lblImagen);

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
            String regulatoryEntity = txtRegulatoryEntity.getText();
            String imagen = txtImagen.getText();
            String athletesRegistradosStr = txtAthletesRegistrados.getText();

            if (name.equals("") || regulatoryEntity.equals("") || athletesRegistradosStr.equals("")
                    || imagen.equals("")) {
                JOptionPane.showMessageDialog(this, "Datos incompletos.", "Agregar deporte",
                                              JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int athletesRegistrados = Integer.parseInt(athletesRegistradosStr);
                if (athletesRegistrados <= 0) {
                    JOptionPane.showMessageDialog(this,
                                                  "Athletes registrados deber ser un n�mero "
                                                          + "positivo.",
                                                  "Agregar deporte", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    principal.agregarSport(name, regulatoryEntity, athletesRegistrados, imagen);
                    dispose();
                }
            } catch (ElementExistsException e1) {
                JOptionPane.showMessageDialog(this, "Ya exists un deporte con el name " + name,
                                              "Agregar deporte", JOptionPane.ERROR_MESSAGE);

            } catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(this, "Athletes registrados deber ser un n�mero.",
                                              "Agregar deporte", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getActionCommand().equals(EXAMINAR)) {
            JFileChooser fc = new JFileChooser("./data/imagenes");
            fc.setDialogTitle("Buscar imagen...");
            fc.setMultiSelectionEnabled(false);

            int resultado = fc.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String imagen = fc.getSelectedFile().getName();
                try {
                    Image variableImagen = (ImageIO.read(new File("./data/imagenes/" + imagen)));

                    if (variableImagen != null) {
                        txtImagen.setText("./data/imagenes/" + imagen);
                    }
                    else {
                        JOptionPane.showMessageDialog(this,
                                                      "El archivo seleccionado no es una imagen "
                                                              + "v�lida.",
                                                      "Agregar deporte", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException e1) {
                    JOptionPane
                            .showMessageDialog(this, "Error al leer la imagen.", "Agregar deporte",
                                               JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

}
