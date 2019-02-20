/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiSports
 * Autor: Equipo Cupi2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiSports.interfaz;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.cupiSports.world.*;


/**
 * Ventana principal de la aplicaci�n.
 */
public class CupiSportsInterface extends JFrame {
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Ruta al archivo donde se guarda la informaci�n.
     */
    private final static String DATA = "./data/cupiSports.data";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Clase principal del world.
     */
    private CupiSports cupiSports;

    // -----------------------------------------------------------------
    // Attributes de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones.
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del banner.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con los sports.
     */
    private SportsPanel panelSports;

    /**
     * Panel con las opciones.
     */
    private PanelOpciones panelOpciones;

    /**
     * Panel con la informaci�n del deporte seleccionado.
     */
    private SportInfoPanel panelInfoSport;

    /**
     * Panel con la informaci�n of the athlete seleccionado.
     */
    private AthleteInfoPanel panelInfoAthlete;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor de la ventana principal de la aplicaci�n.
     */
    public CupiSportsInterface() {
        try {
            cupiSports = new CupiSports(DATA);

            getContentPane().setLayout(new BorderLayout());
            setSize(800, 700);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setTitle("CupiSports");

            // Creaci�n de los paneles aqu�
            JPanel panelNorte = new JPanel(new BorderLayout());
            panelImagen = new PanelImagen();
            panelNorte.add(panelImagen, BorderLayout.NORTH);
            add(panelNorte, BorderLayout.NORTH);

            JPanel panelCentral = new JPanel(new BorderLayout());
            panelSports = new SportsPanel(this);
            panelCentral.add(panelSports, BorderLayout.NORTH);
            panelInfoSport = new SportInfoPanel(this);
            panelCentral.add(panelInfoSport, BorderLayout.CENTER);
            panelInfoAthlete = new AthleteInfoPanel();
            panelCentral.add(panelInfoAthlete, BorderLayout.EAST);
            add(panelCentral, BorderLayout.CENTER);

            panelExtension = new PanelExtension(this);
            add(panelExtension, BorderLayout.SOUTH);

            panelOpciones = new PanelOpciones(this);
            add(panelOpciones, BorderLayout.WEST);

            setLocationRelativeTo(null);

            actualizarListaSports();
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "CupiSports",
                                          JOptionPane.ERROR_MESSAGE);
        }

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Actualiza el panel panelSports con los sports disponibles.
     */
    public void actualizarListaSports() {
        panelSports.actualizarSports(cupiSports.getSports());
    }

    /**
     * Actualiza la informaci�n que se visualiza de un deporte.
     * @param pSport Sport del cual se va a mostrar la informaci�n.pSport != null.
     */
    public void actualizarInfoSport(Sport pSport) {
        panelInfoSport.actualizarInfo(pSport);
    }

    /**
     * Actualiza la informaci�n que se visualiza of the athlete.
     * @param pAthlete Athlete del cual se va a mostrar la informaci�n. pAthlete != null.
     */
    public void actualizarInfoAthlete(Athlete pAthlete) {
        panelInfoAthlete.actualizarInfo(pAthlete);
    }

