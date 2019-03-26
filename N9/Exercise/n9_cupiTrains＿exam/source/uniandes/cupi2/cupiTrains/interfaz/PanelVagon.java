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
 * Panel de informaci�n del vag�n.
 */
public class PanelVagon extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando para ir al primer vag�n.
     */
    private static final String PRIMERO = "First wagon";

    /**
     * Constante que representa el comando para ir al siguiente vag�n.
     */
    private static final String SIGUIENTE = "Next wagon";

    /**
     * Constante que representa el comando para vender un tiquete.
     */
    private static final String VENDER = "Sell ticket";

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
     * Panel para la navegaci�n de vagones.
     */
    private JPanel panelNavegacion;

    /**
     * Panel con la informaci�n del vag�n.
     */
    private JPanel panelInformacion;

    /**
     * Etiqueta con el n�mero del vag�n.
     */
    private JLabel lblNumero;

    /**
     * Etiqueta con la clase del vag�n.
     */
    private JLabel lblClase;
    
    /**
     * Etiqueta con la cantidad total de sillas.
     */
    private JLabel lblCantidadTotalSillas;

    /**
     * Etiqueta con la cantidad de sillas disponibles.
     */
    private JLabel lblCantidadSillasDisponibles;

    /**
     * Etiqueta con el precio del tiquete en el vag�n.
     */
    private JLabel lblPrecio;

    /**
     * Bot�n para seleccionar el primer vag�n.
     */
    private JButton btnPrimero;

    /**
     * Bot�n para seleccionar el siguiente vag�n.
     */
    private JButton btnSiguiente;

    /**
     * Bot�n para vender.
     */
    private JButton btnVender;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con la informaci�n del vag�n.
     * @param pPrincipal Ventana principal de la aplicaci�n de cupiTrenes. pPrincipal != null.
     */
    public PanelVagon( InterfazCupiTrenes pPrincipal )
    {
        principal = pPrincipal;

        setBorder( new TitledBorder( "Wagon information" ) );
        setLayout( new BorderLayout( ) );

        inicializarPanelInformacion( );
        add( panelInformacion, BorderLayout.CENTER );

        inicializarPanelNavegacion( );
        add( panelNavegacion, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa el panel de informaci�n.
     */
    public void inicializarPanelInformacion( )
    {
        panelInformacion = new JPanel( )
        {

            // Sobre-escribe el m�todo paintComponent para poder pintar la imagen de fondo del panel.

            public void paintComponent( Graphics g )
            {
                Dimension tamanio = getSize( );
                ImageIcon imagenFondo = new ImageIcon( "data/imagenes/fondoPapel2.png" );
                g.drawImage( imagenFondo.getImage( ), 0, 0, tamanio.width, tamanio.height, null );
                setOpaque( false );
                super.paintComponent( g );

            }
        };
        panelInformacion.setLayout( new GridLayout( 8, 4 ) );

        Font fuente1 = new Font( "Georgia", Font.BOLD, 10 );
        Font fuente2 = new Font( "Georgia", Font.PLAIN, 14 );
        JLabel lblNum = new JLabel( "Number" );
        lblNum.setFont( new Font( "Georgia", Font.BOLD, 12 ) );
        panelInformacion.add( lblNum );
        panelInformacion.add( new JLabel( "" ) );
        panelInformacion.add( new JLabel( "" ) );
        JLabel lblClas = new JLabel( "" );
        lblClas.setFont( fuente1 );
        panelInformacion.add( lblClas );

        lblNumero = new JLabel( "" );
        lblNumero.setFont( new Font( "Georgia", Font.BOLD, 11 ) );
        panelInformacion.add( lblNumero );
        panelInformacion.add( new JLabel( "" ) );
        panelInformacion.add( new JLabel( "" ) );
        lblClase = new JLabel( "" );
        lblClase.setFont( new Font( "Georgia", Font.BOLD, 11 ) );
        panelInformacion.add( lblClase );

        panelInformacion.add( new JLabel( "" ) );
        JLabel lblTotalSillas = new JLabel( "Total seats:" );
        panelInformacion.add( lblTotalSillas );
        lblTotalSillas.setFont( fuente1 );
        lblCantidadTotalSillas = new JLabel( "" );
        lblCantidadTotalSillas.setFont( fuente2 );
        panelInformacion.add( lblCantidadTotalSillas );
        panelInformacion.add( new JLabel( "" ) );
        
        panelInformacion.add( new JLabel( "" ) );
        panelInformacion.add( new JLabel( "" ) );
        panelInformacion.add( new JLabel( "" ) );
        panelInformacion.add( new JLabel( "" ) );
        
        panelInformacion.add( new JLabel( "" ) );
        JLabel lblSillasDisponibles = new JLabel( "Available seats:" );
        lblSillasDisponibles.setFont( fuente1 );
        panelInformacion.add( lblSillasDisponibles );
        lblCantidadSillasDisponibles = new JLabel( "" );
        lblCantidadSillasDisponibles.setFont( fuente2 );
        panelInformacion.add( lblCantidadSillasDisponibles );
        panelInformacion.add( new JLabel( "" ) );
        
        panelInformacion.add( new JLabel( "" ) );
        panelInformacion.add( new JLabel( "" ) );
        panelInformacion.add( new JLabel( "" ) );
        panelInformacion.add( new JLabel( "" ) );

        panelInformacion.add( new JLabel( "" ) );
        JLabel lblValorTiquete = new JLabel( "Ticket value:   " );
        lblValorTiquete.setFont( fuente1 );
        panelInformacion.add( lblValorTiquete );
        lblPrecio = new JLabel( "" );
        lblPrecio.setFont( fuente2 );
        panelInformacion.add( lblPrecio );
        panelInformacion.add( new JLabel( "" ) );

        panelInformacion.setOpaque( false );
    }

    /**
     * Inicializa el panel de navegaci�n.
     */
    public void inicializarPanelNavegacion( )
    {
        panelNavegacion = new JPanel( );
        panelNavegacion.setLayout( new GridLayout( 1, 3 ) );
        panelNavegacion.setBorder( new TitledBorder( "Wagon selection" ) );

        btnPrimero = new JButton( PRIMERO );
        btnPrimero.setActionCommand( PRIMERO );
        btnPrimero.addActionListener( this );
        panelNavegacion.add( btnPrimero );

        btnSiguiente = new JButton( SIGUIENTE );
        btnSiguiente.setActionCommand( SIGUIENTE );
        btnSiguiente.addActionListener( this );
        panelNavegacion.add( btnSiguiente );

        btnVender = new JButton( VENDER );
        btnVender.setActionCommand( VENDER );
        btnVender.addActionListener( this );
        panelNavegacion.add( btnVender );

    }

    /**
     * Actualiza el panel con la informaci�n del vag�n dada por par�metro.
     * @param pNumeroVagon N�mero del vag�n.
     * @param pClaseVagon Clase del compartimiento. pClaseVagon != null && pClaseVagon != "" && (pClaseVagon ==PRIMERA_CLASE || pClaseVagon == SEGUNDA_CLASE ).
     * @param pCantidadSillasDisponibles Cantidad de sillas disponibles en el vag�n. pCantidadSillasDisponibles >= 0.
     * @param pPrecioSilla Precio del tiquete. pPrecioSilla > 0.
     * @param pSillasTotales Total de sillas en el vag�n. pSillasTotales > 0.
     */
    public void actualizar( int pNumeroVagon, String pClaseVagon, int pCantidadSillasDisponibles, double pPrecioSilla, int pSillasTotales )
    {

        lblNumero.setText( "" + pNumeroVagon );
        lblClase.setText( "" + pClaseVagon );
        lblCantidadTotalSillas.setText( "" + pSillasTotales );
        lblCantidadSillasDisponibles.setText( "  " + pCantidadSillasDisponibles );
        lblPrecio.setText( "  $" + (int)(pPrecioSilla*100)/100.0 );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        if( SIGUIENTE.equals( pEvento.getActionCommand( ) ) )
        {
            principal.darSiguienteVagon( );
        }
        else if( PRIMERO.equals( pEvento.getActionCommand( ) ) )
        {
            principal.darPrimerVagon( );
        }
        else if( VENDER.equals( pEvento.getActionCommand( ) ) )
        {
            principal.venderTiquete( );
        }
    }

}
