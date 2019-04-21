/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiTaxonomia
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiTaxonomia.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa un tax�n del �rbol taxon�mico. <br>
 * <b> inv: </b> <br>
 * nombre != null && nombre != "". <br>
 * tipo pertenece a {LUCA, DOMINIO, REINO, FILO, CLASE, ORDEN, FAMILIA, GENERO, ESPECIE}. <br>
 * La lista de subtaxones est� inicializada. <br>
 * Si el tipo del tax�n no es ESPECIE no puede tener un ser vivo. <br>
 * Si el tipo del tax�n es ESPECIE no puede tener sub-taxones. <br>
 * El tipo de los sub-taxones corresponde al siguiente tipo en la jerarqu�a taxon�mica. <br>
 */
public class Taxon implements Serializable {
    // -------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------

    /**
     * Constante que representa al tipo LUCA.
     */
    public final static int LUCA = 0;

    /**
     * Constante que representa al tipo Dominio.
     */
    public final static int DOMINIO = 1;

    /**
     * Constante que representa al tipo Reino.
     */
    public final static int REINO = 2;

    /**
     * Constante que representa al tipo Filo.
     */
    public final static int FILO = 3;

    /**
     * Constante que representa al tipo Clase.
     */
    public final static int CLASE = 4;

    /**
     * Constante que representa al tipo Orden.
     */
    public final static int ORDEN = 5;

    /**
     * Constante que representa al tipo Familia.
     */
    public final static int FAMILIA = 6;

    /**
     * Constante que representa al tipo G�nero.
     */
    public final static int GENERO = 7;

    /**
     * Constante que representa al tipo Especie.
     */
    public final static int ESPECIE = 8;

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Constante de serializaci�n de la clase.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Nombre del tax�n.
     */
    private String nombre;

    /**
     * Tipo del tax�n.
     */
    private int tipo;

    /**
     * Lista de los sub-taxones del tax�n.
     */
    private ArrayList<Taxon> subTaxones;

    /**
     * Ser vivo del tax�n.
     */
    private SerVivo serVivo;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Construye una tax�n vac�o. <br>
     * <b> post: </b> Se inicializa su tipo en LUCA. <br>
     * Se inicializa su nombre en "�ltimo antepasado com�n universal". <br>
     * La lista de sub-taxones se inicializa vac�a. <br>
     * Se inicializa el ser vivo en null.
     */
    public Taxon() {
        tipo = LUCA;
        nombre = "Last universal common ancestor";

        subTaxones = new ArrayList<Taxon>();
        serVivo = null;

        verificarInvariante();
    }

    /**
     * Inicializa un Tax�n con la informaci�n dada por par�metro. <br>
     * <b> post: </b> El tax�n se inicializ� con los valores de tipo y nombre dados por par�metro
     * . <br>
     * La lista de sub-taxones se inicializa vac�a y el ser vivo en null. <br>
     *
     * @param pTipo   Tipo del tax�n. pTipo pertenece a {LUCA, DOMINIO, REINO, FILO, CLASE, ORDEN,
     *                FAMILIA, GENERO, ESPECIE}.
     * @param pNombre Nombre del tax�n. pNombre != null && pNombre != "".
     */
    public Taxon(int pTipo, String pNombre) {
        tipo = pTipo;
        nombre = pNombre;

        subTaxones = new ArrayList<Taxon>();
        serVivo = null;

        verificarInvariante();
    }

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Retorna el nombre del tax�n.
     *
     * @return Nombre del tax�n.
     */
    public String darNombre() {
        return nombre;
    }

    /**
     * Retorna el tipo del tax�n.
     *
     * @return Tipo del tax�n.
     */
    public int darTipo() {
        return tipo;
    }

    /**
     * Retorna la lista de sub-taxones del tax�n.
     *
     * @return Lista de sub-taxones del tax�n.
     */
    public ArrayList<Taxon> darSubTaxones() {
        return subTaxones;
    }

    /**
     * Retorna el ser vivo del tax�n.
     *
     * @return Ser vivo del tax�n.
     */
    public SerVivo darSerVivo() {
        return serVivo;
    }

