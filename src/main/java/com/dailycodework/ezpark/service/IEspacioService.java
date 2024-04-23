package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;

public interface IEspacioService {
    Espacio addNewEspacio(String tipo, Parqueadero parqueadero);
}
