package com.dailycodework.ezpark.dao;

import com.dailycodework.ezpark.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(Long id);
}
