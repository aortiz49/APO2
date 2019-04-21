/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazOrganigrama.java,v 1.16 2009/04/17 15:27:46 ju-cort1 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005
 *
 * Proyecto Cupi2
 * Ejercicio: n11_organigrama
 * Autor: Mario S�nchez - 21-nov-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.organigrama.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uniandes.cupi2.organigrama.mundo.Cargo;
import uniandes.cupi2.organigrama.mundo.Empresa;
import uniandes.cupi2.organigrama.mundo.OrganigramaException;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazOrganigrama extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String ARCHIVO_EMPRESA = "./data/empresa.dat";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Empresa empresa;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /*
     * Es el panel donde se muestra la representaci�n gr�fica del organigrama
     */
    private PanelGraficoOrganigrama panelGrafico;

    /**
     * Es el panel donde se muestran los botones para controlar la aplicaci�n
     */
    private PanelBotones panelBotones;

    /**
     * Es el panel donde se muestran los datos del empleado seleccionado
     */
    private PanelDatos panelDatos;

    /**
     * Es el panel donde se muestran los botones para los puntos de extensi�n
     */
    private PanelExtension panelExtension;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa la aplicaci�n y construye la ventana principal<br>
     * @param em La empresa de la que se va a manejar la informaci�n
     */
    public InterfazOrganigrama( Empresa em )
    {
        // Crea la clase principal
        empresa = em;

        // Construye la forma
        construirForma( );
        centrar( );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Centra la ventana en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    /**
     * Este m�todo sirve para construir la forma inicializando cada uno de los componentes. <br>
     * <b>pre: </b> La ventana est� vac�a <br>
     * <b>post: </b> Se inicializaron los componentes gr�ficos de la aplicaci�n
     */
    private void construirForma( )
    {
        // Configurar el panel Principal
        getContentPane( ).setLayout( new BorderLayout( ) );
        setSize( 800, 613 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Organigrama" );

        // Inicializar los componentes de la interfaz
        panelGrafico = new PanelGraficoOrganigrama( empresa );
        JScrollPane scroll = new JScrollPane( panelGrafico );
        scroll.setPreferredSize( getPreferredSize( ) );
        getContentPane( ).add( scroll, BorderLayout.CENTER );

        JPanel panelContenedor = new JPanel( new BorderLayout( ) );
        panelBotones = new PanelBotones( this );
        panelContenedor.add( panelBotones, BorderLayout.CENTER );

        panelDatos = new PanelDatos( );
        panelContenedor.add( panelDatos, BorderLayout.NORTH );

        panelExtension = new PanelExtension( this );
        panelContenedor.add( panelExtension, BorderLayout.SOUTH );

        getContentPane( ).add( panelContenedor, BorderLayout.SOUTH );
    }

    /**
     * Este m�todo se usa para eliminar un cargo de la empresa
     */
    public void eliminarCargo( )
    {
        String cargo = JOptionPane.showInputDialog( this, "Indique el nombre del cargo a ser eliminado", "Eliminaci�n Cargos", JOptionPane.QUESTION_MESSAGE );
        if( cargo != null )
        {
            try
            {
                empresa.eliminarCargo( cargo );
                panelGrafico.seleccionar( null );
                panelGrafico.actualizarImagen( );
                panelDatos.limpiar( );
                guardar( );
            }
            catch( OrganigramaException e )
            {

                JOptionPane.showMessageDialog( this, e.getMessage( ), "Eliminaci�n Cargos", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

    /**
     * Este m�todo se usa para realizar una b�squeda usando el c�digo de un empleado
     */
    public void buscarEmpleado( )
    {
        String codigo = JOptionPane.showInputDialog( this, "Indique el c�digo del empleado del que desea ver la informaci�n", "C�digo", JOptionPane.QUESTION_MESSAGE );
        if( codigo != null )
        {
            // Se busca el cargo asociado con el empleado
            Cargo buscado = empresa.buscarCargoEmpleado( codigo );
            if( buscado != null )
            {
                panelGrafico.seleccionar( buscado.darNombreCargo( ) );
                panelGrafico.actualizarImagen( );
                panelDatos.cambiarElemento( buscado );
            }
            else
            {
                panelGrafico.seleccionar( null );
                panelGrafico.actualizarImagen( );
                panelDatos.limpiar( );
                JOptionPane.showMessageDialog( this, "No se encontr� ning�n empleado con el c�digo buscado", "B�squeda Empleados", JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

    /**
     * Este m�todo se usa para despedir un empleado de la organizaci�n
     */
    public void despedirEmpleado( )
    {
        String codigo = JOptionPane.showInputDialog( this, "Indique el c�digo del empleado que desea despedir", "C�digo", JOptionPane.QUESTION_MESSAGE );
        if( codigo != null )
        {
            // Se busca el cargo asociado con el empleado
            try
            {
                empresa.despedirEmpleado( codigo );
                guardar( );
            }
            catch( OrganigramaException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Despido Empleados", JOptionPane.INFORMATION_MESSAGE );
            }
            panelGrafico.seleccionar( null );
            panelGrafico.actualizarImagen( );
        }
    }

    /**
     * Este m�todo se usa para crear un nuevo cargo en la empresa
     */
    public void crearCargo( )
    {
        String nombreCargo = JOptionPane.showInputDialog( this, "Indique el nombre del cargo", "Nombre Cargo", JOptionPane.QUESTION_MESSAGE );
        String salarioS = JOptionPane.showInputDialog( this, "Indique el salario", "Salario Cargo", JOptionPane.QUESTION_MESSAGE );
        String cargoDepende = JOptionPane.showInputDialog( this, "Indique el cargo del que depende", "Cargo del que Depende", JOptionPane.QUESTION_MESSAGE );

        try
        {

            int salario = Integer.parseInt( salarioS );

            if( nombreCargo == null || nombreCargo.equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "Ingrese el nombre del cargo", "Creaci�n Cargos", JOptionPane.ERROR_MESSAGE );
            }
            else if( salario <= 0 )
            {
                JOptionPane.showMessageDialog( this, "El salario del cargo no es v�lido", "Creaci�n Cargos", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                empresa.crearCargo( nombreCargo, salario, cargoDepende );
                panelGrafico.seleccionar( null );
                panelGrafico.actualizarImagen( );
                panelDatos.limpiar( );
                guardar( );
            }

        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "El salario del cargo no es v�lido", "Creaci�n Cargos", JOptionPane.ERROR_MESSAGE );
        }
        catch( OrganigramaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Creaci�n Cargos", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Este m�todo se usar para mostrar el di�logo que sirve para agregar un nuevo empleado.
     */
    public void mostrarVentanaContratar( )
    {
        DialogoContratarEmpleado d = new DialogoContratarEmpleado( this, empresa.darListaCargosDisponibles( ) );
        d.setVisible( true );
    }

    /**
     * Este m�todo se usa para agregar un nuevo empleado a la organizaci�n.
     * @param dialogo Es el di�logo en el que se introdujeron los datos del nuevo empleado
     * @param codigo C�digo del empleado
     * @param cargo Es el cargo del nuevo empleado. No puede estar repetido.
     * @param nombre Es el nombre del nuevo empleado.
     * @param fechaIngreso Es la fecha de ingreso del nuevo empleado.
     */
    public void contratarEmpleado( DialogoContratarEmpleado dialogo, String codigo, String cargo, String nombre, Date fechaIngreso )
    {

        try
        {
            if( cargo == null )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar el cargo del empleado", "Error", JOptionPane.ERROR_MESSAGE );
            }
            if( codigo == null || codigo.equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar el c�digo del empleado", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( nombre == null || nombre.equals( "" ) )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar el nombre del empleado", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( fechaIngreso == null )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar la fecha de ingreso del empleado", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                empresa.contratarPersona( codigo, nombre, fechaIngreso, cargo );
                dialogo.dispose( );
                panelGrafico.seleccionar( null );
                panelGrafico.actualizarImagen( );
                guardar( );
            }
        }
        catch( OrganigramaException e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo agregar el empleado: \n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Guarda el estado actual en el que se encuentra la empresa
     */
    private void guardar( )
    {
        try
        {
            empresa.guardar( );
        }
        catch( OrganigramaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Este m�todo se encarga de salvar la informaci�n de la empresa, justo antes de cerrar la aplicaci�n
     */
    public void dispose( )
    {
        guardar( );
        super.dispose( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( ) throws IOException, ClassNotFoundException {
        int x = empresa.darCabeza().darAltura();
        String resultado = empresa.metodo1( );
        JOptionPane.showMessageDialog( this, resultado,Integer.toString(x) ,
                                       JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        int x = empresa.darCabeza().darPeso();

        String resultado = "There are "+Integer.toString(x) + "nodes.";
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 3
     */
    public void reqFuncOpcion3( )
    {
        String resultado = empresa.metodo3( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 4
     */
    public void reqFuncOpcion4( )
    {
        String resultado = empresa.metodo4( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 5
     */
    public void reqFuncOpcion5( )
    {
        String resultado = empresa.metodo5( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 6
     */
    public void reqFuncOpcion6( )
    {
        String resultado = empresa.metodo6( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }
    
    /**
     * M�todo para la extensi�n 7
     */
    public void reqFuncOpcion7( )
    {
        String resultado = empresa.metodo7( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }
    
    /**
     * M�todo para la extensi�n 8
     */
    public void reqFuncOpcion8( )
    {
        String resultado = empresa.metodo8( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args Argumentos para la ejecuci�n de la aplicaci�n. En este caso no son necesarios
     */
    public static void main( String[] args )
    {
        Empresa empresa = null;
        try
        {
            empresa = new Empresa( ARCHIVO_EMPRESA );
        }
        catch( OrganigramaException e )
        {
            e.printStackTrace( );
            System.exit( 1 );
        }
        InterfazOrganigrama interfaz = new InterfazOrganigrama( empresa );
        interfaz.setVisible( true );
    }

}