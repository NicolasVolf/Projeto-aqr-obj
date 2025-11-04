package com.pelo.insperscore.partidas.dto;

import com.pelo.insperscore.partidas.Partidas;
import com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO;
import com.pelo.insperscore.estadios.dto.EstadioResponseDTO;

import java.time.LocalDate;

public record PartidaResponseDTO(
        Integer id,
        LocalDate data,
        String resultado,
        CampeonatoResponseDTO campeonato,
        EstadioResponseDTO estadio,
        TimeSimplificadoDTO timeMandante,
        TimeSimplificadoDTO timeVisitante
) {
    // Converte a entidade Partidas para este DTO, incluindo relacionamentos
    public static PartidaResponseDTO fromEntity(Partidas partidas) {
        CampeonatoResponseDTO campeonatoDto = partidas.getCampeonato() == null ? null :
                new CampeonatoResponseDTO(partidas.getCampeonato().getId(), partidas.getCampeonato().getNome());

        EstadioResponseDTO estadioDto = partidas.getEstadio() == null ? null :
                new EstadioResponseDTO(partidas.getEstadio().getId(), partidas.getEstadio().getNome());

        TimeSimplificadoDTO mandanteDto = partidas.getTimeMandante() == null ? null :
                new TimeSimplificadoDTO(partidas.getTimeMandante().getId(), partidas.getTimeMandante().getNome());

        TimeSimplificadoDTO visitanteDto = partidas.getTimeVisitante() == null ? null :
                new TimeSimplificadoDTO(partidas.getTimeVisitante().getId(), partidas.getTimeVisitante().getNome());

        return new PartidaResponseDTO(
                partidas.getId(),
                partidas.getData(),
                partidas.getResultado(),
                campeonatoDto,
                estadioDto,
                mandanteDto,
                visitanteDto
        );
    }

    // DTO simplificado para time (evita referência circular)
    public record TimeSimplificadoDTO(
            Integer id,
            String nome
    ) {}
}

