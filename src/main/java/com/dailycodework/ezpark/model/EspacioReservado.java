package com.dailycodework.ezpark.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EspacioReservado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diaReserva")
    private LocalDate dia;

    @Column(name = "horaInicioReserva")
    private int horaInicioReserva;

    @Column(name = "horaFinReserva")
    private int horaFinReserva;

    @Column(name = "matriculaVehiculo")
    private String matriculaVehiculo;

    @Column(name = "idUsuario")
    private String idUsuario;

    @Column(name = "idEspacio")
    private Long idEspacio;

    @Column(name = "valorReserva")
    private Long valorReserva;


    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(Long espacio) {
        this.idEspacio = espacio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHoraInicioReserva() {
        return horaInicioReserva;
    }

    public void setHoraInicioReserva(int horaInicioReserva) {
        this.horaInicioReserva = horaInicioReserva;
    }

    public int getHoraFinReserva() {
        return horaFinReserva;
    }

    public void setHoraFinReserva(int horaFinReserva) {
        this.horaFinReserva = horaFinReserva;
    }

    public Long getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(Long valorReserva) {
        this.valorReserva = valorReserva;
    }
}
