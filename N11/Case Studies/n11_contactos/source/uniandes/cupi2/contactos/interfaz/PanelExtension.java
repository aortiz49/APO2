/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.6 2006/10/23 15:09:19 da-romer Exp $ 
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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Es el panel donde se encuentran los botones para agregar un contacto o cerrar el diálogo
 */
public class PanelExtension extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando Opción 3
     */
    private static final String OPCION_3 = "OPCION_3";

    /**
     * Comando Opción 4
     */
    private static final String OPCION_4 = "OPCION_4";

    /**
     * Comando Opción 5
     */
    private static final String OPCION_5 = "OPCION_5";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia al diálogo donde se escriben los datos del nuevo contacto
     */
    private DialogoEstadisticas dialogo;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Texto opción 1
     */
    private JTextField textoOpcion1;

    /**
     * Texto opción 2
     */
    private JTextField textoOpcion2;

    /**
     * Texto opción 3
     */
    private JTextField textoOpcion3;

    /**
     * Texto opción 4
     */
    private JTextField textoOpcion4;

    /**
     * Texto opción 5
     */
    private JTextField textoOpcion5;

    /**
     * Etiqueta opción 1
     */
    private JLabel etiquetaOpcion1;

    /**
     * Etiqueta opción 2
     */
    private JLabel etiquetaOpcion2;

    /**
     * Etiqueta opción 3
     */
    private JLabel etiquetaOpcion3;

    /**
     * Etiqueta opción 4
     */
    private JLabel etiquetaOpcion4;

    /**
     * Etiqueta opción 5
     */
    private JLabel etiquetaOpcion5;

    /**
     * Botón opción 1
     */
    private JButton botonOpcion1;

    /**
     * Botón opción 2
     */
    private JButton botonOpcion2;

    /**
     * Botón opción 3
     */
    private JButton botonOpcion3;

    /**
     * Botón opción 4
     */
    private JButton botonOpcion4;

    /**
     * Botón opción 5
     */
    private JButton botonOpcion5;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel
     * @param dc Es una referencia al diálogo donde se escriben los datos del nuevo contacto
     */
    public PanelExtension( DialogoEstadisticas dc )
    {
        dialogo = dc;

        setLayout( new GridBagLayout( ) );
        setBorder( new TitledBorder( "Opciones" ) );

        etiquetaOpcion1 = new JLabel( "Valor" );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( etiquetaOpcion1, gbc );

        textoOpcion1 = new JTextField( );
        textoOpcion1.setPreferredSize( new Dimension( 100, 20 ) );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( textoOpcion1, gbc );

        botonOpcion1 = new JButton( "Opción 1" );
        botonOpcion1.addActionListener( this );
        botonOpcion1.setActionCommand( OPCION_1 );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 0, 5, 0 );
        add( botonOpcion1, gbc );

        etiquetaOpcion2 = new JLabel( "Valor" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( etiquetaOpcion2, gbc );

        textoOpcion2 = new JTextField( );
        textoOpcion2.setPreferredSize( new Dimension( 100, 20 ) );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( textoOpcion2, gbc );

        botonOpcion2 = new JButton( "Opción 2" );
        botonOpcion2.addActionListener( this );
        botonOpcion2.setActionCommand( OPCION_2 );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets( 0, 0, 5, 0 );
        add( botonOpcion2, gbc );

        etiquetaOpcion3 = new JLabel( "Valor" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( etiquetaOpcion3, gbc );

        textoOpcion3 = new JTextField( );
        textoOpcion3.setPreferredSize( new Dimension( 100, 20 ) );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( textoOpcion3, gbc );

        botonOpcion3 = new JButton( "Opción 3" );
        botonOpcion3.addActionListener( this );
        botonOpcion3.setActionCommand( OPCION_3 );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets( 0, 0, 5, 0 );
        add( botonOpcion3, gbc );

        etiquetaOpcion4 = new JLabel( "Valor" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( etiquetaOpcion4, gbc );

        textoOpcion4 = new JTextField( );
        textoOpcion4.setPreferredSize( new Dimension( 100, 20 ) );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( textoOpcion4, gbc );

        botonOpcion4 = new JButton( "Opción 4" );
        botonOpcion4.addActionListener( this );
        botonOpcion4.setActionCommand( OPCION_4 );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets( 0, 0, 5, 0 );
        add( botonOpcion4, gbc );

        etiquetaOpcion5 = new JLabel( "Valor" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( etiquetaOpcion5, gbc );

        textoOpcion5 = new JTextField( );
        textoOpcion5.setPreferredSize( new Dimension( 100, 20 ) );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets( 0, 0, 5, 5 );
        add( textoOpcion5, gbc );

        botonOpcion5 = new JButton( "Opción 5" );
        botonOpcion5.addActionListener( this );
        botonOpcion5.setActionCommand( OPCION_5 );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 4;
        add( botonOpcion5, gbc );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ejecuta la acción correspondiente al botón que fue presionado
     * @param evento El evento del click sobre uno de los botones. evento != null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( OPCION_1.equals( comando ) )
        {
            dialogo.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( comando ) )
        {
            dialogo.reqFuncOpcion2( );
        }
        else if( OPCION_3.equals( comando ) )
        {
            dialogo.reqFuncOpcion3( );
        }
        else if( OPCION_4.equals( comando ) )
        {
            dialogo.reqFuncOpcion4( );
        }
        else if( OPCION_5.equals( comando ) )
        {
            dialogo.reqFuncOpcion5( );
        }
    }

    /**
     * El valor asociado con la opción 1
     * @return El valor asociado con la opción 1
     */
    public String darTextoOpcion1( )
    {
        return textoOpcion1.getText( );
    }

    /**
     * El valor asociado con la opción 2
     * @return El valor asociado con la opción 2
     */
    public String darTextoOpcion2( )
    {
        return textoOpcion2.getText( );
    }

    /**
     * El valor asociado con la opción 3
     * @return El valor asociado con la opción 3
     */
    public String darTextoOpcion3( )
    {
        return textoOpcion1.getText( );
    }

    /**
     * El valor asociado con la opción 4
     * @return El valor asociado con la opción 4
     */
    public String darTextoOpcion4( )
    {
        return textoOpcion4.getText( );
    }

    /**
     * El valor asociado con la opción 5
     * @return El valor asociado con la opción 5
     */
    public String darTextoOpcion5( )
    {
        return textoOpcion5.getText( );
    }

    /**
     * Modifica el texto de la opción 1
     * @param texto El nuevo texto
     */
    public void cambiarTextoOpcion1( String texto )
    {
        textoOpcion1.setText( texto );
    }

    /**
     * Modifica el texto de la opción
     * @param texto El nuevo texto 2
     */
    public void cambiarTextoOpcion2( String texto )
    {
        textoOpcion2.setText( texto );
    }

    /**
     * Modifica el texto de la opción
     * @param texto El nuevo texto 3
     */
    public void cambiarTextoOpcion3( String texto )
    {
        textoOpcion3.setText( texto );
    }

    /**
     * Modifica el texto de la opción
     * @param texto El nuevo texto 4
     */
    public void cambiarTextoOpcion4( String texto )
    {
        textoOpcion4.setText( texto );
    }

    /**
     * Modifica el texto de la opción
     * @param texto El nuevo texto 5
     */
    public void cambiarTextoOpcion5( String texto )
    {
        textoOpcion5.setText( texto );
    }
}