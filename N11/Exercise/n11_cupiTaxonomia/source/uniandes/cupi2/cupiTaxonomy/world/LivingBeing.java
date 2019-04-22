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

/**
 * Class that represents a living being in the taxonomic tree.
 */
public class LivingBeing implements Serializable {
    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Serialization constant of the class.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Common name of the living being.
     */
    private String commonName;

    /**
     * Scientific name of the living being.
     */
    private String scientificName;

    /**
     * Characteristics of the living being.
     */
    private String characteristics;

    /**
     * Path of the image of the living being.
     */
    private String imagePath;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Constructs a new living being with the information given by the parameters. <br>
     * <b> post: </b> The living being was initialized with the values: name, scientific name,
     * characteristics, and image.
     *
     * @param pCommonName      Common name of the living being.
     *                         pCommonName != null
     *                         pCommonName != "".
     * @param pScientificName  Scientific name of the living being.
     *                         pScientificName != null
     *                         pScientificName != "".
     * @param pCharacteristics Characteristics of the living being.
     *                         pCharacteristics != null
     *                         pCharacteristics != "".
     * @param pImage           Path of the image to the living being.
     *                         pImage != null  pImage !="".
     */
    public LivingBeing(String pCommonName, String pScientificName, String pCharacteristics,
                       String pImage) {
        commonName = pCommonName;
        scientificName = pScientificName;
        characteristics = pCharacteristics;
        imagePath = pImage;

    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Returns the characteristics of the living being.
     *
     * @return Characteristics of the living being.
     */
    public String getCharacteristics() {
        return characteristics;
    }

    /**
     * Returns the common name of the living being.
     *
     * @return Common name of the living being.
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * Returns the scientific name of the living being.
     *
     * @return Scientific name of the living being.
     */
    public String getScientificName() {
        return scientificName;
    }


    /**
     * Returns the image path to the living being.
     *
     * @return Image path to the living being.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Returns a string with the scientific name and the common name of the living being.
     *
     * @return The representation of the living being in a String: <scientificName> - <commonName>.
     */
    public String toString() {
        return scientificName + " - " + commonName;
    }
}