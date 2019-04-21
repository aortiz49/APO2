/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelGraficoOrganigrama.java,v 1.8 2006/10/30 15:49:33 da-romer Exp $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 *
 * Proyecto Cupi2 
 * Ejercicio: n11_organigrama 
 * Autor: Mario Sánchez - 21/11/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.organigrama.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import uniandes.cupi2.organigrama.mundo.Cargo;
import uniandes.cupi2.organigrama.mundo.Empresa;

/**
 * Es el panel donde se muestra gráficamente el organigrama
 */
public class PanelGraficoOrganigrama extends JPanel
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El ancho en pixeles de la representación de un empleado
     */
    private static final int ANCHO = 100;

    /**
     * El alto en pixeles de la representación de un empleado
     */
    private static final int ALTO = 50;

    /**
     * El tamaño de los bordes izquierdo y derecho
     */
    private static final int BORDE_X = 5;

    /**
     * El tamaño de los bordes superior e inferior
     */
    private static final int BORDE_Y = 20;

    /**
     * El color del borde de las cajas
     */
    private static final Color COLOR_BORDE = Color.BLACK;

    /**
     * El color de las cajas no seleccionadas
     */
    private static final Color COLOR_FONDO = new Color( 255, 254, 170 );

    /**
     * El color de la caja del empleado seleccionado
     */
    private static final Color COLOR_FONDO_SELECCIONADO = new Color( 139, 219, 245 );

    /**
     * El color de las líneas que unen a los empleados
     */
    private static final Color COLOR_LINEAS = new Color( 89, 89, 89 );

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La imagen generada con el organigrama
     */
    private BufferedImage imagenOrganigrama;

    /**
     * El icono asociado a cada empleado
     */
    private BufferedImage imagenEmpresario;

    /**
     * Es la referencia a la empresa que debe ser dibujada
     */
    private Empresa empresa;

    /**
     * El identificador del empleado seleccionado
     */
    private String idSeleccionado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus atributos
     * @param emp Es una referencia a la empresa, cuyo organigrama debe mostrarse en el panel
     */
    public PanelGraficoOrganigrama( Empresa emp )
    {
        setDoubleBuffered( true );
        empresa = emp;
        try
        {
            imagenEmpresario = ImageIO.read( new File( "./data/empresario.gif" ) );
        }
        catch( IOException e )
        {
            imagenEmpresario = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Marca como seleccionado un empleado particular
     * @param seleccionado El empleado que debe ser marcado como seleccionado
     */
    public void seleccionar( String seleccionado )
    {
        idSeleccionado = seleccionado;
    }

    /**
     * Este método se encarga de repintar el panel copiando la imagen generada
     * @param g La superficie del panel donde debe pintarse la imagen
     */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        dibujarOrganigrama( g );
    }

    /**
     * Dibuja la imagen generada sobre la superficie
     * @param g La superficie del panel donde debe pintarse la imagen
     */
    private void dibujarOrganigrama( Graphics g )
    {
        setBackground( Color.WHITE );
        g.clearRect( 0, 0, getWidth( ), getHeight( ) );

        if( imagenOrganigrama == null )
            actualizarImagen( );

        // Copiar la imagen generada
        if( imagenOrganigrama.getWidth( ) > getWidth( ) )
            g.drawImage( imagenOrganigrama, 0, 0, null );
        else
        {
            g.drawImage( imagenOrganigrama, ( getWidth( ) - imagenOrganigrama.getWidth( ) ) / 2, 0, null );
        }
    }

    /**
     * Actualiza la imagen generada con la información actual del organigrama
     */
    public void actualizarImagen( )
    {
        if( empresa != null )
        {
            Cargo cabeza = empresa.darCabeza( );
            if( cabeza != null )
            {
                imagenOrganigrama = dibujarEmpleadoConSubalternos( cabeza );
                setPreferredSize( new Dimension( imagenOrganigrama.getWidth( ), imagenOrganigrama.getHeight( ) ) );
                setSize( new Dimension( imagenOrganigrama.getWidth( ), imagenOrganigrama.getHeight( ) ) );
            }
            else
            {
                imagenOrganigrama = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
                setSize( new Dimension( getWidth( ) - 1, getHeight( ) - 1 ) );

            }
        }
    }

    /**
     * Este método se encarga de dibujar un empleado y a todos los que están debajo en la organización
     * @param elem El empleado que debe ser dibujado
     * @return Retorna la imagen del empleado (y todos su subalternos)
     */
    private BufferedImage dibujarEmpleadoConSubalternos( Cargo elem )
    {
        if( elem.esHoja( ) )
        {
            return dibujarCargo( elem );
        }
        else
        {
            ArrayList hijos = elem.darSubAlternos( );
            ArrayList imagenes = new ArrayList( );

            int ancho = 0;
            int alto = 0;

            for( int i = 0; i < hijos.size( ); i++ )
            {
                Cargo hijo = ( Cargo )hijos.get( i );
                BufferedImage imagenHijo = dibujarEmpleadoConSubalternos( hijo );
                ancho += imagenHijo.getWidth( );
                alto = Math.max( alto, imagenHijo.getHeight( ) );
                imagenes.add( imagenHijo );
            }

            alto += ALTO + BORDE_Y * 2;
            BufferedImage imagenElemento = dibujarCargo( elem );

            // Crear la imagen que va a contener al elemento y a sus hijos
            BufferedImage imagenCompleta = new BufferedImage( ancho, alto, BufferedImage.TYPE_INT_RGB );
            Graphics2D superficie = imagenCompleta.createGraphics( );
            superficie.setColor( Color.WHITE );
            superficie.fillRect( 0, 0, ancho, alto );

            // Dibujar el elemento
            int posElemento = ( ancho / 2 ) - ( ANCHO / 2 ) - BORDE_X;
            superficie.drawImage( imagenElemento, posElemento, 0, null );

            // Agregar a la imagen completa las imágenes de cada uno de los hijos
            int posXActual = 0;
            int posYActual = ALTO + BORDE_Y * 2;

            for( int i = 0; i < imagenes.size( ); i++ )
            {
                BufferedImage imagenHijo = ( BufferedImage )imagenes.get( i );
                superficie.drawImage( imagenHijo, posXActual, posYActual, null );
                int anchoHijo = imagenHijo.getWidth( );

                posXActual += anchoHijo;
            }

            posXActual = 0;
            // Dibujar las líneas
            for( int i = 0; i < imagenes.size( ); i++ )
            {
                BufferedImage imagenHijo = ( BufferedImage )imagenes.get( i );
                int anchoHijo = imagenHijo.getWidth( );
                superficie.setPaint( COLOR_LINEAS );
                superficie.drawLine( ancho / 2, ALTO + BORDE_Y, posXActual + anchoHijo / 2, posYActual + BORDE_Y );
                posXActual += anchoHijo;
            }

            return imagenCompleta;
        }

    }

    /**
     * Este método se encarga de dibujar un empleado (sin dibujar subalternos si los tiene)
     * @param cargo El empleado que debe ser dibujado
     * @return Retorna una imagen donde está dibujado el empleado
     */
    private BufferedImage dibujarCargo( Cargo cargo )
    {
        // Crear la superficie para dibujar el elemento
        BufferedImage imagen = new BufferedImage( ANCHO + BORDE_X * 2, ALTO + BORDE_Y * 2, BufferedImage.TYPE_INT_RGB );
        Graphics2D g = imagen.createGraphics( );
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, ANCHO + BORDE_X * 2, ALTO + BORDE_Y * 2 );

        // Dibujar el rectángulo
        Rectangle2D rec = new Rectangle2D.Double( BORDE_X, BORDE_Y, ANCHO, ALTO );
        if( cargo.darNombreCargo( ).equals( idSeleccionado ) )
        {
            g.setPaint( COLOR_FONDO_SELECCIONADO );
        }
        else
        {
            g.setPaint( COLOR_FONDO );
        }
        g.fill( rec );
        g.setPaint( COLOR_BORDE );
        g.draw( rec );

        // Dibujar el icono si el cargo está ocupado
        if( cargo.darEmpleado( ) != null )
            g.drawImage( imagenEmpresario, 5, 15, null );

        // Dibujar el texto
        String texto = cargo.darNombreCargo( ).substring( 0, Math.min( 18, cargo.darNombreCargo( ).length( ) ) );
        int centroX = Math.abs( ANCHO + BORDE_X * 2 ) / 2;
        int centroY = Math.abs( ALTO + BORDE_Y * 2 ) / 2;

        Font fuente = new Font( "Arial", Font.PLAIN, 11 );
        g.setFont( fuente );
        g.setColor( Color.BLACK );

        FontMetrics metrics = g.getFontMetrics( );
        int ancho = metrics.stringWidth( texto );
        g.drawString( texto, centroX - ancho / 2, centroY + 5 );

        return imagen;
    }

}
