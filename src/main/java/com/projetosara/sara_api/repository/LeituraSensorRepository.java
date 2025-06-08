package com.projetosara.sara_api.repository;

import com.projetosara.sara_api.entity.LeituraSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.Optional;
import java.util.List;

public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {

    @Query("SELECT l FROM LeituraSensor l WHERE (:sensorId IS NULL OR l.sensor.id = :sensorId)")
    Page<LeituraSensor> findByFiltro(@Param("sensorId") Long sensorId, Pageable pageable);

    
    @Query("SELECT l FROM LeituraSensor l WHERE l.sensor.id = :sensorId ORDER BY l.dataHora DESC")
    Optional<LeituraSensor> findTopBySensorIdOrderByDataHoraDesc(@Param("sensorId") Long sensorId);

    
    @Query("SELECT l FROM LeituraSensor l WHERE l.sensor.id = :sensorId AND l.dataHora BETWEEN :startDate AND :endDate ORDER BY l.dataHora ASC")
    List<LeituraSensor> findBySensorIdAndDataHoraBetween(
        @Param("sensorId") Long sensorId,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );
}