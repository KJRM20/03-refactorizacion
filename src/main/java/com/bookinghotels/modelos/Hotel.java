package com.bookinghotels.modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Hotel extends Alojamiento implements IDiaDeSol{
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
        float precioMenor = Float.MAX_VALUE;
        long diasEstadia = ChronoUnit.DAYS.between(fechaInicio, fechaFin.plusDays(1));
        for (Habitacion habitacion : habitaciones){
            if(habitacion.getPrecioPorNoche() < precioMenor){
                precioMenor = habitacion.getPrecioPorNoche();
            }
        }
        return precioMenor * (float) diasEstadia * (float) cantHabitaciones;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Info");
    }

    @Override
    public boolean tieneDiaDeSol() {
        return false;
    }

    @Override
    public void mostrarInfoDiaDeSol() {
        if(tieneDiaDeSol()){
            System.out.println("Actividades: " + diaDeSol.getActividades());
            System.out.println("Extras: ");
            for(String extra : diaDeSol.getExtras()){
                System.out.println("- " + extra);
            }
        }
    }

    @Override
    public float calcularPrecioBaseDiaSol(int cantPersonas) {
        return diaDeSol.getPrecioPorPersona() * cantPersonas;
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
