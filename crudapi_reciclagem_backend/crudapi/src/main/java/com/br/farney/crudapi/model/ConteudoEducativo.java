package com.br.farney.crudapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conteudos_educativos")
public class ConteudoEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoConteudoEducativo tipo;

    @Column(length = 2000)
    private String resumo;

    /**
     * Pode apontar para um PDF, vídeo no YouTube, artigo etc.
     */
    private String url;
}
