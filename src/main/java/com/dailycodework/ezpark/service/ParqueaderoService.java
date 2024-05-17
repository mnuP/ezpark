package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.exception.ResourceNotFoundException;
import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.dao.ParqueaderoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParqueaderoService implements IParqueaderoService {

    private final ParqueaderoDao parqueaderoDao;

    @Override
    public Parqueadero addNewParqueadero(Long idAdministrador, String nombre) {
        Parqueadero parqueadero = new Parqueadero();
        parqueadero.setIdAdministrador(idAdministrador);
        parqueadero.setNombre(nombre);

        return parqueaderoDao.save(parqueadero);
    }

    @Override
    public List<Parqueadero> getAllParqueaderos() {
        return parqueaderoDao.findAll();
    }



    @Override
    public void deleteParqueadero(Long parqueaderoId) {
        Optional<Parqueadero> theparqueadero = parqueaderoDao.findById(parqueaderoId);
        if (theparqueadero.isPresent()) {
            parqueaderoDao.deleteById(parqueaderoId);
        }
    }

    @Override
    public Parqueadero updateParqueadero(Long idParqueadero, String nombre) {
        Parqueadero parqueadero = parqueaderoDao.findById(idParqueadero).
                orElseThrow(()->new ResourceNotFoundException("Parqueadero no encontrado"));
        System.out.println(nombre);
        if (nombre != null) parqueadero.setNombre(nombre);

        return parqueaderoDao.save(parqueadero);
    }

    @Override
    public Optional<Parqueadero> getParqueaderoById(Long idParqueadero) {
        return Optional.of(parqueaderoDao.findById(idParqueadero).get());
    }

}
