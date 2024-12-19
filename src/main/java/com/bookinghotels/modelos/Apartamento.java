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
    public boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaFin, Integer cantPersonas, Integer cantHabitaciones, List<ReservaData> reservas) {
        if (cantPersonas > maxPersonas) return false;
        for (ReservaData reserva : reservas) {
            if (!(fechaFin.isBefore(reserva.getFechaInicio()) || fechaInicio.isAfter(reserva.getFechaFin()))) return false;
        }
        return true;
    }

    @Override
    public Float calcularPrecioBase(LocalDate fechaInicio, LocalDate fechaFin, Integer cantPersonas, Integer cantHabitaciones) {
        long diasEstadia = ChronoUnit.DAYS.between(fechaInicio, fechaFin.plusDays(1));
        if(cantPersonas > maxPersonas){
            throw new IllegalArgumentException("La capacidad máxima de la finca es de " + maxPersonas + " personas.");
        } else {
            return precioPorNoche * (float) diasEstadia;
        }
    }

}
