package com.br.farney.crudapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.br.farney.crudapi.dto.AgendamentoColetaRequest;
import com.br.farney.crudapi.model.AgendamentoColeta;
import com.br.farney.crudapi.model.StatusAgendamento;
import com.br.farney.crudapi.model.Usuario;
import com.br.farney.crudapi.repository.AgendamentoColetaRepository;
import com.br.farney.crudapi.repository.ParceiroLogisticaRepository;
import com.br.farney.crudapi.repository.PontoColetaRepository;
import com.br.farney.crudapi.repository.UsuarioRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
@Validated
public class AgendamentoColetaController {

    private final AgendamentoColetaRepository agendamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PontoColetaRepository pontoColetaRepository;
    private final ParceiroLogisticaRepository parceiroLogisticaRepository;

    @PostMapping
    public ResponseEntity<AgendamentoColeta> criar(@Valid @RequestBody AgendamentoColetaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        AgendamentoColeta novo = new AgendamentoColeta();
        novo.setUsuario(usuario);
        if (request.getPontoColetaId() != null) {
            pontoColetaRepository.findById(request.getPontoColetaId())
                    .ifPresent(novo::setPontoColeta);
        }
        if (request.getParceiroLogisticaId() != null) {
            parceiroLogisticaRepository.findById(request.getParceiroLogisticaId())
                    .ifPresent(novo::setParceiroLogistica);
        }

        novo.setMateriais(request.getMateriais());
        novo.setObservacoes(request.getObservacoes());
        novo.setDataAgendada(request.getDataAgendada());

        return ResponseEntity.ok(agendamentoRepository.save(novo));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoColeta>> listar(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) StatusAgendamento status) {

        if (usuarioId != null) {
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            var lista = agendamentoRepository.findByUsuario(usuario);
            if (status != null) {
                lista = lista.stream().filter(a -> status.equals(a.getStatus())).toList();
            }
            return ResponseEntity.ok(lista);
        }

        if (status != null) {
            return ResponseEntity.ok(agendamentoRepository.findByStatus(status));
        }

        return ResponseEntity.ok(agendamentoRepository.findAll());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AgendamentoColeta> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusAgendamento status) {

        return agendamentoRepository.findById(id)
                .map(agendamento -> {
                    agendamento.setStatus(status);
                    return ResponseEntity.ok(agendamentoRepository.save(agendamento));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
