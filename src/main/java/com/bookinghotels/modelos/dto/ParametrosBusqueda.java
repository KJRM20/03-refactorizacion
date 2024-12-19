package com.bookinghotels.modelos.dto;

import java.time.LocalDate;

public class ParametrosBusqueda {
    private String categoria;
    private String ciudad;
    private Integer cantPersonas;
    private Integer cantHabitaciones;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ParametrosBusqueda(String categoria, String ciudad, Integer adultos, Integer ninos, Integer cantHabitaciones, LocalDate fechaInicio, LocalDate fechaFin) {
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.cantPersonas = adultos + ninos;
        this.cantHabitaciones = cantHabitaciones;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ParametrosBusqueda(){
    }

    //Getters y Setters


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(Integer cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public Integer getCantHabitaciones() {
        return cantHabitaciones;
    }

    public void setCantHabitaciones(Integer cantHabitaciones) {
        this.cantHabitaciones = cantHabitaciones;
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
}
