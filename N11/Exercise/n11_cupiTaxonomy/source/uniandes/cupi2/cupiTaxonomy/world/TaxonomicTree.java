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

package uniandes.cupi2.cupiTaxonomy.world;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Class that represents a taxonomic tree.  <br>
 * <b> inv: </b> <br>
 * rootTaxon != null.
 */
public class TaxonomicTree {
    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Root taxon of the taxonomic tree.
     */
    private Taxon rootTaxon;

    /**
     * File path of the taxonomic tree information.
     */
    private String taxonomyFilePath;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Constructs a taxonomic tree.<br>
     * <b>post: </b> The attribute taxonomyFilePath is initialized with the file path given by
     * the parameter.  <br>
     * If the file in the path given by the parameter exists, the class is created based on the
     * information found in this file.  <br>
     * If the file doesn't exist, it will initialize the root taxon. <br>
     *
     * @param pFilePath The file path of the information to load the taxonomic tree.
     *                  pFilePath != null && pFilePath != "".
     * @throws Exception If the file could not be opened or read.
     */
    public TaxonomicTree(String pFilePath) throws Exception {
        taxonomyFilePath = pFilePath;
        File file = new File(taxonomyFilePath);

        // If the file exists, it should recover the precious state of the program
        if (file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                rootTaxon = (Taxon) ois.readObject();
                ois.close();
                verifyInvariants();
            } catch (Exception e) {
                throw new Exception(
                        "Fatal error: Impossible to restore the state of the program (" + e
                                .getMessage() + ").");
            }
        }
        else {
            rootTaxon = new Taxon();
            verifyInvariants();
        }
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------


    public int getNumberOfSpecies() {
        return rootTaxon.getNumberOfSpecies();
    }

    public boolean exists() {
        return rootTaxon.findTaxon("abc").existeSerVivo();
    }

    public ArrayList<Taxon> addTaxons() {

        ArrayList<Taxon> list = new ArrayList<>();
        rootTaxon.addTaxonsWithLivingBeing(list);
        return list;
    }

    public ArrayList<Taxon> getTaxonsEndingWith(String pString) {
        ArrayList<Taxon> list = new ArrayList<>();
        rootTaxon.getTaxonsEndingWith(pString,list);

        return list;
    }


    /**
     * Returns the number of taxa in the taxonomic tree. <br>
     *
     * @return Number of taxa in the taxonomic tree.
     */
    public int getNumTaxa() {
        if (rootTaxon == null)
            return 0;
        else
            return rootTaxon.getNumTaxa();
    }

    /**
     * Returns the number of living beings in the taxonomic tree. <br>
     *
     * @return Number of living beings in the taxonomic tree.
     */
    public int getNumLivingBeings() {
        if (rootTaxon == null)
            return 0;
        else
            return rootTaxon.getNumLivingBeings();
    }

    /**
     * Returns the list of living beings of the taxon with the name and level given by the
     * parameters. <br>
     * <b> pre: </b> The taxon with the name and level given by the parameters exist.
     *
     * @param pTaxonLevel Level of taxon to be added. pLevel belongs to { Taxon.LUCA,
     *                    Taxon.DOMAIN, Taxon.KINGDOM, Taxon.PHYLUM, Taxon.CLASS, Taxon.ORDER,
     *                    Taxon.FAMILY, Taxon.GENUS, Taxon.SPECIES}.
     * @param pTaxonName  Name of the taxon. pTaxonName != null y pTaxonName != "".
     * @return List of living beings that belong to a taxon.
     */
    public ArrayList<LivingBeing> getLivingBeings(int pTaxonLevel, String pTaxonName) {

        ArrayList<LivingBeing> list = new ArrayList<>();
        if (rootTaxon == null)
            return null;
        else {
            Taxon found = rootTaxon.findTaxonByLevel(pTaxonLevel, pTaxonName);
            found.findLivingBeings(list);
        }
        return list;
    }

    /**
     * Returns the root taxon ofg the taxonomic tree.
     *
     * @return Root taxon of the taxonomic tree.
     */
    public Taxon getTaxonRoot() {
        return rootTaxon;
    }


    /**
     * Returns a list a names of the taxa at the level given by the parameter.
     *
     * @param pTaxonLevel Level of taxon to be added. pLevel belongs to { Taxon.LUCA,
     *                    Taxon.DOMAIN, Taxon.KINGDOM, Taxon.PHYLUM, Taxon.CLASS, Taxon.ORDER,
     *                    Taxon.FAMILY, Taxon.GENUS, Taxon.SPECIES}.
     * @return List of the names of los taxa at the level given by the parameter.
     */
    public ArrayList<String> getTaxaLevel(int pTaxonLevel) {
        ArrayList<String> taxonList = new ArrayList<>();
        rootTaxon.findTaxaByLevel(pTaxonLevel, taxonList);

        return taxonList;
    }

    /**
     * Returns the list of taxa in the taxonomic tree. The taxa are added in preorder. <br>
     *
     * @return List of taxa in the taxonomic tree in preorder.
     */
    public ArrayList<Taxon> getTaxaPreOrder() {
        ArrayList<Taxon> taxonList = new ArrayList<>();
        rootTaxon.findTaxaPreOrder(taxonList);

        return taxonList;
    }

