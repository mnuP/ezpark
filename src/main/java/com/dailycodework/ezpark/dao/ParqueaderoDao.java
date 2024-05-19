package com.dailycodework.ezpark.dao;

import com.dailycodework.ezpark.model.Parqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParqueaderoDao extends JpaRepository<Parqueadero, Long> {

    @Query(" SELECT par FROM Parqueadero par " +
            " WHERE par.idAdministrador = :idAdministrador ")
    List<Parqueadero> getAllParqueaderosFromAdmin(Long idAdministrador);
}
