/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ElementoExisteException.java,v 1.2 2006/08/10 20:04:47 da-romer Exp $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_discotienda 
 * Autor: Mario S�nchez - 26/01/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.discotienda.mundo;

/**
 * Esta excepci�n se lanza cuando se intenta agregar a la discotienda un elemento repetido. <br>
 * El mensaje asociado con la excepci�n debe indicar el disco o la canci�n que caus� el error.
 */
public class ElementoExisteException extends Exception
{
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepci�n indicando el disco o la canci�n que caus� el error
     * @param nomElem nombre del elemento repetido
     */
    public ElementoExisteException( String nomElem )
    {
        super( nomElem );
    }
}
