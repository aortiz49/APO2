/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBotones.java,v 1.7 2006/10/30 16:05:36 da-romer Exp $
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Es el panel donde se encuentran los botones para controlar la aplicación
 */
public class PanelBotones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String DESPEDIR = "Despedir Empleado";

    private static final String CREAR_CARGO = "Crear Cargo";

    private static final String CONTRATAR = "Contratar Empleado";

    private static final String ELIMINAR_CARGO = "Eliminar Cargo";

    private static final String BUSCAR_EMPLEADO = "Buscar Empleado";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal de la aplicación
     */
    private InterfazOrganigrama ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos Interfaz
    // -----------------------------------------------------------------
    /**
     * Es el botón para contratar un empleado
     */
    private JButton botonContratar;

    /**
     * Es el botón para despedir empleados de la organización
     */
    private JButton botonDespedir;

    /**
     * Es el botón para crear un cargo
     */
    private JButton botonCrearCargo;

    /**
     * Es el botón para eliminar un cargo de la empresa
     */
    private JButton botonEliminarCargo;

    /**
     * Es el botón para buscar un empleado
     */
    private JButton botonBuscarEmpleado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param io Es una referencia a la ventana principal de la aplicación
     */
    public PanelBotones( InterfazOrganigrama io )
    {
        ventanaPrincipal = io;

        setBorder( new TitledBorder( "Operaciones" ) );
        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets( 5, 5, 5, 5 );
        gbc.fill = GridBagConstraints.BOTH;
        botonContratar = new JButton( CONTRATAR );
        botonContratar.setActionCommand( CONTRATAR );
        botonContratar.addActionListener( this );
        add( botonContratar, gbc );

        botonCrearCargo = new JButton( CREAR_CARGO );
        botonCrearCargo.setActionCommand( CREAR_CARGO );
        botonCrearCargo.addActionListener( this );
        gbc.gridx = 0;
        add( botonCrearCargo, gbc );

        botonDespedir = new JButton( DESPEDIR );
        botonDespedir.setActionCommand( DESPEDIR );
        botonDespedir.addActionListener( this );        
        gbc.gridx = 3;
        add( botonDespedir, gbc );

        botonEliminarCargo = new JButton( ELIMINAR_CARGO );
        botonEliminarCargo.setActionCommand( ELIMINAR_CARGO );
        botonEliminarCargo.addActionListener( this );
        gbc.gridx = 1;
        add( botonEliminarCargo, gbc );
        
        botonBuscarEmpleado = new JButton( BUSCAR_EMPLEADO );
        botonBuscarEmpleado.setActionCommand( BUSCAR_EMPLEADO );
        botonBuscarEmpleado.addActionListener( this );
        gbc.gridx = 4;
        add( botonBuscarEmpleado,  gbc);
        
    }

    /**
     * Ejecuta una acción según el botón sobre el que se haya hecho click
     * @param evento Es el evento del click sobre uno de los botones - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( BUSCAR_EMPLEADO.equals( comando ) )
        {
            ventanaPrincipal.buscarEmpleado( );
        }
        else if( ELIMINAR_CARGO.equals( comando ) )
        {
            ventanaPrincipal.eliminarCargo( );
        }
        else if( DESPEDIR.equals( comando ) )
        {
            ventanaPrincipal.despedirEmpleado( );
        }
        else if( CREAR_CARGO.equals( comando ) )
        {
            ventanaPrincipal.crearCargo( );
        }
        else if( CONTRATAR.equals( comando ) )
        {
            ventanaPrincipal.mostrarVentanaContratar( );
        }
    }
}
