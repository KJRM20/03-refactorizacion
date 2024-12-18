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

    //Getters y Setters
    public T getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(T alojamiento) {
        this.alojamiento = alojamiento;
    }

    public ClienteData getCliente() {
        return cliente;
    }

    public void setCliente(ClienteData cliente) {
        this.cliente = cliente;
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

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public List<Habitacion> getHabitacionesReservadas() {
        return habitacionesReservadas;
    }

    public void setHabitacionesReservadas(List<Habitacion> habitacionesReservadas) {
        this.habitacionesReservadas = habitacionesReservadas;
    }
}
