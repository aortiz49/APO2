/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n10_carFactory
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.carFactory.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Class that represents the car factory.
 */
public class CarFactory {

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * File path to the factory data.
     */
    private String filePath;

    /**
     * List containing the factory parts, ordered in the order in which they were added. <br>
     * The parts are painted in the order in which they appear in this list.
     */
    private List parts;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs a new factory without parts. <br>
     * <b>post: </b> Parts list is initialized. <br>
     */
    public CarFactory() {
        filePath = null;
        parts = new ArrayList();
        verifyInvariants();
    }

    /**
     * Returns the factory's collection of car parts. <br>
     *
     * @return Collection of car parts.
     */
    public Collection getParts() {
        return parts;
    }

    //
    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Returns the path where the factory information is kept. <br>
     *
     * @return File path. If it hasn't been defined yet, returns null.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Returns a list with all the possible types of car parts.
     *
     * @return List containing type sof car parts. Lista con los types de las parts.
     */
    public ArrayList getPartTypes() {
        ArrayList types = new ArrayList();
        types.add(TruckHood.TYPE);
        types.add(CompactHood.TYPE);
        types.add(SedanHood.TYPE);
        types.add(CompactTrunk.TYPE);
        types.add(SedanTrunk.TYPE);
        types.add(TruckTrunk.TYPE);
        types.add(Tesselation.TYPE);
        types.add(LightningRod.TYPE);
        types.add(Skull.TYPE);
        types.add(LuxuryRims.TYPE);
        types.add(EconomyRims.TYPE);
        types.add(MediumRims.TYPE);

        return types;
    }

    /**
     * Loads a factory by reading a text file. <br>
     * <b>pre: </b> The text file exists. <br>
     * <b>post: </b> The previous factory was eliminated and the new one was loaded. <br>
     * The filepath was updated.
     *
     * @param pFilePath New file path where the information will be read from.
     *                  pFilePath!=null && !pFilePath.equals("").
     * @throws Exception If there are problems reading the file path.
     * @throws Exception If the format of the file is not of the expected format.
     */
    public void openFile(String pFilePath) throws Exception {
        List previousParts = new ArrayList();
        // Load the parts
        BufferedReader br = new BufferedReader(new FileReader(pFilePath));
        String linea = br.readLine();
        try {
            // Save all the previous parts in case an error occurs.
            previousParts.addAll(parts);

            // Eliminate all parts.
            parts.clear();

            // Create the parts
            int numberOfNewParts = Integer.parseInt(linea);
            for (int i = 0; i < numberOfNewParts; i++) {
                linea = br.readLine();
                createPart(linea, br);
            }

            br.close();
        } catch (NumberFormatException nfe) {
            br.close();
            parts.clear();
            parts.addAll(previousParts);
            throw new Exception("Format Error");
        }

        // Changes the filepath to a new path.
        filePath = pFilePath;

        verifyInvariants();
    }

    /**
     * Adds a part given by the parameter to the car factory. <br>
     * <b>post: </b> The car part was added to the factory.
     *
     * @param pF Part to be added.
     *           pF != null.
     */
    public void addPart(IPart pF) {
        parts.add(pF);
        verifyInvariants();
    }

    /**
     * Returns the last car part in the list tha contains the points given by parameter.
     *
     * @param pX Found x coordinate.
     *           pX >= 0.
     * @param pY Found y coordinate.
     *           pY >= 0.
     * @return Part containing the (x,y) coordinate given, false if otherwise.
     */
    public IPart findPart(int pX, int pY) {
        // Create an iterator for all car parts.
        Iterator iter = parts.iterator();

        IPart found = null;

        while (iter.hasNext()) {

            // Save each part as type IPart.
            IPart part = (IPart) iter.next();

            // Save each part.
            if (part.pointIsInside(pX, pY)) {
                found = part;
            }
        }
        return found;
    }

    /**
     * Changes the position of a part to a new (x,y) coordinate value given by the parameters. <br>
     * <b>post: </b> The part with the given coordinates now has different coordinates.
     *
     * @param pX    Coordinate in x where the part was found.
     *              pX >= 0.
     * @param pY    Coordinate in y where the part was found.
     *              pY >= 0.
     * @param pNewX New coordinate in x where the part will be positioned.
     *              pNewX >= 0.
     * @param pNewY New coordinate in y where the part will be positioned.
     *              pNewY >= 0.
     */
    public void changePartPosition(int pX, int pY, int pNewX, int pNewY) {
        IPart foundPart = findPart(pX, pY);
        if (foundPart != null) {
            foundPart.changeX(pNewX);
            foundPart.changeY(pNewY);
        }
        verifyInvariants();
    }

