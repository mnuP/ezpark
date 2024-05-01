package com.dailycodework.ezpark.response;

import com.dailycodework.ezpark.model.EspacioReservado;
import com.dailycodework.ezpark.model.Parqueadero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EspacioResponse {

    private Long id;
    private String tipo;
    private Long parqueaderoID;
    private List<EspacioReservado> reservasResponses;


    public EspacioResponse(Long id, String tipo, Long parqueaderoID) {
        this.id = id;
        this.tipo = tipo;
        this.parqueaderoID = parqueaderoID;
    }

    public EspacioResponse(Long id ,String tipo, Long parqueaderoID ,List<EspacioReservado> reservasResponses) {
        this.tipo = tipo;
        this.parqueaderoID = parqueaderoID;
        this.reservasResponses = reservasResponses;
        this.id = id;
    }
}
