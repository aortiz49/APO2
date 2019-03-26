/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazPaint.java,v 1.16 2010/04/19 17:34:58 lr.ruiz114 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_paint
 * Autor: Mario Sánchez - 27/09/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.paint.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.paint.mundo.Dibujo;
import uniandes.cupi2.paint.mundo.FormatoInvalidoException;
import uniandes.cupi2.paint.mundo.IFigura;

/**
 * Es la clase principal de la interfaz
 */
public class InterfazPaint extends JFrame {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     *
     */
    public static final int NINGUNA = 0;

    /**
     *
     */
    public static final int FIGURA = 1;

    /**
     *
     */
    public static final int SELECCIONAR = 4;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el dibujo con la información sobre la composición actual
     */
    private Dibujo dibujo;

    /**
     * Es la ruta hasta el último directorio de donde se cargó o salvó un archivo
     */
    private String ultimoDirectorio;

    /**
     * Es el panel donde se muestran las figuras
     */
    private PanelEditor panelEditor;

    /**
     * Es el panel donde se muestran los botones para controlar la aplicación
     */
    private PanelBotones panelBotones;

    /**
     * Es la barra del menú
     */
    private BarraMenu barra;

    /**
     * Es el panel donde se encuentran los botones para ejecutar los puntos de extensión
     */
    private PanelExtension panelExtension;

    // -----------------------------------------------------------------
    // Atributos de administración
    // -----------------------------------------------------------------

    /**
     * La coordenada x seleccionada
     */
    private int xSeleccionado;

