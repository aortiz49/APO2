/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupiTrenes
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTrains.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiTrains.world.Train;

/**
 * Di�logo para buscar un tren.
 */
public class DialogoBuscarTren extends JDialog implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando para buscar un tren.
     */
    private static final String BUSCAR = "Buscar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCupiTrenes principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Campo de texto para el origen del tren.
     */
    private JTextField txtOrigen;

    /**
     * Campo de texto para el destino del tren.
     */
    private JTextField txtDestino;

    /**
     * Bot�n para buscar.
     */
    private JButton btnBuscar;


    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del di�logo para buscar un tren.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public DialogoBuscarTren(InterfazCupiTrenes pPrincipal) {
        principal = pPrincipal;
        setTitle("Find train");
        setSize(300, 200);

        setLocationRelativeTo(principal);

        JPanel panelDialogo = new JPanel();
        panelDialogo.setLayout(new BorderLayout());
        panelDialogo.setBorder(new TitledBorder("Find train"));

        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(new GridLayout(4, 2));

        panelIngreso.add(new JLabel());
        panelIngreso.add(new JLabel());

        panelIngreso.add(new JLabel("Origin: "));
        txtOrigen = new JTextField();
        panelIngreso.add(txtOrigen);

        panelIngreso.add(new JLabel("Destination:"));
        txtDestino = new JTextField();
        panelIngreso.add(txtDestino);

        panelIngreso.add(new JLabel());
        panelIngreso.add(new JLabel());

        panelDialogo.add(panelIngreso, BorderLayout.CENTER);

        btnBuscar = new JButton("Find");
        btnBuscar.setActionCommand(BUSCAR);
        btnBuscar.addActionListener(this);
        panelDialogo.add(btnBuscar, BorderLayout.SOUTH);
        add(panelDialogo);
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento != null.
     */
    public void actionPerformed(ActionEvent pEvento) {
        String comando = pEvento.getActionCommand();
        if (BUSCAR.equals(comando)) {
            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();
            Train rutaTren;
            if (origen.equals("") || destino.equals("")) {
                JOptionPane.showMessageDialog(this,
                                              "Origin and destination fields cannot be empty",
                                              "Find train", JOptionPane.ERROR_MESSAGE);
            }
            else {
                rutaTren = principal.buscarTren(origen, destino);
                if (rutaTren == null) {
                    JOptionPane.showMessageDialog(this,
                                                  "No train exists that goes from " + origen
                                                          + " to " + destino + ".", "Find train",
                                                  JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    //dispose();
                    JOptionPane.showMessageDialog(this,
                                                  "The train going from " + origen
                                                          + " to " + destino + " is " + rutaTren.getId(),
                                                  "Find train",
                                                  JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
