package com.bookinghotels.modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class Alojamiento {
    protected String nombre;
    protected String ciudad;
    protected String categoria;
    protected Float calificacion;
    protected String descripcion;
    protected Integer maxAdultos;
    protected Integer maxNinos;
    protected Integer maxPersonas;
    protected List<Habitacion> habitaciones;

    // Constructor
    public Alojamiento(String nombre, String ciudad, String categoria, Float calificacion, Integer maxAdultos, Integer maxNinos) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.categoria = categoria;
        this.calificacion = calificacion;
        this.maxAdultos = maxAdultos;
        this.maxNinos = maxNinos;
        this.maxPersonas = maxAdultos + maxNinos;
        this.habitaciones = new ArrayList<>();
    }

    // Métodos abstractos
    public abstract  boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaFin, Integer cantPersonas, Integer cantHabitaciones, List<ReservaData> reservas);
    public abstract Float calcularPrecioBase(LocalDate fechaInicio, LocalDate fechaFin, Integer cantPersonas, Integer cantHabitaciones);

    // Métodos concretos
    public void mostrarInformacion(){
        System.out.println("\n+--------------- " + nombre + " ---------------+");
        System.out.println("Calificación: " + calificacion );
        if (descripcion != null){
            System.out.println("Descripción: " + calificacion );
        }
        System.out.println("+---------------------------------------------+");
    }

    public void mostrarInformacion(LocalDate fechaInicio, LocalDate fechaFin, Integer cantPersonas, Integer cantHabitaciones) {
        System.out.println("\n+--------------- " + nombre + " ---------------+");
        System.out.println("Calificación: " + calificacion);

        Float precioPorNoche = calcularPrecioBase(fechaInicio, fechaInicio, cantPersonas, cantHabitaciones);
        Float precioBase = calcularPrecioBase(fechaInicio, fechaFin, cantPersonas, cantHabitaciones);
        Float precioTotal = calcularPrecioTotal(precioBase, fechaInicio, fechaFin);
        Float ajustePrecio = calcularAjustePrecio(fechaInicio, fechaFin);

        mostrarPrecios(precioPorNoche, precioBase, precioTotal, ajustePrecio);
        System.out.println("+---------------------------------------------+");
    }

    private void mostrarPrecios(Float precioPorNoche, Float precioBase, Float precioTotal, Float ajustePrecio) {
        System.out.println("Precio por noche: $" + precioPorNoche);
        System.out.println("Precio base: $" + precioBase);

        if (ajustePrecio != 0) {
            String tipoAjuste = ajustePrecio > 0 ? "Incremento" : "Descuento";
            System.out.println(tipoAjuste + " de " + ajustePrecio * 100 + "%");
        }

        System.out.println("Precio Total: $" + precioTotal);
    }

    public void agregarHabitacion(Habitacion habitacion){
        habitaciones.add(habitacion);
    }

    public Float calcularPrecioTotal(Float precioBase, LocalDate fechaInicio, LocalDate fechaFin){
        return precioBase + (precioBase * calcularAjustePrecio(fechaInicio, fechaFin));
    }

    public Float calcularAjustePrecio(LocalDate fechaInicio, LocalDate fechaFin){
        boolean[] aplicaDescuento = new boolean[]{false,false,false};
        long diasEstadia = ChronoUnit.DAYS.between(fechaInicio, fechaFin.plusDays(1));

        for(int i = 0; i < diasEstadia; i++){
            LocalDate fechaActual = fechaInicio.plusDays(i);
            if(!aplicaDescuento[0] && fechaActual.getDayOfMonth() > 5 && fechaActual.getDayOfMonth() <= 10) aplicaDescuento[0] = true;
            if(!aplicaDescuento[1] && fechaActual.getDayOfMonth() > 10 && fechaActual.getDayOfMonth() <= 15) aplicaDescuento[1] = true;
            if(!aplicaDescuento[2] && fechaActual.getDayOfMonth() > fechaActual.lengthOfMonth() - 5) aplicaDescuento[2] = true;
        }

        return (aplicaDescuento[0] ? -0.08f : 0) +
                (aplicaDescuento[1] ? 0.10f : 0) +
                (aplicaDescuento[2] ? 0.15f : 0);
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCategoria() {
        return categoria;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

}
