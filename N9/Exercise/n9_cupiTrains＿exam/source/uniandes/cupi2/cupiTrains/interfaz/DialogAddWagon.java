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

import uniandes.cupi2.cupiTrains.world.Wagon;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Di�logo para eliminar un nuevo vag�n.
 */
public class DialogAddWagon extends JDialog implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando para eliminar.
     */
    private static final String ELIMINAR = "Eliminar";

    /**
     * Constante que representa el comando de cambio en el combobox que contienes los ids de los
     * trenes.
     */
    private static final String CAMBIO_TREN = "Cambio tren";

    private static final String CAMBIO_CLASS = "Change class";


    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La ventana principal de la aplicaci�n.
     */
    private InterfazCupiTrenes principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel del di�logo.
     */
    private JPanel panelDialogo;

    /**
     * Combobox que guarda los identificadores de los trenes.
     */
    private JComboBox cbbIdTrenes;

    /**
     * Combobox que guarda los n�meros de los vagones de un tren.
     */
    private JComboBox cbbNumVagones;

    /**
     * Bot�n para eliminar.
     */
    private JButton btnEliminar;

    private JTextField txtWagon;

    private JTextField txtSeats;
    private JComboBox cbbWClass;
    private JComboBox cbbClasses;
    private JTextField txtPrice;
    private JButton btnCancel;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del di�logo para eliminar un vag�n.
     *
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public DialogAddWagon(InterfazCupiTrenes pPrincipal) {
        setTitle("Eliminate wagon");
        setLocationRelativeTo(null);
        principal = pPrincipal;
        setSize(300, 380);
        setLocationRelativeTo(principal);
        inicializarPanelDialogo();
        add(panelDialogo);
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa el panel del di�logo.
     */
    public void inicializarPanelDialogo() {
        panelDialogo = new JPanel();
        panelDialogo.setLayout(new BorderLayout());
        panelDialogo.setBorder(new TitledBorder("  Add Wagon"));


        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(new GridLayout(14, 2));

        panelIngreso.add(new JLabel());
        panelIngreso.add(new JLabel());

        cbbNumVagones = new JComboBox();
        panelIngreso.add(new JLabel("  Train Id: "));
        cbbIdTrenes = new JComboBox();
        cbbIdTrenes.setActionCommand(CAMBIO_TREN);
        cbbIdTrenes.addActionListener(this);

        ArrayList idTrenes = principal.darIdsTrenes();
        int id;
        for (int i = 0; i < idTrenes.size(); i++) {
            id = (int) idTrenes.get(i);
            cbbIdTrenes.addItem(id);
        }
        panelIngreso.add(cbbIdTrenes);
        panelIngreso.add(new JLabel());
        panelIngreso.add(new JLabel());


        panelIngreso.add(new JLabel("  Wagon number: "));
        txtWagon = new JTextField();
        panelIngreso.add(txtWagon);

        panelIngreso.add(new JLabel());
        panelIngreso.add(new JLabel());


        panelIngreso.add(new JLabel("  Number of seats: "));
        txtSeats = new JTextField();
        panelIngreso.add(txtSeats);

        panelIngreso.add(new JLabel());
        panelIngreso.add(new JLabel());


        cbbClasses = new JComboBox();
        panelIngreso.add(new JLabel("  Class: "));
        cbbWClass = new JComboBox();
        cbbWClass.setActionCommand(CAMBIO_CLASS);
        cbbWClass.addActionListener(this);
        String wagonClass[] = Wagon.CLASSES;
        String id2;
        for (int i = 0; i < wagonClass.length; i++) {
            id2 = wagonClass[i];
            cbbWClass.addItem(id2);
        }
        panelIngreso.add(cbbWClass);

        panelIngreso.add(new JLabel());
        panelIngreso.add(new JLabel());

        panelIngreso.add(new JLabel("  Price: "));
        txtPrice = new JTextField();
        panelIngreso.add(txtPrice);


        panelIngreso.add(new JLabel());
        panelIngreso.add(new JLabel());


        btnEliminar = new JButton("Add");
        btnEliminar.setActionCommand(ELIMINAR);
        btnEliminar.addActionListener(this);
        panelIngreso.add(btnEliminar);

        btnCancel = new JButton("Cancel");
        btnCancel.setActionCommand(ELIMINAR);
        btnCancel.addActionListener(this);
        panelIngreso.add(btnCancel);

        panelDialogo.add(panelIngreso, BorderLayout.CENTER);

        // panelDialogo.add(panelOption,BorderLayout.SOUTH);
    }

    /**
     * Manejo de los eventos de los botones.
     *
     * @param pEvento Acci�n que gener� el evento. pEvento != null.
     */
    public void actionPerformed(ActionEvent pEvento) {
        if (ELIMINAR.equals(pEvento.getActionCommand())) {
            boolean added = false;
            try {
                principal.agregarVagon((int) (cbbIdTrenes.getSelectedItem()),
                                       Integer.parseInt(txtWagon.getText()),
                                       Integer.parseInt(txtSeats.getText()),
                                       (String) cbbWClass.getSelectedItem(),
                                       Double.parseDouble(txtPrice.getText()));
                added = true;

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Train id must be a numeric value.",
                                              "Eliminate "
                        + "ruta", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (added) {
                JOptionPane.showMessageDialog(this, "The wagon " + txtWagon.getText() + " was"
                                                      + " added with value " + Double.parseDouble(txtPrice.getText()),
                                              "Add wagon", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }


        if (CAMBIO_TREN.equals(pEvento.getActionCommand())) {
            if (cbbNumVagones.getItemCount() > 0) {
                cbbNumVagones.removeAllItems();
            }

            int idTren = (int) cbbIdTrenes.getSelectedItem();
            ArrayList numerosVagones = principal.darNumerosVagones(idTren);
            int numero;
            for (int i = 0; i < numerosVagones.size(); i++) {
                numero = (int) numerosVagones.get(i);
                cbbNumVagones.addItem(numero);
            }
        }


    }
}
