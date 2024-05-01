package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.EspacioReservado;
import java.util.List;

public interface IReservaService {
    List<EspacioReservado> getAllReservas();

    EspacioReservado findByIdReserva(Long idReserva);

    Long saveReserva(Long idEspacio, EspacioReservado espacioReservado);

    void cancelarReserva(Long idReserva);
}
