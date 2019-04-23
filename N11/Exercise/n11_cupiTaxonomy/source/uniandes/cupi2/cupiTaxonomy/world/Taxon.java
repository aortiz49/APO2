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

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class that represents a taxon of the taxonomic tree.  <br>
 * <b> inv: </b> <br>
 * name != null && name != "". <br>
 * Level belongs to a {LUCA, DOMAIN, KINGDOM, PHYLUM, CLASS, ORDER, FAMILY, GENUS, SPECIES}. <br>
 * The list of subtaxa is initialized. <br>
 * If the level of the taxon isn't SPECIES, it cannot contain a living being. <br>
 * If the level of the taxon is SPECIES, it cannot have any subtaxa. <br>
 * The level of the subtaxa corresponds to the following level in the taxonomic hierarchy.  <br>
 */
public class Taxon implements Serializable {
    // -------------------------------------------------------------
    // Constants
    // -------------------------------------------------------------

    /**
     * Constant that represents the LUCA level.
     */
    public final static int LUCA = 0;

    /**
     * Constant that represents the Domain level.
     */
    public final static int DOMAIN = 1;

    /**
     * Constant that represents the Kingdom level.
     */
    public final static int KINGDOM = 2;

    /**
     * Constant that represents the Phylum level.
     */
    public final static int PHYLUM = 3;

    /**
     * Constant that represents the Class level.
     */
    public final static int CLASS = 4;

    /**
     * Constant that represents the Order level.
     */
    public final static int ORDER = 5;

    /**
     * Constant that represents the Family level.
     */
    public final static int FAMILY = 6;

    /**
     * Constant that represents the Genus level.
     */
    public final static int GENUS = 7;

    /**
     * Constant that represents the Species level.
     */
    public final static int SPECIES = 8;

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Serialization constant of the class
     */
    private static final long serialVersionUID = 1L;

    /**
     * Name of the taxon.
     */
    private String name;

    /**
     * Level of the taxon.
     */
    private int level;

    /**
     * List of the subtaxa of the current taxon.
     */
    private ArrayList<Taxon> subTaxa;

    /**
     * Living being of the taxon.
     */
    private LivingBeing livingBeing;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Constructs an empty taxon. <br>
     * <b> post: </b> The level is initialized at LUCA. <br>
     * The name is initialized as "Last universal common ancestor". <br>
     * The list of subtaxa is initializes as an empty list.<br>
     * Living being is initialized as null.
     */
    public Taxon() {
        level = LUCA;
        name = "Last universal common ancestor";

        subTaxa = new ArrayList<Taxon>();
        livingBeing = null;

        verifyInvariants();
    }

    /**
     * Initializes a taxon with the information given by the parameters. <br>
     * <b> post: </b> The taxon was initialized with the values for its level and name given by
     * the parameters. <br>
     * The list of subtaxa was initialized as null as well as the living being. <br>
     *
     * @param pLevel Level of taxon to be added. pLevel belongs to { Taxon.LUCA,
     *               Taxon.DOMAIN, Taxon.KINGDOM, Taxon.PHYLUM, Taxon.CLASS, Taxon.ORDER,
     *               Taxon.FAMILY, Taxon.GENUS, Taxon.SPECIES}.
     * @param pName  Name of the taxon.
     *               pName != null
     *               pName != "".
     */
    public Taxon(int pLevel, String pName) {
        level = pLevel;
        name = pName;

        subTaxa = new ArrayList<Taxon>();
        livingBeing = null;

        verifyInvariants();
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    public int getNumberOfSpecies() {
        if (level == SPECIES) {
            return 1;
        }
        else {
            int total = 0;
            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                total += child.getNumberOfSpecies();
            }
            return total;
        }
    }

    public void getTaxonsEndingWith(String pString, ArrayList<Taxon> pList) {
        if(name.endsWith(pString)){
            pList.add(this);
        }
        for (int i = 0; i < subTaxa.size(); i++) {
            Taxon child = subTaxa.get(i);
            child.getTaxonsEndingWith(pString,pList);
        }
    }


    public boolean dependeDe(String nCargo1, String nCargo2) {
        boolean depends = false;

        Taxon taxon2 = findTaxon(nCargo2);

        if (taxon2.findTaxon(nCargo1) != null) {
            depends = true;
        }
        return depends;

    }

    public boolean existeSerVivoEnTaxon(String pNombreSerVivo,String pNombreTaxon ) {

        boolean found = false;
        Taxon taxon = findTaxon(pNombreTaxon);

        if(taxon.findLivingBeing(pNombreSerVivo)!=null){
            found = true;
        }
        return found;
    }
    

