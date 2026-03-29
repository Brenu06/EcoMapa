package com.br.farney.crudapi.model;

import java.math.BigDecimal;
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
@Table(name = "pontos_coleta")
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private String endereco;

    private String cidade;
    private String estado;
    private String cep;

    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPontoColeta tipoPonto;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ponto_coleta_materiais", joinColumns = @JoinColumn(name = "ponto_coleta_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "material")
    private Set<MaterialTipo> materiaisAceitos = new HashSet<>();

    /**
     * Ex: "Seg a Sex - 08h às 18h"
     */
    private String horarioFuncionamento;

    /**
     * Ex: "Entrega presencial", "Drive-thru", etc.
     */
    private String formasEntrega;

    /**
     * Responsável pelo ponto: empresa/ONG/cooperativa.
     */
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    @JsonIgnoreProperties({"senha", "saldoPontos"})
    private Usuario responsavel;

    /**
     * Valor médio de CO2 evitado (em kg) por kg de material coletado.
     * Fictício, apenas para cálculo de impacto.
     */
    private BigDecimal fatorImpactoCo2PorKg;
}
