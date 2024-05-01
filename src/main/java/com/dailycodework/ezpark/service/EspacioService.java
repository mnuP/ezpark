package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.repository.EspacioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspacioService implements IEspacioService{

    private final EspacioRepository espacioRepository;
    private final ParqueaderoService parqueaderoService;

    @Override
    public Espacio addNewEspacio(String tipo, Long parqueaderoSTR) {
        Espacio espacio = new Espacio();

        Optional<Parqueadero> parqueadero = parqueaderoService.getParqueaderoById(parqueaderoSTR);
        espacio.setTipo(tipo);
        espacio.setParqueadero(parqueadero.get().getIdParqueadero());

        return espacioRepository.save(espacio);
    }

    @Override
    public List<Espacio> getAllEspacios() {
        return espacioRepository.findAll();
    }


    @Override
    public Optional<Espacio> getEspacioById(Long idEspacio) {
        return Optional.of(espacioRepository.findById(idEspacio).get());
    }
}
