package com.projetosara.sara_api.service;

import com.projetosara.sara_api.dto.NotificacaoDTO;
import com.projetosara.sara_api.entity.Notificacao;
import com.projetosara.sara_api.mapper.NotificacaoMapper;
import com.projetosara.sara_api.repository.NotificacaoRepository;
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
public class NotificacaoService {
    private final NotificacaoRepository repository;
    private final NotificacaoMapper mapper;

    @Cacheable(value = "notificacoes", key = "#usuarioId + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<NotificacaoDTO> listar(Long usuarioId, Pageable pageable) {
        return repository.findByFiltro(usuarioId, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "notificacoes", key = "#id")
    public Optional<NotificacaoDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @CacheEvict(value = "notificacoes", allEntries = true)
    public NotificacaoDTO save(NotificacaoDTO dto) {
        Notificacao entidade = mapper.toEntity(dto);
        Notificacao salva = repository.save(entidade);
        return mapper.toDTO(salva);
    }

    @CacheEvict(value = "notificacoes", allEntries = true)
    public Optional<NotificacaoDTO> update(Long id, NotificacaoDTO dto) {
        return repository.findById(id).map(existing -> {
            dto.setId(id);
            Notificacao entidadeAtualizada = mapper.toEntity(dto);
            Notificacao salva = repository.save(entidadeAtualizada);
            return mapper.toDTO(salva);
        });
    }

    @CacheEvict(value = "notificacoes", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "notificacoes", key = "'status-' + #statusId + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<NotificacaoDTO> listarPorStatus(Long statusId, Pageable pageable) {
        return repository.findByStatusId(statusId, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "notificacoes", key = "'count-usuario-' + #usuarioId")
    public Long contarPorUsuario(Long usuarioId) {
        return repository.countByUsuarioId(usuarioId);
    }
}