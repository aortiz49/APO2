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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class that represents the train system. <br>
 * <b>inv:</b><br>
 * File path exists;  filePath != null. <br>
 * File path isn't an empty string; filePath != "". <br>
 * No two trains have the same id. <br>
 */
public class CupiTrains implements Serializable {
    // -------------------------------------------------------------
    // Constants
    // -------------------------------------------------------------

    /**
     * Serialization constant for the CupiTrains class.
     */
    private static final long serialVersionUID = 8200346310786852815L;

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * The first train in the train system.
     */
    private Train firstTrain;

    /**
     * Path to the file containing the train system information.
     * del world.
     */
    private String filePath;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Train system constructor.<br>
     * <b>post:</b> The found identified by the file path is loaded into the system. <br>
     * If a file exists containing the train system's information, creates a new system using this
     * information.
     *
     * @param pFilePath The file path of the file containing the train information.
     *                  pFilePath != null && pFilePath != "".
     * @throws PersistenceException If there is an error loading the file.
     **/
    public CupiTrains(String pFilePath) throws PersistenceException {
        filePath = pFilePath;
        openFile();
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Returns the first train.
     *
     * @return First train.
     */
    public Train getFirstTrain() {
        return firstTrain;
    }

    /**
     * Returns the total amount of available chairs in every train. <br>
     *
     * @return Total amount of available chairs.
     */
    public int getTotalAvailableChairs() {
        int totalAvailableChairs = 0;

        Train current = firstTrain;
        for (; current != null; ) {
            totalAvailableChairs += current.getTotalAmountOfAvailableSeats();
            current = current.nextTrain;
        }
        return totalAvailableChairs;
    }

    /**
     * Returns the total amount of money collected. <br>
     *
     * @return Total amount of money collected.
     */
    public double getTotalMoneyCollected() {
        double totalMoney = 0;

        Train current = firstTrain;
        for (; current != null; ) {
            totalMoney += current.getAmountCollected();
            current = current.nextTrain;
        }
        return totalMoney;
    }

    /**
     * Returns a list with all the train ids.
     *
     * @return Train ids.
     */
    public ArrayList<Integer> getTrainIDs() {
        ArrayList<Integer> ids = new ArrayList<>();

        Train actual = firstTrain;
        while (actual != null) {
            ids.add(actual.getId());
            actual = actual.getNextTrain();
        }

        return ids;
    }

    /**
     * Returns a list of wagon numbers of a train whose id is given by the parameter. <br>
     *
     * @param pIdTrain The train id.
     *                 pIdTrain != null && pIdTrain >= 0.
     * @return A list of the wagons numbers of a train.
     */
    public ArrayList<Integer> getWagonNumbers(int pIdTrain) {
        Train searchedTrain = findTrainById(pIdTrain);
        return searchedTrain.getWagonNumbers();
    }

    /**
     * Recovers the state of the systems through a serialized data file. <br>
     * <b>post:</b> The file is loaded into the system.
     *
     * @throws PersistenceException If there is an error loading the file.
     */
    public void openFile() throws PersistenceException {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                firstTrain = (Train) ois.readObject();
                ois.close();
            } catch (Exception e) {
                firstTrain = null;
                throw new PersistenceException(
                        "Error fatal: impossible to restore program state. (" + e.getMessage() + ")"
                                + ".");

            }
        }

