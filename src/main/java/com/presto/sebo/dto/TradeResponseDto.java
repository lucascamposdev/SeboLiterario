package com.presto.sebo.dto;

import com.presto.sebo.entities.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TradeResponseDto {
    private LivroMinimalResponseDto livroRecebido;
    private LivroMinimalResponseDto livroTrocado;
}
