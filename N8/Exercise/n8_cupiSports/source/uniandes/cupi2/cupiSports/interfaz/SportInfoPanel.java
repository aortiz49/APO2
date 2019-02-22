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
 * Panel to to visualizar la informaci�n de a sport.
 */
public class SportInfoPanel extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant to definir el action command del combo de las athletes.
     */
    private static final String COMBO_DEPORTISTAS = "Combo athletes";

    // -----------------------------------------------------------------
    // Attributes of the interface
    // -----------------------------------------------------------------

    /**
     * Label con el name del sport.
     */
    private JLabel lblNameSport;

    /**
     * Label con el ente regulador.
     */
    private JLabel lblRegulatoryEntity;

    /**
     * Label con el n�mero de athletes registrados.
     */
    private JLabel lblRegisteredAthletes;

    /**
     * Campo de texto con el name del sport.
     */
    private JTextField txtNameSport;

    /**
     * Campo de texto con el ente regulador.
     */
    private JTextField txtRegulatoryEntity;

    /**
     * Campo de texto con el n�mero de athletes registrados.
     */
    private JTextField txtRegisteredAthletes;

    /**
     * Label athletes.
     */
    private JLabel lblAthletes;

    /**
     * Combo con los athletes sobresalientes.
     */
    private JComboBox comboOutstandingAthletes;

    /**
     * Panel con la imagen del sport.
     */
    private JPanel panelImage;

    /**
     * Label con la imagen del sport.
     */
    private JLabel lblImage;

    /**
     * Principal window of the application.
     */
    private CupiSportsInterface principal;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Constructor del panel.
     * @param pPrincipal Principal window of the application. pPrincipal != null.
     */
    public SportInfoPanel( CupiSportsInterface pPrincipal )
    {
        principal = pPrincipal;
        setLayout( new java.awt.BorderLayout( ) );
        setBorder( new TitledBorder( "Sport" ) );

        setPreferredSize( new Dimension( 320, 150 ) );
        // Panel Image
        panelImage = new JPanel( );
        panelImage.setPreferredSize( new Dimension( 110, 195 ) );
        panelImage.setLayout( new BorderLayout( ) );
        add( panelImage, java.awt.BorderLayout.NORTH );

        lblImage = new JLabel( );
        lblImage.setHorizontalAlignment( JLabel.CENTER );
        lblImage.setVerticalAlignment( JLabel.CENTER );
        panelImage.add( lblImage, BorderLayout.CENTER );

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

        lblRegulatoryEntity = new JLabel( "Regulatory entity:" );
        panelInformacion.add( lblRegulatoryEntity );

        txtRegulatoryEntity = new JTextField( );
        txtRegulatoryEntity.setEditable( false );
        panelInformacion.add( txtRegulatoryEntity );

        lblRegisteredAthletes = new JLabel( "Registered athletes:" );
        panelInformacion.add( lblRegisteredAthletes );

        txtRegisteredAthletes = new JTextField( );
        txtRegisteredAthletes.setEditable( false );
        panelInformacion.add( txtRegisteredAthletes );

        lblAthletes = new JLabel( "Outstanding athletes:" );
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
    public void updateInfo( Sport pSport )
    {
        if( pSport != null )
        {
            lblImage.setIcon( new ImageIcon( pSport.getImagePath( ) ) );
            txtNameSport.setText( pSport.getName( ) );
            txtRegulatoryEntity.setText( pSport.getRegulatoryEntity( ) );
            txtRegisteredAthletes.setText( pSport.getNumberOfRegisteredAthletes( ) + "" );
            comboOutstandingAthletes.removeAllItems( );
            for( int i = 0; i < pSport.getOutstandingAthletes( ).size( ); i++ )
            {
                comboOutstandingAthletes.addItem( pSport.getOutstandingAthletes( ).get( i ) );
            }
        }
        else
        {
            lblImage.setIcon( new ImageIcon( "" ) );
            txtNameSport.setText( "" );
            txtRegulatoryEntity.setText( "" );
            txtRegisteredAthletes.setText( "" );
            comboOutstandingAthletes.removeAllItems( );
        }
    }

    /**
     * Devuelve El athlete seleccionado.
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
            principal.updateInfoAthlete( getAthleteSeleccionado( ) );
        }
    }

}
