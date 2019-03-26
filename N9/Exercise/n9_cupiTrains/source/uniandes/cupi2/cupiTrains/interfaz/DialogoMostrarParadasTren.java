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

import uniandes.cupi2.cupiTrains.world.TrainStop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;



/**
 * Di�logo para mostrar las paradas del tren.
 */
public class DialogoMostrarParadasTren extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel del di�logo.
     */
    private JPanel panelDialogo;

    /**
     * Vector con las etiquetas con las paradas del tren.
     */
    private JLabel[] lblParadas;

    /**
     * Vector con las etiquetas con los horarios del tren.
     */
    private JLabel[] lblHorarios;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el di�logo para mostrar el tren con sus paradas.
     * @param pParadas Paradas del tren. pParadas != null.
     * @param pCantidadParadas Cantidad de paradas del tren. pParadas >= 2.
     * @param pIdTren Id del tren.
     */
    public DialogoMostrarParadasTren( ArrayList pParadas, int pCantidadParadas, int pIdTren )
    {

        int cantidadParadas = pCantidadParadas;

        lblHorarios = new JLabel[cantidadParadas];
        lblParadas = new JLabel[cantidadParadas];

        setSize( 300, 60 + 30 * cantidadParadas );
        setTitle( "Train: " + pIdTren );

        panelDialogo = new JPanel( );
        panelDialogo.setBorder( new TitledBorder( "Stops " + pIdTren ) );
        panelDialogo.setLayout( new GridBagLayout( ) );
        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        ArrayList<TrainStop> paradas = pParadas;

        for( int i = 0; i < cantidadParadas; i++ )
        {
            TrainStop parada = paradas.get( i );
            String nombreParada = parada.getName( );
            String horarioParada = parada.getSchedule( );
            lblParadas[ i ] = new JLabel( nombreParada );
            lblHorarios[ i ] = new JLabel( horarioParada );

            gbc.gridx = 0;
            panelDialogo.add( lblParadas[ i ], gbc );
            gbc.gridx++;
            panelDialogo.add( lblHorarios[ i ], gbc );
            gbc.gridy++;
            gbc.gridx = 0;
        }
        add( panelDialogo );
    }

}
