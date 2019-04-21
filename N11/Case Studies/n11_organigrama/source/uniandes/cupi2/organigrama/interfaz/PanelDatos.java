/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDatos.java,v 1.7 2006/10/30 15:49:34 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005
 *
 * Proyecto Cupi2
 * Ejercicio: n11_organigrama
 * Autor: Mario Sánchez - 22/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.organigrama.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.organigrama.mundo.Cargo;
import uniandes.cupi2.organigrama.mundo.Empleado;

/**
 * Es el panel donde se muestran los datos del empleado seleccionado
 */
public class PanelDatos extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el objeto utilizado para darle formato a las fechas
     */
    private SimpleDateFormat formato;

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
     * Etiqueta Cargo
     */
    private JLabel etiquetaCargo;

    /**
     * Campo de texto para el cargo
     */
    private JTextField txtCargo;

    /**
     * Etiqueta Fecha
     */
    private JLabel etiquetaFecha;

    /**
     * Campo de texto para la fecha
     */
    private JTextField txtFecha;

    /**
     * Etiqueta código empleado
     */
    private JLabel etiquetaCodigo;

    /**
     * Campo de texto para el código
     */
    private JTextField txtCodigo;

    /**
     * Etiqueta código salario
     */
    private JLabel etiquetaSalario;

    /**
     * Campo de texto para el salario
     */
    private JTextField txtSalario;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     */
    public PanelDatos( )
    {
        formato = new SimpleDateFormat( "dd-MM-yyyy" );
        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbcEtiqueta = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );

        JPanel panelDatosCargo = new JPanel( );
        panelDatosCargo.setLayout( new GridBagLayout( ) );
        panelDatosCargo.setBorder( new TitledBorder( "Información Cargo Empleado" ) );

        etiquetaCargo = new JLabel( "Cargo: " );
        panelDatosCargo.add( etiquetaCargo, gbcEtiqueta );

        gbcEtiqueta.gridy = 1;
        etiquetaSalario = new JLabel( "Salario: " );
        panelDatosCargo.add( etiquetaSalario, gbcEtiqueta );

        JPanel panelDatosEmpleado = new JPanel( );
        panelDatosEmpleado.setLayout( new GridBagLayout( ) );
        panelDatosEmpleado.setBorder( new TitledBorder( "Datos del Empleado" ) );

        GridBagConstraints gbcEtiqueta2 = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        etiquetaNombre = new JLabel( "Nombre: " );
        panelDatosEmpleado.add( etiquetaNombre, gbcEtiqueta2 );

        gbcEtiqueta2.gridy = 1;
        etiquetaCodigo = new JLabel( "Código: " );
        panelDatosEmpleado.add( etiquetaCodigo, gbcEtiqueta2 );

        gbcEtiqueta2.gridy = 2;
        etiquetaFecha = new JLabel( "Fecha de Ingreso: " );
        panelDatosEmpleado.add( etiquetaFecha, gbcEtiqueta2 );

        GridBagConstraints gbcCampos = new GridBagConstraints( 1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtCargo = new JTextField( "", 10 );
        txtCargo.setEditable( false );
        panelDatosCargo.add( txtCargo, gbcCampos );

        gbcCampos.gridy = 1;
        txtSalario = new JTextField( "", 10 );
        txtSalario.setEditable( false );
        panelDatosCargo.add( txtSalario, gbcCampos );

        gbcCampos.gridy = 0;
        txtNombre = new JTextField( "", 10 );
        txtNombre.setEditable( false );
        panelDatosEmpleado.add( txtNombre, gbcCampos );

        gbcCampos.gridy = 1;
        txtCodigo = new JTextField( "", 10 );
        txtCodigo.setEditable( false );
        panelDatosEmpleado.add( txtCodigo, gbcCampos );

        gbcCampos.gridy = 2;
        txtFecha = new JTextField( "", 10 );
        txtFecha.setEditable( false );
        panelDatosEmpleado.add( txtFecha, gbcCampos );

        GridBagConstraints gbcPaneles = new GridBagConstraints( 0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        add( panelDatosCargo, gbcPaneles );

        gbcPaneles.gridx = 1;
        add( panelDatosEmpleado, gbcPaneles );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia los datos del empleado que se está mostrando
     * @param cargo El cargo del empleado a partir del cual se va a obtener la información a ser desplegada - cargo!=null
     */
    public void cambiarElemento( Cargo cargo )
    {
        Empleado empleado = cargo.darEmpleado( );
        txtCargo.setText( cargo.darNombreCargo( ) );
        txtSalario.setText( Integer.toString( cargo.darSalario( ) ) );
        txtNombre.setText( empleado.darNombre( ) );
        txtFecha.setText( formato.format( empleado.darFechaIngreso( ) ) );
        txtCodigo.setText( empleado.darCodigo( ) );
    }

    /**
     * Limpia todos los campos del panel
     */
    public void limpiar( )
    {
        txtCargo.setText( "" );
        txtNombre.setText( "" );
        txtFecha.setText( "" );
        txtCodigo.setText( "" );
        txtSalario.setText( "" );
    }
}
