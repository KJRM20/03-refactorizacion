package com.bookinghotels.modelos;

import java.util.List;

public class DiaDeSolData {
    private String actividades;
    private List<String> extras;

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

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }
}