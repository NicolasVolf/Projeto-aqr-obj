package com.pelo.insperscore.jogadores.dto;

import java.util.List;

public record UpdateJogadorDTO(
        String nome,
        String posicao,
        Integer numero,
        List<String> titulos,
        Integer idade,
        String nacionalidade,
        Integer timeId
) {

}

