/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoContratarEmpleado.java,v 1.6 2007/04/13 04:51:26 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005
 *
 * Proyecto Cupi2
 * Ejercicio: n11_organigrama
 * Autor: Mario S�nchez - 22/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.organigrama.interfaz;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collection;
import java.util.Date;

import javax.swing.JDialog;

/**
 * Este es el di�logo usado para crear un nuevo empleado
 */
public class DialogoContratarEmpleado extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal de la aplicaci�n
     */
    private InterfazOrganigrama ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se introducen los datos del nuevo empleado
     */
    private PanelDatosDialogo panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el di�logo e inicializa los elementos de la interfaz
     * @param io Es una referencia a la clase principal de la interfaz
     * @param elementos Es una lista con los elementos que actualmente est�n en la organizaci�n
     */
    public DialogoContratarEmpleado( InterfazOrganigrama io, Collection elementos )
    {
        super( io, true );
        ventanaPrincipal = io;

        panelDatos = new PanelDatosDialogo( this, elementos );
        add( panelDatos );

        pack( );
        setTitle( "Contratar Empleado" );
        setResizable( false );
        centrar( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Centra el di�logo en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    /**
     * Este m�todo se usa para agregar un nuevo empleado a la organizaci�n.
     * @param cargo Es el cargo del nuevo empleado. No puede estar repetido.
     * @param codigo C�digo del empleado
     * @param nombre Es el nombre del nuevo empleado.
     * @param fechaIngreso Es la fecha de ingreso del nuevo empleado.
     */
    public void contratar( String cargo, String codigo, String nombre, Date fechaIngreso )
    {
        ventanaPrincipal.contratarEmpleado( this, codigo, cargo, nombre, fechaIngreso );
    }
}
