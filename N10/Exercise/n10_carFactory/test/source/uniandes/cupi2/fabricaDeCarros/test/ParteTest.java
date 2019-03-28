/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: carFactory
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.carFactory.test;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import uniandes.cupi2.carFactory.world.*;


/**
 * Clase usada para verificar que los m�todos de la clase Part est�n correctamente implementados.
 */
public class ParteTest {

    // -------------------------------------------------------------
    // Attributes
    // -------------------------------------------------------------

    /**
     * La parte donde se har�n las pruebas.
     */
    private IPart parte;

    // -------------------------------------------------------------
    // Methods
    // -------------------------------------------------------------

    /**
     * <b>Escenario 1 :</b> Construye una nueva parte de tipo TruckHood.
     */
    @Before
    public void setupEscenario1() {
        parte = new TruckHood(50, 200, Color.green);

    }

    /**
     * <b>Escenario 2 :</b>Construye una nueva parte de tipo LightningRod.
     */
    public void setupEscenario2() {
        parte = new LightningRod(350, 221, Color.orange);
    }

    /**
     * <b>Escenario 3 :</b> Construye una nueva parte de tipo Wheel2.
     */
    public void setupEscenario3() {
        parte = new LuxuryRims(30, 452, Color.yellow);
    }

    /**
     * Prueba 1: Prueba el m�todo constructor de la clase TruckHood a partir de un archivo. <br>
     * <b>Methods a probar:</b> <br>
     * TruckHood<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * getHeight<br>
     * getWidth <br>
     * <b> Caso de prueba 1: <b><br>
     * Se construye un chasis leyendo un archivo y se verifica que se haya creado, y que sus
     * atributos sean los correctos.
     */
    @Test
    public void testChasis1() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./test/data/testChasis.dat"));

            br.readLine(); // Tipo

            parte = new TruckHood(br);
            // Prueba de constructor en atributo tipo.
            assertEquals(
                    "La parte se carg� incorrectamente, el tipo de la parte no es el esperado.",
                    "TruckHood", parte.getType());
            // Prueba de constructor en atributo x.
            assertEquals(
                    "La parte se carg� incorrectamente, la coordenada 'x' de la parte no es la "
                            + "esperada.",
                    0, parte.getX());
            // Prueba de constructor en atributo y.
            assertEquals(
                    "La parte se carg� incorrectamente, la coordenada 'y' de la parte no es la "
                            + "esperada.",
                    290, parte.getY());
            // Prueba de constructor en atributo color.
            assertEquals(
                    "La parte se carg� incorrectamente, el color de la parte no es la esperado.",
                    new Color(-3620889), parte.getColor());
            // Prueba de constructor en atributo alto.
            assertEquals("La parte se cre� incorrectamente, el alto de la parte no es la eperado.",
                         200, parte.getHeight());
            // Prueba de constructor en atributo ancho.
            assertEquals(
                    "La parte se cre� incorrectamente, el ancho de la parte no es la esperado.",
                    320, parte.getWidth());

