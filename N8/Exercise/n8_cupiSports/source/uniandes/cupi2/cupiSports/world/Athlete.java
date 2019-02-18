/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n8_Sports
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiSports.world;

/**
 * Class that represents an athlete. <br>
 * <b>inv: </b> <br>
 * name != null && name != "" <br>
 * age > 0. <br>
 * placeOfResidency != null && placeOfResidency != "". <br>
 * amountOfTrophies >= 0 <br>
 * imagePath != null && imagePath != "". <br>
 */
public class Athlete {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Serialization constant.
     */
    private static final long serialVersionUID = 3525724065227912858L;


    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Name of the athlete.
     */
    private String name;

    /**
     * Age of the athlete.
     */
    private int age;

    /**
     * Athlete's place of residence.
     */
    private String placeOfResidence;

    /**
     * Amount of trophies won by the athlete.
     */
    private int amountOfTrophies;

    /**
     * Image path of the athlete.
     */
    private String imagePath;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs an athlete with the values given by the parameters. <br>
     * <b>post:</b> The attributes were initialized with the values given by the parameters.
     *
     * @param pName             Name of the athlete.
     *                          pName != null && pName != "".
     * @param pAge              Age of the athlete.
     *                          pAge > 0.
     * @param pPlaceOfResidency Athlete's place of residence.
     *                          pPlaceOfResidency != null && pPlaceOfResidency != "".
     * @param pAmountOfTrophies Amount of trophies won by the athlete.
     *                          pAmountOfTrophies >= 0.
     * @param pImagePath        Image path of the athlete.
     *                          pImagePath != null && pImagePath != "".
     */
    public Athlete(String pName, int pAge, String pPlaceOfResidency, int pAmountOfTrophies,
                   String pImagePath) {
        name = pName;
        age = pAge;
        placeOfResidence = pPlaceOfResidency;
        amountOfTrophies = pAmountOfTrophies;
        imagePath = pImagePath;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the name of the athlete.
     *
     * @return Name of the athlete.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the athlete.
     *
     * @return Age of the athlete.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the place of residency of the athlete.
     *
     * @return Athlete's place of residence.
     */
    public String getPlaceOfResidency() {
        return placeOfResidence;
    }

    /**
     * Returns the amount of trophies won by the athlete.
     *
     * @return Amount of trophies won by the athlete.
     */
    public int getAmountOfTrophies() {
        return amountOfTrophies;
    }

    /**
     * Returns image path of the athlete.
     *
     * @return Image path of the athlete.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Modifies the age of the athlete.
     *
     * @param pAge Athlete's new age. pAge > 0.
     */
    public void modifyAge(int pAge) {
        age = pAge;
    }

    /**
     * Modifies the athlete's place of residence.
     *
     * @param pPlaceOfResidency Athlete's new place of residency.
     *                          pPlaceOfResidency != null && pPlaceOfResidency != "".
     */
    public void modifyPlaceOfResidency(String pPlaceOfResidency) {
        placeOfResidence = pPlaceOfResidency;
    }

    /**
     * Modifies the amount of trophies an athlete has.
     *
     * @param pAmountOfTrophies New amount of trophies won by the athlete.
     *                          pAmountOfTrophies >= 0.
     */
    public void modifyAmountOfTrophies(int pAmountOfTrophies) {
        amountOfTrophies = pAmountOfTrophies;
    }

    /**
     * Modifies the image path of the athlete.
     *
     * @param pImagePath New image path of the athlete.
     *                   pImagePath != null && pImagePath != "".
     */
    public void modifyImagePath(String pImagePath) {
        imagePath = pImagePath;
    }

    /**
     * Returns the name of the athlete.
     *
     * @return Name of the athlete.
     */
    public String toString() {
        return name;
    }

    // -----------------------------------------------------------------
    // Invariants
    // -----------------------------------------------------------------

    /**
     * Verifies the invariants of the class: <br>
     * name != null && name != "" <br>
     * age > 0. <br>
     * placeOfResidency != null && placeOfResidency != "". <br>
     * amountOfTrophies >= 0 <br>
     * imagePath != null && imagePath != "". <br>
     */
    private void verifyInvariants() {
        assert name != null : "The name is null";
        assert name.equals("") : "The name is empty";
        assert age < 0 : "The age is negative";
        assert placeOfResidence != null : "The place of residence is null";
        assert placeOfResidence.equals("") : "The place of residence is empty";
        assert imagePath != null : "The name is null";
        assert imagePath.equals("") : "The name is empty";

    }


}