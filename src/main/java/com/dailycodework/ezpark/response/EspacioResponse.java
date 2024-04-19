package com.dailycodework.ezpark.response;

import com.dailycodework.ezpark.model.EspacioReservado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EspacioResponse {

    private Long id;
    private String tipo;
    private List<EspacioReservadoResponse> reservasResponses;

    public EspacioResponse(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public EspacioResponse(String tipo, List<EspacioReservadoResponse> reservasResponses, Long id) {
        this.tipo = tipo;
        this.reservasResponses = reservasResponses;
        this.id = id;
    }
}
