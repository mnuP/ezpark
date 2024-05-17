package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.exception.ReservaInvalidaRequestException;
import com.dailycodework.ezpark.model.EspacioReservado;
import com.dailycodework.ezpark.dao.ReservaDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService implements IReservaService {

    private final IEspacioService espacioService;
    private final ReservaDao reservaDao;
    private static ReservaService instance;

    private ReservaService(ReservaDao reservaDao, IEspacioService espacioService){
        this.reservaDao = reservaDao;
        this.espacioService = espacioService;
    }

    public static ReservaService getInstance(ReservaDao reservaDao, IEspacioService espacioService){
        if(instance == null){
            instance = new ReservaService(reservaDao, espacioService);
        }
        return instance;
    }

    @Override
    public List<EspacioReservado> getAllReservas() {
        return reservaDao.findAll();
    }

    @Override
    public EspacioReservado findByIdReserva(Long idReserva) {
        return reservaDao.findById(idReserva).get();
    }

    @Override
    public Long saveReserva(Long idEspacio, EspacioReservado espacioReservado) {
        if (espacioReservado.getHoraFinReserva() <= espacioReservado.getHoraInicioReserva()){
            throw new ReservaInvalidaRequestException("La hora de inicio debe ser antes de la hora fin");
        }
        Espacio espacio = espacioService.getEspacioById(idEspacio).get();
        List<EspacioReservado> reservasExistentes = espacio.getReservasEspacio();

        if (espacioAvailable(espacioReservado, reservasExistentes)){
            espacio.añadirReserva(espacioReservado);
            reservaDao.save(espacioReservado);
        }else{
            throw  new ReservaInvalidaRequestException("Espacio no disponible en el dia seleccionado");
        }
        return espacioReservado.getId();
    }

    @Override
    public void cancelarReserva(Long idReserva) {
        reservaDao.deleteById(idReserva);
    }

    private boolean espacioAvailable(EspacioReservado solicitudReserva, List<EspacioReservado> reservasExistentes) {
        return reservasExistentes.stream()
                .noneMatch(reservaExistente ->
                        (solicitudReserva.getDia().equals(reservaExistente.getDia())) && (

                                (solicitudReserva.getHoraInicioReserva() == reservaExistente.getHoraInicioReserva())
                                || (solicitudReserva.getHoraFinReserva() == reservaExistente.getHoraFinReserva())
                                || (solicitudReserva.getHoraInicioReserva() < reservaExistente.getHoraInicioReserva()
                                && solicitudReserva.getHoraFinReserva() > reservaExistente.getHoraInicioReserva())
                                || (solicitudReserva.getHoraInicioReserva() > reservaExistente.getHoraInicioReserva()
                                && solicitudReserva.getHoraFinReserva() < reservaExistente.getHoraFinReserva())
                                || (solicitudReserva.getHoraInicioReserva() > reservaExistente.getHoraInicioReserva()
                                        && solicitudReserva.getHoraInicioReserva() < reservaExistente.getHoraFinReserva()
                                && solicitudReserva.getHoraFinReserva() > reservaExistente.getHoraFinReserva())
                                || (solicitudReserva.getHoraInicioReserva() < reservaExistente.getHoraInicioReserva()
                                && solicitudReserva.getHoraFinReserva() > reservaExistente.getHoraFinReserva())
                                )


                );
    }

}
