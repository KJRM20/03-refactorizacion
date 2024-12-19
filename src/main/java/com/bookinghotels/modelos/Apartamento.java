package com.bookinghotels.modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Apartamento extends Alojamiento{
    private Integer piso;
    private String numeroApartamento;
    private Float precioPorNoche;

    // Constructores
    public Apartamento(String nombre, String ciudad, Float calificacion, Integer maxAdultos, Integer maxNinos, Float precioPorNoche, Integer piso, String numeroApartamento) {
        super(nombre, ciudad, "Apartamento",calificacion, maxAdultos, maxNinos);
        this.piso = piso;
        this.numeroApartamento = numeroApartamento;
        this.precioPorNoche = precioPorNoche;
    }

    // Métodos
    @Override
    public boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaFin, int cantPersonas, int cantHabitaciones, List<ReservaData> reservas) {
        if (cantPersonas > (maxAdultos + maxNinos)) return false;
        for (ReservaData reserva : reservas) {
            if (!(fechaFin.isBefore(reserva.getFechaInicio()) || fechaInicio.isAfter(reserva.getFechaFin()))) return false;
        }
        return true;
    }

    @Override
    public float calcularPrecioBase(LocalDate fechaInicio, LocalDate fechaFin, int cantPersonas, int cantHabitaciones) {
        int capacidadFinca = maxAdultos + maxNinos;
        long diasEstadia = ChronoUnit.DAYS.between(fechaInicio, fechaFin.plusDays(1));
        if(cantPersonas > capacidadFinca){
            throw new IllegalArgumentException("La capacidad máxima de la finca es de " + capacidadFinca + " personas.");
        } else {
            return precioPorNoche * (float) diasEstadia;
        }
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
