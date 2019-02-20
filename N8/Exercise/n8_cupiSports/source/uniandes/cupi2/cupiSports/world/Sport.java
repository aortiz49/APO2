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

import java.util.ArrayList;

/**
 * Class that represents a sport. <br>
 * <b>inv: </b> <br>
 * name != null && name != "" <br>
 * regulatoryEntity != null && regulatoryEntity != "" <br>
 * numberOfRegisteredAthletes >= 0 <br>
 * imagePath != null && imagePath != "" <br>
 */
public class Sport {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Serialization constant.
     */
    private static final long serialVersionUID = -8659162802685356289L;

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Name of the sport.
     */
    private String name;

    /**
     * Regulatory entity of the sport.
     */
    private String regulatoryEntity;

    /**
     * Number of athletes registered in the sport.
     */
    private int numberOfRegisteredAthletes;

    /**
     * Image path of the sport.
     */
    private String imagePath;

    /**
     * List containing outstanding athletes.
     */
    private ArrayList<Athlete> outstandingAthletes;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs a new sport with the values given by the parameters.  <br>
     * <b>post: </b> The attributes were initialized with the values given by the parameters. An
     * empty list of athletes is created.  <br>
     *
     * @param pName                       Name of the sport.
     *                                    pName != null && pName != "".
     * @param pRegulatoryEntity           Regulatory entity of the sport.
     *                                    pRegulatoryEntity != null && pRegulatoryEntity != "".
     * @param pNumberOfRegisteredAthletes Number of athletes registered in the sport.
     *                                    pNumberOfRegisteredAthletes >= 0.
     * @param pImagePath                  Image path of the sport.
     *                                    pImagePath != null && pImagePath != "".
     */
    public Sport(String pName, String pRegulatoryEntity, int pNumberOfRegisteredAthletes,
                 String pImagePath) {
        name = pName;
        regulatoryEntity = pRegulatoryEntity;
        numberOfRegisteredAthletes = pNumberOfRegisteredAthletes;
        imagePath = pImagePath;
        outstandingAthletes = new ArrayList<>();

        verifyInvariants();
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the name of the sport.
     *
     * @return Name of the sport.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the sport's regulatory entity.
     *
     * @return Regulatory entity of the sport.
     */
    public String getRegulatoryEntity() {
        return regulatoryEntity;
    }

    /**
     * Returns the number of athletes registered in the sport.
     *
     * @return Number of registered athletes.
     */
    public int getNumberOfRegisteredAthletes() {
        return numberOfRegisteredAthletes;
    }

    /**
     * Returns the image path of the sport.
     *
     * @return Image path of the sport.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Returns a list with outstanding athletes.
     *
     * @return Outstanding athletes in the sport.
     */
    public ArrayList<Athlete> getOutstandingAthletes() {
        return outstandingAthletes;
    }

    /**
     * Adds an outstanding athlete.
     *
     * @param pAthlete Athlete to be added to the list of a outstanding athletes.
     *                 pAthlete != null.<br>
     *                 <b>post: </b> The outstanding athlete was added to the list.
     * @throws ElementExistsException Throws an exception if the outstanding player to be added
     *                                to the list already exists in the list.
     */
    public void addOutstandingAthlete(Athlete pAthlete) throws ElementExistsException {

        // Checks to see if the athlete already exists in the list.
        if (outstandingAthleteExists(pAthlete.getName()))
            throw new ElementExistsException(
                    ElementExistsException.REPEATED_SPORT, pAthlete.getName());
        else {
            // If the athlete isn't in the list, add him.
            outstandingAthletes.add(pAthlete);
        }
    }

    /**
     * Eliminates the outstanding athlete with the given name. <br>
     * <b>post: </b> The outstanding athlete was eliminated from the list.
     *
     * @param pNameAthlete Name of the athlete to eliminate.
     *                     pNameAthlete != null && pNameAthlete != "".
     */
    public void eliminateOutstandingAthlete(String pNameAthlete) {
        boolean finished = false;
        for (int i = 0; i < outstandingAthletes.size() && !finished; i++) {
            Athlete currentAthlete = outstandingAthletes.get(i);
            if (currentAthlete.getName().equals(pNameAthlete)) {
                outstandingAthletes.remove(i);
                finished = true;
            }
        }
    }

    /**
     * Verifies if an athlete of a given name is an outstanding athlete. <br>
     *
     * @param pNameAthlete Name of the athlete.
     *                     pNameAthlete != null && pNameAthlete != "".
     * @return True if there exists an outstanding athlete with the name given by the parameter.
     * False if otherwise.
     */
    public boolean outstandingAthleteExists(String pNameAthlete) {
        boolean exists = false;

        for (int i = 0; i < outstandingAthletes.size() && !exists; i++) {
            Athlete currentAthlete = outstandingAthletes.get(i);
            if (currentAthlete.getName().equals(pNameAthlete))
                exists = true;

        }
        return exists;
    }

    /**
     * Returns the athlete who has won the most trophies. If there are two or more athletes with
     * the same amount of trophies, return any of those athletes. <br>
     *
     * @return The athlete with the highest amount of trophies. If there are none with trophies,
     * return null.
     */
    public Athlete getAthleteMostTrophies() {
        Athlete mostTrophies = null;
        int maxQuantity = 0;
        for (Athlete currentAthlete : outstandingAthletes) {
            if (currentAthlete.getAmountOfTrophies() > maxQuantity) {
                maxQuantity = currentAthlete.getAmountOfTrophies();
                mostTrophies = currentAthlete;
            }
        }
        return mostTrophies;
    }

    /**
     * Returns the total amount of trophies won by outstanding athletes. <br>
     *
     * @return Total amount of trophies.
     */
    public int getTotalTrophies() {
        int total = 0;

        for (Athlete currentAthlete : outstandingAthletes) {
            total += currentAthlete.getAmountOfTrophies();
        }

        return total;
    }

    /**
     * Returns the name of the sport.
     *
     * @return Name of the sport.
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
     * regulatoryEntity != null && regulatoryEntity != "" <br>
     * numberOfRegisteredAthletes >= 0 <br>
     * imagePath != null && imagePath != "" <br>
     */
    private void verifyInvariants() {
        assert name != null : "The name is null";
        assert !name.equals("") : "The name is empty";
        assert regulatoryEntity != null : "The regulatory entity is null";
        assert !regulatoryEntity.equals("") : "The regulatory entity is empty";
        assert numberOfRegisteredAthletes > 0 : "The number of registered athletes is negative";
        assert imagePath != null : "The name is null";
        assert !imagePath.equals("") : "The name is empty";

    }


}
