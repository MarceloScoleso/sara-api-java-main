package com.projetosara.sara_api.controller;

import com.projetosara.sara_api.dto.NotificacaoDTO;
import com.projetosara.sara_api.service.NotificacaoService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {
    private final NotificacaoService service;

    @GetMapping
    public Page<NotificacaoDTO> listar(
            @RequestParam(required = false)
            @Parameter(description = "ID do usuário para filtrar notificações") Long usuarioId,

            @ParameterObject
            @PageableDefault(sort = "dataEnvio", direction = Sort.Direction.DESC)
            Pageable pageable) {

        return service.listar(usuarioId, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoDTO> buscarPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NotificacaoDTO> salvar(@RequestBody @Valid NotificacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacaoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid NotificacaoDTO dto) {
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status")
    public Page<NotificacaoDTO> listarPorStatus(
            @RequestParam Long statusId,
            @ParameterObject @PageableDefault(sort = "dataEnvio", direction = Sort.Direction.DESC) Pageable pageable) {

        return service.listarPorStatus(statusId, pageable);
    }

    @GetMapping("/count-by-usuario")
    public ResponseEntity<Long> contarPorUsuario(@RequestParam Long usuarioId) {
        Long count = service.contarPorUsuario(usuarioId);
        return count == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(count);
    }
}