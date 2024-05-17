package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario registrarUsuario(Usuario usuario);
    List<Usuario> getUsuarios();
    void eliminarUsuario(String email);
    Usuario getUsuarioPorEmail(String email);
    Usuario getUsuarioPorId(Long id);
}
