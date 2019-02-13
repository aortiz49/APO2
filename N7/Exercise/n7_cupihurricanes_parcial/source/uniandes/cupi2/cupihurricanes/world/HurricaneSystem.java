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

import java.util.ArrayList;

/**
 * Class in charge of managing, organizing, loading, and saving hurricanes. <br>
 * <b>inv: </b> <br>
 * hurricanes != null <br>
 */
public class HurricaneSystem {
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Array of hurricanes.
     */
    private ArrayList hurricanes;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs a new, empty meteorology system.
     */
    public HurricaneSystem() {
        hurricanes = new ArrayList();
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------


    public void orderDurationByInsertion() {
        for (int i = 1; i < hurricanes.size(); i++) {
            Hurricane current = (Hurricane) hurricanes.get(i);
            boolean finished = false;
            for (int j = i; j > 0 && !finished; j--) {
                Hurricane previous = (Hurricane) hurricanes.get(j - 1);
                if (current.compareByDuration(previous) == 1) {
                    hurricanes.set(j, previous);
                    hurricanes.set(j - 1, current);
                }
                else
                    finished = true;
            }
        }
    }


    public Hurricane findHurricaneLongestDuration() {

        orderDurationByInsertion();
        Hurricane longest = (Hurricane) hurricanes.get(hurricanes.size() - 1);
        return longest;


    }


    /**
     * Returns the list of hurricanes.
     *
     * @return List of hurricanes.
     */
    public ArrayList getHurricanes() {
        return hurricanes;
    }

    /**
     * Registers a new hurricane in the system.
     * <b> post: </b> The hurricane was added successfully to the system.( If no hurricane with
     * the same name exists)
     *
     * @param pName                Name of the hurricane. pName != null && pName != "".
     * @param pCategory            Category of the hurricane. 1 <= pCategory <= 5.
     * @param pVelocity            Wind velocity. 0 <= pVelocity.
     * @param pEstimatedDamageCost Estimated damage cost. 0 <= pEstimatedDamageCost.
     * @param pImage               Hurricane image path. pImage != null && pImage != "".
     * @return True if the hurricane was added, false if contrary.
     */
    public boolean registerHurricane(String pName, int pCategory, int pVelocity,
                                     double pEstimatedDamageCost, String pImage) {
        int hurricaneFound = findHurricane(pName);
        boolean added = false;

        // Verify invariants before creating the object.
        if (hurricaneFound == -1) {
            Hurricane newHurricane =
                    new Hurricane(pName, pCategory, pVelocity, pEstimatedDamageCost, pImage);
            hurricanes.add(newHurricane);
            added = true;
        }
        verifyInvariant();
        return added;
    }

    /**
     * Orders the list of hurricanes in ascending order using bubble sort.  <br>
     * <b>post: </b> The list of hurricanes was ordered by name. (Ascending order).
     */
    public void orderByName() {
        for (int i = 0; i < hurricanes.size() - 1; i++) {
            for (int j = 0; j < hurricanes.size() - 1 - i; j++) {
                Hurricane hurricane1 = (Hurricane) hurricanes.get(j);
                Hurricane hurricane2 = (Hurricane) hurricanes.get(j + 1);
                if (hurricane1.compareByName(hurricane2) == 1) {
                    Hurricane temp = (Hurricane) hurricanes.get(j);
                    hurricanes.set(j, hurricane2);
                    hurricanes.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Orders the list of hurricanes in descending order using insertion sort.  <br>
     * <b>post: </b> The list of hurricanes was ordered by wind velocity. (Decreasing order).
     */
    public void orderByVelocity() {
        for (int i = 1; i < hurricanes.size(); i++) {
            Hurricane current = (Hurricane) hurricanes.get(i);
            boolean finished = false;
            for (int j = i; j > 0 && !finished; j--) {
                Hurricane previous = (Hurricane) hurricanes.get(j - 1);
                if (current.compareByVelocity(previous) == 1) {
                    hurricanes.set(j, previous);
                    hurricanes.set(j - 1, current);
                }
                else
                    finished = true;
            }
        }
    }

    /**
     * Orders the list of hurricanes in ascending order using selection sort.  <br>
     * <b>post: </b> The list of hurricanes was ordered by damages. (Ascending order).
     */
    public void orderByDamage() {
        for (int i = 0; i < hurricanes.size() - 1; i++) {
            int min_index = i;
            Hurricane minHurricane = (Hurricane) hurricanes.get(i);
            for (int j = i + 1; j < hurricanes.size(); j++) {
                Hurricane hurrPosition = (Hurricane) hurricanes.get(j);
                if (minHurricane.compareByDamage(hurrPosition) == 1) {
                    min_index = j;
                    minHurricane = hurrPosition;
                }
            }

            if (min_index != i) {
                Hurricane temp = minHurricane;
                hurricanes.set(i, minHurricane);
                hurricanes.set(min_index, temp);
            }
        }
    }

    /**
     * Finds a hurricane according to its name and returns the index in which it's found. <br>
     *
     * @param pName The name of the hurricane to be searched.
     *              pName!=null.
     * @return Position in which the hurricane with the name given is found.  If it is not
     * found, return -1.
     */
    public int findHurricane(String pName) {
        int position = -1;
        boolean finished = false;

        for (int i = 0; i < hurricanes.size() && !finished; i++) {
            Hurricane hurricanePosition = (Hurricane) hurricanes.get(i);
            String nameHurricane = hurricanePosition.getName();

            // The names are the same.
            if (nameHurricane.equalsIgnoreCase(pName)) {
                position = i;
                finished = true;
            }
        }
        return position;
    }

    /**
     * Finds a hurricane according to its name using a binary search. <br>
     * <b>pre: </b> The list of hurricanes is sorted by name.
     *
     * @param pName The name of the hurricane that will be searched. pName != null && pName != "".
     * @return Index of the hurricane with the given name.
     * If the hurricane doesn't exist return -1.
     */
    public int findBinaryByName(String pName) {

        //List must be sorted in order to perform binary search.
        orderByName();
        int index = -1;
        int start = 0;
        int fin = hurricanes.size() - 1;
        Hurricane found = new Hurricane(pName, 1, 1, 1.1, "a");
        while (start <= fin && index == -1) {
            int middle = (start + fin) / 2;
            Hurricane mid_Hurricane = (Hurricane) hurricanes.get(middle);


            if (mid_Hurricane.compareByName(found) == 0) {
                index = middle;
            }
            else if (mid_Hurricane.compareByName(found) > 0) {
                fin = middle - 1;
            }
            else {
                start = middle + 1;
            }
        }
        return index;

    }

    /**
     * Finds the hurricane with the highest estimated damage cost. <br>
     *
     * @return Index of the hurricane with the highest estimated damage cost.
     * If there are no hurricanes in the system, returns -1.
     */
    public int findHurricaneHighestDamage() {
        int max_index = -1;

        if (hurricanes.size() != 0) {
            // Assume 1st is the biggest.
            Hurricane max_hurricane = (Hurricane) hurricanes.get(0);
            for (int i = 1; i < hurricanes.size() - 1; i++) {
                Hurricane current_hurricane = (Hurricane) hurricanes.get(i);

                if (max_hurricane.compareByDamage(current_hurricane) == -1) {
                    max_index = i;
                    max_hurricane = current_hurricane;
                }
            }
        }
        return max_index;
    }

    /**
     * Finds the hurricane with the lowest estimated damage cost. <br>
     *
     * @return Index of the hurricane with the lowest estimated damage cost.
     * If there are no hurricanes in the system, returns -1.
     */
    public int findHurricaneLowestDamage() {
        int min_index = -1;

        if (hurricanes.size() != 0) {
            // Assume 1st is the smallest.
            Hurricane min_hurricane = (Hurricane) hurricanes.get(0);
            for (int i = 1; i < hurricanes.size() - 1; i++) {
                Hurricane current_hurricane = (Hurricane) hurricanes.get(i);

                if (min_hurricane.compareByDamage(current_hurricane) == 1) {
                    min_index = i;
                    min_hurricane = current_hurricane;
                }
            }
        }
        return min_index;
    }

    /**
     * Finds the hurricane with the highest wind velocity. <br>
     *
     * @return Index of the hurricane with the highest wind velocity.
     * If there are no hurricanes in the system, returns -1.
     */
    public int findHurricaneHighestVelocity() {
        int max_index = 0;

        if (hurricanes.size() != 0) {
            // Assume 1st is the biggest.
            Hurricane max_hurricane = (Hurricane) hurricanes.get(0);
            for (int i = 1; i < hurricanes.size() - 1; i++) {
                Hurricane current_hurricane = (Hurricane) hurricanes.get(i);

                if (max_hurricane.compareByVelocity(current_hurricane) == -1) {
                    max_index = i;
                    max_hurricane = current_hurricane;
                }
            }
        }
        else
            max_index = -1;
        return max_index;
    }

    // -----------------------------------------------------------------
    // Invariants
    // -----------------------------------------------------------------

    /**
     * Verifies that the invariants of the class are met. If something fails, it launches an
     * AssertionError. <br>
     *
     * <b>inv: </b>
     * The hurricanes array list is not empty. <br>
     */
    private void verifyInvariant() {
        assert hurricanes != null : "The hurricanes list cannot be null.";
    }

    // -----------------------------------------------------------------
    // Extension points
    // -----------------------------------------------------------------

    /**
     * Method for extension 1.
     *
     * @return Response 1.
     */
    public String method1() {
        return "Response 1";
    }

    /**
     * Method for extension .
     *
     * @return Response 2.
     */
    public String method2() {
        return "Response 2";
    }


}