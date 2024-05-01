package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.EspacioReservado;
import com.dailycodework.ezpark.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProxyReserva implements IReservaService{

    private final ReservaRepository reservaRepository;
    private ReservaService reservaService;

    @Override
    public List<EspacioReservado> getAllReservas() {
        instantiateService();
        List<EspacioReservado> reservas = reservaService.getAllReservas();
        return reservas;
    }

    @Override
    public EspacioReservado findByIdReserva(Long idReserva) {
        instantiateService();
        EspacioReservado reserva = reservaService.findByIdReserva(idReserva);
        return reserva;
    }

    @Override
    public Long saveReserva(Long idEspacio, EspacioReservado espacioReservado) {
        instantiateService();
        Long idReserva = reservaService.saveReserva(idEspacio, espacioReservado);
        return idReserva;
    }

    @Override
    public void cancelarReserva(Long idReserva) {
        instantiateService();
        reservaService.cancelarReserva(idReserva);
    }

    private void instantiateService(){
        if(this.reservaService == null){
            this.reservaService = ReservaService.getInstance(reservaRepository);
        }
    }
}
