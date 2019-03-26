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
 * Esta excepci�n se lanza cuando se presenta un error al leer o escribir el archivo con la informaci�n del estado del world. <br>
 * El mensaje asociado con la excepci�n debe describir el problema que se present�.
 */
public class PersistenceException extends Exception
{
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepci�n con el mensaje que describe el problema.
     * @param pMensaje Mensaje que describe la causa de la excepci�n. pMensaje != null.
     */
    public PersistenceException( String pMensaje )
    {
        super( pMensaje );
    }
}
