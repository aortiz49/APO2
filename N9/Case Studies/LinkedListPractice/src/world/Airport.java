/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n9_Airplanes
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package world;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Class that represents the airplane system. <br>
 * <b>inv:</b><br>
 * File path exists;  filePath != null. <br>
 * File path isn't an empty string; filePath != "". <br>
 * No two airplanes have the same id. <br>
 */
public class Airport implements Serializable {


    // -------------------------------------------------------------
    // Constants
    // -------------------------------------------------------------

    /**
     * Serialization constant for the Airport class.
     */
    private static final long serialVersionUID = 8200346310786852815L;

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * The first airplane in the airplane system.
     */
    private Airplane firstAirplane;

    /**
     * Path to the file containing the airplane system information.
     * del world.
     */
    private String filePath;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Airplane system constructor.<br>
     * <b>post:</b> The found identified by the file path is loaded into the system. <br>
     * If a file exists containing the airplane system's information, creates a new system using
     * this
     * information.
     *
     * @param pFilePath The file path of the file containing the airplane information.
     *                  pFilePath != null && pFilePath != "".
     * @throws IOException If there is an error loading the file.
     **/
    public Airport(String pFilePath) throws IOException {
        filePath = pFilePath;
        openFile();
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------


    public ArrayList<Airplane> getPlanesWithStops(String pStopName) {
        return null;
    }


    public void swampAirplanes(int pAirplaneId1, int pAirplaneId2) {


    }


    public Airplane getPlaneWithMostStops() {
        return null;
    }


    /**
     * Returns the first airplane.
     *
     * @return First airplane.
     */
    public Airplane getFirstAirplane() {
        return firstAirplane;
    }

    /**
     * Returns the total amount of available chairs in every airplane. <br>
     *
     * @return Total amount of available chairs.
     */
    public int getTotalAvailableSeats() {
        return 0;
    }


    /**
     * Returns a list with all the airplane ids.
     *
     * @return Airplane ids.
     */
    public ArrayList<Integer> getAirplaneIDs() {
        return null;
    }

    /**
     * Returns a list of wagon numbers of a airplane whose id is given by the parameter. <br>
     *
     * @param pIdAirplane The airplane id.
     *                    pIdAirplane != null && pIdAirplane >= 0.
     * @return A list of the wagons numbers of a airplane.
     */
    public ArrayList<Integer> getSeatNumbers(int pIdAirplane) {
        return null;
    }

    /**
     * Recovers the state of the systems through a serialized data file. <br>
     * <b>post:</b> The file is loaded into the system.
     *
     * @throws IOException If there is an error loading the file.
     */
    public void openFile() throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line;
                // Read every line
                while ((line = br.readLine()) != null) {

                    // Grab the name of the city

                    String city = line.substring(0, line.indexOf(":"));
                    String planeId = line.substring(line.indexOf(":") + 1);

                    while (!(line = br.readLine()).contains(":")) {

                        String seadId = line.substring(line.indexOf("-") + 1, line.indexOf("&"));
                        String


                    }

                }

            } catch (Exception e) {
                firstAirplane = null;
                throw new IOException("Fatal error: Cannot read file");

            }
        }

        // If no file exists, the first airplane is null.
        else {
            firstAirplane = null;
        }

    }


    /**
     * Returns the airplane with the id given by the parameter. <br>
     *
     * @param pIdAirplane Id of the airplane to be found.
     * @return Found airplane, null if not found.
     */
    public Airplane findAirplaneById(String pIdAirplane) {
        Airplane found = null;

        Airplane current = firstAirplane;
        boolean isFound = false;
        while (current != null) {
            if (current.getId().equals(pIdAirplane)) {
                found = current;
                isFound = true;
            }
            else
                current = current.getNextAirplane();
        }
        return found;
    }

    /**
     * Adds a new airplane with the information given by the parameter. <br>
     * <b>pre: </b> The array with the names and the array with the arrival times of the airplane
     * stops have the same size and are ordered.
     * <b>post:</b> A new airplane was added to the list which was ordered in ascending order by
     * departure time.
     *
     * @param pIdAirplane Id of the airplane to be added.
     * @throws Exception If plane exists
     */
    public void addNewAirplane(String pIdAirplane) throws Exception {

        // Checks if the list is null
        if (firstAirplane == null)
            firstAirplane = new Airplane(pIdAirplane);

            // Checks if plane already exists
        else if (findAirplaneById(pIdAirplane) != null)
            throw new Exception("Plane exists!");

            // Add it to the end.
        else {
            Airplane current = firstAirplane;

            while (current.getNextAirplane() != null) {
                current = current.getNextAirplane();
            }

            // Add the new train to the end of this, and adjust pointers
            Airplane newPlane = new Airplane(pIdAirplane);
            current.changeNextAirplane(newPlane);
            newPlane.changePreviousAirplane(current);
        }
    }


    /**
     * Adds a new wagon to the airplane whose id is given by the parameter with the
     * characteristics given. <br>
     * <b>post:</b> The wagon was added to the wagons list of the airplane indicated.
     *
     * @param pIdAirplane    Id of the airplane where the wagon will be added.
     * @param pSeatNumber    Number of the wagon that will be added.
     * @param pAmountOfSeats Amount of seats in the wagon.
     *                       pAmountOfSeats >= 0.
     * @param pClass         Seat's class.
     *                       pClass != null && pClass belongs to CLASSES.
     * @param pPrice         Price of one ticket.
     *                       pPrice >= 0.
     * @throws Exception If a wagon already exists with the number given.
     * @throws Exception If a airplane with the given id doesn't exist.
     */
    public void addSeat(String pIdAirplane, int pSeatNumber, int pAmountOfSeats, String pClass,
                        double pPrice) throws Exception, Exception {

        Airplane found = findAirplaneById(pIdAirplane);
        if (found == null)
            throw new Exception("Plane not found");

        // Checks if plane list is empty
        else if (firstAirplane == null)
            throw new Exception("Plane list is empty");

        else {
            found.addSeat(pSeatNumber,pClass);
        }
    }

    /**
     * Eliminates teh airplane whose id is given by the parameter. <br>
     * <b>post:</b> The airplane was eliminated from the list of airplanes.
     *
     * @param pIdAirplane Id of the airplane to eliminate.
     * @throws Exception If a airplane with the given id doesn't exist in the
     *                   list.
     */
    public void eliminateAirplane(int pIdAirplane) throws Exception {


    }

    /**
     * Eliminates a wagon from a airplane whose airplane id and wagon number are given by the
     * parameters.
     * <br>
     * <b>post:</b> The wagon was eliminated from the airplane's wagon list.
     *
     * @param pIdAirplane Id of the airplane where the wagon will be deleted from.
     * @param pNumSeat    Number of the wagon to be eliminated.
     * @throws Exception If the either the airplane or the wagon don't exist.
     */
    public void eliminateSeat(int pIdAirplane, int pNumSeat) throws Exception {

    }

    /**
     * Generates a report with the airplane information and the amount of money collected
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

        out.println("------- PLANE REPORT -------- ");
        out.println();
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss ");

        out.println("Generated on: " + sdf.format(currentDate).toString());
        out.println("Available seats: " + getTotalAvailableSeats());

        out.println();
        out.println("---- Detailed report ----");
        out.println();

        Airplane actual = firstAirplane;

        while (actual != null) {
            actual.printAirplaneDetails(out);
            actual = actual.getNextAirplane();
        }

        out.close();
    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------

    /**
     * Verifies the invariants of the Airport class. <br>
     * <b>inv:</b><br>
     * File path exists;  filePath != null. <br>
     * File path isn't an empty string; filePath != "". <br>
     * No two airplanes have the same id. <br>
     */
    private void verifyInvariant() {
        assert filePath != null : "File path cannot be null.";
        assert !filePath.equals("") : "File path cannot be empty.";
        assert !isIdRepeated() : "There is at least one airplane with a repeated id";
    }

    /**
     * Checks to see if there are two airplanes with the same id.
     *
     * @return True if there are two airplanes with he same id, false if otherwise.
     */
    private boolean isIdRepeated() {
        boolean exists = false;
        Airplane airplane1 = firstAirplane;
        while (airplane1 != null && !exists) {
            Airplane airplane2 = airplane1.getNextAirplane();
            while (airplane2 != null) {
                if (airplane2.getId() == airplane1.getId())
                    exists = true;
                airplane2 = airplane2.getNextAirplane();
            }
            airplane1 = airplane1.getNextAirplane();
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