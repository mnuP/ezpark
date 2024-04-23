package com.dailycodework.ezpark.response;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ParqueaderoResponse {

    private Long idParqueadero;
    private Long idAdministrador;
    private String nombre;
    private List<Espacio> espacioResponses;

    public ParqueaderoResponse(Long idAministrador, String nombre) {
        this.idAdministrador = idAministrador;
        this.nombre = nombre;
    }

    public ParqueaderoResponse(Long idAministrador, Long idParqueadero, String nombre, List<Espacio> espacioResponses) {
        this.idAdministrador = idAministrador;
        this.nombre = nombre;
        this.espacioResponses = espacioResponses;
        this.idParqueadero = idParqueadero;
    }
}
