/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiTaxonomia
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.cupiTaxonomia.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiTaxonomia.world.Taxon;

/**
 * Di�logo para agregar un nuevo ser vivo.
 */
@SuppressWarnings({ "rawtypes", "serial","unchecked" })
public class DialogoAgregarSerVivo extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando para agregar.
     */
    private final static String AGREGAR = "Agregar";

    /**
     * Constante que representa el comando para buscar.
     */
    private final static String BUSCAR = "Buscar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCupiTaxonomia principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * ComboBox con las especies del �rbol taxon�mico.
     */
	private JComboBox comboEspecies;

    /**
     * Campo de texto con el nombre com�n del ser vivo.
     */
    private JTextField txtNombreComun;

    /**
     * Campo de texto con el nombre cient�fico del ser vivo.
     */
    private JTextField txtNombreCientifico;

    /**
     * �rea de texto con las caracter�sticas del ser vivo.
     */
    private JTextArea areaCaracteristicas;

    /**
     * Panel con un scroll que contiene a areaCaracteristicas.
     */
    private JScrollPane scrollCaracteristicas;

    /**
     * Campo de texto con la imagen del ser vivo.
     */
    private JTextField txtImagen;

    /**
     * Bot�n agregar.
     */
    private JButton btnAgregar;

    /**
     * Bot�n buscar imagen.
     */
    private JButton btnBuscar;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un di�logo para agregar un ser vivo.
     * <b> post: </b> Se crea el di�logo con todos sus elementos gr�ficos.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public DialogoAgregarSerVivo( InterfazCupiTaxonomia pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new BorderLayout( ) );
        setSize( 320, 250 );
        setModal( true );
        setLocationRelativeTo( principal );
        setTitle( "Agregar ser vivo" );
        setResizable( true );

        JPanel panel1 = new JPanel( );
        panel1.setLayout( new GridBagLayout( ) );

        GridBagConstraints gbc = new GridBagConstraints( );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 2, 2, 2, 2 );

        panel1.add( new JLabel( " Especie: " ), gbc );

        gbc.gridy = 1;
        panel1.add( new JLabel( " Nombre com�n: " ), gbc );

        gbc.gridy = 2;
        panel1.add( new JLabel( " Nombre cient�fico: " ), gbc );

        gbc.gridy = 3;
        areaCaracteristicas = new JTextArea( );
        areaCaracteristicas.setLineWrap( true );

        gbc.gridwidth = 3;
        scrollCaracteristicas = new JScrollPane( areaCaracteristicas );
        scrollCaracteristicas.setSize( new Dimension( 0, 80 ) );
        scrollCaracteristicas.setPreferredSize( new Dimension( 0, 80 ) );
        scrollCaracteristicas.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scrollCaracteristicas.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scrollCaracteristicas.setBorder( new TitledBorder( " Caracter�sticas: " ) );
        panel1.add( scrollCaracteristicas, gbc );

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel1.add( new JLabel( " Imagen: " ), gbc );

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        comboEspecies = new JComboBox( principal.darTaxonesTipo( Taxon.ESPECIE ).toArray( ) );
        panel1.add( comboEspecies, gbc );

        gbc.gridy = 1;
        txtNombreComun = new JTextField( );
        panel1.add( txtNombreComun, gbc );

        gbc.gridy = 2;
        txtNombreCientifico = new JTextField( );
        panel1.add( txtNombreCientifico, gbc );

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0.9;
        txtImagen = new JTextField( );
        txtImagen.setEditable( false );
        panel1.add( txtImagen, gbc );

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 0.1;
        btnBuscar = new JButton( BUSCAR );
        btnBuscar.setActionCommand( BUSCAR );
        btnBuscar.addActionListener( this );
        panel1.add( btnBuscar, gbc );

        add( panel1, BorderLayout.NORTH );

        btnAgregar = new JButton( AGREGAR );
        btnAgregar.setActionCommand( AGREGAR );
        btnAgregar.addActionListener( this );

        add( btnAgregar, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( AGREGAR ) )
        {
            String especie = ( String )comboEspecies.getSelectedItem( );
            String nombre = txtNombreComun.getText( );
            String nombreC = txtNombreCientifico.getText( );
            String caracteristicas = areaCaracteristicas.getText( );
            String imagen = txtImagen.getText( );

            if( especie != null && !especie.isEmpty( ) && nombre != null && !nombre.isEmpty( ) && nombreC != null && !nombreC.isEmpty( ) && caracteristicas != null && !caracteristicas.isEmpty( ) && imagen != null && !imagen.isEmpty( ) )
            {
                principal.agregarSerVivo( especie, nombre, nombreC, caracteristicas, imagen );
                this.dispose( );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Por favor ingrese la informaci�n completa del ser vivo.", "Agregar ser vivo", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( comando.equals( BUSCAR ) )
        {
            JFileChooser fileChooser = new JFileChooser( "./data/imagenes" );
            if( fileChooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
            {
                txtImagen.setText( fileChooser.getSelectedFile( ).getAbsolutePath( ) );
            }
        }
    }
}