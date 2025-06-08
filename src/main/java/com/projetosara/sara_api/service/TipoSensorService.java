package com.projetosara.sara_api.service;

import com.projetosara.sara_api.dto.TipoSensorDTO;
import com.projetosara.sara_api.entity.TipoSensor;
import com.projetosara.sara_api.mapper.TipoSensorMapper;
import com.projetosara.sara_api.repository.TipoSensorRepository;
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
public class TipoSensorService {
    private final TipoSensorRepository repository;
    private final TipoSensorMapper mapper;

    @Cacheable(value = "tipoSensores", key = "'listar-' + #codigo + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<TipoSensorDTO> listar(String codigo, Pageable pageable) {
        return repository.findByFiltro(codigo, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "tipoSensores", key = "#id")
    public Optional<TipoSensorDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @CacheEvict(value = "tipoSensores", allEntries = true)
    public TipoSensorDTO save(TipoSensorDTO dto) {
        TipoSensor entidade = mapper.toEntity(dto);
        TipoSensor salva = repository.save(entidade);
        return mapper.toDTO(salva);
    }

    @CacheEvict(value = "tipoSensores", allEntries = true)
    public Optional<TipoSensorDTO> update(Long id, TipoSensorDTO dto) {
        return repository.findById(id).map(existing -> {
            dto.setId(id);
            TipoSensor entidadeAtualizada = mapper.toEntity(dto);
            TipoSensor salva = repository.save(entidadeAtualizada);
            return mapper.toDTO(salva);
        });
    }

    @CacheEvict(value = "tipoSensores", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "tipoSensores", key = "'codigo-' + #codigo")
    public Optional<TipoSensorDTO> findByCodigo(String codigo) {
        return repository.findByCodigo(codigo).map(mapper::toDTO);
    }

    @Cacheable(value = "tipoSensores", key = "'count-all'")
    public Long contarTodos() {
        return repository.count();
    }
}