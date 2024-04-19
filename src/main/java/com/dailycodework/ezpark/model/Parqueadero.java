package com.dailycodework.ezpark.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parqueadero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParqueadero;
    private Long idAdministrador;
    private String nombre;

    @OneToMany(mappedBy="parqueadero", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Espacio> espacios;

    @Lob
    private Blob photo;

    public Parqueadero(String idAdministrador, String nombre) {
        this.espacios = new ArrayList<>();
    }

    public Long getIdAdministrador() {
        return idAdministrador;
    };

    public void setIdAdministrador(Long idAdministrador) {
        this.idAdministrador = idAdministrador;
    };

    public String getNombre() {
        return nombre;
    };

    public void setNombre(String nombre) {
        this.nombre = nombre;
    };

    public List<Espacio> getEspacios() {
        return espacios;
    }

    public void setEspacios(List<Espacio> espacios) {
        this.espacios = espacios;
    }

    public void añadirEspacio(Espacio espacio) {
        if(espacios == null) {
            espacios = new ArrayList<>();
        }

        this.espacios.add(espacio);
        espacio.setParqueadero(this);
    };

    public Long getIdParqueadero() {
        return idParqueadero;
    }

    public void setIdParqueadero(Long idParqueadero) {
        this.idParqueadero = idParqueadero;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }
}
