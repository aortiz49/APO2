/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: carFactory
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.carFactory.world;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase abstracta que representa una parte del carro.
 */
public abstract class Part implements IPart {

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Ancho de la parte.
     */
    protected int ancho;

    /**
     * Alto de la parte.
     */
    protected int alto;

    /**
     * Coordinate x de la parte.
     */
    protected int x;

    /**
     * Coordinate y de la parte.
     */
    protected int y;

    /**
     * Color de la parte.
     */
    protected Color color;

    /**
     * Tipo de la parte.
     */
    protected String tipo;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Crea la parte con los valores especificados.<br>
     * El atributo tipo debe ser inicializado en el constructor de cada clase heredada.<br>
     * <b> post: </b> Se inicializ� la parte con las coordenadas 'x' y 'y', el ancho, el alto y
     * el color dados por par�metro.
     *
     * @param pX          Coordinate x de la figura. pX >= 0.
     * @param pY          Coordinate y de la figura. pY >= 0.
     * @param pAlto       Alto de la figura. pAlto >= 0.
     * @param pAncho      Ancho de la figura. pAncho >= 0.
     * @param pCarColor Color del carro. pCarColor != null.
     */
    public Part(int pX, int pY, int pAlto, int pAncho, Color pCarColor) {
        x = pX;
        y = pY;
        alto = pAlto;
        ancho = pAncho;
        color = pCarColor;
        verifyInvariants();
    }

    /**
     * Crea la parte a partir de los datos contenidos en el archivo.
     *
     * @param pBr    Stream que sirve para leer el archivo. pBr!=null.
     * @param pAlto  Altura de la figura. pAlto >= 0.
     * @param pAncho Ancho de la figura. pAncho >= 0.
     * @throws Exception Si hay problemas leyendo el archivo.
     * @throws Exception Si el formato del archivo no es el esperado.
     */
    public Part(BufferedReader pBr, int pAlto, int pAncho) throws Exception {
        String lineaPuntos = pBr.readLine();
        String strColor;
        try {
            inicializarPuntos(lineaPuntos);
            strColor = pBr.readLine();
        } catch (IOException fie) {
            throw new Exception("Error al leer el archivo.");
        }
        alto = pAlto;
        ancho = pAncho;
        color = new Color(Integer.parseInt(strColor));
        verifyInvariants();
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Retorna el alto de la parte.
     *
     * @return Alto de la parte.
     */
    public int getHeight() {
        return alto;
    }

    /**
     * Retorna el ancho de la parte.
     *
     * @return Ancho de la parte.
     */
    public int getWidth() {
        return ancho;
    }

    /**
     * Retorna el color de la parte.
     *
     * @return Color de la parte.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retorna el tipo de la parte.
     *
     * @return Tipo de la parte.
     */
    public String getType() {
        return tipo;
    }

    /**
     * Retorna la coordenada x de la parte.
     *
     * @return Coordinate x de la parte.
     */
    public int getX() {
        return x;
    }

    /**
     * Retorna la coordenada y de la parte.
     *
     * @return Coordinate y de la parte.
     */
    public int getY() {
        return y;
    }

    /**
     * Cambia el ancho de la parte.
     *
     * @param pAncho Nuevo ancho de la parte. pAncho >=0.
     */
    public void changeWidth(int pAncho) {
        ancho = pAncho;
    }

    /**
     * Cambia el alto de la parte.
     *
     * @param pAlto Nuevo alto de la parte. pAlto >= 0.
     */
    public void changeHeight(int pAlto) {
        alto = pAlto;
    }

    /**
     * Cambia el color de la parte..
     *
     * @param pColor Nuevo color de la figura representado en un n�mero de c�digo RGB. pColor >= 0.
     */
    public void changeColor(int pColor) {

        color = new Color(pColor);

    }

    /**
     * Cambia la coordenada x de la figura.
     *
     * @param pX Nueva coordenada x de la figura. pX >= 0.
     */
    public void changeX(int pX) {
        x = pX;
    }

    /**
     * Cambia la coordenada y de la parte.
     *
     * @param pY Nueva coordenada y de la parte. pY >= 0.
     */
    public void changeY(int pY) {
        y = pY;
    }

    /**
     * Indica si un punto est� dentro de una figura o no.
     *
     * @param pX Coordinate x del punto. pX >= 0.
     * @param pY Coordinate y del punto. pY >= 0.
     * @return Retorna true si el punto est� dentro de la parte, false en caso contrario.
     */
    public boolean pointIsInside(int pX, int pY) {
        Rectangle2D rectangulo = new Rectangle2D.Double(x, y, ancho, alto);
        return rectangulo.contains(pX, pY);
    }

    /**
     * Este m�todo sirve para save una parte en un archivo.
     *
     * @param pOut Stream donde se va a save la parte. pOut!=null.
     */
    public void save(PrintWriter pOut) {
        pOut.println(getType());
        pOut.println("" + x + ";" + y);
        pOut.println(color.getRGB());
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Inicializa los puntos de la parte a partir de la l�nea que para por par�metro. <br>
     * <b>post: </b> x, y tienen nuevos valores.
     *
     * @param pLineaPuntos L�nea del archivo donde se encuentran los puntos. pLineaPuntos != null
     *                     y !pLineaPuntos.equals("").
     * @throws Exception Si hay errores de formato.
     * @throws Exception Si hay error al leer la l�nea.
     */
    private void inicializarPuntos(String pLineaPuntos) throws Exception {
        String[] strValores = pLineaPuntos.split(";");
        if (strValores.length != 2) {
            throw new Exception("Error al leer la l�nea " + pLineaPuntos + ".");
        }
        try {
            x = Integer.parseInt(strValores[0]);
            y = Integer.parseInt(strValores[1]);
        } catch (NumberFormatException nfe) {
            throw new Exception("Error al leer la l�nea " + pLineaPuntos + ".");
        }
    }

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Paints the part. <br>
     * <b>post:</b> The part was painted on the canvas.
     *
     * @param pG The surface on which the figure will be painted.
     */
    public abstract void paint(Graphics2D pG);


    /**
     * Pinta la parte como seleccionada. <br>
     * <b>post: </b> Se dibuj� el contorno de la parte seleccionada en el lienzo.
     *
     * @param pG Superficie donde se pinta el rect�ngulo. pG!=null.
     */
    public void paintAsSelected(Graphics2D pG) {
        pG.setColor(Color.black);
        float[] dash = {30, 10, 10, 10};
        BasicStroke stroke =
                new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dash, 0);
        pG.setStroke(stroke);
        pG.drawRect(x, y, ancho, alto);
        pG.setStroke(new BasicStroke(1));
        pG.setColor(color);


    }

    /**
     * Pinta la parte como seleccionada. <br>
     * <b>post: </b> Se dibuj� el contorno de la parte seleccionada en el lienzo.
     *
     * @param pG Superficie donde se pinta el rect�ngulo. pG!=null.
     */
    public void paintShadowed(Graphics2D pG) {
        paint(pG);
    }

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv: </b><br>
     * x>=0<br>
     * y>=0 <br>
     * ancho>=0 <br>
     * alto >=0.<br>
     */
    public void verifyInvariants() {
        assert (x >= 0) : "La coordenada x es inv�lida";
        assert (y >= 0) : "La coordenada y es inv�lida";
        assert (ancho >= 0) : "El ancho es inv�lido";
        assert (alto >= 0) : "El alto es inv�lido";
    }

}