/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n11_taxonomicTree
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package uniandes.cupi2.cupiTaxonomy.userInterface;

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

import uniandes.cupi2.cupiTaxonomy.world.Taxon;

/**
 * Panel containing the taxonomic tree.
 */
@SuppressWarnings({"rawtypes", "serial"})
public class TaxaPanel extends JPanel implements TreeSelectionListener, ActionListener {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant that represents the command to eliminate a taxon.
     */
    private final static String ELIMINATE = "Delete";

    /**
     * Constant that represents the command to show the list in pre-order.
     */
    private final static String PREORDER = "Preorder";

    /**
     * Constant that represents the command to show the list in post-order.
     */
    private final static String POSTORDER = "Postorder";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Principal window of the application.
     */
    private TaxonomicTreeInterface principal;

    // -----------------------------------------------------------------
    // User interface attributes
    // -----------------------------------------------------------------

    /**
     * Tree with the taxa.
     */
    private JTree treeTaxa;

    /**
     * Model of the taxonomic tree.
     */
    private DefaultTreeModel model;

    /**
     * Scroll pane containing the taxon list.
     */
    private JScrollPane scroll;

    /**
     * Button to eliminate a taxon.
     */
    private JButton btnEliminate;

    /**
     * Button to show the taxa in pre-order.
     */
    private JButton btnPreOrder;

    /**
     * Button to show the taxa in post-order.
     */
    private JButton btnPostOrder;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructs the visualization panel of the taxa.
     * <b> post: </b> The panel was created with all its graphical components.
     *
     * @param pPrincipal Principal window of the application. pPrincipal != null.
     */
    public TaxaPanel(TaxonomicTreeInterface pPrincipal) {
        principal = pPrincipal;

        setLayout(new BorderLayout());
        setBorder(new CompoundBorder(new EmptyBorder(0, 5, 0, 5),
                                     new TitledBorder(" Taxonomic Tree ")));
        setPreferredSize(new Dimension(300, 0));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
        DefaultTreeModel model = new DefaultTreeModel(root);

        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);

        treeTaxa = new JTree(model);
        treeTaxa.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeTaxa.addTreeSelectionListener(this);
        treeTaxa.setCellRenderer(renderer);

        scroll = new JScrollPane(treeTaxa);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(
                new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new LineBorder(Color.BLACK, 1)));

        btnEliminate = new JButton(ELIMINATE);
        btnEliminate.setActionCommand(ELIMINATE);
        btnEliminate.addActionListener(this);

        JPanel aux = new JPanel();
        aux.setLayout(new GridLayout(1, 3));

        btnPreOrder = new JButton(PREORDER);
        btnPreOrder.setActionCommand(PREORDER);
        btnPreOrder.setSelected(true);
        btnPreOrder.addActionListener(this);

        btnPostOrder = new JButton(POSTORDER);
        btnPostOrder.setActionCommand(POSTORDER);
        btnPostOrder.addActionListener(this);

        aux.add(btnEliminate);
        aux.add(btnPreOrder);
        aux.add(btnPostOrder);

        add(aux, BorderLayout.SOUTH);
        add(scroll, BorderLayout.CENTER);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Updates the taxonomic tree.
     *
     * @param pTaxonRoot Root taxon of the tree.
     *                   pTaxonRoot != null.
     */
    public void refreshTree(Taxon pTaxonRoot) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(pTaxonRoot);
        model = new DefaultTreeModel(root);
        refresh(pTaxonRoot, root);
        treeTaxa.setModel(model);
        TreePath path = treeTaxa.getPathForRow(root.getLevel());
        treeTaxa.setSelectionPath(path);
    }

    /**
     * Adds the subtaxa of given taxon to the taxonomic tree.
     *
     * @param pTaxon Father taxon.
     *               pTaxon != null
     * @param pNode  Node to which the taxa will be added.
     *               pNode != null
     */
    private void refresh(Taxon pTaxon, DefaultMutableTreeNode pNode) {
        ArrayList subTaxa = pTaxon.getSubTaxa();
        for (int i = 0; i < subTaxa.size(); i++) {
            Taxon taxonChild = (Taxon) subTaxa.get(i);
            DefaultMutableTreeNode nodeChild = new DefaultMutableTreeNode(taxonChild);
            refresh(taxonChild, nodeChild);
            pNode.add(nodeChild);
        }
    }

    /**
     * Method to manage the event in which a user selects a taxon from the list.
     *
     * @param pEvent Event in which a user selects a taxon from the list.
     *               pEvent != null.
     */
    public void valueChanged(TreeSelectionEvent pEvent) {
        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode) treeTaxa.getLastSelectedPathComponent();

        if (node != null) {
            Taxon taxon = (Taxon) node.getUserObject();
            principal.updateLivingBeings(taxon);
        }
    }

    /**
     * Button events manager.
     *
     * @param pEvent Action generated by the event. pEvent != null.
     */
    public void actionPerformed(ActionEvent pEvent) {
        String command = pEvent.getActionCommand();
        if (command.equals(ELIMINATE)) {
            DefaultMutableTreeNode node =
                    (DefaultMutableTreeNode) treeTaxa.getLastSelectedPathComponent();
            if (node != null) {
                Taxon taxon = (Taxon) node.getUserObject();
                if (taxon.getLevel() != Taxon.LUCA) {
                    principal.eliminateTaxon(taxon.getLevel(), taxon.getName());
                }
                else {
                    JOptionPane.showMessageDialog(null, "It is not possible to eliminate the "
                                                          + "LUCA taxon.",
                                                  "Eliminate taxon", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (command.equals(PREORDER)) {
            principal.showTaxaPreOrder();
        }
        else if (command.equals(POSTORDER)) {
            principal.showTaxaPostOrder();
        }
    }
}
