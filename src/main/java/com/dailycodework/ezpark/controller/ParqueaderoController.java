package com.dailycodework.ezpark.controller;

import com.dailycodework.ezpark.exception.ResourceNotFoundException;
import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.response.EspacioResponse;
import com.dailycodework.ezpark.response.ParqueaderoResponse;
import com.dailycodework.ezpark.service.ParqueaderoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parqueaderos")
public class ParqueaderoController {

    private final ParqueaderoService parqueaderoService;

    @PostMapping("/add/new-parqueadero")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ParqueaderoResponse> addNewParqueadero(
            @RequestParam("idAdministrador") String idAdministrador,
            @RequestParam("nombre") String nombre) {
        Parqueadero parkGuardado = parqueaderoService.addNewParqueadero(Long.parseLong(idAdministrador), nombre);
        ParqueaderoResponse response = new ParqueaderoResponse(parkGuardado.getIdAdministrador(), parkGuardado.getIdParqueadero(), parkGuardado.getNombre(), parkGuardado.getEspacios());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-parqueaderos")
    public ResponseEntity<List<ParqueaderoResponse>> getAllParqueaderos() {
        List<Parqueadero> parqueaderos = parqueaderoService.getAllParqueaderos();
        List<ParqueaderoResponse> parqueaderoResponses = new ArrayList<>();
        for (Parqueadero parqueadero : parqueaderos) {
            ParqueaderoResponse parqueaderoResponse = new ParqueaderoResponse(parqueadero.getIdAdministrador(),
                    parqueadero.getIdParqueadero(), parqueadero.getNombre(), parqueadero.getEspacios());
            parqueaderoResponses.add(parqueaderoResponse);
        }
        return ResponseEntity.ok(parqueaderoResponses);
    }

   @DeleteMapping("/delete/parqueadero/{parqueaderoId}")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteParqueadero(@PathVariable Long parqueaderoId){
        parqueaderoService.deleteParqueadero(parqueaderoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{idParqueadero}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity <ParqueaderoResponse> updateParqueadero(@RequestParam(required = false) Long idAdministrador,
                                                                  @RequestParam(required = false) String nombre){
       Parqueadero theParqueadero=parqueaderoService.updateParqueadero(idAdministrador,nombre);
       ParqueaderoResponse parqueaderoResponse=getParqueaderoResponse(theParqueadero);
       return ResponseEntity.ok(parqueaderoResponse);

    }

    @GetMapping("/parqueadero/{idParqueadero}")
    public ResponseEntity<Optional<ParqueaderoResponse>> getParqueaderoById(@PathVariable Long idParqueadero) {
        Optional<Parqueadero> theParqueadero = parqueaderoService.getParqueaderoById(idParqueadero);
        return theParqueadero.map(parqueadero -> {
            ParqueaderoResponse parqueaderoResponse = getFullParqueaderoResponse(parqueadero);
            return ResponseEntity.ok(Optional.of(parqueaderoResponse));
        }).orElseThrow(() -> new ResourceNotFoundException("Parqueadero no encontrado"));
    }



    private ParqueaderoResponse getParqueaderoResponse(Parqueadero parqueadero) {
        return new ParqueaderoResponse(
                parqueadero.getIdAdministrador(),
                parqueadero.getNombre());
    }

    private ParqueaderoResponse getFullParqueaderoResponse(Parqueadero parqueadero) {
        return new ParqueaderoResponse(
                parqueadero.getIdAdministrador(),
                parqueadero.getIdParqueadero(),
                parqueadero.getNombre(),
                parqueadero.getEspacios());
    }
}