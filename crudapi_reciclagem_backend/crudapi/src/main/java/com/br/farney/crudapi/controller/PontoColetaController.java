package com.br.farney.crudapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.farney.crudapi.model.MaterialTipo;
import com.br.farney.crudapi.model.PontoColeta;
import com.br.farney.crudapi.model.TipoPontoColeta;
import com.br.farney.crudapi.repository.PontoColetaRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pontos-coleta")
@RequiredArgsConstructor
public class PontoColetaController {

    private final PontoColetaRepository pontoColetaRepository;

    @GetMapping
    public ResponseEntity<List<PontoColeta>> listar(
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) MaterialTipo material,
            @RequestParam(required = false) TipoPontoColeta tipoPonto) {

        List<PontoColeta> base;

        if (cidade != null && !cidade.isBlank()) {
            base = pontoColetaRepository.findByCidadeIgnoreCase(cidade);
        } else {
            base = pontoColetaRepository.findAll();
        }

        if (material != null) {
            base = base.stream()
                    .filter(p -> p.getMateriaisAceitos() != null && p.getMateriaisAceitos().contains(material))
                    .toList();
        }

        if (tipoPonto != null) {
            base = base.stream()
                    .filter(p -> tipoPonto.equals(p.getTipoPonto()))
                    .toList();
        }

        return ResponseEntity.ok(base);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoColeta> buscarPorId(@PathVariable Long id) {
        return pontoColetaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PontoColeta> criar(@RequestBody PontoColeta pontoColeta) {
        return ResponseEntity.ok(pontoColetaRepository.save(pontoColeta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PontoColeta> atualizar(@PathVariable Long id, @RequestBody PontoColeta atualizado) {
        return pontoColetaRepository.findById(id)
                .map(existing -> {
                    atualizado.setId(existing.getId());
                    return ResponseEntity.ok(pontoColetaRepository.save(atualizado));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!pontoColetaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pontoColetaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