    /**
     * La coordenada y seleccionada
     */
    private int ySeleccionado;
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye e inicializa la interfaz de la aplicación
     */
    public InterfazPaint() {
        dibujo = new Dibujo();
        ultimoDirectorio = "./data";

        barra = new BarraMenu(this);
        setJMenuBar(barra);

        panelEditor = new PanelEditor(this, dibujo);
        panelBotones = new PanelBotones(this);
        panelExtension = new PanelExtension(this);

        add(panelBotones, BorderLayout.WEST);
        add(panelEditor, BorderLayout.CENTER);
        add(panelExtension, BorderLayout.SOUTH);

        setSize(800, 600);
        setTitle("Paint");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        xSeleccionado = -1;
        ySeleccionado = -1;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la coordenada x seleccionada
     * @return La coordenada x seleccionada
     */
    public int getXSeleccionado() {
        return xSeleccionado;
    }

    /**
     * Devuelve la coordenada y seleccionada
     * @return La coordenada y seleccionada
     */
    public int getYSeleccionado() {
        return ySeleccionado;
    }

    /**
     * Agrega una figura del tipo seleccionado en el panel de botones. Los atributos de la figura
     * también se sacan del panel de botones. <br>
     * <b>post: </b> Se agregó la figura y se redibuja la composición.
     * @param figura La figura a agregar
     */
    public void agregarFigura(IFigura figura) {
        dibujo.agregarFigura(figura);
        panelEditor.refrescar();
    }

    /**
     * realiza las acciones dado un click en el Panel Editor
     * @param numClic La cantidad de clicks hechos
     * @param x La coordenada x de donde se hizo click
     * @param y La coordenada y de donde se hizo click
     */
    public void hacerClick(int numClic, int x, int y) {
        int opcion = panelBotones.darFigura();
        if (opcion == InterfazPaint.SELECCIONAR) {
            seleccionar(x, y);
            if (numClic > 1) {
                mostrarVentanaTexto();
            }
        }
        else if (opcion == InterfazPaint.FIGURA) {
            if (xSeleccionado == -1 && ySeleccionado == -1) {
                xSeleccionado = x;
                ySeleccionado = y;
            }
            else {
                panelBotones.agregarFigura(xSeleccionado, ySeleccionado, x, y);
                xSeleccionado = -1;
                ySeleccionado = -1;
            }
        }
        panelEditor.refrescar();
    }

    /**
     * Intenta seleccionar una figura dado un punto. <br>
     * Cada figura debe saber si las coordenadas indicadas sirven para seleccionar esa figura o
     * no. <br>
     * <b>post: </b> Si había una figura que se pudiera seleccionar en esa posición entonces en
     * el atributo "seleccionada" queda una referencia y la figura se pinta como
     * seleccionada.
     * @param x La coordenada x del punto
     * @param y La coordenada y del punto
     */
    public void seleccionar(int x, int y) {
        dibujo.hacerClick(x, y);
        panelEditor.refrescar();
    }

    /**
     * Muestra la ventana de texto utilizada para modificar el texto de la figura seleccionada.
     */
    public void mostrarVentanaTexto() {
        IFigura seleccionada = dibujo.darSeleccionada();
        if (seleccionada != null) {
            String texto = seleccionada.darTexto();
            Font fuente = seleccionada.darTipoLetra();
            DialogoTexto dt = new DialogoTexto(this, texto, fuente);
            dt.setVisible(true);
        }
    }

    /**
     * Cambia el texto de la figura seleccionada. <br>
     * <b>pre: </b>Hay una figura seleccionada <br>
     * <b>post: </b> Se modificó el texto de la figura.
     * @param texto El texto que va a tener la figura
     * @param fuente La fuente del texto de la figura
     */
    public void cambiarTexto(String texto, Font fuente) {
        IFigura seleccionada = dibujo.darSeleccionada();
        seleccionada.cambiarTipoLetra(fuente);
        seleccionada.cambiarTexto(texto);

        panelEditor.refrescar();
    }

    /**
     * Elimina de la composición la figura seleccionada <br>
     * <b>post: </b>Se eliminó de la composición la figura seleccionada
     */
    public void eliminarFiguraSeleccionada() {
        if (dibujo.darSeleccionada() != null) {
            dibujo.eliminarFigura();
            panelEditor.refrescar();
        }
    }

    /**
     * Dibuja las figuras del mundo en el graphics que llega por parámetro
     * @param g2 El graphics de donde se dibujaran las figuras
     */
    public void dibujarFiguras(Graphics2D g2) {
        dibujo.dibujar(g2);
    }

    /**
     * Reinicia la composición después de pedir una confirmación. <br>
     * <b>post: </b> Se reinició la composición
     */
    public void reiniciar() {
        boolean reiniciar = true;
        if (dibujo.haSidoModificado()) {
            reiniciar = pedirConfirmacion();
        }

        if (reiniciar) {
            dibujo.reiniciar();
            panelEditor.refrescar();
        }
    }

    /**
     * Le pide al usuario un archivo para abrir y lo carga en el dibujo. <br>
     * <b>post: </b>Se cargó una composición que estaba guardada
     */
    public void abrir() {
        boolean abrir = true;
        if (dibujo.haSidoModificado()) {
            abrir = pedirConfirmacion();
        }

        if (abrir) {
            JFileChooser fc = new JFileChooser(ultimoDirectorio);
            fc.setDialogTitle("Abrir Composición");
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setMultiSelectionEnabled(false);

            int resultado = fc.showSaveDialog(this);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File seleccionado = fc.getSelectedFile();
                ultimoDirectorio = seleccionado.getParentFile().getAbsolutePath();

                try {
                    dibujo.cargar(seleccionado.getAbsolutePath());
                    panelEditor.refrescar();
                } catch (FormatoInvalidoException e) {
                    JOptionPane.showMessageDialog(this,
                                                  "Hay problemas con el formato del archivo:\n" + e
                                                          .getMessage(), "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Hubo problemas cargando el archivo:\n" + e
                            .getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * Salva la composición en el archivo del que se había cargado o donde se había salvado la
     * última vez. <br>
     * Si se trata de una composición nueva y no se ha salvado entonces se pregunta el nombre del
     * archivo donde se salvará. <br>
     * <b>post: </b> Se salvó la composición.
     */
    public void salvar() {
        String nombreArchivo = dibujo.darNombreArchivo();
        if (nombreArchivo == null) {
            salvarComo();
        }
        else {
            try {
                dibujo.salvar();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Hubo problemas salvando el archivo:\n" + e
                        .getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * Salva la composición en un archivo del cual se le pregunta el nombre al usuario. <br>
     * <b>post: </b> Se salvó la composición en un archivo que se le preguntó al usuario.
     */
    public void salvarComo() {
        JFileChooser fc = new JFileChooser(ultimoDirectorio);
        fc.setDialogTitle("Salvar como");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(false);

        boolean termine = false;

        int resultado = fc.showSaveDialog(this);

        while (!termine) {
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File seleccionado = fc.getSelectedFile();
                ultimoDirectorio = seleccionado.getParentFile().getAbsolutePath();

                int respuesta = JOptionPane.YES_OPTION;

                // Si el archivo ya existe hay que pedir confirmación para
                // sobreescribirlo
                if (seleccionado.exists()) {
                    respuesta = JOptionPane.showConfirmDialog(this,
                                                              "¿Desea sobreescribir el archivo "
                                                                      + "seleccionado?",
                                                              "Sobrescribir",
                                                              JOptionPane.YES_NO_OPTION,
                                                              JOptionPane.INFORMATION_MESSAGE);
                }

                // Si la respuesta fue positiva (o si no fue necesario hacer la
                // pregunta) se graba el archivo
                if (respuesta == JOptionPane.YES_OPTION) {
                    try {
                        dibujo.salvarComo(seleccionado.getAbsolutePath());
                        termine = true;
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(this,
                                                      "Hubo problemas salvando el archivo:\n" + e
                                                              .getMessage(), "Error",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    resultado = fc.showSaveDialog(this);
                }
            }
            else {
                termine = true;
            }
        }
    }

    /**
     * Este método solicita una confirmación para realizar una acción que haría que el trabajo se
     * perdiera. <br>
     * Presenta una ventana con las opciones "Si","No" y "Cancelar". <br>
     * Si se selecciona "Si", entonces se salva el archivo actual. <br>
     * Si se selecciona "No", el archivo no se salva y se retorna true <br>
     * Si se selecciona "Cancelar", el archivo no se salva pero se retorna false para que la
     * acción no se realice.
     * @return Retorna true si la acción se debe realizar; retorna false en caso contrario.
     */
    private boolean pedirConfirmacion() {
        boolean cerrar = true;

        int respuesta = JOptionPane
                .showConfirmDialog(this, "Desea salvar el archivo actual antes de continuar?",
                                   "Salvar", JOptionPane.YES_NO_CANCEL_OPTION,
                                   JOptionPane.INFORMATION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            salvar();
        }

        else if (respuesta == JOptionPane.NO_OPTION) {
            // No hace nada
        }

        else if (respuesta == JOptionPane.CANCEL_OPTION) {
            cerrar = false;
        }

        return cerrar;
    }

    /**
     * Cierra la aplicación, pero antes pregunta si se quiere salvar la composición actual y se
     * salva si el usuario así lo requiere.
     */
    public void dispose() {
        boolean cerrar = true;
        if (dibujo.haSidoModificado()) {
            setVisible(true);
            cerrar = pedirConfirmacion();
        }

        if (cerrar) {
            super.dispose();
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Ejecuta el punto de Extensión 1
     */
    public void reqFuncOpcion1() {
        String resultado = dibujo.metodo1();
        JOptionPane
                .showMessageDialog(this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Ejecuta el punto de Extensión 2
     */
    public void reqFuncOpcion2() {
        String resultado = dibujo.metodo2();
        JOptionPane
                .showMessageDialog(this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Ejecuta el punto de Extensión 3
     */
    public void reqFuncOpcion3() {
        String resultado = dibujo.metodo3();
        JOptionPane
                .showMessageDialog(this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Ejecuta el punto de Extensión 4
     */
    public void reqFuncOpcion4() {
        String resultado = dibujo.metodo4();
        JOptionPane
                .showMessageDialog(this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Ejecuta el punto de Extensión 5
     */
    public void reqFuncOpcion5() {
        String resultado = dibujo.metodo5();
        JOptionPane
                .showMessageDialog(this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación
     * @param args Son los parámetros de la línea de comandos. No se deben usar.
     */
    public static void main(String[] args) {
        InterfazPaint ip = new InterfazPaint();
        ip.setVisible(true);
    }
}