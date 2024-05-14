package com.dailycodework.ezpark.controller;


import com.dailycodework.ezpark.exception.ResourceNotFoundException;
import com.dailycodework.ezpark.exception.ReservaInvalidaRequestException;
import com.dailycodework.ezpark.model.EspacioReservado;
import com.dailycodework.ezpark.response.EspacioReservadoResponse;
import com.dailycodework.ezpark.service.IEspacioService;
import com.dailycodework.ezpark.service.IReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final IReservaService proxyReserva;
    private final IEspacioService espacioService;

    @GetMapping("/all-reservas")
    public ResponseEntity<List<EspacioReservadoResponse>> getAllReservas(){
        List<EspacioReservado> reservas = proxyReserva.getAllReservas();
        List<EspacioReservadoResponse> reservaResponses = new ArrayList<>();
        for(EspacioReservado reserva : reservas){
            EspacioReservadoResponse resp = getEspacioReservadoResponse(reserva);
            reservaResponses.add(resp);
        }
        return ResponseEntity.ok(reservaResponses);
    }

    @GetMapping("/id/{idReserva}")
    public ResponseEntity<?> getReservaById(@PathVariable String idReserva){
        try {
            EspacioReservado reserva = proxyReserva.findByIdReserva(Long.parseLong(idReserva));
            EspacioReservadoResponse reservaResponse = getEspacioReservadoResponse(reserva);

            return ResponseEntity.ok(reservaResponse);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @PostMapping("/espacio/{idEspacio}/reserva")
    public ResponseEntity<?> guardarReserva(@PathVariable Long idEspacio,
                                            @RequestParam("dia") String dia,
                                            @RequestParam("horaInicioReserva") String horaInicioReserva,
                                            @RequestParam("horaFinReserva") String horaFinReserva,
                                            @RequestParam("matricula") String matricula,
                                            @RequestParam("idUsuario") String idUsuario){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        formatter = formatter.withLocale(Locale.US ); // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate date = LocalDate.parse(dia, formatter);

        EspacioReservado reservaRequest = new EspacioReservado();
        reservaRequest.setDia(date);
        reservaRequest.setHoraInicioReserva(Integer.parseInt(horaInicioReserva));
        reservaRequest.setHoraFinReserva(Integer.parseInt(horaFinReserva));
        reservaRequest.setMatriculaVehiculo(matricula);
        reservaRequest.setIdUsuario(idUsuario);

        try{
            Long id = proxyReserva.saveReserva(idEspacio, reservaRequest);
            return ResponseEntity.ok(
                    "La reserva ha sido exitosa, su numero de reserva es: "+id);

        }catch (ReservaInvalidaRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/reserva/{idReserva}/delete")
    public void cancelarReserva(Long idReserva){
        proxyReserva.cancelarReserva(idReserva);
    }

    private EspacioReservadoResponse getEspacioReservadoResponse(EspacioReservado reserva){
        EspacioReservadoResponse espRes = new EspacioReservadoResponse(reserva.getId(), reserva.getDia(), reserva.getHoraInicioReserva(), reserva.getHoraFinReserva(), reserva.getIdUsuario(), reserva.getIdEspacio());
        return espRes;
    }
}
