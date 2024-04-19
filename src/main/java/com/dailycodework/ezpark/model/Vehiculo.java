package com.dailycodework.ezpark.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Vehiculo {

    @Id
    private String matricula;

    @Column(name = "marca")
    private String marca;

    public String getMatricula() {
        return matricula;
    };

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    };

    public String getMarca() {
        return marca;
    };

    public void setMarca(String marca) {
        this.marca = marca;
    };

}
