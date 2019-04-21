/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDatosDialogo.java,v 1.7 2006/10/30 15:49:33 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Todos los derechos reservados 2005
 * <p>
 * Proyecto Cupi2
 * Ejercicio: n11_organigrama
 * Autor: Mario S�nchez - 22/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.organigrama.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;

/**
 * Este es el panel donde se ingresan los datos de un nuevo empleado
 */
public class PanelDatosDialogo extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String AGREGAR = "Agregar";

    private static final String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia al di�logo que contiene este panel.
     */
    private DialogoContratarEmpleado dialogo;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta Nombre
     */
    private JLabel etiquetaNombre;

    /**
     * Campo de texto para el nombre
     */
    private JTextField txtNombre;

    /**
     * Etiqueta C�digo
     */
    private JLabel etiquetaCodigo;

    /**
     * Campo de texto para el c�digo
     */
    private JTextField txtCodigo;

    /**
     * Etiqueta Cargo
     */
    private JLabel etiquetaCargo;

    /**
     * Campo de texto para el cargo
     */
    private JComboBox comboCargo;

    /**
     * Etiqueta Fecha
     */
    private JLabel etiquetaFecha;

    /**
     * Calendario para seleccionar la fecha de ingreso
     */
    private JCalendar calendario;

    /**
     * Es el bot�n para agregar un empleado
     */
    private JButton botonAgregar;

    /**
     * Es el bot�n para cancelar el di�logo
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param d Es una referencia al di�logo que contiene el panel
     * @param cargos Es una lista con los cargos que actualmente existen en la organizaci�n
     */
    public PanelDatosDialogo(DialogoContratarEmpleado d, Collection cargos) {
        dialogo = d;

        setBorder(new TitledBorder("Datos del nuevo Empleado"));
        setLayout(new GridBagLayout());

        JPanel panelInformacion = new JPanel();
        panelInformacion.setLayout(new GridBagLayout());

        GridBagConstraints gbcEtiqueta =
                new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
                                       GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);

        gbcEtiqueta.gridy = 0;
        etiquetaCargo = new JLabel("Cargo: ");
        panelInformacion.add(etiquetaCargo, gbcEtiqueta);

        gbcEtiqueta.gridy = 1;
        etiquetaNombre = new JLabel("Nombre: ");
        panelInformacion.add(etiquetaNombre, gbcEtiqueta);

        gbcEtiqueta.gridy = 2;
        etiquetaCodigo = new JLabel("C�digo: ");
        panelInformacion.add(etiquetaCodigo, gbcEtiqueta);

        gbcEtiqueta.gridy = 3;
        etiquetaFecha = new JLabel("Fecha de Ingreso: ");
        panelInformacion.add(etiquetaFecha, gbcEtiqueta);

        GridBagConstraints gbcCampos =
                new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
                                       GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0);

        gbcCampos.gridy = 0;
        comboCargo = new JComboBox();
        inicializarCombo(comboCargo, cargos);
        panelInformacion.add(comboCargo, gbcCampos);

        gbcCampos.gridy = 1;
        txtNombre = new JTextField("", 10);
        panelInformacion.add(txtNombre, gbcCampos);

        gbcCampos.gridy = 2;
        txtCodigo = new JTextField("", 10);
        panelInformacion.add(txtCodigo, gbcCampos);

        gbcCampos.gridy = 0;
        gbcCampos.gridx = 0;
        add(panelInformacion, gbcCampos);

        gbcCampos.gridy = 1;
        gbcCampos.gridx = 0;
        calendario = new JCalendar();
        add(calendario, gbcCampos);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout());

        GridBagConstraints gbcBotones =
                new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
                                       GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);

        botonAgregar = new JButton("Agregar");
        botonAgregar.setActionCommand(AGREGAR);
        botonAgregar.addActionListener(this);
        panelBotones.add(botonAgregar, gbcBotones);

        gbcBotones.gridx = 1;
        botonCancelar = new JButton("Cancelar");
        botonCancelar.setActionCommand(CANCELAR);
        botonCancelar.addActionListener(this);
        panelBotones.add(botonCancelar, gbcBotones);

        gbcBotones.gridx = 0;
        gbcBotones.gridy = 2;
        add(panelBotones, gbcBotones);

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa el combo especificado con los cargos dados
     * @param combo Combo a ser inicializado
     * @param cargos Lista de cargos existentes
     */
    private void inicializarCombo(JComboBox combo, Collection cargos) {
        Iterator it = cargos.iterator();

        while (it.hasNext()) {
            combo.addItem(it.next());
        }

        if (!cargos.isEmpty())
            combo.setSelectedIndex(0);
    }

    /**
     * Ejecuta una acci�n seg�n el bot�n sobre el que se haya hecho click
     * @param evento Es el evento del click sobre uno de los botones - evento!=null
     */
    public void actionPerformed(ActionEvent evento) {
        String comando = evento.getActionCommand();

        if (CANCELAR.equals(comando)) {
            dialogo.dispose();
        }
        else if (AGREGAR.equals(comando)) {
            dialogo.contratar((String) comboCargo.getSelectedItem(), txtCodigo.getText(),
                              txtNombre.getText(), calendario.getDate());

        }
    }
}
