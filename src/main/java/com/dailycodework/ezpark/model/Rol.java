package com.dailycodework.ezpark.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<Usuario> usuarios = new HashSet<>();

    public Rol(String nombre) {
        this.nombre = nombre;
    }

    public void asignarRolAUsuario(Usuario usuario){
        usuario.getRoles().add(this);
        this.getUsuarios().add(usuario);
    }

    public void quitarUsuarioDeRol(Usuario usuario){
        usuario.getRoles().remove(this);
        this.getUsuarios().remove(usuario);
    }

    public void quitarTodosUsuariosDelRol(){
        if (this.getUsuarios() != null){
            List<Usuario> usuariosRol = this.getUsuarios().stream().toList();
            usuariosRol.forEach(this :: quitarUsuarioDeRol);
        }
    }

    public String getNombre(){
        return nombre != null? nombre : "";
    }
}
