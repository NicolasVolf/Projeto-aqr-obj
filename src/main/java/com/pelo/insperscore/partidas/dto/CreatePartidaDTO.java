package com.pelo.insperscore.partidas.dto;

import java.time.LocalDate;
import java.util.List;

public record CreatePartidaDTO(
        LocalDate data,
        String resultado,
        List<Integer> jogadorIds,
        Integer campeonatoId,
        Integer estadioId,
        Integer timeMandanteId,
        Integer timeVisitanteId
) {

}

