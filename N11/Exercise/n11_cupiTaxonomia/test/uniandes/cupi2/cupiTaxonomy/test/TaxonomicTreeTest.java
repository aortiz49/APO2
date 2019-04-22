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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.cupiTaxonomy.world.*;

/**
 * Class usada para verificar la correcta implementaci�n of la clase TaxonomicTree.
 */
public class TaxonomicTreeTest {
    // -------------------------------------------------------------
    // Constants
    // -------------------------------------------------------------

    /**
     * Constant that represents la ruta of the archivo donde se guarda y se carga la informaci�n.
     */
    private final static String RUTA = "./test/data/test.data";

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * Class donde se har�n las pruebas.
     */
    private TaxonomicTree arbolTaxonomico;

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * Escenario 1: Crea un �rbol taxon�mico vac�o.
     */
    @Before
    public void setupEscenario1() {
        try {
            arbolTaxonomico = new TaxonomicTree("");
        } catch (Exception e) {
            fail("No se deber�a generar el error " + e.getMessage() + ".");
        }
    }

    /**
     * Escenario 2: Crea un �rbol taxon�mico a partir of un archivo.
     */
    @Test
    public void setupEscenario2() {
        try {
            arbolTaxonomico = new TaxonomicTree(RUTA);
        } catch (Exception e) {
            fail("No se deber�a generar el error " + e.getMessage() + ".");
        }
    }

    /**
     * Escenario 3: Crea un �rbol taxon�mico con taxa.
     */
    @Test
    public void setupEscenario3() {
        try {
            arbolTaxonomico = new TaxonomicTree("");
            arbolTaxonomico
                    .addTaxon("Last universal common ancestor", Taxon.DOMAIN, "Domain 1");
            arbolTaxonomico
                    .addTaxon("Last universal common ancestor", Taxon.DOMAIN, "Domain 2");
            arbolTaxonomico.addTaxon("Domain 1", Taxon.KINGDOM, "Kingdom 1");
            arbolTaxonomico.addTaxon("Domain 2", Taxon.KINGDOM, "Kingdom 2");
            arbolTaxonomico.addTaxon("Domain 2", Taxon.KINGDOM, "Kingdom 3");
            arbolTaxonomico.addTaxon("Kingdom 2", Taxon.PHYLUM, "Phylum 1");
            arbolTaxonomico.addTaxon("Kingdom 2", Taxon.PHYLUM, "Phylum 2");
            arbolTaxonomico.addTaxon("Kingdom 3", Taxon.PHYLUM, "Phylum 3");
            arbolTaxonomico.addTaxon("Phylum 1", Taxon.CLASS, "Class 1");
            arbolTaxonomico.addTaxon("Phylum 1", Taxon.CLASS, "Class 2");
            arbolTaxonomico.addTaxon("Phylum 2", Taxon.CLASS, "Class 3");
            arbolTaxonomico.addTaxon("Class 1", Taxon.ORDER, "Order 1");
            arbolTaxonomico.addTaxon("Class 2", Taxon.ORDER, "Order 2");
            arbolTaxonomico.addTaxon("Class 3", Taxon.ORDER, "Order 3");
            arbolTaxonomico.addTaxon("Order 1", Taxon.FAMILY, "Family 1");
            arbolTaxonomico.addTaxon("Family 1", Taxon.GENUS, "Genus 1");
            arbolTaxonomico.addTaxon("Genus 1", Taxon.SPECIES, "Species 1");
        } catch (Exception e) {
            fail("No se deber�a generar el error " + e.getMessage());
        }
    }

