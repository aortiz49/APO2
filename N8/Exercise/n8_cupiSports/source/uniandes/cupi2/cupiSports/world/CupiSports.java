/*
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * University of the Andes
 * Department of Systems and Computer Engineering
 * Licensed under Academic Free License version 2.1
 * Project Cupi2 (http://cupi2.uniandes.edu.co)
 * Exercise: n7_Sports
 * Author: Andres Ortiz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package uniandes.cupi2.cupiSports.world;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class in charge of administrating the CupiSports system. <br>
 * <b>inv:</b><br>
 * sports != null. <br>
 * No two teams can exist with the same name. <br>
 */
public class CupiSports {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * List of sports.
     */
    private ArrayList sports;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructs a new sports system. <br>
     * If the indicated file doesn't exist, it will create an empty sports list. <br>
     * If the file exists, it will load the sports information and its athletes.
     *
     * @param pFileName Name of the file containing the serialized data.
     *                  pFileName != null && pFileName != "".
     * @throws PersistenceException An exception is thrown if there is an error loading the data
     *                              from the file.
     */
    public CupiSports(String pFileName) throws PersistenceException {

        File archivo = new File(pFileName);
        if (archivo.exists()) {
            // El archivo existe: se debe recuperar de all� el estado del modelo del mundo
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                sports = (ArrayList) ois.readObject();
                ois.close();
            } catch (Exception e) {
                // Se atrapan en este bloque todos los tipos de excepci�n
                e.printStackTrace();
                throw new PersistenceException(
                        "Error fatal: imposible restaurar el estado del programa (" + e.getMessage()
                                + ")");
            }
        }
        else {
            // El archivo no existe: es la primera vez que se ejecuta el programa
            sports = new ArrayList();
        }
        verifyInvariants();
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns la lista con todos los sports.
     *
     * @return Lista con los sports.
     */
    public ArrayList<Sport> getSports() {
        return sports;
    }

    /**
     * Agrega un deporte con la informaci�n dada a la lista de sports. <br>
     * <b>post: </b> Se agreg� el deporte a la lista.
     *
     * @param pNameSport                  Name of the sport. pNameSport != null &&
     *                                    pNameSport != "".
     * @param pRegulatoryEntity           Regulatory entity of the sport. pRegulatoryEntity != null
     *                                    && pRegulatoryEntity
     *                                    != "".
     * @param pNumberOfRegisteredAthletes Cantidad de athletes registrados.
     *                                    pNumberOfRegisteredAthletes > 0.
     * @param pImagePath                  Image path of the sport. pImagePath != null
     *                                    && pRutasImagen != "".
     * @throws ElementExistsException Lanza una excepci�n si ya exists un deporte con el name
     *                                dado.
     */
    public void addSport(String pNameSport, String pRegulatoryEntity,
                         int pNumberOfRegisteredAthletes, String pImagePath)
            throws ElementExistsException {
        if (existsSport(pNameSport)) {
            throw new ElementExistsException(ElementExistsException.REPEATED_SPORT, pNameSport);
        }
        else {
            Sport deporteNuevo =
                    new Sport(pNameSport, pRegulatoryEntity, pNumberOfRegisteredAthletes,
                              pImagePath);
            sports.add(deporteNuevo);
            verifyInvariants();
        }
    }

    /**
     * Elimina el deporte con name dado de la lista de sports. <b>post: </b> Se elimin� el
     * deporte de la lista.
     *
     * @param pNameSport Name of the sport a delete. pNameSport != null &&
     *                   pNameSport != "".
     */
    public void deleteSport(String pNameSport) {
        boolean encontro = false;
        for (int i = 0; i < sports.size() && !encontro; i++) {
            Sport deporteActual = (Sport) sports.get(i);
            if (deporteActual.getName().equals(pNameSport)) {
                sports.remove(i);
                encontro = true;
            }
        }
        verifyInvariants();
    }

