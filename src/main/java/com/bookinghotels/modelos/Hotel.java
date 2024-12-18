package com.bookinghotels.modelos;

import java.time.LocalDate;

public class Hotel extends Alojamiento{
    private DiaDeSolData diaDeSolData;
    private Boolean servicioHabitacion;

    // Constructor
    public Hotel(String nombre, String ciudad, Float calificacion, Integer maxAdultos, Integer maxNinos, DiaDeSolData diaDeSolData, Boolean servicioHabitacion) {
        super(nombre, ciudad, calificacion, maxAdultos, maxNinos);
        this.diaDeSolData = diaDeSolData;
        this.servicioHabitacion = servicioHabitacion;
    }

    //Métodos
    @Override
    public boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaFin, int cantPersonas, int cantHabitaciones) {
        return false;
    }

    @Override
    public float calcularPrecioBase(LocalDate fechaInicio, LocalDate fechaFin, int cantPersonas, int cantHabitaciones) {
        return 0;
    }

    @Override
    public float mostrarInformacion() {
        return 0;
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
