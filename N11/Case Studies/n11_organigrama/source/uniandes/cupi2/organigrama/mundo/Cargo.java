/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Cargo.java,v 1.12 2006/10/30 15:49:34 da-romer Exp $
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Esta clase representa un cargo en la empesa <b>inv:</b> <br>
 * nombreCargo != null && nombreCargo != "" <br>
 * salario > 0 <br>
 * subalternos != null <br>
 */
public class Cargo implements Serializable {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Indicador de versión para la serialización
     */
    private static final long serialVersionUID = 100L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del cargo
     */
    private String nombreCargo;

    /**
     * El salario del cargo
     */
    private int salario;

    /**
     * El cargo del que depende el cargo actual
     */
    private Cargo jefe;

    /**
     * El empleado que ocupa el cargo
     */
    private Empleado asignado;

    /**
     * Lista de los subalternos de este empleado
     */
    private ArrayList subalternos;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un cargo con la información especificada
     *
     * @param nCargo   El nombre del cargo
     * @param pago     El pago que recibe el empleado que ocupa el cargo
     * @param superior El cargo del que depende el cargo a ser creado
     */
    public Cargo(String nCargo, int pago, Cargo superior) {
        nombreCargo = nCargo;
        salario = pago;
        jefe = superior;
        asignado = null;
        subalternos = new ArrayList();
        verificarInvariante();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del cargo
     *
     * @return El nombre del cargo
     */
    public String darNombreCargo() {
        return nombreCargo;
    }

    /**
     * Retorna el salario del cargo
     *
     * @return El salario del cargo
     */
    public int darSalario() {
        return salario;
    }

    /**
     * Retorna el empleado que ocupa el cargo
     *
     * @return El empleado que ocupa el cargo
     */
    public Empleado darEmpleado() {
        return asignado;
    }

    /**
     * Indica si el cargo se encuentra vacante
     *
     * @return true si el cargo está vacante o false en caso contrario
     */
    public boolean estaVacante() {
        return asignado == null;
    }

    /**
     * Retorna los subalternos del empleado.
     *
     * @return La lista de subalternos
     */
    public ArrayList darSubAlternos() {
        return subalternos;
    }

    /**
     * Cambia el salario del cargo
     *
     * @param nuevo El nuevo salario del empleado
     */
    public void asignarSalario(int nuevo) {
        salario = nuevo;
        verificarInvariante();
    }

    /**
     * Agrega un cargo a la lista de subalternos del cargo actual
     *
     * @param nCargo El nuevo del nuevo cargo
     * @param pago   El pago del nuevo cargo
     */
    public void agregarCargo(String nCargo, int pago) {
        Cargo subalterno = new Cargo(nCargo, pago, this);
        subalternos.add(subalterno);
    }

    /**
     * Elimina el cargo con el nombre dado
     *
     * @param nCargo El nombre del cargo a eliminar
     * @throws OrganigramaException Si el cargo no es una hoja o no está vacante
     */
    public void eliminarCargo(String nCargo) throws OrganigramaException {
        for (int i = 0; i < subalternos.size(); i++) {
            Cargo hijo = (Cargo) subalternos.get(i);
            if (hijo.darNombreCargo().equalsIgnoreCase(nCargo)) {
                if (hijo.esHoja() && hijo.estaVacante()) {
                    subalternos.remove(i);
                    return;
                }
                else
                    throw new OrganigramaException("No eliminable");
            }
        }
    }

    /**
     * Asigna la persona con los datos especificados al cargo actual
     *
     * @param idPersona El código de la persona
     * @param nombre    El nombre de la persona
     * @param ingreso   La fecha de ingreso
     * @throws OrganigramaException Si el cargo ya se encuentra ocupado
     */
    public void contratar(String idPersona, String nombre, Date ingreso)
            throws OrganigramaException {
        if (asignado != null)
            throw new OrganigramaException("Cargo no vacante...");

        else
            asignado = new Empleado(idPersona, nombre, ingreso);
    }

    /**
     * Elimina la referencia al empleado que ocupaba el cargo
     */
    public void despedir() {
        asignado = null;
    }

    /**
     * Indica si el elemento es una hoja o no
     *
     * @return true si el elemento es una hoja, false en caso contrario
     */
    public boolean esHoja() {
        return subalternos.size() == 0;
    }

    /**
     * Cuenta el número de hojas que hay en el organigrama que comienza con este elemento
     *
     * @return El número de hojas
     */
    public int contarHojas() {
        if (esHoja())
            return 1;
        else {
            int numHojasAcum = 0;
            for (int i = 0; i < subalternos.size(); i++) {
                Cargo hijo = (Cargo) subalternos.get(i);
                numHojasAcum += hijo.contarHojas();
            }
            return numHojasAcum;
        }
    }

    public Cargo buscarSalarioMin() {
        Cargo peor = this;
        int minSalario = salario;

        for (int i = 0; i < subalternos.size(); i++) {
            Cargo hijo = (Cargo) subalternos.get(i);
            Cargo peorHijo = hijo.buscarSalarioMin();
            int temp = peorHijo.darSalario();

            if (temp < minSalario) {
                minSalario = temp;
                peor = peorHijo;
            }
        }
        return peor;
    }

    //assume both exist
    public boolean dependeDe(Cargo pCargo1, Cargo pCargo2) {
        Cargo c2 = buscarCargo(pCargo2.nombreCargo);

        Cargo c1 = c2.buscarCargo(pCargo1.darNombreCargo());

        if (c1 != null) {
            return true;
        }

        else {
            return false;
        }
    }


    public void aumentarSalario1() {
        int salarioBase = salario;
        int numSub = subalternos.size();
        int salarioNuevo = salarioBase * (1 + numSub / 100);

        asignarSalario(salarioNuevo);

        for (int i = 0; i < subalternos.size(); i++) {

            Cargo hijo = (Cargo) subalternos.get(i);
            aumentarSalario1();
        }
    }

    public void aumentarSalario2(int pNivel) {
        int nuevoSalario = salario * (1 + pNivel / 100);
        asignarSalario(nuevoSalario);

        pNivel++;

        for (int i = 0; i < subalternos.size(); i++) {
            Cargo hijo = (Cargo) subalternos.get(i);
            hijo.aumentarSalario2(pNivel);
        }

    }


    /**
     * Calcula el peso del organigrama del que este elemento es raíz
     *
     * @return peso o número de nodos
     */
    public int darPeso() {
        if (esHoja())
            return 1;
        else {
            int pesoAcum = 1;
            for (int i = 0; i < subalternos.size(); i++) {
                Cargo hijo = (Cargo) subalternos.get(i);
                pesoAcum += hijo.darPeso();
            }
            return pesoAcum;
        }
    }

    /**
     * Calcula la altura del organigrama del que este elemento es raíz
     *
     * @return altura del árbol. altura >= 1
     */
    public int darAltura() {
        if (esHoja())
            return 1;
        else {
            int max = 0;
            for (int i = 0; i < subalternos.size(); i++) {
                Cargo hijo = (Cargo) subalternos.get(i);
                int temp = hijo.darAltura();
                if (temp > max)
                    max = temp;
            }
            return max + 1;
        }
    }

    /**
     * Agrega a la lista que recibe como parámetro los nombres de los cargos que existen <br>
     * en el subárbol que comienza en este punto del organigrama
     *
     * @param lista La lista en la que se van a adicionar los cargos que existen en el subárbol <br>
     *              que comienza en este punto del organigrama
     */
    public void darListaCargos(Collection lista) {
        lista.add(nombreCargo);
        for (int i = 0; i < subalternos.size(); i++) {
            Cargo hijo = (Cargo) subalternos.get(i);
            hijo.darListaCargos(lista);
        }
    }

    /**
     * Agrega a la lista que recibe como parámetro los nombres de los cargos que se encuentran <br>
     * vacantes en el subárbol que comienza en este punto del organigrama
     *
     * @param lista La lista en la que se van a adicionar los cargos disponibles en el subárbol <br>
     *              que comienza en este punto del organigrama
     */
    public void darListaCargosDisponibles(Collection lista) {
        if (asignado == null)
            lista.add(nombreCargo);
        for (int i = 0; i < subalternos.size(); i++) {
            Cargo hijo = (Cargo) subalternos.get(i);
            hijo.darListaCargosDisponibles(lista);
        }
    }

    /**
     * Busca un cargo en el organigrama que comienza en este elemento.
     *
     * @param nCargo El nombre del cargo que se está buscando
     * @return El cargo con el nombre dado. Si no existe tal cargo en el <br>
     * organigrama que comienza en el elemento se retorna null.
     */
    public Cargo buscarCargo(String nCargo) {
        if (nombreCargo.equalsIgnoreCase(nCargo)) {
            return this;
        }
        else {
            Cargo buscado = null;
            for (int i = 0; i < subalternos.size() & buscado == null; i++) {
                Cargo hijo = (Cargo) subalternos.get(i);
                buscado = hijo.buscarCargo(nCargo);
            }
            return buscado;
        }
    }


    /**
     * Busca el cargo que ocupa un empleado en la empresa, dado el identificador del empleado
     *
     * @param idEmpleado El código del empleado del que se desea el cargo
     * @return El cargo del empleado con el código dado. Si no se encuentra el cargo del empleado
     * se retorna null
     */
    public Cargo buscarCargoEmpleado(String idEmpleado) {
        if (asignado != null && asignado.darCodigo().equals(idEmpleado)) {
            return this;
        }
        else {
            for (int i = 0; i < subalternos.size(); i++) {
                Cargo hijo = (Cargo) subalternos.get(i);
                Cargo temp = hijo.buscarCargoEmpleado(idEmpleado);
                if (temp != null)
                    return temp;
            }
            return null;
        }
    }

    /**
     * Busca un empleado en el organigrama que comienza en este elemento.
     *
     * @param idEmpleado El código del empleado que se está buscando
     * @return El empleado con el código dado. Si no se encuentra el empleado se retorna null
     */
    public Empleado buscarEmpleado(String idEmpleado) {
        Cargo cargo = buscarCargoEmpleado(idEmpleado);
        return (cargo == null) ? null : cargo.asignado;
    }

    /**
     * Busca el cargo del que depende el cargo con el nombre dado
     *
     * @param nCargo El nombre del cargo del que se desea el cargo jefe
     * @return El cargo del que depende el cargo actual. Si el cargo no es encontrado se retorna
     * null
     */
    public Cargo buscarJefe(String nCargo) {
        Cargo cargo = buscarCargo(nCargo);
        return (cargo == null) ? null : cargo.jefe;
    }

    /**
     * Retorna una cadena que identifica el cargo
     *
     * @return La cadena que identifica el cargo
     */
    public String toString() {
        return nombreCargo;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * nombreCargo != null && nombreCargo != "" <br>
     * salario > 0 <br>
     * subalternos != null <br>
     */
    private void verificarInvariante() {
        assert (nombreCargo != null) : "El nombre del cargo no debería ser null";
        assert (!nombreCargo.equals("")) : "El nombre del cargo no debería ser vacío";
        assert (salario > 0) : "El salario no debería ser negativo";
        assert (subalternos != null) : "La lista de subalternos no debería ser null";
    }
}
