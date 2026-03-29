package com.br.farney.crudapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.farney.crudapi.model.ParceiroTecnologia;
import com.br.farney.crudapi.model.TipoDispositivoEletronico;
import com.br.farney.crudapi.repository.ParceiroTecnologiaRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reciclagem-tecnologia")
@RequiredArgsConstructor
public class ReciclagemTecnologiaController {

    private final ParceiroTecnologiaRepository parceiroTecnologiaRepository;

    @GetMapping
    public ResponseEntity<List<ParceiroTecnologia>> listar(
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) TipoDispositivoEletronico tipoDispositivo) {

        List<ParceiroTecnologia> base;

        if (cidade != null && !cidade.isBlank()) {
            base = parceiroTecnologiaRepository.findByCidadeIgnoreCase(cidade);
        } else {
            base = parceiroTecnologiaRepository.findAll();
        }

        if (tipoDispositivo != null) {
            base = base.stream()
                    .filter(p -> p.getTiposDispositivos() != null && p.getTiposDispositivos().contains(tipoDispositivo))
                    .toList();
        }

        return ResponseEntity.ok(base);
    }
}
