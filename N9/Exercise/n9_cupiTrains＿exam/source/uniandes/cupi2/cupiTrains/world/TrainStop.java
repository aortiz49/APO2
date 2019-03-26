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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that represents a train stop. <br>
 * <b>inv:</b><br>
 * name != null && name != "".<br>
 * arrivalTime != null.<br>
 */
public class TrainStop implements Serializable {

    // -------------------------------------------------------------
    // Constants
    // -------------------------------------------------------------

    /**
     * Serialization constant for the TrainStop class.
     */
    private static final long serialVersionUID = -6927689312444540009L;

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Name of the train stop.
     */
    private String name;

    /**
     * Arrival time of the train at the station.
     */
    private Date arrivalTime;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * TrainStop constructor. <br>
     * <b>post: </b> Constructs a new train stop with the information given by the parameters. <br>
     *
     * @param pName        Name of the train stop.
     *                     pName != null && pName != "".
     * @param pArrivalTime Train's arrival time at the station.
     *                     pArrivalTime != null.
     */
    public TrainStop(String pName, Date pArrivalTime) {
        name = pName;
        arrivalTime = pArrivalTime;
        verifyInvariant();
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Returns el name of the train stop.
     *
     * @return Name of the train stop.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the train's arrival time at the station with the format HH:mm. <br>
     *
     * @return Arrival time of the train to the station.
     */
    public String getSchedule() {
        SimpleDateFormat trainStopFormat = new SimpleDateFormat("HH:mm");
        return trainStopFormat.format(arrivalTime);
    }

    /**
     * Verifies the invariants of the TrainStop class. <br>
     * <b>inv:</b><br>
     * name != null && name != "".<br>
     * arrivalTime != null.<br>
     */
    private void verifyInvariant() {
        assert name != null && !name.equals("") : "The train stop name cannot be null or empty.";
        assert arrivalTime != null : "The arrival time cannot be null.";
    }
}
