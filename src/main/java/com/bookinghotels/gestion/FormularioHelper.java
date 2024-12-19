package com.bookinghotels.gestion;

import java.time.LocalDate;
import java.util.*;

import static com.bookinghotels.utilidades.InputUtilidades.*;

import com.bookinghotels.Main;
import com.bookinghotels.logicaNegocio.FiltroDeHabitacion;
import com.bookinghotels.logicaNegocio.ReservaImplementation;
import com.bookinghotels.modelos.Alojamiento;
import com.bookinghotels.modelos.ClienteData;
import com.bookinghotels.modelos.Habitacion;
import com.bookinghotels.modelos.Hotel;
import com.bookinghotels.modelos.dto.ParametrosBusqueda;

public class FormularioHelper {

    private static final Scanner teclado = new Scanner(System.in);
    private static List<Alojamiento> alojamientos = Main.getAlojamientos();
    private static ReservaImplementation reservaImplementation = Main.getReservaImplementation();

    public static ParametrosBusqueda formularioBuscarAlojamientos() {
        ParametrosBusqueda parametrosBusqueda = new ParametrosBusqueda();

        System.out.println("\n*------------------ Buscar Alojamiento --------------*");
        parametrosBusqueda.setCiudad(obtenerEntrada("¿A cuál ciudad deseas ir?", teclado));
        String categoria = obtenerEntrada("¿Qué tipo de alojamiento buscas?", teclado);
        parametrosBusqueda.setCategoria(categoria);

        String fechaInicio = obtenerEntrada(
                categoria.equalsIgnoreCase("Día de Sol")
                        ? "Escribe el día de la estadía (YYYY-MM-dd):"
                        : "Escribe el día inicial de la estadía (YYYY-MM-dd):",
                teclado);
        parametrosBusqueda.setFechaInicio(parseFecha(fechaInicio));

        String fechaFin = categoria.equalsIgnoreCase("Día de Sol")
                ? fechaInicio
                : obtenerEntrada("Escribe el día final de la estadía (YYYY-MM-dd):", teclado);
        parametrosBusqueda.setFechaFin(parseFecha(fechaFin));

        int adultos = obtenerEntero("Cantidad de adultos:", teclado);
        int ninos = obtenerEntero("Cantidad de niños:", teclado);
        parametrosBusqueda.setCantPersonas(adultos + ninos);

        int cantHabitaciones = categoria.equalsIgnoreCase("Hotel")
                ? obtenerEntero("Cantidad de habitaciones:", teclado)
                : 0;
        parametrosBusqueda.setCantHabitaciones(cantHabitaciones);

        return parametrosBusqueda;
    }

    public static Map<String, Object> formularioConfirmarAlojamiento( FiltroDeHabitacion filtroDeHabitacion, int cantHabitaciones, LocalDate fechaInicio, LocalDate fechaFin){
        Map<String,Object> datosDeConfirmacion = new HashMap<>();

        System.out.println("\n*------------------ Confirmar el Alojamiento --------------*");
        System.out.println("Escribe el nombre del alojamiento en que deseas realizar la reserva: ");
        String alojamiento = teclado.nextLine();
        datosDeConfirmacion.put("nombreAlojamiento", alojamiento);
        List<Habitacion> habitacionesDisponibles= filtroDeHabitacion.confirmarAlojamiento(alojamientos, alojamiento, fechaInicio, fechaFin, reservaImplementation.getReservasData());
        Map<String, List<Habitacion>> habitacionesSeleccionadas = new HashMap<>();
        if (habitacionesDisponibles != null) {
            System.out.println("\nSelecciona cuántas habitaciones deseas reservar para cada tipo:\n");
            int habitacionesTotalesSeleccionadas = 0;
            for (Habitacion habitacion : habitacionesDisponibles) {
                if (habitacionesTotalesSeleccionadas >= cantHabitaciones) {
                    break;
                }
                System.out.print("¿Cuántas '" + habitacion.getTipo() + "' deseas reservar?: ");
                int cantidad = teclado.nextInt();
                teclado.nextLine();

                if (habitacionesTotalesSeleccionadas + cantidad > cantHabitaciones) {
                    System.out.println("\nHas seleccionado más habitaciones de las requeridas, se ajustará a lo establecido.");
                    cantidad = cantHabitaciones - habitacionesTotalesSeleccionadas;
                }

                habitacionesTotalesSeleccionadas += cantidad;
                if (!habitacionesSeleccionadas.containsKey(habitacion.getTipo())) {
                    habitacionesSeleccionadas.put(habitacion.getTipo(), new ArrayList<>());
                }

                for (int i = 0; i < cantidad; i++) {
                    habitacionesSeleccionadas.get(habitacion.getTipo()).add(habitacion);
                }
            }
            datosDeConfirmacion.put("habitacionesSeleccionadas", habitacionesSeleccionadas);
        }
        return datosDeConfirmacion;
    }

