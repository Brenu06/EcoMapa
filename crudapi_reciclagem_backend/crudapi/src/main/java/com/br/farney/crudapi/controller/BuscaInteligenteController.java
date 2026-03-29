package com.br.farney.crudapi.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.farney.crudapi.dto.BuscaInteligenteRequest;
import com.br.farney.crudapi.model.PontoColeta;
import com.br.farney.crudapi.repository.PontoColetaRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/busca")
@RequiredArgsConstructor
@Validated
public class BuscaInteligenteController {

    private final PontoColetaRepository pontoColetaRepository;

    @PostMapping("/inteligente")
    public ResponseEntity<List<PontoColeta>> buscar(@Valid @RequestBody BuscaInteligenteRequest request) {
        var pontos = pontoColetaRepository.findByMateriaisAceitosContaining(request.getMaterial());

        Double lat = request.getLatitude();
        Double lng = request.getLongitude();
        Double raioKm = request.getRaioKm() != null ? request.getRaioKm() : 10.0;

        var filtrados = pontos.stream()
                .filter(p -> p.getLatitude() != null && p.getLongitude() != null)
                .filter(p -> distanceInKm(lat, lng, p.getLatitude(), p.getLongitude()) <= raioKm)
                .sorted(Comparator.comparingDouble(p -> distanceInKm(lat, lng, p.getLatitude(), p.getLongitude())))
                .toList();

        return ResponseEntity.ok(filtrados);
    }

    private static double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // raio da Terra em km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
