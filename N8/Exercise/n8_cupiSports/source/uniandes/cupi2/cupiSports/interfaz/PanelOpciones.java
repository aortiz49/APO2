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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel que se encarga de mostrar los botones de opciones.
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constante para agregar un deporte.
     */
    public static final String NUEVO_DEPORTE = "Agregar deporte";

    /**
     * Constante para eliminar un deporte.
     */
    public static final String ELIMINAR_DEPORTE = "Eliminar deporte";

    /**
     * Constante para agregar un deportista.
     */
    public static final String NUEVO_DEPORTISTA = "Agregar deportista";

    /**
     * Constante para el bot�n de eliminar deportista.
     */
    public static final String ELIMINAR_DEPORTISTA = "Eliminar deportista";

    /**
     * Constante para el bot�n de generar reporte.
     */
    public static final String GENERAR_REPORTE = "Generar reporte";

    /**
     * Constante para el bot�n de importar datos.
     */
    public static final String IMPORTAR_DATOS = "Importar datos";

    /**
     * Constante para definir el directo donde est�n las im�genes de los elementos.
     */
    public static final String DIRECTORIO = "data/imagenes/";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * Interfaz principal de la aplicaci�n.
     */
    private InterfazCupiSports principal;

    // -----------------------------------------------------------------
    // Attributes de la interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n para agregar un nuevo deporte.
     */
    private JButton btnNuevoSport;

    /**
     * Bot�n para eliminar un deporte.
     */
    private JButton btnEliminarSport;

    /**
     * Bot�n para agregar un nuevo deportista.
     */
    private JButton btnNuevoAthlete;

    /**
     * Bot�n para eliminar un deportista.
     */
    private JButton btnEliminarAthlete;

    /**
     * Bot�n para generar reporte.
     */
    private JButton btnGenerarReporte;

    /**
     * Bot�n para leer un archivo.
     */
    private JButton btnLeerArchivo;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param pPrincipal Interfaz principal de la aplicaci�n. pPrincipal != null.
     */
    public PanelOpciones( InterfazCupiSports pPrincipal )
    {
        principal = pPrincipal;
        setLayout( new FlowLayout( FlowLayout.CENTER, 0, 4 ) );
        setBorder( new TitledBorder( "Opciones" ) );
        setPreferredSize( new Dimension( 80, 600 ) );

        btnNuevoSport = new JButton( new ImageIcon( DIRECTORIO + "agregarSport.png" ) );
        add( btnNuevoSport );
        btnNuevoSport.addActionListener( this );
        btnNuevoSport.setActionCommand( NUEVO_DEPORTE );
        btnNuevoSport.setToolTipText( NUEVO_DEPORTE );
        btnNuevoSport.setPreferredSize( new Dimension( 60, 60 ) );

        btnEliminarSport = new JButton( new ImageIcon( DIRECTORIO + "eliminarSport.png" ) );
        add( btnEliminarSport );
        btnEliminarSport.addActionListener( this );
        btnEliminarSport.setActionCommand( ELIMINAR_DEPORTE );
        btnEliminarSport.setToolTipText( ELIMINAR_DEPORTE );
        btnEliminarSport.setPreferredSize( new Dimension( 60, 60 ) );

        btnNuevoAthlete = new JButton( new ImageIcon( DIRECTORIO + "agregarAthlete.png" ) );
        add( btnNuevoAthlete );
        btnNuevoAthlete.addActionListener( this );
        btnNuevoAthlete.setActionCommand( NUEVO_DEPORTISTA );
        btnNuevoAthlete.setToolTipText( NUEVO_DEPORTISTA );
        btnNuevoAthlete.setPreferredSize( new Dimension( 60, 60 ) );

        btnEliminarAthlete = new JButton( new ImageIcon( DIRECTORIO + "eliminarAthlete.png" ) );
        add( btnEliminarAthlete );
        btnEliminarAthlete.addActionListener( this );
        btnEliminarAthlete.setActionCommand( ELIMINAR_DEPORTISTA );
        btnEliminarAthlete.setToolTipText( ELIMINAR_DEPORTISTA );
        btnEliminarAthlete.setPreferredSize( new Dimension( 60, 60 ) );

        btnGenerarReporte = new JButton( new ImageIcon( DIRECTORIO + "Reporte.png" ) );
        add( btnGenerarReporte );
        btnGenerarReporte.addActionListener( this );
        btnGenerarReporte.setActionCommand( GENERAR_REPORTE );
        btnGenerarReporte.setToolTipText( GENERAR_REPORTE );
        btnGenerarReporte.setPreferredSize( new Dimension( 60, 60 ) );

        btnLeerArchivo = new JButton( new ImageIcon( DIRECTORIO + "CargarDatos.png" ) );
        add( btnLeerArchivo );
        btnLeerArchivo.addActionListener( this );
        btnLeerArchivo.setActionCommand( IMPORTAR_DATOS );
        btnLeerArchivo.setToolTipText( IMPORTAR_DATOS );
        btnLeerArchivo.setPreferredSize( new Dimension( 60, 60 ) );
    }
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * M�todo que se encarga de tomar el control cuando se hace click en alguno de los botones.
     * @param event Evento asociado al click del bot�n.
     */
    public void actionPerformed( ActionEvent event )
    {
        if( event.getActionCommand( ).equals( NUEVO_DEPORTE ) )
        {
            principal.mostrarDialogoAgregarSport( );
        }
        else if( event.getActionCommand( ).equals( ELIMINAR_DEPORTE ) )
        {
            principal.eliminarSport( );
        }
        else if( event.getActionCommand( ).equals( NUEVO_DEPORTISTA ) )
        {
            principal.mostrarDialogoAgregarAthlete( );
        }
        else if( event.getActionCommand( ).equals( ELIMINAR_DEPORTISTA ) )
        {
            principal.eliminarAthlete( );
        }
        else if( event.getActionCommand( ).equals( GENERAR_REPORTE ) )
        {
            principal.generarReporteTrophies( "./data/reporte.txt" );
        }
        else if( event.getActionCommand( ).equals( IMPORTAR_DATOS ) )
        {
            principal.actualizarInformacionAthletes( );
        }
    }
}