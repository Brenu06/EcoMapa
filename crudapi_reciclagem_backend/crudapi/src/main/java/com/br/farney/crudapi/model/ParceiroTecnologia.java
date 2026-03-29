package com.br.farney.crudapi.model;

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
@Table(name = "parceiros_tecnologia")
public class ParceiroTecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 1000)
    private String descricao;

    private String endereco;
    private String cidade;
    private String estado;
    private String cep;

    private String telefone;
    private String email;
    private String site;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "parceiro_tecnologia_dispositivos", joinColumns = @JoinColumn(name = "parceiro_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_dispositivo")
    private Set<TipoDispositivoEletronico> tiposDispositivos = new HashSet<>();

    /**
     * Indica se o parceiro aceita doações de equipamentos funcionando.
     */
    private boolean aceitaDoacoes;

    /**
     * Usuário responsável / dono desse parceiro na plataforma.
     */
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    @JsonIgnoreProperties({"senha", "saldoPontos"})
    private Usuario responsavel;
}
