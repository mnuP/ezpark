package com.dailycodework.ezpark.repository;

import com.dailycodework.ezpark.model.EspacioReservado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<EspacioReservado, Long> {
}
