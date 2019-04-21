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

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;

import uniandes.cupi2.cupiTaxonomia.world.ArbolTaxonomico;
import uniandes.cupi2.cupiTaxonomia.world.Taxon;

/**
 * Ventana principal de la aplicaci�n.
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class InterfazCupiTaxonomia extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa la ruta del archivo con la informaci�n del �rbol taxon�mico.
     */
    private static final String RUTA_ARCHIVO = "./data/taxonomicTree1.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del world.
     */
    private ArbolTaxonomico arbol;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la imagen encabezado.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con las opciones de extensi�n.
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la lista de taxones.
     */
    private PanelTaxones panelTaxones;

    /**
     * Panel con la lista de seres vivos.
     */
    private PanelSeresVivos panelSeresVivos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal. <br>
     * <b>post: </b> Se ha creado la ventana con todos sus p�neles.
     */
    public InterfazCupiTaxonomia( )
    {
        // Crea la clase principal
        try
        {
            arbol = new ArbolTaxonomico(RUTA_ARCHIVO );

            // Construye la forma
            setLayout( new BorderLayout( ) );
            setSize( 650, 530 );
            setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
            setTitle( " CupiTaxonom�a " );
            setResizable( false );

            // Creaci�n de los paneles aqu�
            panelImagen = new PanelImagen( );
            add( panelImagen, BorderLayout.NORTH );

            JPanel aux = new JPanel( );
            aux.setLayout( new BorderLayout( ) );

            panelTaxones = new PanelTaxones( this );
            aux.add( panelTaxones, BorderLayout.WEST );

            panelSeresVivos = new PanelSeresVivos( );
            aux.add( panelSeresVivos, BorderLayout.CENTER );

            add( aux, BorderLayout.CENTER );

            panelExtension = new PanelExtension( this );
            add( panelExtension, BorderLayout.SOUTH );

            // Centrar la ventana
            setLocationRelativeTo( null );

            actualizarTaxones( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "CupiTaxonomia", JOptionPane.ERROR_MESSAGE );
            System.exit( ERROR );
        }
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de taxones mostrados.
     */
    private void actualizarTaxones( )
    {
        panelTaxones.refrescarArbol( arbol.darTaxonRaiz( ) );
        panelExtension.actualizar( arbol.darNumTaxones( ), arbol.darNumSeresVivos( ) );
    }

    /**
     * Muestra la lista de taxones en preorden.
     */
    public void verTaxonesPreorden( )
    {
        ArrayList taxones = arbol.darTaxonesPreorden( );
        DialogoVerTaxones dialogo = new DialogoVerTaxones( taxones );
        dialogo.setLocationRelativeTo( this );
        dialogo.setVisible( true );
    }

    /**
     * Muestra la lista de taxones en postorden.
     */
    public void verTaxonesPostorden( )
    {
        ArrayList taxones = arbol.darTaxonesPostorden( );
        DialogoVerTaxones dialogo = new DialogoVerTaxones( taxones );
        dialogo.setLocationRelativeTo( this );
        dialogo.setVisible( true );
    }

    /**
     * Actualiza la lista de seres vivos con los seres del tax�n dado
     * @param pTaxon Tax�n del �rbol taxon�mico. pTaxon != null
     */
    public void actualizarSeresVivos( Taxon pTaxon )
    {
        ArrayList seres = arbol.darSeresVivos( pTaxon.darTipo( ), pTaxon.darNombre( ) );
        panelSeresVivos.refrescar( seres );
    }

    /**
     * Agrega un nuevo tax�n al �rbol taxon�mico.
     * @param pNombrePadre Nombre del tax�n padre del tax�n a agregar. pNombrePadre != null y pNombrePadre != "".
     * @param pTipo Tipo del tax�n a agregar. pTipo pertenece a {Taxon.LUCA, Taxon.DOMINIO, Taxon.REINO, Taxon.FILO, Taxon.CLASE, Taxon.ORDEN, Taxon.FAMILIA, Taxon.GENERO, Taxon.ESPECIE}.
     * @param pNombreTaxon Nombre del tax�n a agregar. pNombreTaxon != null y pNombreTaxon != "".
     */
    public void agregarTaxon( String pNombrePadre, int pTipo, String pNombreTaxon )
    {
        try
        {
            arbol.agregarTaxon( pNombrePadre, pTipo, pNombreTaxon );
            actualizarTaxones( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregar tax�n", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Elimina el tax�n con el nombre y el tipo dado del �rbol taxon�mico.
     * @param pTipo Tipo del tax�n a eliminar. pTipo pertenece a {Taxon.LUCA, Taxon.DOMINIO, Taxon.REINO, Taxon.FILO, Taxon.CLASE, Taxon.ORDEN, Taxon.FAMILIA, Taxon.GENERO, Taxon.ESPECIE}.
     * @param pNombre Nombre del tax�n a eliminar. pNombre != null y pNombre != "".
     */
    public void eliminarTaxon( int pTipo, String pNombre )
    {
        arbol.eliminarTaxon( pTipo, pNombre );
        actualizarTaxones( );
    }

    /**
     * Agrega un ser vivo al �rbol taxon�mico. <br>
     * <b> pre: </b> El tax�n al que se va a gregar el ser vivo es de tipo Especie.
     * @param pNombreTaxon Nombre del tax�n al que se va a agregar el ser vivo. pNombreTaxon != null y pNombreTaxon != "".
     * @param pNombreComun Nombre com�n del ser vivo. pNombreComun != null y pNombreComun != "".
     * @param pNombreCientifico Nombre cient�fico del ser vivo. pNombreCientifico != null y pNombreCientifico != "".
     * @param pCaracteristicas Caracter�sticas del ser vivo. pCaracteristicas != null y pCaracteristicas != "".
     * @param pImagen Ruta con la imagen del ser vivo. pImagen != null y pImagen != "".
     */
    public void agregarSerVivo( String pNombreTaxon, String pNombreComun, String pNombreCientifico, String pCaracteristicas, String pImagen )
    {
        try
        {
            arbol.agregarSerVivo( pNombreTaxon, pNombreComun, pNombreCientifico, pCaracteristicas, pImagen );
            actualizarTaxones( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Agregar ser vivo", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Retorna la lista con los nombres de los taxones del tipo dado.
     * @param pTipo Tipo de los taxones. pTipo pertenece a {Taxon.LUCA, Taxon.DOMINIO, Taxon.REINO, Taxon.FILO, Taxon.CLASE, Taxon.ORDEN, Taxon.FAMILIA, Taxon.GENERO, Taxon.ESPECIE}.
     * @return Lista con los nombre de los taxones del tipo dado.
     */
    public ArrayList<String> darTaxonesTipo( int pTipo )
    {
        return arbol.darTaxonesTipo( pTipo );
    }

    /**
     * M�todo que se llama cuando se cierra la ventana principal de la aplicaci�n.<br>
     * Antes de cerrar se guarda el estado del programa.<br>
     * Si se produce un error se muestra un mensaje que informe al usuario.
     */
    public void dispose( )
    {
        try
        {
            arbol.guardar( RUTA_ARCHIVO );
            super.dispose( );
        }
        catch( Exception e )
        {
            setVisible( true );
            int respuesta = JOptionPane.showConfirmDialog( this, e.getMessage( ) + "\n �Quiere cerrar el programa sin salvar?", "CupiTaxonomia", JOptionPane.YES_NO_OPTION );
            if( respuesta == JOptionPane.YES_OPTION )
            {
                super.dispose( );
            }
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = arbol.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = arbol.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
     * @param pArgs Argumentos de la aplicaci�n. pArgs != null.
     */
    public static void main( String[] pArgs )
    {
        // Unifica la interfaz para Mac y para Windows.
        try 
        {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        InterfazCupiTaxonomia interfaz = new InterfazCupiTaxonomia( );
        interfaz.setVisible( true );
    }
}
