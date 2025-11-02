package com.pelo.insperscore.estadios.dto;

import java.util.List;

public record UpdateEstadioDTO(
        String nome,
        Integer timeId,
        List<Integer> partidaIds
) {

}

