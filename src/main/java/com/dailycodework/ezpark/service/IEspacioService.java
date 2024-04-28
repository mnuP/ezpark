package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;

import java.util.List;

public interface IEspacioService {
    Espacio addNewEspacio(String tipo, Long parqueadero);

    List<Espacio> getAllEspacios();
}
