package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;

import java.util.List;
import java.util.Optional;

public interface IParqueaderoService {
    Parqueadero addNewParqueadero(Long idAdministrador, String nombre);

    List<Parqueadero> getAllParqueaderos();

    Optional<Parqueadero> getParqueaderoById(Long idParqueadero);

    void deleteParqueadero(Long parqueaderoId);

    Parqueadero updateParqueadero(Long idAdministrador, String nombre);
}
