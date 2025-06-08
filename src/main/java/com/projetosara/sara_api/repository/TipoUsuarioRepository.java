package com.projetosara.sara_api.repository;

import com.projetosara.sara_api.entity.TipoUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
    @Query("SELECT t FROM TipoUsuario t WHERE (:codigo IS NULL OR LOWER(t.codigo) LIKE LOWER(CONCAT('%', :codigo, '%')))")
    Page<TipoUsuario> findByFiltro(@Param("codigo") String codigo, Pageable pageable);

    Optional<TipoUsuario> findByCodigo(String codigo);
}