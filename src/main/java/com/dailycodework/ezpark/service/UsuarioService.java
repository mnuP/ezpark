package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.exception.UsuarioYaExisteException;
import com.dailycodework.ezpark.model.Rol;
import com.dailycodework.ezpark.model.Usuario;
import com.dailycodework.ezpark.repository.RolRepository;
import com.dailycodework.ezpark.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new UsuarioYaExisteException(usuario.getEmail() + " ya existe");
        }
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        System.out.println(usuario.getContraseña());
        Rol rolUsuario = rolRepository.findByNombre("ROLE_USER").get();
        usuario.setRoles(Collections.singleton(rolUsuario));
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }


    @Transactional
    @Override
    public void eliminarUsuario(String email) {
        Usuario theUser = getUsuarioPorEmail(email);
        if (theUser != null) {
            usuarioRepository.deleteByEmail(email);
        }
    }

    @Override
    public Usuario getUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
