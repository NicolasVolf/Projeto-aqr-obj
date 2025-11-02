package com.pelo.insperscore.campeonatos.dto;

import java.util.List;

public record UpdateCampeonatoDTO(
        String nome,
        List<Integer> timeIds,
        List<Integer> partidaIds
) {

}
