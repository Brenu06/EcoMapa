package com.br.farney.crudapi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.br.farney.crudapi.dto.RegistroDescarteRequest;
import com.br.farney.crudapi.model.PontoColeta;
import com.br.farney.crudapi.model.RegistroDescarte;
import com.br.farney.crudapi.model.TransacaoPontos;
import com.br.farney.crudapi.model.TipoTransacaoPontos;
import com.br.farney.crudapi.model.Usuario;
import com.br.farney.crudapi.repository.PontoColetaRepository;
import com.br.farney.crudapi.repository.RegistroDescarteRepository;
import com.br.farney.crudapi.repository.TransacaoPontosRepository;
import com.br.farney.crudapi.repository.UsuarioRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/descarte")
@RequiredArgsConstructor
@Validated
public class RegistroDescarteController {

    private final RegistroDescarteRepository registroDescarteRepository;
    private final UsuarioRepository usuarioRepository;
    private final PontoColetaRepository pontoColetaRepository;
    private final TransacaoPontosRepository transacaoPontosRepository;

    @PostMapping
    public ResponseEntity<RegistroDescarte> registrar(@Valid @RequestBody RegistroDescarteRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        PontoColeta ponto = pontoColetaRepository.findById(request.getPontoColetaId())
                .orElseThrow(() -> new IllegalArgumentException("Ponto de coleta não encontrado"));

        RegistroDescarte registro = new RegistroDescarte();
        registro.setUsuario(usuario);
        registro.setPontoColeta(ponto);
        registro.setMaterial(request.getMaterial());
        registro.setQuantidadeKg(request.getQuantidadeKg());

        // Cálculo fictício de impacto: 1.5 kg CO2 por kg de material
        BigDecimal fator = ponto.getFatorImpactoCo2PorKg() != null
                ? ponto.getFatorImpactoCo2PorKg()
                : BigDecimal.valueOf(1.5);
        BigDecimal impacto = fator.multiply(request.getQuantidadeKg());
        registro.setImpactoCo2Kg(impacto);

        // Pontos: 10 pontos por kg
        int pontos = request.getQuantidadeKg().multiply(BigDecimal.TEN).intValue();
        registro.setPontosGerados(pontos);

        registro = registroDescarteRepository.save(registro);

        usuario.setSaldoPontos(usuario.getSaldoPontos() + pontos);
        usuarioRepository.save(usuario);

        TransacaoPontos tx = new TransacaoPontos();
        tx.setUsuario(usuario);
        tx.setTipo(TipoTransacaoPontos.GANHO);
        tx.setPontos(pontos);
        tx.setDescricao("Pontos gerados pelo descarte de " + request.getMaterial());
        tx.setRegistroDescarte(registro);
        transacaoPontosRepository.save(tx);

        return ResponseEntity.ok(registro);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<RegistroDescarte>> listarPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return ResponseEntity.ok(registroDescarteRepository.findByUsuario(usuario));
    }
}
