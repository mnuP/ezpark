package com.dailycodework.ezpark.controller;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.repository.EspacioRepository;
import com.dailycodework.ezpark.response.EspacioResponse;
import com.dailycodework.ezpark.service.EspacioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/espacios")
public class EspacioController {

    private final EspacioService espacioService;

    @PostMapping("/add/new-espacio")
    public ResponseEntity<EspacioResponse> addNewEspacio(
            @RequestParam("tipo") String tipo,
            @RequestParam("parqueadero") Parqueadero parqueadero) {
        Espacio espacioGuardado =  espacioService.addNewEspacio(tipo, parqueadero);
        EspacioResponse response = new EspacioResponse(espacioGuardado.getId(), espacioGuardado.getTipo(), espacioGuardado.getParqueadero());

        return ResponseEntity.ok(response);
    }
}
