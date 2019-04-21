/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiTaxonomia
 * Autor: Equipo Cupi2 2019.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.cupiTaxonomia.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import uniandes.cupi2.cupiTaxonomia.world.Taxon;

/**
 * Panel que contiene el �rbol de taxones.
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class PanelTaxones extends JPanel implements TreeSelectionListener, ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando de eliminar un tax�n.
     */
    private final static String ELIMINAR = "Eliminar";

    /**
     * Constante que representa el comando de mostrar la lista preorden.
     */
    private final static String PREORDEN = "Preorden";

    /**
     * Constante que representa el comando de mostrar la lista postorden.
     */
    private final static String POSTORDEN = "Postorden";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCupiTaxonomia principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * �rbol con los taxones.
     */
    private JTree arbolTaxones;

    /**
     * Modelo del �rbol de taxones.
     */
    private DefaultTreeModel modelo;

    /**
     * Panel con un scroll que contiene a listaTaxones.
     */
    private JScrollPane scroll;

    /**
     * Bot�n para eliminar un tax�n.
     */
    private JButton btnEliminar;

    /**
     * Bot�n para ver taxones en preorden.
     */
    private JButton btnPreorden;

    /**
     * Bot�n para ver taxones en postorden.
     */
    private JButton btnPostorden;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel de visualizaci�n de los taxones.
     * <b> post: </b> Se crea el panel con todos sus elementos gr�ficos.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public PanelTaxones( InterfazCupiTaxonomia pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new BorderLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 5, 0, 5 ), new TitledBorder( " �rbol Taxon�mico " ) ) );
        setPreferredSize( new Dimension( 300, 0 ) );

        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode( "" );
        DefaultTreeModel modelo = new DefaultTreeModel( raiz );

        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer( );
        renderer.setLeafIcon( null );
        renderer.setClosedIcon( null );
        renderer.setOpenIcon( null );

        arbolTaxones = new JTree( modelo );
        arbolTaxones.getSelectionModel( ).setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
        arbolTaxones.addTreeSelectionListener( this );
        arbolTaxones.setCellRenderer( renderer );

        scroll = new JScrollPane( arbolTaxones );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setBorder( new CompoundBorder( new EmptyBorder( 3, 3, 3, 3 ), new LineBorder( Color.BLACK, 1 ) ) );

        btnEliminar = new JButton( ELIMINAR );
        btnEliminar.setActionCommand( ELIMINAR );
        btnEliminar.addActionListener( this );

        JPanel aux = new JPanel( );
        aux.setLayout( new GridLayout( 1, 3 ) );

        btnPreorden = new JButton( PREORDEN );
        btnPreorden.setActionCommand( PREORDEN );
        btnPreorden.setSelected( true );
        btnPreorden.addActionListener( this );

        btnPostorden = new JButton( POSTORDEN );
        btnPostorden.setActionCommand( POSTORDEN );
        btnPostorden.addActionListener( this );

        aux.add( btnEliminar );
        aux.add( btnPreorden );
        aux.add( btnPostorden );

        add( aux, BorderLayout.SOUTH );
        add( scroll, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza el �rbol de taxones.
     * @param pTaxonRaiz Tax�n ra�z del �rbol. pTaxonRaiz != null.
     */
    public void refrescarArbol( Taxon pTaxonRaiz )
    {
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode( pTaxonRaiz );
        modelo = new DefaultTreeModel( raiz );
        refrescar( pTaxonRaiz, raiz );
        arbolTaxones.setModel( modelo );
        TreePath path = arbolTaxones.getPathForRow( raiz.getLevel( ) );
        arbolTaxones.setSelectionPath( path );
    }

    /**
     * Agregar los sub-taxones del tax�n dado al �rbol de taxones.
     * @param pTaxon Tax�n padre. pTaxon != null
     * @param pNodo Nodo al que se van a agregar los taxones. pNodo != null
     */
    private void refrescar( Taxon pTaxon, DefaultMutableTreeNode pNodo )
    {
        ArrayList subTaxones = pTaxon.darSubTaxones( );
        for( int i = 0; i < subTaxones.size( ); i++ )
        {
            Taxon taxonHijo = ( Taxon )subTaxones.get( i );
            DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode( taxonHijo );
            refrescar( taxonHijo, nodoHijo );
            pNodo.add( nodoHijo );
        }
    }

    /**
     * M�todo para atender el evento cuando un usuario selecciona un tax�n de la lista.
     * @param pEvento El evento de selecci�n de un elemento de la lista de taxones. pEvento != null.
     */
    public void valueChanged( TreeSelectionEvent pEvento )
    {
        DefaultMutableTreeNode nodo = ( DefaultMutableTreeNode )arbolTaxones.getLastSelectedPathComponent( );

        if( nodo != null )
        {
            Taxon taxon = ( Taxon )nodo.getUserObject( );
            principal.actualizarSeresVivos( taxon );
        }
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( ELIMINAR ) )
        {
            DefaultMutableTreeNode nodo = ( DefaultMutableTreeNode )arbolTaxones.getLastSelectedPathComponent( );
            if( nodo != null )
            {
                Taxon taxon = ( Taxon )nodo.getUserObject( );
                if( taxon.darTipo( ) != Taxon.LUCA )
                {
                    principal.eliminarTaxon( taxon.darTipo( ), taxon.darNombre( ) );
                }
                else
                {
                    JOptionPane.showMessageDialog( null, "No es posible eliminar el tax�n LUCA.", "Eliminar tax�n", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
        else if( comando.equals( PREORDEN ) )
        {
            principal.verTaxonesPreorden( );
        }
        else if( comando.equals( POSTORDEN ) )
        {
            principal.verTaxonesPostorden( );
        }
    }
}
