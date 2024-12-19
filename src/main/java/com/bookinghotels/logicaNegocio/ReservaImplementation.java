package com.bookinghotels.logicaNegocio;

import com.bookinghotels.modelos.Alojamiento;
import com.bookinghotels.modelos.Habitacion;
import com.bookinghotels.modelos.IReserva;
import com.bookinghotels.modelos.ReservaData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaImplementation implements IReserva {
    private List<ReservaData> reservasData;

    //Constructor
    public ReservaImplementation() {
        this.reservasData = new ArrayList<>();
    }

    // Métodos
    @Override
    public void agregarReserva(ReservaData reserva) {
        reservasData.add(reserva);
    }

    @Override
    public void eliminarReserva(ReservaData reserva) {
        reservasData.remove(reserva);
    }

    @Override
    public void actualizarReserva(String correo, LocalDate fechaNacimiento, Habitacion habitacionACambiar, Habitacion nuevaHabitacion) {
        boolean habitacionEncontrada = false;
        for(ReservaData reserva : reservasData){
            if(reserva.getCliente().getCorreo().equalsIgnoreCase(correo)
                    && reserva.getCliente().getFechaNacimiento().isEqual(fechaNacimiento)){
                List<Habitacion> habitacionesReservadas =  reserva.getHabitacionesReservadas();
                for(Habitacion habitacion : habitacionesReservadas){
                    if(habitacion.getTipo().equalsIgnoreCase(habitacionACambiar.getTipo())){
                        reserva.getHabitacionesReservadas().remove(habitacion);
                        habitacionEncontrada = true;
                        break;
                    }
                }
                if(habitacionEncontrada){
                    reserva.getHabitacionesReservadas().add(nuevaHabitacion);
                    System.out.println("Cambio éxitoso");
                    mostrarReserva(correo,fechaNacimiento);
                }else {
                    System.out.println("La habitación a cambiar no ha sido encontrada en la reserva.");
                }
            }
        }
    }

    @Override
    public void mostrarReserva(String correo, LocalDate fechaNacimiento) {
        for(ReservaData reserva : reservasData){
            if(reserva.getCliente().getCorreo().equalsIgnoreCase(correo)
                    && reserva.getCliente().getFechaNacimiento().isEqual(fechaNacimiento)){
                System.out.println("\n+-------------- Datos de la reserva --------------+");
                System.out.println("Cliente: " + reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellido());
                Alojamiento alojamiento = (Alojamiento) reserva.getAlojamiento();
                System.out.println("Alojamiento: " + alojamiento.getNombre());
                System.out.println("Categoría: " + alojamiento.getCategoria());
                System.out.println("Hora de llegada: " + reserva.getHoraLlegada());
                if(!reserva.getHabitacionesReservadas().isEmpty()){
                    System.out.println("Habitaciones: ");
                    List<Habitacion> habitacionesReservadas =  reserva.getHabitacionesReservadas();
                    for(Habitacion habitacion : habitacionesReservadas){
                        System.out.println("- " + habitacion.getTipo());
                    }
                }
                System.out.println("+-------------------------------------------------+\n");
            }
        }
    }

    public ReservaData obtenerReserva(String correo, LocalDate fechaNacimiento) {
        for(ReservaData reserva : reservasData){
            if(reserva.getCliente().getCorreo().equalsIgnoreCase(correo)
                    && reserva.getCliente().getFechaNacimiento().isEqual(fechaNacimiento)){
                return reserva;
            }
        }
        return null;
    }

    //Getters y Setters
    public List<ReservaData> getReservasData() {
        return reservasData;
    }

    public void setReservasData(List<ReservaData> reservasData) {
        this.reservasData = reservasData;
    }
}
