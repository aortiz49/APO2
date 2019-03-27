package uniandes.cupi2.carFactory.world;

import java.awt.*;
import java.io.BufferedReader;

public abstract class Wheel extends Part {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant representing the diamater of the wheel.
     */
    public final static int DIAMETER = 85;

    /**
     * @param pX
     * @param pY
     * @param pCarColor
     */
    public Wheel(int pX, int pY, Color pCarColor) {
        super(pX, pY, DIAMETER, DIAMETER, pCarColor);
    }

    /**
     * @param pBr
     * @throws Exception
     */
    public Wheel(BufferedReader pBr) throws Exception {
        super(pBr, DIAMETER, DIAMETER);
    }

    @Override
    public void paint(Graphics2D pG) {
        pG.setColor(new Color(14, 14, 14));
        pG.fillOval(x, y, DIAMETER, DIAMETER);

        pG.setColor(Color.DARK_GRAY);
        pG.fillOval(x + DIAMETER / 5, y + DIAMETER / 5, 3 * DIAMETER / 5, 3 * DIAMETER / 5);


        pintarRines(pG);
    }

    public abstract void pintarRines(Graphics2D pG);


}
