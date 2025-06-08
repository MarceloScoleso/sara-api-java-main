package com.projetosara.sara_api.controller;

import com.projetosara.sara_api.dto.LocalizacaoDTO;
import com.projetosara.sara_api.service.LocalizacaoService;
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
@RequestMapping("/api/localizacoes")
@RequiredArgsConstructor
public class LocalizacaoController {
    private final LocalizacaoService service;

    @GetMapping
    public Page<LocalizacaoDTO> listar(
            @RequestParam(required = false)
            @Parameter(description = "Filtro opcional por cidade") String cidade,
            @ParameterObject
            @PageableDefault(sort = "cidade", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return service.listar(cidade, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacaoDTO> buscarPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LocalizacaoDTO> salvar(@RequestBody @Valid LocalizacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalizacaoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid LocalizacaoDTO dto) {
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado")
    public Page<LocalizacaoDTO> listarPorEstado(
            @RequestParam String estado,
            @ParameterObject
            @PageableDefault(sort = "estado", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return service.listarPorEstado(estado, pageable);
    }
}