    /**
     * Creates a part with the data contained in the filepath and adds it to the list. <br>
     * <b>post: </b> A new part was created and added to the car factory.
     *
     * @param pPartType Type of part to create.
     *                  pPartType !=null && pPartType belongs to {
     *                  TruckHood.TYPE, CompactHood.TYPE, SedanHood.TYPE, LuxuryRims.TYPE,
     *                  MediumRims.TYPE, EconomyRims.TYPE, TruckTrunk.TYPE, CompactTrunk.TYPE,
     *                  SedanTrunk.TYPE}.
     * @param pBr       Stream from which the data will be read to create the part.
     *                  pBr!=null.
     * @throws Exception If there are problems reading the file path.
     * @throws Exception If the format of the file is not of the expected format.
     */
    private void createPart(String pPartType, BufferedReader pBr) throws Exception {
        IPart newPart = null;
        if (pPartType.equals(MediumRims.TYPE)) {
            newPart = new MediumRims(pBr);
        }
        else if (pPartType.equals(EconomyRims.TYPE)) {
            newPart = new EconomyRims(pBr);
        }
        else if (pPartType.equals(LuxuryRims.TYPE)) {
            newPart = new LuxuryRims(pBr);
        }
        else if (pPartType.equals(TruckHood.TYPE)) {
            newPart = new TruckHood(pBr);
        }
        else if (pPartType.equals(TruckTrunk.TYPE)) {
            newPart = new TruckTrunk(pBr);
        }
        else if (pPartType.equals(SedanHood.TYPE)) {
            newPart = new SedanHood(pBr);
        }
        else if (pPartType.equals(SedanTrunk.TYPE)) {
            newPart = new SedanTrunk(pBr);
        }
        else if (pPartType.equals(CompactHood.TYPE)) {
            newPart = new CompactHood(pBr);
        }
        else if (pPartType.equals(CompactTrunk.TYPE)) {
            newPart = new CompactTrunk(pBr);
        }
        else if (pPartType.equals(Skull.TYPE)) {
            newPart = new Skull(pBr);
        }
        else if (pPartType.equals(Tesselation.TYPE)) {
            newPart = new Tesselation(pBr);
        }
        else if (pPartType.equals(LightningRod.TYPE)) {
            newPart = new LightningRod(pBr);
        }
        else {
            throw new Exception(
                    "Error in filePath format. The type " + pPartType + " is incorrect.");
        }
        parts.add(newPart);
        verifyInvariants();
    }

    /**
     * Creates and returns a part with the specifications given by the parameters. <br>
     * <b> post: </b> The part was created with the parameters.
     *
     * @param pPartType Type of part to create.
     *                  pPartType !=null && pPartType belongs to {
     *                  TruckHood.TYPE, CompactHood.TYPE, SedanHood.TYPE, LuxuryRims.TYPE,
     *                  MediumRims.TYPE, EconomyRims.TYPE, TruckTrunk.TYPE, CompactTrunk.TYPE,
     *                  SedanTrunk.TYPE}.
     * @param pX        Coordinate in x of the part. pX >= 0.
     * @param pY        Coordinate in y de the part. pY >= 0.
     * @param pCarColor Color of the car.
     *                  pCarColor != null.
     * @return Part created with the given specifications.
     */

