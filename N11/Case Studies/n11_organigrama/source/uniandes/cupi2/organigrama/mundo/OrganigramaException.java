/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: OrganigramaException.java,v 1.3 2006/10/24 16:08:31 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_organigrama
 * Autor: Jorge Villalobos - 20-oct-2006
 * Autor: Katalina Marcos - 08/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.organigrama.mundo;

/**
 * Representa una excepci�n ligada con la actualizaci�n del organigrama
 */
public class OrganigramaException extends Exception
{

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepci�n
     * @param mensaje Es el mensaje asociado con la excepci�n
     */
    public OrganigramaException( String mensaje )
    {
        super( mensaje );
    }
}
