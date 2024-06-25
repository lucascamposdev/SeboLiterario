package com.presto.sebo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.presto.sebo.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "transacoes")
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    private BigDecimal valor;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    @JsonBackReference
    private Livro livro;

}