    public Part createPart(String pPartType, int pX, int pY, Color pCarColor) {
        Part newPart = null;
        if (pPartType.equals(MediumRims.TYPE)) {
            newPart = new MediumRims(pX, pY, pCarColor);
        }
        else if (pPartType.equals(EconomyRims.TYPE)) {
            newPart = new EconomyRims(pX, pY, pCarColor);
        }
        else if (pPartType.equals(LuxuryRims.TYPE)) {
            newPart = new LuxuryRims(pX, pY, pCarColor);
        }
        else if (pPartType.equals(TruckHood.TYPE)) {
            newPart = new TruckHood(pX, pY, pCarColor);
        }
        else if (pPartType.equals(TruckTrunk.TYPE)) {
            newPart = new TruckTrunk(pX, pY, pCarColor);
        }
        else if (pPartType.equals(SedanHood.TYPE)) {
            newPart = new SedanHood(pX, pY, pCarColor);
        }
        else if (pPartType.equals(SedanTrunk.TYPE)) {
            newPart = new SedanTrunk(pX, pY, pCarColor);
        }
        else if (pPartType.equals(CompactHood.TYPE)) {
            newPart = new CompactHood(pX, pY, pCarColor);
        }
        else if (pPartType.equals(CompactTrunk.TYPE)) {
            newPart = new CompactTrunk(pX, pY, pCarColor);
        }
        else if (pPartType.equals(Skull.TYPE)) {
            newPart = new Skull(pX, pY, pCarColor);
        }
        else if (pPartType.equals(Tesselation.TYPE)) {
            newPart = new Tesselation(pX, pY, pCarColor);
        }
        else if (pPartType.equals(LightningRod.TYPE)) {
            newPart = new LightningRod(pX, pY, pCarColor);
        }
        return newPart;
    }

    /**
     * Eliminates the part containing the specified coordinates.  <br>
     * If no part is found that contains these coordinates, then nothing is eliminated. S<br>
     * <b>post: </b> The part matching the specified coordinates was eliminated from the list of
     * parts.
     *
     * @param pX Coordinate in x of the part to be eliminated. pX >= 0.
     * @param pY Coordinate in y of the part to be eliminated. pY >= 0.
     */
    public void eliminatePart(int pX, int pY) {

        //  IPart found = findPart(pX, pY);
        //parts.remove(found);

        // Create an iterator for all car parts.
        Iterator iter = parts.iterator();

        while (iter.hasNext()) {

            // Save each part as type IPart.
            IPart part = (IPart) iter.next();
            if (part.pointIsInside(pX, pY))
                iter.remove();
        }
    }

    /**
     * Paints all parts on the specified canvas. <br>
     * <b>post: </b> All parts were painted on the canvas.
     *
     * @param pG A surface on which the parts will be painted on.
     *           pG != null && pG is a clean surface. (It will not be erased before painting).
     */
    public void paintParts(Graphics2D pG) {

        Iterator iter = parts.iterator();

        while (iter.hasNext()) {

            // Save each part as type IPart.
            IPart part = (IPart) iter.next();

            part.paintShadowed(pG);
        }
    }

    /**
     * Restarts the car factory, eliminating all the parts. <br>
     * <b>post: </b> The list of parts was cleared and the filepath is null.
     */
    public void restart() {
        parts.clear();
        filePath = null;
        verifyInvariants();
    }

    /**
     * Saves the current factory in the filepath that has been being used. <br>
     * <b>post: </b> Factory was saved in the filepath.
     *
     * @throws IOException If there ae exceptions saving the factory.
     */
    public void save() throws IOException {

        PrintWriter output = new PrintWriter(filePath);

        // Create an iterator for all car parts.
        Iterator iter = parts.iterator();

        output.println(parts.size());

        while (iter.hasNext()) {

            // Save each part as type IPart.
            IPart part = (IPart) iter.next();

            // Save each part.
            part.save(output);
        }
        output.close();

    }

    /**
     * Saves the current factory in the filepath given by the parameter. <br>
     * <b>post: </b> The filepath was updated and the factory was saved in the new filepath.
     *
     * @param pFilePath Name of the filepath where the factory will be saved to.
     *                  pFilePath != null && !pFilePath.equals("").
     * @throws IOException If there ae exceptions saving the factory.
     */
    public void save(String pFilePath) throws IOException {
        filePath = pFilePath;
        save();
    }

    /**
     * Verifies the invariants of the class. <br>
     * <b>inv: </b><br>
     * parts != null.
     */
    private void verifyInvariants() {
        assert (parts != null) : "Parts list must be initialized.";


    }

    // -----------------------------------------------------------------
    // Extension methods
    // -----------------------------------------------------------------

    /**
     * Method for extension1.
     *
     * @return Response 1.
     */
    public String method1() {
        return "Response 1.";
    }

    /**
     * Method for extension2.
     *
     * @return Response 2.
     */
    public String method2() {
        return "Response 2.";
    }

}