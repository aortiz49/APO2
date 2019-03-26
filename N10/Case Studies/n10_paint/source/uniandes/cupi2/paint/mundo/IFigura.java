/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IFigura.java,v 1.8 2007/04/13 04:29:55 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_paint
 * Autor: Jorge Villalobos - 26-sep-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.paint.mundo;

import java.awt.*;
import java.io.*;

/**
 * Interface que indica los servicios básicos que debe ofrecer toda figura
 */
public interface IFigura {
    /**
     * Dibuja la figura sobre la superficie que se recibe como parámetro
     *
     * @param g            La superficie donde se debe pintar
     * @param seleccionada Indica si la figura que se va a dibujar es la seleccionada
     */
    public void dibujar(Graphics2D g, boolean seleccionada);

    /**
     * Sirve para saber si un punto está dentro de una figura o no.
     *
     * @param x Es la coordenada x del punto
     * @param y Es la coordenada y del punto
     * @return Retorna true si el punto está dentro de la figura. Retorna false en caso contrario.
     */
    public boolean estaDentro(int x, int y);

    /**
     * Retorna el texto asociado con la figura
     *
     * @return texto asociado con la figura
     */
    public String darTexto();

    /**
     * Cambia el texto asociado con la figura
     *
     * @param txt El nuevo texto asociado con la figura
     */
    public void cambiarTexto(String txt);

    /**
     * Retorna el tipo de letra actual del texto
     *
     * @return fuente actual del texto
     */
    public Font darTipoLetra();

    /**
     * Cambia la fuente actual del texto
     *
     * @param fuenteTexto La nueva fuente del texto
     */
    public void cambiarTipoLetra(Font fuenteTexto);

    /**
     * Retorna la cadena de caracteres que va a identificar este tipo de figuras en la persistencia
     *
     * @return Retorna la cadena con el tipo de la figura
     */
    public String darTipoFigura();

    /**
     * Guarda la figura en un archivo que recibe como parámetro
     *
     * @param out Es el flujo donde se va a guardar la figura
     */
    public void guardar(PrintWriter out);
}
