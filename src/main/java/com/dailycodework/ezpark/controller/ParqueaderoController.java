package com.dailycodework.ezpark.controller;

import com.dailycodework.ezpark.model.Espacio;
import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.response.EspacioResponse;
import com.dailycodework.ezpark.response.ParqueaderoResponse;
import com.dailycodework.ezpark.service.ParqueaderoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parqueaderos")
public class ParqueaderoController {

    private final ParqueaderoService parqueaderoService;

    @PostMapping("/add/new-parqueadero")
    public ResponseEntity<ParqueaderoResponse> addNewParqueadero(
            @RequestParam("idAdministrador") String idAdministrador,
            @RequestParam("nombre") String nombre) {
        Parqueadero parkGuardado =  parqueaderoService.addNewParqueadero(Long.parseLong(idAdministrador), nombre);
        ParqueaderoResponse response = new ParqueaderoResponse(parkGuardado.getIdAdministrador(), parkGuardado.getIdParqueadero(), parkGuardado.getNombre(), parkGuardado.getEspacios());

        return ResponseEntity.ok(response);
    }
}
