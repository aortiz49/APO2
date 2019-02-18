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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiSports.world.Athlete;

/**
 * Panel para para visualizar la informaci�n de un deportista.
 */
public class PanelInfoAthlete extends JPanel
{
    // -----------------------------------------------------------------
    // Attributes de interfaz
    // -----------------------------------------------------------------

    /**
     * Label con el name of the athlete.
     */
    private JLabel lblNameAthlete;

    /**
     * Campo de texto para el name of the athlete.
     */
    private JTextField txtNameAthlete;

    /**
     * Label con la age of the athlete.
     */
    private JLabel lblAge;

    /**
     * Campo de texto con la age of the athlete.
     */
    private JTextField txtAge;

    /**
     * Label con el lugar de residencia of the athlete.
     */
    private JLabel lblPlaceOfResidency;

    /**
     * Campo de texto para el lugar de residencia of the athlete.
     */
    private JTextField txtPlaceOfResidency;

    /**
     * Label Para la cantidad de trofeos ganados.
     */
    private JLabel lblTrophiesGanados;

    /**
     * Campo de texto con los trofeos ganados.
     */
    private JTextField txtTrophiesGanados;

    /**
     * Panel con la imagen of the athlete.
     */
    private JPanel panelImagen;

    /**
     * Label con la imagen of the athlete.
     */
    private JLabel lblImagen;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Constructor del panel.
     */
    public PanelInfoAthlete( )
    {
        setLayout( new java.awt.BorderLayout( ) );
        setBorder( new TitledBorder( "Athlete" ) );

        setPreferredSize( new Dimension( 350, 150 ) );
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

        lblNameAthlete = new JLabel( "Name:" );
        panelInformacion.add( lblNameAthlete );

        txtNameAthlete = new JTextField( );
        txtNameAthlete.setEditable( false );
        panelInformacion.add( txtNameAthlete );

        lblAge = new JLabel( "Age:" );
        panelInformacion.add( lblAge );

        txtAge = new JTextField( );
        txtAge.setEditable( false );
        panelInformacion.add( txtAge );

        lblPlaceOfResidency = new JLabel( "Lugar de residencia:" );
        panelInformacion.add( lblPlaceOfResidency );

        txtPlaceOfResidency = new JTextField( );
        txtPlaceOfResidency.setEditable( false );
        panelInformacion.add( txtPlaceOfResidency );

        lblTrophiesGanados = new JLabel( "Trophies ganados:" );
        panelInformacion.add( lblTrophiesGanados );

        txtTrophiesGanados = new JTextField( );
        txtTrophiesGanados.setEditable( false );
        panelInformacion.add( txtTrophiesGanados );

        granPanel.add( panelInformacion, BorderLayout.NORTH );

    }

    /**
     * Actualiza la informaci�n presentada por el panel.
     * @param pAthlete Athlete del cual se va a obtener la informaci�n. pAthlete != null.
     */
    public void actualizarInfo( Athlete pAthlete )
    {
        if( pAthlete != null )
        {
            lblImagen.setIcon( new ImageIcon( pAthlete.getImagePath( ) ) );
            txtNameAthlete.setText( pAthlete.getName( ) );
            txtAge.setText( String.valueOf( pAthlete.getAge( ) ) );
            txtTrophiesGanados.setText( String.valueOf( pAthlete.getAmountOfTrophies( ) ) );
            txtPlaceOfResidency.setText( pAthlete.getPlaceOfResidency( ) );

        }
        else
        {
            lblImagen.setIcon( new ImageIcon( "" ) );
            txtNameAthlete.setText( "" );
            txtAge.setText( "" );
            txtTrophiesGanados.setText( "" );
            txtPlaceOfResidency.setText( "" );
        }

    }

}
