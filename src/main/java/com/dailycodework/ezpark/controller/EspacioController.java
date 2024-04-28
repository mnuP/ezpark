package com.dailycodework.ezpark.controller;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.repository.EspacioRepository;
import com.dailycodework.ezpark.response.EspacioResponse;
import com.dailycodework.ezpark.response.ParqueaderoResponse;
import com.dailycodework.ezpark.service.EspacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        EspacioResponse response = new EspacioResponse(espacioGuardado.getId(), espacioGuardado.getTipo(), espacioGuardado.getParqueadero().getIdParqueadero());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-espacios")
    public ResponseEntity<List<EspacioResponse>> getAllEspacios() {
        List<Espacio> espacios = espacioService.getAllEspacios();
        List<EspacioResponse> espacioResponses = new ArrayList<>();
        for (Espacio espacio : espacios) {
            EspacioResponse espacioResponse = new EspacioResponse(espacio.getId(), espacio.getTipo(), espacio.getParqueadero().getIdParqueadero());
            espacioResponses.add(espacioResponse);
        }
        return ResponseEntity.ok(espacioResponses);
    }
}
