package com.projetosara.sara_api.service;

import com.projetosara.sara_api.dto.StatusNotificacaoDTO;
import com.projetosara.sara_api.entity.StatusNotificacao;
import com.projetosara.sara_api.mapper.StatusNotificacaoMapper;
import com.projetosara.sara_api.repository.StatusNotificacaoRepository;
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
public class StatusNotificacaoService {
    private final StatusNotificacaoRepository repository;
    private final StatusNotificacaoMapper mapper;

    @Cacheable(value = "statusNotificacoes", key = "'listar-' + #codigo + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<StatusNotificacaoDTO> listar(String codigo, Pageable pageable) {
        return repository.findByFiltro(codigo, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "statusNotificacoes", key = "#id")
    public Optional<StatusNotificacaoDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @CacheEvict(value = "statusNotificacoes", allEntries = true)
    public StatusNotificacaoDTO save(StatusNotificacaoDTO dto) {
        StatusNotificacao entidade = mapper.toEntity(dto);
        StatusNotificacao salva = repository.save(entidade);
        return mapper.toDTO(salva);
    }

    @CacheEvict(value = "statusNotificacoes", allEntries = true)
    public Optional<StatusNotificacaoDTO> update(Long id, StatusNotificacaoDTO dto) {
        return repository.findById(id).map(existing -> {
            dto.setId(id);
            StatusNotificacao entidadeAtualizada = mapper.toEntity(dto);
            StatusNotificacao salva = repository.save(entidadeAtualizada);
            return mapper.toDTO(salva);
        });
    }

    @CacheEvict(value = "statusNotificacoes", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "statusNotificacoes", key = "'codigo-' + #codigo")
    public Optional<StatusNotificacaoDTO> findByCodigo(String codigo) {
        return repository.findByCodigo(codigo).map(mapper::toDTO);
    }

    @Cacheable(value = "statusNotificacoes", key = "'count-all'")
    public Long contarTodos() {
        return repository.count();
    }
}