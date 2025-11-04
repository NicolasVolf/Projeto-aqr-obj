package com.pelo.insperscore.times.dto;

import com.pelo.insperscore.times.Times;
import java.util.List;
import java.util.stream.Collectors;

public record TimeResponseDTO(
        Integer id,
        String nome,
        List<String> titulos,
        List<CampeonatoSimplificadoDTO> campeonatos,
        List<JogadorSimplificadoDTO> jogadores
) {
    public static TimeResponseDTO fromEntity(Times times) {
        List<String> titulosDto = times.getTitulos() == null ? List.of() : times.getTitulos();

        List<CampeonatoSimplificadoDTO> campeonatosDto = times.getCampeonatos() == null ? List.of() :
                times.getCampeonatos()
                        .stream()
                        .map(c -> new CampeonatoSimplificadoDTO(c.getId(), c.getNome()))
                        .collect(Collectors.toList());

        List<JogadorSimplificadoDTO> jogadoresDto = times.getJogadores() == null ? List.of() :
                times.getJogadores()
                        .stream()
                        .map(j -> new JogadorSimplificadoDTO(
                                j.getId(),
                                j.getNome(),
                                j.getPosicao(),
                                j.getNumero()
                        ))
                        .collect(Collectors.toList());

        return new TimeResponseDTO(
                times.getId(),
                times.getNome(),
                titulosDto,
                campeonatosDto,
                jogadoresDto
        );
    }

    public TimeResponseDTO toDto(Times times) {
        return fromEntity(times);
    }

    // DTO simplificado para campeonato (evita referência circular)
    public record CampeonatoSimplificadoDTO(
            Integer id,
            String nome
    ) {}

    // DTO simplificado para jogador (evita referência circular)
    public record JogadorSimplificadoDTO(
            Integer id,
            String nome,
            String posicao,
            Integer numero
    ) {}
}



