package com.bookinghotels.modelos;

import java.time.LocalDate;

public class Hotel extends Alojamiento{
    private DiaDeSolData diaDeSol;
    private Boolean servicioHabitacion;

    // Constructor
    public Hotel(String nombre, String ciudad, Float calificacion, Integer maxAdultos, Integer maxNinos, DiaDeSolData diaDeSol, Boolean servicioHabitacion) {
        super(nombre, ciudad, calificacion, maxAdultos, maxNinos);
        this.diaDeSol = diaDeSol;
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
    public void mostrarInformacion() {
        System.out.println("Info");
    }


    //Getters y Setters
    public DiaDeSolData getDiaDeSol() {
        return diaDeSol;
    }

    public void setDiaDeSol(DiaDeSolData diaDeSol) {
        this.diaDeSol = diaDeSol;
    }

    public Boolean getServicioHabitacion() {
        return servicioHabitacion;
    }

    public void setServicioHabitacion(Boolean servicioHabitacion) {
        this.servicioHabitacion = servicioHabitacion;
    }
}