package com.projetosara.sara_api.repository;

import com.projetosara.sara_api.entity.Localizacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    @Query("SELECT l FROM Localizacao l WHERE (:cidade IS NULL OR LOWER(l.cidade) LIKE LOWER(CONCAT('%', :cidade, '%')))")
    Page<Localizacao> findByFiltro(@Param("cidade") String cidade, Pageable pageable);
    @Query("SELECT l FROM Localizacao l WHERE LOWER(l.estado) LIKE LOWER(CONCAT('%', :estado, '%'))")
    Page<Localizacao> findByEstado(@Param("estado") String estado, Pageable pageable);
}