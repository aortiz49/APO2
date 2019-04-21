/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Empleado.java,v 1.8 2006/10/30 15:49:34 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_organigrama
 * Autor: Jorge Villalobos - 20-oct-2006
 * Autor: Mario Sánchez - 21-nov-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.organigrama.mundo;

import java.io.Serializable;
import java.util.Date;

/**
 * Esta clase representa un empleado de la empresa <br>
 * <b>inv:</b> <br>
 * codigo != null && codigo != "" <br>
 * nombre != null && nombre != "" <br>
 * fechaIngreso != null
 */
public class Empleado implements Serializable {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versión para la serialización
     */
    private static final long serialVersionUID = 200L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * El código que identifica el empleado
     */
    private String codigo;

    /**
     * El nombre del empleado
     */
    private String nombre;

    /**
     * La fecha de ingreso del empleado a la empresa
     */
    private Date fechaIngreso;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea un empleado
     *
     * @param id      El código del empleado
     * @param nombreP el nombre del empleado. nombreElem != null
     * @param fechaP  La fecha de ingreso del empleado a la empresa
     */
    public Empleado(String id, String nombreP, Date fechaP) {
        codigo = id;
        nombre = nombreP;
        fechaIngreso = fechaP;
        verificarInvariante();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el código del empleado
     *
     * @return El código del empleado
     */
    public String darCodigo() {
        return codigo;
    }

    /**
     * Retorna el nombre del empleado
     *
     * @return El nombre del empleado
     */
    public String darNombre() {
        return nombre;
    }

    /**
     * Retorna la fecha de ingreso del empleado
     *
     * @return la fecha de ingreso del empleado a la empresa
     */
    public Date darFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Retorna una cadena que identifica el empleado
     *
     * @return La cadena que identifica el empleado
     */
    public String toString() {
        return codigo;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv:</b> <br>
     * codigo != null && codigo != "" <br>
     * nombre != null && nombre != "" <br>
     * fechaIngreso != null
     */
    private void verificarInvariante() {
        assert (codigo != null) : "El código del empleado no puede ser null";
        assert (!codigo.equals("")) : "El código del empleado no puede ser vacío";
        assert (nombre != null) : "El nombre del empleado no puede ser null";
        assert (!nombre.equals("")) : "El nombre del empleado no puede ser vacío";
        assert (fechaIngreso != null) : "La fecha de ingreso del empleado no puede ser null";
    }
}