        // If no file exists, the first train is null.
        else {
            firstTrain = null;
        }

    }

    /**
     * Finds a train that has the same origin and destination as the strings given by parameter
     * in that order. <br>
     *
     * @param pOrigin      Searched origin.
     *                     pOrigin != null && pOrigin != "".
     * @param pDestination Searched destiny.
     *                     pDestination != null && pDestination != "".
     * @return Train that contains the origin and destination given in its list of train stops,
     * null if no train is found with the given characteristics.
     */
    public Train findTrainByTrainStops(String pOrigin, String pDestination) {
        Train found = null;
        Train current = firstTrain;

        boolean isFound = false;


        while (current != null && !isFound) {
            String originTime = null;
            String destinationTime = null;
            ArrayList<TrainStop> currentStops = current.getTrainStops();
            for (int i = 0; i < currentStops.size(); i++) {
                if (currentStops.get(i).getName().equals(pOrigin)) {
                    originTime = currentStops.get(i).getSchedule();
                }
                else if (currentStops.get(i).getName().equals(pDestination)) {
                    destinationTime = currentStops.get(i).getSchedule();
                }
            }


            if ((originTime != null && destinationTime != null) && isEarlier(originTime,
                                                                             destinationTime)) {
                found = current;
                isFound = true;
            }
            current = current.getNextTrain();
        }

        return found;
    }

    /**
     * Returns the train with the id given by the parameter. <br>
     *
     * @param pIdTrain Id of the train to be found.
     * @return Found train, null if not found.
     */
    public Train findTrainById(int pIdTrain) {
        Train foundTrain = null;
        Train current = firstTrain;
        boolean found = false;
        while (current != null && !found) {
            if (current.getId() == pIdTrain) {
                foundTrain = current;
                found = true;
            }
            else
                current = current.getNextTrain();
        }

        return foundTrain;
    }

    /**
     * Adds a new train with the information given by the parameter. <br>
     * <b>pre: </b> The array with the names and the array with the arrival times of the train
     * stops have the same size and are ordered.
     * <b>post:</b> A new train was added to the list which was ordered in ascending order by
     * departure time.
     *
     * @param pIdTrain               Id of the train to be added.
     * @param pTrainStopNames        Array with the names of the train's stops.
     *                               pTrainStopNames !=null.
     * @param pTrainStopArrivalTimes Array with the arrival times of the train.
     *                               pTrainStopArrivalTimes != null.
     * @throws ElementExistsException If there already exists a train with the id given by the
     *                                parameter.
     */
    public void addNewTrain(int pIdTrain, String[] pTrainStopNames, Date[] pTrainStopArrivalTimes)
            throws ElementExistsException {

        Train found = findTrainById(pIdTrain);

        // Checks if the new train already exists and throws an exception.
        if (found != null)
            throw new ElementExistsException("Train cannot be added because it already exists.");

            // Checks if the train list is empty, and adds the new train as the head.
        else if (firstTrain == null) {
            firstTrain = new Train(pIdTrain);
            for (int i = 0; i < pTrainStopNames.length; i++) {
                firstTrain
                        .addTrainStop(new TrainStop(pTrainStopNames[i], pTrainStopArrivalTimes[i]));
            }
        }


        // Add train somewhere in the list if the list isn't null.
        else {

            Train newTrain = new Train(pIdTrain);

            // Adds the train to the tail of the train system list.
            for (int i = 0; i < pTrainStopNames.length; i++) {
                // Adds the stops for this train.
                newTrain.addTrainStop(new TrainStop(pTrainStopNames[i], pTrainStopArrivalTimes[i]));
            }

            // Check if the new train arrives earlier than the first train and add it to the head.
            if (isEarlier(newTrain.getDepartureTime(), firstTrain.getDepartureTime())) {

                // Save the first train in a temp variable.
                Train temp = firstTrain;

                // Set the first train to new Train.
                firstTrain = newTrain;

                // Set the next train of the first train to be the previous old train.
                firstTrain.changeNextTrain(temp);

                // Set the previous train of the old first train to the new first train
                temp.changePreviousTrain(firstTrain);
            }

            // Place the train somewhere else in the linked list.
            else {
                Train current = firstTrain.getNextTrain();
                Train last = null;
                boolean inserted = false;

                // Add the train to the tail when there is only one train in the system and it
                // arrives after this train.
                if (current == null) {
                    firstTrain.changeNextTrain(newTrain);
                    newTrain.changePreviousTrain(firstTrain);
                    inserted = true;
                }

                // Check if the new train departs earlier than other trains in the list.
                while (current != null && !inserted) {
                    if (isEarlier(newTrain.getDepartureTime(), current.getDepartureTime())) {
                        // Set the pointers from point of view of new Train
                        newTrain.changePreviousTrain(current.previousTrain);
                        newTrain.changeNextTrain(current);

                        // Set pointer from point of view of previous train.
                        current.previousTrain.changeNextTrain(newTrain);

                        // Set pointer from point of view of current train.
                        current.changePreviousTrain(newTrain);

                        inserted = true;
                    }

                    // Save the last train
                    if (current.getNextTrain() == null) {
                        last = current;
                    }

                    current = current.getNextTrain();

                }
                // If all trains in the list arrive earlier than the new train, add it to the
                // tail.
                if (!inserted) {
                    last.changeNextTrain(newTrain);
                    newTrain.changePreviousTrain(last);
                }

            }

        }
    }

    /**
     * Checks to see if the first time received in the parameter is earlier than the second
     * time.
     *
     * @param pTime1 The first time.
     * @param pTime2 The second time.
     * @return True if pTrain1 arrives earlier than pTRain2.
     */
    public boolean isEarlier(String pTime1, String pTime2) {

        boolean isEarlier = false;

        // Grab hours and minutes from first train.
        String tokens1[] = pTime1.split(":");
        int hours1 = Integer.parseInt(tokens1[0]);
        int minutes1 = Integer.parseInt(tokens1[1]);

        // Grab hours and minutes from second train.
        String tokens2[] = pTime2.split(":");
        int hours2 = Integer.parseInt(tokens2[0]);
        int minutes2 = Integer.parseInt(tokens2[1]);

        if ((hours1 == hours2) && (minutes1 < minutes2))
            isEarlier = true;

        else if (hours1 < hours2)
            isEarlier = true;

        return isEarlier;
    }


    /**
     * Adds a new wagon to the train whose id is given by the parameter with the
     * characteristics given. <br>
     * <b>post:</b> The wagon was added to the wagons list of the train indicated.
     *
     * @param pIdTrain       Id of the train where the wagon will be added.
     * @param pWagonNumber   Number of the wagon that will be added.
     * @param pAmountOfSeats Amount of seats in the wagon.
     *                       pAmountOfSeats >= 0.
     * @param pClass         Wagon's class.
     *                       pClass != null && pClass belongs to CLASSES.
     * @param pPrice         Price of one ticket.
     *                       pPrice >= 0.
     * @throws ElementExistsException       If a wagon already exists with the number given.
     * @throws ElementDoesNotExistException If a train with the given id doesn't exist.
     */
    public void addWagon(int pIdTrain, int pWagonNumber, int pAmountOfSeats, String pClass,
                         double pPrice)
            throws ElementExistsException, ElementDoesNotExistException {

        Train actual = findTrainById(pIdTrain);
        if (actual != null) {
            actual.addWagon(pWagonNumber, pAmountOfSeats, pClass, pPrice);
        }
        else {
            throw new ElementDoesNotExistException("The train id " + pIdTrain + " doesn't exist.");
        }
    }

    /**
     * Eliminates teh train whose id is given by the parameter. <br>
     * <b>post:</b> The train was eliminated from the list of trains.
     *
     * @param pIdTrain Id of the train to eliminate.
     * @throws ElementDoesNotExistException If a train with the given id doesn't exist in the list.
     */
    public void eliminateTrain(int pIdTrain) throws ElementDoesNotExistException {

        Train toDelete = findTrainById(pIdTrain);

        // If the train to be deleted doesn't exist, throw new exception.
        if (toDelete == null)
            throw new ElementDoesNotExistException("Train cannot be deleted.");

            // If the train list is empty, throw a new exception.
        else if (firstTrain == null)
            throw new ElementDoesNotExistException("The train list is empty.");

            // If the train to be deleted is the first train, set the next train as the first train.
        else if (pIdTrain == firstTrain.getId())
            firstTrain = firstTrain.getNextTrain();

            // Find the train somewhere in the list and eliminate it.
        else {
            Train current = firstTrain;

            while (current.getNextTrain() != toDelete) {
                current = current.getNextTrain();
            }

            // Checks if the wagon to delete is the last one.
            if (toDelete.getNextTrain() == null)
                current.changeNextTrain(null);

                // Removes the train when its between other trains.
            else {
                current.changeNextTrain(toDelete.getNextTrain());
                toDelete.getNextTrain().changePreviousTrain(current);
            }
        }
    }

    /**
     * Eliminates a wagon from a train whose train id and wagon number are given by the parameters.
     * <br>
     * <b>post:</b> The wagon was eliminated from the train's wagon list.
     *
     * @param pIdTrain  Id of the train where the wagon will be deleted from.
     * @param pNumWagon Number of the wagon to be eliminated.
     * @throws ElementDoesNotExistException If the either the train or the wagon don't exist.
     */
    public void eliminateWagon(int pIdTrain, int pNumWagon) throws ElementDoesNotExistException {
        try {
            findTrainById(pIdTrain).eliminateWagon(pNumWagon);
        } catch (NullPointerException e) {
            throw new ElementDoesNotExistException(
                    "A train with id: " + pIdTrain + " doesn't exist.");
        }
    }

    /**
     * Sells a train ticket for a train whose id and wagon number are given by the parameter. <br>
     * <b>post:</b> The amount of money collected in ticket sales is increased by the price of
     * the ticket and the amount of available seats on the wagon is decreased by 1. <br>
     *
     * @param pIdTrain  Id of the train in which the ticket will be sold.
     * @param pNumWagon Number of the wagon to be sold on the ticket.
     * @return True if the ticket was sold, false if otherwise.
     * @throws ElementDoesNotExistException If the train doesn't exists or if the wagon doesn't
     *                                      exist.
     */
    public boolean sellTicket(int pIdTrain, int pNumWagon) throws ElementDoesNotExistException {
        Train foundTrain = findTrainById(pIdTrain);
        if (foundTrain == null) {
            throw new ElementDoesNotExistException(
                    "A train with id: " + pIdTrain + " doesn't exist.");
        }
        return foundTrain.sellTicket(pNumWagon);
    }

    /**
     * Generates a report with the train information and the amount of money collected
     * from tickets. <br>
     * <b>post:</b> The report was saved in a file FileName in path pFilePath. <br>
     *
     * @param pFilePath Name of the path where the image will be stored.
     *                  pFilePath != "" && pFilePath != null.
     * @param pFileName Name of the file to save.
     *                  pFileName != "" &&  pFileName != null.
     * @throws FileNotFoundException If the file path doesn't exist.
     */
    public void generateReport(String pFilePath, String pFileName) throws FileNotFoundException {
        File reportDirectory = new File(pFilePath);
        if (!reportDirectory.exists()) {
            reportDirectory.mkdirs();
        }

        File reportFile = new File(reportDirectory, pFileName);
        PrintWriter out = new PrintWriter(reportFile);

        out.println("------- TRAIN REPORT -------- ");
        out.println();
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss ");

        out.println("Generated on: " + sdf.format(currentDate).toString());
        out.println("Total amount collected: " + getTotalMoneyCollected());
        out.println("Available seats: " + getTotalAvailableChairs());

        out.println();
        out.println("---- Detailed report ----");
        out.println();

        Train actual = firstTrain;

        while (actual != null) {
            actual.printTrainDetails(out);
            actual = actual.getNextTrain();
        }

        out.close();
    }

    /**
     * Saves the state of the application in a serialized file. <br>
     * <b>post:</b> The state was saved in the filepath. <br>
     *
     * @param pFilePath The path where the file will be saved.
     *                  pFilePath != "" && pFilePath != null.
     * @throws PersistenceException If there is a problem when saving the information.
     */
    public void saveState(String pFilePath) throws PersistenceException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pFilePath));
            oos.writeObject(firstTrain);
            oos.close();
        } catch (IOException e) {
            throw new PersistenceException("Error when saving the state.");
        }
    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------

    /**
     * Verifies the invariants of the CupiTrains class. <br>
     * <b>inv:</b><br>
     * File path exists;  filePath != null. <br>
     * File path isn't an empty string; filePath != "". <br>
     * No two trains have the same id. <br>
     */
    private void verifyInvariant() {
        assert filePath != null : "File path cannot be null.";
        assert !filePath.equals("") : "File path cannot be empty.";
        assert !isIdRepeated() : "There is at least one train with a repeated id";
    }

    /**
     * Checks to see if there are two trains with the same id.
     *
     * @return True if there are two trains with he same id, false if otherwise.
     */
    private boolean isIdRepeated() {
        boolean exists = false;
        Train train1 = firstTrain;
        while (train1 != null && !exists) {
            Train train2 = train1.getNextTrain();
            while (train2 != null) {
                if (train2.getId() == train1.getId())
                    exists = true;
                train2 = train2.getNextTrain();
            }
            train1 = train1.getNextTrain();
        }
        return exists;
    }

    // -------------------------------------------------------------
    // Methods de extensi�n
    // -------------------------------------------------------------

    /**
     * M�todo para la extensi�n1.
     *
     * @return repuesta1.
     */
    public String metodo1() {
        return "HELLO";
    }

    /**
     * M�todo para la extensi�n2.
     *
     * @return exists2.
     */
    public String metodo2() {
        return "Respuesta 2.";
    }



}