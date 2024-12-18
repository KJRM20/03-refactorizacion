package com.bookinghotels.modelos;

import java.time.LocalDate;

public class Finca extends Alojamiento{
    private DiaDeSolData diaDeSol;

    // Constructores
    public Finca(String nombre, String ciudad, Float calificacion, Integer maxAdultos, Integer maxNinos, DiaDeSolData diaDeSol) {
        super(nombre, ciudad, calificacion, maxAdultos, maxNinos);
        this.diaDeSol = diaDeSol;
    }

    // Métodos
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

    // Getters y Setters
    public DiaDeSolData getDiaDeSol() {
        return diaDeSol;
    }

    public void setDiaDeSol(DiaDeSolData diaDeSol) {
        this.diaDeSol = diaDeSol;
    }
}
