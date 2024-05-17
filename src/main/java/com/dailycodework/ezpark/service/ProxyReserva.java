package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.dao.UsuarioDao;
import com.dailycodework.ezpark.model.EspacioReservado;
import com.dailycodework.ezpark.model.Usuario;
import com.dailycodework.ezpark.dao.ReservaDao;
import lombok.RequiredArgsConstructor;
import com.dailycodework.ezpark.dao.UsuarioDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProxyReserva implements IReservaService{

    private final ReservaDao reservaDao;
    private final IEspacioService espacioService;
    private ReservaService reservaService;
    private final UsuarioDao usuarioDao;

    @Override
    public List<EspacioReservado> getAllReservas() {
        instantiateService();
        List<EspacioReservado> reservas = reservaService.getAllReservas();
        return reservas;
    }

    @Override
    public EspacioReservado findByIdReserva(Long idReserva) {
        instantiateService();
        EspacioReservado reserva = reservaService.findByIdReserva(idReserva);
        return reserva;
    }

    @Override
    public Long saveReserva(Long idEspacio, EspacioReservado espacioReservado) {
        instantiateService();
        Usuario miUsuario = usuarioDao.findById(Long.parseLong(espacioReservado.getIdUsuario())).get();
        String valor = "12000";
        System.out.println(miUsuario.getTipoUsuario());
        if(miUsuario.getTipoUsuario().equals("profesor")){
            valor = "5000";
        }
        espacioReservado.setValorReserva(Long.parseLong(valor));
        Long idReserva = reservaService.saveReserva(idEspacio, espacioReservado);
        return idReserva;
    }

    @Override
    public void cancelarReserva(Long idReserva) {
        instantiateService();
        reservaService.cancelarReserva(idReserva);
    }

    private void instantiateService(){
        if(this.reservaService == null){
            this.reservaService = ReservaService.getInstance(reservaDao, espacioService);
        }
    }
}
