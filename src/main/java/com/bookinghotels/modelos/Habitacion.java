package com.bookinghotels.modelos;

import java.time.LocalDate;
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
        System.out.println("\n+-------- " + tipo + " --------+");
        System.out.println(descripcion);
        System.out.println("Precio por noche: $" + precioPorNoche);
        System.out.println("Capacidad para " + capacidad + " personas");
        System.out.println("+-------------------------------+");
    }

    public boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaFin, List<ReservaData> reservas){
        int habitacionesOcupadas = 0;
        for (ReservaData reserva : reservas) {
            List<Habitacion> habitacionesReservadas =  reserva.getHabitacionesReservadas();
            for(Habitacion habitacion : habitacionesReservadas){
                if(habitacion.getTipo().equalsIgnoreCase(tipo)) habitacionesOcupadas++;
            }
        }
        return habitacionesOcupadas < habitacionesDisponibles;
    }

    // Getters y Setters
    public Float getPrecioPorNoche() {
        return precioPorNoche;
    }

    public String getTipo() {
        return tipo;
    }

}
