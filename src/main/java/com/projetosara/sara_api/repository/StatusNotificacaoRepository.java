package com.projetosara.sara_api.repository;

import com.projetosara.sara_api.entity.StatusNotificacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
public interface StatusNotificacaoRepository extends JpaRepository<StatusNotificacao, Long> {
    @Query("SELECT s FROM StatusNotificacao s WHERE (:codigo IS NULL OR LOWER(s.codigo) LIKE LOWER(CONCAT('%', :codigo, '%')))")
    Page<StatusNotificacao> findByFiltro(@Param("codigo") String codigo, Pageable pageable);

    Optional<StatusNotificacao> findByCodigo(String codigo);
}