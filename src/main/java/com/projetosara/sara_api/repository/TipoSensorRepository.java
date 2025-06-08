package com.projetosara.sara_api.repository;

import com.projetosara.sara_api.entity.TipoSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface TipoSensorRepository extends JpaRepository<TipoSensor, Long> {
    @Query("SELECT t FROM TipoSensor t WHERE (:codigo IS NULL OR LOWER(t.codigo) LIKE LOWER(CONCAT('%', :codigo, '%')))")
    Page<TipoSensor> findByFiltro(@Param("codigo") String codigo, Pageable pageable);

    Optional<TipoSensor> findByCodigo(String codigo);
}