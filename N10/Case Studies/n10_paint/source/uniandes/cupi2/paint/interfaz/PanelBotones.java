/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBotones.java,v 1.8 2010/04/19 17:34:58 lr.ruiz114 Exp $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_paint
 * Autor: Mario Sánchez - 27/09/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.paint.interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.paint.mundo.IFigura;
import uniandes.cupi2.paint.mundo.Linea;
import uniandes.cupi2.paint.mundo.Ovalo;
import uniandes.cupi2.paint.mundo.Rectangulo;

/**
 * Este es el panel donde se encuentran los botones y controles de la aplicación
 */
public class PanelBotones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String COLOR_FONDO = "Fondo";

    private static final String COLOR_LINEA = "Linea";

    private static final String BORRAR = "Borrar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazPaint principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el botón para seleccionar figuras
     */
    private JToggleButton botonSeleccionar;

    /**
     * Es el botón para dibujar cuadrados
     */
    private JToggleButton botonRectangulo;

    /**
     * Es el botón para dibujar óvalos
     */
    private JToggleButton botonOvalo;

    /**
     * Es el botón para dibujar líneas
     */
    private JToggleButton botonLinea;

    /**
     * Es el botón para crear una nueva figura1
     */
    private JToggleButton botonFiguraNueva1;

    /**
     * Es el botón para crear una nueva figura1
     */
    private JToggleButton botonFiguraNueva2;

    /**
     * Es el botón para crear una nueva figura3
     */
    private JToggleButton botonFiguraNueva3;

    /**
     * Es el combo box para seleccionar el tipo de línea que tendrá una figura
     */
    private JComboBox cbbTipoLinea;

    /**
     * Es el botón para elegir el color para las líneas de una figura
     */
    private JButton botonColorLinea;

    /**
     * Es el botón para elegir el color para el fondo de una figura
     */
    private JButton botonColorFondo;

    /**
     * Es el botón para eliminar una figura
     */
    private JButton botonBorrar;

    /**
     * Es el grupo de los botones
     */
    private ButtonGroup grupo;

    /**
     * Etiqueta Color de Línea
     */
    private JLabel etiquetaColorLinea;

    /**
     * Etiqueta Color de Fondo
     */
    private JLabel etiquetaColorFondo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param ip Es una referencia a la clase principal de la interfaz
     */
    public PanelBotones( InterfazPaint ip )
    {
        principal = ip;

        setBorder( new TitledBorder( "" ) );

        botonSeleccionar = new JToggleButton( new ImageIcon( "./data/flecha.gif" ) );
        botonSeleccionar.setPreferredSize( new Dimension( 40, 40 ) );
        botonSeleccionar.setToolTipText( "Seleccionar" );

        botonRectangulo = new JToggleButton( new ImageIcon( "./data/rect.gif" ) );
        botonRectangulo.setPreferredSize( new Dimension( 40, 40 ) );
        botonRectangulo.setToolTipText( "Rectángulo" );

        botonOvalo = new JToggleButton( new ImageIcon( "./data/oval.gif" ) );
        botonOvalo.setPreferredSize( new Dimension( 40, 40 ) );
        botonOvalo.setToolTipText( "Óvalo" );

        botonLinea = new JToggleButton( new ImageIcon( "./data/linea.gif" ) );
        botonLinea.setPreferredSize( new Dimension( 40, 40 ) );
        botonLinea.setToolTipText( "Línea" );

        cbbTipoLinea = new JComboBox( );
        TipoLineaRenderer renderer = new TipoLineaRenderer( );
        renderer.setPreferredSize( new Dimension( 40, 20 ) );
        cbbTipoLinea.setRenderer( renderer );

        botonColorFondo = new JButton( " " );
        botonColorFondo.setActionCommand( COLOR_FONDO );
        botonColorFondo.setBackground( Color.RED );
        botonColorFondo.addActionListener( this );

        botonColorLinea = new JButton( " " );
        botonColorLinea.setActionCommand( COLOR_LINEA );
        botonColorLinea.setBackground( Color.BLACK );
        botonColorLinea.addActionListener( this );

        botonBorrar = new JButton( new ImageIcon( "./data/del.gif" ) );
        botonBorrar.setPreferredSize( new Dimension( 40, 40 ) );
        botonBorrar.setActionCommand( BORRAR );
        botonBorrar.addActionListener( this );
        botonBorrar.setToolTipText( "Borrar" );

        botonFiguraNueva1 = new JToggleButton( new ImageIcon( "./data/nuevaFigura.gif" ) );
        botonFiguraNueva1.setPreferredSize( new Dimension( 40, 40 ) );
        botonFiguraNueva1.setToolTipText( "Figura por definir" );

        botonFiguraNueva2 = new JToggleButton( new ImageIcon( "./data/nuevaFigura.gif" ) );
        botonFiguraNueva2.setPreferredSize( new Dimension( 40, 40 ) );
        botonFiguraNueva2.setToolTipText( "Figura por definir" );

        botonFiguraNueva3 = new JToggleButton( new ImageIcon( "./data/nuevaFigura.gif" ) );
        botonFiguraNueva3.setPreferredSize( new Dimension( 40, 40 ) );
        botonFiguraNueva3.setToolTipText( "Figura por definir" );

        etiquetaColorLinea = new JLabel( "Color Línea" );
        etiquetaColorFondo = new JLabel( "Color Fondo" );

        grupo = new ButtonGroup( );
        grupo.add( botonSeleccionar );
        grupo.add( botonRectangulo );
        grupo.add( botonOvalo );
        grupo.add( botonLinea );
        grupo.add( botonFiguraNueva1 );
        grupo.add( botonFiguraNueva2 );
        grupo.add( botonFiguraNueva3 );

        setLayout( new GridBagLayout( ) );
        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 3, 3, 3, 3 ), 0, 0 );

        add( botonSeleccionar, gbc );

        gbc.gridx = 1;
        add( botonBorrar, gbc );

        gbc.gridx = 0;
        gbc.gridy = 2;
        add( botonRectangulo, gbc );

        gbc.gridx = 1;
        add( botonOvalo, gbc );

        gbc.gridx = 0;
        gbc.gridy = 3;
        add( botonLinea, gbc );

        gbc.gridx = 1;
        add( botonFiguraNueva1, gbc );

        gbc.gridx = 0;
        gbc.gridy = 4;
        add( botonFiguraNueva2, gbc );

        gbc.gridx = 1;
        add( botonFiguraNueva3, gbc );

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add( cbbTipoLinea, gbc );

        // Botones de Colores
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridy = 7;

        add( botonColorLinea, gbc );
        gbc.gridy = 9;
        gbc.gridx = 0;
        add( botonColorFondo, gbc );

        // Etiquetas
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.SOUTH;

        gbc.gridy = 6;
        gbc.gridx = 0;

        add( etiquetaColorLinea, gbc );

        gbc.gridy = 8;
        gbc.gridx = 0;
        add( etiquetaColorFondo, gbc );

        inicializarTiposLinea( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa los tipos de línea disponibles
     */
    private void inicializarTiposLinea( )
    {
        BasicStroke tipo0 = new BasicStroke( 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{ 10f, 0f }, 0 );
        cbbTipoLinea.addItem( tipo0 );

        BasicStroke tipo1 = new BasicStroke( 3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{ 10f, 0f }, 0 );
        cbbTipoLinea.addItem( tipo1 );

        BasicStroke tipo2 = new BasicStroke( 5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{ 10f, 0f }, 0 );
        cbbTipoLinea.addItem( tipo2 );

        BasicStroke tipo3 = new BasicStroke( 10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{ 10f, 0f }, 0 );
        cbbTipoLinea.addItem( tipo3 );

        BasicStroke tipo4 = new BasicStroke( 1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{ 10f, 3f }, 0 );
        cbbTipoLinea.addItem( tipo4 );

        BasicStroke tipo5 = new BasicStroke( 3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{ 10f, 3f }, 0 );
        cbbTipoLinea.addItem( tipo5 );

        BasicStroke tipo6 = new BasicStroke( 5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{ 10f, 3f }, 0 );
        cbbTipoLinea.addItem( tipo6 );

        BasicStroke tipo7 = new BasicStroke( 1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{ 10f, 3f, 3f, 3f }, 0 );
        cbbTipoLinea.addItem( tipo7 );

        BasicStroke tipo8 = new BasicStroke( 3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{ 10f, 3f, 3f, 3f }, 0 );
        cbbTipoLinea.addItem( tipo8 );

        BasicStroke tipo9 = new BasicStroke( 5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{ 10f, 3f, 3f, 3f }, 0 );
        cbbTipoLinea.addItem( tipo9 );
    }

    /**
     * Retorna el tipo de línea seleccionado
     * @return tipoLinea
     */
    public BasicStroke darTipoLinea( )
    {
        return ( BasicStroke )cbbTipoLinea.getSelectedItem( );
    }

    /**
     * Retorna el color elegido para las líneas
     * @return colorLinea
     */
    public Color darColorLinea( )
    {
        return botonColorLinea.getBackground( );
    }

    /**
     * Retorna el color elegido para el fondo de las figuras
     * @return colorFondo
     */
    public Color darColorFondo( )
    {
        return botonColorFondo.getBackground( );
    }

    /**
     * Retorna la figura seleccionada
     * @return Retorna InterfazPaint.OVALO o InterfazPaint.RECTANGULO o InterfazPaint.LINEA o InterfazPaint.SELECCIONAR o InterfazPaint.NINGUNA
     */
    public int darFigura( )
    {
        int tipoSeleccionado = InterfazPaint.NINGUNA;
        if( botonSeleccionar.isSelected( ) )
        {
            tipoSeleccionado = InterfazPaint.SELECCIONAR;
        }
        else if( botonOvalo.isSelected( ) || botonRectangulo.isSelected( ) || botonLinea.isSelected( ) )
        {
            tipoSeleccionado = InterfazPaint.FIGURA;
        }
        return tipoSeleccionado;
    }
    
    /**
     * Agrega una figura dada sus coordenadas.
     * @param x1 La coordenada x superior izquierda 
     * @param y1 La coordenada y superior izquierda
     * @param x2 La coordenada x inferior derecha
     * @param y2 La coordenada y inferior derecha
     */
    public void agregarFigura(int x1, int y1, int x2, int y2){
    	IFigura figura = null;
    	if( botonOvalo.isSelected( ) )
        {
            figura = new Ovalo(x1, y1, x2, y2, darColorLinea(), darColorFondo(), darTipoLinea());
        }
        else if(  botonRectangulo.isSelected( ) )
        {
        	figura = new Rectangulo(x1, y1, x2, y2, darColorLinea(), darColorFondo(), darTipoLinea());
        }
        else if(  botonLinea.isSelected( ) )
        {
            figura = new Linea(x1, y1, x2, y2, darColorLinea(), darTipoLinea());
        }
    	if(figura != null){
    		principal.agregarFigura(figura);
    	}
    }

    /**
     * Es el método que se llama cuando se hace click sobre un botón
     * @param evento Es el evento del click sobre un botón- evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( COLOR_LINEA.equals( comando ) )
        {
            Color colorLinea = JColorChooser.showDialog( this, "Color de la línea", botonColorLinea.getBackground( ) );
            if( colorLinea != null )
                botonColorLinea.setBackground( colorLinea );
        }
        else if( COLOR_FONDO.equals( comando ) )
        {
            Color colorFondo = JColorChooser.showDialog( this, "Color del fondo", botonColorFondo.getBackground( ) );
            if( colorFondo != null )
                botonColorFondo.setBackground( colorFondo );
        }
        else if( BORRAR.equals( comando ) )
        {
            principal.eliminarFiguraSeleccionada( );
        }
    }

}