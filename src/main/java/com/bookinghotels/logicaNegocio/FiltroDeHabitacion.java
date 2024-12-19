package com.bookinghotels.logicaNegocio;

import com.bookinghotels.modelos.Alojamiento;
import com.bookinghotels.modelos.Habitacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FiltroDeHabitacion {
    public List<Habitacion> confirmarAlojamiento(List<Alojamiento> alojamientos, String nombreAlojamiento){
            for(Alojamiento alojamiento : alojamientos){
                if(alojamiento.getNombre().equalsIgnoreCase(nombreAlojamiento) && (alojamiento.getCategoria().equalsIgnoreCase("Hotel"))){
                    return confirmarHabitaciones(alojamiento);
                }else if(alojamiento.getNombre().equalsIgnoreCase(nombreAlojamiento)){
                    alojamiento.mostrarInformacion();
                    break;
                }
            }
        return null;
    }

    public List<Habitacion> confirmarHabitaciones(Alojamiento alojamiento){
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();

        for(Habitacion habitacion : alojamiento.getHabitaciones()){
            if(habitacion.estaDisponible()){
                habitacion.mostrarDetalles();
                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;
    }
}
