package com.dailycodework.ezpark.repository;

import com.dailycodework.ezpark.model.Parqueadero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParqueaderoRepository extends JpaRepository<Parqueadero, Long> {
}
