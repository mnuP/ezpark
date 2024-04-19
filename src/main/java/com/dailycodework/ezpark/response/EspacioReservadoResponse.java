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

    private Espacio espacio;

    public EspacioReservadoResponse(Long id, LocalDate dia) {
        this.id = id;
        this.dia = dia;
    }
}
