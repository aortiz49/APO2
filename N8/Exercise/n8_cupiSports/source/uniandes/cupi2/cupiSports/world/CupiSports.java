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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class in charge of administrating Clase que se encarga de administrar el sistema de CupiSports
 * .<br>
 * <b>inv:</b><br>
 * deportes != null. <br>
 * No existsn dos o m�s deportes con el mismo name.<br>
 */
public class CupiSports {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Lista con los deportes.
     */
    private ArrayList<Sport> deportes;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Construye una nuevo sistema de deportes. <br>
     * Si el archivo indicado no exists, se crea una lista de deportes vac�a.<br>
     * Si el archivo exists, se carga la informaci�n de los deportes y su deportistas.
     *
     * @param pNameArchivo Name del archivo que contiene los datos serializados.
     *                       pNameArchivo != null && pNameArchivo != "".
     * @throws PersistenciaException Se lanza una excepci�n si hay alg�n error cargando los datos
     *                               del archivo.
     */
    public CupiSports(String pNameArchivo) throws PersistenciaException {
        // TODO Parte 3 punto C: Implemente el m�todo seg�n la documentaci�n.

        verificarInvariants();
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns la lista con todos los deportes.
     *
     * @return Lista con los deportes.
     */
    public ArrayList<Sport> getSports() {
        return deportes;
    }

    /**
     * Agrega un deporte con la informaci�n dada a la lista de deportes. <br>
     * <b>post: </b> Se agreg� el deporte a la lista.
     *
     * @param pNameSport                  Name of the sport. pNameSport != null &&
     *                                        pNameSport != "".
     * @param pRegulatoryEntity                  Regulatory entity of the sport. pRegulatoryEntity != null
     *                                        && pRegulatoryEntity
     *                                        != "".
     * @param pNumberOfRegisteredAthletes Cantidad de deportistas registrados.
     *                                        pNumberOfRegisteredAthletes > 0.
     * @param pImagePath                     Image path of the sport. pImagePath != null
     *                                        && pRutasImagen != "".
     * @throws ElementExistsException Lanza una excepci�n si ya exists un deporte con el name
     *                                 dado.
     */
    public void agregarSport(String pNameSport, String pRegulatoryEntity,
                               int pNumberOfRegisteredAthletes, String pImagePath)
            throws ElementExistsException {
        if (existsSport(pNameSport)) {
            throw new ElementExistsException(ElementExistsException.DEPORTE_REPETIDO,
                                              pNameSport);
        }
        else {
            Sport deporteNuevo =
                    new Sport(pNameSport, pRegulatoryEntity, pNumberOfRegisteredAthletes,
                                pImagePath);
            deportes.add(deporteNuevo);
            verificarInvariants();
        }
    }

    /**
     * Elimina el deporte con name dado de la lista de deportes. <b>post: </b> Se elimin� el
     * deporte de la lista.
     *
     * @param pNameSport Name of the sport a eliminar. pNameSport != null &&
     *                       pNameSport != "".
     */
    public void eliminarSport(String pNameSport) {
        boolean encontro = false;
        for (int i = 0; i < deportes.size() && !encontro; i++) {
            Sport deporteActual = deportes.get(i);
            if (deporteActual.getName().equals(pNameSport)) {
                deportes.remove(i);
                encontro = true;
            }
        }
        verificarInvariants();
    }

    /**
     * Agrega un deportista sobresaliente a un deporte con la informaci�n dada por par�metro. <br>
     * <b>post: </b> Se agreg� el deportista sobresaliente al deporte correspondiente.
     *
     * @param pNameSport        Name of the sport. pNameSport != null && pNameSport
     *                              != "".
     * @param pNameAthlete     Name of the athlete sobresaliente. pNameAthlete !=
     *                              null &&
     *                              pNameAthlete != "".
     * @param pAge                 Age of the athlete. pAge > 0.
     * @param pPlaceOfResidency      Athlete's place of residence.  pPlaceOfResidency != null &&
     *                              pPlaceOfResidency != "".
     * @param pAmountOfTrophies      Amount of trophies won by the athlete.
     *                              pAmountOfTrophies >= 0.
     * @param pImagePathAthlete Image path of the athlete. pImagePathAthlete !=
     *                              null && pImagePathAthlete != "".
     * @throws ElementExistsException Lanza una excepci�n si en el deporte ya exists un
     *                                 deportista con el name dado.
     */
    public void addOutstandingAthlete(String pNameSport, String pNameAthlete,
                                               int pAge, String pPlaceOfResidency,
                                               int pAmountOfTrophies, String pImagePathAthlete)
            throws ElementExistsException {
        //TODO Parte 4 punto D: Implemente el m�todo seg�n la documentaci�n.   
    }

    /**
     * Elimina el deportista sobresaliente con el name especificado del deporte que tiene el
     * name dado por par�metro. <br>
     * <b>post: </b> Se elimin� el deportista sobresaliente del deporte correspondiente.
     *
     * @param pNameSport    Name of the sport. pNameSport != null && pNameSport != "".
     * @param pNameAthlete Name of the athlete a eliminar. pNameAthlete != null &&
     *                          pNameAthlete != "".
     */
    public void eliminateOutstandingAthlete(String pNameSport, String pNameAthlete) {
        boolean encontro = false;
        for (int i = 0; i < deportes.size() && !encontro; i++) {
            Sport deporteActual = deportes.get(i);
            if (deporteActual.getName().equals(pNameSport)) {
                deporteActual.eliminateOutstandingAthlete(pNameAthlete);
                encontro = true;
            }
        }
        verificarInvariants();
    }

    /**
     * Verifica si exists un deporte con el name dado.
     *
     * @param pNameSport Name of the sport. pNameSport != null && pNameSport != "".
     * @return True si exists el deporte, false en caso contrario.
     */
    public boolean existsSport(String pNameSport) {
        boolean exists = false;

        for (int i = 0; i < deportes.size() && !exists; i++) {
            Sport deporteActual = deportes.get(i);
            if (deporteActual.getName().equals(pNameSport)) {
                exists = true;
            }
        }
        return exists;
    }

    /**
     * Returns el deportista sobresaliente que tiene el mayor n�mero de trofeos. <br>
     * Si hay dos o m�s deportistas con el mismo n�mero de trofeos y ambos son m�ximos, retorna
     * cualquiera.
     *
     * @return Returns el deportista que m�s tiene trofeos. Si no hay deportistas retorna null.
     */
    public Athlete getAthleteMostTrophies() {
        Athlete mostTrophies = null;
        int cantidadMax = 0;
        if (deportes.size() != 0) {
            for (int i = 0; i < deportes.size(); i++) {
                Sport deporteActual = deportes.get(i);
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

        for (int i = 0; i < deportes.size(); i++) {
            Sport deporteActual = deportes.get(i);
            total += deporteActual.getTotalTrophies();
        }

        return total;
    }

    /**
     * Serializa el ArrayList de deportes en el archivo cuya ruta entra como par�metro.
     *
     * @param pRutaArchivo Ruta del archivo donde se va gua.get. pRutaArchivo != null &&
     *                     pRutaArchivo != "".
     * @throws PersistenciaException Lanza una excepci�n si se presenta un problema al momento de
     *                               gua.get el archivo.
     */
    public void gua.get(String pRutaArchivo) throws PersistenciaException {
        // TODO Parte 3 punto A: Implemente el m�todo seg�n la documentaci�n.
    }

    /**
     * Actualiza la informaci�n de un deportista.
     *
     * @param pLinea L�nea de texto con la informaci�n para actualizar un deportista. pLinea !=
     *               null && pLinea != "".
     * @throws FormatoArchivoException Lanza excepci�n si la l�nea de texto no cumple con el
     *                                 formato definido para actualizar la informaci�n de un
     *                                 deportista.
     */
    private void actualizarAthlete(String pLinea) throws FormatoArchivoException {
        // TODO Parte 5 punto B: Implemente el m�todo seg�n la documentaci�n.
    }


    /**
     * Actualiza la informaci�n de los deportistas a partir de un archivo de texto.
     *
     * @param pArchivo Archivo del cual se cargar� la informaci�n. pArchivo != null.
     * @throws FormatoArchivoException Si el archivo no cumple con el formato definido para
     *                                 actualizar la informaci�n.
     * @throws IOException             Si hay problemas de lectura del archivo para actualizar la
     * informaci�n.
     */
    public void actualizarInformacionAthletes(File pArchivo)
            throws FormatoArchivoException, IOException {
        // TODO Parte 5 punto C: Implemente el m�todo seg�n la documentaci�n.
    }

    /**
     * Genera el informe de los trofeos de los deportistas.
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
     * deportes != null <br>
     * No existsn dos o m�s deportes con el mismo name.<br>
     */
    private void verificarInvariants() {
        assert deportes != null : "La lista de deportes es nula.";
        assert buscarSportsConElMismoName() == false : "Hay deportes con el mismo name.";
    }

    /**
     * Revisa si hay dos deportes con el mismo name.
     *
     * @return Returns true si hay dos deportes con el mismo name. Returns false en caso
     * contrario.
     */
    private boolean buscarSportsConElMismoName() {
        for (int i = 0; i < deportes.size(); i++) {
            Sport deporte1 = deportes.get(i);

            for (int j = i + 1; j < deportes.size(); j++) {
                Sport deporte2 = deportes.get(j);
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