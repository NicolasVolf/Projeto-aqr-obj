package com.pelo.insperscore.partidas.dto;

import java.time.LocalDate;

public record UpdatePartidaDTO(
        LocalDate data,
        String resultado,
        Integer campeonatoId,
        Integer estadioId,
        Integer timeMandanteId,
        Integer timeVisitanteId
) {

}

