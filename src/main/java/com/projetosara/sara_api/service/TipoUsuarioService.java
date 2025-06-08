package com.projetosara.sara_api.service;

import com.projetosara.sara_api.dto.TipoUsuarioDTO;
import com.projetosara.sara_api.entity.TipoUsuario;
import com.projetosara.sara_api.mapper.TipoUsuarioMapper;
import com.projetosara.sara_api.repository.TipoUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TipoUsuarioService {
    private final TipoUsuarioRepository repository;
    private final TipoUsuarioMapper mapper;

    @Cacheable(value = "tipoUsuarios", key = "'listar-' + #codigo + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<TipoUsuarioDTO> listar(String codigo, Pageable pageable) {
        return repository.findByFiltro(codigo, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "tipoUsuarios", key = "#id")
    public Optional<TipoUsuarioDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @CacheEvict(value = "tipoUsuarios", allEntries = true)
    public TipoUsuarioDTO save(TipoUsuarioDTO dto) {
        TipoUsuario entidade = mapper.toEntity(dto);
        TipoUsuario salva = repository.save(entidade);
        return mapper.toDTO(salva);
    }

    @CacheEvict(value = "tipoUsuarios", allEntries = true)
    public Optional<TipoUsuarioDTO> update(Long id, TipoUsuarioDTO dto) {
        return repository.findById(id).map(existing -> {
            dto.setId(id);
            TipoUsuario entidadeAtualizada = mapper.toEntity(dto);
            TipoUsuario salva = repository.save(entidadeAtualizada);
            return mapper.toDTO(salva);
        });
    }

    @CacheEvict(value = "tipoUsuarios", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "tipoUsuarios", key = "'codigo-' + #codigo")
    public Optional<TipoUsuarioDTO> findByCodigo(String codigo) {
        return repository.findByCodigo(codigo).map(mapper::toDTO);
    }

    @Cacheable(value = "tipoUsuarios", key = "'count-all'")
    public Long contarTodos() {
        return repository.count();
    }
}