    public static Map<String, Object> formularioHacerReserva() {
        Map<String, Object> datosDeReserva = new HashMap<>();
        System.out.println("\n*----- Datos personales de la Reservación -----*");

        ClienteData cliente = new ClienteData(
                obtenerEntrada("Nombre:", teclado),
                obtenerEntrada("Apellido:", teclado),
                parseFecha(obtenerEntrada("Fecha de nacimiento (YYYY-MM-dd):", teclado)),
                obtenerEntrada("Número de teléfono:", teclado),
                obtenerEntrada("Correo electrónico:", teclado),
                obtenerEntrada("Nacionalidad:", teclado)
        );

        datosDeReserva.put("clienteData", cliente);
        datosDeReserva.put("horaLlegada",
                parseHora(obtenerEntrada("Hora de llegada (HH:mm):", teclado)));

        return datosDeReserva;
    }

    public static Map<String, String> formularioModificarReserva( Alojamiento alojamiento, LocalDate fechaInicio, LocalDate fechaFin) {
        Map<String, String> datosActualizar = new HashMap<>();

        System.out.println("\n¿Qué deseas modificar?");
        if (alojamiento instanceof Hotel) {
            System.out.println("1. Cambio de habitación");
        }
        System.out.println("2. Cambio de alojamiento");
        System.out.println("3. Cancelar operación");
        int opcion = obtenerEntero("Selecciona una opción (1, 2, 3): ", teclado);
        datosActualizar.put("opcion", String.valueOf(opcion));

        if (opcion == 1 && alojamiento instanceof Hotel) {
            FiltroDeHabitacion filtroDeHabitacion = new FiltroDeHabitacion();
            String antiguaHabitacion = obtenerEntrada("Indica el tipo de habitación que deseas cambiar (ejemplo: 'Suite'): ", teclado);

            System.out.println("\nHabitaciones disponibles: ");
            List<Habitacion> habitacionesDisponibles = filtroDeHabitacion.confirmarAlojamiento(alojamientos, alojamiento.getNombre(), fechaInicio, fechaFin, reservaImplementation.getReservasData());

            String nuevaHabitacion = obtenerEntrada("Selecciona el nuevo tipo de habitación: ", teclado);
            boolean habitacionValida = habitacionesDisponibles.stream()
                    .anyMatch(h -> h.getTipo().equalsIgnoreCase(nuevaHabitacion) && h.estaDisponible(fechaInicio, fechaFin, reservaImplementation.getReservasData()));

            if (habitacionValida) {
                datosActualizar.put("antiguaHabitacion", antiguaHabitacion);
                datosActualizar.put("nuevaHabitacion", nuevaHabitacion);
            } else {
                System.out.println("La nueva habitación seleccionada no es válida o no está disponible.");
                datosActualizar.put("opcion", "3");
            }
        }

        return datosActualizar;
    }

    public static Map<String, String> formularioValidarDatos() {
        Map<String, String> datosAValidar = new HashMap<>();

        datosAValidar.put("correo", obtenerEntrada("Ingresa tu correo electrónico: ", teclado));
        datosAValidar.put("fechaNacimiento", obtenerEntrada("Ingresa tu fecha de nacimiento (YYYY-MM-dd): ", teclado));

        return datosAValidar;
    }

    public static boolean continuarProceso(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        String respuesta = obtenerEntrada("\n" + mensaje + " (Si - No):", teclado);
        return respuesta.equalsIgnoreCase("Si");
    }
}
