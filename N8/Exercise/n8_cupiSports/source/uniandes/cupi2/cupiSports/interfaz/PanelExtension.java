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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones.
 */
public class PanelExtension extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Comando Option 1.
     */
    private static final String OPTION_1 = "OPTION_1";

    /**
     * Comando Option 2.
     */
    private static final String OPTION_2 = "OPTION_2";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal window of the application.
     */
    private CupiSportsInterface principal;

    // -----------------------------------------------------------------
    // Attributes of the interface
    // -----------------------------------------------------------------

    /**
     * Button Option 1.
     */
    private JButton btnOption1;

    /**
     * Button Option 2.
     */
    private JButton btnOption2;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param pPrincipal Principal window of the application. pPrincipal != null.
     */
    public PanelExtension( CupiSportsInterface pPrincipal )
    {
        principal = pPrincipal;

        setBorder( new TitledBorder( "Optiones" ) );
        setLayout( new GridLayout( 1, 2 ) );

        // Button opci�n 1
        btnOption1 = new JButton( "Option 1" );
        btnOption1.setActionCommand( OPTION_1 );
        btnOption1.addActionListener( this );
        add( btnOption1 );

        // Button opci�n 2
        btnOption2 = new JButton( "Option 2" );
        btnOption2.setActionCommand( OPTION_2 );
        btnOption2.addActionListener( this );
        add( btnOption2 );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( OPTION_1.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOption1( );
        }
        else if( OPTION_2.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOption2( );
        }
    }

}