    /**
     * Escenario 4: Crea un �rbol taxon�mico con taxa y living beings.
     */
    @Test
    public void setupEscenario4() {
        try {
            arbolTaxonomico = new TaxonomicTree("");
            arbolTaxonomico
                    .addTaxon("Last universal common ancestor", Taxon.DOMAIN, "Domain 1");
            arbolTaxonomico
                    .addTaxon("Last universal common ancestor", Taxon.DOMAIN, "Domain 2");
            arbolTaxonomico.addTaxon("Domain 1", Taxon.KINGDOM, "Kingdom 1");
            arbolTaxonomico.addTaxon("Domain 2", Taxon.KINGDOM, "Kingdom 2");
            arbolTaxonomico.addTaxon("Domain 2", Taxon.KINGDOM, "Kingdom 3");
            arbolTaxonomico.addTaxon("Kingdom 2", Taxon.PHYLUM, "Phylum 1");
            arbolTaxonomico.addTaxon("Kingdom 2", Taxon.PHYLUM, "Phylum 2");
            arbolTaxonomico.addTaxon("Kingdom 3", Taxon.PHYLUM, "Phylum 3");
            arbolTaxonomico.addTaxon("Phylum 1", Taxon.CLASS, "Class 1");
            arbolTaxonomico.addTaxon("Phylum 1", Taxon.CLASS, "Class 2");
            arbolTaxonomico.addTaxon("Phylum 2", Taxon.CLASS, "Class 3");
            arbolTaxonomico.addTaxon("Class 1", Taxon.ORDER, "Order 1");
            arbolTaxonomico.addTaxon("Class 2", Taxon.ORDER, "Order 2");
            arbolTaxonomico.addTaxon("Class 3", Taxon.ORDER, "Order 3");
            arbolTaxonomico.addTaxon("Order 1", Taxon.FAMILY, "Family 1");
            arbolTaxonomico.addTaxon("Order 1", Taxon.FAMILY, "Family 2");
            arbolTaxonomico.addTaxon("Order 3", Taxon.FAMILY, "Family 3");
            arbolTaxonomico.addTaxon("Family 2", Taxon.GENUS, "Genus 1");
            arbolTaxonomico.addTaxon("Family 3", Taxon.GENUS, "Genus 2");
            arbolTaxonomico.addTaxon("Genus 1", Taxon.SPECIES, "Species 1");
            arbolTaxonomico.addTaxon("Genus 1", Taxon.SPECIES, "Species 2");
            arbolTaxonomico.addTaxon("Genus 1", Taxon.SPECIES, "Species 3");
            arbolTaxonomico.addTaxon("Genus 2", Taxon.SPECIES, "Species 4");
            arbolTaxonomico.addTaxon("Genus 2", Taxon.SPECIES, "Species 5");
            arbolTaxonomico
                    .addLivingBeing("Species 1", "Com�n 1", "Cient�fico 1", "Caracter�sticas",
                                    "Image");
            arbolTaxonomico
                    .addLivingBeing("Species 2", "Com�n 2", "Cient�fico 2", "Caracter�sticas",
                                    "Image");
            arbolTaxonomico
                    .addLivingBeing("Species 4", "Com�n 3", "Cient�fico 3", "Caracter�sticas",
                                    "Image");
            arbolTaxonomico
                    .addLivingBeing("Species 5", "Com�n 4", "Cient�fico 4", "Caracter�sticas",
                                    "Image");
        } catch (Exception e) {
            fail("No se deber�a generar el error " + e.getMessage());
        }
    }

    /**
     * Prueba 1: Se encarga of verificar el m�todo constructor of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * TaxonomicTree <br>
     * getTaxonRoot <br>
     * getNumTaxa <br>
     * getNumLivingBeings <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se construye un �rbol taxon�mico vac�o correctamente. <br>
     */
    @Test
    public void testTreeTaxonomico1() {
        setupEscenario1();
        assertNotNull(
                "La categor�a ra�z no fue inicializada. El root taxon of the �rbol no puede ser nula.",
                arbolTaxonomico.getTaxonRoot());
    }

    /**
     * Prueba 2: Se encarga of verificar el m�todo constructor of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * TaxonomicTree <br>
     * getTaxonRoot <br>
     * getNumTaxa <br>
     * getNumLivingBeings <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se construye un �rbol taxon�mico a partir of un archivo.
     */
    @Test
    public void testTreeTaxonomico2() {
        setupEscenario2();
        assertEquals("El �rbol no fue inicializado correctamente. Existen 36 taxa en el �rbol.",
                     36, arbolTaxonomico.getNumTaxa());
        assertEquals(
                "El �rbol no fue inicializado correctamente. Existen 4 living beings en el �rbol.", 2,
                arbolTaxonomico.getNumLivingBeings());
    }

