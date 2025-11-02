package com.pelo.insperscore.jogadores.dto;

import com.pelo.insperscore.jogadores.Jogadores;

public record JogadorResponseDTO(
        Integer id,
        String nome,
        String posicao,
        Integer numero,
        Integer idade,
        String nacionalidade
) {
    public JogadorResponseDTO toDto(Jogadores jogadores) {
        return new JogadorResponseDTO(
                jogadores.getId(),
                jogadores.getNome(),
                jogadores.getPosicao(),
                jogadores.getNumero(),
                jogadores.getIdade(),
                jogadores.getNacionalidade()
        );
    }
}