    /**
     * Agrega un athlete sobresaliente a un deporte con la informaci�n dada por par�metro. <br>
     * <b>post: </b> Se agreg� el athlete sobresaliente al deporte correspondiente.
     *
     * @param pNameSport        Name of the sport. pNameSport != null && pNameSport
     *                          != "".
     * @param pNameAthlete      Name of the athlete sobresaliente. pNameAthlete !=
     *                          null &&
     *                          pNameAthlete != "".
     * @param pAge              Age of the athlete. pAge > 0.
     * @param pPlaceOfResidency Athlete's place of residence.  pPlaceOfResidency != null &&
     *                          pPlaceOfResidency != "".
     * @param pAmountOfTrophies Amount of trophies won by the athlete.
     *                          pAmountOfTrophies >= 0.
     * @param pImagePathAthlete Image path of the athlete. pImagePathAthlete !=
     *                          null && pImagePathAthlete != "".
     * @throws ElementExistsException Lanza una excepci�n si en el deporte ya exists un
     *                                athlete con el name dado.
     */
    public void addOutstandingAthlete(String pNameSport, String pNameAthlete, int pAge,
                                      String pPlaceOfResidency, int pAmountOfTrophies,
                                      String pImagePathAthlete) throws ElementExistsException {


            if (exists(pNameSport)) {
                throw new ElementExistsException(ElementExistsException.REPEATED_SPORT, pNameSport);
            }
            else {
                Sport deporteNuevo =
                        new Sport(pNameSport, pRegulatoryEntity, pNumberOfRegisteredAthletes,
                                  pImagePath);
                sports.add(deporteNuevo);
                verifyInvariants();
            }
    }

    /**
     * Elimina el athlete sobresaliente con el name especificado del deporte que tiene el
     * name dado por par�metro. <br>
     * <b>post: </b> Se elimin� el athlete sobresaliente del deporte correspondiente.
     *
     * @param pNameSport   Name of the sport. pNameSport != null && pNameSport != "".
     * @param pNameAthlete Name of the athlete a delete. pNameAthlete != null &&
     *                     pNameAthlete != "".
     */
    public void eliminateOutstandingAthlete(String pNameSport, String pNameAthlete) {
        boolean encontro = false;
        for (int i = 0; i < sports.size() && !encontro; i++) {
            Sport deporteActual = (Sport) sports.get(i);
            if (deporteActual.getName().equals(pNameSport)) {
                deporteActual.eliminateOutstandingAthlete(pNameAthlete);
                encontro = true;
            }
        }
        verifyInvariants();
    }

    /**
     * Verifica si exists un deporte con el name dado.
     *
     * @param pNameSport Name of the sport. pNameSport != null && pNameSport != "".
     * @return True si exists el deporte, false en caso contrario.
     */
    public boolean existsSport(String pNameSport) {
        boolean exists = false;

        for (int i = 0; i < sports.size() && !exists; i++) {
            Sport deporteActual = (Sport) sports.get(i);
            if (deporteActual.getName().equals(pNameSport)) {
                exists = true;
            }
        }
        return exists;
    }

    /**
     * Returns el athlete sobresaliente que tiene el mayor n�mero de trofeos. <br>
     * Si hay dos o m�s athletes con el mismo n�mero de trofeos y ambos son m�ximos, retorna
     * cualquiera.
     *
     * @return Returns el athlete que m�s tiene trofeos. Si no hay athletes retorna null.
     */
    public Athlete getAthleteMostTrophies() {
        Athlete mostTrophies = null;
        int cantidadMax = 0;
        if (sports.size() != 0) {
            for (int i = 0; i < sports.size(); i++) {
                Sport deporteActual = (Sport) sports.get(i);
                Athlete currentAthleteMas = deporteActual.getAthleteMostTrophies();
                if (currentAthleteMas.getAmountOfTrophies() > cantidadMax) {
                    mostTrophies = currentAthleteMas;
                    cantidadMax = currentAthleteMas.getAmountOfTrophies();
                }
            }
        }
        return mostTrophies;
    }

