/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Dibujo.java,v 1.4 2007/04/13 04:29:55 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_paint
 * Autor: Mario S�nchez - 27/09/2005
 * Autor: Jorge Villalobos - 26/09/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.paint.mundo;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Esta clase representa el editor de dibujos <br>
 * <b>inv:</b><br>
 * figuras != null<br>
 * seleccionada = null o seleccionada pertenece a figuras
 */
public class Dibujo {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una lista con las figuras que hacen parte del dibujo, ordenadas de acuerdo con el orden
     * en el que fueron agregadas en el editor. Las figuras se pintan en el
     * mismo orden en el que aparecen aqu�. Todas las figuras implementan la interfaz IFigura
     */
    private List figuras;

    /**
     * Es la figura que est� seleccionada actualmente en el dibujo (si la hay)
     */
    private IFigura seleccionada;

    /**
     * Es el archivo donde se est� salvando actualmente el dibujo
     */
    private String archivo;

    /**
     * Indica si el dibujo ha sido modificado
     */
    private boolean modificado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo editor sin figuras
     */
    public Dibujo() {
        figuras = new ArrayList();
        seleccionada = null;
        archivo = null;
        modificado = false;
        verificarInvariante();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega una figura al dibujo, al final de la lista <br>
     * <b>post: </b> Se agreg� la figura al dibujo al final de la lista, no hay ninguna figura
     * seleccionada, el dibujo ha sido modificado
     * @param f La figura que va a ser agregada - f != null
     */
    public void agregarFigura(IFigura f) {
        figuras.add(f);
        seleccionada = null;
        modificado = true;
        verificarInvariante();

    }

    /**
     * Reinicia el dibujo, eliminando todas las figuras <br>
     * <b>post: </b> No hay figuras en la composici�n, no hay ning�n nombre de archivo
     * seleccionado, el dibujo no ha sido modificado
     */
    public void reiniciar() {
        figuras.clear();
        seleccionada = null;
        archivo = null;
        modificado = false;
        verificarInvariante();
    }

    /**
     * Retorna el nombre del archivo donde se est� guardando la informaci�n de este dibujo.
     * @return archivo. Si no se ha establecido todav�a el nombre del archivo, retorna null.
     */
    public String darNombreArchivo() {
        return archivo;
    }

    /**
     * Pinta la composici�n sobre la superficie proporcionada
     * @param g Es la superficie sobre la que deben quedar las figuras <br>
     *        g != null y g es una superficie limpia (no se va a borrar antes de pintar)
     */
    public void dibujar(Graphics2D g) {
        Iterator iter = figuras.iterator();
        while (iter.hasNext()) {
            IFigura f = (IFigura) iter.next();
            f.dibujar(g, f == seleccionada);
        }
    }

    /**
     * Selecciona la �ltima figura agregada que se encuentre en el punto x,y. Si no hay ninguna
     * figura en esas coordenadas, no deja ninguna figura como seleccionada
     * @param x La coordenada x del punto
     * @param y La coordenada y del punto
     */
    public void hacerClick(int x, int y) {
        seleccionada = null;
        for (int i = figuras.size() - 1; i >= 0 && seleccionada == null; i--) {
            IFigura f = (IFigura) figuras.get(i);
            if (f.estaDentro(x, y))
                seleccionada = f;
        }
    }

    /**
     * Retorna la figura que se encuentra seleccionada o null si no hay ninguna
     * @return La figura seleccionada en el editor
     */
    public IFigura darSeleccionada() {
        return seleccionada;
    }

    /**
     * Elimina la figura seleccionada. Si no hay ninguna figura seleccionada, no hace nada <br>
     * <b>post:</b> La figura seleccionada ha sido borrada, la composici�n ha sido modificada.
     */
    public void eliminarFigura() {
        if (seleccionada != null) {
            figuras.remove(seleccionada);
            modificado = true;
            seleccionada = null;
            verificarInvariante();
        }
    }

    /**
     * Indica si el dibujo ha sido modificado
     * @return True si ha sido modificado, false de lo contrario
     */
    public boolean haSidoModificado() {
        return modificado;
    }

    /**
     * Indica si la composici�n est� vac�a
     * @return True si esta vacio, false de lo contrario
     */
    public boolean estaVacio() {
        return figuras.size() == 0;
    }

    // -----------------------------------------------------------------
    // Persistencia
    // -----------------------------------------------------------------

    /**
     * Salva la composici�n actual en el archivo especificado. <br>
     * <b>post: </b> Se salv� la composici�n en un archivo.
     * @param nombreArchivo Es el nombre del archivo donde se va a salvar la composici�n
     * @throws IOException Se lanza esta excepci�n si hay problemas salvando la composici�n
     */
    public void salvarComo(String nombreArchivo) throws IOException {
        archivo = nombreArchivo;
        salvar();
    }

    /**
     * Salva la composici�n actual en el archivo que se ven�a usando. <br>
     * <b>pre: </b> Hay un archivo para salvar ya establecido. <br>
     * <b>post: </b> Se salv� la composici�n en un archivo.
     * @throws IOException Se lanza esta excepci�n si hay problemas salvando la composici�n
     */
    public void salvar() throws IOException {
        PrintWriter out = new PrintWriter(archivo);
        // Escribe el n�mero de figuras
        out.println(figuras.size());
        Iterator iter = figuras.iterator();
        while (iter.hasNext()) {
            IFigura f = (IFigura) iter.next();
            // Guarda el tipo de la figura en el archivo
            out.println(f.darTipoFigura());
            // Salva la figura en el archivo que se pasa como par�metro
            f.guardar(out);
        }
        out.close();
        // Indica que la composici�n no ha sido modificada
        modificado = false;
    }

    /**
     * Carga una composici�n a partir de un archivo <br>
     * <b>post: </b> Se elimin� la composici�n anterior y se carg� la nueva
     * @param nombreArchivo El archivo de donde se va a cargar la composici�n - archivo != null
     * @throws FormatoInvalidoException Se lanza esta excepci�n si el archivo tiene un formato
     * incorrecto
     * @throws IOException Se lanza esta excepci�n si hay problemas leyendo del archivo
     */
    public void cargar(String nombreArchivo) throws FormatoInvalidoException, IOException {
        reiniciar();
        archivo = nombreArchivo;
        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
        // Lee del archivo el n�mero de figuras presentes
        String numFigs = br.readLine();
        try {
            // Convierte la cadena le�da a un valor num�rico
            int cuantasFiguras = Integer.parseInt(numFigs);
            for (int i = 0; i < cuantasFiguras; i++) {
                // Lee el tipo de la figura que sigue en el archivo
                String tipoFigura = br.readLine();
                if (tipoFigura.equals(Linea.TIPO)) {
                    figuras.add(new Linea(br));
                }
                else if (tipoFigura.equals(Rectangulo.TIPO)) {
                    figuras.add(new Rectangulo(br));
                }
                else if (tipoFigura.equals(Ovalo.TIPO)) {
                    figuras.add(new Ovalo(br));
                }
                else {
                    throw new FormatoInvalidoException(tipoFigura);
                }
            }
            br.close();
        } catch (NumberFormatException nfe) {
            reiniciar();
            throw new FormatoInvalidoException("Valor num�rico inv�lido: " + numFigs);
        } catch (FormatoInvalidoException e) {
            reiniciar();
            throw e;
        } catch (IOException e) {
            reiniciar();
            throw e;
        } finally {
            br.close();
        }
        verificarInvariante();
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * figuras != null <br>
     * seleccionada = null o seleccionada pertenece a figuras
     */
    private void verificarInvariante() {
        assert (figuras != null) : "Lista nula";

        if (seleccionada != null) {
            for (int i = 0; i < figuras.size(); i++) {
                IFigura f = (IFigura) figuras.get(i);
                if (seleccionada == f)
                    return;
            }
            assert (true) : "Selecci�n inv�lida";
        }
    }

    // -----------------------------------------------------------------
    // Puntos de extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para el punto de extensi�n 1
     * @return respuesta1
     */
    public String metodo1() {
        return "Respuesta 1";
    }

    /**
     * M�todo para el punto de extensi�n 2
     * @return respuesta2
     */
    public String metodo2() {
        return "Respuesta 2";
    }

    /**
     * M�todo para el punto de extensi�n 3
     * @return respuesta3
     */
    public String metodo3() {
        return "Respuesta 3";
    }

    /**
     * M�todo para el punto de extensi�n 4
     * @return respuesta4
     */
    public String metodo4() {
        return "Respuesta 4";
    }

    /**
     * M�todo para el punto de extensi�n 5
     * @return respuesta5
     */
    public String metodo5() {
        return "Respuesta 5";
    }
}