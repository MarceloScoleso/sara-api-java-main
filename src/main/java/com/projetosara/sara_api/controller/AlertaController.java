package com.projetosara.sara_api.controller;

import com.projetosara.sara_api.dto.AlertaDTO;
import com.projetosara.sara_api.service.AlertaService;
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
@RequestMapping("/api/alertas")
@RequiredArgsConstructor
public class AlertaController {
    private final AlertaService service;

    @GetMapping
    public Page<AlertaDTO> listar(
            @RequestParam(required = false)
            @Parameter(description = "Texto a ser buscado na mensagem do alerta")
            String titulo,
            @ParameterObject
            @PageableDefault(sort = "dataHora", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.listar(titulo, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaDTO> buscarPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-nivel")
    public Page<AlertaDTO> buscarPorNivelAlerta(
            @RequestParam Long nivelAlertaId,
            @ParameterObject
            @PageableDefault(sort = "dataHora", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return service.buscarPorNivelAlerta(nivelAlertaId, pageable);
    }

    @PostMapping
    public ResponseEntity<AlertaDTO> salvar(@RequestBody @Valid AlertaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AlertaDTO dto) {
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}