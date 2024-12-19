package com.bookinghotels.modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Finca extends Alojamiento implements IDiaDeSol{
    private DiaDeSolData diaDeSol;
    private Float precioPorNoche;

    // Constructores
    public Finca(String nombre, String ciudad, Float calificacion, Integer maxAdultos, Integer maxNinos, float precioPorNoche, DiaDeSolData diaDeSol) {
        super(nombre, ciudad, "Finca",calificacion, maxAdultos, maxNinos);
        this.diaDeSol = diaDeSol;
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


    @Override
    public boolean tieneDiaDeSol() {
        if(diaDeSol != null){
            return true;
        }
        return false;
    }

    @Override
    public void mostrarInfoDiaDeSol(int cantPersonas, LocalDate fechaInicio) {
        if(tieneDiaDeSol()){
            System.out.println("\n+--------------- " + nombre + " ---------------+");
            System.out.println("Calificación: " + calificacion );
            if (descripcion != null){
                System.out.println("Descripción: " + calificacion );
            }
            System.out.println("Actividades: " + diaDeSol.getActividades());
            System.out.println("Extras: ");
            for(String extra : diaDeSol.getExtras()){
                System.out.println("- " + extra);
            }
            float precioBase = calcularPrecioBaseDiaSol(cantPersonas);
            float precioTotal = calcularPrecioTotal(precioBase, fechaInicio, fechaInicio);
            System.out.println("Precio por persona: $" + diaDeSol.getPrecioPorPersona());
            System.out.println("Precio base: $" + precioBase);
            if(calcularAjustePrecio(fechaInicio,fechaInicio) > 0){
                System.out.println("Incremento de " + calcularAjustePrecio(fechaInicio,fechaInicio) * 100 + "%");
            }else if(calcularAjustePrecio(fechaInicio, fechaInicio) < 0){
                System.out.println("Descuento de " + calcularAjustePrecio(fechaInicio,fechaInicio) * 100 + "%");
            }
            System.out.println("Precio Total: $" + precioTotal);
            System.out.println("+---------------------------------------------+");
        }
    }

    @Override
    public float calcularPrecioBaseDiaSol(int cantPersonas) {
        return diaDeSol.getPrecioPorPersona() * cantPersonas;
    }


    // Getters y Setters
    public DiaDeSolData getDiaDeSol() {
        return diaDeSol;
    }

    public void setDiaDeSol(DiaDeSolData diaDeSol) {
        this.diaDeSol = diaDeSol;
    }
}
