package com.projetosara.sara_api.service;

import com.projetosara.sara_api.dto.UsuarioDTO;
import com.projetosara.sara_api.entity.Usuario;
import com.projetosara.sara_api.mapper.UsuarioMapper;
import com.projetosara.sara_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Cacheable(value = "usuarios", key = "'listar-' + #nomeOuEmail + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<UsuarioDTO> listar(String nomeOuEmail, Pageable pageable) {
        return repository.findByFiltro(nomeOuEmail, pageable).map(mapper::toDTO);
    }

    @Cacheable(value = "usuarios", key = "#id")
    public Optional<UsuarioDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioDTO save(UsuarioDTO dto) {
        Usuario entidade = mapper.toEntity(dto);
        // Criptografa a senha antes de salvar
        entidade.setSenha(passwordEncoder.encode(entidade.getSenha()));
        Usuario salva = repository.save(entidade);
        return mapper.toDTO(salva);
    }

    @CacheEvict(value = "usuarios", allEntries = true)
    public Optional<UsuarioDTO> update(Long id, UsuarioDTO dto) {
        return repository.findById(id).map(existing -> {
            dto.setId(id);
            Usuario entidadeAtualizada = mapper.toEntity(dto);
            // Criptografa a senha antes de atualizar
            entidadeAtualizada.setSenha(passwordEncoder.encode(entidadeAtualizada.getSenha()));
            Usuario salva = repository.save(entidadeAtualizada);
            return mapper.toDTO(salva);
        });
    }

    @CacheEvict(value = "usuarios", allEntries = true)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "usuarios", key = "'email-' + #email")
    public Optional<UsuarioDTO> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDTO);
    }

    @Cacheable(value = "usuarios", key = "'count-all'")
    public Long contarUsuarios() {
        return repository.count();
    }
}