    public boolean existeSerVivo() {
        if (livingBeing != null) {
            return true;
        }
        else {
            boolean found = false;
            for (int i = 0; i < subTaxa.size() && !found; i++) {
                Taxon child = subTaxa.get(i);
                found = child.existeSerVivo();
            }
            return found;
        }
    }

    public void addTaxonsWithLivingBeing(ArrayList<Taxon> pList) {
        for (int i = 0; i < subTaxa.size(); i++) {
            Taxon child = subTaxa.get(i);

            if (child.livingBeing != null) {
                pList.add(this);
            }

            else {
                child.addTaxonsWithLivingBeing(pList);
            }
        }
    }

    public int getHeight() {
        if (subTaxa.size() == 0) {
            return 1;
        }
        else {
            int max = 0;
            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                int temp = child.getHeight();
                if (temp > max) {
                    max = temp;
                }
            }
            return max + 1;
        }
    }


    /**
     * Returns the name of the taxon.
     *
     * @return Name of the taxon.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the level of the taxon.
     *
     * @return Level of the taxon.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the list of the taxon's subtaxa.
     *
     * @return List of the subtaxa of the taxon.
     */
    public ArrayList<Taxon> getSubTaxa() {
        return subTaxa;
    }

    /**
     * Returns the living being of the taxon.
     *
     * @return Living being of the taxon.
     */
    public LivingBeing getLivingBeing() {
        return livingBeing;
    }

    /**
     * Returns the number of taxa of the subtree that has the current taxon as its root. <br>
     *
     * @return Number of taxa of the subtree.
     */
    public int getNumTaxa() {
        if (subTaxa.size() == 0) {
            return 1;
        }
        else {
            int totalTaxa = 1;
            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                totalTaxa += child.getNumTaxa();
            }
            return totalTaxa;
        }
    }

    /**
     * Returns the number of living beings of the subtree that has the current taxon as its root.
     * <br>
     *
     * @return Number of living beings of the subtree that has the current taxon as its root.
     */
    public int getNumLivingBeings() {
        if (subTaxa.size() == 0 && livingBeing != null) {
            return 1;
        }
        else {
            int totalSeres = 0;
            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                totalSeres += child.getNumLivingBeings();
            }
            return totalSeres;
        }
    }

    /**
     * Adds a new taxon to the list of subtaxa of the current taxon. <br>
     * <b> pre: </b> El taxon no es of level SPECIES. <br>
     * <b> post: </b> A new taxon has been added to the list of subtaxa.
     *
     * @param pLevel     Level of taxon to be added. pLevel belongs to { Taxon.LUCA,
     *                   Taxon.DOMAIN, Taxon.KINGDOM, Taxon.PHYLUM, Taxon.CLASS, Taxon.ORDER,
     *                   Taxon.FAMILY, Taxon.GENUS, Taxon.SPECIES}.
     * @param pTaxonName Name of the taxon.
     *                   pTaxonName != null
     *                   pTaxonName != "".
     */
    public void addSubTaxon(int pLevel, String pTaxonName) {
        Taxon newItem = new Taxon(pLevel, pTaxonName);
        subTaxa.add(newItem);
    }

    /**
     * Adds a living being to the current taxon. <br>
     * <b> pre: </b> El taxon es of level SPECIES. <br>
     * <b> post: </b> The attribute Living Being has been initialized with the values given by
     * the parameters.
     *
     * @param pCommonName      Common name of the living being.
     *                         pCommonName != null && pCommonName != "".
     * @param pScientificName  Scientific name of the living being.
     *                         pScientificName != null && pScientificName != "".
     * @param pCharacteristics Characteristics of the living being.
     *                         pCharacteristics != null && pCharacteristics != "".
     * @param pImage           Image path to the living being.
     *                         pImage != null && pImage != "".
     * @throws Exception If the taxon already has a name of living being associated to the taxon.
     */
    public void addLivingBeing(String pCommonName, String pScientificName, String pCharacteristics,
                               String pImage) throws Exception {
        if (livingBeing == null) {
            livingBeing = new LivingBeing(pCommonName, pScientificName, pCharacteristics, pImage);
            verifyInvariants();
        }
        else {
            throw new Exception("The species " + name + " already has a living being assigned.");
        }
    }

    /**
     * Finds a taxon with the name given by the parameter. <br>
     *
     * @param pTaxonName Name of the taxon to find.
     *                   pTaxonName != null && pTaxonName != "".
     * @return Taxon with the given name, null in case it is not found.
     */
    public Taxon findTaxon(String pTaxonName) {
        if (name.equalsIgnoreCase(pTaxonName)) {
            return this;
        }
        else {
            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                Taxon temp = child.findTaxon(pTaxonName);

                if (temp != null) {
                    return temp;
                }
            }
            return null;
        }
    }

    /**
     * Finds the living being with the scientific name given by the parameter. <br>
     *
     * @param pScientificName Scientific name of the living being to be found.
     *                        pScientificName !=null && pScientificName != "".
     * @return Living being with the given scientific name, null in case it is not found.
     */
    public LivingBeing findLivingBeing(String pScientificName) {
        if (livingBeing != null && livingBeing.getScientificName()
                                              .equalsIgnoreCase(pScientificName)) {
            return this.livingBeing;
        }
        else {
            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                LivingBeing temp = child.findLivingBeing(pScientificName);

                if (temp != null) {
                    return temp;
                }
            }
            return null;
        }
    }

    /**
     * Adds the living beings of the taxon or of its subtaxa to the list given by the parameter.
     * <br>
     *
     * @param pListSeres The list where the living beings will be added to.
     *                   pListSeres != null.
     */
    public void findLivingBeings(ArrayList<LivingBeing> pListSeres) {
        if (livingBeing != null) {
            pListSeres.add(livingBeing);
        }
        else {
            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                child.findLivingBeings(pListSeres);
            }
        }
    }

    /**
     * Adds the taxon and its subtaxa to the list given by the parameter in preorder. <br>
     *
     * @param pListTaxa The list where the taxa will be added to.
     *                  pListTaxa !=null.
     */
    public void findTaxaPreOrder(ArrayList<Taxon> pListTaxa) {
        pListTaxa.add(this);

        for (int i = 0; i < subTaxa.size(); i++) {
            Taxon child = subTaxa.get(i);
            child.findTaxaPreOrder(pListTaxa);


        }
    }

    /**
     * Adds the taxon and its subtaxa to the list given by the parameter in postorder. <br>
     *
     * @param pListTaxa The list where the taxa will be added to.
     *                  pListTaxa !=null.
     */
    public void findTaxaPostOrder(ArrayList<Taxon> pListTaxa) {
        if (subTaxa.size() == 0) {
            pListTaxa.add(this);
        }
        else {
            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                child.findTaxaPostOrder(pListTaxa);
            }
            pListTaxa.add(this);
        }
    }

    public void findTaxaInOrder(ArrayList<Taxon> pListTaxa) {
        if (subTaxa.size() == 0) {
            pListTaxa.add(this);
        }
        else {

            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                child.findTaxaInOrder(pListTaxa);
                if (i == 0) {
                    pListTaxa.add(this);
                }
            }
        }
    }

    /**
     * Adds the names of the taxa that belong to the level given by parameter to the list
     * given by
     * the parameter. <br>
     *
     * @param pLevel    Level of taxon to be added. pLevel belongs to { Taxon.LUCA,
     *                  Taxon.DOMAIN, Taxon.KINGDOM, Taxon.PHYLUM, Taxon.CLASS, Taxon.ORDER,
     *                  Taxon.FAMILY, Taxon.GENUS, Taxon.SPECIES}.
     * @param pListTaxa The list where the names will be added to.
     *                  pListTaxa !=null.
     */
    public void findTaxaByLevel(int pLevel, ArrayList<String> pListTaxa) {
        if (level == pLevel) {
            pListTaxa.add(this.name);
        }
        else {
            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                child.findTaxaByLevel(pLevel, pListTaxa);
            }
        }
    }

    /**
     * Returns the taxon with the name and level given  by the parameter. <br>
     *
     * @param pLevel     Level of taxon to be added. pLevel belongs to { Taxon.LUCA,
     *                   Taxon.DOMAIN, Taxon.KINGDOM, Taxon.PHYLUM, Taxon.CLASS, Taxon.ORDER,
     *                   Taxon.FAMILY, Taxon.GENUS, Taxon.SPECIES}.
     * @param pTaxonName Name of the taxon to be found.
     *                   pTaxonName != null && pTaxonName != "".
     * @return Taxon with the given name and level given, null in case it is not found.
     */
    public Taxon findTaxonByLevel(int pLevel, String pTaxonName) {
        if (level == pLevel && name.equalsIgnoreCase(pTaxonName)) {
            return this;
        }

        else {
            for (int i = 0; i < subTaxa.size(); i++) {
                Taxon child = subTaxa.get(i);
                Taxon temp = child.findTaxonByLevel(pLevel, pTaxonName);

                if (temp != null) {
                    return temp;
                }
            }
            return null;
        }
    }

    /**
     * Eliminates a taxon of the subtree that has the current taxon as its root node.  <br>
     * <b> pre: </b> El father taxon and taxon to be eliminated exist. <br>
     * <b> post: </b> The taxon was eliminated from the taxonomic tree. <br>
     * If the taxon that was eliminated has subtaxa, these were also eliminated. <br>
     *
     * @param pLevel     Level of taxon to be added. pLevel belongs to { Taxon.LUCA,
     *                   Taxon.DOMAIN, Taxon.KINGDOM, Taxon.PHYLUM, Taxon.CLASS, Taxon.ORDER,
     *                   Taxon.FAMILY, Taxon.GENUS, Taxon.SPECIES}.
     * @param pTaxonName Name of the taxon to be eliminated.
     *                   pTaxonName != null && pTaxonName != "".
     */
    public void eliminateTaxon(int pLevel, String pTaxonName) {

        boolean eliminated = false;
        for (int i = 0; i < subTaxa.size() && !eliminated; i++) {
            Taxon child = subTaxa.get(i);
            if (child.level == pLevel && child.name.equalsIgnoreCase(pTaxonName)) {
                subTaxa.remove(i);
                eliminated = true;
            }
            else
                child.eliminateTaxon(pLevel, pTaxonName);
        }

    }


    public Taxon findLowestName() {
        Taxon low = this;
        String min = name;

        for (int i = 0; i < subTaxa.size(); i++) {
            Taxon child = (Taxon) subTaxa.get(i);
            Taxon lowestName = child.findLowestName();
            String temp = lowestName.getName();

            if (temp.compareToIgnoreCase(temp) < 0) {
                min = temp;
                low = lowestName;
            }
        }
        return low;
    }


    /**
     * Return a string with the level and the name of the taxon.
     *
     * @return The representation of the taxon in a String:
     * <level of the taxon>: <name of the taxon>.
     */
    public String toString() {
        String toString = getNameLevel() + ": " + name;
        return toString;
    }

    /**
     * Returns the name of the level of the taxon.
     *
     * @return Name of the level of the taxon.
     */
    private String getNameLevel() {
        String nameLevel = "";

        if (level == LUCA) {
            nameLevel = "LUCA";
        }
        else if (level == DOMAIN) {
            nameLevel = "Domain";
        }
        else if (level == KINGDOM) {
            nameLevel = "Kingdom";
        }
        else if (level == PHYLUM) {
            nameLevel = "Phylum";
        }
        else if (level == CLASS) {
            nameLevel = "Class";
        }
        else if (level == ORDER) {
            nameLevel = "Order";
        }
        else if (level == FAMILY) {
            nameLevel = "Family";
        }
        else if (level == GENUS) {
            nameLevel = "Genus";
        }
        else {
            nameLevel = "Species";
        }

        return nameLevel;
    }

    // -----------------------------------------------------------------
    // Invariants
    // -----------------------------------------------------------------

    /**
     * Verifies the invariants of la class. <br>
     * <b> inv: </b> <br>
     * name != null && name != "". <br>
     * Level belongs to a {LUCA, DOMAIN, KINGDOM, PHYLUM, CLASS, ORDER, FAMILY, GENUS,
     * SPECIES}. <br>
     * The list of subtaxa is initialized. <br>
     * If the level of the taxon isn't SPECIES, it cannot contain a living being. <br>
     * If the level of the taxon is SPECIES, it cannot have any subtaxa. <br>
     * The level of the subtaxa corresponds to the following level in the taxonomic hierarchy
     * .  <br>
     */
    private void verifyInvariants() {
        assert name != null && !name.isEmpty() : "The name of the taxon must be initialized.";
        assert level == LUCA || level == DOMAIN || level == KINGDOM || level == PHYLUM
                || level == CLASS || level == ORDER || level == FAMILY || level == GENUS
                || level == SPECIES : "The level of the taxon must be initialized correctly.";
        assert subTaxa != null : "The list of subtaxa must be initialized.";

        if (level != SPECIES) {
            assert livingBeing == null : "This taxon must not have a living being.";
        }
        else {
            assert subTaxa.isEmpty() : "This taxon must not have subtaxa.";
        }

        for (int i = 0; i < subTaxa.size(); i++) {
            Taxon taxon = (Taxon) subTaxa.get(i);
            assert taxon.level == level + 1 : "The level of the subtaxa is incorrect.";
        }
    }
}