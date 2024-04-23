package com.dailycodework.ezpark.response;

import com.dailycodework.ezpark.model.Parqueadero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EspacioResponse {

    private Long id;
    private String tipo;
    private Parqueadero parqueadero;
    private List<EspacioReservadoResponse> reservasResponses;

    public EspacioResponse(Long id, String tipo, Parqueadero parqueadero) {
        this.id = id;
        this.tipo = tipo;
        this.parqueadero = parqueadero;
    }

    public EspacioResponse(Long id ,String tipo, Parqueadero parqueadero ,List<EspacioReservadoResponse> reservasResponses) {
        this.tipo = tipo;
        this.parqueadero = parqueadero;
        this.reservasResponses = reservasResponses;
        this.id = id;
    }
}
