package com.projetosara.sara_api.service;

import com.projetosara.sara_api.dto.AlertaDTO;
import com.projetosara.sara_api.entity.Alerta;
import com.projetosara.sara_api.mapper.AlertaMapper;
import com.projetosara.sara_api.repository.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AlertaService {

    private final AlertaRepository repository;
    private final AlertaMapper mapper;

    @Cacheable(value = "alertas", key = "#titulo + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<AlertaDTO> listar(String titulo, Pageable pageable) {
        return repository.findByFiltro(titulo, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "alertas", key = "'nivelAlerta-' + #nivelAlertaId + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<AlertaDTO> buscarPorNivelAlerta(Long nivelAlertaId, Pageable pageable) {
        return repository.findByNivelAlertaId(nivelAlertaId, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "alertas", key = "#id")
    public Optional<AlertaDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @CacheEvict(value = "alertas", allEntries = true)
    public AlertaDTO save(AlertaDTO dto) {
        Alerta entidade = mapper.toEntity(dto);
        Alerta salva = repository.save(entidade);
        return mapper.toDTO(salva);
    }

    @CacheEvict(value = "alertas", allEntries = true)
    public Optional<AlertaDTO> update(Long id, AlertaDTO dto) {
        return repository.findById(id).map(existing -> {
            dto.setId(id);
            Alerta entidadeAtualizada = mapper.toEntity(dto);
            Alerta salva = repository.save(entidadeAtualizada);
            return mapper.toDTO(salva);
        });
    }

    @CacheEvict(value = "alertas", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}