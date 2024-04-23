package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;

public interface IParqueaderoService {
    Parqueadero addNewParqueadero(Long idAdministrador, String nombre);
}
