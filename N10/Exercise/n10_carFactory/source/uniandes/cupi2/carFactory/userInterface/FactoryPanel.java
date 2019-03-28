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
package uniandes.cupi2.carFactory.userInterface;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.carFactory.world.IPart;

/**
 * Panel en el que se despliega el lienzo de dibujo.
 */
public class FactoryPanel extends JPanel implements MouseListener, MouseMotionListener {
    // -----------------------------------------------------------------
    // Interface attributes
    // -----------------------------------------------------------------

    /**
     * Principal window of the application.
     */
    private CarFactoryInterface principal;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Construye el panel.<br>
     * <b> post: </b> Se inicializa el panel con la userInterface de f�brica de carros dada por
     * par�metro.
     *
     * @param pPrincipalInterface Principal window of the application. pPrincipalInterface != null.
     */
    public FactoryPanel(CarFactoryInterface pPrincipalInterface) {
        principal = pPrincipalInterface;

        addMouseListener(this);
        addMouseMotionListener(this);

        setDoubleBuffered(true);
        setBorder(new TitledBorder(""));
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Este m�todo se encarga de actualizar la visualizaci�n la f�brica.
     */
    public void actualizar() {
        repaint();
    }

    /**
     * Este es el m�todo que se encarga de actualizar la visualizaci�n de la f�brica.
     *
     * @param pG Superficie del panel. pG!=null.
     */
    public void update(Graphics2D pG) {
        pG.setStroke(new BasicStroke(1));
        pG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        pG.setColor(getBackground());
        pG.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar las figuras
        principal.paintParts(pG);

        IPart sombreada = principal.darSombreado();
        if (sombreada != null) {
            sombreada.paintShadowed(pG);
        }

        IPart seleccionada = principal.darSeleccionada();
        if (seleccionada != null) {
            seleccionada.paintAsSelected(pG);
        }
    }

    /**
     * Este es el m�todo llamado por la m�quina virtual cuando hay que repaint el panel. <br>
     * super.paintComponent( pG ) no sabe paint las figuras, as� que hay que sobrecargar el m�todo.
     *
     * @param pG Superficie del panel. pG !=null.
     */
    public void paintComponent(Graphics pG) {
        super.paintComponent(pG);
        update((Graphics2D) pG);
    }

    /**
     * Este m�todo se llama cuando se hace clic sobre la superficie del panel f�brica. <br>
     * Dependiendo de la opci�n seleccionada se debe agregar una figura a la f�brica o se debe
     * seleccionar una de las figuras existentes. <br>
     *
     * @param pEvent Event del clic sobre el panel mapa. pEvent!= null.
     */
    public void mouseClicked(MouseEvent pEvent) {
        if (pEvent.getButton() == MouseEvent.BUTTON1) {
            String opcion = principal.getSelectedOption();


            if (!opcion.equals(ButtonPanel.NONE) && !opcion.equals(ButtonPanel.ERASE)) {
                int x = pEvent.getX();
                int y = pEvent.getY();

                if (opcion.equals(ButtonPanel.SELECT)) {
                    principal.seleccionar(x, y);
                }
                else {
                    int xReal = x;
                    int yReal = y;

                    principal.cambiarSeleccionada(null);
                    principal.addPart(xReal, yReal);
                }
            }
        }
    }

    /**
     * Este m�todo no se implementa.
     *
     * @param pEvent El evento. pEvent!= null.
     */
    public void mousePressed(MouseEvent pEvent) {
        // No se requiere
    }

    /**
     * Este m�todo no se implementa.
     *
     * @param pEvent El evento. pEvent!= null.
     */
    public void mouseReleased(MouseEvent pEvent) {
        // No se requiere
    }

    /**
     * Este m�todo no se implementa.
     *
     * @param pEvent El evento. pEvent!= null.
     */
    public void mouseEntered(MouseEvent pEvent) {

    }

    /**
     * Este m�todo se llama cuando el mouse sale del �rea del panel.
     *
     * @param pEvent El evento. pEvent!= null.
     */
    public void mouseExited(MouseEvent pEvent) {
        principal.cambiarSombreada(null);
        actualizar();
    }

    /**
     * Este m�todo no se implementa.
     *
     * @param pEvent El evento. pEvent!= null.
     */
    public void mouseDragged(MouseEvent pEvent) {

        }

    /**
     * Este m�todo se llama cuando se mueve el mouse sobre la superficie del panel. <br>
     *
     * @param pEvent Event de movimiento sobre el panel. pEvent!= null.
     */
    public void mouseMoved(MouseEvent pEvent) {

        actualizar();
    }
}
