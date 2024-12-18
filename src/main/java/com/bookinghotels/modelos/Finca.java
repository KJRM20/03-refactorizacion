package com.bookinghotels.modelos;

import java.time.LocalDate;

public class Finca extends Alojamiento implements IDiaDeSol{
    private DiaDeSolData diaDeSol;

    // Constructores
    public Finca(String nombre, String ciudad, Float calificacion, Integer maxAdultos, Integer maxNinos, DiaDeSolData diaDeSol) {
        super(nombre, ciudad, calificacion, maxAdultos, maxNinos);
        this.diaDeSol = diaDeSol;
    }

    // Métodos
    @Override
    public boolean estaDisponible(LocalDate fechaInicio, LocalDate fechaFin, int cantPersonas, int cantHabitaciones) {
        return false;
    }

    @Override
    public float calcularPrecioBase(LocalDate fechaInicio, LocalDate fechaFin, int cantPersonas, int cantHabitaciones) {
        return 0;
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

    // Getters y Setters
    public DiaDeSolData getDiaDeSol() {
        return diaDeSol;
    }

    public void setDiaDeSol(DiaDeSolData diaDeSol) {
        this.diaDeSol = diaDeSol;
    }
}