    /**
     * Muestra el dialogo para ingresar un deporte.
     */
    public void mostrarAddSportDialog() {
        AddSportDialog dialogo = new AddSportDialog(this);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    /**
     * Agrega un deporte.
     * @param pNameSport Name of the sport. pNameSport != null && pNameSport!= "".
     * @param pRegulatoryEntity Regulatory entity of the sport. pRegulatoryEntity != null &&
     *                          pRegulatoryEntity != "".
     * @param pNumberOfRegisteredAthletes Cantidad de athletes registrados.
     *                                    pNumberOfRegisteredAthletes > 0.
     * @param pImagePath Image path of the sport. pImagePath != null && pRutasImagen != "".
     * @throws ElementExistsException Si ya exists un deporte con el name dado.
     */
    public void addSport(String pNameSport, String pRegulatoryEntity,
                             int pNumberOfRegisteredAthletes, String pImagePath)
            throws ElementExistsException {
        cupiSports.addSport(pNameSport, pRegulatoryEntity, pNumberOfRegisteredAthletes,
                                pImagePath);
        actualizarListaSports();
    }

    /**
     * Muestra el dialogo para add un athlete sobresaliente.
     */
    public void mostrarAddAthleteDialog() {
        if (panelSports.getSportSeleccionado() == null) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado ning�n deporte.",
                                          "Agregar athlete", JOptionPane.ERROR_MESSAGE);
        }
        else {
            AddAthleteDialog dialogo = new AddAthleteDialog(this);
            dialogo.setLocationRelativeTo(this);
            dialogo.setVisible(true);
        }
    }

    /**
     * Agrega un athlete al deporte seleccionado.
     * @param pNameAthlete Name of the athlete sobresaliente. pNameAthlete != null &&
     *                     pNameAthlete != "".
     * @param pAge Age of the athlete. pAge > 0.
     * @param pPlaceOfResidency Athlete's place of residence.  pPlaceOfResidency != null &&
     *                          pPlaceOfResidency != "".
     * @param pAmountOfTrophies Cantidad de trofeos ganados of the athlete. pAmountOfTrophies >= 0.
     * @param pImagePathAthlete Image path of the athlete. pImagePathAthlete != null &&
     *                          pImagePathAthlete != "".
     * @throws ElementExistsException Si en el deporte ya exists un athlete con el name del que
     * se quiere add.
     */
    public void addOutstandingAthlete(String pNameAthlete, int pAge, String pPlaceOfResidency,
                                      int pAmountOfTrophies, String pImagePathAthlete)
            throws ElementExistsException {
        String nameSport = panelSports.getSportSeleccionado().getName();
        cupiSports.addOutstandingAthlete(nameSport, pNameAthlete, pAge, pPlaceOfResidency,
                                         pAmountOfTrophies, pImagePathAthlete);
        actualizarInfoSport(panelSports.getSportSeleccionado());
    }

    /**
     * Elimina el deporte seleccionado.
     */
    public void deleteSport() {
        if (panelSports.getSportSeleccionado() == null) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado ning�n deporte.",
                                          "Eliminar deporte", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int confirmacion = JOptionPane.showConfirmDialog(this, "�Confirma que desea delete "
                                                                     + panelSports.getSportSeleccionado().toString() + "?", "Eliminar deporte",
                                                             JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                cupiSports.deleteSport(panelSports.getSportSeleccionado().getName());
                actualizarListaSports();
            }
        }

    }

    /**
     * Elimina el athlete seleccionado.
     */
    public void deleteAthlete() {
        if (panelSports.getSportSeleccionado() == null) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado ning�n deporte.",
                                          "Eliminar athlete", JOptionPane.ERROR_MESSAGE);
        }
        else if (panelInfoSport.getAthleteSeleccionado() == null) {
            JOptionPane.showMessageDialog(this,
                                          "No ha seleccionado ning�n athlete sobresaliente del "
                                                  + "deporte.",
                                          "Eliminar athlete", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                                                             "�Confirma que desea delete el "
                                                                     + "athlete "
                                                                     + panelInfoSport
                                                                     .getAthleteSeleccionado()
                                                                     .toString() + "?",
                                                             "Eliminar athlete",
                                                             JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                cupiSports.eliminateOutstandingAthlete(panelSports.getSportSeleccionado().getName(),
                                                       panelInfoSport.getAthleteSeleccionado()
                                                                     .getName());
                actualizarInfoSport(panelSports.getSportSeleccionado());
            }
        }

    }

    /**
     * Actualiza la informaci�n de los athletes a partir de un archivo.
     */
    public void actualizarInformacionAthletes() {
        JFileChooser fc = new JFileChooser("./data/actualizacionInformacion");
        fc.setDialogTitle("Actualizar informaci�n athletes");
        int resultado = fc.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();
            if (archivo != null) {
                try {
                    cupiSports.actualizarInformacionAthletes(archivo);
                    JOptionPane.showMessageDialog(this,
                                                  "La informaci�n de los athletes fue actualizada.",
                                                  "Actualizar informaci�n",
                                                  JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(this,
                                                  "Se present� un problema leyendo el archivo:\n"
                                                          + e.getMessage() + ".", "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this,
                                                  "Se present� un problema leyendo el archivo:\n"
                                                          + e.getMessage() + ".", "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                } catch (FileFormatException e) {
                    JOptionPane.showMessageDialog(this,
                                                  "Se present� un problema debido al formato del "
                                                          + "archivo:\n"
                                                          + e.getMessage(), "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }

        }

    }

    /**
     * Escribe el reporte de los trofeos de los athletes detscados.
     * @param pRutaArchivo Ruta al archivo. pRutaArchivo != null && pRutaArchivo != "".
     */
    public void generarReporteTrophies(String pRutaArchivo) {
        try {
            cupiSports.generarReporteTrophies(pRutaArchivo);
            JOptionPane.showMessageDialog(this, "El reporte se gener� correctamente.",
                                          "Generar reporte", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Generar reporte",
                                          JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Este m�todo se encarga de salvar la informaci�n justo antes de cerrar la aplicaci�n.
     * Si se presenta una excepci�n en el proceso de serializaci�n del estado del world, este
     * m�todo debe informar al usuario que ha ocurrido un error y preguntarle si desea salir de la aplicaci�n sin salvar la informaci�n.
     */
    public void dispose() {
        try {
            cupiSports.saveState(DATA);
            super.dispose();
        } catch (Exception e) {
            setVisible(true);
            int respuesta = JOptionPane.showConfirmDialog(this,
                                                          "Problemas salvando la informaci�n de "
                                                                  + "la discotienda:\n"
                                                                  + e.getMessage()
                                                                  + "\n�Quiere cerrar el programa"
                                                                  + " sin salvar?",
                                                          "Error", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                super.dispose();
            }
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     */
    public void reqFuncOpcion1() {
        String resultado = cupiSports.metodo1();
        JOptionPane
                .showMessageDialog(this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * M�todo para la extensi�n 2.
     */
    public void reqFuncOpcion2() {
        String resultado = cupiSports.metodo2();
        JOptionPane
                .showMessageDialog(this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
     * @param args No se utilizan.
     */
    public static void main(String[] args) {
        CupiSportsInterface interfaz = new CupiSportsInterface();
        interfaz.setVisible(true);
    }

}