    /**
     * Retorna el n�mero de taxones del sub-�rbol que tiene al tax�n actual como ra�z. <br>
     *
     * @return N�mero de taxones del sub-�rbol que tiene al tax�n actual como ra�z.
     */
    public int darNumTaxones() {
        if (subTaxones.size() == 0) {
            return 1;
        }
        else {
            int totalTaxones = 1;
            for (int i = 0; i < subTaxones.size(); i++) {
                Taxon child = subTaxones.get(i);
                totalTaxones += child.darNumTaxones();
            }
            return totalTaxones;
        }
    }

    /**
     * Retorna el n�mero de seres vivos del sub-�rbol que tiene al tax�n actual como ra�z. <br>
     *
     * @return N�mero de seres vivos del sub-�rbol que tiene al tax�n actual como ra�z.
     */
    public int darNumSeresVivos() {
        if (subTaxones.size() == 0 && serVivo != null) {
            return 1;
        }
        else {
            int totalSeres = 0;
            for (int i = 0; i < subTaxones.size(); i++) {
                Taxon child = subTaxones.get(i);
                totalSeres += child.darNumSeresVivos();
            }
            return totalSeres;
        }
    }

    /**
     * Agrega un nuevo tax�n a la lista de sub-taxones del tax�n actual. <br>
     * <b> pre: </b> El tax�n no es de tipo ESPECIE. <br>
     * <b> post: </b> Se ha agregado un nuevo tax�n a la lista de sub-taxones.
     *
     * @param pTipo        Tipo del tax�n. pTipo pertenece a {LUCA, DOMINIO, REINO, FILO, CLASE,
     *                     ORDEN,
     *                     FAMILIA, GENERO, ESPECIE}.
     * @param pNombreTaxon Nombre del tax�n. pNombreTaxon != null && pNombreTaxon != "".
     */
    public void agregarSubTaxon(int pTipo, String pNombreTaxon) {
        Taxon nuevo = new Taxon(pTipo, pNombreTaxon);
        subTaxones.add(nuevo);
    }

    /**
     * Agrega un ser vivo al tax�n actual. <br>
     * <b> pre: </b> El tax�n es de tipo ESPECIE. <br>
     * <b> post: </b> El atributo serVivo ha sido inicializado con los valores dados por par�metro.
     *
     * @param pNombreComun      Nombre com�n del ser vivo. pNombreComun != null && pNombreComun
     *                          != "".
     * @param pNombreCientifico Nombre cient�fico del ser vivo. pNombreCientifico != null &&
     *                          pNombreCientifico != "".
     * @param pCaracteristicas  Caracter�sticas del ser vivo. pCaracteristicas != null &&
     *                          pCaracteristicas != "".
     * @param pImagen           Ruta con la imagen del ser vivo. pImagen != null && pImagen != "".
     * @throws Exception Si el tax�n ya tiene un ser vivo asociado.
     */
    public void agregarSerVivo(String pNombreComun, String pNombreCientifico,
                               String pCaracteristicas, String pImagen) throws Exception {
        if (serVivo == null) {
            serVivo = new SerVivo(pNombreComun, pNombreCientifico, pCaracteristicas, pImagen);
            verificarInvariante();
        }
        else {
            throw new Exception("La especie " + nombre + " ya tiene un ser vivo asignado.");
        }
    }


    /**
     * Busca un tax�n con el nombre dado por par�metro. <br>
     *
     * @param pNombreTaxon Nombre del tax�n a buscar. pNombreTaxon != null && pNombreTaxon != "".
     * @return Tax�n con el nombre dado, null en caso de que no se encuentre.
     */
    public Taxon buscarTaxon(String pNombreTaxon) {
        if (nombre.equalsIgnoreCase(pNombreTaxon)) {
            return this;
        }
        else {
            for (int i = 0; i < subTaxones.size(); i++) {
                Taxon child = subTaxones.get(i);
                Taxon temp = child.buscarTaxon(pNombreTaxon);

                if (temp != null) {
                    return temp;
                }
            }
            return null;
        }
    }

