package com.dailycodework.ezpark.controller;

import com.dailycodework.ezpark.model.Usuario;
import com.dailycodework.ezpark.service.IUsuarioService;
import com.dailycodework.ezpark.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @GetMapping("/todos")

    public ResponseEntity<List<Usuario>> getUsuarios() {
        return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{email}")

    public ResponseEntity<?> getUsuarioPorEmail(@PathVariable("email") String email) {
        try {
            Usuario usuario = usuarioService.getUsuarioPorEmail(email);
            return ResponseEntity.ok(usuario);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el usuario");
        }
    }

    @DeleteMapping("/eliminar/{email}")

    public ResponseEntity<String> eliminarUsuario(@PathVariable("email") String email) {
        try {
            usuarioService.eliminarUsuario(email);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}
