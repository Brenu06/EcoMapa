package com.br.farney.crudapi.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.farney.crudapi.model.PontoColeta;
import com.br.farney.crudapi.model.RegistroDescarte;
import com.br.farney.crudapi.model.Usuario;
import com.br.farney.crudapi.repository.PontoColetaRepository;
import com.br.farney.crudapi.repository.RegistroDescarteRepository;
import com.br.farney.crudapi.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final UsuarioRepository usuarioRepository;
    private final PontoColetaRepository pontoColetaRepository;
    private final RegistroDescarteRepository registroDescarteRepository;

    @GetMapping("/organizacao/{usuarioId}")
    public ResponseEntity<Map<String, Object>> dashboardOrganizacao(@PathVariable Long usuarioId) {
        Usuario responsavel = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        List<PontoColeta> pontos = pontoColetaRepository.findAll().stream()
                .filter(p -> p.getResponsavel() != null && usuarioId.equals(p.getResponsavel().getId()))
                .toList();

        BigDecimal totalKg = BigDecimal.ZERO;
        BigDecimal totalCo2 = BigDecimal.ZERO;
        int totalRegistros = 0;

        for (PontoColeta p : pontos) {
            List<RegistroDescarte> registros = registroDescarteRepository.findByPontoColeta(p);
            for (RegistroDescarte r : registros) {
                if (r.getQuantidadeKg() != null) {
                    totalKg = totalKg.add(r.getQuantidadeKg());
                }
                if (r.getImpactoCo2Kg() != null) {
                    totalCo2 = totalCo2.add(r.getImpactoCo2Kg());
                }
                totalRegistros++;
            }
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("organizacao", responsavel.getNome());
        payload.put("totalPontosColeta", pontos.size());
        payload.put("totalRegistrosDescarte", totalRegistros);
        payload.put("totalKgMateriais", totalKg);
        payload.put("totalCo2EvitadoKg", totalCo2);

        return ResponseEntity.ok(payload);
    }
}
