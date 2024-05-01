package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.EspacioReservado;
import com.dailycodework.ezpark.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;
    private static ReservaService instance;

    private ReservaService(ReservaRepository reservaRepository){
        this.reservaRepository = reservaRepository;
    }

    public static ReservaService getInstance(ReservaRepository reservaRepository){
        if(instance == null){
            instance = new ReservaService(reservaRepository);
        }
        return instance;
    }

    @Override
    public List<EspacioReservado> getAllReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public EspacioReservado findByIdReserva(Long idReserva) {
        return reservaRepository.findById(idReserva).get();
    }

    @Override
    public Long saveReserva(Long idEspacio, EspacioReservado espacioReservado) {
        return Long.parseLong("0");
    }

    @Override
    public void cancelarReserva(Long idReserva) {
        reservaRepository.deleteById(idReserva);
    }
}
