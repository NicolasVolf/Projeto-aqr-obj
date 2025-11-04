package com.pelo.insperscore.jogadores.dto;

import com.pelo.insperscore.jogadores.Jogadores;

public record JogadorResponseDTO(
        Integer id,
        String nome,
        String posicao,
        Integer numero,
        Integer idade,
        String nacionalidade,
        TimeSimplificadoDTO time
) {
    public static JogadorResponseDTO fromEntity(Jogadores jogadores) {
        TimeSimplificadoDTO timeDto = jogadores.getTime() == null ? null :
                new TimeSimplificadoDTO(jogadores.getTime().getId(), jogadores.getTime().getNome());

        return new JogadorResponseDTO(
                jogadores.getId(),
                jogadores.getNome(),
                jogadores.getPosicao(),
                jogadores.getNumero(),
                jogadores.getIdade(),
                jogadores.getNacionalidade(),
                timeDto
        );
    }

    // Mantém compatibilidade com método antigo
    public JogadorResponseDTO toDto(Jogadores jogadores) {
        return fromEntity(jogadores);
    }

    // DTO simplificado para time (evita referência circular)
    public record TimeSimplificadoDTO(
            Integer id,
            String nome
    ) {}
}

