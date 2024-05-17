package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.dao.RolDao;
import com.dailycodework.ezpark.dao.UsuarioDao;
import com.dailycodework.ezpark.exception.UsuarioYaExisteException;
import com.dailycodework.ezpark.model.Rol;
import com.dailycodework.ezpark.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceAdapter implements IUsuarioService{
    private final UsuarioDao usuarioDao;
    private final PasswordEncoder passwordEncoder;
    private final RolDao rolDao;
    private final UsuarioService usuarioService;

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioDao.existsByEmail(usuario.getEmail())) {
            throw new UsuarioYaExisteException(usuario.getEmail() + " ya existe");
        }
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        System.out.println(usuario.getContraseña());
        Rol rolUsuario = rolDao.findByNombre("ROLE_USER").get();
        usuario.setRoles(Collections.singleton(rolUsuario));
        return usuarioDao.save(usuario);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Transactional
    @Override
    public void eliminarUsuario(String email) {
        Usuario theUser = getUsuarioPorEmail(email);
        if (theUser != null) {
            usuarioDao.deleteByEmail(email);
        }
    }

    @Override
    public Usuario getUsuarioPorEmail(String email) {
        return usuarioDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Override
    public Usuario getUsuarioPorId(Long id) {
        return usuarioService.getUsuarioPorId(id);
    }
}
