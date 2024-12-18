package com.bookinghotels;

import com.bookinghotels.modelos.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Alojamiento> alojamientos;

    public static void main(String[] args) {
        inizializarDatos();

    }

    public static void inizializarDatos(){

        Hotel hotel1 = new Hotel("Hotel Mar Azul", "Cartagena", 4.9f, 0, 0,
                new DiaDeSolData("actividades",new ArrayList<String>() {{
                            add("Almuerzo");
                        }}, 20000.0f), true
        );
        hotel1.agregarHabitacion(new Habitacion("Habitación Estándar", "La habitación estándar cuenta con 1 cama queen, aire acondicionado, minibar, baño privado y TV de pantalla plana.", 200000.0f, 10, 2));
        alojamientos.add(hotel1);

        Apartamento apartamento1 = new Apartamento("Apartamento Playa", "Cartagena", 4.7f, 5, 0, 500000.0f, 3, "311A" );
        alojamientos.add(apartamento1);


        Finca finca = new Finca("Finca El Bosque", "Armenia", 4.8f, 5, 0, 800000, null);
        alojamientos.add(finca);
    }


}
