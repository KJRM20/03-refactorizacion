package com.bookinghotels.modelos;

import java.util.List;

public class DiaDeSolData {
    private String actividades;
    private List<String> extras;
    private Float precioPorPersona;

    //Constructor
    public DiaDeSolData(String actividades, List<String> extras, Float precioPorPersona) {
        this.actividades = actividades;
        this.extras = extras;
        this.precioPorPersona = precioPorPersona;
    }

    //Getters y Setters
    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public List<String> getExtras() {
        return extras;
    }


    public Float getPrecioPorPersona() {
        return precioPorPersona;
    }

}
