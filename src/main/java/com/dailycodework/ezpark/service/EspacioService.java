package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.repository.EspacioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EspacioService implements IEspacioService{

    private final EspacioRepository espacioRepository;

    @Override
    public Espacio addNewEspacio(String tipo, Parqueadero parqueadero) {
        Espacio espacio = new Espacio();

        espacio.setTipo(tipo);
        espacio.setParqueadero(parqueadero);

        return espacioRepository.save(espacio);
    }
}
