package com.dailycodework.ezpark.dto;

import com.dailycodework.ezpark.model.Espacio;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ParqueaderoDto {

    private Long idParqueadero;
    private Long idAdministrador;
    private String nombre;
    private List<Espacio> espacioResponses;

    public ParqueaderoDto(Long idAministrador, String nombre) {
        this.idAdministrador = idAministrador;
        this.nombre = nombre;
    }

    public ParqueaderoDto(Long idAministrador, Long idParqueadero, String nombre, List<Espacio> espacioResponses) {
        this.idAdministrador = idAministrador;
        this.nombre = nombre;
        this.espacioResponses = espacioResponses;
        this.idParqueadero = idParqueadero;
    }
}