    /**
     * Returns el total de trofeos.
     *
     * @return Cantidad total de trofeos.
     */
    public int getTotalTrophies() {
        int total = 0;

        for (int i = 0; i < sports.size(); i++) {
            Sport deporteActual = (Sport) sports.get(i);
            total += deporteActual.getTotalTrophies();
        }

        return total;
    }

    /**
     * Serializa el ArrayList de sports en el archivo cuya ruta entra como par�metro.
     *
     * @param pRutaArchivo Ruta del archivo donde se va gua.get. pRutaArchivo != null &&
     *                     pRutaArchivo != "".
     * @throws PersistenceException Lanza una excepci�n si se presenta un problema al momento de
     *                              gua.get el archivo.
     */
    public void saveState(String pRutaArchivo) throws PersistenceException {
        File archivo = new File(pRutaArchivo);
        if (archivo.exists()) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pRutaArchivo));
                oos.writeObject(sports);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new PersistenceException("Error al salvar: " + e.getMessage());
            }
        }
    }

    /**
     * Actualiza la informaci�n de un athlete.
     *
     * @param pLinea L�nea de texto con la informaci�n para actualizar un athlete. pLinea !=
     *               null && pLinea != "".
     * @throws FileFormatException Lanza excepci�n si la l�nea de texto no cumple con el
     *                             formato definido para actualizar la informaci�n de un
     *                             athlete.
     */
    private void actualizarAthlete(String pLinea) throws FileFormatException {
        // TODO Parte 5 punto B: Implemente el m�todo seg�n la documentaci�n.
    }


    /**
     * Actualiza la informaci�n de los athletes a partir de un archivo de texto.
     *
     * @param pArchivo Archivo del cual se cargar� la informaci�n. pArchivo != null.
     * @throws FileFormatException Si el archivo no cumple con el formato definido para
     *                             actualizar la informaci�n.
     * @throws IOException         Si hay problemas de lectura del archivo para actualizar la
     *                             informaci�n.
     */
    public void actualizarInformacionAthletes(File pArchivo)
            throws FileFormatException, IOException {
        // TODO Parte 5 punto C: Implemente el m�todo seg�n la documentaci�n.
    }

    /**
     * Genera el informe de los trofeos de los athletes.
     *
     * @param pRutaArchivo Ruta donde se desea gua.get el archivo con el reporte. pRutaArchivo !=
     *                     null && pRutaArchivo != "".
     * @throws IOException Si ocurre un error en la generaci�n del reporte.
     */
    public void generarReporteTrophies(String pRutaArchivo) throws IOException {
        // TODO Parte 5 punto A: Implemente el m�todo seg�n la documentaci�n.
    }

    // -----------------------------------------------------------------
    // Invariants
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase: <br>
     * sports != null <br>
     * No existsn dos o m�s sports con el mismo name.<br>
     */
    private void verifyInvariants() {
        assert sports != null : "La lista de sports es nula.";
        assert !buscarSportsConElMismoName() : "Hay sports con el mismo name.";
    }

    /**
     * Revisa si hay dos sports con el mismo name.
     *
     * @return Returns true si hay dos sports con el mismo name. Returns false en caso
     * contrario.
     */
    private boolean buscarSportsConElMismoName() {
        for (int i = 0; i < sports.size(); i++) {
            Sport deporte1 = (Sport) sports.get(i);

            for (int j = i + 1; j < sports.size(); j++) {
                Sport deporte2 = (Sport) sports.get(j);
                if (deporte1.equals(deporte2))
                    return true;
            }
        }
        return false;
    }


    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     *
     * @return respuesta1
     */
    public String metodo1() {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     *
     * @return respuesta2
     */
    public String metodo2() {
        return "Respuesta 2";
    }

}