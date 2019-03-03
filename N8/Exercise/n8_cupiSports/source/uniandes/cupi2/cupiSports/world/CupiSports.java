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

import java.io.*;
import uniandes.cupi2.entrenadorArchivos.mundo.BufferedReader;
import uniandes.cupi2.entrenadorArchivos.mundo.PrintWriter;
import java.util.ArrayList;
import java.util.Date;


/**
 * Class in charge of administrating the CupiSports system. <br>
 * <b>inv:</b><br>
 * sports != null. <br>
 * No two teams can exist with the same name. <br>
 */
public class CupiSports {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * List of sports.
     */
    private ArrayList<Sport> sports;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs a new sports system. <br>
     * If the indicated file doesn't exist, it will create an empty sports list. <br>
     * If the file exists, it will load the sports information and its athletes.
     *
     * @param pFileName Name of the file containing the serialized data.
     *                  pFileName != null && pFileName != "".
     * @throws PersistenceException An exception is thrown if there is an error loading the data
     *                              from the file.
     */
    public CupiSports(String pFileName) throws PersistenceException {

        File file = new File(pFileName);
        if (file.exists()) {
            // If the file exists, deserialize and recover the app's internal data and update the
            // app.
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                sports = (ArrayList) ois.readObject();
                ois.close();
            } catch (Exception e) {

                e.printStackTrace();
                throw new PersistenceException(
                        "Fatal error: Impossible to restore the previous state of the program (" + e
                                .getMessage() + ")");
            }
        }
        else {
            // If the file doesn't exist, this is the first time the program is executed or the
            // data file was deleted.
            sports = new ArrayList();
        }
        verifyInvariants();
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns a list containing every sport.
     *
     * @return List with all sports.
     */
    public ArrayList getSports() {
        return sports;
    }

    /**
     * Adds a sport with the information given by the parameters to the list of sports. <br>
     * <b>post: </b> The sport was added to the list.
     *
     * @param pNameSport                  Name of the sport.
     *                                    pNameSport != null && pNameSport != "".
     * @param pRegulatoryEntity           Regulatory entity of the sport.
     *                                    pRegulatoryEntity != null && pRegulatoryEntity != "".
     * @param pNumberOfRegisteredAthletes Number of registered athletes.
     *                                    pNumberOfRegisteredAthletes > 0.
     * @param pImagePath                  Image path of the sport.
     *                                    pImagePath != null && pImagePath != "".
     * @throws ElementExistsException Throws an exception if there already exists a sport with
     *                                the given name.
     */
    public void addSport(String pNameSport, String pRegulatoryEntity,
                         int pNumberOfRegisteredAthletes, String pImagePath)
            throws ElementExistsException {
        if (existsSport(pNameSport)) {
            throw new ElementExistsException(ElementExistsException.REPEATED_SPORT, pNameSport);
        }
        else {
            Sport newSport = new Sport(pNameSport, pRegulatoryEntity, pNumberOfRegisteredAthletes,
                                       pImagePath);
            sports.add(newSport);
            verifyInvariants();
        }
    }

    /**
     * Eliminates the sport with the name given by the parameter from the sports list. <br>
     * <b>post: </b> Sport was eliminated from the list.
     *
     * @param pNameSport Name of the sport a delete.
     *                   pNameSport != null && pNameSport != "".
     */
    public void deleteSport(String pNameSport) {
        boolean found = false;
        for (int i = 0; i < sports.size() && !found; i++) {
            Sport currentSportActual = sports.get(i);
            if (currentSportActual.getName().equals(pNameSport)) {
                sports.remove(i);
                found = true;
            }
        }
        verifyInvariants();
    }

