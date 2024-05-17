package com.dailycodework.ezpark.dao;

import com.dailycodework.ezpark.model.EspacioReservado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaDao extends JpaRepository<EspacioReservado, Long> {
}
