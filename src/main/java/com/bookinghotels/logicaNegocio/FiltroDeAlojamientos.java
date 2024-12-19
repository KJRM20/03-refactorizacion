package com.bookinghotels.logicaNegocio;

import com.bookinghotels.modelos.Alojamiento;
import com.bookinghotels.modelos.Hotel;
import com.bookinghotels.modelos.IDiaDeSol;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FiltroDeAlojamientos {

    public boolean buscarAlojamientos(List<Alojamiento> alojamientos, Map<String, Object> parametrosBusqueda) {
        boolean hayResultados = false;
        String categoria = (String) parametrosBusqueda.get("categoria");
        String ciudad = (String) parametrosBusqueda.get("ciudad");
        int maxAdultos = (int) parametrosBusqueda.get("adultos");
        int maxNinos = (int) parametrosBusqueda.get("ninos");
        int cantPersonas = maxAdultos + maxNinos;
        int cantHabitaciones = (int) parametrosBusqueda.get("cantHabitaciones");
        LocalDate fechaInicio = (LocalDate) parametrosBusqueda.get("fechaInicio");
        LocalDate fechaFin = (LocalDate) parametrosBusqueda.get("fechaFin");
        System.out.println("Tamaño de la lista de alojamientos: " + alojamientos.size());

        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.estaDisponible(fechaInicio, fechaFin, cantPersonas, cantHabitaciones)
                    && alojamiento.getCiudad().equalsIgnoreCase(ciudad)
                    && alojamiento.getCategoria().equalsIgnoreCase(categoria)) {
                alojamiento.mostrarInformacion(fechaInicio, fechaFin, cantPersonas, cantHabitaciones);
                hayResultados = true;
            }
            if (categoria.equalsIgnoreCase("Día de sol") && alojamiento instanceof IDiaDeSol
                    && alojamiento.getCiudad().equalsIgnoreCase(ciudad)) {
                IDiaDeSol diaDeSol = (IDiaDeSol) alojamiento;

                if (diaDeSol.tieneDiaDeSol()) {
                    diaDeSol.mostrarInfoDiaDeSol(cantPersonas, fechaInicio);
                    hayResultados = true;
                }
            }
        }
        return  hayResultados;
    }


}
