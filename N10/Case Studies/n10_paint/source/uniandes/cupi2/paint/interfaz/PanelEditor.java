/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelEditor.java,v 1.9 2010/04/19 17:34:58 lr.ruiz114 Exp $
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

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import uniandes.cupi2.paint.mundo.*;

/**
 * Este es el panel que maneja la interacción con el ratón y muestra las figuras dibujadas
 */
public class PanelEditor extends JPanel implements MouseListener {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazPaint principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel
     * @param ip Es una referencia a la clase principal de la interfaz
     * @param e Es una referencia al editor de la aplicación
     */
    public PanelEditor(InterfazPaint ip, Dibujo e) {
        principal = ip;

        addMouseListener(this);

        setDoubleBuffered(true);
        setBorder(new TitledBorder(""));
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este método se encarga de actualizar la visualización de la composición
     */
    public void refrescar() {
        repaint();
    }

    /**
     * Este es el método llamado por la máquina virtual cuando hay que repintar el panel<br>.
     * <code>super.paint( g )</code> no sabe pintar las figuras, así que hay que
     * sobrecargar el método.
     * @param g Es la superficie del panel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar las figuras
        principal.dibujarFiguras(g2);

        // dibujar el punto seleccionado si lo hay
        int xSeleccionado = principal.getXSeleccionado();
        int ySeleccionado = principal.getYSeleccionado();
        if (xSeleccionado != -1 && ySeleccionado != -1) {
            g2.setColor(Color.GREEN);
            g2.fillOval(xSeleccionado - 2, ySeleccionado - 2, 3, 3);
        }
    }

    /**
     * Este método se llama cuando se hace click sobre la superficie del editor.
     * @param evento Es el evento del click sobre el editor
     */
    public void mouseClicked(MouseEvent evento) {
        if (evento.getButton() == MouseEvent.BUTTON1) {
            int numClick = evento.getClickCount();
            principal.hacerClick(numClick, evento.getX(), evento.getY());
        }
    }

    /**
     * Este método no se implementa
     * @param arg0 El evento
     */
    public void mousePressed(MouseEvent arg0) {// No se requiere
    }

    /**
     * Este método no se implementa
     * @param arg0 El evento
     */
    public void mouseReleased(MouseEvent arg0) {// No se requiere
    }

    /**
     * Este método no se implementa
     * @param arg0 El evento
     */
    public void mouseEntered(MouseEvent arg0) {// No se requiere
    }

    /**
     * Este método no se implementa
     * @param arg0 El evento
     */
    public void mouseExited(MouseEvent arg0) {// No se requiere
    }
}