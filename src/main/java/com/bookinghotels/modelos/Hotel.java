package com.bookinghotels.modelos;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Hotel extends Alojamiento implements IDiaDeSol{
    private DiaDeSolData diaDeSol;
    private Boolean servicioHabitacion;

    // Constructor
    public Hotel(String nombre, String ciudad, Float calificacion, Integer maxAdultos, Integer maxNinos, DiaDeSolData diaDeSol, Boolean servicioHabitacion) {
        super(nombre, ciudad, "Hotel", calificacion, maxAdultos, maxNinos);
        this.diaDeSol = diaDeSol;
        this.servicioHabitacion = servicioHabitacion;
    }

    //Métodos
    @Override
    public boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaFin, Integer cantPersonas, Integer cantHabitaciones, List<ReservaData> reservas) {
        int habitacionesDisponibles = 0;
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.estaDisponible(fechaInicio, fechaFin, reservas)) {
                habitacionesDisponibles++;
            }
            if (habitacionesDisponibles >= cantHabitaciones) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Float calcularPrecioBase(LocalDate fechaInicio, LocalDate fechaFin, Integer cantPersonas, Integer cantHabitaciones) {
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
    public boolean tieneDiaDeSol() {
        if(diaDeSol != null){
            return true;
        }
        return false;
    }

    @Override
    public void mostrarInfoDiaDeSol(Integer cantPersonas, LocalDate fechaInicio) {
        if (!tieneDiaDeSol()) return;
        System.out.println("\n+--------------- " + nombre + " ---------------+");
        System.out.println("Calificación: " + calificacion);

        mostrarActividadesYExtras();

        float precioBase = calcularPrecioBaseDiaSol(cantPersonas);
        float precioTotal = calcularPrecioTotal(precioBase, fechaInicio, fechaInicio);
        mostrarPrecios(fechaInicio, precioBase, precioTotal);
        System.out.println("+---------------------------------------------+");
    }

    private void mostrarActividadesYExtras() {
        System.out.println("Actividades: " + diaDeSol.getActividades());
        System.out.println("Extras: ");
        for (String extra : diaDeSol.getExtras()) {
            System.out.println("- " + extra);
        }
    }

    private void mostrarPrecios(LocalDate fechaInicio, float precioBase, float precioTotal) {
        System.out.println("Precio por persona: $" + diaDeSol.getPrecioPorPersona());
        System.out.println("Precio base: $" + precioBase);

        float ajustePrecio = calcularAjustePrecio(fechaInicio, fechaInicio);
        if (ajustePrecio != 0) {
            String tipoAjuste = ajustePrecio > 0 ? "Incremento" : "Descuento";
            System.out.println(tipoAjuste + " de " + ajustePrecio * 100 + "%");
        }

        System.out.println("Precio Total: $" + precioTotal);
    }

    @Override
    public float calcularPrecioBaseDiaSol(Integer cantPersonas) {
        return diaDeSol.getPrecioPorPersona() * cantPersonas;
    }

}
