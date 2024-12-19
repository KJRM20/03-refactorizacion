package com.bookinghotels.inicializacion;

import com.bookinghotels.modelos.*;

import java.util.ArrayList;
import java.util.List;

public class InicializadorDeDatos {
    public static List<Alojamiento> cargarDatosIniciales() {
        List<Alojamiento> alojamientos = new ArrayList<>();
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

        Finca finca4 = new Finca("Finca El Paraíso", "Quindío", 4.5f, 10, 5, 750000.0f, null);
        alojamientos.add(finca4);

        Finca finca5 = new Finca("Finca Los Lagos", "Cali", 4.6f, 8, 3, 650000.0f,
                new DiaDeSolData("kayak, pesca", new ArrayList<String>() {{
                    add("Cena");
                }}, 35000.0f));
        alojamientos.add(finca5);
        return alojamientos;
    }
}
