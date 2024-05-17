package com.dailycodework.ezpark.controller;

import com.dailycodework.ezpark.exception.ResourceNotFoundException;
import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.dto.ParqueaderoDto;
import com.dailycodework.ezpark.service.ParqueaderoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parqueaderos")
public class ParqueaderoController {

    private final ParqueaderoService parqueaderoService;

    @PostMapping("/add/new-parqueadero")
    public ResponseEntity<ParqueaderoDto> addNewParqueadero(
            @RequestParam(value = "idAdministrador", required = false) String idAdministrador,
            @RequestParam(value = "nombre", required = false) String nombre) {


        // Log parameters to debug
        System.out.println("idAdministrador: " + idAdministrador);
        System.out.println("nombre: " + nombre);

        Parqueadero parkGuardado = parqueaderoService.addNewParqueadero(Long.parseLong(idAdministrador), nombre);
        ParqueaderoDto response = new ParqueaderoDto(
                parkGuardado.getIdAdministrador(),
                parkGuardado.getIdParqueadero(),
                parkGuardado.getNombre(),
                parkGuardado.getEspacios()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-parqueaderos")
    public ResponseEntity<List<ParqueaderoDto>> getAllParqueaderos() {
        List<Parqueadero> parqueaderos = parqueaderoService.getAllParqueaderos();
        List<ParqueaderoDto> parqueaderoRespons = new ArrayList<>();
        for (Parqueadero parqueadero : parqueaderos) {
            ParqueaderoDto parqueaderoDto = new ParqueaderoDto(parqueadero.getIdAdministrador(),
                    parqueadero.getIdParqueadero(), parqueadero.getNombre(), parqueadero.getEspacios());
            parqueaderoRespons.add(parqueaderoDto);
        }
        return ResponseEntity.ok(parqueaderoRespons);
    }

   @DeleteMapping("/delete/parqueadero/{parqueaderoId}")
    public ResponseEntity<Void> deleteParqueadero(@PathVariable Long parqueaderoId){
        parqueaderoService.deleteParqueadero(parqueaderoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{idParqueadero}")
    public ResponseEntity <ParqueaderoDto> updateParqueadero(@PathVariable String idParqueadero,
                                                             @RequestParam("nombre") String nombre){
        System.out.println("controller: "+nombre);
       Parqueadero theParqueadero=parqueaderoService.updateParqueadero(Long.parseLong(idParqueadero),nombre);
       ParqueaderoDto parqueaderoDto =getParqueaderoResponse(theParqueadero);
       return ResponseEntity.ok(parqueaderoDto);
    }

    @GetMapping("/parqueadero/{idParqueadero}")
    public ResponseEntity<Optional<ParqueaderoDto>> getParqueaderoById(@PathVariable Long idParqueadero) {
        Optional<Parqueadero> theParqueadero = parqueaderoService.getParqueaderoById(idParqueadero);
        return theParqueadero.map(parqueadero -> {
            ParqueaderoDto parqueaderoDto = getFullParqueaderoResponse(parqueadero);
            return ResponseEntity.ok(Optional.of(parqueaderoDto));
        }).orElseThrow(() -> new ResourceNotFoundException("Parqueadero no encontrado"));
    }



    private ParqueaderoDto getParqueaderoResponse(Parqueadero parqueadero) {
        return new ParqueaderoDto(
                parqueadero.getIdAdministrador(),
                parqueadero.getNombre());
    }

    private ParqueaderoDto getFullParqueaderoResponse(Parqueadero parqueadero) {
        return new ParqueaderoDto(
                parqueadero.getIdAdministrador(),
                parqueadero.getIdParqueadero(),
                parqueadero.getNombre(),
                parqueadero.getEspacios());
    }
}