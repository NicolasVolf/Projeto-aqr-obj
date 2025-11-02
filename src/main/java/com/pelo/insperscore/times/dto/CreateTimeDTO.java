package com.pelo.insperscore.times.dto;

import java.util.List;

public record CreateTimeDTO(
        String nome,
        List<String> titulos
) {

}

