package com.pelo.insperscore.partidas.dto;

import com.pelo.insperscore.partidas.Partidas;

import java.time.LocalDate;

public record PartidaResponseDTO(
        Integer id,
        LocalDate data,
        String resultado
) {
    public PartidaResponseDTO toDto(Partidas partidas) {
        return new PartidaResponseDTO(
                partidas.getId(),
                partidas.getData(),
                partidas.getResultado()
        );
    }
}

