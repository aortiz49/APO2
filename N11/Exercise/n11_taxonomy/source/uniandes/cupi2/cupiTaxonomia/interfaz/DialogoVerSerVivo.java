/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiTaxonomia
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.cupiTaxonomia.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiTaxonomia.world.SerVivo;

/**
 * Di�logo para ver la informaci�n de un ser vivo.
 */
@SuppressWarnings({ "serial" })
public class DialogoVerSerVivo extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Campo de texto con el nombre com�n del ser vivo.
     */
    private JTextField txtNombreComun;

    /**
     * Campo de texto con el nombre cient�fico del ser vivo.
     */
    private JTextField txtNombreCientifico;

    /**
     * �rea de texto con las caracter�sticas del ser vivo.
     */
    private JTextArea areaCaracteristicas;

    /**
     * Panel con un scroll que contiene a areaCaracteristicas.
     */
    private JScrollPane scrollCaracteristicas;

    /**
     * Etiqueta con la imagen del ser vivo.
     */
    private JLabel labImagen;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un di�logo que muestra la informaci�n de un ser vivo.
     * <b> post: </b> Se crea el di�logo con todos sus elementos gr�ficos.
     * @param pSerVivo Ser vivo cuya informaci�n va a ser mostrada. pSerVivo != null.
     */
    public DialogoVerSerVivo( SerVivo pSerVivo )
    {
        setLayout( new GridBagLayout( ) );
        setSize( 500, 250 );
        setModal( true );
        setTitle( "Ver ser vivo" );
        setResizable( true );

        GridBagConstraints gbc = new GridBagConstraints( );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 2, 2, 2, 2 );

        gbc.gridy = 0;
        gbc.gridheight = 3;
        labImagen = new JLabel( );
        labImagen.setHorizontalAlignment( JLabel.CENTER );
        add( labImagen, gbc );

        try
        {
            BufferedImage img = ImageIO.read( new File( pSerVivo.darRutaImagen( ) ) );
            ImageIcon icono = new ImageIcon( img.getScaledInstance( 200, 200, Image.SCALE_SMOOTH ) );
            labImagen.setIcon( icono );
        }
        catch( IOException e )
        {
            // No es posible poner la imagen
        }

        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        areaCaracteristicas = new JTextArea( pSerVivo.darCaracteristicas( ) );
        areaCaracteristicas.setLineWrap( true );
        areaCaracteristicas.setEditable( false );

        gbc.gridwidth = 2;
        scrollCaracteristicas = new JScrollPane( areaCaracteristicas );
        scrollCaracteristicas.setSize( new Dimension( 250, 100 ) );
        scrollCaracteristicas.setPreferredSize( new Dimension( 250, 100 ) );
        scrollCaracteristicas.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scrollCaracteristicas.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scrollCaracteristicas.setBorder( new TitledBorder( " Caracter�sticas: " ) );
        add( scrollCaracteristicas, gbc );

        gbc.gridwidth = 1;
        gbc.gridy = 0;
        add( new JLabel( " Nombre com�n: " ), gbc );

        gbc.gridy = 1;
        add( new JLabel( " Nombre cient�fico: " ), gbc );

        gbc.gridx = 2;
        gbc.gridy = 0;
        txtNombreComun = new JTextField( pSerVivo.darNombreComun( ) );
        txtNombreComun.setEditable( false );
        add( txtNombreComun, gbc );

        gbc.gridy = 1;
        txtNombreCientifico = new JTextField( pSerVivo.darNombreCientifico( ) );
        txtNombreCientifico.setEditable( false );
        add( txtNombreCientifico, gbc );
    }
}