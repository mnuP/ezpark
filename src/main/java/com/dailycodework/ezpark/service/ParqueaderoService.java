package com.dailycodework.ezpark.service;

import com.dailycodework.ezpark.exception.ResourceNotFoundException;
import com.dailycodework.ezpark.model.Parqueadero;
import com.dailycodework.ezpark.repository.ParqueaderoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParqueaderoService implements IParqueaderoService {

    private final ParqueaderoRepository parqueaderoRepository;

    @Override
    public Parqueadero addNewParqueadero(Long idAdministrador, String nombre) {
        Parqueadero parqueadero = new Parqueadero();
        parqueadero.setIdAdministrador(idAdministrador);
        parqueadero.setNombre(nombre);

        return parqueaderoRepository.save(parqueadero);
    }

    @Override
    public List<Parqueadero> getAllParqueaderos() {
        return parqueaderoRepository.findAll();
    }



    @Override
    public void deleteParqueadero(Long parqueaderoId) {
        Optional<Parqueadero> theparqueadero = parqueaderoRepository.findById(parqueaderoId);
        if (theparqueadero.isPresent()) {
            parqueaderoRepository.deleteById(parqueaderoId);
        }
    }

    @Override
    public Parqueadero updateParqueadero(Long idAdministrador, String nombre) {
        Parqueadero parqueadero = parqueaderoRepository.findById(idAdministrador).
                orElseThrow(()->new ResourceNotFoundException("Parqueadero no encontrado"));
        if (nombre != null) parqueadero.setNombre(nombre);

        return parqueaderoRepository.save(parqueadero);
    }

    @Override
    public Optional<Parqueadero> getParqueaderoById(Long idParqueadero) {
        return Optional.of(parqueaderoRepository.findById(idParqueadero).get());
    }

}
