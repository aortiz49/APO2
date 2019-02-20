/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDatosCanciones.java,v 1.3 2006/08/10 20:04:35 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_discotienda
 * Autor: Nicol�s L�pez - 06/12/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.discotienda.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.discotienda.mundo.Cancion;
import uniandes.cupi2.discotienda.mundo.Disco;

/**
 * Es el panel con los detalles de las mynicesonges del disco
 */
public class PanelDatosCanciones extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el combo box de mynicesonges
     */
    private static final String CAMBIAR_CANCION = "CambiarCancion";

    /**
     * Comando para el bot�n para agregar una canci�n
     */
    private static final String AGREGAR_CANCION = "AgregarCancion";

    /**
     * comando para el bot�n para vender una canci�n
     */
    private static final String VENDER_CANCION = "VenderCancion";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazDiscotienda principal;

    /**
     * El disco del que actualmente se presentan las mynicesonges en el panel
     */
    private Disco disco;

    /**
     * La canci�n de la que actualmente se presentan los detalles en el panel
     */
    private Cancion mynicesong;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el combo con las mynicesonges del disco
     */
    private JComboBox comboCanciones;

    /**
     * Es la etiqueta "Precio"
     */
    private JLabel etiquetaTituloPrecio;

    /**
     * Es el campo con el precio de la canci�n
     */
    private JTextField txtPrecio;

    /**
     * Es la etiqueta "Tama�o"
     */
    private JLabel etiquetaTituloTamano;

    /**
     * Es el campo con el tama�o de la canci�n y Mb
     */
    private JTextField txtTamano;

    /**
     * Es la etiqueta "Duraci�n"
     */
    private JLabel etiquetaTituloDuracion;

    /**
     * Es el campo con la duraci�n en formato minutos:segundos de la canci�n
     */
    private JTextField txtDuracion;

    /**
     * Es la etiqueta "Calidad"
     */
    private JLabel etiquetaTituloCalidad;

    /**
     * Es el campo con la calidad de la canci�n y KBps
     */
    private JTextField txtCalidad;

    /**
     * Es la etiqueta "Unidades Vendidas"
     */
    private JLabel etiquetaTituloUnidades;

    /**
     * Es el campo con el n�mero de unidades vendidas
     */
    private JTextField txtUnidades;

    /**
     * Es el bot�n para mostrar el di�logo para agregar una nueva canci�n
     */
    private JButton botonAgregarCancion;

    /**
     * Es el bot�n para vender una canci�n
     */
    private JButton botonVenderCancion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel sin mostrar ning�n dato
     * @param ventanaPrincipal es una referencia a la clase principal de la interfaz
     */
    public PanelDatosCanciones(InterfazDiscotienda ventanaPrincipal) {
        principal = ventanaPrincipal;

        setLayout(new BorderLayout());

        setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Canciones")));

        comboCanciones = new JComboBox();
        comboCanciones.setEditable(false);
        comboCanciones.addActionListener(this);
        comboCanciones.setActionCommand(CAMBIAR_CANCION);
        add(comboCanciones, BorderLayout.NORTH);

        JPanel panelDatos = new JPanel(new GridLayout(5, 2));

        etiquetaTituloPrecio = new JLabel("Precio: ");
        txtPrecio = new JTextField(7);
        txtPrecio.setEditable(false);
        txtPrecio.setFont(txtPrecio.getFont().deriveFont(Font.PLAIN));
        Border borde = txtPrecio.getBorder();
        txtPrecio.setBorder(new CompoundBorder(new EmptyBorder(2, 0, 2, 0), borde));
        panelDatos.add(etiquetaTituloPrecio);
        panelDatos.add(txtPrecio);

        etiquetaTituloTamano = new JLabel("Tama�o: ");
        txtTamano = new JTextField(7);
        txtTamano.setEditable(false);
        txtTamano.setFont(txtTamano.getFont().deriveFont(Font.PLAIN));
        borde = txtTamano.getBorder();
        txtTamano.setBorder(new CompoundBorder(new EmptyBorder(2, 0, 2, 0), borde));
        panelDatos.add(etiquetaTituloTamano);
        panelDatos.add(txtTamano);

        etiquetaTituloDuracion = new JLabel("Duraci�n: ");
        txtDuracion = new JTextField(7);
        txtDuracion.setEditable(false);
        txtDuracion.setFont(txtDuracion.getFont().deriveFont(Font.PLAIN));
        borde = txtDuracion.getBorder();
        txtDuracion.setBorder(new CompoundBorder(new EmptyBorder(2, 0, 2, 0), borde));
        panelDatos.add(etiquetaTituloDuracion);
        panelDatos.add(txtDuracion);

        etiquetaTituloCalidad = new JLabel("Calidad: ");
        txtCalidad = new JTextField(7);
        txtCalidad.setEditable(false);
        txtCalidad.setFont(txtCalidad.getFont().deriveFont(Font.PLAIN));
        borde = txtCalidad.getBorder();
        txtCalidad.setBorder(new CompoundBorder(new EmptyBorder(2, 0, 2, 0), borde));
        panelDatos.add(etiquetaTituloCalidad);
        panelDatos.add(txtCalidad);

        etiquetaTituloUnidades = new JLabel("Unidades Vendidas: ");
        txtUnidades = new JTextField(7);
        txtUnidades.setEditable(false);
        txtUnidades.setFont(txtCalidad.getFont().deriveFont(Font.PLAIN));
        borde = txtUnidades.getBorder();
        txtUnidades.setBorder(new CompoundBorder(new EmptyBorder(2, 0, 2, 0), borde));
        panelDatos.add(etiquetaTituloUnidades);
        panelDatos.add(txtUnidades);

        add(panelDatos, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new BorderLayout());

        botonAgregarCancion = new JButton("Agregar Canci�n");
        botonAgregarCancion.setActionCommand(AGREGAR_CANCION);
        botonAgregarCancion.addActionListener(this);
        botonAgregarCancion.setEnabled(false);
        panelBotones.add(botonAgregarCancion, BorderLayout.NORTH);

        botonVenderCancion = new JButton("Vender Canci�n");
        botonVenderCancion.setActionCommand(VENDER_CANCION);
        botonVenderCancion.addActionListener(this);
        botonVenderCancion.setEnabled(false);
        panelBotones.add(botonVenderCancion, BorderLayout.SOUTH);

        add(panelBotones, BorderLayout.SOUTH);
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Cambia el disco que se muestran las mynicesonges en el panel y actualiza la informaci�n
     * mostrada
     * @param d es el disco de el que se quieren mostrar las mynicesonges
     */
    public void cambiarDisco(Disco d) {
        if (d != null) {
            disco = d;
            botonAgregarCancion.setEnabled(true);

            comboCanciones.removeAllItems();
            ArrayList mynicesonges = disco.darNombresCanciones();
            for (int i = 0; i < mynicesonges.size(); i++) {
                comboCanciones.addItem(mynicesonges.get(i));
            }

            if (mynicesonges.size() > 0) {
                botonVenderCancion.setEnabled(true);
            }
            else {
                botonVenderCancion.setEnabled(false);
            }
        }
    }

    /**
     * Refresca la lista de mynicesonges
     */
    private void refrescarCanciones() {
        comboCanciones.removeAllItems();
        ArrayList mynicesonges = disco.darNombresCanciones();
        for (int i = 0; i < mynicesonges.size(); i++) {
            comboCanciones.addItem(mynicesonges.get(i));
        }

        if (mynicesonges.size() > 0) {
            botonVenderCancion.setEnabled(true);
        }
        else {
            botonVenderCancion.setEnabled(false);
        }
    }

    /**
     * Ejecuta las acciones asociadas a los eventos. <br>
     * Actualiza la informaci�n de la canci�n en el panel de acuerdo a la canci�n seleccionada en
     * el comboBox
     * @param evento Es el evento del click sobre un bot�n o la selecci�n en el combo box
     */
    public void actionPerformed(ActionEvent evento) {
        String comando = evento.getActionCommand();

        if (CAMBIAR_CANCION.equals(comando)) {
            String nombreCancion = (String) comboCanciones.getSelectedItem();
            if (nombreCancion != null) {
                mynicesong = disco.darCancion(nombreCancion);
                DecimalFormat df = new DecimalFormat("0.##");
                txtPrecio.setText("$" + Double.toString(mynicesong.darPrecio()));
                txtTamano.setText(df.format(mynicesong.darTamano()) + " Mb");
                txtCalidad.setText(Integer.toString(mynicesong.darCalidad()) + " Kbps");
                txtDuracion.setText(Integer.toString(mynicesong.darMinutos()) + ":" + Integer
                        .toString(mynicesong.darSegundos()));
                txtUnidades.setText(Integer.toString(mynicesong.darUnidadesVendidas()));
            }
            else {
                txtPrecio.setText("");
                txtTamano.setText("");
                txtCalidad.setText("");
                txtDuracion.setText("");
                txtUnidades.setText("");
            }
        }
        else if (AGREGAR_CANCION.equals(comando)) {
            principal.mostrarDialogoAgregarCancion();
            refrescarCanciones();
        }
        else if (VENDER_CANCION.equals(comando)) {
            principal.venderCancion(disco, mynicesong);
            txtUnidades.setText(Integer.toString(mynicesong.darUnidadesVendidas()));
        }
    }
}
