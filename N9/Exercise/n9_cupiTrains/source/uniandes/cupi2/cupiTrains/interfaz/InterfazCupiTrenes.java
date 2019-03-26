/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupiTrenes
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTrains.interfaz;

import uniandes.cupi2.cupiTrains.world.*;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;


/**
 * Ventana principal de la aplicaci�n.
 */
public class InterfazCupiTrenes extends JFrame {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta del archivo en donde se va a guardar la informaci�n.
     */
    public static final String RUTA_ARCHIVO = "data/cupiTrains.data";
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo.
     */
    private CupiTrains cupiTrenes;

    /**
     * Tren que se est� visualizando.
     */
    private Train trenActual;

    /**
     * Vag�n que se est� visualizando.
     */
    private Wagon vagonActual;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que contiene la imagen del encabezado.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con las extensiones.
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la informaci�n y acciones.
     */
    private PanelInformacionAcciones panelInformacionAcciones;

    /**
     * Panel que muestra la informaci�n de un tren.
     */
    private PanelTren panelTren;

    /**
     * Panel que muestra la informaci�n de un vag�n.
     */
    private PanelVagon panelVagon;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal.<br>
     * <b>post: </b> Construye la ventana con todos su paneles.
     */
    public InterfazCupiTrenes() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // Crea la clase principal
            cupiTrenes = new CupiTrains(RUTA_ARCHIVO);
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Construye la forma
        setLayout(new BorderLayout());
        setSize(770, 700);
        setTitle("CupiTrenes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen();
        add(panelImagen, BorderLayout.NORTH);

        panelExtension = new PanelExtension(this);
        add(panelExtension, BorderLayout.SOUTH);

        panelInformacionAcciones = new PanelInformacionAcciones(this);
        add(panelInformacionAcciones, BorderLayout.WEST);

        JPanel auxPanelInformacion = new JPanel();
        auxPanelInformacion.setLayout(new BorderLayout());

        panelTren = new PanelTren(this);
        auxPanelInformacion.add(panelTren, BorderLayout.NORTH);

        panelVagon = new PanelVagon(this);
        auxPanelInformacion.add(panelVagon, BorderLayout.CENTER);

        add(auxPanelInformacion, BorderLayout.CENTER);

        trenActual = cupiTrenes.getFirstTrain();
        if (trenActual != null) {
            vagonActual = trenActual.getFirstWagon();
        }
        actualizarPaneles();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la lista de ids de todos los trenes.
     *
     * @return La lista de ids de los trenes.
     */
    public ArrayList darIdsTrenes() {
        return cupiTrenes.getTrainIDs();
    }

    /**
     * Retorna la lista de n�meros de los vagones de un tren dado.
     *
     * @param pIdTren El identificador del tren. pIdTren != null && pIdTren >= 0.
     * @return La lista de n�meros de los vagones del tren.
     */
    public ArrayList darNumerosVagones(int pIdTren) {
        return cupiTrenes.getWagonNumbers(pIdTren);
    }

    public String[] getClasses() {
        return Wagon.CLASSES;
    }

    /**
     * Muestra el siguiente tren al actual, si �ste existe.
     */
    public void darSiguienteTren() {
        if (trenActual == null || trenActual.getNextTrain() == null) {
            JOptionPane.showMessageDialog(this, "There are no more trains that leave later.",
                                          "Get next train", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            trenActual = trenActual.getNextTrain();
            if (trenActual != null) {
                vagonActual = trenActual.getFirstWagon();
            }
            actualizarPaneles();
        }
    }

    /**
     * Muestra el tren anterior al actual, si �ste existe.
     */
    public void darAnteriorTren() {
        if (trenActual == null || trenActual.getPreviousTrain() == null) {
            JOptionPane.showMessageDialog(this, "There are no more trains that leave earlier.",
                                          "Get previous train", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            trenActual = trenActual.getPreviousTrain();
            if (trenActual != null) {
                vagonActual = trenActual.getFirstWagon();
            }
            actualizarPaneles();
        }

    }

    /**
     * Muestra el primer vag�n del tren actual.
     */
    public void darPrimerVagon() {
        if (trenActual != null) {
            vagonActual = trenActual.getFirstWagon();
            if (vagonActual != null) {
                actualizarPaneles();
            }
            else {
                JOptionPane.showMessageDialog(this, "The train has no wagons.", "Get first wagon",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Muestra el siguiente vag�n al actual, si �ste existe.
     */
    public void darSiguienteVagon() {
        if (vagonActual == null || vagonActual.getNextWagon() == null) {
            JOptionPane.showMessageDialog(this, "There are no more wagons on this train",
                                          "Get " + "next wagon", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            vagonActual = vagonActual.getNextWagon();
            actualizarPaneles();
        }
    }

    /**
     * Agrega un nuevo tren con los valores dados por par�metro.
     *
     * @param pIdTren          ID del tren a agregar. pIdRuta > 0.
     * @param pNombresParadas  Arreglo con los nombres las paradas de la ruta. pNombresParadas !=
     *                         null.
     * @param pHorariosParadas Arreglo con los horarios de la ruta. pHorariosParadas != null.
     * @return true si pudo agregar el tren, false de lo contrario.
     * @throws Exception
     */
    public boolean agregarTren(int pIdTren, String[] pNombresParadas, Date[] pHorariosParadas)
            throws Exception {

        cupiTrenes.addNewTrain(pIdTren, pNombresParadas, pHorariosParadas);
        trenActual = cupiTrenes.findTrainById(pIdTren);
        if (trenActual != null) {
            vagonActual = trenActual.getFirstWagon();
        }
        actualizarPaneles();
        return true;

    }

    /**
     * Agrega un nuevo vag�n con las caracter�sticas dadas por par�metro.
     *
     * @param pIdRuta         Id del tren al que se le agregar� un nuevo vag�n para que haga el
     *                        recorrido.
     * @param pNumeroVagon    N�mero del vag�n a agregar.
     * @param pCantidadSillas Cantidad de sillas  en el vag�n. pCantidadSillas >= 0.
     * @param pClase          Clase del compartimiento. pClase != null && pClase != "" && (pClase
     *                        pertenece a { Primera Clase, Segunda Clase, Negocios, VIP } ).
     * @param pPrecio         Precio del tiquete. pPrecio > 0.
     * @throws Exception
     */
    public void agregarVagon(int pIdRuta, int pNumeroVagon, int pCantidadSillas, String pClase,
                             double pPrecio) throws Exception {
        cupiTrenes.addWagon(pIdRuta, pNumeroVagon, pCantidadSillas, pClase, pPrecio);
        vagonActual = cupiTrenes.findTrainById(pIdRuta).findWagonByNumber(pNumeroVagon);
        actualizarPaneles();
    }

    /**
     * Elimina el tren con el id ingresado por el usuario.
     */
    public void eliminarTren() {

        try {
            String res = JOptionPane.showInputDialog(this, "Enter the train id to be eliminated ",
                                                     "Eliminate train", JOptionPane.PLAIN_MESSAGE);
            if (res != null) {
                int idTren = Integer.parseInt(res);
                if (!res.equalsIgnoreCase("")) {

                    cupiTrenes.eliminateTrain(idTren);
                    trenActual = cupiTrenes.getFirstTrain();
                    if (trenActual != null) {
                        vagonActual = trenActual.getFirstWagon();
                    }
                    else {
                        vagonActual = null;
                    }

                    actualizarPaneles();
                }
                else {
                    JOptionPane.showMessageDialog(this,
                                                  "You must enter a train id to eleminate " + "it",
                                                  "Eliminate train", JOptionPane.ERROR_MESSAGE);

                }
            }
        } catch (ElementDoesNotExistException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Eliminate train",
                                          JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "You must enter a numeric value.",
                                          "Eliminate " + "train", JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Elimina el vag�n con las caracter�sticas dadas por par�metro.
     *
     * @param idRuta   Id del tren al cual pertenece el vag�n a eliminar.
     * @param numVagon N�mero del vag�n a eliminar. numVagon >= 0.
     * @return true si logr� eliminar el vag�n. False de lo contrario.
     */
    public boolean eliminarVagon(int idRuta, int numVagon) {
        try {
            cupiTrenes.eliminateWagon(idRuta, numVagon);
            if (trenActual != null) {
                vagonActual = trenActual.getFirstWagon();
            }
            actualizarPaneles();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Eliminate wagon",
                                          JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Vende el tiquete correspondiente al tren y al vag�n que est� siendo mostrado actualmente.<br>
     * <b>post: </b> El vag�n actual tiene una silla disponible menos.
     */
    public void venderTiquete() {
        try {
            if (vagonActual == null) {
                JOptionPane.showMessageDialog(this, "No wagons exist in the selected train.",
                                              "Vender tiquete", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (cupiTrenes.sellTicket(trenActual.getId(), vagonActual.getNumber())) {
                actualizarPaneles();
                JOptionPane.showMessageDialog(this, "The ticket was sold", "Sell ticket",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(this, "No available seats remain.", "Sell ticket",
                                              JOptionPane.ERROR_MESSAGE);
            }
        } catch (ElementDoesNotExistException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Sell ticket",
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Genera el reporte con la informaci�n de cupiTrenes.<br>
     * <b>post: </b> El archivo de texto con la informaci�n de cupiTrenes fue creado.
     */
    public void generarReporte() {
        JFileChooser chooser = new JFileChooser("./data");
        int opcion = chooser.showSaveDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String pathArchivo = chooser.getSelectedFile().getParent();
            String nombreArchivo = chooser.getSelectedFile().getName();
            if (!nombreArchivo.endsWith(".txt")) {
                nombreArchivo = nombreArchivo + ".txt";
            }
            try {
                cupiTrenes.generateReport(pathArchivo, nombreArchivo);
                JOptionPane.showMessageDialog(this, "The report was generated successfully.",
                                              "Generate report", JOptionPane.INFORMATION_MESSAGE);

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Problems when creating the report. ",
                                              "Generate report", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Busca un tren que tenga como paradas el origen y destino dados por par�metro en ese orden.
     *
     * @param pOrigen  Origen buscado. pOrigen != null && pOrigen != "".
     * @param pDestino Destino buscado. pDestino != null && pDestino != "".
     * @return el tren buscado.
     */
    public Train buscarTren(String pOrigen, String pDestino) {
        Train encontrado = cupiTrenes.findTrainByTrainStops(pOrigen, pDestino);
        if (encontrado != null) {
            trenActual = encontrado;
            if (trenActual != null) {
                vagonActual = trenActual.getFirstWagon();
            }
            actualizarPaneles();
        }
        return encontrado;
    }

    /**
     * Muestra el di�logo que permite buscar un tren.
     */
    public void mostrarDialogoBuscarTren() {
        DialogoBuscarTren dialogo = new DialogoBuscarTren(this);
        setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    /**
     * Revers
     */

    /**
     * Muestra el di�logo que permite agregar un tren.
     */
    public void mostrarDialogoAgregarTren() {
        try {
            String cantStr = JOptionPane
                    .showInputDialog(this, "Enter the number of train stops.", "Add train",
                                     JOptionPane.PLAIN_MESSAGE);
            if (cantStr != null) {
                int cantidadParadas = Integer.parseInt(cantStr);

                if (cantidadParadas < 2) {
                    JOptionPane.showMessageDialog(this, "A train must have at least two stops.",
                                                  "Add train", JOptionPane.ERROR_MESSAGE);
                }
                else if (cantidadParadas > 10) {
                    JOptionPane.showMessageDialog(this, "Train cannot exceed ten stops.",
                                                  "Add " + "train", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    DialogoAgregarTren dialogo = new DialogoAgregarTren(this, cantidadParadas);
                    dialogo.setLocationRelativeTo(this);
                    dialogo.setVisible(true);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "You must enter a numeric value.", "Add train",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra el di�logo que permite agregar un vag�n.
     */
    public void mostrarDialogoAgregarVagon() {
        DialogAddWagon dialogo = new DialogAddWagon(this);
        dialogo.setVisible(true);
    }

    /**
     * Muestra el di�logo que permite eliminar un vag�n.
     */
    public void mostrarDialogoEliminarVagon() {
        DialogoEliminarVagon dialogo = new DialogoEliminarVagon(this);
        dialogo.setVisible(true);
    }

    /**
     * Muestra todas las paradas del tren actual.
     */
    public void mostrarTodasLasParadas() {
        if (trenActual != null) {
            DialogoMostrarParadasTren dialogo =
                    new DialogoMostrarParadasTren(trenActual.getTrainStops(),
                                                  trenActual.getTrainStops().size(),
                                                  trenActual.getId());
            dialogo.setLocationRelativeTo(this);
            dialogo.setVisible(true);
        }
    }

    /**
     * Actualiza los p�neles para que muestren la informaci�n del vag�n actual y del tren actual.
     */
    public void actualizarPaneles() {

        panelInformacionAcciones.actualizar(cupiTrenes.getTotalMoneyCollected(),
                                            cupiTrenes.getTotalAvailableChairs());
        if (trenActual != null) {
            if (vagonActual != null) {
                panelVagon.actualizar(vagonActual.getNumber(), vagonActual.getWagonClass(),
                                      vagonActual.getTotalAmountOfAvailableSeats(),
                                      vagonActual.getPrice(), vagonActual.getTotalAmountOfSeats());
            }
            else {
                panelVagon.actualizar(0, "", 0, 0, 0);

            }
            panelTren.actualizar(trenActual.getDestination(), trenActual.getOrigin(),
                                 trenActual.getArrivalTime(), trenActual.getDepartureTime(),
                                 trenActual.getId(), trenActual.getTrainStops().size());

        }
        else {
            panelTren.actualizar("Undefined destination", "Undefined origin", "Undefined schedule",
                                 "Undefined schedule", 0, 0);
            panelVagon.actualizar(0, "", 0, 0, 0);

        }

    }


    /**
     * M�todo que se llama cuando se cierra la ventana principal de la aplicaci�n.<br>
     * Antes de cerrar se guarda el estado de cupiTrenes.<br>
     * Si se produce un error se muestra un mensaje que informe al usuario.
     */
    public void dispose() {
        try {
            cupiTrenes.saveState(RUTA_ARCHIVO);
            super.dispose();
        } catch (Exception e) {
            setVisible(true);
            int respuesta = JOptionPane.showConfirmDialog(this, "Problems saving cupiTrains:\n" + e
                                                                  .getMessage() + "." + "\nWant " + "to cose without saving?.", "Error",
                                                          JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                super.dispose();
            }
        }
    }

    // -----------------------------------------------------------------
    // Puntos de extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     */
    public void reqFuncOpcion1() {
        Wagon resultado = cupiTrenes.getFirstTrain().reverseWagons();
        actualizarPaneles();


        JOptionPane.showMessageDialog(this, "Wagon 1 is now = " + resultado.getNumber(), "Response",
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * M�todo para la extensi�n 2.
     */
    public void reqFuncOpcion2() {


        String name = JOptionPane.showInputDialog("Enter train id.");
        Train result = cupiTrenes.findTrainById(Integer.parseInt(name));

        Wagon w1 = result.reverseWagons();
        Wagon current = w1;
        ArrayList<Integer> names = new ArrayList<>();
        while (current != null) {
            names.add(current.getNumber());
            current = current.getNextWagon();

            String aName = "";
            for (int i = 0; i < names.size(); i++) {
                aName += "wagon # " + i + names.get(i) + "\n";
            }

            System.out.println(" ");
            JOptionPane.showMessageDialog(this, aName, "Response", JOptionPane.INFORMATION_MESSAGE);
        }
        actualizarPaneles();
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
     *
     * @param pArgs Los argumentos de ejecuci�n de la aplicaci�n. pArgs != null
     */
    public static void main(String[] pArgs) {
        // Unify the interface for Mac and for Windows.
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            InterfazCupiTrenes interfaz = new InterfazCupiTrenes();
            interfaz.setVisible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


    }

}


