package com.bookinghotels.modelos;

public interface IReserva {
    public void agregarReserva(ReservaData reserva);
    public void eliminarReserva(ReservaData reserva);
    public void actualizarReserva();
    public void mostrarReserva();
}
