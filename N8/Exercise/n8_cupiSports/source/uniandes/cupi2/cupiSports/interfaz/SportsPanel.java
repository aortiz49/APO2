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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiSports.world.Sport;

/**
 * Panel para el manejo de los sports.
 */
public class SportsPanel extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constante para definir el action command del combo de los sports.
     */
    private static final String COMBO_DEPORTES = "Combo sports";
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Interfaz principal de la aplicaci�n.
     */
    private CupiSportsInterface principal;

    // -----------------------------------------------------------------
    // Attributes de la interfaz
    // -----------------------------------------------------------------

    /**
     * Combo Box donde est�n los sports.
     */
    private JComboBox comboSports;


    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel donde se encuentra un combo con los sports.
     * @param pPrincipal Interfaz principal de la aplicaci�n. pPrincipal != null.
     */
    public SportsPanel(CupiSportsInterface pPrincipal) {
        principal = pPrincipal;
        setBorder(new TitledBorder("Sports"));
        BorderLayout layout = new BorderLayout();
        layout.setVgap(5);
        layout.setHgap(5);
        setLayout(layout);

        comboSports = new JComboBox();
        comboSports.setEditable(false);
        comboSports.addActionListener(this);
        comboSports.setActionCommand(COMBO_DEPORTES);
        add(comboSports, BorderLayout.NORTH);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Actualiza el combo box con la lista de sports.
     * @param pSports Lista de sports que estar�n en el comboBox. pSports != null.
     */
    public void actualizarSports(ArrayList<Sport> pSports) {
        comboSports.removeAllItems();
        for (int i = 0; i < pSports.size(); i++) {
            comboSports.addItem(pSports.get(i));
        }
    }

    /**
     * Devuelve el deporte seleccionado.
     * @return Sport seleccionado.
     */
    public Sport getSportSeleccionado() {
        return (Sport) comboSports.getSelectedItem();
    }

    /**
     * Selecciona el deporte.
     * @param pIndice �ndice del deporte a seleccionar.
     */
    public void seleccionarSport(int pIndice) {
        comboSports.setSelectedIndex(pIndice);
    }

    /**
     * M�todo en el que se tratan los eventos del di�logo. <br>
     * <b>post:</b> Cambia la informaci�n of the athlete que se est� mostrando de acuerdo al
     * nuevo athlete seleccionado.
     * @param e Par�metro que tiene encapsulado las caracter�sticas del elemento.
     */
    public void actionPerformed(ActionEvent e) {
        // TODO Parte 6 punto C: Implemente el manejo del evento con comando COMBO_DEPORTES
        // proveniente de comboSports.



        String command = e.getActionCommand();

        if(COMBO_DEPORTES.equals(command)) {
            Sport sportName = (Sport) comboSports.getSelectedItem();
           principal.actualizarInfoSport(sportName);

        }
    }

}
