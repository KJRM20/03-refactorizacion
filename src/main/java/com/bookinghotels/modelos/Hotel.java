package com.bookinghotels.modelos;

public class Hotel extends Alojamiento{
    private DiaDeSolData diaDeSolData;
    private Boolean servicioHabitacion;

    public Hotel(String nombre, String ciudad, Float calificacion, Integer maxAdultos, Integer maxNinos, DiaDeSolData diaDeSolData, Boolean servicioHabitacion) {
        super(nombre, ciudad, calificacion, maxAdultos, maxNinos);
        this.diaDeSolData = diaDeSolData;
        this.servicioHabitacion = servicioHabitacion;
    }

    //Getters y Setters
    public DiaDeSolData getDiaDeSolData() {
        return diaDeSolData;
    }

    public void setDiaDeSolData(DiaDeSolData diaDeSolData) {
        this.diaDeSolData = diaDeSolData;
    }

    public Boolean getServicioHabitacion() {
        return servicioHabitacion;
    }

    public void setServicioHabitacion(Boolean servicioHabitacion) {
        this.servicioHabitacion = servicioHabitacion;
    }
}
