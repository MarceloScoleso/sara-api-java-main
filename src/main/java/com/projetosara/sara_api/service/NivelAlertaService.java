package com.projetosara.sara_api.service;

import com.projetosara.sara_api.dto.NivelAlertaDTO;
import com.projetosara.sara_api.entity.NivelAlerta;
import com.projetosara.sara_api.mapper.NivelAlertaMapper;
import com.projetosara.sara_api.repository.NivelAlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class NivelAlertaService {
    private final NivelAlertaRepository repository;
    private final NivelAlertaMapper mapper;

    @Cacheable(value = "niveisAlerta", key = "#codigo + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<NivelAlertaDTO> listar(String codigo, Pageable pageable) {
        return repository.findByFiltro(codigo, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "niveisAlerta", key = "'codigoExato-' + #codigo")
    public Optional<NivelAlertaDTO> findByCodigoExato(String codigo) {
        return repository.findByCodigoExato(codigo).map(mapper::toDTO);
    }

    @Cacheable(value = "niveisAlerta", key = "'contarAlertas-' + #id")
    public Long contarAlertas(Long id) {
        return repository.findById(id)
                .map(nivel -> (long) nivel.getAlertas().size())
                .orElse(null);
    }


    @Cacheable(value = "niveisAlerta", key = "#id")
    public Optional<NivelAlertaDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @CacheEvict(value = "niveisAlerta", allEntries = true)
    public NivelAlertaDTO save(NivelAlertaDTO dto) {
        NivelAlerta entidade = mapper.toEntity(dto);
        NivelAlerta salva = repository.save(entidade);
        return mapper.toDTO(salva);
    }

    @CacheEvict(value = "niveisAlerta", allEntries = true)
    public Optional<NivelAlertaDTO> update(Long id, NivelAlertaDTO dto) {
        return repository.findById(id).map(existing -> {
            dto.setId(id);
            NivelAlerta entidadeAtualizada = mapper.toEntity(dto);
            NivelAlerta salva = repository.save(entidadeAtualizada);
            return mapper.toDTO(salva);
        });
    }

    @CacheEvict(value = "niveisAlerta", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }
}