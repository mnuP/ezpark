package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.exception.RolYaExisteException;
import com.dailycodework.ezpark.exception.UsuarioYaExisteException;
import com.dailycodework.ezpark.model.Rol;
import com.dailycodework.ezpark.model.Usuario;
import com.dailycodework.ezpark.repository.RolRepository;
import com.dailycodework.ezpark.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolService implements IRolService {
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Rol> obtenerRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol crearRol(Rol rol) {
        String nombreRol = "ROLE_" + rol.getNombre().toUpperCase();
        Rol nuevoRol = new Rol(nombreRol);
        if (rolRepository.existsByNombre(nombreRol)) {
            throw new RolYaExisteException("El rol " + rol.getNombre() + " ya existe");
        }
        return rolRepository.save(nuevoRol);
    }

    @Override
    public void eliminarRol(Long idRol) {
        this.quitarTodosUsuariosDeRol(idRol);
        rolRepository.deleteById(idRol);
    }

    @Override
    public Rol buscarPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre).orElseThrow(() -> new UsernameNotFoundException("Rol no encontrado"));
    }

    @Override
    public Usuario quitarUsuarioDeRol(Long idUsuario, Long idRol) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        Optional<Rol> rolOptional = rolRepository.findById(idRol);
        if (rolOptional.isPresent() && rolOptional.get().getUsuarios().contains(usuarioOptional.get())) {
            rolOptional.get().quitarUsuarioDeRol(usuarioOptional.get());
            rolRepository.save(rolOptional.get());
            return usuarioOptional.get();
        }
        throw new UsernameNotFoundException("Usuario no encontrado");
    }

    @Override
    public Usuario asignarRolAUsuario(Long idUsuario, Long idRol) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        Optional<Rol> rolOptional = rolRepository.findById(idRol);
        if (usuarioOptional.isPresent() && usuarioOptional.get().getRoles().contains(rolOptional.get())) {
            throw new UsuarioYaExisteException(
                    usuarioOptional.get().getNombre() + " ya está asignado al rol " + rolOptional.get().getNombre());
        }
        if (rolOptional.isPresent()) {
            rolOptional.get().asignarRolAUsuario(usuarioOptional.get());
            rolRepository.save(rolOptional.get());
        }
        return usuarioOptional.get();
    }

    @Override
    public Rol quitarTodosUsuariosDeRol(Long idRol) {
        Optional<Rol> rolOptional = rolRepository.findById(idRol);
        rolOptional.ifPresent(Rol::quitarTodosUsuariosDelRol);
        return rolRepository.save(rolOptional.get());
    }
}
