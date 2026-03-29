package com.br.farney.crudapi.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agendamentos_coleta")
public class AgendamentoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"senha", "saldoPontos"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ponto_coleta_id")
    @JsonIgnoreProperties({"responsavel"})
    private PontoColeta pontoColeta;

    @ManyToOne
    @JoinColumn(name = "parceiro_logistica_id")
    private ParceiroLogistica parceiroLogistica;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "agendamento_materiais", joinColumns = @JoinColumn(name = "agendamento_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "material")
    private Set<MaterialTipo> materiais = new HashSet<>();

    private String observacoes;

    private LocalDateTime dataSolicitacao = LocalDateTime.now();
    private LocalDateTime dataAgendada;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status = StatusAgendamento.PENDENTE;
}
