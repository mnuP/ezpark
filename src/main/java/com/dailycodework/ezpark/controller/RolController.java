package com.dailycodework.ezpark.controller;

import com.dailycodework.ezpark.exception.RolYaExisteException;
import com.dailycodework.ezpark.model.Rol;
import com.dailycodework.ezpark.model.Usuario;
import com.dailycodework.ezpark.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {
    private final IRolService rolService;

    @GetMapping("/todos-los-roles")
    public ResponseEntity<List<Rol>> obtenerTodosLosRoles(){
        return new ResponseEntity<>(rolService.obtenerRoles(), FOUND);
    }

    @PostMapping("/crear-nuevo-rol")
    public ResponseEntity<String> crearRol(@RequestBody Rol elRol){
        try{
            rolService.crearRol(elRol);
            return ResponseEntity.ok("¡Nuevo rol creado exitosamente!");
        }catch(RolYaExisteException re){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(re.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idRol}")
    public void eliminarRol(@PathVariable("idRol") Long idRol){
        rolService.eliminarRol(idRol);
    }

    @PostMapping("/remover-todos-los-usuarios-de-rol/{idRol}")
    public Rol removerTodosLosUsuariosDeRol(@PathVariable("idRol") Long idRol){
        return rolService.quitarTodosUsuariosDeRol(idRol);
    }

    @PostMapping("/remover-usuario-de-rol")
    public Usuario removerUsuarioDeRol(
            @RequestParam("idUsuario") Long idUsuario,
            @RequestParam("idRol") Long idRol){
        return rolService.quitarUsuarioDeRol(idUsuario, idRol);
    }

    @PostMapping("/asignar-usuario-a-rol")
    public Usuario asignarUsuarioARol(
            @RequestParam("idUsuario") Long idUsuario,
            @RequestParam("idRol") Long idRol){
        return rolService.asignarRolAUsuario(idUsuario, idRol);
    }
}
