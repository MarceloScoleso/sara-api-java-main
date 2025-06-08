package com.projetosara.sara_api.controller;

import com.projetosara.sara_api.dto.StatusNotificacaoDTO;
import com.projetosara.sara_api.service.StatusNotificacaoService;
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
@RequestMapping("/api/status-notificacoes")
@RequiredArgsConstructor
public class StatusNotificacaoController {

    private final StatusNotificacaoService service;

    @GetMapping
    public Page<StatusNotificacaoDTO> listar(
            @RequestParam(required = false)
            @Parameter(description = "Filtro por descrição") String descricao,

            @ParameterObject
            @PageableDefault(sort = "descricao", direction = Sort.Direction.ASC)
            Pageable pageable) {

        return service.listar(descricao, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusNotificacaoDTO> buscarPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StatusNotificacaoDTO> salvar(@RequestBody @Valid StatusNotificacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusNotificacaoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid StatusNotificacaoDTO dto) {
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<StatusNotificacaoDTO> buscarPorCodigo(@PathVariable String codigo) {
        return service.findByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarTodos() {
        return ResponseEntity.ok(service.contarTodos());
    }
}