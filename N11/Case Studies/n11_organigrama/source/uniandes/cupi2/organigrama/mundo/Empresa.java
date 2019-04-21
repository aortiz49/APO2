/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Empresa.java,v 1.15 2007/04/13 04:51:26 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_organigrama
 * Autor: Jorge Villalobos - 20-oct-2006
 * Autor: Mario S�nchez - 21-nov-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.organigrama.mundo;

import java.io.*;
import java.util.*;

/**
 * Esta clase representa una empresa con su organigrama organizacional. <br>
 * <b>inv:</b> <br>
 * los nombres de los cargos son �nicos <br>
 * los c�digos de identidad de los empleados son �nicos
 */
public class Empresa {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ra�z del �rbol de cargos del organigrama
     */
    private Cargo organigrama;

    private String archivoEmpresa;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una empresa sin cargos
     *
     * @param archivo El archivo en el que se va a guardar el organigrama de la empresa
     * @throws OrganigramaException Si hay problemas al recuperar el organigrama de la empresa
     *                              del archivo
     */
    public Empresa(String archivo) throws OrganigramaException {
        archivoEmpresa = archivo;

        File archi = new File(archivoEmpresa);

        // Si El archivo existe: se debe recuperar de all� el estado del modelo del mundo
        if (archi.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoEmpresa));
                organigrama = (Cargo) ois.readObject();
                ois.close();
                verificarInvariante();
            } catch (Exception e) {
                throw new OrganigramaException(
                        "Error fatal: imposible restaurar el estado del programa (" + e.getMessage()
                                + ")");
            }
        }
        else {
            organigrama = null;
            verificarInvariante();
        }

    }

    public void unpack() throws IOException, ClassNotFoundException {

        Empresa company = this;
        // Reading the object from a file
        FileInputStream file = new FileInputStream("./data/empresa.dat");
        ObjectInputStream in = new ObjectInputStream(file);

        Object t = in.readObject();
        System.out.println(t);
        Cargo x = (Cargo) t;
        x.darEmpleado();
        in.close();
        file.close();
        System.out.println("Object has been deserialized\n" + "Data after Deserialization.");

        System.out.println(company.toString());
    }


    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo cargo al organigrama de la empresa. Si el organigrama est� vac�o, <br>
     * se ignora el valor del par�metro nCargoJefe.
     *
     * @param nCargo     El nombre del nuevo cargo - nCargo!=null && nCargo!=""
     * @param pago       El salario asociado con el nuevo cargo - pago>0
     * @param nCargoJefe El nombre del cargo del que depende el nuevo cargo
     * @throws OrganigramaException Si el cargo del que se depende no existe
     * @throws OrganigramaException Si el nombre del nuevo cargo ya se encuentra asociado a otro
     *                              cargo existente
     */
    public void crearCargo(String nCargo, int pago, String nCargoJefe) throws OrganigramaException {
        if (organigrama == null) {
            organigrama = new Cargo(nCargo, pago, null);
        }
        else {
            Cargo padre = buscarCargo(nCargoJefe);
            if (padre == null)
                throw new OrganigramaException("Cargo inv�lido");

            Cargo nodo = buscarCargo(nCargo);
            if (nodo != null)
                throw new OrganigramaException("Cargo repetido");

            padre.agregarCargo(nCargo, pago);
        }
        verificarInvariante();
    }

    /**
     * Elimina el cargo con el nombre dado
     *
     * @param nCargo El nombre del cargo a ser eliminado
     * @throws OrganigramaException Si no hay cargos en la empresa
     * @throws OrganigramaException Si el cargo a ser eliminado no existe
     * @throws OrganigramaException Si el cargo a ser eliminado no se encuentra vacante
     */
    public void eliminarCargo(String nCargo) throws OrganigramaException {
        if (organigrama == null)
            throw new OrganigramaException("Cargo inv�lido");

        if (organigrama.darNombreCargo().equalsIgnoreCase(nCargo)) {
            // Se quiere eliminar el cargo de la ra�z
            if (organigrama.esHoja() && organigrama.estaVacante())
                organigrama = null;
            else
                throw new OrganigramaException("Imposible eliminar");
        }
        else {
            Cargo padre = organigrama.buscarJefe(nCargo);
            if (padre == null)
                throw new OrganigramaException("Cargo inv�lido");

            padre.eliminarCargo(nCargo);
        }
    }

    /**
     * Contrata a la persona con la informaci�n especificada
     *
     * @param idPersona El c�digo de la persona - idPersona!=null
     * @param nombre    El nombre de la persona - nombre!=null && nombre!=""
     * @param ingreso   La fecha de ingreso de la persona - ingreso!=null
     * @param nCargo    El nombre del cargo que va a ocupar la persona - nCargo!=null
     * @throws OrganigramaException Si el cargo que va a ocupar la persona no existe
     * @throws OrganigramaException Si el c�digo ya se encuentra asociado con otra persona
     */
    public void contratarPersona(String idPersona, String nombre, Date ingreso, String nCargo)
            throws OrganigramaException {
        if (organigrama == null)
            throw new OrganigramaException("Cargo inexistente...");

        Cargo cargo = buscarCargo(nCargo);
        if (cargo == null)
            throw new OrganigramaException("Cargo inexistente...");

        Empleado temp = buscarEmpleado(idPersona);
        if (temp != null)
            throw new OrganigramaException("Identidad repetida...");

        cargo.contratar(idPersona, nombre, ingreso);
    }

    /**
     * Despide el empleado con el c�digo dado
     *
     * @param idPersona El c�digo de la persona a ser despedida
     * @throws OrganigramaException Si empleado no existe
     */
    public void despedirEmpleado(String idPersona) throws OrganigramaException {
        if (organigrama == null)
            throw new OrganigramaException("Empleado inexistente...");

        Cargo cargo = buscarCargoEmpleado(idPersona);
        if (cargo == null)
            throw new OrganigramaException("Empleado no encontrado...");

        cargo.despedir();
    }

    /**
     * Busca el cargo del empleado con el c�digo dado
     *
     * @param idPersona El c�digo del empleado del que se desea el cargo - idPersona!=null
     * @return El cargo de la persona con el c�digo dado. Si el empleado no existe se retorna null
     */
    public Cargo buscarCargoEmpleado(String idPersona) {
        return organigrama == null ? null : organigrama.buscarCargoEmpleado(idPersona);
    }

    /**
     * Busca y retorna el empleado con el c�digo dado
     *
     * @param idPersona El c�digo del empleado que se busca - idPersona != null
     * @return el empleado buscado o null si no se encuentra
     */
    public Empleado buscarEmpleado(String idPersona) {
        return organigrama == null ? null : organigrama.buscarEmpleado(idPersona);
    }

    /**
     * Retorna el n�mero de integrantes del organigrama
     *
     * @return El n�mero de cargos de la empresa
     */
    public int contarCargos() {
        return organigrama == null ? 0 : organigrama.darPeso();
    }

    /**
     * Busca el cargo con el nombre dado
     *
     * @param nCargo Nombre del Cargo
     * @return El cargo con el nombre dado. Si el cargo no se encontr� se retorn� null
     */
    public Cargo buscarCargo(String nCargo) {
        return organigrama == null ? null : organigrama.buscarCargo(nCargo);
    }


    public void aumentarSalario2(int pNivel) {
        organigrama.aumentarSalario2(1);
    }

    /**
     * Retorna la cabeza de la empresa
     *
     * @return la cabeza de la empresa
     */
    public Cargo darCabeza() {
        return organigrama;
    }

    /**
     * Retorna una lista con los nombres de todos los cargos de la empresa
     *
     * @return Lista con todos los cargos de la empresa
     */
    public Collection darListaCargos() {
        Collection lista = new ArrayList();
        if (organigrama != null) {
            organigrama.darListaCargos(lista);
        }
        return lista;
    }

    /**
     * Retorna una lista con los nombres de todos los cargos que se encuentran <br>
     * disponibles en la empresa
     *
     * @return Lista con todos los cargos de la empresa que se encuentran vacantes
     */
    public Collection darListaCargosDisponibles() {
        Collection lista = new ArrayList();
        if (organigrama != null) {
            organigrama.darListaCargosDisponibles(lista);
        }
        return lista;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * los nombres de los cargos son �nicos <br>
     * los c�digos de identidad de los empleados son �nicos
     */
    private void verificarInvariante() {
        Collection cargos = darListaCargos();
        Iterator it = cargos.iterator();

        while (it.hasNext()) {
            String nomCargo = (String) it.next();
            assert (contarOcurrenciasCargo(nomCargo) == 1) :
                    "El nombre del cargo deber�a ser �nico";

            Cargo c = buscarCargo(nomCargo);
            Empleado e = c.darEmpleado();

            if (e != null) {
                assert (contarOcurrenciasCodigoEmpleado(e.darCodigo()) == 1) :
                        "El c�digo del empleado deber�a ser �nico";
            }
        }
    }

    /**
     * Cuenta las ocurrencias del cargo con el nombre dado en el organigrama
     *
     * @param nomCargo El nombre del cargo del que se van a contar las ocurrencias
     * @return El n�mero de ocurrencias del cargo
     */
    private int contarOcurrenciasCargo(String nomCargo) {
        int ocurrencias = 0;

        Collection cargos = darListaCargos();
        Iterator it = cargos.iterator();

        while (it.hasNext()) {
            String nCargo = (String) it.next();

            if (nCargo.equalsIgnoreCase(nomCargo)) {
                ocurrencias++;
            }
        }

        return ocurrencias;
    }

    /**
     * Cuenta las ocurrencias del c�digo dado en el organigrama
     *
     * @param codigo El c�digo del que se van a contar las ocurrencias
     * @return El n�mero de ocurrencias del c�digo
     */
    private int contarOcurrenciasCodigoEmpleado(String codigo) {
        int ocurrencias = 0;

        Collection cargos = darListaCargos();
        Iterator it = cargos.iterator();

        while (it.hasNext()) {
            String nCargo = (String) it.next();
            Cargo c = buscarCargo(nCargo);
            Empleado e = c.darEmpleado();

            if (e != null && e.darCodigo().equalsIgnoreCase(codigo)) {
                ocurrencias++;
            }
        }

        return ocurrencias;
    }

    // -----------------------------------------------------------------
    // Persistencia
    // -----------------------------------------------------------------

    /**
     * Guarda el estado actual en el que se encuentra la empresa
     *
     * @throws OrganigramaException Si hay problemas al tratar de guardar los cargos de la empresa
     */
    public void guardar() throws OrganigramaException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoEmpresa));
            oos.writeObject(organigrama);
            oos.close();
        } catch (IOException e) {
            throw new OrganigramaException("Problemas con la persistencia");
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     *
     * @return respuesta1
     */
    public String metodo1() {
        return "respuesta1";
    }

    /**
     * M�todo para la extensi�n2
     *
     * @return respuesta2
     */
    public String metodo2() {
        return "respuesta2";
    }

    /**
     * M�todo para la extensi�n2
     *
     * @return respuesta3
     */
    public String metodo3() {
        return "respuesta3";
    }

    /**
     * M�todo para la extensi�n2
     *
     * @return respuesta4
     */
    public String metodo4() {
        return "respuesta4";
    }

    /**
     * M�todo para la extensi�n2
     *
     * @return respuesta5
     */
    public String metodo5() {
        return "respuesta5";
    }

    /**
     * M�todo para la extensi�n2
     *
     * @return respuesta6
     */
    public String metodo6() {
        return "respuesta6";
    }

    /**
     * M�todo para la extensi�n7
     *
     * @return respuesta7
     */
    public String metodo7() {
        return "respuesta7";
    }

    /**
     * M�todo para la extensi�n8
     *
     * @return respuesta8
     */
    public String metodo8() {
        return "respuesta8";
    }
}