/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiSports
 * Autor: Equipo Cupi2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiSports.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiSports.world.Sport;
import uniandes.cupi2.cupiSports.world.Athlete;

/**
 * Panel para para visualizar la informaci�n de un deporte.
 */
public class PanelInfoSport extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constante para definir el action command del combo de las deportistas.
     */
    private static final String COMBO_DEPORTISTAS = "Combo deportistas";

    // -----------------------------------------------------------------
    // Attributes de interfaz
    // -----------------------------------------------------------------

    /**
     * Label con el name del deporte.
     */
    private JLabel lblNameSport;

    /**
     * Label con el ente regulador.
     */
    private JLabel lblRegulatoryEntity;

    /**
     * Label con el n�mero de deportistas registrados.
     */
    private JLabel lblAthletesRegistrados;

    /**
     * Campo de texto con el name del deporte.
     */
    private JTextField txtNameSport;

    /**
     * Campo de texto con el ente regulador.
     */
    private JTextField txtRegulatoryEntity;

    /**
     * Campo de texto con el n�mero de deportistas registrados.
     */
    private JTextField txtAthletesRegistrados;

    /**
     * Label deportistas.
     */
    private JLabel lblAthletes;

    /**
     * Combo con los deportistas sobresalientes.
     */
    private JComboBox comboOutstandingAthletes;

    /**
     * Panel con la imagen del deporte.
     */
    private JPanel panelImagen;

    /**
     * Label con la imagen del deporte.
     */
    private JLabel lblImagen;

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCupiSports principal;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Constructor del panel.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public PanelInfoSport( InterfazCupiSports pPrincipal )
    {
        principal = pPrincipal;
        setLayout( new java.awt.BorderLayout( ) );
        setBorder( new TitledBorder( "Sport" ) );

        setPreferredSize( new Dimension( 320, 150 ) );
        // Panel Imagen
        panelImagen = new JPanel( );
        panelImagen.setPreferredSize( new Dimension( 110, 195 ) );
        panelImagen.setLayout( new BorderLayout( ) );
        add( panelImagen, java.awt.BorderLayout.NORTH );

        lblImagen = new JLabel( );
        lblImagen.setHorizontalAlignment( JLabel.CENTER );
        lblImagen.setVerticalAlignment( JLabel.CENTER );
        panelImagen.add( lblImagen, BorderLayout.CENTER );

        JPanel granPanel = new JPanel( );
        granPanel.setPreferredSize( new Dimension( 400, 195 ) );
        granPanel.setLayout( new BorderLayout( ) );
        add( granPanel, java.awt.BorderLayout.CENTER );
        JPanel panelInformacion = new JPanel( );
        GridLayout layout = new GridLayout( 4, 2 );
        layout.setVgap( 8 );
        layout.setHgap( 8 );
        panelInformacion.setLayout( layout );

        lblNameSport = new JLabel( "Name:" );
        panelInformacion.add( lblNameSport );

        txtNameSport = new JTextField( );
        txtNameSport.setEditable( false );
        panelInformacion.add( txtNameSport );

        lblRegulatoryEntity = new JLabel( "Ente regulador:" );
        panelInformacion.add( lblRegulatoryEntity );

        txtRegulatoryEntity = new JTextField( );
        txtRegulatoryEntity.setEditable( false );
        panelInformacion.add( txtRegulatoryEntity );

        lblAthletesRegistrados = new JLabel( "Athletes registrados:" );
        panelInformacion.add( lblAthletesRegistrados );

        txtAthletesRegistrados = new JTextField( );
        txtAthletesRegistrados.setEditable( false );
        panelInformacion.add( txtAthletesRegistrados );

        lblAthletes = new JLabel( "Athletes sobresalientes:" );
        panelInformacion.add( lblAthletes );
        comboOutstandingAthletes = new JComboBox( );
        comboOutstandingAthletes.addActionListener( this );
        comboOutstandingAthletes.setActionCommand( COMBO_DEPORTISTAS );
        panelInformacion.add( comboOutstandingAthletes );

        granPanel.add( panelInformacion, java.awt.BorderLayout.NORTH );

    }

    /**
     * Actualiza la informaci�n presentada por el panel.
     * @param pSport Sport del cual se va a obtener la informaci�n. pSport != null.
     */
    public void actualizarInfo( Sport pSport )
    {
        if( pSport != null )
        {
            lblImagen.setIcon( new ImageIcon( pSport.getImagePath( ) ) );
            txtNameSport.setText( pSport.getName( ) );
            txtRegulatoryEntity.setText( pSport.getRegulatoryEntity( ) );
            txtAthletesRegistrados.setText( pSport.getNumberOfRegisteredAthletes( ) + "" );
            comboOutstandingAthletes.removeAllItems( );
            for( int i = 0; i < pSport.getOutstandingAthletes( ).size( ); i++ )
            {
                comboOutstandingAthletes.addItem( pSport.getOutstandingAthletes( ).get( i ) );
            }
        }
        else
        {
            lblImagen.setIcon( new ImageIcon( "" ) );
            txtNameSport.setText( "" );
            txtRegulatoryEntity.setText( "" );
            txtAthletesRegistrados.setText( "" );
            comboOutstandingAthletes.removeAllItems( );
        }
    }

    /**
     * Devuelve El deportista seleccionado.
     * @return Athlete seleccionado.
     */
    public Athlete getAthleteSeleccionado( )
    {
        return ( Athlete )comboOutstandingAthletes.getSelectedItem( );
    }

    /**
     * M�todo en el que se tratan los eventos del di�logo.
     * @param e Par�metro que tiene encapsulado las caracter�sticas del elemento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( COMBO_DEPORTISTAS ) )
        {
            principal.actualizarInfoAthlete( getAthleteSeleccionado( ) );
        }
    }

}
