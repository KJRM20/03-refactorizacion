package com.bookinghotels.modelos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaData<T> {
    private T alojamiento;
    private ClienteData cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaLlegada;
    private List<Habitacion> habitacionesReservadas;

    // Constructor
    public ReservaData(T alojamiento, ClienteData cliente, LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaLlegada, List<Habitacion> habitacionesReservadas) {
        this.alojamiento = alojamiento;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaLlegada = horaLlegada;
        this.habitacionesReservadas = habitacionesReservadas;
    }

    // Getters y Setters
    public T getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(T alojamiento) {
        this.alojamiento = alojamiento;
    }

    public ClienteData getCliente() {
        return cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public List<Habitacion> getHabitacionesReservadas() {
        return habitacionesReservadas;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }
}
