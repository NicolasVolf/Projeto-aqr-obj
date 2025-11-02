package com.pelo.insperscore.times.dto;

import java.util.List;

public record UpdateTimeDTO(
        String nome,
        List<String> titulos,
        List<Integer> campeonatoIds,
        List<Integer> jogadorIds
) {

}

