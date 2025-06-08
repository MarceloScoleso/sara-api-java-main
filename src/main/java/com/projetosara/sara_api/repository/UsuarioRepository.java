package com.projetosara.sara_api.repository;

import com.projetosara.sara_api.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u " +
        "WHERE (:nome IS NULL OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) " +
        "ORDER BY u.nome ASC")
    Page<Usuario> findByFiltro(@Param("nome") String nome, Pageable pageable);

    Optional<Usuario> findByEmail(String email);
}