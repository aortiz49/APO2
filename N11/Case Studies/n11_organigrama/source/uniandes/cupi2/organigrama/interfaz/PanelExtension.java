/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.4 2006/10/29 14:40:56 da-romer Exp $ 
 * Universidad de los Andes (Bogota - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005 
 *
 * Proyecto Cupi2 
 * Ejercicio: n11_organigrama 
 * Autor: Mario S�nchez - 25/08/2005
 * Autor: Daniel Romero - 24/10/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.organigrama.interfaz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Es el panel que contiene los botones para ejecutar los puntos de extensi�n
 */
public class PanelExtension extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El comando para el bot�n 1
     */
    private final String OPCION_1 = "opcion 1";

    /**
     * El comando para el bot�n 2
     */
    private final String OPCION_2 = "opcion 2";

    /**
     * El comando para el bot�n 3
     */
    private final String OPCION_3 = "opcion 3";

    /**
     * El comando para el bot�n 4
     */
    private final String OPCION_4 = "opcion 4";

    /**
     * El comando para el bot�n 1
     */
    private final String OPCION_5 = "opcion 5";

    /**
     * El comando para el bot�n 6
     */
    private final String OPCION_6 = "opcion 6";
    
    /**
     * El comando para el bot�n 7
     */
    private final String OPCION_7 = "opcion 7";

    /**
     * El comando para el bot�n 8
     */
    private final String OPCION_8 = "opcion 8";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la referencia a la interfaz de la aplicaci�n
     */
    private InterfazOrganigrama principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el bot�n 1
     */
    private JButton botonOpcion1;

    /**
     * Es el bot�n 2
     */
    private JButton botonOpcion2;

    /**
     * Es el bot�n 3
     */
    private JButton botonOpcion3;

    /**
     * Es el bot�n 4
     */
    private JButton botonOpcion4;

    /**
     * Es el bot�n 5
     */
    private JButton botonOpcion5;

    /**
     * Es el bot�n 6
     */
    private JButton botonOpcion6;
    
    /**
     * Es el bot�n 7
     */
    private JButton botonOpcion7;
    
    /**
     * Es el bot�n 8
     */
    private JButton botonOpcion8;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con una referencia a la ventana principal de la aplicaci�n <br>
     * <b>post: </b> Construy� el panel <br>
     * @param io - Referencia a la ventana principal - is!=null
     */
    public PanelExtension( InterfazOrganigrama io )
    {
        principal = io;
        inicializar( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa los componentes del panel <br>
     * <b>post: </b> Se inicializaron y se ubicaron los componentes del panel <br>
     */
    private void inicializar( )
    {
        setBorder( new TitledBorder( "Puntos de Extensi�n" ) );

        setLayout( new FlowLayout( ) );
        botonOpcion1 = new JButton( "Opci�n 1" );
        botonOpcion1.setActionCommand( OPCION_1 );
        botonOpcion1.addActionListener( this );

        botonOpcion2 = new JButton( "Opci�n 2" );
        botonOpcion2.setActionCommand( OPCION_2 );
        botonOpcion2.addActionListener( this );

        botonOpcion3 = new JButton( "Opci�n 3" );
        botonOpcion3.setActionCommand( OPCION_3 );
        botonOpcion3.addActionListener( this );

        botonOpcion4 = new JButton( "Opci�n 4" );
        botonOpcion4.setActionCommand( OPCION_4 );
        botonOpcion4.addActionListener( this );

        botonOpcion5 = new JButton( "Opci�n 5" );
        botonOpcion5.setActionCommand( OPCION_5 );
        botonOpcion5.addActionListener( this );

        botonOpcion6 = new JButton( "Opci�n 6" );
        botonOpcion6.setActionCommand( OPCION_6 );
        botonOpcion6.addActionListener( this );
        
        botonOpcion7 = new JButton( "Opci�n 7" );
        botonOpcion7.setActionCommand( OPCION_7 );
        botonOpcion7.addActionListener( this );
        
        botonOpcion8 = new JButton( "Opci�n 8" );
        botonOpcion8.setActionCommand( OPCION_8 );
        botonOpcion8.addActionListener( this );

        add( botonOpcion1 );
        add( botonOpcion2 );
        add( botonOpcion3 );
        add( botonOpcion4 );
        add( botonOpcion5 );
        add( botonOpcion6 );
        add( botonOpcion7 );
        add( botonOpcion8 );
    }

    /**
     * Este m�todo se llama cuando se presiona uno de los botones <br>
     * <b>post: </b> Se ejecut� la acci�n correspondiente al bot�n presionado <br>
     * @param event El evento del click en el bot�n
     */
    public void actionPerformed( ActionEvent event )
    {
        String comando = event.getActionCommand( );
        if( OPCION_1.equals( comando ) )
        {
            try {
                principal.reqFuncOpcion1( );
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if( OPCION_2.equals( comando ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( OPCION_3.equals( comando ) )
        {
            principal.reqFuncOpcion3( );
        }
        else if( OPCION_4.equals( comando ) )
        {
            principal.reqFuncOpcion4( );
        }
        else if( OPCION_5.equals( comando ) )
        {
            principal.reqFuncOpcion5( );
        }
        else if( OPCION_6.equals( comando ) )
        {
            principal.reqFuncOpcion6( );
        }
        else if( OPCION_7.equals( comando ) )
        {
            principal.reqFuncOpcion7( );
        }
        else if( OPCION_8.equals( comando ) )
        {
            principal.reqFuncOpcion8( );
        }
    }

}
