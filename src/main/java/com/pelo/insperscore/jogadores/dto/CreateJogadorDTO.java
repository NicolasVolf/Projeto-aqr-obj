package com.pelo.insperscore.jogadores.dto;

import java.util.List;

public record CreateJogadorDTO(
        String nome,
        String posicao,
        Integer numero,
        List<String> titulos,
        Integer idade,
        String nacionalidade,
        Integer timeId
) {

}

