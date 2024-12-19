package com.bookinghotels.modelos;

import java.time.LocalDate;

public class ClienteData {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String numeroTel;
    private String correo;
    private String nacionalidad;

    // Constructor
    public ClienteData(String nombre, String apellido, LocalDate fechaNacimiento, String numeroTel, String correo, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroTel = numeroTel;
        this.correo = correo;
        this.nacionalidad = nacionalidad;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

}