    /**
     * Prueba 3: Se encarga of verificar el m�todo addTaxon of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * addTaxon <br>
     * getTaxonRoot <br>
     * getNumTaxa <br>
     * <b> Casos of prueba: </b>
     * 1. Se agrega correctamente un sub-taxon al taxon actual. <br>
     * 2. Se agrega correctamente un taxon nieto.
     */
    @Test
    public void testAddTaxon() {
        setupEscenario1();
        try {
            // Caso of prueba 1.
            arbolTaxonomico
                    .addTaxon("Last universal common ancestor", Taxon.DOMAIN, "Domain 1");
            Taxon root = arbolTaxonomico.getTaxonRoot();
            assertEquals(
                    "No se agreg� correctamente el taxon. El root taxon debe tenede 1 subtaxon.", 1,
                    root.getSubTaxa().size());

            // Caso of prueba 2.
            arbolTaxonomico.addTaxon("Domain 1", Taxon.KINGDOM, "Kingdom 1");
            assertEquals("No se agreg� correctamente el taxon. Deben haber 3 taxa en el �rbol.",
                         3, arbolTaxonomico.getNumTaxa());
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Prueba 4: Se encarga of verificar el m�todo addTaxon of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * addTaxon <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se intentto be added un taxon existente.
     */
    @Test
    public void testAddTaxonError() {
        setupEscenario3();
        try {
            arbolTaxonomico
                    .addTaxon("�ltimo antepasado com�n universal", Taxon.DOMAIN, "Domain 1");
            fail("No deber�to be added el taxon pues ya existe un taxon con ese name.");
        } catch (Exception e) {
            // Debe generar excepci�n.
        }
    }

    /**
     * Prueba 5: Se encarga of verificar el m�todo eliminateTaxon of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * eliminateTaxon <br>
     * getNumTaxa <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se intenta eliminar exitosamente un taxon. <br>
     * 2. Se intenta eliminar un taxon que no existe.
     */
    @Test
    public void testEliminateTaxon() {
        setupEscenario3();
        arbolTaxonomico.eliminateTaxon(Taxon.CLASS, "Class 1");
        assertEquals(
                "El taxon no fue eliminado correctamente. Deben existir 13 taxa en el �rbol.",
                13, arbolTaxonomico.getNumTaxa());

        arbolTaxonomico.eliminateTaxon(Taxon.KINGDOM, "Kingdom 4");
        assertEquals("El taxon no deber�a ser eliminado. Deben existir 13 taxa en el �rbol.", 13,
                     arbolTaxonomico.getNumTaxa());
    }

    /**
     * Prueba 6: Se encarga of verificar el m�todo addLivingBeing of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * addLivingBeing <br>
     * getNumLivingBeings <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Agrega correctamente al living being.
     */
    @Test
    public void testAddLivingBeing() {
        setupEscenario3();
        try {
            arbolTaxonomico.addLivingBeing("Species 1", "Com�n", "Cient�fico", "Caracter�sticas",
                                           "Image");
            assertEquals(
                    "No se agreg� correctamente el living being. Debe haber 1 living being en el �rbol.", 1,
                    arbolTaxonomico.getNumLivingBeings());
        } catch (Exception e) {
            fail("No se deber�a generar el error: " + e.getMessage() + ".");
        }
    }

    /**
     * Prueba 7: Se encarga of verificar el m�todo addLivingBeing of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * addLivingBeing <br>
     * getNumLivingBeings <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Ya existe un living being with the mismo name cient�fico.
     */
    @Test
    public void testAddLivingBeingError() {
        setupEscenario4();

        try {
            arbolTaxonomico.addLivingBeing("Species 1", "Com�n", "Cient�fico 3", "Caracter�sticas",
                                           "Image");
            fail("No deber�to be added el living being pues ya existe un living being with the mismo name "
                         + "cient�fico.");
        } catch (Exception e) {
            // Debe generar excepci�n.
        }
    }

    /**
     * Prueba 8: Se encarga of verificar el m�todo getNumTaxa of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * getNumTaxa <br>
     * addTaxon <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se calcula correctamente el number of taxa en el �rbol.
     */
    @Test
    public void testDarNumTaxa() {
        setupEscenario3();
        int numTaxa = arbolTaxonomico.getNumTaxa();
        assertEquals(
                "El number of taxa no es calculado correctamente. Debe haber 18 taxa en el "
                        + "�rbol", 18, numTaxa);

        try {
            arbolTaxonomico.addTaxon("Kingdom 3", Taxon.PHYLUM, "Phylum 4");
            numTaxa = arbolTaxonomico.getNumTaxa();
            assertEquals(
                    "El number of taxa no es calculado correctamente. Debe haber 19 taxa en"
                            + " el �rbol", 19, numTaxa);

            arbolTaxonomico.addTaxon("Kingdom 3", Taxon.PHYLUM, "Phylum 5");
            arbolTaxonomico.addTaxon("Kingdom 3", Taxon.PHYLUM, "Phylum 6");
            numTaxa = arbolTaxonomico.getNumTaxa();
            assertEquals(
                    "El number of taxa no es calculado correctamente. Debe haber 21 taxa en"
                            + " el �rbol", 21, numTaxa);
        } catch (Exception e) {
            fail("No deber�a generar excepci�n.");
        }
    }

    /**
     * Prueba 9: Se encarga of verificar el m�todo getNumLivingBeings of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * getNumLivingBeings <br>
     * addLivingBeing <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se calcula correctamente el number of living beings en el �rbol.
     */
    @Test
    public void testDarNumLivingBeings() {
        setupEscenario4();
        int numLivingBeings = arbolTaxonomico.getNumLivingBeings();
        assertEquals(
                "El number of living beings no es calculado correctamente. Debe haber 4 living beings"
                        + " en el �rbol.", 4, numLivingBeings);

        try {
            arbolTaxonomico
                    .addLivingBeing("Species 3", "Com�n 5", "Cient�fico 5", "Caracter�sticas",
                                    "Image");
            numLivingBeings = arbolTaxonomico.getNumLivingBeings();
            assertEquals(
                    "El number of living beings no es calculado correctamente. Debe haber 5 seres "
                            + "vivos en el �rbol.", 5, numLivingBeings);

        } catch (Exception e) {
            fail("No deber�a generar excepci�n.");
        }
    }

    /**
     * Prueba 10: Se encarga of verificar el m�todo getLivingBeings of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * getLivingBeings <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Returns the list of living beings of un subtree que tiene living beings. <br>
     * 2. Returns the list of living beings of un subtree que no tiene living beings.
     */
    @Test
    public void testDarLivingBeings() {
        setupEscenario4();

        // Caso of prueba 1.
        ArrayList<LivingBeing> seresVivos = arbolTaxonomico.getLivingBeings(Taxon.FAMILY, "Family 2");
        assertEquals(
                "El number of living beings encontrados no es correcto. Debe haber 2 living beings en"
                        + " la list.", 2, seresVivos.size());

        // Caso of prueba 2.
        seresVivos = arbolTaxonomico.getLivingBeings(Taxon.ORDER, "Order 2");
        assertEquals(
                "El number of living beings encontrados no es correcto. No debe haber living beings "
                        + "en la list.", 0, seresVivos.size());
    }

    /**
     * Prueba 11: Se encarga of verificar el m�todo getTaxaLevel of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * getTaxaLevel <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Returns the list of taxa of un level que existe en el �rbol taxon�mico. <br>
     * 2. Returns the list of taxa of un level que no existe en el �rbol taxon�mico.
     */
    @Test
    public void testDarTaxaLevel() {
        // Caso of prueba 1.
        setupEscenario3();
        ArrayList<String> taxa = arbolTaxonomico.getTaxaLevel(Taxon.KINGDOM);
        assertEquals(
                "El number of taxa encontrados no es correcto. Hay 3 taxa of level Kingdom.", 3,
                taxa.size());

        taxa = arbolTaxonomico.getTaxaLevel(Taxon.GENUS);
        assertEquals("El number of taxa encontrados no es correcto. Hay 1 taxon of level Genus.",
                     1, taxa.size());

        // Caso of prueba 2.
        setupEscenario1();
        taxa = arbolTaxonomico.getTaxaLevel(Taxon.PHYLUM);
        assertEquals(
                "El number of taxa encontrados no es correcto. No debe haber taxa of level "
                        + "Phylum.", 0, taxa.size());

    }

    /**
     * Prueba 12: se encarga of verificar el m�todo getTaxaPre-order of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * getTaxaPre-order <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Returns the list of taxa en preorden in the taxonomic tree. <br>
     */
    @Test
    public void testDarTaxaPreOrder() {
        setupEscenario3();
        ArrayList<Taxon> taxonList = arbolTaxonomico.getTaxaPreOrder();

        assertEquals("El number of taxa encontrados no es correcto.", 18, taxonList.size());
        assertEquals("Los taxa no fueron ordenados correctamente.",
                     "Last universal common ancestor", (taxonList.get(0)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Domain 1",
                     (taxonList.get(1)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Kingdom 1",
                     (taxonList.get(2)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Domain 2",
                     (taxonList.get(3)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Kingdom 2",
                     (taxonList.get(4)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Phylum 1",
                     (taxonList.get(5)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Class 1",
                     (taxonList.get(6)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Order 1",
                     (taxonList.get(7)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Family 1",
                     (taxonList.get(8)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 1",
                     (taxonList.get(9)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 1",
                     (taxonList.get(10)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Class 2",
                     (taxonList.get(11)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Order 2",
                     (taxonList.get(12)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Phylum 2",
                     (taxonList.get(13)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Class 3",
                     (taxonList.get(14)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Order 3",
                     (taxonList.get(15)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Kingdom 3",
                     (taxonList.get(16)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Phylum 3",
                     (taxonList.get(17)).getName());
    }

    /**
     * Prueba 14: Se encarga of verificar el m�todo getTaxaPostOrder of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * getTaxaPostOrder <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Returns the list of taxa en postorden in the taxonomic tree. <br>
     */
    @Test
    public void testDarTaxaPostOrder() {
        setupEscenario3();
        ArrayList<Taxon> taxonList = arbolTaxonomico.getTaxaPostOrder();

        assertEquals("El number of taxa encontrados no es correcto.", 18, taxonList.size());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Kingdom 1",
                     (taxonList.get(0)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Domain 1",
                     (taxonList.get(1)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Species 1",
                     (taxonList.get(2)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Genus 1",
                     (taxonList.get(3)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Family 1",
                     (taxonList.get(4)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Order 1",
                     (taxonList.get(5)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Class 1",
                     (taxonList.get(6)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Order 2",
                     (taxonList.get(7)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Class 2",
                     (taxonList.get(8)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Phylum 1",
                     (taxonList.get(9)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Order 3",
                     (taxonList.get(10)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Class 3",
                     (taxonList.get(11)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Phylum 2",
                     (taxonList.get(12)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Kingdom 2",
                     (taxonList.get(13)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Phylum 3",
                     (taxonList.get(14)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Kingdom 3",
                     (taxonList.get(15)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.", "Domain 2",
                     (taxonList.get(16)).getName());
        assertEquals("Los taxa no fueron ordenados correctamente.",
                     "Last universal common ancestor", (taxonList.get(17)).getName());
    }

    /**
     * Prueba 15: Se encarga of verificar el m�todo save of la clase. <br>
     * <b> Methods a probar: </b> <br>
     * save <br>
     * TaxonomicTree <br>
     * getNumTaxa <br>
     * getNumLivingBeings <br>
     * <b> Casos of prueba: </b> <br>
     * 1. Se guarda correctamente el estado of the world. <br>
     */
    @Test
    public void testSave() {
        try {
            setupEscenario4();
            String ruta = "./test/data/testSave.data";
            arbolTaxonomico.save(ruta);

            TaxonomicTree newItemTree = new TaxonomicTree(ruta);
            assertEquals(
                    "La informaci�n no fue guardada correctamente. Debe haber 18 taxa en el "
                            + "�rbol.",
                    25, newItemTree.getNumTaxa());
            assertEquals(
                    "La informaci�n no fue guardada correctamente. Debe haber 4 living beings en el"
                            + " �rbol.",
                    4, newItemTree.getNumLivingBeings());
        } catch (Exception e) {
            fail("No deber�a generar la excepci�n" + e.getMessage() + ".");
        }
    }
}