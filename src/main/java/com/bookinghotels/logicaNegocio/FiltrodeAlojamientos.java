package com.bookinghotels.logicaNegocio;

import com.bookinghotels.modelos.Alojamiento;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FiltrodeAlojamientos {

    public void buscarAlojamientos(List<Alojamiento> alojamientos, Map<String, Object> parametrosBusqueda) {
        int maxAdultos = (int) parametrosBusqueda.get("maxAdultos");
        int maxNinos = (int) parametrosBusqueda.get("maxNinos");
        int cantPersonas = maxAdultos + maxNinos;
        int cantHabitaciones = (int) parametrosBusqueda.get("cantHabitaciones");
        LocalDate fechaInicio = (LocalDate) parametrosBusqueda.get("fechaInicio");
        LocalDate fechaFin = (LocalDate) parametrosBusqueda.get("fechaFin");

        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.estaDisponible(fechaInicio, fechaFin, cantPersonas, cantHabitaciones)) {
                alojamiento.mostrarInformacion(fechaInicio, fechaFin, cantPersonas, cantHabitaciones);
            }
        }
    }


}