    /**
     * Busca al ser vivo con el nombre cient�fico dado por par�metro. <br>
     *
     * @param pNombreCientifico Nombre cient�fico del ser vivo a buscar. pNombreCientifico !=
     *                          null && pNombreCientifico != "".
     * @return Ser vivo con el nombre cient�fico dado, null en caso de que no se encuentre.
     */
    public SerVivo buscarSerVivo(String pNombreCientifico) {
        if (serVivo != null && serVivo.darNombreCientifico().equalsIgnoreCase(pNombreCientifico)) {
            return this.serVivo;
        }
        else {
            for (int i = 0; i < subTaxones.size(); i++) {
                Taxon child = subTaxones.get(i);
                SerVivo temp = child.buscarSerVivo(pNombreCientifico);

                if (temp != null) {
                    return temp;
                }
            }
            return null;
        }
    }

    /**
     * Agrega los seres vivos del tax�n o de sus sub-taxones a la lista dada por par�metro. <br>
     *
     * @param pListaSeres La lista donde se agregan los seres vivos. pListaSeres != null.
     */
    public void buscarSeresVivos(ArrayList<SerVivo> pListaSeres) {
        if (serVivo != null) {
            pListaSeres.add(serVivo);
        }
        else {
            for (int i = 0; i < subTaxones.size(); i++) {
                Taxon child = subTaxones.get(i);
                child.buscarSeresVivos(pListaSeres);
            }
        }
    }

    /**
     * Retorna una lista con el tax�n actual y sus sub-taxones. <br>
     * Los taxones se agregan en preorden. <br>
     *
     * @param pListaTaxones La lista de taxones donde se acumula la respuesta. pListaTaxones !=
     *                      null.
     */
    public void buscarTaxonesPreorden(ArrayList<Taxon> pListaTaxones) {
        pListaTaxones.add(this);

        for (int i = 0; i < subTaxones.size(); i++) {
            Taxon child = subTaxones.get(i);
            child.buscarTaxonesPreorden(pListaTaxones);
        }
    }

    /**
     * Retorna una lista con el tax�n actual y sus sub-taxones. <br>
     * Los taxones se agregan en postorden. <br>
     *
     * @param pListaTaxones La lista de taxones donde se acumula la respuesta. pListaTaxones !=
     *                      null.
     */
    public void buscarTaxonesPostorden(ArrayList<Taxon> pListaTaxones) {
        if (subTaxones.size() == 0) {
            pListaTaxones.add(this);
        }
        else {
            for (int i = 0; i < subTaxones.size(); i++) {
                Taxon child = subTaxones.get(i);
                child.buscarTaxonesPostorden(pListaTaxones);
            }
            pListaTaxones.add(this);
        }
    }

    /**
     * Retorna una lista con los nombres de los taxones del tipo dado por par�metro. <br>
     *
     * @param pTipo         Tipo del tax�n que se quiere buscar. pTipo pertenece a {LUCA,
     *                      DOMINIO, REINO,
     *                      FILO, CLASE, ORDEN, FAMILIA, GENERO, ESPECIE}.
     * @param pListaTaxones La lista de los nombres donde se acumula la respuesta. pListaTaxones
     *                      != null.
     */
    public void buscarTaxonesNivel(int pTipo, ArrayList<String> pListaTaxones) {
        if (tipo == pTipo) {
            pListaTaxones.add(this.nombre);
        }
        else {
            for (int i = 0; i < subTaxones.size(); i++) {
                Taxon child = subTaxones.get(i);
                child.buscarTaxonesNivel(pTipo, pListaTaxones);
            }
        }
    }

    /**
     * Retorna el tax�n con el nombre dado y tipo dados por par�metro. <br>
     *
     * @param pTipo        Tipo del tax�n que se quiere buscar. pTipo pertenece a {LUCA, DOMINIO,
     *                     REINO,
     *                     FILO, CLASE, ORDEN, FAMILIA, GENERO, ESPECIE}.
     * @param pNombreTaxon Nombre del tax�n buscado. pNombreTaxon != null && pNombreTaxon != "".
     * @return Tax�n con nombre dado y cuyo nivel se encuentra en la distancia dada, null en caso
     * de que no se encuentre.
     */
    public Taxon buscarTaxonNivel(int pTipo, String pNombreTaxon) {
        if (tipo == pTipo && nombre.equalsIgnoreCase(pNombreTaxon)) {
            return this;
        }

        else {
            for (int i = 0; i < subTaxones.size(); i++) {
                Taxon child = subTaxones.get(i);
                Taxon temp = child.buscarTaxonNivel(pTipo, pNombreTaxon);

                if (temp != null) {
                    return temp;
                }
            }
            return null;
        }
    }

