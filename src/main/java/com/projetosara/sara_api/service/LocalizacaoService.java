package com.projetosara.sara_api.service;

import com.projetosara.sara_api.dto.LocalizacaoDTO;
import com.projetosara.sara_api.entity.Localizacao;
import com.projetosara.sara_api.mapper.LocalizacaoMapper;
import com.projetosara.sara_api.repository.LocalizacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LocalizacaoService {
    private final LocalizacaoRepository repository;
    private final LocalizacaoMapper mapper;

    @Cacheable(value = "localizacoes", key = "#cidade + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<LocalizacaoDTO> listar(String cidade, Pageable pageable) {
        return repository.findByFiltro(cidade, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "localizacoes", key = "#id")
    public Optional<LocalizacaoDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @CacheEvict(value = "localizacoes", allEntries = true)
    public LocalizacaoDTO save(LocalizacaoDTO dto) {
        Localizacao entidade = mapper.toEntity(dto);
        Localizacao salva = repository.save(entidade);
        return mapper.toDTO(salva);
    }

    @CacheEvict(value = "localizacoes", allEntries = true)
    public Optional<LocalizacaoDTO> update(Long id, LocalizacaoDTO dto) {
        return repository.findById(id).map(existing -> {
            dto.setId(id);
            Localizacao entidadeAtualizada = mapper.toEntity(dto);
            Localizacao salva = repository.save(entidadeAtualizada);
            return mapper.toDTO(salva);
        });
    }

    @CacheEvict(value = "localizacoes", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "localizacoes", key = "'estado-' + #estado + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<LocalizacaoDTO> listarPorEstado(String estado, Pageable pageable) {
        return repository.findByEstado(estado, pageable).map(mapper::toDTO);
    }
}