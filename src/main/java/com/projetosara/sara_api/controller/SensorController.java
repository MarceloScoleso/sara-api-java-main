package com.projetosara.sara_api.controller;

import com.projetosara.sara_api.dto.SensorDTO;
import com.projetosara.sara_api.service.SensorService;
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
@RequestMapping("/api/sensores")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService service;

    @GetMapping
    public Page<SensorDTO> listar(
            @RequestParam(required = false)
            @Parameter(description = "ID do Tipo de Sensor") Long tipoSensorId,

            @ParameterObject
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {

        return service.listar(tipoSensorId, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDTO> buscarPorId(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SensorDTO> salvar(@RequestBody @Valid SensorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorDTO> atualizar(@PathVariable Long id, @RequestBody @Valid SensorDTO dto) {
        return service.update(id, dto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo/{tipoSensorId}")
    public Page<SensorDTO> listarPorTipo(
            @PathVariable Long tipoSensorId,
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        return service.listarPorTipo(tipoSensorId, pageable);
    }

    @GetMapping("/count-by-localizacao")
    public ResponseEntity<Long> contarPorLocalizacao(@RequestParam Long localizacaoId) {
        Long count = service.contarPorLocalizacao(localizacaoId);
        return count == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(count);
    }
}