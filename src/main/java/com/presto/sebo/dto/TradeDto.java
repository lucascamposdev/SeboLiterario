package com.presto.sebo.dto;

import com.presto.sebo.entities.Livro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TradeDto {

    @NotNull
    @Valid
    private Livro livroRecebido;
    @NotNull
    private Long livroTrocadoId;
}
