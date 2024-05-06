
package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Rol;
import com.dailycodework.ezpark.model.Usuario;

import java.util.List;

public interface IRolService {
    List<Rol> obtenerRoles();
    Rol crearRol(Rol rol);

    void eliminarRol(Long id);
    Rol buscarPorNombre(String nombre);

    Usuario quitarUsuarioDeRol(Long idUsuario, Long idRol);
    Usuario asignarRolAUsuario(Long idUsuario, Long idRol);
    Rol quitarTodosUsuariosDeRol(Long idRol);
}
