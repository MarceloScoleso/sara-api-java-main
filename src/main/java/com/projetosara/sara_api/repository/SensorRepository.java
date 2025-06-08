package com.projetosara.sara_api.repository;

import com.projetosara.sara_api.entity.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    @Query("SELECT s FROM Sensor s WHERE (:tipoSensorId IS NULL OR s.tipoSensor.id = :tipoSensorId)")
    Page<Sensor> findByFiltro(@Param("tipoSensorId") Long tipoSensorId, Pageable pageable);

    @Query("SELECT s FROM Sensor s WHERE s.tipoSensor.id = :tipoSensorId")
    Page<Sensor> findByTipoSensorId(@Param("tipoSensorId") Long tipoSensorId, Pageable pageable);

    @Query("SELECT COUNT(s) FROM Sensor s WHERE s.localizacao.id = :localizacaoId")
    Long countByLocalizacaoId(@Param("localizacaoId") Long localizacaoId);
} 