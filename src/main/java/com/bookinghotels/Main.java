package com.bookinghotels;

import com.bookinghotels.logicaNegocio.FiltroDeAlojamientos;
import com.bookinghotels.logicaNegocio.FiltroDeHabitacion;
import com.bookinghotels.logicaNegocio.ReservaImplementation;
import com.bookinghotels.modelos.*;
import com.bookinghotels.modelos.dto.ParametrosBusqueda;
import com.bookinghotels.inicializacion.InicializadorDeDatos;
import com.bookinghotels.utilidades.InputUtilidades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.bookinghotels.utilidades.InputUtilidades.*;

public class Main {
    private static List<Alojamiento> alojamientos = new ArrayList<>();
    private static ReservaImplementation reservaImplementation = new ReservaImplementation();

    public static void main(String[] args) {
        inicializarDatos();
        mostrarLogo();
        gestionarMenu();
    }

    public static void inicializarDatos(){
        alojamientos = InicializadorDeDatos.cargarDatosIniciales();
    }

    public static void  mostrarLogo(){
        System.out.println("\n         ___|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|___    ");
        System.out.println("        |                                     |    ");
        System.out.println("        |      Bienvenido(a) a Book Stay      |    ");
        System.out.println("        |_____________________________________|    ");
        System.out.println("               |     |     |     |     |          \n");
    }

    public static void mostrarMenu(){
        System.out.println("\n*----------------------- Menú -----------------------*");
        System.out.println("| 1. Buscar y reservar alojamiento.                  | ");
        System.out.println("| 2. Consultar reservaciones realizadas.             | ");
        System.out.println("| 3. Modificar una reservación.                      | ");
        System.out.println("| 0. Salir.                                          | ");
        System.out.println("*----------------------------------------------------*\n");
    }

    public static void gestionarMenu(){
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        while (!salir){
            mostrarMenu();
            System.out.println("Ingresa la opción que deseas realizar: ");
            int opcion = teclado.nextInt();
            switch (opcion) {
                case 0 -> {
                    System.out.println("\n¡Gracias por usar nuestros servicios!\n");
                    salir = true;
                }
                case 1 -> gestionarOpcionBuscarYReservar();
                case 2 -> System.out.println("Consultar reservaciones. (Funcionalidad en desarrollo)");
                case 3 -> gestionarOpcionModificarReserva();
                default -> System.out.println("\nOpción no válida, intenta nuevamente.");
            }
        }
    }

    public static ParametrosBusqueda formularioBuscarAlojamientos(Scanner teclado) {
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

    public static Map<String, Object> formularioConfirmarAlojamiento(FiltroDeHabitacion filtroDeHabitacion, int cantHabitaciones, LocalDate fechaInicio, LocalDate fechaFin){
        Scanner teclado = new Scanner(System.in);
        Map<String,Object> datosDeConfirmacion = new HashMap<>();

        System.out.println("\n*------------------ Confirmar el Alojamiento --------------*");
        System.out.println("Escribe el nombre del alojamiento en que deseas realizar la reserva: ");
        String alojamiento = teclado.nextLine();
        datosDeConfirmacion.put("nombreAlojamiento", alojamiento);
        List<Habitacion> habitacionesDisponibles= filtroDeHabitacion.confirmarAlojamiento(alojamientos, alojamiento, fechaInicio, fechaFin, reservaImplementation.getReservasData());
        System.out.println("\nSelecciona cuántas habitaciones deseas reservar para cada tipo:\n");
        Map<String, List<Habitacion>> habitacionesSeleccionadas = new HashMap<>();
        if (habitacionesDisponibles != null) {
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
        System.out.println("\nHabitaciones seleccionadas: " + habitacionesSeleccionadas);
        return datosDeConfirmacion;
    }

    public static Map<String, Object> formularioHacerReserva(Scanner teclado) {
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

    public static Map<String, String> formularioValidarDatos() {
        Scanner teclado = new Scanner(System.in);
        Map<String, String> datosAValidar = new HashMap<>();

        datosAValidar.put("correo", obtenerEntrada("Ingresa tu correo electrónico: ", teclado));
        datosAValidar.put("fechaNacimiento", obtenerEntrada("Ingresa tu fecha de nacimiento (YYYY-MM-dd): ", teclado));

        return datosAValidar;
    }

    public static Map<String, String> formularioModificarReserva(Alojamiento alojamiento, LocalDate fechaInicio, LocalDate fechaFin) {
        Scanner teclado = new Scanner(System.in);
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

    public static void gestionarOpcionBuscarYReservar() {
        Scanner teclado = new Scanner(System.in);
        FiltroDeAlojamientos filtroDeAlojamientos = new FiltroDeAlojamientos();
        ParametrosBusqueda parametrosBusqueda = formularioBuscarAlojamientos(teclado);

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
                Map<String, Object> datosClienteReserva = formularioHacerReserva(teclado);
                crearReserva(datosAlojamientoReserva, datosClienteReserva);
            } else {
                System.out.println("\nProceso de reserva cancelado.");
            }
        }
        System.out.println("Serás redirigido(a) al menú principal. Espera un momento...");
    }

    public static void gestionarOpcionModificarReserva() {
        System.out.println("\n*-------------------- Modificar Reservación ----------------*");
        Map<String, String> datosAValidar = formularioValidarDatos();

        try {
            LocalDate fechaNacimiento = parseFecha(datosAValidar.get("fechaNacimiento"));
            ReservaData reserva = (ReservaData) reservaImplementation.obtenerReserva(datosAValidar.get("correo"), fechaNacimiento);

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

    private static void crearReserva(Map<String, Object> datosAlojamiento, Map<String, Object> datosCliente) {
        ClienteData clienteData = (ClienteData) datosCliente.get("clienteData");
        LocalDate fechaInicio = (LocalDate) datosAlojamiento.get("fechaInicio");
        LocalDate fechaFin = (LocalDate) datosAlojamiento.get("fechaFin");
        LocalTime horaLlegada = (LocalTime) datosCliente.get("horaLlegada");

        // Obtener lista de habitaciones
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

    public static boolean continuarProceso(String mensaje){
        Scanner teclado = new Scanner(System.in);
        System.out.println( "\n" + mensaje + " (Si - No): ");
        String respuesta = teclado.nextLine();
        if(respuesta.equalsIgnoreCase("Si")){
            return true;
        }
        return false;
    }
    
}
