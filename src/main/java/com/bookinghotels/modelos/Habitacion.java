package com.bookinghotels.modelos;

import java.util.List;

public class Habitacion {
    private String tipo;
    private String descripcion;
    private Float precioPorNoche;
    private Integer habitacionesDisponibles;
    private Integer capacidad;

    // Constructor
    public Habitacion(String tipo, String descripcion, Float precioPorNoche, Integer habitacionesDisponibles, Integer capacidad) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precioPorNoche = precioPorNoche;
        this.habitacionesDisponibles = habitacionesDisponibles;
        this.capacidad = capacidad;
    }

    // Métodos
    public void mostrarDetalles(){
        System.out.println("+-------- " + tipo + " --------+");
        System.out.println(descripcion);
        System.out.println("Precio por noche: $" + precioPorNoche);
        System.out.println("Capacidad para " + capacidad + " personas");
        System.out.println("+-------------------------------+");
    }

    public boolean estaDisponible(){
        return false;
    }

    // Getters y Setters
    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getHabitacionesDisponibles() {
        return habitacionesDisponibles;
    }

    public void setHabitacionesDisponibles(Integer habitacionesDisponibles) {
        this.habitacionesDisponibles = habitacionesDisponibles;
    }

    public Float getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(Float precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
