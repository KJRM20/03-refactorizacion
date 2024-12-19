package com.bookinghotels.logicaNegocio;

import com.bookinghotels.modelos.Alojamiento;
import com.bookinghotels.modelos.Habitacion;
import com.bookinghotels.modelos.ReservaData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FiltroDeHabitacion {
    public List<Habitacion> confirmarAlojamiento(List<Alojamiento> alojamientos, String nombreAlojamiento, LocalDate fechaInicio, LocalDate fechaFin, List<ReservaData> reservas){
            for(Alojamiento alojamiento : alojamientos){
                if(alojamiento.getNombre().equalsIgnoreCase(nombreAlojamiento) && (alojamiento.getCategoria().equalsIgnoreCase("Hotel"))){
                    return confirmarHabitaciones(alojamiento, fechaInicio, fechaFin, reservas);
                }else if(alojamiento.getNombre().equalsIgnoreCase(nombreAlojamiento)){
                    alojamiento.mostrarInformacion();
                    break;
                }
            }
        return null;
    }

    public List<Habitacion> confirmarHabitaciones(Alojamiento alojamiento, LocalDate fechaInicio, LocalDate fechaFin, List<ReservaData> reservas){
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();

        for(Habitacion habitacion : alojamiento.getHabitaciones()){
            if(habitacion.estaDisponible(fechaInicio, fechaFin, reservas)){
                habitacion.mostrarDetalles();
                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;
    }
}