    /**
     * Adds an outstanding athlete to a sport with the information given by the parameter. <br>
     * <b>post: </b> The outstanding athlete was added to the corresponding sport.
     *
     * @param pNameSport        Name of the sport.
     *                          pNameSport != null && pNameSport != "".
     * @param pNameAthlete      Name of the outstanding athlete.
     *                          pNameAthlete !=null &&  pNameAthlete != "".
     * @param pAge              Age of the athlete. pAge > 0.
     * @param pPlaceOfResidency Athlete's place of residence.
     *                          pPlaceOfResidency != null && pPlaceOfResidency != "".
     * @param pAmountOfTrophies Amount of trophies won by the athlete.
     *                          pAmountOfTrophies >= 0.
     * @param pImagePathAthlete Image path of the athlete.
     *                          pImagePathAthlete != null && pImagePathAthlete != "".
     * @throws ElementExistsException Throws an exception if there already exists an athlete
     *                                in the sport with the given name.
     */
    public void addOutstandingAthlete(String pNameSport, String pNameAthlete, int pAge,
                                      String pPlaceOfResidency, int pAmountOfTrophies,
                                      String pImagePathAthlete) throws ElementExistsException {

        boolean added = false;
        for (int i = 0; i < sports.size() && !added; i++) {
            Sport currentSport = sports.get(i);

            Athlete newAthlete =
                    new Athlete(pNameAthlete, pAge, pPlaceOfResidency, pAmountOfTrophies,
                                pImagePathAthlete);

            // If the new athlete already exists in the current sport, throw an exception.
            if (currentSport.getName().equals(pNameSport) && currentSport
                    .outstandingAthleteExists(pNameAthlete))
                throw new ElementExistsException(ElementExistsException.REPEATED_ATHLETE,
                                                 pNameAthlete);

                // Checks if the current sport matches the new player's sport.
            else if (currentSport.getName().equals(pNameSport)) {
                currentSport.addOutstandingAthlete(newAthlete);
                added = true;
            }
        }
        verifyInvariants();
    }

    /**
     * Eliminates an outstanding athlete with the name given by the parameter from the sport
     * given by the parameter.  <br>
     * <b>post: </b> The outstanding player was eliminated from the corresponding sport.
     *
     * @param pNameSport   Name of the sport.
     *                     pNameSport != null && pNameSport != "".
     * @param pNameAthlete Name of the athlete a delete.
     *                     pNameAthlete != null && pNameAthlete != "".
     */
    public void eliminateOutstandingAthlete(String pNameSport, String pNameAthlete) {
        boolean found = false;
        for (int i = 0; i < sports.size() && !found; i++) {
            Sport currentSportActual = sports.get(i);
            if (currentSportActual.getName().equals(pNameSport)) {
                currentSportActual.eliminateOutstandingAthlete(pNameAthlete);
                found = true;
            }
        }
        verifyInvariants();
    }

    /**
     * Checks to see if a sport with the name given by the parameter exists in the list.
     *
     * @param pNameSport Name of the sport. pNameSport != null && pNameSport != "".
     * @return True if the sport exists, false if contrary.
     */
    public boolean existsSport(String pNameSport) {
        boolean exists = false;

        for (int i = 0; i < sports.size() && !exists; i++) {
            Sport currentSportActual = sports.get(i);
            if (currentSportActual.getName().equals(pNameSport)) {
                exists = true;
            }
        }
        return exists;
    }

    /**
     * Returns the outstanding athlete with the most trophies won.  <br>
     * If there are two or more athletes with the same number of trophies, return either one of
     * them.
     *
     * @return The athlete who has the most trophies. If there are no athletes, return null.
     */
    public Athlete getAthleteMostTrophies() {
        Athlete mostTrophies = null;
        int max = 0;
        if (sports.size() != 0) {
            for (int i = 0; i < sports.size(); i++) {
                Sport currentSportActual = sports.get(i);
                Athlete currentAthleteMas = currentSportActual.getAthleteMostTrophies();
                if (currentAthleteMas.getAmountOfTrophies() > max) {
                    mostTrophies = currentAthleteMas;
                    max = currentAthleteMas.getAmountOfTrophies();
                }
            }
        }
        return mostTrophies;
    }

    /**
     * Returns the total amount of trophies across all sports.
     *
     * @return Total amount of trophies.
     */
    public int getTotalTrophies() {
        int total = 0;

        for (int i = 0; i < sports.size(); i++) {
            Sport currentSportActual = sports.get(i);
            total += currentSportActual.getTotalTrophies();
        }

        return total;
    }

