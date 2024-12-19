package com.bookinghotels.logicaNegocio;

import com.bookinghotels.modelos.Alojamiento;
import com.bookinghotels.modelos.IDiaDeSol;
import com.bookinghotels.modelos.ReservaData;
import com.bookinghotels.modelos.dto.ParametrosBusqueda;
import java.util.List;

public class FiltroDeAlojamientos {

    public boolean buscarAlojamientos(List<Alojamiento> alojamientos, List<ReservaData> reservas, ParametrosBusqueda parametros) {
        boolean hayResultados = false;
        for (Alojamiento alojamiento : alojamientos) {
            if (cumpleFiltrosGenerales(alojamiento, reservas, parametros)) {
                alojamiento.mostrarInformacion(parametros.getFechaInicio(), parametros.getFechaFin(), parametros.getCantPersonas(), parametros.getCantHabitaciones());
                hayResultados = true;
            }
            if (procesarDiaDeSol(alojamiento, parametros)) {
                hayResultados = true;
            }
        }
        return hayResultados;
    }

    public Alojamiento buscarAlojamientoPorNombre(List<Alojamiento> alojamientos, String nombre) {
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getNombre().equalsIgnoreCase(nombre)) {
                return alojamiento;
            }
        }
        return null;
    }

    // Métodos auxiliares
    private boolean cumpleFiltrosGenerales(Alojamiento alojamiento, List<ReservaData> reservas, ParametrosBusqueda parametros) {
        return alojamiento.getCiudad().equalsIgnoreCase(parametros.getCiudad()) &&
                alojamiento.getCategoria().equalsIgnoreCase(parametros.getCategoria()) &&
                alojamiento.estaDisponible(parametros.getFechaInicio(), parametros.getFechaFin(), parametros.getCantPersonas(), parametros.getCantHabitaciones(), reservas);
    }

    private boolean procesarDiaDeSol(Alojamiento alojamiento, ParametrosBusqueda parametros) {
        if (!"Día de sol".equalsIgnoreCase(parametros.getCategoria()) || !(alojamiento instanceof IDiaDeSol)) return false;

        IDiaDeSol diaDeSol = (IDiaDeSol) alojamiento;
        if (alojamiento.getCiudad().equalsIgnoreCase(parametros.getCiudad()) && diaDeSol.tieneDiaDeSol()) {
            diaDeSol.mostrarInfoDiaDeSol(parametros.getCantPersonas(), parametros.getFechaInicio());
            return true;
        }
        return false;
    }
}
