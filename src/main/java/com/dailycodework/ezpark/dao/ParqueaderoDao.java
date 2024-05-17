package com.dailycodework.ezpark.dao;

import com.dailycodework.ezpark.model.Parqueadero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParqueaderoDao extends JpaRepository<Parqueadero, Long> {
}
