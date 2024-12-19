package com.bookinghotels;

import com.bookinghotels.gestion.GestorMenus;
import com.bookinghotels.logicaNegocio.FiltroDeAlojamientos;
import com.bookinghotels.logicaNegocio.FiltroDeHabitacion;
import com.bookinghotels.logicaNegocio.ReservaImplementation;
import com.bookinghotels.modelos.*;
import com.bookinghotels.modelos.dto.ParametrosBusqueda;
import com.bookinghotels.inicializacion.InicializadorDeDatos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.bookinghotels.gestion.FormularioHelper.*;
import static com.bookinghotels.utilidades.InputUtilidades.*;

public class Main {
    private static List<Alojamiento> alojamientos = new ArrayList<>();
    private static ReservaImplementation reservaImplementation = new ReservaImplementation();

    public static void main(String[] args) {
        inicializarDatos();
        gestionarMenu();
    }

    public static void inicializarDatos(){
        alojamientos = InicializadorDeDatos.cargarDatosIniciales();
    }

    public static void gestionarMenu(){
        new GestorMenus().gestionarOpciones();
    }

    public static void gestionarOpcionBuscarYReservar() {
        Scanner teclado = new Scanner(System.in);
        FiltroDeAlojamientos filtroDeAlojamientos = new FiltroDeAlojamientos();
        ParametrosBusqueda parametrosBusqueda = formularioBuscarAlojamientos();

        if (!filtroDeAlojamientos.buscarAlojamientos(alojamientos, reservaImplementation.getReservasData(), parametrosBusqueda)) {
            System.out.println("\nNo se han encontrado resultados a la búsqueda.");
            return;
        }

        if (continuarProceso("¿Deseas hacer una reservación?")) {
            Map<String, Object> datosAlojamientoReserva = formularioConfirmarAlojamiento(
                    new FiltroDeHabitacion(),
                    parametrosBusqueda.getCantHabitaciones(),
                    parametrosBusqueda.getFechaInicio(), parametrosBusqueda.getFechaFin()
            );

            if (continuarProceso("¿Confirmas la selección?")) {
                Map<String, Object> datosClienteReserva = formularioHacerReserva();
                crearReserva(datosAlojamientoReserva, datosClienteReserva);
            } else {
                System.out.println("\nProceso de reserva cancelado.");
            }
        }
        System.out.println("Serás redirigido(a) al menú principal. Espera un momento...");
    }

    public static void gestionarOpcionModificarReserva() {
        System.out.println("\n*-------------------- Modificar Reservación ----------------*");
        Map<String, String> datosAValidar =  formularioValidarDatos();

        try {
            LocalDate fechaNacimiento = parseFecha(datosAValidar.get("fechaNacimiento"));
            ReservaData reserva = reservaImplementation.obtenerReserva(datosAValidar.get("correo"), fechaNacimiento);

            reservaImplementation.mostrarReserva(datosAValidar.get("correo"), fechaNacimiento);
            Alojamiento alojamiento = (Alojamiento) reserva.getAlojamiento();

            Map<String, String> datosActualizar = formularioModificarReserva(alojamiento, reserva.getFechaInicio(), reserva.getFechaFin());

            switch (datosActualizar.get("opcion")) {
                case "1":
                    procesarCambioHabitacion(reserva, datosActualizar);
                    break;
                case "2":
                    reservaImplementation.eliminarReserva(reserva);
                    System.out.println("La reserva ha sido eliminada.");
                    break;
                case "3":
                    System.out.println("Operación cancelada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Serás redirigido(a) al menú principal. Espera un momento...");
    }

    public static void gestionarOpcionConsultarReserva(){
        System.out.println("\n*-------------------- Consultar Reservación ----------------*");
        Map<String, String> datosAValidar =  formularioValidarDatos();
        LocalDate fechaNacimiento = parseFecha(datosAValidar.get("fechaNacimiento"));
        ReservaData reserva = reservaImplementation.obtenerReserva(datosAValidar.get("correo"), fechaNacimiento);
        reservaImplementation.mostrarReserva(datosAValidar.get("correo"), fechaNacimiento);
    }

    private static void crearReserva(Map<String, Object> datosAlojamiento, Map<String, Object> datosCliente) {
        ClienteData clienteData = (ClienteData) datosCliente.get("clienteData");
        LocalDate fechaInicio = (LocalDate) datosAlojamiento.get("fechaInicio");
        LocalDate fechaFin = (LocalDate) datosAlojamiento.get("fechaFin");
        LocalTime horaLlegada = (LocalTime) datosCliente.get("horaLlegada");

        List<Habitacion> listaHabitaciones = new ArrayList<>();
        Map<String, List<Habitacion>> habitacionesSeleccionadas =
                (Map<String, List<Habitacion>>) datosAlojamiento.get("habitacionesSeleccionadas");
        if (habitacionesSeleccionadas != null) {
            habitacionesSeleccionadas.values().forEach(listaHabitaciones::addAll);
        }

        Alojamiento alojamiento = new FiltroDeAlojamientos()
                .buscarAlojamientoPorNombre(alojamientos, (String) datosAlojamiento.get("nombreAlojamiento"));

        ReservaData reserva = new ReservaData(alojamiento, clienteData, fechaInicio, fechaFin, horaLlegada, listaHabitaciones);
        reservaImplementation.agregarReserva(reserva);
        System.out.println("Se ha realizado la reserva con éxito!");
        reservaImplementation.mostrarReserva(clienteData.getCorreo(), clienteData.getFechaNacimiento());
    }

    private static void procesarCambioHabitacion(ReservaData reserva, Map<String, String> datosActualizar) {
        Alojamiento alojamiento = (Alojamiento) reserva.getAlojamiento();
        List<Habitacion> habitaciones = alojamiento.getHabitaciones();
        Habitacion antiguaHabitacion = null, nuevaHabitacion = null;

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo().equalsIgnoreCase(datosActualizar.get("antiguaHabitacion"))) {
                antiguaHabitacion = habitacion;
            }
            if (habitacion.getTipo().equalsIgnoreCase(datosActualizar.get("nuevaHabitacion"))) {
                nuevaHabitacion = habitacion;
            }
        }

        if (antiguaHabitacion != null && nuevaHabitacion != null && nuevaHabitacion.estaDisponible(reserva.getFechaInicio(), reserva.getFechaFin(), reservaImplementation.getReservasData())) {
            reservaImplementation.actualizarReserva(
                    reserva.getCliente().getCorreo(),
                    reserva.getCliente().getFechaNacimiento(),
                    antiguaHabitacion,
                    nuevaHabitacion
            );
            System.out.println("Cambio de habitación realizado con éxito.");
        } else {
            System.out.println("No se pudo realizar el cambio de habitación.");
        }
    }

    //Getters
    public static List<Alojamiento> getAlojamientos() {
        return new ArrayList<>(alojamientos);
    }

    public static ReservaImplementation getReservaImplementation() {
        return reservaImplementation;
    }
}
