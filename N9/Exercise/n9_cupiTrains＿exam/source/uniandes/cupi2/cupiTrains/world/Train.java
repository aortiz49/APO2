/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n9_Trains
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTrains.world;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that represents a train. <br>
 * <b>inv:</b><br>
 * No two wagons have the same name. <br>
 * The list of stops is initialized.<br>
 */
public class Train implements Serializable {

    // -------------------------------------------------------------
    // Constants
    // -------------------------------------------------------------

    /**
     * Serialization constant for the Train class.
     */
    private static final long serialVersionUID = -6154554262789086130L;

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Number that identifies the train.
     */
    private int id;

    /**
     * Next train.
     */
    Train nextTrain;

    /**
     * Previous train.
     */
    Train previousTrain;

    /**
     * Train's first wagon.
     */
    Wagon firstWagon;

    /**
     * Train stops.
     */
    private ArrayList<TrainStop> trainStops;

    /**
     * Amount of money collected from train ticket sales.
     */
    private double amountCollected;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Train constructor.<br>
     * <b>post:</b> A train was created with the id given by the parameter. <br>
     * The amount of money collected was initialized to 0. <br>
     * The list of trainStops was created was an empty vector. <br>
     * The first wagon, the previous train, and the next train are null.
     *
     * @param pId Number that identifies the train.
     */
    public Train(int pId) {
        id = pId;
        amountCollected = 0;
        trainStops = new ArrayList<>();
        firstWagon = null;
        previousTrain = null;
        nextTrain = null;
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    public boolean existenVagonesMismaCantidadDisponibles() {
    	boolean exists = false;
    	
    	Wagon current = firstWagon;
    	Wagon compared = firstWagon.getNextWagon();
    	int same = 0;
    	
    	while(current != null && compared!=null) {
    		// If the two wagons are not equal and the number of seats is the same increment the var
    		if((current != compared) && current.getTotalAmountOfAvailableSeats() == compared.getTotalAmountOfAvailableSeats()) {
    			same++;
    		}		
    		compared = compared.getNextWagon();
    		
    		// when we reach the end of the linked list, reset the compared wagon back to the first wagon and advance the current wagon by one
    		if(compared==null) {
    			compared = firstWagon;
    			current = current.getNextWagon();
    		}
    	}
    	
    	if(same == 0)
    		exists = false;
    	else if( same >= 2)
    		exists = true;
    	return exists;
    }
    
    public Wagon reverseWagons() {

        Wagon current = firstWagon;
        Wagon previous = null;
        Wagon next = null;

        while(current!=null){
            next = current.getNextWagon();
            current.changeNextWagon(previous);
            previous = current;
            current = next;

        }
        return previous;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Returns the next train.
     *
     * @return The next train.
     */
    public Train getNextTrain() {
        return nextTrain;
    }

    /**
     * Returns the previous train.
     *
     * @return The previous train.
     */
    public Train getPreviousTrain() {
        return previousTrain;
    }

    /**
     * Returns the first wagon on the train.
     *
     * @return First wagon on the train.
     */
    public Wagon getFirstWagon() {
        return firstWagon;
    }

    /**
     * Returns the train's id number.
     *
     * @return Train's id number..
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the train's trainStops.
     *
     * @return List of trainStops at which the train stops.
     */
    public ArrayList<TrainStop> getTrainStops() {
        return trainStops;
    }

    /**
     * Returns the train's originating trainStop.  <br>
     * This is the train's first stop.
     *
     * @return Train's origin.
     */
    public String getOrigin() {
        TrainStop first = trainStops.get(0);
        return first.getName();
    }

    /**
     * Returns the train's final destination. <br>
     * This is the train's last stop.
     *
     * @return Train's final destination.
     */
    public String getDestination() {
        TrainStop last = trainStops.get(trainStops.size() - 1);
        return last.getName();
    }

    /**
     * Returns the amount of money collected from train ticket sales. <br>
     *
     * @return Amount of money collected.
     */
    public double getAmountCollected() {
        return amountCollected;
    }

    /**
     * Returns the train's departure time from the first train stop.<br>
     *
     * @return Departure time.
     */
    public String getDepartureTime() {
        TrainStop first = trainStops.get(0);
        return first.getSchedule();
    }

    /**
     * Returns the train's arrival time to the last train stop. <br>
     *
     * @return Arrival time.
     */
    public String getArrivalTime() {
        TrainStop last = trainStops.get(trainStops.size() - 1);
        return last.getSchedule();
    }

    /**
     * Returns the numbers on the wagons.
     *
     * @return A list with the numbers on the wagons.
     */
    public ArrayList<Integer> getWagonNumbers() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        Wagon current = firstWagon;
        while (current != null) {
            numbers.add(current.getNumber());
            current = current.getNextWagon();
        }

        return numbers;
    }

    /**
     * Returns the amount of seats available on the train.
     *
     * @return Amount of seats available.
     */
    public int getTotalAmountOfAvailableSeats() {
        int numberOfSeats = 0;

        Wagon current = firstWagon;
        while (current != null) {
            numberOfSeats += current.getTotalAmountOfAvailableSeats();
            current = current.getNextWagon();
        }

        return numberOfSeats;
    }

    /**
     * Finds the wagon with the number given by the parameter. <br>
     *
     * @param pWagonNumber Number of the wagon to be found. pWagonNumber > 0.
     * @return Found wagon, null in case no wagon exists with that number.
     */
    public Wagon findWagonByNumber(int pWagonNumber) {
        Wagon found = null;
        Wagon current = firstWagon;

        boolean isFound = false;
        while (current != null && !isFound) {

            if (current.getNumber() == pWagonNumber) {
                found = current;
                isFound = true;
            }
            else
                current = current.getNextWagon();
        }
        return found;
    }

    /**
     * Verifies if the origin and destination cities given by the parameters are in the
     * train's stop list. <br>
     *
     * @param pOrigin      Desired origin city.
     *                     pOrigin != null && pOrigin != "".
     * @param pDestination Desired destination city.
     *                     pDestination != null && pDestination != "".
     * @return True if the cities are found in the list of train stops for the train, false if
     * otherwise.
     */
    public boolean containsTrainStops(String pOrigin, String pDestination) {
        boolean foundDestination = false;
        boolean foundOrigin = false;
        for (int i = 0; i < trainStops.size() && !foundDestination; i++) {
            TrainStop current = (TrainStop) trainStops.get(i);
            if (!foundOrigin) {
                if (pOrigin.equalsIgnoreCase(current.getName())) {
                    foundOrigin = true;
                }
            }
            else {
                if (pDestination.equalsIgnoreCase(current.getName())) {
                    foundDestination = true;
                }
            }
        }
        return foundDestination;
    }

    /**
     * Changes the next train to the one given by the parameter. <br>
     * <b>post:</b> The next train was changed to the one given by the parameter. <br>
     *
     * @param pNewNextTrain The next train.
     */
    public void changeNextTrain(Train pNewNextTrain) {
        nextTrain = pNewNextTrain;
        verifyInvariant();
    }

    /**
     * Changes the previous train to the one given by the parameter. <br>
     * <b>post:</b> The previous train was changed to the one given by the parameter. <br>
     *
     * @param pNewPreviousTrain The previous train.
     */
    public void changePreviousTrain(Train pNewPreviousTrain) {
        previousTrain = pNewPreviousTrain;
        verifyInvariant();
    }

    /**
     * Adds a new train stop to the train. <br>
     * <b> pre: </b> The arrival time is later than the existing arrival times for existing train
     * stops. <br>
     * <b> post: </b> A new train stop was added to the list of train stops. <br>
     *
     * @param pTrainStop Train stop to be added.
     *                   pTrainStop != null.
     */
    public void addTrainStop(TrainStop pTrainStop) {
        trainStops.add(pTrainStop);
    }

    /**
     * Adds a new wagon to the train with the values given by the parameters. <br>
     * <b>post:</b> The wagon is added to the end of the list. <br>
     *
     * @param pWagonNumber   Number of the wagon to be added. pWagonNumber > 0.
     * @param pAmountOfSeats Amount of seats in the wagon.
     *                       pAmountOfSeats >= 0.
     * @param pClass         Wagon class. pClass belongs a CLASSES.
     * @param pPrice         Price of one seat on the wagon. pPrice >= 0
     * @throws ElementExistsException If a wagon already exists with the number given.
     */
    public void addWagon(int pWagonNumber, int pAmountOfSeats, String pClass, double pPrice)
            throws ElementExistsException {

        // If there already exists a wagon with the number given, throw an exception.
        if (findWagonByNumber(pWagonNumber) != null)
            throw new ElementExistsException("Wagon number already exists!");

            // If there are no wagons, make this wagon the first one.
        else if (firstWagon == null) {
            firstWagon = new Wagon(pWagonNumber, pAmountOfSeats, pClass, pPrice);
        }

        // Get the last wagon.
        else {
            Wagon current = firstWagon;
            while (current.getNextWagon() != null) {
                current = current.getNextWagon();
            }
            // Adds the wagon to the TAIL of the train.
            current.changeNextWagon(new Wagon(pWagonNumber, pAmountOfSeats, pClass, pPrice));
        }
        verifyInvariant();

    }

    /**
     * Eliminates the wagon with the number given by the parameter. <br>
     * <b>post:</b> The wagon was eliminated from the list. <br>
     *
     * @param pWagonNumber Number of the wagon to eliminate. pWagonNumber > 0.
     * @throws ElementDoesNotExistException If the wagon with the given number does not exist.
     */
    public void eliminateWagon(int pWagonNumber) throws ElementDoesNotExistException {
        // This is the wagon we want to delete.
        Wagon toDelete = findWagonByNumber(pWagonNumber);

        // Checks if if the wagon exists in the list.
        if (toDelete == null)
            throw new ElementDoesNotExistException("The wagon doesn't exist!");

            // Checks the list is empty.
        else if (firstWagon == null)
            throw new ElementDoesNotExistException("The list is empty!");

            // Checks if it is the first wagon.
        else if (pWagonNumber == firstWagon.getNumber())
            firstWagon = firstWagon.getNextWagon();

            // Checks if the wagon is somewhere in the list.
        else {
            Wagon current = firstWagon;

            // Finds the wagon before the desired wagon.
            while (current.getNextWagon() != toDelete) {
                current = current.getNextWagon();
            }

            // Checks if the wagon to be deleted is the last wagon.
            if (toDelete.getNextWagon() == null)
                current.changeNextWagon(null);

                // This will in effect remove the wagon from the list.
            else
                current.changeNextWagon(toDelete.getNextWagon());

            // Finally, verify invariants.
            verifyInvariant();

        }
    }

    /**
     * Sells a ticket from the wagon number given by the parameter. <br>
     * <b>post:</b> The amount of money collected was incremented by the price of the ticket. <br>
     *
     * @param pWagonNumber Number of the wagon for which the ticket will be sold.
     *                     pWagonNumber > 0.
     * @return True if the ticket was sold, false if otherwise.
     * @throws ElementDoesNotExistException If there doesn't exist a wagon with the given number.
     */
    public boolean sellTicket(int pWagonNumber) throws ElementDoesNotExistException {
        Wagon wagonToSell = findWagonByNumber(pWagonNumber);
        if (wagonToSell == null) {
            throw new ElementDoesNotExistException(
                    "There doesn't a exist a wagon with number " + pWagonNumber);
        }
        else {
            boolean sold = wagonToSell.sellTicket();
            if (sold) {
                amountCollected += wagonToSell.getPrice();
            }

            return sold;
        }
    }

    /**
     * Prints the train's data to a file. <br>
     *
     * @param pWriter Object that writes the report to a file.
     *                pWriter != null.
     */
    public void printTrainDetails(PrintWriter pWriter) {
        pWriter.println("______________________");
        pWriter.println();
        pWriter.println("Train Id:  " + id);
        pWriter.println("First train stop: " + getOrigin() + " - " + getDepartureTime());
        pWriter.println("Last train stop: " + getDestination() + " - " + getArrivalTime());
        pWriter.println("Amount of train stops: " + trainStops.size());
        pWriter.println("Money collected: " + getAmountCollected());
        pWriter.println("Amount of available seats: " + getTotalAmountOfAvailableSeats());
    }

    /**
     * Compares two trains by departure time. <br>
     *
     * @param pTrain Train that is being compared to.
     *               pTrain != null. <br>
     * @return Returns 0 if the trains have the same departure time. <br>
     * Returns -1 if pTrain has a later departure time. <br>
     * Returns 1 if pTrain has an earlier departure time. <br>
     */
    public int compareByDepartureTime(Train pTrain) {
        int comparison = getDepartureTime().compareTo(pTrain.getDepartureTime());
        if (comparison > 0) {
            comparison = 1;
        }
        else if (comparison < 0) {
            comparison = -1;
        }
        return comparison;
    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------

    /**
     * Verifies the invariants of the Train class.  <br>
     * <b>inv:</b><br>
     * No two wagons have the same name. <br>
     * The list of stops is initialized.<br>
     */
    private void verifyInvariant() {
        assert !existsRepeatedWagon() : "There is more than one wagon with the same number.";
        assert trainStops != null : "The list of train stops isn't initialized.";
    }

    /**
     * Verifies if there are two wagons with the same number.
     *
     * @return True if there are two wagons with the same number, false if otherwise.
     */
    private boolean existsRepeatedWagon() {
        boolean exists = false;
        Wagon wagon1 = firstWagon;
        while (wagon1 != null && !exists) {
            Wagon wagon2 = wagon1.getNextWagon();
            while (wagon2 != null) {
                if (wagon2.getNumber() == wagon1.getNumber())
                    exists = true;
                wagon2 = wagon2.getNextWagon();
            }
            wagon1 = wagon1.getNextWagon();
        }
        return exists;
    }



    
}