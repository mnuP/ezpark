package com.dailycodework.ezpark.controller;

import com.dailycodework.ezpark.exception.ResourceNotFoundException;
import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.repository.EspacioRepository;
import com.dailycodework.ezpark.response.EspacioReservadoResponse;
import com.dailycodework.ezpark.response.EspacioResponse;
import com.dailycodework.ezpark.response.ParqueaderoResponse;
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
    public ResponseEntity<EspacioResponse> addNewEspacio(
            @RequestParam("tipo") String tipo,
            @RequestParam("parqueadero") String parqueadero) {
        Espacio espacioGuardado =  espacioService.addNewEspacio(tipo, Long.parseLong(parqueadero));
        EspacioResponse response = new EspacioResponse(espacioGuardado.getId(), espacioGuardado.getTipo(), espacioGuardado.getIdParqueadero());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-espacios")
    public ResponseEntity<List<EspacioResponse>> getAllEspacios() {
        List<Espacio> espacios = espacioService.getAllEspacios();
        List<EspacioResponse> espacioResponses = new ArrayList<>();
        for (Espacio espacio : espacios) {
            EspacioResponse espacioResponse = new EspacioResponse(espacio.getId(), espacio.getTipo(), espacio.getIdParqueadero());
            espacioResponses.add(espacioResponse);
        }
        return ResponseEntity.ok(espacioResponses);
    }

    @GetMapping("/espacio/{idEspacio}")
    public ResponseEntity<Optional<EspacioResponse>> getEspacioById(@PathVariable Long idEspacio) {
        Optional<Espacio> theEspacio = espacioService.getEspacioById(idEspacio);
        return theEspacio.map(espacio -> {
            EspacioResponse espacioResponse = getFullEspacioResponse(espacio);
            return ResponseEntity.ok(Optional.of(espacioResponse));
        }).orElseThrow(() -> new ResourceNotFoundException("Espacio no encontrado"));
    }

    private EspacioResponse getFullEspacioResponse(Espacio espacio) {
        return new EspacioResponse(
                espacio.getId(),
                espacio.getTipo(),
                espacio.getIdParqueadero(),
                espacio.getReservasEspacio()
        );

    }
}
