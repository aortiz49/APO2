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
import java.io.PrintWriter;

/**
 * Class that contains the method signatures of the Part class.
 */
public interface IPart {

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Returns the height of the part.
     *
     * @return Height of the part.
     */
    public int getHeight();

    /**
     * Returns the width of the part.
     *
     * @return Width of the part.
     */
    public int getWidth();

    /**
     * Returns the color of the part.
     *
     * @return Color of the part.
     */
    public Color getColor();

    /**
     * Returns the type of part.
     *
     * @return Type of part.
     */
    public String getType();

    /**
     * Returns the x coordinate of the part.
     *
     * @return X coordinate of the part.
     */
    public int getX();

    /**
     * Returns the y coordinate of the part.
     *
     * @returnY y coordinate of the part.
     */
    public int getY();

    /**
     * Changes the x coordinate of the part.
     *
     * @param pX x coordinate of the part.
     *           pX >= 0
     */
    public void changeX(int pX);

    /**
     * Changes the y coordinate of the part.
     *
     * @param pY y coordinate of the part.
     *           pY >= 0
     */
    public void changeY(int pY);

    /**
     * Changes the width of the part.
     *
     * @param pWidth New width of the part.
     *               pWidth > 0.
     */
    public void changeWidth(int pWidth);

    /**
     * Changes the height of the part.
     *
     * @param pHeight New height of the part.
     *                pHeight > 0.
     */
    public abstract void changeHeight(int pHeight);

    /**
     * Changes the color of the part.
     *
     * @param pColor New figure color which is represented by an RGB code.
     *               pColor >= 0.
     */
    public void changeColor(int pColor);

    /**
     * Indicates if a point is inside the figure or not.
     *
     * @param pX X coordinate of the point. pX >= 0.
     * @param pY Y coordinate of the point. pY >= 0.
     * @return Returns true if the point is in the figure, false if otherwise.
     */
    public boolean pointIsInside(int pX, int pY);

    /**
     * Saves the figure information in a file. <br>
     * <b>post: </b> The figure information was saved in the file.
     *
     * @param pOut Stream where the the figure will be saved to.
     *             pOut!=null.
     */
    public void save(PrintWriter pOut);

    /**
     * Draws the figure <br>
     * <b>post: </b> The figure was painted on the canvas.
     *
     * @param pG The surface on which the figure will be painted.
     *           pG!=null.
     */
    public void paint(Graphics2D pG);

    /**
     * Paints the figure as a selection. <br>
     * <b>post: </b> The outline of the selected figure was drawn on the canvas.
     *
     * @param pG Surface on which the rectangle will be painted.
     */
    public void paintAsSelected(Graphics2D pG);

    /**
     * Paints the shadowed figure. <br>
     * <b>post: </b> The shadowed figure was painted on the canvas.
     *
     * @param pG The surface on which the figure will be painted.
     *           pG!=null.
     */
    public void paintShadowed(Graphics2D pG);
}