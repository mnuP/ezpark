package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.exception.RolYaExisteException;
import com.dailycodework.ezpark.exception.UsuarioYaExisteException;
import com.dailycodework.ezpark.model.Rol;
import com.dailycodework.ezpark.model.Usuario;
import com.dailycodework.ezpark.dao.RolDao;
import com.dailycodework.ezpark.dao.UsuarioDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolService implements IRolService {
    private final RolDao rolDao;
    private final UsuarioDao usuarioDao;

    @Override
    public List<Rol> obtenerRoles() {
        return rolDao.findAll();
    }

    @Override
    public Rol crearRol(Rol rol) {
        String nombreRol = "ROLE_" + rol.getNombre().toUpperCase();
        Rol nuevoRol = new Rol(nombreRol);
        if (rolDao.existsByNombre(nombreRol)) {
            throw new RolYaExisteException("El rol " + rol.getNombre() + " ya existe");
        }
        return rolDao.save(nuevoRol);
    }

    @Override
    public void eliminarRol(Long idRol) {
        this.quitarTodosUsuariosDeRol(idRol);
        rolDao.deleteById(idRol);
    }

    @Override
    public Rol buscarPorNombre(String nombre) {
        return rolDao.findByNombre(nombre).get();
    }

    @Override
    public Usuario quitarUsuarioDeRol(Long idUsuario, Long idRol) {
        Optional<Usuario> usuarioOptional = usuarioDao.findById(idUsuario);
        Optional<Rol> rolOptional = rolDao.findById(idRol);
        if (rolOptional.isPresent() && rolOptional.get().getUsuarios().contains(usuarioOptional.get())) {
            rolOptional.get().quitarUsuarioDeRol(usuarioOptional.get());
            rolDao.save(rolOptional.get());
            return usuarioOptional.get();
        }
        throw new UsernameNotFoundException("Usuario no encontrado");
    }

    @Override
    public Usuario asignarRolAUsuario(Long idUsuario, Long idRol) {
        Optional<Usuario> usuarioOptional = usuarioDao.findById(idUsuario);
        Optional<Rol> rolOptional = rolDao.findById(idRol);
        if (usuarioOptional.isPresent() && usuarioOptional.get().getRoles().contains(rolOptional.get())) {
            throw new UsuarioYaExisteException(
                    usuarioOptional.get().getNombre() + " ya está asignado al rol " + rolOptional.get().getNombre());
        }
        if (rolOptional.isPresent()) {
            rolOptional.get().asignarRolAUsuario(usuarioOptional.get());
            rolDao.save(rolOptional.get());
        }
        return usuarioOptional.get();
    }

    @Override
    public Rol quitarTodosUsuariosDeRol(Long idRol) {
        Optional<Rol> rolOptional = rolDao.findById(idRol);
        rolOptional.ifPresent(Rol::quitarTodosUsuariosDelRol);
        return rolDao.save(rolOptional.get());
    }
}
