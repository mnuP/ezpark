package com.dailycodework.ezpark.response;

import com.dailycodework.ezpark.model.Espacio;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspacioReservadoResponse {

    private Long id;

    private LocalDate dia;

    private String matriculaVehiculo;

    private String idUsuario;

    private int horaInicio;

    private int horaFin;

    private Long idEspacio;

    public EspacioReservadoResponse(Long id, LocalDate dia, int horaInicio, int horaFin, String idUsuario, Long idEspacio) {
        this.id = id;
        this.dia = dia;
        this.idUsuario = idUsuario;
        this.idEspacio = idEspacio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}
