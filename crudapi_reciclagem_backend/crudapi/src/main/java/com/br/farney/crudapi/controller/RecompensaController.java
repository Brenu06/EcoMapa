package com.br.farney.crudapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.br.farney.crudapi.dto.ResgateRecompensaRequest;
import com.br.farney.crudapi.model.Recompensa;
import com.br.farney.crudapi.model.TransacaoPontos;
import com.br.farney.crudapi.model.TipoTransacaoPontos;
import com.br.farney.crudapi.model.Usuario;
import com.br.farney.crudapi.repository.RecompensaRepository;
import com.br.farney.crudapi.repository.TransacaoPontosRepository;
import com.br.farney.crudapi.repository.UsuarioRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recompensas")
@RequiredArgsConstructor
@Validated
public class RecompensaController {

    private final RecompensaRepository recompensaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TransacaoPontosRepository transacaoPontosRepository;

    @GetMapping
    public ResponseEntity<List<Recompensa>> listar() {
        return ResponseEntity.ok(recompensaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Recompensa> criar(@RequestBody Recompensa recompensa) {
        return ResponseEntity.ok(recompensaRepository.save(recompensa));
    }

    @PostMapping("/{id}/resgatar")
    public ResponseEntity<String> resgatar(@PathVariable Long id, @Valid @RequestBody ResgateRecompensaRequest request) {
        Recompensa recompensa = recompensaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recompensa não encontrada"));

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!recompensa.isAtivo()) {
            return ResponseEntity.badRequest().body("Recompensa inativa");
        }

        if (usuario.getSaldoPontos() < recompensa.getPontosNecessarios()) {
            return ResponseEntity.badRequest().body("Pontos insuficientes para resgatar esta recompensa");
        }

        usuario.setSaldoPontos(usuario.getSaldoPontos() - recompensa.getPontosNecessarios());
        usuarioRepository.save(usuario);

        TransacaoPontos tx = new TransacaoPontos();
        tx.setUsuario(usuario);
        tx.setTipo(TipoTransacaoPontos.RESGATE);
        tx.setPontos(recompensa.getPontosNecessarios());
        tx.setDescricao("Resgate da recompensa: " + recompensa.getNome());
        tx.setRecompensa(recompensa);
        transacaoPontosRepository.save(tx);

        return ResponseEntity.ok("Recompensa resgatada com sucesso");
    }
}
