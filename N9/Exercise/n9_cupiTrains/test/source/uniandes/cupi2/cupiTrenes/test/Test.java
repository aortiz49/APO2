package source.uniandes.cupi2.cupiTrenes.test;

import uniandes.cupi2.cupiTrains.world.CupiTrains;
import uniandes.cupi2.cupiTrains.world.PersistenceException;

public class Test {

    public static void main(String[] args) {
        try {
            CupiTrains test = new CupiTrains("");

            test.findTrainByTrainStops("Miami","Orlando");
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}
