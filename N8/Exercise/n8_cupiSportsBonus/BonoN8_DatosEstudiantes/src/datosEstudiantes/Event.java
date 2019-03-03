package datosEstudiantes;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Event {

    private String idEstudiante;

    private String nomActividad;

    private Date fecha;

    public Event(String pId, String pNomAct, Date pFecha) {
        idEstudiante = pId;
        nomActividad = pNomAct;
        fecha = pFecha;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNomActividad() {
        return nomActividad;
    }

    public void setNomActividad(String nomActividad) {
        this.nomActividad = nomActividad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String fechaStr = sdf.format(fecha);

        return nomActividad + "__________" + fechaStr;
    }

}





