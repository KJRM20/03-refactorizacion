package com.bookinghotels.utilidades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class InputUtilidades {
    public static String obtenerEntrada(String mensaje, Scanner teclado) {
        System.out.println(mensaje);
        return teclado.nextLine();
    }

    public static int obtenerEntero(String mensaje, Scanner teclado) {
        while (true) {
            try {
                System.out.println(mensaje);
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
            }
        }
    }

    public static LocalDate parseFecha(String fecha) {
        try {
            return LocalDate.parse(fecha);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha inválida. Usa el formato YYYY-MM-dd.");
        }
    }

    public static LocalTime parseHora(String hora) {
        try {
            return LocalTime.parse(hora);
        } catch (Exception e) {
            throw new IllegalArgumentException("Hora inválida. Usa el formato HH:mm.");
        }
    }
}