    /**
     * Returns the list of taxa in the taxonomic tree. The taxa are added in ostorder. <br>
     *
     * @return List of taxa in the taxonomic tree in postorden.
     */
    public ArrayList<Taxon> getTaxaPostOrder() {
        ArrayList<Taxon> taxonList = new ArrayList<>();
        rootTaxon.findTaxaPostOrder(taxonList);

        return taxonList;

    }

    /**
     * Returns the list of taxa in the taxonomic tree. The taxa are added in ostorder. <br>
     *
     * @return List of taxa in the taxonomic tree in postorden.
     */
    public ArrayList<Taxon> getTaxaInOrder() {
        ArrayList<Taxon> taxonList = new ArrayList<>();
        rootTaxon.findTaxaInOrder(taxonList);

        return taxonList;

    }

    /**
     * Adds a living being to a taxon in the taxonomic tree. <br>
     * <b> pre: </b> The taxon exists and belongs to the SPECIES level. <br>
     * <b> post: </b> A new living being was added to a taxon in the taxonomic tree.
     *
     * @param pTaxonName       Name of the taxon to which the living being will be added to.
     *                         pTaxonName != && null && pTaxonName != "".
     * @param pCommonName      Common name of the living being to be added.
     *                         pCommonName != null && pCommonName != "".
     * @param pScientificName  Scientific name of the living being to be added.
     *                         pScientificName !=null && pScientificName != "".
     * @param pCharacteristics Characteristics of the living being to be added.
     *                         pCharacteristics != null && pCharacteristics != "".
     * @param pImage           Path of image to the living being to be added.
     *                         pImage != null y pImage != "".
     * @throws Exception If there already exists a living being with the given scientific name.
     * @throws Exception If the taxon already has a living being.
     */
    public void addLivingBeing(String pTaxonName, String pCommonName, String pScientificName,
                               String pCharacteristics, String pImage) throws Exception {
        Taxon taxon = rootTaxon.findTaxon(pTaxonName);
        taxon.addLivingBeing(pCommonName, pScientificName, pCharacteristics, pImage);
    }

    /**
     * Adds a new subtaxon to the taxon with the name given by the parameter. <br>
     * <p> pre: </b> The level of the child taxon is greater than the level of the father taxon.<br>
     * <b> post: </b> The new taxon was added to the taxonomic tree.
     *
     * @param pTaxonNameFather Name of the taxon father of the taxon to be added.
     *                         pTaxonNameFather !=
     *                         null && pTaxonNameFather != "".
     * @param pTaxonLevelChild Level of the taxon to be added. pTaxonLevelChild pertenece a {LUCA,
     *                         DOMAIN,
     *                         KINGDOM, PHYLUM, CLASS, ORDER, FAMILY, GENUS, SPECIES}.
     * @param pTaxonNameChild  Name of the taxon to be added. pTaxonNameChild != null &&
     *                         pTaxonNameChild != "".
     * @throws Exception If there already exists a taxon with the given name.
     */
    public void addTaxon(String pTaxonNameFather, int pTaxonLevelChild, String pTaxonNameChild)
            throws Exception {
        Taxon taxon = rootTaxon.findTaxon(pTaxonNameChild);
        if (taxon == null) {
            Taxon father = rootTaxon.findTaxonByLevel(pTaxonLevelChild - 1, pTaxonNameFather);
            father.addSubTaxon(pTaxonLevelChild, pTaxonNameChild);
        }
        else {
            throw new Exception("A taxon with the name " + pTaxonNameChild + " already exists.");
        }
        verifyInvariants();
    }

    /**
     * Eliminates the taxon from the taxonomic tree with the name and level given by the
     * parameter. <br>
     * <b> pre: </b> The taxon to be eliminated exists. <br>
     * <b> post: </b> The taxon was eliminated from the taxonomic tree.
     *
     * @param pTaxonLevel Level of the taxon to be eliminated.
     *                    pTaxonLevel belongs to {LUCA, DOMAIN, KINGDOM,
     *                    PHYLUM, CLASS, ORDER, FAMILY, GENUS, SPECIES}.
     * @param pTaxonName  Name of the taxon to be eliminated.
     *                    pTaxonName != null && pTaxonName != "".
     */
    public void eliminateTaxon(int pTaxonLevel, String pTaxonName) {
        rootTaxon.eliminateTaxon(pTaxonLevel, pTaxonName);
    }

    /**
     * Saves the current state of the taxonomic tree. <br>
     *
     * @param pFilePath Path of the file where the state of the application will be saved to.
     *                  pFilePath != null & pFilePath != "".
     * @throws Exception If there are problems wen saving the current state.
     */
    public void save(String pFilePath) throws Exception {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pFilePath));
            oos.writeObject(rootTaxon);
            oos.close();
        } catch (IOException e) {
            throw new Exception("Error when saving: " + e.getMessage() + ".");
        }
    }

    // -----------------------------------------------------------------
    // Invariants
    // -----------------------------------------------------------------

    /**
     * Verifies the invariants of the class. <br>
     * <b> inv: </b><br>
     * rootTaxon != null <br>
     */
    private void verifyInvariants() {
        assert rootTaxon != null : "El root taxon must not be null.";
    }

    // -----------------------------------------------------------------
    // Extension points
    // -----------------------------------------------------------------

    /**
     * Method for extension 1.
     *
     * @return response1.
     */
    public String method1() {
        return "Response 1.";
    }

    /**
     * Method for extension2.
     *
     * @return response2.
     */
    public String method2() {
        return "Response 2.";
    }
}