            br.close();
        } catch (FileNotFoundException e) {
            fail("No se debe generar el error: " + e.getMessage());
        } catch (Exception e1) {
            fail("No se debe generar el error: " + e1.getMessage());
        }
    }

    /**
     * Prueba 2: Prueba el m�todo constructor de la clase TruckHood. <br>
     * <b>Methods a probar:</b> <br>
     * TruckHood<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * getHeight<br>
     * getWidth <br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye un chasis se verifica que se haya creado, y que sus atributos sean los
     * correctos.
     */
    @Test
    public void testChasis2() {

        // Prueba de constructor en atributo tipo.
        assertEquals("La parte se cre� incorrectamente, el tipo de la parte no es el esperado.",
                     "TruckHood", parte.getType());
        // Prueba de constructor en atributo x.
        assertEquals(
                "La parte se cre� incorrectamente, la coordenada 'x' de la parte no es la "
                        + "esperada.",
                50, parte.getX());
        // Prueba de constructor en atributo y.
        assertEquals(
                "La parte se cre� incorrectamente, la coordenada 'y' de la parte no es la "
                        + "esperada.",
                200, parte.getY());
        // Prueba de constructor en atributo color.
        assertEquals("La parte se cre� incorrectamente, el color de la parte no es el esperado.",
                     Color.green, parte.getColor());
        // Prueba de constructor en atributo alto.
        assertEquals("La parte se cre� incorrectamente, el alto de la parte no es el esperado.",
                     200, parte.getHeight());
        // Prueba de constructor en atributo ancho.
        assertEquals("La parte se cre� incorrectamente, el ancho de la parte no es el esperado.",
                     320, parte.getWidth());
    }

    /**
     * Prueba 3: Prueba el m�todo constructor de la clase LightningRod a partir de un archivo. <br>
     * <b>Methods a probar:</b> <br>
     * LightningRod<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * getHeight<br>
     * getWidth <br>
     * <b> Caso de prueba 1: <b><br>
     * Se construye un est�ncil LightningRod leyendo un archivo y se verifica que se haya creado, y que
     * sus atributos sean los correctos.
     */
    @Test
    public void testEstencil1() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./test/data/testStencil.dat"));

            br.readLine(); // Tipo

            parte = new LightningRod(br);
            // Prueba de constructor en atributo tipo.
            assertEquals("La parte se carg� incorrectamente, el tipo de la parte no es el esperado",
                         "LightningRod", parte.getType());
            // Prueba de constructor en atributo x.
            assertEquals(
                    "La parte se carg� incorrectamente, la coordenada 'x' de la parte no es la "
                            + "esperada.",
                    parte.getX(), 9);
            // Prueba de constructor en atributo y.
            assertEquals(
                    "La parte se carg� incorrectamente, la coordenada 'y' de la parte no es la "
                            + "esperada.",
                    parte.getY(), 376);
            // Prueba de constructor en atributo color.
            assertEquals(
                    "La parte se carg� incorrectamente, el color de la parte no es el esperado.",
                    new Color(-3620889), parte.getColor());
            // Prueba de constructor en atributo alto.
            assertEquals("La parte se cre� incorrectamente, el alto de la parte no es el esperado.",
                         50, parte.getHeight());
            // Prueba de constructor en atributo ancho.
            assertEquals(
                    "La parte se cre� incorrectamente, el ancho de la parte no es el esperado.",
                    100, parte.getWidth());

            br.close();
        } catch (FileNotFoundException e) {
            fail("No se debe generar el error: " + e.getMessage());
        } catch (Exception e1) {
            fail("No se debe generar el error: " + e1.getMessage());
        }
    }

    /**
     * Prueba 4: Prueba el m�todo constructor de la clase LightningRod. <br>
     * <b>Methods a probar:</b> <br>
     * LightningRod<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * getHeight<br>
     * getWidth <br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye un est�ncil LightningRod se verifica que se haya creado, y que sus atributos sean los
     * correctos.
     */
    @Test
    public void testEstencil2() {
        setupEscenario2();
        // Prueba de constructor en atributo tipo.
        assertEquals("La parte se cre� incorrectamente, el tipo de la parte no es el esperado.",
                     "LightningRod", parte.getType());
        // Prueba de constructor en atributo x.
        assertEquals(
                "La parte se cre� incorrectamente, la coordenada 'x' de la parte no es la "
                        + "esperada.",
                350, parte.getX());
        // Prueba de constructor en atributo y.
        assertEquals(
                "La parte se cre� incorrectamente, la coordenada 'y' de la parte no es la "
                        + "esperada.",
                221, parte.getY());
        // Prueba de constructor en atributo color.
        assertEquals("La parte se cre� incorrectamente, el color de la parte no es el esperado.",
                     Color.orange, parte.getColor());
        // Prueba de constructor en atributo alto.
        assertEquals("La parte se cre� incorrectamente, el alto de la parte no es el esperado.", 50,
                     parte.getHeight());
        // Prueba de constructor en atributo ancho.
        assertEquals("La parte se cre� incorrectamente, el ancho de la parte no es el esperado.",
                     100, parte.getWidth());
    }

    /**
     * Prueba 5: Prueba el m�todo constructor de la clase LuxuryRims a partir de un archivo. <br>
     * <b>Methods a probar:</b> <br>
     * LuxuryRims<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * getHeight<br>
     * getWidth <br>
     * <b> Caso de prueba 1: <b><br>
     * Se construye una llanta LuxuryRims leyendo un archivo y se verifica que se haya creado, y
     * que sus atributos sean los correctos.
     */
    @Test
    public void testWheel1() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./test/data/testLlanta.dat"));

            br.readLine(); // Tipo

            parte = new LuxuryRims(br);
            // Prueba de constructor en atributo tipo.
            assertEquals("La parte se cre� incorrectamente, el tipo de la parte no es el esperado.",
                         "LuxuryRims", parte.getType());
            // Prueba de constructor en atributo x.
            assertEquals(
                    "La parte se cre� incorrectamente, la coordenada 'x' de la parte no es la "
                            + "esperada.",
                    parte.getX(), 50);
            // Prueba de constructor en atributo y.
            assertEquals(
                    "La parte se cre� incorrectamente, la coordenada 'y' de la parte no es la "
                            + "esperada.",
                    parte.getY(), 285);
            // Prueba de constructor en atributo color.
            assertEquals(
                    "La parte se carg� incorrectamente, el color de la parte no es el esperado.",
                    new Color(-3620889), parte.getColor());
            // Prueba de constructor en atributo alto.
            assertEquals("La parte se cre� incorrectamente, el alto de la parte no es el esperado.",
                         85, parte.getHeight());
            // Prueba de constructor en atributo ancho.
            assertEquals(
                    "La parte se cre� incorrectamente, el ancho de la parte no es el esperado.", 85,
                    parte.getWidth());

            br.close();
        } catch (FileNotFoundException e) {
            fail("No se debe generar el error: " + e.getMessage());
        } catch (Exception e1) {
            fail("No se debe generar el error: " + e1.getMessage());
        }
    }

    /**
     * Prueba 6: Prueba el m�todo constructor de la clase LuxuryRims. <br>
     * <b>Methods a probar:</b> <br>
     * LuxuryRims<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * getHeight<br>
     * getWidth <br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye una llanta LuxuryRims se verifica que se haya creado, y que sus atributos
     * sean los correctos.
     */
    @Test
    public void testWheel2() {
        setupEscenario3();
        // Prueba de constructor en atributo tipo.
        assertEquals("La parte se cre� incorrectamente, el tipo de la parte no es el esperado.",
                     "LuxuryRims", parte.getType());
        // Prueba de constructor en atributo x.
        assertEquals(
                "La parte se cre� incorrectamente, la coordenada 'x' de la parte no es la "
                        + "esperada.",
                30, parte.getX());
        // Prueba de constructor en atributo y.
        assertEquals(
                "La parte se cre� incorrectamente, la coordenada 'y' de la parte no es la "
                        + "esperada.",
                452, parte.getY());
        // Prueba de constructor en atributo color.
        assertEquals("La parte se cre� incorrectamente, el color de la parte no es el esperado.",
                     Color.yellow, parte.getColor());
        // Prueba de constructor en atributo alto.
        assertEquals("La parte se cre� incorrectamente, el alto de la parte no es el esperado.", 85,
                     parte.getHeight());
        // Prueba de constructor en atributo ancho.
        assertEquals("La parte se cre� incorrectamente, el ancho de la parte no es el esperado.",
                     85, parte.getWidth());
    }

    /**
     * Prueba 9: Prueba el m�todo pointIsInside. <br>
     * <b>Methods a probar:</b> <br>
     * pointIsInside<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye una parte, se consultan sobre 2 puntos que est�n dentro de la parte, y se
     * verifica que la parte los reconozca.<br>
     * <b> Caso de prueba 2 : <b><br>
     * Se construye una parte, se consultan sobre 2 puntos que no est�n dentro de la parte, y se
     * verifica que la parte no los reconozca.<br>
     */
    @Test
    public void testEstaDentro() {


        // Prueba del punto cuando est� dentro de la figura
        assertTrue("El punto debe estar en la figura.", parte.pointIsInside(60, 350));
        assertTrue("El punto debe estar en la figura.", parte.pointIsInside(180, 370));
        // Prueba del punto cuando no est� en la figura.
        assertFalse("El punto no debe estar en la figura.", parte.pointIsInside(49, 250));
        assertFalse("El punto no debe estar en la figura.", parte.pointIsInside(80, 418));
    }

    /**
     * Prueba 10: Prueba el m�todo changeX. <br>
     * <b>Methods a probar:</b> <br>
     * changeX<br>
     * getX<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye una parte, se cambia su coordenada 'x' y luego se consulta para verificar el
     * cambio.<br>
     */
    @Test
    public void testCambiarX() {

        parte.changeX(400);
        assertEquals("No hizo el cambio de la coordenada 'x' correctamente", 400, parte.getX());

        parte.changeX(50);
        assertEquals("No hizo el cambio de la coordenada 'x' correctamente", 50, parte.getX());
    }

    /**
     * Prueba 11: Prueba el m�todo changeY. <br>
     * <b>Methods a probar:</b> <br>
     * changeY<br>
     * getY<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye una parte, se cambia su coordenada 'y' y luego se consulta para verificar el
     * cambio.<br>
     */
    @Test
    public void testCambiarY() {

        parte.changeY(400);
        assertEquals("No hizo el cambio de la coordenada 'y' correctamente", 400, parte.getY());

        parte.changeY(50);
        assertEquals("No hizo el cambio de la coordenada 'y' correctamente", 50, parte.getY());
    }

    /**
     * Prueba 12: Prueba el m�todo changeHeight. <br>
     * <b>Methods a probar:</b> <br>
     * changeHeight<br>
     * getHeight<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye una parte, se cambia su coordenada alto y luego se consulta para verificar el
     * cambio.<br>
     */
    @Test
    public void testCambiarAlto() {

        parte.changeHeight(400);
        assertEquals("No hizo el cambio del alto de la parte correctamente", 400,
                     parte.getHeight());

        parte.changeHeight(50);
        assertEquals("No hizo el cambio del alto de la parte correctamente", 50, parte.getHeight());
    }

    /**
     * Prueba 13: Prueba el m�todo changeWidth. <br>
     * <b>Methods a probar:</b> <br>
     * changeWidth<br>
     * getWidth<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye una parte, se cambia su coordenada ancho y luego se consulta para verificar
     * el cambio.<br>
     */
    @Test
    public void testCambiarAncho() {

        parte.changeWidth(400);
        assertEquals("No hizo el cambio del ancho de la parte correctamente", 400,
                     parte.getWidth());

        parte.changeWidth(50);
        assertEquals("No hizo el cambio del ancho de la parte correctamente", 50, parte.getWidth());
    }

    /**
     * Prueba 14: Prueba el m�todo save para TruckHood. <br>
     * <b>Methods a probar:</b> <br>
     * save<br>
     * TruckHood<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye una parte, se guarda en un archivo. Luego se lee el archivo l�nea por l�nea
     * verificando que la informaci�n guardada correspondiera a la parte.<br>
     * <b> Caso de prueba 2 : <b><br>
     * Se carga una nueva parte con el archivo guardado, y se verifica que los atributos de esta
     * parte con la parte original sean los mismo.
     */
    @Test
    public void testGuardarChasis() {


        PrintWriter out;
        try {
            out = new PrintWriter("./test/data/testChasis2.dat");
            parte.save(out);

            out.close();

            BufferedReader br = new BufferedReader(new FileReader("./test/data/testChasis2.dat"));

            // Prueba del guardado
            assertEquals(
                    "La parte se guard� incorrectamente, el tipo de la parte no corresponde al "
                            + "esperado.",
                    parte.getType(), br.readLine());
            String cors[] = br.readLine().split(";");
            assertEquals(
                    "La parte se guard� incorrectamente, la coordenada 'x' no corresponde a la "
                            + "esperada.",
                    parte.getX(), Integer.parseInt(cors[0]));
            assertEquals(
                    "La parte se guard� incorrectamente, la coordenada 'y' no corresponde a la "
                            + "esperada.",
                    parte.getY(), Integer.parseInt(cors[1]));
            assertEquals("La parte se guard� incorrectamente", parte.getColor(),
                         new Color(Integer.parseInt(br.readLine())));

            br.close();

            br = new BufferedReader(new FileReader("./test/data/testChasis2.dat"));
            br.readLine();

            Part p = new TruckHood(br);

            // Prueba de constructor de TruckHood
            assertEquals(
                    "La parte se carg� incorrectamente, el tipo de la parte no corresponde al "
                            + "esperado.",
                    parte.getType(), p.getType());
            assertEquals(
                    "La parte se carg� incorrectamente, la coordenada 'x' no corresponde a la "
                            + "esperada.",
                    parte.getX(), p.getX());
            assertEquals(
                    "La parte se carg� incorrectamente, la coordenada 'y' no corresponde a la "
                            + "esperada.",
                    parte.getY(), p.getY());
            assertEquals("La parte se carg� incorrectamente, el color no corresponde al esperado.",
                         parte.getColor(), p.getColor());

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            fail("No deber�a generar error: " + e.getMessage());
        }
    }

    /**
     * Prueba 15: Prueba el m�todo save para LightningRod. <br>
     * <b>Methods a probar:</b> <br>
     * save<br>
     * LightningRod<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye una parte, se guarda en un archivo. Luego se lee el archivo l�nea por l�nea
     * verificando que la informaci�n guardada correspondiera a la parte.<br>
     * <b> Caso de prueba 2 : <b><br>
     * Se carga una nueva parte con el archivo guardado, y se verifica que los atributos de esta
     * parte con la parte original sean los mismo.
     */
    @Test
    public void testGuardarStencil() {
        setupEscenario2();

        PrintWriter out;
        try {

            out = new PrintWriter("./test/data/testStencil2.dat");
            parte.save(out);

            out.close();

            BufferedReader br = new BufferedReader(new FileReader("./test/data/testStencil2.dat"));

            // Prueba del guardado
            assertEquals(
                    "La parte se guard� incorrectamente, el tipo de la parte no corresponde al "
                            + "esperado.",
                    parte.getType(), br.readLine());
            String cors[] = br.readLine().split(";");
            assertEquals(
                    "La parte se guard� incorrectamente, la coordenada 'x' no corresponde a la "
                            + "esperada.",
                    parte.getX(), Integer.parseInt(cors[0]));
            assertEquals(
                    "La parte se guard� incorrectamente, la coordenada 'y' no corresponde a la "
                            + "esperada.",
                    parte.getY(), Integer.parseInt(cors[1]));
            assertEquals("La parte se guard� incorrectamente, el color no corresponde al esperado.",
                         parte.getColor(), new Color(Integer.parseInt(br.readLine())));

            br.close();

            br = new BufferedReader(new FileReader("./test/data/testStencil2.dat"));
            br.readLine();

            Part p = new LightningRod(br);

            // Prueba de constructor de LightningRod
            assertEquals(
                    "La parte se carg� incorrectamente, el tipo de la parte no corresponde al "
                            + "esperado.",
                    parte.getType(), p.getType());
            assertEquals(
                    "La parte se carg� incorrectamente, la coordenada 'x' no corresponde a la "
                            + "esperada.",
                    parte.getX(), p.getX());
            assertEquals(
                    "La parte se carg� incorrectamente, la coordenada 'y' no corresponde a la "
                            + "esperada.",
                    parte.getY(), p.getY());
            assertEquals("La parte se carg� incorrectamente, el color no corresponde al esperado.",
                         parte.getColor(), p.getColor());

            br.close();
        } catch (Exception e) {
            fail("No deber�a generar error: " + e.getMessage());
        }
    }

    /**
     * Prueba 16: Prueba el m�todo save para Wheel. <br>
     * <b>Methods a probar:</b> <br>
     * save<br>
     * LightningRod<br>
     * getType<br>
     * getX<br>
     * getY<br>
     * getColor<br>
     * <b> Caso de prueba 1 : <b><br>
     * Se construye una parte, se guarda en un archivo. Luego se lee el archivo l�nea por l�nea
     * verificando que la informaci�n guardada correspondiera a la parte.<br>
     * <b> Caso de prueba 2 : <b><br>
     * Se carga una nueva parte con el archivo guardado, y se verifica que los atributos de esta
     * parte con la parte original sean los mismo.
     */
    @Test
    public void testGuardarWheel() {
        setupEscenario3();

        PrintWriter out;
        try {
            out = new PrintWriter("./test/data/testWheel2.dat");
            parte.save(out);

            out.close();

            BufferedReader br = new BufferedReader(new FileReader("./test/data/testWheel2.dat"));

            // Prueba del guardado
            assertEquals(
                    "La parte se guard� incorrectamente, el tipo de la parte no corresponde al "
                            + "esperado.",
                    parte.getType(), br.readLine());
            String cors[] = br.readLine().split(";");
            assertEquals(
                    "La parte se guard� incorrectamente, la coordenada 'x' no corresponde a la "
                            + "esperada.",
                    parte.getX(), Integer.parseInt(cors[0]));
            assertEquals(
                    "La parte se guard� incorrectamente, la coordenada 'y' no corresponde a la "
                            + "esperada.",
                    parte.getY(), Integer.parseInt(cors[1]));
            assertEquals("La parte se guard� incorrectamente, el color no corresponde al esperado.",
                         parte.getColor(), new Color(Integer.parseInt(br.readLine())));

            br.close();

            br = new BufferedReader(new FileReader("./test/data/testWheel2.dat"));
            br.readLine();

            Part p = new LuxuryRims(br);

            // Prueba de constructor de LuxuryRims
            assertEquals(
                    "La parte se carg� incorrectamente, el tipo de la parte no corresponde al esperado.",
                    parte.getType(), p.getType());
            assertEquals(
                    "La parte se carg� incorrectamente, la coordenada 'x' no corresponde a la esperada.",
                    parte.getX(), p.getX());
            assertEquals(
                    "La parte se carg� incorrectamente, la coordenada 'y' no corresponde a la esperada.",
                    parte.getY(), p.getY());
            assertEquals("La parte se carg� incorrectamente, el color no corresponde al esperado.",
                         parte.getColor(), p.getColor());

            br.close();
        } catch (Exception e) {
            fail("No deber�a generar error: " + e.getMessage());
        }
    }

}