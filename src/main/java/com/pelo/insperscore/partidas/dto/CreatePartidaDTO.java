package com.pelo.insperscore.partidas.dto;

import java.time.LocalDate;

public record CreatePartidaDTO(
        LocalDate data,
        String resultado,
        Integer campeonatoId,
        Integer estadioId,
        Integer timeMandanteId,
        Integer timeVisitanteId
) {

}

