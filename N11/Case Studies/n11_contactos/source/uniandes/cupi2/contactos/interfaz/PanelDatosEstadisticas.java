/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDatosEstadisticas.java,v 1.4 2006/10/22 02:21:44 da-romer Exp $ 
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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.contactos.mundo.*;

/**
 * Es el panel donde se muestran los datos estadísticos del directorio
 */
public class PanelDatosEstadisticas extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    private JLabel[] etiquetas;

    private JTextField[] textos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Construye el panel
     * @param datos Los datos estadísticos que se van a desplegar en el panel
     */

    public PanelDatosEstadisticas( DatoEstadistico[] datos )
    {
        setLayout( new GridBagLayout( ) );
        setBorder( new TitledBorder( "Estadísticas" ) );

        etiquetas = new JLabel[datos.length];
        textos = new JTextField[datos.length];

        for( int cont = 0; cont < datos.length; cont++ )
        {
            etiquetas[ cont ] = new JLabel( datos[ cont ].darNombre( ) );
            GridBagConstraints gbc = new GridBagConstraints( );
            gbc.gridx = 0;
            gbc.gridy = cont;
            gbc.insets = new Insets( 0, 0, 5, 5 );
            gbc.fill = GridBagConstraints.BOTH;
            add( etiquetas[ cont ], gbc );

            textos[ cont ] = new JTextField( datos[ cont ].darValor( ) );
            textos[ cont ].setPreferredSize( new Dimension( 100, 20 ) );
            textos[ cont ].setEnabled( false );
            gbc = new GridBagConstraints( );
            gbc.gridx = 1;
            gbc.gridy = cont;
            gbc.insets = new Insets( 0, 0, 5, 0 );
            gbc.fill = GridBagConstraints.BOTH;
            add( textos[ cont ], gbc );
        }
    }
}