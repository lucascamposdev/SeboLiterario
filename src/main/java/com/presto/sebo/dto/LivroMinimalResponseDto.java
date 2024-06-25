package com.presto.sebo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LivroMinimalResponseDto {

    private Long id;
    private String titulo;
    private BigDecimal preco;
    private Integer quantidade;
}
