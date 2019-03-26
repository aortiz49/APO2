/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: carFactory
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.carFactory.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * Este es el panel donde se encuentran los botones y controles de la aplicaci�n.
 */
public class PanelBotones extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constante que representa el color de fondo.
     */
    private static final String COLOR_FONDO = "Background";

    /**
     * Constante que representa el bot�n seleccionar.
     */
    public static final String SELECCIONAR = "Select";

    /**
     * Constante que representa ning�n bot�n seleccionado.
     */
    public static final String NINGUNA = "None";

    /**
     * Constante que representa ning�n bot�n borrar.
     */
    public static final String BORRAR = "Erase";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazFabricaDeCarros principal;

    // -----------------------------------------------------------------
    // Attributes de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Arreglo de botones.
     */
    private JToggleButton[] botones;

    /**
     * Grupo de los botones.
     */
    private ButtonGroup grupo;

    /**
     * Bot�n para elegir el color del carro.
     */
    private JButton btnColorCarro;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes.<br>
     * <b> post: </b> Se inicializa el panel con la interfaz de f�brica de carros dada por
     * par�metro.
     * @param pInterfazPrincipal Es una referencia a la clase principal de la interfaz.
     *                           pInterfazPrincipal !=null.
     */
    public PanelBotones(InterfazFabricaDeCarros pInterfazPrincipal) {
        String dirImagenes =
                "." + File.separator + "data" + File.separator + "images" + File.separator;

        principal = pInterfazPrincipal;

        setBorder(new TitledBorder(""));

        ArrayList tipos = new ArrayList();
        tipos.add(SELECCIONAR);
        tipos.add(BORRAR);
        tipos.addAll(pInterfazPrincipal.darOpcionesSeleccion());
        botones = new JToggleButton[tipos.size()];
        grupo = new ButtonGroup();
        String tipo;
        for (int i = 0; i < tipos.size(); i++) {
            tipo = (String) tipos.get(i);
            botones[i] = new JToggleButton(new ImageIcon(dirImagenes + tipo + "Button" + ".gif"));
            botones[i].setPreferredSize(new Dimension(50, 50));
            botones[i].setToolTipText(tipo);
            botones[i].setActionCommand(tipo);
            if (tipo.equals(BORRAR)) {
                botones[i].addActionListener(this);
            }
            grupo.add(botones[i]);
        }

        btnColorCarro = new JButton(" ");
        btnColorCarro.setActionCommand(COLOR_FONDO);
        btnColorCarro.setBackground(new Color(200, 0, 0));
        btnColorCarro.addActionListener(this);
        btnColorCarro.setToolTipText("Color Fondo");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER,
                                                        GridBagConstraints.CENTER,
                                                        new Insets(2, 2, 2, 2), 0, 0);

        int b = 0;
        int tam = botones.length;
        for (int i = 0; i < tam + 1 / 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (b != tam) {

                    gbc.gridx = j;
                    gbc.gridy = i;
                    add(botones[b], gbc);
                    b++;
                }

            }
        }

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = botones.length / 2 + 1;
        add(btnColorCarro, gbc);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Retorna la opci�n seleccionada.
     * @return La opci�n seleccionada en este panel.
     */
    public String darOpcionSeleccionada() {
        ArrayList opciones = principal.darOpcionesSeleccion();
        String tipoSeleccionado = NINGUNA;
        if (botones[0].isSelected()) {
            tipoSeleccionado = SELECCIONAR;
        }
        else if (botones[1].isSelected()) {
            tipoSeleccionado = BORRAR;
        }
        else {
            boolean seleccionado = false;
            for (int i = 2; i < botones.length && !seleccionado; i++) {
                seleccionado = botones[i].isSelected();
                if (seleccionado) {
                    tipoSeleccionado = (String) opciones.get(i - 2);
                }
            }
        }
        return tipoSeleccionado;
    }

    /**
     * Retorna el color elegido para el fondo de los carros.
     * @return Color de fondo.
     */
    public Color getColorFondo() {
        return btnColorCarro.getBackground();
    }

    /**
     * Ejecuta la acci�n que corresponde al bot�n oprimido.
     * @param pEvento Es el evento del click sobre un bot�n. pEvento != null.
     */
    public void actionPerformed(ActionEvent pEvento) {
        String comando = pEvento.getActionCommand();

        if (BORRAR.equals(comando)) {
            principal.eliminar();
        }

        else if (COLOR_FONDO.equals(comando)) {
            Color colorFondo = JColorChooser
                    .showDialog(this, "Color del fondo", btnColorCarro.getBackground());
            btnColorCarro.setBackground(colorFondo);
        }
    }

}
