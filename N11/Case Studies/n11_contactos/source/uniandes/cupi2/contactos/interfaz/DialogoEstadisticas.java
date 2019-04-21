/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoEstadisticas.java,v 1.9 2007/04/13 04:51:14 carl-veg Exp $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_contactos
 * Autor: Mario Sánchez - 28/10/2005
 * Autor: Daniel Romero - 19/10/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.contactos.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.contactos.mundo.DatoEstadistico;

/**
 * Es el diálogo usado para mostrar las estadísticas y las extensiones
 */
public class DialogoEstadisticas extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private final static String CERRAR = "Cerrar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazContactos ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se muestran las estadísticas
     */
    private PanelDatosEstadisticas panelDatosEstadisticas;

    /**
     * Es el panel para las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Es el botón para cerrar el diálogo
     */
    private JButton botonCerrar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el diálogo para visualizar las estadísticas
     * @param ic Es una referencia a la clase principal de la interfaz
     * @param datos Datos de la estadística
     */
    public DialogoEstadisticas( InterfazContactos ic, DatoEstadistico[] datos )
    {
        super( ic, true );
        ventanaPrincipal = ic;

        panelDatosEstadisticas = new PanelDatosEstadisticas( datos );
        add( panelDatosEstadisticas, BorderLayout.CENTER );

        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.NORTH );

        JPanel panelBotones = new JPanel( );
        botonCerrar = new JButton( "Cerrar" );
        botonCerrar.addActionListener( this );
        botonCerrar.setActionCommand( CERRAR );
        panelBotones.add( botonCerrar );
        add( panelBotones, BorderLayout.SOUTH );

        setTitle( "Estadísticas Directorio Contactos" );
        setSize( 257, 405 );
        centrar( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Centra el diálogo en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    /**
     * Cierra el diálogo
     */
    private void cerrarDialogo( )
    {
        dispose( );
    }

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String valor = panelExtension.darTextoOpcion1( );

        if( valor == null || "".equals( valor ) )
        {
            JOptionPane.showMessageDialog( this, "Debe ingresar el valor", "Directorio Contactos", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try
            {
                int valorEnt = Integer.parseInt( valor );
                ventanaPrincipal.reqFuncOpcion1( valorEnt );
                panelExtension.cambiarTextoOpcion1( "" );
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "El valor ingresado debe ser un número", "Directorio Contactos", JOptionPane.ERROR_MESSAGE );
            }

        }
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String valor = panelExtension.darTextoOpcion2( );

        if( valor == null || "".equals( valor ) )
        {
            JOptionPane.showMessageDialog( this, "Debe ingresar el valor", "Directorio Contactos", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try
            {
                int valorEnt = Integer.parseInt( valor );
                ventanaPrincipal.reqFuncOpcion2( valorEnt );
                panelExtension.cambiarTextoOpcion2( "" );
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "El valor ingresado debe ser un número", "Directorio Contactos", JOptionPane.ERROR_MESSAGE );
            }

        }
    }

    /**
     * Método para la extensión 3
     */
    public void reqFuncOpcion3( )
    {
        String valor = panelExtension.darTextoOpcion3( );

        if( valor == null || "".equals( valor ) )
        {
            JOptionPane.showMessageDialog( this, "Debe ingresar el valor", "Directorio Contactos", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            ventanaPrincipal.reqFuncOpcion3( valor );
            panelExtension.cambiarTextoOpcion3( "" );
        }
    }

    /**
     * Método para la extensión 4
     */
    public void reqFuncOpcion4( )
    {
        String valor = panelExtension.darTextoOpcion4( );

        if( valor == null || "".equals( valor ) )
        {
            JOptionPane.showMessageDialog( this, "Debe ingresar el valor", "Directorio Contactos", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            ventanaPrincipal.reqFuncOpcion4( valor );
            panelExtension.cambiarTextoOpcion4( "" );
        }
    }

    /**
     * Método para la extensión 5
     */
    public void reqFuncOpcion5( )
    {
        String valor = panelExtension.darTextoOpcion5( );

        if( valor == null || "".equals( valor ) )
        {
            JOptionPane.showMessageDialog( this, "Debe ingresar el valor", "Directorio Contactos", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            ventanaPrincipal.reqFuncOpcion5( valor );
            panelExtension.cambiarTextoOpcion5( "" );
        }
    }

    /**
     * Ejecuta la acción correspondiente al botón que fue presionado
     * @param evento El evento del click sobre uno de los botones. evento != null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( CERRAR.equals( comando ) )
        {
            cerrarDialogo( );
        }

    }
}