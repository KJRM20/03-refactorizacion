package com.bookinghotels.modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Finca extends Alojamiento implements IDiaDeSol{
    private DiaDeSolData diaDeSol;
    private Float precioPorNoche;

    // Constructores
    public Finca(String nombre, String ciudad, Float calificacion, Integer maxAdultos, Integer maxNinos, Float precioPorNoche, DiaDeSolData diaDeSol) {
        super(nombre, ciudad, "Finca",calificacion, maxAdultos, maxNinos);
        this.diaDeSol = diaDeSol;
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

        Float precioBase = calcularPrecioBaseDiaSol(cantPersonas);
        Float precioTotal = calcularPrecioTotal(precioBase, fechaInicio, fechaInicio);
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

    private void mostrarPrecios(LocalDate fechaInicio, Float precioBase, Float precioTotal) {
        System.out.println("Precio por persona: $" + diaDeSol.getPrecioPorPersona());
        System.out.println("Precio base: $" + precioBase);

        Float ajustePrecio = calcularAjustePrecio(fechaInicio, fechaInicio);
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
