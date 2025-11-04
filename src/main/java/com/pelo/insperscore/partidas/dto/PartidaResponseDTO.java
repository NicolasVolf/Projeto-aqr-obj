package com.pelo.insperscore.partidas.dto;

import com.pelo.insperscore.partidas.Partidas;
import com.pelo.insperscore.jogadores.dto.JogadorResponseDTO;
import com.pelo.insperscore.campeonatos.dto.CampeonatoResponseDTO;
import com.pelo.insperscore.estadios.dto.EstadioResponseDTO;
import com.pelo.insperscore.times.dto.TimeResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record PartidaResponseDTO(
        Integer id,
        LocalDate data,
        String resultado,
        List<JogadorResponseDTO> jogadores,
        CampeonatoResponseDTO campeonato,
        EstadioResponseDTO estadio,
        TimeResponseDTO timeMandante,
        TimeResponseDTO timeVisitante
) {
    // Converte a entidade Partidas para este DTO, incluindo relacionamentos
    public static PartidaResponseDTO fromEntity(Partidas partidas) {
        List<JogadorResponseDTO> jogadoresDto = partidas.getJogadores() == null ? List.of() : partidas.getJogadores()
                .stream()
                .map(j -> new JogadorResponseDTO(
                        j.getId(),
                        j.getNome(),
                        j.getPosicao(),
                        j.getNumero(),
                        j.getIdade(),
                        j.getNacionalidade()
                ))
                .collect(Collectors.toList());

        CampeonatoResponseDTO campeonatoDto = partidas.getCampeonato() == null ? null :
                new CampeonatoResponseDTO(partidas.getCampeonato().getId(), partidas.getCampeonato().getNome());

        EstadioResponseDTO estadioDto = partidas.getEstadio() == null ? null :
                new EstadioResponseDTO(partidas.getEstadio().getId(), partidas.getEstadio().getNome());

        TimeResponseDTO mandanteDto = partidas.getTimeMandante() == null ? null :
                new TimeResponseDTO(partidas.getTimeMandante().getId(), partidas.getTimeMandante().getNome());

        TimeResponseDTO visitanteDto = partidas.getTimeVisitante() == null ? null :
                new TimeResponseDTO(partidas.getTimeVisitante().getId(), partidas.getTimeVisitante().getNome());

        return new PartidaResponseDTO(
                partidas.getId(),
                partidas.getData(),
                partidas.getResultado(),
                jogadoresDto,
                campeonatoDto,
                estadioDto,
                mandanteDto,
                visitanteDto
        );
    }
}
