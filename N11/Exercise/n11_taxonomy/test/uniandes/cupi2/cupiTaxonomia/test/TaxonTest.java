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

package uniandes.cupi2.cupiTaxonomia.test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import uniandes.cupi2.cupiTaxonomia.world.*;

/**
 * Clase usada para verificar la correcta implementaci�n de la clase Tax�n.
 */
public class TaxonTest {
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private Taxon taxon;

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Escenario 1. Crea un nuevo tax�n con valores por defecto, sin sub-taxones y sin seres vivos.
     */
    @Before
    public void setupEscenario1() {
        taxon = new Taxon();
    }

    /**
     * Escenario 2. Crea un nuevo tax�n de tipo Especie sin sub-taxones y sin seres vivos.
     */
    @Test
    public void setupEscenario2() {
        taxon = new Taxon(Taxon.ESPECIE, "Especie");
    }

    /**
     * Escenario 3. Crea un nuevo tax�n de tipo Dominio con sub-taxones de tipo Reino y sin seres
     * vivos.
     */
    @Test
    public void setupEscenario3() {
        taxon = new Taxon(Taxon.DOMINIO, "Dominio");
        try {
            taxon.agregarSubTaxon(Taxon.REINO, "Reino 1");
            taxon.agregarSubTaxon(Taxon.REINO, "Reino 2");
            taxon.agregarSubTaxon(Taxon.REINO, "Reino 3");
            taxon.agregarSubTaxon(Taxon.REINO, "Reino 4");
            taxon.agregarSubTaxon(Taxon.REINO, "Reino 5");
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Escenario 4. Crea un nuevo tax�n de tipo Especie con un ser vivo.
     */
    @Test
    public void setupEscenario4() {
        taxon = new Taxon(Taxon.ESPECIE, "Especie");
        try {
            taxon.agregarSerVivo("ser vivo", "cientifico", "caracteristicas", "imagen");
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Escenario 5. Crea un nuevo tax�n de tipo Familia con sub-taxones hijos de tipo G�nero y
     * sub-taxones nietos de tipo Especie.
     */
    @Test
    public void setupEscenario5() {
        taxon = new Taxon(Taxon.FAMILIA, "Familia");
        try {
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 1");
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 2");
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 3");
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 4");
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 5");
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 6");

            Taxon taxonHijo2 = taxon.buscarTaxon("Genero 2");
            Taxon taxonHijo5 = taxon.buscarTaxon("Genero 5");

            taxonHijo2.agregarSubTaxon(Taxon.ESPECIE, "Especie 21");
            taxonHijo2.agregarSubTaxon(Taxon.ESPECIE, "Especie 22");
            taxonHijo2.agregarSubTaxon(Taxon.ESPECIE, "Especie 23");
            taxonHijo5.agregarSubTaxon(Taxon.ESPECIE, "Especie 51");
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Escenario 6. Crea un nuevo tax�n de tipo Familia con sub-taxones de tipo Especie y seres
     * vivos.
     */
    @Test
    public void setupEscenario6() {
        taxon = new Taxon(Taxon.FAMILIA, "Familia");
        try {
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 1");
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 2");
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 3");
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 4");
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 5");
            taxon.agregarSubTaxon(Taxon.GENERO, "Genero 6");

            Taxon taxonHijo2 = taxon.buscarTaxon("Genero 2");
            Taxon taxonHijo3 = taxon.buscarTaxon("Genero 3");
            Taxon taxonHijo5 = taxon.buscarTaxon("Genero 5");

            taxonHijo2.agregarSubTaxon(Taxon.ESPECIE, "Especie 21");
            taxonHijo2.agregarSubTaxon(Taxon.ESPECIE, "Especie 22");
            taxonHijo2.agregarSubTaxon(Taxon.ESPECIE, "Especie 23");
            taxonHijo3.agregarSubTaxon(Taxon.ESPECIE, "Especie 31");
            taxonHijo5.agregarSubTaxon(Taxon.ESPECIE, "Especie 51");

            Taxon taxonNieto21 = taxon.buscarTaxon("Especie 21");
            Taxon taxonNieto23 = taxon.buscarTaxon("Especie 23");

            taxonNieto21.agregarSerVivo("ser vivo", "cientifico1", "caracteristicas", "imagen");
            taxonNieto23.agregarSerVivo("ser vivo", "cientifico2", "caracteristicas", "imagen");
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Prueba 1: Se encarga de verificar m�todo constructor (sin par�metros) de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * Taxon <br>
     * darNombre <br>
     * darTipo <br>
     * darSubTaxones <br>
     * darSerVivo <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Se crea un tax�n con los valores por defecto. <br>
     */
    @Test
    public void testTaxon() {

        assertNotNull("El nombre del tax�n no fue inicializado.", taxon.darNombre());
        assertEquals("El nombre dle tax�n no fue inicializado correctamente.",
                     "�ltimo antepasado com�n universal", taxon.darNombre());
        assertEquals("El tipo del tax�n no fue inicializado correctamente.", Taxon.LUCA,
                     taxon.darTipo());
        assertNotNull("La lista de sub-taxones no fue inicializada.", taxon.darSubTaxones());
        assertNull("El ser vivo debe ser nulo.", taxon.darSerVivo());
    }

    /**
     * Prueba 2: Se encarga de verificar el m�todo constructor (con par�metros) de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * Taxon <br>
     * darNombre <br>
     * darTipo <br>
     * darSubTaxones <br>
     * darSerVivo <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Se crea un tax�n con los valores dados. <br>
     */
    @Test
    public void testTaxon2() {
        setupEscenario2();

        assertNotNull("El nombre del tax�n no fue inicializado.", taxon.darNombre());
        assertEquals("El nombre dle tax�n no fue inicializado correctamente.", "Especie",
                     taxon.darNombre());
        assertEquals("El tipo del tax�n no fue inicializado correctamente.", Taxon.ESPECIE,
                     taxon.darTipo());
        assertNotNull("La lista de sub-taxones no fue inicializada.", taxon.darSubTaxones());
        assertNull("El ser vivo debe ser nulo.", taxon.darSerVivo());
    }

    /**
     * Prueba 3: Se encarga de verificar el m�todo agregarSerVivo de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarSerVivo <br>
     * darSerVivo <br>
     * <b> Casos de prueba: </b> <br>
     * 1. El tax�n no tiene un ser vivo.
     */
    @Test
    public void testAgregarSerVivo() {
        setupEscenario2();

        try {
            taxon.agregarSerVivo("nombre", "cientifico", "caracteristicas", "imagen");
            SerVivo ser = taxon.darSerVivo();
            assertNotNull("No se agreg� el ser vivo al tax�n.", taxon.darSerVivo());
            assertEquals("El nombre cient�fico del ser vivo no es el correcto.", "cientifico",
                         ser.darNombreCientifico());
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Prueba 4: Se encargar de verificar el m�todo agregarSerVivo de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarSerVivo <br>
     * <b> Casos de prueba: </b> <br>
     * 1. El tax�n tiene un ser vivo.
     */
    @Test
    public void testAgregarSerVivoError() {
        setupEscenario4();
        try {
            taxon.agregarSerVivo("nombre", "cient�fico", "caracter�sticas", "imagen");
            fail("No deber�a agregar pues el tax�n ya tiene un ser vivo.");
        } catch (Exception e) {
            // Debe generar excepci�n.
        }
    }

    /**
     * Prueba 5 - Se encarga de verificar el m�todo agregarSubTaxon de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarSubTaxon <br>
     * darSubTaxones <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Se agrega correctamente el subtax�n.
     */
    @Test
    public void testAgregarSubTaxon() {
        taxon.agregarSubTaxon(Taxon.DOMINIO, "Dominio");
        ArrayList<Taxon> subTaxones = taxon.darSubTaxones();
        assertEquals("No se agrego el sub-tax�n.", 1, subTaxones.size());
        Taxon t = (Taxon) subTaxones.get(0);
        assertEquals("El tipo de tax�n agregado no es correcto.", Taxon.DOMINIO, t.darTipo());
        assertEquals("El nombre del tax�n agregado no correcto.", "Dominio", t.darNombre());
    }

    /**
     * Prueba 6: Se encarga de verificar el m�todo buscarTaxon de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarTaxon <br>
     * darNombre <br>
     * <b> Casos de prueba: </b> <br>
     * 1. El tax�n buscado es el actual. <br>
     * 2. El tax�n buscado es un hijo del tax�n actual. <br>
     * 3. El tax�n buscado es un nieto del tax�n actual. <br>
     * 4. El tax�n buscado no existe.
     */
    @Test
    public void testBuscarTaxon() {
        setupEscenario5();

        // Caso de prueba 1.
        Taxon actual = taxon.buscarTaxon("Familia");
        assertNotNull("El tax�n buscado si existe.", actual);
        assertEquals("El tax�n encontrado no es el correcto.", "Familia", actual.darNombre());

        // Caso de prueba 2.
        Taxon hijo = taxon.buscarTaxon("Genero 3");
        assertNotNull("El tax�n buscado si existe.", hijo);
        assertEquals("El tax�n encontrado no es el correcto.", "Genero 3", hijo.darNombre());

        hijo = taxon.buscarTaxon("Genero 6");
        assertNotNull("El tax�n buscado si existe.", hijo);
        assertEquals("El tax�n encontrado no es el correcto.", "Genero 6", hijo.darNombre());

        // Caso de prueba 3.
        Taxon nieto = taxon.buscarTaxon("Especie 22");
        assertNotNull("El tax�n buscado si existe.", nieto);
        assertEquals("El tax�n encontrado no es el correcto.", "Especie 22", nieto.darNombre());

        nieto = taxon.buscarTaxon("Especie 51");
        assertNotNull("El tax�n buscado si existe.", nieto);
        assertEquals("El tax�n encontrado no es el correcto.", "Especie 51", nieto.darNombre());

        // Caso de prueba 4.
        Taxon noExiste = taxon.buscarTaxon("No existe");
        assertNull("El tax�n buscado no existe.", noExiste);
    }

    /**
     * Prueba 7: Se encarga de verificar el m�todo buscarTaxonNivel de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarTaxonNivel <br>
     * <b> Casos de prueba: </b> <br>
     * 1. El tax�n buscado es el actual. <br>
     * 2. El tax�n buscado es un hijo del tax�n actual. <br>
     * 3. El tax�n buscado es un nieto del tax�n actual. 
     */
    @Test
    public void testBuscarTaxonNivel1() {

        setupEscenario5();

        // Caso de prueba 1.
        Taxon actual = taxon.buscarTaxonNivel(Taxon.FAMILIA, "Familia");
        assertNotNull("El tax�n buscado s� existe.", actual);
        assertEquals("El tax�n encontrado no es el correcto.", "Familia", actual.darNombre());

        // Caso de prueba 2.
        Taxon hijo = taxon.buscarTaxonNivel(Taxon.GENERO, "Genero 2");
        assertNotNull("El tax�n buscado s� existe.", hijo);
        assertEquals("El tax�n encontrado no es el correcto.", "Genero 2", hijo.darNombre());

        hijo = taxon.buscarTaxonNivel(Taxon.GENERO, "Genero 4");
        assertNotNull("El tax�n buscado s� existe.", hijo);
        assertEquals("El tax�n encontrado no es el correcto.", "Genero 4", hijo.darNombre());

        // Caso de prueba 3.
        Taxon nieto = taxon.buscarTaxonNivel(Taxon.ESPECIE, "Especie 51");
        assertNotNull("El tax�n buscado s� existe.", nieto);
        assertEquals("El tax�n encontrado no es el correcto.", "Especie 51", nieto.darNombre());

        nieto = taxon.buscarTaxonNivel(Taxon.ESPECIE, "Especie 23");
        assertNotNull("El tax�n buscado s� existe.", nieto);
        assertEquals("El tax�n encontrado no es el correcto.", "Especie 23", nieto.darNombre());
    }

    /**
     * Prueba 8: Se encarga de verificar el m�todo buscarTaxonNivel de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarTaxonNivel <br>
     * <b> Casos de prueba: </b> <br>
     * 1. La distancia es superior al n�mero de niveles y el tax�n buscado no existe. <br>
     * 2. La distancia es superior al n�mero de niveles y el tax�n buscado existe, pero no es de
     * ese nivel. <br>
     * 3. La distancia es v�lida pero el tax�n no existe.
     */
    @Test
    public void testBuscarTaxonNivel2() {
        setupEscenario5();

        // Caso de prueba 1.
        Taxon noExiste = taxon.buscarTaxonNivel(Taxon.REINO, "Reino 4");
        assertNull("El tax�n buscado no existe.", noExiste);

        // Caso de prueba 2.
        noExiste = taxon.buscarTaxonNivel(Taxon.ESPECIE, "Genero 5");
        assertNull("El tax�n buscado no existe.", noExiste);

        // Caso de prueba 3.
        noExiste = taxon.buscarTaxonNivel(Taxon.ESPECIE, "No existe");
        assertNull("El tax�n buscado no existe.", noExiste);
    }

    /**
     * Prueba 9: Se encarga de verificar el m�todo buscarSerVivo de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarSerVivo <br>
     * <b> Casos de prueba: </b> <br>
     * 1. El ser vivo buscado es del tax�n actual.
     */
    @Test
    public void testBuscarSerVivo1() {
        setupEscenario4();

        SerVivo ser = taxon.buscarSerVivo("cientifico");
        assertNotNull("El ser vivo buscado si existe.", ser);
        assertEquals("El ser vivo encontrado no es el correcto.", "cientifico",
                     ser.darNombreCientifico());
    }

    /**
     * Prueba 10: Se encarga de verificar el m�todo buscarSerVivo de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarSerVivo <br>
     * <b> Casos de prueba: </b> <br>
     * 1. El ser vivo buscado es de un hijo del tax�n actual. <br>
     * 2. El ser vivo buscado es de un nieto del tax�n actual. <br>
     * 3. El ser vivo buscado no existe.
     */
    @Test
    public void testBuscarSerVivo2() {
        setupEscenario6();

        // Caso de prueba 1.
        Taxon taxonPadre = taxon.buscarTaxon("Genero 2");
        SerVivo ser = taxonPadre.buscarSerVivo("cientifico1");
        assertNotNull("El ser vivo buscado si existe.", ser);
        assertEquals("El ser vivo encontrado no es el correcto.", "cientifico1",
                     ser.darNombreCientifico());

        // Caso de prueba 2.
        ser = taxon.buscarSerVivo("cientifico2");
        assertNotNull("El ser vivo buscado si existe", ser);
        assertEquals("El ser vivo encontrado no es el correcto", "cientifico2",
                     ser.darNombreCientifico());

        ser = taxon.buscarSerVivo("cientifico1");
        assertNotNull("El ser vivo buscado si existe", ser);
        assertEquals("El ser vivo encontrado no es el correcto", "cientifico1",
                     ser.darNombreCientifico());

        // Caso de prueba 3.
        SerVivo noExiste = taxon.buscarSerVivo("No existe");
        assertNull("El ser vivo buscado no existe.", noExiste);
    }

    /**
     * Prueba 11: Se encarga de verificar el m�todo eliminarTaxon de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * eliminarTaxon <br>
     * buscarTaxon <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Se elimina un subtax�n sin hijos. <br>
     * 2. Se elimina un subtax�n con hijos. <br>
     * 3. Se va a eliminar un tax�n de un sub-tax�n. <br>
     * 4. Se va a eliminar un tax�n de un sub-tax�n que tiene sub-taxones.
     */
    @Test
    public void testEliminarTaxon() {
        setupEscenario6();

        // Caso de prueba 1.
        taxon.eliminarTaxon(Taxon.GENERO, "Genero 4");
        assertEquals("El tax�n no fue eliminado. Deber�an haber 5 sub-taxones.", 5,
                     taxon.darSubTaxones().size());
        Taxon t = taxon.buscarTaxon("Genero 4");
        assertNull("El tax�n no fue eliminado correctamente.", t);

        // Caso de prueba 2.
        taxon.eliminarTaxon(Taxon.GENERO, "Genero 5");
        assertEquals("El tax�n no fue eliminado. Deber�an haber 4 sub-taxones.", 4,
                     taxon.darSubTaxones().size());
        t = taxon.buscarTaxon("Genero 5");
        assertNull("El tax�n no fue eliminado correctamente.", t);
        t = taxon.buscarTaxon("Especie 51");
        assertNull("Los subtaxones del taxon eliminado deber�an ser eliminados.", t);

        // Caso de prueba 3.
        taxon.eliminarTaxon(Taxon.ESPECIE, "Especie 23");
        t = taxon.buscarTaxon("Especie 23");
        assertNull("El tax�n no fue eliminado", t);

        // Caso de prueba 4.
        taxon.eliminarTaxon(Taxon.GENERO, "Genero 2");
        assertEquals("El tax�n no fue eliminado. Deber�an haber 3 sub-taxones.", 3,
                     taxon.darSubTaxones().size());
        t = taxon.buscarTaxon("Genero 2");
        assertNull("El tax�n no fue eliminado correctamente.", t);
        t = taxon.buscarTaxon("Especie 21");
        assertNull("El tax�n no fue eliminado correctamente.", t);
    }

    /**
     * Prueba 12: Se encarga de verificar el m�todo darNumTaxones de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * darNumTaxones <br>
     * <b> Casos de prueba: </b> <br>
     * 1. El tax�n es una hoja. <br>
     * 2. El tax�n es tiene sub-taxones. <br>
     */
    @Test
    public void testDarNumTaxones() {
        // Caso de prueba 1.
        setupEscenario2();
        int numTaxones = taxon.darNumTaxones();
        assertEquals("El n�mero de taxones no es calculado correctamente.", 1, numTaxones);

        // Caso de prueba 2.
        setupEscenario3();
        numTaxones = taxon.darNumTaxones();
        assertEquals("El n�mero de taxones no es calculado correctamente.", 6, numTaxones);


        setupEscenario5();
        numTaxones = taxon.darNumTaxones();
        assertEquals("El n�mero de taxones no es calculado correctamente.", 11, numTaxones);
    }

    /**
     * Prueba 13: Se encarga de verificar el m�todo darNumSeresVivos de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * darNumSeresVivos <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Se calcula correctamente el n�mero de seres vivos.
     */
    @Test
    public void testDarNumSeresVivos() {
        setupEscenario6();
        int numSeres = taxon.darNumSeresVivos();
        assertEquals("El n�mero de libros no es calculado correctamente.", 2, numSeres);

        try {
            Taxon taxonEspecie = taxon.buscarTaxon("Especie 51");
            taxonEspecie.agregarSerVivo("nombre comun", "cientifico3", "caracteristicas", "imagen");
            numSeres = taxon.darNumSeresVivos();
            assertEquals("El n�mero de libros no es calculado correctamente.", 3, numSeres);
        } catch (Exception e) {
            // No deber�a generar excepci�n.
        }

    }

    /**
     * Prueba 14: Se encarga de verificar el m�todo buscarSeresVivos de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarSeresVivos <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Se retorna correctamente la lista de seres vivos.
     */
    @Test
    public void testBuscarSeresVivos() {
        setupEscenario6();

        ArrayList<SerVivo> listaSeresVivos = new ArrayList<SerVivo>();
        taxon.buscarSeresVivos(listaSeresVivos);
        assertEquals("El n�mero de seres vivos encontrados no es correcto.", 2,
                     listaSeresVivos.size());
    }

    /**
     * Prueba 15: Se encarga de verificar el m�todo buscarTaxonesPreorden de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarTaxonesPreorden <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Los taxones fueron ordenados correctamente en preorden.
     */
    @Test
    public void testBuscarTaxonesPreorden() {
        setupEscenario5();

        ArrayList<Taxon> listaTaxones = new ArrayList<Taxon>();
        taxon.buscarTaxonesPreorden(listaTaxones);

        assertEquals("El n�mero de taxones encontrados no es correcto.", 11, listaTaxones.size());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Familia",
                     ((Taxon) listaTaxones.get(0)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 1",
                     ((Taxon) listaTaxones.get(1)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 2",
                     ((Taxon) listaTaxones.get(2)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 21",
                     ((Taxon) listaTaxones.get(3)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 22",
                     ((Taxon) listaTaxones.get(4)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 23",
                     ((Taxon) listaTaxones.get(5)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 3",
                     ((Taxon) listaTaxones.get(6)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 4",
                     ((Taxon) listaTaxones.get(7)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 5",
                     ((Taxon) listaTaxones.get(8)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 51",
                     ((Taxon) listaTaxones.get(9)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 6",
                     ((Taxon) listaTaxones.get(10)).darNombre());
    }

    /**
     * Prueba 16: Se encarga de verificar el m�todo buscarTaxonesPostorden de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarTaxonesPostorden <br>
     * <b> Casos de prueba: </b> 
     * 1.  Los taxones fueron ordenados correctamente en postorden.
     */
    @Test
    public void testBuscarTaxonesPostorden() {
        setupEscenario5();

        ArrayList<Taxon> listaTaxones = new ArrayList<Taxon>();
        taxon.buscarTaxonesPostorden(listaTaxones);

        assertEquals("El n�mero de taxones encontrados no es correcto.", 11, listaTaxones.size());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 1",
                     ((Taxon) listaTaxones.get(0)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 21",
                     ((Taxon) listaTaxones.get(1)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 22",
                     ((Taxon) listaTaxones.get(2)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 23",
                     ((Taxon) listaTaxones.get(3)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 2",
                     ((Taxon) listaTaxones.get(4)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 3",
                     ((Taxon) listaTaxones.get(5)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 4",
                     ((Taxon) listaTaxones.get(6)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 51",
                     ((Taxon) listaTaxones.get(7)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 5",
                     ((Taxon) listaTaxones.get(8)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Genero 6",
                     ((Taxon) listaTaxones.get(9)).darNombre());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Familia",
                     ((Taxon) listaTaxones.get(10)).darNombre());
    }

    /**
     * Prueba 17: Se encarga de verificar el m�todo buscarTaxonesNivel de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarTaxonesNivel <br>
     * <b> Casos de prueba: </b> <br>
     * 1. Se retorna correctamente los elementos del nivel. 
     */
    @Test
    public void testBuscarTaxonesNivel() {
        setupEscenario5();

        ArrayList<String> listaTaxones = new ArrayList<String>();
        taxon.buscarTaxonesNivel(Taxon.ESPECIE, listaTaxones);

        assertEquals("El n�mero de taxones encontrados no es correcto.", 4, listaTaxones.size());
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 21",
                     (String) listaTaxones.get(0));
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 22",
                     (String) listaTaxones.get(1));
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 23",
                     (String) listaTaxones.get(2));
        assertEquals("Los taxones no fueron ordenados correctamente.", "Especie 51",
                     (String) listaTaxones.get(3));
    }

    /**
     * Prueba 18: Se encarga de verificar el m�todo toString de la clase. <br>
     * <b> M�todos a probar: </b> <br>
     * toString <br>
     * <b> Casos de prueba: </b> <br>
     * 1. El m�todo retorna: el nombre del tipo: nombre del tax�n.
     */
    @Test
    public void testToString() {
        setupEscenario2();
        assertEquals("La representaci�n String del tax�n no es correcta.", "Especie: Especie",
                     taxon.toString());
    }
}