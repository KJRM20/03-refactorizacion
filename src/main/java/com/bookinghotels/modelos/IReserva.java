package com.bookinghotels.modelos;

import java.time.LocalDate;

public interface IReserva {
    public void agregarReserva(ReservaData reserva);
    public void eliminarReserva(ReservaData reserva);
    public void actualizarReserva(String correo, LocalDate fechaNacimiento, Habitacion habitacionACambiar, Habitacion nuevaHabitacion);
    public void mostrarReserva(String correo, LocalDate fechaNacimiento);
}
