/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: carFactory
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.carFactory.userInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
/**
 * Panel de manejo de extensiones.
 */
public class ExtensionPanel extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constante para la Opci�n 1.
     */
    private static final String OPTION_1 = "OPTION_1";

    /**
     * Constante para la Opci�n 2.
     */
    private static final String OPTION_2 = "OPTION_2";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal window of the application.
     */
    private CarFactoryInterface principal;

    // -----------------------------------------------------------------
    // Attributes de userInterface
    // -----------------------------------------------------------------

    /**
     * Bot�n Opci�n 1.
     */
    private JButton btnOpcion1;

    /**
     * Bot�n Opci�n 2.
     */
    private JButton btnOpcion2;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.<br>
     * <b> post: </b> Se inicializa el panel con la userInterface de f�brica de carros dada por par�metro.
     * @param pPrincipalInterface Ventana principal. pPrincipalInterface !=null.
     */
    public ExtensionPanel(CarFactoryInterface pPrincipalInterface )
    {
        principal = pPrincipalInterface;

        setBorder( new TitledBorder( "Options" ) );
        setLayout( new GridLayout( 1, 2 ) );

        // Bot�n opci�n 1
        btnOpcion1 = new JButton( "Option 1" );
        btnOpcion1.setActionCommand( OPTION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Bot�n opci�n 2
        btnOpcion2 = new JButton( "Option 2" );
        btnOpcion2.setActionCommand( OPTION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Ejecuta la acci�n que corresponde al bot�n oprimido
     * @param pEvent Acci�n que gener� el evento. pEvent != null.
     */
    public void actionPerformed( ActionEvent pEvent )
    {
        if( OPTION_1.equals( pEvent.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPTION_2.equals( pEvent.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
    }

}
