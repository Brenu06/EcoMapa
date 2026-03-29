package com.br.farney.crudapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.farney.crudapi.model.ConteudoEducativo;
import com.br.farney.crudapi.repository.ConteudoEducativoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/conteudos-educativos")
@RequiredArgsConstructor
public class ConteudoEducativoController {

    private final ConteudoEducativoRepository conteudoEducativoRepository;

    @GetMapping
    public ResponseEntity<List<ConteudoEducativo>> listar() {
        return ResponseEntity.ok(conteudoEducativoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<ConteudoEducativo> criar(@RequestBody ConteudoEducativo conteudo) {
        return ResponseEntity.ok(conteudoEducativoRepository.save(conteudo));
    }
}
