package com.bookinghotels.modelos;

import java.time.LocalDate;

public interface IDiaDeSol {
     boolean tieneDiaDeSol();
     void mostrarInfoDiaDeSol(Integer cantPersonas, LocalDate fechaInicio);
     float calcularPrecioBaseDiaSol(Integer cantPersonas);
}
