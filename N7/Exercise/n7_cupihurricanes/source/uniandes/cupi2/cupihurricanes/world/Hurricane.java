/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n7_Hurricane
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package uniandes.cupi2.cupihurricanes.world;

/**
 * Class which represents a hurricane. <br>
 * <b>inv: </b> <br>
 * name != null <br>
 * !name.equals("") <br>
 * category >= 1 <br>
 * category <= 5 <br>
 * velocity >= 0 <br>
 * estimatedDamageCost >= 0 <br>
 * image != null <br>
 * !image.equals("") <br>
 */
public class Hurricane {
    // --------------------------------------------------------
    // Attributes
    // --------------------------------------------------------

    /**
     * Name of the hurricane.
     */
    private String name;

    /**
     * Hurricane category according to the Saffir-Simpson system.
     */
    private int category;

    /**
     * Wind velocity in km/hr.
     */
    private int velocity;

    /**
     * Estimated cost of damages in millions of dollars.
     */
    private double estimatedDamageCost;

    /**
     * Path where the image of the hurricane is found.
     */
    private String image;

    // --------------------------------------------------------
    // Constructors
    // --------------------------------------------------------

    /**
     * Constructs a new hurricane with the values given by the parameters. <br>
     * <b>post: </b> A hurricane was constructed with the indicated parameters.
     *
     * @param pName                Name of the hurricane. pName != null && pName != "".
     * @param pCategory            Category of the hurricane. 1 <= pCategory <= 5.
     * @param pVelocity            Wind velocity. 0 <= pVelocity.
     * @param pEstimatedDamageCost Estimated damage cost. 0 <= pEstimatedDamageCost.
     * @param pImage               Hurricane image path. pImage != null && pImage != "".
     */
    public Hurricane(String pName, int pCategory, int pVelocity, double pEstimatedDamageCost,
                     String pImage) {

        name = pName;
        category = pCategory;
        velocity = pVelocity;
        estimatedDamageCost = pEstimatedDamageCost;
        image = pImage;

        verifyInvariant();
    }

    // --------------------------------------------------------
    // Methods
    // --------------------------------------------------------

    /**
     * Returns the name of the hurricane. <br>
     *
     * @return Name of the hurricane.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the category of the hurricane. <br>
     *
     * @return Category of the hurricane.
     */
    public int getCategory() {
        return category;
    }

    /**
     * Returns the velocity of the hurricane. <br>
     *
     * @return Velocity of the  hurricane.
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     * Returns The estimated damage cost of the hurricane. <br>
     *
     * @return Estimated damage cost of the hurricane.
     */
    public double getEstimatedDamageCost() {
        return estimatedDamageCost;
    }

    /**
     * Returns the image path of the hurricane.
     *
     * @return Image path of the hurricane.
     */
    public String getImage() {
        return image;
    }

    /**
     * Compares two hurricanes according to their names. <br>
     *
     * @param pHurricane Hurricane that is being compared. pHurricane != null. <br>
     * @return Returns 0 if the hurricanes have the same name. <br>
     * Returns a negative integer if pHurricane is lexicographically LOWER than the hurricane's
     * name. <br>
     * Returns a positive integer if pHurricane is lexicographically GREATER than the hurricane's
     * name. <br>
     */
    public int compareByName(Hurricane pHurricane) {
        int value;

        if(name.compareToIgnoreCase(pHurricane.getName()) == 0)
            value = 0;
        else if (name.compareToIgnoreCase(pHurricane.getName()) < 0)
            value = -1;
        else
            value = 1;
        return value;
    }

    /**
     * Compares two hurricanes according to their estimated damage cost. <br>
     *
     * @param pHurricane Hurricane that is being compared. pHurricane != null. <br>
     * @return Returns 0 if the hurricanes have the same estimated damage cost. <br>
     * Returns -1 if the estimated damage cost of pHurricane is LOWER than that of the
     * hurricane.<br>
     * Returns 1 if the estimated damage cost of pHurricane is GREATER than that of the
     * hurricane.<br>
     */
    public int compareByDamage(Hurricane pHurricane) {
        int value;
        if(estimatedDamageCost < pHurricane.getEstimatedDamageCost())
            value = -1;
        else if(estimatedDamageCost == pHurricane.getEstimatedDamageCost())
            value = 0;
        else
            value = 1;

        return value;
    }

    /**
     * Compares two hurricanes according to their velocity. <br>
     *
     * @param pHurricane Hurricane that is being compared. pHurricane != null. <br>
     * @return Returns 0 if the hurricanes have the same velocity. <br>
     * Returns -1 if the velocity of pHurricane is LOWER than that of the hurricane.  <br>
     * Returns 1 if the velocity of pHurricane is GREATER than that of the hurricane. <br>
     */
    public int compareByVelocity(Hurricane pHurricane) {
        return Double.compare(velocity, pHurricane.getVelocity());
    }

    /**
     * Returns a string with the name of the hurricane.
     *
     * @return A representation of the hurricane in a string of characters.
     */
    public String toString() {
        return name;
    }

    // --------------------------------------------------------
    // Invariants
    // --------------------------------------------------------


    /**
     * Verifies that the invariants of the class are met. If something fails, it launches an
     * AssertionError. <br>
     *
     * <b>inv: </b>
     * The name is not null nor empty <br>
     * The category of the hurricane is between 1 and 5. <br>
     * The velocity of the hurricane is greater than or equal to 0. <br>
     * The estimated damage cost is greater than or equal to 0. <br>
     * The image path name is not null nor empty  <br>
     */
    private void verifyInvariant() {
        assert name != null && !name.equals("") : "The hurricane's name is invalid";
        assert category >= 1 && category <= 5 : "The category range is invalid";
        assert velocity >= 0 : "The velocity is invalid, must be positive value";
        assert estimatedDamageCost >= 0 :
                "The estimated damage cost in invalid, must be " + "positive value";
        assert image != null && !image.equals("") : "The image path's name is invalid";

    }
}
