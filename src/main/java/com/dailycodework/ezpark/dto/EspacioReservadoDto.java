package com.dailycodework.ezpark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspacioReservadoDto {

    private Long id;

    private LocalDate dia;

    private String matriculaVehiculo;

    private String idUsuario;

    private int horaInicio;

    private int horaFin;

    private Long idEspacio;

    public EspacioReservadoDto(Long id, LocalDate dia, int horaInicio, int horaFin, String idUsuario, Long idEspacio) {
        this.id = id;
        this.dia = dia;
        this.idUsuario = idUsuario;
        this.idEspacio = idEspacio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public EspacioReservadoDto(Long id, LocalDate dia, int horaInicio, int horaFin, String idUsuario, Long idEspacio, String matriculaVehiculo) {
        this.id = id;
        this.dia = dia;
        this.idUsuario = idUsuario;
        this.idEspacio = idEspacio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.matriculaVehiculo = matriculaVehiculo;
    }
}
