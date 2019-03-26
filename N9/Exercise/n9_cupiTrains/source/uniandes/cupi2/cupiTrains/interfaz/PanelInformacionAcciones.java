/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupiTrenes
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiTrains.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel que contiene la informaci�n general y las acciones.
 */
public class PanelInformacionAcciones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constate que representa el comando para agregar un tren.
     */
    private final static String AGREGAR_TREN = "Add train";

    /**
     * Constate que representa el comando para eliminar un tren.
     */
    private final static String ELIMINAR_TREN = "Eliminate train";

    /**
     * Constate que representa el comando para agregar un vag�n.
     */
    private final static String AGREGAR_VAGON = "Add wagon";

    /**
     * Constate que representa el comando para eliminar un vag�n.
     */
    private final static String ELIMINAR_VAGON = "Eliminate wagon";

    /**
     * Constate que representa el comando para generar un reporte.
     */
    private final static String GENERAR_REPORTE = "Generate report";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCupiTrenes principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta con la cantidad recaudada.
     */
    private JLabel lblRecaudo;

    /**
     * Etiqueta con la cantidad de sillas disponibles.
     */
    private JLabel lblDisponibles;

    /**
     * Bot�n para agregar tren.
     */
    private JButton btnAgregarTren;

    /**
     * Bot�n para eliminar tren.
     */
    private JButton btnEliminarTren;

    /**
     * Bot�n para agregar vag�n.
     */
    private JButton btnAgregarVagon;

    /**
     * Bot�n para eliminar vag�n.
     */
    private JButton btnEliminarVagon;

    /**
     * Bot�n para generar reporte.
     */
    private JButton btnGenerarReporte;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con la informaci�n de las rutas del tren. <br>
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public PanelInformacionAcciones( InterfazCupiTrenes pPrincipal )
    {
        principal = pPrincipal;

        setBorder( new TitledBorder( "General information" ) );
        setLayout( new BorderLayout( ) );

        JPanel panelInformacion = new JPanel( )
        {
            public void paintComponent( Graphics g )
            {
                Dimension tamanio = getSize( );
                ImageIcon imagenFondo = new ImageIcon( "data/imagenes/ferrocarril.png" );
                g.drawImage( imagenFondo.getImage( ), 0, 0, tamanio.width, tamanio.height, null );
                setOpaque( false );
                super.paintComponent( g );


            }
        };

        panelInformacion.setLayout( new GridLayout( 3, 2 ) );

        Font fuente1 = new Font( "Andalus", Font.BOLD, 16 );
        panelInformacion.setOpaque( false );

        panelInformacion.add( new JLabel( ) );
        panelInformacion.add( new JLabel( ) );

        JLabel lblRecaudoTotal = new JLabel( "Total money: " );
        lblRecaudoTotal.setForeground( Color.yellow );
        lblRecaudoTotal.setFont( fuente1 );
        panelInformacion.add( lblRecaudoTotal );
        lblRecaudo = new JLabel( );
        lblRecaudo.setForeground( Color.white );
        lblRecaudo.setFont( fuente1 );
        panelInformacion.add( lblRecaudo );

        JLabel lblSillasDisponibles = new JLabel( "Available seats: " );
        lblSillasDisponibles.setForeground( Color.yellow );
        lblSillasDisponibles.setFont( fuente1 );
        panelInformacion.add( lblSillasDisponibles );
        lblDisponibles = new JLabel( );
        lblDisponibles.setForeground( Color.WHITE );
        lblDisponibles.setFont( fuente1 );
        panelInformacion.add( lblDisponibles );

        add( panelInformacion, BorderLayout.CENTER );

        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 5, 1, 4, 2 ) );
        panelBotones.setBorder( new TitledBorder( "Actions" ) );

        btnAgregarTren = new JButton( "Add train" );
        btnAgregarTren.setActionCommand( AGREGAR_TREN );
        btnAgregarTren.addActionListener( this );
        panelBotones.add( btnAgregarTren );

        btnEliminarTren = new JButton( " Eliminate train" );
        btnEliminarTren.setActionCommand( ELIMINAR_TREN );
        btnEliminarTren.addActionListener( this );
        panelBotones.add( btnEliminarTren );

        btnAgregarVagon = new JButton( "Add wagon" );
        btnAgregarVagon.setActionCommand( AGREGAR_VAGON );
        btnAgregarVagon.addActionListener( this );
        panelBotones.add( btnAgregarVagon );

        btnEliminarVagon = new JButton( " Eliminate wagon" );
        btnEliminarVagon.setActionCommand( ELIMINAR_VAGON );
        btnEliminarVagon.addActionListener( this );
        panelBotones.add( btnEliminarVagon );

        btnGenerarReporte = new JButton( "Generate report" );
        btnGenerarReporte.setActionCommand( GENERAR_REPORTE );
        btnGenerarReporte.addActionListener( this );
        panelBotones.add( btnGenerarReporte );

        add( panelBotones, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza el panel de informaci�n.
     * @param pTotalRecaudo Total de dinero recaudado a mostrar. pTotalRecaudo >= 0.
     * @param pTotalSillasDisponibles N�mero total de sillas disponibles a mostrar. pTotalSillasDisponibles >= 0.
     */
    public void actualizar( double pTotalRecaudo, int pTotalSillasDisponibles )
    {
        lblRecaudo.setText( "$" + (int) (pTotalRecaudo *100)/100.0);
        lblDisponibles.setText( "" + pTotalSillasDisponibles );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( AGREGAR_TREN ) )
        {
            principal.mostrarDialogoAgregarTren( );
        }
        else if( comando.equals( ELIMINAR_TREN ) )
        {
            principal.eliminarTren( );
        }
        else if( comando.equals( AGREGAR_VAGON ) )
        {
            principal.mostrarDialogoAgregarVagon( );
        }
        else if( comando.equals( ELIMINAR_VAGON ) )
        {
            principal.mostrarDialogoEliminarVagon( );
        }
        else if( comando.equals( GENERAR_REPORTE ) )
        {
            principal.generarReporte( );
        }
    }

}
