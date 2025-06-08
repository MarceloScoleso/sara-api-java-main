package com.projetosara.sara_api.service;

import com.projetosara.sara_api.dto.LeituraSensorDTO;
import com.projetosara.sara_api.entity.LeituraSensor;
import com.projetosara.sara_api.mapper.LeituraSensorMapper;
import com.projetosara.sara_api.repository.LeituraSensorRepository;
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
public class LeituraSensorService {
    private final LeituraSensorRepository repository;
    private final LeituraSensorMapper mapper;

    @Cacheable(value = "leiturasSensor", key = "#sensorId + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<LeituraSensorDTO> listar(Long sensorId, Pageable pageable) {
        return repository.findByFiltro(sensorId, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "leiturasSensor", key = "#id")
    public Optional<LeituraSensorDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Cacheable(value = "leiturasSensorUltima", key = "#sensorId")
    public Optional<LeituraSensorDTO> buscarUltimaLeitura(Long sensorId) {
        return repository.findTopBySensorIdOrderByDataHoraDesc(sensorId)
                .map(mapper::toDTO);
    }

    @CacheEvict(value = {"leiturasSensor", "leiturasSensorUltima"}, allEntries = true)
    public LeituraSensorDTO save(LeituraSensorDTO dto) {
        LeituraSensor entidade = mapper.toEntity(dto);
        LeituraSensor salva = repository.save(entidade);
        return mapper.toDTO(salva);
    }

    @CacheEvict(value = {"leiturasSensor", "leiturasSensorUltima"}, allEntries = true)
    public Optional<LeituraSensorDTO> update(Long id, LeituraSensorDTO dto) {
        return repository.findById(id).map(existing -> {
            dto.setId(id);
            LeituraSensor entidadeAtualizada = mapper.toEntity(dto);
            LeituraSensor salva = repository.save(entidadeAtualizada);
            return mapper.toDTO(salva);
        });
    }

    @CacheEvict(value = {"leiturasSensor", "leiturasSensorUltima"}, allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}