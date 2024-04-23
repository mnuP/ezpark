package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.repository.ParqueaderoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParqueaderoService implements IParqueaderoService{

    private final ParqueaderoRepository parqueaderoRepository;

    @Override
    public Parqueadero addNewParqueadero(Long idAdministrador, String nombre) {
        Parqueadero parqueadero = new Parqueadero();  
        parqueadero.setIdAdministrador(idAdministrador);
        parqueadero.setNombre(nombre);

        return parqueaderoRepository.save(parqueadero);
    }
}