    /**
     * Elimina un tax�n del sub-�rbol que tiene como ra�z al tax�n actual. <br>
     * <b> pre: </b> El tax�n padre y el tax�n a eliminar existen. <br>
     * <b> post: </b> Se elimin� el tax�n del sub-�rbol. <br>
     * Si el tax�n que se elimin� ten�a sub-taxones, estos tambi�n fueron eliminados. <br>
     *
     * @param pTipo        Tipo del tax�n que se quiere eliminar. pTipo pertenece a {LUCA,
     *                     DOMINIO,
     *                     REINO, FILO, CLASE, ORDEN, FAMILIA, GENERO, ESPECIE}.
     * @param pNombreTaxon Nombre del tax�n a eliminar. pNombreTaxon != null && pNombreTaxon
     *                     != "".
     */
    public void eliminarTaxon(int pTipo, String pNombreTaxon) {

    }


    /**
     * Retorna una cadena con el tipo y el nombre del tax�n.
     *
     * @return La representaci�n del tax�n en String: <tipo del tax�n>: <nombre del tax�n>.
     */
    public String toString() {
        String toString = darNombreTipo() + ": " + nombre;
        return toString;
    }

    /**
     * Retorna el nombre del tipo del tax�n.
     *
     * @return Nombre del tipo del tax�n.
     */
    private String darNombreTipo() {
        String nombreTipo = "";

        if (tipo == LUCA) {
            nombreTipo = "LUCA";
        }
        else if (tipo == DOMINIO) {
            nombreTipo = "Dominio";
        }
        else if (tipo == REINO) {
            nombreTipo = "Reino";
        }
        else if (tipo == FILO) {
            nombreTipo = "Filo";
        }
        else if (tipo == CLASE) {
            nombreTipo = "Clase";
        }
        else if (tipo == ORDEN) {
            nombreTipo = "Orden";
        }
        else if (tipo == FAMILIA) {
            nombreTipo = "Familia";
        }
        else if (tipo == GENERO) {
            nombreTipo = "G�nero";
        }
        else {
            nombreTipo = "Especie";
        }

        return nombreTipo;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica la invariante de la clase <br>
     * <b> inv: </b> <br>
     * nombre != null && nombre != "". <br>
     * tipo pertenece a {LUCA, DOMINIO, REINO, FILO, CLASE, ORDEN, FAMILIA, GENERO, ESPECIE}.
     * <br>
     * La lista de subtaxones est� inicializada. <br>
     * Si el tipo del tax�n no es ESPECIE no puede tener un ser vivo. <br>
     * Si el tipo del tax�n es ESPECIE no puede tener sub-taxones. <br>
     * El tipo de los sub-taxones corresponde al siguiente tipo en la jerarqu�a taxon�mica. <br>
     */
    private void verificarInvariante() {
        assert nombre != null && !nombre.isEmpty() : "El nombre del tax�n debe ser inicializado.";
        assert tipo == LUCA || tipo == DOMINIO || tipo == REINO || tipo == FILO || tipo == CLASE
                || tipo == ORDEN || tipo == FAMILIA || tipo == GENERO || tipo == ESPECIE :
                "El tipo del tax�n debe ser inicializado correctamente.";
        assert subTaxones != null : "La lista de sub-taxones debe estar inicializada.";

        if (tipo != ESPECIE) {
            assert serVivo == null : "El tax�n no deber�a tener un ser vivo.";
        }
        else {
            assert subTaxones.isEmpty() : "El tax�n no deber�a tener sub-taxones.";
        }

        for (int i = 0; i < subTaxones.size(); i++) {
            Taxon taxon = (Taxon) subTaxones.get(i);
            assert taxon.tipo == tipo + 1 : "El tipo de los sub-taxones es incorrecto.";
        }
    }
}