    /**
     * Serializes the sports ArrayList in the file whose path is given by the parameter.
     *
     * @param pFilePath Path where the file will be saved.
     *                  pFilePath != null &&
     *                  pFilePath != "".
     * @throws PersistenceException Throws an exception if there is a problem saving the
     *                              program's state in the file.
     */
    public void saveState(String pFilePath) throws PersistenceException {
        File file = new File(pFilePath);

        // If the file given by the parameter doesn't exist, create a new file, serialize the
        // data from the program and store it in the given data path.
        if (!file.exists()) {
            File directory = new File("./data/");
            file = new File(directory, "cupiSports.data");
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(sports);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new PersistenceException("Error when saving: " + e.getMessage());
            }
        }

        else {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pFilePath));
                oos.writeObject(sports);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new PersistenceException("Error when saving: " + e.getMessage());
            }
        }
    }

    /**
     * Updates the information of an athlete from information given by a text file.
     *
     * @param pLinea Line of text containing the information to update an athlete.
     *               pLine != null && pLine != "".
     * @throws FileFormatException Throws an exception if the line of text doesn't comply with
     *                             the defined format to update the athlete's information.
     */
    private void updateAthlete(String pLinea) throws FileFormatException {
        String[] string = pLinea.split(";");
        int num = string.length;

        // Check if line is in the correct format.
        if (num != 6) {
            // The number is six because that's the number of elements separated by the delimiter.
            throw new FileFormatException("Incorrect format");
        }

        else {

            // Store the values that will be used to modify the athlete.
            String fileSport = string[0];
            String newNameAthlete = string[1];
            String newAge = string[2];
            String newCity = string[3];
            String newTrophies = string[4];
            String newImage = string[5];

            if (!existsSport(fileSport)) {
                throw new FileFormatException(
                        "The athlete couldn't be updated because " + "the sport isn't in the list");
            }

            // Verify that the city name is not empty
            boolean inValidResidency = newCity.equals("");

            // Verify that the age is not less than zero and contains only numbers
            boolean ageIsInValid =
                    newAge.equals("") || Integer.parseInt(newAge) < 0 || !newAge.matches("\\d+");

            // Verify that the age is not less than zero and contains only numbers.
            boolean trophyCountIsInValid =
                    newTrophies.equals("") || Integer.parseInt(newTrophies) < 0 || !newTrophies
                            .matches("\\d+");
            // If the values are valid, continue
            if (!(inValidResidency || ageIsInValid || trophyCountIsInValid)) {

                boolean modified = false;
                Sport found;
                ArrayList<Athlete> athletes;

                // Search for the sport the athlete plays
                for (int i = 0; i < sports.size() && !modified; i++) {
                    Sport sport = sports.get(i);
                    if (sport.getName().equals(fileSport)) {
                        found = sport;

                        // Create an ArrayList of the outstanding athletes in the current sport.
                        athletes = found.getOutstandingAthletes();

                        // If the sport has no outstanding athletes, throw an exception
                        if (athletes.size() == 0)
                            throw new FileFormatException(
                                    "There are no outstanding athletes in this sport. Athlete "
                                            + "was not updated.");

                        for (int j = 0; j < athletes.size() && !modified; j++) {
                            Athlete athlete = athletes.get(j);
                            if (athlete.getName().equals(newNameAthlete)) {

                                athlete.modifyAge(Integer.parseInt(newAge));
                                athlete.modifyPlaceOfResidency(newCity);
                                athlete.modifyAmountOfTrophies(Integer.parseInt(newTrophies));
                                athlete.modifyImagePath(newImage);

                                verifyInvariants();
                                modified = true;
                            }
                        }
                    }
                }

                if (!modified)
                    throw new FileFormatException(newNameAthlete + " doesn't exist in this sport.");
            }
        }
    }


    /**
     * Updates the information of the athletes in a text file.
     *
     * @param pFile File from which the athlete information is loaded from.
     *              pFile != null.
     * @throws FileFormatException If the file doesn't comply with the defined format to update
     *                             the information.
     * @throws IOException         If there are problems when reading the information from the
     *                             file.
     */
    public void updateAthletesInformation(File pFile) throws FileFormatException, IOException {
        long startTime = System.currentTimeMillis();
        if (sports.size() == 0)
            throw new FileFormatException(
                    "There are no sports in the system! Athletes weren't updated.");

        BufferedReader reader = new BufferedReader(new FileReader(pFile));
        File file = new File(pFile.getAbsolutePath());

        if (file.exists()) {
            String fileName = file.toString();
            String p1 = fileName.substring(fileName.indexOf(".") + 1);
            String p2 = p1.substring(p1.indexOf(".") + 1);
            if (!file.isDirectory() && !p2.equals("txt")) {
                throw new FileFormatException("File isn't a .txt file!");
            }

            String line;
            String firstLine;
            try {
                line = reader.readLine();
                firstLine = line;
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }

            if (line == null)
                throw new FileFormatException("The file is empty");
            else {
                for (int i = 0; i < Integer.parseInt(firstLine); i++) {
                    line = reader.readLine();
                    if (line != null)
                        updateAthlete(line);
                    else
                        throw new FileFormatException("There is no information to read!");

                }
            }
        }
        else {
            throw new FileNotFoundException();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Total time with BufferedInputStream: %d ms", endTime - startTime));
    }

    /**
     * Generates the report of the athletes trophies.
     *
     * @param pFilePath Filepath where the file with the report will be saved to.
     *                  pFilePath !=null && pFilePath != "".
     * @throws IOException If an error occurs when generating the report.
     */
    public void generateTrophyReport(String pFilePath) throws IOException {

        String x = "test";
        try {
            addSportNew();
        } catch (FileFormatException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter log = new PrintWriter(new FileWriter(pFilePath, true));
            Date current = new Date();
            log.println(
                    "****************************************************************************************");
            log.println("                             CupiSports - Trophy Report");
            log.println(
                    "****************************************************************************************");
            log.println("                                           **");
            log.println();
            log.println("Date:            " + current + ".");
            log.println("Athlete with most trophies:     " + getAthleteMostTrophies().getName());
            log.println("Total trophies: " + getTotalTrophies() + ".");
            log.close();
        } catch (IOException e) {
            throw new IOException("Error when saving: " + e.getMessage());

        }
    }

    // -----------------------------------------------------------------
    // Invariants
    // -----------------------------------------------------------------

    /**
     * VVerifies the invariants of the class:  <br>
     * sports != null <br>
     * No two sports exist with the same name. <br>
     */
    private void verifyInvariants() {
        assert sports != null : "The list of sports is null.";
        assert !buscarSportsConElMismoName() : "There are sports with the same name.";
    }

    /**
     * Checks if there are two sports with the same name.
     *
     * @return Returns true if there are two sports with the same name. False is otherwise.
     */
    private boolean buscarSportsConElMismoName() {
        for (int i = 0; i < sports.size(); i++) {
            Sport currentSport1 = sports.get(i);

            for (int j = i + 1; j < sports.size(); j++) {
                Sport currentSport2 = sports.get(j);
                if (currentSport1.equals(currentSport2))
                    return true;
            }
        }
        return false;
    }


    // -----------------------------------------------------------------
    // Extension points
    // -----------------------------------------------------------------

    /**
     * Method for extension  1
     *
     * @return response1
     */
    public String method1() {
        return "Response 1";
    }

    /**
     * Method for extension 2
     *
     * @return response2
     */
    public String method2() {
        return "Response 2";
    }


    public void addSportNew() throws FileFormatException, IOException {
        BufferedReader br = new BufferedReader(
                new FileReader("./data/updatePlayerInformation" + "/testData.txt"));
        String line = null;
        String num = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (line == null)
            throw new FileFormatException("The file is empty");
        else{
            while((line = br.readLine()) != null){
                if(line.startsWith("SPORT:")){
                    String[] tokens = line.split(";");
                    System.out.println(tokens[0]);
                }
            }
        }


    }





}