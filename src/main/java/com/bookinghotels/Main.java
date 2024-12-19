package com.bookinghotels;

import com.bookinghotels.logicaNegocio.FiltroDeAlojamientos;
import com.bookinghotels.logicaNegocio.FiltroDeHabitacion;
import com.bookinghotels.logicaNegocio.ReservaImplementation;
import com.bookinghotels.modelos.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {
    private static List<Alojamiento> alojamientos = new ArrayList<>();
    private static ReservaImplementation reservaImplementation = new ReservaImplementation();

    public static void main(String[] args) {
        inicializarDatos();
        mostrarLogo();
        gestionarMenu();
    }

    public static void inicializarDatos(){
        // Hoteles
        Hotel hotel1 = new Hotel("Hotel Caribe Real", "Cartagena", 4.8f, 0, 0,
                new DiaDeSolData("snorking, yoga", new ArrayList<String>() {{
                    add("Almuerzo");
                    add("Cena");
                }}, 80000.0f), true);
        hotel1.agregarHabitacion(new Habitacion("Habitación Estándar", "Cómoda y funcional, con cama doble y vista al jardín.", 250000.0f, 8, 2));
        hotel1.agregarHabitacion(new Habitacion("Habitación Premium", "Cama king-size, minibar, y balcón con vista al mar.", 400000.0f, 5, 2));
        hotel1.agregarHabitacion(new Habitacion("Habitación Familiar", "Capacidad para 4 personas, con dos camas queen y sala de estar.", 600000.0f, 4, 4));
        hotel1.agregarHabitacion(new Habitacion("Suite Nupcial", "Lujo total, jacuzzi privado y champaña de bienvenida.", 800000.0f, 2, 2));
        hotel1.agregarHabitacion(new Habitacion("Habitación Económica", "Espacio básico con cama doble y baño privado.", 150000.0f, 10, 2));
        alojamientos.add(hotel1);

        Hotel hotel2 = new Hotel("Hotel Sol Dorado", "Santa Marta", 4.7f, 0, 0,
                new DiaDeSolData("kayak, voleibol playa", new ArrayList<String>() {{
                    add("Almuerzo");
                }}, 25000.0f), true);
        hotel2.agregarHabitacion(new Habitacion("Habitación Deluxe", "Amplia habitación con vista al mar y balcón privado.", 350000.0f, 6, 3));
        hotel2.agregarHabitacion(new Habitacion("Habitación Estándar", "Cama queen-size, aire acondicionado y baño privado.", 200000.0f, 12, 2));
        hotel2.agregarHabitacion(new Habitacion("Habitación Familiar", "Capacidad para 6 personas con cocina pequeña.", 500000.0f, 5, 6));
        hotel2.agregarHabitacion(new Habitacion("Suite Presidencial", "Cama king-size, sala de estar, y jacuzzi.", 1200000.0f, 1, 2));
        hotel2.agregarHabitacion(new Habitacion("Habitación Individual", "Cama individual para viajeros solitarios.", 120000.0f, 15, 1));
        alojamientos.add(hotel2);

        Hotel hotel3 = new Hotel("Hotel Montaña Verde", "Medellín", 4.9f, 0, 0,
                new DiaDeSolData("senderismo, yoga", new ArrayList<String>() {{
                    add("Desayuno");
                }}, 20000.0f), true);
        hotel3.agregarHabitacion(new Habitacion("Habitación Económica", "Básica pero cómoda, con cama doble.", 100000.0f, 20, 2));
        hotel3.agregarHabitacion(new Habitacion("Habitación Familiar", "Amplia y con capacidad para toda la familia.", 400000.0f, 8, 6));
        hotel3.agregarHabitacion(new Habitacion("Suite Ejecutiva", "Ideal para viajeros de negocios.", 500000.0f, 3, 2));
        hotel3.agregarHabitacion(new Habitacion("Habitación Premium", "Balcón privado con vista a la montaña.", 300000.0f, 6, 2));
        hotel3.agregarHabitacion(new Habitacion("Habitación Estándar", "Cama queen y baño privado.", 200000.0f, 10, 2));
        alojamientos.add(hotel3);

        Hotel hotel4 = new Hotel("Hotel Playa Blanca", "Cartagena", 4.5f, 0, 0, null, false);
        hotel4.agregarHabitacion(new Habitacion("Habitación Vista al Mar", "Hermosa vista al mar, cama king-size.", 500000.0f, 5, 2));
        hotel4.agregarHabitacion(new Habitacion("Habitación Familiar", "Ideal para familias, con capacidad para 4 personas.", 400000.0f, 7, 4));
        hotel4.agregarHabitacion(new Habitacion("Habitación Económica", "Perfecta para presupuestos ajustados.", 150000.0f, 10, 2));
        hotel4.agregarHabitacion(new Habitacion("Suite Presidencial", "Exclusiva y lujosa, con jacuzzi privado.", 1200000.0f, 2, 2));
        hotel4.agregarHabitacion(new Habitacion("Habitación Doble", "Cómoda y funcional.", 200000.0f, 15, 2));
        alojamientos.add(hotel4);

        Hotel hotel5 = new Hotel("Hotel Bosque Mágico", "Manizales", 4.6f, 0, 0,
                new DiaDeSolData("pesca, observación de aves", new ArrayList<String>() {{
                    add("Desayuno");
                    add("Cena");
                }}, 25000.0f), true);
        hotel5.agregarHabitacion(new Habitacion("Habitación Estándar", "Espacio acogedor con cama doble.", 200000.0f, 10, 2));
        hotel5.agregarHabitacion(new Habitacion("Habitación Premium", "Balcón con vista panorámica.", 400000.0f, 4, 2));
        hotel5.agregarHabitacion(new Habitacion("Habitación Familiar", "Espaciosa y equipada para toda la familia.", 600000.0f, 5, 6));
        hotel5.agregarHabitacion(new Habitacion("Suite Ejecutiva", "Cama king-size y escritorio para trabajo.", 500000.0f, 3, 2));
        hotel5.agregarHabitacion(new Habitacion("Habitación Individual", "Perfecta para una persona.", 120000.0f, 20, 1));
        alojamientos.add(hotel5);

        // Apartamentos
        alojamientos.add(new Apartamento("Apartamento Costa Brisa", "Santa Marta", 4.6f, 5, 2, 400000.0f, 3, "201B"));
        alojamientos.add(new Apartamento("Apartamento Sol Caribe", "Cartagena", 4.8f, 4, 1, 500000.0f, 2, "101A"));
        alojamientos.add(new Apartamento("Apartamento Sierra Nevada", "Santa Marta", 4.5f, 6, 2, 450000.0f, 4, "402C"));
        alojamientos.add(new Apartamento("Apartamento Vista Azul", "Barranquilla", 4.7f, 3, 1, 380000.0f, 2, "303D"));
        alojamientos.add(new Apartamento("Apartamento Luna Blanca", "San Andrés", 4.9f, 2, 1, 600000.0f, 1, "501E"));

        // Fincas
        Finca finca1 = new Finca("Finca El Encanto", "Armenia", 4.8f, 5, 0, 800000.0f,
                new DiaDeSolData("piscina, barbacoa", new ArrayList<String>() {{
                    add("Almuerzo");
                }}, 50000.0f));
        alojamientos.add(finca1);

        Finca finca2 = new Finca("Finca La Montaña", "Manizales", 4.7f, 6, 0, 700000.0f, null);
        alojamientos.add(finca2);

        Finca finca3 = new Finca("Finca Río Claro", "Medellín", 4.9f, 4, 0, 600000.0f,
                new DiaDeSolData("paseos en bote, canopy", new ArrayList<String>() {{
                    add("Desayuno");
                }}, 40000.0f));
        alojamientos.add(finca3);

        Finca finca4 = new Finca("Finca El Paraíso", "Quindío", 4.5f, 5, 0, 750000.0f, null);
        alojamientos.add(finca4);

        Finca finca5 = new Finca("Finca Los Lagos", "Cali", 4.6f, 3, 0, 650000.0f,
                new DiaDeSolData("kayak, pesca", new ArrayList<String>() {{
                    add("Cena");
                }}, 35000.0f));
        alojamientos.add(finca5);
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

    public static Map<String, Object> formularioBuscarAlojamientos(Scanner teclado) {
        Map<String, Object> parametrosBusqueda = new HashMap<>();

        System.out.println("\n*------------------ Buscar Alojamiento --------------*");
        parametrosBusqueda.put("ciudad", obtenerEntrada("¿A cuál ciudad deseas ir?", teclado));
        String categoria = obtenerEntrada("¿Qué tipo de alojamiento buscas?", teclado);
        parametrosBusqueda.put("categoria", categoria);

        String fechaInicio = obtenerEntrada(
                categoria.equalsIgnoreCase("Día de Sol")
                        ? "Escribe el día de la estadía (YYYY-MM-dd):"
                        : "Escribe el día inicial de la estadía (YYYY-MM-dd):",
                teclado);
        parametrosBusqueda.put("fechaInicio", parseFecha(fechaInicio));

        String fechaFin = categoria.equalsIgnoreCase("Día de Sol")
                ? fechaInicio
                : obtenerEntrada("Escribe el día final de la estadía (YYYY-MM-dd):", teclado);
        parametrosBusqueda.put("fechaFin", parseFecha(fechaFin));

        parametrosBusqueda.put("adultos", obtenerEntero("Cantidad de adultos:", teclado));
        parametrosBusqueda.put("ninos", obtenerEntero("Cantidad de niños:", teclado));

        int cantHabitaciones = categoria.equalsIgnoreCase("Hotel")
                ? obtenerEntero("Cantidad de habitaciones:", teclado)
                : 0;
        parametrosBusqueda.put("cantHabitaciones", cantHabitaciones);

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
        System.out.println("Datos de confirmacion: " + datosDeConfirmacion);
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

            habitacionesDisponibles.forEach(h -> System.out.println(h.getTipo() + (h.estaDisponible(fechaInicio, fechaFin, reservaImplementation.getReservasData()) ? " (Disponible)" : " (No Disponible)")));

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
        Map<String, Object> parametrosBusqueda = formularioBuscarAlojamientos(teclado);
        LocalDate fechaInicio = (LocalDate) parametrosBusqueda.get("fechaInicio");
        LocalDate fechaFin = (LocalDate) parametrosBusqueda.get("fechaFin");

        if (!filtroDeAlojamientos.buscarAlojamientos(alojamientos, reservaImplementation.getReservasData(), parametrosBusqueda)) {
            System.out.println("\nNo se han encontrado resultados a la búsqueda.");
            return;
        }

        if (continuarProceso("¿Deseas hacer una reservación?")) {
            Map<String, Object> datosAlojamientoReserva = formularioConfirmarAlojamiento(
                    new FiltroDeHabitacion(),
                    (int) parametrosBusqueda.get("cantHabitaciones"),
                    fechaInicio, fechaFin
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
        System.out.println("Lista de habitaciones seleccionadas en reserva: " + habitacionesSeleccionadas);
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

    private static String obtenerEntrada(String mensaje, Scanner teclado) {
        System.out.println(mensaje);
        return teclado.nextLine();
    }

    private static int obtenerEntero(String mensaje, Scanner teclado) {
        while (true) {
            try {
                System.out.println(mensaje);
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
            }
        }
    }

    private static LocalDate parseFecha(String fecha) {
        try {
            return LocalDate.parse(fecha);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha inválida. Usa el formato YYYY-MM-dd.");
        }
    }

    private static LocalTime parseHora(String hora) {
        try {
            return LocalTime.parse(hora);
        } catch (Exception e) {
            throw new IllegalArgumentException("Hora inválida. Usa el formato HH:mm.");
        }
    }
}
