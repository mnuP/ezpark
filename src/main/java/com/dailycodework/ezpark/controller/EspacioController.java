package com.dailycodework.ezpark.controller;

import com.dailycodework.ezpark.exception.ResourceNotFoundException;
import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.dto.EspacioDto;
import com.dailycodework.ezpark.service.EspacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/espacios")

public class EspacioController {

    private final EspacioService espacioService;

    @PostMapping("/add/new-espacio")
    public ResponseEntity<EspacioDto> addNewEspacio(
            @RequestParam("tipo") String tipo,
            @RequestParam("parqueadero") String parqueadero) {
        Espacio espacioGuardado =  espacioService.addNewEspacio(tipo, Long.parseLong(parqueadero));
        EspacioDto response = new EspacioDto(espacioGuardado.getId(), espacioGuardado.getTipo(), espacioGuardado.getIdParqueadero());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-espacios")
    public ResponseEntity<List<EspacioDto>> getAllEspacios() {
        List<Espacio> espacios = espacioService.getAllEspacios();
        List<EspacioDto> espacioRespons = new ArrayList<>();
        for (Espacio espacio : espacios) {
            EspacioDto espacioDto = new EspacioDto(espacio.getId(), espacio.getTipo(), espacio.getIdParqueadero());
            espacioRespons.add(espacioDto);
        }
        return ResponseEntity.ok(espacioRespons);
    }

    @GetMapping("/espacio/{idEspacio}")
    public ResponseEntity<Optional<EspacioDto>> getEspacioById(@PathVariable Long idEspacio) {
        Optional<Espacio> theEspacio = espacioService.getEspacioById(idEspacio);
        return theEspacio.map(espacio -> {
            EspacioDto espacioDto = getFullEspacioResponse(espacio);
            return ResponseEntity.ok(Optional.of(espacioDto));
        }).orElseThrow(() -> new ResourceNotFoundException("Espacio no encontrado"));
    }

    private EspacioDto getFullEspacioResponse(Espacio espacio) {
        return new EspacioDto(
                espacio.getId(),
                espacio.getTipo(),
                espacio.getIdParqueadero(),
                espacio.getReservasEspacio()
        );

    }
}
