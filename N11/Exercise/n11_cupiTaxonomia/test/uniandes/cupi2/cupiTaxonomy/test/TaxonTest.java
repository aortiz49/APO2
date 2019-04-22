/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad of los Andes (Bogot� - Colombia)
 * Departamento of Ingenier�a of Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiTaxonomy
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiTaxonomy.test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import uniandes.cupi2.cupiTaxonomy.world.*;

/**
 * Class usada para verificar la correcta implementaci�n of la clase Taxon.
 */
public class TaxonTest {
    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Class donde se har�n las pruebas.
     */
    private Taxon taxon;

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Escenario 1. Crea un newItem taxon con valores por defecto, sin sub-taxa y sin living beings.
     */
    @Before
    public void setupEscenario1() {
        taxon = new Taxon();
    }

    /**
     * Escenario 2. Crea un newItem taxon of level Species sin sub-taxa y sin living beings.
     */
    @Test
    public void setupEscenario2() {
        taxon = new Taxon(Taxon.SPECIES, "Species");
    }

    /**
     * Escenario 3. Crea un newItem taxon of level Domain con sub-taxa of level Kingdom y sin seres
     * vivos.
     */
    @Test
    public void setupEscenario3() {
        taxon = new Taxon(Taxon.DOMAIN, "Domain");
        try {
            taxon.addSubTaxon(Taxon.KINGDOM, "Kingdom 1");
            taxon.addSubTaxon(Taxon.KINGDOM, "Kingdom 2");
            taxon.addSubTaxon(Taxon.KINGDOM, "Kingdom 3");
            taxon.addSubTaxon(Taxon.KINGDOM, "Kingdom 4");
            taxon.addSubTaxon(Taxon.KINGDOM, "Kingdom 5");
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Escenario 4. Crea un newItem taxon of level Species con un living being.
     */
    @Test
    public void setupEscenario4() {
        taxon = new Taxon(Taxon.SPECIES, "Species");
        try {
            taxon.addLivingBeing("living being", "cientifico", "characteristics", "image");
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Escenario 5. Crea un newItem taxon of level Family con sub-taxa hijos of level Genus y
     * sub-taxa nietos of level Species.
     */
    @Test
    public void setupEscenario5() {
        taxon = new Taxon(Taxon.FAMILY, "Family");
        try {
            taxon.addSubTaxon(Taxon.GENUS, "Genus 1");
            taxon.addSubTaxon(Taxon.GENUS, "Genus 2");
            taxon.addSubTaxon(Taxon.GENUS, "Genus 3");
            taxon.addSubTaxon(Taxon.GENUS, "Genus 4");
            taxon.addSubTaxon(Taxon.GENUS, "Genus 5");
            taxon.addSubTaxon(Taxon.GENUS, "Genus 6");

            Taxon taxonChild2 = taxon.findTaxon("Genus 2");
            Taxon taxonChild5 = taxon.findTaxon("Genus 5");

            taxonChild2.addSubTaxon(Taxon.SPECIES, "Species 21");
            taxonChild2.addSubTaxon(Taxon.SPECIES, "Species 22");
            taxonChild2.addSubTaxon(Taxon.SPECIES, "Species 23");
            taxonChild5.addSubTaxon(Taxon.SPECIES, "Species 51");
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Escenario 6. Crea un newItem taxon of level Family con sub-taxa of level Species y seres
     * vivos.
     */
    @Test
    public void setupEscenario6() {
        taxon = new Taxon(Taxon.FAMILY, "Family");
        try {
            taxon.addSubTaxon(Taxon.GENUS, "Genus 1");
            taxon.addSubTaxon(Taxon.GENUS, "Genus 2");
            taxon.addSubTaxon(Taxon.GENUS, "Genus 3");
            taxon.addSubTaxon(Taxon.GENUS, "Genus 4");
            taxon.addSubTaxon(Taxon.GENUS, "Genus 5");
            taxon.addSubTaxon(Taxon.GENUS, "Genus 6");

            Taxon taxonChild2 = taxon.findTaxon("Genus 2");
            Taxon taxonChild3 = taxon.findTaxon("Genus 3");
            Taxon taxonChild5 = taxon.findTaxon("Genus 5");

            taxonChild2.addSubTaxon(Taxon.SPECIES, "Species 21");
            taxonChild2.addSubTaxon(Taxon.SPECIES, "Species 22");
            taxonChild2.addSubTaxon(Taxon.SPECIES, "Species 23");
            taxonChild3.addSubTaxon(Taxon.SPECIES, "Species 31");
            taxonChild5.addSubTaxon(Taxon.SPECIES, "Species 51");

            Taxon taxonNieto21 = taxon.findTaxon("Species 21");
            Taxon taxonNieto23 = taxon.findTaxon("Species 23");

            taxonNieto21.addLivingBeing("living being", "cientifico1", "characteristics", "image");
            taxonNieto23.addLivingBeing("living being", "cientifico2", "characteristics", "image");
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Prueba 1: Se encarga of verificar m�todo constructor (sin parameters) of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * Taxon <br>
     * getName <br>
     * getLevel <br>
     * getSubTaxa <br>
     * getLivingBeing <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se crea un taxon con los valores por defecto. <br>
     */
    @Test
    public void testTaxon() {

        assertNotNull("El name of the taxon no fue inicializado.", taxon.getName());
        assertEquals("El name dle taxon no fue inicializado correctamente.",
                     "Last universal " + "common ancestor", taxon.getName());
        assertEquals("El level of the taxon no fue inicializado correctamente.", Taxon.LUCA,
                     taxon.getLevel());
        assertNotNull("La list of sub-taxa no fue inicializada.", taxon.getSubTaxa());
        assertNull("El living being debe ser nulo.", taxon.getLivingBeing());
    }

    /**
     * Prueba 2: Se encarga of verificar el m�todo constructor (con parameters) of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * Taxon <br>
     * getName <br>
     * getLevel <br>
     * getSubTaxa <br>
     * getLivingBeing <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se crea un taxon con los valores dados. <br>
     */
    @Test
    public void testTaxon2() {
        setupEscenario2();

        assertNotNull("El name of the taxon no fue inicializado.", taxon.getName());
        assertEquals("El name dle taxon no fue inicializado correctamente.", "Species",
                     taxon.getName());
        assertEquals("El level of the taxon no fue inicializado correctamente.", Taxon.SPECIES,
                     taxon.getLevel());
        assertNotNull("La list of sub-taxa no fue inicializada.", taxon.getSubTaxa());
        assertNull("El living being debe ser nulo.", taxon.getLivingBeing());
    }

    /**
     * Prueba 3: Se encarga of verificar el m�todo addLivingBeing of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * addLivingBeing <br>
     * getLivingBeing <br>
     * <b> Casos of prueba: </b> <br>
     * 1. El taxon no tiene un living being.
     */
    @Test
    public void testAddLivingBeing() {
        setupEscenario2();

        try {
            taxon.addLivingBeing("name", "cientifico", "characteristics", "image");
            LivingBeing ser = taxon.getLivingBeing();
            assertNotNull("No se agreg� el living being al taxon.", taxon.getLivingBeing());
            assertEquals("El name cient�fico of the living being no es el correcto.", "cientifico",
                         ser.getScientificName());
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Prueba 4: Se encargar of verificar el m�todo addLivingBeing of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * addLivingBeing <br>
     * <b> Casos of prueba: </b> <br>
     * 1. El taxon tiene un living being.
     */
    @Test
    public void testAddLivingBeingError() {
        setupEscenario4();
        try {
            taxon.addLivingBeing("name", "cient�fico", "caracter�sticas", "image");
            fail("No deber�to be added pues el taxon ya tiene un living being.");
        } catch (Exception e) {
            // Debe generar excepci�n.
        }
    }

    /**
     * Prueba 5 - Se encarga of verificar el m�todo addSubTaxon of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * addSubTaxon <br>
     * getSubTaxa <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se agrega correctamente el subtaxon.
     */
    @Test
    public void testAddSubTaxon() {
        taxon.addSubTaxon(Taxon.DOMAIN, "Domain");
        ArrayList<Taxon> subTaxa = taxon.getSubTaxa();
        assertEquals("No se agrego el sub-taxon.", 1, subTaxa.size());
        Taxon t = (Taxon) subTaxa.get(0);
        assertEquals("El level of taxon agregado no es correcto.", Taxon.DOMAIN, t.getLevel());
        assertEquals("El name of the taxon agregado no correcto.", "Domain", t.getName());
    }

    /**
     * Prueba 6: Se encarga of verificar el m�todo findTaxon of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * findTaxon <br>
     * getName <br>
     * <b> Casos of prueba: </b> <br>
     * 1. El taxon buscado es el actual. <br>
     * 2. El taxon buscado es un hijo of the taxon actual. <br>
     * 3. El taxon buscado es un nieto of the taxon actual. <br>
     * 4. El taxon buscado no existe.
     */
    @Test
    public void testSearchTaxon() {
        setupEscenario5();

        // Caso of prueba 1.
        Taxon actual = taxon.findTaxon("Family");
        assertNotNull("El taxon buscado si existe.", actual);
        assertEquals("El taxon encontrado no es el correcto.", "Family", actual.getName());

        // Caso of prueba 2.
        Taxon hijo = taxon.findTaxon("Genus 3");
        assertNotNull("El taxon buscado si existe.", hijo);
        assertEquals("El taxon encontrado no es el correcto.", "Genus 3", hijo.getName());

        hijo = taxon.findTaxon("Genus 6");
        assertNotNull("El taxon buscado si existe.", hijo);
        assertEquals("El taxon encontrado no es el correcto.", "Genus 6", hijo.getName());

        // Caso of prueba 3.
        Taxon nieto = taxon.findTaxon("Species 22");
        assertNotNull("El taxon buscado si existe.", nieto);
        assertEquals("El taxon encontrado no es el correcto.", "Species 22", nieto.getName());

        nieto = taxon.findTaxon("Species 51");
        assertNotNull("El taxon buscado si existe.", nieto);
        assertEquals("El taxon encontrado no es el correcto.", "Species 51", nieto.getName());

        // Caso of prueba 4.
        Taxon noExiste = taxon.findTaxon("No existe");
        assertNull("El taxon buscado no existe.", noExiste);
    }

    /**
     * Prueba 7: Se encarga of verificar el m�todo findTaxonByLevel of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * findTaxonByLevel <br>
     * <b> Casos of prueba: </b> <br>
     * 1. El taxon buscado es el actual. <br>
     * 2. El taxon buscado es un hijo of the taxon actual. <br>
     * 3. El taxon buscado es un nieto of the taxon actual.
     */
    @Test
    public void testSearchTaxonNivel1() {

        setupEscenario5();

        // Caso of prueba 1.
        Taxon actual = taxon.findTaxonByLevel(Taxon.FAMILY, "Family");
        assertNotNull("El taxon buscado s� existe.", actual);
        assertEquals("El taxon encontrado no es el correcto.", "Family", actual.getName());

        // Caso of prueba 2.
        Taxon hijo = taxon.findTaxonByLevel(Taxon.GENUS, "Genus 2");
        assertNotNull("El taxon buscado s� existe.", hijo);
        assertEquals("El taxon encontrado no es el correcto.", "Genus 2", hijo.getName());

        hijo = taxon.findTaxonByLevel(Taxon.GENUS, "Genus 4");
        assertNotNull("El taxon buscado s� existe.", hijo);
        assertEquals("El taxon encontrado no es el correcto.", "Genus 4", hijo.getName());

        // Caso of prueba 3.
        Taxon nieto = taxon.findTaxonByLevel(Taxon.SPECIES, "Species 51");
        assertNotNull("El taxon buscado s� existe.", nieto);
        assertEquals("El taxon encontrado no es el correcto.", "Species 51", nieto.getName());

        nieto = taxon.findTaxonByLevel(Taxon.SPECIES, "Species 23");
        assertNotNull("El taxon buscado s� existe.", nieto);
        assertEquals("El taxon encontrado no es el correcto.", "Species 23", nieto.getName());
    }

    /**
     * Prueba 8: Se encarga of verificar el m�todo findTaxonByLevel of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * findTaxonByLevel <br>
     * <b> Casos of prueba: </b> <br>
     * 1. La distancia es superior al number of niveles y el taxon buscado no existe. <br>
     * 2. La distancia es superior al number of niveles y el taxon buscado existe, pero no es de
     * ese nivel. <br>
     * 3. La distancia es v�lida pero el taxon no existe.
     */
    @Test
    public void testSearchTaxonNivel2() {
        setupEscenario5();

        // Caso of prueba 1.
        Taxon noExiste = taxon.findTaxonByLevel(Taxon.KINGDOM, "Kingdom 4");
        assertNull("El taxon buscado no existe.", noExiste);

        // Caso of prueba 2.
        noExiste = taxon.findTaxonByLevel(Taxon.SPECIES, "Genus 5");
        assertNull("El taxon buscado no existe.", noExiste);

        // Caso of prueba 3.
        noExiste = taxon.findTaxonByLevel(Taxon.SPECIES, "No existe");
        assertNull("El taxon buscado no existe.", noExiste);
    }

    /**
     * Prueba 9: Se encarga of verificar el m�todo findLivingBeing of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * findLivingBeing <br>
     * <b> Casos of prueba: </b> <br>
     * 1. El living being buscado es of the taxon actual.
     */
    @Test
    public void testSearchLivingBeing1() {
        setupEscenario4();

        LivingBeing ser = taxon.findLivingBeing("cientifico");
        assertNotNull("El living being buscado si existe.", ser);
        assertEquals("El living being encontrado no es el correcto.", "cientifico",
                     ser.getScientificName());
    }

    /**
     * Prueba 10: Se encarga of verificar el m�todo findLivingBeing of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * findLivingBeing <br>
     * <b> Casos of prueba: </b> <br>
     * 1. El living being buscado es of un hijo of the taxon actual. <br>
     * 2. El living being buscado es of un nieto of the taxon actual. <br>
     * 3. El living being buscado no existe.
     */
    @Test
    public void testSearchLivingBeing2() {
        setupEscenario6();

        // Caso of prueba 1.
        Taxon taxonFather = taxon.findTaxon("Genus 2");
        LivingBeing ser = taxonFather.findLivingBeing("cientifico1");
        assertNotNull("El living being buscado si existe.", ser);
        assertEquals("El living being encontrado no es el correcto.", "cientifico1",
                     ser.getScientificName());

        // Caso of prueba 2.
        ser = taxon.findLivingBeing("cientifico2");
        assertNotNull("El living being buscado si existe", ser);
        assertEquals("El living being encontrado no es el correcto", "cientifico2",
                     ser.getScientificName());

        ser = taxon.findLivingBeing("cientifico1");
        assertNotNull("El living being buscado si existe", ser);
        assertEquals("El living being encontrado no es el correcto", "cientifico1",
                     ser.getScientificName());

        // Caso of prueba 3.
        LivingBeing noExiste = taxon.findLivingBeing("No existe");
        assertNull("El living being buscado no existe.", noExiste);
    }

    /**
     * Prueba 11: Se encarga of verificar el m�todo eliminateTaxon of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * eliminateTaxon <br>
     * findTaxon <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se elimina un subtaxon sin hijos. <br>
     * 2. Se elimina un subtaxon con hijos. <br>
     * 3. Se va a eliminar un taxon of un sub-taxon. <br>
     * 4. Se va a eliminar un taxon of un sub-taxon que tiene sub-taxa.
     */
    @Test
    public void testEliminateTaxon() {
        setupEscenario6();

        // Caso of prueba 1.
        taxon.eliminateTaxon(Taxon.GENUS, "Genus 4");
        assertEquals("El taxon no fue eliminado. Deber�an haber 5 sub-taxa.", 5,
                     taxon.getSubTaxa().size());
        Taxon t = taxon.findTaxon("Genus 4");
        assertNull("El taxon no fue eliminado correctamente.", t);

        // Caso of prueba 2.
        taxon.eliminateTaxon(Taxon.GENUS, "Genus 5");
        assertEquals("El taxon no fue eliminado. Deber�an haber 4 sub-taxa.", 4,
                     taxon.getSubTaxa().size());
        t = taxon.findTaxon("Genus 5");
        assertNull("El taxon no fue eliminado correctamente.", t);
        t = taxon.findTaxon("Species 51");
        assertNull("Los subtaxa of the taxon eliminado deber�an ser eliminados.", t);

        // Caso of prueba 3.
        taxon.eliminateTaxon(Taxon.SPECIES, "Species 23");
        t = taxon.findTaxon("Species 23");
        assertNull("El taxon no fue eliminado", t);

        // Caso of prueba 4.
        taxon.eliminateTaxon(Taxon.GENUS, "Genus 2");
        assertEquals("El taxon no fue eliminado. Deber�an haber 3 sub-taxa.", 3,
                     taxon.getSubTaxa().size());
        t = taxon.findTaxon("Genus 2");
        assertNull("El taxon no fue eliminado correctamente.", t);
        t = taxon.findTaxon("Species 21");
        assertNull("El taxon no fue eliminado correctamente.", t);
    }

    /**
     * Prueba 12: Se encarga of verificar el m�todo getNumTaxa of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * getNumTaxa <br>
     * <b> Casos of prueba: </b> <br>
     * 1. El taxon es una hoja. <br>
     * 2. El taxon es tiene sub-taxa. <br>
     */
    @Test
    public void testDarNumTaxa() {
        // Caso of prueba 1.
        setupEscenario2();
        int numTaxa = taxon.getNumTaxa();
        assertEquals("El number of taxa no es calculado correctamente.", 1, numTaxa);

        // Caso of prueba 2.
        setupEscenario3();
        numTaxa = taxon.getNumTaxa();
        assertEquals("El number of taxa no es calculado correctamente.", 6, numTaxa);


        setupEscenario5();
        numTaxa = taxon.getNumTaxa();
        assertEquals("El number of taxa no es calculado correctamente.", 11, numTaxa);
    }

    /**
     * Prueba 13: Se encarga of verificar el m�todo getNumLivingBeings of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * getNumLivingBeings <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se calcula correctamente el number of living beings.
     */
    @Test
    public void testDarNumLivingBeings() {
        setupEscenario6();
        int numSeres = taxon.getNumLivingBeings();
        assertEquals("El number of libros no es calculado correctamente.", 2, numSeres);

        try {
            Taxon taxonSpecies = taxon.findTaxon("Species 51");
            taxonSpecies.addLivingBeing("name comun", "cientifico3", "characteristics", "image");
            numSeres = taxon.getNumLivingBeings();
            assertEquals("El number of libros no es calculado correctamente.", 3, numSeres);
        } catch (Exception e) {
            // No deber�a generar excepci�n.
        }

    }

    /**
     * Prueba 14: Se encarga of verificar el m�todo findLivingBeings of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * findLivingBeings <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se retorna correctamente la list of living beings.
     */
    @Test
    public void testSearchLivingBeings() {
        setupEscenario6();

        ArrayList<LivingBeing> listLivingBeings = new ArrayList<LivingBeing>();
        taxon.findLivingBeings(listLivingBeings);
        assertEquals("El number of living beings encontrados no es correcto.", 2,
                     listLivingBeings.size());
    }

    /**
     * Prueba 15: Se encarga of verificar el m�todo findTaxaPre-order of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * findTaxaPre-order <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Los taxa fueron ordenados correctamente en preorden.
     */
    @Test
    public void testSearchTaxaPreOrder() {
        setupEscenario5();

        ArrayList<Taxon> taxonList = new ArrayList<Taxon>();
        taxon.findTaxaPreOrder(taxonList);

        assertEquals("El number of taxa encontrados no es correcto.", 11, taxonList.size());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Family",
                     ((Taxon) taxonList.get(0)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 1",
                     ((Taxon) taxonList.get(1)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 2",
                     ((Taxon) taxonList.get(2)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 21",
                     ((Taxon) taxonList.get(3)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 22",
                     ((Taxon) taxonList.get(4)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 23",
                     ((Taxon) taxonList.get(5)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 3",
                     ((Taxon) taxonList.get(6)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 4",
                     ((Taxon) taxonList.get(7)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 5",
                     ((Taxon) taxonList.get(8)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 51",
                     ((Taxon) taxonList.get(9)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 6",
                     ((Taxon) taxonList.get(10)).getName());
    }

    /**
     * Prueba 16: Se encarga of verificar el m�todo findTaxaPostOrder of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * findTaxaPostOrder <br>
     * <b> Casos of prueba: </b>
     * 1.  Los taxa fueron ordenados correctamente en postorden.
     */
    @Test
    public void testSearchTaxaPostOrder() {
        setupEscenario5();

        ArrayList<Taxon> taxonList = new ArrayList<Taxon>();
        taxon.findTaxaPostOrder(taxonList);

        assertEquals("El number of taxa encontrados no es correcto.", 11, taxonList.size());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 1",
                     ((Taxon) taxonList.get(0)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 21",
                     ((Taxon) taxonList.get(1)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 22",
                     ((Taxon) taxonList.get(2)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 23",
                     ((Taxon) taxonList.get(3)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 2",
                     ((Taxon) taxonList.get(4)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 3",
                     ((Taxon) taxonList.get(5)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 4",
                     ((Taxon) taxonList.get(6)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 51",
                     ((Taxon) taxonList.get(7)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 5",
                     ((Taxon) taxonList.get(8)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 6",
                     ((Taxon) taxonList.get(9)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Family",
                     ((Taxon) taxonList.get(10)).getName());
    }

    /**
     * Prueba 17: Se encarga of verificar el m�todo findTaxaByLevel of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * findTaxaByLevel <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se retorna correctamente los elementos of the nivel.
     */
    @Test
    public void testSearchTaxaNivel() {
        setupEscenario5();

        ArrayList<String> taxonList = new ArrayList<String>();
        taxon.findTaxaByLevel(Taxon.SPECIES, taxonList);

        assertEquals("El number of taxa encontrados no es correcto.", 4, taxonList.size());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 21",
                     (String) taxonList.get(0));
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 22",
                     (String) taxonList.get(1));
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 23",
                     (String) taxonList.get(2));
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 51",
                     (String) taxonList.get(3));
    }

    /**
     * Prueba 18: Se encarga of verificar el m�todo toString of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * toString <br>
     * <b> Casos of prueba: </b> <br>
     * 1. El m�todo retorna: el name of the level: name of the taxon.
     */
    @Test
    public void testToString() {
        setupEscenario2();
        assertEquals("La representaci�n String of the taxon no es correcta.", "Species: Species",
                     taxon.toString());
    }
}