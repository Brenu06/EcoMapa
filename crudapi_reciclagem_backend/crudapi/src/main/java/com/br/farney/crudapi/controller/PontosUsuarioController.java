package com.br.farney.crudapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.farney.crudapi.model.TransacaoPontos;
import com.br.farney.crudapi.model.Usuario;
import com.br.farney.crudapi.repository.TransacaoPontosRepository;
import com.br.farney.crudapi.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class PontosUsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final TransacaoPontosRepository transacaoPontosRepository;

    @GetMapping("/{id}/pontos")
    public ResponseEntity<Integer> saldoPontos(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return ResponseEntity.ok(usuario.getSaldoPontos());
    }

    @GetMapping("/{id}/transacoes")
    public ResponseEntity<List<TransacaoPontos>> historico(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return ResponseEntity.ok(transacaoPontosRepository.findByUsuarioOrderByDataHoraDesc(usuario));
    }
}
