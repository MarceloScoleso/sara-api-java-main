package com.projetosara.sara_api.repository;

import com.projetosara.sara_api.entity.NivelAlerta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
public interface NivelAlertaRepository extends JpaRepository<NivelAlerta, Long> {
    @Query("SELECT n FROM NivelAlerta n WHERE (:codigo IS NULL OR LOWER(n.codigo) LIKE LOWER(CONCAT('%', :codigo, '%')))")
    Page<NivelAlerta> findByFiltro(@Param("codigo") String codigo, Pageable pageable);
    @Query("SELECT n FROM NivelAlerta n WHERE LOWER(n.codigo) = LOWER(:codigo)")
    Optional<NivelAlerta> findByCodigoExato(@Param("codigo") String codigo);
}