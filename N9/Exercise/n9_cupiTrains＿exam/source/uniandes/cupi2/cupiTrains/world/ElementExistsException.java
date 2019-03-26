/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupiTrenes
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiTrains.world;

/**
 * Excepci�n que se lanza cuando se intenta agregar un elemento existente. <br>
 */
public class ElementExistsException extends Exception
{
    /**
     * Construye la excepci�n con el mensaje que describe el problema.
     * @param pMensaje Mensaje que describe la causa de la excepci�n. pMensaje != null.
     */
    public ElementExistsException( String pMensaje )
    {
        super( pMensaje );
    }
}
