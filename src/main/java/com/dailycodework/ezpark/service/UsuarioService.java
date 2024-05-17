package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.model.Usuario;
import com.dailycodework.ezpark.dao.UsuarioDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioDao usuarioDao;

    public Usuario getUsuarioPorId(Long id) {
        return usuarioDao.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
