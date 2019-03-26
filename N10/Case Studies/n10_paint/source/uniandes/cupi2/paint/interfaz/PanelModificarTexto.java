/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelModificarTexto.java,v 1.5 2006/10/02 17:14:35 da-romer Exp $
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

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * Este es el panel en el que se modifican las propiedades del texto de una figura
 */
public class PanelModificarTexto extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String FUENTE = "Fuente";

    private static final String TAMANO = "Tamaño";

    private static final String ESTILO = "Estilo";

    private static final String CANCELAR = "Cancelar";

    private static final String OK = "Ok";

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es una referencia al diálogo que contiene este panel
     */
    private DialogoTexto dialogo;

    /**
     * Es el comboBox para seleccionar la fuente
     */
    private JComboBox cbbFuente;

    /**
     * Es el comboBox para seleccionar el tamaño de la fuente
     */
    private JComboBox cbbTamano;

    /**
     * Es el comboBox para seleccionar el estilo de la fuente (normal, negrita, itálica)
     */
    private JComboBox cbbEstilo;

    /**
     * Es el campo de texto donde se muestra el texto que quedará en la figura
     */
    private JTextField txtTexto;

    /**
     * Es el botón Ok
     */
    private JButton botonOk;

    /**
     * Es el botón Cancelar
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el panel
     * @param dt Es una referencia a la clase principal de la interfaz
     * @param fuentes Es una lista con los nombres de las fuentes instaladas en el sistema
     */
    public PanelModificarTexto( DialogoTexto dt, ArrayList fuentes )
    {
        dialogo = dt;

        setLayout( new GridBagLayout( ) );

        // Inicializa los componentes
        inicializarComponentes( fuentes );

        // Ubica los componentes
        ubicarComponentes( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ubica los componentes en sus posiciones. <br>
     * <b>pre: </b>Los componentes ya están inicializados
     */
    private void ubicarComponentes( )
    {
        GridBagConstraints gbc;

        gbc = new GridBagConstraints( 0, 0, 3, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtTexto, gbc );

        gbc = new GridBagConstraints( 0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbFuente, gbc );

        gbc = new GridBagConstraints( 1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbTamano, gbc );

        gbc = new GridBagConstraints( 2, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbEstilo, gbc );

        JPanel panelBotones = new JPanel( );
        panelBotones.add( botonOk );
        panelBotones.add( botonCancelar );
        gbc = new GridBagConstraints( 0, 2, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelBotones, gbc );
    }

    /**
     * Inicializa los componentes
     * @param fuentes Es una lista con los nombres de las fuentes instaladas en el sistema
     */
    private void inicializarComponentes( ArrayList fuentes )
    {
        cbbFuente = new JComboBox( );
        cbbFuente.setActionCommand( FUENTE );
        for( int i = 0; i < fuentes.size( ); i++ )
        {
            String nombreFuente = ( String )fuentes.get( i );
            cbbFuente.addItem( nombreFuente );
        }
        cbbFuente.addActionListener( this );

        cbbTamano = new JComboBox( );
        cbbTamano.setActionCommand( TAMANO );
        for( int i = 8; i <= 32; i += 2 )
        {
            cbbTamano.addItem( new Integer( i ) );
        }
        cbbTamano.addActionListener( this );

        cbbEstilo = new JComboBox( );
        cbbEstilo.setActionCommand( TAMANO );
        cbbEstilo.addItem( "Normal" );
        cbbEstilo.addItem( "Negrita" );
        cbbEstilo.addItem( "Itálica" );
        cbbEstilo.addActionListener( this );

        txtTexto = new JTextField( );

        botonOk = new JButton( "Ok" );
        botonOk.setActionCommand( OK );
        botonOk.addActionListener( this );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
    }

    /**
     * Cambia la fuente del texto mostrado por la seleccionada actualmente en los combo boxes.
     */
    public void actualizarFuente( )
    {
        String nombreFuente = ( String )cbbFuente.getSelectedItem( );
        int tamano = ( ( Integer )cbbTamano.getSelectedItem( ) ).intValue( );
        int numEstilo = cbbEstilo.getSelectedIndex( );

        int estilo = -1;
        switch( numEstilo )
        {
            case 0:
                estilo = Font.PLAIN;
                break;
            case 1:
                estilo = Font.BOLD;
                break;
            case 2:
                estilo = Font.ITALIC;
                break;
            default:
                estilo = Font.PLAIN;
        }

        Font fuente = new Font( nombreFuente, estilo, tamano );
        txtTexto.setFont( fuente );
    }

    /**
     * Cambia el texto mostrado y actualiza los combo box para mostrar la fuente indicada
     * @param texto El texto que se va a mostrar
     * @param fuente La fuente actual del texto
     */
    public void cambiarTexto( String texto, Font fuente )
    {
        txtTexto.setText( texto );

        String nombreFuente = fuente.getFamily( );
        int estilo = fuente.getStyle( );
        int tamano = fuente.getSize( );

        boolean encontre = false;
        for( int i = 0; i < cbbFuente.getItemCount( ) && !encontre; i++ )
        {
            String nombre = ( String )cbbFuente.getItemAt( i );
            if( nombre.equals( nombreFuente ) )
            {
                encontre = true;
                cbbFuente.setSelectedIndex( i );
            }
        }

        encontre = false;
        for( int i = 0; i < cbbTamano.getItemCount( ) && !encontre; i++ )
        {
            Integer num = ( Integer )cbbTamano.getItemAt( i );
            if( tamano == num.intValue( ) )
            {
                encontre = true;
                cbbTamano.setSelectedIndex( i );
            }
        }

        switch( estilo )
        {
            case Font.PLAIN:
                cbbEstilo.setSelectedIndex( 0 );
                break;
            case Font.BOLD:
                cbbEstilo.setSelectedIndex( 1 );
                break;
            case Font.ITALIC:
                cbbEstilo.setSelectedIndex( 2 );
                break;
            default:
                cbbEstilo.setSelectedIndex( 0 );
        }
    }

    /**
     * Es el método que ejecuta la acción que se debe ejecutar cuando se hace click sobre un botón o cuando cambia la opción seleccionada en uno de los comboBox
     * @param evento Es el evento del click sobre un botón - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( OK.equals( comando ) )
        {
            String texto = txtTexto.getText( );
            Font fuente = txtTexto.getFont( );

            dialogo.cambiarTexto( texto, fuente );
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
        else if( FUENTE.equals( comando ) || TAMANO.equals( comando ) || ESTILO.equals( comando ) )
        {
            actualizarFuente( );
        }
    }

}