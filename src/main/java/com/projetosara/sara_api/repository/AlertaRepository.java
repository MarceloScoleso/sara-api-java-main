package com.projetosara.sara_api.repository;

import com.projetosara.sara_api.entity.Alerta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    @Query("SELECT a FROM Alerta a WHERE " +
        "(:titulo IS NULL OR LOWER(a.mensagem) LIKE LOWER(CONCAT('%', :titulo, '%')))")
    Page<Alerta> findByFiltro(@Param("titulo") String titulo, Pageable pageable);

    Page<Alerta> findByNivelAlertaId(Long nivelAlertaId, Pageable pageable);
}