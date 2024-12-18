package com.bookinghotels.modelos;

import java.time.LocalDate;

public class Apartamento extends Alojamiento{
    private Integer piso;
    private String numeroApartamento;

    // Constructores
    public Apartamento(String nombre, String ciudad, Float calificacion, Integer maxAdultos, Integer maxNinos, Integer piso, String numeroApartamento) {
        super(nombre, ciudad, calificacion, maxAdultos, maxNinos);
        this.piso = piso;
        this.numeroApartamento = numeroApartamento;
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
    public void mostrarInformacion() {
        System.out.println("Info");
    }

    // Getters y Setters
    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public String getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }
}
