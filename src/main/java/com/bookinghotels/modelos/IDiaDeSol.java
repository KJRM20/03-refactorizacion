package com.bookinghotels.modelos;

import java.time.LocalDate;

public interface IDiaDeSol {
    public boolean tieneDiaDeSol();
    public void mostrarInfoDiaDeSol(Integer cantPersonas, LocalDate fechaInicio);
    public float calcularPrecioBaseDiaSol(Integer cantPersonas);
}
