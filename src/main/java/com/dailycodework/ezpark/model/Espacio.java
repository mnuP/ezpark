package com.dailycodework.ezpark.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor

public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="tipo")
    private String tipo;

    @OneToMany(mappedBy = "idEspacio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EspacioReservado> reservasEspacio;

    @Column(name="idParqueadero")
    private Long parqueadero;

    public Espacio() {
        this.reservasEspacio = new ArrayList<>();
    };

    public String getTipo() {
        return tipo;
    };

    public void setTipo(String tipo) {
        this.tipo = tipo;
    };

    public Long getId() {
        return id;
    };

    public void setId(Long id) {
        this.id = id;
    };

    public void añadirReserva(EspacioReservado espacio){
        if(reservasEspacio == null){
            reservasEspacio = new ArrayList<>();
        }

        this.reservasEspacio.add(espacio);
        espacio.setIdEspacio(this.getId());
    };

    public Long getIdParqueadero() {
        return parqueadero;
    }

    public void setIdParqueadero(Long parqueadero) {
        this.parqueadero = parqueadero;
    }

    public List<EspacioReservado> getReservasEspacio() {
        return reservasEspacio;
    };
}
